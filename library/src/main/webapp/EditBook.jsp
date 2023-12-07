<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Library</title>
	<link rel="stylesheet" href="style.css" />
</head>
<body>
	<div >
	  <div class="row">
       	<h1>Book</h1>
       	<button id="btn-list"><a href="<%request.getContextPath();%>list">List all books</a></button>
      </div>
      <div>
        <c:if test="${book!=null}"><form action="<%request.getContextPath();%>update" method="post">
        	<table>
          		<caption>
            		<h2>Edit book</h2>
          		</caption>
          			<c:if test="${book!=null}"><input type="hidden" name="id" value="<c:out value='${book.id}'/>" /></c:if>
          		<tr>
          			<th>Title:</th>
          			<td><input type="text" name="title" value="<c:out value='${book.title}'/>" /></td>
          		</tr>
          		<tr>
          			<th>Author:</th>
          			<td><input type="text" name="author" value="<c:out value='${book.author}'/>" /></td>
          		</tr>
          		<tr>
          			<th>Category:</th>
          			<td><input type="text" name="category" value="<c:out value='${book.category}'/>" /></td>
          		</tr>
          		<tr>
          			<th>Quantity:</th>
          			<td><input type="number" name="quantity" value="<c:out value='${book.quantity}'/>" /></td>
          		</tr>
          		<tr><td><input type="submit" value="Save"/></td></tr>
        	</table>
        </form></c:if>
        <c:if test="${book==null}"><form action="<%request.getContextPath();%>insert" method="post">
        	<table>
          		<caption>
            		<h2>
          	  			Add new book
            		</h2>
          		</caption>
          			<c:if test="${book!=null}"><input type="hidden" name="id" value="<c:out value='${book.id}'/>" /></c:if>
          		<tr>
          			<th>Title:</th>
          			<td><input type="text" name="title" value="<c:out value='${book.title}'/>" /></td>
          		</tr>
          		<tr>
          			<th>Author:</th>
          			<td><input type="text" name="author" value="<c:out value='${book.author}'/>" /></td>
          		</tr>
          		<tr>
          			<th>Category:</th>
          			<td><input type="text" name="category" value="<c:out value='${book.category}'/>" /></td>
          		</tr>
          		<tr>
          			<th>Quantity:</th>
          			<td><input type="number" name="quantity" value="<c:out value='${book.quantity}'/>" /></td>
          		</tr>
          		<tr><td><input type="submit" value="Save"/></td></tr>
        	</table>
        </form></c:if>
      </div>
	</div>
</body>
</html>