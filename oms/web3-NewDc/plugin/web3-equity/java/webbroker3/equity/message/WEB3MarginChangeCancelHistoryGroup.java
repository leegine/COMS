head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCancelHistoryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文約定履歴明細クラス(WEB3MarginChangeCancelHistoryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
Revesion History : 2006/11/02 張騰宇(中訊) モデル 948,999
Revesion History : 2007/06/05 何文敏 (中訊) モデル1164
Revesion History : 2007/07/24 何文敏 (中訊) モデル1188
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引注文約定履歴明細）。<br>
 * <br>
 * 信用取引注文約定履歴明細クラス
 * @@version 1.0
 */
public class WEB3MarginChangeCancelHistoryGroup extends Message 
{
   
    /**
     * (注文No)<BR>
     */
    public String orderActionId;
    
    /**
     * (注文受付日時)<BR>
     */
    public Date orderDate;
    
    /**
     * (注文内容区分)<BR>
     * 00：新規注文 01：注文受付 02：新規注文(失敗) 03：訂正注文 <BR>
     * 04：訂正受付 05：訂正完了 06：訂正注文(失敗) 07：取消注文 <BR>
     * 08：取消受付 09：取消完了 10：取消注文(失敗) 11：一部約定 <BR>
     * 12：全部約定 13：約定取消 14：失効 15：失効取消  <BR>
     * 16：無効 17：注文繰越 18：注文繰越(失敗) <BR>
     * 19：約定取消(現引現渡)<BR>
     * 20：発注中 21：発注遅延 <BR>
     * 22：切替遅延 23：切替注文 24：切替受付 25：切替完了 <BR>
     * 26：切替注文(失敗) 27：ストップ注文失効 29：強制失効 99：その他<BR>
     */
    public String orderType;
    
    /**
     * (値段条件)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@7:成行残数取消 <BR>
     */
    public String priceCondType;
    
    /**
     * (執行条件)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * 逆指値用発注条件単価
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以上　@2：以下 <BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (指値用発注条件単価) <BR>
     * Ｗ指値用発注条件単価
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * 1：以上　@2：以下 <BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (W指値用注文単価区分)<BR>
     * 0：成行　@1：指値
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W指値用注文単価) <BR>
     * W指値用注文単価
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 約定以外、かつ、発注条件区分が、「2：W指値」の場合設定される。<BR>
     */
    public String wlimitExecCondType;

    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * 約定以外、かつ、<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される。<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 約定以外、かつ、<BR>
     * 発注条件区分または元発注条件区分が「2：W指値」、かつ<BR>
     * W指値の有効状態が"ストップ注文有効"（＝切替完了）の場合、設定される。<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * <BR>
     * 約定以外、かつ、<BR>
     * 発注条件区分または元発注条件区分が「2：W指値」、かつ<BR>
     * W指値の有効状態が"ストップ注文有効"（＝切替完了）の場合、設定される。<BR>
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
    public String orgOrderCondOperator;

    /**
     * (元Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (元Ｗ指値用注文単価)<BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される<BR>
     */
    public String orgWlimitPrice;

    /**
     * (元Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitExecCondType; 

    /**
     * (注文株数)<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * <BR>
     * 0：成行　@　@1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     */
    public String limitPrice;
    
    /**
     * (約定株数)<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     */
    public String execPrice;
    
    /**
     * (注文有効期限)<BR>
     * 期限<BR>
     * <BR>
     * YYYY/MM/DD形式で表示<BR>
     */
    public Date expirationDate;
    
    /**
     * (受付結果区分)<BR>
     * 0000：正常 1001：受付エラー 1002：訂正エラー 1003：取消エラー 1004：切替エラー<BR>
     * 0001：値幅エラー 0002：預り金不足エラー <BR>
     * 0003：株式残高不足エラー 0004：保証金不足エラー <BR>
     * 0005：建株残高不足エラー 0006：売買停止銘柄エラー <BR>
     * 0007：市場変更銘柄エラー 0008：買付余力エラー 
     * 0009：売付可能数量エラー 0010：特定口座エラー 0011：注文繰越スキップ銘柄エラー <BR>
     * 0012：二階建チェックエラー　@0014：呼値チェックエラー<BR>
     * 0015：空売りチェックエラー　@0016：決済期日到来済エラー<BR>
     * 9001：その他エラー<BR>
     */
    public String acceptedResultDiv;
    
    /**
     * @@roseuid 414047D8011E
     */
    public WEB3MarginChangeCancelHistoryGroup() 
    {
     
    }
}
@
