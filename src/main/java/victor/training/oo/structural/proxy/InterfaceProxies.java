package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
    public static void main(String[] args) {
        MathsImplem impl = new MathsImplem();
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Calling " + method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(impl, args);
            }
        };
        IMaths maths = (IMaths) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
                new Class<?>[]{IMaths.class},
                h);

        bizMethod(maths);
    }

    private static void bizMethod(IMaths maths) {
        System.out.println(maths.sum(1,1));
        System.out.println(maths.sum(2,0));
        System.out.println(maths.sum(3,-1));
        System.out.println(maths.product(2,1));
        System.out.println(maths.sum(3,1));
    }
}
interface IMaths {
    int sum(int a, int b);
    int product(int a, int b);
}

class MathsImplem implements IMaths {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int product(int a, int b) {
        return a * b;
    }
}