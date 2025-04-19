package com.university.controller;

import com.university.dao.CourseDAO;
import com.university.model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseController", urlPatterns = {"/courses", "/course/*"})
public class CourseController extends HttpServlet {
    private CourseDAO dao;

    @Override
    public void init() {
        dao = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        String action = req.getParameter("action");

        if ((path == null || "/".equals(path)) && action == null) {
            list(req, resp);
        } else if ("new".equals(action) || "/new".equals(path)) {
            showForm(req, resp, null);
        } else if ("edit".equals(action) || "/edit".equals(path)) {
            showForm(req, resp, req.getParameter("id"));
        } else if ("delete".equals(action) || "/delete".equals(path)) {
            delete(req, resp);
        } else if ("view".equals(action) || "/view".equals(path)) {
            view(req, resp);
        } else {
            list(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        String action = req.getParameter("action");

        if ("add".equals(action) || "/add".equals(path)) {
            add(req, resp);
        } else if ("update".equals(action) || "/update".equals(path)) {
            update(req, resp);
        } else {
            list(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Course> courses = dao.getAllCourses();
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/WEB-INF/views/course-list.jsp")
           .forward(req, resp);
    }

    private void showForm(HttpServletRequest req, HttpServletResponse resp, String id)
            throws ServletException, IOException {
        if (id != null) {
            Course c = dao.getCourseById(Integer.parseInt(id));
            req.setAttribute("course", c);
        }
        req.getRequestDispatcher("/WEB-INF/views/course-form.jsp")
           .forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Course c = new Course();
        c.setCode(req.getParameter("code"));
        c.setName(req.getParameter("name"));
        c.setDescription(req.getParameter("description"));
        c.setCredits(Integer.parseInt(req.getParameter("credits")));
        dao.addCourse(c);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Course c = new Course();
        c.setId(Integer.parseInt(req.getParameter("id")));
        c.setCode(req.getParameter("code"));
        c.setName(req.getParameter("name"));
        c.setDescription(req.getParameter("description"));
        c.setCredits(Integer.parseInt(req.getParameter("credits")));
        dao.updateCourse(c);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        dao.deleteCourse(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    private void view(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Course c = dao.getCourseById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("course", c);
        req.getRequestDispatcher("/WEB-INF/views/course-view.jsp")
           .forward(req, resp);
    }
}