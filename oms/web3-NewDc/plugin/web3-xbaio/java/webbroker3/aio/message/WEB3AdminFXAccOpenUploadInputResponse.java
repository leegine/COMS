head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・FX口座開設アップロード入力レスポンス(WEB3AdminFXAccOpenUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・FX口座開設アップロード入力レスポンス)<BR>
 * 管理者・FX口座開設アップロード入力レスポンスクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadInputResponse extends WEB3GenResponse 
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
     * (アップロード履歴一覧)<BR>
     */
    public WEB3FXUploadHistoryUnit uploadHistoryUnit;
    
    /**
     * @@roseuid 43F49A70001F
     */
    public WEB3AdminFXAccOpenUploadInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminFXAccOpenUploadInputResponse(WEB3AdminFXAccOpenUploadInputRequest l_request)
    {
        super(l_request);
    }
}
@
