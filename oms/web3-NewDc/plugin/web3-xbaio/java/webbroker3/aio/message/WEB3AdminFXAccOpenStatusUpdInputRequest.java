head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.14.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenStatusUpdInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座開設ステータス更新入力リクエスト(WEB3AdminFXAccOpenStatusUpdInputRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・FX口座開設ステータス更新入力リクエスト) <BR>
 * 管理者・FX口座開設ステータス更新入力リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenStatusUpdInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_status_upd_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (FX検索条件) <BR>
     * FX検索条件
     */
    public WEB3FXSearchConditionUnit fxSearchConditionUnit;

    /**
     * @@roseuid 41E78F640119
     */
    public WEB3AdminFXAccOpenStatusUpdInputRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenStatusUpdInputRequest.class);
    
    /**
     * (validate) <BR>
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １） FX検索条件のチェック <BR>
     * １－１） 未入力の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01748 <BR>
     * <BR>
     * １－２） FX検索条件.validate()メソッドをコールする。
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１） FX検索条件のチェック 
        //１－１） 未入力の場合、例外をスローする。
        if (this.fxSearchConditionUnit == null)
        {
            log.debug("FX検索条件が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01748,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "FX検索条件が未指定です。");
        }
        
        //１－２） FX検索条件.validate()メソッドをコールする。
        this.fxSearchConditionUnit.validate();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX口座開設ステータス更新入力レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXAccOpenStatusUpdInputResponse(this);
    }
}@
