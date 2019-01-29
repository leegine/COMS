head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdListReferenceResponse.java;


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
File Name           : サービス利用管理者外部連携ID一覧照会ﾚｽﾎﾟﾝｽ(WEB3AdminSrvRegiOtherOrgIdListReferenceResponse.java)
Revision History    : 2008/03/10 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (サービス利用管理者外部連携ID一覧照会ﾚｽﾎﾟﾝｽ)<BR>
 * サービス利用管理者外部連携ID一覧照会ﾚｽﾎﾟﾝｽクラス<BR>
 * <BR>
 * @@author 王志葵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdListReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_otherorgId_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803101404L;

    /**
     * (サービス利用管理者外部連携ID一覧照会明細)<BR>
     * サービス利用管理者外部連携ID一覧照会明細<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdGroup[] otherOrgIdList;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     * <BR>
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * (サービス利用管理者外部連携ID一覧照会ﾚｽﾎﾟﾝｽ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 47B8D11C01D3
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    public WEB3AdminSrvRegiOtherOrgIdListReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
