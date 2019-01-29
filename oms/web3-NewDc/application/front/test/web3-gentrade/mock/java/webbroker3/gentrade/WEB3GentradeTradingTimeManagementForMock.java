head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.17.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeTradingTimeManagementForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeTradingTimeManagementForMock extends WEB3GentradeTradingTimeManagement
{   
    
    private static WEB3GentradeTradingClendarContext l_clendarContext = 
        new WEB3GentradeTradingClendarContext();
    private static WEB3GentradeTradingClendarContext l_clendarContextUserSet = null;
    private static Timestamp l_tsBizDate = null;
    private static Timestamp l_tsBizDateUserSet = null;
    
    static 
    {
        setSystemAttributes();
    }

    public static void setSystemAttributes()
    {           
        if (l_clendarContextUserSet != null)
        {
            l_clendarContext.setInstitutionCode(l_clendarContextUserSet.getInstitutionCode());
            l_clendarContext.setBranchCode(l_clendarContextUserSet.getBranchCode());
            l_clendarContext.setProductCode(l_clendarContextUserSet.getProductCode());
            l_clendarContext.setMarketCode(l_clendarContextUserSet.getMarketCode());
            l_clendarContext.setTradingTimeType(l_clendarContextUserSet.getTradingTimeType());
            l_clendarContext.setBizDateType(l_clendarContextUserSet.getBizDateType());
            l_clendarContext.setBizdateCalcParameter(l_clendarContextUserSet.getBizdateCalcParameter());
            l_clendarContext.setEnableOrder(l_clendarContextUserSet.getEnableOrder());
            l_clendarContext.setOrderAcceptProduct(l_clendarContextUserSet.getOrderAcceptProduct());
            l_clendarContext.setOrderAcceptTransaction(l_clendarContextUserSet.getOrderAcceptTransaction());
            l_clendarContext.setSubmitMarketTrigger(l_clendarContextUserSet.getSubmitMarketTrigger());
            l_clendarContext.setTradingStopProduct(l_clendarContextUserSet.getTradingStopProduct());
            l_clendarContext.setTradingStopTransaction(l_clendarContextUserSet.getTradingStopTransaction());
            l_clendarContextUserSet = null;
        }
        else
        {
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setBizDateType("1");
        }
        if (ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH) == null)
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, 
                l_clendarContext);            
        }
        
        if (l_tsBizDateUserSet != null)
        {
            l_tsBizDate = l_tsBizDateUserSet;
            l_tsBizDateUserSet = null;
        }
        else
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);
            
            l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());            
        }
        if (ThreadLocalSystemAttributesRegistry.getAttribute(BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE) == null)
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, l_tsBizDate);            
        }

        if (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG) == null)
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG,l_tsBizDate);            
        }
    }
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3GentradeTradingTimeManagementForMock.class);
   
    public WEB3GentradeTradingTimeManagementForMock()
    {
        super();
    }
    
    /**
     * (営業日区分取得)のMockメソッド<BR>
     * <BR>
     * 期待値の範囲:<BR>
     * 0：非営業日 1：終日営業日 2：半日（午前のみ） 3：半日（午後のみ）<BR>
     * (WEB3BizDateTypeDef.javaにて定義)<BR>
     * <BR>
     * @@param l_tsOrderAcceptDate - 受付日時
     * @@param l_strExpectValue - 期待値
     */
    public static void mockGetBizDateType(Timestamp l_tsOrderAcceptDate,String l_strExpectValue)
        throws WEB3BaseException
    {
        CalendarParams l_calendarParams = new CalendarParams();
        
        l_calendarParams.setHoliday(WEB3DateUtility.toDay(l_tsOrderAcceptDate));
        l_calendarParams.setBizDateType(l_strExpectValue);
        l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        TestDBUtility.insertWithDel(l_calendarParams);                
    }

    /**
     * (海外の営業日区分取得)のMockメソッド<BR>
     * <BR>
     * 期待値の範囲:<BR>
     * 0：非営業日 1：終日営業日 2：半日（午前のみ） 3：半日（午後のみ）<BR>
     * (WEB3BizDateTypeDef.javaにて定義)<BR>
     * <BR>
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_strMarketCode 市場コード
     * @@param l_tsOrderAcceptDate 受付日時
     * @@param l_strExpectValue - 期待値
     */
    public static void mockGetFeqBizDateType(
        String l_strInstitutionCode,
        String l_strMarketCode,
        Timestamp l_tsOrderAcceptDate,
        String l_strExpectValue) throws WEB3BaseException
    {
       Timestamp l_strOrderAcceptYMD = 
            new Timestamp(WEB3DateUtility.toDay(l_tsOrderAcceptDate).getTime());
        FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
        l_feqCalendarParams.setMarketCode(l_strMarketCode);
        l_feqCalendarParams.setInstitutionCode(l_strInstitutionCode);
        l_feqCalendarParams.setBizDate(l_strOrderAcceptYMD);
        l_feqCalendarParams.setBizDateType(l_strExpectValue);
        l_feqCalendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_feqCalendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        TestDBUtility.insertWithDel(l_feqCalendarParams);
    }
    
    /**
     * (海外の営業日区分取得)のMockメソッド<BR>
     * <BR>
     * 期待値の範囲:<BR>
     * 0：非営業日 1：終日営業日 2：半日（午前のみ） 3：半日（午後のみ）<BR>
     * (WEB3BizDateTypeDef.javaにて定義)<BR>
     * <BR>
     * @@param l_tsOrderAcceptDate 受付日時
     * @@param l_strExpectValue - 期待値
     */
    public static void mockGetFeqBizDateType(Timestamp l_tsOrderAcceptDate, String l_strExpectValue)
        throws WEB3BaseException
    {
        mockGetFeqBizDateType(
            l_clendarContext.getInstitutionCode(), 
            l_clendarContext.getMarketCode(), 
            l_tsOrderAcceptDate, 
            l_strExpectValue);
        
    }
    
    /**
     * (get市場閉局警告外株市場)のMockメソッド<BR>
     * <BR>
     * @@param l_genBranch 部店オブジェクト
     * @@param l_expects - 期待値
     */
    public static void mockGetTradeCloseFeqMarket(WEB3GentradeBranch l_genBranch,
        String[] l_expects) throws WEB3BaseException
    {
        if (!(l_genBranch instanceof WEB3GentradeBranchForMock))
        {
            log.error("WEB3GentradeBranch must use Mock Object WEB3GentradeBranchForMock");
            return;
        }
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeBranch",
            "getMarketMessageSuspension",
            new Class[] {ProductTypeEnum.class,
                String.class,
                String.class},
            new Long(100));

        WEB3GentradeTradingTimeManagementForMock.mockGetFeqBizDateType(l_tsBizDate, "1");
        WEB3GentradeTradingTimeManagementForMock.mockGetFeqBizDateType(l_tsBizDate, "1");
        
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqBranchMarketDealtConds = 
            new WEB3GentradeFeqBranchMarketDealtCond[l_expects.length];
        
        
        for (int i = 0; i < l_expects.length; i++)
        {
            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = 
                TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_feqBranchMarketDealtCondParams.setBranchCode(l_genBranch.getBranchCode());
            l_feqBranchMarketDealtCondParams.setInstitutionCode(l_genBranch.getInstitution().getInstitutionCode());
            l_feqBranchMarketDealtCondParams.setMarketCode(l_expects[i]);
            l_feqBranchMarketDealtCondParams.setMartCanDealtEquity("1");
            l_feqBranchMarketDealtConds[i] = new WEB3GentradeFeqBranchMarketDealtCondForMock(l_feqBranchMarketDealtCondParams);
            
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseTime(l_expects[i],l_clendarContext.getProductCode(),"150001");
        }        
        
        WEB3GentradeFeqBranchMarketDealtCondForMock.mockGetFeqHandlingCondBranchMarket(
            l_genBranch, 
            l_feqBranchMarketDealtConds);
    }
    
    /**
     * (get市場閉局時間)のMockメソッド<BR>
     * <BR>
     * @@param l_strMarketCode - 市場コード
     * @@param l_strProductCode - 銘柄コード 
     * @@param l_strExpectValue - 期待値
     */
    public static void mockGetTradeCloseTime(
        String l_strMarketCode,
        String l_strProductCode,
        String l_strExpectValue)
        throws WEB3BaseException
    {
        WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsBizDate, l_clendarContext.getBizDateType());
                
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
        l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
        l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
        l_tradingTimeParams.setMarketCode(l_strMarketCode);
        l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
        l_tradingTimeParams.setProductCode(l_strProductCode);
        l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime(l_strExpectValue);
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("0");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        TestDBUtility.insertWithDel(l_tradingTimeParams);                
    }    
    
    /**
     * (get発注日)のMockメソッド<BR>
     * <BR>
     * @@param l_strExpectValue - 期待値
     */
    public static void mockGetOrderBizDate(Date l_expectDate)
        throws WEB3BaseException
    {
        WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), l_clendarContext.getBizDateType());
        
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
        l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
        l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
        l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
        l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
        l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");        
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("0");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        TestDBUtility.insertWithDel(l_tradingTimeParams);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, new Timestamp(l_expectDate.getTime()));        
    }
    
    /**
     * (setTimestamp)のMockメソッド<BR>
     * <BR>
     * @@throws WEB3BaseException 
     */
    public static void mockSetTimestamp() throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_tsBizDate.getTime()));
        
        WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
            new Timestamp(l_tsBizDate.getTime()), 
            l_clendarContext.getBizDateType());
        
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
        l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
        l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
        l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
        l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
        l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");        
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("0");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
        
        TestDBUtility.insertWithDel(l_tradingTimeParams);
    }
    
    /**
     * (setTimestamp)のMockメソッド<BR>
     * <BR>
     * @@throws WEB3BaseException 
     */
    public static void mockGetTradeCloseMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productType,
        String l_strMargineTradeDiv,
        String[] l_strExpectValue)
        throws WEB3BaseException
    {
        if (l_strExpectValue == null)
        {
            mockGetBizDateType(l_tsBizDate,WEB3BizDateTypeDef.NO_BIZ_DATE);
            return;
        }
        if((l_productType.equals(ProductTypeEnum.EQUITY))
            &&( ! WEB3MarginTradingDivDef.DEFAULT.equals(l_strMargineTradeDiv)))
        {
            mockGetTradeCloseSuspensionMarketRepayment(l_genBranch, l_strMargineTradeDiv, l_strExpectValue);
            return;
        }
        
        
    }
    
    protected static void mockGetTradeCloseSuspensionMarketRepayment(
        WEB3GentradeBranch l_genBranch,
        String l_strMargineTradeDiv,
        String[] l_strExpectValue)
            throws WEB3BaseException
    {        
        if (!(l_genBranch instanceof WEB3GentradeBranchForMock))
        {
            log.error("WEB3GentradeBranch must use Mock Object WEB3GentradeBranchForMock");
            return;
        }
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        if (l_strExpectValue == null)
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "getMarketMessageSuspension",
                new Class[] {ProductTypeEnum.class,
                    String.class,
                    String.class},
                new Long(0));
            return;
        }
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeBranch",
            "getMarketMessageSuspension",
            new Class[] {ProductTypeEnum.class,
                String.class,
                String.class},
            new Long(100));
        
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketDealtCond = new WEB3GentradeBranchMarketRepayDealtCond[l_strExpectValue.length];
        for (int i = 0; i < l_strExpectValue.length; i++)
        {
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams = 
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_branchMarketRepayDealtCondParams.setBranchCode(l_genBranch.getBranchCode());
            l_branchMarketRepayDealtCondParams.setInstitutionCode(l_genBranch.getInstitution().getInstitutionCode());
            l_branchMarketRepayDealtCondParams.setMarketCode(l_strExpectValue[i]);
            l_branchMarketRepayDealtCondParams.setRepaymentDiv(l_strMargineTradeDiv);
            l_branchMarketRepayDealtCondParams.setMartCanDealt("1");
            
            l_branchMarketDealtCond[i] = 
                new WEB3GentradeBranchMarketRepayDealtCondForMock(l_branchMarketRepayDealtCondParams);
            
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseTime(l_strExpectValue[i],l_clendarContext.getProductCode(),"150001");
        }
        WEB3GentradeBranchMarketRepayDealtCondForMock.mockGetBranchMarketRepayDealtCond(l_genBranch,l_branchMarketDealtCond);
        
        
    }

    /**
     * mockIs市場開局時間帯<BR>
     * @@return java.lang.boolean
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public static void mockIsTradeOpenTimeZone(boolean l_blnExpectValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mockIsTradeOpenTimeZone()";
        log.entering(STR_METHOD_NAME);
        if (!l_blnExpectValue)
        {
            mockGetBizDateType(l_tsBizDate,WEB3BizDateTypeDef.NO_BIZ_DATE);
        }
        else
        {
            mockGetBizDateType(l_tsBizDate,WEB3BizDateTypeDef.BIZ_DATE_AM);
        }

        TestDBUtility.deleteAll(TradingTimeRow.TYPE);

    }

    /**
     * is取引所休憩時間帯()のMockメソッド <BR>
     * 取引所が休憩時間帯の場合はtrueを、そうでない場合はfalseを返却する。<BR> 
     * @@throws WEB3BaseException 
     */
    public static void mockIsTradeCloseTimeZone(boolean l_blnExpectValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTradeCloseTimeZone()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsBizDate, l_clendarContext.getBizDateType());
        WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
        l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
        l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
        l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
        l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
        l_tradingTimeParams.setBizDateType("2");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");
        if (!l_blnExpectValue)
        {
            l_tradingTimeParams.setSubmitMarketTrigger("1");
        }
        else
        {
            l_tradingTimeParams.setSubmitMarketTrigger("0");
        }

        TestDBUtility.insertWithDel(l_tradingTimeParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 
     * @@param l_isPass
     *  (1)true: pass,(2)false: throws Exception
     * @@throws WEB3BaseException
     */
    public static void mockValidateTradeCloseChangeOrCancel(boolean l_isPass) throws WEB3BaseException
    {
        mockIsTradeOpenTimeZone(l_isPass);
    }

    public static void setClendarContext(WEB3GentradeTradingClendarContext l_tmpClendarContext)
    {
        l_clendarContextUserSet = l_tmpClendarContext;
        setSystemAttributes();
    }
    
    public static void setTimestampTag(Timestamp l_tsBizDateTmp)
    {
        l_tsBizDateUserSet = l_tsBizDateTmp;
        setSystemAttributes();
    }

    public static WEB3GentradeTradingClendarContext getClendarContext()
    {
        return l_clendarContext;
    }

    public static Timestamp getTimestampTag()
    {
        return l_tsBizDate;
    }

    public static void resetProductCode(String l_strProductCode)
    {
        l_clendarContext.setProductCode(l_strProductCode);
    }
    
    public static void resetMarketCode(String l_strMarketCode)
    {
        l_clendarContext.setMarketCode(l_strMarketCode);
    }
    
    public static void resetTradingTimeType(String l_strTradingTimeType)
    {
        l_clendarContext.setTradingTimeType(l_strTradingTimeType);
    }
    
    public static void resetOrderAcceptTransaction(String l_strOrderAcceptTransaction)
    {
        l_clendarContext.setOrderAcceptTransaction(l_strOrderAcceptTransaction);
    }
    
    public static void resetOrderAcceptProduct(String l_strOrderAcceptProduct)
    {
        l_clendarContext.setOrderAcceptProduct(l_strOrderAcceptProduct);
    }
    
    /**
     * 
     * @@param l_isPass
     *  (1)true: pass,(2)false: throws Exception
     * @@throws WEB3BaseException
     */
    public static void mockValidateOrderAccept(boolean l_isPass) throws WEB3BaseException
    {
        if(!l_isPass)
        {
            l_clendarContext.setBranchCode(null);
        }
        
        TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
        
        TradingTimeParams l_tradingTimeParams =
            TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
        l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
        l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
        
        Timestamp l_tsOrderAcceptDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                TIMESTAMP_TAG);

        //営業日区分の取得
        String l_bizDateType = getBizDateType(l_tsOrderAcceptDate);
            
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setBizDateType(l_bizDateType);
        l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
        l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
        TestDBUtility.insertWithDel(l_tradingTimeParams);
    }
    
    /**
     * 
     * @@param l_strValues ：預期の閉局間近の市場コード
     * @@param l_strProductDiv：銘柄タイプ
     * @@throws WEB3BaseException
     */
    public static void mockGetTradeCloseSuspension(String[] l_strValues,String l_strProductDiv) throws WEB3BaseException
    {
        TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        int l_intCntValues = l_strValues.length;
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setMarketCode("0");
        l_clendarContext.setProductCode(l_strValues[0]);
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setOrderAcceptProduct("1");
        l_clendarContext.setBizDateType("1");
        l_clendarContext.setEnableOrder("0");
        l_clendarContext.setBizdateCalcParameter("0");
        l_clendarContext.setSubmitMarketTrigger("1");
        setClendarContext(l_clendarContext);
        
        for(int i=0;i<l_intCntValues;i++)
        {
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_branchIndexDealtCondParams.setBranchCode(l_clendarContext.getBranchCode());
            l_branchIndexDealtCondParams.setMarketCode(l_clendarContext.getMarketCode());
            l_branchIndexDealtCondParams.setFutureOptionDiv(l_strProductDiv);
            l_branchIndexDealtCondParams.setTargetProductCode(l_strValues[i]);
            l_branchIndexDealtCondParams.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_strValues[i]);
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            l_tradingTimeParams.setBizdateCalcParameter(l_clendarContext.getBizdateCalcParameter());
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime(WEB3DateUtility.formatDate(l_tsBizDate,"HHmmss"));
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }  
    }
    
    /**
     * 
     * @@param l_blnIsExpected
     * @@return
     * @@throws WEB3SystemLayerException
     */
    public static void mockIsSubmitMarketTrigger(boolean l_blnIsExpected)
        throws WEB3SystemLayerException
    {
        try
        {

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            l_clendarContext.setBizdateCalcParameter("0");
            
            if (!l_blnIsExpected)
            {
                l_clendarContext.setSubmitMarketTrigger("0");
                setClendarContext(l_clendarContext);
                
                TestDBUtility.deleteAll(TradingTimeRow.TYPE);
                TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
                l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
                l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
                l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
                l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
                l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
                l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
                l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
                TestDBUtility.insertWithDel(l_tradingTimeParams);
            }
            else
            {

                l_clendarContext.setSubmitMarketTrigger("1");
                setClendarContext(l_clendarContext);
                
                TestDBUtility.deleteAll(TradingTimeRow.TYPE);
                TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
                l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
                l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
                l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
                l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
                l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
                l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
                l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
                TestDBUtility.insertWithDel(l_tradingTimeParams);
            }
        }
        catch (Exception l_ex)
        {
            log.error("",l_ex);
        }
    }
}
@
