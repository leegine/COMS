head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込取消確認リクエスト(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報株式委託手数料コース変更申込取消確認リクエスト)<BR>
 * お客様情報株式委託手数料コース変更申込取消確認リクエスト<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest extends WEB3GenRequest
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082161L;

    /**
     * (ＩＤ)<BR>
     * 委託手数料変更申込ＩＤ<BR>
     */
    public String id;

    /**
     * @@roseuid 418F39F002EE
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@ＩＤのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413EC1EE0312
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //１）　@ＩＤのチェック
        //１－１）　@未入力の場合、例外をスローする。
        //        class: WEB3BusinessLayerException
        //        tag:   BUSINESS_ERROR_00080
        if (this.id == null || "".equals(this.id))
        {        
            //例外
            log.debug("[ＩＤ] = " + id);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + STR_METHOD_NAME, "ＩＤの未入力の場合");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
