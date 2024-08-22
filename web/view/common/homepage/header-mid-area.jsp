<%-- 
    Document   : header-mid-area
    Created on : Jun 2, 2024, 2:22:20 AM
    Author     : PMQUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header-mid-area ptb-40">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-5 col-12">
                <div class="header-search">
                    <form action="home" method="get">
                        <input type="hidden" name="search" value="searchByName">
                        <input type="text" placeholder="Search entire store here..." name="keyword"/>
                        <a href="#" onclick="return this.closest('form').submit()"><i class="fa fa-search"></i></a>
                    </form>
                </div>
            </div>
            <div class="col-lg-6 col-md-4 col-12">
                <div class="logo-area text-center logo-xs-mrg">
                    <a href="home"><img src="${pageContext.request.contextPath}/img/logo/logo.png" alt="logo" /></a>
                </div>
            </div>
            <div class="col-lg-3 col-md-3 col-12">
                <div class="my-cart">

                    <ul>
                        <c:if test="${accFounded != null}">
                            <li><a href="cart"><i class="fa fa-shopping-cart"></i>My Cart</a>
                                <div class="mini-cart-sub">
                                    <div class="cart-bottom">
                                        <a class="view-cart" href="cart">view cart</a>
                                    </div>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${accFounded == null}">
                            <li><a href="authen?action=login"><i class="fa fa-shopping-cart"></i>My Cart</a>
                                <div class="mini-cart-sub">
                                    <div class="cart-bottom">
                                        <a class="view-cart" href="authen?action=login">view cart</a>
                                    </div>
                                </div>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
