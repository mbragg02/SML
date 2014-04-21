package sml;
/**
 * Instruction for BNZ
 * If the contents of register s1 is not zero, then make the statement labeled L2 the next one to execute.
 * L1 bnz s1 L2
 */
public class BnzInstruction extends Instruction {
	private int op1;
	private String nextLabel;
	
	public BnzInstruction(String label, String op) {
		super(label, op);
	}
	
	public BnzInstruction(String label, int op1, String nextLabel) {
		this(label, "bnz");
		this.op1 = op1;
		this.nextLabel = nextLabel;
	}

	@Override
	public void execute(Machine m) {
		
		if (m.getRegisters().getRegister(op1) != 0) {
			int pcCounter = 0;
			for (Instruction inst : m.getProg()) {
				if (inst.label.equals(nextLabel)) {
					m.setPc(pcCounter);
					break;
				}
				++pcCounter;
			} 			
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + nextLabel + " next to execute" ;
	}
}
