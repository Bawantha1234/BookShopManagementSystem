package lk.ijse.BookShopManagementSystem.tm;

public class Employee {
        private  String id;
        private  String name;
        private  String address;
        private  String nic;
        private  String contactNumber;
        private  double salary;

    public Employee() {
    }

    public Employee(String id, String name, String address, String nic, String contactNumber, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.salary = salary;
    }

    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public  String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public  double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", salary=" + salary +
                '}';
    }
}