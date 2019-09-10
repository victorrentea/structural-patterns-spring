package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class InterfaceProxies {
    public static void main(String[] args) {
        MathsImpl realImpl = new MathsImpl();

        MethodInterceptor h = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.info("Calls method {}, with params {}",
                        method.getName(),
                        Arrays.toString(args));
                return method.invoke(realImpl, args);
            }
        };
        MathsImpl maths = (MathsImpl) Enhancer.create(MathsImpl.class, h);

        System.out.println(maths.sum(1,1));
        System.out.println(maths.sum(-2,4));
        System.out.println(maths.sum(1,4));
    }
}


class MathsImpl {
    public Integer sum(int a, int b) {
        return a + b;
    }
}
