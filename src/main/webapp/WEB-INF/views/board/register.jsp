<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../includes/header.jsp"%>

<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<h1 class="h3 mb-0 text-gray-800">Board Register</h1>
	</div>

	<!-- DataTables Example -->
	<div class="card shadow mb-4">
		
		<div class="card-body">
			<div class="content">
				<!-- 주의 : 파일 업로드시 폼태크에는 post방식, enctype="multipart/form-data"을 명시해야 파일을 전송한다. -->
				<form id="boardWriteForm" action="/board/register" method="post" enctype="multipart/form-data">
					<div class="form-group input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Title</span>
						</div>
						<input type="text" id="title" name="title" class="form-control"/>
					</div>
					<div class="form-group input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Content</span>
						</div>
						<textarea id="content" name="content" class="form-control"></textarea>
					</div>
					<div class="form-group input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Writer</span>
						</div>
						<input type="text" id="writer" name="writer" class="form-control"/>
					</div>
					<div class="form-group input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Files</span>
						</div>
						<input type="file" id="files" name="files" class="form-control" multiple="multiple"/>
					</div>
					
					<div class="mt-3 d-flex justify-content-end">
						<button type="submit" id="boardWriteBtn" name="boardWriteBtn" class="btn btn-primary mr-2">글쓰기</button>
						<button id="boardCancelBtn" name="boardCancelBtn" class="btn btn-danger">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">

</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp"%>
