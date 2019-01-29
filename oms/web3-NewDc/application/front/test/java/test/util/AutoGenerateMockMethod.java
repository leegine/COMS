head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.32.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	89c4d7d7dfd671c;
filename	AutoGenerateMockMethod.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : AutoGenerateMockMethod.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/05/17  金傑 (中訊) 新規作成
 */
package test.util;

/**
 * @@author jin-jie
 *
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 *
 * The Program can produce Mock'class automatically in the console.
 * 
 */
public class AutoGenerateMockMethod
{
    private StringBuffer l_strSbContent = new StringBuffer();

    private Class l_clazz = null;

    private String l_strMethodName = null;

    private String[] l_strNameParams = null;

    private List l_lisParamName = new ArrayList();

    private Map l_mapForType = new HashMap();

    private String l_strPackName = null;

    private String l_strClassName = null;

    private String l_strReturnType = null;

    private boolean l_isWEB3BaseException = false;
    
    private boolean l_isException = false;

    private Map l_mapForPrimaryType = new HashMap();

    private boolean l_isParam = true;;

    private Class[] l_classEx = null;

    private boolean l_isOverLoad;

    private boolean l_isGenerate = false;
    
    private boolean l_blnIsExisitMethod = false;

    private static final String VOID = "Void";
    
    private static final Class WEB3_BASE_EXCEPTION = WEB3BaseException.class;

    /**
     * storted Primary's type mapping into Object's type 
     *
     */
    public AutoGenerateMockMethod()
    {
        this.l_mapForPrimaryType.put("int", "Int");
        this.l_mapForPrimaryType.put("double", "Double");
        this.l_mapForPrimaryType.put("long", "Long");
        this.l_mapForPrimaryType.put("float", "Float");
        this.l_mapForPrimaryType.put("boolean", "Boolean");
    }

    /**
     * it's logging
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(AutoGenerateMockMethod.class);

    /**
     * Output the content of Mock's method <BR>
     * <BR>
     * @@param l_clazzName           : the name of the produced Mock's class.
     * @@param l_strMethodName       : the name of the method for Mock's class.
     * @@param l_strParams           : the parameters of Mock's method.
     * @@param l_isOverLoad          : it's overload's flag(true: overload false not overload).
     * @@param l_clzForParameterTypes: the type of the parameters of overload's method for Mock's method
     * @@return String
     * @@throws Exception
     */
    public String getMockMethodContent(Class l_clazzName, String l_strMethodName, String[] l_strParams,
        boolean l_isOverLoad, Class[] l_clzForParameterTypes) throws Exception
    {
        final String STR_METHOD_NAME = "getMockMethodContent(Class, String, String[], boolean, Class[])";
        log.entering(STR_METHOD_NAME);

        this.l_clazz = l_clazzName;
        this.l_strMethodName = l_strMethodName;

        if ((l_strParams.length == 0) || ("".equals(l_strParams[0])))
        {
            this.l_isParam = false;
        }
        else
        {
            this.l_strNameParams = l_strParams;
        }
        this.l_isOverLoad = l_isOverLoad;

        Method l_methods[] = this.l_clazz.getDeclaredMethods();
        int l_intMethodsSize = l_methods.length;
        for (int i = 0; i < l_intMethodsSize; i++)
        {
            if ((this.l_strMethodName.equals(l_methods[i].getName())) && (!l_isGenerate))
            {
                // existed method in this class.
                this.l_blnIsExisitMethod = true;
                this.l_classEx = l_methods[i].getExceptionTypes();
                log.debug("getExceptionTypes() " + l_classEx.length);
                
                // judge wether the Exception is be thrown
                this.checkIsException();
                
                // call the type of return's value
                this.getReturnType(l_methods[i]);
                log.debug("this.l_strReturnType " + this.l_strReturnType);

                Class l_paramTypeCls[] = l_methods[i].getParameterTypes();

                // the parameter of mock's method and the paremeter of the real method is not same!
                if (l_paramTypeCls.length == 0 && this.l_isParam)
                {
                    throw new RuntimeException(
                        "error! the parameter of mock's method and" + 
                        "the paremeter of the real method is not same!");
                }
                // no parameter
                else if (l_paramTypeCls.length == 0)
                {
                    this.getMethodParamsAndExceptionWithoutParams();
                    this.l_isGenerate = true;
                }
                // overload and parameters is same
                else if ((this.l_strNameParams != null) && 
                          (this.l_isOverLoad) && 
                          (l_paramTypeCls.length == this.l_strNameParams.length))
                {
                    // identify class's type
                    if (l_clzForParameterTypes != null)
                    {
                        if (l_clzForParameterTypes.length != l_strParams.length)
                        {
                            throw new RuntimeException(
                                "error! the parameter of mock's method and" + 
                                "the paremeter of the real method is not same!");
                        }
                        this.getMethodParamsAndException(l_clzForParameterTypes);
                        this.l_isGenerate = true;
                    }
                    else
                    {
                        this.getMethodParamsAndException(l_paramTypeCls);
                        this.l_isGenerate = true;
                    }
                }
                // not overload
                else if ((this.l_strNameParams != null) && 
                         (!this.l_isOverLoad) && 
                         (l_paramTypeCls.length == this.l_strNameParams.length) &&
                         (l_clzForParameterTypes == null))
                {
                    this.getMethodParamsAndException(l_paramTypeCls);
                    this.l_isGenerate = true;
                }
                else
                {
                    if (this.l_isOverLoad)
                    {
                        continue;
                    }
                    else
                    {
                        throw new RuntimeException("error! setting overload's method is wrong!");
                    }
                }

                break;
            }
        }
        if (this.l_blnIsExisitMethod)
        {
            this.l_strPackName = (this.l_clazz.getPackage()).getName();
            this.l_strClassName = this.format(this.l_clazz.getName());

            this.getMainContentPartOne();
            this.getMainContentPartTwo();
            this.getMainContentPartThree();
            this.getMethodSuperReturnInfo();
            log.exiting(STR_METHOD_NAME);
            return this.l_strSbContent.toString();
        }
        throw new RuntimeException("The method isn't existed！");
    }

    private boolean isEmptyParams()
    {
        return this.l_isParam;
    }
    
    private void getReturnType(Method l_method)
    {
        
        // the type is array
        if(l_method.getReturnType().isArray())
        {
            this.l_strReturnType = this.format((l_method.getReturnType()).getName())+"[]";
        }
        // the type is not array
        else
        {
            this.l_strReturnType = this.format((l_method.getReturnType()).getName());
        }
        
    }
    
    /**
     * judge the exception is wether existed<BR>
     *
     */
    private void checkIsException()
    {
        if ((this.l_classEx.length != 0))
        {
            this.l_isException = true;
            int l_intExceptionLength = this.l_classEx.length;
            for (int k = 0; k < l_intExceptionLength; k++)
            {
                // It's the exception of the WEB3BaseException
                if (WEB3_BASE_EXCEPTION.equals(this.l_classEx[k]))
                {
                    this.l_isWEB3BaseException = true;
                    break;
                }
            }
        }
    }

    private void getMethodParamsAndException(Class l_cls[])
    {
        this.getMethodDefine(l_cls);
        if (this.l_isException)
        {
            this.l_strSbContent.append("throws ");
            getExceptions();
        }
    }
    
    private void getMethodParamsAndExceptionWithoutParams()
    {
        this.l_strSbContent.append("public " + this.l_strReturnType + " " + this.l_strMethodName + "()");
        if(this.l_isException)
        {
            this.l_strSbContent.append("throws ");
            getExceptions();
        }
    }

    private void getMethodDefine(Class l_cls[])
    {
        this.l_strSbContent.append("public " + this.l_strReturnType + " " + l_strMethodName);
        this.l_strSbContent.append("(" + "\n");
        int l_intCsSize = l_cls.length;
        for (int i = 0; i < l_intCsSize; i++)
        {
            if (l_cls[i].isArray())
            {
                this.l_strSbContent.append(format(l_cls[i].getName()) + "[] " + l_strNameParams[i] + "," + "\n");
                log.debug("l_cls[i].getName() " + this.format(l_cls[i].getName()));
                this.l_lisParamName.add(this.format(l_cls[i].getName() + "[]"));
            }
            else
            {
                String l_strType = this.format(l_cls[i].getName());
                this.l_strSbContent.append(l_strType + " " + l_strNameParams[i] + "," + "\n");
                log.debug("l_cls[i].getName() " + l_strType);
                this.l_lisParamName.add(l_strType);
                this.setParamType(l_cls[i], l_strType);
            }
        }
        this.removeLastCommont();
        this.l_strSbContent.append(")");
    }

    private void getExceptions()
    {
        int l_intClassExSize = this.l_classEx.length;
        for (int i = 0; i < l_intClassExSize; i++)
        {
            this.l_strSbContent.append(this.format(this.l_classEx[i].getName()) + ", ");
        }
        this.removeLastCommont();
    }

    private String format(String l_str)
    {
        l_str = l_str.substring(l_str.lastIndexOf(".") + 1);
        return l_str.replace(';', ' ');
    }

    private void getMainContentPartOne()
    {
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("{");
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(");
        this.l_strSbContent.append("\n");

        this.getClassNameAndMethodName();
        if (!this.isEmptyParams())
        {
            this.l_strSbContent.append(",new Object[]{});");
        }
        else
        {
            this.l_strSbContent.append(",new Object[]{");
            int l_intParamNameSize = this.l_lisParamName.size();
            for (int i = 0; i < l_intParamNameSize; i++)
            {
                String l_strTypeKey = (String) this.l_lisParamName.get(i);
                if (this.l_mapForType.containsKey(l_strTypeKey))
                {
                    this.l_strSbContent.append(this.l_mapForType.get(l_strTypeKey) + this.l_strNameParams[i] + "),"
                            + "\n");
                }
                else
                {
                    this.l_strSbContent.append(this.l_strNameParams[i] + "," + "\n");
                }

            }
            this.removeLastCommont();
            this.l_strSbContent.append("});");
        }
    }

    private void getMainContentPartTwo()
    {
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("if(TestBaseForMock.MOCK_MANAGER.isMockUsed(");
        this.l_strSbContent.append("\n");
        this.getClassNameAndMethodName();
        this.l_strSbContent.append("))");
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("{");
        this.getLogInfo(this.l_clazz, this.l_strMethodName);
        this.l_strSbContent.append("\n");
    }

    private void getLogInfo(Class l_clazz, String l_strMethodName)
    {
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("log.debug(" + "\"" + this.l_strPackName + "." + this.l_strClassName + "ForMock."
                + l_strMethodName + "()\"" + ");");
    }

    private void setParamType(Class l_classType, String l_strType)
    {
        if (double.class.equals(l_classType))
        {
            this.l_mapForType.put(l_strType, "new Double(");
        }
        else if (int.class.equals(l_classType))
        {
            this.l_mapForType.put(l_strType, "new Integer(");
        }
        else if (long.class.equals(l_classType))
        {
            this.l_mapForType.put(l_strType, "new Long(");
        }
        else if (char.class.equals(l_classType))
        {
            this.l_mapForType.put(l_strType, "new Character(");
        }
        else if (float.class.equals(l_classType))
        {
            this.l_mapForType.put(l_strType, "new Float(");
        }
        else if (boolean.class.equals(l_classType))
        {
            this.l_mapForType.put(l_strType, "new Boolean(");
        }
    }

    private void getClassNameAndMethodName()
    {
        this.l_strSbContent.append("\"" + this.l_strPackName + "." + this.l_strClassName + "\"" + ",");
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("\"" + this.l_strMethodName + "\"" + ",");
        this.l_strSbContent.append("\n");
        if (!this.isEmptyParams())
        {
            this.l_strSbContent.append("new Class[]{}");
        }
        else
        {
            this.l_strSbContent.append("new Class[]{");
            int l_intParamNameSize = this.l_lisParamName.size();
            for (int i = 0; i < l_intParamNameSize; i++)
            {
                this.l_strSbContent.append(this.l_lisParamName.get(i) + ".class" + "," + "\n");
            }
            this.removeLastCommont();
            this.l_strSbContent.append("}");
            this.l_strSbContent.append("\n");
        }
    }

    private void getReturnTypeInfo()
    {

        if (this.l_mapForPrimaryType.containsValue(this.l_strReturnType))
        {
            this.l_strSbContent.append("return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(");
            this.l_strSbContent.append("\n");
            this.getClassNameAndMethodName();
            this.l_strSbContent.append(").");
            this.l_strSbContent.append("as" + this.l_strReturnType + "();");
        }
        else
        {
            this.l_strSbContent.append("return" + "(" + this.l_strReturnType + ")"
                    + "TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(");
            this.l_strSbContent.append("\n");
            this.getClassNameAndMethodName();
            this.l_strSbContent.append(").");
            this.l_strSbContent.append("as" + "Object()" + ";");
        }

    }

    private void toLowerForFirstCaption()
    {
        String l_strCaption = (this.l_strReturnType.substring(0, 1)).toUpperCase();
        log.debug("l_strCaption " + l_strCaption);
        String l_strRemain = this.l_strReturnType.substring(1);
        log.debug("l_strRemain " + l_strRemain);
        this.l_strReturnType = l_strCaption + l_strRemain;
        log.debug("this.l_strReturnType " + this.l_strReturnType);
    }

    private void getMainContentPartThree()
    {
        if (this.l_isWEB3BaseException)
        {
            this.getExceptionInfo();
        }
        this.getMethodReturnInfo();
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("}");
    }

    private void getExceptionInfo()
    {
        this.l_strSbContent.append("TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(");
        this.l_strSbContent.append("\n");
        this.getClassNameAndMethodName();
        this.l_strSbContent.append(").asWEB3BaseException();");
    }

    private void getMethodReturnInfo()
    {
        this.toLowerForFirstCaption();
        if (VOID.equals(this.l_strReturnType))
        {
            this.getMethodReturnInfoForVoid();
        }
        else
        {
            this.getMethodReturnInfoForNotVoid();
        }
    }

    private void getMethodReturnInfoForVoid()
    {
        this.l_strSbContent.append("TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(");
        this.l_strSbContent.append("\n");
        this.getClassNameAndMethodName();
        this.l_strSbContent.append(").asVoid();");
        this.l_strSbContent.append("\n");
        this.l_strSbContent.append("return;");
    }

    private void getMethodReturnInfoForNotVoid()
    {
        this.getReturnTypeInfo();
    }

    /**
     * produce the information of super's method <BR>
     *
     */
    private void getMethodSuperReturnInfo()
    {
        this.l_strSbContent.append("\n");
        if (VOID.equals(this.l_strReturnType))
        {
            this.l_strSbContent.append("super." + this.l_strMethodName + "(");
        }
        else
        {
            this.l_strSbContent.append("return super." + this.l_strMethodName + "(");
        }
        if (!this.isEmptyParams())
        {
            this.l_strSbContent.append(");");
            this.l_strSbContent.append("\n");
            this.l_strSbContent.append("}");
        }
        else
        {
            int l_intNameParamsSize = this.l_strNameParams.length;
            for (int i = 0; i < l_intNameParamsSize; i++)
            {
                this.l_strSbContent.append(this.l_strNameParams[i] + "," + "\n");
            }
            this.removeLastCommont();
            this.l_strSbContent.append(");");
            this.l_strSbContent.append("\n");
            this.l_strSbContent.append("}");
        }
    }

    /**
     * remove the lastest commont <BR>
     * 
     */
    private void removeLastCommont()
    {
        this.l_strSbContent.deleteCharAt(this.l_strSbContent.length() - 2);
    }

    /**
     * @@param args
     */
    public static void main(String[] args)
    {
        try
        {
            // String[] l_ParamNames = {
            // "l_dblCommissionFee",
            // "l_dblCommissionFeeTax",
            // "l_dblSetupFee",
            // "l_dblSetupFeeTax",
            // "l_dblNameTransferFee",
            // "l_dblNameTransferFeeTax",
            // "l_dblManagementFee",
            // "l_dblManagementFeeTax",
            // "l_dblInterestFee",
            // "l_dblPayInterestFee",
            // "l_dblLoanEquityFee",
            // "l_dblOther",
            // "l_contractType" };
            //            
            // String l_strGenerateMethod = new
            // AutoGenerateMockMethod().getMockMethodContent(
            // WEB3EquityBizLogicProvider.class,
            // "calcExpenses",
            // l_ParamNames);
            //        
            // System.out.println(l_strGenerateMethod);

            // bug1 如@果生成的方法@有參數，而給出的l_ParamNames是空字符串，會出現空指針異常(2007年8月17日已經解決此問題)
            
            // bug2 如@果方法@抛出NotFoundException，就無法@生成(2007年8月17日已經解決此問題)
            
            // bug3 無法@生成如@果該方法@返回的是數組 (2007年8月17日已經解決此問題)
            
            // bug4 如@果是參數個數相同的重載的方法@，不能自動判斷參數的類型，從而無法@生成正確的方法@(2007年10月08日已經解決此問題)
            // 例如@：WEB3EquityProductManager類中的getTradedProduct這個方法@
            String[] l_ParamNames = {"l_request"};//如@果沒有引數時，請這樣使用：String[] l_ParamNames = {""};
                                       // 也可以這樣使用：String[] l_ParamNames = {};
            
            // 2007年12月26日發現 bug5:如@果方法@是protected的，會報NullPointerException
            // 2008年01月08日解決bug5,但是把父類中的protected,生成后@變成了public訪問控制權限
            
            // 2007年12月26日發現 bug6:如@果方法@(不是重載)參數個數與實際不符，生成的代碼不正確。
            // 2008年01月08日解決bug6
            
            // 2008年01月07日發現 bug7:如@果方法@不存在，會抛出NullPointerException
            // 2008年01月08日解決bug7
            // 2008年02月01日發現 bug8:生成WEB3EquityPTSOrderManager類中的expireOrder方法@，
            //                         抛出“該方法@並不存在！”異常信息
            
            // WEB3FuturesOrderManagerImpl
            // WEB3IfoProductManagerImpl
            // WEB3EquityPTSOrderManager
            // WEB3EquityPTSOrderManagerReusableValidations
            String l_strGenerateMethod = new AutoGenerateMockMethod().getMockMethodContent(
                    WEB3ToSuccOptionChangeOpenContractInputServiceImpl.class, 
                    "execute", 
                    l_ParamNames, 
                    false,// 如@果是重載：true，不是重載,該參數設置true或者是false都是一樣的
                    null);// 如@果不用此參數,就設置為null 
                           //只有重載時，才設置此參數，該參數的作用是：如@果是參數個數相同的重載的方法@時，
                          // 指定參數類型，例如@：
            // (SubAccount subaccount, EqTypeNewCashBasedOrderSpec eqtypenewcashbasedorderspec, long l, String s, boolean flag

//          String[] l_ParamNames = {"l_lngProductId","l_lngMarkedId"}; 
//          String l_strGenerateMethod = new AutoGenerateMockMethod().getMockMethodContent(
//                  WEB3EquityProductManager.class, 
//                  "getTradedProduct", 
//                  l_ParamNames, 
//                  true,
//                  new Class[]{long.class,long.class});
            

            
//          String[] l_ParamNames = {"l_institution","l_strProductCode","l_strMarketCode"};  
//            String l_strGenerateMethod = new AutoGenerateMockMethod().getMockMethodContent(
//                    WEB3EquityProductManager.class, 
//                    "getTradedProduct", 
//                    l_ParamNames, 
//                    true,
//                    null);
           
                         
            
            
            System.out.println(l_strGenerateMethod);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
        }
    }

}
@
