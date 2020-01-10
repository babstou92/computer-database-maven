<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" media="screen">
<style><%@ include file ="../style/main.css"%></style>
<style><%@ include file ="../style/font-awesome.css"%></style>
<style><%@ include file ="../style/bootstrap.min.css"%></style>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/computer-database"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <c:out value="${computer.getIdComputer()}"/>
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="editcomputer" method="POST">
                        <input type="hidden" id="id" name="id" value="${computer.getIdComputer()}"/> 
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="label.computerName"/></label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="<spring:message code="label.computerName"/>" value="${computer.getName()}">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="label.introducedDate"/></label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="<spring:message code="label.introducedDate"/>" value="${computer.getIntroducedDate()}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="label.discontinuedDate"/></label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="<spring:message code="label.discontinuedDate"/>" value="${computer.getDiscontinuedDate()}">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="label.company"/></label>
                                <select class="form-control" id="companyId" name="companyId">
                                
                                    <option value="0">--</option>
                                    
                                      <c:forEach items="${listCompany}" var="company">
                                    
                                    	<option value="${company.getIdCompany()} "><c:out value="${company.getNameCompany()}"/></option>
                                    	
                                   	 </c:forEach>
                                   	 
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="label.add"/>" class="btn btn-primary">
                            or
                            <a href="/computer-database" class="btn btn-default"><spring:message code="label.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>