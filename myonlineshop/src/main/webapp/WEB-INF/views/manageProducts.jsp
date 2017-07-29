<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Product Management</h4>
                </div>
                <div class="panel-body">

                    <sf:form class="form-horizontal" modelAttribute="product"
                    action="${contextRoot}/manage/products" method="POST">
                        <div class="form-group">
                            <label class="control-label col-md-4" for="name">Enter Product Name</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="name" id="name" placeholder="Product Name"
                                          class="form-control"/>
                                <em class="help-block">Please enter Product Name</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="brand">Enter Brand Name</label>
                            <div class="col-md-8">
                                <sf:input type="text" path="brand" id="brand" placeholder="Brand Name"
                                          class="form-control"/>
                                <em class="help-block">Please enter Brand Name</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="description">Product Description</label>
                            <div class="col-md-8">
                                <sf:textarea path="description" id="description" rows="4"
                                             placeholder="Write product description..."
                                             class="form-control"/>
                                <em class="help-block">Please enter description...</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="unitPrice">Enter Unit Price</label>
                            <div class="col-md-8">
                                <sf:input type="number" path="unitPrice" id="unitPrice"
                                          placeholder="Unit price in &#8372"
                                          class="form-control"/>
                                <em class="help-block">Please enter Unit Price</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="quantity">Enter Quantity</label>
                            <div class="col-md-8">
                                <sf:input type="number" path="quantity" id="quantity" placeholder="Quantity"
                                          class="form-control"/>
                                <em class="help-block">Please enter Quantity</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="categoryId">Enter category Id</label>
                            <div class="col-md-8">
                                <sf:select class="form-control" id="categoryId" path="categoryId"
                                           items="${categories}"
                                           itemLabel="name"
                                           itemValue="id"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" name="submit" id="submit" value="Submit"
                                       class="btn btn-primary">
                                <sf:hidden path="id"/>
                                <sf:hidden path="code"/>
                                <sf:hidden path="supplierId"/>
                                <sf:hidden path="active"/>
                                <sf:hidden path="purchases"/>
                                <sf:hidden path="views"/>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
</div>
