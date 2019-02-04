<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>Budget App Output</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>

<body>

	<div id="ajaxLoader" style="display: none" class="ajaxLoaderClass">
		<i class="fa fa-spinner fa-spin" id="loaderSpinner"
			style="font-size: 240px; color: #28B9B5"></i>
	</div>

	<div class="container">
		<canvas id="barChart"></canvas>
		<canvas id="pieChart"></canvas>
	</div>
	<script src="${pageContext.request.contextPath}/js/output.js"></script>
</body>
</html>