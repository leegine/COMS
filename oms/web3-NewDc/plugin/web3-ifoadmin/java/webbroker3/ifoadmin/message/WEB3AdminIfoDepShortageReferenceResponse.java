head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況照会レスポンス(WEB3AdminIfoDepShortageReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・証拠金不足状況照会レスポンス)<BR>
 * 管理者・証拠金不足状況照会レスポンスクラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271151L;

    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages = "0";

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords = "0";

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex = "0";

    /**
     * (表示日時)<BR>
     * 表示日時<BR>
     */
    public Date dispDate;

    /**
     * (証拠金不足状況情報一覧)<BR>
     * 証拠金不足状況情報一覧
     */
    public WEB3IfoDepShortageInfo[] ifoDepShortageInfos;

    /**
     * @@roseuid 49A7485500EA
     */
    public WEB3AdminIfoDepShortageReferenceResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminIfoDepShortageReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
