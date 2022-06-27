package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Calculations involving a binary operator and two operands
 *
 * @author Nicholas Shinn
 */
public class BinaryOperation implements ExpressionNode {

    /** Addition operator */
    public static final String ADD = "+";

    /** Division operator */
    public static final String DIV = "/";

    /** Multiplication operator */
    public static final String MUL = "*";

    /** Subtraction operator */
    public static final String SUB = "-";

    /** List of binary operators */
    public static final java.util.Collection<String> OPERATORS = Arrays.asList(ADD, DIV, MUL, SUB);

    /** Operator string */
    private String OPERATOR;

    /** List of Node children */
    private ArrayList<ExpressionNode> CHILDREN = new ArrayList<>();

    /**
     * Creates BinaryOperation nodes
     *
     * @param operator operator to be applied to operands
     *
     * @param leftChild left operand
     *
     * @param rightChild right operand
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
        if(OPERATORS.contains(operator)&&leftChild!=null&&rightChild!=null){
            CHILDREN.add(leftChild);
            this.OPERATOR = operator;
            CHILDREN.add(rightChild);
        } else {
            Errors.report(Errors.Type.ILLEGAL_VALUE, operator);
        }
    }

    /**
     * Emits the instructions for the left and right operand along with an instruction for the operator itself
     *
     * @return instructions for both operands and the operator
     */
    public java.util.List<Machine.Instruction> emit(){
        java.util.List<Machine.Instruction> instruct_list = new ArrayList<>();
        java.util.List<Machine.Instruction> instruct_list2 = new ArrayList<>();
        switch(OPERATOR) {
            case ADD:
                instruct_list.add(new Machine.Add());
                break;
            case SUB:
                instruct_list.add(new Machine.Subtract());
                break;
            case MUL:
                instruct_list.add(new Machine.Multiply());
                break;
            case DIV:
                instruct_list.add(new Machine.Divide());
                break;
        }
        instruct_list2.addAll(CHILDREN.get(0).emit());
        instruct_list2.addAll(CHILDREN.get(1).emit());
        instruct_list2.addAll(instruct_list);
        return instruct_list2;
    }

    /**
     * Evaluates both operands before applying the operator to them
     *
     * @param symTab symbol table, if needed, to fetch variable values
     *
     * @return value after applying operator to operands
     */
    public int evaluate(java.util.Map<String,Integer> symTab) {
        int x = CHILDREN.get(0).evaluate(symTab);
        int y = CHILDREN.get(1).evaluate(symTab);
        switch(OPERATOR) {
            case ADD:
                return x + y;
            case SUB:
                return x - y;
            case MUL:
                return x * y;
            case DIV:
                if(y==0){
                    Errors.report(Errors.Type.DIVIDE_BY_ZERO, null);
                } else { return x / y; }
            default:
                return 0;
        }
    }

    /**
     * Prints the operation in standard output
     */
    public void infixDisplay(){
        System.out.print("(");
        CHILDREN.get(0).infixDisplay();
        System.out.print(OPERATOR);
        CHILDREN.get(1).infixDisplay();
        System.out.print(")");
    }
}
