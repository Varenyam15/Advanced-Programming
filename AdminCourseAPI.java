package edu.univ.erp.api.admin;

import edu.univ.erp.auth.UserSession;
import edu.univ.erp.domain.Course;
import edu.univ.erp.service.AdminService;

import java.util.List;


public class AdminCourseAPI {

    private final AdminService adminService;

    public AdminCourseAPI() {
        this.adminService = new AdminService();
    }

    private void requireAdmin() {
        if (!UserSession.isLoggedIn() || !"ADMIN".equals(UserSession.getRole())) {
            throw new SecurityException("Access denied: ADMIN role required.");
        }
    }

    public boolean createCourse(String courseId, String title, String department, int credits) {
        requireAdmin();
        Course c = new Course(courseId, title, department, credits);
        return adminService.addCourse(c);
    }

    public boolean updateCourse(String courseId, String title, String department, int credits) {
        requireAdmin();
        Course c = adminService.getCourseById(courseId);
        if (c == null) return false;

        c.setCourseName(title);
        c.setDepartment(department);
        c.setCredits(credits);

        return adminService.updateCourse(c);
    }

    public boolean deleteCourse(String courseId) {
        requireAdmin();
        return adminService.deleteCourse(courseId);
    }

    public Course getCourse(String courseId) {
        requireAdmin();
        return adminService.getCourseById(courseId);
    }

    public List<Course> getAllCourses() {
        requireAdmin();
        return adminService.getAllCourses();
    }
}


