//문서가 로딩되면 자동으로 실행하는 함수
$(function(){
	setDepartList();
	viewmemberList();
	$("#org_tree").hide();
});

//부서+팀 데이터를 가져오는 함수
function setDepartList(){
	$.ajax({
   		url:"/member/departments",
   		type:"get",
   		success:function(result){
   			//페이지가 로딩된이후에
   			//ajax를이용해서 동적으로 부서관련 태그들 생성된다
   			//동적으로 생성된 태그의 이벤트는 태그인라인 이벤트를 활용했습니다.
   			$("#org_tree").html(result);
   			//viewmemberList();
   		}
    });
}//close jquery

//사원정보를 가져오는 함수
function viewmemberList(){
	$.ajax({
   		url:"/member/all/empList",
   		type:"get",
   		success:function(resultHtml){
   			$("#sel_memList").html(resultHtml);
   		}
    });
}//close jquery

//부서명 클릭시 부서소속사원데려오기
//function viewDepMemList(){
//	var dept_id=target.siblings(".department_id").val();//해당부서의 pk
//	$.ajax({
//   		url:"/member/DepMem/empList"+dept_id,
//   		type:"get",
//   		success:function(resultHtml){
//   			$("#sel_memList").html(resultHtml);
//   		}
//    });
//}//close jquery
//팀명 클릭시 팀소속사원데려오기

function btnTeamNameClicked(el){
	var target=$(el);
	var team_id=target.siblings(".team_id").val();
	//alert(team_id);
	$.ajax({
   		url:"/member/TeamMem/empList/"+team_id,
   		type:"get",
   		success:function(resultHtml){
   			$("#sel_memList").html(resultHtml);
   		}
    });
}//close jquery

//회사명메뉴아이콘클릭시 회사메뉴토글
function corpIconMenuClicked(){
	$("#corp_menu").slideToggle(400);
}
//부서등록 클릭시 작성 창 보여주기
function btnDepartInsertClicked(el){
	$(el).hide();//자기자신 숨기고 
	$(el).siblings(".departAdd").show(); //작성창이 나타남
}; //close jquery

//부서등록
function addDepart(el,event) {
	 if (event.which == 13) {//인풋태그에 값을 입력하고 엔터키실행시
		var target=$(el);//인풋태그
        var depName=target.val(); 
        alert(depName);
        $.ajax({
     		url:"/admin/addDepart",
     		type:"post",
     		data: {"name": depName.trim()},
     		success:function(result){
     			target.hide();
       			setDepartList();
       			$("#org_tree").show();
			}
		});
	} 	
}

//회사명 클릭시 하위그룹(부서리스트) 토글
function viewDepartList(){
	$("#org_tree").slideToggle(400);
	//회사명 눌렸을때 해당 소속 사원 리스트 조회
	if($("#org_tree").css("display")!="none"){
		viewmemberList();
	}
}

//부서이름 클릭시 팀 리스트 토글
function btnDepartNameClicked(el){
	target=$(el).parents("ul").siblings(".team_list");
	$(".team_list").not(target).hide();
	target.slideToggle(400);
	var department=$(el).children(".dept-name").text();
	if(target.css("display")!="none"){
		$.ajax({
			url:"/admin/all/empList",
			type:"get",
			data: {"department": $(el).val().trim()},
			success:function(resultHtml){
				$("#sel_memList").html(resultHtml);
				//viewDepMemList();
			}
		});
	}
}//close jquery

//부메클릭시 부서메뉴리스트 토글
function depIconMenuClicked(el){
	var target=$(el).parents("ul").siblings(".depart_menu");
	//다른 부서의 메뉴아이콘 클릭시 닫아주기
	$(".depart_menu").not(target).hide();
	//이미 토글된 다른 팀 메뉴 숨겨주기
	$(".team_manu").not(target).hide();
	target.slideToggle(400);
}//close jquery

//부메리-팀 등록클릭시 작성창 보여주기
function btnTeamInsertClicked(el){
	$(el).hide();//자기자신 숨기고 
	$(el).siblings(".teamInsert").show(); //작성창이 나타남
}; //close jquery

//부메리-팀등록-값입력 후 엔터이벤트실행
 function addTeam(el,event) {
	 if (event.which == 13) {//인풋태그에 값을 입력하고 엔터키실행시

		var target=$(el);//인풋태그
        var dept_id=target.siblings(".department_id").val();//해당부서의 pk
        var departmentName=target.parents(".depart_menu").siblings(".departList").children(".btn_depart_name").find(".dept-name").text();
        var name=target.text(); //팀명이 입력되는 인풋값에 텍스트
        alert(departmentName+"의 소속되는 그룹으로 추가합니다");
        $.ajax({
     		url:"/admin/teamInsert/"+dept_id,
     		type:"post",
     		data: {"name": $(el).val().trim()},
     		success:function(result){
     			target.parents(".depart_menu").siblings(".departList").children(".btn_depart_name").find(".dept-name").text(result); 
       			setDepartList();
			}
		});
	} 	
}
//부메리-부서 삭제 기능 
function depDelete(el){
	var target=$(el);//클릭한 삭제버튼태그
	var dep_id = target.siblings(".department_id").val();//클릭한 삭제버튼의 부서번호
	var departmentName=target.parents(".depart_menu").siblings(".departList").children(".btn_depart_name").find(".dept-name").text();//클릭한 삭제버튼의 부서이름
	//alert(dep_id); 부서의 pk값이 나옴 
	if(confirm(departmentName+" 를 정말 삭제하시겠습니까?")){//삭제처리시 물어보기
		$.ajax({
       		url:"/admin/depDelete/"+dep_id, //부서번호로 경로 넘겨주기
       		type:"delete",		//딜리트매핑이니까 타입도 딜리트
       		success:function(result){	//성공했을경우.
       			alert(departmentName+"가 삭제되었습니다.");
       			setDepartList();
       		}//success:function
		});//$.ajax
	}	//if
} //function depDelete

//부서수정 클릭시 부서명입력창
function depUpdate(el){
	$(el).hide(); //부서수정사라지고
	$(el).siblings(".depUpdate").show();//작성창만나타남
}; //close jquery

//부서명 수정
function depUpdateKeyup(el,event) {
    if (event.which == 13) {//엔터키가 실행될 경우
	
    	var target=$(el);//엔터키가 실행된 태그
    	var dept_id=target.siblings(".department_id").val();//엔터키가 실행된 태그 중에서 department_id의 클래스 이름을 가지고 있는 형제태그
    	var departmentName=target.parents(".depart_menu").siblings(".btn_depart_name").find(".dept-name").text();
    	//alert(dept_id);//해당 부서의 pk값
    	//*
       	$.ajax({ 
       		url:"/admin/depUpdate/"+dept_id, 
       		type:"post",							//공백제거
       		data: {"departmentName": $(el).val().trim()}, //입력된 값의 변수이름:입력창.변수.공백제거
       		success:function(result){
       			setDepartList();
       		}
        });
    }
}
//팀메클릭시 팀메뉴리스트 토글
function TeamIconMenuClicked(el){
	var target=$(el).parents(".teamBox").siblings(".team_manu");
	//이미 토글된 다른 팀 메뉴 숨겨주기
	$(".team_manu").not(target).hide();
	//이미 토글된 다른부서 숨겨주기
	$(".depart_menu").not(target).hide();
	target.slideToggle(400);
}//close jquery

//팀메리-팀 삭제
function teamDelete(el){
	var target=$(el);//클릭한 삭제버튼태그
	var team_id=target.siblings(".team_id").val();//클릭된 
	var name=target.parents(".team_manu").siblings(".teamBox").children(".team_name").text();
	if(confirm(name+" 을 정말 삭제하시겠습니까?")){
		$.ajax({
       		url:"/admin/teamDelete/"+team_id,
       		type:"delete",		
       		success:function(result){	
       			target.parents(".team_list").remove(); 
       			alert(name+"이 삭제되었습니다.");
       			setDepartList();
       		}//success:function
       			
       		
		});
	}	
} //close jquery

//팀수정 클릭이벤트시
function teamUpdate(el){
	$(el).hide(); //부서수정사라지고
	$(el).siblings(".teamUpdate").show();//작성창만나타남
}; //close jquery

//팀메리-팀명 수정
function teamUpdateKeyup(el,event) {
    if (event.which == 13) {//엔터키가 실행될 경우

    	var target=$(el);//엔터키가 실행된 태그
    	var team_id=target.siblings(".team_id").val();
    	var teamName=target.parents(".team_menu").siblings(".teamBox").children("team_name").text();
       	$.ajax({ 
       		url:"/admin/teamUpdate/"+team_id, 
       		type:"post",							//공백제거
       		data: {"teamName": $(el).val().trim()}, //입력된 값의 변수이름:입력창.변수.공백제거
       		success:function(result){
       		setDepartList();
       		}
        });
    }
}
