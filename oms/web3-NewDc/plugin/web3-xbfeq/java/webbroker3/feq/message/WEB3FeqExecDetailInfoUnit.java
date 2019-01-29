head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式約定詳細（管理者）(WEB3FeqExecDetailInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/
package webbroker3.feq.message;

import java.util.Date;


/**
 * (外国株式約定詳細（管理者）)<BR>
 * 外国株式約定詳細（管理者）クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqExecDetailInfoUnit extends WEB3FeqExecuteUnit 
{
    
    /**
     * (約定ID)<BR>
     * 約定ID
     */
    public String execId;
    
    /**
     * (約定番号)<BR>
     * 約定番号
     */
    public String execNo;
    
    /**
     * (売買代金)<BR>
     * 売買代金
     */
    public String foreignTradePrice;
    
    /**
     * (現地手数料)<BR>
     * 現地手数料
     */
    public String localCommission;
    
    /**
     * (現地取引税)<BR>
     * 現地取引税
     */
    public String localTradingTax;
    
    /**
     * (その他コスト1)<BR>
     * その他コスト1
     */
    public String otherCost1;
    
    /**
     * (その他コスト2)<BR>
     * その他コスト2
     */
    public String otherCost2;
    
    /**
     * (清算代金（円貨）)<BR>
     * 清算代金（円貨）
     */
    public String clearUpPrice;
    
    /**
     * (清算代金（外貨）)<BR>
     * 清算代金（外貨）
     */
    public String foreignClearUpPrice;
    
    /**
     * (国内手数料（円貨）)<BR>
     * 国内手数料（円貨）
     */
    public String commission;
    
    /**
     * (国内手数料（外貨）)<BR>
     * 国内手数料（外貨）
     */
    public String foreignCommission;
    
    /**
     * (消費税（円貨）)<BR>
     * 消費税（円貨）
     */
    public String consumptionTax;
    
    /**
     * (消費税（外貨）)<BR>
     * 消費税（外貨）
     */
    public String foreignConsumptionTax;
    
    /**
     * (受渡金額（円貨）)<BR>
     * 受渡金額（円貨）
     */
    public String deliveryPrice;
    
    /**
     * (受渡金額（外貨）)<BR>
     * 受渡金額（外貨）
     */
    public String foreignDeliveryPrice;
    
    /**
     * (国内受渡日)<BR>
     * 国内受渡日
     */
    public Date deliveryDate;
    
    /**
     * (外国株式約定詳細（管理者）)<BR>
     * コンストラクタ
     * @@roseuid 4281E60D03A8
     */
    public WEB3FeqExecDetailInfoUnit() 
    {
     
    }
}
@
