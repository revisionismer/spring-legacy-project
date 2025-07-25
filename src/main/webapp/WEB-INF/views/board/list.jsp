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
	
	const result = '${result}';
	
	const myModal = new bootstrap.Modal(document.getElementById('myModal'));
	
	console.log(myModal);
	
	if(result) {
		myModal.show();
	}

	document.querySelector('.board_tbody').addEventListener("click", function(e) {
		
		const target = e.target.closest("tr");
		
		const bno = target.dataset.bno;
		
		console.log('bno : ', bno);
		
		// ``을 사용할땐 jsp에선 backtick \을 앞에 넣어줘야 동작한다.
		window.location = `/board/read/\${bno}`;
		
	}, false);
	
	document.querySelector('#modalClose').addEventListener("click", function(e) {
		// 모달이 꺼질때 모든 버튼 인풋 셀렉트 텍스트 에어리어의 포커스를 날린다.
		$('button, input, select, textarea').each(function () {
            $(this).blur();
        });
	}, false);
	
</script>

<%@include file="../includes/footer.jsp" %>

<!-- 내가 만든 자바스크립트 파일 위치할 곳 -->
<%@include file="../includes/end.jsp" %>
