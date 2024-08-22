<%-- 
    Document   : chart
    Created on : Jul 18, 2024, 11:28:10 PM
    Author     : PMQUANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin - Charts</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor-admin/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="${pageContext.request.contextPath}/vendor-admin/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/colReorder-bootstrap4.css">

    </head>

    <body id="page-top">

        <!--navbar-->
        <jsp:include page="../common/admin/navbar.jsp"></jsp:include>

            <div id="wrapper">

                <!-- Sidebar -->
            <jsp:include page="../common/admin/sidebar.jsp"></jsp:include>

                <div id="content-wrapper">

                    <div class="container-fluid">

                        <!-- Icon Cards-->
                    <jsp:include page="../common/admin/iconCard.jsp"></jsp:include>

                        <div id="wrapper">
                            <div id="content-wrapper">

                                <div class="container-fluid">

                                    <!-- Breadcrumbs-->
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item">
                                            <a href="#">Dashboard</a>
                                        </li>
                                        <li class="breadcrumb-item active">Charts</li>
                                    </ol>

                                    <!-- Area Chart Example-->
                                    <div class="row">
                                        <div class="col-lg-8">
                                            <div class="card mb-6">
                                                <div class="card-header">
                                                    <i class="fas fa-chart-bar"></i>
                                                    Chart</div>
                                                <div class="card-body">
                                                    <canvas id="myChart" style="width:100%;max-width:600px"></canvas>
                                            </div>
                                            <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <!-- /.container-fluid -->

                            <!-- Sticky Footer -->
                            <footer class="sticky-footer">
                                <div class="container my-auto">
                                    <div class="copyright text-center my-auto">
                                        <span>Copyright © Your Website 2019</span>
                                    </div>
                                </div>
                            </footer>

                        </div>
                        <!-- /.content-wrapper -->

                    </div>
                    <!-- /#wrapper -->

                    <!-- Scroll to Top Button-->
                    <a class="scroll-to-top rounded" href="#page-top">
                        <i class="fas fa-angle-up"></i>
                    </a>

                    <!-- Logout Modal-->
                    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                                <div class="modal-footer">
                                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                                    <a class="btn btn-primary" href="login.html">Logout</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Bootstrap core JavaScript-->
                    <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
                    <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

                    <!-- Core plugin JavaScript-->
                    <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

                    <!-- Page level plugin JavaScript-->
                    <script src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>

                    <!-- Custom scripts for all pages-->
                    <script src="${pageContext.request.contextPath}/js/sb-admin.min.js"></script>

                    <!-- Demo scripts for this page-->
                    <script src="${pageContext.request.contextPath}/js/demo/chart-bar-demo.js"></script>
                    <script src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
                    <script>
                        var xValues = [];
                        var yValues = [];

                        <c:forEach var="product" items="${chart}">
        xValues.push("${product.label}");
        yValues.push(${product.value});
        </c:forEach>;

                        console.log(xValues);
                        console.log(yValues);

                        new Chart("myChart", {
                            type: "bar",
                            data: {
                                labels: xValues,
                                datasets: [{
                                        backgroundColor: ["red", "green", "blue", "orange", "brown"],
                                        data: yValues
                                    }]
                            },
                            options: {
                                legend: {display: false},
                                title: {
                                    display: true,
                                    text: "Product Sold"
                                }
                            }
                        });
</script>
 </body>

    </html>
