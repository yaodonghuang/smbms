$(function(){
	$(".viewProvider").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/provider.do?method=view&proid="+obj.attr("proid");
	});
	$(".modifyProvider").on("click",function(){
		var obj=$(this);
		window.location.href=path+"/provider.do?method=modify&proid="+obj.attr("proid");
	});
	$(".deleteProvider").on("click",function(){
		var obj=$(this);
		
		if(confirm("你确定要删除供应商【"+obj.attr("proname")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/provider.do",
				data:{method:"delprovider",proid:obj.attr("proid")},
				dataType:"json",
				success:function(data){
					//删除成功：移除删除行
					if(data.delResult=="true"){
						alert("删除成功！");
						obj.parents("tr").remove();
					}else if(data.delResult=="false"){
						alert("对不起，删除供应商"+obj.attr("proname")+"失败");
					}else if(data.delResult=="notexist"){
						alert("对不起，供应商"+obj.attr("proname")+"不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败！");
				}
			});
		}
	})
});