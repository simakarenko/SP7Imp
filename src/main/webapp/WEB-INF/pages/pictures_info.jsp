<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h3>All Pictures</h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="delete_picture" class="btn btn-default navbar-btn">Delete Picture
                        </button>
                    </li>
                    <li>
                        <button type="button" id="reset" class="btn btn-default navbar-btn">Reset</button>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Id</b></td>
            <td><b>Image</b></td>
            <td><b>Name</b></td>
        </tr>
        </thead>
        <c:forEach items="${pictures}" var="picture">

            <tr>
                <td><input type="checkbox" name="toDeletePictures[]" value="${picture.id}" id="checkbox_${picture.id}"/>
                </td>
                <td>${picture.id}</td>
                <td><img height="50" width="55" src="<c:url value="/static/${picture.name}"/>"/></td>
                <td>${picture.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
    $('.dropdown-toggle').dropdown();

    $('#all_pictures').click(function () {
        window.location.href = '/allPictures';
    });

    $('#reset').click(function () {
        window.location.href = '/reset';
    });

    $('#delete_picture').click(function () {
        var data = {'toDeletePictures[]': []};
        $(":checked").each(function () {
            data['toDeletePictures[]'].push($(this).val());
        });
        $.post("/pictures/delete", data, function (data, status) {
            window.location.reload();
        });
    });
</script>
</body>
</html>