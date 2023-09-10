

package com.jsfcompref.model;


public class SKU {

    private char [] data;

    public SKU(char [] data) {
        this.data = new char[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    @Override
    public String toString() {
        String result = new String(data);
        return result;
    }

}
