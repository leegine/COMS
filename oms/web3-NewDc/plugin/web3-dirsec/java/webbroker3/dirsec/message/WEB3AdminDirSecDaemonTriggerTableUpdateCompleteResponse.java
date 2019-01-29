head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・デーモントリガーテーブルステータス更新完了レスポンス(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  齊珂 (中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・デーモントリガーテーブルステータス更新完了レスポンス)<BR>
 * 管理者・デーモントリガーテーブルステータス更新完了レスポンスクラス<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse extends WEB3GenResponse
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_update_complete";

	/**
	 * SerialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200607192050L;
	
	/**
	 * コンストラクタ。<BR>
	 * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
	 * 
	 * @@param l_request - リクエストオブジェクト
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse(
		WEB3GenRequest l_request)
	{
		super(l_request);
	}

	/**
	 * @@roseuid 44BE20C10138
	 */
	public WEB3AdminDirSecDaemonTriggerTableUpdateCompleteResponse()
	{

	}
}
@
