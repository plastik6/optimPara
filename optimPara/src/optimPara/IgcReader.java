package optimPara;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class IgcReader {

	private final static int hourStart = 1;
	private final static int hourEnd = 3;

	private final static int minStart = 3;
	private final static int minEnd = 5;

	private final static int secStart = 5;
	private final static int secEnd = 7;

	private final static int latitudeDDStart = 7;
	private final static int latitudeDDEnd = 9;

	private final static int latitudeMMStart = 9;
	private final static int latitudeMMEnd = 11;

	private final static int latitudeMmmStart = 11;
	private final static int latitudeMmmEnd = 14;

	private final static int longitudeDDStart = 15;
	private final static int longitudeDDEnd = 18;

	private final static int longitudeMMStart = 18;
	private final static int longitudeMMEnd = 20;

	private final static int longitudeMmmStart = 20;
	private final static int longitudeMmmEnd = 23;

	public static ArrayList<Point> readFile(String filePath, double samplingThreshold) {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		ArrayList<Point> pointList = new ArrayList<Point>();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null) {

				if (line.startsWith("B")) {

					// line.substring(beginIndex, endIndex);

					Calendar date = Calendar.getInstance();
					date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(line.substring(hourStart, hourEnd)));
					date.set(Calendar.MINUTE, Integer.parseInt(line.substring(minStart, minEnd)));
					date.set(Calendar.SECOND, Integer.parseInt(line.substring(secStart, secEnd)));

					double lat = Math.toRadians(Double.parseDouble(line.substring(latitudeDDStart, latitudeDDEnd))
							+ Double.parseDouble(line.substring(latitudeMMStart, latitudeMMEnd)) / 60.0
							+ Double.parseDouble(line.substring(latitudeMmmStart, latitudeMmmEnd)) / 60000.0);

					double longi = Math.toRadians(Double.parseDouble(line.substring(longitudeDDStart, longitudeDDEnd))
							+ Double.parseDouble(line.substring(longitudeMMStart, longitudeMMEnd)) / 60.0
							+ Double.parseDouble(line.substring(longitudeMmmStart, longitudeMmmEnd)) / 60000.0);

					System.out.println(String.format("%s | Heure : %s | Latitude : %7.8f | Longitude : %7.8f", line,
							dateFormat.format(date.getTime()), lat, longi));

					Point newPoint = new Point(date.getTime(), lat, longi);

					if (pointList.size() == 0) {
						pointList.add(newPoint);

					} else {

						Point lastPoint = pointList.get(pointList.size() - 1);

						if (Maths.computeDistanceExact(newPoint, lastPoint) > samplingThreshold) {

							pointList.add(newPoint);
						}
					}
				}

				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Total points number : " + pointList.size());
		return pointList;
	}

}
