package report;

public class ReportManager {
    private static ReportManager instance;

    private ReportManager() {
        // Private constructor
    }

    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }

    public void generateReport(String reportType) {
        System.out.println("Generating " + reportType + " report...");
    }
}
