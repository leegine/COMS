head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.09.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUploadCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込UL中止リクエスト(WEB3AdminAccOpenApplyUploadCancelRequest.java)
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
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者口座開設申込UL中止リクエスト)<BR>
 * 管理者口座開設申込UL中止リクエスト<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyUploadCancelRequest extends WEB3GenRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyUploadCancelRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_upload_cancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200711211748L;

    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadID;

    /**
     * @@roseuid 4743EF520382
     */
    public WEB3AdminAccOpenApplyUploadCancelRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyUploadCancelResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@アップロードIDのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@ class　@　@: WEB3BusinessLayerException<BR>
     * 　@　@ tag　@　@　@: BUSINESS_ERROR_00973<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4729785B01B1
     */
    public void validate() throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "validate()";
    	log.entering(STR_METHOD_NAME);

    	if (WEB3StringTypeUtility.isEmpty(this.uploadID))
    	{
    		log.debug("アップロードIDが未指定です。");
    		log.exiting(STR_METHOD_NAME);
    		throw new WEB3BusinessLayerException(
    			WEB3ErrorCatalog.BUSINESS_ERROR_00973,
    			this.getClass().getName() + "." + STR_METHOD_NAME,
    			"アップロードIDが未指定です。");
    	}

    	log.exiting(STR_METHOD_NAME);
    }
}
@
