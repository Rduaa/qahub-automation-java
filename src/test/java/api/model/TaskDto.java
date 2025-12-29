package api.model;

public class TaskDto {
    public Integer id;
    public String title;

    public TaskDto() {}

    public TaskDto(String title) {
        this.title = title;
    }
}