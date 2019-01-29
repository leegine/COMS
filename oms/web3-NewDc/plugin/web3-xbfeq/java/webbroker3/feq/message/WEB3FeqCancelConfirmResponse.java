head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式取消確認レスポンス(WEB3FeqCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式取消確認レスポンス)<BR>
 * 外国株式取消確認レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_cancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     */
    public String localProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定<BR>
     */
    public String taxType;
    
    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * 1：買付<BR>
     * 2：売付<BR>
     */
    public String dealingType;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     */
    public String orderQuantity;
    
    /**
     * (内出来数量)<BR>
     * 内出来数量<BR>
     */
    public String partContQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 注文単価区分<BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     * <BR>
     * ※注文単価区分が”指値”の場合、設定。<BR>
     */
    public String limitPrice;
    
    /**
     * (執行条件)<BR>
     * 執行条件<BR>
     * <BR>
     * 1：条件なし<BR>
     * 3：寄付<BR>
     * 4：引け<BR>
     * 7：不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (注文期限区分)<BR>
     * 注文期限区分<BR>
     * <BR>
     * 1：当日限り<BR>
     * 2：出来るまで注文<BR>
     */
    public String expirationDateType;
    
    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     * <BR>
     * ※注文期限区分が”出来るまで注文”の場合、設定<BR>
     */
    public Date expirationDate;
    
    /**
     * (発注条件)<BR>
     * 発注条件<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * 逆指値用発注条件単価<BR>
     * <BR>
     * ※発注条件が”逆指値”の場合、設定<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * 逆指値用発注条件演算子<BR>
     * <BR>
     * 1：以上<BR>
     * 2：以下<BR>
     * <BR>
     * ※発注条件が”逆指値”の場合、設定<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (W指値用発注条件単価)<BR>
     * W指値用発注条件単価<BR>
     * <BR>
     * ※発注条件が”W指値”の場合、設定<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (W指値用発注条件演算子)<BR>
     * W指値用発注条件演算子<BR>
     * <BR>
     * 1：以上<BR>
     * 2：以下<BR>
     * <BR>
     * ※発注条件が”W指値”の場合、設定<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (W指値用注文単価区分)<BR>
     * W指値用注文単価区分<BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値<BR>
     * <BR>
     * ※発注条件が”W指値”の場合、設定<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W指値用注文単価)<BR>
     * W指値用注文単価<BR>
     * <BR>
     * ※W指値用注文単価区分が”指値”の場合、設定。<BR>
     */
    public String wLimitPrice;
    
    /**
     * (概算受渡代金)<BR>
     * 概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (概算簿価単価)<BR>
     * 概算簿価単価<BR>
     * <BR>
     * ※売付の場合のみ設定<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日<BR>
     */
    public Date checkDate;
    
    /**
     * (時価取得区分)<BR>
     * 時価取得区分<BR>
     * <BR>
     * 1:現在値　@　@
     * 2:売気配値　@　@
     * 3:買気配値　@　@
     * 4:前日終値
     */
    public String currentPriceGetDiv;
    
    /**
     * (時価)<BR>
     * 時価<BR>
     */
    public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 前日比<BR>
     */
    public String comparedPreviousDay;

    /**
     * (取引時間)<BR>
     * 取引時間<BR>
     */
    public Date currentPriceTime;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     * <BR>
     * A0：USD A3：HKD Z4：EUR<BR>
     * 注文単価区分が「指値」の場合セット<BR>
     */
    public String currencyCode;
    
    /**
     * @@roseuid 42CE3A060203
     */
    public WEB3FeqCancelConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
