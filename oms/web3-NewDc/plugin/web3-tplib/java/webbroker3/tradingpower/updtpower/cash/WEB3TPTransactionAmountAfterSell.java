head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionAmountAfterSell.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 売付後取引代金(WEB3TPTransactionAmountAfterSell.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) 新規作成
                    2006/09/15 車進　@  (中訊)   モデルNo.32
 Revesion History : 2010/01/11 武波　@  (中訊)   モデルNo.426,No.427
 */
package webbroker3.tradingpower.updtpower.cash;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSellOrderPriceDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (売付後取引代金)
 * ※取引代金クラスの拡張クラス
 */
public class WEB3TPTransactionAmountAfterSell extends WEB3TPTransactionAmount
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTransactionAmountAfterSell.class);

    /**
     * (注文銘柄ID)
     */
    private long orderProductId;

    /**
     * (デフォルトコンストラクタ)
     */
    public WEB3TPTransactionAmountAfterSell()
    {
        super();
    }

    /**
     * (staticメソッド)(create売付後取引代金)
     * 
     * 売付後取引代金オブジェクトを生成し返却する。
     * 
     * １）売付後取引代金オブジェクトを生成する。
     * 　@-デフォルトコンストラクタをコール
     * 
     * ２）生成した売付後取引代金オブジェクトのプロパティに値をセットする。
     * 
     * 　@○顧客条件をセット
     * 　@　@-売付後取引代金オブジェクト.set顧客属性()をコール
     * 
     * 　@　@[引数]　@
     * 　@　@　@顧客属性：引数.総資金.get顧客属性()の戻り値
     * 　@
     * 　@○計算条件をセット
     * 　@　@-売付後取引代金オブジェクト.set余力計算条件をコール
     * 
     * 　@　@[引数]
     * 　@　@　@余力計算条件：引数.総資金.get余力計算条件の戻り値
     * 
     * 　@○現注文内容をセット
     * 　@　@-売付後取引代金オブジェクト.set現注文内容()をコール
     * 
     * 　@　@[引数]
     * 　@　@　@現注文内容：引数.総資金.get現注文内容の戻り値
     * 
     * 　@○注文銘柄IDをセット
     * 　@　@-売付後取引代金オブジェクトset注文銘柄ID()をコール
     * 
     * 　@　@[引数]
     * 　@　@　@注文銘柄ID：引数.注文銘柄ID
     * 
     * ３）生成した売付後取引代金オブジェクトの返却する。
     * 
     * 　@[返却値]
     * 　@　@生成した売付後取引代金オブジェクト
     * 
     * @@param l_valuation - (総資金)
     * @@param l_orderProductId - (注文銘柄ID)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmountSellOrder
     */
    public static WEB3TPTransactionAmountAfterSell createWEB3TPTransactionAmountAfterSell(
        WEB3TPCashValuation l_valuation,
        long l_orderProductId)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionAmountSellOrder.createWEB3TPTransactionAmountAfterSell(WEB3TPCashValuation, long)";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionAmountAfterSell l_instance = new WEB3TPTransactionAmountAfterSell();

        //顧客条件をセット
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        //計算条件をセット
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        //現注文内容をセット
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        //注文銘柄IDをセット
        l_instance.setOrderProductId(l_orderProductId);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (create取引情報(株式注文:EqtypeOrderUnitRow))<BR>
     * ※スーパークラス(：取引代金).create取引情報(株式注文:EqtypeOrderUnitRow)のオーバーライド<BR>
     * ※現物売付注文の未約定代金計上部分がスーバークラスと異なる。<BR>
     * <BR>
     * シーケンス図「(売付後取引代金)create取引情報(EqtypeOrderUnitRow)」参照 <BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRow
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector[] createWEB3TPTransactionReflector(
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionAmountSellOrder.createWEB3TPTransactionReflector(l_eqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        /*
         * 1.1 部店プリファ@レンステーブルより「未約定売付単価区分」を取得する。
         */
        String l_strInstBran = this.getCalcCondition().getInstBranCalcCondition(
            WEB3TPCalcCondition.SELLORDER_PRICE_DIV);

        //約定済数量
        double l_dblExecutedQuantity = 0;
        //約定済代金
        double l_dblExecutedAmount = 0;
        //未約定数量 
        double l_dblUnexecutedQuantity = 0;
        //未約定代金
        double l_dblUnexecutedAmount = 0;

        //トランザクション発生日
        Date l_tranDate = WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        //受渡日
        Date l_deliveryDate = l_eqtypeOrderUnitRow.getDeliveryDate();
        //トランザクションタイプ
        FinTransactionType l_tranType = l_eqtypeOrderUnitRow.getOrderType().toFinTransactionType();
        //1.2損益方向
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);

        //1.3 [a. 発注中現物売付注文 かつ　@注文銘柄と同銘柄の場合]
        // （引数.株式注文.注文有効状態 ！= 2:CLOSE and 
        // 　@引数.株式注文.注文種別 == 2：現物売注文 and 
        // 　@引数.株式注文.銘柄ID = this.get注文銘柄ID()）
        if(!OrderOpenStatusEnum.CLOSED.equals(l_eqtypeOrderUnitRow.getOrderOpenStatus())
            && FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_tranType)
            && l_eqtypeOrderUnitRow.getProductId() == this.getOrderProductId())
        {
            //1.3.1 [b. 時価評価の場合]
            //(１）の戻り値 == 1:QUOTE_PRICE）
            if(WEB3TPSellOrderPriceDivDef.QUOTE_PRICE.equals(l_strInstBran) == true)
            {
                //“約定済数量” = 引数.株式注文.注文数量
                l_dblExecutedQuantity = l_eqtypeOrderUnitRow.getQuantity();
                //“約定済代金” = 引数.株式注文.概算受渡代金
                l_dblExecutedAmount = l_eqtypeOrderUnitRow.getEstimatedPrice();
                //“未約定数量” = 0
                l_dblUnexecutedQuantity = 0;
                //“未約定代金” = 0
                l_dblUnexecutedAmount = 0;
            }
            //1.3.2  [b. 以外（0円評価）の場合]
            else
            {
                //“約定済数量” = 引数.株式注文.注文数量
                l_dblExecutedQuantity = l_eqtypeOrderUnitRow.getQuantity();
                //“約定済代金” = “受渡代金”
                l_dblExecutedAmount = getNetAmountTotal(l_eqtypeOrderUnitRow);
                //“未約定数量” = 0
                l_dblUnexecutedQuantity = 0;
                //“未約定代金” = 0
                l_dblUnexecutedAmount = 0;
            }
        }
        //1.4 [a. 以外の場合]
        else
        {
            /*
             * ①@“約定済数量”を取得する。
             */
            //“約定済数量” = 引数.株式注文.約定数量
            l_dblExecutedQuantity = l_eqtypeOrderUnitRow.getExecutedQuantity();

            /*
             * ②“未約定数量”を取得する。
             */
            //1.4.1 [b. 引数.株式注文が無効となっている場合]
            //（引数.株式注文.注文有効状態 == 2:クローズ）
            if(OrderOpenStatusEnum.CLOSED.equals(l_eqtypeOrderUnitRow.getOrderOpenStatus()))
            {
                //“未約定数量” = 0
                l_dblUnexecutedQuantity = 0;
            }
            //[b. 以外（引数.株式注文が有効）の場合]
            else
            {
                //“未約定数量” = 引数.株式注文.注文数量 - 引数.株式注文.約定数量
                l_dblUnexecutedQuantity = l_eqtypeOrderUnitRow.getQuantity()
                    - l_eqtypeOrderUnitRow.getExecutedQuantity();
            }

            /*
             * ③“約定済代金”、“未約定代金”を取得する。
             */
            //1.4.2 [b. 全部約定 の場合]
            //（“未約定数量” == 0 ）
            if(l_dblUnexecutedQuantity == 0)
            {
                //“約定済代金” = “受渡代金”
                l_dblExecutedAmount = getNetAmountTotal(l_eqtypeOrderUnitRow);
                //“未約定代金” = 0
                l_dblUnexecutedAmount = 0;
            }
            //1.4.3  [b. 以外（未約定または一部約定） の場合]
            else
            {
                //概算受渡代金
                double l_dblEstimatedPrice = l_eqtypeOrderUnitRow.getEstimatedPrice();

                //[c. 現引注文 又は 現渡注文 の場合]
                //（引数.株式注文.注文種別 == 7：現引注文 or 8：現渡注文）
                if(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_tranType)
                    || FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_tranType))
                {
                    //“約定済代金” = 引数.株式注文.概算受渡代金
                    l_dblExecutedAmount = l_dblEstimatedPrice * l_intCashDir;
                    //“未約定代金” = 0
                    l_dblUnexecutedAmount = 0;
                }
                //[c. ミニ株買付注文 の場合]
                //（引数.株式注文.注文種別 == 101：株式ミニ株買注文）
                else if(FinTransactionType.EQTYPE_MINI_STOCK_BUY.equals(l_tranType))
                {
                    //“約定済代金” = 0
                    l_dblExecutedAmount = 0;
                    //“未約定代金” = 引数.株式注文.概算受渡代金
                    l_dblUnexecutedAmount = l_dblEstimatedPrice * l_intCashDir;
                }
                //[c. 以外（現物買付注文、現物売付注文、信用返済注文、ミニ株売付注文） の場合]
                else
                {
                    //“約定済代金” = “受渡代金”
                    l_dblExecutedAmount = getNetAmountTotal(l_eqtypeOrderUnitRow);

                    //[d. 現物買付注文 の場合]
                    //（引数.株式注文.注文種別 == 1：現物買注文）
                    if(FinTransactionType.EQTYPE_EQUITY_BUY.equals(l_tranType))
                    {
                        //“未約定代金” = (引数.株式注文.概算受渡代金 - 絶対値(“約定済代金”))  × -1
                        l_dblUnexecutedAmount = (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice
                            * l_intCashDir : (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount))
                            * l_intCashDir;
                    }
                    //[d. 以外（注文銘柄以外の現物売付注文、信用返済注文、ミニ株売付注文）の場合]
                    else
                    {
                        //“未約定代金” = 0
                        l_dblUnexecutedAmount = 0;
                    }
                }
            }
        }

        //1.5 [a.今回注文かつ売付注文の場合]
        //(引数.株式注文.注文単位ID == -1 && 引数.株式注文.注文種別 == 2:現物売注文)
        if(l_eqtypeOrderUnitRow.getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID
            && FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_tranType))
        {
            //注文単価
            double l_dblPrice = 0;

            //[時価評価の場合]
            //(１）の戻り値 == 1:QUOTE_PRICE）
            if(WEB3TPSellOrderPriceDivDef.QUOTE_PRICE.equals(l_strInstBran) == true)
            {
                //注文単価 = 引数.株式注文.注文単価
                l_dblPrice = l_eqtypeOrderUnitRow.getPrice();
            }
            //[以外（0円評価）の場合]
            else
            {
                //注文単価 = 0
                l_dblPrice = 0;
            }

            //1.5.1 取引情報オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return new WEB3TPTransactionReflector[]{WEB3TPTransactionReflectorNewSellOrder.createWEB3TPTransactionReflectorNewSellOrder(
                getCalcCondition(),
                l_eqtypeOrderUnitRow.getProductType(),
                l_eqtypeOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_eqtypeOrderUnitRow.getTaxType(),
                l_dblPrice)};
        }
        //1.6 [a.以外（既存注文）の場合]
        //　@-取引情報.create取引情報()をコールする　@
        else
        {
            //1.6.1 取引情報オブジェクトを返却する
            log.exiting(STR_METHOD_NAME);
            return new WEB3TPTransactionReflector[]{WEB3TPTransactionReflector.create(
                getCalcCondition(),
                l_eqtypeOrderUnitRow.getProductType(),
                l_eqtypeOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_eqtypeOrderUnitRow.getTaxType())};
        }
    }

    /**
     * (get注文銘柄ID)
     * 
     * this.注文銘柄IDを返却する。
     * 
     * @@return long
     */
    public long getOrderProductId()
    {
        return this.orderProductId;
    }

    /**
     * (set注文銘柄ID)
     * 
     * 引数.注文銘柄IDをthis.注文銘柄IDにセットする。
     * 
     * @@param l_orderProductId - (注文銘柄ID)
     */
    public void setOrderProductId(long l_orderProductId)
    {
        this.orderProductId = l_orderProductId;
    }

    /**
     * (create取引情報)<BR>
     * (create取引情報(外株注文:FeqOrderUnitRow))<BR>
     * ※スーパークラス(：取引代金).create取引情報(外株注文:EqtypeOrderUnitRow)のオーバーライド<BR>
     * ※外株売付注文の未約定代金計上部分がスーバークラスと異なる。<BR>
     * <BR>
     * シーケンス図「(売付後取引代金)create取引情報(FeqOrderUnitRow)」参照<BR>
     * @@param l_feqOrderUnitRow - (外株注文)<BR>
     * 外株注文<BR>
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(FeqOrderUnitRow l_feqOrderUnitRow)
    {
        final String STR_METHOD_NAME = "createTransactionReflector(FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //get会社部店別余力計算条件
        //String:"sellorder.price.div"
        String l_strInstBran = this.getCalcCondition().getInstBranCalcCondition(
            WEB3TPCalcCondition.SELLORDER_PRICE_DIV);

        //get損益方向(トランザクションタイプ : FinTransactionType)
        //FinTransactionType = 引数.外株注文.get注文種別().toFinTransactionType()の戻り値
        FinTransactionType l_tranType = l_feqOrderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);

        //約定済数量
        double l_dblExecutedQuantity = 0;
        //約定済代金
        double l_dblExecutedAmount = 0;
        //未約定数量 
        double l_dblUnexecutedQuantity = 0;
        //未約定代金
        double l_dblUnexecutedAmount = 0;

        if(OrderOpenStatusEnum.OPEN.equals(l_feqOrderUnitRow.getOrderOpenStatus())
            && FinTransactionType.EQTYPE_FEQ_SELL.equals(l_tranType)
            && l_feqOrderUnitRow.getProductId() == this.getOrderProductId())
        {
            if(WEB3TPSellOrderPriceDivDef.QUOTE_PRICE.equals(l_strInstBran))
            {
                l_dblExecutedQuantity = l_feqOrderUnitRow.getQuantity();
                l_dblExecutedAmount = l_feqOrderUnitRow.getEstimatedPrice();
                l_dblUnexecutedQuantity = 0;
                l_dblUnexecutedAmount = 0;
            }
            else
            {
                l_dblExecutedQuantity = l_feqOrderUnitRow.getQuantity();
                l_dblExecutedAmount = this.getNetAmountTotal(l_feqOrderUnitRow);
                l_dblUnexecutedQuantity = 0;
                l_dblUnexecutedAmount = 0;
            }
        }
        else
        {
            //“約定済数量” = 引数.外株注文.約定数量
            l_dblExecutedQuantity = l_feqOrderUnitRow.getExecutedQuantity();

            //[b. 引数.外株注文が無効となっている場合]
            //（引数.外株注文.注文有効状態 == 2:クローズ）
            if(OrderOpenStatusEnum.CLOSED.equals(l_feqOrderUnitRow.getOrderOpenStatus()))
            {
                //“未約定数量” = 0
                l_dblUnexecutedQuantity = 0;
            }
            //[b. 以外（引数.外株注文が有効）の場合]
            else
            {
                if (l_feqOrderUnitRow.getConfirmedQuantityIsNull())
                {
                    //“未約定数量” = 引数.外株注文.注文数量
                    l_dblUnexecutedQuantity = l_feqOrderUnitRow.getQuantity();
                }
                else
                {
                    //“未約定数量” = 引数.外株注文.市場から確認済みの数量 - 引数.外株注文.約定数量
                    l_dblUnexecutedQuantity =
                        l_feqOrderUnitRow.getConfirmedQuantity()
                        - l_feqOrderUnitRow.getExecutedQuantity();
                }
            }

            //[b. 全部約定 の場合]
            //（“未約定数量” == 0 ）
            if(GtlUtils.Double.isZero(l_dblUnexecutedQuantity))
            {
                //“約定済代金” = get約定済受渡代金合計
                l_dblExecutedAmount = this.getNetAmountTotal(l_feqOrderUnitRow);
                //“未約定代金” = 0
                l_dblUnexecutedAmount = 0;
            }
            //[b. 以外（未約定または一部約定） の場合]
            else
            {
                //“約定済代金” = get約定済受渡代金合計                
                l_dblExecutedAmount = this.getNetAmountTotal(l_feqOrderUnitRow);

                if(FinTransactionType.EQTYPE_FEQ_BUY.equals(l_tranType))
                {
                    if (l_dblUnexecutedQuantity > 0)
                    {
                        BigDecimal l_bdEstimatedPrice = new BigDecimal(
                            Double.toString(l_feqOrderUnitRow.getEstimatedPrice()));
                        BigDecimal l_bdExecutedAmount = new BigDecimal(
                            Double.toString(Math.abs(l_dblExecutedAmount)));
                        l_dblUnexecutedAmount =
                            l_bdEstimatedPrice.subtract(l_bdExecutedAmount).doubleValue() * l_intCashDir;
                    }
                    else
                    {
                        l_dblUnexecutedAmount = 0;
                    }
                }
                else
                {
                    l_dblUnexecutedAmount = 0;
                }
            }
        }

        //トランザクション発生日
        Date l_tranDate = WEB3DateUtility.getDate(
            l_feqOrderUnitRow.getBizDate(),
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //受渡日
        Date l_deliveryDate = l_feqOrderUnitRow.getDeliveryDate();
        //[a.今回注文かつ売付注文の場合]
        //(引数.外株注文.注文単位ID == -1 && 引数.外株注文.注文種別 == 702)
        if(l_feqOrderUnitRow.getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID
            && FinTransactionType.EQTYPE_FEQ_SELL.equals(l_tranType))
        {
            
            //create今回売付注文分取引情報
            //余力計算条件 = this.get余力計算条件()
            //ProductTypeEnum = 引数.外株注文.get銘柄タイプ()
            //long = 引数.外株注文.get銘柄ID()
            //FinTransactionType = 引数.外株注文.get注文種別().toFinTransactionType()
            //Date = 引数.外株注文.get発注日()
            //double = 計算した"未約定数量"
            //double = 計算した"未約定代金"
            //double = 計算した"約定済数量"
            //double = 計算した"約定済代金"
            //Date = 引数.外株注文.get受渡日()
            //TaxTypeEnum = 引数.外株注文.get税区分()
            //double = 0
            log.exiting(STR_METHOD_NAME);
            return WEB3TPTransactionReflectorNewSellOrder.createWEB3TPTransactionReflectorNewSellOrder(
                this.getCalcCondition(),
                l_feqOrderUnitRow.getProductType(),
                l_feqOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_feqOrderUnitRow.getTaxType(),
                0D);
        }
        //[a.以外（既存注文）の場合]
        //　@-取引情報.create取引情報()をコールする　@
        else
        {
            //create取引情報
            //余力計算条件 = this.get余力計算条件()
            //ProductTypeEnum = 引数.外株注文.get銘柄タイプ()
            //long = 引数.外株注文.get銘柄ID()
            //FinTransactionType = 引数.外株注文.get注文種別().toFinTransactionType()
            //Date = 引数.外株注文.get発注日()
            //double = 計算した"未約定数量"
            //double = 計算した"未約定代金"
            //double = 計算した"約定済数量"
            //double = 計算した"約定済代金"
            //Date = 引数.株式注文.get受渡日()
            //TaxTypeEnum = 引数.外株注文.get税区分()
            log.exiting(STR_METHOD_NAME);
            return WEB3TPTransactionReflector.create(
                this.getCalcCondition(),
                l_feqOrderUnitRow.getProductType(),
                l_feqOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_feqOrderUnitRow.getTaxType());
        }
    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.appendSuper(super.toString());
        l_builder.append("orderProductId", this.getOrderProductId());

        return l_builder.toString();
    }
}@
