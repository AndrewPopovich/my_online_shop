<%@include file="../shared/flows-header.jsp" %>

<div class="row">
    <div class="col-sm-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h4>Personal Details</h4>
            </div>

            <div class="panel-body">
                <div class="text-center">
                    <h4>${registerModel.user.firstName} ${registerModel.user.secondName}</h4>
                    <h5>Email: ${registerModel.user.email}</h5>
                    <h5>Contact Number: ${registerModel.user.contactNumber}</h5>
                    <h5>Role: ${registerModel.user.role}</h5>
                </div>
            </div>

            <div class="panel-footer">
                <a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
            </div>
        </div>
    </div>

    <div class="col-sm-6">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h4>Billing Address</h4>
            </div>

            <div class="panel-body">
                <div class="panel-body">
                    <div class="text-center">
                        <h5>Address Line One: ${registerModel.billing.addressLineOne}</h5>
                        <h5>Address Line Two: ${registerModel.billing.addressLineTwo}</h5>
                        <h5>City: ${registerModel.billing.city}</h5>
                        <h5>Country: ${registerModel.billing.country}</h5>
                        <h5>State: ${registerModel.billing.state}</h5>
                        <h5>Postal code: ${registerModel.billing.postalCode}</h5>

                    </div>
                </div>
            </div>

            <div class="panel-footer">
                <a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-sm-4 col-sm-offset-4">
        <div class="text-center">
            <a href="${flowExecutionUrl}&_eventId_success" class="btn btn-primary">Confirm</a>
        </div>
    </div>
</div>

<%@include file="../shared/flows-footer.jsp" %>
