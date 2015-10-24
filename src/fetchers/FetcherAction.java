package fetchers;

import fetchers.selenium.SeleniumFetcher;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jeremiep on 15-10-22.
 */
public abstract class FetcherAction<T> {
    private abstract class FetcherActionState {
        public abstract void setCompleted();
        public abstract void setCompleted(T result);
        public abstract boolean isCompleted();
        public abstract boolean hasResult();
        public abstract T getValue();
    }

    private class UnexecutedActionState extends FetcherActionState {
        @Override
        public void setCompleted() {
            (FetcherAction.this).currentState = new ExecutedActionState();
        }

        @Override
        public void setCompleted(T result) {
            (FetcherAction.this).currentState = new ExecutedActionState(result);
        }

        @Override
        public boolean isCompleted() {
            return false;
        }

        @Override
        public boolean hasResult() {
            throw new RuntimeException("Action has not been executed yet.");
        }

        @Override
        public T getValue() {
            throw new RuntimeException("Can't get result of an unexecuted action.");
        }
    }

    private class ExecutedActionState extends FetcherActionState {
        private T value = null;
        private Date completed_date = null;

        public ExecutedActionState() {
            this.value = null;
            this.completed_date = Calendar.getInstance().getTime();
        }

        public ExecutedActionState(T result) {
            this.value = result;
            this.completed_date = Calendar.getInstance().getTime();
        }

        @Override
        public void setCompleted() {
            throw new RuntimeException("Action has already been executed.");
        }

        @Override
        public void setCompleted(T result) {
            throw new RuntimeException("Action has already been executed.");
        }

        @Override
        public boolean isCompleted() {
            return true;
        }

        @Override
        public boolean hasResult() {
            return this.isCompleted() && this.value != null;
        }

        @Override
        public T getValue() {
            if(!this.hasResult()) { throw new RuntimeException("Executed action has no result ..."); }
            return this.value;
        }
    }

    private FetcherActionState currentState = new UnexecutedActionState();

    public abstract void executeAction(SeleniumFetcher fetcher) throws FetcherActionException;

    protected void setCompleted() {
        this.currentState.setCompleted();
    }

    protected void setCompleted(T result) {
        this.currentState.setCompleted(result);
    }

    public boolean isCompleted() {
        return this.currentState.isCompleted();
    }

    public boolean hasResult() {
        return this.currentState.hasResult();
    }

    public T getExecutedValue() { return this.currentState.getValue(); }
}
