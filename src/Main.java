import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        WorkWeek crtWeek = new WorkWeek();
        ArrayList<Person> employees = new ArrayList<>();

        while (true) {
            ClearConsole();
            System.out.println("Choose an option:");
            System.out.println("1. Show all employees.");
            System.out.println("2. Add an employee.");
            System.out.println("3. Create a timetable and print it.");
            System.out.println("4. Change availability for employees.");
            System.out.println("e. Exit.");

            String choice = _readString();

            switch (choice) {
                case "1":
                    PrintEmployees(employees);
                    _sleep(5);
                    break;
                case "2":
                    AddEmployee(employees);
                    _sleep(1);
                    break;
                case "3":
                    //MakeRandomTimetable(employees);
                    break;
                case "4":
                    SetAvailability(employees);
                    break;
                case "e":
                    System.exit(0);
                default:
                    System.out.println("Invalid choice...");
                    _sleep(3);
            }
        }
    }

    private static void SetAvailability(ArrayList<Person> listToSetIn) {
        while (true) {
            //print a menu of choices
            System.out.println("Choose an employee:");
            PrintEmployees(listToSetIn);
            System.out.println("e. Exit.");

            String choice = _readString();

            if (_checkString(choice) && Integer.parseInt(choice) <= listToSetIn.size()+1) {
                /*
                    TODO:
                        - print shifts for selected employee, followed by true or false
                        - when choosing a shift, the value will be switched
                 */
            }
            else {
                if (!choice.equals("e")) {
                    //if choice is neither a valid int nor 'e', then invalid
                    System.out.println("Invalid choice...");
                    _sleep(2);
                }
                else {
                    //if choice not valid int but is 'e', exit program
                    System.exit(0);
                }
            }

        }
    }

    //function to check if the given string is a number
    private static boolean _checkString(String stringToCheck) {
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (!(stringToCheck.charAt(i) >= '0' && stringToCheck.charAt(i) <= '9'))
                return false;
        }

        return true;
    }
    private static void PrintEmployees(ArrayList<Person> listToPrint) {
        if (listToPrint.size() == 0)
            System.out.println("No employees added so far...");
        else {
            for (Person personFromList : listToPrint) {
                System.out.println(personFromList.GetName() + " with ID " + personFromList.GetID());
            }
        }
    }
    private static void AddEmployee(ArrayList<Person> listToAddTo) {
        System.out.print("Name: ");

        listToAddTo.add(new Person(_readString()));
    }

    private static void ClearConsole(){
        for (int i = 0; i < 50; i++) {
            System.out.println("\r\b");
        }
    }

    private static void _sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String _readString() {
        String name;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            name = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return name;
    }
}
