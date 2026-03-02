import org.example.Arcsin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ArcsinTest {
    @ParameterizedTest(name = "arcsin({0})")
    @DisplayName("Check corner values")
    @ValueSource(doubles = {
            -5,
            -1.0000001,
            -1.0,
            -0.99,
            -0.5,
            -0.000001,
            -0.0001,
            -0.0,
            0.0,
            0.0001,
            0.000001,
            0.5,
            0.99,
            1.0,
            1.0000001,
            5,
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.MIN_VALUE,
    })
    void checkCornerDots(double param) {
        assertAll(
                () -> assertEquals(Math.asin(param), Arcsin.asin(param), 0.001)
        );
    }

    @Test
    void maxIterationsZeroReturnsFirstTerm() {
        double x = 0.3;
        double res = Arcsin.asin(x, 1e-15, 0);
        assertEquals(x, res, 0.0);
    }

    @Test
    void oddFunctionProperty() {
        double x = 0.7;
        double a = Arcsin.asin(x, 1e-15, 200_000);
        double b = Arcsin.asin(-x, 1e-15, 200_000);
        assertEquals(-a, b, 1e-12);
    }

    @Test
    void matchesMathAsinForTypicalValues() {
        double eps = 1e-15;
        int iters = 200_000;

        double[] xs = { -0.9, -0.5, -0.1, 0.1, 0.5, 0.9 };
        for (double x : xs) {
            double res = Arcsin.asin(x, eps, iters);
            double ref = Math.asin(x);
            assertEquals(ref, res, 1e-12, "x=" + x);
        }
    }

    @Test
    void smallXApproximatelyEqualsX() {
        double x = 1e-6;
        double res = Arcsin.asin(x, 1e-25, 50);
        assertEquals(x, res, 1e-18);
    }

    @Test
    void nearOneStillReasonableButNeedsLooserTolerance() {
        double x = 0.999999;
        double res = Arcsin.asin(x, 1e-12, 2_000_000);
        double ref = Math.asin(x);

        assertEquals(ref, res, 1e-8);
    }
}
