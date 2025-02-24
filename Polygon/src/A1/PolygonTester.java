
package A1;

/**
 * PolygonTester should enable a thorough testing of the polygon hierarchy.
 * 
 * It should provide an easy to read input-output recording of the test cases.
 * 
 * The student should also submit these recorded test results in TestIO.txt file
 * as part of Assignment1.
 * 
 * @author Andy Mirzaian
 */
public class PolygonTester {

	// TODO: place additional test-helper methods here if you like
	public static void main(String[] args) {
		SimplePolygon simplePolygon = new SimplePolygon().getNewPoly();
		System.out.println(simplePolygon.toString());
	}

}
