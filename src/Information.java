public class Information {
    private String fname;
    private String lname;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String zipcode;

    //Setter Methods
    public void setFname(String sfname) {
        fname = sfname;
    }
    public void setLname(String slname) {
        lname = slname;
    }
    public void setAddress(String saddress) {
        address = saddress;
    }
    public void setCity(String scity) {
        city = scity;
    }
    public void setState(String sstate) {
        state = sstate;
    }
    public void setPhone(String sphone) {
        phone = sphone;
    }
    public void setZipcode(String szipcode) {
        zipcode = szipcode;
    }

    //Getter Method
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getPhone() {
        return phone;
    }
    public String getZipcode() {
        return zipcode;
    }

    //parameterized constructor
    public Information(String fn, String ln, String add, String ci, String st, String ph, String zip){
        setFname(fn);
        setLname(ln);
        setAddress(add);
        setCity(ci);
        setState(st);
        setPhone(ph);
        setZipcode(zip);
    }

    //Method For Print the Information
    public void print(){
        System.out.println("First Name : " + fname);
        System.out.println("Last Name : " + lname);
        System.out.println("Address : " + address);
        System.out.println("City : " + city);
        System.out.println("State : " + state);
        System.out.println("Phone Number : " + phone);
        System.out.println("Zip-Code : " + zipcode);
    }
}