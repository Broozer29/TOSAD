package domain;

public class Smaller extends OperatorDecorator{
	
	public Smaller(Operator o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	private String symbol = "<";
	
	@Override
	public String getCode(){
		return wrappee.getCode()+symbol;
	}

}
