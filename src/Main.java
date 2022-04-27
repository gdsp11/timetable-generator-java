import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    private static void makefromfile(ArrayList<Person> listToSetIn) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:\\Users\\Tudor\\Documents\\timetable-generator\\names.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
                if (line != null)
                    listToSetIn.add(new Person(line));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        WorkWeek crtWeek = new WorkWeek();
        ArrayList<Person> employees = new ArrayList<>();

        makefromfile(employees);

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
            System.out.println("e. Back.");

            String choice = _readString();
            int personChoiceInt = 0;
            if (_checkString(choice))
                personChoiceInt = Integer.parseInt(choice)-1;
            else if (choice.equals("e")) {
                ClearConsole();
                break;
            }

            if (_checkString(choice) && Integer.parseInt(choice) <= listToSetIn.size()+1) {
                /*
                    TODO:
                        - print days to choose from.
                        - print shifts for selected employee, followed by true or false
                        - when choosing a shift, the value will be switched
                 */
                ClearConsole();
                while (true) {
                    ClearConsole();
                    System.out.println(listToSetIn.get(personChoiceInt).GetName());
                    System.out.println("Choose a day:\n" +
                                        "1. Monday\n" +
                                        "2. Tuesday\n" +
                                        "3. Wednesday\n" +
                                        "4. Thursday\n" +
                                        "5. Friday\n" +
                                        "6. Saturday\n" +
                                        "7. Sunday\n" +
                                        "e. Back");
                    String day = _readString();
                    int dayChoiceInt = 0;
                    if (_checkString(day))
                        dayChoiceInt = Integer.parseInt(day);
                    else if (day.equals("e")){
                        _sleep(1);
                        ClearConsole();
                        break;
                    }
                    if (!_checkString(day) ||
                        !(Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 7) ||
                        day.isEmpty()) {
                        System.out.println("Invalid choice...");
                        _sleep(2);
                        ClearConsole();
                    }
                    else {
                        //TODO: print shifts for selected day
                        ClearConsole();
                        while (true) {
                            ClearConsole();
                            System.out.println(listToSetIn.get(personChoiceInt).GetName());
                            System.out.format("1. %24s%16s\n", "Morning shift",
                                    listToSetIn.get(personChoiceInt).ShowAvailability(
                                            DayName.DayFromInt(dayChoiceInt), Shift.MORNING));
                            System.out.format("2. %24s%16s\n", "Middle shift",
                                    listToSetIn.get(personChoiceInt).ShowAvailability(
                                            DayName.DayFromInt(dayChoiceInt), Shift.MIDDLE));
                            System.out.format("3. %24s%16s\n", "Evening shift",
                                    listToSetIn.get(personChoiceInt).ShowAvailability(
                                            DayName.DayFromInt(dayChoiceInt), Shift.EVENING));
                            System.out.println("e. Back.");

                            String shiftChoice = _readString();

                            if (shiftChoice.equals("e")) {
                                _sleep(1);
                                ClearConsole();
                                break;
                            }

                            switch (shiftChoice) {
                                case "1" -> listToSetIn.get(personChoiceInt).SwitchAvailability(
                                        DayName.DayFromInt(dayChoiceInt), Shift.MORNING);
                                case "2" -> listToSetIn.get(personChoiceInt).SwitchAvailability(
                                        DayName.DayFromInt(dayChoiceInt), Shift.MIDDLE);
                                case "3" -> listToSetIn.get(personChoiceInt).SwitchAvailability(
                                        DayName.DayFromInt(dayChoiceInt), Shift.EVENING);
                                default -> {
                                    System.out.println("Invalid choice...");
                                    _sleep(2);
                                }
                            }
                        }
                    }
                }

            }
            else {
                if (!choice.equals("e")) {
                    //if choice is neither a valid int nor 'e', then invalid
                    System.out.println("Invalid choice...");
                    _sleep(2);
                }
                else {
                    //if choice not valid int but is 'e', exit program
                    _sleep(1);
                    ClearConsole();
                    break;
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
            int count = 0;
            System.out.format("%2s%24s%32s\n", "Count", "Name", "ID");
            for (Person personFromList : listToPrint) {
                count++;

                //format the print into 3 columns for easier reading
                System.out.format("%3d%29s%47s\n", count, personFromList.GetName(), personFromList.GetID());
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
