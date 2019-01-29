head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.10.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082137L;

    /**
     * @@roseuid 418F38680242
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse()
    {

    }

    /**
     * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse
     * @@roseuid 415BCAAD0247
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
