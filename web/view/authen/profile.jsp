<%-- 
    Document   : profile
    Created on : Jun 29, 2024, 12:22:46 AM
    Author     : PMQUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">

    <!-- Mirrored from htmldemo.net/koparion/koparion/my-account.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:38 GMT -->

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

    <body class="cart">
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
        <jsp:include page="../common/authen/breadcrumbs-profile-area.jsp"></jsp:include>
            <!-- breadcrumbs-area-end -->
            <!-- entry-header-area-start -->
            <div class="entry-header-area">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="entry-header-title">
                                <h2>My-Account</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- entry-header-area-end -->
            <!-- my account wrapper start -->
            <div class="my-account-wrapper mb-70">
                <div class="container">
                    <div class="section-bg-color">
                        <div class="row">
                            <div class="col-lg-12">
                                <!-- My Account Page Start -->
                                <div class="myaccount-page-wrapper">
                                    <!-- My Account Tab Menu Start -->
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4">
                                            <div class="myaccount-tab-menu nav" role="tablist">
                                                <a href="#account-info" data-bs-toggle="tab"><i class="fa fa-user"></i> User Profile</a>
                                            </div>
                                        </div>
                                        <!-- My Account Tab Menu End -->

                                        <!-- My Account Tab Content Start -->
                                        <div class="col-lg-9 col-md-8">
                                            <div class="tab-content" id="myaccountContent">
                                                <!-- Single Tab Content Start -->
                                                <div class="tab-pane fade" id="account-info" role="tabpanel">
                                                    <div class="myaccount-content">
                                                        <h5>Account Details</h5>
                                                        <div class="account-details-form">
                                                            <form action="authen?action=changeInfo" method="post">
                                                                <div class="row">
                                                                    <div class="col-lg-6">
                                                                        <div class="single-input-item">
                                                                            <label for="first-name" class="required">UserName</label>
                                                                            <input type="text" id="first-name" placeholder="UserName" value="${accFounded.username}" readonly/>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-6">
                                                                    <div class="single-input-item">
                                                                        <label for="email" class="required">Email</label>
                                                                        <input type="email" id="last-name" placeholder="Email" value="${accFounded.email}" readonly/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="single-input-item">
                                                                <label for="address" class="required">Address</label>
                                                                <input type="text" id="address" placeholder="Address" value="${accFounded.address}" name="address"/>
                                                            </div>
                                                            <div class="single-input-item">
                                                                <label for="phone" class="required">Phone</label>
                                                                <input type="text" id="phone" placeholder="Phone" value="${accFounded.phone}" name="phone"/>
                                                            </div>
                                                            <fieldset>
                                                                <legend>Password change</legend>
                                                                <input type="hidden" name="username" value="${accFounded.username}">
                                                                <div class="row">
                                                                    <div class="col-lg-6">
                                                                        <div class="single-input-item">
                                                                            <label for="new-pwd" class="required">New Password</label>
                                                                            <input type="password" id="new-pwd" placeholder="New Password" name="newPass"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-lg-6">
                                                                        <div class="single-input-item">
                                                                            <label for="confirm-pwd" class="required">Confirm Password</label>
                                                                            <input type="password" id="confirm-pwd" placeholder="Confirm Password" name="confirmPass"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </fieldset>
                                                            <div class="single-input-item">
                                                                <input type="submit" name="submit" class="btn btn-sqr" style="padding-top: 0%;width: 20%" value="Save Changes">
                                                            </div>
                                                            <p style="color:red">${error}</p>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div> <!-- Single Tab Content End -->

                                        </div>
                                    </div> <!-- My Account Tab Content End -->
                                </div>

                            </div> <!-- My Account Page End -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- my account wrapper end -->
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

    <!-- Mirrored from htmldemo.net/koparion/koparion/my-account.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 23 Feb 2024 17:30:39 GMT -->

</html>
