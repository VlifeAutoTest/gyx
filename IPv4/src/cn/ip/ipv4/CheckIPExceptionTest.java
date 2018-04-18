package cn.ip.ipv4;


import java.io.IOException;
import org.junit.Test;

public class CheckIPExceptionTest {

    @Test(timeout=2000)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeout=2000)
    public void testTimeout2() throws InterruptedException {
        Thread.sleep(2001);
    }
    
    @Test(expected=IOException.class)
    public void testExceptions() throws InterruptedException {

        throw new RuntimeException();
    }
    
    
    @Test(expected=RuntimeException.class)
    public void testExceptions2() throws InterruptedException {
        
        throw new RuntimeException();
    }
    
}
