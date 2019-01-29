head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderNotifyMainResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ルールエンジン通知メインレスポンスクラス(WEB3RlsCondOrderNotifyMainResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 劉 (FLJ)新規作成
 */

package webbroker3.omsadaptor.message;

import webbroker3.common.message.*;

/**
 * （ルールエンジン通知メインレスポンスクラス）。<br>
 * <br>
 * ルールエンジン通知メインレスポンスクラス
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3RlsCondOrderNotifyMainResponse
    extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "rls_cond_notifyMain";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511010000L;

    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3RlsCondOrderNotifyMainResponse()
    {
    }

}
@
