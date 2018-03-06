package com.dream.xukuan.stu12hw;

import java.util.List;

/**
 * @author XK
 * @date 2018/3/6.
 */
public class Person {
    String displayName;
    List<String> nums;

    public Person(String displayName, List<String> nums) {
        this.displayName = displayName;
        this.nums = nums;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getNums() {
        return nums;
    }

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + displayName + '\'' +
                ", nums=" + nums +
                '}';
    }
}