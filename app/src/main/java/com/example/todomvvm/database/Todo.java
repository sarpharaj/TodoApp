package com.example.todomvvm.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * A basic class representing a one-column todo_database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 * See the documentation for the full set of annotations.
 * https://developer.android.com/topic/libraries/architecture/room.html
 */

@Entity(tableName = "todo_table")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "title")
    private String mTitle;
    private String mDetail;
    private String mDate;
    //private  int priority;


    
    public Todo(@NonNull String title, String detail, String date) {
        this.mTitle = title;
        this.mDetail = detail;
        this.mDate = date;
        //this.priority = priority;
    }
    @Ignore
    public Todo(int id, String title, String detail){
        this.id = id;
        this.mTitle = title;
        this.mDetail = detail;
    }
    public String getTitle() {
        return this.mTitle;
    }
    public void setTitle(String mTitle) {this.mTitle = mTitle;}

    public String getDetail() {
        return this.mDetail;
    }
    public void setDetail(String mDetail) {
        this.mDetail = mDetail;
    }

    public String getDate() {return this.mDate;}
    public void setDate(String mDate){this.mDate = mDate;}

 /*   public int getPriority() {return  this.priority;}
    public void setPriority(int priority){this.priority = priority;}*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
