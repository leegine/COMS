head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文詳細情報(WEB3FeqOrderDetailInfoUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

/**
 * (外国株式注文詳細情報)<BR>
 * 外国株式注文詳細情報クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqOrderDetailInfoUnit extends WEB3FeqOrderCommonUnit 
{
    
    /**
     * (注文経路区分)<BR>
     * 注文経路区分<BR>
     * <BR>
     * 1：コールセンター<BR>
     * 2：ＰＣ<BR>
     * 3：スリングショット<BR>
     * 4：i-mode<BR>
     * 5：Vodafone<BR>
     * 6：AU<BR>
     * 9：HOST<BR>
     */
    public String orderRootDiv;
    
    /**
     * (注文状態区分)<BR>
     * 注文状態区分<BR>
     * <BR>
     * 0：その他<BR>
     * 1：受付済（新規注文）<BR>
     * 2：発注中（新規注文）<BR>
     * 3：発注済（新規注文）<BR>
     * 6：発注失敗（新規注文）<BR>
     * 7：受付済（変更注文）<BR>
     * 8：発注中（変更注文）<BR>
     * 10：発注済（変更注文）<BR>
     * 11：発注失敗（変更注文）<BR>
     * 12：受付済（取消注文）<BR>
     * 13：発注中（取消注文）<BR>
     * 14：発注済（取消注文）<BR>
     * 15：発注失敗（取消注文）<BR>
     * 20：一部失効<BR>
     * 21：全部失効<BR>
     * 22：無効<BR>
     * 50：繰越済<BR>
     * 51：繰越失敗<BR>
     */
    public String orderState;
    
    /**
     * (訂正取消区分)<BR>
     * 訂正取消区分<BR>
     * <BR>
     * 0：初期値<BR>
     * 1：取消中<BR>
     * 2：一部取消完了<BR>
     * 3：全部取消完了<BR>
     * 4：取消失敗<BR>
     * 5：訂正中<BR>
     * 6：一部訂正完了<BR>
     * 7：全部訂正完了<BR>
     * 8：訂正失敗<BR>
     * 9：エラー<BR>
     * 7：全部訂正完了<BR>
     * 8：訂正失敗<BR>
     * 9：エラー<BR>
     */
    public String changeCancelDiv;
    
    /**
     * (繰越エラーコード)<BR>
     * 繰越エラーコード<BR>
     * <BR>
     * ※注文状態区分が”繰越失敗”の場合セット<BR>
     * <BR>
     * 0001：値幅エラー<BR>
     * 0002：預り金不足エラー<BR>
     * 0003：残高不足エラー<BR>
     * 0006：売買停止銘柄エラー<BR>
     * 0008：買付余力エラー<BR>
     * 0009：売付可能数量エラー<BR>
     * 0010：特定口座エラー<BR>
     * 0011：注文繰越スキップ銘柄エラー<BR>
     * 0012：外貨不足エラー<BR>
     * 9001：その他エラー <BR>
     */
    public String transferErrCode;
    
    /**
     * (外国株式注文詳細情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 42A530EE03C7
     */
    public WEB3FeqOrderDetailInfoUnit() 
    {
     
    }
}
@
