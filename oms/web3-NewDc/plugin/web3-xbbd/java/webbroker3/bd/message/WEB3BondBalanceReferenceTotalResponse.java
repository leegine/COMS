head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会残高合計レスポンス(WEB3BondBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 武波 (中訊) 新規作成 モデル206
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券残高照会残高合計レスポンス)<BR>
 * 債券残高照会残高合計レスポンスクラス
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceTotalResponse extends WEB3GenResponse
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceTotalResponse.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference_total";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707171902L;

    /**
     * (概算評価額（円貨）合計)<BR>
     * 概算評価額（円貨）合計<BR>
     */
    public String yenEstimatedTotalAsset;

    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceTotalResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondBalanceReferenceTotalResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
