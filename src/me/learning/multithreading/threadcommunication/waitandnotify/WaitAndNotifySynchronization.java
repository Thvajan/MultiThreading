package me.learning.multithreading.threadcommunication.waitandnotify;

public class WaitAndNotifySynchronization {

	public static void main(String[] args) {
		Process p = new Process();

		Thread t1 = new Thread(() -> {
			try {
				p.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "t1");

		Thread t2 = new Thread(() -> {
			try {
				p.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "t2");
		t1.start();
		t2.start();
//		t2.start();
	}
}

class Process {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Started Producing....");
			this.wait();
			System.out.println("Resuming Production....");
		}
	}

	public void consume() throws InterruptedException {
		Thread.sleep(1520);
		synchronized (this) {
			System.out.println("Consumed");
			this.notify();
			//notify only happens when this block is over or this thread goes into wait
			Thread.sleep(1520);
		}
	}

}
