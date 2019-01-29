head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLRestraintMoneyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン出金拘束金一覧レスポンス(WEB3AdminSLRestraintMoneyListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 孫洪江 (中訊) 新規作成 仕様変更モデル764
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (証券担保ローン出金拘束金一覧レスポンス)<BR>
 * 証券担保ローン出金拘束金一覧レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminSLRestraintMoneyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_restraint_money_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131759L;

    /**
     * (出金停止情報一覧)<BR>
     */
    public WEB3SLCashOutStopInfoUnit[] cashOutStopInfoList;

    /**
     * (総ページ番号)<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     */
    public String pageIndex;

    public WEB3AdminSLRestraintMoneyListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminSLRestraintMoneyListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
