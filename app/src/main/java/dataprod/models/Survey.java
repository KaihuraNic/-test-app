package dataprod.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Survey")
public class Survey {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int Id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "status")
    public int status;

    @ColumnInfo(name = "syncStatus")
    private int syncStatus;

    public Survey(int Id, String title, int status, int syncStatus) {
        Id = Id;
        this.title = title;
        this.status = status;
        this.syncStatus = syncStatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }
}
