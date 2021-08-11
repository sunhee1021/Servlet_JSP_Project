const API_URL = "http://api.visitjeju.net/vsjApi/contents/searchList";
const API_KEY = "wuuv42tnk9hazf5h"
const LOCALE = "kr";

// 제주비짓 전체 데이터를 담은 배열
var totalResult = [];

// 1차 지역 라벨 배열
var firstDivision = [];

// 1차 지역 null 값이 없는 배열 전체
var notNullTotalResult = [];

// 1차 지역 라벨 선택한 것
var selectedFirst = "";

// 2차 지역 라벨 배열
var secondDivision = [];

// 2차 지역 라벨 선택한 것
var selectedSecond = "";

// 카드 배열
var cardarr = [];

init();

//화면 open시 실행
//비동기 async&await 사용
//관광지 페이지 진입시 콜백 함수를여러개 사용하게 되면서, 
//예상치 못한 순서로 코드가 실행되는 문제를 해결하기 위해 사용함

//async: 예약어 (함수 앞에 붙이기)
//await: 함수 내부 로직 중 HTTP 통신을 하는 비동기 처리 코드 앞에 await를 붙인다
//주의) 비동기 처리 메서드가 반드시 프로미스 객체를 반환해야 await가 의도한대로 동작한다
//프로미스란, 자바스크립트 비동기 처리를 위한 객체!

async function init() {

	//API로 넘어온 정보를 담을 변수를 생성
	var form_data = {
		apiKey: API_KEY,
		locale: LOCALE,
		page: 100
	};

	await getJejuData(form_data);
	await firstDiv();
}

// 제주비짓 REST API 데이터 받기
//getJejuData()함수는 프로미스 객체를 반환하는 함수이다.
//해당 함수 실행시 프로미스가 이행되며 결과값은 form_data
//즉, 제주 비짓에서 제공하는 API를 받아와서, init함수 안에서 생성한 form_data변수에 담고 반환
async function getJejuData(form_data) {
	return new Promise((resolve, reject) => {

		//HTTP요청
		$.ajax(
			{
				url: API_URL,
				data: form_data,
				success: (response) => {
					totalResult.push(response);
					resolve(form_data);
				}
				//데이터를 다 불러오기 전까지 로딩이미지
				,beforeSend: function() {
					$('.wrap-loading').removeClass('display-none');
				}, complete: function() {
					$('.wrap-loading').addClass('display-none');
				}
			}
		);
	});
}

// 1차지역 라벨이 null 값인 경우를 제외하고 전체 목록에 담아서 반환
async function firstDiv() {
	return new Promise((resolve, reject) => {
		
		//제주비짓에서 받아온 전체 데이터
		$.each(totalResult, function(index, item) {
			$.each(item.items, (index2, item2) => {

				var str = "";
				//1차 지역코드가 없는경우
				if (item2.region1cd == null) {
					return true;
				//1차 지역코드가 있는 경우
				//1. 1차 지역코드를 str에 담아둔다
				//2. 1차 지역코드가 있는 item2를 배열에 담는다 
				} else {
					str = item2.region1cd.label;
					notNullTotalResult.push(item2);
				}
				//str에 담아둔 1차 지역코드로 화면 세팅
				setDivision1(str);
			});
		});
		resolve();
	});
}

//init함수를 통해 가공한 데이터를 중복 값을 제외한 1차 라벨 화면에 셋팅
function setDivision1(str) {
	if (firstDivision.length == 0 || !firstDivisionContainStr(firstDivision, str)) {
		firstDivision.push(str);
		document.querySelector('#division1').innerHTML += '<td>' + str + '</td>';
	}
}

// 라벨 중복 값은 true, 조회 완료후에 중복값이 없다면 false 반환
function firstDivisionContainStr(firstDivision, str) {
	for (var i = 0; i < firstDivision.length; i++) {
		if (str === firstDivision[i]) {
			return true;
		}
	}
	return false;
}

// 1차 지역 클릭 이벤트 동적 할당
$(document).on("click", "#division1 > td", function(event) {
	// 카드 지역 초기화
	$('#row').empty();

	var targetValue = $(event.target).text();

	if (targetValue === "전체") {
		return;
	} else {

		selectedFirst = targetValue;

		// 2차지역 라벨 배열 초기화
		secondDivision = [];

		var arr = [];

		$.each(notNullTotalResult, function(index, item) {
			if (item.region1cd.label === targetValue) {
				arr.push(item);
			}
		});

		setSecondDivision(arr);
		document.querySelector('#paging').style.display = "none";
		$('.category-choice').removeClass('display-none');
		
	}
});

// 2차 지역 라벨 만들기
function setSecondDivision(arr) {

	// 라벨 중복 값 제거
	$.each(arr, function(index, item) {

		var str = item.region2cd.label;
		
		if(str === "region1>"){
			return true;
		}
		
		if (!firstDivisionContainStr(secondDivision, str)) {
			secondDivision.push(str);
		}
	});

	var innerValue = ""

	innerValue += '<div class="container box_1170"><table class="table table-bordered"><tbody class="text-center">';

	for (var i = 0; i < secondDivision.length; i++) {

		if (i % 4 == 0) {
			innerValue += '<tr>';
		}

		if (i == 0) {
			var rowsp = Math.ceil(secondDivision.length / 4) + 1;
			innerValue += '<td style="vertical-align: center; background-color: orange; color: white;" rowspan="' + rowsp + '">전체</td>';
			continue;
		}

		innerValue += '<td>';

		innerValue += secondDivision[i];

		innerValue += '</td>';


		if (i % 4 == 3) {
			innerValue += "</tr>";
		}
	}

	innerValue += '</tbody></table></div>';

	// 2차 지역 라벨 클리어 
	$('#div2').empty();
	$('#div2').html(innerValue);
}

// 2차 지역 라벨 클릭 시 이벤트 동적 생성
$(document).on("click", "#div2 > div > table > tbody > tr > td", function(event) {

	var targetValue = $(event.target).text();
	cardarr = [];
	if(targetValue === "전체") {
		return;
	} else {
		selectedSecond = targetValue;
		
		
		
		$.each(notNullTotalResult, function(index, item){
			if(item.region1cd.label === selectedFirst && item.region2cd.label === selectedSecond) {
				cardarr.push(item);
			}
		});

	}
	
	currentPage = 1;
	
	totalPage = cardarr.length;
	pager(totalPage, currentPage, pageSize);
	
	document.querySelector('#paging').style.display = "block";
	
});

// 카드 생성
function setCard(arr) {

	var innerValue = "";

	//$.each(arr, function(index, item) {

		// <a href="#" id="myBtn"></a>
		innerValue += `<div class="col-xl-4 col-lg-6 col-md-6">
                        <!-- Single Room -->
                        <div class="single-room mb-50">
                            <div class="room-img">
                               <img src="${arr.repPhoto.photoid.imgpath}" alt="" id="myBtn" class="card-image">
								<input type="hidden" value="${arr.contentsid}">
                            </div>
                            <div class="room-caption text-center">
                                <h3><a href="#" id="myBtn">${arr.title}</a><input type="hidden" value="${arr.contentsid}"></h3>
								<div>${selectedFirst} > ${selectedSecond}</div>
								<div class="mt-3">
									<button class="genric-btn warning circle" id="button-insert">담기</button>
									<input type="hidden" value="${arr.contentsid}">
								</div>
                            </div>
                        </div>
                       </div>`;
	
	$('#row').append(innerValue);
	//});
}

var pageSize = 6;
var currentPage = 1;
var viewPageCount = 3; 

// 페이지 클릭 시 이벤트
$(document).on("click", "#page > a", function(event){
	event.preventDefault();
	var number = event.target.text.trim();
	var totalPage = cardarr.length;
	
	if(number === ">") {
		currentPage = currentPage + viewPageCount;
	}
	else if(number === "<") {
		currentPage = (currentPage - viewPageCount) <= 0 ? 1 : currentPage - viewPageCount;
	} else {
		currentPage = parseInt(number);
	}
	
	pager(totalPage, currentPage, pageSize);
})

// 페이징 처리
function pager(total, currentPage, pageSize) {
	$('#row').empty();
	$('.category-choice').addClass('display-none');
	
	if(currentPage>maxPage){
		currentPage = maxPage;
	}
	
	
	var start = (currentPage - 1) * pageSize;
	var end = currentPage * pageSize;
	
	var maxPage = Math.ceil(total / pageSize); 
	
	
	
	
	for(var i = start; i < end; i++) {
	
		if(i >= total) { break; }
	
		//setCard(arr[i].items);
		setCard(cardarr[i]);
	}
	
	var innerValue = "";
	
	// 시작 페이지
	if(currentPage < viewPageCount) {
	
		innerValue += `<div id="page">`;
		for(var i = 1; i <= viewPageCount; i++){
			
			if(i == currentPage) {
				innerValue += `<span class="genric-btn success radius">${i}</span>`;
				if(maxPage == i) {
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

// 모달 부분
var modal = document.getElementById('myModal');
var btn = document.getElementById('myBtn');

var span = document.getElementsByClassName('close')[0];

$(document).on("click", "#myBtn", function(event) {
	$('#myModal').empty();

	event.preventDefault();

	var contentId = $(event.target).next().val();

	var form_data = {
		apiKey: API_KEY,
		locale: LOCALE,
		cid: contentId
	};

	$.ajax(
		{
			url: API_URL,
			data: form_data,
			success: function(response) {
				var innerValue = "" + `<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="myModalLabel">${response.items[0].title}</h4>
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
												</div>
												<div class="modal-body text-center">
													<img id = "image" class="" src="${response.items[0].repPhoto.photoid.imgpath}">
													<div class = "modal-title-area">
														<p class="category" style="font-size: 18px">${selectedFirst} > ${selectedSecond}</p>
										                <p class="intro-tag" style="font-size: 14px; color: #e68a00;">${"#" + (response.items[0].tag).replaceAll(",", " #")}</p>
									                </div>
									                <div class = "modal-intro-area">
										                <p class="intro-text" align="center" style="font-size: 16px;">${response.items[0].introduction}</p>
										                <button type="button" class="genric-btn primary-border radius" onclick="createMap(${response.items[0].latitude}, ${response.items[0].longitude})">위치보기</button>
														<p class="intro-addr" align="center" style="font-size: 12px;">주소: ${response.items[0].address}</p>
									                </div>
									                <div class = "modal-map-area">
									                	<div id="map" class="map"></div>
									                </div>
												</div>
												<div class="modal-footer text-center" style="display: block;">
													<button type="button" class="genric-btn warning circle" id="button-insert">담기</button>
													<input type="hidden" value="${response.items[0].contentsid}">
												</div>
											</div>
										</div>`;
										
				$('#myModal').empty();
				$('#myModal').html(innerValue);
				$('#myModal').modal('show');
				
				//createMap(response.items[0].latitude, response.items[0].longitude);
				
				/******모달 내부 map*******/
				/*var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center: new kakao.maps.LatLng(response.items[0].latitude, response.items[0].longitude), // 지도의 중심좌표
						level: 9 // 지도의 확대 레벨
					};
				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

				// 마커가 표시될 위치입니다 
				var markerPosition = new kakao.maps.LatLng(response.items[0].latitude, response.items[0].longitude);

				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
					position: markerPosition
				});

				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);*/

			}
		}
	)

});

function createMap(lat,lon) {
	$('.intro-addr').empty();
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
						level: 6 // 지도의 확대 레벨
					};
				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

				// 마커가 표시될 위치입니다 
				var markerPosition = new kakao.maps.LatLng(lat, lon);

				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
					position: markerPosition
				});

				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);
}


$(document).on("click", ".close", function(event) {
	$('#myModal').modal('hide');
});
                 
// 모달 창 이외의 곳 클릭시에도 모달창 닫기
window.onclick = function(event) {
	if (event.target == modal) {
		$('#myModal').modal('hide');
	}
}

// 찜목록 담기 비동기 처리
$(document).on("click", "#button-insert", function(event) {
	const targetValue = $(event.target).next().val();
	const url = "insertcartlist.tourlist";
	const form_data = {
		contentId: targetValue
	}

	$.ajax(
		{
			url: url,
			data: form_data,
			success: function(response) {
				swal(response);
			}
		}
	)
});