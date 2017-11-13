package javen.example.com.smartnews.main.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import javen.example.com.smartnews.R;
import javen.example.com.smartnews.main.fragment.ALittleVideoFragment;
import javen.example.com.smartnews.main.fragment.HomeFragment;
import javen.example.com.smartnews.main.fragment.PersonFragment;
import javen.example.com.smartnews.main.fragment.PlayVideoFragment;

/**
 * Created by Javen on 13/11/2017.
 */

public class MainHelper {

    public static final int FIRST_PAGE = 0;
    public static final int SECOND_PAGE = 1;
    public static final int THIRD_PAGE = 2;
    public static final int FOURTH_PAGE = 3;

    private Fragment homeFragment;
    private Fragment playVideoFragment;
    private Fragment aLittleVideoFragment;
    private Fragment personFragment;
    private static MainHelper mainHelper;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static MainHelper getInstance(Context context) {
        if (mainHelper == null) {
            mainHelper = new MainHelper(context);
        }

        return mainHelper;
    }

    public MainHelper(Context context) {
        fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
    }


    public void switchFragmentByClickBottomNavigationView(int pageNum) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);

        switch (pageNum) {
            case FIRST_PAGE:
                showFirstPage(fragmentTransaction);
                break;
            case SECOND_PAGE:
                showSecondPage(fragmentTransaction);
                break;
            case THIRD_PAGE:
                showThirdPage(fragmentTransaction);
                break;
            case FOURTH_PAGE:
                showFourthPage(fragmentTransaction);
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }

        if (playVideoFragment != null) {
            fragmentTransaction.hide(playVideoFragment);
        }

        if (aLittleVideoFragment != null) {
            fragmentTransaction.hide(aLittleVideoFragment);
        }

        if (personFragment != null) {
            fragmentTransaction.hide(personFragment);
        }
    }

    private void showFirstPage(FragmentTransaction fragmentTransaction) {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.container, homeFragment);
        } else {
            fragmentTransaction.show(homeFragment);
        }
    }

    private void showSecondPage(FragmentTransaction fragmentTransaction) {
        if (playVideoFragment == null) {
            playVideoFragment = new PlayVideoFragment();
            fragmentTransaction.add(R.id.container, playVideoFragment);
        } else {
            fragmentTransaction.show(playVideoFragment);
        }
    }

    private void showThirdPage(FragmentTransaction fragmentTransaction) {
        if (aLittleVideoFragment == null) {
            aLittleVideoFragment = new ALittleVideoFragment();
            fragmentTransaction.add(R.id.container, aLittleVideoFragment);
        } else {
            fragmentTransaction.show(aLittleVideoFragment);
        }
    }

    private void showFourthPage(FragmentTransaction fragmentTransaction) {
        if (personFragment == null) {
            personFragment = new PersonFragment();
            fragmentTransaction.add(R.id.container, personFragment);
        } else {
            fragmentTransaction.show(personFragment);
        }
    }

}
