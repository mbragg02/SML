package sml;

/**
 * Instruction for LIN
 * Store integer x in register r
 * L1 lin r x
 */

public class LinInstruction extends Instruction {
	private int register;
	private int value;

	public LinInstruction(String label, String opcode) {
		super(label, opcode);
	}

	public LinInstruction(String label, int register, int value) {
		super(label, "lin");
		this.register = register;
		this.value = value;

	}

	@Override
	public void execute(Machine m) {
		try {
			m.getRegisters().setRegister(register, value);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + value;
	}
}
