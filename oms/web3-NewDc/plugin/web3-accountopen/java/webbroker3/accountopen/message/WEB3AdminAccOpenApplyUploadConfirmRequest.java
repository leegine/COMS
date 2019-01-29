head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.02.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込UL確認リクエスト(WEB3AdminAccOpenApplyUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 武波 (中訊) 新規作成 モデル No.147
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設申込UL確認リクエスト)<BR>
 * 管理者口座開設申込UL確認リクエスト<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyUploadConfirmRequest extends WEB3GenRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyUploadConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_upload_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200711211758L;

    /**
     * (アップロードファ@イル)<BR>
     * アップロードファ@イル<BR>
     */
    public String[] uploadFile;

    /**
     * @@roseuid 4743EF520269
     */
    public WEB3AdminAccOpenApplyUploadConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyUploadConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@アップロードファ@イルのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_00976<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4729784201C4
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

    	if (this.uploadFile == null || this.uploadFile.length == 0)
    	{
    		log.debug("アップロードファ@イルが未指定です。");
    		log.exiting(STR_METHOD_NAME);
    		throw new WEB3BusinessLayerException(
    			WEB3ErrorCatalog.BUSINESS_ERROR_00976,
    			this.getClass().getName() + "." + STR_METHOD_NAME,
    			"アップロードファ@イルが未指定です。");
    	}

    	log.exiting(STR_METHOD_NAME);

    }
}
@
