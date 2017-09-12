<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
	<div class="row" style="margin-top:40px;">
		<div class="col-md-10">
            <div class="text-left">
                <a class="btn btn-success btn-green" href="#reviews-anchor" id="open-review-box">Leave a Review</a>
            </div>

            <div class="row" id="post-review-box" style="display:none;">
                <div class="col-md-12">
                    <sf:form  action="${contextRoot}/comment/add" method="POST" modelAttribute="comment">

                        <sf:textarea class="form-control animated" cols="50" id="new-review" path="description"
                            name="comment" placeholder="Enter your review here..." rows="5"></sf:textarea>
                        <sf:errors path="description" cssClass="help-block" element="em"/>

                        <br/>
                        <div class="input-group">
                              <div class="input-group-addon">http://</div>
                              <sf:input type="text" class="form-control" path="fileReference" id="fileReference"
                              placeholder="Enter your file reference..."/>
                            </div>

                        <div class="text-right">
                            <div class="stars starrr" data-rating="0"></div>
                            <a class="btn btn-danger btn-sm" href="#" id="close-review-box" style="display:none; margin-right: 10px;">
                            <span class="glyphicon glyphicon-remove"></span>Cancel</a>
                            <button class="btn btn-success btn-lg" type="submit">Save</button>
                            <sf:hidden path="date"/>
                            <sf:hidden path="userName"/>
                            <sf:hidden path="productId"/>
                        </div>
                    </sf:form>
                </div>
            </div>

		</div>
	</div>
