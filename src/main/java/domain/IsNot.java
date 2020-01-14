package domain;

public class IsNot extends OperatorDecorator{
	
	public IsNot(Operator o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	private String symbol = "!";
	
	@Override
	public String getCode(){
		return wrappee.getCode()+symbol;
	}

}
