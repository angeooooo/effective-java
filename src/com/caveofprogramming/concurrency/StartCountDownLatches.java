package com.caveofprogramming.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ProcessorLatch implements Runnable {

	private CountDownLatch latch;

	public ProcessorLatch(final CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("ProcessorLatch - Starting: ");

		try {
			Thread.sleep(3000);
		} catch (final InterruptedException e) {
			System.out.println(new StringBuilder("ProcessorLatch - ").append("Error trying to finish process")
					.append(e.getMessage()));
		}

		latch.countDown();

		System.out.println("ProcessorLatch - Completed: ");

	}
}

public class StartCountDownLatches {

	public static void main(final String[] args) {
		final CountDownLatch latch = new CountDownLatch(3);
		final ExecutorService executor = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 5; i++) {
			final ProcessorLatch process = new ProcessorLatch(latch);
			executor.submit(process);
		}
		executor.shutdown();

		System.out.println("StartCountDownLatches - All tasks submitted");

		try {
			latch.await();
		} catch (InterruptedException e) {
			System.out.println(new StringBuilder("StartCountDownLatches - ").append("Error trying to finish process")
					.append(e.getMessage()));
		}
	}

}
