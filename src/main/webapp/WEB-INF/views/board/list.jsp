<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Board List</h1>
	</div>

	<!-- DataTables Example -->
	<div class="card shadow mb-4">
	
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered" id="dataTable">
					<thead>
						<tr>
							<th>Bno</th>
							<th>Title</th>
							<th>Content</th>
							<th>Writer</th>
							<th>createDate</th>
							<th>updateDate</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="board" items="${list}">
						<tr>
							<td><c:out value="${board.bno}"/></td>
							<td><c:out value="${board.title}"/></td>
							<td><c:out value="${board.content}"/></td>
							<td><c:out value="${board.writer}"/></td>
							<td><c:out value="${board.createDate}"/></td>
							<td><c:out value="${board.updateDate}"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
	const name = "AAA";
	console.log(jQuery);

</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp" %>
