head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityValuationProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 対象銘柄(WEB3TPSecurityValuationProduct.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
 Revesion History : 2007/07/28 崔遠鵬(中訊) 仕様変更モデルNo.117
 */
package webbroker3.tradingpower.updtpower.asset;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (対象銘柄)<BR>
 * 銘柄テーブルから取得した銘柄情報を保持する。<BR>
 */
public class WEB3TPSecurityValuationProduct
{

    /**
     * (銘柄ＩＤ)
     */
    private long productId;

    /**
     * (銘柄タイプ)
     */
    private ProductTypeEnum productType;

    /**
     * (計算単位)
     */
    private double unitSize;

    /**
     * (評価単価)
     */
    private double unitPrice;

    /**
     * (代用評価掛目)
     */
    private double substituteValuationRatio;

    /**
     * (証券評価掛目)
     */
    private double valuationRatio;

    /**
     * (優先市場ID)
     */
    private long primaryMarketId;

    /**
     * (評価対象外銘柄フラグ)
     */
//    private boolean isNotRequired;

    /**
     * (ミニ株区分)
     * 預り資産対応のため追加
     */
    private String miniStockDivDef;

    /**
     * (前日単価)
     */
    private double prePrice;

    /**
     * @@roseuid 41087DBC0206
     */
    public WEB3TPSecurityValuationProduct()
    {

    }

    /**
     * (create対象銘柄)<BR>
     * 対象銘柄のインスタンスを生成する。
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct
     * @@roseuid 40DA5AEE0342
     */
    public static WEB3TPSecurityValuationProduct create()
    {
        return new WEB3TPSecurityValuationProduct();
    }

    /**
     * (get銘柄ＩＤ)<BR>
     * 銘柄ＩＤを取得する。
     * @@return long
     * @@roseuid 40B4466300DE
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * (set銘柄ID)<BR>
     * 銘柄IDを設定する。
     * @@param l_lngProductId - (銘柄ID)
     * @@roseuid 40B4466F011D
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを取得する。
     * @@return ProductTypeEnum
     * @@roseuid 40B4464F0226
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set銘柄タイプ)<BR>
     * 銘柄タイプを設定する。
     * @@param l_productType - (銘柄タイプ)
     * @@roseuid 40B44657038E
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get計算単位)<BR>
     * 計算単位を取得する。
     * @@return double
     * @@roseuid 40E563170102
     */
    public double getUnitSize()
    {
        return unitSize;
    }

    /**
     * (set計算単位)<BR>
     * 計算単位を設定する。
     * @@param l_unitSize - (計算単位)
     * @@roseuid 40E562FA00BA
     */
    public void setUnitSize(double l_dblUnitSize)
    {
        unitSize = l_dblUnitSize;
    }

    /**
     * (get評価単価)<BR>
     * 評価単価を取得する。
     * @@return double
     * @@roseuid 40BABFAA035D
     */
    public double getUnitPrice()
    {
        return unitPrice;
    }

    /**
     * (set評価単価)<BR>
     * 評価単価を設定する。
     * @@param l_unitPrice - (評価単価)
     * @@roseuid 40BABFC5009C
     */
    public void setUnitPrice(double l_dblUnitPrice)
    {
        unitPrice = l_dblUnitPrice;
    }

    /**
     * (get前日単価)<BR>
     * 前日単価を返却する。
     * @@return double
     */
    public double getPrePrice()
    {
        return prePrice;
    }

    /**
     * (set前日単価)<BR>
     * 引数.前日単価を前日単価にセットする。
     * @@param l_prePrice - (前日単価)
     */
    public void setPrePrice(double l_dblPrePrice)
    {
        prePrice = l_dblPrePrice;
    }

    /**
     * (get代用評価掛目)<BR>
     * 代用評価掛目を取得する。
     * @@return double
     * @@roseuid 40B446C8010D
     */
    public double getSubstituteValuationRatio()
    {
        return substituteValuationRatio;
    }

    /**
     * (set代用評価掛目)<BR>
     * 代用評価掛目を設定する。
     * @@param l_substituteValuationRatio - (代用評価掛目)
     * @@roseuid 40B446CD0311
     */
    public void setSubstituteValuationRatio(double l_dblSubstituteValuationRatio)
    {
        substituteValuationRatio = l_dblSubstituteValuationRatio;
    }

    /**
     * (get証券評価掛目)<BR>
     * 証券評価掛目を取得する。
     * @@return double
     * @@roseuid 40B446E00023
     */
    public double getValuationRatio()
    {
        return valuationRatio;
    }

    /**
     * (set証券評価掛目)<BR>
     * 証券評価掛目を設定する。
     * @@param l_valuationRatio - (証券評価掛目)
     * @@roseuid 40B446E6016B
     */
    public void setValuationRatio(double l_dblValuationRatio)
    {
        valuationRatio = l_dblValuationRatio;
    }

    /**
     * (set評価対象外銘柄フラグ)<BR>
     * 評価対象外銘柄フラグを設定する。
     * @@param l_isNotRequiredFlag - (評価対象外銘柄フラグ)
     * @@roseuid 40E014A50325
     */
//    public void setNotRequiredFlag(boolean l_isNotRequiredFlag)
//    {
//        isNotRequired = l_isNotRequiredFlag;
//    }

    /**
     * (is評価対象外銘柄)<BR>
     * 評価対象外銘柄フラグを取得する。
     * @@return boolean
     * @@roseuid 40E014AC0357
     */
//    public boolean isNotRequired()
//    {
//        return isNotRequired;
//    }

    /**
     * (get証券評価掛目)<BR>
     * 指定した預り区分の証券評価掛目を取得する。
     * @@param l_strDepositType 預り区分
     * @@return double
     */
    public double getValuationRatio(String l_strDepositType)
    {
        final String STR_METHOD_NAME = "getValuationRatio(String l_strDepositType)";

        if (WEB3TPDepositTypeDef.TRUST.equals(l_strDepositType))
        {
            return getValuationRatio();
        }
        else if (WEB3TPDepositTypeDef.SUBSTITUTE.equals(l_strDepositType))
        {
            return getSubstituteValuationRatio();
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (get優先市場ID)<BR>
     * 優先市場IDを取得する。
     * @@return long
     */
    public long getPrimaryMarketId()
    {
        return primaryMarketId;
    }

    /**
     * (set優先市場ID)<BR>
     * 優先市場IDを設定する。
     * @@param l_lngPrimaryMarketId - (優先市場ID)
     */
    public void setPrimaryMarketId(long l_lngPrimaryMarketId)
    {
        primaryMarketId = l_lngPrimaryMarketId;
    }

    /**
     * (getミニ株区分)<BR>
     * ミニ株区分を取得する。
     * @@return ProductTypeEnum
     * @@author kikuchi
     */
    public String getMiniStockDivDef()
    {
        return miniStockDivDef;
    }

    /**
     * (setミニ株区分)<BR>
     * ミニ株区分を設定する。
     * @@param l_miniStockDivDef - (ミニ株区分)
     * @@author kikuchi
     */
    public void setMiniStockDivDef(String l_miniStockDivDef)
    {
        miniStockDivDef = l_miniStockDivDef;
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("productId", getProductId())
            .append("productType", getProductType())
            .append("unitSize", getUnitSize())
            .append("unitPricee", getUnitPrice())
            .append("substituteValuationRatio", getSubstituteValuationRatio())
            .append("valuationRatio", getValuationRatio())
//            .append("isNotRequired", isNotRequired())
            .append("primaryMarketId", getPrimaryMarketId())
            .append("miniStockDivDef", getMiniStockDivDef())
            .toString();
    }

}
@
