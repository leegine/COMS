head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqSendQueueReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3AdminFeqSendQueueReferenceServiceImplTest (WEB3AdminFeqSendQueueReferenceServiceImplTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/03 íöè∫öı (íÜêu) 
Revesion History : 2007/02/25 Íéâœ   (íÜêu) õîú‰ùÃçX346 èCâ¸testGetInputScreen_case1
*/

package webbroker3.feq.service.delegate.stdimpls;


import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceResponse;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author ding-zhaokui
 *
 */
public class WEB3AdminFeqSendQueueReferenceServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminFeqSendQueueReferenceServiceImplTest.class);

    public WEB3AdminFeqSendQueueReferenceServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueReferenceServiceImpl.getInputScreen(WEB3AdminFeqSendQueueReferenceInputRequest)'
     */
    public void testGetInputScreen_case1() 
    {
        final String STR_METHOD_NAME = " testGetInputScreen_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestDBUtility.getSystemPreferencesRow();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3AdminFeqSendQueueReferenceServiceImpl l_imp =
            new WEB3AdminFeqSendQueueReferenceServiceImpl();
        WEB3AdminFeqSendQueueReferenceInputRequest l_request =
            new WEB3AdminFeqSendQueueReferenceInputRequest();
        WEB3AdminFeqSendQueueReferenceInputResponse l_response = null;
        Date l_date = WEB3DateUtility.getDate("20070206", "yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_date.getTime()));
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
             
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administratorSet,
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
                false,
                true);
            
            l_response =
                (WEB3AdminFeqSendQueueReferenceInputResponse)l_imp.getInputScreen(l_request);
            
            String l_strorderDate1 = WEB3DateUtility.formatDate(l_response.orderDateList[0], "yyyy/MM/dd:HH:mm:ss");
            String l_strorderDate2 = WEB3DateUtility.formatDate(l_response.orderDateList[1], "yyyy/MM/dd:HH:mm:ss");
            String l_strorderDate3 = WEB3DateUtility.formatDate(l_response.orderDateList[2], "yyyy/MM/dd:HH:mm:ss");
            String l_strorderDate4 = WEB3DateUtility.formatDate(l_response.orderDateList[3], "yyyy/MM/dd:HH:mm:ss");
            String l_strorderDate5 = WEB3DateUtility.formatDate(l_response.orderDateList[4], "yyyy/MM/dd:HH:mm:ss");
            
            assertEquals("2007/02/01:00:00:00", l_strorderDate1);
            assertEquals("2007/02/02:00:00:00", l_strorderDate2);
            assertEquals("2007/02/05:00:00:00", l_strorderDate3);
            assertEquals("2007/02/06:00:00:00", l_strorderDate4);
            assertEquals("2007/02/07:00:00:00", l_strorderDate5);
            assertEquals("SP", l_response.marketList[0]);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(TEST_END + STR_METHOD_NAME);
    }


    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueReferenceServiceImpl.createQueryString(String, String, String, String, boolean)'
     */
    public void testCreateQueryString_case1()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3AdminFeqSendQueueReferenceServiceImpl l_imp =
            new WEB3AdminFeqSendQueueReferenceServiceImpl();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
             
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administratorSet,
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
                false,
                true);
            String l_strStatus = null;
            String l_strOrderEmpCode = null;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            boolean l_blnMailSendProcessDateFlag = false;
            String l_strQueryString = "";
            l_strQueryString = l_imp.createQueryString(
                l_strStatus,
                l_strOrderEmpCode,
                l_strBranchCode,
                l_strAccountCode,
                l_blnMailSendProcessDateFlag,
                null);
            assertEquals(" institution_code = ?  and biz_date = ? ",l_strQueryString);

            l_strQueryString = l_imp.createQueryString(
                l_strStatus,
                l_strOrderEmpCode,
                l_strBranchCode,
                l_strAccountCode,
                l_blnMailSendProcessDateFlag,
                "111");
            assertEquals(" market_code = ?  and " + " institution_code = ?  and biz_date = ? ",l_strQueryString);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueReferenceServiceImpl.createQueryDataContainer(String, String, String, Date, String, String)'
     */
    public void testCreateQueryDataContainer_case1()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3AdminFeqSendQueueReferenceServiceImpl l_imp =
            new WEB3AdminFeqSendQueueReferenceServiceImpl();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
             
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administratorSet,
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
                false,
                true);
            String l_strInstitutionCode = "111";
            String l_strStatus = null;
            String l_strOrderEmpCode = null;
            Date l_datBizDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(l_datBizDate.getTime()));
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            Object[] l_strTestQueryContainers = l_imp.createQueryDataContainer(
                l_strInstitutionCode, 
                l_strStatus, 
                l_strOrderEmpCode, 
                l_datBizDate,
                l_strBranchCode, 
                l_strAccountCode,
                null);
            assertEquals(l_strTestQueryContainers[1], WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"));
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateQueryDataContainer_case2()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3AdminFeqSendQueueReferenceServiceImpl l_imp =
            new WEB3AdminFeqSendQueueReferenceServiceImpl();
        try
        {
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
             
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administratorSet,
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
                false,
                true);
            String l_strInstitutionCode = "222";
            String l_strStatus = null;
            String l_strOrderEmpCode = null;
            Date l_datBizDate = WEB3DateUtility.getDate("20070208", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(l_datBizDate.getTime()));
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            Object[] l_strTestQueryContainers = l_imp.createQueryDataContainer(
                l_strInstitutionCode, 
                l_strStatus, 
                l_strOrderEmpCode, 
                l_datBizDate,
                l_strBranchCode, 
                l_strAccountCode,
                null);
            assertEquals(l_strTestQueryContainers[1], WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"));
            l_strTestQueryContainers = l_imp.createQueryDataContainer(
                l_strInstitutionCode, 
                l_strStatus, 
                l_strOrderEmpCode, 
                l_datBizDate,
                l_strBranchCode, 
                l_strAccountCode,
                "111");
            assertEquals(l_strTestQueryContainers[0], "111");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(TEST_END + STR_METHOD_NAME);
    }

    public void testGetListScreen_case1()
    {
        final String STR_METHOD_NAME = " testGetListScreen_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdminFeqSendQueueReferenceServiceImpl l_imp =
                new WEB3AdminFeqSendQueueReferenceServiceImpl();

            TestDBUtility.deleteAll(SleSendQParams.TYPE);
            SleSendQParams l_sleSendQParams = new SleSendQParams();
            l_sleSendQParams.setBranchCode("381");
            l_sleSendQParams.setAccountCode("123456");
            l_sleSendQParams.setInstitutionCode("0D");
            l_sleSendQParams.setMarketCode("SP");
            l_sleSendQParams.setQueueId(1001L);
            l_sleSendQParams.setProductCode("1");
            l_sleSendQParams.setBrokerName("test");
            l_sleSendQParams.setBizDate("20080202");
            l_sleSendQParams.setOpType(SleSendqOpTypeEnum.CANCEL_ORDER);
            l_sleSendQParams.setOrderType(OrderTypeEnum.ASSET_IN);
            l_sleSendQParams.setAccountId(1001);
            l_sleSendQParams.setSubAccountId(1001);
            l_sleSendQParams.setStatus(SleSendqProcStatusEnum.PROCESSED);
            l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.TRUE);
            l_sleSendQParams.setOrderEmpCode("1");
            l_sleSendQParams.setOrderRequestNumber("1");
            l_sleSendQParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_sleSendQParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_sleSendQParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_sleSendQParams);

            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderUnitId(0);
            l_feqOrderUnitParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProduct = TestDBUtility.getFeqProductRow();
            l_feqProduct.setProductId(1001L);
            TestDBUtility.insertWithDel(l_feqProduct);

            WEB3AdminFeqSendQueueReferenceRequest l_request = new WEB3AdminFeqSendQueueReferenceRequest();
            l_request.branchCode = "381";
            l_request.transactionDiv = "1";
            l_request.managementCode = "1";
            l_request.marketCode = "SP";
            l_request.accountCode = "123456";
            l_request.sendMailDateFlag = false;
            l_request.orderDate =WEB3DateUtility.getDate("20080202", "yyyyMMdd");
            l_request.sortKeys = new WEB3ForeignSortKey[1];
            l_request.sortKeys[0] = new WEB3ForeignSortKey();
            l_request.sortKeys[0].keyItem = WEB3FeqSortKeyItemNameDef.BRANCH_CODE;
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.pageIndex = "3";
            l_request.pageSize = "3";
            WEB3AdminFeqSendQueueReferenceResponse l_response =
                l_imp.getListScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(TEST_END + STR_METHOD_NAME);
    }
}
@
