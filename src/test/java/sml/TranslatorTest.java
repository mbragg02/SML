package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

public class TranslatorTest {

    private Translator translator;

    @Mock
    private Labels labels;

    @Mock
    private ArrayList<Instruction> program;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        String fileName = "program.text";
        this.translator = new Translator(fileName);
    }

    @Test
    public void testReadAndTranslateUnknownFile() throws Exception {
        String fileName = "unknownFile";
        this.translator = new Translator(fileName);
        assertFalse(translator.readAndTranslate(labels, program));
    }

    @Test
    public void testReadAndTranslate() throws Exception {
        translator.readAndTranslate(labels, program);
    }

    @Test
    public void testGetInstructionUnknown() throws Exception {
        assertEquals(null, translator.getInstruction("unknown") );
    }

    @Test
    public void testScanEmpty() throws Exception {
       assertEquals("", translator.scan());

    }

    @Test
    public void testScanIntMaxValue() throws Exception {
        assertEquals(Integer.MAX_VALUE, translator.scanInt());

    }
}