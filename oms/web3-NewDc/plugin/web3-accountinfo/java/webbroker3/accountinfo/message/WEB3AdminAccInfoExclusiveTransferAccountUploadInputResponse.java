head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082135L;

    /**
     * (アップロード履歴一覧)<BR>
     * アップロード履歴一覧<BR>
     */
    public WEB3AccInfoUploadHistoryUnit uploadHistoryList;

    /**
     * @@roseuid 418F3867033C
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse()
    {

    }

    /**
     * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse
     * @@roseuid 415BCACB0014
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
