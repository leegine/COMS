head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistStatusSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・利金・分配金登録状況問合せ入力レスポンス(WEB3AdminInformProfDistStatusSearchInputResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・利金・分配金登録状況問合せ入力レスポンス)<BR>
 * 管理者・利金・分配金登録状況問合せ入力レスポンスクラス<BR>
 */
public class WEB3AdminInformProfDistStatusSearchInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_status_search_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * (前営業日)<BR>
     * 前営業日日付
     */
    public Date previousBizDate;

    /**
     * (前日)<BR>
     * 前日日付
     */
    public Date previousDate;

    /**
     * @@roseuid 4663A9D80109
     */
    public WEB3AdminInformProfDistStatusSearchInputResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformProfDistStatusSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
