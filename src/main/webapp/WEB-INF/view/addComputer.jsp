   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<a href="?locale=en"><img style="margin-left: 50px;" src="https://cdn.icon-icons.com/icons2/107/PNG/512/united_kingdom_flag_flags_18060.png" id="drapeau" width="24" height="24" alt="England"> </a>
<a href="?locale=fr"><img style="margin-left: 10px;" src="http://www.vogo-group.com/wp-content/uploads/2019/10/france_icon.png" id="drapeau" alt="France" width="24" height="24"></a>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/computer-database"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="label.addComputer"/></label></h1>
                    <form:form method="POST" action="addcomputer" modelAttribute="computer">

                        <fieldset>
                            <div class="form-group">
                                <form:label for="computerName" path="computerName"><spring:message code="label.computerName"/></form:label>
                                <form:input type="text" path="computerName" class="form-control" id="computerName" name="computerName" placeholder="computerName"/>
                                                             
                                <c:if test="${nameError != ''}">
                                 <c:out value="${nameError}" />
                                </c:if>                              
                               
                            </div>
                            <div class="form-group">
                                <form:label for="introduced" path="introducedDate"><spring:message code="label.introducedDate"/></form:label>
                                <form:input type="text" path="introducedDate" class="form-control" id="introduced" name="introduced" placeholder="introducedDate"/>
                            
                           		<c:if test="${dateError != ''}">
                                 <c:out value="${dateError}" />
                                </c:if>  
                                
                            </div>
                            <div class="form-group">
                                <form:label for="discontinued" path="discontinuedDate"><spring:message code="label.discontinuedDate"/></form:label>
                                <form:input type="text" path="discontinuedDate" class="form-control" id="discontinued" name="discontinued" placeholder="discontinuedDate"/>
                            </div>
                            <div class="form-group">
                                <form:label for="companyId" path="idCompany"><spring:message code="label.company"/></form:label>
                                <form:select class="form-control" path="idCompany" id="companyId" name="companyId">
                               
                                    <form:option value="0">--</form:option>
                                   
                                    <c:forEach items="${listCompany}" var="company">
                                   
                                    <form:option value="${company.getIdCompany()} "><c:out value="${company.getNameCompany()}"/></form:option>
                                   
                                    </c:forEach>
                                   
                                </form:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="label.add"/>" class="btn btn-primary">
                            or
                            <a href="/computer-database" class="btn btn-default"><spring:message code="label.cancel"/></a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>