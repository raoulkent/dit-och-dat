package com.example.myapplicationtest;

public  class Data {
    static int sum = 0;
    String text ;

    public Data(int sum,String text){
        this.sum=sum;
        this.text=text;

    }

public static int getSum(){
        return sum;
}

}
