head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文単位テーブル検索入力リクエスト(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・注文単位テーブル検索入力リクエスト)<BR>
 * 管理者・入力単位テーブル検索入力リクエストクラス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableSearchInputRequest extends WEB3GenRequest
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_search_input";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * @@roseuid 44BE20C0033C
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchInputRequest()
	{

	}

	/**
	 * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
	 * <BR>
	 * 
	 * @@return レスポンスオブジェクト
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3AdminDirSecAioOrderUnitTableSearchInputResponse(this);
	}
}
@
