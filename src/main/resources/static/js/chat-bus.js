/**
 * 
 */

$(function(){
	$("#question").keyup(questionKeyuped);
});
function openChat(){
	setConnectStated(true);//챗창보이게처리
	connect();
	//mqConnect();
}

function showMessage(message) {
    $("#chat-content").append(message);
	//대화창 스크롤을 항상 최하위에 배치   
    $("#chat-content").scrollTop($("#chat-content").prop("scrollHeight"));
}

function setConnectStated(isTrue){
	if(isTrue){//true
		$("#btn-chat-open").hide();
		$("#chat-disp").show();
	}else{
		$("#btn-chat-open").show();
		$("#chat-disp").hide();
	}
	//챗봇창 화면 클리어
	$("#chat-content").html("");
}

function disconnect() {
    setConnectStated(false);
    console.log("Disconnected");
}
//버튼클릭시 접속
function connect() {
	var formData={message:"안녕", order:0};
	sendMessage(formData);
}

function sendMessage(formData){
	
	//주소에 대한 좌표확인
	
	if(formData.order==1){
		console.log("입력된 주소의 좌표값확인");
		getPositionOfAddress(formData);
		return;
	}
	
	exec(formData);
	
}

function stationClicked(el){
	console.log($(el).siblings(".arsId").val());
	
	//var question=$("#question").val().trim();//질문에대한답
	var forms=$(".form");
	var form=$(forms[forms.length-1]);
	var formData={
		message:$(el).siblings(".arsId").val(),
		order:form.find(".order").val(),
		areaInfo:form.find(".areaInfo").val(),
		nx:form.find(".nx").val(),
		ny:form.find(".ny").val(),
		arsId:$(el).siblings(".arsId").val(),
		stationNm:$(el).text()
		
	};
	
	exec(formData);
}

function exec(formData){
	$.ajax({
		url:`/bus-bot/${formData.order}`,
		type:"post",
		data:formData,
		success:function(responsedHtml){
			showMessage(responsedHtml);
			if(formData.order==1){
				getBusStation(formData);
			}else if(formData.order==2){
				getStationByUidItem(formData);
			}
		}
	});
}

function getStationByUidItem(formData){
	$.ajax({
		url:`/bus-bot/busOfArrival/${formData.arsId}`,
		success:function(result){
			var targets=$(".select-list");
			$(targets[targets.length-1]).html(result);
		}
	});
}


//입력된주소로 좌표얻어오기
function getPositionOfAddress(formData){
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	var rs={};
	geocoder.addressSearch(formData.message, function(result, status) {
			
		if (status === kakao.maps.services.Status.OK) {
			
			rs['x']=result[0].x; 
			rs['y']=result[0].y;
			//rs=dfs_xy_conv("toXY", result[0].y, result[0].x);
			
		}else{
			rs['x']=0;
			rs['y']=0;
		}
		console.log("x:"+rs.x);
		console.log("y:"+rs.y);
		formData.nx=rs.x;
		formData.ny=rs.y;
		if(rs.x==0){
			showMessage(inputTagWrontText("잘못입력된 장소입니다.</br>다시입력해주세요"));
			return;}
		//getBusStation(formData);
		exec(formData)
	});
	
}

function getBusStation(formData){
	console.log(">>>"+formData.nx);
	console.log(">>>"+formData.ny);
	var radius=400;
	$.ajax({
		url: `/bus-bot/getBusStation/${radius}`,
		type: "post",
		data:{
			radius:radius,
			tmX:formData.nx,
			tmY:formData.ny
			},
		success:function(busStationListHtml){
			var targets=$(".select-list");
			$(targets[targets.length-1]).html(busStationListHtml);
		}
	});
}


function inputTagWrontText(text){
	var now=new Date();
	var ampm=(now.getHours()>11)?"오후":"오전";
	var time= ampm + now.getHours()%12+":"+now.getMinutes();
	var message=`
		
		<div class="msg bot flex">
			<div  class="icon">
				<img src="/images/icon/robot-solid.svg">
			</div>
			<div class="message">
				<div class="part">
					<p>${text}</p>
				</div>
				<div class="time">${time}</div>
			</div>
		</div>
	`;
	return message;
}


function inputTagString(text){
	var now=new Date();
	var ampm=(now.getHours()>11)?"오후":"오전";
	var time= ampm + now.getHours()%12+":"+now.getMinutes();
	var message=`
		<div class="msg user flex end">
			<div class="message">
				<div class="part">
					<p>${text}</p>
				</div>
				<div class="time">${time}</div>
			</div>
		</div>
	`;
	return message;
}
//메뉴클릭시 메뉴 텍스트 화면에 표현 
function menuclicked(el){
	var text=$(el).text().trim();
	var fToken=$(el).siblings(".f-token").val();
	console.log("-----> fToken:"+fToken+"----");
	var message=inputTagString(text);
	showMessage(message);
}

//엔터가 입력이되면 질문을 텍스트 화면에 표현 
function questionKeyuped(event){
	if(event.keyCode!=13)return;
	btnMsgSendClicked()
}

//전송버튼 클릭이되면 질문을 텍스트 화면에 표현
function btnMsgSendClicked(){
	var question=$("#question").val().trim();//질문에대한답
	var forms=$(".form");
	var form=$(forms[forms.length-1]);
	var formData={
		message:question,
		order:form.find(".order").val(),
		//areaInfo:form.find(".areaInfo").val(),
		//nx:form.find(".nx").val(),
		//ny:form.find(".ny").val(),
		
	};
	
	
	if(question=="" || question.length<2)return;
	//메세지 서버로 전달
	console.log(formData);
	//var order=$(forms[forms.length-1]).find(".order");
	sendMessage(formData);
	 
	var message=inputTagString(question);
	showMessage(message);//사용자가 입력한 메세지 채팅창에 출력
	$("#question").val("");//질문 input 리셋
}
