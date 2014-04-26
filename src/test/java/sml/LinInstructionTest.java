package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class LinInstructionTest {

    private int register = 1;
	private int value = 5;
	
	private Instruction lin;
	
	@Mock 
	private Machine machine;
	@Mock 
	private Registers registers;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		when(machine.getRegisters()).thenReturn(registers);
        this.lin = new LinInstruction("L2", register, value);
	}

	@Test
	public void testExecute() {
		this.lin.execute(machine);
		verify(registers).setRegister(register, value);
	}


    @Test
    public void testExecuteWithException() {
        doThrow(new IllegalArgumentException()).when(registers).setRegister(anyInt(), anyInt());
        lin.execute(machine);
    }

    @Test
    public void testToString() {
        assertEquals(this.lin.toString(), "L2: lin register 1 value is 5");
    }



}
