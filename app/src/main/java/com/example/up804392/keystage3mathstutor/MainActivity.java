package com.example.up804392.keystage3mathstutor;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.up804392.keystage3mathstutor.db.Database;
import com.example.up804392.keystage3mathstutor.ui.about.AboutFragment;
import com.example.up804392.keystage3mathstutor.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "mathsTutor";
    private NavigationView sideNavigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private boolean doubleBackToExitPressedOnce = false;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        sideNavigationView = findViewById(R.id.nav_view);
        sideNavigationView.setNavigationItemSelectedListener(item -> {
            MenuItem subItem;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    changeFragment(new HomeFragment(), R.id.nav_home);
                    break;
                case R.id.nav_about:
                    changeFragment(new AboutFragment(), R.id.nav_about);
                    break;
                case R.id.nav_login:
                    item.setVisible(false);
                    subItem = sideNavigationView.getMenu().findItem(R.id.nav_online).getSubMenu().findItem(R.id.nav_logOut);
                    subItem.setVisible(true);
                    Toast.makeText(this, "logged in", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_logOut:
                    item.setVisible(false);
                    subItem = sideNavigationView.getMenu().findItem(R.id.nav_online).getSubMenu().findItem(R.id.nav_login);
                    subItem.setVisible(true);
                    Toast.makeText(this, "logged out", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_score:
                    Toast.makeText(this, "Online scoreboards coming soon", Toast.LENGTH_SHORT).show();
                    break;
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        database = getDatabase();

        if (savedInstanceState == null) {
            changeFragment(new HomeFragment(), R.id.nav_home);
        }
    }


    public void changeFragment(Fragment fragment, int navMenuItemID) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
        sideNavigationView.setCheckedItem(navMenuItemID);
        MenuItem  item = sideNavigationView.getCheckedItem();
        if (item != null) {
            item.setChecked(true);
        }
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
    }

    public void uncheckCurrentCheckedItemInNavigationView() {
        MenuItem  item = sideNavigationView.getCheckedItem();
        if (item != null) {
            item.setChecked(false);
        }
    }

    public Database getDatabase() {
        if (database == null) {
            database = Room.databaseBuilder(getApplicationContext(), Database.class, DATABASE_NAME).build();
        }
        return database;
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setToolbarTitle(int id) {
        toolbar.setTitle(id);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce=false, 2000);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, drawer)
                || super.onSupportNavigateUp();
    }
}
