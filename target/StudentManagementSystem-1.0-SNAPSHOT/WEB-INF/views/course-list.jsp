<%-- 
    Document   : course-list.jsp
    Created on : Apr 17, 2025, 12:29:14?PM
    Author     : Admin
--%>

<%@ include file="header.jsp" %>
<h2>Course List</h2>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Name</th>
            <th>Description</th>
            <th>Credits</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.id}</td>
                <td>${course.code}</td>
                <td>${course.name}</td>
                <td>${course.description}</td>
                <td>${course.credits}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/courses?action=view&id=${course.id}" class="btn">View</a>
                    <a href="${pageContext.request.contextPath}/courses?action=edit&id=${course.id}" class="btn">Edit</a>
                    <a href="${pageContext.request.contextPath}/courses?action=delete&id=${course.id}" class="btn" onclick="return confirm('Are you sure you want to delete this course?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="${pageContext.request.contextPath}/courses?action=new" class="btn">Add New Course</a>
<%@ include file="footer.jsp" %>
