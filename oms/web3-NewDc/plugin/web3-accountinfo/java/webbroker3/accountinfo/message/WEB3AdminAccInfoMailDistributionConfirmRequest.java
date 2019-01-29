head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報案内メール配信指示確認要求(WEB3AdminAccInfoMailDistributionConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報案内メール配信指示確認要求)<BR>
 * 管理者お客様情報案内メール配信指示確認要求<BR>
 */
public class WEB3AdminAccInfoMailDistributionConfirmRequest extends WEB3GenRequest
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailDistributionConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412131135L;

    /**
     * (送信メール区分)<BR>
     * 送信メール区分<BR>
     * <BR>
     * ※ 「メール内容」リンク作成パラメータ。<BR>
     * <BR>
	 *	先頭2桁は商品単位に採番し、後ろの2桁は任意<BR>
	 *	01：サービス利用　@02：口座開設　@03：問合せ管理　@04：案内メール<BR>
     *  ※詳細は、「メール送信テーブル.送信メール区分」シート参照。<BR>
     */
    public String sendMailDiv;
    
    /**
     * (識別ID) <BR>
     */
    public String discernId;
    
    /** (全顧客フラグ)<BR>
     *  true：　@全顧客に配信<BR>
     *  false：　@案内メール希望客のみに配信<BR>
     */
    public boolean allAccountFlag;
    


    /**
     * @@roseuid 418F39F3033C
     */
    public WEB3AdminAccInfoMailDistributionConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailDistributionConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     *<BR>
     * １）　@送信メール区分のチェック<BR>
　@   *        １－１）　@未入力の場合、例外をスローする。<BR>
　@   *        １－２）　@案内メール（"0401"）以外の値場合、例外をスローする。<BR>
     *<BR>
     * ２）　@識別ＩＤのチェック<BR>
　@   *        ２－１）　@未入力の場合、例外をスローする。<BR>
　@   *        ２－２）　@Default（"----"）以外の値の場合、例外をスローする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D3E5700F3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);

        if (this.sendMailDiv == null || "".equals(this.sendMailDiv))
        {
            log.debug("[送信メール区分] = " + sendMailDiv);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00862, 
                getClass().getName() + STR_METHOD_NAME,"送信メール区分未入力の場合");
        }
        if (!WEB3SendmailDivDef.ACCINFO_GUIDE_MAIL.equals(this.sendMailDiv))
        {
            log.debug("[送信メール区分] = " + sendMailDiv);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01383, 
                getClass().getName() + STR_METHOD_NAME, "案内メール（0401）以外の値場合");
        }
        
        if (this.discernId == null || "".equals(this.discernId))
        {
            log.debug("[識別ＩＤ] = " + discernId);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00865, 
                getClass().getName() + STR_METHOD_NAME, "識別ＩＤ未入力の場合");
        }
        if (!"----".equals(this.discernId))
        {
            log.debug("[識別ＩＤ] = " + discernId);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01384, 
                getClass().getName() + STR_METHOD_NAME, "識別ＩＤDefault（----）以外の値の場合");
        }
        log.exiting(STR_METHOD_NAME);
        

    }
}@
