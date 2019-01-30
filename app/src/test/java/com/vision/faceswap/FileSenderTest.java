package com.vision.faceswap;

import com.google.common.io.Resources;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;

public class FileSenderTest {

    private FileSender fileSender = new FileSender();

    @Test
    public void test() throws Exception {
        final File dst = new File(Resources.getResource("jack.jpg").getFile());
        final File src = new File(Resources.getResource("test6.jpg").getFile());
        final String result = fileSender.send(src, dst);

        assertFalse(result.isEmpty());
    }

}