head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込UL完了レスポンス(WEB3AdminAccOpenApplyUploadCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/21 武波 (中訊) 新規作成 モデル No.147
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設申込UL完了レスポンス)<BR>
 * 管理者口座開設申込UL完了レスポンス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyUploadCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_upload_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200711211753L;

    /**
     * @@roseuid 4743EF520344
     */
    public WEB3AdminAccOpenApplyUploadCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenApplyUploadCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
