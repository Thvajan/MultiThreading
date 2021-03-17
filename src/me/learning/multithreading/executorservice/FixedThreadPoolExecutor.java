package me.learning.multithreading.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExecutor {

	public static void main(String[] args) {
		// Single thread that will execute tasks sequentially.
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 16; i++) {
			executorService.execute(new Task(i + 1));
		}
		// Prevents ExecutorService from taking any further tasks
		executorService.shutdown();
		
		try {
			/*awaitTermination waits for all currently running tasks to get completed within the given time.
			If all tasks end within mentioned time, it returns true; else returns false*/
			if(!executorService.awaitTermination(1500, TimeUnit.MILLISECONDS)) {
				/* Forcefull terminates all current tasks */
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class MyTask implements Runnable {
	private int id;

	public MyTask(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Task with Id: " + id + " is running - Thread Id: " + Thread.currentThread().getId());
		long duration = (long) (Math.random() * 5);
		try {
			TimeUnit.SECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}