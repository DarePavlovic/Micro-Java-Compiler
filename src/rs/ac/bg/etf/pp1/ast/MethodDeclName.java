// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclName implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethOnce MethOnce;
    private String methName;

    public MethodDeclName (MethOnce MethOnce, String methName) {
        this.MethOnce=MethOnce;
        if(MethOnce!=null) MethOnce.setParent(this);
        this.methName=methName;
    }

    public MethOnce getMethOnce() {
        return MethOnce;
    }

    public void setMethOnce(MethOnce MethOnce) {
        this.MethOnce=MethOnce;
    }

    public String getMethName() {
        return methName;
    }

    public void setMethName(String methName) {
        this.methName=methName;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethOnce!=null) MethOnce.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethOnce!=null) MethOnce.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethOnce!=null) MethOnce.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclName(\n");

        if(MethOnce!=null)
            buffer.append(MethOnce.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclName]");
        return buffer.toString();
    }
}
