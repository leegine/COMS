head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.15.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報一覧レスポンス(WEB3AdminMailInfoReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (メール情報一覧レスポンス)<BR>
 * メール情報一覧レスポンスクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (メール情報一覧明細)<BR>
     */
    public WEB3AdminMailInfoGroup[] mailInfoList;

    /**
     * (総ページ数)<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;    

    /**
     * (メール情報一覧レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 413C0D9403D8
     */
    public WEB3AdminMailInfoReferenceResponse()
    {

    }
    
    /**
     * (メール情報一覧レスポンス)<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminMailInfoReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
