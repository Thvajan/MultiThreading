package me.learning.multithreading.threadcommunication.waitandnotify;

import java.util.ArrayList;
import java.util.List;

public class ProducerAndConsumerSynchronization {

	public static void main(String[] args) throws InterruptedException {
		Process1 p = new Process1();

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

class Process1 {

	private List<Integer> list = new ArrayList<>();
	private int low = 0;
	private int high = 5;
	private final Object lock = new Object();
	private boolean stopped;
	private int value;

	public void setStopped(boolean b) {
		this.stopped = b;
	}

	public void producer() throws InterruptedException {
		synchronized (lock) {
			while (!stopped) {
				if (list.size() == high) {
					System.out.println("Waiting to be Consumed....");
					lock.wait();
					value=0;
				} else {
					System.out.println("Producing item: " + value);
					list.add(value);
					value++;
					lock.notify();
					Thread.sleep(300);
				}
			}
		}
	}

	public void consumer() throws InterruptedException {
		synchronized (lock) {
			while (!stopped) {
				if (list.size() == low) {
					System.out.println("Waiting for items to be produced....");
					lock.wait();
				} else {
					System.out.println("Consuming item: " + list.remove(list.size() - 1));
					lock.notify();
					Thread.sleep(500);
				}
			}
		}
	}

}
