package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("추가하고싶은 항목의 제목: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("중복된 제목입니다!");
			return;
		}
		sc.nextLine();
		System.out.print("추가하고싶은 항목의 내용: ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("지우고싶은 항목의 제목: ");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		System.out.print("수정을 원하시는 항목의 제목: ");

		Scanner sc = new Scanner(System.in);
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("제목이 존재하지 않습니다!");
			return;
		}

		System.out.print("항목의 새로운 제목: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다!");
			return;
		}
		
		sc.nextLine();
		System.out.print("항목의 새로운 내용: ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 업데이트 되었습니다!");
			}
		}

	}

	public static void listAll(TodoList l) {
		List list = new ArrayList();
		list = l.getList();
		if (list.isEmpty())
			System.out.println("TodoList가 존재하지 않습니다!");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "]" + "  " + item.getDesc() + "   " + item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter("todolist.txt");
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("TodoList 저장!!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			
			int number = 0;
			String oneLine;
			while ((oneLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneLine, "##");
				String[] buffer = new String[3];
				buffer[0] = st.nextToken();
				buffer[1] = st.nextToken();
				buffer[2] = st.nextToken();
				TodoItem item = new TodoItem(buffer[0], buffer[1], buffer[2]);
				l.addItem(item);
				number++;
			}
			System.out.println(number + "개의 항목을 읽었습니다!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
