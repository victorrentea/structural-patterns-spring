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
      Maths realStuff = new Maths();


      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("Calling method " + method.getName() + " with args  " + Arrays.toString(args));
            return method.invoke(realStuff, args);
         }
      };

      Maths maths = (Maths) Enhancer.create(Maths.class, callback);

      method(maths);
   }

   public static void method(Maths maths) {
      System.out.println(maths.getClass());
      System.out.println(maths.sum(1,1));
      System.out.println(maths.sum(1,2));
      System.out.println(maths.multiply(1,2));
   }
}


class Maths {
   public int sum(int a, int b) {
      return a + b;
   }
   public int multiply(int a, int b) {
      return a * b;
   }
}