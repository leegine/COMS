head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.31.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	89c4d7d7dfd671c;
filename	AutoGenerateMockMethodTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revision History : 
*/
package test.util;

import junit.framework.TestCase;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.util.WEB3LogUtility;

public class AutoGenerateMockMethodTest extends TestCase
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(AutoGenerateMockMethodTest.class);
    
    public AutoGenerateMockMethodTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void test_C0001()
    {
        final String STR_METHOD_NAME = "test_C0001()";
//        log.debug(TEST_START + STR_METHOD_NAME);
                
        String l_strExectedContent = "";
        String l_strExpectedContentForParams = "public double calcExpenses(" + "\n"+
            "double l_dblCommissionFee," + "\n"+
            "double l_dblCommissionFeeTax," + "\n"+
            "double l_dblSetupFee," + "\n"+
            "double l_dblSetupFeeTax," + "\n"+
            "double l_dblNameTransferFee," + "\n"+
            "double l_dblNameTransferFeeTax," + "\n"+
            "double l_dblManagementFee," + "\n"+
            "double l_dblManagementFeeTax," + "\n"+
            "double l_dblInterestFee," + "\n"+
            "double l_dblPayInterestFee," + "\n"+
            "double l_dblLoanEquityFee," + "\n"+
            "double l_dblOther," + "\n"+
            "ContractTypeEnum l_contractType" + "\n"+
            ")";
        
        String l_strExpectedContentForMainPartOne = "\n"+"{" + "\n"+
                "TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(" + "\n"+
                "\"webbroker3.equity.WEB3EquityBizLogicProvider\""+","+"\n"+
                "\"calcExpenses\""+","+"\n"+
                "new Class[]{double.class"+","+"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "double.class," +"\n"+
                "ContractTypeEnum.class" +"\n"+
                "}"+"\n"+
                ",new Object[]{new Double(l_dblCommissionFee)," + "\n"+
                "new Double(l_dblCommissionFeeTax)," + "\n"+
                "new Double(l_dblSetupFee)," + "\n"+
                "new Double(l_dblSetupFeeTax)," + "\n"+
                "new Double(l_dblNameTransferFee)," + "\n"+
                "new Double(l_dblNameTransferFeeTax)," + "\n"+
                "new Double(l_dblManagementFee)," + "\n"+
                "new Double(l_dblManagementFeeTax)," + "\n"+
                "new Double(l_dblInterestFee)," + "\n"+
                "new Double(l_dblPayInterestFee)," + "\n"+
                "new Double(l_dblLoanEquityFee)," +"\n"+
                "new Double(l_dblOther)," + "\n"+
                "l_contractType"+
                "\n"+
                 "});"; 
        
        String l_strExpectedContentForMainPartTwo = "\n"+"if(TestBaseForMock.MOCK_MANAGER.isMockUsed("+"\n"+
        "\"webbroker3.equity.WEB3EquityBizLogicProvider\""+","+"\n"+
        "\"calcExpenses\""+","+"\n"+
        "new Class[]{double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "ContractTypeEnum.class"+"\n"+
        "}" + "\n"+
        "))"+"\n"+
        "{"+"\n" +
        "log.debug("+"\"webbroker3.equity.WEB3EquityBizLogicProviderForMock.calcExpenses()\""+");"+"\n" +
         "return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("+"\n" +
         "\"webbroker3.equity.WEB3EquityBizLogicProvider\""+","+"\n" +
         "\"calcExpenses\""+","+"\n"+
        "new Class[]{double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "double.class," +"\n"+
        "ContractTypeEnum.class"+"\n"+
        "}" +"\n"+
        ").asDouble();"+"\n" +
        "}"+"\n"; 
        
        String l_strSuperReturn = "return super.calcExpenses(" +
                "l_dblCommissionFee," +"\n"+
                "l_dblCommissionFeeTax," +"\n"+
                "l_dblSetupFee," +"\n"+
                "l_dblSetupFeeTax," +"\n"+
                "l_dblNameTransferFee," +"\n"+
                "l_dblNameTransferFeeTax," +"\n"+
                "l_dblManagementFee," +"\n"+
                "l_dblManagementFeeTax," +"\n"+
                "l_dblInterestFee," +"\n"+
                "l_dblPayInterestFee," +"\n"+
                "l_dblLoanEquityFee," +"\n"+
                "l_dblOther," +"\n"+
                "l_contractType" +"\n"+
                ");"+"\n"+
                "}";
        
        l_strExectedContent=l_strExectedContent+
                            l_strExpectedContentForParams+
                            l_strExpectedContentForMainPartOne+
                            l_strExpectedContentForMainPartTwo+
                            l_strSuperReturn;
        
        
        try
        {
            String[] l_ParamNames = { 
                    "l_dblCommissionFee", 
                    "l_dblCommissionFeeTax", 
                    "l_dblSetupFee",
                    "l_dblSetupFeeTax", 
                    "l_dblNameTransferFee", 
                    "l_dblNameTransferFeeTax", 
                    "l_dblManagementFee",
                    "l_dblManagementFeeTax", 
                    "l_dblInterestFee", 
                    "l_dblPayInterestFee", 
                    "l_dblLoanEquityFee",
                    "l_dblOther", 
                    "l_contractType" };
            
            String l_strContentResult = new AutoGenerateMockMethod().getMockMethodContent(
                    WEB3EquityBizLogicProvider.class,
                    "calcExpenses",
                    l_ParamNames,
                    false,
                    null);
            
            assertEquals(l_strExectedContent,l_strContentResult);
            
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }
    }
    
    public void test_C0002()
    {
        final String STR_METHOD_NAME = "test_C0002()";
        try
        {
            String[] l_ParamNames = { 
                    "l_dblCommissionFee", 
                    "l_dblCommissionFeeTax", 
                    "l_dblSetupFee",
                    "l_dblSetupFeeTax", 
                    "l_dblNameTransferFee", 
                    "l_dblNameTransferFeeTax", 
                    "l_dblManagementFee",
                    "l_dblManagementFeeTax", 
                    "l_dblInterestFee", 
                    "l_dblPayInterestFee", 
                    "l_dblLoanEquityFee",
                    "l_dblOther"};
            
            String l_strContentResult = new AutoGenerateMockMethod().getMockMethodContent(
                    WEB3EquityBizLogicProvider.class,
                    "calcExpenses",
                    l_ParamNames,
                    false,
                    null);
            fail();
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            assertEquals(RuntimeException.class,l_ex.getClass());
            assertEquals("error! setting overload's method is wrong!",l_ex.getMessage());
        }
    }

}
@
