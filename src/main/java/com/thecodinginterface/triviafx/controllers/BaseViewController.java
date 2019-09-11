package com.thecodinginterface.triviafx.controllers;

public abstract class BaseViewController {
    protected FrontController frontCtrl;

    public void setFrontController(FrontController frontCtrl) {
        this.frontCtrl = frontCtrl;
        initData();
    }

    public FrontController getFrontController() {
        return frontCtrl;
    }

    abstract void initData();
}