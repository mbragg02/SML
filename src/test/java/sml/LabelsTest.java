package sml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class LabelsTest {

    private Labels labels;
    private String someLabel;

    @Before
    public void setUp() throws Exception {
        someLabel = "someLabel";
        labels = new Labels();

    }

    @Test
    public void testAddLabel() throws Exception {

       int labelsSize = labels.addLabel(someLabel);

        assertEquals(labelsSize , 0);

        labelsSize = labels.addLabel(someLabel);

        assertEquals(labelsSize , 1);
    }

    @Test
    public void testIndexOfUnknownLabel() throws Exception {
        int indexOfLabel = labels.indexOf("unknown label");
        // returns -1 if label is unknown
        assertEquals( indexOfLabel, -1);
    }

    @Test
    public void testIndexOf() {

        labels.addLabel(someLabel);
        int indexOfLabel = labels.indexOf(someLabel);

        assertEquals(indexOfLabel, 0);
    }

    @Test
    public void testToString() throws Exception {
        labels.addLabel(someLabel);
        labels.addLabel(someLabel);

        String result = labels.toString();
        assertEquals(result, "(someLabel, someLabel)");
    }

    @Test
    public void testReset() throws Exception {
        labels.addLabel(someLabel);
        int indexOfLabel = labels.indexOf(someLabel);

        assertEquals(indexOfLabel, 0);

        labels.reset();

        int indexOfLabelAgain = labels.indexOf(someLabel);

        assertEquals(indexOfLabelAgain, -1);

    }


}