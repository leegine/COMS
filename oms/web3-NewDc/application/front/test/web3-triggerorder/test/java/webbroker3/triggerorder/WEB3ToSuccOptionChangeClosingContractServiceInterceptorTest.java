head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionChangeClosingContractServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
 Copyright        : (株)大和総研 証券ソリューションシステム第二部 
 File Name        : WEB3ToSuccOptionChangeClosingContractServiceInterceptorTest.java
 Author Name      : Daiwa Institute of Research  
 Revesion History : 2008/04/22 楊夫志 (中訊) 新規作成  
 */
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputRequest;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionChangeClosingContractServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3ToSuccOptionChangeClosingContractServiceInterceptorTest.class);

    public WEB3ToSuccOptionChangeClosingContractServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(IfoProductRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 正常終了
     * 
     * WEB3SuccOptionsCloseChangeCompleteRequest
     */
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceInterceptor l_interceptor = new WEB3ToSuccOptionChangeClosingContractServiceInterceptor();

        WEB3SuccOptionsCloseChangeCompleteRequest[] l_requests = new WEB3SuccOptionsCloseChangeCompleteRequest[1];

        l_requests[0] = new WEB3SuccOptionsCloseChangeCompleteRequest();
        l_requests[0].id = "1001";

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(2));

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(2);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams); 
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("12");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams1); 
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setTradingTimeType("12");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setMarketCode("0");
            l_tradingTimeParams2.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams2); 
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setTradingTimeType("12");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setMarketCode("0");
            l_tradingTimeParams3.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams3); 

            l_interceptor.onCall(null, l_requests);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }

    /**
     * 正常終了
     * 
     * WEB3SuccOptionsCloseChangeConfirmRequest
     */
    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceInterceptor l_interceptor = new WEB3ToSuccOptionChangeClosingContractServiceInterceptor();

        WEB3SuccOptionsCloseChangeConfirmRequest[] l_requests = new WEB3SuccOptionsCloseChangeConfirmRequest[1];

        l_requests[0] = new WEB3SuccOptionsCloseChangeConfirmRequest();
        l_requests[0].id = "1001";

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(2));

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(2);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams); 
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("12");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams1); 
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setTradingTimeType("12");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setMarketCode("0");
            l_tradingTimeParams2.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams2); 
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setTradingTimeType("12");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setMarketCode("0");
            l_tradingTimeParams3.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams3); 
            l_interceptor.onCall(null, l_requests);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }

    /**
     * SYSTEM_ERROR_80018
     */
    public void testOnCall_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceInterceptor l_interceptor = new WEB3ToSuccOptionChangeClosingContractServiceInterceptor();

        WEB3SuccOptionsOpenChangeInputRequest[] l_requests = new WEB3SuccOptionsOpenChangeInputRequest[1];

        l_requests[0] = new WEB3SuccOptionsOpenChangeInputRequest();
        l_requests[0].id = "1001";

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(2));

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(2);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_interceptor.onCall(null, l_requests);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

    }

    /**
     * SYSTEM_ERROR_80005
     */
    public void testOnCall_C0004()
    {
        final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceInterceptor l_interceptor = new WEB3ToSuccOptionChangeClosingContractServiceInterceptor();

        WEB3SuccOptionsCloseChangeCompleteRequest[] l_requests = new WEB3SuccOptionsCloseChangeCompleteRequest[1];

        l_requests[0] = new WEB3SuccOptionsCloseChangeCompleteRequest();
        l_requests[0].id = "1001";

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(2));

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(2);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            l_interceptor.onCall(null, l_requests);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常終了
     * 
     * onReturn
     * 
     * onThrowable
     * 
     */
    public void testOnReturnOnThrowable_C0001()
    {
        final String STR_METHOD_NAME = "testOnReturnOnThrowable_C0001()";
        log.entering(STR_METHOD_NAME);

        TestSpecialClassUtility.testServiceInterceptor(new WEB3ToSuccOptionChangeClosingContractServiceInterceptor());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
