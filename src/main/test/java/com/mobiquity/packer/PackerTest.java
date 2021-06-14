package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackerTest {

    @Test
    void pack() throws APIException {
        String result = Packer.pack("src/main/test/resources/example_input");
        assertEquals("4\n-\n2, 7\n8, 9", result);
    }
}
