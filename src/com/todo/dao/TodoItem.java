package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
	private int id;
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;

	public TodoItem(String category, String title, String desc, String due_date){
		this.category = category;
        this.title = title;
        this.desc = desc;
        this.due_date = due_date;
        
        Date now = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.current_date = format1.format(now);
    }
	
	public TodoItem(String category, String title, String desc, String due_date, String current_date){
		this.category = category;
        this.title = title;
        this.desc = desc;
        this.due_date = due_date;
        this.current_date = current_date;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }
	
	public String toString() {
		return "[" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
	}
}
