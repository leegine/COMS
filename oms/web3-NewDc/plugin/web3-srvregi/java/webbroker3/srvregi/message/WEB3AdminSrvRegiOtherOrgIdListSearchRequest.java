head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListSearchRequest.java;


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
File Name           : サービス利用管理者外部連携ID一覧条件入力ﾘｸｴｽﾄ(WEB3AdminSrvRegiOtherOrgIdListSearchRequest.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (サービス利用管理者外部連携ID一覧条件入力ﾘｸｴｽﾄ)<BR>
 * サービス利用管理者外部連携ID一覧条件入力ﾘｸｴｽﾄクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListSearchRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgid_list_search";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101405L;

    /**
     * (特殊処理区分)<BR>
     * 特殊処理区分<BR>
     */
    public String specialProcessDiv;

    /**
     * @@roseuid 47D111330169
     */
    public WEB3AdminSrvRegiOtherOrgIdListSearchRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiOtherOrgIdListSearchResponse(this);
    }
}
@
