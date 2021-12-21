package com.ni.publishDao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.nic.publishModel.DetailsModel;

import sun.misc.BASE64Decoder;

public class ViewDAO {
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;

	public ViewDAO(Connection con) {
		super();
		this.con = con;
	}

	public List<DetailsModel> getAllDetails() {
		List<DetailsModel> list = new ArrayList<DetailsModel>();

		try {
			query = "SELECT * FROM pi_details;";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				DetailsModel detailsModel = new DetailsModel();
				detailsModel.setId(rs.getInt("id"));
				detailsModel.setCategory(rs.getString("pi_categoty"));
				detailsModel.setSubcategory(rs.getString("pi_sub_category"));
				detailsModel.setDescription(rs.getString("pi_desc"));
				detailsModel.setPublishDate(rs.getDate("publish_date"));
				detailsModel.setFromDate(rs.getDate("validate_from"));
				detailsModel.setToDate(rs.getDate("validate_to"));

				list.add(detailsModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<DetailsModel> getAllData() {
		List<DetailsModel> list = new ArrayList<DetailsModel>();

		try {
			query = "SELECT * FROM pi_details;";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				DetailsModel detailsModel = new DetailsModel();
				detailsModel.setId(rs.getInt("id"));
				detailsModel.setCategory(rs.getString("pi_categoty"));
				detailsModel.setSubcategory(rs.getString("pi_sub_category"));
				detailsModel.setDescription(rs.getString("pi_desc"));
				detailsModel.setPublishDate(rs.getDate("publish_date"));
				detailsModel.setFromDate(rs.getDate("validate_from"));
				detailsModel.setToDate(rs.getDate("validate_to"));
				byte[] biteToRead = rs.getBytes("content");

				list.add(detailsModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public byte[] getDoc(int id, HttpServletResponse response) {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline; filename=document.pdf");
		query = "SELECT content FROM pi_details where id=?;";

		try {
			

			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next())
				return rs.getBytes("content");
			/*
			 * OutputStream out;
			 * 
			 * out = new FileOutputStream("out.pdf"); out.write(rs.getBytes(""));
			 * out.close();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
