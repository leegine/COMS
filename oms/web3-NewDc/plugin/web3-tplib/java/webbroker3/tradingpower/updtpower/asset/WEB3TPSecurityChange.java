head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証券変動(WEB3TPSecurityChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
                    2006/09/14 車進　@     (中訊)モデルNo.36
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (証券変動)
 *
 * 預り残高変動を表現する
 */
public abstract class WEB3TPSecurityChange
    extends WEB3TPAssetReflector
{

    /**
     * (受渡日)
     */
    private Date deliveryDate;
    
    /**
     * (預り区分)
     */
    private String depositType;

    /**
     * (特定口座フラグ)
     */
    private boolean isSpecialAccountFlag;

    /**
     * (変動数量)
     */
    private double quantity;

    /**
     * (評価単価)
     */
    private double unitPrice;

    /**
     * (評価掛目)
     */
    private double valuationRatio;

    /**
     * (評価額)
     */
    private double valuationPrice;

    /**
     * (税区分)
     */
    private TaxTypeEnum taxType;  
    
    /**
     * @@roseuid 41087D760060
     */
    public WEB3TPSecurityChange()
    {

    }

    /**
     * (get預り区分)<BR>
     * 預り区分を取得する
     * @@return String
     * @@roseuid 40B2D37601FB
     */
    public String getDepositType()
    {
        return depositType;
    }

    /**
     * (set預り区分)<BR>
     * 預り区分を設定する。
     * @@param l_strDepositType - (預り区分)
     * @@roseuid 40B2D3EC02A7
     */
    public void setDepositType(String l_strDepositType)
    {
        depositType = l_strDepositType;
    }

    /**
     * (is特定口座区分)<BR>
     * 特定口座区分を取得する
     * @@return boolean
     * @@roseuid 40B2D38E0027
     */
    public boolean isSpecialAccount()
    {
        return isSpecialAccountFlag;
    }

    /**
     * (set特定口座区分)<BR>
     * 特定口座区分を設定する。
     * @@param l_isSpecialAccountFlag - (特定口座区分)
     * @@roseuid 40B2D3EC02C6
     */
    public void setSpecialAccountFlag(boolean l_isSpecialAccountFlag)
    {
        isSpecialAccountFlag = l_isSpecialAccountFlag;
    }

    /**
     * (get変動数量)<BR>
     * 変動数量を取得する
     * @@return double
     * @@roseuid 40B2D39F00E2
     */
    public double getQuantity()
    {
        return quantity;
    }

    /**
     * (set変動数量)<BR>
     * 変動数量を設定する。
     * @@param l_dblQuantity - (変動数量)
     * @@roseuid 40B2D3EC02E6
     */
    public void setQuantity(double l_dblQuantity)
    {
        quantity = l_dblQuantity;
    }

    /**
     * (get評価単価)<BR>
     * 評価単価を取得する。
     * @@return double
     * @@roseuid 40B2D3A803C0
     */
    public double getUnitPrice()
    {
        return unitPrice;
    }

    /**
     * (set評価単価)<BR>
     * 評価単価を設定する。
     * @@param l_dblUnitPrice - (評価単価)
     * @@roseuid 40B2D3EC0315
     */
    public void setUnitPrice(double l_dblUnitPrice)
    {
        unitPrice = l_dblUnitPrice;
    }

    /**
     * (get評価掛目)<BR>
     * 評価掛目を取得する。
     * @@return double
     * @@roseuid 40B2D3CA0007
     */
    public double getValuationRatio()
    {
        return valuationRatio;
    }

    /**
     * (set評価掛目)<BR>
     * 評価掛目を設定する。
     * @@param l_dblValuationRatio - (評価掛目)
     * @@roseuid 40B2D3EC0334
     */
    public void setValuationRatio(double l_dblValuationRatio)
    {
        valuationRatio = l_dblValuationRatio;
    }

    /**
     * (get評価額)<BR>
     * 評価額を取得する。
     * @@return double
     * @@roseuid 40B3329B02F5
     */
    public double getValuationPrice()
    {
        return valuationPrice;
    }

    /**
     * (set評価額)<BR>
     * 評価額を設定する。
     * @@param l_dblValuationPrice - (評価額)
     * @@roseuid 40B333E102B7
     */
    public void setValuationPrice(double l_dblValuationPrice)
    {
        valuationPrice = l_dblValuationPrice;
    }

    /**
     * (get受渡日)<BR>
     * 受渡日を取得する。
     * @@return deliveryDate を戻します。
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set受渡日)<BR>
     * 受渡日を設定する。
     * @@param deliveryDate deliveryDate を設定。
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        this.deliveryDate = l_datDeliveryDate;
    }

    /**
     * (to特定口座区分)<BR>
     * 税区分を特定口座区分へ変換する
     * @@param l_taxType - (税区分)
     * @@return boolean
     */
    public boolean toSpecialAccountFlag(TaxTypeEnum l_taxType)
    {
        if (l_taxType != null && (l_taxType.equals(TaxTypeEnum.SPECIAL)
                                  || l_taxType.equals(TaxTypeEnum.SPECIAL_WITHHOLD)))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * (set税区分) <BR>
     * <BR>
     * 引数.税区分を、this.税区分にセットする。<BR>
     * @@param l_taxType  - (税区分)
     */
    public void setTaxType(TaxTypeEnum l_taxType)
    {
        this.taxType = l_taxType;
    }    
    
    /**
     * (get税区分)<BR>
     * <BR>
     * this.税区分を返却する。 <BR>
     * @@return taxType
     */
    public TaxTypeEnum getTaxType()
    {
        return taxType;
    }    
    
    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("depositType", getDepositType())
            .append("isSpecialAccount", isSpecialAccount())
            .append("quantity", getQuantity())
            .append("unitPrice", getUnitPrice())
            .append("valuationRatio", getValuationRatio())
            .append("valuationPrice", getValuationPrice())
            .append("deliveryDate", getDeliveryDate())
            .append("taxType", getTaxType())
            .toString();
    }
}
@
