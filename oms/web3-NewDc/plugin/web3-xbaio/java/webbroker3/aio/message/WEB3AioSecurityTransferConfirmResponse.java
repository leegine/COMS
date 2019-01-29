head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替確認レスポンス(WEB3AioSecurityTransferConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (証券振替確認レスポンス)<BR>
 * 証券振替確認レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412070941L; 
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * (注文ID)<BR>
     * 確認時に取得した注文ID
     */
    public String orderId;
    
    /**
     * @@roseuid 41B0255E03C8
     */
    public WEB3AioSecurityTransferConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioSecurityTransferConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
