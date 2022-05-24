//添加图书到购物车请求
function addToCart(bookId){
	$.ajax({
		url:"addToCart.do",
		dataType:"json",
		async:true,
		data:{"bookId":bookId},
		type:"POST",
		success:function(data){
			$("#cart .num").html(data);
		}
	})
}



