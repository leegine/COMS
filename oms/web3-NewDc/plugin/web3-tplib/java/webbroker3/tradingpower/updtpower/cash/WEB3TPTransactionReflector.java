head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 堀野 和美(FLJ) 新規作成
                   2006/09/12 徐宏偉 (中訊) モデルNo.020
                   2006/09/12 徐宏偉 (中訊) モデルNo.058
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (取引情報)<BR>
 *
 * 取引情報変動を表現する。<BR>
 * 以下の情報源から余力更新時<BR>
 * 必要な情報を当クラスにロードする。<BR>
 * 未約定の注文を仮想の取引として扱う。<BR>
 * <BR>
 * -（当日）現注文内容<BR>
 * -当日以降注文<BR>
 * -当日より前の客勘反映済トランザクション<BR>
 * <BR>
 */
public class WEB3TPTransactionReflector
    extends WEB3TPAssetReflector
{
    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPTransactionReflector.class);
        
    /**
     * (銘柄タイプ)<BR>
     */
    private ProductTypeEnum productType;

    /**
     * (銘柄ID)<BR>
     */
    private long productId;

    /**
     * (トランザクションタイプ)<BR>
     */
    private FinTransactionType finTransactionType;

    /**
     * (受渡日)<BR>
     */
    private Date deliveryDate;


    /**
     * (トランザクション発生日)<BR>
     */
    private Date finTransactionDate;

    /**
     * (未約定数量)<BR>
     */
    private double unexecutedQuantity;

    /**
     * (未約定代金)<BR>
     */
    private double unexecutedAmount;

    /**
     * (約定済数量)<BR>
     */
    private double executedQuantity;

    /**
     * (約定済代金)<BR>
     */
    private double executedAmount;
    
    /**
     * (税区分) <BR>
     */
    private TaxTypeEnum taxType;

    /**
     * 取引代金の＋－符号を取引タイプごとに持つMap<BR>
     */
    private static Map cashDirMap;
    
    /**
     * (プラス)
     */    
    private static final Integer PLUS = new Integer(1);
    
    /**
     * (マイナス)
     */    
    private static final Integer MINUS = new Integer( -1);

    static
    {
        cashDirMap = new HashMap();
        cashDirMap.put(FinTransactionType.EQTYPE_EQUITY_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_EQUITY_SELL, PLUS);
        //信用返済は+-が既に入っているのでplusにする
        cashDirMap.put(FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG, PLUS);
        //信用返済は+-が既に入っているのでplusにする
        cashDirMap.put(FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT, PLUS);
        //現引は-が既に入っているのでplusにする
        cashDirMap.put(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_OPEN, MINUS);
        
        //障害対応 Y00109
        //cashDirMap.put(FinTransactionType.EQTYPE_IDX_OPTIONS_BUY_TO_CLOSE, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_IDX_OPTIONS_SELL_TO_CLOSE, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MF_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MF_RECRUIT, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MF_SELL, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MINI_STOCK_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_MINI_STOCK_SELL, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_RUITO_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_RUITO_SELL, PLUS);
        cashDirMap.put(FinTransactionType.EQTYPE_FEQ_BUY, MINUS);
        cashDirMap.put(FinTransactionType.EQTYPE_FEQ_SELL, PLUS);
        cashDirMap.put(FinTransactionType.CREDIT, PLUS);
        cashDirMap.put(FinTransactionType.DEBIT, MINUS);
        cashDirMap.put(FinTransactionType.OTHER, MINUS);
        
        //モデル 058
        //[a.引数.トランザクションタイプ == 401：債券買付] 
        //   返却値：-1 
        cashDirMap.put(FinTransactionType.EQTYPE_BOND_BUY, MINUS);
        
        //[a.引数.トランザクションタイプ == 402：債券売却] 
        //   返却値：1 
        cashDirMap.put(FinTransactionType.EQTYPE_BOND_SELL, PLUS);
        
        //振替は、注文のAssetTransferTypeを見るように変更
        //そのためこのマップにエントリーしない
        
        //抽出対象となる
		//      注文種別：
		//		1007：振替注文(預り金から株先証拠金)
		//		1008：振替注文(株先証拠金から預り金)
		//		1011：為替保証金振替注文（預り金から為替保証金）　@←追加
		//		1012：為替保証金振替注文（為替保証金から預り金）　@←追加
		//		1013：外国株式振替注文（預り金から外国株式口座）　@←追加
		//		1014：外国株式振替注文（外国株式口座から預り金）　@←追加
		//		1017：その他振替注文（預り金からX）　@　@　@　@　@　@　@←追加
		//		1018：その他振替注文（Xから預り金）　@　@　@　@　@　@　@←追加        
                
    };    

    /**
     * @@roseuid 41048CF901A4
     */
    public WEB3TPTransactionReflector()
    {
    }

    /**
     * (static)(create取引情報)<BR> 
     *<BR>
     * 取引情報を作成し、返却する。 <BR>
     *<BR>
     * 1)取引情報インスタンス(="取引情報")を生成する。 <BR>
     *　@-デフォルトコンストラクタをコール <BR>
     *<BR>
     * 2)生成した取引情報インスタンスの属性に値をセット <BR>
     *<BR>
     *　@－"取引情報".set余力計算条件(:余力計算条件 = 引数.余力計算条件)<BR> 
     *　@－"取引情報".set銘柄タイプ(:ProductionType = 引数.銘柄タイプ) <BR>
     *　@－"取引情報".set銘柄ID(:long = 引数.銘柄ID) <BR>
     *　@－"取引情報".setトランザクションタイプ(:FinTransactionType = 引数.トランザクションタイプ) <BR>
     *　@－"取引情報".setトランザクション発生日(:Date = 引数.トランザクション発生日) <BR>
     *　@－"取引情報".set受渡日(:Date = 引数.受渡日) <BR>
     *　@－"取引情報".set未約定数量(:double = 引数.未約定数量) <BR>
     *　@－"取引情報".set未約定代金(:double = 引数.未約定代金) <BR>
     *　@－"取引情報".set約定済数量(:double = 引数.約定済数量) <BR>
     *　@－"取引情報".set約定済代金(:double = 引数.約定済代金) <BR>
     *　@－"取引情報".calc変動反映日(:Date = 引数.受渡日) <BR>
     *　@－"取引情報".set税区分(:TaxTypeEnum = 引数.税区分) <BR>
     *<BR>
     * 3)取引情報インスタンスを返却する。 <BR>
     * <BR>
     * @@param l_calcCondition - (余力計算条件) 
     * @@param l_productType- (銘柄タイプ) 
     * @@param l_lngProductId - (銘柄ID) 
     * @@param l_finTransactionType - (トランザクションタイプ)
     * @@param l_datFinTransactionDate - (トランザクション発生日) 
     * @@param l_dblUnexecutedQuantity - (未約定数量) 
     * @@param l_dblUnexecutedAmount - (未約定代金)
     * @@param l_dblExecutedQuantity - (約定済数量)
     * @@param l_dblExecutedAmount - (約定済代金) 
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_taxType - (税区分)
     * @@return　@WEB3TPTransactionReflector
     */
    public static WEB3TPTransactionReflector create(
        WEB3TPCalcCondition l_calcCondition,
        ProductTypeEnum l_productType, 
        long l_lngProductId,
        FinTransactionType l_finTransactionType,
        Date l_datFinTransactionDate,
        double l_dblUnexecutedQuantity, 
        double l_dblUnexecutedAmount,
        double l_dblExecutedQuantity, 
        double l_dblExecutedAmount,
        Date l_datDeliveryDate,
        TaxTypeEnum l_taxType)
    {
        //1)取引情報インスタンス(="取引情報")を生成する。 
        //-デフォルトコンストラクタをコール 
        WEB3TPTransactionReflector l_instance = new WEB3TPTransactionReflector();
        
        //2)生成した取引情報インスタンスの属性に値をセット 
        //
        //　@－"取引情報".set余力計算条件(:余力計算条件 = 引数.余力計算条件) 
        l_instance.setCalcCondition(l_calcCondition);

        //　@－"取引情報".set銘柄タイプ(:ProductionType = 引数.銘柄タイプ) 
        l_instance.setProductType(l_productType);
        
        //　@－"取引情報".set銘柄ID(:long = 引数.銘柄ID) 
        l_instance.setProductId(l_lngProductId);
        
        //　@－"取引情報".setトランザクションタイプ(:FinTransactionType = 引数.トランザクションタイプ) 
        l_instance.setFinTransactionType(l_finTransactionType);

        //　@－"取引情報".setトランザクション発生日(:Date = 引数.トランザクション発生日) 
        l_instance.setFinTransactionDate(l_datFinTransactionDate);

        //　@－"取引情報".set受渡日(:Date = 引数.受渡日) 
        l_instance.setDeliveryDate(l_datDeliveryDate);

        //　@－"取引情報".set未約定数量(:double = 引数.未約定数量) 
        l_instance.setUnexecutedQuantity(l_dblUnexecutedQuantity);

        //　@－"取引情報".set未約定代金(:double = 引数.未約定代金) 
        l_instance.setUnexecutedAmount(l_dblUnexecutedAmount);

        //　@－"取引情報".set約定済数量(:double = 引数.約定済数量) 
        l_instance.setExecutedQuantity(l_dblExecutedQuantity);

        //　@－"取引情報".set約定済代金(:double = 引数.約定済代金) 
        l_instance.setExecutedAmount(l_dblExecutedAmount);

        //　@－"取引情報".calc変動反映日(:Date = 引数.受渡日) 
        l_instance.calcReflectDay(l_datDeliveryDate);

        //－"取引情報".set税区分(:TaxTypeEnum = 引数.税区分) 
        l_instance.setTaxType(l_taxType);

        //3)取引情報インスタンスを返却する。 
        return l_instance;

    }

    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを返す。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 40C5A2EB0241
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set銘柄タイプ)<BR>
     * 引数を銘柄タイプにセットする。<BR>
     * @@param l_productType - (銘柄タイプ)
     * @@roseuid 40C5A31A0389
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get銘柄ID)<BR>
     * 銘柄IDを返す。<BR>
     * @@return long
     * @@roseuid 40D9724E01CB
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * (set銘柄ID)<BR>
     * 引数を銘柄IDにセットする。<BR>
     * @@param l_lngProductId - (銘柄ID)
     * @@roseuid 40D9727703CE
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }
    
    /**
     * (getトランザクションタイプ)<BR>
     * トランザクションタイプを返す。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType
     * @@roseuid 40C5A2EF00F9
     */
    public FinTransactionType getFinTransactionType()
    {
        return finTransactionType;
    }

    /**
     * (setトランザクションタイプ)<BR>
     * 引数をトランザクションタイプにセットする。<BR>
     * @@param l_finTransactionType - (トランザクションタイプ)
     * @@roseuid 40C5A3210270
     */
    public void setFinTransactionType(FinTransactionType l_finTransactionType)
    {
        finTransactionType = l_finTransactionType;
    }

    /**
     * (getトランザクション発生日)<BR>
     * トランザクションタイプを返す。<BR>
     * @@return Date
     */
    public Date getFinTransactionDate()
    {
        return finTransactionDate;
    }

    /**
     * (setトランザクション発生日)<BR>
     * 引数をトランザクション発生日にセットする。<BR>
     * @@param l_finTransactionDate - (トランザクション発生日)
     */
    public void setFinTransactionDate(Date l_finTransactionDate)
    {
        finTransactionDate = l_finTransactionDate;
    }

    /**
     * (get受渡日)<BR>
     * 受渡日を返す。<BR>
     * @@return Date
     */
    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }

    /**
     * (set受渡日)<BR>
     * 引数を受渡日にセットする。<BR>
     * @@param date (受渡日)
     */
    public void setDeliveryDate(Date date) 
    {
        deliveryDate = date;
    }

        
    /**
     * (get未約定数量)<BR>
     * 未約定数量を返す。<BR>
     * @@return double
     * @@roseuid 40DFC50F00E4
     */
    public double getUnexecutedQuantity()
    {
        return unexecutedQuantity;
    }

    /**
     * (set未約定数量)<BR>
     * 引数を未約定数量にセットする。<BR>
     * @@param l_dblUnexecutedQuantity - (未約定数量)
     * @@roseuid 40DFC536023C
     */
    public void setUnexecutedQuantity(double l_dblUnexecutedQuantity)
    {
        unexecutedQuantity = l_dblUnexecutedQuantity;
    }

    /**
     * (get未約定代金)<BR>
     * 未約定代金を返す。<BR>
     * @@return double
     * @@roseuid 40DBB362021C
     */
    public double getUnexecutedAmount()
    {
        return unexecutedAmount;
    }

    /**
     * (set未約定代金)<BR>
     * 引数を未約定代金にセットする。<BR>
     * @@param l_dblUnexecutedAmount - (未約定代金)
     * @@roseuid 40DBB39C0219
     */
    public void setUnexecutedAmount(double l_dblUnexecutedAmount)
    {
        unexecutedAmount = l_dblUnexecutedAmount;
    }

    /**
     * (get約定済数量)<BR>
     * 約定済数量を返す。<BR>
     * @@return double
     * @@roseuid 40DFC514025B
     */
    public double getExecutedQuantity()
    {
        return executedQuantity;
    }

    /**
     * (set約定済数量)<BR>
     * 引数を約定済数量にセットする。<BR>
     * @@param l_dblExecutedQuantity - (約定済数量)
     * @@roseuid 40DFC53F03C2
     */
    public void setExecutedQuantity(double l_dblExecutedQuantity)
    {
        executedQuantity = l_dblExecutedQuantity;
    }

    /**
     * (get約定済代金)<BR>
     * 約定済代金を返す。<BR>
     * @@return double
     * @@roseuid 40DBB3670180
     */
    public double getExecutedAmount()
    {
        return executedAmount;
    }

    /**
     * (set約定済代金)<BR>
     * 引数を約定済代金にセットする。<BR>
     * @@param l_dblExecutedAmount - (約定済代金)
     * @@roseuid 40DBB3B00312
     */
    public void setExecutedAmount(double l_dblExecutedAmount)
    {
        executedAmount = l_dblExecutedAmount;
    }
    
    /**
     * (get税区分)<BR> 
     *<BR>
     * this.税区分を返却する。<BR>
     */
    public TaxTypeEnum getTaxType()
    {
        return this.taxType;
    }
    
    /**
     * (set税区分)<BR> 
     *<BR>
     * 引数.税区分を、this.税区分にセットする。<BR>
     * @@param l_taxType - (税区分)
     */
    public void setTaxType(TaxTypeEnum l_taxType)
    {
        taxType = l_taxType;
    }
    
    /**
     * (get受渡代金)<BR>
     * 受渡代金＝未約定代金＋約定済代金として値を返す。<BR>
     * @@return double
     */    
    public double getAmount()
    {
        return unexecutedAmount + executedAmount;
    }

    /**
     * (calc変動反映日)<BR>
     * <BR>
     * 変動反映開始日、変動反映終了日をセットする。<BR>
     * <BR>
     * １）変動反映開始日をセットする。<BR>
     * 　@[a.ミニ株、投信、入出金、債券の場合]<BR>
     * 　@（this.トランザクションタイプ = 201：ミニ株買<BR> 
     * 　@　@|| this.トランザクションタイプ = 202：ミニ株売<BR>
     * 　@　@|| this.トランザクションタイプ = 301：投信買<BR>
     * 　@　@|| this.トランザクションタイプ = 302：投信売<BR>
     * 　@　@|| this.トランザクションタイプ = 303：投信応募<BR>
     * 　@　@|| this.トランザクションタイプ = 20：出金<BR>
     * 　@　@|| this.トランザクションタイプ = 10：入金<BR>
     * 　@　@|| this.トランザクションタイプ = 401：債券買<BR>
     * 　@　@|| this.トランザクションタイプ = 402：債券売）<BR>
     * <BR>
     * 　@　@[ｂ.引数.受渡日 > T+5 の場合]<BR>
     * 　@　@　@－this.set変動反映開始日(:Date = Ｔ+5)<BR>
     * <BR>
     * 　@　@[ｂ.以外 の場合]<BR>
     * 　@　@　@－this.set変動反映開始日(:Date = 引数.受渡日)<BR>
     * <BR>
     * 　@[a.以外 の場合]<BR>
     * 　@　@－this.set変動反映開始日(:Date = 引数.受渡日)<BR>
     * <BR>
     * ２）変動反映終了日をセットする。<BR>
     * 　@　@－this.set変動反映終了日(:Date = T+5)<BR>
     * <BR>
     * ※T+5 = this.get余力計算条件().get営業日(:int = 5)<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
    	//余力T+6以降対応

        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //ミニ株、投信、入出金、債券の場合    	
        if(FinTransactionType.EQTYPE_MINI_STOCK_BUY.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MINI_STOCK_SELL.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MF_BUY.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MF_RECRUIT.equals(finTransactionType)
                || FinTransactionType.EQTYPE_MF_SELL.equals(finTransactionType)
                || FinTransactionType.DEBIT.equals(finTransactionType)
                || FinTransactionType.CREDIT.equals(finTransactionType)
                || FinTransactionType.EQTYPE_BOND_BUY.equals(finTransactionType)
                || FinTransactionType.EQTYPE_BOND_SELL.equals(finTransactionType))
        {
            //営業日[5] < 引数.受渡日の場合
            //変動反映開始日＝引数.受渡日
            if(WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) < 0)
            {
                l_datDeliveryDate = l_datT5;
            }
        }

        //上記取引(ミニ株、投信、入出金、債券)以外
        //変動反映開始日＝引数.受渡日
        setReflectStartDay(l_datDeliveryDate);

        setReflectEndDay(l_datT5);

    }

    /**
     * (static)(get損益方向)<BR> 
     * <BR>
     * 引数.トランザクションタイプをKeyとして益の場合1、損の場合-1を返す。<BR> 
     * 既に受渡代金に符号が考慮されているトランザクションタイプの場合は１を返す。<BR> 
     * <BR>
     * １）引数.トランザクションタイプ別に損益方向を返却する。 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 70：現物買取引の場合] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 80：現物売取引] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 110：買建返済取引（売返済）] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 120：売建返済取引（買返済）] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 130：現引取引] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 140：現渡取引] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 605：OP新規買建注文] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 608：OP買建売返済注文（売返済）] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 301：投信買付] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 303：投信乗換] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 302：投信売付] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 201：ミニ株買付] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 202：ミニ株売付] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 501：累投買付] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 502：累投売付] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 701：外株買い] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 702：外株売り] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 10：入金] <BR>
     * 　@　@返却値：1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 20：出金] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 401：債券買付] <BR>
     * 　@　@返却値：-1 <BR>
     * <BR>
     * 　@[a.引数.トランザクションタイプ == 402：債券売却] <BR>
     * 　@　@返却値：1 <BR>
     * @@param l_tranType - (トランザクションタイプ) 
     * @@return int
     */
    protected static int getCashDir(FinTransactionType l_tranType)
    {
        final String STR_METHOD_NAME = 
            "WEB3TPTransactionReflector.getCashDir(FinTransactionType l_tranType)";
        Integer l_intDir = (Integer)cashDirMap.get(l_tranType);
        if (l_intDir == null)
        {
            //想定外の取引種別が渡された場合
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        return l_intDir.intValue();
    }

    /**
     * (get損益方向)<BR>
     * 金額がプラスかマイナスかを返す。<BR>
     * 振替注文用
     * @@return double
     */
    protected static int getCashDir(AssetTransferTypeEnum l_assetTransferType)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionReflector.getCashDir(AssetTransferTypeEnum l_assetTransferType)";
        
        if(AssetTransferTypeEnum.CASH_IN.equals(l_assetTransferType))
            return 1;
        else if(AssetTransferTypeEnum.CASH_OUT.equals(l_assetTransferType))
            return -1;
        
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);        
        
    }
    
    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        final String l_strYYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";
        
        return ToStringUtils
            .newToStringBuilder(this)
            .append("productType", this.getProductType())
            .append("productId", this.getProductId())
            .append("finTransactionType", this.getFinTransactionType())
            .append("deliveryDate", WEB3DateUtility.formatDate(this.getDeliveryDate(), l_strYYYYMMDDHHMMSS))
            .append("finTransactionDate", WEB3DateUtility.formatDate(this.getFinTransactionDate(), l_strYYYYMMDDHHMMSS))
            .append("unexecutedQuantity", this.getUnexecutedQuantity())
            .append("unexecutedAmount", this.getUnexecutedAmount())
            .append("executedQuantity", this.getExecutedQuantity())
            .append("executedAmount", this.getExecutedAmount())
            .append("reflectStartDay", this.getReflectStartDay())
            .append("reflectEndDay", this.getReflectEndDay())
            .append("taxType", this.getTaxType())
            .toString();
    }
}
@
