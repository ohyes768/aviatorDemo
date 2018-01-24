package com.dbapp.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: jackson.tang
 * @version: 1.0
 * @Date: Created in 2018/1/24 10:12
 * @Modified:
 */
public class AviatorMain {
    public static void main(String[] args) {
        Map message = new HashMap();
        message.put("collectorReceiptTime","1111");
        message.put("xxx","222");


        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0
        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0
        AviatorEvaluator.addFunction(new ToValFunction());
        String val= (String)AviatorEvaluator.execute("toVal(collectorReceiptTime)", message );
    }

    static class AddFunction extends AbstractFunction {
        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorDouble(left.doubleValue() + right.doubleValue());
        }
        public String getName() {
            return "add";
        }
    }

    static class ToValFunction extends AbstractFunction {
        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
            String left = FunctionUtils.getStringValue(arg1, env);
//            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorString(left);
        }
        public String getName() {
            return "toVal";
        }
    }

}
