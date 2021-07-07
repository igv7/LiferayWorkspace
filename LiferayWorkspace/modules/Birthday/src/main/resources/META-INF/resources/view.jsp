<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">


<%@page import="java.util.List"%>
<%@page import="utils.Utils"%>
<%@page import="obj.Worker"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/init.jsp"%>
<%
	List<Worker> workers = (ArrayList<Worker>) request.getAttribute("workers");
%>


<p>
	<!-- <b><liferay-ui:message key="birthday.caption" /></b> -->
<h3>Birthdays</h3>
<div class="float-end d-flex flex-row bd-highlight mb-3">
	<div class="p-2">
		<form method="post" enctype="application/x-www-form-urlencoded">
			<input type="text" name="date" placeholder="dd/mm" />
			<button type="submit" name="type" value="filter">Search</button>
		</form>
	</div>
	<div class="p-2">
		<form method="post" enctype="application/x-www-form-urlencoded">
			<button type="submit" name="type" value="clean">Clean</button>
		</form>
	</div>
</div>
<table class="table table-stripped">
	<tr>
		<th>Worker ID</th>
		<th>Name</th>
		<th>Date Of Birth</th>
		<th></th>
	</tr>
	<%
		for (Worker worker : workers) {
	%>
	<tr>
		<td><%=worker.getId()%></td>
		<td><%=worker.getName()%></td>
		<td><%=worker.getBirthdate()%></td>
		<td>
			<%
				if (Utils.getInstance().isToday(worker.getBirthdate())) {
			%>
			<button class="btn btn-success"
				onclick="sendbday(<%=worker.getId()%>);">Send congratulations</button> <%
 	}
 %>
		</td>
	</tr>
	<%
		}
	%>
</table>
<script>
function sendbday(id) {
	jQuery.ajax({
		url: '${sendData}',
		method: "post",
		data: { type: 'bday', id : id },
		dataType: "json",
		success: function() { console.log('Birthdate sended'); }
	});
}
</script>
</p>