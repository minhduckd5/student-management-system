/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.university.dao;

import com.university.model.Student;
import com.university.model.Course;
import com.university.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

//          String sql =
//            "SELECT "
//          + "  s.id, "
//          + "  s.name, "
//          + "  s.email, "
//          + "  s.registration_date, "
//          + "  c.id       AS course_id, "
//          + "  c.code     AS course_code, "
//          + "  c.name     AS course_name, "
//          + "  c.description, "
//          + "  c.credits "
//          + "FROM students s "
//          + "LEFT JOIN courses c "
//          + "  ON s.course = c.name"; 

            String sql = "SELECT s.*, " +
             "c.id AS cid, c.code, c.name AS course_name, c.description, c.credits " +
             "FROM students s LEFT JOIN courses c ON s.course_id = c.id";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
//                student.setCourse(rs.getString("course"));

//                int cid = rs.getInt("course_id");
//                if (cid > 0) {
//                    Course c = new Course(
//                        cid,
//                        rs.getString("course_code"),
//                        rs.getString("course_name"),
//                        rs.getString("description"),
//                        rs.getInt("credits")
//                    );
//                    student.setCourse(c);
//                } else {
//                    student.setCourse(null);
//                }

                int courseId = rs.getInt("cid");
                if (courseId > 0) {
                    Course course = new Course(
                        courseId,
                        rs.getString("code"),
                        rs.getString("course_name"),
                        rs.getString("description"),
                        rs.getInt("credits")
                    );
                    student.setCourse(course);
                } else {
                    student.setCourse(null);
                }

                // Convert SQL timestamp to LocalDateTime
                if (rs.getTimestamp("registration_date") != null) {
                    student.setRegistrationDate(rs.getTimestamp("registration_date").toLocalDateTime());
                }

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Get student by ID
    public Student getStudentById(int id) {
//            String sql =
//                "SELECT "
//              + "  s.id, "
//              + "  s.name, "
//              + "  s.email, "
//              + "  s.registration_date, "
//              + "  c.id       AS course_id, "
//              + "  c.code     AS course_code, "
//              + "  c.name     AS course_name, "
//              + "  c.description, "
//              + "  c.credits "
//              + "FROM students s "
//              + "LEFT JOIN courses c "
//              + "  ON s.course = c.name "
//              + "WHERE s.id = ?";
            String sql = "SELECT s.*, " +
             "c.id AS cid, c.code, c.name AS course_name, c.description, c.credits " +
             "FROM students s LEFT JOIN courses c ON s.course_id = c.id where s.id = ?";
        Student student = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setEmail(rs.getString("email"));

//                    int cid = rs.getInt("course_id");
//                    if (cid > 0) {
//                        Course c = new Course(
//                            cid,
//                            rs.getString("course_code"),
//                            rs.getString("course_name"),
//                            rs.getString("description"),
//                            rs.getInt("credits")
//                        );
//                        student.setCourse(c);
//                    } else {
//                        student.setCourse(null);
//                    }

                        int courseId = rs.getInt("cid");
                        if (courseId > 0) {
                            Course course = new Course(
                                courseId,
                                rs.getString("code"),
                                rs.getString("course_name"),
                                rs.getString("description"),
                                rs.getInt("credits")
                            );
                            student.setCourse(course);
                        } else {
                            student.setCourse(null);
                        }

                    // Convert SQL timestamp to LocalDateTime
                    if (rs.getTimestamp("registration_date") != null) {
                        student.setRegistrationDate(rs.getTimestamp("registration_date").toLocalDateTime());
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    // Add a new student
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, course, course_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
//            pstmt.setString(3, student.getCourse());
            pstmt.setString(3, student.getCourse().getName());
            pstmt.setInt(4, student.getCourse().getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing student
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, course = ?, course_id = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
//            pstmt.setString(3, student.getCourse());
            pstmt.setString(3, student.getCourse().getName());
            
            pstmt.setInt(4, student.getCourse().getId());
            pstmt.setInt(5, student.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a student
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
