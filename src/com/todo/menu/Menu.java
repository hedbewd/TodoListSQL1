package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 항목을 추가하고 싶으시면 'add'를 입력하세요.");
        System.out.println("2. 항목을 지우고 싶으시면 'del를 입력하세요.");
        System.out.println("3. 항목을 수정하고 싶으시면 'edit'을 입력하세요.");
        System.out.println("4. 전체 항목을 보고 싶으시면 'ls'를 입력하세요.");
        System.out.println("5. 이름순으로 정렬하고 싶으시면 'ls_name_asc'를 입력하세요.");
        System.out.println("6. 이름역순으로 정렬하고 싶으시면 'ls_name_desc'를 입력하세요.");
        System.out.println("7. 날짜순으로 정렬하고 싶으시면 'ls_date'을 입력하세요.");
        System.out.println("8. 키워드를 통해 리스트를 찾고 싶으시면 'find'를 입력하세요.");
        System.out.println("9. 종료하고 싶으시면 'exit'을 누르시거나 exit 버튼을 누르세요.");
    }
    
    public static void prompt()
    {
    	System.out.print("Command: ");
    }
}
