head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiStreamServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3SrvRegiStreamServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/05/26 武波 (中訊) 新規作成
*/
package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SrvStatusDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3SrvRegiStreamServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamServiceImplTest.class);
    public WEB3SrvRegiStreamServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    WEB3SrvRegiStreamServiceImpl l_impl = new WEB3SrvRegiStreamServiceImpl();

    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
//            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_impl.execute(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminSrvRegiCustomerChangeInputRequest l_srvRegiStreamRequest = new WEB3AdminSrvRegiCustomerChangeInputRequest();
            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00758, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "1";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";
//            WEB3SrvRegiStreamServiceInterceptor l_srvRegiStreamServiceInterceptor =
//                new WEB3SrvRegiStreamServiceInterceptor();
//            l_srvRegiStreamServiceInterceptor.onCall(null, null);
            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    public void testExecute_0005()
    {
        final String STR_METHOD_NAME = "testExecute_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01927);
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "1";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01927, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0006()
    {
        final String STR_METHOD_NAME = "testExecute_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.STOP);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "1";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01927, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0007()
    {
        final String STR_METHOD_NAME = "testExecute_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
                        
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "1";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0008()
    {
        final String STR_METHOD_NAME = "testExecute_0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);
            
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "1";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01984, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0009()
    {
        final String STR_METHOD_NAME = "testExecute_0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(false);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
            
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setInstitutionCode(institutionParams.getInstitutionCode());
            productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "1";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            l_impl.execute(l_srvRegiStreamRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01187, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0010()
    {
        final String STR_METHOD_NAME = "testExecute_0010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
            
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setInstitutionCode(institutionParams.getInstitutionCode());
            productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "1";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd") + "12";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl",
                    "saveBondBuyAmount", new Class[]
                   { long.class, double.class,Date.class,Date.class,String.class },
                   null);
            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse =
                (WEB3SrvRegiStreamResponse)l_impl.execute(l_srvRegiStreamRequest);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0011()
    {
        final String STR_METHOD_NAME = "testExecute_0011()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "3";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse =
                (WEB3SrvRegiStreamResponse)l_impl.execute(l_srvRegiStreamRequest);
            assertEquals("1234567890", l_srvRegiStreamResponse.orderNo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testExecute_0012()
    {
        final String STR_METHOD_NAME = "testExecute_0012()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl",
                    "cancelBondBuyAmount", new Class[]
                   { String.class },
                   null);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
            
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setInstitutionCode(institutionParams.getInstitutionCode());
            productParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl",
                    "saveBondBuyAmount", new Class[]
                   { long.class, double.class,Date.class,Date.class,String.class },
                   null);
            
            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "2";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = "1234567890";
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse =
                (WEB3SrvRegiStreamResponse)l_impl.execute(l_srvRegiStreamRequest);
            assertEquals("1234567890", l_srvRegiStreamResponse.orderNo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testExecute_0013()
    {
        final String STR_METHOD_NAME = "testExecute_0013()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "3";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = null;
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse =
                (WEB3SrvRegiStreamResponse)l_impl.execute(l_srvRegiStreamRequest);
            assertNull(l_srvRegiStreamResponse.orderNo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0014()
    {
        final String STR_METHOD_NAME = "testExecute_0014()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductCode("9876543210");
            l_bondProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            TestDBUtility.insertWithDel(l_feqProductParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            BondProductParams bondProductParams = TestDBUtility.getBondProductRow();
            bondProductParams.setProductCode("9876543210");
            bondProductParams.setInstitutionCode(institutionParams.getInstitutionCode());            
            TestDBUtility.insertWithDel(bondProductParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "3";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = null;
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            WEB3SrvRegiStreamResponse l_srvRegiStreamResponse =
                (WEB3SrvRegiStreamResponse)l_impl.execute(l_srvRegiStreamRequest);
            assertNull(l_srvRegiStreamResponse.orderNo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_0015()
    {
        final String STR_METHOD_NAME = "testExecute_0015()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123)); 

            OrderValidationResult l_orderValidationResult = OrderValidationResult.VALIDATION_OK_RESULT;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class},
                l_orderValidationResult);

            WEB3GentradeProspectusResult l_gentradeProspectusResult =
                new WEB3GentradeProspectusResult();
            l_gentradeProspectusResult.checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                "validateProspectus",
                new Class[] {String.class, String.class},
                l_gentradeProspectusResult);

            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            TestDBUtility.deleteAll(BondProductParams.TYPE);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams =
                TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSrvDiv("1234");
            l_srvRegiMasterParams.setSrvStatus(WEB3SrvStatusDef.PROVIDING);
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tTradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tTradingTimeParams.setInstitutionCode("0D");
            l_tTradingTimeParams.setBranchCode("381");
            l_tTradingTimeParams.setTradingTimeType("1");
            l_tTradingTimeParams.setBizDateType("1");
            l_tTradingTimeParams.setMarketCode("11");
            l_tTradingTimeParams.setProductCode("12");
            TestDBUtility.insertWithDel(l_tTradingTimeParams);

            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setOrderAcceptProduct("1");
            //注文受付トランザクション
            l_clendarContext.setOrderAcceptTransaction("1");
            //受付時間区分
            l_clendarContext.setTradingTimeType("1");
            //銘柄コード
            l_clendarContext.setProductCode("12");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", GtlUtils.getSystemTimestamp());

            WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = new WEB3SrvRegiStreamRequest();
            l_srvRegiStreamRequest.serviceDiv = "1234";
            l_srvRegiStreamRequest.tradingType = "3";
            l_srvRegiStreamRequest.productCode = "9876543210";
            l_srvRegiStreamRequest.batTypeCode = "123";
            l_srvRegiStreamRequest.orderNo = null;
            l_srvRegiStreamRequest.deliveryDate = "20080526";
            l_srvRegiStreamRequest.orderBizDate = "20080526";
            l_srvRegiStreamRequest.amount = "123456789012";

            l_impl.execute(l_srvRegiStreamRequest);

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01067, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}

@
