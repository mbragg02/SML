package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.SubInstruction;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubInstructionTest {

    private int op1 = 2;
	private int op2 = 3;
	
	private Instruction sub;
	
	@Mock 
	private Machine machine;
	@Mock 
	private Registers registers;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
        int result = 1;
        this.sub = new SubInstruction("L2", result, op1, op2);
	}

	@Test
	public void testExecute() {
		int val1 = 8;
		int val2 = 2;
		
		when(machine.getRegisters()).thenReturn(registers);
		when(registers.getRegister(op1)).thenReturn(val1);
		when(registers.getRegister(op2)).thenReturn(val2);
		
		sub.execute(machine);
		
		verify(registers).setRegister(1, val1 - val2);
	}

}
