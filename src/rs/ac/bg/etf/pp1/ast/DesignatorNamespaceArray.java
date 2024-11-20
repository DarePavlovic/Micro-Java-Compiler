// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorNamespaceArray extends Designator {

    private String namespaceDesigNameArray;
    private String desigNSNameArray;
    private Expr Expr;

    public DesignatorNamespaceArray (String namespaceDesigNameArray, String desigNSNameArray, Expr Expr) {
        this.namespaceDesigNameArray=namespaceDesigNameArray;
        this.desigNSNameArray=desigNSNameArray;
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public String getNamespaceDesigNameArray() {
        return namespaceDesigNameArray;
    }

    public void setNamespaceDesigNameArray(String namespaceDesigNameArray) {
        this.namespaceDesigNameArray=namespaceDesigNameArray;
    }

    public String getDesigNSNameArray() {
        return desigNSNameArray;
    }

    public void setDesigNSNameArray(String desigNSNameArray) {
        this.desigNSNameArray=desigNSNameArray;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorNamespaceArray(\n");

        buffer.append(" "+tab+namespaceDesigNameArray);
        buffer.append("\n");

        buffer.append(" "+tab+desigNSNameArray);
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorNamespaceArray]");
        return buffer.toString();
    }
}
