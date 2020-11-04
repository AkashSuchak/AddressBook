import java.io.*;
import java.util.*;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddressBook{
    String fileName;
    public AddressBook(String fileName){
        this.fileName =fileName;
    }

    public void addPerson(){
        JSONObject jo = new JSONObject();
        JSONArray jsonArray = null;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter First Name : ");
        String fn = input.nextLine();
        jo.put("FirstName", fn);

        System.out.println("Enter Last Name : ");
        String ln = input.nextLine();
        jo.put("LastName", ln);

        System.out.println("Enter Address : ");
        String add = input.nextLine();
        jo.put("Address", add);

        System.out.println("Enter City : ");
        String ct = input.nextLine();
        jo.put("City", ct);

        System.out.println("Enter State : ");
        String st = input.nextLine();
        jo.put("State", st);

        System.out.println("Enter Phone Number : ");
        String pn = input.nextLine();
        jo.put("Phone", pn);

        System.out.println("Enter ZIPCODE : ");
        String zc = input.nextLine();
        jo.put("Zipcode", zc);

        Information info = new Information(fn, ln, add, ct, st, pn, zc);
        info.print();

        try {
            File file = new File("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\"+this.fileName+".json");
            if(file.exists()){
                FileReader fileReader = new FileReader(file);
                int i;
                StringBuilder str= new StringBuilder();
                while ((i=fileReader.read()) != -1)
                    str.append((char) i);
                if(str != null && str.length() > 0) {
                       jsonArray = (JSONArray) new JSONParser().parse(str.toString());
                }
            }
            if(jsonArray == null) {
                jsonArray = new JSONArray(); 
            }
            jsonArray.add(jo);

            JSONArray sortedJsonArray = new JSONArray();
            List list = new ArrayList();
            for(int i = 0; i < jsonArray.size(); i++) {
                list.add(jsonArray.get(i));
            }

            Collections.sort(list,
                    new Comparator() {
                        private static final String KEY_NAME = "LastName";

                        @Override
                        public int compare(Object a, Object b) {
                            String str1 = new String();
                            String str2 = new String();
                            try {
                                str1 = (String) ((JSONObject)a).get(KEY_NAME);
                                str2 = (String) ((JSONObject)b).get(KEY_NAME);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return str1.compareTo(str2);

                        }
                    });
            for(int i = 0; i < jsonArray.size(); i++) {
                sortedJsonArray.add(list.get(i));
            }

            FileWriter fw = new FileWriter("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\"+this.fileName+".json");
            fw.write(sortedJsonArray.toJSONString());
            fw.close();
            System.out.println("Information Added : "+jo);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    //Delete ContactPerson from AddressBook
    public void deletePerson() {
        try {
            System.out.print("Enter LastName For DELETE Data : ");
            Scanner scanner = new Scanner(System.in);
            String lastName = scanner.nextLine();

            Object obj = new JSONParser().parse(new FileReader("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\" + this.fileName + ".json"));
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject;
            JSONArray updatedJsonArray = new JSONArray();

            List list = new ArrayList();
            List list1 = new ArrayList();
            List dataList = new ArrayList();
            int count = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                list.add(jsonArray.get(i));
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                String lName = (String) (jsonObject).get("LastName");

                if (lastName.equals(lName)) {
                    count += 1;
                    list1.add(i);
                    dataList.add(jsonArray.get(i));
                }
            }
            if (count > 1) {
                System.out.println("----------------------------------------------------");
                System.out.println("Multiple Data Found");
                System.out.println(dataList);
                System.out.println("----------------------------------------------------");
                System.out.println("Enter First Name from the Above data for Delete : ");
                Scanner scanner2 = new Scanner(System.in);
                String firstName = scanner2.nextLine();
                list1.clear();
                dataList.clear();
                for (int d = 0; d < list.size(); d++) {
                    jsonObject = (JSONObject) list.get(d);
                    String firstName1 = (String) (jsonObject).get("FirstName");
                    String lastName1 = (String) (jsonObject).get("LastName");

                    if (firstName.equals(firstName1) && lastName.equals(lastName1)) {
                        list1.add(d);
                        dataList.add(list.get(d));
                    }
                }

                System.out.println("Do You Want to Delete this Data ?");
                System.out.println(dataList);
                System.out.println("----------------------------------------------------");
                System.out.println("Press y to delete ");
                System.out.println("Press n to cancel ");
                Scanner scanner1 = new Scanner(System.in);
                String cnfrm = scanner1.nextLine();
                if (cnfrm.equals("y") || cnfrm.equals("Y")) {
                    for (int j = 0; j < list1.size(); j++) {
                        int number = (int) list1.get(j);
                        list.remove(number);
                    }
                    System.out.println("Data Deleted Successfully");
                    System.out.println("----------------------------------------------------");
                } else if (cnfrm == "n" || cnfrm == "N") {
                    Main mn = new Main();
                    mn.ADB(this.fileName);
                }

                //System.out.println(list1);

            } else if (count == 1) {
                System.out.println("Do You Want to Delete this Data ?");
                System.out.println(dataList);
                System.out.println("----------------------------------------------------");
                System.out.println("Press y to delete ");
                System.out.println("Press n to cancel ");
                Scanner scanner1 = new Scanner(System.in);
                String cnfrm = scanner1.nextLine();
                if (cnfrm.equals("y") || cnfrm.equals("Y")) {
                    for (int j = 0; j < list1.size(); j++) {
                        int number = (int) list1.get(j);
                        list.remove(number);
                    }
                    System.out.println("Data Deleted Successfully");
                } else if (cnfrm == "n" || cnfrm == "N") {
                    Main mn = new Main();
                    mn.ADB(this.fileName);
                }
            } else if (count == 0){
                System.out.println("Not Found");
            }

            for (int k = 0; k < list.size(); k++) {
                updatedJsonArray.add(list.get(k));
            }

            FileWriter fw = new FileWriter("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\" + this.fileName + ".json");
            fw.write(updatedJsonArray.toJSONString());
            fw.close();

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void modifyPerson(){
        System.out.print("\nEnter FirstName to Update Data : ");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        try {
            Object obj = new JSONParser().parse(new FileReader("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\" + this.fileName + ".json"));

            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject = null;
            JSONArray updatedJsonArray = new JSONArray();
            JSONObject jsonObject1 =new JSONObject();


            List list = new ArrayList();
            List list1 = new ArrayList();
            List dataList = new ArrayList();
            int count = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                list.add(jsonArray.get(i));
            }

            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                String firstName1 = (String) (jsonObject).get("FirstName");

                if (firstName.equals(firstName1)) {
                    count += 1;
                    list1.add(i);
                    dataList.add(jsonArray.get(i));
                    jsonObject1 = (JSONObject) jsonArray.get(i);
                }
            }
            if (count == 1) {
                System.out.println("Do You Want to Update from Following Data ?");
                System.out.println(dataList);
                System.out.println("----------------------------------------------------");
                System.out.println("Press y to Update ");
                System.out.println("Press n to cancel ");
                Scanner scanner1 = new Scanner(System.in);
                String cnfrm = scanner1.nextLine();

                if (cnfrm.equals("y") || cnfrm.equals("Y")) {
                    System.out.println("What you want to update ? ");
                    System.out.println("LASTNAME, ADDRESS, CITY, STATE, ZIPCODE, PHONENUMBER ");
                    Scanner scanner2 = new Scanner(System.in);
                    String data = scanner2.nextLine();

                    switch (data){
                        case "LASTNAME":
                            System.out.println("Enter Last Name : ");
                            String ln = scanner2.nextLine();
                            jsonObject1.put("LastName",ln);
                            break;
                        case "ADDRESS":
                            System.out.println("Enter Address : ");
                            String add = scanner2.nextLine();
                            jsonObject1.put("Address",add);
                            break;
                        case "CITY":
                            System.out.println("Enter City : ");
                            String ct = scanner2.nextLine();
                            jsonObject1.put("City",ct);
                            break;
                        case "STATE":
                            System.out.println("Enter State : ");
                            String st = scanner2.nextLine();
                            jsonObject1.put("State",st);
                            break;
                        case "PHONENUMBER":
                            System.out.println("Enter Phone Number : ");
                            String pn = scanner2.nextLine();
                            jsonObject1.put("Phone",pn);
                            break;
                        case "ZIPCODE":
                            System.out.println("Enter ZIPCODE : ");
                            String zc = scanner2.nextLine();
                            jsonObject1.put("Zipcode",zc);
                            break;
                        default:
                            System.out.println("WrongInput ");
                            break;
                    }
                    System.out.println("Data Updated Successfully");
                } else if (cnfrm == "n" || cnfrm == "N") {
                    Main mn = new Main();
                    mn.ADB(this.fileName);
                }
            }
            for (int k = 0; k < list.size(); k++) {
                updatedJsonArray.add(list.get(k));
            }
            FileWriter fw = new FileWriter("C:\\Users\\Akash\\Desktop\\codinclub\\AddressBook\\" + this.fileName + ".json");
            fw.write(updatedJsonArray.toJSONString());
            fw.close();

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}