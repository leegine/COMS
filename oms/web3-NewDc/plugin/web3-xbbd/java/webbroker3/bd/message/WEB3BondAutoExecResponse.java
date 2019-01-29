head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券自動約定レスポンス(WEB3BondAutoExecResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 徐大方 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (債券自動約定レスポンス)<BR>
 * 債券自動約定レスポンスクラス<BR>
 *
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3BondAutoExecResponse extends WEB3BackResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_auto_execution";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610051109L;

    /**
     * (債券自動約定レスポンス ())<BR>
     * コンストラクタ<BR>
     */
    public WEB3BondAutoExecResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3BondAutoExecResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
