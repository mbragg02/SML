package sml;

import lombok.Data;

/**
 * This class ....
 * 
 * An instance contains 32 registers and methods to access and change them
 * 
 * @author someone
 */

@Data
public class Registers {

	private final static int NUMBER_OF_REGISTERS = 32;
	private int registers[];

	// Constructor: an instance whose registers are set to 0

	{
		registers = new int[NUMBER_OF_REGISTERS];
	}
	
	public Registers() {
		// Set all the values on the register to zero
		for (int i = 0; i != registers.length; i++) {
			registers[i] = 0;
		}
	}

	// Set register i to v.
	// Precondition: 0 <= i <= NUMBER_OF_REGISTERS

	public void setRegister(int i, int v) throws IllegalArgumentException {
		if (i <= NUMBER_OF_REGISTERS && i >= 0) {
			
			registers[i] = v;
			
		} else {
			throw new IllegalArgumentException("Register must be between 0 and " + NUMBER_OF_REGISTERS);
		}
		
	}

	public int getRegister(int i) {
		return registers[i];
	}

}