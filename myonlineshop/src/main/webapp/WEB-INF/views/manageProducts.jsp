<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>Product Management</h4>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-md-4" for="name">Enter Product Name</label>
                            <div class="col-md-8">
                                <input type="text" name="name" id="name" placeholder="Product Name"
                                       class="form-control">

                                <em class="help-block">Please enter Product Name</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="brand">Enter Brand Name</label>
                            <div class="col-md-8">
                                <input type="text" name="brand" id="brand" placeholder="Brand Name"
                                       class="form-control">
                                <em class="help-block">Please enter Brand Name</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="description">Product Description</label>
                            <div class="col-md-8">
                                <textarea name="description" id="description" rows="4"
                                          placeholder="Write product description..."
                                          class="form-control"> </textarea>
                                <em class="help-block">Please enter description...</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="unitPrice">Enter Unit Price</label>
                            <div class="col-md-8">
                                <input type="text" name="unitPrice" id="unitPrice" placeholder="Unit price in &#8372"
                                       class="form-control">
                                <em class="help-block">Please enter Unit Price</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="quantity">Enter Quantity</label>
                            <div class="col-md-8">
                                <input type="text" name="quantity" id="quantity" placeholder="Quantity"
                                       class="form-control">
                                <em class="help-block">Please enter Quantitye</em>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-4" for="categoryId">Enter category Id</label>
                            <div class="col-md-8">
                                <select class="form-control" id="categoryId" name="categoryId">
                                    <option value="1">Category One</option>
                                    <option value="2">Category Two</option>
                                    <option value="3">Category Three</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" name="submit" id="submit" value="Submit"
                                       class="btn btn-primary">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
