head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoDirectChangeCancelResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ダイレクト指定変更アップロード中止レスポンス(WEB3AdminPvInfoDirectChangeCancelResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/26 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・ダイレクト指定変更アップロード中止レスポンス)<BR>
 * 管理者・ダイレクト指定変更アップロード中止レスポンスクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoDirectChangeCancelResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_PvInfo_directChangeCancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;

     /**
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminPvInfoDirectChangeCancelResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
