/*
function reloadDivArea() {	//reload 새로고침 함수
    $('#commentList').load(location.href+' #commentList');
}
*/
$(document).on("click",'#submit', function(){	// 댓글입력박스 
	let bidx = $("form[id=submitUpdate]").serialize();
	$.ajax({
		method:"POST",
		url:"comment-regi.do",
		data: bidx,
	})
	.done(function(data){
		alert( "Data Saved: " + data );  
	    $('#commentList').html(data);
	    $('#content').val("");
	    //reloadDivArea();
	})
})

$(document).on('click', '.btnUpdateForm', function () {	//수정 폼 오픈 버튼 
	console.log('클릭');
	let contents = $(this).parent().parent().find('.cont').text();
	if (contents!=null) {
		console.log('값이존재');
		console.log('값'+ contents);
	};
	$(this).parent().parent().next().css('display', '');
	$(this).parent().parent().next().find('.editBox').val(contents);
});
$(document).on('click', '.btnCancel', function() {	// 수정 폼 버튼 닫기
	console.log('클릭');
	$(this).parent().parent().css('display','none');
});

$(document).on('click', '.btnUpdate', function () {	// 수정 내용, 넘버   to Controller
	let contents = $(this).prev().val();
	let cno = $(this).attr('cno');
	let bno = $(this).attr('bno');
	$.ajax({
		  method: "POST",
		  url: "comment-edit-update.do",
		  data: { comment: contents, comment_num: cno, b_idx: bno}
		})
	  .done(function( data ) {
	    alert( "Data Saved: " + data );  
	    $('#commentList').html(data);
	  });
});

$(document).on('click','.btnDelete', function() {	// 삭제 클릭 -> controller info pass 
	console.log('click');
	let cno = $(this).attr('cnodelete');
	let bno = $(this).attr('bnodelete');
	$.ajax({
		method:"POST",
		url: "comment-delete.do",
		data: {comment_num: cno,
			   b_idx: bno	   
		}
	})
	.done(function(data){	// 완료 후 html 수정된 html data 받아오기
		alert("삭제완료", + data);
		$('#commentList').html(data);
	});
});
$(document).on('click', '.btnReplyForm', function () {	//답글 폼 오픈 버튼 
	console.log('클릭');
	$(this).parent().parent().next().next().css('display','');
	
});
$(document).on('click', '.btnCancelR', function() {	// 답글 폼 버튼 닫기
	console.log('클릭');
	$(this).parent().parent().css('display','none');
});
$(document).on('click','.btnReply', function(){
	let contents = $(this).prev().val();
	let bidx = $(this).attr('bidx');
	let order = $(this).attr('order');
	let group = $(this).attr('group');
	let depth  = $(this).attr('depth');
	
	$.ajax({
		method:"POST",
		url:"comment-reply.do",
		data:{
			contents: contents,
			bidx: bidx,
			order: order,
			groupNum: group,
			depth: depth
		}		
	})
	.done(function(data){	// 완료 후 html 수정된 html data 받아오기
		alert("답글완료", + data);
		$('#commentList').html(data);
	});
});
