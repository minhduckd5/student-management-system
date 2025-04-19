<%-- 
    Document   : student-view
    Created on : Apr 17, 2025, 11:31:00?AM
    Author     : Admin
--%>

<%@ include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Student Details</h2>

<c:if test="${student != null}">
    <div style="background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
        <div style="margin-bottom: 15px;">
            <strong>ID:</strong> ${student.id}
        </div>
        <div style="margin-bottom: 15px;">
            <strong>Name:</strong> ${student.name}
        </div>
        <div style="margin-bottom: 15px;">
            <strong>Email:</strong> ${student.email}
        </div>
        <div style="margin-bottom: 15px;">
            <strong>Course:</strong> 
            <c:choose>
            <c:when test="${not empty student.course}">
               ${student.course.id} - ${student.course.code} - ${student.course.name}
            </c:when>
            <c:otherwise>
              <span style="color: gray;">No Course Assigned</span>
            </c:otherwise>
          </c:choose>
        </div>
        <div style="margin-bottom: 15px;">
            <strong>Registration Date:</strong>
            <fmt:parseDate value="${student.registrationDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
            <fmt:formatDate value="${parsedDate}" type="both" pattern="MMMM d, yyyy h:mm a" />
        </div>
    </div>

    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/student/edit?id=${student.id}" class="btn">Edit</a>
        <a href="${pageContext.request.contextPath}/students" class="btn">Back to List</a>
    </div>
</c:if>

<c:if test="${student == null}">
    <div style="background: #ffe6e6; padding: 20px; border-radius: 5px;">
        <p>Student not found.</p>
    </div>
    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/students" class="btn">Back to List</a>
    </div>
</c:if>

<%@ include file="footer.jsp" %>