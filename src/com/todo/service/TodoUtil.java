package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("추가하고싶은 항목의 카테고리: ");
		category = sc.next();
		
		System.out.print("추가하고싶은 항목의 제목: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("중복된 제목입니다!");
			return;
		}
		sc.nextLine();
		System.out.print("추가하고싶은 항목의 내용: ");
		desc = sc.nextLine().trim();
		
		System.out.print("추가하고싶은 항목의 마감일자: ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("지우고싶은 항목의 번호: ");
		
		int want_del_number = sc.nextInt();
		int number = 1;
		
		l.one_list(want_del_number);
		
		System.out.print("위 항목을 삭제하시겠습니까? (y/n)");
		String yes_or_no = sc.next();
		
		if (yes_or_no.equals("y")) {
			for (TodoItem item : l.getList()) {
				if (number == want_del_number) {
					l.deleteItem(item);
					break;
				}
				number++;
			}
		}
		
		System.out.println("항목이 삭제되었습니다!!");
	}


	public static void updateItem(TodoList l) {
		
		System.out.print("수정을 원하시는 항목의 번호: ");

		Scanner sc = new Scanner(System.in);
		
		int want_edit_num = sc.nextInt();
		int number = 1;
		
		l.one_list(want_edit_num);

		System.out.print("항목의 새로운 제목: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다!");
			return;
		}
		
		System.out.print("항목의 새로운 카테고리: ");
		String new_category = sc.next().trim();
		
		sc.nextLine();
		System.out.print("항목의 새로운 내용: ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("항목의 새로운 마감일자: ");
		String new_due_date = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (want_edit_num == number) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);
				System.out.println("항목이 업데이트 되었습니다!");
			}
			number++;
		}

	}

	public static void listAll(TodoList l) {
		int totalNumber = 0;
		int number = 1;
		List list = new ArrayList();
		list = l.getList();
		
		if (list.isEmpty())
			System.out.println("TodoList가 존재하지 않습니다!");
		
		for (TodoItem item : l.getList()) {
			totalNumber++;
		}
		
		System.out.println("[전체 목록, 총 " + totalNumber + "개]");
		
		for (TodoItem item : l.getList()) {
			System.out.println(number + ". " + "[" + item.getCategory() + "]   " + item.getTitle() + "  -  " + item.getDesc() + "  -  " +item.getDue_date() + "  -  " + item.getCurrent_date());
			number++;
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
				String[] buffer = new String[5];
				buffer[0] = st.nextToken();
				buffer[1] = st.nextToken();
				buffer[2] = st.nextToken();
				buffer[3] = st.nextToken();
				buffer[4] = st.nextToken();
				TodoItem item = new TodoItem(buffer[0], buffer[1], buffer[2], buffer[3], buffer[4]);
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
	
	public static void findList(TodoList l, String want_find) {
		
		int number = 1;
		int totalNumber = 0;
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(want_find) || item.getDesc().contains(want_find)) {
				l.one_list(number);
				totalNumber++;
			}
			number++;
		}
		
		System.out.println("총 " + totalNumber + "개의 항목을 찾았습니다!");
		
	}
}
