package com.jsfcompref.model;

import java.util.Date;

public class SimpleBean {

    private int number = 0;
    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Holds value of property prime.
     */
    private float prime = 0.07f;

    /**
     * Getter for property prime.
     * @return Value of property prime.
     */
    public float getPrime() {
        return this.prime;
    }

    /**
     * Setter for property prime.
     * @param prime New value of property prime.
     */
    public void setPrime(float prime) {
        this.prime = prime;
    }

    /**
     * Holds value of property sku.
     */
    private SKU sku;

    /**
     * Getter for property sku.
     * @return Value of property sku.
     */
    public SKU getSku() {
        return this.sku;
    }

    /**
     * Setter for property sku.
     * @param sku New value of property sku.
     */
    public void setSku(SKU sku) {
        this.sku = sku;
    }

    /**
     * Holds value of property date.
     */
    private Date date = new Date();

    /**
     * Getter for property date.
     * @return Value of property date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Setter for property date.
     * @param date New value of property date.
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
