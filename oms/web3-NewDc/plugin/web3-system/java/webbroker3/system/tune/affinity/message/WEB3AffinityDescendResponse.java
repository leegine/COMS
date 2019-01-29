head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AffinityDescendResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 下り処理レスポンス（Affinity）(WEB3AffinityDescendResponse.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 */
package webbroker3.system.tune.affinity.message;

import com.fitechlabs.xtrade.kernel.message.*;

/**
 * 下り処理レスポンス（Affinity）
 */
public class WEB3AffinityDescendResponse
    extends Response
{

    /**
     * ポリモルフィックタイプ
     */
    public static final String PTYPE = "descend_response";

    /**
     * シリアルバージョンUID
     */
    public static final long serialVersionUId = 200409081330L;

    /**
     * エラー情報
     */
    public ErrorInfo error;

    /**
     * サブレスポンス
     */
    public Response response[];

}
@
