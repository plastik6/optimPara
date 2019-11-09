package optimPara;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Point {
	private final double latitude;
	private final double longitude;
	private final Date date;

	/**
	 * @param latitude  in rad
	 * @param longitude in rad
	 */
	public Point(Date date, double latitude, double longitude) {
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;

	}

	/**
	 * @return latitude in rad
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return longitude in rad
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the passage date
	 */
	public Date getDate() {
		return date;
	}

	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		return String.format("Heure : %s | Latitude : %7.8f | Longitude : %7.8f",
				dateFormat.format(this.getDate().getTime()), Math.toDegrees(this.getLatitude()), this.getLongitude());

	}

}
