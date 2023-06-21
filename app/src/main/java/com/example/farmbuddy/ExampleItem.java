package com.example.farmbuddy;

public class ExampleItem {
    private String Company_Name,Contact,Email,Location,Product,curl,price;

    public ExampleItem(String company_Name, String contact, String email, String location, String product, String curl, String price) {
        Company_Name = company_Name;
        Contact = contact;
        Email = email;
        Location = location;
        Product = product;
        this.curl = curl;
        this.price = price;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
