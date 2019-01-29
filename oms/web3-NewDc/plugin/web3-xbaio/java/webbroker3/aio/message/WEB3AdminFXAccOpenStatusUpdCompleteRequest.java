head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座開設ステータス更新完了リクエスト(WEB3AdminFXAccOpenStatusUpdCompleteRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・FX口座開設ステータス更新完了リクエスト) <BR>
 * 管理者・FX口座開設ステータス更新完了リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdCompleteRequest extends
    WEB3AdminFXAccOpenStatusUpdCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_status_upd_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (暗証番号) <BR>
     * 画面から入力された暗証番号
     */
    public String password;

    /**
     * @@roseuid 41E78F650186
     */
    public WEB3AdminFXAccOpenStatusUpdCompleteRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenStatusUpdCompleteRequest.class);
        
    /**
     * (validate) <BR>
     * リクエストデータの整合性をチェックする。 <BR>
     * １） スーパークラス.validate()メソッドをコールする。
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41BE6AEB0330
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１） スーパークラス.validate()メソッドをコールする。
        super.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX口座開設ステータス更新完了レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccOpenStatusUpdCompleteResponse(this);
    }
}@
