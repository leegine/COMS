head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoProductManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoProductManagerImplTest(WEB3IfoProductManagerImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.ifo.data.IfoDeliveryMonthMasterParams;
import webbroker3.ifo.data.IfoDeliveryMonthMasterRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoProductManagerImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoProductManagerImplTest.class);

    public WEB3IfoProductManagerImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.WEB3IfoProductManagerImpl.getIfoTradedProduct(Institution, String, IfoDerivativeTypeEnum, String, double, String)'
     */
    public void testGetIfoTradedProductCase1()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase1()";
        log.entering(STR_METHOD_NAME);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        l_subAccountParams.setInstitutionCode("0D");
        l_subAccountParams.setInstitutionId(33);
        
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("0");
        l_marketParams.setInstitutionCode("0D");
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        l_productParams.setProductType(ProductTypeEnum.IFO);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setProductCode("0005");
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("0006");
        l_ifoProductParams.setMonthOfDelivery("200706");
        l_ifoProductParams.setStrikePrice(100.0);
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setSplitType("000");
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setProductId(3304148080000L);
        l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
        
        IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setProductId(3304148080000L);
        Calendar ca =  Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        Date date = ca.getTime();
        l_ifoTradedProductParams.setValidForBizDate(null);
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                            333812512203L,
                            33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            WEB3IfoProductManagerImpl l_productMgr = 
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            
            l_productMgr.getIfoTradedProduct(l_subAccount.getInstitution(),"0006",
                    IfoDerivativeTypeEnum.FUTURES, "200706", 100.0, "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 先物OP限月マスタテーブルを以下の条件で検索する
    　@[条件]
    　@先物OP限月マスタテーブル.原資産銘柄コード = 引数.原資産銘柄コード
    　@先物OP限月マスタテーブル.先物／オプション区分 = 引数.先物／オプション区分
     */
    public void testGetMonthOfDeliveryCase001()
    {
        final String STR_METHOD_NAME = "testGetMonthOfDeliveryCase001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);
            IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams =
                TestDBUtility.getIfoDeliveryMonthMasterRow();
            TestDBUtility.insertWithDel(l_IfoDeliveryMonthMasterParams);
            
            WEB3IfoProductManagerImpl l_impl = new WEB3IfoProductManagerImpl();
            List l_lisMonthOfDelivery = l_impl.getMonthOfDelivery("0005", "1");
            
            String[] l_str = new String[l_lisMonthOfDelivery.size()];
            l_lisMonthOfDelivery.toArray(l_str);
            
            assertEquals("200807", l_str[0]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 該当するデータが存在しない場合には「データ不整合エラー」の例外をスローする。 
     */
    public void testGetMonthOfDeliveryCase002()
    {
        final String STR_METHOD_NAME = "testGetMonthOfDeliveryCase002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);

            
            WEB3IfoProductManagerImpl l_impl = new WEB3IfoProductManagerImpl();
            l_impl.getMonthOfDelivery("0005", "1");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * （部店指数別）取扱条件[]為空
     */
    public void testGetDeliveryMonthListCase0001()
    {
        final String STR_METHOD_NAME = "testGetDeliveryMonthListCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);

            WEB3GentradeBranchIndexDealtCond[] l_gentradeBranchIndexDealtConds = null;
            
            WEB3IfoProductManagerImpl l_impl = new WEB3IfoProductManagerImpl();
            l_impl.getDeliveryMonthList(l_gentradeBranchIndexDealtConds, "1");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 取扱可能な原資産銘柄コードと先物/オプション区分に一致する限月の一覧を取得する。
     取得した全ての限月で重複しない値をセット。
     */
    public void testGetDeliveryMonthListCase0002()
    {
        final String STR_METHOD_NAME = "testGetDeliveryMonthListCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondRow = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondRow.setTargetProductCode("1234");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondRow);
            
            BranchIndexDealtCondParams l_branchIndexDealtCondRow1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondRow1.setTargetProductCode("2345");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondRow1);

            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);
            IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams =
                TestDBUtility.getIfoDeliveryMonthMasterRow();
            l_IfoDeliveryMonthMasterParams.setUnderlyingProductCode("1234");
            l_IfoDeliveryMonthMasterParams.setFutureOptionDiv("1");
            l_IfoDeliveryMonthMasterParams.setDeliveryMonth("200804");
            TestDBUtility.insertWithDel(l_IfoDeliveryMonthMasterParams);
            
            IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams1 =
                TestDBUtility.getIfoDeliveryMonthMasterRow();
            l_IfoDeliveryMonthMasterParams1.setUnderlyingProductCode("1234");
            l_IfoDeliveryMonthMasterParams1.setFutureOptionDiv("1");
            l_IfoDeliveryMonthMasterParams1.setDeliveryMonth("200803");
            TestDBUtility.insertWithDel(l_IfoDeliveryMonthMasterParams1);
            
            IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams4 =
                TestDBUtility.getIfoDeliveryMonthMasterRow();
            l_IfoDeliveryMonthMasterParams4.setUnderlyingProductCode("1234");
            l_IfoDeliveryMonthMasterParams4.setFutureOptionDiv("1");
            l_IfoDeliveryMonthMasterParams4.setDeliveryMonth("200810");
            TestDBUtility.insertWithDel(l_IfoDeliveryMonthMasterParams4);
            
            IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams5 =
                TestDBUtility.getIfoDeliveryMonthMasterRow();
            l_IfoDeliveryMonthMasterParams5.setUnderlyingProductCode("1234");
            l_IfoDeliveryMonthMasterParams5.setFutureOptionDiv("1");
            l_IfoDeliveryMonthMasterParams5.setDeliveryMonth("201806");
            TestDBUtility.insertWithDel(l_IfoDeliveryMonthMasterParams5);
            
            IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams2 =
                TestDBUtility.getIfoDeliveryMonthMasterRow();
            l_IfoDeliveryMonthMasterParams2.setUnderlyingProductCode("2345");
            l_IfoDeliveryMonthMasterParams2.setFutureOptionDiv("1");
            l_IfoDeliveryMonthMasterParams2.setDeliveryMonth("200803");
            TestDBUtility.insertWithDel(l_IfoDeliveryMonthMasterParams2);
            
            IfoDeliveryMonthMasterParams l_IfoDeliveryMonthMasterParams3 =
                TestDBUtility.getIfoDeliveryMonthMasterRow();
            l_IfoDeliveryMonthMasterParams3.setUnderlyingProductCode("2345");
            l_IfoDeliveryMonthMasterParams3.setFutureOptionDiv("1");
            l_IfoDeliveryMonthMasterParams3.setDeliveryMonth("200802");
            TestDBUtility.insertWithDel(l_IfoDeliveryMonthMasterParams3);
            
            WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtCond =
                new WEB3GentradeBranchIndexDealtCond[2];
            
            l_branchIndexDealtCond[0] = new WEB3GentradeBranchIndexDealtCond(l_branchIndexDealtCondRow);
            l_branchIndexDealtCond[1] = new WEB3GentradeBranchIndexDealtCond(l_branchIndexDealtCondRow1);
            
            WEB3IfoProductManagerImpl l_impl = new WEB3IfoProductManagerImpl();
            List l_lisDeliveryMonthList = l_impl.getDeliveryMonthList(l_branchIndexDealtCond, "1");
            
            assertEquals(5, l_lisDeliveryMonthList.size());
            
            String[] l_str = new String[l_lisDeliveryMonthList.size()];
            l_lisDeliveryMonthList.toArray(l_str);
            
            assertEquals("200802", l_str[0]);
            assertEquals("200803", l_str[1]);
            assertEquals("200804", l_str[2]);
            assertEquals("200810", l_str[3]);
            assertEquals("201806", l_str[4]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }  
}
@
