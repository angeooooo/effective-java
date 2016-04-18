package com.caveofprogramming.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public final class StartAtomicIncrement {

	private final AtomicInteger increment = new AtomicInteger(0);

	private void doWork() {
		final Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment.incrementAndGet();
				}
			}
		});

		final Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment.incrementAndGet();
				}
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (final InterruptedException e) {
			System.out.println(new StringBuilder("StartAtomicIncrement - ")
					.append("Error trying to finish process")
					.append(e.getMessage()));
		}
	}

	public int getIncrement(){
		return increment.get();
	}

	public static void main(final String[] args) {
		final StartAtomicIncrement process = new StartAtomicIncrement();
		process.doWork();
		System.out.println(new StringBuilder("StartAtomicIncrement - ")
				.append("increment: ").append(process.getIncrement()));

	}

}
