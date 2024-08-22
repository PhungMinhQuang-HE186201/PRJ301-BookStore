/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * @author PMQUANG
 */
public class Order {

    private int id;
    private float list_price;
    private int accountId;
    private Timestamp orderDate;
    private String status;
    private String username;
    Vector<OrderItem> listOrderItems = new Vector<>();

    public Order() {
    }

    public Order(int id, float list_price, int accountId, Timestamp orderDate, String status) {
        this.id = id;
        this.list_price = list_price;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(int bill_id, String username, Timestamp orderDate, float total, String status) {
        this.id = bill_id;
        this.username = username;
        this.orderDate = orderDate;
        this.list_price = total;
        this.status = status;
    }

    public Order(int id, String username, String status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getList_price() {
        return list_price;
    }

    public void setList_price(float list_price) {
        this.list_price = list_price;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Vector<OrderItem> getListOrderItems() {
        return listOrderItems;
    }

    public void setListOrderItems(Vector<OrderItem> listOrderItems) {
        this.listOrderItems = listOrderItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", list_price=" + list_price + ", accountId=" + accountId + ", orderDate=" + orderDate + ", status=" + status + '}';
    }

}
