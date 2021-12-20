<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Publish Form</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">  -->
<style type="text/css">
select {
	width: 50%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=text] {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit] {
	width: 20%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	align-content: center;
}

input[type=submit]:hover {
	background-color: #45a049;
}

div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
	padding-left: 300px;
}

textarea {
	width: 50%;
	height: 150px;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	padding: 12px 20px;
}
</style>
<!-- DatePicker Script -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script>
$(function() {
	var currentDate = new Date();
	$("#publishDate").datepicker({
		minDate : 0
	}).datepicker("setDate", currentDate);
});

$(function() {
	var currentDate = new Date();
	
	$("#datePicker").datepicker({
		minDate : 0
	}).datepicker("setDate", currentDate);
});
$(function() {
	var currentDate = new Date();
	$("#datePicker2").datepicker({
		minDate : 0
	}).datepicker("setDate", "");
});

$(function() {
	var category = document.getElementById("category");
    if (ddlFruits.value == "") {
        //If the "Please Select" option is selected display error.
        alert("Please select category!");
        return false;
    }
    return true;
});
</script>
</head>
<body>
	<h2 align="center">Publish Details</h2>
	<br>
	<div>
		<form method="POST" action="<%=request.getContextPath()%>/SubmitServlet" enctype="multipart/form-data">
			<label for="Category">Category<font color="red">*</font></label><br> <select
				id="category" name="category" required="required">
				<option value="">Select Category</option>
			</select><br> <label for="SubCategory">Sub Category<font color="red">*</font></label><br>
			<select id="sub_category" name="sub_category" required="required">
				<option value="">Select SubCategory</option>
			</select><br> 
			<label
				for="Description">Description<font color="red">*</font></label><br>
			<textarea id="describe" name="description" required="required"></textarea><br>
			<label for="Publish_Date">Publish Date<font color="red">*</font></label><br>
			<input type="text" id="publishDate" name="publishDate" required="required"><br>
			
			<label for="From-Date">Valid From<font color="red">*</font></label><br>
			<input type="text" id="datePicker" name="fromDate" required="required"><br> <label
				for="To-Date">Valid draw<font color="red">*</font></label><br> <input
				type="text" id="datePicker2" name="toDate" required="required"><br> 
			<br> <label for="File">Choose PDF(Maximum 2MB)<font color="red">*</font></label><br>
			
			<input type="file" id="file" name="file" required="required"><br> <br> <input
				type="submit" value="Publish News">
				
		</form>
	</div>
	<script type="text/javascript">
	$(document).ready(function () {
        $.ajax({
            url: "DropDownServlet",
            method: "GET",
            data: {operation: 'category'},
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                let obj = $.parseJSON(data);
                $.each(obj, function (key, value) {
                    $('#category').append('<option value="' + value.id + '">' + value.name + '</option>')
                });
                $('select').formSelect(); 
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#category').append('<option value="">Category Unavailable</option>');
            },
            cache: false
        });
        
        $('#category').change(function () {
            $('#sub_category').find('option').remove();
            $('#sub_category').append('<option value=""">Select SubCategory</option>'); 

            let cid = $('#category').val();
            let data = {operation: "sub_category",id: cid};
				
            $.ajax({
                url: "DropDownServlet",
                method: "GET",
                data: data,
                success: function (data, textStatus, jqXHR) {
                    console.log(data);
                    let obj = $.parseJSON(data);
                    $.each(obj, function (key, value) {
                        $('#sub_category').append('<option value="' + value.id + '">' + value.name + '</option>')
                    });
                    $('select').formSelect(); 
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#sub_category').append('<option value="">Category Unavailable</option>');
                },
                cache: false
            });
        });
        /* Validation Start and End Date */
        $("#datePicker2").change(function() {
			var startDate = document.getElementById("datePicker").value;
			var endDate = document.getElementById("datePicker2").value;

			if ((Date.parse(startDate) >= Date.parse(endDate))) {
				alert("End date should be greater than Start date");
				document.getElementById("datePicker2").value = "";
			}
		});

    });
	</script>
</body>
</html>