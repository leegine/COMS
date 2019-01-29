head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文繰越メインレスポンス(WEB3IfoOrderCarryOverMainResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/6/21 孫洪江 (中訊) 新規作成 モデル 669
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (先物OP注文繰越メインレスポンス)<BR>
 * 先物OP注文繰越メインレスポンスクラス<BR>
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3IfoOrderCarryOverMainResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "ifo_order_carryover_main";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200706211550L;

    public WEB3IfoOrderCarryOverMainResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3IfoOrderCarryOverMainResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
