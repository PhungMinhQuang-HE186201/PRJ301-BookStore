<%-- 
    Document   : home
    Created on : Jun 1, 2024, 9:37:44 PM
    Author     : PMQUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">


    <!-- Mirrored from htmldemo.net/koparion/koparion/shop.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:51 GMT -->

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css"
              integrity="sha512-+L4yy6FRcDGbXJ9mPG8MT/3UCDzwR9gPeyFNMCtInsol++5m3bk2bXWKdZjvybmohrAsn3Ua5x8gfLnbE1YkOg=="
              crossorigin="anonymous"
              referrerpolicy="no-referrer" />
<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">-->
        <!-- flexslider.css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexslider.css">
        <!-- chosen.min.css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chosen.min.css">
        <!-- style css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <!-- responsive css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/responsive.css">
    </head>

    <body class="shop">
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
        <jsp:include page="../common/homepage/breadcrumbs-home-area.jsp"></jsp:include>
            <!-- breadcrumbs-area-end -->
            <!-- shop-main-area-start -->
            <div class="shop-main-area mb-70">
                <div class="category-image mb-30">
                    <a href="#"><img src="${pageContext.request.contextPath}/img/banner/32.jpg" alt="banner"/></a>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-12 col-12 order-lg-1 order-2 mt-sm-50 mt-xs-40">
                        <div class="shop-left">
                            <div class="section-title-5 mb-30">
                                <h2>Shopping Options</h2>
                            </div>
                            <div class="left-title mb-20">
                                <h4>Category</h4>
                            </div>
                            <div class="left-menu mb-30">
                                <ul>
                                    <c:forEach items="${vectorCategory}" var="c">
                                        <li><a href="home?search=category&category_id=${c.getId()}" onclick="saveScrollPosition()">${c.getName()}</a></li>
                                        </c:forEach>
                                </ul>
                            </div>

                            <div class="left-title mb-20">
                                <h4>Price</h4>
                            </div>
                            <div class="left-menu mb-30">
                                <ul>
                                    <li><a href="home" onclick="saveScrollPosition()">All</a></li>
                                    <li><a href="home?search=price&price=0:25000" onclick="saveScrollPosition()"><=25,000đ</a></li>
                                    <li><a href="home?search=price&price=26000:30000" onclick="saveScrollPosition()">26,000-30,000đ</a></li>
                                    <li><a href="home?search=price&price=31000:max" onclick="saveScrollPosition()">>=31,000</a></li>
                                </ul>
                            </div>
                            <div class="left-title mb-20">
                                <h4>Best Seller</h4>
                            </div>
                            <div class="random-area mb-30">
                                <c:forEach items="${bestSeller}" var="best">
                                    <div class="product-active-2 owl-carousel">
                                        <div class="product-total-2">
                                            <div class="single-most-product bd mb-18">
                                                <div class="most-product-img">
                                                    <a href="product-details?id=${best.getProduct_id()}&category_id=${best.getCategory_id()}" onclick="saveScrollPosition()"><img src="${pageContext.request.contextPath}/img/product/${best.getImage()}" alt="book" /></a>
                                                </div>
                                                <div class="most-product-content">
                                                    <h4><a href="product-details?id=${best.getProduct_id()}&category_id=${best.getCategory_id()}" onclick="saveScrollPosition()">${best.getProduct_name()}</a></h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="banner-area mb-30">
                                <div class="banner-img-2">
                                    <a href="#"><img src="${pageContext.request.contextPath}/img/banner/31.jpg" alt="banner"/></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9 col-md-12 col-12 order-lg-2 order-1">
                        <div class="section-title-5 mb-30">
                            <h2>Manga</h2>
                        </div>
                        <div class="toolbar mb-30" style="transform: translateY(-90%);">
                            <form action="home" method="get">
                                <div style="margin-right:0%;" class="toolbar-sorter">
                                    <span>Sort By</span>
                                    <select id="sorter" class="sorter-options" name="sortOption" onchange="this.form.submit()" onclick="saveScrollPosition()">
                                        <option value="id_desc" ${param.sortOption == 'id_desc' ? 'selected' : ''}>Latest</option>
                                        <option value="id_asc" ${param.sortOption == 'id_asc' ? 'selected' : ''}>Oldest</option>
                                        <option value="name_asc" ${param.sortOption == 'name_asc' ? 'selected' : ''}>Product Name A-Z</option>
                                        <option value="name_desc" ${param.sortOption == 'name_desc' ? 'selected' : ''}>Product Name Z-A</option>
                                        <option value="price_asc" ${param.sortOption == 'price_asc' ? 'selected' : ''}>Price Ascending</option>
                                        <option value="price_desc" ${param.sortOption == 'price_desc' ? 'selected' : ''}>Price Descending</option>
                                    </select>
                                </div>
                                <input type="hidden" name="search" value="Sort">
                                <input type="hidden" name="pageIndex" value="${page.pageIndex}">
                            </form>
                        </div>
                        <!-- tab-area-start -->
                        <div class="tab-content" style="transform: translateY(-5%);">
                            <div class="tab-pane fade show active" id="th">
                                <div class="row">
                                    <c:forEach items="${vectorProduct}" var="p">
                                        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6">
                                            <!-- single-product-start -->
                                            <div class="product-wrapper mb-40">
                                                <div class="product-img">
                                                    <a href="product-details?id=${p.getId()}&category_id=${p.getCategory_id()}">
                                                        <img src="${pageContext.request.contextPath}/img/product/${p.getImage()}" 
                                                             alt="book" 
                                                             style="width: 200px; height: 300px; object-fit: cover;" />
                                                    </a>
                                                </div>
                                                <div class="product-details text-center">
                                                    <h4><a href="product-details?id=${p.getId()}&category_id=${p.getCategory_id()}">${p.getName()}</a></h4>
                                                    <div class="product-price">
                                                        <ul>
                                                            <li>${p.getFormattedPrice()}đ</li>
                                                        </ul>
                                                    </div>
                                                </div>

                                            </div>
                                            <!-- single-product-end -->
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <!-- tab-area-end -->
                        <!-- pagination-area-start -->
                        <div class="pagination-area mt-50" style="margin-top:0">
                            <div class="page-number">
                                <ul>
                                    <c:if test="${page.pageIndex == page.totalPage && page.pageIndex != 1 || page.pageIndex > page.totalPage || page.pageIndex < page.totalPage && page.pageIndex > 1}">
                                        <li>
                                            <a href="${page.urlPattern}pageIndex=${page.pageIndex-1}">
                                                <i class="fa fa-angle-left"></i>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${page.totalPage}" var="pageIndex">
                                        <li class="${pageIndex == page.pageIndex ? 'active' : ''}">
                                            <a href="${page.urlPattern}pageIndex=${pageIndex}" style="${pageIndex == page.pageIndex ? 'text-decoration: underline;' : ''}">${pageIndex}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${page.pageIndex ==1 && page.pageIndex != page.totalPage|| page.pageIndex < page.totalPage && page.pageIndex > 1}">
                                        <li>
                                            <a href="${page.urlPattern}pageIndex=${page.pageIndex+1}">
                                                <i class="fa fa-angle-right"></i>
                                            </a>
                                        </li>
                                    </c:if>

                                </ul>
                            </div>
                        </div>
                        <!-- pagination-area-end -->
                    </div>
                </div>
            </div>
        </div>
        <!-- shop-main-area-end -->
        <!-- footer-area-start -->
        <jsp:include page="../common/homepage/footer.jsp"></jsp:include>
            <!-- footer-area-end -->







            <!-- all js here -->
            <!-- jquery latest version -->
            <script src="${pageContext.request.contextPath}/js/vendor/jquery-1.12.4.min.js"></script>
        <!-- modernizr css -->
        <script src="${pageContext.request.contextPath}/js/vendor/modernizr-2.8.3.min.js"></script>

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
        <script>
                                        // Lưu trạng thái cuộn trang vào localStorage
                                        function saveScrollPosition() {
                                            localStorage.setItem("scrollTop", window.pageYOffset || document.documentElement.scrollTop);
                                        }

                                        // Khôi phục trạng thái cuộn trang từ localStorage
                                        function restoreScrollPosition() {
                                            var scrollTop = localStorage.getItem("scrollTop");
                                            if (scrollTop !== null) {
                                                window.scrollTo(0, parseInt(scrollTop, 10));
                                                localStorage.removeItem("scrollTop");
                                            }
                                        }

                                        // Khôi phục trạng thái cuộn trang khi trang tải lại
                                        window.onload = function () {
                                            restoreScrollPosition();
                                        };
        </script>
    </body>


    <!-- Mirrored from htmldemo.net/koparion/koparion/shop.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:52 GMT -->

</html>
