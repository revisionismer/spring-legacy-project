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
		
		<!-- 댓글 입력 영역 -->
		<div id="comment-area" class="card shadow">
    		<!-- Comments Form -->
    		<div class="card my-4">
        		<h5 class="card-header">Comment</h5>
        		<div class="card-body">
            		<form id="commentForm">
            			<input type="hidden" id="username" name="username" value="관리자" />
            			<input type="hidden" id="bno" name="bno" value="${board.bno}" />
            			<div class="form-group">
            				<textarea id="comment-contents" class="form-control" rows="3"></textarea>
            			</div>
            		</form>
            		<button id="commentWriteBtn" class="btn btn-primary" style="float: right;">Save</button>
        		</div>
    		</div>
		</div>
		
		<!-- 댓글 리스트 영역 -->
		<div id="replyList" class="d-flex flex-column">
			<div id="comment">
				<!-- 이미지는 /resources/img/** 경로로 하게 servlet-context-xml에 설저 -->
				<img class="d-flex mb-3 ml-2 mr-2 rounded-circle" src="/resources/img/undraw_profile.svg" alt="" style="width: 30px; height: 30px;">
				<div class="media-body">
					<!--"작성자 이름  날짜" 및 내용 출력-->
					<h5 class="mt-0">
						커멘트 &nbsp;&nbsp;<small class="text-muted">2025-09-14</small>
					</h5>
					<p>안녕</p>
				</div>

				<div id="removeCommentArea" class="m-3">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</div>
		</div>
		
		<div id="replyCommentList" class="d-flex justify-content-center">
			<ul class="pagination">
    			<li class="page-item">
    				<a class="page-link" href="#">Previous</a>
    			</li>
    						
    			<li class="page-item">
    				<a class="page-link" href="#">1</a>
    			</li>
						
    			<li class="page-item">
    				<a class="page-link" href="#">Next</a>
    			</li>
  					
  			</ul>
		</div>
	</div>	
</div>

<!-- 댓글 모달창 영역 -->
<div class="modal" id="replyAddModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal Title</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">x</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="input-group input-group-lg">
					<div class="input-group input-group-sm">
						<div class="input-group-prepend">
							<span class="input-group-text">Replyer</span>
						</div>
						<input type="text" name="replyer" class="form-control" />
					</div>
					<div class="input-group input-group-sm">
						<div class="input-group-prepend">
							<span class="input-group-text">Reply Title</span>
						</div>
						<input type="text" name="replyTitle" class="form-control"/>
					</div>
					<div class="input-group input-group-sm">
						<div class="input-group-prepend">
							<span class="input-group-text">Reply Content</span>
						</div>
						<textarea name="replyContent" class="form-control"></textarea>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" id="replyRegBtn" class="btn btn-primary">Save changes</button>
				<button type="button" id="replycloseBtn" class="btn btn-secondary">Close</button>
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
		getRepliesListPaging();
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
	const replyList = document.querySelector("#replyList");
	
	// 2025-10-01 : 댓글 페이징 처리
	var replyPageNum = 1;

	function getRepliesListPaging(pageNum) {
		
		// 1-1. 현재 URL의 쿼리 문자열 받아온다.
		const queryString = window.location.search;
	
		// 1-2. queryString으로 받아온 문자열을 URLSearchParams에 셋팅
		const urlParams = new URLSearchParams(queryString);
		
		// 1-3. 이전 페이지 번호 정보
		var pageParam = urlParams.get("pageNum");
		
		// 1-4 .1페이지내에 보여줄 댓글 게시글 갯수
		var amountParam = urlParams.get("amount");
	
		// 1-5. 댓글 목록 함수 파라미터에 댓글 페이징 번호가 있으면 page, 없을떈 1
		var pageNum = pageNum ? pageNum : 1;
		var amount = amountParam ? amountParam : 10;
		
		var url = `/api/replies/list/\${bno}?pageNum=\${pageNum}&amount=\${amount}`;
		
		$.ajax({
			type: "GET",
			url: url,
			dataType: "json",  // 1-1. 서버에서 결과값으로 받을 데이터의 타입.
			contentType: "application/json",  // 1-2. 서버로 보낼 데이터 타입
			success: function(res) {
				console.log(res);
				
				const pagination = res.pagination;
				const replies = res.replies;
				
				printReplyList(pagination, replies);
				
				
				// 2025-10-01 : 여기까지.
				if(replies.length == 10) {
					replyPageNum = res.pagination.endPage + 1; 
				} else {
					replyPageNum = res.pagination.endPage; 
				}
				
				
			},
			error: function(res) {
				console.log(res);
			}
		});	
	}
	
	const pageUL = document.querySelector("#replyCommentList .pagination");
	
	const printReplyList = (pagination, replies) => {
		
		replyList.innerHTML = "";
		
		let str = ``;
		
		for(var i = 0; i < replies.length; i++) {
			str += `
				<div id="comment_\${i}" class="m-3 d-flex justify-content-between;">
					<!-- 이미지는 /resources/img/** 경로로 하게 servlet-context-xml에 설저 -->
					<img class="d-flex mb-3 ml-2 mr-2 rounded-circle" src="/resources/img/undraw_profile.svg" alt="" style="width: 30px; height: 30px;">
					<div class="media-body">
						<!--"작성자 이름  날짜" 및 내용 출력-->
						<div id="comment_writer" class="mt-0">
							<h5>\${replies[i].writer} &nbsp;&nbsp;</h5><small class="text-muted">2025-09-14</small>
						</div>
						<div id="comment_content">
							<p>\${replies[i].content}</p>
						</div>
					</div>

					<div id="removeCommentBtnArea" class="m-3">
						<button type="button" id="removeReplyBtn_\${i}" class="close" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</div>
			`;
			
			replyList.innerHTML = str;
		}
		
		// -------------- commentList  --------------
		const {startPage, endPage, prev, next} = pagination;
	
		const pageNum = pagination.criteria.pageNum;
		
		let paginationStr = ``;
		
		if(prev) { 
			paginationStr += `
				<li class="page-item">
					<a class="page-link" href="\${startPage - 1}">Previous</a>
				</li>
			`;
		}
		
		for(let i = startPage; i <= endPage; i++) {
			
			paginationStr += `
				<li class="page-item \${i == pageNum ? 'active' : ''}">
    				<a class="page-link" href="\${i}">\${i}</a>
    			</li>
    		`;
			
		}
		
		if(next) { // 일단 나오게끔 하기위해 !붙임
			paginationStr += `
				<li class="page-item">
					<a class="page-link" href="\${endPage + 1}">Next</a>
				</li>
			`;
		}
		
		pageUL.innerHTML = paginationStr;
		
	}
	
	pageUL.addEventListener("click", (e) => {
		e.preventDefault();
		e.stopPropagation();
		
		const target = e.target;
		const pageNum = target.getAttribute("href");
		
		replyPageNum = pageNum;
		
		getRepliesListPaging(pageNum);
		
	}, false);
	
	// 댓글 모달  : 8분 56초
	const replyAddModal = new bootstrap.Modal(document.querySelector('#replyAddModal'));
	
	const replyerInput = document.querySelector("input[name='replyer']");
	const replyTitleInput = document.querySelector("input[name='replyTitle']");
	const replyContentInput = document.querySelector("[name='replyContent']");
	
	document.querySelector("#comment-contents").addEventListener('click', function(e) {
		replyAddModal.show();
	}, false);
	
	document.querySelector("#replycloseBtn").addEventListener('click', function(e) {
		replyAddModal.hide();
		
		// 2025-10-01 : aria-hidden 문제로 모달창이 닫힌 후에 포커싱을 잡아주기 위해 추가.
		$('#comment-contents').focus();
	}, false);
	
	// 2025-10-02 : 모달창 띄워서 하는 방법
	document.querySelector("#replyRegBtn").addEventListener('click', function(e) {
		e.preventDefault();
		e.stopPropagation();
		
		var url = "/api/replies/register";
		
		const replyerInput = document.querySelector("input[name='replyer']");
		const replyTitleInput = document.querySelector("input[name='replyTitle']");
		const replyContentInput = document.querySelector("[name='replyContent']");
		const bno = '${board.bno}';
		
		const replyObj = {
			writer : replyerInput.value,
			title : replyTitleInput.value,
			content : replyContentInput.value,
			bno : bno
		}
		
		if(confirm(`${bno}번 게시글에 댓글을 작성하시겠습니까?`)) {
			
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(replyObj),
				dataType: "json",  // 1-1. 서버에서 결과값으로 받을 데이터의 타입.
				contentType: "application/json",  // 1-2. 서버로 보낼 데이터 타입
				success: function(res) {
					console.log(res);
					var rno = res.rno;
					var replyCount = res.replyCount;
					
					console.log("rno : ", rno);
					console.log("replyCount", replyCount);
					
					replyerInput.value = '';
					replyTitleInput.value = '';
					replyContentInput.value = '';
				
					replyAddModal.hide();
					
					getRepliesListPaging(replyPageNum);
				},
				error: function(res) {
					console.log(res);
				}
			});	
		}
		
	}, false); 
	
	// 텍스트 창에서 직접 댓글 다는 방법 
	document.querySelector("#commentWriteBtn").addEventListener('click', function(e) {
		e.preventDefault();
		e.stopPropagation();
		
		var url = "/api/replies/register";
		
		const username = document.querySelector("#username");
		const replyContentInput = document.querySelector("#comment-contents");
		const bno = document.querySelector("#bno")
		
		const replyObj = {
			writer : username.value,
			content : replyContentInput.value,
			bno : bno.value
		}
		
		if(confirm(`${bno}번 게시글에 댓글을 작성하시겠습니까?`)) {
			
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(replyObj),
				dataType: "json",  // 1-1. 서버에서 결과값으로 받을 데이터의 타입.
				contentType: "application/json",  // 1-2. 서버로 보낼 데이터 타입
				success: function(res) {
					console.log(res);
					var rno = res.rno;
					var replyCount = res.replyCount;
					
					console.log("rno : ", rno);
					console.log("replyCount", replyCount);
					
					replyContentInput.value = '';
					
					replyAddModal.hide();
					
					getRepliesListPaging(replyPageNum);
				},
				error: function(res) {
					console.log(res);
				}
			});	
		}
		
	}, false);
	
</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp"%>
