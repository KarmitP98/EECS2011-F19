/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Winter 2019
 * Assignment 1: Polygon Hierarchy
 * Section:  M  or  Z ?
 * Student Name:   ?
 * Student eecs account:  ?
 * Student ID number:  ? 
 **********************************************************/
package A1;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Scanner;

/**
 * The class SimplePolygon implements the Polygon interface.
 * 
 * It is intended to be further extended by ConvexPolygon.
 * 
 * 
 */
public class SimplePolygon implements Polygon {

	/********* protected fields ************************/

	protected int n; // number of vertices of the polygon
	protected Point2D.Double[] vertices; // vertices[0..n-1] around the polygon boundary

	/********* protected constructors ******************/

	/**
	 * constructor used in the static factory method getNewPoly()
	 * 
	 * @param size number of edges (also vertices) of the polygon
	 */
	protected SimplePolygon(int size) {
		this.n = size;
		Scanner sc = new Scanner(System.in);
		vertices = new Point2D.Double[size];

		for (int i = 0; i < n; i++)
			vertices[i] = new Point2D.Double(sc.nextDouble(), sc.nextDouble());

	}

	/** default no-parameter constructor */
	protected SimplePolygon() {

	}

	/********* public getters & toString ***************/

	/**
	 * static factory method constructs and returns an unverified simple-polygon,
	 * initialised according to user provided input data. Runs in O(n) time.
	 * 
	 * @return an unverified simple-polygon instance
	 */
	public static SimplePolygon getNewPoly() {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		SimplePolygon p = new SimplePolygon(size);

		return p;
	}

	/**
	 * 
	 * @return n, the number of edges (equivalently, vertices) of the polygon.
	 */
	public int getSize() {
		return n;
	}

	/**
	 * 
	 * @param i index of the vertex.
	 * @return the i-th vertex of the polygon.
	 * @throws IndexOutOfBoundsException if {@code i < 0 || i >= n }.
	 */

	public Point2D.Double getVertex(int i) throws IndexOutOfBoundsException {
		try {
			return vertices[i]; // TODO: replace this line with a try-catch code
		} catch (Exception e) {
			System.out.println("Out of bounds");
		}
		return null;

	}

	/**
	 * @return a String representation of the polygon in O(n) time.
	 */
	@Override
	public String toString() {
		return "xxx"; // TODO: replace this line with your code
	}

	/************** utilities *********************/

	/**
	 * 
	 * @param a
	 * @param b
	 * @param c three input points.
	 * @return twice the signed area of the oriented triangle (a,b,c). Runs in O(1)
	 *         time.
	 */
	public static double delta(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
		/*
		 * double determinant = (a.getX() * ((b.getY()) - (c.getY()))) - (a.getY() *
		 * (b.getX() - c.getX())) + ((b.getX() * c.getY()) - (c.getX() * b.getY()));
		 */

		double det = (b.getX() * c.getY() - c.getX() * b.getY()) - (a.getX() * c.getY() - a.getY() * c.getX())
				+ (a.getX() * b.getY() - a.getY() * b.getX());
		//System.out.println("TEST:: Delta: " + det + " Points: " + a + " " + b + " " + c);
		return det;
	}

	/**
	 * @param a
	 * @param b end points of line-segment (a,b).
	 * @param c
	 * @param d end points of line-segment (c,d).
	 * @return true if closed line-segments (a,b) and (c,d) are disjoint. Runs in
	 *         O(1) time.
	 */
	public static boolean disjointSegments(Point2D.Double a, Point2D.Double b, Point2D.Double c, Point2D.Double d) {

		int dir1 = orientation(a, b, c), dir2 = orientation(a, b, d), dir3 = orientation(c, d, a),
				dir4 = orientation(c, d, b);

		if (dir1 != dir2 && dir3 != dir4)
			return true;
		if (dir1 == 0 && onLine(a, b, c)) // when p2 of line2 are on the line1
			return true;
		if (dir2 == 0 && onLine(a, b, d)) // when p1 of line2 are on the line1
			return true;
		if (dir3 == 0 && onLine(c, d, a)) // when p2 of line1 are on the line2
			return true;
		if (dir4 == 0 && onLine(c, d, b)) // when p1 of line1 are on the line2
			return true;

		return false;

	}

	/**
	 * @param i
	 * @param j indices of two edges of the polygon.
	 * @return true if the i-th and j-th edges of the polygon are disjoint. Runs in
	 *         O(1) time.
	 * @throws IndexOutOfBoundsException if i or j are outside the index range
	 *                                   [0..n-1].
	 */
	public boolean disjointEdges(int i, int j) throws IndexOutOfBoundsException {
		if (i < 0 || i > n - 1 || j < 0 || j > n - 1)
			throw new IndexOutOfBoundsException();

		Point2D.Double a, b, c, d;

		a = vertices[i];
		if (i == n - 1)
			b = vertices[0];
		else
			b = vertices[i + 1];

		c = vertices[j];
		if (j == n - 1)
			d = vertices[0];
		else
			d = vertices[j + 1];

		return disjointSegments(a, b, c, d);

	}

	public static int orientation(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		int val = (int) ((p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y));

		if (val == 0)
			return 0;

		return (val > 0) ? 1 : 2;
	}

	public static boolean onLine(Point2D.Double a, Point2D.Double b, Point2D.Double p) {

		if (p.x <= Math.max(a.x, b.x) && p.x <= Math.min(a.x, b.x)
				&& (p.y <= Math.max(a.y, b.y) && p.y <= Math.min(a.y, b.y)))
			return true;

		return false;
	}

	/**
	 * This method verifies whether the claimed "simple-polygon" is indeed simple.
	 * 
	 * @return true if the polygon is simple. Runs in O(n^2) time.
	 */
	public boolean isSimple() {
		boolean simple = true;

		for (int i = 0; i < vertices.length - 1; i++) {
			for (int j = 0; j < n; j++) {
				if (disjointEdges(i, j) == true) {
					simple = false;
					break;
				}
			}
		}

		return simple;

	}

	/************ perimeter & area ***************/

	/**
	 * 
	 * @return the sum of the edge lengths of the polygon. Runs in O(n) time.
	 */
	public double perimeter() {
		double perimeter = 0.0;
		for (int i = 0; i < n; i++) {
			if (i == n - 1)
				perimeter = perimeter + (vertices[i].distance(vertices[0]));
			else
				perimeter = perimeter + (vertices[i].distance(vertices[i + 1]));

		}
		return perimeter;
	}

	/**
	 * 
	 * @return area of the polygon interior. Runs in O(n) time not counting the
	 *         simplicity test.
	 * @throws NonSimplePolygonException if the polygon is non-simple.
	 */

	public double area() throws NonSimplePolygonException {

		Point2D.Double origin = new Point2D.Double(0, 0);
		double area = 0.0, sum = 0.0;
		for (int x = 0; x < n; x++) {
			if (x == n - 1)
				sum = sum + delta(origin, vertices[x], vertices[0]);
			else
				sum = sum + delta(origin, vertices[x], vertices[x + 1]);
		}
		return area = 0.5 * Math.abs(sum);
	}

}