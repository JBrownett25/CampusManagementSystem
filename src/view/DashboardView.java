package view;

import controller.MainController;
import javax.swing.*;

public class DashboardView extends JFrame {

    private MainController controller;

    public DashboardView(MainController controller) {
        this.controller = controller;
        initializeDashboard();
    }

    private void initializeDashboard() {
        setTitle("Smart Campus Management System Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centre the window on the screen

        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each module, passing the shared controller.
        StudentManagementPanel studentPanel = new StudentManagementPanel(controller);
        RoomSchedulingPanel roomPanel = new RoomSchedulingPanel(controller);
        ResourceManagementPanel resourcePanel = new ResourceManagementPanel(controller);

        tabbedPane.addTab("Student Management", studentPanel);
        tabbedPane.addTab("Room Scheduling", roomPanel);
        tabbedPane.addTab("Resource Management", resourcePanel);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainController controller = new MainController();
            DashboardView dashboard = new DashboardView(controller);
            dashboard.setVisible(true);
        });
    }
}
