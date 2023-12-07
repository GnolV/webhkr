<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Library</title>
	<link rel="stylesheet" href="/style.css" />
</head>
<body>
	<div class="container">
      <div class="row">
        <h1>Books List</h1>
        <button id="btn-list"><a href="<%request.getContextPath();%>list">List all books</a></button>
      </div>
      <table id="myTable" border="1px solid black" border-collapse="collapse" margin-left="auto" margin-right="auto" >
        <thead>
          <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Quantity</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="book" items="${listBook}">
          	<tr>
          	  <td><c:out value="${book.id}"/></td>
          	  <td><c:out value="${book.title}"/></td>
          	  <td><c:out value="${book.author}"/></td>
          	  <td><c:out value="${book.category}"/></td>
          	  <td><c:out value="${book.quantity}"/></td>
          	<td>
          		<button id='btn-edit'><a href="edit?id=<c:out value='${book.id}' />">Edit</a></button>
          		<button id='btn-delete'><a href="delete?id=<c:out value='${book.id}' />">Delete</a></button>
          	</td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      <div>
        <button id="btn-add"><a href="<%request.getContextPath();%>new">Add</a></button>
      </div>
    </div>
</body>
</html>