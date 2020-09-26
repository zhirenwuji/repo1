package com.h3c.weixin;



import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] arr = {4,2,5,1,9,3};
        for (int i = 1; i < arr.length-1; i++) {
            for (int j = i-1; j >= 0; j++) {
                int temp = arr[j];
                if (arr[j]>arr[j+1]){
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println("uuuuuuuuuu2222");
        System.out.println("uuuuuuuuuu");
        System.out.println("uuuuuuuuuu");
        System.out.println("uuuuuuuuuu");

        System.out.println(Arrays.toString(arr));
        System.out.println("++++++++++");


    }


}
