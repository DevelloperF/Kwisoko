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
	default:
		if (menu == "Home") {
			break;
		}

		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
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
								data : 'quantity'
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
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160;';
									str += '<a href="'
											+ window.contextRoot
											+ '/cart/add/'
											+ data
											+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									return str;
								}
							}

					]
				});
	}
});