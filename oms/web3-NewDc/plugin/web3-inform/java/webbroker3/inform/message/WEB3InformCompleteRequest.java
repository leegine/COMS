head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡完了リクエスト(WEB3InformCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡完了リクエスト)<BR>
 * 連絡完了リクエストクラス
 */
public class WEB3InformCompleteRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_complete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;
    /**
     * (連絡情報)<BR>
     * 連絡情報<BR>
     */
    public WEB3InformDetailInfoUnit informInfoUnit;

    /**
     * (暗証番号)<BR>
     * 画面にて入力された暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 41EE625C00BB
     */
    public WEB3InformCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {

        return new WEB3InformCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）連絡情報 == null の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01816<BR>
     * <BR>
     * ２）連絡情報.validate()をコールする。<BR>
     * <BR>
     * @@roseuid 418F525D0388
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // １）連絡情報 == null の場合、例外をスローする
        if (this.informInfoUnit == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01816,
                this.getClass().getName() + STR_METHOD_NAME,
                "連絡情報がnullの値である。");
        }

        // ２）連絡情報.validate()をコールする
        this.informInfoUnit.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
