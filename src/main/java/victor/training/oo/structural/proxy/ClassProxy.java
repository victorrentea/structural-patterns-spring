package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class ClassProxy {
   public static void main(String[] args) {
      Math impl = new Math();

      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            log.info("The method {} is called with args {} ", method.getName(), Arrays.toString(args));
            return method.invoke(impl, args);
         }
      };
      Math math = (Math) Enhancer.create(Math.class, callback);

      bizLogic(math);
   }

   private static void bizLogic(Math math) {
      System.out.println(math.getClass());
      System.out.println(math.sum(1, 1));
      System.out.println(math.sum(2, 0));
      System.out.println(math.sum(4, -2));
      System.out.println(math.product(1, 2));
      System.out.println(math.product(4, 2));
   }
}

class Math  {
   public int sum(int a, int b) {
      return a + b;
   }
   public int product(int a, int b) {
      return a  * b;
   }
}