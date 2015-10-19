package com.example.artroo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CashMainActivity extends Activity {
	//	ListView mainListView;
	ArrayList<User> db_dates;
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deteditor);
		Intent intent = getIntent();

		loadExpenses();


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
				//Log.d("date==",db_cur.getString(1)+"--"+db_cur.getString(2)+""+db_cur.getString(3)+"--"+db_cur.getString(4)+"--"+db_cur.getString(5));

				db_dates.add(data);

			} while (db_cur.moveToNext());
		}
		dbase.close();
	}

	public void Create(View view)
	{

		Spinner spinnerFrom = (Spinner) findViewById(R.id.deteditor_from);
		String selectedSpinnerValueFrom = spinnerFrom.getSelectedItem().toString();
		Spinner spinnerTo = (Spinner) findViewById(R.id.deteditor_to);
		String selectedSpinnerValueTo = spinnerTo.getSelectedItem().toString();
		EditText moneyEditText=(EditText)findViewById(R.id.deteditor_money);
		EditText noteEditText=(EditText)findViewById(R.id.deteditor_note);
		EditText dateEditText=(EditText)findViewById(R.id.deteditor_date);
		//	Log.d("date",date+"");
		if(selectedSpinnerValueFrom.equals("None") &&selectedSpinnerValueTo.equalsIgnoreCase("None"))
		{
			Toast.makeText(getApplicationContext(), "all fields cannot be null", Toast.LENGTH_SHORT).show();
		}
		else
		{
			ExpenseDatabase db = new ExpenseDatabase(getApplicationContext());
			db.open();


			db.createAlertManual(ExpenseDatabase.MANUAL_TABLE,
					ExpenseDatabase.EXPENSE_INT, new String[] { "" + noteEditText.getText().toString(),
					"" + dateEditText.getText().toString() ,"" + selectedSpinnerValueFrom.toString(),"" + selectedSpinnerValueTo.toString(),"" + moneyEditText.getText().toString()});


			db.close();

			loadExpenses();
		}
		//  mainListView.setAdapter(new UserAdapterCash(this,db_dates));

	}

	public void Cancel(View view)
	{
		Intent intent=new Intent(this, CashActivity.class);

		intent.putExtra("key",db_dates);
		startActivity(intent);
		finish();    
	}

	public void onHomeClick(View v) {			
		startActivity(new Intent(this, MainActivity.class));
		finish();    
	}





}

