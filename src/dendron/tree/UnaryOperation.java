package dendron.tree;


import dendron.machine.Machine;

import java.util.ArrayList;

import java.util.Arrays;

/**
 * Calculation by unary operator and an operand
 *
 * @author Nicholas Shinn
 */
public class UnaryOperation implements ExpressionNode {

    /** Negate operator */
    public static final String NEG = "_";

    /** Square root operator */
    public static final String SQRT = "#";

    /** List of operators */
    public static final java.util.Collection<String> OPERATORS = Arrays.asList(NEG, SQRT);

    /** Operator to be applied to operand */
    private String OPERATOR;

    /** Operand to be affected by operator */
    private ExpressionNode NODE;

    /**
     * Creates a UnaryOperator node
     *
     * @param operator operator to be used on operand
     *
     * @param expr operand
     */
    public UnaryOperation(String operator, ExpressionNode expr){
        if(OPERATORS.contains(operator)&&expr!=null){
            OPERATOR = operator;
            NODE = expr;
        }
    }

    /**
     * Gets instructions for Node, adds instruction for operator
     *
     * @return instructions for Node and operator
     */
    public java.util.List<Machine.Instruction> emit(){
        java.util.List<Machine.Instruction> instruct_list = new ArrayList<>();
        java.util.List<Machine.Instruction> instruct_list2 = new ArrayList<>();
        if (OPERATOR.equals(NEG)) {
            Machine.Instruction negate = new Machine.Negate();
            instruct_list2.add(negate);
        } else {
            Machine.Instruction sqr = new Machine.SquareRoot();
            instruct_list2.add(sqr);
        }
        instruct_list = NODE.emit();
        instruct_list.addAll(instruct_list2);
        return instruct_list;
    }

    /**
     * Computes result of applying operator to operand
     *
     * @param symTab symbol table, if needed, to fetch variable values
     *
     * @return result of applying operator to operand
     */
    public int evaluate(java.util.Map<String,Integer> symTab) {
        if (OPERATOR.equals(NEG)) {
            int x = NODE.evaluate(symTab);
            return x * -1;
        } else {
            return (int) Math.sqrt(NODE.evaluate(symTab));
        }
    }

    /**
     * Prints operator and the infix of the Node
     */
    public void infixDisplay(){
        System.out.print(OPERATOR);
        NODE.infixDisplay();
    }

}
