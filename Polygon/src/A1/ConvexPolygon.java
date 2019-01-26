/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Winter 2019
 * Assignment 1: Polygon Hierarchy
 * Section: Z 
 * Student Name:  Karmit Patel
 * Student eecs account: kk07
 * Student ID number:  215688211
 **********************************************************/
package A1;

import java.awt.geom.Point2D;

/**
 * The class ConvexPolygon extends SimplePolygon.
 * 
 * TODO:_______ Add more Javadoc comments here. ______ ???
 * 
 * @author Andy Mirzaian
 */
public class ConvexPolygon extends SimplePolygon {

	private Point2D.Double[] vert;
	private double oSlope, nSlope;
	private int size, dir;
	private boolean convex = true;

	public boolean isConvex() {
		SimplePolygon poly = new SimplePolygon().getNewPoly();
		vert = poly.vertices;
		size = poly.getSize();
		dir = 1;

		oSlope = (vertices[1].getY() - vertices[0].getY()) / (vertices[1].getX() - vertices[0].getX());

		for (int i = 1; i < n; i++) {
			Point2D.Double p1 = vertices[i];
			Point2D.Double p2;
			if (i == n - 1)
				p2 = vertices[0];
			else
				p2 = vertices[n + 1];

			nSlope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());

			if (i == 1) {
				if (nSlope <= oSlope) {
					dir = 1;
				} else {
					dir = -1;
				}
			} else {
				if (dir == 1 && nSlope >= oSlope) {
					convex = false;
					break;
				} else if (dir == -1 && nSlope < oSlope) {
					convex = false;
					break;
				}
			}

		}

		return convex;
	}
}
