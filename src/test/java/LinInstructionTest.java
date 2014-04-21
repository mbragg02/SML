
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import sml.Instruction;
import sml.LinInstruction;
import sml.Machine;
import sml.Registers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LinInstructionTest {
	
	private String label = "L2";
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
		this.lin = new LinInstruction(label, register, value);
	}

	@Test
	public void testExecute() {
		this.lin.execute(machine);
		verify(registers).setRegister(register, value);
	}

}
