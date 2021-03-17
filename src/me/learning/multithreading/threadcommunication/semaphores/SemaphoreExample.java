package me.learning.multithreading.threadcommunication.semaphores;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 25; i++) {
			executorService.execute(()->{
				Downloader.INSTANCE.downloadData();
			});
		}
		executorService.shutdown();
	}
}

enum Downloader{
	
	INSTANCE;
	
	private Semaphore semaphore = new Semaphore(4, true);
	
	public void downloadData() {
		try {
			semaphore.acquire();
			download();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			semaphore.release();
		}
	}

	void download() {
		System.out.println("Downloading data...");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}