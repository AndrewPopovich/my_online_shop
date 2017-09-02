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
        case 'User Cart':
            $('#userCart').addClass('active');
            break;
        default:
            if (menu == "Home") break;
            $('#listProducts').addClass('active');
            $('#a_' + menu).addClass('active');
            break;
    }

    var token = $('meta[name="_csrf"]').attr('content');
    var header = $('meta[name="_csrf_header"]').attr('content');

    if(token.length > 0 && header.length > 0) {
        $(document).ajaxSend(function(e, xhr, options){
            xhr.setRequestHeader(header, token);
        });

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
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += '<a href="' + window.contextRoot + '/show/' + row.id + '/product">';
                        str += '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="dataTableImg"/>';
                        str += '</a>';
                        return str;
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
                            return '<span style="color:#ff667d">Out of Stock!</span>'
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

                        if (userRole == 'ADMIN') {
                            str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
                        } else {
                            if (row.quantity < 1) {
                                str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                            } else {
                                str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
                            }
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
                    bSortable: false,
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
                            return '<span style="color:#ff667d">Out of Stock!</span>'
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


                        if (data) {
                            str += '<div class="onoffswitch">\n';
                            str += '<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch' + row.id + '" value="' + row.id + '" checked>\n';
                            str += '<label class="onoffswitch-label" for="myonoffswitch' + row.id + '">\n';
                            str += '<span class="onoffswitch-inner"></span>\n';
                            str += '<span class="onoffswitch-switch"></span>\n';
                            str += '</label>\n';
                            str += '</div>';
                        } else {
                            str += '<div class="onoffswitch">\n';
                            str += '<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch' + row.id + '" value="' + row.id + '">\n';
                            str += '<label class="onoffswitch-label" for="myonoffswitch' + row.id + '">\n';
                            str += '<span class="onoffswitch-inner"></span>\n';
                            str += '<span class="onoffswitch-switch"></span>\n';
                            str += '</label>\n';
                            str += '</div>';
                        }


                        return str;
                    }

                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += '<a href="' + window.contextRoot + '/manage/' + data + '/product" class="btn btn-warning">' +
                            '<span class="glyphicon glyphicon-pencil"></span>' +
                            '</a>';
                        return str;
                    }
                }
            ],

            initComplete: function () {
                var api = this.api();
                api.$('.onoffswitch input[type="checkbox"]').on('change', function () {
                    var checkbox = $(this);
                    var checked = checkbox.prop('checked');
                    var dMsg = (checked) ? 'You want to activate the product?' :
                        'You want to deactivate the product?';

                    var value = checkbox.prop('value');

                    bootbox.confirm({
                        size: 'medium',
                        title: 'Product activation & deactivation',
                        message: dMsg,
                        callback: function (confirmed) {
                            if (confirmed) {
                                console.log(value);

                                var activationUrl = window.contextRoot + '/manage/products/' + value + '/activation';

                                $.post(activationUrl, function (data) {
                                    bootbox.alert({
                                        size: 'medium',
                                        title: 'Information',
                                        message: data
                                    });
                                });

                            } else {
                                checkbox.prop('checked', !checked);
                            }
                        }
                    })
                });
            }
        });
    }

    var $categoryForm = $('#categoryForm');

    if ($categoryForm.length) {
        $categoryForm.validate({
            rules: {
                name: {
                    required: true,
                    minlength: 3
                },
                description: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: 'Please add the category name!',
                    minlength: 'The category name should not be less 3 characters!'
                },
                description: {
                    required: 'Please add the category description!'
                }
            },
            errorElement: 'em',
            errorPlacement: function (error, element) {
                error.addClass('help-block');
                error.insertAfter(element);
            }
        });
    }

    var $loginForm = $('#loginForm');

    if ($loginForm.length) {
            $loginForm.validate({
                rules: {
                    username: {
                        required: true,
                        email: true
                    },
                    password: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: 'Please enter the email!',
                        email: 'Please enter valid email address!'
                    },
                    password: {
                        required: 'Please enter the password!'
                    }
                },
                errorElement: 'em',
                errorPlacement: function (error, element) {
                    error.addClass('help-block');
                    error.insertAfter(element);
                }
            });
    }

    $('button[name="refreshCart"]').click(function(){
    var cartLineId = $(this).attr('value');
    var countElement = $('#count_' + cartLineId);

    var originalCount = countElement.attr('value');
    var currentCount = countElement.val();

    if(currentCount !== originalCount) {
        if (currentCount < 1 || currentCount > 5) {
            countElement.val(originalCount);
            bootbox.alert({
                size: 'medium',
                title: 'Error',
                message: 'Product count should be minimun 1 and maximum 5!'
            });
        } else {
            var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currentCount;

            window.location.href = updateUrl;
        }
    }

    });
});