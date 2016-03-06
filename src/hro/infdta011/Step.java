package hro.infdta011;

public interface Step<Input, Output> {
	public Output run(Input input);
}
