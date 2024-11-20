// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorNamespace extends Designator {

    private String namespaceDesigName;
    private String desigNSName;

    public DesignatorNamespace (String namespaceDesigName, String desigNSName) {
        this.namespaceDesigName=namespaceDesigName;
        this.desigNSName=desigNSName;
    }

    public String getNamespaceDesigName() {
        return namespaceDesigName;
    }

    public void setNamespaceDesigName(String namespaceDesigName) {
        this.namespaceDesigName=namespaceDesigName;
    }

    public String getDesigNSName() {
        return desigNSName;
    }

    public void setDesigNSName(String desigNSName) {
        this.desigNSName=desigNSName;
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
        buffer.append("DesignatorNamespace(\n");

        buffer.append(" "+tab+namespaceDesigName);
        buffer.append("\n");

        buffer.append(" "+tab+desigNSName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorNamespace]");
        return buffer.toString();
    }
}
