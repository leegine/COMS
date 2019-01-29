head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOOfferConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO購入申込概況明細メッセージデータ(WEB3IPOOfferConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO購入申込概況明細メッセージデータクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPOOfferConditionUnit extends Message
{
    
    /**
     * 購入申込数量
     */
    public String offerQuantity;
    
    /**
     * 購入申込件数
     */
    public String offerNumber;
    
    /**
     * 購入辞退数量
     */
    public String declineQuantity;
    
    /**
     * 購入辞退件数
     */
    public String declineNumber;
    
    /**
     * 未定数量
     */
    public String undecideQuantity;
    
    /**
     * 未定件数
     */
    public String undecideNumber;
    
    /**
     * 合計数量
     */
    public String totalQuantity;
    
    /**
     * 合計件数
     */
    public String totalNumber;
    
    /**
     * 購入確定数量
     */
    public String decisionQuantity;
    
    /**
     * 購入確定件数
     */
    public String decisionNumber;
    
    /**
     * 辞退落選数量
     */
    public String declineRejectedQuantity;
    
    /**
     * 辞退落選件数
     */
    public String declineRejectedNumber;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40EBAAFA0212
     */
    public WEB3IPOOfferConditionUnit() 
    {
     
    }
}
@
