package net.edmacdonald.jvmcli.expressionevaluator;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

public class BooleanIdentity extends EventEvaluatorBase {
    private boolean value;
    @Override
    public boolean evaluate(Object event) throws NullPointerException, EvaluationException {
        return value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
