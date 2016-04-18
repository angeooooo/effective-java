package com.caveofprogramming.concurrency;

import java.util.Scanner;

class ProcessorWaitAndNotify {
	
	public void produce() throws InterruptedException {
		synchronized(this) {
			System.out.println("ProcessorWaitAndNotify - Producer thread running...");
			wait();
			System.out.println("ProcessorWaitAndNotify - Resumed...");
		}
	}
	
	public void consume() throws InterruptedException {
		final Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized(this) {
			System.out.println("ProcessorWaitAndNotify - Waiting for return key.");
			scanner.nextLine();
			System.out.println("ProcessorWaitAndNotify - Return key pressed.");
			notify();
			Thread.sleep(5000);
			scanner.close();
		}
	}
}

public final class StartWaitAndNotify {

	public static void main(final String[] args) throws InterruptedException {
		System.out.println("StartWaitAndNotify - Starting...");
		
		final ProcessorWaitAndNotify process = new ProcessorWaitAndNotify();
		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					process.produce();
				} catch (InterruptedException e) {
					System.out.println(new StringBuilder("StartWaitAndNotify - ")
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
					System.out.println(new StringBuilder("StartWaitAndNotify - ")
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
