head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadCancelResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・FX口座開設アップロード中止レスポンス(WEB3AdminFXAccOpenUploadCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設アップロード中止レスポンス)<BR>
 * 管理者・FX口座開設アップロード中止レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadCancelResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602181050L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fx_acc_open_upload_cancel";
    /**
     * @@roseuid 43F49A700222
     */
    public WEB3AdminFXAccOpenUploadCancelResponse() 
    {
    
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminFXAccOpenUploadCancelResponse(WEB3AdminFXAccOpenUploadCancelRequest l_request)
    {
        super(l_request);
    }
}
@
