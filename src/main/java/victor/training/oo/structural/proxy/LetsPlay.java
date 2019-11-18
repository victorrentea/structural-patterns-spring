package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LetsPlay {

    public static void main(String[] args) {
        MathematicsImpl realImpl = new MathematicsImpl();

        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("CIA here: You are calling : " + method.getName() +
                        " with args " + Arrays.toString(args));
                return method.invoke(realImpl, args);
            }
        };

        Mathematics m = (Mathematics) Proxy.newProxyInstance(LetsPlay.class.getClassLoader(),
                new Class<?>[]{Mathematics.class},
                h);

        bizLogic(m);
    }

    private static void bizLogic(Mathematics m) {
        System.out.println("Who's on the phone? " + m.getClass());
        System.out.println(m.sum(1,1));
        System.out.println(m.sum(2,0));
        System.out.println(m.sum(3,-1));
        System.out.println(m.sum(3,1));
    }
}


interface Mathematics {
    int sum(int a, int b);
}
class MathematicsImpl implements Mathematics {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}