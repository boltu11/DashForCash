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
    private char[] itemSize;// size will range from s,m,l,xl
    private char[] itemType;// type will be liquids or solids like coffee or muffins
    
    
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
      public char[] getItemType (){
      return itemType;
    }
       public char[] getItemSize(){
      return itemSize;
    }
       
     //set methods
    public void setItemPrice(double price){
      itemPrice=price;
    }
    public void setItemName(String name){
      itemName= name;
    }
    public void setItemType(char[] type){ //Possible item types
      itemType = type;
    }
    public void setItemSizes(char[] size){ //Possible item Sizes
      itemSize = size;
    }
    public String displayItem(){
    String itemDiscription =itemName+" "+itemSize+" $"+itemPrice;
    return itemDiscription;
    }
       
    
       
       
    
    
    
}
