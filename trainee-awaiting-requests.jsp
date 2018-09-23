<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%--
  Created by IntelliJ IDEA.
  User: amit kolluri
  Date: 8/25/2018
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Batch Details</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="/static/css/landing.css">
</head>
<body>
<div class="container">
    <%@include file="../../common/header-default.jsp" %>
    <div class="content">
        <%@include file="../sidebar-trainer.jsp" %>
        <div class="ota-main_container">
            <h1>Awaiting Trainee Approvals</h1>
            &nbsp;

            <form:form action="/trainer/processapprovedrequests.htm" method="post">
                <c:if test="${empty awaitingtraineeslist}">
                    <h3>No Approvals pending.</h3>
                </c:if>
                <c:if test="${fn:length(awaitingtraineeslist)>0}">

                    <div class="wrap">
                        <table id="example" class="head">
                            <tr>
                                <td class="wrap-td">Trainee Name</td>
                                <td class="wrap-td">Email</td>
                                <td class="wrap-td">Batch</td>
                                <td class="wrap-td">Approve</td>
                            </tr>
                        </table>

                        <div class="inner_table">
                            <table id="test-table">
                                <tbody>
                                <c:forEach items="${awaitingtraineeslist}" var="user">

                                <tr>
                                    <td>${user.firstName} ${user.lastName}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <c:forEach items="${batchList}" var="batch">
                                            <c:if test="${batch.id == user.batchId}">
                                                ${batch.name}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>

                                        <input type="checkbox" name="status" value="${user.id}">

                                    </td>

                                </tr>
                                </c:forEach>

                            </table>
                            <div class="edit-batch-submit-button">
                                <input type="submit" value="submit" class="button"/>
                            </div>
                        </div>


                    </div>
                </c:if>
            </form:form>

        </div>

    </div>
    <section class="icons">
        <div class="icon-box"></div>
    </section>
</div>
<%@include file="../../common/footer.jsp" %>
</body>
</html>
