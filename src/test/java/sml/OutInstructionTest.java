package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OutInstructionTest {

    private int op1 = 2;

	private Instruction out;

	@Mock 
	private Machine machine;
	@Mock 
	private Registers registers;

	@Before
	public void setUp() throws Exception {
		initMocks(this);

        this.out = new OutInstruction("L2", op1);
	}

	@Test
	public void testExecute() {
		int val1 = 8;
		when(machine.getRegisters()).thenReturn(registers);
		when(registers.getRegister(op1)).thenReturn(val1);

		out.execute(machine);

		verify(registers).getRegister(op1);
	}

    @Test
    public void testToString() {
        assertEquals(this.out.toString(), "L2: out 2 to console");
    }

}
