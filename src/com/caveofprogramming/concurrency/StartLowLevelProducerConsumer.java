package com.caveofprogramming.concurrency;

import java.util.LinkedList;
import java.util.Random;

class ProcessorLowLevelProducerConsumer {

	private final LinkedList<Integer> list = new LinkedList<Integer>();
	private final static int LIMIT = 10;
	private final Object lock = new Object();

	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				while (list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {

		final Random random = new Random();

		while (true) {
			synchronized (lock) {
				while (list.size() == 0) {
					lock.wait();
				}

				System.out.print("ProcessorLowLevelProducerConsumer - List size is: " + list.size());
				final int value = list.removeFirst();
				System.out.println("; - value is: " + value);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}

public final class StartLowLevelProducerConsumer {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("StartLowLevelProducerConsumer - Starting...");

		final ProcessorLowLevelProducerConsumer process = new ProcessorLowLevelProducerConsumer();
		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.produce();
				} catch (InterruptedException e) {
					System.out.println(new StringBuilder("StartLowLevelProducerConsumer - ")
							.append("Error trying to finish process").append(e.getMessage()));
				}
			}
		});

		final Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.consume();
				} catch (InterruptedException e) {
					System.out.println(new StringBuilder("StartLowLevelProducerConsumer - ")
							.append("Error trying to finish process").append(e.getMessage()));
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}
}
