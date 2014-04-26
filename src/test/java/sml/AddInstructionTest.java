package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddInstructionTest {

    private int val1 = 6;
	private int val2 = 8;
	
	private Instruction add;

	@Mock
    private Machine machine;
	@Mock 
	private Registers registers;

	@Before
	public void testAddInstruction() {
		initMocks(this);
        String label = "L1";
        int result = 1;
        int op1 = 2;
        int op2 = 3;
        this.add = new AddInstruction(label, result, op1, op2);
		
		when(machine.getRegisters()).thenReturn(registers);
		when(registers.getRegister(op1)).thenReturn(val1);
		when(registers.getRegister(op2)).thenReturn(val2);
	}
	
	@Test
	public void testExecuteAddInstruction() {
		add.execute(machine);
		verify(registers).setRegister(1, val1 + val2);	
	}

    @Test
    public void testExecuteWithException() {
        doThrow(new IllegalArgumentException()).when(registers).setRegister(anyInt(), anyInt());
        add.execute(machine);
    }



    @Test
    public void testToString() {
        assertEquals(this.add.toString(), "L1: add 2 + 3 to 1");
    }

}
