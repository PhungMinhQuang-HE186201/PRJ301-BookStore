<%-- 
    Document   : product-details
    Created on : Jun 5, 2024, 2:02:28 AM
    Author     : PMQUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">

    <!-- Mirrored from htmldemo.net/koparion/koparion/# by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:50 GMT -->
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
    <body class="product-details">
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
        <jsp:include page="../common/homepage/breadcrumbs-product-details-area.jsp"></jsp:include>
            <!-- breadcrumbs-area-end -->
            <!-- product-main-area-start -->
            <div class="product-main-area mb-70">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-9 col-md-12 col-12 order-lg-1 order-1">
                            <!-- product-main-area-start -->
                            <div class="product-main-area">
                                <div class="row">
                                    <div class="col-lg-5 col-md-6 col-12">
                                        <img src="${pageContext.request.contextPath}/img/product/${productFounded.getImage()}" alt="book" class="primary" />
                                    </div>
                                <div class="col-lg-7 col-md-6 col-12">
                                    <div class="product-info-main">
                                        <div class="page-title">
                                            <h1>${productFounded.getName()}</h1>
                                        </div>
                                        <div class="product-info-price">
                                            <div class="price-final">
                                                <span>${productFounded.getFormattedPrice()}đ</span>
                                            </div>
                                        </div>
                                        <div class="product-add-form">
                                            <form action="cart?action=addProduct" method="post">
                                                <input type="hidden" name="id" value="${productFounded.getId()}">
                                                <div class="quality-button">
                                                    <input class="qty" type="number" name="quantity" value="1" min="1" max="${productFounded.getQuantity()}">
                                                </div>
                                                <a href="#" onclick="return this.closest('form').submit();">Add to cart</a>
                                            </form>
                                        </div>
                                        <div class="product-social-links">
                                            <div class="product-addto-links-text">
                                                <p>${productFounded.getDescription()}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>	
                        </div>
                        <!-- product-main-area-end -->
                        <!-- product-info-area-start -->
                        <div class="product-info-area mt-80">
                            <!-- Nav tabs -->
                            <ul class="nav">
                                <li><a class="active" href="#Details" data-bs-toggle="tab">Details</a></li>
                               
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade show active" id="Details">
                                    <div class="valu">
                                        <p>${productFounded.getDescription()}</p>
                                        <ul>
                                            <li><i class="fa fa-circle"></i>exists: ${productFounded.getQuantity()}</li>
                                            <li><i class="fa fa-circle"></i>category: ${categoryFounded.getName()}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>	
                        </div>
                        <!-- product-info-area-end -->
                        <!-- new-book-area-start -->
                        <div class="new-book-area mt-60">
                            <div class="section-title text-center mb-30">
                                <h3>upsell products</h3>
                            </div>
                            <div class="tab-active-2 owl-carousel">
                                <!-- single-product-start -->
                                <div class="product-wrapper">
                                    <div class="product-img">
                                        <a href="product-details?id=3&category_id=1">
                                            <img src="${pageContext.request.contextPath}/img/product/1.jpg" alt="book" class="primary" />
                                        </a>
                                    </div>
                                    <div class="product-details text-center">
                                        <h4><a href="product-details?id=3&category_id=1">One Piece</a></h4>
                                        <div class="product-price">
                                            <ul>
                                                <li>30,000đ</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-link">
                                        <div class="product-button">
                                            <a href="#" title="Add to cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="add-to-link">
                                            <ul>
                                                <li><a href="#" title="Details"><i class="fa fa-external-link"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>	
                                </div>
                                <!-- single-product-end -->
                                <!-- single-product-start -->
                                <div class="product-wrapper">
                                    <div class="product-img">
                                        <a href="product-details?id=14&category_id=3">
                                            <img src="${pageContext.request.contextPath}/img/product/3.jpg" alt="book" class="primary" />
                                        </a>
                                    </div>
                                    <div class="product-details text-center">
                                        <h4><a href="product-details?id=14&category_id=3">Bluelock</a></h4>
                                        <div class="product-price">
                                            <ul>
                                                <li>25,000đ</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-link">
                                        <div class="product-button">
                                            <a href="#" title="Add to cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="add-to-link">
                                            <ul>
                                                <li><a href="#" title="Details"><i class="fa fa-external-link"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>	
                                </div>
                                <!-- single-product-end -->
                                <!-- single-product-start -->
                                <div class="product-wrapper">
                                    <div class="product-img">
                                        <a href="product-details?id=2&category_id=1">
                                            <img src="${pageContext.request.contextPath}/img/product/5.jpg" alt="book" class="primary" />
                                        </a>
                                    </div>
                                    <div class="product-details text-center">
                                        <h4><a href="product-details?id=2&category_id=1">Naruto</a></h4>
                                        <div class="product-price">
                                            <ul>
                                                <li>30,000đ</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-link">
                                        <div class="product-button">
                                            <a href="#" title="Add to cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="add-to-link">
                                            <ul>
                                                <li><a href="#" title="Details"><i class="fa fa-external-link"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>	
                                </div>
                                <!-- single-product-end -->
                                <!-- single-product-start -->
                                <div class="product-wrapper">
                                    <div class="product-img">
                                        <a href="product-details?id=9&category_id=2">
                                            <img src="${pageContext.request.contextPath}/img/product/7.jpg" alt="book" class="primary" />
                                        </a>
                                    </div>
                                    <div class="product-details text-center">
                                        <h4><a href="product-details?id=9&category_id=2">Conan</a></h4>
                                        <div class="product-price">
                                            <ul>
                                                <li>25,000đ</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-link">
                                        <div class="product-button">
                                            <a href="#" title="Add to cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                        </div>
                                        <div class="add-to-link">
                                            <ul>
                                                <li><a href="#" title="Details"><i class="fa fa-external-link"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>	
                                </div>
                                <!-- single-product-end -->	
                            </div>
                        </div>
                        <!-- new-book-area-start -->
                    </div>
                    <div class="col-lg-3 col-md-12 col-12 order-lg-2 order-2">
                        <div class="shop-left">
                            <div class="left-title mb-20">
                                <h4>Related Products</h4>
                            </div>
                            <div class="random-area mb-30">
                                <div class="product-active-2 owl-carousel">
                                    <div class="product-total-2">
                                        <div class="single-most-product bd mb-18">
                                            <div class="most-product-img">
                                                <a href="product-details?id=12&category_id=2"><img src="${pageContext.request.contextPath}/img/product/20.jpg" alt="book" /></a>
                                            </div>
                                            <div class="most-product-content">
                                                <h4><a href="product-details?id=12&category_id=2">Gosick</a></h4>
                                                <div class="product-price">
                                                    <ul>
                                                        <li>25,000đ</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="single-most-product bd mb-18">
                                            <div class="most-product-img">
                                                <a href="product-details?id=16&category_id=3"><img src="${pageContext.request.contextPath}/img/product/21.jpg" alt="book" /></a>
                                            </div>
                                            <div class="most-product-content">
                                                <h4><a href="product-details?id=16&category_id=3">Dorabase</a></h4>
                                                <div class="product-price">
                                                    <ul>
                                                        <li>25,000đ</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="single-most-product">
                                            <div class="most-product-img">
                                                <a href="product-details?id=5&category_id=1"><img src="${pageContext.request.contextPath}/img/product/22.jpg" alt="book" /></a>
                                            </div>
                                            <div class="most-product-content">
                                                <h4><a href="product-details?id=5&category_id=1">Jujutsu Kaisen</a></h4>
                                                <div class="product-price">
                                                    <ul>
                                                        <li>25,000đ</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="product-total-2">
                                        <div class="single-most-product bd mb-18">
                                            <div class="most-product-img">
                                                <a href="product-details?id=4&category_id=1"><img src="${pageContext.request.contextPath}/img/product/10.jpg" alt="book" /></a>
                                            </div>
                                            <div class="most-product-content">
                                                <h4><a href="product-details?id=4&category_id=1">Kimetsu No Yaiba</a></h4>
                                                <div class="product-price">
                                                    <ul>
                                                        <li>25,000đ</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="single-most-product bd mb-18">
                                            <div class="most-product-img">
                                                <a href="product-details?id=6&category_id=1"><img src="${pageContext.request.contextPath}/img/product/11.jpg" alt="book" /></a>
                                            </div>
                                            <div class="most-product-content">
                                                <h4><a href="product-details?id=6&category_id=1">My Hero Academia</a></h4>
                                                <div class="product-price">
                                                    <ul>
                                                        <li>25,000đ</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="single-most-product">
                                            <div class="most-product-img">
                                                <a href="product-details?id=18&category_id=4"><img src="${pageContext.request.contextPath}/img/product/8.jpg" alt="book" /></a>
                                            </div>
                                            <div class="most-product-content">
                                                <h4><a href="product-details?id=18&category_id=4">Spy X Family</a></h4>
                                                <div class="product-price">
                                                    <ul>
                                                        <li>25,000đ</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>	
                                </div>
                            </div>
                            <div class="banner-area mb-30">
                                <div class="banner-img-2">
                                    <a href="#"><img src="${pageContext.request.contextPath}/img/banner/31.jpg" alt="banner" /></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- product-main-area-end -->
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
    </body>

    <!-- Mirrored from htmldemo.net/koparion/koparion/# by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:51 GMT -->
</html>
