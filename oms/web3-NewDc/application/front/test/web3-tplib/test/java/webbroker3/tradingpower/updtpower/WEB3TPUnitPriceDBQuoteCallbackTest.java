head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPUnitPriceDBQuoteCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.updtpower;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ログ出力ユーティリティ。
 */ 
public class WEB3TPUnitPriceDBQuoteCallbackTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPUnitPriceDBQuoteCallbackTest.class);
    
    public WEB3TPUnitPriceDBQuoteCallbackTest(String arg0)
    {
        super(arg0);
    }
    
    /**
     * 構造函數測試
     */
    /*  
    public void test_WEB3TPUnitPriceDBQuoteCallback()
    {
        String STR_METHOD_NAME = "test_WEB3TPUnitPriceDBQuoteCallback()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
        
        try
        {        
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            WEB3TPUnitPriceDBQuoteCallback l_tpCallBack = 
                new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);

            assertEquals(l_calcCondition, l_tpCallBack.calcCondition);    
            log.debug("test success~~~~");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }
    */
    
    /**
     * test_GetUnitPrice01
     */

    public void test_GetUnitPrice01()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice01()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061116"),"yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061116"),"yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);
        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack = 
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            ProductRow l_row = ProductDao.findRowByPk(2601888600000L);
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_row);
            assertEquals("100.0", l_dblUnitPrice + "");           
            log.debug("test_GetUnitPrice01 success~~~~");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }    

    /**
     * test_GetUnitPrice02
     */
    public void test_GetUnitPrice02()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice02()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061116"),"yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061116"),"yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);
        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack = 
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            ProductRow l_row = ProductDao.findRowByPk(2601894900000L);
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_row);
            assertEquals("151000.0", l_dblUnitPrice + "");           
            log.debug("test_GetUnitPrice02 success~~~~");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }   
    
    /**
     * test_GetUnitPrice03
     */
    public void test_GetUnitPrice03()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice03()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061116"),"yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061116"),"yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);
        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack = 
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            ProductRow l_row = ProductDao.findRowByPk(2605100000000L);
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_row);
            assertEquals("1.0", l_dblUnitPrice + "");           
            log.debug("test_GetUnitPrice03 success~~~~");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }   
    
    /**
     * test_GetUnitPrice11
     */
    public void test_GetUnitPrice11()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice11()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        
        //cmp data set
        l_product.setUnitPrice(1.0D);
        double l_dblPrice = 1.1D;
        
        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack = 
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_dblPrice, l_product);
            assertEquals("1.0", l_dblUnitPrice + "");           
            log.debug("test_GetUnitPrice11 success~~~~");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }   
    
    
    /**
     * test_GetUnitPrice31
     */
    public void test_GetUnitPrice31()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice11()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        
        //cmp data set
        l_product.setUnitPrice(1.0D);
        double l_dblPrice = 1.1D;
        
        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack = 
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPriceNotCompare(l_dblPrice, l_product);
            assertEquals("1.1", l_dblUnitPrice + "");           
            log.debug("test_GetUnitPrice11 success~~~~");
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    } 
    
    
    /**
     * test_GetUnitPrice21
     */
    public void test_GetUnitPrice21()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice21()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);

        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();

        l_targetContractDetail.setProductId(2605100000000L);
        l_targetContractDetail.setLastClosingPrice(2.0D);

        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(("20061112"), "yyyyMMdd"));

        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack =
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_targetContractDetail);
            assertEquals("2.0", l_dblUnitPrice + "");
            log.debug("test_GetUnitPrice21 success~~~~");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }

    /**
     * test_GetUnitPrice22
     */
    public void test_GetUnitPrice22()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice22()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);

        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();

        l_targetContractDetail.setProductId(2601894900000L);
        l_targetContractDetail.setLastClosingPrice(2.0D);
        l_targetContractDetail.setMarketId(2601L);

        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(("20061111"), "yyyyMMdd"));

        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack =
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_targetContractDetail);
            assertEquals("2.0", l_dblUnitPrice + "");
            log.debug("test_GetUnitPrice22 success~~~~");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }

    /**
     * test_GetUnitPrice23
     */
    public void test_GetUnitPrice23()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice23()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);

        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();

        l_targetContractDetail.setProductId(2601895000000L);
        l_targetContractDetail.setLastClosingPrice(2.0D);
        l_targetContractDetail.setMarketId(2601L);

        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(("20061111"), "yyyyMMdd"));

        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack =
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_targetContractDetail);
            assertEquals("3", WEB3StringTypeUtility.formatNumber(l_dblUnitPrice));
            log.debug("test_GetUnitPrice23 success~~~~");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }

    /**
     * test_GetUnitPrice24
     */
    public void test_GetUnitPrice24()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice24()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);

        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();

        l_targetContractDetail.setProductId(2601902000000L);
        l_targetContractDetail.setLastClosingPrice(2.0D);
        l_targetContractDetail.setMarketId(2601L);

        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(("20061115"), "yyyyMMdd"));

        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack =
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_targetContractDetail);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_dblUnitPrice));
            log.debug("test_GetUnitPrice24 success~~~~");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }

    /**
     * test_GetUnitPrice25
     */
    public void test_GetUnitPrice25()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice25()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);

        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();

        l_targetContractDetail.setProductId(2601894900000L);
        l_targetContractDetail.setLastClosingPrice(2.0D);
        l_targetContractDetail.setMarketId(2601L);

        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(("20061115"), "yyyyMMdd"));

        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack =
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_targetContractDetail);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_dblUnitPrice));
            log.debug("test_GetUnitPrice25 success~~~~");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }

    /**
     * test_GetUnitPrice26
     */
    public void test_GetUnitPrice26()
    {
        String STR_METHOD_NAME = "test_GetUnitPrice26()";
        log.info(STR_METHOD_NAME + STR_METHOD_NAME);

        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();

        Date[] l_datBizDate = new Date[2];

        l_datBizDate[0] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_datBizDate[1] = WEB3DateUtility.getDate(("20061113"), "yyyyMMdd");
        l_calcCondition.setBizDate(l_datBizDate);

        WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();

        l_targetContractDetail.setProductId(2601895000000L);
        l_targetContractDetail.setLastClosingPrice(2.0D);
        l_targetContractDetail.setMarketId(2601L);

        l_targetContractDetail.setOpenDate(WEB3DateUtility.getDate(("20061115"), "yyyyMMdd"));

        WEB3TPUnitPriceDBQuoteCallback l_tpCallBack =
            new WEB3TPUnitPriceDBQuoteCallback(l_calcCondition);
        try
        {
            double l_dblUnitPrice = l_tpCallBack.getUnitPrice(l_targetContractDetail);
            assertEquals("3", WEB3StringTypeUtility.formatNumber(l_dblUnitPrice));
            log.debug("test_GetUnitPrice26 success~~~~");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + STR_METHOD_NAME);
    }
}
@
