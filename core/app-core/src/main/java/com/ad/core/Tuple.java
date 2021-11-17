package com.ad.core;

/**
 * @author CoderYoung
 */
public class Tuple {

    public static <T1, T2> Tuple2<T1, T2> tuple(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> tuple(T1 t1, T2 t2, T3 t3) {
        return new Tuple3<>(t1, t2, t3);
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> tuple(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new Tuple4<>(t1, t2, t3, t4);
    }

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> tuple(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        return new Tuple5<>(t1, t2, t3, t4, t5);
    }


    /**
     * 二元组
     *
     * @param <T1>
     * @param <T2>
     */
    public static class Tuple2<T1, T2> {
        private T1 t1;
        private T2 t2;

        public Tuple2(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public T1 getT1() {
            return t1;
        }

        public void setT1(T1 t1) {
            this.t1 = t1;
        }

        public T2 getT2() {
            return t2;
        }

        public void setT2(T2 t2) {
            this.t2 = t2;
        }

    }

    /**
     * 三元组
     *
     * @param <T1>
     * @param <T2>
     * @param <T3>
     */
    public static class Tuple3<T1, T2, T3> extends Tuple2<T1, T2> {
        private T3 t3;

        public Tuple3(T1 t1, T2 t2, T3 t3) {
            super(t1, t2);
            this.t3 = t3;
        }

        public T3 getT3() {
            return t3;
        }

        public void setT3(T3 t3) {
            this.t3 = t3;
        }

    }

    /**
     * 四元组
     *
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param <T4>
     */
    public static class Tuple4<T1, T2, T3, T4> extends Tuple3<T1, T2, T3> {
        private T4 t4;

        public Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
            super(t1, t2, t3);
            this.t4 = t4;
        }

        public T4 getT4() {
            return t4;
        }

        public void setT4(T4 t4) {
            this.t4 = t4;
        }
    }

    /**
     * 五元组
     *
     * @param <T1>
     * @param <T2>
     * @param <T3>
     * @param <T4>
     * @param <T5>
     */
    public static class Tuple5<T1, T2, T3, T4, T5> extends Tuple4<T1, T2, T3, T4> {
        private T5 t5;

        public Tuple5(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
            super(t1, t2, t3, t4);
            this.t5 = t5;
        }

        public T5 getT5() {
            return t5;
        }

        public void setT5(T5 t5) {
            this.t5 = t5;
        }
    }

}
