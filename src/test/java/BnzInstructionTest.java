
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import sml.BnzInstruction;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BnzInstructionTest {
	
	private String label = "L1";
	private String nextLabel = "L2";
	private int op1 = 1;
	
	private Instruction bnz;
	
	@Mock 
	private Machine machine;
	@Mock 
	private Registers registers;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		when(machine.getRegisters()).thenReturn(registers);
//		when(machine.getProg()).thenReturn(//);
		this.bnz = new BnzInstruction(label, op1, nextLabel);
	}

	@Test
	public void testExecuteWithZeroOperand() {
		this.op1 = 0;
		this.bnz.execute(machine);
		//Mockito.verifyZeroInteractions(machine.getProg());
	}


}
