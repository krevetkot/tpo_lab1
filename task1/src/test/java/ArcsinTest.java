import org.example.Arcsin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Arcsin unit test")
public class ArcsinTest {
    @Test
    @DisplayName("Out of domain: x < -1 or x > 1 -> NaN")
    void outOfDomainReturnsNaN() {
        assertTrue(Double.isNaN(Arcsin.asin(1.000001)));
        assertTrue(Double.isNaN(Arcsin.asin(-1.000001)));
        assertTrue(Double.isNaN(Arcsin.asin(4.99999)));
        assertTrue(Double.isNaN(Arcsin.asin(-4.99999)));
    }

    @Test
    @DisplayName("NaN and Infinity -> NaN")
    void nanAndInfinityReturnNaN() {
        assertTrue(Double.isNaN(Arcsin.asin(Double.NaN)));
        assertTrue(Double.isNaN(Arcsin.asin(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isNaN(Arcsin.asin(Double.NEGATIVE_INFINITY)));
    }

    @Test
    @DisplayName("Boundary values x=+-1 -> +-pi/2")
    void boundaryValues() {
        double eps = 1e-6;
        int iters = 2000000;
        assertEquals(Math.PI / 2, Arcsin.asin(1.0, eps, iters), 1e-2);
        assertEquals(-Math.PI / 2, Arcsin.asin(-1.0, eps, iters), 1e-2);
    }

    @Test
    @DisplayName("maxIteration=0 -> only first item")
    void IterationZeroReturnsFirstItem() {
        double x = 0.3;
        double res = Arcsin.asin(x, 1e-30, 0);
        assertEquals(x, res, 0.0);
    }

    @Test
    @DisplayName("maxIteration=1 -> x + x^3/6")
    void IterationOneReturnsTwoItems() {
        double x = 0.3;
        double expect = x + (x * x * x) / 6.0;
        double res = Arcsin.asin(x, 1e-30, 1);
        assertEquals(expect, res, 0.0);
    }

    @Test
    @DisplayName("maxIteration=2 -> x + x^3/6 + 3x^5 / 40")
    void IterationTwiReturnsThreeItems() {
        double x = 0.3;
        double expect = x + (x * x * x) / 6.0 + (3.0 * (x * x * x * x * x)) / 40.0;
        double res = Arcsin.asin(x, 1e-30, 2);
        assertEquals(expect, res, 0.0);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -0.9, -0.5, -0.1, 0.0, 0.1, 0.5, 0.9 })
    @DisplayName("Matches Math.asin for typical values")
    void matchesMathAsinTypical(double x) {
        double eps = 1e-15;
        int iters = 200000;

        double res = Arcsin.asin(x, eps, iters);
        double ref = Math.asin(x);

        assertEquals(ref, res, 1e-12, "x=" + x);
    }

    @Test
    @DisplayName("Odd function property: asin(-x) = -asin(x)")
    void oddFunctionProperty() {
        double x = 0.7;
        double a = Arcsin.asin(x, 1e-15, 200_000);
        double b = Arcsin.asin(-x, 1e-15, 200_000);
        assertEquals(-a, b, 1e-12);
    }

    @Test
    @DisplayName("Small Approximation returns x")
    void smallXApproximatelyEqualsX() {
        double x = 1e-6;
        double res = Arcsin.asin(x, 1e-25, 50);
        assertEquals(x, res, 1e-18);
    }


    @Test
    @DisplayName("Near 1: still close to Math.asin, but needs looser tolerance and more iterations")
    void nearOneStillReasonableButNeedsLooserTolerance() {
        double x = 0.999999;
        double res = Arcsin.asin(x, 1e-12, 2_000_000);
        double ref = Math.asin(x);

        assertEquals(ref, res, 1e-6);
    }
}
