head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約完了レスポンスクラス(WEB3MutualSellCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 黄建 (中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー 
                   2004/12/07 于美麗 (中訊) 残対応
*/
package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託解約完了レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3MutualSellCompleteResponse extends WEB3GenResponse {
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111710L;
    
    /**
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * 識別番号
     */
    public String orderActionId;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A89E3201A3
     */
    public WEB3MutualSellCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualSellCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
