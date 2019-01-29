head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP計算サービス(WEB3IfoBizLogicProvider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 馬振田 (中訊) 新規作成
                 : 2004/07/07 李媛（中訊）　@方法@calcRestraintTurnover() を更新しました
Revesion History : 2007/06/08 孟亜南 (中訊) 仕様変更モデルNo.681
Revesion History : 2007/06/14 孟亜南 (中訊) 仕様変更モデルNo.731
Revesion History : 2007/10/10 張騰宇 (中訊) 仕様変更モデルNo.784 計算式書 014 015
Revesion History : 2007/10/16 張騰宇 (中訊) 仕様変更モデルNo.791
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
Revesion History : 2008/09/03 安陽(中訊) IFO小数点対応
**/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP計算サービス)<BR>
 * オプション計算サービスクラス<BR>
 * @@author  馬振田
 * @@version 1.0
 */
public class WEB3IfoBizLogicProvider extends WEB3GentradeBizLogicProvider implements GlobalBizLogicProvider, IfoBizLogicProvider
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoBizLogicProvider.class);

    /**
     * @@roseuid 40C075150157
     */
    public WEB3IfoBizLogicProvider()
    {

    }

    /**
     * (calc受渡代金)<BR>
     * 受渡代金を計算する。<BR>
     * <BR>
     * 以下の計算式で受渡代金を求める。<BR>
     * <BR>
     * （計算式）<BR>
     * 　@○　@買の場合（売買==SideEnum.SideEnum.BUY）<BR>
     * 　@　@諸経費計算用代金＋（委託手数料＋委託手数料消費税）<BR>
     * <BR>
     * 　@○　@売の場合（売買==SideEnum.SideEnum.SELL）<BR>
     * 　@　@諸経費計算用代金－（委託手数料＋委託手数料消費税）<BR>
     * <BR>
     * (*1) 先物OP取引銘柄.1単位当たり乗数<BR>
     * @@param l_dealing - 売買<BR>
     * 　@SideEnum.BUY（買）<BR>
     * 　@SideEnum.SELL（売）<BR>
     *
     * @@param l_dblExpenseCalculationAmount - 諸費用計算用代金<BR>
     * <BR>
     * 　@－数量×単価×指数乗数(*1)<BR>
     * 　@但し、概算受渡代金の計算の場合は（数量×単価×指数乗数(*1)×割増拘束率）<BR>
     * @@param l_dblConsignmentCommission - 委託手数料
     * @@param l_dblCommConsumptionTax - 委託手数料消費税
     * @@return double
     * @@roseuid 4073C1BD007C
     */
    public double calcDeliveryAmount(SideEnum l_dealing, double l_dblExpenseCalculationAmount, double l_dblConsignmentCommission, double l_dblCommConsumptionTax)
    {
        double l_dblDeliveryAmount = 0.0;
        BigDecimal l_bdExpenseCalculationAmount = new BigDecimal(l_dblExpenseCalculationAmount + "");
        BigDecimal l_bdConsignmentCommission = new BigDecimal(l_dblConsignmentCommission + "");
        BigDecimal l_bdCommConsumptionTax = new BigDecimal(l_dblCommConsumptionTax + "");
        log.debug("l_dealing = " + l_dealing);
        log.debug("諸費用計算用代金 = " + l_dblExpenseCalculationAmount);
        log.debug("委託手数料 = " + l_dblConsignmentCommission);
        log.debug("委託手数料消費税 = " + l_dblCommConsumptionTax);
        
        if (SideEnum.BUY.equals(l_dealing))
        {
            log.debug("買の場合");
            l_dblDeliveryAmount =
                l_bdExpenseCalculationAmount.add(l_bdConsignmentCommission).add(l_bdCommConsumptionTax).doubleValue();
        }

        if (SideEnum.SELL.equals(l_dealing))
        {
            log.debug("売の場合");
            l_dblDeliveryAmount =
                l_bdExpenseCalculationAmount.subtract(l_bdConsignmentCommission).subtract(l_bdCommConsumptionTax).doubleValue();
        }
        log.debug("受渡代金 = " + l_dblDeliveryAmount);
        return l_dblDeliveryAmount;
    }

    /**
     * (calc手数料（按分）)<BR>
     * 一口約定に対する委託手数料・消費税を計算し、  <BR>
     * その内容を表すConsolidatedCommissionInfoオブジェクトを返す。  <BR>
     * <BR>
     * 1) 引数.取引勘定明細Params[]の要素分ループ処理を行い、<BR>
     * 以下項目の合計値を計算する。 <BR>
     * <BR>
     * 　@　@・(合計)約定代金(*1) <BR>
     * 　@　@・(合計)約定数量(*2) <BR>
     * <BR>
     * 2) (合計)委託手数料を算出する。 <BR>
     * <BR>
     * 　@　@2)-1 this.create手数料(注文単位ID,数量)をコールする(*3) <BR>
     * 　@　@2)-2 2)-1で生成した手数料.諸経費計算用代金へ 1)で算出した<BR>
     * 　@　@　@　@　@　@(合計)約定代金を設定する <BR>
     * 　@　@2)-2 calc委託手数料(手数料,補助口座)をコールする(*4) <BR>
     * <BR>
     * 3) (合計)消費税を算出する。 <BR>
     * <BR>
     * 　@　@3)-1 calc消費税(金額,基準日,補助口座)をコールする(*5) <BR>
     * 　@　@3)-2 2)-1で生成した手数料.消費税へ 3)-1で算出した<BR>
     * 　@　@　@　@　@　@(合計)消費税を設定する <BR>
     * <BR>
     * 4) 委託手数料、消費税について、按分計算を行う。 <BR>
     * <BR>
     * 　@　@4－1) 手数料.手数料乗数 == nullの場合 <BR>
     * <BR>
     * 　@　@　@　@this.calc手数料（代金按分）をコールする(*6) <BR>
     * <BR>
     * 　@　@4－2) 手数料.手数料乗数 != nullの場合 <BR>
     * <BR>
     * 　@　@　@　@this.calc手数料（枚数按分）をコールする(*7) <BR>
     * <BR>
     * 5) 4)で取得した計算結果を返却する。 <BR>
     * <BR>
     * <BR>
     * (*1) 取引勘定明細Params[n]の約定代金(約定数量 * 約定単価 * 指数乗数)<BR>
     * 　@　@を合計した値。 <BR>
     * (*2) 取引勘定明細Params[n].約定数量を合計した値。 <BR>
     * (*3) [引数設定] <BR>
     * 　@　@　@　@注文単位ID ： 取引勘定明細Params[0].注文単位ID <BR>
     * 　@　@　@　@数量 ： 1)で算出した(合計)約定数量 <BR>
     * (*4) [引数設定] <BR>
     * 　@　@　@　@手数料 ： 2)-1で生成した手数料 <BR>
     * 　@　@　@　@補助口座 : 補助口座 <BR>
     * (*5) [引数設定] <BR>
     * 　@　@　@　@金額 ： 2)-1で生成した手数料.get手数料金額() <BR>
     * 　@　@　@　@基準日 ： 2)-1で生成した手数料.get発注日() <BR>
     * 　@　@　@　@補助口座 ： 補助口座 <BR>
     * (*6) [引数設定] <BR>
     * 　@　@　@　@取引勘定明細Params ： 当メソッドの引数.取引勘定明細Params <BR>
     * 　@　@　@　@手数料 ： 2)-1で生成した手数料 <BR>
     * 　@　@　@　@(分割)約定代金[] ： 取引勘定明細Paramsの約定代金 <BR>
     * (*7) [引数設定] <BR>
     * 　@　@　@　@取引勘定明細Params ： 当メソッドの引数.取引勘定明細Params <BR>
     * 　@　@　@　@手数料 ： 2)-1で生成した手数料 <BR>
     * 　@　@　@　@(分割)約定数量[] ： 取引勘定明細Params.約定数量<BR>
     * @@param l_arrIfoFinTransactionRow - 取引勘定明細Params<BR>
     * トランザクション（取引勘定明細）行オブジェクトの配列<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4088B35503E1
     */
    public ConsolidatedCommissionInfo calcCommission(IfoFinTransactionRow[] l_arrIfoFinTransactionRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCommission(IfoFinTransactionRow[])";

        log.entering(STR_METHOD_NAME);

        if (l_arrIfoFinTransactionRow == null)
        {
            log.error("引数がnullです。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoProductManager l_productManager = (IfoProductManager)l_tm.getProductManager();
        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();

        // ［合計］約定代金と最大約定を求める
        int l_intLength = l_arrIfoFinTransactionRow.length;
        BigDecimal[] l_bdAmounts = new BigDecimal[l_intLength];
        BigDecimal l_bdTotalAmount = new BigDecimal("0.0");
        double l_dblTotalQuantity = 0;
        
        int l_intMax = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            IfoFinTransactionRow l_row = l_arrIfoFinTransactionRow[i];

            TradedProduct l_tradedProduct = null;
            try
            {
                l_tradedProduct =  l_productManager.getTradedProduct(
                    l_productManager.getProduct(l_row.getProductId()), 
                    l_finObjectManager.getMarket(l_row.getMarketId()));
            }
            catch (NotFoundException l_exp)
            {
                log.error(STR_METHOD_NAME, l_exp);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    l_exp.getMessage());
            }

            IfoTradedProductRow l_ifoTradedProductRow = (IfoTradedProductRow)l_tradedProduct.getDataSourceObject();
             
            BigDecimal l_bdPrice = new BigDecimal(l_row.getPrice() + "");
            log.debug("取引勘定明細[" + i + "].約定単価：" + l_bdPrice.doubleValue());
    
            BigDecimal l_bdQuantity = new BigDecimal(l_row.getQuantity() + "");
            log.debug("取引勘定明細[" + i + "].約定数量：" + l_bdQuantity.doubleValue());
            
            l_dblTotalQuantity += l_row.getQuantity();
    
            // 約定代金＝約定数量×約定単価×指数乗数
            // 指数乗数は先物OP取引銘柄マスタ.１単位当り指数乗数から設定してください。
            BigDecimal l_bdUnitSize = new BigDecimal(l_ifoTradedProductRow.getUnitSize() + "");
            l_bdAmounts[i] = l_bdPrice.multiply(l_bdQuantity).multiply(l_bdUnitSize);
            log.debug("取引勘定明細[" + i + "].約定金額：" + l_bdAmounts[i].doubleValue());
    
            l_bdTotalAmount = l_bdTotalAmount.add(l_bdAmounts[i]);
        }
        log.debug("［合計］約定代金：" + l_bdTotalAmount.doubleValue());
        log.debug("［合計］約定数量：" + l_dblTotalQuantity);

        // ［合計］委託手数料を求める
        BigDecimal l_bdTotalCommission;
        WEB3GentradeCommission l_commission;
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_arrIfoFinTransactionRow[l_intMax].getAccountId(),
                    l_arrIfoFinTransactionRow[l_intMax].getSubAccountId()
                    );
            
            l_commission = this.createCommission(
                l_arrIfoFinTransactionRow[l_intMax].getOrderUnitId(),
                l_dblTotalQuantity);
            l_commission.setExpensesCalcAmount(l_bdTotalAmount.doubleValue());
            
            calcCommission(l_commission, l_subAccount);
            l_bdTotalCommission = new BigDecimal(l_commission.getCommission() + "");
            log.debug("［合計］委託手数料：" + l_bdTotalCommission.doubleValue());
        }
        catch (NotFoundException l_nfE)
        {
            String l_strMessage = "データが見つかりません";
            log.error(l_strMessage, l_nfE);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                l_nfE.getMessage()
                );
        }

        // ［合計］消費税の計算を行う
        BigDecimal l_bdTotalSalesTax =
            new BigDecimal(
                calcSalesTax(
                    l_commission.getCommission(),
                    l_commission.getOrderBizDate(),
                    l_subAccount) + ""
                    );
        log.debug("［合計］消費税：" + l_bdTotalSalesTax.doubleValue());
        l_commission.setConsumptionTax(l_bdTotalSalesTax.doubleValue());

        //委託手数料、消費税について、按分計算を行う。
        ConsolidatedCommissionInfo l_consolidatedCommissionInfo = null;

        //手数料.手数料乗数 == nullの場合
        //this.calc手数料（代金按分）をコールする
        if (l_commission.getCommitionPerUnit() == 0)
        {
            double[] l_dblAmounts = new double[l_intLength];
            for (int i = 0; i < l_intLength; i++)
            {
                l_dblAmounts[i] = l_bdAmounts[i].doubleValue();
            }
            l_consolidatedCommissionInfo =
                this.calcCommissionAmount(l_arrIfoFinTransactionRow, l_commission, l_dblAmounts);
        }
        //手数料.手数料乗数 != nullの場合
        //this.calc手数料（枚数按分）をコールする
        else
        {
            double[] l_dblQuantitys = new double[l_intLength];
            for (int i = 0; i < l_intLength; i++)
            {
                l_dblQuantitys[i] = l_arrIfoFinTransactionRow[i].getQuantity();
            }
            l_consolidatedCommissionInfo =
                this.calcCommissionQuantity(l_arrIfoFinTransactionRow, l_commission, l_dblQuantitys);
        }

        log.exiting(STR_METHOD_NAME);
        return l_consolidatedCommissionInfo;
    }

    /**
     * (calc売買代金)<BR>
     * 売買代金を計算して返却する。<BR>
     * <BR>
     * 売買代金 ＝ 注文株数×計算単価×指数乗数(*1)<BR>
     * <BR>
     * (*1) 先物OP取引銘柄.1単位当たり乗数<BR>
     * @@param l_dblQuantity - 数量
     * @@param l_dblCalcUnitPrice - 計算単価<BR>
     * 売買代金を計算するための単価<BR>
     * @@param l_ifoTradedProduct - 先物OP取引銘柄<BR>
     * 先物OP取引銘柄オブジェクト<BR>
     * @@return double
     * @@roseuid 40A853D00145
     */
    public double calcTurnOver(double l_dblQuantity, double l_dblCalcUnitPrice, WEB3IfoTradedProductImpl l_ifoTradedProduct)
    {
        final String STR_METHOD_NAME = "calcTurnOver(double , double, WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        double l_dblTurnover = 0.0;
        BigDecimal l_bdTurnover;
        BigDecimal l_bdUnitPrice;
        BigDecimal l_bdQuantity;
        BigDecimal l_bdMultiple;

        //売買代金 ＝ 注文株数×計算単価×指数乗数(*1)
        //(*1) 先物OP取引銘柄.1単位当たり乗数
        IfoTradedProductRow l_row =
            (IfoTradedProductRow)l_ifoTradedProduct.getDataSourceObject();
        l_bdMultiple = new BigDecimal(l_row.getUnitSize() + "");

        l_bdQuantity = new BigDecimal(l_dblQuantity + "");
        l_bdUnitPrice = new BigDecimal(l_dblCalcUnitPrice + "");
        l_bdTurnover = l_bdUnitPrice.multiply(l_bdQuantity);
        l_bdTurnover = l_bdTurnover.multiply(l_bdMultiple);
        l_dblTurnover = l_bdTurnover.setScale(0, BigDecimal.ROUND_FLOOR).doubleValue();
        log.debug("売買代金：[" + l_dblTurnover + "]");
        log.debug("計算単価：[" + l_dblCalcUnitPrice + "]");
        log.debug("注文株数：[" + l_dblQuantity + "]");
        log.debug("指数乗数：[" + l_row.getUnitSize() + "]");

        log.exiting(STR_METHOD_NAME);

        return l_dblTurnover;
    }

    /**
      * (calc拘束売買代金)<BR>
      *<BR>
      * 計算単価、株数より拘束売買代金を計算して返却する。<BR>
      *<BR>
      * ※計算の詳細については<BR>
      * 「基本設計計算式書（株価指数オプション）_ver.1.1.doc」<BR>
      * 8.拘束売買代金　@を参照 <BR>
      *<BR>
      * １）会社部店商品テーブルより割増拘束率を取得する。（引数.部店IDと引数.手数料商品コードで取得）<BR>
      *<BR>
      * ２）拘束売買代金を計算する。<BR>
      *<BR>
      * 　@拘束売買代金＝引数.数量×引数.計算単価×割増拘束率<BR>
      *
      * （注）割増拘束率を採用しないケースについては、当該項目には'1'が設定されているため 、<BR>
      * 一律上記の計算式で計算する。<BR>
      *
      * ３）　@指数乗数を掛ける<BR>
      * 　@以下の値を返却する。<BR>
      * （拘束売買代金）×先物OP取引銘柄.1単位当たり乗数<BR>
      *
      * @@param l_dblQuantity - (株数)<BR>
      *<BR>
      * 注文（約定）株数<BR>
      * @@param l_dblUnitPrice - (計算単価)<BR>
      *<BR>
      * 拘束売買代金を計算するための単価<BR>
      * @@param l_lngBranchId - 部店ID<BR>
      * @@param l_strCommissionProductCode - 手数料商品コード<BR>
      * ５０：先物<BR>
      * ５１：株価指数ＯＰ<BR>
      * @@param l_blnIsLimitPrice - (is指値)<BR>
      *
      * 指値注文の場合はtrue、成行注文の場合はfalse。<BR>
      * @@param l_ifoTradedProduct - 先物OP取引銘柄オブジェクト
      * @@return double
      * @@throws WEB3BaseEception 拘束売買代金の計算に失敗した場合
      */
     public double calcRestraintTurnOver(
         double l_dblQuantity,
         double l_dblUnitPrice,
         long l_lngBranchId,
         String l_strCommissionProductCode,
         boolean l_blnIsLimitPrice,
		WEB3IfoTradedProductImpl l_ifoTradedProduct)
         throws WEB3BaseException
     {
         String l_errorMessage1 = "会社部店商品テーブルに該当するデータがありません。";
         long l_lngRestraintTurnover;
         double l_dbRestraintTurnover = 0.0;
         InstBranchProductRow l_row = null;
         BigDecimal l_bdUnitPrice;
         BigDecimal l_bdQuantity;
         BigDecimal l_bdPremiumRestraintRate;
		 BigDecimal l_bdRestraintTurnover;
		 BigDecimal l_bdUnitSize;
         int l_bdEstimatePriceCalcForm;
         final String STR_METHOD_NAME = "calcRestraintTurnover()";
         log.entering(STR_METHOD_NAME);

         if(l_dblQuantity < 0 || l_dblUnitPrice < 0)
         {
             log.debug("株数<0 || 計算単価<0");
             throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                  STR_METHOD_NAME);
         }
         // 会社部店商品テーブルを読み込む
         try
         {
             l_row =
                 InstBranchProductDao.findRowByPk(
                     l_lngBranchId,
                     l_strCommissionProductCode);
         }
         catch (DataException exp2)
         {
             // DBアクセスエラー。
             log.debug("DBアクセスエラー.");
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 STR_METHOD_NAME,
                 exp2.getMessage(),
                 exp2);
         }
         if (l_row == null)
         {
             // 該当するデータが無い。
             log.debug("該当するデータが無い。");
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 STR_METHOD_NAME,
                 l_errorMessage1);
         }

         if (!l_blnIsLimitPrice)
         {
             // 執行条件が成行の場合
             log.debug("執行条件が成行の場合:");
             l_bdUnitPrice = new BigDecimal(l_dblUnitPrice + "");
             l_bdQuantity = new BigDecimal(l_dblQuantity + "");
             l_bdPremiumRestraintRate =
                 new BigDecimal(l_row.getPremiumRestraintRate() + "");
             l_bdEstimatePriceCalcForm = l_row.getEstimatePriceCalcForm();
             if (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_bdEstimatePriceCalcForm)
             {
                 // 概算金額計算方式がSTOP高方式の場合
                 // 拘束売買代金 ＝ 計算単価 × 注文株数
                 log.debug("概算金額計算方式がSTOP高方式の場合: 拘束売買代金 ＝ 計算単価 × 注文株数");
				l_bdRestraintTurnover =
                     l_bdUnitPrice
                         .multiply(l_bdQuantity);
             }
             else
             {
                 //概算金額計算方式が割増拘束方式の場合
                 // 拘束売買代金 ＝ 計算単価 × 注文株数 × 割増拘束率
                 log.debug("概算金額計算方式が割増拘束方式の場合: 拘束売買代金 ＝ 計算単価 × 注文株数 × 割増拘束率");
				l_bdRestraintTurnover =
                     l_bdUnitPrice
                         .multiply(l_bdQuantity)
                         .multiply(l_bdPremiumRestraintRate);
             }
             log.debug("計算単価：[" + l_dblUnitPrice + "]");
             log.debug("注文株数：[" + l_dblQuantity + "]");
             log.debug("割増拘束率：[" + l_row.getPremiumRestraintRate() + "]");
         }
         else
         {
             // 執行条件が指値の場合
             // 拘束売買代金 ＝ 計算単価 × 注文株数
             log.debug("執行条件が指値の場合: 拘束売買代金 ＝ 計算単価 × 注文株数");
             l_bdUnitPrice = new BigDecimal(l_dblUnitPrice + "");
             l_bdQuantity = new BigDecimal(l_dblQuantity + "");
			 l_bdRestraintTurnover = l_bdUnitPrice.multiply(l_bdQuantity);
             log.debug("計算単価：[" + l_dblUnitPrice + "]");
             log.debug("注文株数：[" + l_dblQuantity + "]");
         }

		//(*1) 先物OP取引銘柄.1単位当たり乗数
		IfoTradedProductRow l_IfoTradedProductRow =
			(IfoTradedProductRow)l_ifoTradedProduct.getDataSourceObject();
		l_bdUnitSize = new BigDecimal(l_IfoTradedProductRow.getUnitSize() + "");
         // 小数点以下切り捨て
        l_dbRestraintTurnover =l_bdRestraintTurnover.multiply(l_bdUnitSize).setScale(
            0, BigDecimal.ROUND_FLOOR).doubleValue();
		log.debug("拘束売買代金：[" + l_dbRestraintTurnover + "]");

         log.exiting(STR_METHOD_NAME);
         return l_dbRestraintTurnover;
     }


    /**
     * (calcCommission)<BR>
     * @@param l_orderExecution
     * @@return double
     * @@roseuid 40A3495A00B7
     */
    public double calcCommission(OrderExecution l_orderExecution)
    {
        //ある注文の約定手数料を算出する。
        return 0.0;
    }

    /**
     * (calcSalesTax)<BR>
     * @@param l_dbData
     * @@return double
     * @@roseuid 40A3495903AE
    */
    public double calcSalesTax(double l_dblData)
    {
        //指定のサービス料金から消費税を算出します。
        return 0.0;
    }

    /**
     * (calcCapitalGainTax)<BR>
     * @@param l_subAccount
     * @@param l_taxTypeEnum
     * @@param l_dblData
     * @@return double
     */
    public double calcCapitalGainTax(
        SubAccount l_subAccount,
        TaxTypeEnum l_taxTypeEnum,
        double l_dblData)
    {
        //譲渡益税の計算を行う。
        return 0.0;
    }

    /**
     * (checkTradingPower)<BR>
     * @@param l_subAccount - 補助口座
     * @@param l_orderSpec - 注文内容
     *
     * @@return OrderValidationResult
     * @@roseuid 4010AF2C0227
     */
    public OrderValidationResult checkTradingPower(SubAccount l_subAccount, OrderSpec l_orderSpec)
    {
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    /**
     * (calc約定代金)<BR>
     * @@param pType - プロダクトタイプ
     * @@param pId - プロダクトID
     * @@param price - 約定単価
     * @@param quantity - 約定数量
     * @@param qType - 数量タイプ
     *
     * @@return double - 約定金額
     * @@roseuid 4010AF2C0227
     */
    public double calcExecutionAmount(
        ProductTypeEnum l_productType, 
        long l_lngProductId, 
        double l_dblPrice, 
        double l_dblQuantity, 
        QuantityTypeEnum l_quantityType)
    {
        String STR_METHOD_NAME = "calcExecutionAmount)";
        log.debug(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        
        try
        {
            long l_lngMarketID = l_ifoProductManagerImpl.getProduct(l_lngProductId).getPrimaryMarket().getMarketId();
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoProductManagerImpl.getTradedProduct(l_lngProductId, l_lngMarketID);
            
            log.exiting(STR_METHOD_NAME);

            BigDecimal l_bdPrice = new BigDecimal(l_dblPrice + "");
            BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity + "");
            BigDecimal l_bdUnitSize = new BigDecimal(l_ifoTradedProductImpl.getUnitSize() + "");
            return l_bdPrice.multiply(l_bdQuantity).multiply(l_bdUnitSize).doubleValue();
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
        }
    }
    
    /**
     * (create手数料)<BR>
     *　@概算売買代金計算に使用する手数料オブジェクトを生成し、<BR> 
     *　@引数で指定された注文の注文単位オブジェクトよりプロパティをセットする。<BR> 
     *　@<BR>
     *１）　@OP注文マネージャ.getOrderUnit()で、<BR> 
     *　@　@　@引数の注文単位IDに該当する注文単位オブジェクトを取得する。<BR> 
     *　@<BR>
     *２）　@手数料インスタンスを生成し、<BR>
     *　@　@　@プロパティに以下の通りに注文単位オブジェクトの値をセットする。<BR> 
     *　@　@　@注文チャネル    = 注文単位.初回注文の注文チャネル<BR>
     *　@　@　@証券会社コード   = 注文単位.部店IDから取得される証券会社のコード<BR>
     *　@　@　@部店ID      = 注文単位.部店ID<BR>
     *　@　@　@発注日       = 注文単位.発注日<BR>
     *　@　@　@取引コード(SONAR)  = 注文単位.取引コード(SONAR)<BR>
     *　@　@　@手数料商品コード  = 注文単位.手数料商品コード<BR>
     *　@　@　@弁済区分      = 00（その他）<BR>
     *　@　@　@原注文注文チャネル = 注文単位.初回注文の注文チャネル<BR>
     *　@　@　@原注文手数料No  = 注文単位.初回注文の手数料No<BR>
     *　@　@　@原注文手数料No枝番    = 注文単位.初回注文の手数料No枝番<BR>
     *　@　@　@is指値  　@　@　@　@　@　@　@= 注文単位.isMarketOrder( )==falseの場合はtrue<BR>
     *　@　@　@注文単位オブジェクト.isMarketOrder( )==trueの場合はfalse<BR>
     *　@　@　@原資産銘柄コード   = 注文単位.get銘柄( ).get原資産銘柄コード( )の戻り値<BR>
     *　@　@　@日計り区分  = 注文単位.日計り区分<BR>
     *　@　@　@数量     = 注文単位.注文数量<BR>
     *　@<BR>
     *　@　@　@※以下のプロパティは設定しない。<BR> 
     *　@　@　@手数料No <BR>
     *　@　@　@手数料No枝番 <BR>
     *　@　@　@手数料金額 <BR>
     *　@　@　@諸経費計算用代金 <BR>
     *　@　@　@手数料コースコード <BR>
     *　@　@　@市場コード（SONAR）<BR>
     *　@　@　@手数料乗数<BR>
     *　@<BR>
     *３）生成した手数料オブジェクトのインスタンスを返却する。<BR>      
     * @@param l_lngOrderUnitId - 注文単位Id
     * @@return WEB3GentradeCommission
     * @@roseuid 4010AF2C0227
     */
    public WEB3GentradeCommission createCommission(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createCommission(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCommission l_commission = null;
        try
        {
            //１）　@OP注文マネージャ.getOrderUnit()で、 
            //　@　@　@引数の注文単位IDに該当する注文単位オブジェクトを取得する。 
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager = 
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
            IfoOrderUnitRow l_orderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //２）　@手数料インスタンスを生成し、
            //  プロパティに以下の通りに注文単位オブジェクトの値をセットする。
            l_commission = new WEB3GentradeCommission();
 
            // 注文チャネル    = 注文単位.初回注文の注文チャネル
            l_commission.setOrderChannel(l_orderUnitRow.getOrderChanel());

            // 証券会社コード   = 注文単位.部店IDから取得される証券会社のコード
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(l_orderUnitRow.getBranchId());
            l_commission.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());

            // 部店ID      = 注文単位.部店ID
            l_commission.setBranchId(l_orderUnitRow.getBranchId());

            // 発注日       = 注文単位.発注日
            String l_strBizDate = l_orderUnitRow.getBizDate();
            l_commission.setOrderBizDate(new Timestamp(WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd").getTime()));

            // 取引コード(SONAR)  = 注文単位.取引コード(SONAR)
            l_commission.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());

            // 手数料商品コード  = 注文単位.手数料商品コード
            l_commission.setCommissionProductCode(l_orderUnitRow.getCommProductCode());

            // 弁済区分      = 00（その他）
            l_commission.setPayType(WEB3PayTypeDef.OTHER);

            // 原注文注文チャネル = 注文単位.初回注文の注文チャネル
            l_commission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());

            // 原注文手数料No  = 注文単位.初回注文の手数料No
            l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());

            // 原注文手数料No枝番    = 注文単位.初回注文の手数料No枝番
            l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());

            // is指値  　@　@　@　@　@　@　@= 注文単位.isMarketOrder( )==falseの場合はtrue
            if (l_orderUnit.isMarketOrder() == false)
            {
                l_commission.setIsLimitPrice(true);
            }
            else
            {
                l_commission.setIsLimitPrice(false);
            }
            
            // 原資産銘柄コード   = 注文単位.get銘柄( ).get原資産銘柄コード( )の戻り値
            l_commission.setUnderlyingProductCode(
                ((IfoProduct)l_orderUnit.getProduct()).getUnderlyingProductCode());
            
            // 日計り区分  = 注文単位.日計り区分
            l_commission.setDayTradeType(l_orderUnitRow.getDayTradeType());
            
            // 数量     = 注文単位.注文数量
            l_commission.setQuantity(l_orderUnit.getQuantity());
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DBへのアクセスに失敗しました。", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DBへのアクセスに失敗しました。", l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }
    
    /**
     * (create手数料)<BR>
     *　@概算売買代金計算に使用する手数料オブジェクトを生成し、<BR> 
     *　@引数で指定された注文の注文単位オブジェクトよりプロパティをセットする。<BR> 
     *　@<BR>
     *１）　@create手数料（long 注文単位ID）（オーバーロードメソッド）をコールする。<BR> 
     *　@　@　@引数の注文単位IDに該当する注文単位オブジェクトを取得する。<BR> 
     *　@<BR>
     *２）　@１）で生成した手数料オブジェクトのプロパティへ以下の通りに値をセットする。<BR>
     *      手数料.数量= 引数.数量<BR> 
     *　@<BR>
     *３）　@手数料オブジェクトのインスタンスを返却する。<BR>      
     * @@param l_lngOrderUnitId - 注文単位Id
     * @@param l_quantity - 数量
     * @@return WEB3GentradeCommission
     * @@roseuid 4010AF2C0227
     */
    public WEB3GentradeCommission createCommission(long l_lngOrderUnitId, double l_quantity)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createCommission(long,double)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCommission l_commission = null;
        //１）　@create手数料（long 注文単位ID）（オーバーロードメソッド）をコールする。
        l_commission = this.createCommission(l_lngOrderUnitId);
        
        //２）　@１）で生成した手数料オブジェクトのプロパティへ以下の通りに値をセットする。
        // 手数料.数量= 引数.数量
        l_commission.setQuantity(l_quantity);
        
        log.exiting(STR_METHOD_NAME);
        return l_commission;
    }

    /**
     * (calc手数料（代金按分）)<BR>
     * 一口約定の委託手数料・消費税を約定代金の割合から計算し、 <BR>
     * その内容を表すConsolidatedCommissionInfoオブジェクトを返す。 <BR>
     * <BR>
     * 1) 引数.取引勘定明細Params[]の中から、以下の条件で最大約定を決定する。 <BR>
     * <BR>
     * 　@　@①@約定代金が最高額の約定を最大約定とする。 <BR>
     * 　@　@②約定代金が同額の場合、約定単価が高い約定を最大約定とする。 <BR>
     * 　@　@③約定単価が同額の場合、<BR>
     * 　@　@　@　@約定テーブル.約定順番号が大きい約定を最大約定とする。 <BR>
     * <BR>
     * 2) 1)で決定した最大約定以外の要素について、(分割)委託手数料、<BR>
     * (分割)消費税を計算する。 <BR>
     * <BR>
     * 　@　@2)-1 (分割)委託手数料は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)委託手数料 = <BR>
     * 　@　@　@　@　@　@(合計)委託手数料(*1) * (分割)約定代金(*3) / (合計)約定代金(*4) <BR>
     * 　@　@　@　@※小数点以下切捨て <BR>
     * <BR>
     * 　@　@2)-2 (分割)消費税は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)消費税 = <BR>
     * 　@　@　@　@　@　@(合計)消費税(*2) * (分割)約定代金(*3) / (合計)約定代金(*4) <BR>
     * 　@　@　@　@※小数点以下切捨て <BR>
     * <BR>
     * 　@　@2)-3 ConsolidatedCommisionInfoへ計算結果を設定する(*5) <BR>
     * <BR>
     * 3) 最大約定の要素について、(分割)委託手数料、(分割)消費税を計算する。 <BR>
     * <BR>
     * 　@　@3)-1 (分割)委託手数料は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)委託手数料 = (合計)委託手数料 - 2)で<BR>
     * 　@　@　@　@　@　@算出した(分割)委託手数料合計値 <BR>
     * <BR>
     * 　@　@3)-2 (分割)消費税は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)消費税 = (合計)消費税 - 2)で算出した(分割)消費税合計値 <BR>
     * <BR>
     * 　@　@3)-3 ConsolidatedCommisionInfoへ計算結果を設定する(*5) <BR>
     * <BR>
     * (*1) 引数.手数料.get手数料金額() <BR>
     * (*2) 引数.手数料.get消費税() <BR>
     * (*3) 引数.(分割)約定代金[]の要素番号は、<BR>
     * 　@　@引数.取引勘定明細Params[]の要素番号と対応させる。 <BR>
     * (*4) 引数.手数料.get諸経費計算用代金() <BR>
     * (*5) ConsolidatedCommisionInfoの要素番号は、<BR>
     * 　@　@引数.取引勘定明細Params[]の要素番号と対応させる。<BR>
     * @@param l_arrIfoFinTransactionRows - (取引勘定明細Params)<BR>
     * トランザクション（取引勘定明細）行オブジェクトの配列<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料<BR>
     * @@param l_dblAmounts - ([分割]約定代金)<BR>
     * [分割]約定代金<BR>
     * @@return ConsolidatedCommissionInfo
     * @@throws WEB3BaseException
     */
    public ConsolidatedCommissionInfo calcCommissionAmount(
        IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
        WEB3GentradeCommission l_commission,
        double[] l_dblAmounts) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCommissionAmount(IfoFinTransactionRow[], WEB3GentradeCommission, double[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoOrderManager l_orderMgr = (IfoOrderManager)l_tm.getOrderManager();

        //引数.取引勘定明細Params[]の中から、以下の条件で最大約定を決定する
        //約定代金が最高額の約定を最大約定とする
        //約定代金が同額の場合、約定単価が高い約定を最大約定とする
        //約定単価が同額の場合、約定テーブル.約定順番号が大きい約定を最大約定とする
        int l_intLength = l_arrIfoFinTransactionRows.length;
        int l_intMax = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            IfoFinTransactionRow l_row = l_arrIfoFinTransactionRows[i];
            if (i != 0)
            {
                BigDecimal l_bdAmount = new BigDecimal(l_dblAmounts[i] + "");
                BigDecimal l_bdMaxAmount = new BigDecimal(l_dblAmounts[l_intMax] + "");
                int l_intResult = l_bdAmount.compareTo(l_bdMaxAmount);

                if (l_intResult == 1)
                {
                    // 約定金額が最大約定金額より大きい場合
                    l_intMax = i;
                }
                else if (l_intResult == 0)
                {
                    IfoFinTransactionRow l_maxRow = l_arrIfoFinTransactionRows[l_intMax];
                    if (l_row.getPrice() > l_maxRow.getPrice())
                    {
                        // 約定金額が同額で、約定単価が高い場合
                        l_intMax = i;
                    }
                    else if (l_row.getPrice() == l_maxRow.getPrice())
                    {
                        IfoOrderExecution l_orderExec = null;
                        IfoOrderExecution l_orderExecMax = null;
                        try
                        {
                            l_orderExec =
                                (IfoOrderExecution)l_orderMgr.getOrderExecution(
                                    l_row.getOrderExecutionId());
                            l_orderExecMax =
                                (IfoOrderExecution) l_orderMgr.getOrderExecution(
                                    l_maxRow.getOrderExecutionId());
                            if (l_orderExec.getExecutionSerialNo()
                                > l_orderExecMax.getExecutionSerialNo())
                            {
                                // 約定単価が同額で、約定順番号が大きい場合
                                l_intMax = i;
                            }
                        }
                        catch (NotFoundException l_exp)
                        {
                            String l_strMessage = "オプション約定データが見つかりません。";
                            log.error(l_strMessage, l_exp);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                l_exp.getMessage());
                        }
                    }
                }
            }
        }

        // 分割(n)の委託手数料と消費税、委託手数料の総和、消費税の総和の計算
        // 最大約定は処理をスキップ
        double[] l_dbCommissions = new double[l_intLength];
        double[] l_dbSalesTaxs = new double[l_intLength];
        BigDecimal l_bdSigmaCommission = new BigDecimal(0);
        BigDecimal l_bdSigmaSalesTax = new BigDecimal(0);
        BigDecimal l_bdCommission;
        BigDecimal l_bdSalesTax;
        BigDecimal l_bdTotalCommission = new BigDecimal(l_commission.getCommission() + "");
        BigDecimal l_bdTotalAmount = new BigDecimal(l_commission.getExpensesCalcAmount() + "");
        BigDecimal l_bdTotalSalesTax = new BigDecimal(l_commission.getConsumptionTax() + "");
        int l_scale = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            if (i != l_intMax)
            {
                BigDecimal l_bdAmount = new BigDecimal(l_dblAmounts[i] + "");
                l_bdCommission =
                    (l_bdTotalCommission.multiply(l_bdAmount)).divide(
                        l_bdTotalAmount,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbCommissions[i] = l_bdCommission.longValue();
                log.debug("［分割(" + i + ")］委託手数料：" + l_dbCommissions[i]);
                l_bdSalesTax =
                    (l_bdTotalSalesTax.multiply(l_bdAmount)).divide(
                        l_bdTotalAmount,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbSalesTaxs[i] = l_bdSalesTax.longValue();
                log.debug("［分割(" + i + ")］消費税：" + l_dbSalesTaxs[i]);
                l_bdSigmaCommission =
                    l_bdSigmaCommission.add(new BigDecimal(l_dbCommissions[i] + ""));
                l_bdSigmaSalesTax =
                    l_bdSigmaSalesTax.add(new BigDecimal(l_dbSalesTaxs[i] + ""));
            }
        }

        // 委託手数料、消費税の端数を計算し、最大約定の委託手数料、消費税に寄せる
        l_bdCommission = l_bdTotalCommission.subtract(l_bdSigmaCommission);
        l_dbCommissions[l_intMax] = l_bdCommission.doubleValue();
        log.debug(
            "［分割(" + l_intMax + ")］委託手数料：" + l_dbCommissions[l_intMax]);
        l_bdSalesTax = l_bdTotalSalesTax.subtract(l_bdSigmaSalesTax);
        l_dbSalesTaxs[l_intMax] = l_bdSalesTax.doubleValue();

        log.exiting(STR_METHOD_NAME);
        return new ConsolidatedCommissionInfo(
            l_dbCommissions,
            l_bdTotalCommission.doubleValue(),
            l_dbSalesTaxs,
            l_bdTotalSalesTax.doubleValue());
    }

    /**
     * (calc手数料（枚数按分）)<BR>
     * 一口約定の委託手数料・消費税を約定数量の割合から計算し、 <BR>
     * その内容を表すConsolidatedCommissionInfoオブジェクトを返す。 <BR>
     * <BR>
     * 1) 引数.取引勘定明細Params[]の中から、以下の条件で最大約定を決定する。 <BR>
     * <BR>
     * 　@　@①@約定テーブル.約定順番号が大きい約定を最大約定とする。 <BR>
     * <BR>
     * 2) 1)で決定した最大約定以外の要素について、<BR>
     * (分割)委託手数料、(分割)消費税を計算する。 <BR>
     * <BR>
     * 　@　@2)-1 (分割)委託手数料は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)委託手数料 = <BR>
     * 　@　@　@　@　@　@(合計)委託手数料(*1) * (分割)約定数量(*3) / (合計)約定数量(*4)<BR>
     * 　@　@　@　@※小数点以下切捨て <BR>
     * <BR>
     * 　@　@2)-2 (分割)消費税は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)消費税 = <BR>
     * 　@　@　@　@　@　@(合計)消費税(*2) * (分割)約定数量(*3) / (合計)約定数量(*4) <BR>
     * 　@　@　@　@※小数点以下切捨て <BR>
     * <BR>
     * 　@　@2)-3 ConsolidatedCommisionInfoへ計算結果を設定する(*5) <BR>
     * <BR>
     * 3) 最大約定の要素について、(分割)委託手数料、(分割)消費税を計算する。 <BR>
     * <BR>
     * 　@　@3)-1 (分割)委託手数料は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)委託手数料 = (合計)委託手数料 - 2)で<BR>
     * 　@　@　@　@　@　@算出した(分割)委託手数料合計値 <BR>
     * <BR>
     * 　@　@3)-2 (分割)消費税は以下のとおりに算出する。 <BR>
     * <BR>
     * 　@　@　@　@(分割)消費税 = (合計)消費税 - 2)で算出した(分割)消費税合計値 <BR>
     * <BR>
     * 　@　@3)-3 ConsolidatedCommisionInfoへ計算結果を設定する(*5) <BR>
     * <BR>
     * (*1) 引数.手数料.get手数料金額() <BR>
     * (*2) 引数.手数料.get消費税() <BR>
     * (*3) 引数.(分割)約定数量[]の要素番号は、<BR>
     * 引数.取引勘定明細Params[]の要素番号と対応させる。 <BR>
     * (*4) 引数.手数料.get数量() <BR>
     * (*5) ConsolidatedCommisionInfoの要素番号は、<BR>
     * 引数.取引勘定明細Params[]の要素番号と対応させる。<BR>
     * @@param l_arrIfoFinTransactionRows - (取引勘定明細Params)<BR>
     * トランザクション（取引勘定明細）行オブジェクトの配列<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料<BR>
     * @@param l_dblQuantitys - ([分割]約定数量)<BR>
     * [分割]約定数量<BR>
     * @@return ConsolidatedCommissionInfo
     * @@throws WEB3BaseException
     */
    public ConsolidatedCommissionInfo calcCommissionQuantity(
        IfoFinTransactionRow[] l_arrIfoFinTransactionRows,
        WEB3GentradeCommission l_commission,
        double[] l_dblQuantitys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcCommissionAmount(IfoFinTransactionRow[], WEB3GentradeCommission, double[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        IfoOrderManager l_orderMgr = (IfoOrderManager)l_tm.getOrderManager();

        //引数.取引勘定明細Params[]の中から、以下の条件で最大約定を決定する
        //約定テーブル.約定順番号が大きい約定を最大約定とする
        int l_intLength = l_arrIfoFinTransactionRows.length;
        int l_intMax = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            IfoFinTransactionRow l_row = l_arrIfoFinTransactionRows[i];
            if (i != 0)
            {

                IfoFinTransactionRow l_maxRow = l_arrIfoFinTransactionRows[l_intMax];
                IfoOrderExecution l_orderExec = null;
                IfoOrderExecution l_orderExecMax = null;
                try
                {
                    l_orderExec =
                        (IfoOrderExecution)l_orderMgr.getOrderExecution(
                            l_row.getOrderExecutionId());
                    l_orderExecMax =
                        (IfoOrderExecution) l_orderMgr.getOrderExecution(
                            l_maxRow.getOrderExecutionId());
                    if (l_orderExec.getExecutionSerialNo()
                        > l_orderExecMax.getExecutionSerialNo())
                    {
                        //約定数量が同数の場合、約定テーブル.約定順番号が大きい約定を最大約定とする
                        l_intMax = i;
                    }
                }
                catch (NotFoundException l_exp)
                {
                    String l_strMessage = "オプション約定データが見つかりません。";
                    log.error(l_strMessage, l_exp);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        l_exp.getMessage());
                }
            }
        }

        // 分割(n)の委託手数料と消費税、委託手数料の総和、消費税の総和の計算
        // 最大約定は処理をスキップ
        double[] l_dbCommissions = new double[l_intLength];
        double[] l_dbSalesTaxs = new double[l_intLength];
        BigDecimal l_bdSigmaCommission = new BigDecimal(0);
        BigDecimal l_bdSigmaSalesTax = new BigDecimal(0);
        BigDecimal l_bdCommission;
        BigDecimal l_bdSalesTax;
        BigDecimal l_bdTotalCommission = new BigDecimal(l_commission.getCommission() + "");
        BigDecimal l_bdTotalQuantity = new BigDecimal(l_commission.getQuantity() + "");
        BigDecimal l_bdTotalSalesTax = new BigDecimal(l_commission.getConsumptionTax() + "");
        int l_scale = 0;
        for (int i = 0; i < l_intLength; i++)
        {
            if (i != l_intMax)
            {
                BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantitys[i] + "");
                l_bdCommission =
                    (l_bdTotalCommission.multiply(l_bdQuantity)).divide(
                        l_bdTotalQuantity,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbCommissions[i] = l_bdCommission.longValue();
                log.debug("［分割(" + i + ")］委託手数料：" + l_dbCommissions[i]);
                l_bdSalesTax =
                    (l_bdTotalSalesTax.multiply(l_bdQuantity)).divide(
                        l_bdTotalQuantity,
                        l_scale,
                        BigDecimal.ROUND_DOWN);
                l_dbSalesTaxs[i] = l_bdSalesTax.longValue();
                log.debug("［分割(" + i + ")］消費税：" + l_dbSalesTaxs[i]);
                l_bdSigmaCommission =
                    l_bdSigmaCommission.add(new BigDecimal(l_dbCommissions[i] + ""));
                l_bdSigmaSalesTax =
                    l_bdSigmaSalesTax.add(new BigDecimal(l_dbSalesTaxs[i] + ""));
            }
        }

        // 委託手数料、消費税の端数を計算し、最大約定の委託手数料、消費税に寄せる
        l_bdCommission = l_bdTotalCommission.subtract(l_bdSigmaCommission);
        l_dbCommissions[l_intMax] = l_bdCommission.doubleValue();
        log.debug(
            "［分割(" + l_intMax + ")］委託手数料：" + l_dbCommissions[l_intMax]);
        l_bdSalesTax = l_bdTotalSalesTax.subtract(l_bdSigmaSalesTax);
        l_dbSalesTaxs[l_intMax] = l_bdSalesTax.doubleValue();

        log.exiting(STR_METHOD_NAME);
        return new ConsolidatedCommissionInfo(
            l_dbCommissions,
            l_bdTotalCommission.doubleValue(),
            l_dbSalesTaxs,
            l_bdTotalSalesTax.doubleValue());
    }
}
@
