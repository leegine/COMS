head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AffinityDescendRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 下り処理リクエスト（Affinity）クラス(WEB3AffinityDescendRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 */
package webbroker3.system.tune.affinity.message;

import com.fitechlabs.xtrade.kernel.message.*;

/**
 * 下り処理リクエスト（Affinity）
 */
public class WEB3AffinityDescendRequest
    extends Request
{

    /**
     * ポリモルフィックタイプ
     */
    public static final String PTYPE = "descend_request";

    /**
     * シリアルバージョンUID
     */
    public static final long serialVersionUId = 200409081330L;

    /**
     * 顧客ID
     */
    public String account_id;

    /**
     * 識別コードID
     */
    public String head2_order_request_number;

    /**
     * 顧客IDレンジ
     */
    public String account_id_range;

    /**
     * サブリクエスト
     */
    public Request request[];
}
@
