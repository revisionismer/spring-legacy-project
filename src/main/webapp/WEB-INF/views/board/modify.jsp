<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Board Modify</h1>
	</div>

	<!-- DataTables Example -->
	<div class="card shadow mb-4">

		<div class="card-body">
			<div id="boardWriteForm">
				<form action="/board/register" method="post">
					<input type="hidden" id="bno" name="bno" value="<c:out value="${board.bno}"></c:out>">
					<div class="input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Title</span>
						</div>
						<input type="text" id="title" name="title" class="form-control" value="<c:out value="${board.title}"></c:out>" />
					</div>
					<div class="input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Content</span>
						</div>
						<textarea id="content" name="content" class="form-control"><c:out value="${board.content}"></c:out></textarea>
					</div>
					<div class="input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Modify</span>
						</div>
						<input type="text" id="writer" name="writer" class="form-control" value='<c:out value="${board.writer}"></c:out>' readonly="readonly" />
					</div>

					<div class="mt-3 d-flex justify-content-end">
						<button type="button" class="btn btn-info mr-2 btnBoardModify">확인</button>
						<button type="button" class="btn btn-danger mr-2 btnBoardDelete">삭제</button>
						<button type="button" class="btn btn-warning btnBoardList">목록으로</button>
					</div>
				</form>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->
	<%@include file="../includes/footer.jsp"%>

	<script type="text/javascript">
		document.querySelector(".btnBoardList").addEventListener('click', function(e) {
			window.location.href = "/board/list";
		
		}, false);
	</script>

	<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
	<%@include file="../includes/end.jsp"%>