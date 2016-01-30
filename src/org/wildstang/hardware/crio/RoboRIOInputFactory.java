package org.wildstang.hardware.crio;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.hardware.InputFactory;
import org.wildstang.framework.io.Input;
import org.wildstang.hardware.crio.inputs.WSInputType;
import org.wildstang.hardware.crio.inputs.WsAbsoluteEncoder;
import org.wildstang.hardware.crio.inputs.WsAnalogInput;
import org.wildstang.hardware.crio.inputs.WsDigitalInput;
import org.wildstang.hardware.crio.inputs.WsHallEffectInput;
import org.wildstang.hardware.crio.inputs.WsI2CInput;
import org.wildstang.hardware.crio.inputs.WsJoystickAxis;
import org.wildstang.hardware.crio.inputs.WsJoystickButton;
import org.wildstang.hardware.crio.inputs.WsLIDAR;
import org.wildstang.hardware.crio.inputs.WsMotionProfileControl;
import org.wildstang.hardware.crio.inputs.config.WsAbsoluteEncoderConfig;
import org.wildstang.hardware.crio.inputs.config.WsAnalogInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsDigitalInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsI2CInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsJSButtonInputConfig;
import org.wildstang.hardware.crio.inputs.config.WsJSJoystickInputConfig;


public class RoboRIOInputFactory implements InputFactory
{

   private static Logger s_log = Logger.getLogger(RoboRIOInputFactory.class.getName());
   private static final String s_className = "RoboRIOInputFactory";
   private boolean s_initialised = false;

   public RoboRIOInputFactory()
   {

   }


   public void init()
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "init");

      if (!s_initialised)
      {
         s_initialised = true;
      }

      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "init");
   }


   @Override
   public Input createInput(Inputs p_input)
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "createAnalogInput");
      
      Input in = null;
      
      if (s_log.isLoggable(Level.FINE))
      {
         s_log.fine("Creating analog input: Name = " + p_input.getName() + ", type = " + p_input.getType());
      }
      
      switch ((WSInputType)p_input.getType())
      {
         case POT:
            in = new WsAnalogInput(p_input.getName(), ((WsAnalogInputConfig)p_input.getConfig()).getChannel());
            break;
         case SWITCH:
            in = new WsDigitalInput(p_input.getName(), ((WsDigitalInputConfig)p_input.getConfig()).getChannel(), ((WsDigitalInputConfig)p_input.getConfig()).getPullup());
            break;
         case ABSOLUTE_ENCODER:
            in = new WsAbsoluteEncoder(p_input.getName(), ((WsAbsoluteEncoderConfig)p_input.getConfig()).getChannel(), ((WsAbsoluteEncoderConfig)p_input.getConfig()).getMaxVoltage());
            break;
         case HALL_EFFECT:
            in = new WsHallEffectInput(p_input.getName(), ((WsI2CInputConfig)p_input.getConfig()).getPort(), ((WsI2CInputConfig)p_input.getConfig()).getAddress());
            break;
         case LIDAR:
            // Port is the address, module is the port - such as I2C.kMXP
            in = new WsLIDAR(p_input.getName(), ((WsI2CInputConfig)p_input.getConfig()).getPort(), ((WsI2CInputConfig)p_input.getConfig()).getAddress());
            break;
         case JS_BUTTON:
            in = new WsJoystickButton(p_input.getName(), ((WsJSButtonInputConfig)p_input.getConfig()).getPort(), ((WsJSButtonInputConfig)p_input.getConfig()).getButton());
            break;
         case JS_JOYSTICK:
            in = new WsJoystickAxis(p_input.getName(), ((WsJSJoystickInputConfig)p_input.getConfig()).getPort(), ((WsJSJoystickInputConfig)p_input.getConfig()).getAxis());
            break;
         case I2C:
             in = new WsI2CInput(p_input.getName(), ((WsI2CInputConfig)p_input.getConfig()).getPort(), ((WsI2CInputConfig)p_input.getConfig()).getAddress());
             break;
         case MOTION_PROFILE_CONTROL:
             in = new WsMotionProfileControl(p_input.getName());
            break;
         case NULL:
         default:
//            in = new NullAnalogInput(p_name);
            break;
      }
      
      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "createAnalogInput");

      return in;
   }
   
   
}
