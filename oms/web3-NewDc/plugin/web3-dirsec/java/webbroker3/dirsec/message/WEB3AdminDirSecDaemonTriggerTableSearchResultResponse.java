head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecDaemonTriggerTableSearchResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・デーモントリガーテーブル検索結果レスポンス(WEB3AdminDirSecDaemonTriggerTableSearchResultResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  齊珂 (中訊) 新規作成
*/

package webbroker3.dirsec.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * <管理者・デーモントリガーテーブル検索結果レスポンス><BR>
 * 管理者・デーモントリガーテーブル検索結果レスポンスクラス<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */

public class WEB3AdminDirSecDaemonTriggerTableSearchResultResponse extends WEB3GenResponse
{
	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = 
		"admin_dirsec_daemon_trigger_table_search_result";

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
	public WEB3AdminDirSecDaemonTriggerTableSearchResultResponse(
		WEB3GenRequest l_request)
	{
		super(l_request);
	}

	/**
	 * (処理タイプ)<BR>
	 * 処理タイプ<BR>
	 */
	public String triggerType;

	/**
	 * (識別コード)<BR>
	 * 識別コード<BR>
	 */
	public String orderRequestNumber;

	/**
	 * (顧客コード（自）)<BR>
	 * 顧客コード（自）<BR>
	 */
	public String rangeFrom;

	/**
	 * (顧客コード（至）)<BR>
	 * 顧客コード（至）<BR>
	 */
	public String rangeTo;

	/**
	 * (ステータス)<BR>
	 * ステータス<BR>
	 */
	public String triggerStatus;

	/**
	 * (最終処理日時)<BR>
	 * 最終処理日時<BR>
	 */
	public Date triggerDate;

}
@
