head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式為替ネッティングリクエスト(WEB3FeqNettingExchangeRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/10 張騰宇 (中訊) 新規作成 モデル548
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (外国株式為替ネッティングリクエスト)<BR>
 * 外国株式為替ネッティングリクエスト<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3FeqNettingExchangeRequest  extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "feq_netting_exchange";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 201009081516L;

    /**
     * コンストラクタ。<BR>
     */
    public WEB3FeqNettingExchangeRequest()
    {

    }

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3FeqNettingExchangeResponse(this);
    }
}
@
