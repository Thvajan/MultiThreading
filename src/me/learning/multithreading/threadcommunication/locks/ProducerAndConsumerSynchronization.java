package me.learning.multithreading.threadcommunication.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumerSynchronization {

	public static void main(String[] args) throws InterruptedException {
		Process p = new Process();

		Thread t1 = new Thread(() -> {
			try {
				p.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "t1");

		Thread t2 = new Thread(() -> {
			try {
				p.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "t2");
		t1.start();
		t2.start();
		Thread.sleep(10000);
		p.setStopped(true);
//		t2.start();
	}
}

class Process {

	private Lock lockObj = new ReentrantLock(true);
	private Condition condition = lockObj.newCondition();
	private List<Integer> list = new ArrayList<>();
	private int low = 0;
	private int high = 5;
	private volatile boolean stopped;
	private int value;

	public void setStopped(boolean b) {
		System.out.println("stopped");
		this.stopped = b;
	}

	public void producer() throws InterruptedException {
		lockObj.lock();
		try {
			while (!stopped) {
				if (list.size() == high) {
					System.out.println("Waiting to be Consumed....");
					condition.await();
					value = 0;
				} else {
					System.out.println("Producing item: " + value);
					list.add(value);
					value++;
					condition.signal();
					Thread.sleep(300);
				}
			}
		} finally {
			lockObj.unlock();
		}
	}

	public void consumer() throws InterruptedException {
		lockObj.lock();
		try {
			while (!stopped) {
				if (list.size() == low) {
					System.out.println("Waiting for items to be produced....");
					condition.await();
				} else {
					System.out.println("Consuming item: " + list.remove(list.size() - 1));
					condition.signal();
					Thread.sleep(500);
				}
			}
		} finally {
			lockObj.unlock();
		}
	}

}
