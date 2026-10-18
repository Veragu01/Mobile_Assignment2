package com.example.mobile_assignment2.task1.task1a;

public class Fruit {

    private String documentID;
    private String name;
    private String type;
    private int quantity;
    private int rating;

    public Fruit(String name, String type, int quantity, int rating) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.rating = rating;
    }

    public Fruit() {

    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getDocumentID() {
        return this.documentID;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRating() {
        return rating;
    }
}
