package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());
	}

	public void listAll() {
		int number = 1;
		for (TodoItem myitem : list) {
			System.out.println(number + ". " + "[" + myitem.getCategory() + "]   " + myitem.getTitle() + "  -  " + myitem.getDesc() + "  -  " + myitem.getDue_date() + "  -  " + myitem.getCurrent_date());
			number++;
		}
	}
	
	public void one_list(int want_number) {
		int number = 1;
		for (TodoItem myitem : list) {
			if (want_number == number) {
				System.out.println(number + ". " + "[" + myitem.getCategory() + "]   " + myitem.getTitle() + "  -  " + myitem.getDesc() + "  -  " + myitem.getDue_date() + "  -  " + myitem.getCurrent_date());
			}
			number++;
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
