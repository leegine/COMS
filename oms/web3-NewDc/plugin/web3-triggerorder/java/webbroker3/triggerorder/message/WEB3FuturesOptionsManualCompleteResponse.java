head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOptionsManualCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注完了レスポンス(WEB3FuturesOptionsManualCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (先物OP手動発注完了レスポンス)<BR>
 * 先物OP手動発注完了レスポンスクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3FuturesOptionsManualCompleteResponse extends WEB3GenResponse 
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
     * (更新時間)<BR>
     * 更新時間<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (先物OP手動発注Unit)<BR>
     * 先物OP手動発注Unit<BR>
     */
    public WEB3FuturesOptionsManualUnit manualUnits[];
    
    /**
     * コンストラクタ<BR>
     * @@roseuid 43F488920271
     */
    public WEB3FuturesOptionsManualCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FuturesOptionsManualCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
