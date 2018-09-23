<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%--
  Created by IntelliJ IDEA.
  User: amit kolluri
  Date: 8/21/2018
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>edit-batch</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/css/landing.css">
</head>

<body>
<div class="wrapper">
    <%@include file="../../common/header-default.jsp" %>
    <%@include file="../sidebar-trainer.jsp" %>
    <section class="content">
        <h1 class="content_header"></h1>
        <div class="content_container">
            <div class="edit-batch-name">
                <form:form action="/trainer/editbatch.htm" method="post"
                           modelAttribute="command">
                    <table class="batch-edit-table">
                        <tr class="batch-edit-table-row">
                            <td class="batch-edit-table-data">Current Batch Name</td>
                            <td class="batch-edit-table-data">${batchName}</td>
                        </tr>
                        <tr class="batch-edit-row">
                            <td class="batch-edit-table-data">Enter New Batch Name</td>

                            <td class="batch-edit-table-data">
                                <form:input path="newBatchName" class="input-box" type="text"
                                            name="newBatchName"/>
                            </td>

                        </tr>
                    </table>
                    <div class="edit-batch-submit-button">
                        <input type="submit" value="submit" class="button"/>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
</div>
<%@include file="../../common/footer.jsp" %>
</body>
</html>