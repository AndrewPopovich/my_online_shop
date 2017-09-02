<div class="container">

<%-- Breadcrumb --%>
    <div class="row">

        <div class="col-xs-12">

            <ol class="breadcrumb">
                <li><a href="${contextRoot}/home">Home</a></li>
                <li><a href="${contextRoot}/show/all/products">All products</a></li>
                <li class="active">${product.name}</li>
            </ol>

        </div>

    </div>

    <div class="card">
        <div class="container-fliud">
            <div class="wrapper row">
                <div class="preview col-md-6">

                    <div class="preview-pic tab-content">
                      <div class="tab-pane active" id="pic-1">
                      <a href="#bigImg" data-toggle="modal">
                          <img src="${images}/${product.code}.jpg" class="bigSingleImg">
                      </a>
                      </div>
                      <div class="tab-pane" id="pic-2"><img src="${images}/${product.code}.jpg" class="bigSingleImg"/></div>
                      <div class="tab-pane" id="pic-3"><img src="${images}/${product.code}.jpg" class="bigSingleImg"/></div>
                      <div class="tab-pane" id="pic-4"><img src="${images}/${product.code}.jpg" class="bigSingleImg"/></div>
                      <div class="tab-pane" id="pic-5"><img src="${images}/${product.code}.jpg" class="bigSingleImg"/></div>
                    </div>
                    <ul class="preview-thumbnail nav nav-tabs">
                      <li class="active"><a data-target="#pic-1" data-toggle="tab"><img src="${images}/${product.code}.jpg" class="adminDataTableImg"/></a></li>
                      <li><a data-target="#pic-2" data-toggle="tab"><img src="${images}/${product.code}.jpg" class="adminDataTableImg"/></a></li>
                      <li><a data-target="#pic-3" data-toggle="tab"><img src="${images}/${product.code}.jpg" class="adminDataTableImg"/></a></li>
                      <li><a data-target="#pic-4" data-toggle="tab"><img src="${images}/${product.code}.jpg" class="adminDataTableImg"/></a></li>
                      <li><a data-target="#pic-5" data-toggle="tab"><img src="${images}/${product.code}.jpg" class="adminDataTableImg"/></a></li>
                    </ul>

                </div>
                <div class="details col-md-6">
                    <h3 class="product-title">${product.name}</h3>
                    <div class="rating">
                        <span class="review-no">${product.views} reviews</span>
                    </div>
                    <h4></h4>

                    <h4 class="price">Current price: <span>&#8372; ${product.unitPrice}</span></h4>
                    <h2></h2>
                    <div class="action">
                        <c:choose>
                            <c:when test="${product.quantity < 1}">
                                <h6>Qty. Available: <span style="color:#ff667d">Out of Stock!</span></h6>
                            </c:when>
                            <c:otherwise>
                                <h6>Qty. Available: ${product.quantity}</h6>
                            </c:otherwise>
                        </c:choose>

                        <security:authorize access="hasAuthority('USER')">
                        <c:choose>
                            <c:when test="${product.quantity < 1}">
                                <a href="javascript:void(0)" class="btn btn-success disable"><strike><span
                                        class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</strike></a>
                            </c:when>
                            <c:otherwise>
                                <a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success"><span
                                        class="glyphicon glyphicon-shopping-cart"></span>Add to Cart</a>
                            </c:otherwise>
                        </c:choose>
                        </security:authorize>

                        <security:authorize access="hasAuthority('ADMIN')">
                            <a href="${contextRoot}/manage/${product.id}/product" class="btn btn-warning"><span
                                                        class="glyphicon glyphicon-pencil"></span>Edit</a>
                        </security:authorize>

                        <a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>
                    </div>
                    <h2></h2>
                    <p class="product-description">${product.description}</p>
                </div>
            </div>
        </div>
    </div>

<center>
    <div class="modal fade" id="bigImg" role="img" tabindex="-1">


        <div class="row carousel-holder">

                            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img class="slide-image modalImgSize" src="${images}/${product.code}.jpg" alt="" data-dismiss="modal">
                                    </div>
                                    <div class="item">
                                        <img class="slide-image modalImgSize" src="${images}/${product.code}.jpg" alt="" data-dismiss="modal">
                                    </div>
                                    <div class="item">
                                        <img class="slide-image modalImgSize" src="${images}/${product.code}.jpg" alt="" data-dismiss="modal">
                                    </div>
                                    <div class="item">
                                        <img class="slide-image modalImgSize" src="${images}/${product.code}.jpg" alt="" data-dismiss="modal">
                                    </div>
                                    <div class="item">
                                        <img class="slide-image modalImgSize" src="${images}/${product.code}.jpg" alt="" data-dismiss="modal">
                                    </div>
                                </div>
                                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                </a>
                            </div>
                    </div>
    </div>
    </center>
</div>
