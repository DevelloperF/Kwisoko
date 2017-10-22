<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<script>
	window.userRole = '${userModel.role}';
</script>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${contextRoot}/home">Online
				Shopping</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="about"><a href="${contextRoot}/about">About</a></li>

				<li id="contact"><a href="${contextRoot}/contact">Contact</a></li>

				<li id="listProducts"><a
					href="${contextRoot}/show/all/products">View Products</a></li>

				<li id="manageProducts"><a
					href="${contextRoot}/manage/products">Manage Products</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li id="register"><a href="${contextRoot}/register">Sign up</a></li>

				<li id="login"><a href="${contextRoot}/login">Login</a></li>
				 
				 <li class="dropdown" id="userModel">
						  <a class="btn btn-default dropdown-toggle" href="javascript:void(0)" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						    FullName
						    <span class="caret"></span>
						  </a>
						  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
		                       <li id="cart">
			                        <a href="${contextRoot}/cart">
			                        	<span class="glyphicon glyphicon-shopping-cart"></span>&#160;<span class="badge">0</span> - &#8377;0.0
			                        </a>
			                    </li>		     
			                	<li role="separator" class="divider"></li>	                                   
		                     
		                     <li id="logout">
		                        <a href="${contextRoot}/logout">Logout</a>
		                    </li>                    			    	
						  </ul>		
						</li>   
				 
			</ul>

		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>

