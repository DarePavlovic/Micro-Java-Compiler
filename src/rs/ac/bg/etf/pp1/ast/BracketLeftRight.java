// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class BracketLeftRight extends Brackets {

    public BracketLeftRight () {
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
        buffer.append("BracketLeftRight(\n");

        buffer.append(tab);
        buffer.append(") [BracketLeftRight]");
        return buffer.toString();
    }
}
