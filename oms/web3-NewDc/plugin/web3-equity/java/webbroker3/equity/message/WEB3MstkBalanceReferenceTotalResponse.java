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
filename	WEB3MstkBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資残高合計レスポンス(WEB3MstkBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * （株式ミニ投資残高照会残高合計レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資残高照会残高合計レスポンスクラス<BR>
 */
public class WEB3MstkBalanceReferenceTotalResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_balance_reference_total";

    /**
     * (特定口座評価額合計)<BR>
     * <BR>
     * 特定口座評価額合計<BR>
     */
    public String capitalGainTotalAsset;
    
    /**
     * (特定口座評価損益合計)<BR>
     * <BR>
     * 特定口座評価損益合計<BR>
     */
    public String capitalGainTotalAppraisalProfitLoss;
    
    /**
     * (一般口座評価額合計)<BR>
     * <BR>
     * 一般口座評価額合計<BR>
     */
    public String normalAccountTotalAsset;
    
    /**
     * (一般口座評価損益合計)<BR>
     * <BR>
     * 一般口座評価損益合計<BR>
     */
    public String normalAccountTotalAppraisalProfitLoss;
    
    /**
     * @@roseuid 4206CC9902CB<BR>
     */
    public WEB3MstkBalanceReferenceTotalResponse() 
    {
     
    }
    
    public WEB3MstkBalanceReferenceTotalResponse(WEB3MstkBalanceReferenceTotalRequest l_request)
    {
        super(l_request);
    }
}
@
