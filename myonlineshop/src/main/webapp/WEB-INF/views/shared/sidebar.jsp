<p class="lead">Shop Name</p>
<div class="list-group">

    <c:forEach items="${categories}" var="categore">
        <a href="#" class="list-group-item">${categore.name}</a>
    </c:forEach>
</div>
