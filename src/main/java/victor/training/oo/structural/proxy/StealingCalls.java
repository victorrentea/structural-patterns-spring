package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class StealingCalls {
    public static void main(String[] args) {
        MathsImplem realImplem = new MathsImplem();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Calling method " + method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(realImplem, args);
            }
        };
        Maths proxy = (Maths) Proxy.newProxyInstance(StealingCalls.class.getClassLoader(),
                new Class<?>[]{Maths.class},
                h);

        bizLogic(proxy);
    }

    private static void bizLogic(Maths proxy) {
        System.out.println(proxy.getClass().getName());
        System.out.println(proxy.sum(1, 1));
        System.out.println(proxy.sum(2, 0));
        System.out.println(proxy.sum(3, -1));
        System.out.println(proxy.sum(3, 1));
    }
}

interface Maths {
    int sum(int a, int b);
}

class MathsImplem implements Maths {
    public int sum(int a, int b) {
        return  a + b;
    }
}