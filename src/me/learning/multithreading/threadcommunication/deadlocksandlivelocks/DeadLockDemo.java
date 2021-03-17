package me.learning.multithreading.threadcommunication.deadlocksandlivelocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {

	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);

	public void worker1() {
		lock1.lock();
		System.out.println("Worker1 got lock1");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock2.lock();
		System.out.println("Worker1 got lock2");
		lock1.unlock();
		lock2.unlock();
	}
	
	public void worker2() {
		/*
		 * Deadlock is caused as the workers are acquiring the locks in different order.
		 * In order to avoid deadlock due to cyclic dependency, the locks needs to be
		 * acquired in same order by all threads
		 */
		lock2.lock();
		System.out.println("Worker2 got lock2");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock1.lock();
		System.out.println("Worker2 got lock1");
		lock1.unlock();
		lock2.unlock();
	}

	public static void main(String[] args) {

		DeadLockDemo deadLockDemo = new DeadLockDemo();
		
		new Thread(deadLockDemo::worker1, "Worker1").start();
		new Thread(deadLockDemo::worker2, "Worker2").start();
		
	}
}
