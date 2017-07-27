$(function () {

    switch (menu) {
        case 'About as':
            $('#about').addClass('active');
            break;
        case 'Our contact':
            $('#contact').addClass('active');
            break;
        case 'All products':
            $('#listProducts').addClass('active');
            break;
        default:
            if (menu == "Home") break;
            $('#listProducts').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }

    var $table = $('#productListTable');

    if ($table.length) {
        var jsonUrl = '';

        if (window.categoryId == '') {
            jsonUrl = window.contextRoot + '/json/data/all/products';
        } else {
            jsonUrl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';
        }
    }

    $table.DataTable({
        lengthMenu: [[3, 5, 10, -1], ['3 records', '5 records', '10 records', 'all']],
        pageLength: 5,
        ajax: {
            url: jsonUrl,
            dataSrc: ''
        },
        columns: [
            {
                data: 'name'
            },
            {
                data: 'brand'
            },
            {
                data: 'unitPrice',
                mRender: function (data, type, row) {
                    return '&#8372; ' + data
                }
            },
            {
                data: 'quantity'
            },
            {
                data: 'id',
                bSortable: false,
                mRender: function (data, type, row) {
                    var str = '';
                    str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160 &#160 &#160';
                    str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

                    return str;
                }
            }
        ]
    })
});