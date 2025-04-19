<%-- 
    Document   : course-view
    Created on : Apr 17, 2025, 12:31:41?PM
    Author     : Admin
--%>

<%@ include file="header.jsp" %>

<h2>Course Details</h2>

<c:if test="${course != null}">
    <div style="background:#fff;padding:20px;border-radius:5px;box-shadow:0 0 10px rgba(0,0,0,0.1);">
        <div><strong>ID:</strong> ${course.id}</div>
        <div><strong>Code:</strong> ${course.code}</div>
        <div><strong>Name:</strong> ${course.name}</div>
        <div><strong>Description:</strong> ${course.description}</div>
        <div><strong>Credits:</strong> ${course.credits}</div>
    </div>
    <div style="margin-top:1rem;">
        <a href="${pageContext.request.contextPath}/course/edit?id=${course.id}" class="btn">Edit</a>
        <a href="${pageContext.request.contextPath}/courses" class="btn">Back to List</a>
    </div>
</c:if>

<c:if test="${course == null}">
    <p>Course not found.</p>
    <a href="${pageContext.request.contextPath}/courses" class="btn">Back to List</a>
</c:if>

<%@ include file="footer.jsp" %>
