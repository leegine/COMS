head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文単位テーブル注文状態更新完了レスポンス(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・注文単位テーブル注文状態更新完了レスポンス)<BR>
 * 管理者・注文単位テーブル注文状態更新完了レスポンスクラス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse extends WEB3GenResponse
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_update_complete";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * @@roseuid 44BE20C0003E
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse()
	{

	}

	/**
	 * コンストラクタ。<BR>
	 * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
	 * 
	 * @@param l_request - リクエストオブジェクト
	 */
	public WEB3AdminDirSecAioOrderUnitTableUpdateCompleteResponse(WEB3GenRequest l_request)
	{
		super(l_request);
	}
}
@
