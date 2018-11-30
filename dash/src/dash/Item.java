/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dash;

/**
 *
 * @author nav
 */
public class Item {
    private String itemName;
    private double itemPrice;
    private int itemType;// type will be liquids or solids like coffee or muffins
    
    
    //contructor
    public Item(){
         
    }
    
    //return methods
    public double getItemPrice(){
      return itemPrice;
    }
     public String getItemName(){
      return itemName;
    }
      public int getItemType (){
      return itemType;
    }
     
       
     //set methods
    public void setItemPrice(double price){
      itemPrice=price;
    }
    public void setItemName(String name){
      itemName= name;
    }
    public void setItemType(int type){ //Possible item types
      itemType = type;
    }
    public String displayItem(){
    String itemDiscription =itemName+" $"+itemPrice;
    return itemDiscription;
    }
       
    
       
       
    
    
    
}
