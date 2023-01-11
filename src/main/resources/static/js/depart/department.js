//회사명 클릭 부서토글



/**
 * 
 //부서수정시 엔터키 누르면 Enter Key 뜨게 함 이걸 전송으로 바꿔줘야하나?
$(document).ready(function(){
    $("#depUpdate").on("keyup", function(event) {
        if (event.which == 13) {
            alert("Enter Key");
        }
    });
});

 $("#depUpdate").enterkey(function(){
	$.ajax({
		url:"/admin/depUpdate{depId}",
		type:"post",
		async:false,//동기식
		data:queryString,
		success:function(){
			alert("수정완료");
		}
	});
});
 */

/**
$("#depUpdate").enterkey(function(){
	$("depUpdate").sumit();
	$.ajax({
		url:"/admin/depUpdate",
		type:"post",
		async:false,
		data:queryString,
		success:function(){
			alert("수정완료");
		}
	});
});
 */
/**
//삭제기능
function depDelete(el){
	var id = $(el).parent().siblings(".dtoName").text();
	if(confirm(name+"부서를 삭제하시겠습니까?")){
		location.href=`/admin/depDelete{depId}`;
	}
}
 */