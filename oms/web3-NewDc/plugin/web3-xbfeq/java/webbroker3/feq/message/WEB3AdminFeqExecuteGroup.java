head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文明細（管理者）(WEB3AdminFeqExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/


package webbroker3.feq.message;


/**
 * (外国株式注文明細（管理者）)<BR>
 * 外国株式注文明細（管理者）クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqExecuteGroup extends WEB3FeqOrderCommonUnit 
{    
    /**
     * (注文履歴番号)<BR>
     * 注文履歴番号<BR>
     * （訂正番号）
     */
    public String orderActionId;
    
    /**
     * (約定数量)<BR>
     * 約定数量<BR>
     * <BR>
     * ※未出来注文の場合は、null。
     */
    public String execQuantity;
    
    /**
     * (受渡代金)<BR>
     * 受渡代金
     */
    public String deliveryPrice;
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）
     */
    public String foreignDeliveryPrice;
    
    /**
     * (処理状況)<BR>
     * 処理状況
     */
    public String transactionStateType;
    
    /**
     * (外国株式注文明細（管理者）)<BR>
     * コンストラクタ
     * @@roseuid 4201EF9D02F3
     */
    public WEB3AdminFeqExecuteGroup() 
    {
     
    }
}
@
