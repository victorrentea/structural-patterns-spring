package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@Slf4j
public class InterfaceProxy {
   public static void main(String[] args) {
      MathImpl impl = new MathImpl();

      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("The method {} is called with args {} ", method.getName(), Arrays.toString(args));
            return method.invoke(impl, args);
         }
      };
      Math math = (Math) Proxy.newProxyInstance(InterfaceProxy.class.getClassLoader(),
          new Class<?>[]{Math.class}, h);

      bizLogic(math);
   }

   private static void bizLogic(Math math) {
      System.out.println(math.sum(1, 1));
      System.out.println(math.sum(2, 0));
      System.out.println(math.sum(4, -2));
      System.out.println(math.product(1, 2));
      System.out.println(math.product(4, 2));
   }
}

interface Math {
   int sum(int a, int b);
   int product(int a, int b);
}

class MathImpl implements Math {

   @Override
   public int sum(int a, int b) {
      return a + b;
   }

   @Override
   public int product(int a, int b) {
      return a  * b;
   }
}