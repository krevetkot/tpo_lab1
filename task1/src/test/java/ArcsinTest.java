import org.example.Arcsin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Arcsin unit test")
public class ArcsinTest {
    @Test
    @DisplayName("Boundaries: -1 < x < 1")
    void outOfDomainReturnsNaN() {
        assertTrue(Double.isNaN(Arcsin.asin(1.000001)));
        assertTrue(Double.isNaN(Arcsin.asin(-1.000001)));
        assertTrue(Double.isNaN(Arcsin.asin(4.99999)));
        assertTrue(Double.isNaN(Arcsin.asin(-4.99999)));
    }

    @Test
    @DisplayName("NaN and Infinity")
    void nanAndInfinityReturnNaN() {
        assertTrue(Double.isNaN(Arcsin.asin(Double.NaN)));
        assertTrue(Double.isNaN(Arcsin.asin(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isNaN(Arcsin.asin(Double.NEGATIVE_INFINITY)));
    }

    @Test
    @DisplayName("Boundary values x=+-1")
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
        Arcsin.setMaxIterations(1);
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
    @DisplayName("Typical values")
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
    @DisplayName("Near 1")
    void nearOneStillReasonableButNeedsLooserTolerance() {
        double x = 0.999999;
        double res = Arcsin.asin(x, 1e-12, 2_000_000);
        double ref = Math.asin(x);

        assertEquals(ref, res, 1e-6);
    }

    @Test
    @DisplayName("asin(x) with current global settings")
    void changeCurrentGlobalSettings() {
        double x = 0.5;

        Arcsin.setEPS(1e-4);
        Arcsin.setMaxIterations(2);

        double expected = Arcsin.asin(x, 1e-4, 2);
        double actual = Arcsin.asin(x);

        assertEquals(expected, actual, 0.0);
        assertEquals(1e-4, Arcsin.getEPS(), 0.0);
        assertEquals(2, Arcsin.getMaxIterations(), 0.0);
    }
}
