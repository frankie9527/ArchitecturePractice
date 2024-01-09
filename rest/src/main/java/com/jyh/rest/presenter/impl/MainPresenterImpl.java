package com.jyh.rest.presenter.impl;


import com.jyh.rest.base.LazyFragment;
import com.jyh.rest.interactor.MainInteractor;
import com.jyh.rest.interactor.impl.MainInteractorImpl;
import com.jyh.rest.presenter.MainPresenter;
import com.jyh.rest.view.MainView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        interactor = new MainInteractorImpl();
    }

    @Override
    public void initialized() {
        List<LazyFragment> list_tab = interactor.getTabFragment();
        view.onloadSuccess(list_tab);
    }
}
