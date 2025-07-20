<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Board Read</h1>
	</div>

	<!-- DataTables Example -->
	<div class="card shadow mb-4">

		<div class="card-body">
			<input type="hidden" id="bno" name="bno" value="<c:out value="${board.bno}"></c:out>">
			<div class="input-group input-group-lg">
				<div class="input-group-prepend">
					<span class="input-group-text">Title</span>
				</div>
				<input type="text" id="title" name="title" class="form-control" value="<c:out value="${board.title}"></c:out>" readonly="readonly" />
			</div>
			<div class="input-group input-group-lg">
				<div class="input-group-prepend">
					<span class="input-group-text">Content</span>
				</div>
				<textarea id="content" name="content" class="form-control" readonly="readonly"><c:out value="${board.content}"></c:out></textarea>
			</div>
			<div class="input-group input-group-lg">
				<div class="input-group-prepend">
					<span class="input-group-text">Writer</span>
				</div>
				<input type="text" id="writer" name="writer" class="form-control" value="<c:out value="${board.writer}"></c:out>" readonly="readonly" />
			</div>
			<div class="input-group input-group-lg">
				<div class="input-group-prepend">
					<span class="input-group-text">createDate</span>
				</div>
				<input type="text" id="createDate" name="createDate" class="form-control" value="<c:out value="${board.createDate}"></c:out>" readonly="readonly" />
			</div>

			<div class="mt-3 d-flex justify-content-end">
				<button type="button" class="btn btn-info mr-2 btnBoardModifyView">수정</button>
				<button type="button" class="btn btn-warning btnBoardList">목록으로</button>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">
	
	document.querySelector(".btnBoardList").addEventListener('click', function(e) {
		window.location.href = "/board/list";
		
	}, false);
	
	document.querySelector(".btnBoardModifyView").addEventListener('click', function(e) {
		window.location.href = "/board/modify/${board.bno}";
		
	}, false);
	
</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp"%>
