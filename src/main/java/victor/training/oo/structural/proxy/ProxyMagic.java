package victor.training.oo.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyMagic {
   private static final Logger log = LoggerFactory.getLogger(ProxyMagic.class);
   public static void main(String[] args) {
      MathsImpl realImplem = new MathsImpl();
      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("Calling method {} with args {}", method.getName(), Arrays.toString(args));
            return method.invoke(realImplem, args);
         }
      };

      Maths maths = (Maths) Proxy.newProxyInstance(ProxyMagic.class.getClassLoader(),
          new Class<?>[]{Maths.class}, h);

      bizMethod(maths);
   }

   private static void bizMethod(Maths maths) {
      System.out.println(maths.sum(1, 1));
      System.out.println(maths.sum(2, 0));
      System.out.println(maths.sum(3, -1));
      System.out.println(maths.product(-2, -1));

      System.out.println(maths.sum(3, 1));
   }
}

class MathsImpl implements  Maths{
   public int sum(int a, int b) {
      return a+b;
   }
   public int product(int a, int b) {
      return a*b;
   }
}


interface Maths {
   int sum (int a, int b);
   int product (int a, int b);
}