package cn.ip.ipv4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// create test suite
@RunWith(Suite.class)
@Suite.SuiteClasses({CheckIPTest.class,CheckValidIPTest.class, CheckIPExceptionTest.class})
public class CheckIPAllTest {

}