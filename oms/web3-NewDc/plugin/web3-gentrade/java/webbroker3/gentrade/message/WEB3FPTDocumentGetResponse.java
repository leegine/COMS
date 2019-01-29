head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.56.57;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentGetResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : 金商法@書面情報取得レスポンスクラス(WEB3FPTDocumentGetResponse.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 劉レイ(北京中訊) 新規作成 仕様変更モデルNo.354
 */
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (金商法@書面情報取得レスポンス)<BR>
 * 金商法@書面情報取得レスポンスクラス<BR>
 * <BR>
 * @@author 劉レイ(北京中訊)
 * @@version 1.0
 */
public class WEB3FPTDocumentGetResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "fpt_document_get";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011170922L;

    /**
     * (書面情報)<BR>
     * 書面情報<BR>
     */
    public WEB3FPTDocumentInfoUnit[] documentList;

    /**
     * コンストラクタ<BR>
     */
    public WEB3FPTDocumentGetResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3FPTDocumentGetResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
