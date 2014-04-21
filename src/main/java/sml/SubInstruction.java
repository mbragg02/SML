package sml;

/**
 * Instruction for SUBTRACTION
 * Subtract the contents of register s2 from the contents of s1 and store the result in register r
 * L1 sub r s1 s2
 */
public class SubInstruction extends Instruction{
	private int result;
	private int op1;
	private int op2;
	
	public SubInstruction(String label, String op) {
		super(label, op);
	}
	
	public SubInstruction(String label, int result, int op1, int op2) {
		this(label, "sub");
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		try {
			m.getRegisters().setRegister(result, value1 - value2);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + op2 + " - " + op1 + " to " + result;
	}

}
