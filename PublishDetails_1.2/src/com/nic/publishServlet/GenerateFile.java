package com.nic.publishServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ni.publishDao.DBCon;
import com.ni.publishDao.ViewDAO;

@WebServlet("/GenerateFile")
public class GenerateFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ViewDAO dao;
	
	public GenerateFile() {
		super();
		this.dao = new ViewDAO(DBCon.getCon());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream sos;
		int id = Integer.parseInt(request.getParameter("id"));
		
		sos = response.getOutputStream();
		sos.write(dao.getDoc(id, response));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
