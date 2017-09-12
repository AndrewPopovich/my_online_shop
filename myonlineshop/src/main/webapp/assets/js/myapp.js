$(function () {

    switch (menu) {
        case 'About':
            $('#about').addClass('active');
            break;
        case 'Contact':
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
                        str += '<img src="' + window.contextRoot + '/resources/images/' + data + '/0.jpg" class="dataTableImg"/>';
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
                        return '<img src="' + window.contextRoot + '/resources/images/' + data + '/0.jpg" class="adminDataTableImg"/>'
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

    (function(e){var t,o={className:"autosizejs",append:"",callback:!1,resizeDelay:10},i='<textarea tabindex="-1" style="position:absolute; top:-999px; left:0; right:auto; bottom:auto; border:0; padding: 0; -moz-box-sizing:content-box; -webkit-box-sizing:content-box; box-sizing:content-box; word-wrap:break-word; height:0 !important; min-height:0 !important; overflow:hidden; transition:none; -webkit-transition:none; -moz-transition:none;"/>',n=["fontFamily","fontSize","fontWeight","fontStyle","letterSpacing","textTransform","wordSpacing","textIndent"],s=e(i).data("autosize",!0)[0];s.style.lineHeight="99px","99px"===e(s).css("lineHeight")&&n.push("lineHeight"),s.style.lineHeight="",e.fn.autosize=function(i){return this.length?(i=e.extend({},o,i||{}),s.parentNode!==document.body&&e(document.body).append(s),this.each(function(){function o(){var t,o;"getComputedStyle"in window?(t=window.getComputedStyle(u,null),o=u.getBoundingClientRect().width,e.each(["paddingLeft","paddingRight","borderLeftWidth","borderRightWidth"],function(e,i){o-=parseInt(t[i],10)}),s.style.width=o+"px"):s.style.width=Math.max(p.width(),0)+"px"}function a(){var a={};if(t=u,s.className=i.className,d=parseInt(p.css("maxHeight"),10),e.each(n,function(e,t){a[t]=p.css(t)}),e(s).css(a),o(),window.chrome){var r=u.style.width;u.style.width="0px",u.offsetWidth,u.style.width=r}}function r(){var e,n;t!==u?a():o(),s.value=u.value+i.append,s.style.overflowY=u.style.overflowY,n=parseInt(u.style.height,10),s.scrollTop=0,s.scrollTop=9e4,e=s.scrollTop,d&&e>d?(u.style.overflowY="scroll",e=d):(u.style.overflowY="hidden",c>e&&(e=c)),e+=w,n!==e&&(u.style.height=e+"px",f&&i.callback.call(u,u))}function l(){clearTimeout(h),h=setTimeout(function(){var e=p.width();e!==g&&(g=e,r())},parseInt(i.resizeDelay,10))}var d,c,h,u=this,p=e(u),w=0,f=e.isFunction(i.callback),z={height:u.style.height,overflow:u.style.overflow,overflowY:u.style.overflowY,wordWrap:u.style.wordWrap,resize:u.style.resize},g=p.width();p.data("autosize")||(p.data("autosize",!0),("border-box"===p.css("box-sizing")||"border-box"===p.css("-moz-box-sizing")||"border-box"===p.css("-webkit-box-sizing"))&&(w=p.outerHeight()-p.height()),c=Math.max(parseInt(p.css("minHeight"),10)-w||0,p.height()),p.css({overflow:"hidden",overflowY:"hidden",wordWrap:"break-word",resize:"none"===p.css("resize")||"vertical"===p.css("resize")?"none":"horizontal"}),"onpropertychange"in u?"oninput"in u?p.on("input.autosize keyup.autosize",r):p.on("propertychange.autosize",function(){"value"===event.propertyName&&r()}):p.on("input.autosize",r),i.resizeDelay!==!1&&e(window).on("resize.autosize",l),p.on("autosize.resize",r),p.on("autosize.resizeIncludeStyle",function(){t=null,r()}),p.on("autosize.destroy",function(){t=null,clearTimeout(h),e(window).off("resize",l),p.off("autosize").off(".autosize").css(z).removeData("autosize")}),r())})):this}})(window.jQuery||window.$);

    var __slice=[].slice;(function(e,t){var n;n=function(){function t(t,n){var r,i,s,o=this;this.options=e.extend({},this.defaults,n);this.$el=t;s=this.defaults;for(r in s){i=s[r];if(this.$el.data(r)!=null){this.options[r]=this.$el.data(r)}}this.createStars();this.syncRating();this.$el.on("mouseover.starrr","span",function(e){return o.syncRating(o.$el.find("span").index(e.currentTarget)+1)});this.$el.on("mouseout.starrr",function(){return o.syncRating()});this.$el.on("click.starrr","span",function(e){return o.setRating(o.$el.find("span").index(e.currentTarget)+1)});this.$el.on("starrr:change",this.options.change)}t.prototype.defaults={rating:void 0,numStars:5,change:function(e,t){}};t.prototype.createStars=function(){var e,t,n;n=[];for(e=1,t=this.options.numStars;1<=t?e<=t:e>=t;1<=t?e++:e--){n.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"))}return n};t.prototype.setRating=function(e){if(this.options.rating===e){e=void 0}this.options.rating=e;this.syncRating();return this.$el.trigger("starrr:change",e)};t.prototype.syncRating=function(e){var t,n,r,i;e||(e=this.options.rating);if(e){for(t=n=0,i=e-1;0<=i?n<=i:n>=i;t=0<=i?++n:--n){this.$el.find("span").eq(t).removeClass("glyphicon-star-empty").addClass("glyphicon-star")}}if(e&&e<5){for(t=r=e;e<=4?r<=4:r>=4;t=e<=4?++r:--r){this.$el.find("span").eq(t).removeClass("glyphicon-star").addClass("glyphicon-star-empty")}}if(!e){return this.$el.find("span").removeClass("glyphicon-star").addClass("glyphicon-star-empty")}};return t}();return e.fn.extend({starrr:function(){var t,r;r=arguments[0],t=2<=arguments.length?__slice.call(arguments,1):[];return this.each(function(){var i;i=e(this).data("star-rating");if(!i){e(this).data("star-rating",i=new n(e(this),r))}if(typeof r==="string"){return i[r].apply(i,t)}})}})})(window.jQuery,window);$(function(){return $(".starrr").starrr()})

    $(function(){

      $('#new-review').autosize({append: "\n"});

      var reviewBox = $('#post-review-box');
      var newReview = $('#new-review');
      var openReviewBtn = $('#open-review-box');
      var closeReviewBtn = $('#close-review-box');
      var ratingsField = $('#ratings-hidden');

      openReviewBtn.click(function(e)
      {
        reviewBox.slideDown(400, function()
          {
            $('#new-review').trigger('autosize.resize');
            newReview.focus();
          });
        openReviewBtn.fadeOut(100);
        closeReviewBtn.show();
      });

      closeReviewBtn.click(function(e)
      {
        e.preventDefault();
        reviewBox.slideUp(300, function()
          {
            newReview.focus();
            openReviewBtn.fadeIn(200);
          });
        closeReviewBtn.hide();

      });

      $('.starrr').on('starrr:change', function(e, value){
        ratingsField.val(value);
      });
    });

    var $commentsTable = $('#commentsTable')

        if ($commentsTable.length) {
            var jsonUrl = window.contextRoot + '/json/data/comments/' + window.productId + '/product';

                $commentsTable.DataTable({
                    ajax: {
                        url: jsonUrl,
                        dataSrc: ''
                    },
                    columns: [
                        {
                            data: 'description'
                        }
                    ]

                });
        }
});