/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dash;

/**
 *
 * @author navi
 */
public class SalesTransaction {
    int id;
    double total;
    String status1;
    public SalesTransaction(int saleId, double saleTotal,String status){
        id = saleId;
        total =saleTotal;
        status1=status;
};
    
    
    
    public String returnTransaction(){
       String transaction = String.valueOf(id)+" "+String.valueOf(total)+" "+status1;
       return transaction;
    }
}
