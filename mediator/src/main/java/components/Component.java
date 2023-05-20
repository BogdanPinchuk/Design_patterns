package components;

import mediator.Mediator;

public interface Component {
	void setMediator(Mediator mediator);

	String getName();
}
