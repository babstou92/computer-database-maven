<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<!-- <link href="../style/bootstrap.min.css" rel="stylesheet" media="screen"> -->
<!-- <link href="../style/font-awesome.css" rel="stylesheet" media="screen"> -->
<!-- <link href="../style/main.css" rel="stylesheet" media="screen"> -->
<style><%@ include file ="../style/main.css"%></style>
<style><%@ include file ="../style/font-awesome.css"%></style>
<style><%@ include file ="../style/bootstrap.min.css"%></style>
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
        	
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
            
                <c:out value="${nbComputer}" /> Computers found 
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="/computer-database/addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                
                	<c:forEach items ="${listComputer}" var="computer">
                	    <tr>
	                        <td class="editMode">
	                            <input type="checkbox" name="cb" class="cb" value="0">
	                        </td>
	                        <td>
	                            <a href="editComputer.html" onclick="">${computer.getName()}</a>
	                        </td>
	                        <td>${computer.getIntroducedDate()}</td>
	                        <td>${computer.getDiscontinuedDate()}</td>
	                        <td>${computer.getCompany().getNameCompany()}</td>
                    	</tr>
                    </c:forEach>
            
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
            
            	<c:if test="${currentPage != 1}">
                	<li>
                    	<a href="dashboard" aria-label="Previous">
                      		<span aria-hidden="true">&laquo;</span>
                  		</a> 	
		        	</li>
		        </c:if>
		        
              <c:if test="${currentPage - 2 > 0}">
              	<li><a href="dashboard?page=${currentPage - 2}"><c:out value="${currentPage - 2}"/></a></li>
              </c:if>
              
              <c:if test="${currentPage - 1 > 0}">
              	<li><a href="dashboard?page=${currentPage - 1}"><c:out value="${currentPage - 1}"/></a></li>
              </c:if>
              
              <c:if test="${currentPage != 1}">
              	<li><a href="dashboard?page=${currentPage    }"><c:out value="${currentPage    }"/></a></li>
              </c:if>
              
              <c:if test="${currentPage + 1  < nbPage+1}">
              	<li><a href="dashboard?page=${currentPage + 1}"><c:out value="${currentPage + 1}"/></a></li>
              </c:if>
              
              <c:if test="${currentPage + 2  < nbPage+1}">
              	<li><a href="dashboard?page=${currentPage + 2}"><c:out value="${currentPage + 2}"/></a></li>
              </c:if>
              
              <c:if test="${currentPage < nbPage}">
              	<li>
	                <a href="dashboard?page=${nbPage}" aria-label="Next">
	                    <span aria-hidden="true">&raquo;</span>
	                </a>            
            	</li>
            </c:if>
            
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
        
            <a class="btn btn-default" href="dashboard?limit=${10}"><c:out value="${10}"/></a>
            <a class="btn btn-default" href="dashboard?limit=${50}"><c:out value="${50}"/></a>
            <a class="btn btn-default" href="dashboard?limit=${100}"><c:out value="${100}"/></a>
            
        </div>
	</div>
    </footer>
<script src="../script/jquery.min.js"></script>
<script src="../script/bootstrap.min.js"></script>
<script src="../script/dashboard.js"></script>

</body>
</html>

