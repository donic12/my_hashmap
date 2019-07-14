package jdk7_myhashmap;

public class Test01 {
    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.put("1号","aaaa");
        myHashMap.put("2号","bbbb");
        myHashMap.put("3号","cccc");
        myHashMap.put("4号","dddd");
        myHashMap.put("5号","eeee");
        myHashMap.put("6号","ffff");
        myHashMap.put("7号","gggg");
        myHashMap.put("14号","hhhh");
        myHashMap.print();
    }
}
