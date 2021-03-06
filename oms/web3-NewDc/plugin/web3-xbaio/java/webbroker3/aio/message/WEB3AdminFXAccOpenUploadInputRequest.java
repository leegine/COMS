head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・FX口座開設アップロード入力リクエスト(WEB3AdminFXAccOpenUploadInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設アップロード入力リクエスト)<BR>
 * 管理者・FX口座開設アップロード入力リクエストクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadInputRequest extends WEB3GenRequest 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602181050L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_acc_open_upload_input";
    
    /**
     * @@roseuid 43F49A6D0232
     */
    public WEB3AdminFXAccOpenUploadInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6402B2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFXAccOpenUploadInputResponse();        
    }
}
@
