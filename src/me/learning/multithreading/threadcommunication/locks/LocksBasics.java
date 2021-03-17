package me.learning.multithreading.threadcommunication.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LocksBasics {

	private static int counter = 0;
	private static Lock lockObj = new ReentrantLock(true);

	public static void increment() {
		lockObj.lock();
		try {
			for (int i = 0; i < 10000; i++) {
				counter++;
			}
		} finally{
			lockObj.unlock();
		}
	}
	
	public static void unlockObj() {
		//to show the lock can be unlocked in any other method
		lockObj.unlock();
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			increment();
		}, "t1");

		Thread t2 = new Thread(() -> {
			increment();
			}, "t2");
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Counter: "+counter);
	}
}
