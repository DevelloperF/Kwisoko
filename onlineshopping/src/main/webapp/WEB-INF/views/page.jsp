<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Création des variables css,js et images avec spring qui seront utilisées ultérieurement -->
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<!-- Création de la variable du contexte /onlineshopping -->
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Online Shopping Website Using Spring MVC and Hibernate">
<meta name="author" content="Niyomukunzi Jésus-Marie Filao">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">


<title>Online Shopping - ${title}</title>

<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">


<!-- Bootstrap  pour le plugin jquery dataTables -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">



<!-- Custom CSS AVEC USAGE DE LA ARIABLE ${css} précedemment créée-->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->

		<div class="content">
			<!-- Loading the home content -->
			<c:if test="${userClickHome == true}">
				<%@include file="home.jsp"%>
			</c:if>


			<!-- Loading this section only user clicks on about -->
			<c:if test="${userClickAbout == true}">
				<%@include file="about.jsp"%>
			</c:if>




			<!-- Loading this section only user clicks on contact -->
			<c:if test="${userClickContact == true}">
				<%@include file="contact.jsp"%>
			</c:if>


			<!-- Loading this section only user clicks on allProducts or categoryproducts -->
			<c:if
				test="${userClickAllProducts == true or userClickCategoryProducts==true}">
				<%@include file="listProducts.jsp"%>
			</c:if>

			<!-- Loading this section only user clicks Show Product  -->
			<c:if test="${userClickShowProduct == true}">
				<%@include file="singleProduct.jsp"%>
			</c:if>

			<!-- Loading this section only user clicks manage Product  -->
			<c:if test="${userClikManageProducts == true}">
				<%@include file="manageProducts.jsp"%>
			</c:if>
			
			<!-- Loading this section only user clicks manage Product  -->
			<c:if test="${userClickShowCart == true}">
				<%@include file="cart.jsp"%>
			</c:if> 

		</div>
		<!-- Footer comes here -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<!-- jQueryValidator -->
		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>


		<!-- DataTablePlugin -->
		<script src="${js}/jquery.dataTables.js"></script>


		<!-- DataTable Bootstrap Script -->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!--  BootBox  -->
		<script src="${js}/bootbox.min.js"></script>

		<!-- 		Self coded javascript  pour rendre les menu actifs-->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>