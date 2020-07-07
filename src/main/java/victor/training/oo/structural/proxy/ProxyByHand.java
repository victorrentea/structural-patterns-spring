package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyByHand {
   public static void main(String[] args) {
      Math realImplem = new Math();
      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("Calling method " + method.getName() + " with args " + Arrays.toString(objects));
            return method.invoke(realImplem, objects); // forward the call to the real Impl
         }
      };

      Math math = (Math) Enhancer.create(Math.class, callback);

      // pretty much ~~ to
//      Math math = new Math(){
//         @Override
//         Integer sum(int a, int b) {
//            System.out.println("Calling method " + method.getName() + " with args " + Arrays.toString(objects));
//            return super.sum(a, b);
//         }
//      };

      bizMethod(math);
   }

   private static void bizMethod(Math math) {
      System.out.println("To who am I talking to ? " + math.getClass());
      System.out.println(math.sum(1, 1));
      System.out.println(math.sum(1, 2));
      System.out.println(math.product(2, 2));
   }
}

class Math {
   Integer sum(int a, int b) {
      return a + b;
   }
   Integer product(int a, int b) {
      return a * b;
   }
}