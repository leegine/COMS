head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse.java;


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
File Name           : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ(WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_upload_cancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101408L;

    /**
     * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B8D7EE0370
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
