package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MulInstructionTest {

    private int op1 = 2;
	private int op2 = 3;
	
	private Instruction mul;
	
	@Mock 
	private Machine machine;
	@Mock 
	private Registers registers;
    private int val1;
    private int val2;

    @Before
	public void setUp() throws Exception {
		initMocks(this);
        int result = 1;
        val1 = 8;
        val2 = 2;

        when(machine.getRegisters()).thenReturn(registers);
        when(registers.getRegister(op1)).thenReturn(val1);
        when(registers.getRegister(op2)).thenReturn(val2);
        this.mul = new MulInstruction("L2", result, op1, op2);
	}

	@Test
	public void testExecute() {

		
		mul.execute(machine);
		
		verify(registers).setRegister(1, val1 * val2);
	}

    @Test
    public void testExecuteWithException() {
        doThrow(new IllegalArgumentException()).when(registers).setRegister(anyInt(), anyInt());
        mul.execute(machine);
    }

    @Test
    public void testToString() {
        assertEquals(this.mul.toString(), "L2: mul 2 x 3 to 1");
    }

}
