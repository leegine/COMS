head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報メールアドレス変更完了リクエスト(WEB3AccInfoMailAddressChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
Revesion History : 2007/08/28 武波 (中訊) 仕様変更・モデルNo.217
Revesion History : 2010/02/21 武波 (中訊) 仕様変更・モデルNo.263
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報メールアドレス変更完了リクエスト)<BR>
 * お客様情報メールアドレス変更完了リクエスト<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeCompleteRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mailAddressChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082158L;

    /**
     * (変更後メールアドレス)<BR>
     * 変更後メールアドレス<BR>
     */
    public String changedMailAddress;
    
    /**
     * (メールアドレス削除フラグ)<BR>
     * <BR>
     * true：　@削除<BR>
     * false：　@削除でない<BR>
     */
    public boolean mailAddressDelFlag;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (複数アドレス情報)<BR>
     * 複数アドレス情報<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo multiMailAddressInfo;

    /**
     * (複数アドレスリスト)<BR>
     * 複数アドレスリスト<BR>
     */
    public WEB3AccInfoMultiMailAddressList multiMailAddressList;

    /**
     * @@roseuid 418F39F30128
     */
    public WEB3AccInfoMailAddressChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoMailAddressChangeCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@変更後メールアドレス，メールアドレス削除フラグのチェック<BR>
     *　@１－１）　@（メールアドレス削除フラグ == false）の場合、<BR>
     *      変更後メールアドレスが未入力であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01153<BR>
     *　@１－２）　@（メールアドレス削除フラグ == true）の場合、<BR>
     *      変更後メールアドレスに入力があれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01154<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D420201AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (!this.mailAddressDelFlag)
        {
            if (this.changedMailAddress == null || "".equals(this.changedMailAddress))
            {
                log.error("変更後メールアドレス未入力の場合、例外をスロー");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01153, getClass().getName() + STR_METHOD_NAME, "変更後メールアドレス未入力");

            }
        }
        else
        {
            if (this.changedMailAddress != null && !("".equals(this.changedMailAddress)))
            {
                log.error("変更後メールアドレス入力の場合、例外をスロー");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01154, getClass().getName() + STR_METHOD_NAME, "変更後メールアドレス入力");

            }

        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
