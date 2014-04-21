package sml;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class RegistersTest {

    private Registers registers;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        // Number of registers are 32 which are set to zero on registers construction.
        registers = new Registers();
    }

    @Test
    public void testSetRegister() throws Exception {
        registers.setRegister(0, 1);
        int result = registers.getRegister(0);
        assertEquals(result, 1);
    }

    @Test
    public void testSetRegisterException() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Register must be between 0 and 32");
        registers.setRegister(33, 1);

    }

    @Test
    public void testGetRegister() throws Exception {
        int result = registers.getRegister(0);
        assertEquals(result, 0);


    }

    @Test
    public void testGetRegisters() throws Exception {
        int[] result = registers.getRegisters();
        assertEquals(result.length, 32);
    }

    @Test
    public void testSetRegisters() throws Exception {
        int[] newRegisters = new int[32];
        newRegisters[0] = 1;
        registers.setRegisters(newRegisters);
        int result = registers.getRegister(0);
        assertEquals(result, 1);

    }
}