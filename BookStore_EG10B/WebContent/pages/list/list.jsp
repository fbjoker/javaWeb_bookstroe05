<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 引入base页面 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	/* 给查询按钮绑定单击事件，点击提交请求查询价格区间的分页数据 */
	$(function(){
		$("#queryBtn").click(function(){
			//获取最低和最高价格
			var min = $("input[name='min']").val();
			var max = $("input[name='max']").val();
			//发送请求前检查用户输入的参数必须满足要求[必须是数字 ， 必须是正确的价格区间]
			//正则表达式
			//js提供了一个方法： boolean  isNaN(str);   is not a number , 如果传入的str不是数字返回true ， 如果是数字返回false
			if(isNaN(min)){
				//价格区间错误时，回显上一次正确的价格值
				var defaultMin = $("input[name='min']")[0].defaultValue;
				alert($("input[name='min']")[0]);
				$("input[name='min']").val(defaultMin);//将上次的正确值设置回表单项内
				alert("请输入正确的价格!!");
				//取消a标签的默认行为
				return false;
			}else if(isNaN(max)){
				var defaultMax = $("input[name='max']")[0].defaultValue;
				$("input[name='max']").val(defaultMax);//将上次的正确值设置回表单项内
				alert("请输入正确的价格!!");
				//取消a标签的默认行为
				return false;
			}else if((min-max)>0){//默认当做字符串比较
				//检查价格区间
				alert("请输入正确的价格区间！！");
				return false;
			}
			//发送请求
			window.location = "BookClientServlet?type=findPageByPrice&min="+min+"&max="+max;
			
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				价格：<input type="text" name="min" value="${param.min }"> 元 - <input type="text" name="max" value="${param.max }"> 元 <button id="queryBtn">查询</button>
			</div>
			
			<c:choose>
			<c:when test="${empty cart.map }">
			<h2>购物车为空!!</h2>
			</c:when>
			<c:otherwise>
			<div style="text-align: center">
				<span>您的购物车中有${sessionScope.cart.totalCount }件商品</span>
				<div>
					您刚刚将<span style="color: red">${sessionScope.title }</span>加入到了购物车中
				</div>
			</div>
			</c:otherwise>
			</c:choose>
			
			<!-- 判断page对象的图书集合是否为空 -->
			<c:choose>
				<c:when test="${empty page.data }">
					<h2 style="color:red; text-align: center;">你来晚了一步，图书卖空了....</h2>
				</c:when>
				<c:otherwise>
					<!-- 遍历显示分页图书集合 -->
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="static/img/default.jpg" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span>
									<span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">￥${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<a href="CartServlet?type=addBook&bookId=${book.id }">加入购物车</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			
		</div>
	</div>
		
	<!-- 引入分页导航栏 -->	
	<%@ include file="/WEB-INF/include/nav.jsp" %>
		
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>