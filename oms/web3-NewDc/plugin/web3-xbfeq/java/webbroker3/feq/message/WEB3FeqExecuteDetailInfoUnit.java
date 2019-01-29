head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式約定詳細情報(WEB3FeqExecuteDetailInfoUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー 
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.464  
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (外国株式約定詳細情報)<BR>
 * 外国株式約定詳細情報クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqExecuteDetailInfoUnit extends Message 
{
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * (現地受渡日)<BR>
     * 現地受渡日<BR>
     */
    public Date localDeliveryDate;
    
    
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
     * (約定代金)<BR>
     * 約定代金<BR>
     */
    public String execAmount;
    
    /**
     * (約定状態区分)<BR>
     * 約定状態区分<BR>
     * <BR>
     * 0：未約定<BR>
     * 1：一部成立<BR>
     * 2：全部成立<BR>
     * 3：約定処理中(一部成立)<BR>
     * 4：約定処理中(全部成立)<BR>
     */
    public String execType;
    
    /**
     * (受渡代金)<BR>
     * 受渡代金<BR>
     */
    public String deliveryPrice;
    
    /**
     * (扱者コード)<BR>
     * 扱者コード<BR>
     */
    public String traderCode;  
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）<BR>
     */
    public String foreignDeliveryPrice;
    
    /**
     * (現地手数料)<BR>
     * 現地手数料<BR>
     */
    public String localCommission;
    
    /**
     * (現地取引税)<BR>
     * 現地取引税<BR>
     */
    public String localTradingTax;
    
    /**
     * (その他コスト１)<BR>
     * その他コスト１<BR>
     */
    public String otherCost1;
    
    /**
     * (その他コスト２)<BR>
     * その他コスト２<BR>
     */
    public String otherCost2;
    
    /**
     * (清算代金)<BR>
     * 清算代金<BR>
     */
    public String clearUpPrice;
    
    /**
     * (清算代金（外貨）)<BR>
     * 清算代金（外貨）<BR>
     */
    public String foreignClearUpPrice;
    
    /**
     * (国内手数料)<BR>
     * 国内手数料<BR>
     */
    public String commission;
    
    /**
     * (国内手数料消費税)<BR>
     * 国内手数料消費税<BR>
     */
    public String commissionConsumptionTax;
    
    /**
     * (国内手数料（外貨）)<BR>
     * 国内手数料（外貨）<BR>
     */
    public String foreignCommission;
    
    /**
     * (国内手数料消費税（外貨）)<BR>
     * 国内手数料消費税（外貨）<BR>
     */
    public String foreignCommissionConsumptionTax;
    
    /**
     * (約定明細一覧)<BR>
     * 外国株式約定詳細（管理者）の配列<BR>
     */
    public WEB3FeqExecDetailInfoUnit[] execDetailList;
    
    /**
     * (約定為替レート)<BR>
     * 約定為替レート<BR>
     */
    public String execExchangeRate;
    
    /**
     * (外国株式約定詳細情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 42A537780118
     */
    public WEB3FeqExecuteDetailInfoUnit() 
    {
     
    }
}
@
