head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsChangeCancelHistoryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文履歴一覧行(WEB3OptionsChangeCancelHistoryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 鄒鋭 (中訊) 新規作成
              001: 2004/07/28 王暁傑 (中訊) 対応 詳細設計チェック指摘事項 (日本側) 
                   com.fitechlabs.xtrade.kernel.message.Messageを継承。
              002: 2006/07/12 張騰宇　@(中訊)　@仕様変更　@454,457,470,488,527
Revesion History : 2007/06/08 孫洪江(中訊) 仕様変更モデルNo.701
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (株価指数オプション注文履歴一覧行)<BR>
 * 株価指数オプション注文履歴一覧行クラス<BR>                                                                    
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsChangeCancelHistoryGroup extends Message
{
    /**
     * (注文ＮＯ)<BR>
     * 注文履歴ID<BR>
     */
    public String orderActionId;

    /**
     * 注文受付日
     */
    public Date orderDate;

    /**
     * (注文数量)<BR>
     * 約定以外の場合設定<BR>
     */
    public String opOrderQuantity;

    /**
     * (注文単価区分)<BR>
     * 0：成行　@　@1：指値<BR>
     * <BR>
     * 約定以外の場合設定<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * 注文単価区分が「指値」の場合設定<BR>
     */
    public String limitPrice;

    /**
     * (注文内容区分)<BR>
     * ※コード値はメッセージ定義フォルダ以下の<BR>
     * 「ﾒｯｾｰｼﾞ定義書_株価指数オプション(共通).xls」の注文内容区分定義を参照<BR>
     */
    public String orderType;

    /**
     * (執行条件)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     * <BR>
     * 注文履歴の場合設定<BR>
     */
    public String execCondType;

    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     * <BR>
     * 注文履歴の場合設定<BR>
     */
    public String orderCondType;

    /**
     * (逆指値用プレミアム/原資産価格)<BR>
     * 0：原資産価格　@1：プレミアム<BR>
     */
    public String stopPremium_underlyingAssets;

    /**
     * (逆指値用発注条件単価)<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W指値用プレミアム/原資産価格)<BR>
     * 0：原資産価格　@1：プレミアム<BR>
     */
    public String wlimitPremium_underlyingAssets;

    /**
     * (W指値用発注条件単価)<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W指値用注文単価)<BR>
     */
    public String wLimitPrice;
    
    /**
     * (W指値用執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 約定以外、かつ、発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitExecCondType;     
    
    /**
     * (W指値用有効状態区分)<BR>
     * <BR>
     * 0：リミット注文有効　@1：ストップ注文有効 <BR> 
     * 2：ストップ注文失効済 <BR>
     * 約定以外、かつ、<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitEnableStatusDiv; 
    
    /**
     * (W指値用切替前注文単価)<BR>
     * <BR>
     * 約定以外、かつ、<BR>
     * 発注条件区分または元発注条件区分が「2：W指値」、かつ <BR>
     * W指値の有効状態が"ストップ注文有効"（＝切替完了）の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice; 
    
    /**
     * (W指値用切替前執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * <BR>
     * 約定以外、かつ、<BR>
     * 発注条件区分または元発注条件区分が「2：W指値」、かつ <BR>
     * W指値の有効状態が"ストップ注文有効"（＝切替完了）の場合、設定される<BR>
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
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgPremium_underlyingAssets; 
    
    /**
     * (元発注条件単価)<BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgOrderCondPrice; 
    
    /**
     * (元発注条件演算子)<BR>
     * <BR>
     * 1：以上　@2：以下 <BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合に設定<BR>
     */
    public String orgCondOperator; 
    
    /**
     * (元W指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値 <BR>
     * ※元発注条件区分が2：Ｗ指値の場合に設定<BR>
     */
    public String orgWLimitOrderPriceDiv; 
    
    /**
     * (元W指値用注文単価)<BR>
     * <BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合に設定<BR>
     */
    public String orgWLimitPrice; 
    
    /**
     * (元W指値用執行条件)<BR>
     * <BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * ※元発注条件区分が2：Ｗ指値の場合に設定<BR>
     */
    public String orgWlimitExecCondType;
    
    /**
     * (約定数量)<BR>
     * 約定の場合設定<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     * 約定の場合設定<BR>
     */
    public String execPrice;

    /**
     * (注文有効期限)<BR>
     * 注文履歴の場合設定<BR>
     * 「当日限り」の場合はnullを設定<BR>
     */
    public Date expirationDate;

    /**
     * (備考（決済順序）)<BR>
     * 返済建玉順番を表示<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * 一括返済時の場合設定<BR>
     */
    public String closingOrder;

    /**
     * (受付結果区分)<BR>
     * ※コード値はメッセージ定義フォルダ以下の<BR>
     * 「ﾒｯｾｰｼﾞ定義書_株価指数オプション(共通).xls」の受付結果区分定義を参照<BR>
     */
    public String acceptedResultDiv;
    
    /**
     * (夕場前繰越対象フラグ)<BR>
     * false：夕場前繰越なし<BR>
     * true：夕場前繰越あり<BR>
     */
    public boolean eveningSessionCarryoverFlag;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40C075320261
     */
    public WEB3OptionsChangeCancelHistoryGroup()
    {

    }
}
@
