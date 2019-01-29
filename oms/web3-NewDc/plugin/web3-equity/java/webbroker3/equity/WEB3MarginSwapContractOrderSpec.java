head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapContractOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  /**
                 :  (信用現引現渡注文内容)<BR>
                 :  信用取引・現引現渡注文内容の入力を表現する。<BR>
                 :  注文マネージャに渡すパラメタになる。<BR>(WEB3MarginSwapContractOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSwapContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.util.WEB3LogUtility;

/**
 * （信用現引現渡注文内容）。<BR>
 * <BR>
 * 信用取引・現引現渡注文内容の入力を表現する。<BR>
 * 注文マネージャに渡すパラメタになる。<BR>
 * <BR>
 * xTradeのEqTypeSwapContractOrderSpecを拡張したクラス。
 * @@version 1.0
 */
public class WEB3MarginSwapContractOrderSpec extends EqTypeSwapContractOrderSpec 
{
    /**
    * ログ出力ユーティリティ。<BR>
    */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MarginSwapContractOrderSpec.class);

    
    /**
     * コンストラクタ。<BR>
     * @@param l_trader - (扱者)
     * @@param l_closeMarginContractEntry - (決済建株エントリ)
     * @@param l_taxType - (税区分)
     */
    protected WEB3MarginSwapContractOrderSpec(
        Trader l_trader,
        EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry,
        TaxTypeEnum l_taxType)
    {
        super(l_trader, l_closeMarginContractEntry, l_taxType);
    }

    /**
     * (決済順序区分。0<BR>
     * (0：ランダム　@1：単価益順　@2：単価損順　@3：建日順)
     */
    private String closingOrderType;
    
    /**
     * (税区分（現引現渡）)<BR>
     * 現引先／現渡元口座の税区分
     */
    private TaxTypeEnum swapTaxType;

    /**
     * (create現引現渡注文内容)<BR>
     * （staticメソッド）<BR>
     * 信用現引現渡注文内容オブジェクトを生成し返却する。<BR>
     * <BR>
     * 手続きはシーケンス図「（信用注文）create現引現渡注文内容」を参照。<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト
     * @@param l_closeMarginContractEntry - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。
     * @@param l_strClosingOrderType - 決済順序区分。<BR>
     * (0：ランダム　@1：単価益順　@2：単価損順　@3：建日順)
     * @@param l_taxType - (税区分)<BR>
     * 建株の税区分。<BR>
     * <BR>
     * 0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収<BR>
     * （TaxTypeEnumにて定義）<BR>
     * @@param l_swapTaxType - (税区分（現引現渡）)<BR>
     * 現引先／現渡元の税区分。<BR>
     * <BR>
     * 0:その他,　@1:一般,　@2:特定,　@3:特定口座かつ源泉徴収<BR>
     * （TaxTypeEnumにて定義）<BR>
     * @@return WEB3MarginSwapContractOrderSpec
     * @@roseuid 40BBD2F501A4
     */
    public static WEB3MarginSwapContractOrderSpec createSwapMarginOrderSpec(Trader l_trader, EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry, String l_strClosingOrderType, TaxTypeEnum l_taxType, TaxTypeEnum l_swapTaxType) 
    {
        String STR_METHOD_NAME="createSwapMarginOrderSpec(Trader,EqTypeSettleContractOrderEntry[],String,TaxTypeEnum,TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginSwapContractOrderSpec l_marginSwapContractOrderSpec=
            new WEB3MarginSwapContractOrderSpec(l_trader,l_closeMarginContractEntry,l_taxType);
            
        l_marginSwapContractOrderSpec.setClosingOrderType(l_strClosingOrderType);
        l_marginSwapContractOrderSpec.setSwapTaxType(l_swapTaxType);
        
        log.exiting(STR_METHOD_NAME);
        return  l_marginSwapContractOrderSpec;
    }
    
    /**
     * (set決済順序区分)<BR>
     * 決済順序区分をセットする。
     * @@param l_strClosingOrderType - 決済順序区分。
     * @@roseuid 40BD5C61025D
     */
    public void setClosingOrderType(String l_strClosingOrderType) 
    {
        this.closingOrderType = l_strClosingOrderType;
    }
    
    /**
     * (get決済順序区分)<BR>
     * 決済順序区分を取得する。ClosingOrderType
     * @@return String
     * @@roseuid 40BD5C61026D
     */
    public String getClosingOrderType() 
    {
        return this.closingOrderType;
    }
    
    /**
     * (set税区分（現引現渡）)<BR>
     * 税区分（現引現渡）をセットする。
     * @@param l_swapTaxType - 税区分（現引現渡）。
     * @@roseuid 40BD535B0328
     */
    public void setSwapTaxType(TaxTypeEnum l_swapTaxType) 
    {
        this.swapTaxType = l_swapTaxType;
    }
    
    /**
     * (get税区分（現引現渡）)<BR>
     * 税区分（現引現渡）を取得する。
     * @@return TaxTypeEnum
     * @@roseuid 40BD535B032A
     */
    public TaxTypeEnum getSwapTaxType() 
    {
        return this.swapTaxType;
    }
}
@
