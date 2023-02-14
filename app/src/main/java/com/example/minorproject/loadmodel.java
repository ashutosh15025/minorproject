package com.example.minorproject;

import java.util.Random;

public class loadmodel {
    public int load(){
        Random rand=new Random();
        int i1= rand.nextInt(100-0)+0;
        return i1/25;
    }

}
