package com.meyoustu.amuse.gous;

/**
 * @author Liangcheng Juves
 * Created at 2020/05/20 12:56
 */
public abstract class WholeImpl implements Whole {
    private WholeImpl() {
    }

    public static WholeImpl WHOLE = new WholeImpl() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };

    private static WholeImpl whole;

    public static WholeImpl getInstance() {
        if (whole == null) {
            synchronized (WholeImpl.class) {
                whole = (whole == null) ?
                        new WholeImpl() {
                            @Override
                            public int hashCode() {
                                return super.hashCode();
                            }
                        } :
                        whole;
            }
        }
        return whole;
    }

    public static WholeImpl newInstance() {
        return new WholeImpl() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
    }
}
