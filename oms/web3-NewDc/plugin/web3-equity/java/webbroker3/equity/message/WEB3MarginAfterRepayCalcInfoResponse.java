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
filename	WEB3MarginAfterRepayCalcInfoResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引返済後余力計算情報レスポンス(WEB3MarginAfterRepayCalcInfoResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 中尾寿彦 (SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;

/**
 * （信用取引返済後余力計算情報レスポンス）。<BR>
 * <BR>
 * 信用取引返済後余力計算情報レスポンスクラス
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3MarginAfterRepayCalcInfoResponse extends WEB3GenResponse
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_after_repay_calc_info";
    
    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200504201900L;
    
    /**
     * (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。<BR>
     */
    public WEB3MarginSettleContractOrderSpec orderSpec;
    
    /**
     * (信用返済更新インタセプタ)<BR>
     * 信用返済更新インタセプタオブジェクト。<BR>
     */
    public WEB3MarginCloseMarginUpdateInterceptor interceptor;
    
    /**
     * (デフォルトコンストラクタ)<BR>
     */
    public WEB3MarginAfterRepayCalcInfoResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - (リクエストオブジェクト)
     */
    public WEB3MarginAfterRepayCalcInfoResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
