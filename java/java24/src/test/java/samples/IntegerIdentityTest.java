package samples;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegerIdentityTest {
    private final int x = 3;

    @Test
    void check1() {
        assertThat(new Integer(x) != new Integer(x)).isTrue();
    }

    @Test
    void check2() {
        assertThat(Integer.valueOf(x) == Integer.valueOf(x)).isTrue();
    }

    @Test
    void check3() {
        assertThat(new Integer(x) == x).isTrue();
    }

    @Test
    void check4() {
        assertThat(Integer.valueOf(x) == x).isTrue();
    }

    @Test
    void go() {
        long a = 42L;
        a = a << 32;
        System.out.println(a);
    }
}
