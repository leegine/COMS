head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引建株照会明細(WEB3MarginContractReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引建株照会明細）。<br>
 * <br>
 * 信用取引建株照会明細クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginContractReferenceUnit extends Message 
{
    
    /**
     * (ID)<BR>
     * 建株ＩＤ
     */
    public String id;
    
    /**
     * (銘柄コード)
     */
    public String productCode;
    
    /**
     * (銘柄名)
     */
    public String productName;
    
    /**
     * (市場コード)
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定
     */
    public String taxType;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建<BR>
     * （ContractTypeEnumにて定義）<BR>
     */
    public String contractType;
    
    /**
     * (弁済)<BR>
     * 信用取引弁済
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (建日)
     */
    public Date openDate;
    
    /**
     * (建単価)
     */
    public String contractPrice;
    
    /**
     * (建株数)
     */
    public String contractOrderQuantity;
    
    /**
     * (期日)<BR>
     * 一般信用、無期限の場合”99991231”
     */
    public Date closeDate;
    
    /**
     * (建代金)
     */
    public String contractExecPrice;
    
    /**
     * (建手数料)
     */
    public String contractCommission;
    
    /**
     * (順日歩)
     */
    public String interestFee;
    
    /**
     * (逆日歩)
     */
    public String payInterestFee;
    
    /**
     * (貸株料)
     */
    public String loanEquityFee;
    
    /**
     * (書換料)
     */
    public String setupFee;
    
    /**
     * (管理費)
     */
    public String managementFee;
    
    /**
     * (その他)
     */
    public String otherwise;
    
    /**
     * (評価損益)
     */
    public String appraisalProfitLoss;
    
    /**
     * (決済状態区分)<BR>
     * 0：決済済　@1：未決済　@2：決済中
     */
    public String settlementState;
    
    /**
     * (返済可能フラグ)<BR>
     * true：返済可能　@false：返済不可
     */
    public boolean closeMarginFlag;
    
    /**
     * (現引現渡可能フラグ)<BR>
     * true：現引現渡可能　@false：現引現渡不可
     */
    public boolean swapFlag;
    
    /**
     * (信用取引建株照会明細)<BR>
     * コンストラクタ。
     * @@return webbroker3.margin.message.WEB3MarginContractReferenceUnit
     * @@roseuid 40E3F0B7013F
     */
    public WEB3MarginContractReferenceUnit() 
    {
     
    }
}
@
