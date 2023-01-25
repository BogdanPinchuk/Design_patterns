package refactoring_guru;

import builders.CarBuilder;
import builders.CarManualBuilder;
import cars.Car;
import cars.Manual;

public class Main {
	public static void main(String[] args) {
		Director director = new Director();

		CarBuilder builder = new CarBuilder();
		director.constuctSportCar(builder);

		Car car = builder.getResult();
		System.out.println("Car built:\n" + car.getCarType());

		CarManualBuilder manualBuilder = new CarManualBuilder();
		director.constuctSportCar(manualBuilder);
		Manual manual = manualBuilder.getResult();
		System.out.println("\nCar manual build:\n" + manual.print());
		

		System.out.println("Finished!");
	}
}
