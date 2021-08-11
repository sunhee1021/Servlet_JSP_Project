const API_URL = "http://api.visitjeju.net/vsjApi/contents/searchList";
const API_KEY = "wuuv42tnk9hazf5h"
const LOCALE = "kr";

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
	center : new kakao.maps.LatLng(33.36159410409114, 126.52920948469817), // 지도의 중심좌표
	// 지도의 확대 레벨
	level : 10
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//관광지 API URL
var url = "http://api.visitjeju.net/vsjApi/contents/searchList";

//샘플 사용자가 담아둔 관광지 목록의 contentID
//var markerArray = ["CNTS_000000000021622","CNTS_200000000009833","CNTS_000000000022065","CNTS_200000000007536"];
var markerArray = $('#myTourList').val().replaceAll("[","").replaceAll("]","").split(", ");

// 내 찜목록 배열
var myTourList = [];

//마커를 표시할 위치와 title 객체 배열
var positions = [];

init();

function init() {
	
	if(markerArray.length == 0 || markerArray[0] === "") {
		document.querySelector('#test').style.display = "none";
		return;
	}
	
	//해당 유저가 담아둔 관광지의 개수만큼 배열 돌기
	for(var index in markerArray) {
		var data = {
					apiKey: "wuuv42tnk9hazf5h",
					locale: "kr",
					cid: markerArray[index]
				};
		
		//JSON데이터를 가져온다
		$.getJSON(url, data, function(response) {
			//내 찜목록 배열에 응답 결과 넣기
			myTourList.push(response);
			//마커 생성하기 위한 해당 관광지의 위도, 경도값 받아두기
			var marker = {
							title: response.items[0].title,
							latlng: new kakao.maps.LatLng(response.items[0].latitude, response.items[0].longitude)
						}
			//마커 배열에 넣기
			positions.push(marker);
			
			//마커 이미지의 이미지 주소
			var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
			
			//넣어둔 마커 배열의 수만큼 마커 찍기
			for (var i = 0; i < positions.length; i ++) {
			    // 마커 이미지의 이미지 크기
			    var imageSize = new kakao.maps.Size(24, 35); 
			    // 마커 이미지를 생성합니다    
			    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
			    
			    // 마커를 생성합니다
			    var marker = new kakao.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng, // 마커를 표시할 위치
			        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			        image : markerImage // 마커 이미지 
			    });
			}
			
		}); 
	
	};
	
}

// 카드 생성
function setCard(arr) {
	
	var innerValue = "";
	
	$.each(arr, function(index, item) {
		
		// <a href="#" id="myBtn"></a>
		innerValue += `<div class="col-xl-4 col-lg-6 col-md-6">
                        <!-- Single Room -->
                        <div class="single-room mb-50">
                            <div class="room-img">
                               <img src="${item.repPhoto.photoid.imgpath}" alt="" id="myBtn" class="card-image">
								<input type="hidden" value="${item.contentsid}">
                            </div>
                            <div class="room-caption text-center">
                                <h3><a href="#" id="myBtn">${item.title}</a><input type="hidden" value="${item.contentsid}"></h3>
								<div>${item.region1cd.label} > ${item.region2cd.label}</div>
								<div>${"#"+(item.tag).replaceAll(",", " #")}</div>
								<div class="mt-3">
									<button class="genric-btn warning circle" id="button-delete">삭제</button>
									<input type="hidden" value="${item.contentsid}">
								</div>
                            </div>
                        </div>
                       </div>`;
	});
	
	
	$('#row').append(innerValue);
}

var pageSize = 6;
var currentPage = 1;
var viewPageCount = 3;

$('#test').on("click", function(event){
	
	event.preventDefault();

	event.target.parentNode.style.display = "none";

	var totalPage = myTourList.length;
	
	pager(totalPage, currentPage, pageSize);
	
});

// 페이지 클릭 시 이벤트
$(document).on("click", "#page > a", function(event){
	event.preventDefault();
	var number = event.target.text.trim();
	var totalPage = myTourList.length;
	
	if(number === ">") {
		currentPage = (currentPage + viewPageCount > Math.ceil(total / pageSize)) ? Math.ceil(total / pageSize) : currentPage + viewPageCount;
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
	
	var start = (currentPage - 1) * pageSize;
	var end = currentPage * pageSize;
	
	var maxPage = Math.ceil(total / pageSize); 
	
	for(var i = start; i < end; i++) {
	
		if(i >= total) { break; }
	
		setCard(myTourList[i].items);
	}
	
	var innerValue = "";
	
	// 시작 페이지
	if(currentPage <= viewPageCount) {
	
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
														<p class="category" style="font-size: 18px">${response.items[0].region1cd.label} > ${response.items[0].region2cd.label}</p>
										                <p class="intro-tag" style="font-size: 14px; color: #e68a00;">${"#" + (response.items[0].tag).replaceAll(",", " #")}</p>
									                </div>
									                <div class = "modal-intro-area">
										                <p class="intro-text" align="center" style="font-size: 16px;">${response.items[0].introduction}</p>
										                <button type="button" class="genric-btn primary-border radius" onclick="createMap(${response.items[0].latitude}, ${response.items[0].longitude})">위치보기</button>
														<p class="intro-addr" align="center" style="font-size: 12px;">주소: ${response.items[0].address}</p>
									                </div>
									                <div class = "modal-map-area">
									                	<div id="modal-map" class="map"></div>
									                </div>
												</div>
												<div class="modal-footer text-center" style="display: block;">
													<button type="button" class="genric-btn warning circle" id="button-delete">삭제</button>
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
	
	var mapContainer = document.getElementById('modal-map'), // 지도를 표시할 div 
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

//테스트해볼게여 delete
$(document).on("click", "#button-delete", function(event) {
	const targetValue = $(event.target).next().val();
	const url = "deletecartlist.tourlist";
	const form_data = {
		contentId: targetValue
	}

	$.ajax(
		{
			url: url,
			data: form_data,
			success: function(response) {
				swal(response);
				$.each(myTourList, function(index, item) {
					
					if(item.items[0].contentsid === targetValue) {
						myTourList.splice(index, 1);
						return false;
					}
				});
				
				var total = myTourList.length;
				currentPage = 1;
				$('#row').empty();
				pager(total, 1, pageSize);
				$('#myModal').modal('hide');
				
				// 맵을 다시 생성해서 마커 재배치
				setMap();		
				
				if(myTourList.length == 0 || myTourList[0] === "") {
					document.querySelector('#test').style.display = "none";
					document.querySelector('#paging').style.display = "none";
					return;
				}
				
			}
		}
	)
});

function setMap() {
	
	$('#map').empty();
				
	mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new kakao.maps.LatLng(33.36159410409114, 126.52920948469817), // 지도의 중심좌표
		// 지도의 확대 레벨
		level : 10
	};
	
	map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	positions = [];
	
	$.each(myTourList, function(index, response) {
		var marker = {
				title: response.items[0].title,
				latlng: new kakao.maps.LatLng(response.items[0].latitude, response.items[0].longitude)
			}

		positions.push(marker);	
	});
	
	
	//마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	    
	for (var i = 0; i < positions.length; i ++) {
	    
	    // 마커 이미지의 이미지 크기 입니다
	    var imageSize = new kakao.maps.Size(24, 35); 
	    
	    // 마커 이미지를 생성합니다    
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
	    
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng, // 마커를 표시할 위치
	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
	        image : markerImage // 마커 이미지 
	    });
	}
	
	
}