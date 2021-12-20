package com.ni.publishDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nic.publishModel.DetailsModel;

public class PublishDAO {
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;
	
	public PublishDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public int uploadData(DetailsModel model) {
		int success=0;
		try {
			query = "insert into pi_details (pi_categoty,pi_sub_category,pi_desc,publish_date,"
					+ "validate_from,validate_to,content) values(?,?,?,?,?,?,?);";
			pst = this.con.prepareStatement(query);

			pst.setString(1, model.getCategory());
			pst.setString(2, model.getSubcategory());
			pst.setString(3, model.getDescription());
			pst.setDate(4, model.getPublishDate());
			pst.setDate(5,model.getFromDate());
			pst.setDate(6,model.getToDate());
			pst.setBytes(7, model.getFile());
			System.out.println(pst);
			success = pst.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public String selectCategory(int stateId) {
		String state=null;
		// Step 1: Establishing a Connection
		
		try  {
			query = "select item_desc from pi_category where item_code=?;";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, stateId);
			System.out.println(pst);
			// Step 3: Execute the query or update query
			rs = pst.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				state=rs.getString("item_desc");
			}
		} catch (SQLException e) {
			System.out.println("err : "+e);
		}
		return state;
	}
	
	public String selectSubCategory(int subCateId) {
		String city = null;
		// Step 1: Establishing a Connection
		try  {
			query = "select item_desc from pi_sub_category where item_code=?;";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, subCateId);
			System.out.println(pst);
			// Step 3: Execute the query or update query
			rs = pst.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				city=rs.getString("item_desc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return city;
	}
}
