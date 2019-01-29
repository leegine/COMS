head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenChangeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設切替レスポンス（WEB3AccOpenChangeResponse.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.164
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者口座開設切替レスポンス)<BR>
 * 管理者口座開設切替レスポンス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccOpenChangeResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_change";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908131114L;

    /**
     * (管理者口座開設切替レスポンス)<BR>
     * 管理者口座開設切替レスポンス<BR>
     */
    public WEB3AccOpenChangeResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * リクエストオブジェクト<BR>
     */
    public WEB3AccOpenChangeResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
