head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockClassTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : MyTestMock.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/01/23 王暁傑 (中訊) 新規作成
 */
package webbroker3;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

/**
 * Mockテストクラス。
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3MockClassTest extends TestBaseForMock
{

    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

    private static String l_strWeb3BaseExceptionErrorMessage = "Not WEB3BaseException : ";
    
    private static String l_strWeb3MockRtExceptionErrorMessage = "Not WEB3MockObjectRuntimeException : ";
    
    private static String exMessage = "Exception : ";
    
    private StringBuffer l_strBrMethodParams = null;
    
    private List l_lisTotoalMock = null;
    
    private Map l_escapeMockClass = null;
    
    private List l_lisErrorForNotMock = null;

    /** Flow Control  */
    private static final boolean xtradelFlag = true;
    private static final boolean web3GenFlag = true;
    private static final boolean web3OtherFlag = true;
    private static final boolean web3EquityFlag = true;
    private static final boolean web3feqFlag = true;
    private static final boolean web3IfoFlag = true;
    private static final boolean web3triggerorderbase = true;
    private static final boolean web3Xbbd = true;
    private static final boolean web3ComplianceAudit = true;
    private static final boolean web3AccountOpenFlag = true;
    private static final boolean web3AccountinfoFlag = true;
    private static final boolean web3XbmfFlag = true;
    private static final boolean web3XbaioFlag = true;
    private static final boolean web3PvInfoFlag = true;
    private static final boolean web3DirSecFlag = true;
    private static final boolean web3SrvregiFlag = true;
    private static final boolean web3informFlag = true;

    
    private Map l_allMockClassMap = null;
    private List l_lisErrorMethods = null;
    private static int count = 0;
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MockClassTest.class);       
    
    public WEB3MockClassTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_allMockClassMap = new HashMap();
        this.l_lisErrorMethods = new ArrayList();
        this.l_lisTotoalMock = new ArrayList();
        this.l_escapeMockClass = new HashMap();
        this.l_lisErrorForNotMock = new ArrayList();
        //this.loadMockClass();
        this.loadEscapeMockClass();
        String l_strAllTest = "./" + "/application/front/test";
        String l_strAllJava = "./" + "/plugin";
        String l_strPathForMock = "./" + "application/front/test/web3-mock-object";
        String l_strError = "C:/workspace1";
        this.countFile(l_strPathForMock);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_allMockClassMap = null;
        this.l_lisErrorMethods = null;
        this.l_lisTotoalMock = null;
    }

    public void testAllMockClass()
    {
        final String STR_METHOD_NAME = "testAllMockClass()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Set l_keySet = l_allMockClassMap.keySet();

            Object[] l_allKey = new Object[l_keySet.size()];
            l_keySet.toArray(l_allKey);
            String[] l_allKeyToString = new String[l_allKey.length];

            for (int i = 0; i < l_allKey.length; i++)
            {
                String l_strTemp = l_allKey[i].getClass().getName();
                int l_trimLength = l_strTemp.lastIndexOf(".");
                l_strTemp = l_strTemp.substring(l_trimLength + 1, l_strTemp.length()) + ".java";
                l_allKeyToString[i] = l_strTemp;

                mockClassTest(l_allKey[i], (String[]) l_allMockClassMap.get(l_allKey[i]));
            }

            Arrays.sort(l_allKeyToString);

            String[] l_arrTotalMock = (String[]) this.l_lisTotoalMock.toArray(new String[0]);
            for (int i = 0; i < l_arrTotalMock.length; i++)
            {
                int l_search = Arrays.binarySearch(l_allKeyToString, l_arrTotalMock[i]);
                if (l_search < 0 && !this.l_escapeMockClass.containsKey(l_arrTotalMock[i]))
                {
                    this.l_lisErrorForNotMock.add(l_arrTotalMock[i]);
                }
            }
            if(!this.l_lisErrorForNotMock.isEmpty())
            {
                StringBuffer l_strBr = new StringBuffer();

                for (int i = 0; i < this.l_lisErrorForNotMock.size(); i++)
                {
                    l_strBr.append(this.l_lisErrorForNotMock.get(i) + "\n");
                }
                assertEquals("Mockが存在しない :","", l_strBr.toString());
            }

            if (!this.l_lisErrorMethods.isEmpty())
            {
                StringBuffer l_strBuf = new StringBuffer();
                for (int i = 0; i < this.l_lisErrorMethods.size(); i++)
                {
                    l_strBuf.append(this.l_lisErrorMethods.get(i) + "\n");
                }
                assertEquals("メソッドのエラー :","", l_strBuf.toString());
            }

            log.debug("count For Mock:" + this.count);
            log.debug("l_allKey.length " + l_allKey.length);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void mockClassTest(Object l_obj,String[] l_escapeMethods)
    {
        final String STR_METHOD_NAME = "mockClassTest()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        Class[] l_parameterTypes = null;
        String l_className = null;
        String l_methodName = null;
        try
        {
            Map l_escapeMethodsMap = new HashMap();
            for (int i = 0; i < l_escapeMethods.length; i++)
            {
                l_escapeMethodsMap.put(l_escapeMethods[i],"");
            }
            Class l_class = l_obj.getClass();
            
            Method[] l_allMethods = l_class.getMethods();
            
            WEB3MockObjectRuntimeException l_exception = new WEB3MockObjectRuntimeException(l_class.getName());
            
            WEB3BaseException l_web3BaseException = new WEB3BaseException(new ErrorInfo(),l_class.getName());


            for (int i = 0; i <  l_allMethods.length; i++)
            {
                Method l_perMethod = l_allMethods[i];
                
                //回避のメソッド
                //|| スパクラスのメソッド
                //|| static Mockメソッドの場合、continue
                if ((l_escapeMethods.length != 0 && l_escapeMethodsMap.containsKey(l_perMethod.getName()))
                    || !l_perMethod.getDeclaringClass().equals(l_obj.getClass())
                    || l_perMethod.getName().startsWith("mock"))
                {
                    continue;
                }
                
                l_parameterTypes = l_perMethod.getParameterTypes();
                l_className = l_class.getName().substring(0,l_class.getName().length() - 7);
                String l_strSuperClassName = l_class.getSuperclass().getName();
                if (!l_strSuperClassName.equals(l_className) && !l_strSuperClassName.endsWith("ForMock"))
                {
                    fail("Class Name error: " + "It's the " + l_strSuperClassName + "ForMock." + "But it's "
                            + l_className+"ForMock");
                }
                l_methodName = l_perMethod.getName();
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    l_className,
                    l_methodName,
                    l_parameterTypes, 
                    l_exception
                    );
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        l_className,
                        l_methodName,
                        l_parameterTypes, 
                        l_web3BaseException
                        );
                try
                {
                    log.debug("l_perMethod = " + l_perMethod);
                    log.debug("initParamsValue(l_perMethod.getParameterTypes()) = " + Arrays.asList(initParamsValue(l_perMethod.getParameterTypes())));

                    l_perMethod.invoke(l_obj, initParamsValue(l_parameterTypes));
//                    fail(l_className+"ForMock"+"."+l_methodName);
                    String l_strErrorMessage = "Not Exception : ";
//                  l_lisErrorMethods.add(l_strErrorMessage+l_className+"ForMock"+"."+l_methodName);
                    if (this.l_strBrMethodParams.length() != 0)
                    {
                        l_lisErrorMethods.add(l_strErrorMessage + l_className + "ForMock"
                                + "." + l_methodName+this.l_strBrMethodParams.toString());
                    }
                    else
                    {
                        l_lisErrorMethods.add(l_strErrorMessage + l_className + "ForMock"
                                + "." + l_methodName+"()");
                    }
                }
                catch (InvocationTargetException l_ex)
                {
                    log.error("", l_ex);
                    
                    boolean l_blnIsWeb3BaseException = false; 
                    Class[] l_clazzArray = l_perMethod.getExceptionTypes();
                    for (int j = 0; j < l_clazzArray.length; j++)
                    {
                        if(l_clazzArray[j].equals(WEB3BaseException.class)){
                            l_blnIsWeb3BaseException = true;
                            break;
                        }

                    }
                    if (l_blnIsWeb3BaseException)
                    {
                        if (!WEB3BaseException.class.equals(l_ex.getTargetException().getClass()))
                        {
                            if (this.l_strBrMethodParams.length() != 0)
                            {
                                l_lisErrorMethods.add(l_strWeb3BaseExceptionErrorMessage + l_className + "ForMock"
                                        + "." + l_methodName+this.l_strBrMethodParams.toString());
                            }
                            else
                            {
                                l_lisErrorMethods.add(l_strWeb3BaseExceptionErrorMessage + l_className + "ForMock"
                                        + "." + l_methodName+"()");
                            }
                        }
//                      assertEquals(l_className+"ForMock"+"."+l_methodName,
//                              WEB3BaseException.class, l_ex.getTargetException().getClass());
                    }
                    else
                    {
                        if (!WEB3MockObjectRuntimeException.class.equals(l_ex.getTargetException().getClass()))
                        {
                            if (this.l_strBrMethodParams.length() != 0)
                            {
                                l_lisErrorMethods.add(l_strWeb3MockRtExceptionErrorMessage + l_className + "ForMock"
                                        + "." + l_methodName + this.l_strBrMethodParams.toString());
                            }
                            else
                            {
                                l_lisErrorMethods.add(l_strWeb3MockRtExceptionErrorMessage + l_className + "ForMock"
                                        + "." + l_methodName + "()");
                            }
                        }
                    }
                }
            }                            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
//          l_lisErrorMethods.add(exMessage+l_className+"ForMock"+"."+l_methodName);
            if (this.l_strBrMethodParams.length() != 0)
            {
                l_lisErrorMethods.add(exMessage + l_className + "ForMock"
                        + "." + l_methodName+this.l_strBrMethodParams.toString());
            }
            else
            {
                l_lisErrorMethods.add(exMessage + l_className + "ForMock"
                        + "." + l_methodName+"()");
            }
//            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private Object[] initParamsValue(Class[] l_class) throws Exception
    {
        final String STR_METHOD_NAME = "initParamsValue()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        this.l_strBrMethodParams = null;
        this.l_strBrMethodParams = new StringBuffer();
        l_strBrMethodParams.append("(");
        
        if (l_class == null || l_class.length == 0)
        {
            return new Object[]{};
        }
        List l_returnValue = new ArrayList();
        for (int i = 0; i <  l_class.length; i++)
        {
            if (Object.class.isAssignableFrom(l_class[i]))
            {
                l_returnValue.add(null);
                String l_strTemp = l_class[i].getName();
                int l_intLength = l_strTemp.lastIndexOf(".");
                l_strTemp = l_strTemp.substring(l_intLength+1,l_strTemp.length());
                l_strBrMethodParams.append(l_strTemp+",");
            }            
            else if (int.class.equals(l_class[i]))
            {
                l_returnValue.add(new Integer(0));
                l_strBrMethodParams.append("int"+",");
            }
            else if (long.class.equals(l_class[i]))
            {
                l_returnValue.add(new Long(0));
                l_strBrMethodParams.append("long"+",");
            }
            else if (double.class.equals(l_class[i]))
            {
                l_returnValue.add(new Double(0));
                l_strBrMethodParams.append("double"+",");
            }
            else if (char.class.equals(l_class[i]))
            {
                l_returnValue.add(new Character('a'));
                l_strBrMethodParams.append("char"+",");
            }
            else if (float.class.equals(l_class[i]))
            {
                l_returnValue.add(new Float(0));
                l_strBrMethodParams.append("float"+",");
            }
            else if (boolean.class.equals(l_class[i]))
            {
                l_returnValue.add(Boolean.FALSE);
                l_strBrMethodParams.append("boolean"+",");
            }
        }
        Object[] l_return = new Object[l_returnValue.size()];
        l_returnValue.toArray(l_return);
        l_strBrMethodParams.deleteCharAt(l_strBrMethodParams.length()-1);
        l_strBrMethodParams.append(")");
        log.debug("l_strBrMethodParams.toString() "+l_strBrMethodParams.toString());
        log.exiting(TEST_END + STR_METHOD_NAME);
        return l_return;
    }
    
//    private void loadMockClass()
//    {
//        final String STR_METHOD_NAME = "loadMockClass()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            l_allMockClassMap.put(new TestAllMockClassForMock(), new String[] { "method3", "method4" });
//
//            if (xtradelFlag)
//            {
//                // **************************com.fitechlabs*************************************            
//                l_allMockClassMap.put(new OpLoginSecurityServiceImplForMock(), new String[] {});
//                l_allMockClassMap.put(l_finApp.getTradingSystem(), new String[] {});
//                l_allMockClassMap.put(new DefaultOrderValidatorImplForMock(), new String[] {});
//                l_allMockClassMap.put(new LoginInfoImplForMock(),new String[] {});
//                l_allMockClassMap.put(new OpLoginAdminServiceImplForMock(),new String[] {});
//                l_allMockClassMap.put(new EqTypeMarketResponseReceiverCallbackServiceImplForMock(),new String[] {});
//                l_allMockClassMap.put(new IfoMarketResponseReceiverCallbackServiceImplForMock(),new String[] {});
//            }
//
//            if (web3GenFlag)
//            {
//
//                // **************************webbroker3.gentrade*************************************
//                l_allMockClassMap.put((WEB3GentradeAccountManager) l_finApp.getAccountManager(), new String[] {});
//
//                // WEB3GentradeBizLogicProviderForMock
//                l_allMockClassMap.put((WEB3GentradeBizLogicProvider) l_finApp.getGlobalBizLogicProvider(), new String[] {});
//                
//                TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
//                l_allMockClassMap.put(new WEB3GentradeBranchForMock(TestDBUtility.getBranchRow().getBranchId()),
//                        new String[] {});
//                TestDBUtility.insertWithDel(TestDBUtility.getBranchMarketDealCondRow());
//                l_allMockClassMap.put(new WEB3GentradeBranchMarketDealtCondForMock(TestDBUtility
//                        .getBranchMarketDealCondRow()), new String[] {});
//                TestDBUtility.insertWithDel(TestDBUtility.getBranchMarketRepayDealtCondRow());
//                l_allMockClassMap.put(new WEB3GentradeBranchMarketRepayDealtCondForMock(TestDBUtility
//                        .getBranchMarketRepayDealtCondRow()), new String[] {});
//                TestDBUtility.insertWithDel(TestDBUtility.getFeqBranchMarketDealtCondRow());
//                l_allMockClassMap.put(new WEB3GentradeFeqBranchMarketDealtCondForMock(TestDBUtility
//                        .getFeqBranchMarketDealtCondRow()), new String[] {});
//
//                l_allMockClassMap.put((WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager(), new String[] {});
//
//                TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
//                l_allMockClassMap.put(new WEB3GentradeInstitutionForMock(TestDBUtility.getInstitutionRow()),
//                        new String[] {});
//                TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
//                l_allMockClassMap.put(new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow()),
//                        new String[] {});
//
//                l_allMockClassMap.put((WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator(), new String[] {});
//
//                TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
//                l_allMockClassMap.put(new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow()),
//                        new String[] {});
//
//                TestDBUtility.insertWithDel(TestDBUtility.getAdministratorRow());
//                l_allMockClassMap.put(new WEB3AdministratorForMock(TestDBUtility.getAdministratorRow()),
//                        new String[] {});
//
//                l_allMockClassMap.put(new WEB3GentradeBatoClientServiceImplForMock(), new String[] {});
//                // WEB3GentradeOrderValidatorForMock
//                l_allMockClassMap.put(new WEB3GentradeOrderValidatorForMock(), new String[] {});
//                // WEB3GentradeTradingClendarContextForMock
//                l_allMockClassMap.put(new WEB3GentradeTradingClendarContextForMock(), new String[] {});
//                l_allMockClassMap.put(new WEB3GentradeTradingCalendarModelImplForMock(), new String[] {});
//                // WEB3GentradeTradingClendarDetailsImplForMock
//                l_allMockClassMap.put(new WEB3GentradeTradingClendarDetailsImplForMock(), new String[] {});
//                // WEB3HostReqOrderNumberManageServiceImplForMock
//                l_allMockClassMap.put(new WEB3HostReqOrderNumberManageServiceImplForMock(), new String[] {});
//            }
//
//            if (web3EquityFlag)
//            {
//                // **************************webbroker3.equity*************************************
//
//                TradingModule l_tradingModule = l_finApp.getTradingModule("eqtype");
//
//                // WEB3EquityFinTransactionManagerForMock
//                l_allMockClassMap.put((WEB3EquityFinTransactionManager) l_tradingModule.getFinTransactionManager(),
//                        new String[] {});
//                // WEB3EquityOrderManagerForMock
//                l_allMockClassMap.put((WEB3EquityOrderManager) l_tradingModule.getOrderManager(), new String[] {});
//
//                l_allMockClassMap.put((WEB3EquityPositionManager)l_tradingModule.getPositionManager(),new String[] {});
//                
//                l_allMockClassMap.put((WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider(),new String[] {});
//                
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setProductType(ProductTypeEnum.EQUITY);
//                TestDBUtility.insertWithDel(l_productParams);
//                
//                TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//                l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
//                TestDBUtility.insertWithDel(l_tradedProductParams);
//                
//                TestDBUtility.insertWithDel(TestDBUtility.getEqtypeProductRow());
//                
//                l_allMockClassMap.put(new WEB3EquityProductForMock(TestDBUtility.getEqtypeProductRow()),
//                        new String[] {});
//
//                l_allMockClassMap.put((WEB3EquityProductManager) l_tradingModule.getProductManager(), new String[] {
//                        "toProduct", "toTradedProduct" });
//
//                l_allMockClassMap.put(l_tradingModule, new String[] {});
//
//                l_allMockClassMap.put(new WEB3EquityMarginExecuteReferenceServiceImplForMock(), new String[] {});
//                l_allMockClassMap.put(l_tradingModule.getBizLogicProvider(), new String[] {});
//
//                l_allMockClassMap.put(new WEB3MarginExecuteReferenceServiceImplForMock(), new String[] {});
//                // WEB3EquityFrontOrderServiceImplForMock
//                l_allMockClassMap.put(new WEB3EquityFrontOrderServiceImplForMock(), new String[] {});
//                // WEB3EquityReceiveCloseOrderUnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3EquityReceiveCloseOrderUnitServiceImplForMock(), new String[] {});
//                // WEB3EquityTypeOrderManagerReusableValidationsForMock
//                l_allMockClassMap.put(new WEB3EquityTypeOrderManagerReusableValidationsForMock(), new String[] {"register"});
//                // WEB3EquityContractForMock
//                l_allMockClassMap.put(new WEB3EquityContractForMock(TestDBUtility.getEqtypeContractRow()),new String[] {});
//            }
//
//            if (web3feqFlag)
//            {
//                // **************************webbroker3.feq*************************************
//                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
//
//                l_allMockClassMap.put((WEB3FeqBizLogicProvider) l_tradingModule.getBizLogicProvider(), new String[] {});
//
//                l_allMockClassMap.put(l_tradingModule.getBizLogicProvider(), new String[] {});
//
//                l_allMockClassMap.put((WEB3FeqOrderManager) l_tradingModule.getOrderManager(), new String[] {});
//
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
//                TestDBUtility.insertWithDel(l_productParams);
//                TestDBUtility.insertWithDel(TestDBUtility.getFeqProductRow());
//                l_allMockClassMap.put(new WEB3FeqProductForMock(TestDBUtility.getFeqProductRow()), new String[] {});
//
//                l_allMockClassMap.put((WEB3FeqProductManager) l_tradingModule.getProductManager(), new String[] {
//                        "toProduct", "toTradedProduct" });
//
//                TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//                l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
//                TestDBUtility.insertWithDel(l_tradedProductParams);
//                TestDBUtility.insertWithDel(TestDBUtility.getFeqTradedProductRow());
//                l_allMockClassMap.put(new WEB3FeqTradedProductForMock(TestDBUtility.getFeqTradedProductRow()),
//                        new String[] {});
//            }
//
//            if (web3IfoFlag)
//            {
//                // **************************webbroker3.xbifo*************************************
//                // 先物OP注文受付UnitServiceImplForMock
//                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
//
//                // WEB3IfoPositionManagerImplForMock
//                l_allMockClassMap.put(l_tradingModule.getPositionManager(), new String[] {});
//                
//                // 先物OP注文受付UnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3IfoOrderAcceptUnitServiceImplForMock(), new String[] {});
//
//                // 先物OP発注サービスImplForMock
//                l_allMockClassMap.put(new WEB3IfoFrontOrderServiceImplForMock(), new String[] {});
//
//                // 拡張取引モジュールラスForMock
//                l_allMockClassMap.put(new WEB3IfoTradingModuleForMock(), new String[] {});
//
//                l_allMockClassMap.put(l_tradingModule.getProductManager(), new String[] { "toProduct",
//                        "toTradedProduct" });
//
//                // 先物OP訂正取消受付UnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock(), new String[] {});
//
//                // OP注文マネージャForMock
//                l_allMockClassMap.put(l_tradingModule.getOrderManager(), new String[] {});
//
//                // 先物OP発注審査個別チェックForMock
//                l_allMockClassMap.put(new WEB3IfoOrderManagerReusableValidationsForMock(), new String[] { "register" });
//
//                // (先物OP失効通知UnitServiceImpl)ForMock
//                l_allMockClassMap.put(new WEB3IfoCloseNotifyUnitServiceImplForMock(), new String[] {});
//
//                l_allMockClassMap.put(l_tradingModule.getBizLogicProvider(), new String[] {});
//
//                //先物訂正取消通知UnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3FuturesChangeCancelNotifyUnitServiceImplForMock(), new String[] {});
//
//                //OP訂正取消通知UnitServiceImpl(Mock)
//                l_allMockClassMap.put(new WEB3OptionChangeCancelNotifyUnitServiceImplForMock(), new String[] {});
//                
//                // WEB3IfoOrderAcceptUnitServiceImplForMork
//                l_allMockClassMap.put(new WEB3IfoOrderAcceptUnitServiceImplForMork(), new String[] {});
//                // WEB3OptionOrderCarryOverUnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3OptionOrderCarryOverUnitServiceImplForMock(), new String[] {});
//                // WEB3FuturesOrderCarryOverUnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3FuturesOrderCarryOverUnitServiceImplForMock(), new String[] {});
//                // WEB3FuturesOrderExecNotifyUnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3FuturesOrderExecNotifyUnitServiceImplForMock(), new String[] {});
//                // WEB3IfoExecuteEndNotifyUnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3IfoExecuteEndNotifyUnitServiceImplForMock(), new String[] {});
//            }
//            
//            if (web3ComplianceAudit)
//            {
//                // **************************webbroker3.compliance.audit*************************************
//                l_allMockClassMap.put(new WEB3ComplianceStatusCountReferenceServiceImplForMock(), new String[] {});
//            }
//            
//            if (web3AccountOpenFlag)
//            {
//                // **************************webbroker3.accountopen*************************************
//                l_allMockClassMap.put(new WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock(), new String[] {});
//            }
//            
//            if (web3AccountinfoFlag)
//            {
//                // **************************webbroker3.xb-accountinfo*************************************
//                l_allMockClassMap.put(new WEB3AdminAccInfoCampaignCommonForMock(), new String[] { "register" });
//                //個別顧客指定変更サービスImplMock
//                l_allMockClassMap.put(new WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock(), new String[] {});
//
//                l_allMockClassMap.put(new WEB3AdminAccInfoCampaignAccOpenChangeServiceImplForMock(), new String[] {});
//
//                l_allMockClassMap.put(new WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock(), new String[] {});
//                
//            }
//
//            if (web3XbmfFlag)
//            {
//                // **************************webbroker3.xb-mf*************************************
//                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
//
//                // 拡張取引モジュールラスForMock
//                l_allMockClassMap.put(new WEB3MutualFundTradingModuleForMock(), new String[] {});
//
//                // OP注文マネージャForMock
//                l_allMockClassMap.put(l_tradingModule.getOrderManager(), new String[] {});
//
//                l_allMockClassMap.put(l_tradingModule.getProductManager(), new String[] { "toProduct",
//                        "toTradedProduct" });
//                
//                // WEB3MutualFundBizLogicProviderForMock
//                l_allMockClassMap.put(l_tradingModule.getBizLogicProvider(), new String[] {});
//                
//                l_allMockClassMap.put(l_tradingModule.getPositionManager(),new String[] {});
//                
//                //投信注文受付UnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3MutualOrderAcceptUnitServiceImplForMock(), new String[] {});
//
//                MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
//                l_mutualFundProductParams.setProductId(123456L);
//                l_mutualFundProductParams.setInstitutionCode("0D");
//                l_mutualFundProductParams.setProductCode("3318");
//                l_mutualFundProductParams.setProductIssueCode("123");
//                l_mutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
//                l_mutualFundProductParams.setInitPurchaseMinQty(150L);
//                l_mutualFundProductParams.setAddtlPurchaseMinQty(50L);
//                l_mutualFundProductParams.setLastUpdater("012");
//                l_mutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20070210", "yyyyMMdd"));
//                l_mutualFundProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//                l_mutualFundProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//                TestDBUtility.insertWithDel(l_mutualFundProductParams);
//
//                ProductParams l_productParams = TestDBUtility.getProductRow();
//                l_productParams.setProductId(123456L);
//                l_productParams.setInstitutionCode("0D");
//                l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//                TestDBUtility.insertWithDel(l_productParams);
//
//                l_allMockClassMap.put(new WEB3MutualFundProductForMock(l_mutualFundProductParams), new String[] {});
//                // WEB3MutualFundOrderManagerReusableValidationsCheckForMock
//                l_allMockClassMap.put(new WEB3MutualFundOrderManagerReusableValidationsCheckForMock(), new String[] {"register"});
//            }
//
//            if (web3XbaioFlag)
//            {
//                // **************************webbroker3.xb-aio*************************************
//                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
//                l_allMockClassMap.put(l_tradingModule.getOrderManager(), new String[] {});
//                l_allMockClassMap.put(new WEB3AdminAioListServiceImplForMock(), new String[] {});
//                // AioMarketResponseReceiverCallbackServiceImplForMock
//                l_allMockClassMap.put(new AioMarketResponseReceiverCallbackServiceImplForMock(), new String[] {});
//                // WEB3AioCashoutTradingPowerUnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3AioCashoutTradingPowerUnitServiceImplForMock(), new String[] {});
//                // WEB3AioMarketRequestSenderServiceImplForMock
//                l_allMockClassMap.put(new WEB3AioMarketRequestSenderServiceImplForMock(), new String[] {});
//                // WEB3AioBizLogicProviderForMock
//                l_allMockClassMap.put(new WEB3AioBizLogicProviderForMock(), new String[] {});
//                // WEB3MarginTransferServiceImplForMock
//                l_allMockClassMap.put(new WEB3MarginTransferServiceImplForMock(), new String[] {});
//                // WEB3FXTelegramProcessServiceImplForMock
//                l_allMockClassMap.put(new WEB3FXTelegramProcessServiceImplForMock(), new String[] {});
//                // WEB3FXDataControlServiceImplForMock.java
//                l_allMockClassMap.put(new WEB3FXDataControlServiceImplForMock(), new String[] {});
//                
//            }
//            if(web3PvInfoFlag)
//            {
//                // **************************webbroker3.pvinfo*************************************
//                l_allMockClassMap.put(new WEB3PvInfoDataManagerImplForMock(), new String[] {});
//            }
//
//            if(web3triggerorderbase)
//            {
//                // **************************webbroker3.triggerorder*************************************
//                l_allMockClassMap.put(new WEB3ToSuccReservationEqTypeOrderUpdateServiceImplForMock(), new String[] {});
//            }
//            if (web3Xbbd)
//            {
//                // **************************webbroker3.xb-bd*************************************
//                TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.BOND);
//                l_allMockClassMap.put(l_tradingModule.getOrderManager(), new String[] {});
//                l_allMockClassMap.put(new WEB3AdminBondHelperServiceImplForMock(), new String[] {});
//                l_allMockClassMap.put(l_tradingModule.getProductManager(), new String[] { "toProduct" });
//                l_allMockClassMap.put(l_tradingModule.getBizLogicProvider(),new String[] {});
//                // WEB3AdminBondDomesticProductListServiceImplForMock
//                l_allMockClassMap.put(new WEB3AdminBondDomesticProductListServiceImplForMock(),new String[] {});
//            }
//            
//            if (web3DirSecFlag)
//            {
//                // **************************webbroker3.dirsec*************************************
//                l_allMockClassMap.put(new WEB3AdminDirSecFrontOrderCommonServiceImplForMock(), new String[] {});
//            }
//            
//            if (web3SrvregiFlag)
//            {
//                // **************************webbroker3.srvregi*************************************
//                l_allMockClassMap.put(new WEB3SrvRegiRegistServiceImplForMock(), new String[] {});
//                // WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock
//                l_allMockClassMap.put(new WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock(), new String[] {});
//            }
//            
//            if(web3informFlag)
//            {
//                // **************************webbroker3.inform*************************************
//                l_allMockClassMap.put(new WEB3InformHostReqOrderNumberManageServiceImplForMock(), new String[] {});
//            }
//
//            if (web3OtherFlag)
//            {
//                // **************************webbroker3.tradingpower*************************************
//                l_allMockClassMap.put(new WEB3TPTradingPowerServiceImplForMock(), new String[] {});
//                l_allMockClassMap.put(new WEB3TPTradingPowerSettlementServiceImplForMock(), new String[] {});
//                // WEB3IfoDepositCalcServiceImplForMock
//                l_allMockClassMap.put(new WEB3IfoDepositCalcServiceImplForMock(), new String[] {});
//                // WEB3ProtoQuoteDataSupplierServiceForMock
//                l_allMockClassMap.put(new WEB3ProtoQuoteDataSupplierServiceForMock(), new String[] {});
//
//                //**************************webbroker3.MQGateway*************************************
//                // Gatewayサービスを登録ForMock
//                l_allMockClassMap.put(new WEB3MQGatewayServiceImplForMock(), new String[] {});
//            }
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
    private void loadEscapeMockClass()
    {
        this.l_escapeMockClass.put("LoginInfoImpl.java","escape");
        this.l_escapeMockClass.put("EqTypeMarketAdapterImplForMock.java","escape");
        this.l_escapeMockClass.put("IfoMarketAdapterImplForMock.java","escape");
        
        this.l_escapeMockClass.put("WEB3EquityTradedProductForMock.java","escape");

        this.l_escapeMockClass.put("WEB3GentradeTradingTimeManagementForMock.java","escape");
        this.l_escapeMockClass.put("WEB3IfoFinTransactionManagerImplForMock.java","escape");
        this.l_escapeMockClass.put("WEB3IfoProductImplForMock.java","escape");
        this.l_escapeMockClass.put("WEB3IfoTradedProductImplForMock.java","escape");
        this.l_escapeMockClass.put("WEB3MutualFundTradedProductForMock.java","escape");
        this.l_escapeMockClass.put("WEB3MutualFundTradingTimeManagementForMock.java","escape");
        this.l_escapeMockClass.put("WEB3DefaultMQSendResultForMock.java","escape");
        this.l_escapeMockClass.put("WEB3OptionOrderManagerImplForMock.java","escape");
        this.l_escapeMockClass.put("WEB3MockObjectAppPlugin.java","escape");
        this.l_escapeMockClass.put("WEB3AccInfoCommissionCourseRegistForMock.java","escape");
        // WEB3AioTradingModuleForMock
        this.l_escapeMockClass.put("WEB3AioTradingModuleForMock.java","escape");
        // AioMarketAdapterImplForMock
        this.l_escapeMockClass.put("AioMarketAdapterImplForMock.java","escape");
        // ServerAccessorImplForMock
        this.l_escapeMockClass.put("ServerAccessorImplForMock.java","escape");
        // ServerAccessorImplForMockException
        this.l_escapeMockClass.put("ServerAccessorImplForMockException.java","escape");
        // WEB3ComplianceAuditOperatorForMock
        this.l_escapeMockClass.put("WEB3ComplianceAuditOperatorForMock.java","escape");
        // WEB3ComplianceAuditDbManagerForMock
        this.l_escapeMockClass.put("WEB3ComplianceAuditDbManagerForMock.java","escape");
        // WEB3ComplianceAuditHelperForMock
        this.l_escapeMockClass.put("WEB3ComplianceAuditHelperForMock.java","escape");
        // WEB3IfoQuoteDataImplForMock
        this.l_escapeMockClass.put("WEB3IfoQuoteDataImplForMock.java","escape");
    }

    private void countFile(String path)
    {   
        System.out.println("l_strPath " + path);
        File l_file = null;
        try
        {
            l_file = new File(path);
        }
        catch (Exception l_ex)
        {
            // File not Fonud! Count All Project for java file
            l_file = new File(".");
        }
        String [] l_fileList = l_file.list();
        if(l_fileList == null)
        {
            return;
        }

        boolean l_isDo= false;

        for (int i = 0; i < l_fileList.length; i++)
        {
            if (l_file.listFiles()[i].isDirectory())
            {
                String l_strNewPath = l_file.listFiles()[i].getAbsolutePath() + "\\";
                countFile(l_strNewPath);
            }
            else if(!l_isDo)
            {
                l_isDo = true;
                File l_contentFile = new File(path);
                String [] l_fileListForJava =l_contentFile.list(new FileFilter("\\w*.java"));
                if (l_fileListForJava != null)
                {
                    if (l_fileListForJava.length != 0)
                    {
                        count +=l_fileListForJava.length;
                    }
                    for (int j = 0; j < l_fileListForJava.length; j++)
                    {
                        this.l_lisTotoalMock.add(l_fileListForJava[j]);
                    }
                }
            }
        }
        
    }
}
@
