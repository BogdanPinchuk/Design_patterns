package refactoring_guru;

import decorators.CompressionDecorator;
import decorators.DataSource;
import decorators.DataSourceDecorator;
import decorators.EncryptionDecorator;
import decorators.FileDataSource;
import java.io.File;

public class Main {
	public static void main(String[] args) {
		String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
		String path = System.getProperty("user.dir") + "\\out";
		if (!new File(path).exists()){
			new File(path).mkdir();
		}

		path += "\\OutputDemo.txt";

		DataSourceDecorator encoded = new CompressionDecorator(
				new EncryptionDecorator(
						new FileDataSource(path)));
		encoded.writeData(salaryRecords);
		DataSource plain = new FileDataSource(path);

		System.out.println("- Input ----------------");
		System.out.println(salaryRecords);
		System.out.println("- Encoded --------------");
		System.out.println(plain.readData());
		System.out.println("- Decoded --------------");
		System.out.println(encoded.readData());

		System.out.println("\nFinished!");
	}
}
