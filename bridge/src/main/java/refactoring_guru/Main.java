package refactoring_guru;

import devices.Device;
import devices.Radio;
import devices.TV;
import remotes.AdvancedRemote;
import remotes.BasicRemote;

public class Main {
	public static void main(String[] args) {
		testDevice(new TV());
		testDevice(new Radio());

		System.out.println("\nFinished!");
	}

	public static void testDevice(Device device) {
		System.out.println("Test with basic remote.");
		BasicRemote basicRemote = new AdvancedRemote(device);
		basicRemote.power();
		device.printStatus();

		System.out.println("Test with advanced remote.");
		AdvancedRemote advancedRemote = new AdvancedRemote(device);
		advancedRemote.power();
		advancedRemote.mute();
		device.printStatus();
	}
}
