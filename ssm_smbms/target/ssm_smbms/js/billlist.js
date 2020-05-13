$(function(){
	//通过jqery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	$(".viewBill").on("click",function(){
		//将被绑定的元素<a>转换成jquery对象，可以使用jquery的方法
		var obj=$(this);
		window.location.href=path+"/bill.do?method=view & billid="+obj.attr("billid");
	});
	$(".modifyBill").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/bill.do?method=modify & billid="+obj.attr("billid");
	});
	$(".deleteBill").on("click",function(){
		var obj=$(this);
		
		if(confirm("你确定要删除账单【"+obj.attr("productName")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/bill.do",
				data:{method:"delbill",uid:obj.attr("billid")},
				dataType:"json",
				success:function(data){
					//删除成功：移除删除行
					if(data.delResult=="true"){
						alert("删除成功！");
						obj.parents("tr").remove();
					}else if(data.delResult=="false"){
						alert("对不起，删除账单"+obj.attr("username")+"失败");
					}else if(data.delResult=="notexist"){
						alert("对不起，账单"+obj.attr("username")+"不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败！");
				}
			});
		}
	})
});