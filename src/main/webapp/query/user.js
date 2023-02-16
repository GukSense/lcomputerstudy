
$(document).on('click','.retrunBtn',function(){
	console.log('click');
	let idx = $(this).attr('useridx');
	console.log(idx)
	$.ajax({
		method:"POST",
		url:"administrator-mode.do",
		data:{idx:idx}		
	})
	.done(function(data){
		console.log(data);
		$('.administratorBtn').html(data);
	})
})