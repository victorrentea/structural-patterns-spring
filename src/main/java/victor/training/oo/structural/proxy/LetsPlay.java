package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LetsPlay {

    public static void main(String[] args) {
        Mathematics realImpl = new Mathematics();

        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("CIA here: You are calling : " + method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(realImpl, args);
            }
        };
        Mathematics m = (Mathematics) Enhancer.create(Mathematics.class, methodInterceptor);

        bizLogic(m);
    }

    private static void bizLogic(Mathematics m) {
        System.out.println("Who's on the phone? " + m.getClass());
        System.out.println(m.sum(1,1));
        System.out.println(m.sum(2,0));
        System.out.println(m.sum(3,-1));
        System.out.println(m.sum(3,1));
        System.out.println(m.product(3,2));
    }
}

class Mathematics {
    public int sum(int a, int b) {
        return a + b;
    }
    public int product(int a, int b) {
        return a * b;
    }
}