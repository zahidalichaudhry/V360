package com.itpvt.v360.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.itpvt.v360.Config;
import com.itpvt.v360.R;
import com.itpvt.v360.RequestHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Reg_modelshoot extends AppCompatActivity {
    Button shoot_date, Submit;
    String model_date,from_date, to_date,  contact,from_date_only,from_time_only
            ,to_Date_only,to_time_only;;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String email;
    EditText city, description, venue;
    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");

        Date minDate2 = Calendar.getInstance().getTime();
//    String minDate = mFormatter.format(new Date());
//
//    private SlideDateTimeListener from = new SlideDateTimeListener() {
//        @Override
//        public void onDateTimeSet(Date date) {
////            Toast.makeText(Reg_marriage.this, mFormatter.format(date), Toast.LENGTH_SHORT).show();
//            model_date = mFormatter.format(date).toString();
//            shoot_date.setText(model_date);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reg_modelshoot);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        toolbar.setTitle("Register Model Shoot");
        toolbar.setTitleTextColor(getResources().getColor(R.color.yellow));
//        toolbar.setBackgroundColor(getResources().getColor(R.color.iron));



        shoot_date = (Button) findViewById(R.id.model_fromDate);
        venue = (EditText) findViewById(R.id.model_venue);
        city = (EditText) findViewById(R.id.model_city);
        description = (EditText) findViewById(R.id.model_description);
        Submit = (Button) findViewById(R.id.model_submit);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the string value form sharedpreferences
//        user_type = sharedPreferences.getString(Config.CATEGORY_SHARED_PREF, null);
        email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, null);
        contact = sharedPreferences.getString(Config.PHONE_SHARED_PREF, null);


        shoot_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Reg_modelshoot.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                             shoot_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                from_date_only=shoot_date.getText().toString();
                                timpicker();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();


//                new SlideDateTimePicker.Builder(getSupportFragmentManager())
//                        .setListener(from)
//                        .setInitialDate(new Date())
//                        .setMinDate(minDate2)
//                        //.setMaxDate(maxDate)
//                        //.setIs24HourTime(true)
//                        //.setTheme(SlideDateTimePicker.HOLO_DARK)
//                        //.setIndicatorColor(Color.parseColor("#990000"))
//                        .build()
//                        .show();
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (city.getText().length()==0)
                {
                    city.requestFocus();
                    city.setError(Html.fromHtml("<font color='red'>Please Enter City</font>"));
                }
                else if (description.getText().length()==0)
                {
                    description.requestFocus();
                    description.setError(Html.fromHtml("<font color='red'>Please Enter Description</font>"));
                }
                else if (venue.getText().length()==0)
                {
                    venue.requestFocus();
                    venue.setError(Html.fromHtml("<font color='red'>Please Enter Venue</font>"));
                }
                else if (view==Submit)
                {
                    submitDetails();
                }
            }
        });
    }
    private void timpicker() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        from_time_only=(hourOfDay + ":" + minute).toString();
                        model_date=(from_date_only+from_time_only).trim();
                       String date= shoot_date.getText().toString();
                       shoot_date.setText(date + "/" +from_time_only);

                        ///////////////////////////////////////////////////////
                    }
                }, mHour, mMinute, false);

        timePickerDialog.show();
    }

    private void submitDetails() {
        final String str_venue = venue.getText().toString().trim();
        final String str_city = city.getText().toString().trim();
        final String str_description = description.getText().toString().trim();

        {
            //Now will create a StringRequest almost the same as we did before

            class UpdateEmployee extends AsyncTask<Void, Void, String> {
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(Reg_modelshoot.this, "Submitting Details...", "Please Wait...", false, false);
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if (s.equals("Successfully Register")) {
                        Intent intent=new Intent(Reg_modelshoot.this,Submitted.class);
                        finish();
                        startActivity(intent);
                        Toast.makeText(Reg_modelshoot.this, "Submitted Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(Reg_modelshoot.this, s, Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                protected String doInBackground(Void... params) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Config.MODEL_EMAIL, email);
                    hashMap.put(Config.MODEL_CONTACT, contact);
                    hashMap.put(Config.MODEL_VENUE, str_venue);
                    hashMap.put(Config.MODEL_CITY, str_city);
                    hashMap.put(Config.MODEL_DATE, model_date);
                    hashMap.put(Config.MODEL_DESCRIPTION, str_description);

                    RequestHandler rh = new RequestHandler();
                    String s = rh.sendPostRequest(Config.URL_MODEL_SHOOT, hashMap);
                    return s;
                }
            }
            UpdateEmployee ue = new UpdateEmployee();
            ue.execute();
        }
    }
}
