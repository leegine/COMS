head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFrontOrderCommonServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontMarketNoticeHistoryUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.equity.data.MarketNoticeManagementParams;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFrontOrderCommonServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderCommonServiceImplTest.class);
    
    public WEB3AdminFrontOrderCommonServiceImplTest(String name)
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
    
    WEB3AdminFrontOrderCommonServiceImpl l_adminFrontOrderCommonServiceImpl =
        new WEB3AdminFrontOrderCommonServiceImpl();
    
    public void test_getFindPossibleMarketCode_C0001() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            String l_strInstitutionCode = "OD";
            l_adminFrontOrderCommonServiceImpl.getFindPossibleMarketCode(l_strInstitutionCode);
            fail();
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02046,l_ex.getErrorInfo());
        } 
        
    }
    
    public void test_getFindPossibleMarketCode_C0002() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION);
            l_orderSwitchingParams.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            String l_strInstitutionCode = "0D";
            String[] l_dispMarketLists = 
                l_adminFrontOrderCommonServiceImpl.getFindPossibleMarketCode(l_strInstitutionCode);
            assertEquals("N12",l_dispMarketLists[0]);
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        } 
        
    }
    
    public void test_getFindPossibleMarketCode_C0003() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setMarketCode(WEB3MarketCodeDef.SAPPORO);
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION);
            l_orderSwitchingParams.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            
            OrderSwitchingParams l_orderSwitchingParams1 = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams1.setMarketCode(WEB3MarketCodeDef.JASDAQ);
            l_orderSwitchingParams1.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams1.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION);
            l_orderSwitchingParams1.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams1);
            
            OrderSwitchingParams l_orderSwitchingParams2 = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams2.setMarketCode(WEB3MarketCodeDef.FUKUOKA);
            l_orderSwitchingParams2.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams2.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION);
            l_orderSwitchingParams2.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams2);
            
            String l_strInstitutionCode = "0D";
            String[] l_dispMarketLists = 
                l_adminFrontOrderCommonServiceImpl.getFindPossibleMarketCode(l_strInstitutionCode);
            
            int flag = 0;
            String[] l_strArry = {"82","92","62"};
            for (int i=0; i<l_strArry.length;i++)
            {
                for (int j=0; j<l_dispMarketLists.length;j++)
                {
                    if (l_strArry[i].equals(l_dispMarketLists[j]))
                    {
                        flag = flag + 1;
                    }
                }
            }
            assertEquals(2,flag);
            assertEquals("22",l_dispMarketLists[0]);
            assertEquals("62",l_dispMarketLists[1]);
            assertEquals("82",l_dispMarketLists[2]);
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        } 
        
    }
    
    public void test_getFindPossibleMarketCode_C0004() throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "test_getFindPossibleMarketCode_C0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setMarketCode(WEB3MarketCodeDef.SAPPORO);
            l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION);
            l_orderSwitchingParams.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            
            OrderSwitchingParams l_orderSwitchingParams1 = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams1.setMarketCode(WEB3MarketCodeDef.JASDAQ);
            l_orderSwitchingParams1.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams1.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION);
            l_orderSwitchingParams1.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams1);
            
            OrderSwitchingParams l_orderSwitchingParams2 = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams2.setMarketCode(WEB3MarketCodeDef.SAPPORO);
            l_orderSwitchingParams2.setProductType(ProductTypeEnum.EQUITY);
            l_orderSwitchingParams2.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.TOKYO_STOCK_JASDAQ_AUCTION);
            l_orderSwitchingParams2.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDel(l_orderSwitchingParams2);
            
            String l_strInstitutionCode = "0D";
            String[] l_dispMarketLists = 
                l_adminFrontOrderCommonServiceImpl.getFindPossibleMarketCode(l_strInstitutionCode);
            
            int flag = 0;
            String[] l_strArry = {"82","92"};
            for (int i=0; i<l_strArry.length;i++)
            {
                for (int j=0; j<l_dispMarketLists.length;j++)
                {
                    if (l_strArry[i].equals(l_dispMarketLists[j]))
                    {
                        flag = flag + 1;
                    }
                }
            }
            assertEquals(1,flag);
            assertEquals("22",l_dispMarketLists[0]);
            assertEquals("82",l_dispMarketLists[1]);
        } 
        catch (WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        } 
        
    }
    
    public void test_createNoticeHistryRefList_0001()
    {
        WEB3AdminFrontOrderCommonServiceImpl l_common= new WEB3AdminFrontOrderCommonServiceImpl();
        List l_lst = new ArrayList();
        MarketNoticeManagementParams l_market = new MarketNoticeManagementParams();
        l_market.setAcceptNumber("123456");
        l_market.setVirtualServerNumberMarket("aaa");
        l_market.setDataClassCode("A");
        l_market.setCorpCode("b");
        l_market.setNoticeNumber(5468);
        l_market.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_market.setBranchCode("765");
        l_market.setAccountCode("567");
        l_market.setProductCode("ii");
        l_market.setBuySellDiv("kk");
        l_market.setErrorCode("dd");
        l_market.setDataClassDetailCode("5");
        l_market.setResendFlg("1");
        l_market.setExecutionCondition("2");
        l_market.setPriceConditionType("1");
        l_market.setOrderQuantity(123D);
        l_market.setLimitPrice(0D);
        l_market.setMarginCode("2");
        l_market.setTradeauditCode("2");
        l_market.setShortSellOrderFlag("5");
        l_market.setOrgLimitPrice(741D);
        l_market.setOrgCorpCode("pp");
        l_market.setCutQuantity(25L);
        l_market.setExecPrice(98D);
        l_market.setExecQuantity(88D);
        l_market.setLeftQuantity(70D);
        l_market.setPriceMark("1");
        l_market.setExecMark("P");
        l_market.setExecNumber(8);
        l_market.setModifiedResult("io");
        l_market.setReasonCode("in");
        l_market.setStopMark("2");
        l_market.setOrgExecutionCondition("2");
        l_market.setOrgPriceConditionType("12");
        l_market.setOrgOrderQuantity(33D);
        l_market.setCanceledQuantity(22L);
        l_market.setExecutedQuantity(89L);
        l_market.setExpirationQuantity(56D);
        
        l_lst.add(l_market);
        WEB3AdminFrontMarketNoticeHistoryUnit[] l_retHistryLists = null;
        l_retHistryLists = l_common.createNoticeHistryRefList(l_lst);
        assertEquals("123456", "" + l_retHistryLists[0].acceptNumber);
        assertEquals("aaa", "" + l_retHistryLists[0].virtualServerNumber);
        assertEquals("A", "" + l_retHistryLists[0].dataClassCode);
        assertEquals("b", "" + l_retHistryLists[0].corpCode);
        assertEquals("5468", "" + l_retHistryLists[0].noticeNumber);
        assertEquals("0","" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),
            l_retHistryLists[0].createdTimestamp));
        assertEquals("765", "" + l_retHistryLists[0].branchCode);
        assertEquals("567", "" + l_retHistryLists[0].accountCode);
        assertEquals("ii", "" + l_retHistryLists[0].productCode);
        assertEquals("kk", "" + l_retHistryLists[0].dealingType);
        assertEquals("dd", "" + l_retHistryLists[0].errorCode);
        assertEquals("5", "" + l_retHistryLists[0].dataClassDetailCode);
        assertEquals("1", "" + l_retHistryLists[0].resendFlg);
        assertEquals("2", "" + l_retHistryLists[0].execCondType);
        assertEquals("1", "" + l_retHistryLists[0].priceCondType);
        assertEquals("123.0", "" + l_retHistryLists[0].orderQuantity);
        assertEquals("0.0", "" + l_retHistryLists[0].limitPrice);
        assertEquals("2", "" + l_retHistryLists[0].marginCode);
        assertEquals("2", "" + l_retHistryLists[0].tradeauditCode);
        assertEquals("5", "" + l_retHistryLists[0].shortSellOrderFlag);
        assertEquals("741.0", "" + l_retHistryLists[0].orgLimitPrice);
        assertEquals("pp", "" + l_retHistryLists[0].orgCorpCode);
        assertEquals("25", "" + l_retHistryLists[0].cutQuantity);
        assertEquals("98.0", "" + l_retHistryLists[0].execPrice);
        assertEquals("88.0", "" + l_retHistryLists[0].execQuantity);
        assertEquals("70.0", "" + l_retHistryLists[0].leftQuantity);
        assertEquals("1", "" + l_retHistryLists[0].priceMark);
        assertEquals("P", "" + l_retHistryLists[0].execMark);
        assertEquals("8", "" + l_retHistryLists[0].execNumber);
        assertEquals("io", "" + l_retHistryLists[0].modifiedResult);
        assertEquals("in", "" + l_retHistryLists[0].reasonCode);
        assertEquals("2", "" + l_retHistryLists[0].stopMark);
        assertEquals("2", "" + l_retHistryLists[0].orgExecCondType);
        assertEquals("12", "" + l_retHistryLists[0].orgPriceCondType);
        assertEquals("33.0", "" + l_retHistryLists[0].orgOrderQuantity);
        assertEquals("22", "" + l_retHistryLists[0].canceledQuantity);
        assertEquals("89", "" + l_retHistryLists[0].executedQuantity);
        assertEquals("56.0", "" + l_retHistryLists[0].expirationQuantity);
    }
    
    //getFrontOrderMarketCode
    //     １）　@市場コードがJASDAQ　@or　@NNMの場合、市場コードを"２"（大証）に変換する。
    //１）　@市場コードがJASDAQ 10
    //返回21
    public void testGetFrontOrderMarketCode_Case1()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderMarketCode_Case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFrontOrderCommonService l_service =
                (WEB3AdminFrontOrderCommonService)Services.getService(WEB3AdminFrontOrderCommonService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderMarketCode("10", "1");
            assertEquals("21", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getFrontOrderMarketCode
    //     １）　@市場コードがJASDAQ　@or　@NNMの場合、市場コードを"２"（大証）に変換する。
    //１）　@市場コードがNNM 9
    //返回21
    public void testGetFrontOrderMarketCode_Case2()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderMarketCode_Case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFrontOrderCommonService l_service =
                (WEB3AdminFrontOrderCommonService)Services.getService(WEB3AdminFrontOrderCommonService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderMarketCode("9", "1");
            assertEquals("21", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //getFrontOrderMarketCode
    //     １）　@市場コードがJASDAQ　@or　@NNMの場合、市場コードを"２"（大証）に変換する。
    //１）　@市場コードが東京 1
    //返回11
    public void testGetFrontOrderMarketCode_Case3()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderMarketCode_Case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFrontOrderCommonService l_service =
                (WEB3AdminFrontOrderCommonService)Services.getService(WEB3AdminFrontOrderCommonService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderMarketCode("1", "1");
            assertEquals("11", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
