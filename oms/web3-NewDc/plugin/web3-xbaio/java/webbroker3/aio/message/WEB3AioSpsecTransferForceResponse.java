head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制レスポンス(WEB3AioSpsecTransferForceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (特定口座振替強制レスポンス)<BR>
 * 特定口座振替強制レスポンスクラス  
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_spsec_transfer_force";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502041626L; 
    
    /**
     * @@roseuid 41B045C4005D
     */
    public WEB3AioSpsecTransferForceResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
	 * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioSpsecTransferForceResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }   
}
@
