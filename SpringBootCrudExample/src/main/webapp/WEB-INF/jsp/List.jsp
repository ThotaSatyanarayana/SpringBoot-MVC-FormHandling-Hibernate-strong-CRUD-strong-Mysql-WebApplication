
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="resources/css/layout.css">
<div class="site-inner">
	<h1 style="margin-left: 25%;">
		<a href="Register" style="text-decoration: none">Add Student</a>
	</h1>
	<table>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Gender</th>
			<th>District</th>
			<th>Address</th>
			<th>Subjects</th>
			<th>Photo</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.name }</td>
				<td>${student.email}</td>
				<td>${student.mobile}</td>
				<td>${student.gender}</td>
				<td>${student.district}</td>
				<td>${student.address}</td>
				<td>${student.subjects}</td>
				<td><img src="imagedisplay?student_id=${student.student_id}"
					width="200px;" height="200px;"></td>
				<td><a href="update?student_id=${student.student_id}"
					style="text-decoration: none">Update</a></td>
				<td><a href="delete?student_id=${student.student_id}"
					style="text-decoration: none">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<style>
table {
	margin-left: 5%;
	margin-top: 2%;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 15px;
	text-align: left;
}

table#t01 tr:nth-child(even) {
	background-color: #eee;
}

table#t01 tr:nth-child(odd) {
	background-color: #fff;
}

table#t01 th {
	background-color: black;
	color: white;
}
</style>