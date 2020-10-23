package victor.training.oo.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyMagic {
   private static final Logger log = LoggerFactory.getLogger(ProxyMagic.class);
   public static void main(String[] args) {
      Maths real = new Maths();

      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            log.info("Calling method {} with args {}", method.getName(), Arrays.toString(args));
            return method.invoke(real, args);
         }
      };
      Maths maths = (Maths) Enhancer.create(Maths.class, callback);

//      maths = new Maths() {
//         @Override
//         public int sum(int a, int b) {
//            return super.sum(a, b);
//         }
//      };

      bizMethod(maths);
   }

   private static void bizMethod(Maths maths) {
      System.out.println("Who am I calling ? " + maths.getClass());
      System.out.println(maths.sum(1, 1));
      System.out.println(maths.sum(2, 0));
      System.out.println(maths.sum(3, -1));
      System.out.println(maths.product(-2, -1));

      System.out.println(maths.sum(3, 1));
   }
}

class Maths {
   public int sum(int a, int b) {
      return a+b;
   }
   public int product(int a, int b) {
      return a*b;
   }
}
