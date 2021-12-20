package com.ni.publishDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.nic.publishModel.Category;
import com.nic.publishModel.SubCategory;

public class CategoryDAO {
	
	Connection con;
	PreparedStatement pst;
	String query;
	ResultSet rs;
	
	public CategoryDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<Category>();

		try {
			query = "select * from pi_category";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("item_code"));
				category.setName(rs.getString("item_desc"));
				list.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<SubCategory> getSubCateBycateId(int cateId) {
		List<SubCategory> list = new ArrayList<SubCategory>();

		try {
			query = "select * from pi_sub_category where main_item_code=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, cateId);
			rs = pst.executeQuery();
			while (rs.next()) {
				SubCategory subCategory = new SubCategory();
				subCategory.setId(rs.getInt("item_code"));
				subCategory.setcId(rs.getInt("main_item_code"));
				subCategory.setName(rs.getString("item_desc"));
				list.add(subCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
