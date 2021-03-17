package me.learning.multithreading.threadcommunication.deadlocksandlivelocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockDemo {

	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);

	public void worker1() {
		while (true) {
			try {
				lock1.tryLock(50, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Worker1 got lock1");
			System.out.println("Worker1 tried to get lock2");
			if (lock2.tryLock()) {
				System.out.println("Worker1 got lock2");
				lock2.unlock();
			} else {
				System.out.println("Worker1 unable to get lock2");
				continue;
			}
			break;
		}
		lock1.unlock();
		lock2.unlock();
	}

	public void worker2() {
		while (true) {
			try {
				lock2.tryLock(50, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Worker2 got lock2");
			System.out.println("Worker2 tried to get lock1");
			if (lock1.tryLock()) {
				System.out.println("Worker2 got lock1");
				lock1.unlock();
			} else {
				System.out.println("Worker2 unable to get lock1");
				continue;
			}
			break;
		}
		lock1.unlock();
		lock2.unlock();
	}

	public static void main(String[] args) {

		LiveLockDemo liveLockDemo = new LiveLockDemo();

		new Thread(liveLockDemo::worker1, "Worker1").start();
		new Thread(liveLockDemo::worker2, "Worker2").start();

	}
}
