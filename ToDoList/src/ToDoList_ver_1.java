import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Евгений on 22.06.2017.
 */
public class ToDoList_ver_1 {
    public static void main(String[] args) throws IOException {


        System.out.println("Добро пожаловать!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList();

        System.out.println("Что Вы хотите сделать?");
        System.out.println("1 - Добавить задание в список");
        System.out.println("2 - Просмотреть текущий список заданий");
        System.out.println("3 - Выход");;

        int n = 0;

        while(n != 1 || n != 2 || n != 3) {
            n = Integer.parseInt(br.readLine());
            if (n == 1) {
                addTask(list);
                System.out.println("Вывести полученный список на экран?");
                System.out.println("2 - Да");
                System.out.println("3 - Нет");
            }
            else if (n == 2) {
                showList(list);
                System.out.println("Нажмите 3, чтобы выйти.");
            }
            else if (n == 3){
                System.out.println("Приятно было с Вами поработать, до новых встреч!");
                break;
            }
            else System.out.println("Введите корректное значение");
        }


    }

    private static void showList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++){
            System.out.println(i + 1 + " " + list.get(i));
        }

    }

    public static void addTask(ArrayList<String> list) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean a = false;
        String s;
        while (!a) {
            System.out.println("Я готов записать Ваше задание.");
            list.add(br.readLine());
            System.out.println("Еще задание? ;)");
            s = br.readLine();
            if (s.equals("нет") || s.equals("Нет") || s.equals("НЕТ") || s.equals("нЕТ")) a = true;
        }
    }


}

