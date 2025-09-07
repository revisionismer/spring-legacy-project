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
			<div class="content">
				<div class="input-group input-group-lg">
					<div class="input-group-prepend">
						<span class="input-group-text">Bno</span>
					</div>
					<input type="text" id="bno" name="bno" class="form-control" value="<c:out value="${board.bno}"></c:out>" readonly="readonly" disabled="disabled">
							
				</div>
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
				<div class="input-group input-group-lg">
					<div class="input-group-prepend">
						<span class="input-group-text">updateDate</span>
					</div>
					<input type="text" id="updateDate" name="updateDate" class="form-control" value="<c:out value="${board.updateDate}"></c:out>" readonly="readonly" />
				</div>

				<div class="mt-3 d-flex justify-content-end">
					<button type="button" class="btn btn-info mr-2 btnBoardModifyView">수정</button>
					<button type="button" class="btn btn-warning btnBoardList">목록으로</button>
				</div>
			</div>
		</div>
	</div>
</div>

<form id="actionForm" action="/board/list" method="get">
	<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
	<input type="hidden" name="amount" value="${cri.amount}"/>
	
	<!-- 검색 조건이 null이 아니고 키워드도 null이 아니라면 -->
	<c:if test="${cri.types != null && cri.keyword != null}">
		<c:forEach var="type" items="${cri.types}">
			<input type="hidden" name="types" value="${type}" />
		</c:forEach>
						
		<input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>" />
	</c:if>
</form>
<!-- /.container-fluid -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">

	$(document).ready(function(){
		getBoardListPaging();
	});

	const actionForm = document.querySelector("#actionForm");
	const bno = '${board.bno}';
	
	document.querySelector(".btnBoardList").addEventListener('click', function(e) {
		// form submit 방지
		e.preventDefault();
		
		// 전파 방지
		e.stopPropagation();
			
		// window.location.href = "/board/list";
		actionForm.submit();	
	}, false);
	
	document.querySelector(".btnBoardModifyView").addEventListener('click', function(e) {
		// form submit 방지
		e.preventDefault();
		
		// 전파 방지
		e.stopPropagation();
		
//		window.location.href = "/board/modify/${board.bno}";
		actionForm.setAttribute("action", `/board/modify/\${bno}`);
		actionForm.submit();
		
	}, false);
	
	/*
		// ajax 양식(GET)
		$.ajax({
			url: url,
			type: "GET",
			dataType: "json",  // 3-5. 서버에서 결과값으로 받을 데이터의 타입.
			success: function(res) {
				console.log(res);
			},
			error: function(res) {
				console.log(res);
			}
		});	
		
		// ajax 양식(POST)
		$.ajax({
			url: url,
			type: "POST",
			contentType: "application/json",  // 3-3. 서버로 보낼 데이터 타입
			data: null,
			dataType: "json",  // 3-5. 서버에서 결과값으로 받을 데이터의 타입.
			success: function(res) {
				console.log(res);
			},
			error: function(res) {
				console.log(res);
			}
		});	
	
	*/
	
	function getBoardListPaging() {
		
		var pageNum = 1;
		var amount = 10;
		
		// 2025-09-07 : 여기까지
		var url = `/api/replies/list/\${bno}?pageNum=\${pageNum}&amount=\${amount}`;
		
		$.ajax({
			type: "GET",
			url: url,
			dataType: "json",  // 1-1. 서버에서 결과값으로 받을 데이터의 타입.
			contentType: "application/json",  // 1-2. 서버로 보낼 데이터 타입
			success: function(res) {
				console.log(res);
			},
			error: function(res) {
				console.log(res);
			}
		});	
	}
	
</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp"%>
