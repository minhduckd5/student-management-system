/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.controller;

import com.university.dao.StudentDAO;
import com.university.model.Student;
import com.university.dao.CourseDAO;
import com.university.model.Course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentController", urlPatterns = {"/students", "/student/*"})
public class StudentController extends HttpServlet {

    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    
    @Override
    public void init() {
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String action = request.getParameter("action");

        if (pathInfo == null && action == null) {
            // List all students
            listStudents(request, response);

        } else if (pathInfo != null && pathInfo.equals("/new")) {
            // Show form to add a new student
            showNewForm(request, response);

        } else if (pathInfo != null && pathInfo.equals("/edit")) {
            // Show form to edit an existing student
            showEditForm(request, response);

        } else if (pathInfo != null && pathInfo.equals("/delete")) {
            // Delete a student
            deleteStudent(request, response);

        } else if (pathInfo != null && pathInfo.equals("/view")) {
            // View a specific student
            viewStudent(request, response);

        } else {
            // Default - show all students
            listStudents(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.equals("/add")) {
            // Add a new student
            addStudent(request, response);

        } else if (pathInfo != null && pathInfo.equals("/update")) {
            // Update an existing student
            updateStudent(request, response);

        } else {
            // Default - show all students
            listStudents(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/views/student-list.jsp").forward(request, response);
    }

    private void viewStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/WEB-INF/views/student-view.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO courseDAO = new CourseDAO();
        List<Course> courseList = courseDAO.getAllCourses();
        request.setAttribute("courseList", courseList);
        request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id); 
        request.setAttribute("student", student);
//        int courseId = Integer.parseInt(request.getParameter("course_id"));
//        Course course = new CourseDAO().getCourseById(courseId);
        List<Course> courseList = courseDAO.getAllCourses();
        request.setAttribute("courseList",courseList);
        request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
//        String course = request.getParameter("course");

//        int courseId = Integer.parseInt(request.getParameter("course"));
//        Course course = new CourseDAO().getCourseById(courseId);
        String courseName = request.getParameter("course");

        // Reuse loaded courseList instead of hitting DB again
        List<Course> courseList = courseDAO.getAllCourses();  // already used for JSP too

        Course matchedCourse = null;
        for (Course c : courseList) {
            if (c.getName().equals(courseName)) {
                matchedCourse = c;
                break;
            }
        }
        if (matchedCourse == null) {
            throw new IllegalArgumentException("Invalied course: " + courseName);
        }
        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setEmail(email);
//        newStudent.setCourse(course);
        newStudent.setCourse(matchedCourse);

        studentDAO.addStudent(newStudent);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
//        String courseName = request.getParameter("course");
//        Course course = courseDAO.getCourseByName(courseName);

//        int courseId = Integer.parseInt(request.getParameter("course"));
//        Course course = new CourseDAO().getCourseById(courseId);

        String courseName = request.getParameter("course");
        // Reuse loaded courseList instead of hitting DB again
        List<Course> courseList = courseDAO.getAllCourses();  // already used for JSP too

        Course matchedCourse = null;
        for (Course c : courseList) {
            if (c.getName().equals(courseName)) {
                matchedCourse = c;
                break;
            }
        }
        if (matchedCourse == null) {
            throw new IllegalArgumentException("Invalid course: " + courseName);
        }
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
//        student.setCourse(course);
        student.setCourse(matchedCourse);

        studentDAO.updateStudent(student);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect(request.getContextPath() + "/students");
    }
}
