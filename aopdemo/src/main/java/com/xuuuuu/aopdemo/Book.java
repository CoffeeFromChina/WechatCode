package com.xuuuuu.aopdemo;

public class Book {
  // 连接点（Joinpoint）。类里面可以被增强的方法，这些方法称为连接点
  public void add() {
    System.out.println("add...........");
  }
}
