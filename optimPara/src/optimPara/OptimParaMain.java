package optimPara;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class OptimParaMain {

	public static void main(String[] args) {

		System.out.println("Helloworld, this is optimPara!");

		String filePath = "/Users/Philippe/eclipse-workspace/optimPara/ressources/2019-08-25-igcfile-227369-189847.igc";

		ArrayList<Point> pointList = IgcReader.readFile(filePath, 0.05);

		faiFinder finder = new faiFinder(pointList);
		int[] faiIndexes = finder.findOptimum();

		SwingUtilities.invokeLater(() -> {
			MyChart example = new MyChart(pointList, faiIndexes);
			example.setSize(800, 400);
			example.setLocationRelativeTo(null);
			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example.setVisible(true);
		});

	}

}
