<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="index.jsp"><img src="assets/img/BiblioSoft Logo.png"
                                     alt="BiblioSoft Logo" class="img-responsive logo"></a>
        </div>
        <div class="container-fluid">
            <div class="navbar-btn">
                <button type="button" class="btn-toggle-fullwidth">
                    <i class="lnr lnr-arrow-left-circle"></i>
                </button>
            </div>
            <form class="navbar-form navbar-left"></form>
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown"> <img src="assets/img/user.png"
                                                                         class="img-circle" alt="Avatar">
                        <span>${sessionScope.ReaderEntity.name}</span></a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll">
            <nav>
                <ul class="nav">
                    <li><a href="readerIndex.jsp" class=""><i class="lnr lnr-home"></i> <span>Reader Home</span></a>
                    </li>
                    <li><a href="readerChangeInformation.jsp" class=""><i class="lnr lnr-code"></i> <span>Change Information</span></a>
                    </li>
                    <li><a href="readerSearchBook.jsp" class=""><i
                            class="lnr lnr-chart-bars"></i><span>Search Book</span></a></li>
                    <li>
                        <a href="#subPages1" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>Borrow Record</span>
                            <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                        <div id="subPages1" class="collapse ">
                            <ul class="nav">
                                <li><a href="ReaderBorrowHistory" class=""><i class="lnr lnr-cog"></i> <span>Borrow History</span></a>
                                </li>
                                <li><a href="ReaderReturnHistory" class=""><i class="lnr lnr-alarm"></i><span>Return History</span></a>
                                </li>
                            </ul>
                        </div>
                    </li>

                    <li><a href="ReaderBorrowCart" class=""><i class="lnr lnr-linearicons"></i> <span>Borrow Cart</span></a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>