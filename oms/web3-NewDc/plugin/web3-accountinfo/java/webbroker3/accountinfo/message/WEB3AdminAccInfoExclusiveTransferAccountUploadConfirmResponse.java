head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082136L;

    /**
     * (アップロード件数)<BR>
     * アップロード件数<BR>
     */
    public String uploadNumber;

    /**
     * (アップロードＩＤ)<BR>
     * アップロードＩＤ<BR>
     */
    public String uploadID;
    
    /**
     * (エラー顧客一覧)<BR>
     *エラー顧客一覧
     */
    public WEB3AdminAccInfoErrorAccount[] errorAccountList;

    /**
     * @@roseuid 418F3869000F
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse()
    {

    }

    /**
     * (管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse
     * @@roseuid 415BCA9B0360
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
