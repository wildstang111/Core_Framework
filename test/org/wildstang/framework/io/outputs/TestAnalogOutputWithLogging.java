package org.wildstang.framework.io.outputs;

import org.junit.Before;

public class TestAnalogOutputWithLogging extends TestAnalogOutput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
