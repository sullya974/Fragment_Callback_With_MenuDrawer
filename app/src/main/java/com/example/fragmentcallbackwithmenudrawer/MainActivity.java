package com.example.fragmentcallbackwithmenudrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    //Gestion des fragments
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navView
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        addFragment();
        setSupportActionBar(toolbar);

        //Options d'accessibilité
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        //Synchro le bouton hamburger et le menu
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        //Force l'affichage du premier fragment au démarrage
        navView.setCheckedItem(R.id.nav_fragment_1);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void initUI() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
    }

    private void addFragment(){
        fragmentManager = getSupportFragmentManager();
        // Commencer la discution avec le fragment
        fragmentTransaction = fragmentManager.beginTransaction();
        //Appel du nouveau fragment
        Fragment_01 fragment_01 = new Fragment_01();
        // Ajouter au container de fragment
        fragmentTransaction.add(R.id.fragment_container, fragment_01);

        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_fragment_1:// Id des items de note menu_principal
                setFragment(new Fragment_01());
                break;
            case R.id.nav_fragment_2:
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, new Fragment_02()).commit();
                break;
            case R.id.nav_fragment_3:
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, new Fragment_03()).commit();
                break;
            case R.id.nav_fragment_4:
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, new Fragment_04()).commit();
                break;
            case R.id.nav_fragment_5:
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, new Fragment_05()).commit();
                break;
            default:
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container, new Fragment_01()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, fragment);
    }
}