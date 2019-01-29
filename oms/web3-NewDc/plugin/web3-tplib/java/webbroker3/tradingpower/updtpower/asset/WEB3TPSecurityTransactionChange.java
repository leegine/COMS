head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecurityTransactionChange.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 取引変動(WEB3TPSecurityTransactionChange.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/07/29 山田　@卓司 (FLJ) 新規作成
 Revision History : 2008/04/01 崔遠鵬(中訊) モデルNo.273
 Revision History : 2008/04/07 崔遠鵬(中訊) モデルNo.274
 */
package webbroker3.tradingpower.updtpower.asset;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.tradingpower.define.WEB3TPExecTypeDef;
import webbroker3.util.WEB3DateUtility;

/**
 * (取引変動)
 *
 * 当日以降取引の預り残高変動を表現する。
 */
public class WEB3TPSecurityTransactionChange
    extends WEB3TPSecurityChange
{

    /**
     * (銘柄タイプ)
     */
    private ProductTypeEnum productType;

    /**
     * (売買区分)
     */
    private SideEnum side;

    /**
     * (約定区分)
     */
    private String execType;

    /**
     * (注文カテゴリ)
     */
    private OrderCategEnum orderCateg;

    /**
     * @@roseuid 41087DAD00CE
     */
    public WEB3TPSecurityTransactionChange()
    {

    }

    /**
     * (create取引変動)<BR>
     * @@return webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityTransactionChange
     * @@roseuid 40D80DC103B7
     */
    public static WEB3TPSecurityTransactionChange create()
    {
        return new WEB3TPSecurityTransactionChange();
    }

    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを取得する。
     * @@return 銘柄タイプ
     */
    public ProductTypeEnum getProductType()
    {
        return productType;
    }

    /**
     * (set銘柄タイプ)<BR>
     * 銘柄タイプを設定する。
     * @@param l_productType 銘柄タイプ
     */
    public void setProductType(ProductTypeEnum l_productType)
    {
        productType = l_productType;
    }

    /**
     * (get売買区分)<BR>
     * 売買区分を取得する。
     * @@return SideEnum
     * @@roseuid 40B2D92E02A7
     */
    public SideEnum getSide()
    {
        return side;
    }

    /**
     * (set売買区分)<BR>
     * 売買区分を設定する。
     * @@param l_side - (売買区分)
     * @@roseuid 40B2D96A0315
     */
    public void setSide(SideEnum l_side)
    {
        side = l_side;
    }

    /**
     * (get約定区分)<BR>
     * 約定区分を取得する。
     * @@return String
     * @@roseuid 40B2D93702A7
     */
    public String getExecType()
    {
        return execType;
    }

    /**
     * (set約定区分)<BR>
     * 約定区分を設定する。
     * @@param l_strExecType - (約定済区分)
     * @@roseuid 40B2D96A0343
     */
    public void setExecType(String l_strExecType)
    {
        execType = l_strExecType;
    }

    /**
     * (get注文カテゴリ)<BR>
     * 注文カテゴリを取得する。
     * @@return OrderCategEnum
     * @@roseuid 40DA41F50025
     */
    public OrderCategEnum getOrderCateg()
    {
        return orderCateg;
    }

    /**
     * (set注文カテゴリ)<BR>
     * 注文カテゴリを設定する。
     * @@param l_orderCateg - (注文カテゴリ)
     * @@roseuid 40DA42000381
     */
    public void setOrderCateg(OrderCategEnum l_orderCateg)
    {
        orderCateg = l_orderCateg;
    }

    /**
     * (calc変動反映日)<BR>
     * <BR>
     * 変動反映開始日、変動反映終了日を計算しセットする。<BR>
     * <BR>
     * １）”変動反映開始日”を計算する。<BR>
     * 　@[a.累投買付または現物買付（保護残扱い）の場合]<BR>
     * 　@((this.銘柄タイプ() == 7：累積投資 && this.get売買区分 == 1：買付)<BR>
     * 　@　@||<BR>
     * 　@　@(this.銘柄タイプ() == 1：株式 && this.get売買区分 == 1：買付 && this.get預り区分 == 0：保護))<BR>
     * <BR>
     * 　@　@[b.PTS出来終了かつ約定済の場合]<BR>
     * 　@　@(this.get余力計算条件().isPTS出来終了区分() == true && this.get約定区分 == 2：約定済)<BR>
     * <BR>
     * 　@　@　@”変動反映開始日” = 引数.受渡日<BR>
     * <BR>
     * 　@　@[b.以外の場合]　@<BR>
     * <BR>
     * 　@　@　@”変動反映開始日” = this.get余力計算条件().roll営業日(:Date = 引数.受渡日, :int = 1)<BR>
     * <BR>
     * 　@[a.以外の場合]<BR>
     * <BR>
     * 　@　@”変動反映開始日” = 引数.受渡日<BR>
     * <BR>
     * ２）１）で計算した”変動反映開始日”を補正する。<BR>
     * <BR>
     * 　@[”変動反映開始日”が営業日(T+5)より未来日付の場合]<BR>
     * 　@(”変動反映開始日” > 営業日(T+5)(*))<BR>
     * <BR>
     * 　@　@”変動反映開始日” = 営業日(T+5)(*)<BR>
     * <BR>
     * ３）”変動反映開始日”をセットする。<BR>
     * 　@-this.set変動反映開始日()をコール<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@Date = ”変動反映開始日”<BR>
     * <BR>
     * ４）”変動反映終了日”をセットする。<BR>
     * 　@-this.set変動反映終了日()をコール<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@Date = 営業日(T+5)(*)<BR>
     * <BR>
     * ５）受渡日をセットする。<BR>
     * 　@-this.set受渡日()をコール<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@Date = 引数.受渡日<BR>
     * <BR>
     * ----<BR>
     * (*)<BR>
     * 営業日(T+5) = this.get余力計算条件().get営業日(:int = 5)の戻り値<BR>
     * <BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {       
        if ( (ProductTypeEnum.RUITO.equals(getProductType())
              && SideEnum.BUY.equals(getSide()))
            || (ProductTypeEnum.EQUITY.equals(getProductType())
                && SideEnum.BUY.equals(getSide())
                && WEB3TPDepositTypeDef.TRUST.equals(getDepositType())))
        {
            //PTS出来終了かつ約定済の場合
            if (getCalcCondition().isPtsOrderExecutionEndType() &&
                WEB3TPExecTypeDef.EXECUTED.equals(this.getExecType()))
            {
                setReflectStartDay(l_datDeliveryDate);
            }
            else {
                    setReflectStartDay(getCalcCondition().rollBizDate(l_datDeliveryDate, 1));                   
            }
        }
        else
        {
            setReflectStartDay(l_datDeliveryDate);
        }
 
        
        //上記の結果、変動反映開始日がT+5を超える場合
		//Ｔ+5にまとめる        
		Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
		if (WEB3DateUtility.compareToDay(l_datT5, this.getReflectStartDay()) < 0) 
		{
		    //株式以外の場合、補正する
            if (!ProductTypeEnum.EQUITY.equals(getProductType())) 
            {
                setReflectStartDay(l_datT5);
            }
        }
        
        setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));

        setDeliveryDate(l_datDeliveryDate);    
    }

    /**
     * このオブジェクトの文字列表現を返す
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("productType", getProductType())
            .append("side", getSide())
            .append("execType", getExecType())
            .append("orderCateg", getOrderCateg())
            .toString();
    }

}
@
