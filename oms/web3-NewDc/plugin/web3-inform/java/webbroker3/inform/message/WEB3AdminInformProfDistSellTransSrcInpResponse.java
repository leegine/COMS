head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先検索入力レスポンスクラス(WEB3AdminInformProfDistSellTransSrcInpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.045
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (利金・分配金・売却代金振込先検索入力レスポンスクラス)<BR>
 * 利金・分配金・売却代金振込先検索入力レスポンスクラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_sell_trans_src_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200705242212L;

    /**
     * (登録日（自）)<BR>
     * 登録日（自）
     */
    public Date registDateFrom;

    /**
     * (登録日（至）)<BR>
     * 登録日（至）
     */
    public Date registDateTo;

    /**
     * @@roseuid 4655937502AE
     */
    public WEB3AdminInformProfDistSellTransSrcInpResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformProfDistSellTransSrcInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
