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
filename	WEB3MarginBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高合計レスポンス(WEB3MarginBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * （信用取引残高照会残高合計レスポンス）。<BR>
 * <BR>
 * 信用取引残高照会残高合計レスポンスクラス<BR>
 */
public class WEB3MarginBalanceReferenceTotalResponse extends WEB3GenResponse
{
            
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_balance_reference_total";
    
    /**
     * (買建株総額)<BR>
     *<BR>
     * 買建株総額<BR>
     */
    public String buyTotalPrice;
    
    /**
     * (売建株総額)<BR>
     *<BR>
     * 売建株総額<BR>
     */
    public String sellTotalPrice;
    
    /**
     * (特定口座建株総額)<BR>
     *<BR>
     * 特定口座建株総額<BR>
     */
    public String capitalGainTotalPrice;
    
    /**
     * (一般口座建株総額)<BR>
     *<BR>
     * 一般口座建株総額<BR>
     */
    public String normalAccountTotalPrice;
    
    /**
     * (建株総額合計)<BR>
     *<BR>
     * 建株総額合計<BR>
     */
    public String totalPrice;
    
    /**
     * (買建株評価損益合計)<BR>
     *<BR>
     * 買建株評価損益合計<BR>
     */
    public String buyTotalAssetProfitLoss;
    
    /**
     * (売建株評価損益合計)<BR>
     *<BR>
     * 売建株評価損益合計<BR>
     */
    public String sellTotalAssetProfitLoss;
    
    /**
     * (特定口座評価損益合計)<BR>
     *<BR>
     * 特定口座評価損益合計<BR>
     */
    public String capitalGainTotalAssetProfitLoss;
    
    /**
     * (一般口座評価損益合計)<BR>
     *<BR>
     * 一般口座評価損益合計<BR>
     */
    public String normalAccountTotalAssetProfitLoss;
    
    /**
     * (建株評価損益合計)<BR>
     *<BR>
     * 建株評価損益合計<BR>
     */
    public String totalAssetProfitLoss;
    
    /**
     * (買建株評価損益合計（諸経費考慮）)<BR>
     *<BR>
     * 買建株評価損益合計（諸経費考慮）<BR>
     */
    public String buyTotalAssetProfitLossCost;
    
    /**
     * (売建株評価損益合計（諸経費考慮）)<BR>
     *<BR>
     * 売建株評価損益合計（諸経費考慮）<BR>
     */
    public String sellTotalAssetProfitLossCost;
    
    /**
     * (特定口座評価損益合計（諸経費考慮）)<BR>
     *<BR>
     * 特定口座評価損益合計（諸経費考慮）<BR>
     */
    public String capitalGainTotalAssetProfitLossCost;
    
    /**
     * (一般口座評価損益合計（諸経費考慮）)<BR>
     *<BR>
     * 一般口座評価損益合計（諸経費考慮）<BR>
     */
    public String normalAccountTotalAssetProfitLossCost;
    
    /**
     * (建株評価損益合計（諸経費考慮）)<BR>
     *<BR>
     * 建株評価損益合計（諸経費考慮）<BR>
     */
    public String totalAssetProfitLossCost;
    
    /**
     * @@roseuid 4206CDBB0182<BR>
     */
    public WEB3MarginBalanceReferenceTotalResponse() 
    {
     
    }
    
    public WEB3MarginBalanceReferenceTotalResponse(WEB3MarginBalanceReferenceTotalRequest l_request)
    {
        super(l_request);
    }
}
@
