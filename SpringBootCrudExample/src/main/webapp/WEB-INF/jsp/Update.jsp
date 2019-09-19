
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" type="text/css" href="css/layout.css">
<div class="site-inner">
	<fieldset>
		<legend>Register</legend>
		<form:form modelAttribute="student" action="update" method="post"
			enctype="multipart/form-data">

			<table align="center">

				<tr>
					<td><form:hidden path="student_id" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<td><form:input path="mobile" /></td>
					<td><form:errors path="mobile" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Districts</td>
					<td><form:select path="district">
							<form:option value="Select">---Select---</form:option>
							<form:options items="${districts }" />
						</form:select></td>
					<td><form:errors path="district" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><form:radiobutton path="gender" value="Male" />Male <form:radiobutton
							path="gender" value="Female" />Female</td>
					<td><form:errors path="gender" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Subjects:</td>
					<td><form:checkboxes path="subjects" items="${subjects}" /></td>
					<td><form:errors path="subjects" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><form:textarea path="address" cols="50" rows="5" /></td>
					<td><form:errors path="address" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td>Photo</td>
					<td><input type="file" name="file" id="preview" /> <img
						src="" id="output" /></td>
					<td><form:errors path="photo" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Update" /></td>
				</tr>
			</table>
		</form:form>
	</fieldset>
</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="js/imagepreview.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script src="js/subject.js"></script>