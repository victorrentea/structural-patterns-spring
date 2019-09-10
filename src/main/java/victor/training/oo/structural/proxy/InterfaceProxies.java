package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class InterfaceProxies {
    public static void main(String[] args) {
        MathsImpl realImpl = new MathsImpl();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                log.info("Calls method {}, with params {}",
                        method.getName(),
                        Arrays.toString(args));
                return method.invoke(realImpl, args);
            }
        };
        Maths maths = (Maths) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
                new Class<?>[]{Maths.class},
                h);

        System.out.println(maths.sum(1,1));
        System.out.println(maths.sum(-2,4));
        System.out.println(maths.sum(1,4));
    }
}

interface Maths {
    Integer sum(int a, int b);
}


class MathsImpl implements Maths {
    public Integer sum(int a, int b) {
        return a + b;
    }
}
