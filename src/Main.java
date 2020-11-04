import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        FileHandling fh = new FileHandling();
        Main mn =new Main();
        System.out.println("Welcome TO Adress Book");
        System.out.println("======================");
        System.out.println("Enter 1 : Create New AddressBook " + "\nEnter 2 : Open AddressBook"  + "\nEnter 3 : Exit");

        Scanner AddBookInput = new Scanner(System.in);
        String BookInput =AddBookInput.nextLine();
        int abi = Integer.parseInt(BookInput);

        switch (abi){
            case 1:
                fh.FileCreate();
                mn.ADB(fh.fileName);
                break;
            case 2:
                fh.FileOpen();
                mn.ADB(fh.fileName);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Wrong Input !!! Try Again");
                main(null);
        }
    }
    public void ADB(String fileName){

        AddressBook ab = new AddressBook(fileName);
        String input, s;
        int cs;

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter 1 : Add Contact" + "\nEnter 2 : Modify Contact" + "\nEnter 3 : Delete Contact" + "\nEnter 4 : Back to Main Menu" + "\nEnter 5 : Exit");
            input = in.nextLine();
            cs = Integer.parseInt(input);

            switch (cs){
                case 1: ab.addPerson(); break;
                case 2: ab.modifyPerson(); break;
                case 3: ab.deletePerson(); break;
                case 4: main(null); break;
                case 5: System.exit(0);
                default:
                    System.out.println("Wrong Input : Try Again");
                    Main mn = new Main();
                    System.out.println(fileName);
                    mn.ADB(fileName);
            }
        }
    }
}
