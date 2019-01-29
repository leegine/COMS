head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建入力画面レスポンスクラス(WEB3FuturesOpenMarginChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強(中訊) 新規作成
                   2006/07/28 張騰宇　@仕様変更　@モデル454,457,470,488
Revesion History : 2007/06/11 孟亜南  仕様変更モデルNo.639
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (株価指数先物訂正新規建入力画面レスポンス)<BR>
 * 株価指数先物訂正新規建入力画面レスポンスクラス
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeInputResponse extends WEB3FuturesCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211424L;
    /**
     * (取引区分)<BR>
     * 3：新規買建注文　@4：新規売建注文<BR>
     */
    public String tradingType;
    
    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪<BR>
     */
    public String marketCode;
    
    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     */
    public String targetProductCode;
    
    /**
     * (限月)<BR>
     * YYYYMM形式<BR>
     */
    public String delivaryMonth;
    
    /**
     * (現在値)<BR>
     * 値がついていないときは基準値を設定。<BR>
     */
    public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 値がついていないときは未設定。<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (取引時間)<BR>
     * 値がついていないときは未設定。<BR>
     */
    public Date currentPriceTime;
    
    /**
     * (注文数量)<BR>
     * 一括返済時に「ランダムモード」の場合は設定されない<BR>
     */
    public String futOrderQuantity;
    
    /**
     * (内約定数量)<BR>
     * 注文数量のうち、約定している数量<BR>
     */
    public String partExecQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価区分が「1：指値」の場合に設定<BR>
     */
    public String limitPrice;
    
    /**
     * (執行条件)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (注文期限区分)<BR>
     * 1：当日限り　@2：出来るまで注文　@3：夕場まで注文<BR>
     */
    public String expirationDateType;
    
    /**
     * (注文有効期限)<BR>
     * 注文期限区分が「2：出来るまで注文」の場合に設定<BR>
     */
    public Date expirationDate;
    
    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * 発注条件区分が、「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * 発注条件区分が、「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (Ｗ指値用発注条件単価)<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (Ｗ指値用注文単価)<BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される<BR>
     */
    public String wLimitPrice;
    
    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitExecCondType;
    
    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * <BR>
     * 発注条件区分または元発注条件区分「2：W指値」の場合設定<BR>
     */
    public String wlimitEnableStatusDiv;
    
    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice;
    
    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgExecCondType;
    
    /**
     * (元発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     * <BR>
     * ※発注条件執行後の場合に設定<BR>
     */
    public String orgOrderCondType;
    
    /**
     * (元発注条件単価)<BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合<BR>
     */
    public String orgOrderCondPrice;
    
    /**
     * (元発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合<BR>
     */
    public String orgCondOperator;
    
    /**
     * (元Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWLimitOrderPriceDiv;
    
    /**
     * (元Ｗ指値用注文単価)<BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される<BR>
     */
    public String orgWLimitPrice;
    
    /**
     * (元Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (概算建代金)
     */
    public String estimatedContractPrice;
    
    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 40F7AE1202CE
     */
    public WEB3FuturesOpenMarginChangeInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3FuturesOpenMarginChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
    
}
@
