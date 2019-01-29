head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOptionsManualConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注確認レスポンス(WEB3FuturesOptionsManualConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (先物OP手動発注確認レスポンス)<BR>
 * 先物OP手動発注確認レスポンスクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3FuturesOptionsManualConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_options_manual_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602162240L;
    
    /**
     * (先物OP手動発注Unit)<BR>
     * 先物OP手動発注Unit<BR>
     */
    public WEB3FuturesOptionsManualUnit manualUnits[];
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F4889201F4
     */
    public WEB3FuturesOptionsManualConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FuturesOptionsManualConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
