package com.caveofprogramming.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class StartMultipleLocks {

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private Random random = new Random();

	private List<Integer> list1 = new ArrayList<Integer>();

	public List<Integer> getList1() {
		return list1;
	}

	public List<Integer> getList2() {
		return list2;
	}

	private List<Integer> list2 = new ArrayList<Integer>();

	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}

	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public static void main(final String[] args) {
		System.out.println("StartMultipleLocks - Starting...");
		final StartMultipleLocks process = new StartMultipleLocks();

		final long start = System.currentTimeMillis();

		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process.process();
			}
		});

		final Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process.process();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (final InterruptedException e) {
			System.out.println(new StringBuilder("StartMultipleLocks - ").append("Error trying to finish process")
					.append(e.getMessage()));
		}
		final long end = System.currentTimeMillis();

		System.out.println("StartMultipleLocks - Time take: " + (end - start));
		System.out.println(
				"StartMultipleLocks - List1: " + process.getList1().size() + ", List2: " + process.getList2().size());
	}

}
