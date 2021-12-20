package com.nic.publishServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ni.publishDao.DBCon;
import com.ni.publishDao.PublishDAO;
import com.nic.publishModel.DetailsModel;

@WebServlet("/SubmitServlet")
@MultipartConfig
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PublishDAO dao;

	public SubmitServlet() {
		super();
		this.dao = new PublishDAO(DBCon.getCon());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String category = null;
		String subCategory = null;
		int categoryId = Integer.parseInt(request.getParameter("category"));
		int subCategoryId = Integer.parseInt(request.getParameter("sub_category"));
		String description = request.getParameter("description");
		// Date getting part here....
		final Part filePart = request.getPart("file");
		
		// Date Conversion for Util task
		Date publishDate = null;
		Date fromDate = null;
		Date toDate = null;
		try {
			publishDate = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("publishDate"));
			fromDate = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("fromDate"));
			toDate = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("toDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Here is original date for SQL server
		java.sql.Date sqlpublishDate = new java.sql.Date(publishDate.getTime());
		java.sql.Date sqlfromDate = new java.sql.Date(fromDate.getTime());
		java.sql.Date sqltoDate = new java.sql.Date(toDate.getTime());
		// Here DropDown Options get from DataBase
		// DropDown Values get in DB
		if (categoryId != 0)
			category = dao.selectCategory(categoryId);
		if (subCategoryId != 0)
			subCategory = dao.selectSubCategory(subCategoryId);
		
		InputStream pdfFileBytes = null;
		final PrintWriter writer = response.getWriter();

		boolean checkFile = checkPdf(filePart);

		if (!checkFile) {
			writer.println("<b> Invalid File");
			writer.println("<br> File size too big");
			return;
		} else {
			pdfFileBytes = filePart.getInputStream(); // to get the body of the request as binary data

			final byte[] bytes = new byte[pdfFileBytes.available()];
			pdfFileBytes.read(bytes);

			DetailsModel model = new DetailsModel(category, subCategory,description,sqlpublishDate, sqlfromDate, sqltoDate,bytes);
			int res = dao.uploadData(model);
			if (res >= 1) {
				System.out.println("Inserted");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");
				dispatcher.forward(request, response);
			} else
				writer.println("<br>Not Inserted");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Check Pdf or not and it's size
	private boolean checkPdf(Part filePart) {
		try {
			if (!filePart.getContentType().equals("application/pdf"))
				return false;
			else if (filePart.getSize() > 1048576) // 2mb
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
