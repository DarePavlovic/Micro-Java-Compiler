// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class TypeNamespace extends Type {

    private String nsName;
    private String nsTypeName;

    public TypeNamespace (String nsName, String nsTypeName) {
        this.nsName=nsName;
        this.nsTypeName=nsTypeName;
    }

    public String getNsName() {
        return nsName;
    }

    public void setNsName(String nsName) {
        this.nsName=nsName;
    }

    public String getNsTypeName() {
        return nsTypeName;
    }

    public void setNsTypeName(String nsTypeName) {
        this.nsTypeName=nsTypeName;
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
        buffer.append("TypeNamespace(\n");

        buffer.append(" "+tab+nsName);
        buffer.append("\n");

        buffer.append(" "+tab+nsTypeName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeNamespace]");
        return buffer.toString();
    }
}
