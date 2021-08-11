var pageSize = 10;
var viewPageCount = 3;
var currentPage = 1;

init();

function init() {
	currentPage = ($('#current-page').val() == null || $('#current-page').val() === "") ? 1 : $('#current-page').val();
	$('#current-page').val(currentPage);
	
	var total = parseInt($('#total-data').val()); 
	$('#qna-total-page').text(`Total ${document.getElementById('total-data').value} / ${currentPage} page`);
	
	pager(total, currentPage, pageSize);
}

// 글제목 클릭 시 이벤트
$(document).on("click", "#qna-content", function(event){
	const qnaNumber = event.target.querySelectorAll('input[type=hidden]')[0].value.trim();
	const email = event.target.querySelectorAll('input[type=hidden]')[1].value.trim();
	location.href = "qnaInputPassword.qna?qnaNumber=" + qnaNumber + "&page=" + $('#current-page').val() + "&email=" + email;
});

// 페이지 번호 클릭 시 이벤트
$(document).on("click", "#page > a", getList);
$(document).on("click", "#search-submit", getList);

function getList(event) {
	
	event.preventDefault();
	
	const arrow = $(event.target).text().trim();
	const total = parseInt($('#total-data').val());
	
	if(!isNaN(arrow)) {
		currentPage = parseInt($(event.target).text().trim());	
	} else {
		
		if(arrow === ">") {
			currentPage = (currentPage + viewPageCount) >= Math.ceil(total / pageSize) ? parseInt(Math.ceil(total / pageSize)) : parseInt(currentPage + viewPageCount);
		} else if(arrow === "<") {
			currentPage = (currentPage - viewPageCount) <= 0 ? 1 : parseInt(currentPage - viewPageCount);
		} else {
			currentPage = 1;
		}
	
		console.log(currentPage);
	}
	
	
	
	var start = (currentPage - 1) * pageSize + 1;
	var end = currentPage * pageSize;
	
	const url = "ajaxGetContent.ajax";
	

	const str1 = $('#select-condition option:selected').val();
	const str2 = $('input[name=stx]').val();
	
	const searchCondition = {
		str1 : str2
	};
	
	const form_data = {
		pageSize: pageSize,
		currentPage: currentPage,
		searchConditionKey : str1,
		searchConditionValue : str2
	};
	
	$.ajax(
		{
			url: url,
			data: form_data,
			dataType: "json",
			success: function(response) {
				setQnaTable(response.list);
				
				document.getElementById('total-data').value = response.total;
				$('#current-page').val(currentPage);
				$('#qna-total-page').text(`Total ${response.total} / ${currentPage} page`);
				
				pager(response.total, currentPage, pageSize);
			}			
		}
	)
}


// select box 바뀔 때
$(document).on("change", "#select-condition", function() {
	$('input[name=stx]').val("");
});

// 페이지 이동 시 목록 셋팅
function setQnaTable(arr) {
	$('#qna-table').empty();
	
	var innerValue = "";
	
	innerValue += 
	`<tbody class="text-center">
		<tr id="table-head" class="">
			<th style="width: 15%;">상태</th>
			<th style="width: 55%;">제목</th>
			<th style="width: 10%;">작성자</th>
			<th style="width: 10%;">조회</th>
			<th style="width: 10%;">날짜</th>
		</tr>`;
		
		
	$.each(arr, function(index, board) {
		innerValue += `<tr>`
		if(board.qnaStatus == 0) {
			innerValue += `<td><span class="qnaIco qnaIco2"><i class="far fa-smile" aria-hidden="true"></i> 답변완료</span></td>`;
		} else if(board.qnaStatus == 1) {
			innerValue += `<td><span class="qnaIco qnaIco3"><i class="fa fa-spinner" aria-hidden="true"></i> 접수완료</span></td>`;
		}
		innerValue +=
			`<td style="text-align: left;" id="qna-content">`
			
		for(var i = 0; i < board.qnaDepth; i++) {
			innerValue += `&nbsp;&nbsp;&nbsp;`;
		}
		
		if(board.qnaDepth > 0) {
			innerValue += `<i class="fas fa-angle-double-right" aria-hidden="true"></i>`;
		}
		
		innerValue +=
			    `${board.qnaTitle }
			    <input type="hidden" value="${board.qnaNumber} ">
			    <input type="hidden" value="${board.email} ">
			 </td>
			 <td>${board.nickname }</td>
			 <td>${board.qnaViewcount }</td>
			 <td>${board.qnaWritedate }</td>`;
			
			
		innerValue += `</tr>`;
	
	});
			
		
	innerValue += `</tbody>`;
	$('#qna-table').html(innerValue);
	
}


// 페이징 처리
function pager(total, currentPage, pageSize) {
	
	var maxPage = Math.ceil(total / pageSize); 
	
	var innerValue = "";
	
	// 시작 페이지
	if(currentPage < viewPageCount) {
	
		innerValue += `<div id="page">`;
		for(var i = 1; i <= viewPageCount; i++){
			
			if(i == currentPage) {
				innerValue += `<span class="genric-btn success radius">${i}</span>`;
				if(maxPage <= i) {
					break;
				}
			} else if(maxPage == i){
				innerValue += `<a class="genric-btn success-border radius" href="javascript:void(0)">${i}</a>`
				break;
			} else {
				innerValue += `<a class="genric-btn success-border radius" href="javascript:void(0)">${i}</a>`
			}
			
		}
		
		if(maxPage > viewPageCount){
			innerValue += `<a class="genric-btn success-border radius" href="javascript:void(0)">></a>`;
		}
		
		innerValue += `</div>`;
					  
	} 
	// 끝 페이지
	else if(currentPage > maxPage - viewPageCount + 1) {
	
		innerValue += `<div id="page"><a class="genric-btn success-border radius" href="javascript:void(0)"><</a>`;
		for(var i = maxPage - viewPageCount + 1; i <= maxPage; i++){
			
			if(i == currentPage) {
				innerValue += `<span class="genric-btn success radius">${i}</span>`;
			} else {
				innerValue += `<a class="genric-btn success-border radius" href="javascript:void(0)">${i}</a>`
			}
			
		}
		
		innerValue += `</div>`;
	
	} 
	// 중간 페이지
	else {
		innerValue += `<div id="page"><a class="genric-btn success-border radius" href="javascript:void(0)"><</a>`;
		for(var i = currentPage - 1; i <= currentPage + 1; i++){
			
			if(i == currentPage) {
				innerValue += `<span class="genric-btn success radius">${i}</span>`;
			} else {
				innerValue += `<a class="genric-btn success-border radius" href="javascript:void(0)">${i}</a>`
			}
			
		}
		
		innerValue += `<a class="genric-btn success-border radius" href="javascript:void(0)">></a></div>`;
	}
	
	$('#paging').html(innerValue);
}