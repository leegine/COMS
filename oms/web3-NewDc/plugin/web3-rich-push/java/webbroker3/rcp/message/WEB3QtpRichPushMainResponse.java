head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.16.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885ade604a;
filename	WEB3QtpRichPushMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : QTPリッチクライアントプッシュメインレスポンスクラス(WEB3QtpRichPushMainResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 孫 (FLJ)新規作成
 */

package webbroker3.rcp.message;

import webbroker3.common.message.*;

/**
 * （QTPリッチクライアントプッシュメインレスポンスクラス）。<br>
 * <br>
 * QTPリッチクライアントプッシュメインレスポンスクラス
 * @@author 孫 (FLJ)
 * @@version 1.0
 */
public class WEB3QtpRichPushMainResponse
    extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "qtp_rich_push_main";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200602060000L;

    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3QtpRichPushMainResponse()
    {
    }

}
@
