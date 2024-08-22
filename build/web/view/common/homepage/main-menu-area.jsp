<%-- 
    Document   : main-menu-area
    Created on : Jun 2, 2024, 2:30:01 AM
    Author     : PMQUANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="main-menu-area d-md-none d-none d-lg-block sticky-header-1" id="header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="menu-area">
                    <nav>
                        <ul>
                            <li class="active"><a href="home">Home<i class=""></i></a>
                            </li>
                            <li><a href="product-details.html">Book<i class="fa fa-angle-down"></i></a>
                                <div class="mega-menu">
                                    <span>
                                        <a href="home?search=category&category_id=1" onclick="saveScrollPosition()" class="title">Shounen</a>
                                        <a href="home?search=searchByName&keyword=Dragon+ball" onclick="saveScrollPosition()">Dragon ball</a>
                                        <a href="home?search=searchByName&keyword=Naruto" onclick="saveScrollPosition()">Naruto</a>
                                        <a href="home?search=searchByName&keyword=One+piece" onclick="saveScrollPosition()">One piece</a>
                                        <a href="home?search=searchByName&keyword=Kimetsu+no+yaiba" onclick="saveScrollPosition()">Kimetsu no yaiba</a>
                                        <a href="home?search=searchByName&keyword=Jujutsu+kaisen" onclick="saveScrollPosition()">Jujutsu kaisen</a>
                                        <a href="home?search=searchByName&keyword=My+hero+academia" onclick="saveScrollPosition()">My hero academia</a>
                                        <a href="home?search=searchByName&keyword=One+Punch+Man" onclick="saveScrollPosition()">One Punch Man</a>
                                        <a href="home?search=searchByName&keyword=Bleach" onclick="saveScrollPosition()">Bleach</a>
                                    </span>
                                    <span>
                                        <a href="home?search=category&category_id=2" onclick="saveScrollPosition()" class="title">Detective</a>
                                        <a href="home?search=searchByName&keyword=Conan" onclick="saveScrollPosition()">Conan</a>
                                        <a href="home?search=searchByName&keyword=Death+note" onclick="saveScrollPosition()">Death note</a>
                                        <a href="home?search=searchByName&keyword=Kindaichi" onclick="saveScrollPosition()">Kindaichi</a>
                                        <a href="home?search=searchByName&keyword=Gosick" onclick="saveScrollPosition()">Gosick</a>
                                    </span>
                                    <span>
                                        <a href="home?search=category&category_id=3" onclick="saveScrollPosition()" class="title">Sports</a>
                                        <a href="home?search=searchByName&keyword=Tsubasa" onclick="saveScrollPosition()">Tsubasa</a>
                                        <a href="home?search=searchByName&keyword=Bluelock" onclick="saveScrollPosition()">Bluelock</a>
                                        <a href="home?search=searchByName&keyword=Haikyuu" onclick="saveScrollPosition()">Haikyuu</a>
                                        <a href="home?search=searchByName&keyword=Dorabase" onclick="saveScrollPosition()">Dorabase</a>
                                        <a href="home?search=searchByName&keyword=Slam dunk" onclick="saveScrollPosition()">Slam dunk</a>
                                    </span>
                                    <span>
                                        <a href="home?search=category&category_id=4" onclick="saveScrollPosition()" class="title">Comedy</a>
                                        <a href="home?search=searchByName&keyword=Spy+x+family" onclick="saveScrollPosition()">Spy x family</a>
                                        <a href="home?search=searchByName&keyword=Beelzebub" onclick="saveScrollPosition()">Beelzebub</a>
                                        <a href="home?search=searchByName&keyword=Doraemon" onclick="saveScrollPosition()">Doraemon</a>
                                    </span>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
