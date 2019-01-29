head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderTypeJudge.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文種別判定(WEB3BondOrderTypeJudge.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
Revesion History : 2007/07/11 周墨洋 (中訊) 仕様変更・モデル196
Revesion History : 2007/07/25 劉立峰(中訊) 仕様変更モデルNO.241
*/

package webbroker3.bd;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

/**
 * (債券注文種別判定)<BR>
 * 債券注文種別判定クラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3BondOrderTypeJudge 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderTypeJudge.class);
   
    
    /**
     * (注文種別)<BR>
     * 注文種別<BR>
     */
    private OrderTypeEnum orderType;
    
    /**
     * (取引)<BR>
     * 取引<BR>
     */
    private String trading;
    
    /**
     * @@roseuid 44E33623004E
     */
    public WEB3BondOrderTypeJudge() 
    {
     
    }
    
    /**
     * (債券注文種別判定)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）注文種別、取引のプロパティを設定する<BR>
     * <BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@param l_strTrading - (取引)<BR>
     * 取引<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CAEA2D029A
     */
    public WEB3BondOrderTypeJudge(OrderTypeEnum l_orderType, String l_strTrading) 
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "WEB3BondOrderTypeJudge(OrderTypeEnum, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）注文種別、取引のプロパティを設定する
        this.orderType = l_orderType;
        this.trading = l_strTrading;

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get注文種別)<BR>
     * get注文種別<BR>
     * <BR>
     * 　@注文種別を返す<BR>
     * @@return OrderTypeEnum
     * @@roseuid 44CAE5EA0098
     */
    public OrderTypeEnum getOrderType() 
    {
        return this.orderType;
    }
    
    /**
     * (get取引)<BR>
     * get取引<BR>
     * <BR>
     * 　@取引を返す<BR>
     * @@return String
     * @@roseuid 44CAE5FA023E
     */
    public String getTrading() 
    {
        return this.trading;
    }
    
    /**
     * (is応募注文)<BR>
     * is応募注文<BR>
     * <BR>
     * １）this.get取引() == 募集取引<BR>
     * 　@　@かつ<BR>
     * 　@　@this.get注文種別() == 債券買付<BR>
     * 　@　@の場合、trueを返す<BR>
     * <BR>
     * ２）this.get注文種別() == 国内債券応募注文<BR>
     * 　@　@の場合、trueを返す<BR>
     * <BR>
     * ３）上記以外の場合、falseを返す<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 44CAE6080192
     */
    public boolean isRecruitOrder()
    {
        //１）this.get取引() == 募集取引
        //　@　@かつ
        //　@　@this.get注文種別() == 債券買付
        //　@　@の場合、trueを返す
        if (WEB3DealTypeDef.RECRUIT_TRADING.equals(this.getTrading())
            && OrderTypeEnum.BOND_BUY.equals(this.getOrderType()))
        {
            return true;
        }
        else if (OrderTypeEnum.DOMESTIC_BOND_RECRUIT.equals(this.getOrderType()))
        {
            //２）this.get注文種別() == 国内債券応募注文
            //　@の場合、trueを返す
            return true;
        }
        else
        {
            //３）上記以外の場合、falseを返す
            return false;
        }
    }
    
    /**
     * (is買付注文)<BR>
     * is買付注文<BR>
     * <BR>
     * this.get取引()＝＝国内仕切取引<BR>
     * かつ<BR>
     * this.get注文種別()＝＝債券買付<BR>
     * の場合、trueを返す<BR>
     * <BR>
     * 上記以外の場合、falseを返す<BR>
     * @@return boolean
     * @@roseuid 44CAE7E601E6
     */
    public boolean isBuyOrder() 
    {
        if (WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING.equals(this.getTrading()) &&
            OrderTypeEnum.BOND_BUY.equals(this.getOrderType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * (is売却注文)<BR>
     * is売却注文<BR>
     * <BR>
     * this.get取引()＝＝国内仕切取引<BR>
     * かつ<BR>
     * this.get注文種別()＝＝債券売却<BR>
     * の場合、trueを返す<BR>
     * <BR>
     * 上記以外の場合、falseを返す<BR>
     * @@return boolean
     * @@roseuid 44CAE82902E1
     */
    public boolean isSellOrder() 
    {
        if (WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING.equals(this.getTrading()) && 
            OrderTypeEnum.BOND_SELL.equals(this.getOrderType()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
@
