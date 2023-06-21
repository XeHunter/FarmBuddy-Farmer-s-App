package com.example.farmbuddy;

import static com.example.farmbuddy.flogin.PREFS_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmbuddy.bottom.news;
import com.example.farmbuddy.bottom.profrag;
import com.example.farmbuddy.bottom.save;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class farm extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView ;
    NavController navcontroller;
    BottomNavigationView btn;
    ActionBar actionBar;
    static String USER_NAME = "file";
    static String KEY_NAME = "name";
    SharedPreferences sd ;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
        drawerLayout = findViewById(R.id.d1);
        btn = findViewById(R.id.btmnavi);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        DrawerArrowDrawable arrowDrawable = actionBarDrawerToggle.getDrawerArrowDrawable();
        arrowDrawable.setColor(getResources().getColor(R.color.black));
        actionBarDrawerToggle.setDrawerArrowDrawable(arrowDrawable);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navcontroller = Navigation.findNavController(farm.this,R.id.fragment);
        NavigationUI.setupWithNavController(btn,navcontroller);
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#E954CF58"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Farmers Section" + "</font>"));
        navigationView = findViewById(R.id.n1);
        View header  = navigationView.getHeaderView(0);
        textView =  header.findViewById(R.id.drawname);
        sd = getSharedPreferences(USER_NAME , MODE_PRIVATE);
        String name = sd.getString(KEY_NAME , null);
        if(name != null)
        {
            textView.setText("Hello \n"+name+"!");
        }
        else{
            textView.setText("Hello Farmer!");
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.rateus:

                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(farm.this);
                        bottomSheetDialog.setContentView(R.layout.rate);
                        ImageView imageView = bottomSheetDialog.findViewById(R.id.close);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bottomSheetDialog.hide();
                            }
                        });
                        RatingBar r1 = bottomSheetDialog.findViewById(R.id.rateBar);
                        Button b1 = bottomSheetDialog.findViewById(R.id.submit);
                        b1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(r1.getRating()>0){
                                    bottomSheetDialog.hide();
                                    Toast.makeText(farm.this, "Thank You For Rating Us!!", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    bottomSheetDialog.hide();
                                    Toast.makeText(farm.this, "Please Select Number Of Stars", Toast.LENGTH_SHORT).show();

                                }
                            }

                        });
                        bottomSheetDialog.show();


                        break;
                    case R.id.share:
                        Intent si = new Intent(Intent.ACTION_SEND);
                        si.setType("text/plain");
                        String body = "Like Share this app" + "\n\nhttps://drive.google.com/drive/folders/10qQqVyXHEJVrnO-DH5CsqG3-4mCHd2x3?usp=sharing";
                        String sub = "";
                        si.putExtra(Intent.EXTRA_TEXT,body);
                        si.putExtra(Intent.EXTRA_SUBJECT,sub);
                        startActivity(Intent.createChooser(si,"Share Using"));
                        break;
                    case R.id.contact:
                        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "farmbuddyhelpline@gmail.com", null));
                        startActivity(i);
                        break;
                    case R.id.about:
                        Intent intent1 = new Intent(farm.this,about.class);
                        startActivity(intent1);
                        break;
                    case R.id.privacy:
                        gotoUrl("https://doc-hosting.flycricket.io/farmbuddy/1a0f146f-3051-4cc6-8944-ded4fc00e204/privacy");
                        break;
                    case R.id.out:
                        AlertDialog.Builder builder = new AlertDialog.Builder(farm.this);
                        builder.setTitle("Logout!!");
                        builder.setMessage("Are you sure want to Logout??").setCancelable(false).
                                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        farm.super.onBackPressed();
                                        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME , MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.clear();
                                        editor.apply();
                                        startActivity(new Intent(farm.this , main2.class));
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(farm.this, Logins.class);
        startActivity(i);
        finish();
    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW , uri));
    }
}