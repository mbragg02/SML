package sml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";
	
	private Properties props = null;
	private static final int INS_SUBCLASS_CONSTRUCTOR = 1;
	private static final int FIRST_PARAMETER 		  = 0;
	private static final String STRING_TYPE = "String";
	private static final String INT_TYPE 	= "int";


	public Translator(String fileName) {
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	@SuppressWarnings("resource")
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {
		Scanner sc; // Scanner attached to the file chosen by the user
		try {
			sc = new Scanner(new File(fileName));
		} catch (IOException ioE) {
			System.out.println("File: IO error to start " + ioE.getMessage());
			return false;
		}
		labels = lab;
		labels.reset();
		program = prog;
		program.clear();

		try {
			line = sc.nextLine();
		} catch (NoSuchElementException ioE) {
			return false;
		}

		// Each iteration processes line and reads the next line into line
		while (line != null) {
			// Store the label in label
			String label = scan();

			if (label.length() > 0) {
				Instruction ins = getInstruction(label);
				if (ins != null) {
					labels.addLabel(label);
					program.add(ins);
				}
			}

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {

		if (line.equals(""))
			return null;
	
		/*
		 * Names of classes for different instructions are stored in an external file, "sml.properties". 
		 * Other instruction classes can be created, with their names put into the properties file. 
		 * The following code will be able create a new instance of the class with the appropriate arguments.
		 * The format of arguments accepted are:
		 * (label, s1)
		 * (label, r, s1)
		 * (label, s1, x)
		 * (label, r, s1, s2)
		 * Where label & x are strings. s1, r, s2 are integers.
		 */
		
		props = new Properties();
		String ins = scan();
		Instruction instruction = null;
		Class<?> instruction_class;
		
		try {

			props.load(new FileInputStream("src/sml.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("SML properties file not found.");
		} catch (IOException e) {
			System.out.println("Java IO Exception");
		}

		// Get the name of the class for the given instruction from the properties file.
		String instruction_name = props.getProperty(ins);
		
		try {
			// Get the Class with the given name
			instruction_class = Class.forName(instruction_name);

			// Get an array of public constructors for the given instruction class.
			Constructor<?>[] all_constructors = instruction_class.getConstructors();
			
			// Get the second constructor
			Constructor<?> constructor = all_constructors[INS_SUBCLASS_CONSTRUCTOR];
			
			// Get an array of the constructors parameter types
			Class<?>[] parameter_types  = constructor.getParameterTypes();
			
			// Create an array of objects to hold the parameter values to pass into the constructor.newInstance
			Object[] parameters = new Object[parameter_types.length];
			
			// The first parameter is always the label that has been passed in.
			parameters[FIRST_PARAMETER] = label;
			
			// Loop to check through the remaining parameter types. Call scan() for String, scanInt() for int.
			for (int i = 1; i < parameters.length; i ++){
					if (parameter_types[i].getSimpleName().equals(STRING_TYPE)){
					parameters[i] = scan();
				} else if (parameter_types[i].getSimpleName().equals(INT_TYPE)){
					parameters[i] = scanInt();
				}
			}
			
			// Create the new instruction
			instruction = (Instruction) constructor.newInstance(parameters);
		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		}

        return instruction;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	public String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' '
				&& line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	public int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}