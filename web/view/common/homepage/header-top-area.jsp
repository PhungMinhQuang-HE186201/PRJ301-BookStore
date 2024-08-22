<%-- 
    Document   : header-top-area
    Created on : Jun 2, 2024, 2:20:45 AM
    Author     : PMQUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header-top-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-12">
                <div class="hdt-social-network" style="margin-top: 10px;">
                    <a  href="https://www.facebook.com/profile.php?id=100035831596593" target="_blank"
                        class="fb-icon"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                    <a  href="https://www.youtube.com/" target="_blank" class="yt-icon"><i class="fa fa-youtube-play"
                                                                                           aria-hidden="true"></i></a>
                </div>

            </div>
            <div class="col-lg-6 col-md-6 col-12">
                <div class="account-area text-end">
                    <ul>
                        <c:if test="${accFounded != null}">
                            <!--User-->
                            <li>
                                <a href="${pageContext.request.contextPath}/authen?action=profile">My Account</a>
                            </li>
                        </c:if>
                        <c:if test="${accFounded != null}">
                            <!--User-->
                            <li>
                                <a href="userbill">My Order</a>
                            </li>
                        </c:if>
                        <c:if test="${accFounded == null}">
                            <li>
                                <a href="authen?action=login">Sign in</a>
                            </li>
                            <li>
                                <a href="authen?action=signUp">Sign up</a>
                            </li>
                        </c:if>               
                        <c:if test="${accFounded != null}">
                            <li>
                                <a href="authen?action=logout">Sign out</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
