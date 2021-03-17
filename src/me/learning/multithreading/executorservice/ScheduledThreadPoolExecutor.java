package me.learning.multithreading.executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor {

	public static void main(String[] args) {
		// Single thread that will execute tasks sequentially.
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(new StockMarketUpdator(), 5000, 1000, TimeUnit.MILLISECONDS);
	}
}

class StockMarketUpdator implements Runnable {

	@Override
	public void run() {
		System.out.println("Downloading stock market latest data..");
	}

}