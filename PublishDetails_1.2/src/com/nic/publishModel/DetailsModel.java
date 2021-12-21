package com.nic.publishModel;

import java.io.OutputStream;
import java.sql.Date;

public class DetailsModel {
	private int id;
	private String category, subcategory, description;
	private Date publishDate, fromDate, toDate;
	private byte[] file;
	private OutputStream doc;

	public DetailsModel() {
		super();
	}

	public DetailsModel(int id, String category, String subcategory, String description, Date publishDate,
			Date fromDate, Date toDate, OutputStream doc) {
		super();
		this.id = id;
		this.category = category;
		this.subcategory = subcategory;
		this.description = description;
		this.publishDate = publishDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.doc = doc;
	}

	public DetailsModel(int id, String category, String subcategory, String description, Date publishDate,
			Date fromDate, Date toDate) {
		super();
		this.id = id;
		this.category = category;
		this.subcategory = subcategory;
		this.description = description;
		this.publishDate = publishDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public DetailsModel(String category, String subcategory, String description, Date publishDate, Date fromDate,
			Date toDate, byte[] file) {
		super();
		this.category = category;
		this.subcategory = subcategory;
		this.description = description;
		this.publishDate = publishDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public OutputStream getDoc() {
		return doc;
	}

	public void setDoc(OutputStream doc) {
		this.doc = doc;
	}
}
