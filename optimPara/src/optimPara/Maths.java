package optimPara;

import org.apache.commons.math3.util.FastMath;

public class Maths {

	/**
	 * @param p1
	 * @param p2
	 * @return distance in km
	 */
	public static double computeDistanceExact(Point p1, Point p2) {
		double R = 6378.0; // Radius of the earth in km

		double lat1 = p1.getLatitude();
		double lat2 = p2.getLatitude();
		double lon1 = p1.getLongitude();
		double lon2 = p2.getLongitude();

		double dLat = lat2 - lat1;
		double dLon = lon2 - lon1;

		double sinLat2 = FastMath.sin(dLat / 2);
		double sinLon2 = FastMath.sin(dLon / 2);
		double a = sinLat2 * sinLat2 + FastMath.cos(lat1) * FastMath.cos(lat2) * sinLon2 * sinLon2;
		double c = 2 * FastMath.atan2(Math.sqrt(a), FastMath.sqrt(1 - a));

		return R * c; // Distance in km

	}

	public static double fastComputeDistance(Point p1, Point p2) {
		double x = (p2.getLongitude() - p1.getLongitude()) * Math.cos((p1.getLatitude() + p2.getLatitude()) / 2);

		double y = (p2.getLatitude() - p1.getLatitude());

		return Math.sqrt(x * x + y * y);
	}

}
