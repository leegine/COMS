head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.02.18.27;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6184d9e706b7e68;
filename	WEB3AdminFXTransferOrderUnitServiceImplTest.java;

1.1
date	2011.04.07.01.37.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXTransferOrderUnitServiceImplTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3AdminFXTransferOrderUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 ë∑ç^ç] (íÜêu) êVãKçÏê¨
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3AdminFXTransferOrderUploadCsv;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.FxTransferMasterRow;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFXTransferOrderUnitServiceImplTest extends TestBaseForMock
{
    private WEB3AdminFXTransferOrderUnitServiceImpl l_service = null;
    private WEB3GenResponse l_response = null;
    private WEB3AdminFXTransferOrderUploadCsv l_fxTransferOrderUploadCsv = null;
    private int l_intLineNumber;
    private String l_strAdministratorCode = null;
    private Institution l_institution = null;
    private String l_strPassword = null;
    
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminFXTransferOrderUnitServiceImplTest.class);

    public WEB3AdminFXTransferOrderUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_service = new WEB3AdminFXTransferOrderUnitServiceImpl();
        this.l_fxTransferOrderUploadCsv = new WEB3AdminFXTransferOrderUploadCsvForTest();
        this.l_strAdministratorCode = "123";
        this.l_institution = new InstitutionForTest();
        this.l_strPassword = "password";
        this.l_intLineNumber = 0;
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        Date l_datpreBizDate = null;
        try
        {
            this.initData();
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[]{},
                l_branch);

            String l_strNewNumber = null;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },
                l_strNewNumber);

            long l_lngProductId = 123L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(l_lngProductId));

            LoginInfo l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            boolean l_blnIsMarginAccountEstablished = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(l_blnIsMarginAccountEstablished));

            Map l_mapAttributes = new Hashtable();
            l_mapAttributes.put("TRADING_PWD_ENV", "0");
            //l_mapAttributes.put("TRADING_PWD_ENV", "1");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                "submitMarginTransfer",
                new Class[]{ WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class},
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001L);
            l_fxTransferMasterParams.setTransferDiv("0");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            this.l_response = this.l_service.execute(
                this.l_fxTransferOrderUploadCsv,
                this.l_intLineNumber,
                this.l_strAdministratorCode,
                this.l_institution,
                this.l_strPassword);
            
            fail();
        }
        catch(WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            log.error(l_web3MockObjectRuntimeException.getMessage(), l_web3MockObjectRuntimeException);
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                    "submitMarginTransfer",
                    new Class[]{ WEB3GentradeMainAccount.class, Date.class, double.class, String.class,Trader.class});
            
            assertEquals(WEB3GentradeMainAccountForMock.class, ((
                    WEB3GentradeMainAccount)l_paramsValue6.getFirstCalled()[0]).getClass());
            
            Date l_resultDate = (Date)l_paramsValue6.getFirstCalled()[1];
            assertEquals(0, WEB3DateUtility.compareToDay(l_datpreBizDate,l_resultDate));
            assertEquals("12345.0", ((Double)l_paramsValue6.getFirstCalled()[2]).toString());
            assertEquals("password", l_paramsValue6.getFirstCalled()[3].toString());
            
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class});
            assertEquals("0D", (l_paramsValue1.getFirstCalled()[0]).toString());
            assertEquals("381", (l_paramsValue1.getFirstCalled()[1]).toString());
            assertEquals("2512246", (l_paramsValue1.getFirstCalled()[2]).toString());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[]{ String.class, String.class, ProductTypeEnum.class });
            assertEquals("0D", (l_paramsValue2.getFirstCalled()[0]).toString());
            assertEquals("381", (l_paramsValue2.getFirstCalled()[1]).toString());
            assertEquals("5:CASH", (l_paramsValue2.getFirstCalled()[2]).toString());
                
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "getProductId",
                    new Class[]{ Institution.class });
            assertEquals(InstitutionForTest.class, l_paramsValue3.getFirstCalled()[0].getClass());  
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginTypeAttributes",
                    new Class[] {long.class});
            assertEquals(0L, ((Long)l_paramsValue4.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isMarginAccountEstablished",
                    new Class[] {String.class});
            assertEquals("0", l_paramsValue5.getFirstCalled()[0]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        Date l_datpreBizDate = null;
        try
        {
            
            this.initData();
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", 
                "getBranch",
                new Class[]{},
                l_branch);
            
            String l_strNewNumber = null;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },
                l_strNewNumber);
            
            long l_lngProductId = 123L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(l_lngProductId));
                    
            LoginInfo l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            boolean l_blnIsMarginAccountEstablished = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(l_blnIsMarginAccountEstablished));
            
            Map l_mapAttributes = new Hashtable();
            //l_mapAttributes.put("TRADING_PWD_ENV", "0");
            l_mapAttributes.put("TRADING_PWD_ENV", "1");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);
            
//            CompFxConditionParams l_compFxConditionParams = null;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.aio.WEB3FXDataControlServiceImpl",
//                "getCompFxCondition",
//                new Class[]{ String.class, String.class },
//                l_compFxConditionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                "submitMarginTransfer",
                new Class[]{ WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class},
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001L);
            l_fxTransferMasterParams.setTransferDiv("0");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            this.l_response = this.l_service.execute(
                this.l_fxTransferOrderUploadCsv,
                this.l_intLineNumber,
                this.l_strAdministratorCode,
                this.l_institution,
                this.l_strPassword);
            
            fail();
        }
        catch(WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            log.error(l_web3MockObjectRuntimeException.getMessage(), l_web3MockObjectRuntimeException);
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                    "submitMarginTransfer",
                    new Class[]{ WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class});
            
            assertEquals(WEB3GentradeMainAccountForMock.class, ((
                    WEB3GentradeMainAccount)l_paramsValue6.getFirstCalled()[0]).getClass());
            
            Date l_resultDate = (Date)l_paramsValue6.getFirstCalled()[1];
            assertEquals(0, WEB3DateUtility.compareToDay(l_datpreBizDate,l_resultDate));
            assertEquals("12345.0", ((Double)l_paramsValue6.getFirstCalled()[2]).toString());
            assertEquals("2512246", l_paramsValue6.getFirstCalled()[3].toString());
            
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class});
            assertEquals("0D", (l_paramsValue1.getFirstCalled()[0]).toString());
            assertEquals("381", (l_paramsValue1.getFirstCalled()[1]).toString());
            assertEquals("2512246", (l_paramsValue1.getFirstCalled()[2]).toString());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber",
                    new Class[]{ String.class, String.class, ProductTypeEnum.class });
            assertEquals("0D", (l_paramsValue2.getFirstCalled()[0]).toString());
            assertEquals("381", (l_paramsValue2.getFirstCalled()[1]).toString());
            assertEquals("5:CASH", (l_paramsValue2.getFirstCalled()[2]).toString());
                
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.aio.WEB3AioOrderManager",
                    "getProductId",
                    new Class[]{ Institution.class });
            assertEquals(InstitutionForTest.class, l_paramsValue3.getFirstCalled()[0].getClass());  
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginTypeAttributes",
                    new Class[] {long.class});
            assertEquals(0L, ((Long)l_paramsValue4.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isMarginAccountEstablished",
                    new Class[] {String.class});
            assertEquals("0", l_paramsValue5.getFirstCalled()[0]);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        Date l_datpreBizDate = null;
        try
        {
            
            this.initData();
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
            
            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", 
                "getBranch",
                new Class[]{},
                l_branch);
            
            String l_strNewNumber = null;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },
                l_strNewNumber);
            
            long l_lngProductId = 123L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(l_lngProductId));
                    
            LoginInfo l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            
            boolean l_blnIsMarginAccountEstablished = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(l_blnIsMarginAccountEstablished));
            
            Map l_mapAttributes = new Hashtable();
            //l_mapAttributes.put("TRADING_PWD_ENV", "0");
            l_mapAttributes.put("TRADING_PWD_ENV", "1");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);
            
//            CompFxConditionParams l_compFxConditionParams = null;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.aio.WEB3FXDataControlServiceImpl",
//                "getCompFxCondition",
//                new Class[]{ String.class, String.class },
//                l_compFxConditionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                "submitMarginTransfer",
                new Class[]{ WEB3GentradeMainAccount.class, Date.class, double.class, String.class, Trader.class},
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            this.l_response = this.l_service.execute(
                this.l_fxTransferOrderUploadCsv,
                this.l_intLineNumber,
                this.l_strAdministratorCode,
                this.l_institution,
                this.l_strPassword);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        Date l_datpreBizDate = null;
        try
        {
            this.initData();
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Branch l_branch = new BranchImpl(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[]{int.class},
                l_branch);

            String l_strNewNumber = "20032";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", new Class[]
                { String.class, String.class, ProductTypeEnum.class },
                l_strNewNumber);

            long l_lngProductId = 123L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[]{ Institution.class },
                new Long(l_lngProductId));

            LoginInfo l_loginInfo = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);

            boolean l_blnIsMarginAccountEstablished = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isMarginAccountEstablished",
                new Class[] {String.class},
                new Boolean(l_blnIsMarginAccountEstablished));

            Map l_mapAttributes = new Hashtable();
            l_mapAttributes.put("TRADING_PWD_ENV", "0");
            //l_mapAttributes.put("TRADING_PWD_ENV", "1");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_mapAttributes);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl",
                    "submitMarginTransfer",
                    new Class[]
                    { WEB3GentradeMainAccount.class, Date.class, double.class, String.class , Trader.class},
                    null);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001L);
            l_fxTransferMasterParams.setTransferDiv("0");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
                new Class[]
                { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class },
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);

            this.l_response = this.l_service.execute(
                this.l_fxTransferOrderUploadCsv,
                this.l_intLineNumber,
                this.l_strAdministratorCode,
                this.l_institution,
                this.l_strPassword);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisCheckResult =
                l_queryProcessor.doFindAllQuery(
                    GftTransferStatusRow.TYPE,
                    " order_request_number = ? ",
                    null,
                    null,
                    new Object[] {"20032"});

            assertNull(l_response);
            assertEquals(1, l_lisCheckResult.size());
            GftTransferStatusRow l_gftTransferStatusRow =
                (GftTransferStatusRow)l_lisCheckResult.get(0);

            assertEquals("01", l_gftTransferStatusRow.getFxUploadNumber());
            assertEquals("5", l_gftTransferStatusRow.getIoListTradeDiv());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class InstitutionForTest implements Institution
    {

        public long getInstitutionId()
        {
        
            return 0;
        }

        public String getInstitutionCode()
        {
            
            return "0D";
        }
    
        public String getInstitutionName()
        {
          
            return null;
        }
    
        public Branch[] getBranches()
        {
            
            return null;
        }
    
        public Object getDataSourceObject()
        {
            
            return null;
        }
    }
    
    private class LoginInfoForTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo()
        {
            
            return null;
        }

        public long getLoginId()
        {
            
            return 0;
        }

        public long getLoginTypeId()
        {
            
            return 0;
        }

        public String getUsername()
        {
            
            return null;
        }

        public String getInitialPassword()
        {
            
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            
            return null;
        }

        public boolean isDisabled()
        {
            
            return false;
        }

        public Set getReachableAccountIds()
        {
            
            return null;
        }

        public Set getReachableLoginIds()
        {
            
            return null;
        }

        public Set getReachableLogins()
        {
            
            return null;
        }

        public Map getAttributes()
        {
            
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            
            return false;
        }

        public boolean hasFailedLogin()
        {
            
            return false;
        }

        public int getFailureCount()
        {
            
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            
            return null;
        }

		public Map getLoginAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Map getLoginTypeAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Long getDefaultAccountId() {
			// TODO Auto-generated method stub
			return null;
		}
        
    }
    private class WEB3AdminFXTransferOrderUploadCsvForTest extends WEB3AdminFXTransferOrderUploadCsv
    {
        public WEB3GentradeMainAccount getMainAccount(int l_intLineNumber) throws WEB3BaseException
        {
            WEB3GentradeMainAccount l_mainAccount = null;
            l_mainAccount = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            return l_mainAccount;
        }
        
        public double getCashOutAmt(int l_intLineNumber) throws WEB3BaseException
        {
            return 12345L;
        }
        
        public Date getPaymentDate(int l_intLineNumber) throws WEB3BaseException
        {
            Date l_datpreBizDate = null;
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            return l_datpreBizDate;
        }

        public String getCashInOutNumber(int l_intLineNumber)
        {
            return "01";
        }
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //TradingTimeRow
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("000000");
            l_tradingTimeParams1.setEndTime("235959");
            l_tradingTimeParams1.setSubmitMarketTrigger("0");
            l_tradingTimeParams1.setEnableOrder("0");
            l_tradingTimeParams1.setBizdateCalcParameter("1");
            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            //å˚ç¿ÇhÇc]
            l_subAccountParams.setAccountId(333812512246L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(33381251220301L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            //èÿåîâÔé–ÉRÅ[Éh
            l_subAccountParams.setInstitutionCode("0D");
            //èÿåîâÔé–ID
            l_subAccountParams.setInstitutionId(33);
            //ïîìXÇhÇc
            l_subAccountParams.setBranchId(33381L);
            //ï‚èïå˚ç¿ÉXÉeÅ[É^ÉX
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
            //å˚ç¿ìoò^ì˙
            l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //å˚ç¿ï¬çΩì˙
            l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //écçÇ(ìñì˙Åj
            l_subAccountParams.setCashBalance(13456.0);
            //çÏê¨ì˙ït
            l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //çXêVì˙ït
            l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //CompFxConditionRow
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("02");
            l_compFxConditionParams.setGroupName("name");
            l_compFxConditionParams.setUrl("url");
            l_compFxConditionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_compFxConditionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_compFxConditionParams.setFxHeadOfLoginId("159");
            l_compFxConditionParams.setSingleSignOnUrl("url");
            l_compFxConditionParams.setValidTerm("12");
            l_compFxConditionParams.setFxSystemDiv("1");
            
            CompFxConditionParams l_compFxConditionParams1 = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams1.setInstitutionCode("0D");
            l_compFxConditionParams1.setBranchCode("381");
            l_compFxConditionParams1.setFxSystemCode("02");
            l_compFxConditionParams1.setGroupName("name");
            l_compFxConditionParams1.setUrl("url");
            l_compFxConditionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_compFxConditionParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_compFxConditionParams1.setFxHeadOfLoginId("159");
            l_compFxConditionParams1.setSingleSignOnUrl("url");
            l_compFxConditionParams1.setValidTerm("12");
            l_compFxConditionParams1.setFxSystemDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams1);
            
            //FxAccountRow
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxAccountId(333812512246L);
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("02");
            l_fxAccountParams.setAccountCode("2512246");
            l_fxAccountParams.setFxAccountDiv("1");
            l_fxAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_fxAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_fxAccountParams);
            
            FxAccountParams l_fxAccountParams1 = TestDBUtility.getFxAccountRow();
            l_fxAccountParams1.setFxAccountId(333812512246L);
            l_fxAccountParams1.setInstitutionCode("0D");
            l_fxAccountParams1.setBranchCode("381");
            l_fxAccountParams1.setFxSystemCode("02");
            l_fxAccountParams1.setAccountCode("2512246");
            l_fxAccountParams1.setFxAccountDiv("1");
            l_fxAccountParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_fxAccountParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_fxAccountParams1);
            
            
            //FxAccountCodeRow
            TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setFxSystemCode("02");
            l_fxAccountCodeParams.setAccountCode("2512246");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_fxAccountCodeParams.setFxAccountCode("25");
            l_fxAccountCodeParams.setLastUpdater("123456");
            l_fxAccountCodeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_fxAccountCodeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            FxAccountCodeParams l_fxAccountCodeParams1 = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams1.setInstitutionCode("0D");
            l_fxAccountCodeParams1.setBranchCode("381");
            l_fxAccountCodeParams1.setFxSystemCode("02");
            l_fxAccountCodeParams1.setAccountCode("2512246");
            l_fxAccountCodeParams1.setFxCourseDiv("1");
            l_fxAccountCodeParams1.setFxAccountCode("25");
            l_fxAccountCodeParams1.setLastUpdater("123456");
            l_fxAccountCodeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_fxAccountCodeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_fxAccountCodeParams1);
            
            //MainAccountRow   
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("ì‡ì°Å@@élòY");
            l_mainAccountParams.setFamilyNameAlt1("≈≤ƒ≥ º€≥");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("ìåãûìs");
            l_mainAccountParams.setAddressLine2("ç]ìåãÊ");
            l_mainAccountParams.setAddressLine3("ê[êÏÇT");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            l_mainAccountParams.setTransferCount(17);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.commit();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d773 15
@

