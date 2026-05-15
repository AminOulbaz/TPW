package beans;

public class Task {
    private int id;
    private String title;
    private String description;
    private int workPackageId;
    //amplitude(endMonth-beginMonth) less or equal of the work package's period
    private int beginMonth;
    private int endMonth;
    private String orderedId;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWorkPackageId() {
        return workPackageId;
    }

    public void setWorkPackageId(int workPackageId) {
        this.workPackageId = workPackageId;
    }

    public int getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(int beginMonth) {
        this.beginMonth = beginMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public String getOrderedId() {
        return orderedId;
    }

    public void setOrderedId(String orderedId) {
        this.orderedId = orderedId;
    }
}
