package me.learning.multithreading.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorWithFuture {

	public static void main(String[] args) {
		// Single thread that will execute tasks sequentially.
		ExecutorService executorService = Executors.newSingleThreadExecutor();
//		for (int i = 0; i < 5; i++) {
//		Future<Integer> submit = executorService.submit(new CallableEx(10));
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("Task with Id: " + 2 + " is running - Thread Id: " + Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 2;
		});
		System.out.println("Getting Completable future..");
		cf.thenAccept((i) -> System.out.println(i));
		try {
			cf.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");
//		try {
//			System.out.println("Getting future..");
//			System.out.println(submit.get());
//			System.out.println("Done");
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//		}
		executorService.shutdown();
	}
}

class CallableEx implements Callable<Integer> {
	private int id;

	public CallableEx(int id) {
		this.id = id;
	}

	@Override
	public Integer call() {
		System.out.println("Task with Id: " + id + " is running - Thread Id: " + Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return id;
	}

}