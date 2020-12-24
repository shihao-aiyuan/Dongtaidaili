package juc;


import lombok.Getter;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author shihao
 * @create 2020-09-03 17:26
 */
public enum EnumTest {

     ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

     @Getter private Integer retCode;
     @Getter private String retMassage;

     EnumTest(Integer retCode,String retMassage){
         this.retCode=retCode;
         this.retMassage=retMassage;
    }

    public static EnumTest forEach_Enum(int code){

        EnumTest[] enumTests = EnumTest.values();
        for (EnumTest enumTest : enumTests) {
            Integer retCode = enumTest.getRetCode();
            if(code==retCode ){
                return enumTest;
            }
        }
        return null;
    }

}
