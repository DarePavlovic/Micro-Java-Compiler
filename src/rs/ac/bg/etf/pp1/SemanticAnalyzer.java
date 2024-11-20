package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.ast.MethodDeclName;

public class SemanticAnalyzer extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	int formNumber=0;
	int nVars=0;
	boolean namespaceMethNow = false;
	boolean namespaceJe = false;
	boolean errorDetected = false;
	Struct boolType = new Struct(Struct.Bool);
	Obj currentMethod = null;
	Struct typeRightNow = null;
	boolean returnIma = false;
	String curNamespaceName="";
	ArrayList<Obj> desCom = new ArrayList<Obj>();
	int petlja =0; //nivo ugnjezdavanja for petlje 
	ArrayList<Struct> fParam = new ArrayList<Struct>();
	ArrayList<Struct> actParams = new ArrayList<Struct>();
	Struct tipDesnogDesignatora=Tab.noType;
	boolean globalniFlag=true;
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean checkNameIfDoesExist(String name) {
		Obj node = Tab.currentScope.findSymbol(name);
		if(node!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void visit(GlobFlag gl) {
		globalniFlag=false;
	}
	public void visit(VarDecl varDecl) {
		varDeclCount++;
		if(curNamespaceName=="" || namespaceMethNow) {
			if(!checkNameIfDoesExist(varDecl.getVarName())) {
				Brackets brackets = varDecl.getBrackets();
				if(brackets instanceof BracketLeftRight) {
					Struct niz = new Struct(Struct.Array, typeRightNow);
					Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), niz);
					report_info("Deklarisan niz " + varDecl.getVarName(), varDecl);
					if(globalniFlag) {
						varNode.setLevel(0);
					}
					else {
						varNode.setLevel(1);
					}
				}
				else {
					Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), typeRightNow);
					report_info("Deklarisana je promenljiva " + varDecl.getVarName(), varDecl);
					if(globalniFlag) {
						varNode.setLevel(0);
					}
					else {
						varNode.setLevel(1);
					}
				}
			}
			else {
				report_error("Greska-var: Vec je deklarisana promenljiva " +varDecl.getVarName(), varDecl);
			}
		}
		else {
			String name = curNamespaceName+"::"+varDecl.getVarName();
			if(!checkNameIfDoesExist(name)) {
				Brackets brackets = varDecl.getBrackets();
				if(brackets instanceof BracketLeftRight) {
					Struct niz = new Struct(Struct.Array, typeRightNow);
					Obj varNode = Tab.insert(Obj.Var, name, niz);
					varNode.setLevel(0);
					report_info("Deklarisan niz " + name, varDecl);
		
				}
				else {
					Obj varNode = Tab.insert(Obj.Var, name, typeRightNow);
					varNode.setLevel(0);
					report_info("Deklarisana je promenljiva " + name, varDecl);
				}
			}
			else {
				report_error("Greska-varNamespace: Vec je deklarisana promenljiva " +varDecl.getVarName(), varDecl);
			}
		}
		
	}

    
    public void visit(ConstDecl constDecl) {
    	String constName = constDecl.getConstName();
    	ConstLiteral constLiteral = constDecl.getConstLiteral();
    	Obj varNode;
    	if(curNamespaceName=="") {
    		if(!checkNameIfDoesExist(constName)){
    	    	if(constLiteral instanceof ConstLitNumber) {
    	    		if(typeRightNow.getKind()==Struct.Int) {
    	    			 varNode = Tab.insert(Obj.Con, constName, Tab.intType);
    	    			varNode.setAdr(((ConstLitNumber)constLiteral).getLiteralNum());
    					report_info("Definisana konstanta " + constName + " = "+ ((ConstLitNumber)constLiteral).getLiteralNum(), constDecl);
    	    		}
    	    		else {
    	        		report_error("Greska-konstanta: nisu ekvivalentni tipovi", constDecl);
    	        	}
    	    		
    	    	}
    	    	else if(constLiteral instanceof ConstLitChar) {
    	    		if(typeRightNow.getKind()==Struct.Char) {
    	    			 varNode = Tab.insert(Obj.Con, constName, Tab.charType);
    	    			varNode.setAdr(((ConstLitChar)constLiteral).getLiteralChar());
    					report_info("Definisana konstanta " + constName + " = "+ ((ConstLitChar)constLiteral).getLiteralChar(), constDecl);
    	    		}
    	    		else {
    	        		report_error("Greska-konstanta: nisu ekvivalentni tipovi", constDecl);
    	        	}
    	    	}
    	    	else if(constLiteral instanceof ConstLitBool) {
    	    		if(typeRightNow.getKind()==Struct.Bool) {
    	    			 varNode = Tab.insert(Obj.Con, constName, boolType);
    	    			if(((ConstLitBool)constLiteral).getLiteralBool()==true) {
    	    				varNode.setAdr(1);
    	    			}
    	    			else {
    	    				varNode.setAdr(0);
    	    			}
    					report_info("Definisana konstanta " + constName + " = "+ ((ConstLitBool)constLiteral).getLiteralBool(), constDecl);
    	    		}
    	    		else {
    	        		report_error("Greska-konstanta: nisu ekvivalentni tipovi", constDecl);
    	        	}
    	    	}
    	    	else {
    	    		report_error("Greska-konstanta: los tip konstante", constDecl);
    	    	}
    	    	}
    	    	else {
    				report_error("Greska-konstanta: Vec je deklarisana konstanta" +constName, constDecl);
    	    	}
    	}
    	else {
    		String name = curNamespaceName+"::"+constName;
    		constName = name;
    		if(!checkNameIfDoesExist(constName)){
    	    	if(constLiteral instanceof ConstLitNumber) {
    	    		if(typeRightNow.getKind()==Struct.Int) {
    	    			 varNode = Tab.insert(Obj.Con, constName, Tab.intType);
    	    			varNode.setAdr(((ConstLitNumber)constLiteral).getLiteralNum());
    	    			varNode.setLevel(0);
    					report_info("Definisana konstanta " + constName + " = "+ ((ConstLitNumber)constLiteral).getLiteralNum(), constDecl);
    	    		}
    	    		else {
    	        		report_error("Greska-konstanta: nisu ekvivalentni tipovi", constDecl);
    	        	}
    	    		
    	    	}
    	    	else if(constLiteral instanceof ConstLitChar) {
    	    		if(typeRightNow.getKind()==Struct.Char) {
    	    			 varNode = Tab.insert(Obj.Con, constName, Tab.charType);
    	    			varNode.setAdr(((ConstLitChar)constLiteral).getLiteralChar());
    	    			varNode.setLevel(0);
    					report_info("Definisana konstanta " + constName + " = "+ ((ConstLitChar)constLiteral).getLiteralChar(), constDecl);
    	    		}
    	    		else {
    	        		report_error("Greska-konstanta: nisu ekvivalentni tipovi", constDecl);
    	        	}
    	    	}
    	    	else if(constLiteral instanceof ConstLitBool) {
    	    		if(typeRightNow.getKind()==Struct.Bool) {
    	    			 varNode = Tab.insert(Obj.Con, constName, boolType);
    	    			if(((ConstLitBool)constLiteral).getLiteralBool()==true) {
    	    				varNode.setAdr(1);
    	    			}
    	    			else {
    	    				varNode.setAdr(0);
    	    			}
    	    			varNode.setLevel(0);
    					report_info("Definisana konstanta " + constName + " = "+ ((ConstLitBool)constLiteral).getLiteralBool(), constDecl);
    	    		}
    	    		else {
    	        		report_error("Greska-konstanta: nisu ekvivalentni tipovi", constDecl);
    	        	}
    	    	}
    	    	else {
    	    		report_error("Greska-konstanta: los tip konstante", constDecl);
    	    	}
    	    	}
    	    	else {
    				report_error("Greska-konstanta: Vec je deklarisana konstanta" +constName, constDecl);
    	    	}
    	}
    	
    }
	
	
    public void visit(ProgName progName)
    {
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	Tab.openScope();
    }
    
    public void visit(Program program) {
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	
    	Tab.closeScope();
    }
    
    
    public void visit(NamespaceName nameSpaceName) {
    	//ne otvarati scope
    	namespaceJe=true;
    	curNamespaceName = nameSpaceName.getNamespaceName();
    }
    
    public void visit(NamespaceList namespaceL) {
    	curNamespaceName = "";
    	namespaceJe=false;
    }
    
    public void visit(NamespaceMethBegin be) {
    	namespaceMethNow = true;
    }
    
    public void visit(NamespaceMethEnd end) {
    	namespaceMethNow = false;
    }
    
    public void visit(TypeSimple type) {
    	Obj typeNode = Tab.find(type.getTypeName());	
	    if(typeNode == Tab.noObj) {
	    	if(type.getTypeName().equals("bool")) {		
	    		typeNode = Tab.insert(Obj.Type, "bool", boolType);
	    		typeRightNow = typeNode.getType();
	    		type.struct = boolType;
	       	}
	   		else {
	   			report_error("Greska: Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola!", null);
	    		type.struct = Tab.noType;
	  		}   		
	    }
	    else {
	    	if(typeNode.getKind()==Obj.Type) {
	    		if(type.getTypeName().equals("bool")) {
		   		    typeRightNow = boolType;
		    		type.struct = boolType;
		       	}
	    		else {
	    			type.struct = typeNode.getType();
	    			typeRightNow = type.struct;
    			}
	    			
	    	}
	    	else {
	    		report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
	    		type.struct = Tab.noType;
	    		typeRightNow=Tab.noType;
	   		}
	  	}
	    
    }
    
    
    public void visit(TypeNamespace type) {
    	String name = type.getNsName()+"::"+type.getNsTypeName();
    	Obj typeNode = Tab.find(name);
    	if(typeNode==Tab.noObj) {
    		report_error("Greska-TypeNamespace: Nije pronadjen tip " + name, type);
    	}
    	else {
    		if(typeNode.getKind()==Obj.Type) {
    			type.struct = typeNode.getType();
    			typeRightNow = type.struct;
    		}
    		else {
    			report_error("Greska-TypeNamespace: Ime " +name+" ne predstavlja tip", type);
    			type.struct  = Tab.noType;
    			typeRightNow = Tab.noType;
    		}
    	}
    }
    
    
    public void visit(MethodDeclName methDecl) {
    	String name;
    	if(namespaceJe) {
    		name = curNamespaceName+"::"+methDecl.getMethName();
    	}
    	else {
    		name = methDecl.getMethName();
    	}
    	
    	currentMethod = Tab.insert(Obj.Meth, name,methDecl.getMethOnce().struct);
    	methDecl.obj = currentMethod;
    	Tab.openScope();
    	report_info("Obradjuje se funckija "+name, methDecl);
    }
    
    public void visit(MethodDecl methDecl) {
	    if(currentMethod.getType()!=Tab.noType) {
	    	if(returnIma) {
	    		Tab.chainLocalSymbols(currentMethod);
	    	    Tab.closeScope();
	    	   	currentMethod = null;
	    	   	returnIma=false;
	    	}
	    	else {
	       		report_error("Greska-MethodDecl: Funkcija "+currentMethod.getName()+" mora imati return naredbu", methDecl);
	   		}
	   	}
	    else {
	    	Tab.chainLocalSymbols(currentMethod);
	    	Tab.closeScope();
		    currentMethod = null;
		   	returnIma=false;
	    }
    }
    
    public void visit(MethOnceType methOnceType) {
    	methOnceType.struct = methOnceType.getType().struct;  
    }
    
    public void visit(MethOnceVoid methOnceVoid) {
    	methOnceVoid.struct = Tab.noType;
    }
    
    public void visit(FormListOfParam formList) {
    	fParam.clear();
    	currentMethod.setLevel(formNumber);
    	formNumber=0;
    }
    
    public void visit(FormParsBegin formParsBegin) {
		if(!checkNameIfDoesExist(formParsBegin.getFormParName())) {
			Brackets brackets = formParsBegin.getBrackets();
			if(brackets instanceof BracketLeftRight) {
				Struct niz = new Struct(Struct.Array, typeRightNow);
				Obj varNode = Tab.insert(Obj.Var, formParsBegin.getFormParName(), niz);
				fParam.add(niz);
				formNumber++;
				varNode.setFpPos(formNumber);
				varNode.setLevel(1);
				report_info("Deklarisan lokalni niz " + formParsBegin.getFormParName(), formParsBegin);
	
			}
			else {
				Obj varNode = Tab.insert(Obj.Var, formParsBegin.getFormParName(), typeRightNow);
				fParam.add(typeRightNow);
				formNumber++;
				varNode.setFpPos(formNumber);
				varNode.setLevel(1);
				report_info("Deklarisan lokalna promenljiva " + formParsBegin.getFormParName(), formParsBegin);
			}
		}
		else {
			report_error("Greska: Vec je deklarisana promenljiva" +formParsBegin.getFormParName(), formParsBegin);
		}
    }
    
    public void visit(FormParsExtension formParsExt) {
    	if(!checkNameIfDoesExist(formParsExt.getFormParsName())) {
			Brackets brackets = formParsExt.getBrackets();
			if(brackets instanceof BracketLeftRight) {
				Struct niz = new Struct(Struct.Array, typeRightNow);
				Obj varNode = Tab.insert(Obj.Var, formParsExt.getFormParsName(), niz);
				fParam.add(niz);
				formNumber++;
				varNode.setFpPos(formNumber);
				varNode.setLevel(1);
				report_info("Deklarisan lokalni niz " + formParsExt.getFormParsName(), formParsExt);
	
			}
			else {
				Obj varNode = Tab.insert(Obj.Var, formParsExt.getFormParsName(), typeRightNow);
				fParam.add(varNode.getType());
				formNumber++;
				varNode.setFpPos(formNumber);
				varNode.setLevel(1);
				report_info("Deklarisan lokalna promenljiva " + formParsExt.getFormParsName(), formParsExt);
			}
		}
		else {
			report_error("Greska: Vec je deklarisana promenljiva" +formParsExt.getFormParsName(), formParsExt);
		}
    }
    
    
	public void visit(ActParsExprBegin begin){
		actParams.add(begin.getExpr().struct);
	}

	public void visit(ActParsExprExtension extension){
		actParams.add(extension.getExpr().struct);
	}
    //Expr->Term,AddOpList
    //Term->Factor, MulListOptional
    //Factor->num, char, bool, desig,...
    
    
    //radim visit Factor neterminala
    public void visit(FactorNumber number) {
    	number.struct = Tab.intType;
    }
    public void visit(FactorChar ch) {
    	ch.struct = Tab.charType;
    }
    public void visit(FactorBool bl) {
    	bl.struct = boolType;
    }
    public void visit(FactorExpr fe) {
    	fe.struct = fe.getExpr().struct;
    }
    public void visit(FactorDesignator fd) {  
    	String name;
    	Obj node;
    	if(fd.getDesignator() instanceof DesignatorVarArray){
    		name = ((DesignatorVarArray)fd.getDesignator()).getDesigVarArrayName();
    		node = Tab.find(name);
    		if(node==Tab.noObj) {
    			fd.struct = Tab.noType;
    			report_error("Greska-FactorDesignator: "+name+" nije pronadjen u tabeli simbola", fd);
    		}
    		else {
    			
	    		fd.struct = node.getType().getElemType();
    		}
    	}
    	else if(fd.getDesignator() instanceof DesignatorNamespaceArray) {
    		name =((DesignatorNamespaceArray)fd.getDesignator()).getNamespaceDesigNameArray()+"::"+((DesignatorNamespaceArray)fd.getDesignator()).getDesigNSNameArray();
    		node = Tab.find(name);
    		if(node==Tab.noObj) {
    			fd.struct = Tab.noType;
    			report_error("Greska-FactorDesignator: "+name+" nije pronadjen u tabeli simbola", fd);
    		}
    		else {
	    		fd.struct = node.getType().getElemType();
    		}    	
    	}
    	else if(fd.getDesignator() instanceof DesignatorVar) {
    		name = ((DesignatorVar)fd.getDesignator()).getDesigVarName();
    		node = Tab.find(name);
    		if(node==Tab.noObj) {
    			fd.struct = Tab.noType;
    			report_error("Greska-FactorDesignator: "+name+" nije pronadjen u tabeli simbola", fd);
    		}
    		else {
    			fd.struct = node.getType();
    		}
    	}
    	else if(fd.getDesignator() instanceof DesignatorNamespace) {
    		name =((DesignatorNamespace)fd.getDesignator()).getNamespaceDesigName()+"::"+((DesignatorNamespace)fd.getDesignator()).getDesigNSName();
    		node = Tab.find(name);
    		if(node==Tab.noObj) {
    			fd.struct = Tab.noType;
    			report_error("Greska-FactorDesignator: "+name+" nije pronadjen u tabeli simbola", fd);
    		}
    		else {
    			fd.struct = node.getType();
    		}    	
    	}
    	else {
    		fd.struct=Tab.noType;
    		report_error("Greska: Designator je loseg tipa", fd);
    	}
    }
    public void visit(FactorTypeExpr fte) {
    	if(fte.getExpr().struct.assignableTo(Tab.intType)) {
    		fte.struct = fte.getType().struct;
    	}
    	else {
    		fte.struct = Tab.noType;
			report_error("Greska-Factor-new Expr: Expr treba da bude tipa Int ", fte);
    	}
    }
    public void visit(FactorDesignatorActPars factorDesignatorActPars) {//kad je Designator metoda
    	String name;
    	Obj node;
 
    	if(factorDesignatorActPars.getDesignator() instanceof DesignatorVar) {
    		if(namespaceJe) {
    			name = curNamespaceName+"::"+((DesignatorVar)factorDesignatorActPars.getDesignator()).getDesigVarName();
    		}
    		else {
        		name = ((DesignatorVar)factorDesignatorActPars.getDesignator()).getDesigVarName();
    		}
    		node = Tab.find(name);
    		if(node==Tab.noObj) {
    			report_error("Greska-FactorDesignatorActPars: Ne postoji promenljiva  "+name+" u tabeli simbola", factorDesignatorActPars);
    		}
    		else {
	    		if(node.getKind()==Obj.Meth) {
	    			factorDesignatorActPars.struct = node.getType();
	    		}
	    		else {
	    			report_error("Greska-factorDesignatorActPars: Designator "+name+" mora oznacavati globalnu funkciju glavnog programa", factorDesignatorActPars);
	    			factorDesignatorActPars.struct = Tab.noType;
	    		}
    		

	    		if(node.getLevel()!=actParams.size()) {
	    			report_error("Greska-FactorDesignatorActPars: Broj stvarnih i formalnih parametara "+node.getName()+"metode mora biti isti", factorDesignatorActPars);
	    			factorDesignatorActPars.struct = Tab.noType;
	    		}
	    		else {
	    				boolean flag = true;
	    				int id=0;
	    				Collection<Obj> formalniParametri = node.getLocalSymbols();
	    				for(Obj o:formalniParametri) {
	    					if(o.getType().assignableTo(actParams.get(id))) {
	    						
	    					}
	    					else {
	    						flag=false;
	    						report_error("Greska-FactorDesignatorActPars: Tipovi stvarnih argumenata i formalnih parametara "+node.getName()+"metode nisu kompatibilni", factorDesignatorActPars);
	    					}
	    					id++;
	    				}   				
	    				if(flag==true) {
	    					factorDesignatorActPars.struct = node.getType();
	    				}
	    				else {
							factorDesignatorActPars.struct =Tab.noType;
			    				report_error("Greska-FactorDesignatorActPars: Tipovi stvarnih argumenata i formalnih parametara " +node.getName()+ " metode nisu kompatibilni", factorDesignatorActPars);
	    				}
	    			}
    			}
    		}
	    	else if(factorDesignatorActPars.getDesignator() instanceof DesignatorNamespace) {
	    		name =((DesignatorNamespace)factorDesignatorActPars.getDesignator()).getNamespaceDesigName()+"::"+((DesignatorNamespace)factorDesignatorActPars.getDesignator()).getDesigNSName();
	    		node = Tab.find(name);
	    		if(node==Tab.noObj) {
	    			report_error("Greska-FactorDesignatorActPars: Ne postoji promenljiva  "+name+" u tabeli simbola", factorDesignatorActPars);
	    		}
	    		else {
		    		if(node.getKind()==Obj.Meth) {
		    			factorDesignatorActPars.struct = node.getType();
		    		}
		    		else {
		    			report_error("Greska-factorDesignatorActPars: Designator "+name+" mora oznacavati globalnu funkciju glavnog programa", factorDesignatorActPars);
		    			factorDesignatorActPars.struct = Tab.noType;
		    		}  
	    		}
	    	}
	    	else {
	    		factorDesignatorActPars.struct=Tab.noType;
	    		report_error("Greska: Designator je loseg tipa", factorDesignatorActPars);
	    	}
	    	actParams.clear();
    	//}
    }
    //visitovan Factor
    
    //radim visit Term-a
    
    public void visit(MulFactor mulFact) {
    	if(mulFact.getFactor().struct.assignableTo(Tab.intType)) {
			mulFact.struct = Tab.intType;
		}
		else {
			mulFact.struct = Tab.noType;
			report_error("Greska-Factor-mulList: Treba da bude tipa Int ", mulFact);
		}
    }
    public void visit(MulListOptional mulListOpt) {
    	mulListOpt.struct = mulListOpt.getMulFactor().struct;
    }
    
    public void visit(Term term) {
    	if(term.getMulList() instanceof MulListOptional) {
    		if(term.getFactor().struct.assignableTo(Tab.intType) && ((MulListOptional)term.getMulList()).struct.assignableTo(Tab.intType)) {
    			term.struct=Tab.intType;
    		}
    		else {
    			term.struct = Tab.noType;
    			report_error("Greska-Term: Term i Factor treba da budu tipa int!! ", term);
    		}
    	}
    	else {
    		term.struct = term.getFactor().struct;
    	}
    }
    //visitovan Term<-Factor, MulListOptional
    
    //radim visit AddOpList klase
    public void visit(AddOpListPlus addOpPlus) {
    	if(addOpPlus.getTerm().struct.assignableTo(Tab.intType)) {
    		addOpPlus.struct = Tab.intType;
    	}
    	else {
			addOpPlus.struct = Tab.noType;
			report_error("Greska-AddOpPlus:Treba da budu tipa int!! ", addOpPlus);
		}
    }
    public void visit(AddOpListMinus addOpMinus) {
    	if(addOpMinus.getTerm().struct.assignableTo(Tab.intType)) {
    		addOpMinus.struct = Tab.intType;
    	}
    	else {
			addOpMinus.struct = Tab.noType;
			report_error("Greska-AddOpMinus:Treba da budu tipa int!! ", addOpMinus);
		}
    }
    //visitovana cela AddOpList klasa
    
    //visitujem Expr neterminal
    public void visit(ExprMinusTermAddOp exprMinus) {
    	if(exprMinus.getAddOpList() instanceof AddOpListPlus || exprMinus.getAddOpList() instanceof AddOpListMinus) {
	    	if(exprMinus.getTerm().struct.assignableTo(Tab.intType) && exprMinus.getAddOpList().struct.assignableTo(Tab.intType)) {
	    		exprMinus.struct=Tab.intType;
	    	}
	    	else {
	    		exprMinus.struct=Tab.noType;
	    		report_error("Greska-exprMinus: Posle Minusa moraju biti INT tipovi", exprMinus);
	
	    	}
    	}
    	else {
    		if(exprMinus.getTerm().struct.assignableTo(Tab.intType)) {
	    		exprMinus.struct=Tab.intType;
	    	}
	    	else {
	    		exprMinus.struct=Tab.noType;
	    		report_error("Greska-exprMinus: Posle Minusa mora biti tip INT", exprMinus);
	
	    	}
    	}
    }
    public void visit(ExprTermAddOp exprTerm) {
    	if(exprTerm.getAddOpList() instanceof AddOpListPlus || exprTerm.getAddOpList() instanceof AddOpListMinus) {
	    	if(exprTerm.getTerm().struct.assignableTo(Tab.intType) && exprTerm.getAddOpList().struct.assignableTo(Tab.intType)) {
	    		exprTerm.struct=Tab.intType;
	    	}
	    	else {
	    		exprTerm.struct=Tab.noType;
	    		report_error("Greska-exprTerm: Izrazi nisu kompatibilni", exprTerm);
	    	}
    	}
    	else {//onda sam usao u NoAddOpList i onda gledam samo Term
    		exprTerm.struct = exprTerm.getTerm().struct;
    	}
    }
    //visitovan Expr <-Term, AddOpList
    
    
    //radim visit Designatora
    public void visit(DesignatorVar desigVar) {
    	String name;
    	if(namespaceJe && desigVar.getParent() instanceof FactorDesignatorActPars) {
    		name = curNamespaceName+"::"+desigVar.getDesigVarName();
    	}
    	else {
    		name = desigVar.getDesigVarName();

    	}
    	Obj designator = Tab.find(name);
    	if(designator==Tab.noObj) {
    		report_error("Greska-DesignatorVar: Promenljiva "+name+" ne postoji u tabeli simbola", desigVar);
    	}
    	else {
    		desigVar.obj = designator;
    	}
    }
    //treba odraditi visit DesignatorArray
    public void visit(DesignatorVarArray designatorArray) {
    	//Obj designator = designatorArray.obj;
    	Obj designator = Tab.find(designatorArray.getDesigVarArrayName());
    	if(designator==Tab.noObj) {
    		designatorArray.obj = Tab.noObj;
    		report_error("Greska: Promenljiva "+designatorArray.getDesigVarArrayName()+" ne postoji u tabeli simbola", designatorArray);
    	}
    	else {
	    	if(designator.getKind()==Obj.Var && designator.getType().getKind()==Struct.Array) {
	    		if(!designatorArray.getExpr().struct.assignableTo(Tab.intType)) {
	        		report_error("Greska-DesignatorVarArray: Expr u zagradama kod  "+designatorArray.getDesigVarArrayName()+" nije tipa INT", designatorArray);
	        	}
	    		else {
	    			//designatorArray.obj = new Obj(Obj.Elem, designator.getName(), designator.getType().getElemType());
	    			designatorArray.obj  = designator;
	    		}
	    		
	    	}
	    	else {
	    		report_error("Greska: "+designatorArray.getDesigVarArrayName()+" nije Niz", designatorArray);
	    	}
    	}
    	
    }
    
    public void visit(DesignatorNamespace designatorNamespace) {
    	String name = designatorNamespace.getNamespaceDesigName() +"::"+designatorNamespace.getDesigNSName();
    	Obj node = Tab.find(name);
    	if(node==null) {
    		designatorNamespace.obj = Tab.noObj;
    		report_error("Nije nadjen simbol "+name+" u tabeli simbola!", designatorNamespace);
    	}
    	else {
    		designatorNamespace.obj = node;
    	}
    }
    
    public void visit(DesignatorNamespaceArray array) {
    	String name = array.getNamespaceDesigNameArray() +"::"+array.getDesigNSNameArray();
    	Obj designator = Tab.find(name);
    	if(designator==Tab.noObj) {
    		array.obj = Tab.noObj;
    		report_error("Greska: Niz "+name+" ne postoji u tabeli simbola", array);
    	}
    	else {
	    	if(designator.getKind()==Obj.Var && designator.getType().getKind()==Struct.Array) {
	    		if(!array.getExpr().struct.assignableTo(Tab.intType)) {
	        		report_error("Greska: Expr u zagradama kod  "+name+" nije tipa INT", array);
	        	}
	    		else {
	    			array.obj = designator;
	    		}
	    		
	    	}
	    	else {
	    		report_error("Greska: "+name+" nije Niz", array);
	    	}
    	}
    }
    //ostao visit DesignatorNamespace-a
    //visitovan Designator
	
    //visitujem DesignatorStatement
    
    public void visit(DesignatorStatementAssignOp designExpr) {
    	Designator des = designExpr.getDesignator();
    	if(des.obj.getKind()==Obj.Var) {
    		if(des.obj.getType().getKind()==Struct.Array) {
    			
    			if(!des.obj.getType().getElemType().assignableTo(designExpr.getExpr().struct)) {
        			report_error("Greska-designatorStatement: Designator: "+des.obj.getName()+" nije kompatibilan sa expr pri dodeli", designExpr);
        		}
    		}
    		else {
    			if(!des.obj.getType().assignableTo(designExpr.getExpr().struct)) {
        			report_error("Greska-designatorStatement: Designator: "+des.obj.getName()+" nije kompatibilan sa expr pri dodeli", designExpr);
        		}
    		}
    		
    	}
    	else {
			report_error("Greska-designatorStatement: Designator: "+designExpr.getDesignator().obj.getName()+" nije promenljiva ili element niz ", designExpr);
    	}
    }
    
    public void visit(DesignatorStatementPlusPlus plusplus) {
    	Designator designator = plusplus.getDesignator();
    	
    	if(designator instanceof DesignatorVar || designator instanceof DesignatorNamespace) {
    		if(designator.obj.getType().getKind()==Struct.Array) {
        			report_error("Greska-DesignatorStatementPlusPlus: "+designator.obj.getName()+ " mora biti promenljiva ili element niza!", plusplus);
        	}
    		else {
    			if(!designator.obj.getType().assignableTo(Tab.intType)){
        			report_error("Greska-DesignatorStatementPlusPlus: "+designator.obj.getName()+ " mora biti int, char ili bool tipa!", plusplus);
        		}
    		}
    	}
    	else {
    		if(!designator.obj.getType().getElemType().assignableTo(Tab.intType)){
        		report_error("Greska-DesignatorStatementPlusPlus: "+designator.obj.getName()+ " mora biti int, char ili bool tipa!", plusplus);
        	}
    	} 
    }

    public void visit(DesignatorStatementMinusMinus minusminus) {
    	Designator designator = minusminus.getDesignator();
    	
    	if(designator instanceof DesignatorVar || designator instanceof DesignatorNamespace) {
    		if(designator.obj.getType().getKind()==Struct.Array) {
        			report_error("Greska-DesignatorStatementMinusMinus: "+designator.obj.getName()+ " mora biti promenljiva ili element niza!", minusminus);
        	}
    		else {
    			if(!designator.obj.getType().assignableTo(Tab.intType)){
        			report_error("Greska-DesignatorStatementMinusMinus: "+designator.obj.getName()+ " mora biti int, char ili bool tipa!", minusminus);
        		}
    		}
    	}
    	else {
    		if(!designator.obj.getType().getElemType().assignableTo(Tab.intType)){
        		report_error("Greska-DesignatorStatementMinusMinus: "+designator.obj.getName()+ " mora biti int, char ili bool tipa!", minusminus);
        	}
    	} 	
    }
    
    public void visit(DesignatorStatementActParams actParam) {
    	Designator des = actParam.getDesignator();
    	String name = des.obj.getName();
    	if(des.obj.getKind()!=Obj.Meth) {
			report_error("Greska-DesignatorStatementActParams: Designator "+name+" mora biti globalna funkcija glavnog programa", actParam);
    	}
    	else {
    		String s = name;
    		if(actParam.getActParams() instanceof  ActParameters) {
    			if(s.contentEquals("chr")) {
    				if(actParams.size()!=1) {
    					report_error("Greska-DesignatorStatementActParams: Funkcija chr sadrzi samo jedan parametar", actParam);
    				}
    				else {
    					if(actParams.get(0).getKind()==Struct.Int) {
    					}
    					else {
        					report_error("Greska-DesignatorStatementActParams: Parametar funkcije chr mora biti tipa int", actParam);
    					}
    				}
    			}
    			else if(s.contentEquals("ord")) {
    				if(actParams.size()!=1) {
    					report_error("Greska-DesignatorStatementActParams: Funkcija ord sadrzi samo jedan parametar", actParam);
    				}
    				else {
    					if(actParams.get(0).getKind()==Struct.Char) {
    					}
    					else {
        					report_error("Greska-DesignatorStatementActParams: Parametar funkcije ord mora biti tipa char", actParam);
    					}
    				}
    			}
    			else if(s.contentEquals("len")) {
    				if(actParams.size()!=1) {
    					report_error("Greska-DesignatorStatementActParams: Funkcija len sadrzi samo jedan parametar", actParam);
    				}
    				else {
    					if(actParams.get(0).getKind()==Struct.Array) {
    						if(actParams.get(0).getElemType().getKind()==Struct.Int || actParams.get(0).getElemType().getKind()== Struct.Char) {
    						}
    						else {
            					report_error("Greska-DesignatorStatementActParams: Parametar funkcije len mora biti niz tipa int ili char", actParam);
    						}
    					}
    					else {
        					report_error("Greska-DesignatorStatementActParams: Parametar funkcije len mora biti niz", actParam);
    					}
    				}
    			}
    			else if(actParam.getActParams() instanceof ActParameters) {
        		if(des.obj.getLevel()!=actParams.size()) {
        			report_error("Greska-DesignatorStatementActParams: Broj stvarnih i formalnih parametara "+des.obj.getName()+"metode mora biti isti", actParam);
        		}
        		else {
        			int id=0;
    				Collection<Obj> formalniParametri =des.obj.getLocalSymbols();
    				for(Obj o:formalniParametri) {
    					if(o.getType().assignableTo(actParams.get(id))) {
    						
    					}
    					else {
    						report_error("Greska-DesignatorStatementActParams: Tipovi stvarnih argumenata i formalnih parametara "+des.obj.getName()+"metode nisu kompatibilni", actParam);
    					}
    					id++;
    				}
    			}
        	}
    		else {
        		if(des.obj.getLevel()!=0) {				//ovo proveriti da li radi
        			report_error("Greska-DesignatorStatementActParams: "+des.obj.getName()+"ne treba da ima parametre", des);
        		}
    		
    		}
    		actParams.clear();
    		}
    	}
    }
    
    public void visit(DesignatorStatementWithMul designatorMul) {
    	Obj posleZvezde = designatorMul.getDesniDesignator().obj;
    	Designator posleJednako = designatorMul.getDesignator();
    	if(posleJednako.obj.getType().getKind()!=Struct.Array) {
    		report_error("Greska-DesignatorStatementWithMul: Designator "+posleJednako.obj.getName()+" mora biti niz", designatorMul);
    	}
    	else {
    		if(posleJednako.obj.getType().getElemType().compatibleWith(posleZvezde.getType().getElemType())) {
    			for(Obj k:desCom) {
    				if(k.getType().compatibleWith(posleJednako.obj.getType().getElemType())) {
    					//super sve
    				}
    				else {
    					report_error("Greska-DesignatorStatementWithMul: Polje/element niza "+k.getName()+" mora biti kompatibilan sa designatorom "+ posleJednako.obj.getName(), designatorMul);
    				}
    			}
    		}
    		else {
    			report_error("Greska-DesignatorStatementWithMul: Designator "+ posleJednako.obj.getName()+" mora biti kompatibilan sa designatorom "+posleZvezde.getName(), designatorMul);
    		}
    	}
    	desCom.clear();
    }
    
    
    public void visit(DesniDesignator desni) {
    	Designator designator = desni.getDesignator();
    	if(designator.obj.getType().getKind()==Struct.Array) {
    		desni.obj = designator.obj;
    	}
    	else {
    		desni.obj = Tab.noObj;
    		report_error("Greska-DesignatorStatementWithMul: Designator sa desne strane posle * mora biti niz", desni);
    	}
    }
    
    public void visit(DesignatorComma designatorComma) {
    	Designator designator = designatorComma.getDesignator();
    	if(designator.obj.getKind()==Obj.Var) {
    		if(designator.obj.getType().getKind()==Struct.Array) {
    				Obj t = new Obj(Obj.Var, designator.obj.getName(), designator.obj.getType().getElemType());
    				desCom.add(t);
    		}
    		else {	
				Obj t = new Obj(Obj.Var, designator.obj.getName(), designator.obj.getType());
				desCom.add(t);
    		}
    	}

    	else {
    		report_error("Greska-DesignatorStatement-Comma: Designator"+designator.obj.getName()+" mora biti promenljiva ili element niza", designatorComma);
    	}
    
    }
    
    //visitovan DesignatorStatement-> dovrsiti DesignatorStatementWithMul
    
    
    public void visit(StatementRead read) {
    	Designator designator=read.getDesignator();
    	if(designator instanceof DesignatorVar || designator instanceof DesignatorNamespace) {
    		if(designator.obj.getType().getKind()==Struct.Array) {
        			report_error("Greska: Statement-read: "+designator.obj.getName()+ "mora biti promenljiva ili element niza!", read);
        	}
    		else {
    			if(designator.obj.getType().assignableTo(Tab.intType)
        				|| designator.obj.getType().assignableTo(Tab.charType)
        				|| designator.obj.getType().assignableTo(boolType)){
        			//report_info("Info: Izvrsava se citanje "+designator.obj.getName(), read);
        		}
        		else {
        			report_error("Greska: Statement-read: "+designator.obj.getName()+ "mora biti int, char ili bool tipa!", read);
        		}
    		}
    	}
    	else {
    		if(designator.obj.getType().getElemType().assignableTo(Tab.intType)
        			|| designator.obj.getType().getElemType().assignableTo(Tab.charType)
        			|| designator.obj.getType().getElemType().assignableTo(boolType)){
        		//report_info("Info: Izvrsava se citanje "+designator.obj.getName(), read);
        	}
        	else {
        		report_error("Greska: Statement-read: "+designator.obj.getName()+ "mora biti int, char ili bool tipa!", read);
        	}
    	}
    }
    	 
    public void visit(StatementPrint print) {
    	Struct exp = print.getExpr().struct;
    	if(exp.assignableTo(Tab.intType) || exp.assignableTo(Tab.charType)|| exp.assignableTo(boolType)) {
    		printCallCount++;
    	}
    	else {
    		report_error("Greska-Statement-print: Expr mora biti tipa int, char ili bool", print);
    	}
    	
    }
    
    public void visit(ForOppening forIndikator) {
    	petlja = petlja+1;
    }
    
    public void visit(StatementBreak breakStatement) {
    	if(petlja<1) {
    		report_error("Greska-StatementBreak: Break mora da se nalazi samo unutar petlje", breakStatement);
    	}
    }
    
    public void visit(StatementContinue continueStatement) {
    	if(petlja<1) {
    		report_error("Greska-StatementContinue: Continue mora da se nalazi samo unutar petlje", continueStatement);
    	}
    }
    
    public void visit(StatementFor forS) {
    	petlja--;
    }
    
    public void visit(StatementReturn retStatement) {
    	if(currentMethod!=Tab.noObj) {
	    	
	    	if(retStatement.getExprBrackets() instanceof ExprOptional) {
	    		Struct expr = ((ExprOptional)(retStatement.getExprBrackets())).getExpr().struct;	    		
	    		if(expr.assignableTo(currentMethod.getType())) {
	    			returnIma=true;
	    		}
	    		else {
	    			report_error("Greska-Statement-return: Povratni tip nije ekvivalentan povratnom tipu funkcije", retStatement);
	    		}
	    	}
	    	else {//nema expr
	    		if(currentMethod.getType().assignableTo(Tab.noType)) {
	    			returnIma=true;
	    		}
	    		else {
	    			report_error("Greska-Statement-return: Povratni tip nije dobar, funkcija je deklarisana kao void", retStatement);
	    		}
	    		
	    	}	
    	}
    	else {
			report_error("Greska-Statement-return: Nalazi se van tela metode, odnosno globalne funkcije", retStatement);
    	}
    }
    
    public void visit(StatementIf stateIf) {
    	//moram ispitati da li je Condition tipa Bool
    	
    	if(!stateIf.getCondition().struct.assignableTo(boolType)) {
    		report_error("Greska-StatementIf: Condition u zagradi mora biti tipa bool", stateIf);
    	}
    }
    
    
    public void visit(CondExpr ex) {
    	ex.struct=ex.getExpr().struct;
    }
    
    public void visit(RelationExpr expr) {
    	if(expr.getExpr().struct.compatibleWith(expr.getExpr1().struct)) {
    		Expr elevi = expr.getExpr();
    		Expr edesni = expr.getExpr1();
    		if(elevi.struct.getKind()==Struct.Array && edesni.struct.getKind()==Struct.Array) {
    			if(expr.getRelop() instanceof RelOpDoubleEqual || expr.getRelop() instanceof RelOpNoEqual) {
    				expr.struct = boolType;
    			}
    			else {
    				expr.struct=Tab.noType;
    	    		report_error("Greska-ConditionRelationOperator: Za nizove provera moze biti samo == ili !=", expr);

    			}
    		}
    		else {
    			expr.struct = boolType;
    		}
    		
    		
    	}
    	else {
    		report_error("Greska-ConditionRelationOperator: Tipovi oba izraza moraju biti kompatibilni", expr);
    		expr.struct = Tab.noType;
    	}
    }
    
    public void visit(ConditionFact fact) {
    	fact.struct = fact.getCondFact().struct;
    }
    
    public void visit(ConditionTermAndFact termAndFact) {
    	if(!termAndFact.getCondTerm().struct.compatibleWith(termAndFact.getCondFact().struct)) {
    		report_error("Greska-ConditionTermAndFact: prilikom AND operacije moraju biti istog tipa", termAndFact);
    		termAndFact.struct = Tab.noType;
    	}
    	else {
    		termAndFact.struct = termAndFact.getCondFact().struct;
    	}
    }
    
    public void visit(ConditionTerm term) {
    	term.struct = term.getCondTerm().struct;
    }
    
    public void visit(ConditionOrTerm condOrTerm) {
    	if(condOrTerm.getCondition().struct.compatibleWith(condOrTerm.getCondTerm().struct)) {
    		condOrTerm.struct = condOrTerm.getCondTerm().struct;
    	}
    	else {
    		condOrTerm.struct = Tab.noType;
    		report_error("Greska-ConditionOrTerm: prilikom OR operacije moraju biti istog tipa", condOrTerm);
    	}
    }
    
	public boolean passed(){
    	return !errorDetected;
    }
}
