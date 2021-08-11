/* 유닉스 타임 날짜 변화함수 스타트 */

/*
유닉스타임란?: 1185397439 이런식으로 들어오는 시간데이터!
*/

    	function UnixTimestamp(time) {
			// 유닉스 타임에 1000을 곱해줘요
    		var date = new Date(time * 1000);
			// 현지시간에 따라 지정된 연도를 반환해요			
    		var year = date.getFullYear();
			// 현지시간에 따라 지정된 월을 반환하는데 0부터라 1을 더해줘요
    		var month = (date.getMonth() + 1);
			// 현지시간에 따라 지정된 일(며칠인지)을 반환해줘요
    		var day = date.getDate();
    		
			// 날짜값을 리턴해줘요
    		return ({
    			date : date,
    			year : year,
    			month : month,
    			day : day,
    		});
    	}
    	/* 유닉스 타임 날짜 변화함수 끝 */
		
		
		//페이지가 로딩되면 시작하는 이벤트
    	window.onload = function() {

    	// API가 들어오는 URL
    	var wUrl = "https://api.openweathermap.org/data/2.5/onecall?lat=33.5097&lon=126.5219&exclude=hourly,minutely,current&appid=61698f0d96bc4fe8c944ed0ff63b195f&units=metric&lang=kr";
    	var jUrl = "http://api.visitjeju.net/vsjApi/contents/searchList?apiKey=wuuv42tnk9hazf5h&locale=kr&page=50"
    			
    	
		/* 	날씨 시작	*/
    	// 각 날짜 객체에 날짜와 온도정보 날씨를 넣을거에요
    	
		var day1 = {}
    	,day2 = {}
    	,day3 = {}
    	,day4 = {}
    	,day5 = {}
    	,day6 = {}
    	,day7 = {}
		,days = {};
		
		// 그걸 배열로 담아요
		var dayArr=[day1,day2,day3,day4,day5,day6,day7];
		
    	// 데이터를 넣어요
    	$.getJSON(wUrl, function(data) {
			for(i=0; i<dayArr.length; i++){
				// 날짜 데이터를 넣어요
				dayArr[i].date = UnixTimestamp(data.daily[i].dt);
				// 기온 데이터를 넣어요
				dayArr[i].temp = data.daily[i].temp;
				// 날씨 정보를 넣어요
    			dayArr[i].weather = data.daily[i].weather;
			}
    		
    		// 날짜 정보를 가공해요
    		let day1Date = dayArr[0].date.year+"년 "+dayArr[0].date.month+"월 "+dayArr[0].date.day+"일"
    		let day2Date = dayArr[1].date.year+"년 "+dayArr[1].date.month+"월 "+dayArr[1].date.day+"일"
    		let day3Date = dayArr[2].date.year+"년 "+dayArr[2].date.month+"월 "+dayArr[2].date.day+"일"
    		let day4Date = dayArr[3].date.year+"년 "+dayArr[3].date.month+"월 "+dayArr[3].date.day+"일"
    		let day5Date = dayArr[4].date.year+"년 "+dayArr[4].date.month+"월 "+dayArr[4].date.day+"일"
    		let day6Date = dayArr[5].date.year+"년 "+dayArr[5].date.month+"월 "+dayArr[5].date.day+"일"
    		let day7Date = dayArr[6].date.year+"년 "+dayArr[6].date.month+"월 "+dayArr[6].date.day+"일"
    		
    		// 날짜 정보를 보여줘요
    		$(".day1").append("<a>"+day1Date+"</a>");
    		$(".day2").append("<a>"+day2Date+"</a>");
    		$(".day3").append("<a>"+day3Date+"</a>");
    		$(".day4").append("<a>"+day4Date+"</a>");
    		$(".day5").append("<a>"+day5Date+"</a>");
    		$(".day6").append("<a>"+day6Date+"</a>");
    		$(".day7").append("<a>"+day7Date+"</a>");
    		
    		// 날씨 아이콘을 보여줘요
    		$(".day1icon").find('img').attr('src','http://openweathermap.org/img/wn/'+dayArr[0].weather[0].icon+'@2x.png');
    		$(".day2icon").find('img').attr('src','http://openweathermap.org/img/wn/'+dayArr[1].weather[0].icon+'@2x.png');
    		$(".day3icon").find('img').attr('src','http://openweathermap.org/img/wn/'+dayArr[2].weather[0].icon+'@2x.png');
    		$(".day4icon").find('img').attr('src','http://openweathermap.org/img/wn/'+dayArr[3].weather[0].icon+'@2x.png');
    		$(".day5icon").find('img').attr('src','http://openweathermap.org/img/wn/'+dayArr[4].weather[0].icon+'@2x.png');
    		$(".day6icon").find('img').attr('src','http://openweathermap.org/img/wn/'+dayArr[5].weather[0].icon+'@2x.png');
    		$(".day7icon").find('img').attr('src','http://openweathermap.org/img/wn/'+dayArr[6].weather[0].icon+'@2x.png');
    		
    		// 날씨 설명을 보여줘요
    		$(".day1description").append(dayArr[1].weather[0].description);
    		$(".day2description").append(dayArr[2].weather[0].description);
    		$(".day3description").append(dayArr[3].weather[0].description);
    		$(".day4description").append(dayArr[4].weather[0].description);
    		$(".day5description").append(dayArr[5].weather[0].description);
    		$(".day6description").append(dayArr[6].weather[0].description);
    			

    		// 온도를 가공해서 보여줘요
    		$(".day1temp").append("최고 기온: "+dayArr[0].temp.max+" ℃<br>최저 기온: "+dayArr[0].temp.min+" ℃");
    		$(".day2temp").append("최고 기온: "+dayArr[1].temp.max+" ℃<br>최저 기온: "+dayArr[1].temp.min+" ℃");
    		$(".day3temp").append("최고 기온: "+dayArr[2].temp.max+" ℃<br>최저 기온: "+dayArr[2].temp.min+" ℃");
    		$(".day4temp").append("최고 기온: "+dayArr[3].temp.max+" ℃<br>최저 기온: "+dayArr[3].temp.min+" ℃");
    		$(".day5temp").append("최고 기온: "+dayArr[4].temp.max+" ℃<br>최저 기온: "+dayArr[4].temp.min+" ℃");
    		$(".day6temp").append("최고 기온: "+dayArr[5].temp.max+" ℃<br>최저 기온: "+dayArr[5].temp.min+" ℃");
    		$(".day7temp").append("최고 기온: "+dayArr[6].temp.max+" ℃<br>최저 기온: "+dayArr[6].temp.min+" ℃");
    		/* 	날씨 	끝*/
    		});
    	
    	/* 랜덤 이미지 갤러리 시작 */
    	let randomImg = Math.floor(Math.random()*1000);
    	
    	
    	$.getJSON(jUrl, function(data) {
    		console.log(data.items[randomImg+1].repPhoto.photoid.imgpath);
    		var random1= data.items[randomImg];
    		var random2= data.items[randomImg+1];
    		var random3= data.items[randomImg+2];
    		var random4= data.items[randomImg+3];
    		var random5= data.items[randomImg+4];
    		var random6= data.items[randomImg+5];
    		var random7= data.items[randomImg+6];
    		var random8= data.items[randomImg+7];
    		var random9= data.items[randomImg+8];
    		var random10= data.items[randomImg+9];
    		var random11= data.items[randomImg+10];
    		var random12= data.items[randomImg+11];
    		
    		$(".random1").find('img').attr('src',random1.repPhoto.photoid.imgpath);
    		$(".random2").find('img').attr('src',random2.repPhoto.photoid.imgpath);
    		$(".random3").find('img').attr('src',random3.repPhoto.photoid.imgpath);
    		$(".random4").find('img').attr('src',random4.repPhoto.photoid.imgpath);
    		$(".random5").find('img').attr('src',random5.repPhoto.photoid.imgpath);
    		$(".random6").find('img').attr('src',random6.repPhoto.photoid.imgpath);
    		$(".random7").find('img').attr('src',random7.repPhoto.photoid.imgpath);
    		$(".random8").find('img').attr('src',random8.repPhoto.photoid.imgpath);
    		$(".random9").find('img').attr('src',random9.repPhoto.photoid.imgpath);
    		$(".random10").find('img').attr('src',random10.repPhoto.photoid.imgpath);
    		$(".random11").find('img').attr('src',random11.repPhoto.photoid.imgpath);
    		$(".random12").find('img').attr('src',random12.repPhoto.photoid.imgpath);
    		
    		$(".random1title").append("<span class='randomTitle'><u>"+random1.title+"</u><br><span>"+random1.region1cd.label+" > "+random1.region2cd.label+"</span></span>");
    		$(".random2title").append("<span class='randomTitle'><u>"+random2.title+"</u> <br><span>"+random2.region1cd.label+" > "+random2.region2cd.label+"</span></span>");
    		$(".random3title").append("<span class='randomTitle'><u>"+random3.title+"</u> <br><span>"+random3.region1cd.label+" > "+random3.region2cd.label+"</span></span>");
    		$(".random4title").append("<span class='randomTitle'><u>"+random4.title+"</u> <br><span>"+random4.region1cd.label+" > "+random4.region2cd.label+"</span></span>");
    		$(".random5title").append("<span class='randomTitle'><u>"+random5.title+"</u> <br><span>"+random5.region1cd.label+" > "+random5.region2cd.label+"</span></span>");
    		$(".random6title").append("<span class='randomTitle'><u>"+random6.title+"</u> <br><span>"+random6.region1cd.label+" > "+random6.region2cd.label+"</span></span>");
    		$(".random7title").append("<span class='randomTitle'><u>"+random7.title+"</u> <br><span>"+random7.region1cd.label+" > "+random7.region2cd.label+"</span></span>");
    		$(".random8title").append("<span class='randomTitle'><u>"+random8.title+"</u> <br><span>"+random8.region1cd.label+" > "+random8.region2cd.label+"</span></span>");
    		$(".random9title").append("<span class='randomTitle'><u>"+random9.title+"</u> <br><span>"+random9.region1cd.label+" > "+random9.region2cd.label+"</span></span>");
    		$(".random10title").append("<span class='randomTitle'><u>"+random10.title+"</u> <br><span>"+random10.region1cd.label+" > "+random10.region2cd.label+"</span></span>");
    		$(".random11title").append("<span class='randomTitle'><u>"+random11.title+"</u> <br><span>"+random11.region1cd.label+" > "+random11.region2cd.label+"</span></span>");
    		$(".random12title").append("<span class='randomTitle'><u>"+random12.title+"</u> <br><span>"+random12.region1cd.label+" > "+random12.region2cd.label+"</span></span>");
    		
    	});
    	}