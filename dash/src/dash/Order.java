/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dash;

import java.util.*;
/**
 *
 * @author navi
 */
public class Order {
    int orderId;
    int itemId;
    List<Item>itemList=new ArrayList<>(); //List of all items
    
    //Contructor
    public Order(){
 
    };
    
    //add items with quantity
    public void addItems(Item item,int quantity){
       for(int i = 1;i<=quantity;i++){
        itemList.add(item);
       }
    } 
    
    //remove items with paticular id on the list
    public void removeItem(Item item,int id){
         itemList.remove(id);
    }
    
    //change price 
    public void changePrice(Item item,double price,int id,int index){
        Item changePriceItem =itemList.get(id); //Retrive item in question
        changePriceItem.setItemPrice(price);
        //Change the value(Price)
        itemList.set(index, changePriceItem);      //Replace old item with new one
    }
    
    //return Order as a array of items
    public Item[] returnOrder(){
    //Trying to convert the list of item objects int items array
    Item[] items = itemList.toArray(new Item[itemList.size()]); 
    return items;
    }
    
    //Get total Price
    public double getTotal(){
    double total =0;
    Item temporyItem;
    for(int i =0;i<itemList.size();i++){
        temporyItem=itemList.get(i);
        total = temporyItem.getItemPrice()+total;
    }
    return total; 
    }
    
    
}
