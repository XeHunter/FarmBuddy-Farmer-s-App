package com.example.farmbuddy;

import static com.example.farmbuddy.flogin.PREFS_NAME;
import static com.example.farmbuddy.vendorlogin.KEY_NAME1;
import static com.example.farmbuddy.vendorlogin.PREFS_NAME1;
import static com.example.farmbuddy.vendorlogin.USER_NAME1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class hvendor extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView ;
    ActionBar actionBar;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    ExtendedFloatingActionButton extendedFloatingActionButton;
    static String USER_NAME1 = "file1";
    static String KEY_NAME1 = "name1";
    SharedPreferences sd ;
    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hvendor);
        recyclerView = findViewById(R.id.rv);
        drawerLayout = findViewById(R.id.d2);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        DrawerArrowDrawable arrowDrawable = actionBarDrawerToggle.getDrawerArrowDrawable();
        arrowDrawable.setColor(getResources().getColor(R.color.black));
        actionBarDrawerToggle.setDrawerArrowDrawable(arrowDrawable);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#E954CF58"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(Html.fromHtml("<font color=\"black\">" + "Vendors Section" + "</font>"));
        navigationView = findViewById(R.id.n2);
        View header  = navigationView.getHeaderView(0);
        textView =  header.findViewById(R.id.drawname1);
        sd = getSharedPreferences(USER_NAME1 , MODE_PRIVATE);
        String name = sd.getString(KEY_NAME1 , null);
        if(name != null)
        {
            textView.setText("Hello \n"+name+"!");
        }
        else{
            textView.setText("Hello Vendor!");
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.rateus1:

                        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(hvendor.this);
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
                                    Toast.makeText(hvendor.this, "Thank You For Rating Us!!", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    bottomSheetDialog.hide();
                                    Toast.makeText(hvendor.this, "Please Select Number Of Stars", Toast.LENGTH_SHORT).show();

                                }
                            }

                        });
                        bottomSheetDialog.show();


                        break;
                    case R.id.share1:
                        Intent si = new Intent(Intent.ACTION_SEND);
                        si.setType("text/plain");
                        String body = "Like Share this app" + "\n\nhttps://drive.google.com/drive/folders/10qQqVyXHEJVrnO-DH5CsqG3-4mCHd2x3?usp=sharing";
                        String sub = "";
                        si.putExtra(Intent.EXTRA_TEXT,body);
                        si.putExtra(Intent.EXTRA_SUBJECT,sub);
                        startActivity(Intent.createChooser(si,"Share Using"));
                        break;
                    case R.id.contact1:
                        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "farmbuddyhelpline@gmail.com", null));
                        startActivity(i);
                        break;
                    case R.id.about1:
                        Intent intent1 = new Intent(hvendor.this,about.class);
                        startActivity(intent1);
                        break;
                    case R.id.privacy1:
                        gotoUrl("https://doc-hosting.flycricket.io/farmbuddy/1a0f146f-3051-4cc6-8944-ded4fc00e204/privacy");
                        break;
                    case R.id.out1:
                        AlertDialog.Builder builder = new AlertDialog.Builder(hvendor.this);
                        builder.setTitle("Logout!!");
                        builder.setMessage("Are you sure want to Logout??").setCancelable(false).
                                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        hvendor.super.onBackPressed();
                                        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME1 , MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.clear();
                                        editor.apply();
                                        startActivity(new Intent(hvendor.this , main2.class));
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Vendor"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        extendedFloatingActionButton = findViewById(R.id.add);
        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hvendor.this,AddActivity2.class);
                startActivity(i);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 20 && extendedFloatingActionButton.isExtended()){
                    extendedFloatingActionButton.shrink();
                }
                if(dy < -20 && !extendedFloatingActionButton.isExtended()){
                    extendedFloatingActionButton.extend();
                }
                if(!recyclerView.canScrollVertically(-1)){
                    extendedFloatingActionButton.extend();
                }
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
        Intent i = new Intent(hvendor.this, Logins.class);
        startActivity(i);
        finish();
    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW , uri));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Vendor").orderByChild("Product").startAt(str).endAt(str+"~"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);
    }
}
