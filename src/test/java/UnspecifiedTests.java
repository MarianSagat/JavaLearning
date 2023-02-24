import org.testng.annotations.Test;

public class UnspecifiedTests {

    interface IAction<T> {
        void execute(T item);
    }

    static class Option {
        Option() {
            count = 1;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        Integer count;
    }

    void configure(IAction<? super Option> action) {
        action.execute(new Option());
    }

    @Test
    public void anonymousClassOverridingMethodInBody() {

        //NOTE: THIS IS ONLY EXCEPTION WHEN INTERFACE IS WITH NEW KEYWORD!!!!
        //ALTHOUGH INTERFACES CANNOT BE INSTANTIATED!!!
        //ANONYMOUS "INLINE" CLASS DECLARATION
        configure(new IAction<Option>() {
            @Override
            public void execute(Option item) {
                item.setCount(2);
                for (int i = 0; i < item.count; i++)
                    System.out.println("execution of action: " + i);
            }
        });

    }
}
