// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MethodDeclName MethodDeclName;
    private FormsParams FormsParams;
    private FormListOfParam FormListOfParam;
    private VarDList VarDList;
    private Statements Statements;

    public MethodDecl (MethodDeclName MethodDeclName, FormsParams FormsParams, FormListOfParam FormListOfParam, VarDList VarDList, Statements Statements) {
        this.MethodDeclName=MethodDeclName;
        if(MethodDeclName!=null) MethodDeclName.setParent(this);
        this.FormsParams=FormsParams;
        if(FormsParams!=null) FormsParams.setParent(this);
        this.FormListOfParam=FormListOfParam;
        if(FormListOfParam!=null) FormListOfParam.setParent(this);
        this.VarDList=VarDList;
        if(VarDList!=null) VarDList.setParent(this);
        this.Statements=Statements;
        if(Statements!=null) Statements.setParent(this);
    }

    public MethodDeclName getMethodDeclName() {
        return MethodDeclName;
    }

    public void setMethodDeclName(MethodDeclName MethodDeclName) {
        this.MethodDeclName=MethodDeclName;
    }

    public FormsParams getFormsParams() {
        return FormsParams;
    }

    public void setFormsParams(FormsParams FormsParams) {
        this.FormsParams=FormsParams;
    }

    public FormListOfParam getFormListOfParam() {
        return FormListOfParam;
    }

    public void setFormListOfParam(FormListOfParam FormListOfParam) {
        this.FormListOfParam=FormListOfParam;
    }

    public VarDList getVarDList() {
        return VarDList;
    }

    public void setVarDList(VarDList VarDList) {
        this.VarDList=VarDList;
    }

    public Statements getStatements() {
        return Statements;
    }

    public void setStatements(Statements Statements) {
        this.Statements=Statements;
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
        if(MethodDeclName!=null) MethodDeclName.accept(visitor);
        if(FormsParams!=null) FormsParams.accept(visitor);
        if(FormListOfParam!=null) FormListOfParam.accept(visitor);
        if(VarDList!=null) VarDList.accept(visitor);
        if(Statements!=null) Statements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclName!=null) MethodDeclName.traverseTopDown(visitor);
        if(FormsParams!=null) FormsParams.traverseTopDown(visitor);
        if(FormListOfParam!=null) FormListOfParam.traverseTopDown(visitor);
        if(VarDList!=null) VarDList.traverseTopDown(visitor);
        if(Statements!=null) Statements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclName!=null) MethodDeclName.traverseBottomUp(visitor);
        if(FormsParams!=null) FormsParams.traverseBottomUp(visitor);
        if(FormListOfParam!=null) FormListOfParam.traverseBottomUp(visitor);
        if(VarDList!=null) VarDList.traverseBottomUp(visitor);
        if(Statements!=null) Statements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodDeclName!=null)
            buffer.append(MethodDeclName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormsParams!=null)
            buffer.append(FormsParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormListOfParam!=null)
            buffer.append(FormListOfParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDList!=null)
            buffer.append(VarDList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statements!=null)
            buffer.append(Statements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
