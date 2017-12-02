<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<link rel="stylesheet"
			href="/E02/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet"
			href="/E02/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />
		<script src="/E02/bootstrap-3.3.7-dist/js/bootstrap.min.js">
</script>
		<link rel="stylesheet" href="/E02/css/index.css" />
		<title>首页</title>
	</head>
	<body>
		<div style="width:100;hieght:100;">
			<div class="table-responsive">
				<form action="">
					<div style="padding: 25% 100px">
						<div class="input-group input-group-sm">
							<span class="input-group-addon">用户名</span>
							<input type="text" class="form-control" name="username"
								placeholder="请输入名称" width="50" />
						</div>
						<div class="input-group input-group-sm">
							<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span>
							<input type="password" class="form-control" id="inputPassword"
								placeholder="请输入密码" width="50" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<div style="padding: 100px 100px 10px;">
			<form class="bs-example bs-example-form" role="form">
				<div class="input-group input-group-lg">
					<span class="input-group-addon">@</span>
					<input type="text" class="form-control" placeholder="Twitterhandle">
				</div>
				<br>

				<div class="input-group">
					<span class="input-group-addon">@</span>
					<input type="text" class="form-control" placeholder="Twitterhandle">
				</div>
				<br>

				<div class="input-group input-group-sm">
					<span class="input-group-addon">@</span>
					<input type="text" class="form-control" placeholder="Twitterhandle">
				</div>
			</form>
		</div>
	</body>
</html>
