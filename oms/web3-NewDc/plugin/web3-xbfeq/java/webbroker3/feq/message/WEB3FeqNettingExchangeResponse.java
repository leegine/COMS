head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式為替ネッティングレスポンス(WEB3FeqNettingExchangeResponse.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/10 張騰宇 (中訊) 新規作成 モデル548
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (外国株式為替ネッティングレスポンス)<BR>
 * 外国株式為替ネッティングレスポンス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3FeqNettingExchangeResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "feq_netting_exchange";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 201009081517L;

    /**
     * コンストラクタ。<BR>
     */
    public WEB3FeqNettingExchangeResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     */
    protected WEB3FeqNettingExchangeResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
