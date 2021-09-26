package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		String want_find;
		
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("제목순으로 정렬하였습니다!");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목역순으로 정렬하였습니다!");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜순으로 정렬하였습니다!");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜역순으로 정렬하였습니다!");
				isList = true;
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;
				
			case "save":
				TodoUtil.saveList(l, "todolist.txt");
				break;
				
			case "load":
				TodoUtil.loadList(l, "todolist.txt");
				break;
				
			case "find":
				want_find = sc.next();
				TodoUtil.findList(l, want_find);
				break;
				
			case "find_cate":
				want_find = sc.next();
				TodoUtil.findCate(l,want_find);
				break;
				
			case "help":
				Menu.displaymenu();
				isList = true;
				break;

			case "exit":
				System.out.println("TodoList App의 모든 데이터가 저장되었습니다!");
				TodoUtil.saveList(l, "todolist.txt");
				quit = true;
				break;

			default:
				System.out.println("언급된 커맨드만 사용해주세요! (도움말 커맨드 - 'help')");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
