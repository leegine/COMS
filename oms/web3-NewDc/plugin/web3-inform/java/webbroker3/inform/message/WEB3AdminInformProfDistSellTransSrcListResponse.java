head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先一覧レスポンスクラス(WEB3AdminInformProfDistSellTransSrcListResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.045
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (利金・分配金・売却代金振込先一覧レスポンスクラス)<BR>
 * 利金・分配金・売却代金振込先一覧レスポンスクラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_sell_trans_src_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200705242212L;

    /**
     * (振込先情報)<BR>
     * 振込先情報の配列
     */
    public WEB3AdminInformProfDistTransferInfo[] transferInfo;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号
     */
    public String pageIndex;

    /**
     * (総ページ数)<BR>
     * 総ページ数
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数
     */
    public String totalRecords;

    /**
     * @@roseuid 46559375034A
     */
    public WEB3AdminInformProfDistSellTransSrcListResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformProfDistSellTransSrcListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
