package com.caveofprogramming.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	
	private int id;
	
	public Processor(final int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Processor - Starting: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			System.out.println(new StringBuilder("Processor - ")
					.append("Error trying to finish process")
					.append(e.getMessage()));
		}
		
		System.out.println("Processor - Completed: " + id);

	}
}

public class StartThreadPool {

	public static void main(final String[] args) {
		final ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i =0; i<5;i++) {
			final Processor process = new Processor(i);
			executor.submit(process);
		}
		
		executor.shutdown();
		
		System.out.println("StartThreadPool - All tasks submitted");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			System.out.println(new StringBuilder("StartThreadPool - ")
					.append("Error trying to finish process")
					.append(e.getMessage()));
		}
	}

}


