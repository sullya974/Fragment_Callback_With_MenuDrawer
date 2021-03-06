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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentcallbackwithmenudrawer.utils.Gol;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    //Variable Emplacement - Get Class path
    private static final String emplacement = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Button btnAddfragment, btnPopFragment, btnRemoveFragment;
    TextView tvFragmentCount;

    //Gestion des fragments
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        initUI();
        setSupportActionBar(toolbar);
        listenToBackStack();

        //Options d'accessibilitÃ©
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
//        navView.setNavigationItemSelectedListener(this);
        // GÃ¨re le premier chargement et les changements de config. tel la rotation d'Ã©cran
        if (savedInstanceState == null) {
//            addFragment();
            //Force l'affichage du premier fragment au dÃ©marrage
            navView.setCheckedItem(R.id.nav_fragment_1);
        }

        Gol.addLog(emplacement, "onCreate");
    }

    public void initUI() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        btnAddfragment = findViewById(R.id.btn_addFragment);
        btnAddfragment.setOnClickListener(this);
        btnPopFragment = findViewById(R.id.btn_addFragment);
        btnPopFragment.setOnClickListener(this);
        btnRemoveFragment = findViewById(R.id.btn_addFragment);
        btnRemoveFragment.setOnClickListener(this);
        tvFragmentCount = findViewById(R.id.tv_fragmentCount);
    }

//    @Override
//    public void onBackPressed() {
//        // Ajout des infos pour la gestion du backStack
//        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
//        //Gestion du navigationDrawer
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else if(fragment != null){ // Gestion bouton retour dans le cadre d'un fragment
//            fragmentTransaction = fragmentManager.beginTransaction();
//            //Si fragment prÃ©sent on le retire du backStack
//            fragmentTransaction.remove(fragment);
//            fragmentTransaction.addToBackStack("Remove " + fragment.toString());
//            fragmentTransaction.commit();
//        }else{
//            super.onBackPressed();
//        }
//    }

    /**
     * Affiche les fragments en fonction du nombre d'entrÃ©e de la pile
     */
    private void addFragment() {
        Fragment fragment;
        //Appel du nouveau fragment
        if (fragmentManager.getBackStackEntryCount() > 0) {

            switch (fragmentManager.getBackStackEntryCount()) { //Get the number of entries currently in the back stack
                case 0:
                    fragment = new Fragment_01();
                    break;
                case 1:
                    fragment = new Fragment_02();
                    break;
                case 2:
                    fragment = new Fragment_03();
                    break;
                default:
                    fragment = new Fragment_01();
            }
        } else {
            // On rÃ©cupÃ¨re le fragment courant du container de l'activitÃ©
            fragment = fragmentManager.findFragmentById(R.id.fragment_container);
            // Et en fonction on demande l'affichage
            if (fragment instanceof Fragment_01) {
                fragment = new Fragment_02();
            } else if (fragment instanceof Fragment_02) {
                fragment = new Fragment_03();
            } else if (fragment instanceof Fragment_03) {
                fragment = new Fragment_01();
            } else {
                //par dÃ©faut
                fragment = new Fragment_01();
            }
        }
        loadFragment(fragment);
    }

    /**
     * GÃ¨re le chargement de fragment
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        Fragment currentFragment;
        fragmentTransaction = fragmentManager.beginTransaction();
        //Le fragment courant
        currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction.replace(R.id.fragment_container, fragment, "DEMO FRAGMENT");
        fragmentTransaction.commit();
    }


    /**
     * Nous permet de connaÃ®tre le nombre de fragment sauvegardÃ©s dans le backstack
     * grÃ¢ce Ã  getBAckStackEntryCount()
     */
    private void listenToBackStack() {
        //Affiche nombre fragment dans la pile
        tvFragmentCount.setText("Nombre de fragment(s) enregistrÃ©(s) dans la pile " +
                fragmentManager.getBackStackEntryCount());
        //Ajout d'un listener pour Ã©couer les changements dans le backstack
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                tvFragmentCount.setText("Nombre de fragment(s) enregistrÃ©(s) dans la pile " +
                        fragmentManager.getBackStackEntryCount());

                StringBuilder backStackEntryMessage = new StringBuilder("Statue courant de fragment transaction dans le back stack // Position : "
                        + fragmentManager.getBackStackEntryCount());
                //Pour le backStck de haut en bas
                for (int index = (fragmentManager.getBackStackEntryCount() - 1); index >= 0; index--) {
                    //Utilise BackStackEntry pour afficher le nom
                    FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(index);
                    //On affiche le nom qui est enregistrÃ© dans le addBackStack de la mÃ©thode addFragment
                    backStackEntryMessage.append(entry.getName() + "\n");
                }
                Log.i(TAG, "onBackStackChanged: " + backStackEntryMessage.toString());
            }
        });
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_fragment_1:// Id des items de note menu_principal
//                setFragment(new Fragment_01());
//                break;
//            case R.id.nav_fragment_2:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.fragment_container, new Fragment_02()).commit();
//                break;
//            case R.id.nav_fragment_3:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.fragment_container, new Fragment_03()).commit();
//                break;
//            case R.id.nav_fragment_4:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.fragment_container, new Fragment_04()).commit();
//                break;
//            case R.id.nav_fragment_5:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.fragment_container, new Fragment_05()).commit();
//                break;
//            default:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.fragment_container, new Fragment_01()).commit();
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//
//        return true;
//    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, fragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addFragment:
                addFragment();
                break;
            case R.id.btn_popFragment:
                popFragment();
                break;
            case R.id.btn_removeFragment:
                removeFragment();
                break;
            default:
                break;
        }
    }

    private void removeFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        //Le fragment courant
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.addToBackStack("Remove fragment - " + fragment.toString());
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, "Aucun fragment Ã  supprimer", Toast.LENGTH_SHORT).show();
        }
    }

    private void popFragment() {
        //On fait appel Ã  la position dans le backStack pour la supprimer
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (backStackEntryCount > 0) {
            //R2cupÃ¨re la derniÃ¨r entrÃ©e dans le backStack
            FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(backStackEntryCount - 1);
            //Supprime le dernier entrÃ©e du backStack
            fragmentManager.popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}