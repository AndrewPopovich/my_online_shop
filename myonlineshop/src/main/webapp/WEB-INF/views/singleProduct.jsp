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

    <%-- Display product image --%>
    <div class="row">

        <div class="col-xs-12 col-sm-4">

            <div class="thumbnail">

                <a href="#bigImg" data-toggle="modal">
                    <img src="${images}/${product.code}.jpg" class="img img-responsive">
                </a>
            </div>
        </div>

        <%-- Display product description --%>
        <div class="col-xs-12 col-sm-8">

            <h3>${product.name}</h3>
            <hr/>

            <p>${product.description}</p>
            <hr/>

            <h4>Price: <strong>&#8372; ${product.unitPrice}</strong></h4>
            <hr/>

            <c:choose>
                <c:when test="${product.quantity < 1}">
                    <h6>Qty. Available: <span style="color:red">Out of Stock!</span></h6>
                </c:when>
                <c:otherwise>
                    <h6>Qty. Available: ${product.quantity}</h6>
                </c:otherwise>
            </c:choose>

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

            <a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>

        </div>

    </div>

    <div class="modal fade" id="bigImg" role="img" tabindex="-1">
        <div class="modal-dialog" role="img">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <img src="${images}/${product.code}.jpg" class="img img-responsive">
                </div>
            </div>
        </div>
    </div>
</div>
