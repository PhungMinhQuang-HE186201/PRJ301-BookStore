/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author PMQUANG
 */
public class OrderItem {
    private int item_id,order_id,product_id,quantity,accountId;
    private float price_item,total_item;
    private String product_name,image;
    private int numberSold,category_id;

    public OrderItem() {
    }
    
    public OrderItem(int item_id, int order_id, int product_id, int quantity) {
        this.item_id = item_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public OrderItem(int item_id, int order_id, int product_id, int quantity, float price_item) {
        this.item_id = item_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price_item = price_item;
    }
    
    public OrderItem(int product_id, String product_name,String image, int numberSold,int category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.image = image;
        this.category_id = category_id;
        this.numberSold = numberSold;
    }

    public OrderItem(int item_id, int order_id, int product_id, int quantity,float price_item ,int accountId) {
        this.item_id = item_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.price_item = price_item;
        this.accountId = accountId;
    }
    
    public OrderItem(int item_id, String product_name, int quantity, float price_item, float total_item) {
        this.item_id = item_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price_item = price_item;
        this.total_item = total_item;
    }
    
    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice_item() {
        return price_item;
    }

    public void setPrice_item(float price_item) {
        this.price_item = price_item;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    
    
    public float getTotal_item() {
        return total_item;
    }

    public void setTotal_item(float total_item) {
        this.total_item = total_item;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "product_id=" + product_id + ", product_name=" + product_name + ", image=" + image + ", numberSold=" + numberSold + '}';
    }

    
    
    
    
}
