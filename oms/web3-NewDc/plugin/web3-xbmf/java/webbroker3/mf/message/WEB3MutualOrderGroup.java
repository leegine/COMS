head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託注文照会注文単位データクラス(WEB3MutualOrderGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12  黄建 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル535
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 投資信託注文照会注文単位データクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualOrderGroup extends Message
{   
    /**
     * 投信注文ID<BR>
     * <BR>
     * (画面では非表示)<BR>
     */    
    public String id;
    
    /**
     * 銘柄名
     */    
    public String mutualProductName;
    
    /**
     * 口座区分<BR>
     * <BR>
     * 0:一般　@1:特定　@2：その他<BR>
     */    
    public String taxType;
    
    /**
     * 売買区分(投信)<BR>
     * <BR>
     * 1:買付　@2:解約　@3:乗換  5:募集 <BR>
     */    
    public String mutualDealingType;
    
    /**
     * 請求方法@<BR>
     * <BR>
     * 0:解約請求　@1:買取請求<BR>
     * <BR>
     * (買付、募集注文の場合、null)<BR>
     */    
    public String sellBuyDiv;
    
    /**
     * 指定方法@<BR>
     * <BR>
     * 2:全部　@3:金額　@4:口数<BR>
     */    
    public String specifyDiv;
    
    /**
     * 決済方法@<BR>
     * <BR>
     * 1:円貨　@2:外貨<BR>
     */    
    public String settleDiv;
    
    /**
     * 注文数量区分<BR>
     * <BR>
     * 0:口数<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     */    
    public String mutualOrderQuantityType;
    
    /**
     * 注文数量
     */    
    public String mutualOrderQuantity;
    
    /**
     * 参考基準価額通貨コード<BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     */    
    public String constantValueCurrencyCode;
    
    /**
     * 参考基準価額
     */    
    public String constantValue;
    
    /**
     * 基準価額適用日
     * （募集注文の場合、null）<BR>
     */    
    public Date constantValueAppDate;
    
    /**
     * 概算売買口数<BR>
     * <BR>
     */    
    public String estimatedQty;    
    
    /**
     * 概算受渡代金通貨コード<BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     */    
    public String estimatedPriceCurrencyCode;
    
    /**
     * 概算受渡代金<BR>
     * <BR>
     */    
    public String estimatedPrice;
    
    /**
     * 受渡方法@<BR>
     * <BR>
     * 1:銀行振込み　@2:証券口座入金<BR>
     */    
    public String deliveryDiv;
    
    /**
     * 注文日時
     */    
    public Date orderDate;
    
    /**
     * 発注日
     */    
    public Date orderBizDate;
    
    /**
     * 約定日
     */    
    public Date executionTimestamp;
    
    /**
     * 受渡日
     */    
    public Date deliveryDate;
    
    /**
     * 注文状態区分<BR>
     * <BR>
     * 1:注文中　@6:注文受付エラー　@12:注文取消中<BR>
     * 13:注文取消済　@15:注文取消失敗<BR>
     * 52:約定中<BR>
     * 53:約定済<BR>
     * 54:約定済(銀行振込)<BR>
     * 55:乗換中<BR>
     * <BR>
     */    
    public String orderState;
    
    /**
     * 取消可能フラグ<BR>
     * <BR>
     * true:取消可能　@false:取消不可<BR>
     */    
    public boolean cancelFlag;
    
    /**
     * 銘柄名（乗換先）<BR>
     * <BR>
     * (注文が"乗換"以外の場合、nullが設定される)<BR>
     */    
    public String switchingProductName;
    
    /**
     * 計算基準価額通貨コード（乗換先）<BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
     * <BR>
     * (注文が"乗換"以外の場合、nullが設定される)<BR>
     */    
    public String switchingConstantValueCurrencyCode;
    
    /**
     * 計算基準価額（乗換先）<BR>
     * <BR>
     * (注文が"乗換"以外の場合、nullが設定される)<BR>
     */    
    public String switchingConstantValue;
    
    /**
     * 口座区分（乗換先）<BR>
     * <BR>
     * 0:一般　@1:特定<BR>
     * <BR>
     * (注文が"乗換"以外の場合、nullが設定される)<BR>
     */    
    public String switchingTaxType;
    
    /**
     * 概算買付口数（乗換先）<BR>
     * <BR>
     * (注文が"乗換"以外の場合、nullが設定される)<BR>
     */    
    public String switchingEstimatedQty;
    
    /**
     * 源泉徴収拘束金(乗換元)<BR>
     * <BR>
     * (注文が"乗換"以外の場合、nullが設定される) <BR>
     */    
    public String switchingWHRestraintPrice;
    
    /**
     * 注文チャネル<BR>
     * <BR>
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル<BR>
     * （コールセンターの時のみ使用）<BR>
     */        
    public String orderChannel;
    
    /**
     * (注文経路区分)<BR>
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット<BR>
     * 4：i-mode　@5：Vodafone　@6：AU　@9：HOST<BR>
     * （コールセンターの時のみ使用）<BR>
     */    
    public String orderRootDiv;
    
    /**
     * (オペレータコード)<BR>
     * 取扱者コード<BR>
     * （コールセンターの時のみ使用）<BR>
     */    
    public String operatorCode;

    /**
     * (外貨MMFフラグ)<BR>
     * 外貨MMFフラグ <BR>
     * <BR>
     * true:銘柄が外貨MMF <BR>
     * false:銘柄が外貨MMFでない<BR>
     */
    public boolean frgnMmfFlag;

    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A9A68F02E9
     */    
    public WEB3MutualOrderGroup()    
    {
    }
}
@
