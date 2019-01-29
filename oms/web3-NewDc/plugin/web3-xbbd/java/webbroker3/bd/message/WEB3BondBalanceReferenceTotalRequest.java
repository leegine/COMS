head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会残高合計リクエスト(WEB3BondBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/17 武波 (中訊) 新規作成 モデル206
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券残高照会残高合計リクエスト)<BR>
 * 債券残高照会残高合計リクエストクラス
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondBalanceReferenceTotalRequest extends WEB3BondBalanceReferenceCommonRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceTotalRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference_total";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707171901L;

    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceTotalRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondBalanceReferenceTotalResponse(this);
    }
}
@
