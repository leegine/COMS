head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替通知レスポンス(WEB3AioSecurityTransferNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (証券振替通知レスポンス)<BR>
 * 証券振替通知レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_notify";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412070953L; 
    
    /**
     * @@roseuid 41B045C400FA
     */
    public WEB3AioSecurityTransferNotifyResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioSecurityTransferNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }   
}
@
