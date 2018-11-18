<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">

<jsp:include page="readerNavbar.jsp"/>
<body>
<!-- WRAPPER -->
<form method="post" action="readerChangeInformation.jsp">
    <jsp:include page="readerLeftSlider.jsp"/>
    <!-- END LEFT SIDEBAR -->
    <!-- MAIN -->

    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <h3 class="page-title">Reader Index</h3>
                <div class="row">
                    <!-- BASIC TABLE -->
                    <div class="panel">
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Reader_id</th>
                                    <th>Reader_name</th>
                                    <th>State</th>
                                    <th>Email</th>
                                    <th>MaxBorrow</th>
                                </tr>
                                <tr>
                                    <td>${sessionScope.ReaderEntity.id}</td>
                                    <td>${sessionScope.ReaderEntity.name}</td>
                                    <td>${sessionScope.ReaderEntity.state}</td>
                                    <td>${sessionScope.ReaderEntity.email}</td>
                                    <td>10</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END MAIN CONTENT -->
    </div>
    <!-- END MAIN -->
    <div class="clearfix"></div>

    </div>
    <!-- END WRAPPER -->
    <!-- Javascript -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="assets/scripts/klorofil-common.js"></script>
</form>
</body>

</html>
