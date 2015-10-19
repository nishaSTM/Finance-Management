package com.example.artroo;

import java.io.Serializable;

public class User implements Serializable {
	public String note;
    public String transaction_date;
    public String typeOfItem;
    public String sourceOfPayment;
    public String money;
    

    public User(String note, String transaction_date,String typeOfItem, String sourceOfPayment,String money) {
       this.note = note;
       this.transaction_date = transaction_date;
       this.typeOfItem = typeOfItem;
       this.sourceOfPayment = sourceOfPayment;
       this.money = money;
       
    }


}


