head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
Author Name         : Daiwa Institute of Research
File Name           : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ(WEB3AdminSrvRegiOtherOrgIdUploadInputResponse.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101414L;

    /**
     * (サービス利用アップロード履歴明細)<BR>
     * サービス利用アップロード履歴明細<BR>
     */
    public WEB3SrvRegiUploadHistoryInfoUnit uploadHistoryUnit;

    /**
     * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B8D7B50221
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadInputResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
