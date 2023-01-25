package cars;

import components.Engine;
import components.GPSNavigator;
import components.Transmission;
import components.TripComputer;

public class Manual {
	private final CarType carType;
	private final int seats;
	private final Engine engine;
	private final Transmission transmission;
	private final TripComputer tripComputer;
	private final GPSNavigator gpsNavigator;


	public Manual(CarType carType, int seats, Engine engine, Transmission transmission,
				  TripComputer tripComputer, GPSNavigator gpsNavigator) {
		this.carType = carType;
		this.seats = seats;
		this.engine = engine;
		this.transmission = transmission;
		this.tripComputer = tripComputer;
		this.gpsNavigator = gpsNavigator;
	}

	public String print() {
		return new StringBuilder("Type of car: " + carType + "\n")
				.append("Count of seats: ").append(seats).append("\n")
				.append("Engine: volume - ").append(engine.getVolume())
				.append("; mileage - ").append(engine.getMileage()).append("\n")
				.append("Transmission: ").append(transmission).append("\n")
				.append((this.tripComputer != null) ? "Trip Computer: Functional\n" :
						"Trip computer: N/A\n")
				.append((this.gpsNavigator != null) ? "GPS Navigator: Functional\n" :
						"GPS Navigator: N/A\n")
				.toString();
	}
}
