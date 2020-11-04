import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHandling{
    public String fileName;
    public void FileCreate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name Of AddressBook : ");
        fileName = sc.nextLine();
        System.out.print("Your selected AddressBook is : " + fileName);

        try {
            File fObj = new File("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\"+fileName+".json");
            if (fObj.createNewFile()) {
                System.out.println("File created: " + fObj.getName());
            } else {
                System.out.println("File already exists.");
                System.out.println("Do You Want to Create New File ?");
                System.out.println("y or n");

                Scanner nf = new Scanner(System.in);
                String newFile = nf.next();

                if (newFile.equals("y")) {
                    FileHandling fh = new FileHandling();
                    fh.FileCreate();
                } else if (newFile.equals("n")) {
                    Main mn = new Main();
                    mn.main(null);
                } else {
                    System.out.println("Wrong Input !!!");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void FileOpen() {
        System.out.println("Enter File Name :");
        Scanner sc = new Scanner(System.in);
        fileName = sc.nextLine();

        File fObj = new File("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\" +fileName+".json");

        if (fObj.exists() && !fObj.isDirectory()){
            System.out.println("File Opened : " + fObj.getName());

        }else{
            System.out.println("File Does Not Exists or Wrong Input!!!");
            Main.main(null);
        }
    }
}