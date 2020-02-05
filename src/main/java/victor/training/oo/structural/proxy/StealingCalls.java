package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.MathContext;
import java.util.Arrays;

public class StealingCalls {
    public static void main(String[] args) {
        Maths realImplem = new Maths();


        Callback callback = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("Calling method " + method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(realImplem, args);
            }
        };
        Maths proxy = (Maths) Enhancer.create(Maths.class, callback);

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
class Maths {
    public int sum(int a, int b) {
        return  a + b;
    }
}