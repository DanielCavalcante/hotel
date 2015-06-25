<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Hotel | Reservas</title>
        <!-- Bootstrap core CSS -->
        <link href="${context}/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="${context}/dist/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="${context}/dist/js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="${context}/dist/js/html5shiv.min.js"></script>
        <script src="${context}/dist/js/respond.min.js"></script>
        <![endif]-->
        <style>
            body {
                padding-top: 50px;
            }
            .starter-template {
                padding: 40px 15px;
            }
        </style>
    </head>
    <body>

    <jsp:include page="includes/menu.jsp"></jsp:include>
    <div class="container">
        <div class="starter-template">
            <decorator:body />
        </div>
    </div>
    <script src="${context}/dist/js/jquery.min.js"></script>
    <script src="${context}/dist/js/bootstrap.min.js"></script>
    <script src="${context}/dist/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
