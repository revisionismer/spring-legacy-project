<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
			
			<div id="search_condition" style="padding-bottom: 10px">
				<select name="typeSelect">
				 	<option value="">--</option>
					<option value="T" ${cri.typeStr == 'T'? 'selected' : ''}>제목</option>
					<option value="C" ${cri.typeStr == 'C'? 'selected' : ''}>내용</option>
					<option value="W" ${cri.typeStr == 'W'? 'selected' : ''}>작성자</option>
					<option value="TC" ${cri.typeStr == 'TC'? 'selected' : ''}>제목 OR 내용</option>
					<option value="TW" ${cri.typeStr == 'TW'? 'selected' : ''}>제목 OR 작성자</option>
					<option value="TCW" ${cri.typeStr == 'TCW'? 'selected' : ''}>제목 OR 내용 OR 작성자</option>
				</select>
				<input type="text" name="keywordInput" value="${cri.keyword}" />
				<button class="btn-default btn-sm searchBtn">검색</button>
			</div>
			
			<div class="table-responsive">
				
				<form id="actionForm" action="/board/list" method="get">
					<input type="hidden" name="pageNum" value="${cri.pageNum}" />
					<input type="hidden" name="amount" value="${cri.amount}" />
					
					<!-- 검색 조건이 null이 아니고 키워드도 null이 아니라면 -->
					<c:if test="${cri.types != null && cri.keyword != null}">
						<c:forEach var="type" items="${cri.types}">
							<input type="hidden" name="types" value="${type}" />
						</c:forEach>
						
						<input type="hidden" name="keyword" value="<c:out value="${cri.keyword}"/>" />
					</c:if>
				
				</form>
			
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
					<tbody class="board_tbody">
					<c:forEach var="board" items="${list}">
						<tr data-bno="${board.bno}" >
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
				<!-- id="board-bottom" style="display: flex; justify-content: space-between;" -->
				<div class="d-flex justify-content-between">
					<!-- 2025-08-09 : 여기까지 -->
					<ul class="pagination">
						<c:if test="${pagination.prev}">
    						<li class="page-item">
    							<a class="page-link" href="${pagination.startPage - 1}">Previous</a>
    						</li>
    					</c:if>
    					<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="num">
    						<li class="page-item ${cri.pageNum == num ? 'active' : ''}">
    							<a class="page-link" href="${num}">${num}</a>
    						</li>
						</c:forEach>
						<c:if test="${pagination.next}">
    						<li class="page-item"><a class="page-link" href="${pagination.endPage + 1}">Next</a></li>
  						</c:if>
  					</ul>
  					
  					<div class="d-sm-flex align-items-center justify-content-end">
						<button id="boardWriteBtn" class="btn btn-primary mr-2">글쓰기</button>
					</div>
				</div>
		
			</div>
		</div>
	</div>

</div>
<!-- /.container-fluid -->

<!-- bootstrap : modal -->
<div id="myModal" class="modal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Modal body text goes here.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary">Save changes</button>
				<button type="button" id="modalClose" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">

	$("select[name='typeSelect']").css('width', '15%');
	$("select[name='typeSelect']").css('height', '28px');
	
	const result = '${result}';
	
	const myModal = new bootstrap.Modal(document.getElementById('myModal'));
	
	console.log(myModal);
	
	if(result) {
		myModal.show();
	}
	
	document.querySelector('#modalClose').addEventListener("click", function(e) {
		// 모달이 꺼질때 모든 버튼 인풋 셀렉트 텍스트 에어리어의 포커스를 날린다.
		$('button, input, select, textarea').each(function () {
            $(this).blur();
        });
	}, false);
	

	document.querySelector('#boardWriteBtn').addEventListener("click", function(e) {
		window.location.href = '/board/register';
	}, false);
	
	const actionForm = document.querySelector("#actionForm");

	document.querySelector('.board_tbody').addEventListener("click", function(e) {
		
		const target = e.target.closest("tr");
		
		const bno = target.dataset.bno;
		
		const before = document.querySelector('#clonedActionForm');
		
		if(before) {
			before.remove();
		}
		
		const clonedActionForm = actionForm.cloneNode(true);
		clonedActionForm.setAttribute("action", `/board/read/\${bno}`)
		clonedActionForm.setAttribute("id", 'clonedActionForm')
		
		document.body.appendChild(clonedActionForm);
		
		clonedActionForm.submit();
		
		// ``을 사용할땐 jsp에선 backtick \을 앞에 넣어줘야 동작한다.
	//	window.location.href = `/board/read/\${bno}`;
		
	}, false);
	
	document.querySelector('.pagination').addEventListener("click", function(e) {
		
		e.preventDefault();
		
		const target = e.target;
		console.log(target);
		
		// 2025-08-17 : page-link 이외의 영역 클릭시에는 이동하지 않는다.
		if(target.className != 'page-link') {
			return false;
		}
		
		const targetPage = target.getAttribute("href");
		
	//	console.log(targetPage);

	//  참고 : 검색 조건이 들어가야 할땐 아래 방식으론 안된다. 		
	//	window.location.href = `/board/list?pageNum=\${targetPage}`;
	
		actionForm.setAttribute("action", "/board/list");
		actionForm.querySelector("input[name='pageNum']").value = targetPage;
		actionForm.submit();
	
	}, false);
	
	
	document.querySelector(".searchBtn").addEventListener("click", function(e) {
		// form submit 방지
		e.preventDefault();
		
		// 전파 방지
		e.stopPropagation();
		
		const selectObj = document.querySelector("select[name='typeSelect']");
		
		const selectValue = selectObj.options[selectObj.selectedIndex].value;
		
		console.log("selectValue :" + selectValue);
		// 1-1. 검색 조건을 배열로 만든다.(T, TC, TW, TCW -> [T], [T, C], [T, W], [T, C, W])
		const arr = selectValue.split("");
		
		console.log(arr);
		
		// 1-2. pageNum과 amount도 새로 만들어 준다.(검색된 pageNum은 1)
		let str = '';
		
		str = `<input type='hidden' name='pageNum' value=1>`;
		str += `<input type='hidden' name='amount' value=${cri.amount}>`;

		// 1-3. 위에서 만든 검색조건 배열을 types= 형태로 만들어주기
		if(arr && arr.length > 0) { // 1-4. 검색 조건이 있다면
			for(var type of arr) { // 1-5. arr 배열에 있는 값을 type에 넣어주기
				str += `<input type='hidden' name='types' value=\${type}>`;
			} 
		}
		
		// 1-6. 키워드 값도 넣어주기
		const keywordValue = document.querySelector("input[name='keywordInput']").value;
		str += `<input type='hidden' name='keyword' value=\${keywordValue}>`

		actionForm.innerHTML = str;

		console.log(str);
		
		actionForm.submit();
		
	});
	
	// 2026-01-11 : 검색 버튼 엔터키에도 동작하게 변경 -> 엔터는 포커스가 있는 요소에서만 발생하기 때문에 검색 버튼에 이벤트를 거는게 아니라 input text 쪽에 건다.
	document.querySelector("input[name='keywordInput']").addEventListener("keydown", function(e) {
		if (e.key === "Enter") {
			e.preventDefault();   
			e.stopPropagation();
			
		    document.querySelector(".searchBtn").click();
		}
	});
	
	
</script>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp" %>
