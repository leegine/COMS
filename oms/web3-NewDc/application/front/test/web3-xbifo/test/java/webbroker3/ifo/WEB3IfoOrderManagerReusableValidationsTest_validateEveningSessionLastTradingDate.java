head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderManagerReusableValidationsTest_validateEveningSessionLastTradingDate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3IfoOrderManagerReusableValidationsTest_validateEveningSessionLastTradingDate.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/22 ã‡åÜ (íÜêu) êVãKçÏê¨
*/
package webbroker3.ifo;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoOrderManagerReusableValidationsTest_validateEveningSessionLastTradingDate extends TestBaseForMock
{

    private WEB3IfoOrderManagerReusableValidations l_validations = null;
    
    private WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = null;
    
    private String l_strExpirationDateType = null;

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3IfoOrderManagerReusableValidationsTest_validateEveningSessionLastTradingDate.class);
    
    public WEB3IfoOrderManagerReusableValidationsTest_validateEveningSessionLastTradingDate(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_validations = new WEB3IfoOrderManagerReusableValidations();
        this.initData();
        this.l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
    }

    protected void tearDown() throws Exception
    {
        super.checkMethodValue();
        this.l_validations = null;
        this.l_ifoTradedProductImplForMock = null;
        super.tearDown();
    }
    
    /**
     * èââÒíçï∂ÇÃèÍçá äéÇ¬
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==tureÇÃèÍçá äéÇ¬
     * 
     * ùeèoàŸèÌêMëßÅFWEB3ErrorCatalog.BUSINESS_ERROR_00145
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0001()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.setExpectedDate(new Date(),"1");
            // ìñì˙å¿ÇË
            this.l_strExpirationDateType = "1";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
//                    "getLastTradingDate", 
//                    new Class[]{},
//                    date);
            
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,null);
            fail();
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00145,l_web3BusinessLayerException.getErrorInfo());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==tureÇÃèÍçá
     * 
     * 
     * ùeèoàŸèÌêMëßÅFWEB3ErrorCatalog.BUSINESS_ERROR_00145
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0002()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.setExpectedDate(new Date(),"1");
            // ìñì˙å¿ÇË
            this.l_strExpirationDateType = "1";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,new Date());
            fail();
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00145,l_web3BusinessLayerException.getErrorInfo());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==trueÇÃèÍçá
     *
     * 
     * ê≥èÌï‘âÒ
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0003()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.setExpectedDate(new Date(),"1");
            // ìñì˙å¿ÇË
            this.l_strExpirationDateType = "1";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,22);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,7-1,21);
            
            Date date1 = ca1.getTime();
            System.out.println(date1);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,null);
            assertTrue(true);
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * èââÒíçï∂ÇÃèÍçá äéÇ¬
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==falseÇÃèÍçá
     * à¯êî.íçï∂ä˙å¿ãÊï™==Åhó[èÍÇ‹Ç≈íçï∂Åh
     * 
     * ùeèoàŸèÌêMëßÅFWEB3ErrorCatalog.BUSINESS_ERROR_00145
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0004()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // ó[èÍÇ‹Ç≈íçï∂
            this.l_strExpirationDateType = "3";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,null);
            fail();
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00145,l_web3BusinessLayerException.getErrorInfo());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==falseÇÃèÍçá
     * à¯êî.íçï∂ä˙å¿ãÊï™==Åhó[èÍÇ‹Ç≈íçï∂Åh
     * 
     * ùeèoàŸèÌêMëßÅFWEB3ErrorCatalog.BUSINESS_ERROR_00145
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0005()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // ó[èÍÇ‹Ç≈íçï∂
            this.l_strExpirationDateType = "3";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,new Date());
            fail();
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00145,l_web3BusinessLayerException.getErrorInfo());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==falseÇÃèÍçá
     * à¯êî.íçï∂ä˙å¿ãÊï™==Åhó[èÍÇ‹Ç≈íçï∂Åh
     * 
     * ê≥èÌï‘âÒ
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0006()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // ó[èÍÇ‹Ç≈íçï∂
            this.l_strExpirationDateType = "3";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,22);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,7-1,21);
            
            Date date1 = ca1.getTime();
            System.out.println(date1);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,new Date());
            assertTrue(true);
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * èââÒíçï∂ÇÃèÍçá äéÇ¬
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==falseÇÃèÍçá
     * à¯êî.íçï∂ä˙å¿ãÊï™==ÅhèoóàÇÈÇ‹Ç≈íçï∂Åh
     * 
     * ùeèoàŸèÌêMëßÅFWEB3ErrorCatalog.BUSINESS_ERROR_00145
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0007()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // èoóàÇÈÇ‹Ç≈íçï∂
            this.l_strExpirationDateType = "2";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,null);
            fail();
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00145,l_web3BusinessLayerException.getErrorInfo());
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==falseÇÃèÍçá
     * à¯êî.íçï∂ä˙å¿ãÊï™==ÅhèoóàÇÈÇ‹Ç≈íçï∂Åh
     * 
     * ê≥èÌï‘âÒ
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0008()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // èoóàÇÈÇ‹Ç≈íçï∂
            this.l_strExpirationDateType = "2";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,new Date());
            assertTrue(true);
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * èââÒíçï∂ÇÃèÍçá äéÇ¬
     * éÊà¯éûä‘ä«óù.isó[èÍéûä‘ë—()==falseÇÃèÍçá
     * à¯êî.íçï∂ä˙å¿ãÊï™==ÅhèoóàÇÈÇ‹Ç≈íçï∂Åh
     * 
     * ê≥èÌï‘âÒ
     * 
     *
     */
    public void testValidateEveningSessionLastTradingDate_C0009()
    {
        final String STR_METHOD_NAME = "testValidateEveningSessionLastTradingDate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // èoóàÇÈÇ‹Ç≈íçï∂
            this.l_strExpirationDateType = "2";
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,22);
            
            Date date = ca.getTime();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoTradedProductImpl", 
                    "getLastTradingDate", 
                    new Class[]{},
                    date);
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,7-1,21);
            
            Date date1 = ca1.getTime();
            System.out.println(date1);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            this.l_validations.validateEveningSessionLastTradingDate(this.l_ifoTradedProductImplForMock,this.l_strExpirationDateType,null);
            assertTrue(true);
        }
        catch(WEB3BusinessLayerException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();
            l_IfoTradedProductParams.setLastTradingDate(date);
            l_IfoTradedProductParams.setUnlistedDate(date);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");

            String l_strCreateDate = l_format.format(date);
            l_ifoTradedProductUpdqParams.setUnlistedDate(date);            
            l_ifoTradedProductUpdqParams.setLastTradingDate(date);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,21);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void setExpectedDate(Date l_expectDate,String l_sessionType)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

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
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType(l_sessionType);
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);  
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
