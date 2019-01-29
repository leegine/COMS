head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式残高合計レスポンス(WEB3FeqBalanceReferenceTotalResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式残高合計レスポンス)<BR>
 * 外国株式残高合計レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBalanceReferenceTotalResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_balanceReferenceTotal";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (特定口座評価額合計)<BR>
     * 特定口座評価額合計<BR>
     */
    public String capitalGainTotalAsset;
    
    /**
     * (特定口座評価損益合計)<BR>
     * 特定口座評価損益合計<BR>
     */
    public String capitalGainTotalAppraisalProfitLoss;
    
    /**
     * (一般口座評価額合計)<BR>
     * 一般口座評価額合計<BR>
     */
    public String normalAccountTotalAsset;
    
    /**
     * (一般口座評価損益合計)<BR>
     * 一般口座評価損益合計<BR>
     */
    public String normalAccountTotalAppraisalProfitLoss;
    
    /**
     * @@roseuid 42CE3A04031C
     */
    public WEB3FeqBalanceReferenceTotalResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqBalanceReferenceTotalResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
