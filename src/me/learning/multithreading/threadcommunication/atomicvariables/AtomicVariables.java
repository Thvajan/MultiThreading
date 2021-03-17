package me.learning.multithreading.threadcommunication.atomicvariables;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariables {

	private static AtomicInteger counter = new AtomicInteger(0);
	
	public static void main(String[] args) {
		AtomicVariables atomicVariables = new AtomicVariables();
		
		Thread t1 = new Thread(()->increment());
		Thread t2 = new Thread(()->increment());
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(counter);
	}
	
	public static void increment() {
//		synchronized (AtomicVariables.class) {
			for (int i = 0; i < 10000; i++) {
				counter.getAndAdd(2);
			}
//		}
	}
}
