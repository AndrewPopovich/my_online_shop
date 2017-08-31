<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="home">
                    <a class="navbar-brand" href="${contextRoot}/home">My shop</a>
                </li>
                <li id="about">
                    <a href="${contextRoot}/about">About</a>
                </li>
                <li id="contact">
                    <a href="${contextRoot}/contact">Contact</a>
                </li>
                <li id="listProducts">
                    <a href="${contextRoot}/show/all/products">View Products</a>
                </li>
                <li id="manageProducts">
                    <a href="${contextRoot}/manage/products">Manage Products</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li id="register">
                    <a href="${contextRoot}/register">Sign Up</a>
                </li>
                <li id="login">
                    <a href="${contextRoot}/login">Login</a>
                </li>
                <li class="dropdown">
                    <a href="javascript:void(0)"
                    class="btn btn-default dropdown-toggle"
                    id="dropdownMenu1"
                    data-toggle="dropdown">
                    Full Name
                    <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <li>
                            <a href="${contextRoot}/cart">
                                <span class="glyphicon glyphicon-shopping-cart"></span>
                                <span class="badge">0</span>
                                - &#8372; 0.0
                            </a>
                        </li>

                        <li class="divider" role="separator"></li>

                        <li>
                            <a href="${contextRoot}/logout">Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
