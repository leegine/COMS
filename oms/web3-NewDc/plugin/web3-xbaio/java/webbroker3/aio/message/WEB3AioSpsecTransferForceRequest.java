head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSpsecTransferForceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制リクエスト(WEB3AioSpsecTransferForceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (特定口座振替強制リクエスト)<BR>
 * 特定口座振替強制リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioSpsecTransferForceRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_spsec_transfer_force";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502041628L;
    
    /**
     * @@roseuid 41B045C30399
     */
    public WEB3AioSpsecTransferForceRequest() 
    {
     
    }

    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 特定口座振替強制レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioSpsecTransferForceResponse(this);
    }
}
@
