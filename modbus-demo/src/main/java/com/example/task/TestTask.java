package com.example.task;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ghgande.j2mod.modbus.Modbus;
import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.net.AbstractSerialConnection;
import com.ghgande.j2mod.modbus.procimg.Register;
import com.ghgande.j2mod.modbus.util.SerialParameters;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@Component
public class TestTask implements ApplicationRunner {

    protected static ModbusSerialMaster master;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            SerialParameters param = new SerialParameters();
            param.setEncoding(Modbus.SERIAL_ENCODING_RTU);
            param.setPortName("COM1");
            param.setBaudRate(9600);
            param.setDatabits(8);
            param.setParity(AbstractSerialConnection.EVEN_PARITY);
            param.setStopbits(AbstractSerialConnection.ONE_STOP_BIT);
            master = new ModbusSerialMaster(param);
            master.connect();
            Register[] registers = master.readMultipleRegisters(1, 0, 2);
            for (Register register : registers) {
                System.out.println("start");
                byte[] bytes = register.toBytes();
                for (int i=0; i< bytes.length; i++) {
                    System.out.print(Byte.toUnsignedInt(bytes[i]));
                }
                System.out.println("end");
            }
        }
        catch (Exception e) {
            log.error(e);
        }
        finally {
            if (master != null) {
                master.disconnect();
            }
        }
        
    }

}
