head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正新規建入力画面レスポンス(WEB3OptionsOpenMarginChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 鄒鋭 (中訊) 新規作成
              001: 2006/07/12 張騰宇　@(中訊)　@仕様変更　@454,457,470,488,527
Revesion History : 2007/06/08 趙林鵬 (中訊) モデルNo.639
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;

import webbroker3.ifo.message.WEB3OptionsCommonResponse;

/**
 * (株価指数オプション訂正新規建入力画面レスポンス)<BR>
 * 株価指数オプション訂正新規建入力画面レスポンスクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeInputResponse extends WEB3OptionsCommonResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_openMarginChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141551L;

    /**
     * (取引区分)<BR>
     * 3：新規買建注文　@4：新規売建注文
     */
    public String tradingType;

    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪
     */
    public String marketCode;

    /**
     * (指数種別)<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225
     */
    public String targetProductCode;

    /**
     * (限月)<BR>
     * YYYYMM形式
     */
    public String delivaryMonth;

    /**
     * (オプション商品区分)<BR>
     * P：プットオプション C：コールオプション
     */
    public String opProductType;

    /**
     * (行使価格)<BR>
     */
    public String strikePrice;

    /**
     * (現在値)<BR>
     * 値がついていないときは基準値を設定。
     */
    public String currentPrice;

    /**
     * (前日比)<BR>
     * 値がついていないときは未設定。
     */
    public String comparedPreviousDay;

    /**
     * (取引時間)<BR>
     * 値がついていないときは未設定。
     */
    public java.util.Date currentPriceTime;

    /**
     * (注文数量)<BR>
     * 一括返済時に「ランダムモード」の場合は設定されない
     */
    public String opOrderQuantity;

    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * 注文単価区分が「1：指値」の場合に設定
     */
    public String limitPrice;

    /**
     * (執行条件)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行
     */
    public String execCondType;

    /**
     * (注文期限区分)<BR>
     * 1：当日限り　@2：出来るまで注文　@3：夕場まで注文
     */
    public String expirationDateType;

    /**
     * (注文有効期限)<BR>
     * 注文期限区分が「2：出来るまで注文」の場合に設定
     */
    public java.util.Date expirationDate;

    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値
     */
    public String orderCondType;

    /**
     * (逆指値用プレミアム／原資産価格)<BR>
     * 0：DEFAULT（時価）（*）先物ＯＰの場合は原資産時価<BR>
     * 1：プレミアム<BR>
     * 発注条件区分が、「1：逆指値」の場合設定される<BR>
     */
    public String stopPremium_underlyingAssets;

    /**
     * (逆指値用発注条件単価)<BR>
     * 発注条件区分が、「1：逆指値」の場合設定される
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * 発注条件区分が、「1：逆指値」の場合設定される
     */
    public String stopOrderCondOperator;

    /**
     * (Ｗ指値用プレミアム／原資産価格)<BR>
     * 0：DEFAULT（時価）（*）先物ＯＰの場合は原資産時価<BR>
     * 1：プレミアム<BR>
     * 発注条件区分が、「2：W指値」の場合設定される
     */
    public String wlimitPremium_underlyingAssets;

    /**
     * (Ｗ指値用発注条件単価)<BR>
     * 発注条件区分が、「2：W指値」の場合設定される
     */
    public String wlimitOrderCondPrice;

    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * 発注条件区分が、「2：W指値」の場合設定される
     */
    public String wlimitOrderCondOperator;

    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * 発注条件区分が、「2：W指値」の場合設定される
     */
    public String wLimitOrderPriceDiv;

    /**
     * (Ｗ指値用注文単価)<BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される
     */
    public String wLimitPrice;
    
    /**
     * (W指値用執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitExecCondType;     
    
    /**
     * (W指値用有効状態区分)<BR>
     * <BR>
     * 0：リミット注文有効　@1：ストップ注文有効  <BR>
     * 2：ストップ注文失効済 <BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitEnableStatusDiv; 
    
    /**
     * (W指値用切替前注文単価)<BR>
     * <BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice; 
    
    /**
     * (W指値用切替前執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgExecCondType; 
    
    /**
     * (元発注条件区分)<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値 <BR>
     * <BR>
     * ※発注条件執行後の場合に設定<BR>
     */
    public String orgOrderCondType; 
    
    /**
     * (元プレミアム/原資産価格)<BR>
     * <BR>
     * 0：プレミアム　@1：原資産価格 <BR>
     * <BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合<BR>
     */
    public String orgPremium_underlyingAssets; 
    
    /**
     * (元発注条件単価)<BR>
     * <BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合<BR>
     */
    public String orgOrderCondPrice; 
    
    /**
     * (元発注条件演算子)<BR>
     * <BR>
     * 1：以上　@2：以下 <BR>
     *※元発注条件区分が、1：逆指値、2：Ｗ指値の場合<BR>
     */
    public String orgCondOperator; 
    
    /**
     * (元W指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値 <BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWLimitOrderPriceDiv; 
    
    /**
     * (元W指値用注文単価)<BR>
     * <BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される<BR>
     */
    public String orgWLimitPrice; 
    
    /**
     * (元W指値用執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (概算受渡代金)<BR>
     */
    public String estimatedPrice;

    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納
     */
    public String[] messageSuspension;

    /**
     * (内約定数量)<BR>
     * 注文数量のうち、約定している数量<BR>
     */
    public String partExecQuantity;
        
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsOpenMarginChangeInputResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsOpenMarginChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
