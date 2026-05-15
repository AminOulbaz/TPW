package dto;

import beans.Collaboration;
import beans.Task;

public class CollaborationDetailed {
    private Collaboration collaboration;
    private Task task;

    public CollaborationDetailed() {
    }

    public Collaboration getCollaboration() {
        return collaboration;
    }

    public void setCollaboration(Collaboration collaboration) {
        this.collaboration = collaboration;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
