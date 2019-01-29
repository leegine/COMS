head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・下り処理一覧レスポンス(WEB3AdminDirSecAPMngListResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/21 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・下り処理一覧レスポンス)<BR>
 * 管理者・下り処理一覧レスポンスクラス。<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngListResponse extends WEB3GenResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200807211620L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_apmng_list";

    /**
     * (総ページ数)<BR>
     * 総ページ数。<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数。<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号。<BR>
     */
    public String pageIndex;

    /**
     * (下り処理情報一覧)<BR>
     * 下り処理情報一覧。<BR>
     */
    public WEB3AdminDirSecAPMngForcedStartInfoUnit[] apMngInfoList;

    /**
     * @@roseuid 488437FE02B7
     */
    public WEB3AdminDirSecAPMngListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminDirSecAPMngListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
