package com.caveofprogramming.concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class StartProducerConcumer {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void main(final String[] args) throws InterruptedException {
		System.out.println("StartProducerConcumer - Starting...");

		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					System.out.println(new StringBuilder("StartProducerConcumer - ")
							.append("Error trying to finish process").append(e.getMessage()));
				}
			}
		});
		
		final Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					System.out.println(new StringBuilder("StartProducerConcumer - ")
							.append("Error trying to finish process").append(e.getMessage()));
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

	private static void producer() throws InterruptedException {
		Random random = new Random();

		while (true) {
			queue.put(random.nextInt(100));
		}
	}

	private static void consumer() throws InterruptedException {
		Random random = new Random();

		while (true) {
			Thread.sleep(100);

			if (random.nextInt(10) == 0) {
				Integer value = queue.take();
				System.out.println("StartProducerConcumer - Taken value:" + value + "; Queue size is: " + queue.size());
			}
		}
	}
}
