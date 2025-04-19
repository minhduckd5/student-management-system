<%-- 
    Document   : course-form
    Created on : Apr 17, 2025, 12:29:46?PM
    Author     : Admin
--%>

<%@ include file="header.jsp" %>

<c:choose>
    <c:when test="${course != null}">
        <h2>Edit Course</h2>
        <form action="${pageContext.request.contextPath}/course/update" method="post">
            <input type="hidden" name="id" value="${course.id}" />
    </c:when>
    <c:otherwise>
        <h2>Add New Course</h2>
        <form action="${pageContext.request.contextPath}/course/add" method="post">
    </c:otherwise>
</c:choose>

    <div class="form-group">
        <label for="code">Code:</label>
        <input type="text" id="code" name="code" value="${course.code}" required />
    </div>

    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${course.name}" required />
    </div>

    <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4">${course.description}</textarea>
    </div>

    <div class="form-group">
        <label for="credits">Credits:</label>
        <input type="number" id="credits" name="credits" value="${course.credits}" required />
    </div>

    <button type="submit" class="btn">Save</button>
    <a href="${pageContext.request.contextPath}/courses" class="btn">Cancel</a>
</form>

<%@ include file="footer.jsp" %>
