head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityBizLogicProviderForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス （株式計算）ForMock(WEB3EquityBizLogicProviderForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 計算サービス （株式計算）ForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3EquityBizLogicProviderForMock extends WEB3EquityBizLogicProvider
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityBizLogicProviderForMock.class);

    /**
     * （get手数料コースコード(Mock)）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strCommProductCode - (手数料商品コード)<BR>
     * @@param l_strRegNo - (手数料NO)<BR>
     * @@param l_strRevision - (枝番)<BR>
     * @@param l_datBizDate - (発注日)<BR>
     */
     public String getCommissionCourseDiv(
         String l_strInstitutionCode,
         String l_strCommProductCode,
         String l_strRegNo,
         String l_strRevision,
         Date l_datBizDate)
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME =
             "getCommissionCourseDiv(String, String, String, String, Date)-->ForMock";
         log.entering(STR_METHOD_NAME);

         //1）參數驗證
         TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
             "webbroker3.equity.WEB3EquityBizLogicProvider",
             "getCommissionCourseDiv",
             new Class[] {String.class, String.class, String.class, String.class, Date.class},
             new Object[]{l_strInstitutionCode, l_strCommProductCode, l_strRegNo, l_strRevision, l_datBizDate});

         if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
             "webbroker3.equity.WEB3EquityBizLogicProvider",
             "getCommissionCourseDiv",
             new Class[] {String.class, String.class, String.class, String.class, Date.class}))
         {
             log.exiting(STR_METHOD_NAME);
             //2）MockFor --〉 asWEB3BaseException
             TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                 "webbroker3.equity.WEB3EquityBizLogicProvider",
                 "getCommissionCourseDiv",
                 new Class[] {
                     String.class, String.class, String.class, String.class, Date.class}
                     ).asWEB3BaseException();

             //3)MockFor --〉 asObject
             return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                 "webbroker3.equity.WEB3EquityBizLogicProvider",
                 "getCommissionCourseDiv",
                 new Class[] {String.class, String.class, String.class, String.class, Date.class}).asObject();
         }

         log.exiting(STR_METHOD_NAME);
         return super.getCommissionCourseDiv(
             l_strInstitutionCode, l_strCommProductCode, l_strRegNo, l_strRevision, l_datBizDate);
    }
     
     public WEB3GentradeCommission createCommission(WEB3GentradeSubAccount l_genSubAccount, String l_strMarketCode,
            Date l_datBizDate, String l_strOrderChanel, String l_strMarginType, double l_dblRepaymentNum,
            OrderCategEnum l_orderCateg) throws WEB3BaseException
    {
        
         TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                 "webbroker3.equity.WEB3EquityBizLogicProvider",
                 "createCommission",
                 new Class[]{WEB3GentradeSubAccount.class,String.class,Date.class,String.class,String.class,double.class,OrderCategEnum.class},
                 new Object[]{l_genSubAccount,l_strMarketCode,l_datBizDate,l_strOrderChanel,l_strMarginType,new Double(l_dblRepaymentNum),l_orderCateg});
         if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                 "webbroker3.equity.WEB3EquityBizLogicProvider",
                 "createCommission",
                 new Class[]{WEB3GentradeSubAccount.class,String.class,Date.class,String.class,String.class,double.class,OrderCategEnum.class}))
         {
             log.debug("webbroker3.equity.WEB3EquityBizLogicProviderForMock.createCommission()");
             
             TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                     "webbroker3.equity.WEB3EquityBizLogicProvider",
                     "createCommission",
                     new Class[]{WEB3GentradeSubAccount.class,String.class,Date.class,String.class,String.class,double.class,OrderCategEnum.class}).asWEB3BaseException();
             
             return (WEB3GentradeCommission)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                     "webbroker3.equity.WEB3EquityBizLogicProvider",
                     "createCommission",
                     new Class[]{WEB3GentradeSubAccount.class,String.class,Date.class,String.class,String.class,double.class,OrderCategEnum.class}).asObject();
             
         } 
         return super.createCommission(l_genSubAccount, l_strMarketCode, l_datBizDate, l_strOrderChanel,
                l_strMarginType, l_dblRepaymentNum, l_orderCateg);
    }
     
     public double calcExpenses(
             double l_dblCommissionFee,
             double l_dblCommissionFeeTax,
             double l_dblSetupFee,
             double l_dblSetupFeeTax,
             double l_dblNameTransferFee,
             double l_dblNameTransferFeeTax,
             double l_dblManagementFee,
             double l_dblManagementFeeTax,
             double l_dblInterestFee,
             double l_dblPayInterestFee,
             double l_dblLoanEquityFee,
             double l_dblOther,
             ContractTypeEnum l_contractType)
         {
         
         TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                 "webbroker3.equity.WEB3EquityBizLogicProvider",
                 "calcExpenses",
                 new Class[]{double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             ContractTypeEnum.class},
                 new Object[]{new Double(l_dblCommissionFee),
                         new Double(l_dblCommissionFeeTax),
                         new Double(l_dblSetupFee),
                         new Double(l_dblSetupFeeTax),
                         new Double(l_dblNameTransferFee),
                         new Double(l_dblNameTransferFeeTax),
                         new Double(l_dblManagementFee),
                         new Double(l_dblManagementFeeTax),
                         new Double(l_dblInterestFee),
                         new Double(l_dblPayInterestFee),
                         new Double(l_dblLoanEquityFee),
                         new Double(l_dblOther),
                         l_contractType});
         
         if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                 "webbroker3.equity.WEB3EquityBizLogicProvider",
                 "calcExpenses",
                 new Class[]{double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             double.class,
                             ContractTypeEnum.class}))
         {
             log.debug("webbroker3.equity.WEB3EquityBizLogicProviderForMock.calcExpenses");
             
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                     "webbroker3.equity.WEB3EquityBizLogicProvider",
                     "calcExpenses",
                     new Class[]{double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 double.class,
                                 ContractTypeEnum.class}).asDouble();
             
         }
         
         
         
         return super.calcExpenses(
                 l_dblCommissionFee,
                 l_dblCommissionFeeTax,
                 l_dblSetupFee,
                 l_dblSetupFeeTax,
                 l_dblNameTransferFee,
                 l_dblNameTransferFeeTax,
                 l_dblManagementFee,
                 l_dblManagementFeeTax,
                 l_dblInterestFee,
                 l_dblPayInterestFee,
                 l_dblLoanEquityFee,
                 l_dblOther,
                 l_contractType);
         }
     
     public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                "calcCommission", new Class[]
                { WEB3GentradeCommission.class, SubAccount.class }, new Object[]
                { l_commission, l_subAccount });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityBizLogicProvider", "calcCommission",
                new Class[]
                { WEB3GentradeCommission.class, SubAccount.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityBizLogicProviderForMock.calcCommission()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class }).asWEB3BaseException();
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class }).asVoid();
            return;
        }
        super.calcCommission(l_commission, l_subAccount);
    }
     
     public double calcEstimatedBookPrice(long l_lngProductId, SubAccount l_subAccount, TaxTypeEnum l_taxType)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                "calcEstimatedBookPrice", new Class[]
                { long.class, SubAccount.class, TaxTypeEnum.class }, new Object[]
                { new Long(l_lngProductId), l_subAccount, l_taxType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.equity.WEB3EquityBizLogicProvider",
                "calcEstimatedBookPrice", new Class[]
                { long.class, SubAccount.class, TaxTypeEnum.class }))
        {
            log.debug("webbroker3.equity.WEB3EquityBizLogicProviderForMock.calcEstimatedBookPrice()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice", new Class[]
                    { long.class, SubAccount.class, TaxTypeEnum.class }).asWEB3BaseException();
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice", new Class[]
                    { long.class, SubAccount.class, TaxTypeEnum.class }).asDouble();
        }
        return super.calcEstimatedBookPrice(l_lngProductId, l_subAccount, l_taxType);
    }
}
@
