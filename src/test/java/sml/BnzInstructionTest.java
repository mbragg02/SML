package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class BnzInstructionTest {

    private Instruction bnz;
    private Instruction nextInstruction;
    private Instruction nextNextInstruction;
	
	@Mock 
	private Machine machine;
	@Mock 
	private Registers registers;
    @Mock
    private ArrayList<Instruction> instructionList;
    @Mock
    private Iterator<Instruction> instructionIterator;


    @Before
	public void setUp() throws Exception {
		initMocks(this);

		when(machine.getRegisters()).thenReturn(registers);
        when(registers.getRegister(anyInt())).thenReturn(1);


        this.bnz = new BnzInstruction("L2", 1, "L3");
        this.nextInstruction     = new AddInstruction("some label", 1, 2, 3);
        this.nextNextInstruction = new AddInstruction("L3", 1, 2, 3);
	}

	@Test
	public void testExecuteWithZeroOperand() {
        when(registers.getRegister(anyInt())).thenReturn(0);

        this.bnz.execute(machine);

        verify(machine, times(0)).getProg();
	}

    @Test
    public void testExecute() {
        when(machine.getProg()).thenReturn(instructionList);
        when(instructionList.iterator()).thenReturn(instructionIterator);
        when(instructionIterator.hasNext()).thenReturn(true, true, false);
        when(instructionIterator.next()).thenReturn(nextInstruction, nextNextInstruction);

        this.bnz.execute(machine);

        verify(machine).getProg();
        verify(machine).setPc(1);
    }

    @Test
    public void testToString() {
        assertEquals(this.bnz.toString(), "L2: bnz L3 next to execute");
    }
}
