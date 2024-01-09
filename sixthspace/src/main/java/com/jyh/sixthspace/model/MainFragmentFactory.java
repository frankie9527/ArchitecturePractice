package com.jyh.sixthspace.model;


import android.util.SparseArray;

import androidx.fragment.app.Fragment;

import com.jyh.sixthspace.live.ui.fragment.LiveMainFragment;
import com.jyh.sixthspace.ui.fragment.MoviesFragment;
import com.jyh.sixthspace.ui.fragment.RecommendFragment;
import com.jyh.sixthspace.ui.fragment.TeleVisionFragment;

/**
 * Created by Administrator on 2017/4/13.
 */

public class MainFragmentFactory {
  private  static SparseArray<Fragment> map=new SparseArray<Fragment>();
    public static Fragment getFragment(int posistion){
        Fragment  currentFragment=map.get(posistion) ;
          if (currentFragment==null){
              switch (posistion){
                  case 0:
                    map.put(0,new RecommendFragment());
                      break;
                  case 1:
                      map.put(1,new MoviesFragment());
                      break;
                  case 2:
                      map.put(2,new LiveMainFragment());
                      break;
                  case 3:
                      map.put(3,new TeleVisionFragment());
                      break;
                  default:
                      break;
              }
              currentFragment=map.get(posistion);
          }
        return currentFragment;
    }
}
