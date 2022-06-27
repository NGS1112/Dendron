package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;

import java.util.ArrayList;

/**
 * Creates a Variable node
 *
 * @author Nicholas Shinn
 */
public class Variable implements ExpressionNode {

    /**
     * Variable name
     */
    private String VARIABLE;

    /**
     * Assigns a name for the variable
     *
     * @param name name of variable
     */
    public Variable(String name) {
        VARIABLE = name;
    }

    /**
     * Emits a Load instruction for the variable
     *
     * @return Machine.Load(Variable)
     */
    public java.util.List<Machine.Instruction> emit() {
        java.util.List<Machine.Instruction> instruct_list = new ArrayList<>();
        Machine.Instruction load = new Machine.Load(VARIABLE);
        instruct_list.add(load);
        return instruct_list;
    }

    /**
     * Prints Variable name
     */
    public void infixDisplay() {
        System.out.print(VARIABLE);
    }

    /**
     * Gets a Variable's value
     *
     * @param symTab symbol table, if needed, to fetch variable values
     * @return Variable value
     */
    public int evaluate(java.util.Map<String, Integer> symTab) {
        Integer x = symTab.get(VARIABLE);
        if (x == null) {
            Errors.report(Errors.Type.UNINITIALIZED, VARIABLE);
            return 0;
        } else {
            return x;
        }
    }
}
