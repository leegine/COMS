head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡確認リクエスト(WEB3InformConfirmRequest.java)
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
 * (連絡確認リクエスト)<BR>
 * 連絡確認リクエストクラス<BR>
 */
public class WEB3InformConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformConfirmRequest.class);

    /**
     * (連絡情報)<BR>
     * 連絡情報<BR>
     */
    public WEB3InformDetailInfoUnit informInfoUnit;

    /**
     * @@roseuid 41EE625C01E4
     */
    public WEB3InformConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3InformConfirmResponse(this);
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
     * @@roseuid 41B9290D01EF
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
