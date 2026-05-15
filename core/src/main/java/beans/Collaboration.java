package beans;

public class Collaboration {
    private String collaborator;
    private int task;
    private int month;
    //hours expected defined by the manager
    private int expectedHours;
    private int effectiveHours;

    public Collaboration() {
    }

    public String getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(String collaborator) {
        this.collaborator = collaborator;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getExpectedHours() {
        return expectedHours;
    }

    public void setExpectedHours(int expectedHours) {
        this.expectedHours = expectedHours;
    }

    public int getEffectiveHours() {
        return effectiveHours;
    }

    public void setEffectiveHours(int effectiveHours) {
        this.effectiveHours = effectiveHours;
    }
}
