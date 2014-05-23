package ua.av.utils;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class AddEmployeeFeatureTest {

    @Test
    public void testIsDateValid() throws Exception {

        assertTrue(AddEmployeeFeature.isDateValid("1995-05-05"));

    }
}
