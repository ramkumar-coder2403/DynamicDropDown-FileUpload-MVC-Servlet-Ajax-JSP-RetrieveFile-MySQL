<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Data</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<style type="text/css">
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
</style>
</head>
<body>
	<div>
		<form action="<%=request.getContextPath()%>/ViewServlet" method="post">
			<table id="content">
				<tr>
					<th>Main Category</th>
					<th>Sub Category</th>
					<th>Description</th>
					<th>Publish Date</th>
					<th>Valid From</th>
					<th>Valid To</th>
					<th>Content</th>
				</tr>

			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$
									.ajax({

										url : "ViewServlet",
										method : "GET",
										data : {
											operation : 'get_details'
										},

										success : function(data, textStatus,
												jqXHR) {
											console.log(data);
											let obj = $.parseJSON(data);
											var link = document
													.createElement('download');
											var fileName = "doc.pdf";

											$
													.each(
															obj,
															function(key, value) {

																$('#content')
																		.append(
																				'<tr> <td>'
																						+ value.category
																						+ '</td> <td>'
																						+ value.subcategory
																						+ '</td> <td>'
																						+ value.description
																						+ '</td> <td>'
																						+ value.publishDate
																						+ '</td> <td>'
																						+ value.fromDate
																						+ '</td><td>'
																						+ value.toDate
																						+ '</td> <td><a href=GenerateFile?id=' +value.id
														+ '>Download</a></td>')
															});
											/* $('select').formSelect(); */
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											$('#content').append(
													'<td>null</td>');
										},
										cache : false
									});
						});
	</script>

</body>
</html>