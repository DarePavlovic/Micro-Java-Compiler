// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class ConstLitBool extends ConstLiteral {

    private Boolean literalBool;

    public ConstLitBool (Boolean literalBool) {
        this.literalBool=literalBool;
    }

    public Boolean getLiteralBool() {
        return literalBool;
    }

    public void setLiteralBool(Boolean literalBool) {
        this.literalBool=literalBool;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstLitBool(\n");

        buffer.append(" "+tab+literalBool);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstLitBool]");
        return buffer.toString();
    }
}
