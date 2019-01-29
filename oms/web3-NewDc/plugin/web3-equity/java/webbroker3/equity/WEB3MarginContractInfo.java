head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  (信用取引建株情報)<BR>
                 :   建株情報一時格納クラス(WEB3MarginContractInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 李松峰 (中訊) 新規作成
                   2005/01/06 岡村和明 (SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.util.Date;


/**
 * （信用取引建株情報）。<BR>
 * <BR>
 * 建株情報一時格納クラス
 * @@version 1.0
 */
public class WEB3MarginContractInfo 
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
    public String standardName;
    
    /**
     * (市場コード)
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定
     */
    public String accountType;
    
    /**
     * (建区分)<BR>
     * 1：買建　@2：売建
     * （ContractTypeEnumにて定義）
     */
    public String contractType;
    
    /**
     * (弁済区分)<BR>
     * 1：制度信用　@2：一般信用
     */
    public String repaymentType;
    
    /**
     * (弁済期限値)<BR>
     * 月指定。<BR>
     * 無期限の場合”9999999”
     */
    public String repaymentNum;
    
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
    public String quantity;
    
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
     * (評価損益)
     */
    public String evaluationIncome;
    
    /**
     * (評価損益(諸経費考慮))<BR>
     * 諸経費を差し引いた評価損益
     */
    public String takeExpensesOffEvaluationIncome;
    
    /**
     * (建手数料)
     */
    public String setupFee;
    
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
    public String transferFee;
    
    /**
     * (管理費)
     */
    public String managementFee;
    
    /**
     * (その他)
     */
    public String other;
    
    /**
     * (決済状態区分)<BR>
     * 0：決済済　@1：未決済　@2：決済中
     */
    public String closingStatusType;
    
    /**
     * (返済可能フラグ)<BR>
     * true：返済可能　@false：返済不可
     */
    public boolean closingPossibleFlag;
    
    /**
     * (現引現渡可能フラグ)<BR>
     * true：現引現渡可能　@false：現引現渡不可
     */
    public boolean swapPossibleFlag;
    
    /**
     * (信用取引建株情報)<BR>
     * コンストラクタ
     * @@roseuid 40EB7FD70197
     */
    public WEB3MarginContractInfo() 
    {
     
    }
}
@
