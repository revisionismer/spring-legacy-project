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
			<div class="content">
				<form id="boardForm" method="post" enctype="multipart/form-data">
				
					<div class="input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Bno</span>
						</div>
						<input type="text" id="bno" name="bno" class="form-control" value="<c:out value="${board.bno}"></c:out>" readonly="readonly">
							
					</div>
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
							<span class="input-group-text">Writer</span>
						</div>
						<input type="text" id="writer" name="writer" class="form-control" value='<c:out value="${board.writer}"></c:out>' readonly="readonly" />
					</div>
					
					<div class="input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">createDate</span>
						</div>
						<input type="text" id="createDate" name="createDate" class="form-control" value="<c:out value="${board.createDate}"></c:out>" readonly="readonly" disabled="disabled" />
					</div>
					<div class="input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">updateDate</span>
						</div>
						<input type="text" id="updateDate" name="updateDate" class="form-control" value="<c:out value="${board.updateDate}"></c:out>" readonly="readonly" disabled="disabled" />
					</div>
					
					<div class="form-group input-group input-group-lg">
						<div class="input-group-prepend">
							<span class="input-group-text">Files</span>
						</div>
						<input type="file" id="files" name="files" class="form-control" multiple="multiple"/>
					</div>
				
					<div class="mt-3 d-flex justify-content-end">
						<button type="button" class="btn btn-info mr-2 btnBoardModify">수정하기</button>
						<button type="button" class="btn btn-danger mr-2 btnBoardDelete">삭제하기</button>
						<button type="button" class="btn btn-warning btnBoardList">목록으로</button>
					</div>
					
					<!-- 삭제될 이미지들을 담는 임시 저장소 input hidden으로 만들어서 추가한다. -->
					<div class="deleteImages"></div>
				</form>
			</div>
			
			<div class="attachFileList d-flex">
				<c:if test="${board.attachFileList != null && board.attachFileList.size() > 0}">
					<c:forEach items="${board.attachFileList}" var="boardFile">
						<c:if test="${boardFile.ano != null}">
							<div id="thumnailImageArea" class="d-flex">
								<!-- a 태그는 target 속성에 _blank가 있으면 새창으로 열어준다. -->
								<a href="/images/${boardFile.savedFileName}" target="_blank">
									<img src="/images/s_${boardFile.savedFileName}" alt="image" >
								</a>
								<div id="removeImgArea">
									<button id="removeImgBtn" class="btn" 
											data-ano="${boardFile.ano}" 
											data-fullname="${boardFile.savedFileName}">X</button>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
		
		</div>
		
	</div>
</div>
<!-- /.container-fluid -->
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

<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">

	const bno = `${board.bno}`;
	const boardForm = document.querySelector("#boardForm");
	
	const actionForm = document.querySelector("#actionForm");

	document.querySelector(".btnBoardList").addEventListener('click', function(e) {
		// form submit 방지
		e.preventDefault();
		
		// 전파 방지
		e.stopPropagation();
		
//		window.location.href = "/board/list";
		actionForm.submit();

	}, false);
	
	document.querySelector(".btnBoardModify").addEventListener('click', function(e) {
		// form submit 방지
		e.preventDefault();
		
		// 전파 방지
		e.stopPropagation();
		
		if(confirm(`\${bno}번 게시글을 수정하시겠습니까?`)) {
			boardForm.action = `/board/modify/\${bno}`;
			boardForm.method = 'post';
			boardForm.submit();
		}
		
	}, false);
	
	document.querySelector(".btnBoardDelete").addEventListener('click', function(e) {
		// form submit 방지
		e.preventDefault();
		
		// 전파 방지
		e.stopPropagation();
		
		if(confirm(`\${bno}번 게시글을 삭제하시겠습니까?`)) {
			boardForm.action = `/board/delete/\${bno}`;
			boardForm.method = 'post';
			boardForm.submit();	
		}
		
	}, false);
	
	// 이미지 삭제 이벤트 처리
	document.querySelector(".attachFileList").addEventListener("click", (e) => {
		
		const target = e.target;
		
		if(target.tagName !== 'BUTTON') {
			return;
		}
		
		const ano = target.getAttribute("data-ano");
		const fullname = target.getAttribute("data-fullname");
		
		console.log("ano :", ano, "fullname : ", fullname);
		
		if(ano && fullname) {
			
			let str = '';
			
			str += `<input type='hidden' id='anos' name='anos' value='\${ano}'>`;
			str += `<input type='hidden' id='fullnames' name='fullnames' value='\${fullname}'>`;
			
			// 1-1. target에서 가장 가까운 thumnailImageArea를 없애준다.(아직 DB에서 삭제 안됨)
			target.closest("#thumnailImageArea").remove();
			
			// 1-2. 화면에서 삭제된 이미지를 input hidden 태그로 추가한다.
			document.querySelector(".deleteImages").innerHTML += str;
		}
		
	}, false);
	
	
</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp"%>