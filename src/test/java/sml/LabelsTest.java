package sml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class LabelsTest {

    private Labels labels;

    @Before
    public void setUp() throws Exception {
        labels = new Labels();

    }

    @Test
    public void testAddLabel() throws Exception {
       int labelsSize = labels.addLabel("some label");

        assertEquals(labelsSize , 0);

        labelsSize = labels.addLabel("some label");

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
        labels.addLabel("somelabel");
        int indexOfLabel = labels.indexOf("somelabel");

        assertEquals(indexOfLabel, 0);
    }

    @Test
    public void testToString() throws Exception {
        labels.addLabel("somelabel");
        labels.addLabel("somelabel");

        String result = labels.toString();
        assertEquals(result, "(somelabel, somelabel)");
    }

    @Test
    public void testReset() throws Exception {
        labels.addLabel("somelabel");
        int indexOfLabel = labels.indexOf("somelabel");

        assertEquals(indexOfLabel, 0);

        labels.reset();

        int indexOfLabelAgain = labels.indexOf("somelabel");

        assertEquals(indexOfLabelAgain, -1);

    }
}