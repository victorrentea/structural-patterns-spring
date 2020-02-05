package victor.training.oo.structural;

import org.springframework.cache.annotation.Cacheable;

public class SomeImplem implements ISomeImplem {

    @Override
    @Cacheable
    public void m() {

    }
}
