package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LetsPlay {
   public static void main(String[] args) {
      Maths realStuff = new Maths();


      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Calling method " + method.getName() + " with args  " + Arrays.toString(args));
            return method.invoke(realStuff, args);
         }
      };

      IMaths maths = (IMaths) Proxy.newProxyInstance(LetsPlay.class.getClassLoader(),
          new Class<?>[]{IMaths.class}, h);

      method(maths);
   }

   public static void method(IMaths maths) {
      System.out.println(maths.sum(1,1));
      System.out.println(maths.sum(1,2));
      System.out.println(maths.multiply(1,2));
   }
}

interface IMaths {
   int sum(int a, int b);

   int multiply(int a, int b);
}

class Maths implements IMaths {
   public int sum(int a, int b) {
      return a + b;
   }
   @Override
   public int multiply(int a, int b) {
      return a * b;
   }
}