package domain;

public class Equal extends OperatorDecorator{
	
	private String symbol = "=";
	
	public Equal(Operator o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String getCode() {
		return wrappee.getCode()+symbol;
	}

}
