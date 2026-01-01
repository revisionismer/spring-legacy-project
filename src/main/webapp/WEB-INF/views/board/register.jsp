<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
						<input type="text" id="writer" name="writer" class="form-control" value="<sec:authentication property="principal.userVO.name"/>" readonly="readonly" />
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
	
	const boardFormObj = document.querySelector("#boardWriteForm");
	
	// 1-1. 파일 업로드 게시판 등록하기 전 유효성 검사(프론트단)
	document.querySelector("#boardWriteBtn").addEventListener("click", (e) => {
		e.stopPropagation();
		e.preventDefault();
		
		// 1-2. 파일 확장자 정규 표현식
		const regex = /(.*?)\.(gif|png|jpg|jpeg|bmp|GIF|PNG|JPG|JPEG|BMP)$/;
		
		// 1-3. 
		const fileInput = document.querySelector("input[name='files']");	
		
		// 1-4.
		const fileArr = fileInput.files;
		
		// 1-5.
		if(fileArr && fileArr.length > 0) {
			for(const file of fileArr) {
			//	Tip : console.dir(file);
				if(!file.name.match(regex)) {
					fileInput.value = '';
					alert("이미지 파일만 업로드 할 수 있습니다.");
					return;
				}
			}
		}
		
		boardFormObj.submit();
		
	}, false);

</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp"%>
