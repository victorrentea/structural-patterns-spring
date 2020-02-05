package victor.training.oo.structural;

import org.springframework.cache.annotation.Cacheable;

public interface ISomeImplem {
    @Cacheable // I woundn't do this
    void m();
}
