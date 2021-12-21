package com.nic.publishServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ni.publishDao.DBCon;
import com.ni.publishDao.ViewDAO;
import com.nic.publishModel.Category;
import com.nic.publishModel.DetailsModel;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Dao Class
		ViewDAO dao = new ViewDAO(DBCon.getCon());
		String op = request.getParameter("operation");

		if (op.equals("get_details")) {

			List<DetailsModel> dlist = dao.getAllData();
			Gson json = new Gson();
			String detailsList = json.toJson(dlist);
			response.setContentType("text/html");
			response.getWriter().write(detailsList);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// URL
		String action = request.getServletPath();
		if (action == "/file") {
			int a = Integer.parseInt(request.getParameter("id"));
			System.out.print(a);
		}
		doGet(request, response);
	}

}
