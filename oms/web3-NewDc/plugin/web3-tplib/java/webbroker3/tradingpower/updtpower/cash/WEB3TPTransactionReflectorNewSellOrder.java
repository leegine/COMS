head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionReflectorNewSellOrder.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 今回売付注文分取引情報(WEB3TPTransactionReflectorNewSellOrder.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) 新規作成
                    2006/09/15 車進　@  (中訊)   モデルNo.31
 */
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (今回売付注文分取引情報)
 * ※取引情報クラスの拡張クラス
 */
public class WEB3TPTransactionReflectorNewSellOrder extends WEB3TPTransactionReflector
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTransactionReflectorNewSellOrder.class);

    /**
     * (注文単価)
     */
    private double price;

    /**
     * (デフォルトコンストラクタ)
     */
    public WEB3TPTransactionReflectorNewSellOrder()
    {
        super();
    }

    /**
     * (staticメソッド)(create今回売付注文分取引情報)<BR>
     * ※今回売付注文分引情報を生成し、返却する。<BR>
     * <BR>
     * １）今回売付注文分取引情報インスタンスを生成する。<BR>
     * 　@-デフォルトコンストラクタをコール<BR>
     * <BR>
     * ２）１）で生成したインスタンスの属性にセットする。<BR>
     * <BR>
     * 　@今回売付注文分取引情報.計算条件 = 引数.計算条件<BR>
     * 　@今回売付注文分取引情報.銘柄タイプ = 引数.銘柄タイプ<BR> 
     * 　@今回売付注文分取引情報.銘柄ID =  引数.銘柄ID<BR>
     * 　@今回売付注文分取引情報.トランザクションタイプ =  引数.トランザクションタイプ<BR>
     * 　@今回売付注文分取引情報.トランザクション発生日 =  引数.トランザクション発生日<BR>
     * 　@今回売付注文分取引情報.受渡日 =  引数.受渡日<BR>
     * 　@今回売付注文分取引情報.未約定数量 =  引数.未約定数量<BR>
     * 　@今回売付注文分取引情報.未約定代金 =  引数.未約定代金<BR>
     * 　@今回売付注文分取引情報.約定数量 =  引数.約定数量<BR>
     * 　@今回売付注文分取引情報.約定代金 =  引数.約定代金<BR>
     * 　@今回売付注文分取引情報.注文単価 =  引数.注文単価<BR>
     * <BR>
     * ３）変動反映開始日/変動反映終了日をセットする。<BR>
     * 　@-今回売付注文分取引情報.calc変動反映日()をコール<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@受渡日：引数.受渡日<BR>
     * <BR>
     * ４）今回売付注文分取引情報インスタンスを返却する。<BR>
     * <BR>
     * @@param l_calcCondition - (計算条件)
     * @@param l_productType - (銘柄タイプ)
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_finTransactionType - (トランザクションタイプ)
     * @@param l_tranDate - (トランザクション発生日)
     * @@param l_dblUnexecutedQuantity - (未約定数量)
     * @@param l_dblUnexecutedAmount - (未約定代金)
     * @@param l_dblExecutedQuantity - (約定済数量)
     * @@param l_dblExecutedAmount - (約定済代金)
     * @@param l_deliveryDate - (受渡日)
     * @@param l_taxType - (税区分)
     * @@param l_dblPrice - (注文単価)
     * @@return 
     * webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorNewSellOrder
     */
    public static WEB3TPTransactionReflectorNewSellOrder createWEB3TPTransactionReflectorNewSellOrder(
        WEB3TPCalcCondition l_calcCondition,
        ProductTypeEnum l_productType,
        long l_lngProductId,
        FinTransactionType l_finTransactionType,
        Date l_finTransactionDate,
        double l_dblUnexecutedQuantity,
        double l_dblUnexecutedAmount,
        double l_dblExecutedQuantity,
        double l_dblExecutedAmount,
        Date l_deliveryDate,
        TaxTypeEnum l_taxType,
        double l_dblPrice)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionReflectorNewSellOrder.createWEB3TPTransactionReflectorNewSellOrder()";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionReflectorNewSellOrder l_instance = new WEB3TPTransactionReflectorNewSellOrder();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setProductType(l_productType);
        l_instance.setProductId(l_lngProductId);
        l_instance.setFinTransactionType(l_finTransactionType);
        l_instance.setFinTransactionDate(l_finTransactionDate);
        l_instance.setUnexecutedQuantity(l_dblUnexecutedQuantity);
        l_instance.setUnexecutedAmount(l_dblUnexecutedAmount);
        l_instance.setExecutedQuantity(l_dblExecutedQuantity);
        l_instance.setExecutedAmount(l_dblExecutedAmount);
        l_instance.setDeliveryDate(l_deliveryDate);
        l_instance.setTaxType(l_taxType);
        l_instance.setPrice(l_dblPrice);

        l_instance.calcReflectDay(l_deliveryDate);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (get注文単価)
     * 
     * this.注文単価を返却する。
     * 
     * @@return double
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * (set注文単価)
     * 
     * 引数.注文単価をthis.注文単価にセットする。
     * 
     * @@param l_dblPrice - (注文単価)
     */
    public void setPrice(double l_dblPrice)
    {
        this.price = l_dblPrice;
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
        l_builder.append("price", this.getPrice());

        return l_builder.toString();
    }
}@
