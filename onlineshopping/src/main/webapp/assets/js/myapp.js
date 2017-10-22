$(function() {
	// Solving Menu Problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');

		break;
	case 'Contact Us':
		$('#contact').addClass('active');

		break;

	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	default:
		if (menu == "Home") {
			break;
		}

		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if ((token != undefined && header != undefined)
			&& (token.length > 0 && header.length > 0)) {
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}

	// Code for jquery dataTable
	// Create a dataset

	var $table = $('#productListTable');

	// execute the below code only where we have this table
	if ($table.length) {

		var jsonUrl = '';

		// That means that the user asked all products
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			// That means that the user asked for one cathegory we have
			// window.categoryId dinamic
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								// Permet de personnaliser le look de nos
								// données en y
								// ajoutant
								// les élements
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'quantity',

								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red;">Out of Stock</span>';
									} else {
										return data;
									}
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									} else {

										str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}

									return str;

								}
							}

					]
				});
	}

	// Dismissing the alert after 3 seconds
	var $alert = $('.alert');

	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
	}

	// Data table for admin

	var $adminProductsTable = $('#adminProductsTable');

	// execute the below code only where we have this table
	if ($adminProductsTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable
				.DataTable({

					lengthMenu : [ [ 10, 30, 50 - 1 ],
							[ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'quantity',

								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red;">Out of Stock</span>';
									} else {
										return data;
									}
								}
							},
							{
								data : 'unitPrice',
								// Permet de personnaliser le look de nos
								// données en y
								// ajoutant
								// les élements
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += ' <label class="switch">';
									if (data) {
										str += ' <input type="checkbox" checked="checked"  value="'
												+ row.id + '" />';

									} else {
										str += ' <input type="checkbox" value="'
												+ row.id + '" />';

									}
									str += '<div class="slider"></div></label>'
									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href=" ' + window.contextRoot
											+ '/manage/' + data + '/product"';
									str += 'class="btn btn-warning"> <span class="glyphicon glyphicon-pencil"></span></a>';

									return str;
								}
							}

					],
					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {
											// Choix du checkbox
											var checkbox = $(this);
											// Verification de son état. True si
											// séléctionné. Faux
											// au
											// contraire
											var checked = checkbox
													.prop('checked');
											// Quand ça devient rue ou false
											// dMSG=Dialogue message

											var dMsg = (checked) ? 'You want to active the product?'
													: 'You want to deactivate the product?';

											// Will give the product id
											var value = checkbox.prop('value');

											// Boot box permet d'afficher les
											// messages facilement le
											// callback permet de savoir si
											// l'utilisateur a clické
											// sur oui
											// ou non

											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation/Deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																// Utilisation
																// de l'ajax
																// pour
																// désactiver le
																// produit
																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';
																// Le bootbox
																// pour afficher
																// les boîtes de
																// dialogue

																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'Information',
																								message : data
																							});
																				});

															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													})
										});
					}
				});
	}

	// -------------Pour le jquery validator

	// Valdiation code for category
	var $categoryForm = $('#categoryForm');
	if ($categoryForm.length) {
		$categoryForm
				.validate({
					rules : {
						name : {
							required : true,
							minlength : 2
						},
						description : {
							required : true

						}
					},
					messages : {

						name : {
							required : 'Please add the category name!',
							minlength : 'The category name should not be less than 2 characters'
						},
						description : {
							required : 'Please add a description for this category!'
						}
					},
					errorElement : 'em',
					errorPlacement : function(error, element) {
						// Add the class of help-block
						error.addClass('help-block');
						// Add the error element after the input element
						error.insertAfter(element);
					}

				});
	}

	// ---------------------------------------

	// Valdiation code for the login form
	var $loginForm = $('#loginForm');
	if ($loginForm.length) {
		$loginForm.validate({
			rules : {
				username : {
					required : true,
					email : true
				},
				password : {
					required : true

				}
			},
			messages : {

				username : {
					required : 'Please enter the username!',
					email : 'Please enter valid email address!'
				},
				description : {
					required : 'Please enter the password'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				// Add the class of help-block
				error.addClass('help-block');
				// Add the error element after the input element
				error.insertAfter(element);
			}

		});
	}

	// ---------------------------------------

});