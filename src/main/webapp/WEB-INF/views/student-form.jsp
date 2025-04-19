<%-- 
    Document   : student-form
    Created on : Apr 17, 2025, 11:29:36?AM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<c:if test="${student != null}">
    <h2>Edit Student</h2>
    <form action="${pageContext.request.contextPath}/student/update" method="post">
        <input type="hidden" name="id" value="${student.id}" />
</c:if>

<c:if test="${student == null}">
    <h2>Add New Student</h2>
    <form action="${pageContext.request.contextPath}/student/add" method="post">
</c:if>

    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${student.name}" required />
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${student.email}" required />
    </div>

<!--    <div class="form-group">
        <label for="course">Course:</label>
        <select id="course" name="course" required>
            <option value="">-- Select Course --</option>
            <option value="Computer Science" ${student.course == 'Computer Science' ? 'selected' : ''}>Computer Science</option>
            <option value="Information Technology" ${student.course == 'Information Technology' ? 'selected' : ''}>Information Technology</option>
            <option value="Software Engineering" ${student.course == 'Software Engineering' ? 'selected' : ''}>Software Engineering</option>
            <option value="Data Science" ${student.course == 'Data Science' ? 'selected' : ''}>Data Science</option>
            <option value="Cybersecurity" ${student.course == 'Cybersecurity' ? 'selected' : ''}>Cybersecurity</option>
        </select>
    </div>-->
    <c:set var="courseList" value="${requestScope.courseList}" />
    <div class="form-group">
      <label for="course">Course:</label>
      <select id="course" name="course" required>
        <option value="">-- Select Course --</option>
        <c:forEach var="c" items="${courseList}">
          <!--<option value="${c.id}"-->
             <!--  ${student.course != null && student.course.id == c.id ? 'selected' : ''}> -->
            <option value="${c.name}" ${student.course.name == c.name ? 'selected' : ''}>
            ${c.code} - ${c.name}
          </option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn">Save</button>
    <a href="${pageContext.request.contextPath}/students" class="btn">Cancel</a>
</form>

<%@ include file="footer.jsp" %>
