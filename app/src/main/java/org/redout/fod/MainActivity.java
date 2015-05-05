package org.redout.fod;

import android.app.DatePickerDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.redout.solunarlib.RiseSetTransit;
import org.redout.solunarlib.Solunar;
import org.redout.solunarlib.SolunarFacade;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private EditText fcstDateEtxt;
    private TextView resultsTview;
    private Button goButton;

    private DatePickerDialog fcstDatePickerDialog;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
    }

    private void findViewsById() {
        fcstDateEtxt = (EditText) findViewById(R.id.etxt_fcstdate);
        fcstDateEtxt.setInputType(InputType.TYPE_NULL);
        fcstDateEtxt.requestFocus();
        fcstDateEtxt.setText(dateFormatter.format(Calendar.getInstance().getTime()));

        resultsTview = (TextView) findViewById(R.id.tviewResults);
        goButton = (Button) findViewById(R.id.goButton);
        goButton.setOnClickListener(this);

    }

    private void setDateTimeField() {
        fcstDateEtxt.setOnClickListener(this);
        Calendar newCal = Calendar.getInstance();
        fcstDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fcstDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH), newCal.get(Calendar.DAY_OF_MONTH));
    }

    public void onClick(View view) {
        if (view == fcstDateEtxt) {
            fcstDatePickerDialog.show();
        } else if (view == goButton) {
            Calendar c = Calendar.getInstance();
            try {
                Date d = dateFormatter.parse(fcstDateEtxt.getText().toString());
                c.setTime(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            getCoords(view,c);
        } else {
            System.out.println("Unknown Item: " + view.toString() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getCoords(View v, Calendar reportDate) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location locationGPS = (Location) locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        String coords = "";
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(locationGPS.getLatitude(), locationGPS.getLongitude(), 1);
            //addresses = gcd.getFromLocation(39.74,-104.99, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null) {
            Address address = addresses.get(0);
            coords += address.getLocality() + ", " + address.getAdminArea() + " ";
            coords += "Lat: " + address.getLatitude() + " Lon: " + address.getLongitude();

        } else {
            coords += "Lat: " + locationGPS.getLatitude() + " Lon: " + locationGPS.getLongitude();
        }

        Solunar data = getData(locationGPS.getLatitude(), locationGPS.getLongitude(), reportDate);
        RiseSetTransit rst = data.getSolRST();
//        coords += " Sunrise: " + convertTime(rst.getRise());
//        coords += " Sunset: " + convertTime(rst.getSet());
        coords += " Sunrise: " + formatDate(convertTimeToCal(data.getDayOf(), rst.getRise()));
        coords += " Sunset: " + formatDate(convertTimeToCal(data.getDayOf(), rst.getSet()));
        TextView tv = (TextView) findViewById(R.id.tviewResults);
        tv.setText(coords);

    }
    public Solunar getData(double lat, double lon, Calendar c) {
        SolunarFacade facade = SolunarFacade.getInstance();
        Solunar data = facade.getForDate(c, lat, lon);
        return data;
    }

    private String convertTime(Double d) {
        long hour = d.longValue();
        long min = Math.round((d - hour) * .60 * 100);
        return String.format("%02d",hour) + ":" + String.format("%02d",min);
    }

    private Calendar convertTimeToCal(Calendar cal, Double d) {
        int hour = d.intValue();
        int min = (int) Math.round((d - hour) * .60 * 100);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        return cal;
    }

    private String formatDate(Calendar cal){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy hh:mma z");
        return sdf.format(cal.getTime());
    }
}
