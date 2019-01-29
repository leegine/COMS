head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式残高照会残高合計レスポンス(WEB3EquityBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
				   2006/08/29 李俊　@(中訊)　@仕様変更 モデルNo.971
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * （現物株式残高照会残高合計レスポンス）。<BR>
 * <BR>
 * 現物株式残高照会残高合計レスポンスクラス<BR>
 */
public class WEB3EquityBalanceReferenceTotalResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_balance_reference_total";
    
    /**
     * (特定口座評価額合計)<BR>
     *<BR>
     * 特定口座評価額合計<BR>
     */
    public String capitalGainTotalAsset;
    
    /**
     * (特定口座評価損益合計)<BR>
     *<BR>
     * 特定口座評価損益合計<BR>
     */
    public String capitalGainTotalAppraisalProfitLoss;
    
    /**
     * (一般口座評価額合計)<BR>
     *<BR>
     * 一般口座評価額合計<BR>
     */
    public String normalAccountTotalAsset;
    
    /**
     * (一般口座評価損益合計)<BR>
     *<BR>
     * 一般口座評価損益合計<BR>
     */
    public String normalAccountTotalAppraisalProfitLoss;
    
    /**
     * (ストックオプション口座評価額合計)<BR>
     *<BR>
     *ストックオプション口座評価額合計<BR>
     */
    public String stockOptionTotalAsset;
    
    /**
     * (ストックオプション口座評価損益合計)<BR>
     *<BR>
     *ストックオプション口座評価損益合計<BR>
     */
    public String stockOptionTotalAppraisalProfitLoss;
    
    /**
     * @@roseuid 4206C8A903C5<BR>
     */
    public WEB3EquityBalanceReferenceTotalResponse() 
    {
     
    }
    
    public WEB3EquityBalanceReferenceTotalResponse(WEB3EquityBalanceReferenceTotalRequest l_request) 
    {
        super(l_request); 
    }
}
@
