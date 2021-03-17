package me.learning.multithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {

	public static void main(String[] args) {
		// Single thread that will execute tasks sequentially.
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Task(i));
		}
		executorService.shutdown();
	}
}

class Task implements Runnable {
	private int id;

	public Task(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Task with Id: " + id + " is running - Thread Id: " + Thread.currentThread().getName());
		long duration = (long) (Math.random() * 5);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}