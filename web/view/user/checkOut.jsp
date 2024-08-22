<%-- 
    Document   : checkOut
    Created on : Jul 1, 2024, 5:57:14 PM
    Author     : PMQUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">

    <!-- Mirrored from htmldemo.net/koparion/koparion/checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:39 GMT -->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Koparion – Book Shop HTML5 Template</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">

        <!-- all css here -->
        <!-- bootstrap v3.3.6 css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <!-- animate css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
        <!-- meanmenu css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/meanmenu.min.css">
        <!-- owl.carousel css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css">
        <!-- font-awesome css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
        <!-- flexslider.css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexslider.css">
        <!-- chosen.min.css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chosen.min.css">
        <!-- font-awesome css -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"
              integrity="sha512-+L4yy6FRcDGbXJ9mPG8MT/3UCDzwR9gPeyFNMCtInsol++5m3bk2bXWKdZjvybmohrAsn3Ua5x8gfLnbE1YkOg=="
              crossorigin="anonymous"
              referrerpolicy="no-referrer" />
        <!-- style css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <!-- responsive css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    </head>
    <body class="checkout">
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <!-- Add your site or application content here -->
        <!-- header-area-start -->
        <header>
            <!-- header-top-area-start -->
            <jsp:include page="../common/homepage/header-top-area.jsp"></jsp:include>
                <!-- header-top-area-end -->
                <!-- header-mid-area-start -->
            <jsp:include page="../common/homepage/header-mid-area.jsp"></jsp:include>
                <!-- header-mid-area-end -->
                <!-- main-menu-area-start -->
            <jsp:include page="../common/homepage/main-menu-area.jsp"></jsp:include>
                <!-- main-menu-area-end -->
                <!-- mobile-menu-area-start -->
            <jsp:include page="../common/homepage/mobile-menu-area.jsp"></jsp:include>
                <!-- mobile-menu-area-end -->
            </header>
            <!-- header-area-end -->
            <!-- breadcrumbs-area-start -->
            <div class="breadcrumbs-area mb-70">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="breadcrumbs-menu">
                                <ul>
                                    <li><a href="#">Home</a></li>
                                    <li><a href="#" class="active">checkout</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- breadcrumbs-area-end -->
            <!-- entry-header-area-start -->
            <div class="entry-header-area">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="entry-header-title">
                                <h2>Checkout</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- entry-header-area-end -->

            <!-- checkout-area-start -->
            <div class="checkout-area mb-70">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <form action="#">
                                <div class="row">

                                </div>
                                <div class="col-lg-6 col-md-12 col-12">
                                    <div class="your-order">
                                        <h3>${accFounded.username} order</h3>
                                    <div class="your-order-table table-responsive">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th class="product-name">Product</th>
                                                    <th class="product-total">Total</th>
                                                </tr>							
                                            </thead>
                                            <c:forEach items="${cart.listOrderItems}" var="od">
                                                <c:forEach items="${vectorProduct}" var="product">
                                                    <c:if test="${product.id == od.getProduct_id()}">
                                                        <c:set var="p" value="${product}"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                                <tbody>
                                                    <tr class="cart_item">
                                                        <td class="product-name">
                                                            ${p.name} <strong class="product-quantity"> × ${od.quantity}</strong>
                                                        </td>
                                                        <td class="product-total">
                                                            <span class="amount">${p.price * od.quantity}</span>

                                                        </td>

                                                    </tr>
                                                </tbody>
                                                <c:set var="lineTotal" value="${p.price * od.quantity}" />
                                                <c:set var="totalAmount" value="${totalAmount + lineTotal}" scope="page" />
                                            </c:forEach>
                                            <tfoot>
                                                <tr class="order-total">
                                                    <th>Order Total</th>
                                                    <td><strong><span class="amount">${totalAmount}</span></strong>
                                                    </td>
                                                </tr>								
                                            </tfoot>
                                        </table>
                                    </div>
                                    <div class="payment">
                                        <div class="order-date">
                                            <p>Order date: <label for="order-date">${cart.orderDate}</label></p>

                                        </div>
                                        <div class="status">
                                            <p>Status: <label for="order-date">${cart.status}</label></p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- checkout-area-end -->
    <!-- footer-area-start -->
    <jsp:include page="../common/homepage/footer.jsp"></jsp:include>
        <!-- footer-area-end -->


        <!-- all js here -->
        <!-- jquery latest version -->
        <script src="${pageContext.request.contextPath}/js/vendor/jquery-1.12.4.min.js"></script>


    <!-- bootstrap js -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- owl.carousel js -->
    <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
    <!-- meanmenu js -->
    <script src="${pageContext.request.contextPath}/js/jquery.meanmenu.js"></script>
    <!-- wow js -->
    <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
    <!-- jquery.parallax-1.1.3.js -->
    <script src="${pageContext.request.contextPath}/js/jquery.parallax-1.1.3.js"></script>
    <!-- jquery.countdown.min.js -->
    <script src="${pageContext.request.contextPath}/js/jquery.countdown.min.js"></script>
    <!-- jquery.flexslider.js -->
    <script src="${pageContext.request.contextPath}/js/jquery.flexslider.js"></script>
    <!-- chosen.jquery.min.js -->
    <script src="${pageContext.request.contextPath}/js/chosen.jquery.min.js"></script>
    <!-- jquery.counterup.min.js -->
    <script src="${pageContext.request.contextPath}/js/jquery.counterup.min.js"></script>
    <!-- waypoints.min.js -->
    <script src="${pageContext.request.contextPath}/js/waypoints.min.js"></script>
    <!-- plugins js -->
    <script src="${pageContext.request.contextPath}/js/plugins.js"></script>
    <!-- main js -->
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <!-- modernizr css -->
    <script src="${pageContext.request.contextPath}/js/vendor/modernizr-2.8.3.min.js"></script>
</body>

<!-- Mirrored from htmldemo.net/koparion/koparion/checkout.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:39 GMT -->
</html>
