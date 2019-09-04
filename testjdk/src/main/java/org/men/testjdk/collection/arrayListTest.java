package org.men.testjdk.collection;

import java.util.ArrayList;

public class arrayListTest {

    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        arrayList.add(myArrayList);
        //初始化扩容当数组满之后 oldCapacity + (oldCapacity >> 1)扩容
        for(int i= 100;i>0;i--){
            System.out.println(i+(i>>1)+"：原值"+i+":增加值"+(i>>1) );
        }
    }
}
