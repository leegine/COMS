head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderExecNotifyNormalTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : (WEB3OptionOrderExecNotifyNormalTransactionCallbackTest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2008/04/29 安陽(中訊) 新規作成
Revision History : 2010/07/16 趙天月(中訊) 大証次期デリバティブシステム対応
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.ifo.data.HostOptionOrderExecNotifyParams;
import webbroker3.ifo.data.HostOptionOrderExecNotifyRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOrderExecNotifyNormalTransactionCallbackTest extends TestBaseForMock
{
    private WEB3OptionOrderExecNotifyNormalTransactionCallback l_callBack = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3OptionOrderExecNotifyNormalTransactionCallbackTest.class);

    public WEB3OptionOrderExecNotifyNormalTransactionCallbackTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_callBack = null;
    }
    
/*    public void testT()
    {
        try
        {
            TestDBUtility.deleteAll(HostOptionOrderExecNotifyParams.TYPE);
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setRequestCode(WEB3HostRequestCodeDef.OPTION_EXEC_NOTICE);
            l_params.setOrderRequestNumber("007");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");
            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);
            TestDBUtility.insertWithDel(l_params);
            
            l_params.setStatus("1");
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            
            int i =l_QueryProcessor.doUpdateQuery(l_params);
            
            System.out.print("===================="+i);
            
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
    }*/
    
    public void testProcess_C0009()
    {
        final String STR_METHOD_NAME = "testProcess_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            //
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber(null);
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");

            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            //
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            

            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            //
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");

            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            //
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");

            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
                        
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            this.l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, (ErrorInfo)l_ex.getDetails());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
    
    public void testProcess_C0010()
    {
        final String STR_METHOD_NAME = "testProcess_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            //
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");
            l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_params.setOrderRequestNumber("007");
            
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            //
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            

            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            //
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("2");

            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams.setName("exec.notify.wait.seconds");
            l_SystemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_SystemPreferencesParams);
            
            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            //
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");

            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
                        
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            this.l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02752, (ErrorInfo)l_ex.getDetails());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyNormalTransactionCallback.process()'
     */
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            //
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");

            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            //
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            

            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            //
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");

            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            //
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");

            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
                        
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            this.l_callBack.process();
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl", 
                    "notifyClose",
                    new Class[] 
                              {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            });
            assertEquals(new Double(99.5D), l_paramsValue.getFirstCalled()[1]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }
    
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            //
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");

            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            //
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            //
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");

            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            
            l_ifoOrderUnitParams.setConfirmedQuantity(88.8);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            //
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");

            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            
            l_hostFotypeCloseOrderNotifyParams.setCloseQuantity(45.6);
            
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
                        
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            this.l_callBack.process();
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl", 
                    "notifyClose",
                    new Class[] 
                              {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            });
            assertEquals(new Double(43.2D), l_paramsValue.getFirstCalled()[1]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }

    // 大証次期デリバティブシステム対応
    public void testProcess_C0003()
    {
        final String STR_METHOD_NAME = "testProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setRequestCode("EI811");
            l_params.setStatus("0");
            l_params.setInstitutionCode("0H");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");

            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            
            l_ifoOrderUnitParams.setConfirmedQuantity(88.8);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");

            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
                        
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            this.l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals("顧客マスタテーブルで顧客オブジェクトを取得できない場合", l_ex.getMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
                
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess_C0004()
    {
        final String STR_METHOD_NAME = "testProcess_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setRequestCode("EI811");
            l_params.setStatus("0");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");
            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            

            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");
            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
                        
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            this.l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals("お客様のオプション口座が開設されておりません。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess_C0005()
    {
        final String STR_METHOD_NAME = "testProcess_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setRequestCode("EI811");
            l_params.setStatus("0");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");
            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            

            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            l_ifoOrderUnitParams.setAcceptNumber("62345678910111213146");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");
            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
                        
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            this.l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
//          assertEquals("テーブルに該当するデータがありません。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess_C0006()
    {
        final String STR_METHOD_NAME = "testProcess_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setRequestCode("EI811");
            l_params.setStatus("0");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");
            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            

            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("2");
            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
            
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            this.l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess_C0007()
    {
        final String STR_METHOD_NAME = "testProcess_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");
            l_params.setOrderRequestNumber("007");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);            

            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");
            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            TestDBUtility.insertWithDel(l_hostFotypeCloseOrderNotifyParams);
            
            TestDBUtility.deleteAll(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                new HostFotypeOrderClmdNotifyParams();
            l_hostFotypeOrderClmdNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CHANGE_CANCEL_NOTICE);
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CANCEL);
            l_hostFotypeOrderClmdNotifyParams.setStatus(WEB3StatusDef.DEALING);
            l_hostFotypeOrderClmdNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeOrderClmdNotifyParams.setProductCode("1006160060");
            l_hostFotypeOrderClmdNotifyParams.setBuySellDiv("1");
            l_hostFotypeOrderClmdNotifyParams.setAccountCode("2512246");
            l_hostFotypeOrderClmdNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeOrderClmdNotifyParams.setModifiedQuantity(5000.0);
            l_hostFotypeOrderClmdNotifyParams.setModifiedLimitPrice(5.0);
            l_hostFotypeOrderClmdNotifyParams.setModifiedResult("01");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("X");
            TestDBUtility.insertWithDel(l_hostFotypeOrderClmdNotifyParams);
            
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);
                       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
                    "notifyCancel",
                    new Class[]
                              {OrderUnit.class,
                               WEB3IfoChangeCancelNotifyUpdateInterceptor.class
                              },
                    WEB3StatusDef.DEALT);
            
            this.l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals("取消通知処理が失敗しました（業務エラー）。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess_C0008()
    {
        final String STR_METHOD_NAME = "testProcess_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAllAndCommit(HostOptionOrderExecNotifyParams.TYPE);
            HostOptionOrderExecNotifyParams l_params = new HostOptionOrderExecNotifyParams();
            l_params.setRequestCode(WEB3HostRequestCodeDef.OPTION_EXEC_NOTICE);
            l_params.setOrderRequestNumber("007");
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setAcceptNumber("12345678910111213141");
            l_params.setProductCode("1006160060");
            l_params.setBuySellDiv("1");
            l_params.setOrderRequestNumber("007");
            l_params.setRequestCode(WEB3HostRequestCodeDef.OPTION_EXEC_NOTICE);
            l_params.setStatus("0");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_params.setDealedType(WEB3DealedTypeDef.FULLY_EXECUTED);
            TestDBUtility.insertWithDelAndCommit(l_params);

            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);

            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_branchParams);            

            TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderRequestNumber("007");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);

            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyParams l_hostFotypeCloseOrderNotifyParams =
                new HostFotypeCloseOrderNotifyParams();
            l_hostFotypeCloseOrderNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CLOSE_NOTICE);
            l_hostFotypeCloseOrderNotifyParams.setInstitutionCode("0D");
            l_hostFotypeCloseOrderNotifyParams.setBranchCode("381");
            l_hostFotypeCloseOrderNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeCloseOrderNotifyParams.setProductCode("1006160060");
            l_hostFotypeCloseOrderNotifyParams.setBuySellDiv("1");
            l_hostFotypeCloseOrderNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeCloseOrderNotifyParams.setCloseNotifyType(WEB3CloseNotifyTypeDef.CLOSE);
            l_hostFotypeCloseOrderNotifyParams.setStatus(WEB3StatusDef.DEALING);
            l_hostFotypeCloseOrderNotifyParams.setExecutedQuantity(99.5);
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeCloseOrderNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                new HostFotypeOrderClmdNotifyParams();
            l_hostFotypeOrderClmdNotifyParams.setRequestCode(WEB3HostRequestCodeDef.OPTION_CHANGE_CANCEL_NOTICE);
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CANCEL);
            l_hostFotypeOrderClmdNotifyParams.setStatus(WEB3StatusDef.DEALING);
            l_hostFotypeOrderClmdNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeOrderClmdNotifyParams.setProductCode("1006160060");
            l_hostFotypeOrderClmdNotifyParams.setBuySellDiv("2");
            l_hostFotypeOrderClmdNotifyParams.setAccountCode("2512246");
            l_hostFotypeOrderClmdNotifyParams.setOrderRequestNumber("007");
            l_hostFotypeOrderClmdNotifyParams.setModifiedQuantity(5000.0);
            l_hostFotypeOrderClmdNotifyParams.setModifiedLimitPrice(5.0);
            l_hostFotypeOrderClmdNotifyParams.setModifiedResult("01");
            l_hostFotypeOrderClmdNotifyParams.setModifiedExecutionType("X");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderClmdNotifyParams);
            
            HostOptionOrderExecNotifyRow l_row = l_params;
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_row,l_params);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl",
                    "notifyExecute", new Class[]
                    {OrderUnit.class, Timestamp.class, double.class, double.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                            {OrderUnit.class,
                            double.class,
                            String.class,
                            String.class,
                            },
                     "1");

            //(1)--------------------------------------------------------------------------------
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            
            List l_lisSearchExec =
                l_QueryProcessor.doFindAllQuery(HostOptionOrderExecNotifyParams.TYPE);
            
            int l_intNumExec = l_lisSearchExec.size();
            
            if(l_intNumExec != 1)
            {
                throw new Exception("***l_intNumExec != 1***");
            }
            
            HostOptionOrderExecNotifyRow l_execRow = (HostOptionOrderExecNotifyRow)l_lisSearchExec.get(0);
            HostOptionOrderExecNotifyParams l_execParams =
                new HostOptionOrderExecNotifyParams(l_execRow);
            
            this.l_callBack = new WEB3OptionOrderExecNotifyNormalTransactionCallback(l_execRow, l_execParams);
            
            l_QueryProcessor.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_callBack);
            
            List l_lisSearchResultExec = l_QueryProcessor.doFindAllQuery(HostOptionOrderExecNotifyParams.TYPE);
            HostOptionOrderExecNotifyRow l_execResultRow = (HostOptionOrderExecNotifyRow)l_lisSearchResultExec.get(0);
            
            assertEquals("1", l_execResultRow.getStatus());
            
            
            //(2)--------------------------------------------------------------------------------            
//            WEB3AsynOptionOrderExecNotifyServiceImpl.WEB3OptionOrderExecNotifyTransactionCallback l_innerExecNotifyTransactionCallback =
//                new WEB3AsynOptionOrderExecNotifyServiceImpl(null).new WEB3OptionOrderExecNotifyTransactionCallback();
//            
//            l_innerExecNotifyTransactionCallback.process();
//            
            
//            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            
//            List l_lisSearchResultExec =
//                l_QueryProcessor.doFindAllQuery(HostOptionOrderExecNotifyParams.TYPE);
//            
//            int l_intNumExec = l_lisSearchResultExec.size();
//            if(l_intNumExec != 1)
//            {
//                throw new Exception("***l_intNumExec != 1***");
//            }
//            
//            HostOptionOrderExecNotifyRow l_execResultRow = (HostOptionOrderExecNotifyRow)l_lisSearchResultExec.get(0);
            
//            assertEquals("1", l_execResultRow.getStatus());
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
                TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
                TestDBUtility.deleteAllAndCommit(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyParams.TYPE);
                TestDBUtility.deleteAllAndCommit(HostFotypeOrderClmdNotifyParams.TYPE);
                TestDBUtility.deleteAllAndCommit(HostOptionOrderExecNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.debug("***delete fail***");
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
}
@
