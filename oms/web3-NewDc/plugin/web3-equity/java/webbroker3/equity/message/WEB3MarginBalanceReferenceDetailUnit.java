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
filename	WEB3MarginBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高照会明細(WEB3MarginBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;


/**
 * （信用取引残高照会明細）。<BR>
 * <BR>
 * 信用取引残高照会明細クラス<BR>
 */
public class WEB3MarginBalanceReferenceDetailUnit extends WEB3MarginContractReferenceUnit 
{
    
    /**
     * (時価)<BR>
     *<BR>
     * 時価<BR>
     */
    public String currentPrice = null;
    
    /**
     * (前日比)<BR>
     * <BR>
     * 前日比<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (時価取得時間)<BR>
     * <BR>
     * 時価取得時間<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (新規建可能フラグ)<BR>
     * <BR>
     * true：新規建可能　@false：新規建不可<BR>
     */
    public boolean tradingFlag;
    
    /**
     * (評価損益（諸経費考慮）)<BR>
     * <BR>
     * 諸経費を差し引いた評価損益<BR>
     */
    public String appraisalProfitLossCost = null;
    
    /**
     * コンストラクタ。<BR>
     * @@roseuid 41C189020071<BR>
     */
    public WEB3MarginBalanceReferenceDetailUnit() 
    {
     
    }
}
@
