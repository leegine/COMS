head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOptionsManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注完了リクエスト(WEB3FuturesOptionsManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (先物OP手動発注完了リクエスト)<BR>
 * 先物OP手動発注完了リクエストクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3FuturesOptionsManualCompleteRequest extends WEB3ManualCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_options_manual_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602162240L;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password = null;
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F488920196
     */
    public WEB3FuturesOptionsManualCompleteRequest() 
    {
     
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * 先物OP手動発注完了レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOptionsManualCompleteResponse(this);
    }
}
@
