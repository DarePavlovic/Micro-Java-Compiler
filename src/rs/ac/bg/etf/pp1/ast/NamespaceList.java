// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class NamespaceList extends Namespace {

    private Namespace Namespace;
    private NamespaceName NamespaceName;
    private DeclList DeclList;
    private NamespaceMethBegin NamespaceMethBegin;
    private MethodDeclList MethodDeclList;
    private NamespaceMethEnd NamespaceMethEnd;

    public NamespaceList (Namespace Namespace, NamespaceName NamespaceName, DeclList DeclList, NamespaceMethBegin NamespaceMethBegin, MethodDeclList MethodDeclList, NamespaceMethEnd NamespaceMethEnd) {
        this.Namespace=Namespace;
        if(Namespace!=null) Namespace.setParent(this);
        this.NamespaceName=NamespaceName;
        if(NamespaceName!=null) NamespaceName.setParent(this);
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
        this.NamespaceMethBegin=NamespaceMethBegin;
        if(NamespaceMethBegin!=null) NamespaceMethBegin.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
        this.NamespaceMethEnd=NamespaceMethEnd;
        if(NamespaceMethEnd!=null) NamespaceMethEnd.setParent(this);
    }

    public Namespace getNamespace() {
        return Namespace;
    }

    public void setNamespace(Namespace Namespace) {
        this.Namespace=Namespace;
    }

    public NamespaceName getNamespaceName() {
        return NamespaceName;
    }

    public void setNamespaceName(NamespaceName NamespaceName) {
        this.NamespaceName=NamespaceName;
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public NamespaceMethBegin getNamespaceMethBegin() {
        return NamespaceMethBegin;
    }

    public void setNamespaceMethBegin(NamespaceMethBegin NamespaceMethBegin) {
        this.NamespaceMethBegin=NamespaceMethBegin;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public NamespaceMethEnd getNamespaceMethEnd() {
        return NamespaceMethEnd;
    }

    public void setNamespaceMethEnd(NamespaceMethEnd NamespaceMethEnd) {
        this.NamespaceMethEnd=NamespaceMethEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Namespace!=null) Namespace.accept(visitor);
        if(NamespaceName!=null) NamespaceName.accept(visitor);
        if(DeclList!=null) DeclList.accept(visitor);
        if(NamespaceMethBegin!=null) NamespaceMethBegin.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
        if(NamespaceMethEnd!=null) NamespaceMethEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Namespace!=null) Namespace.traverseTopDown(visitor);
        if(NamespaceName!=null) NamespaceName.traverseTopDown(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
        if(NamespaceMethBegin!=null) NamespaceMethBegin.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
        if(NamespaceMethEnd!=null) NamespaceMethEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Namespace!=null) Namespace.traverseBottomUp(visitor);
        if(NamespaceName!=null) NamespaceName.traverseBottomUp(visitor);
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        if(NamespaceMethBegin!=null) NamespaceMethBegin.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        if(NamespaceMethEnd!=null) NamespaceMethEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NamespaceList(\n");

        if(Namespace!=null)
            buffer.append(Namespace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NamespaceName!=null)
            buffer.append(NamespaceName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NamespaceMethBegin!=null)
            buffer.append(NamespaceMethBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NamespaceMethEnd!=null)
            buffer.append(NamespaceMethEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NamespaceList]");
        return buffer.toString();
    }
}
