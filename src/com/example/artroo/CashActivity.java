package com.example.artroo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CashActivity extends Activity {

	ListView mainListView;
	ArrayList<User> db_dates;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Intent intent = getIntent();
		db_dates = new ArrayList<User>();
		db_dates = (ArrayList<User>) getIntent().getSerializableExtra("key");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deteditorcash);
		mainListView =(ListView)findViewById(R.id.cashList);
		//  db_dates=getList();
		loadExpenses();
		//if(db_dates.size()==0)
		//{
		//mainListView.setAdapter(a);

		//}
		final UserAdapterCash userAdapter=new UserAdapterCash(this,db_dates);
		
		mainListView.setAdapter(userAdapter);
		//mainListView.setEmptyView(findViewById(android.R.id.empty));
		 // Item Click Listener for the listview
		OnItemLongClickListener itemClickListener = new OnItemLongClickListener() {
           
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View container,
					int position, long id) {
				
				removeItemFromList(position);   
				return false;
			}
			private void removeItemFromList(int position) {
				// TODO Auto-generated method stub
			//	Log.d("Hello",position+"==");
				   final int deletePosition = position;
			           
			        AlertDialog.Builder alert = new AlertDialog.Builder(
			                CashActivity.this);
			    
			        alert.setTitle("Delete");
			        alert.setMessage("Do you want delete this item?");
			        alert.setPositiveButton("YES", new OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			                // TOD O Auto-generated method stub
			                    
			                    // main code on after clicking yes
			            	 delete(db_dates.get(deletePosition)); 
			                    db_dates.remove(deletePosition);
			                    mainListView.setAdapter(userAdapter);
			                    userAdapter.notifyDataSetChanged();
			                    userAdapter.notifyDataSetInvalidated();
			      
			            }
			        });
			        alert.setNegativeButton("CANCEL", new OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int which) {
			                // TODO Auto-generated method stub
			                dialog.dismiss();
			            }
			        });
			      
			        alert.show();

			}

			
        };
 
        // Setting the item click listener for the listview
        mainListView.setOnItemLongClickListener(itemClickListener);
      
	
	}
	private void loadExpenses() {
		db_dates = new ArrayList<User>();
		//db_amnt = new ArrayList<String>();

		ExpenseDatabase dbase = new ExpenseDatabase(getApplicationContext());
		dbase.open();
		Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
				ExpenseDatabase.EXPENSE_INT);
		if (db_cur != null && db_cur.getCount() > 0) {
			db_cur.moveToFirst();
			do {
				User data=new User(db_cur.getString(1),db_cur.getString(2),db_cur.getString(3),db_cur.getString(4),db_cur.getString(5));

				//data.setAmount(db_cur.getLong(1)+"");data.setName(db_cur.getString(3));
			//	Log.d("date==",db_cur.getString(1)+"--"+db_cur.getString(2)+""+db_cur.getString(3)+"--"+db_cur.getString(4)+"--"+db_cur.getString(5));

				db_dates.add(data);

			} while (db_cur.moveToNext());
		}
		dbase.close();
	}
	
	private void delete(User user)
	{
		ExpenseDatabase dbase = new ExpenseDatabase(getApplicationContext());
		String whereClause="transaction_date" + "= '" + user.transaction_date + "'and money"
				 + "= '" + user.money + "'and typeOfItem"
				 + "= '" + user.typeOfItem + "'and sourceOfPayment"
			     + "= '" + user.sourceOfPayment + "'and note"
			     + "= '" + user.note + "'";
		
		
		dbase.open();
		dbase.deleteManual(ExpenseDatabase.MANUAL_TABLE,ExpenseDatabase.EXPENSE_INT,whereClause);
		
	}

	public void onHomeClick(View v) {			
		startActivity(new Intent(this, MainActivity.class));
		finish();    
	}




}
