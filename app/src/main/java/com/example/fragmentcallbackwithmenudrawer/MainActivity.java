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
import android.view.View;
import android.widget.Button;

import com.example.fragmentcallbackwithmenudrawer.utils.Gol;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Button btn_addFragment;

    //Variable Emplacement - Get Class path
    private static final String emplacement = MainActivity.class.getSimpleName();

    //Gestion des fragments
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
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

        // Gère le premier chargement et les changements de config. tel la rotation d'écran
        if (savedInstanceState == null) {
//            addFragment();

            //Force l'affichage du premier fragment au démarrage
            navView.setCheckedItem(R.id.nav_fragment_1);
        }

//        btn_addFragment.setOnClickListener(v -> {
//            addFragment();
//        });

        Gol.addLog(emplacement, "onCreate");
    }

    public void btn_addFragment(View view) {
        addFragment();
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
        btn_addFragment = findViewById(R.id.btn_addFragment);
    }

    private void addFragment() {
        fragmentManager = getSupportFragmentManager();
        // Commencer la discution avec le fragment
        fragmentTransaction = fragmentManager.beginTransaction();
        //Appel du nouveau fragment
        Fragment_01 fragment_01 = new Fragment_01();
        // Ajouter au container de fragment
        fragmentTransaction.add(R.id.fragment_container, fragment_01);

        fragmentTransaction.addToBackStack("fragmentStack1");

        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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

    /**
     * Méthodes du cycle de vie
     **/
    @Override
    protected void onStart() {
        super.onStart();
        Gol.addLog(emplacement, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Gol.addLog(emplacement, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Gol.addLog(emplacement, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Gol.addLog(emplacement, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Gol.addLog(emplacement, "onDestroy");
    }
}