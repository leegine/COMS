head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPSecurityValuationPerProductTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.tradingpower.updtpower.asset;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3TPSecurityValuationPerProductTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPSecurityValuationPerProductTest.class);
    
    private  WEB3TPSecurityValuationProduct l_product = new WEB3TPSecurityValuationProduct();
    
    private WEB3TPSecurityValuation l_valuation = new WEB3TPSecurityValuation();
    
    private int i = 0;
    
    public WEB3TPSecurityValuationPerProductTest(String arg0)
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
     * getPrevPriceSubstituteValuation
     */
    public void testGetPrevPriceSubstituteValuationCase1()
    {
        final String STR_METHOD_NAME = "testGetPrevPriceSubstituteValuationCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPSecurityValuationPerProduct l_securityValuationPerProduct =
                new WEB3TPSecurityValuationPerProductForTest();
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            
            Date[] l_dats = new Date[9];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_dats[2] = l_dat;
            l_dats[3] = l_dat;
            l_dats[4] = l_dat;
            l_dats[5] = l_dat;
            l_dats[6] = l_dat;
            l_condition.setBizDate(l_dats);
            
            this.l_product.setPrePrice(10.5);
            this.l_product.setSubstituteValuationRatio(10);
            this.l_product.setProductType(ProductTypeEnum.EQUITY);
            l_securityValuationPerProduct.setProduct(this.l_product);
            l_securityValuationPerProduct.setCalcCondition(l_condition);
            double l_dblResult = l_securityValuationPerProduct.getPrevPriceSubstituteValuation();
            
            assertEquals(new Double(13965.0), new Double(l_dblResult));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcValuationPriceCase0001()
    {
        final String STR_METHOD_NAME = "testCalcValuationPriceCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPSecurityValuationPerProduct l_securityValuationPerProduct =
                new WEB3TPSecurityValuationPerProductForTest();
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            
            Date[] l_dats = new Date[9];
            l_dats[0] = l_dat;
            l_dats[1] = l_dat;
            l_dats[2] = l_dat;
            l_dats[3] = l_dat;
            l_dats[4] = l_dat;
            l_dats[5] = l_dat;
            l_dats[6] = l_dat;
            l_condition.setBizDate(l_dats);
            l_condition.setEquityNextDayOrderStartDiv(true);
            
            this.l_product.setPrePrice(10.5);
            this.l_product.setSubstituteValuationRatio(10);
            this.l_product.setProductType(ProductTypeEnum.EQUITY);
            l_securityValuationPerProduct.setProduct(this.l_product);
            l_securityValuationPerProduct.setCalcCondition(l_condition);

            Method method = WEB3TPSecurityValuationPerProduct.class.getDeclaredMethod(
                "calcValuationPrice", 
                new Class[]{String.class});
            
            Object[] l_obj = {"1"};
            method.setAccessible(true);
            method.invoke(l_securityValuationPerProduct, l_obj);

            double l_dblResult = l_securityValuationPerProduct.getPrevPriceSubstituteValuation();

            assertEquals(WEB3StringTypeUtility.formatNumber(l_dblResult), "13965");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
        
        public void testCalcValuationPriceCase0002()
        {
            final String STR_METHOD_NAME = "testCalcValuationPriceCase0002()";
            log.entering(STR_METHOD_NAME);

            try
            {
                WEB3TPSecurityValuationPerProduct l_securityValuationPerProduct =
                    new WEB3TPSecurityValuationPerProductForTest();
                WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
                Date l_dat = WEB3DateUtility.getDate("20070101","yyyyMMdd");
                
                Date[] l_dats = new Date[9];
                l_dats[0] = l_dat;
                l_dats[1] = l_dat;
                l_dats[2] = l_dat;
                l_dats[3] = l_dat;
                l_dats[4] = l_dat;
                l_dats[5] = l_dat;
                l_dats[6] = l_dat;
                l_condition.setBizDate(l_dats);
                l_condition.setEquityNextDayOrderStartDiv(false);
                
                this.l_product.setPrePrice(10.5);
                this.l_product.setSubstituteValuationRatio(10);
                this.l_product.setProductType(ProductTypeEnum.EQUITY);
                l_securityValuationPerProduct.setProduct(this.l_product);
                l_securityValuationPerProduct.setCalcCondition(l_condition);

                Method method = WEB3TPSecurityValuationPerProduct.class.getDeclaredMethod(
                    "calcValuationPrice", 
                    new Class[]{String.class});
                
                Object[] l_obj = {"1"};
                method.setAccessible(true);
                method.invoke(l_securityValuationPerProduct, l_obj);

                double l_dblResult = l_securityValuationPerProduct.getPrevPriceSubstituteValuation();

                assertEquals(WEB3StringTypeUtility.formatNumber(l_dblResult), "13965");
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            log.exiting(STR_METHOD_NAME); 

    }
        
    private class WEB3TPSecurityValuationPerProductForTest extends WEB3TPSecurityValuationPerProduct
    {
        
        public WEB3TPSecurityValuationPerProductForTest()
        {
            super();
            super.create(l_product,l_valuation);
        }
        protected List getAssetChanges(String l_strDepositType)
        {
            WEB3TPSecurityChangeForTest l_change1 = new WEB3TPSecurityChangeForTest();
            l_change1.setQuantity(111);
            l_change1.setValuationPrice(25);
            l_change1.setValuationRatio(4);
            List l_list = new ArrayList();
            l_list.add(l_change1);
            return l_list;
        }
        
        protected List getTransferChanges(String l_strDepositType)
        {
            WEB3TPSecurityChangeForTest l_change1 = new WEB3TPSecurityChangeForTest();
            l_change1.setQuantity(222);
            List l_list = new ArrayList();
            l_list.add(l_change1);
            return l_list;
        }
        
        protected List getDeliveryChanges(String l_strDepositType)
        {
            WEB3TPSecurityChangeForTest l_change1 = new WEB3TPSecurityChangeForTest();
            l_change1.setQuantity(100);
            l_change1.setValuationPrice(4);
            l_change1.setValuationRatio(5);
            List l_list = new ArrayList();
            l_list.add(l_change1);
            return l_list;
        }
        
        protected List getTransactionChanges(String l_strDepositType)
        {
            WEB3TPSecurityTransactionChangeForTest l_change1 = new WEB3TPSecurityTransactionChangeForTest();
            if (i == 0)
            {
                l_change1.setExecType(WEB3TPExecTypeDef.EXECUTED);
                l_change1.setSide(SideEnum.SELL);
                i++;
            }
            else if(i == 1)
            {
                l_change1.setExecType(WEB3TPExecTypeDef.UNEXECUTED);
                l_change1.setSide(SideEnum.BUY);
                i--;
            }
            l_change1.setValuationPrice(4);
            l_change1.setValuationRatio(5);
            l_change1.setQuantity(100);
            
            List l_list = new ArrayList();
            l_list.add(l_change1);
            return l_list;
        }
    }
    
    private class WEB3TPSecurityChangeForTest extends WEB3TPSecurityChange
    {
        /**
         * (評価額)
         */
        private double valuationPrice = 10.0;
        
        public void calcReflectDay(Date l_datDeliveryDate)
        {
            
        }
        
        public boolean isDuringReflectTime(Date l_datBizDate)
        {
            return true;
        }
    }
    
    private class WEB3TPSecurityTransactionChangeForTest extends WEB3TPSecurityTransactionChange
    {
        public boolean isDuringReflectTime(Date l_datBizDate)
        {
            return true;
        }
    }
}
@
