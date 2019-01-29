head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座情報変更確認リクエスト(WEB3AdminFXAccInfoChangeConfirmRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・FX口座情報変更確認リクエスト) <BR>
 * 管理者・FX口座情報変更確認リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoChangeConfirmRequest extends
    WEB3AdminFXAccInfoChangeCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_change_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * @@roseuid 41E78FE30261
     */
    public WEB3AdminFXAccInfoChangeConfirmRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccInfoChangeConfirmRequest.class);
        
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX口座情報変更確認レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccInfoChangeConfirmResponse(this);
    }
    
    /**
     * (validate) <BR>
     * クエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １） 入力内容のチェック <BR>
     * １－１）スーパークラスのvalidate()をコールする。
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１） 入力内容のチェック 
        //１－１）スーパークラスのvalidate()をコールする。
        super.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
}@
