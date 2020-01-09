<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1>Add Computer</h1>
                    <form action="addcomputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name">
                                                              
                                	<c:if test="${ValidationNameIsEmpty}">
                                		<c:out value="Vous devez rentrer au moins un caractère" />
                                	</c:if>                               
                                
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="text" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="text" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                
                                    <option value="0">--</option>
                                    
                                    	<c:forEach items="${listCompany}" var="company">
                                    
                                    		<option value="${company.getIdCompany()} "><c:out value="${company.getNameCompany()}"/></option>
                                    	
                                    	</c:forEach>
                                    
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="/computer-database" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>