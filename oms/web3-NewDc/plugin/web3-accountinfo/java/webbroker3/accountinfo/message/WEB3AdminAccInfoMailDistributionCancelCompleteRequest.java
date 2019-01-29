head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報案内メール配信指示取消完了要求(WEB3AdminAccInfoMailDistributionCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 彭巍 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報案内メール配信指示取消完了要求)<BR>
 * 管理者お客様情報案内メール配信指示取消完了要求<BR>
 */
public class WEB3AdminAccInfoMailDistributionCancelCompleteRequest extends WEB3GenRequest
{
    
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionCancelCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailDistributionCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412131135L;
   
    /**
     * (ID) <BR>
     *  案内メール配信指示ID<BR>
     */
    public String id;
    
    /**
     * (暗証番号) <BR>
     */
    public String password;
    
    /**
     * @@roseuid 418F39F3033C
     */
    public WEB3AdminAccInfoMailDistributionCancelCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailDistributionCancelCompleteResponse(this);
    }

    /** リクエストデータの整合性をチェックする。<BR>
     *<BR>
     * １）ＩＤのチェック<BR>
　@   *        １－１）未入力の場合、例外をスローする。<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413D3E5700F3
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
                this.getClass().getName() + STR_METHOD_NAME, "ＩＤ未入力の場合");
        }

        log.exiting(STR_METHOD_NAME);

    }
}@
