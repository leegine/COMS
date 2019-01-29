head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAPMngForcedStartCnfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・下り処理強制起動確認レスポンス(WEB3AdminDirSecAPMngForcedStartCnfResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/21 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・下り処理強制起動確認レスポンス)<BR>
 * 管理者・下り処理強制起動確認レスポンスクラス。<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3AdminDirSecAPMngForcedStartCnfResponse extends WEB3GenResponse
{

    /**
	 * serialVersionUID<BR>
	 */
	private static final long serialVersionUID = 200807211640L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dirsec_apmng_forced_start_cnf";

	/**
     * @@roseuid 488437FE00B4
     */
    public WEB3AdminDirSecAPMngForcedStartCnfResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminDirSecAPMngForcedStartCnfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
