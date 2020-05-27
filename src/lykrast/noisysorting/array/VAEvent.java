package lykrast.noisysorting.array;

public class VAEvent {
	private Type type;

	public VAEvent(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	public VAEventSingle toSingle() {
		return (VAEventSingle)this;
	}

	public static enum Type {
		SINGLE, FRAME, REFRESH, CLEAR;
	}
}
