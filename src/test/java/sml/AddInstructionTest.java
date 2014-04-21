package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import sml.AddInstruction;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

}
