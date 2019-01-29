head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.12.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecWorkingCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・稼動状況変更完了レスポンス(WEB3AdminDirSecWorkingCompleteResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/25 柴双紅(中訊) 新規作成 モデルNo.117
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・稼動状況変更完了レスポンス)<BR>
 * 管理者・稼動状況変更完了レスポンス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminDirSecWorkingCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dir_sec_working_complete";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200804251753L;

    /**
     * @@roseuid 481155FD02B7
     */
    public WEB3AdminDirSecWorkingCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     */
    public WEB3AdminDirSecWorkingCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
