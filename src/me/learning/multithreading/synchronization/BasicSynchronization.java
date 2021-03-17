package me.learning.multithreading.synchronization;

public class BasicSynchronization {

	public static int counter1 = 0;
	public static int counter2 = 0;

	public static void process() {
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				increment1Alt();
			}
		}, "t1");

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100000; i++) {
				increment2Alt();
			}
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
		System.out.println("Counter1: " + counter1);
		System.out.println("Counter2: " + counter2);
		System.out.println("Duration: " + (System.currentTimeMillis() - start));
	}

	private static synchronized void increment1() {
		counter1++;
	}

	private static void increment1Alt() {
		//Synchronizing blocks are better than at method level
		synchronized (BasicSynchronization.class) {
			counter1++;
		}
	}

	private static synchronized void increment2() {
		counter2++;
	}

	private static void increment2Alt() {
		synchronized (BasicSynchronization.class) {
			counter2++;
		}
	}

	public static void main(String[] args) {
		process();
	}
}
