package refactoring_guru;

import correct.CorrectSingelton;
import correct.Singleton;
import incorrect.IncorrectSingelton;

public class Main {
	public static void main(String[] args) {
		System.out.println("First task: correct 1 thread");
		System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
				"If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
				"RESULT:" + "\n");

		Singleton singleton = Singleton.getInstance("FOO");
		Singleton anotherSingleton = Singleton.getInstance("BAR");
		System.out.println(singleton.value);
		System.out.println(anotherSingleton.value);

		System.out.println("\nSecond task: incorrect multi-threads");
		System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
				"If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
				"RESULT:" + "\n");
		Thread threadFoo = new Thread(new ThreadFoo());
		Thread threadBar = new Thread(new ThreadBar());
		threadFoo.start();
		threadBar.start();

		System.out.println("\nThird task: incorrect multi-threads");
		System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
				"If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
				"RESULT:" + "\n");
		Thread threadFooM = new Thread(new ThreadFooM());
		Thread threadBarM = new Thread(new ThreadBarM());
		threadFooM.start();
		threadBarM.start();

		System.out.println("\nFinished!");
	}

	static class ThreadFoo implements Runnable {
		@Override
		public void run() {
			IncorrectSingelton singleton = IncorrectSingelton.getInstance("FOO");
			System.out.println(singleton.value);
		}
	}

	static class ThreadBar implements Runnable{
		@Override
		public void run() {
			IncorrectSingelton singleton = IncorrectSingelton.getInstance("BAR");
		System.out.println(singleton.value);
		}
	}

	static class ThreadFooM implements Runnable {
		@Override
		public void run() {
			CorrectSingelton singleton = CorrectSingelton.getInstance("FOO");
			System.out.println(singleton.value);
		}
	}

	static class ThreadBarM implements Runnable{
		@Override
		public void run() {
			CorrectSingelton singleton = CorrectSingelton.getInstance("BAR");
			System.out.println(singleton.value);
		}
	}
}
