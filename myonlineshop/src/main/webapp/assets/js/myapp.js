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
        case 'Manage Products':
            $('#manageProducts').addClass('active');
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

        $table.DataTable({
            lengthMenu: [[5, 10, 25, -1], ['5 records', '10 records', '25 records', 'all']],
            pageLength: 10,
            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },
            columns: [
                {
                    data: 'code',
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="dataTableImg"/>'
                    }
                },
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
                    data: 'quantity',
                    mRender: function (data, type, row) {
                        if (data < 1) {
                            return '<span style="color:red">Out of Stock!</span>'
                        }
                        return data;
                    }
                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160 &#160 &#160';

                        if (row.quantity < 1) {
                            str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                        } else {
                            str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                        }
                        return str;
                    }
                }
            ]
        });
    }

    var $alert = $('.alert');

    if ($alert.length) {
        setTimeout(function () {
            $alert.fadeOut("slow");
        }, 3000)
    }

    $('.onoffswitch-label input[type="checkbox"]').on('change', function () {
        var checkbox = $(this);
        var checked = checkbox.prop('checked');
        var dMsg = (checked) ? 'You want to activate the product?' :
            'You want to deactivate the product?';

        var value = checked.prop('value');

        bootbox.confirm({
            size: 'medium',
            title: 'Product activation & deactivation',
            message: dMsg,
            callback: function (confirmed) {
                if (confirmed) {
                    console.log(value);
                    bootbox.alert({
                        size: 'medium',
                        title: 'Information',
                        message: 'You are going to perform operation on product ' + value
                    });
                } else {
                    checkbox.prop('checked', !checked);
                }
            }
        })
    });

    var $adminProductsTable = $('#adminProductsTable');

    if ($adminProductsTable.length) {
        var jsonUrl = window.contextRoot + '/json/data/admin/all/products';


        $adminProductsTable.DataTable({
            lengthMenu: [[10, 25, 50, -1], ['10 records', '25 records', '50 records', 'all']],
            pageLength: 25,
            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },
            columns: [
                {
                    data: 'id'
                },
                {
                    data: 'code',
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="adminDataTableImg"/>'
                    }
                },
                {
                    data: 'name'
                },
                {
                    data: 'brand'
                },
                {
                    data: 'quantity',
                    mRender: function (data, type, row) {
                        if (data < 1) {
                            return '<span style="color:red">Out of Stock!</span>'
                        }
                        return data;
                    }
                },
                {
                    data: 'unitPrice',
                    mRender: function (data, type, row) {
                        return '&#8372; ' + data
                    }
                },
                {
                    data: 'active',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';

                        str += '<div class="onoffswitch">\n';
                        if (data) {
                            str += '<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked value="' + row.id + '">\n';
                        } else {
                            str += '<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" value="' + row.id + '">\n';
                        }
                        str += '<label class="onoffswitch-label" for="myonoffswitch">\n';
                        str += '<span class="onoffswitch-inner"></span>\n';
                        str += '<span class="onoffswitch-switch"></span>\n';
                        str += '</label>\n';
                        str += '</div>';

                        return str;
                    }

                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += 'a <href="${contextRoot}/manage/' + data + '/product" class="btn btn-warning">' +
                            '<span class=""glyphicon glyphicon-pencil></span>' +
                            '</a>';
                        return str;
                    }
                }
            ]
        });
    }
});