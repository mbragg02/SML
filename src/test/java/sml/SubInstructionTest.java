package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubInstructionTest {

    private int op1 = 2;
	private int op2 = 3;
	
	private Instruction sub;
	
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

        this.sub = new SubInstruction("L2", result, op1, op2);
	}

	@Test
	public void testExecute() {

		
		sub.execute(machine);
		
		verify(registers).setRegister(1, val1 - val2);
	}

    @Test
    public void testExecuteWithException() {
        doThrow(new IllegalArgumentException()).when(registers).setRegister(anyInt(), anyInt());
        sub.execute(machine);

    }



    @Test
    public void testToString() {
        assertEquals(this.sub.toString(), "L2: sub 3 - 2 to 1");
    }

}
