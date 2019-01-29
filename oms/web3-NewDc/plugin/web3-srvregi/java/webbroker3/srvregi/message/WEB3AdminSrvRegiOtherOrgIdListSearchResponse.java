head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListSearchResponse.java;


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
File Name           : サービス利用管理者外部連携ID一覧条件入力ﾚｽﾎﾟﾝｽ(WEB3AdminSrvRegiOtherOrgIdListSearchResponse.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (サービス利用管理者外部連携ID一覧条件入力ﾚｽﾎﾟﾝｽ)<BR>
 * サービス利用管理者外部連携ID一覧条件入力ﾚｽﾎﾟﾝｽクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListSearchResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_list_search";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101406L;

    /**
     * (サービス区分)<BR>
     * サービス区分<BR>
     */
    public String[] serviceDiv;

    /**
     * (サービス名称)<BR>
     * サービス名称<BR>
     */
    public String[] serviceName;

    /**
     * (サービス利用管理者外部連携ID一覧条件入力ﾚｽﾎﾟﾝｽ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B9248701E2
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
