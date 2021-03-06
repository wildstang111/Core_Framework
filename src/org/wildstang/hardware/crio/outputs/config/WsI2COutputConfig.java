package org.wildstang.hardware.crio.outputs.config;

import org.wildstang.framework.hardware.OutputConfig;

import edu.wpi.first.wpilibj.I2C;

public class WsI2COutputConfig implements OutputConfig
{
   private I2C.Port m_port = null;
   private int m_address = 0;
   
   public WsI2COutputConfig(I2C.Port p_port, int p_address)
   {
      m_port = p_port;
      m_address = p_address;
   }

   public I2C.Port getPort()
   {
      return m_port;
   }

   public int getAddress()
   {
      return m_address;
   }

   @Override
   public String toString()
   {
      StringBuffer buf = new StringBuffer();
      
      buf.append("{\"port\": \"");
      buf.append(m_port);
      buf.append("\",\"address\": ");
      buf.append(Integer.toHexString(m_address));
      buf.append("}");
      
      return buf.toString();
   }
   
   
}
