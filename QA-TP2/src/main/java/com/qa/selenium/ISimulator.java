package com.qa.selenium;

public interface ISimulator {
    public void signIn() throws InterruptedException;

    public void signOut() throws InterruptedException;

    public void signUp() throws InterruptedException;

    public void orderProduct() throws InterruptedException;

}
