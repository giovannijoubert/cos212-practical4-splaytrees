/**
 * Name:
 * 
 * Student Number:
 */

/**
 * A note on testing:
 * 
 * This Main takes a different approach to testing. Previously we printed
 * information to STDOUT and verified the results manually.
 * 
 * This Main uses a very simple testing framework, implemented in the Main class
 * itself. It provides a structure for testing and validates the results at run
 * time.
 * 
 * This provides the following advantages:
 * 
 * - Regression testing - Often as you try to fix one bug, you introduce another
 * bug somewhere else. Using this Main (or a proper unit testing framework such
 * as TestNG) you can be sure that old functions still work as intended after
 * refactoring.
 * 
 * - Quick and easy validation - The framework will give a summary, indicating
 * if all test passed. This saves time and effort as validation is done by the
 * framework and not by a fallible human eye (of course, the tests also need to
 * be correct, otherwise you will be implementing the wrong functionality
 * correctly).
 * 
 * - Understanding of specs and requirements - Your understanding will be tested
 * by writing the test cases first. It it much easier to fix the test cases and
 * a tree on pen and paper, than it is to fix the tree implementation.
 * 
 * You may use the provided framework (can be modified to suit your needs) or
 * your own way of testing. This is just an example of an alternative way of
 * testing.
 */

public class Main {

    public static void main(String[] args) {

        // NOTE: this Main only tests two of the many cases.
        // You are advised to write your own comprehensive test cases.

        SplayTree<Integer> tree = new SplayTree<>();

        startTestCase("Create tree with 'CASE 1' structure");
        // Tests the insert function

        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);

        assertEquals(tree.root.key, 20);
        assertEquals(tree.root.right.key, 30);
        assertEquals(tree.root.left.key, 10);
        assertEquals(tree.root.left.left.key, 5);
        assertEquals(tree.root.left.right.key, 15);

        endTestCase();

        startTestCase("Splaying 'CASE 1' case");
        // Tests the access function
        tree.access(10);

        assertEquals(tree.root.key, 10);
        assertEquals(tree.root.left.key, 5);
        assertEquals(tree.root.right.key, 20);
        assertEquals(tree.root.right.left.key, 15);
        assertEquals(tree.root.right.right.key, 30);

        endTestCase();


        tree = new SplayTree<>();

        startTestCase("Create tree with 'CASE 2' structure");
        // Tests the insert function

        tree.insert(170);
        tree.insert(160);
        tree.insert(200);
        tree.insert(150);
        tree.insert(165);
        tree.insert(150);
        tree.insert(140);
        tree.insert(155);
        tree.insert(130);
        tree.insert(145);
        tree.insert(135);
        tree.insert(120);

        assertEquals(tree.root.key, 170);
        assertEquals(tree.root.right.key, 200);
        assertEquals(tree.root.left.key, 160);
        assertEquals(tree.root.left.right.key, 165);
        assertEquals(tree.root.left.left.key, 150);
        assertEquals(tree.root.left.left.right.key, 155);
        assertEquals(tree.root.left.left.left.key, 140);
        assertEquals(tree.root.left.left.left.right.key, 145);
        assertEquals(tree.root.left.left.left.left.key, 130);
        assertEquals(tree.root.left.left.left.left.right.key, 135);
        assertEquals(tree.root.left.left.left.left.left.key, 120);
        
        

        endTestCase();

        startTestCase("Splaying 'CASE 2' case");
        // Tests the access function
        tree.access(130);

        assertEquals(tree.root.key, 160);
        assertEquals(tree.root.right.key, 170);
        assertEquals(tree.root.right.right.key, 200);
        assertEquals(tree.root.right.left.key, 165);
        assertEquals(tree.root.left.key, 140);
        assertEquals(tree.root.left.right.key, 150);
        assertEquals(tree.root.left.right.right.key, 155);
        assertEquals(tree.root.left.right.left.key, 145);
        assertEquals(tree.root.left.left.key, 130);
        assertEquals(tree.root.left.left.left.key, 120);
        assertEquals(tree.root.left.left.right.key, 135);
    

        endTestCase();


        tree = new SplayTree<>();

        startTestCase("Create tree with 'CASE 3' structure");
        // Tests the insert function

        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12);
        tree.insert(18);

        assertEquals(tree.root.key, 20);
        assertEquals(tree.root.right.key, 30);
        assertEquals(tree.root.left.key, 10);
        assertEquals(tree.root.left.right.key, 15);
        assertEquals(tree.root.left.left.key, 5);
        assertEquals(tree.root.left.right.right.key, 18);
        assertEquals(tree.root.left.right.left.key, 12);

        
        

        endTestCase();

        startTestCase("Splaying 'CASE 3' case");
        // Tests the access function
        tree.access(15);

        assertEquals(tree.root.key, 15);
        assertEquals(tree.root.right.key, 20);
        assertEquals(tree.root.left.key, 10);
        assertEquals(tree.root.left.right.key, 12);
        assertEquals(tree.root.left.left.key, 5);
        assertEquals(tree.root.right.right.key, 30);
        assertEquals(tree.root.right.left.key, 18);

        endTestCase();

        System.out.println(tree.findSuccessor(30));

    }

    public static Integer testCounter = 0;
    public static Integer testsPassed = 0;
    public static String currentTestCaseName = "";

    /**
     * Start a new test case. All future asserts will now be part of this case.
     */
    public static void startTestCase(String name) {
        currentTestCaseName = name;
        testCounter = 0;
        testsPassed = 0;

        System.out.println("=========================");
        System.out.println("CASE: " + currentTestCaseName);
        System.out.println("_________________________");
    }

    /**
     * End the current test case and print a summary
     */
    public static void endTestCase() {
        System.out.println("_________________________");
        System.out.println("SUMMARY:");
        if (testCounter == testsPassed) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
        System.out.println("Passed " + testsPassed + "/" + testCounter + " assertions");

        testCounter = 0;
        testsPassed = 0;
        currentTestCaseName = "";
    }

    /**
     * Asserts that the "actual" object is equal to the "expected" object NOTE: Does
     * not work for asserting null due to .toString()
     */
    public static void assertEquals(Object actual, Object expected) {
        testCounter++;

        if (!actual.equals(expected)) {
            System.out.println("Assert " + testCounter + ": FAIL");
            System.out.println("\tExpected " + expected.toString() + ". Receieved " + actual.toString());
        } else {
            System.out.println("Assert " + testCounter + ": PASS");
            testsPassed++;

        }
    }
}