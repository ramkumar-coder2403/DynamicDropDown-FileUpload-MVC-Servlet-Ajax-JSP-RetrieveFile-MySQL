package com.nic.publishServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.ni.publishDao.CategoryDAO;
import com.ni.publishDao.DBCon;
import com.nic.publishModel.Category;
import com.nic.publishModel.SubCategory;


@WebServlet("/DropDownServlet")
public class DropDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoryDAO dao = new CategoryDAO(DBCon.getCon());

		String op = request.getParameter("operation");

		if (op.equals("category")) {

			List<Category> clist = dao.getAllCategory();
			Gson json = new Gson();
			String categoryList = json.toJson(clist);
			response.setContentType("text/html");
			response.getWriter().write(categoryList);
		}
		if (op.equals("sub_category")) {
            int id = Integer.parseInt(request.getParameter("id"));
            List<SubCategory> clist = dao.getSubCateBycateId(id);
            Gson json = new Gson();
            String categoryList = json.toJson(clist);
            response.setContentType("text/html");
            response.getWriter().write(categoryList);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
