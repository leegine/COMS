head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeCancelHistoryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文約定履歴明細(WEB3FeqChangeCancelHistoryGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.471
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (外国株式注文約定履歴明細)<BR>
 * 外国株式注文約定履歴明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqChangeCancelHistoryGroup extends Message 
{
    
    /**
     * (注文履歴ID)<BR>
     * 注文履歴ID<BR>
     */
    public String orderActionId;
    
    /**
     * (受付日時)<BR>
     * 受付日時<BR>
     */
    public Date orderDate;
    
    /**
     * (履歴状態区分)<BR>
     * 履歴状態区分<BR>
     * <BR>
     * 00：新規注文<BR>
     * 01：注文受付<BR>
     * 02：新規注文(失敗)<BR>
     * 03：訂正注文<BR>
     * 04：訂正受付<BR>
     * 05：訂正完了<BR>
     * 06：訂正注文(失敗)<BR>
     * 07：取消注文<BR>
     * 08：取消受付<BR>
     * 09：取消完了<BR>
     * 10：取消注文(失敗)<BR>
     * 11：一部約定<BR>
     * 12：全部約定<BR>
     * 13：約定取消<BR>
     * 14：失効<BR>
     * 15：失効取消<BR>
     * 16：無効<BR>
     * 17：注文繰越<BR>
     * 18：注文繰越(失敗)<BR>
     * 19：約定取消(現引現渡)<BR>
     * 20：発注中<BR>
     * 21：発注遅延<BR>
     * 22：切替遅延<BR>
     * 23：切替注文<BR>
     * 24：切替受付<BR>
     * 25：切替完了<BR>
     * 26：切替注文(失敗)<BR>
     * 27：ストップ注文失効<BR>
     * 28：注文受付取消<BR>
     * 29：強制失効<BR>
     * 30：承認済<BR>
     * 31：約定処理中（一部約定）<BR>
     * 32：約定処理中（全部約定）<BR>
     * 99：その他<BR>
     */
    public String orderType;
    
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
     * (発注条件)<BR>
     * 発注条件<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (発注条件単価)<BR>
     * 発注条件単価<BR>
     * <BR>
     * ※発注条件が”逆指値”もしくは”W指値”の場合、設定<BR>
     */
    public String orderCondPrice;
    
    /**
     * (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * <BR>
     * 1：以上<BR>
     * 2：以下<BR>
     * <BR>
     * ※発注条件が”逆指値”もしくは”W指値”の場合、設定<BR>
     */
    public String condOperator;
    
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
     * (注文数量)<BR>
     * 注文数量<BR>
     */
    public String orderQuantity;
    
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
     * (約定数量)<BR>
     * 約定数量<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice;
    
    /**
     * (約定日時)<BR>
     * 約定日時<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     * <BR>
     * ※注文期限区分が”出来るまで注文”の場合、設定<BR>
     */
    public Date expirationDate;
    
    /**
     * (受付結果区分)<BR>
     * 受付結果区分<BR>
     * <BR>
     * 0000：正常<BR>
     * 1001：受付エラー<BR>
     * 1002：訂正エラー<BR>
     * 1003：取消エラー<BR>
     * 0001：値幅エラー<BR>
     * 0002：預り金不足エラー<BR>
     * 0003：残高不足エラー<BR>
     * 0006：売買停止銘柄エラー<BR>
     * 0008：買付余力エラー<BR>
     * 0009：売付可能数量エラー<BR>
     * 0010：特定口座エラー<BR>
     * 0011：注文繰越スキップ銘柄エラー<BR>
     * 0012：外貨不足エラー<BR>
     * 9001：その他エラー<BR>
     */
    public String acceptedResultDiv;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (外国株式注文約定履歴明細)
     * @@roseuid 4289E3DC0278
     */
    public WEB3FeqChangeCancelHistoryGroup() 
    {
     
    }
}
@
