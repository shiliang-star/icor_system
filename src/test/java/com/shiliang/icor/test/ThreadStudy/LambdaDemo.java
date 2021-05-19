package com.shiliang.icor.test.ThreadStudy;

/**
 * @Author sl
 * @Date 2021/3/1 20:38
 * @Description Lambda表达式
 */
public class LambdaDemo {

    //静态内部类
    static class LambdaImpl2 implements ILambda{

        @Override
        public void test() {
            System.out.println("我爱Lambda表达式-------静态内部类");
        }
    }


    public static void main(String[] args) {
        ILambda lambda = new LambdaImpl();
        lambda.test();

        lambda = new LambdaImpl2();
        lambda.test();

        //静态内部类
         class LambdaImpl3 implements ILambda{

            @Override
            public void test() {
                System.out.println("我爱Lambda表达式-------局部内部类");
            }
        }
        lambda = new LambdaImpl3();
        lambda.test();

        //匿名内部类，没有类的名称，必须借助接口或者父类
        lambda=new ILambda() {
            @Override
            public void test() {
                System.out.println("我爱Lambda表达式-------匿名内部类");
            }
        };
        lambda.test();


        //调用lambda表达式简化
        lambda = () -> {
            System.out.println("我爱Lambda表达式-------Lambda表达式");
        };
        lambda.test();


        lambda = () -> System.out.println("我爱Lambda表达式-------Lambda表达式");
        lambda.test();
    }



}

/**
 * 函数式接口（任何接口，如果只包含唯一一个抽象方法，那么他就是一个函数式接口）
 */
@FunctionalInterface
interface ILambda{
    void test();

    default int mv() {
        return 0;
    }
}

class LambdaImpl implements ILambda{

    @Override
    public void test() {
        System.out.println("我爱Lambda表达式");
    }
}