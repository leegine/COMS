head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableSearchResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注文単位テーブル検索結果レスポンス(WEB3AdminDirSecAioOrderUnitTableSearchResultResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
Revesion History : 2007/10/31  謝旋 (中訊) モデルNo.113
Revesion History : 2008/07/15  劉剣 (中訊) モデルNo.130
*/

package webbroker3.dirsec.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・注文単位テーブル検索結果レスポンス)<BR>
 * 管理者・注文単位テーブル検索結果レスポンスクラス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAioOrderUnitTableSearchResultResponse extends WEB3GenResponse
{
	/**
	 * PTYPE
	 */
	public static final String PTYPE = "admin_dirsec_aio_order_unit_table_search_result";

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 200607192121L;

	/**
	 * (口座ID)<BR>
	 * 口座ID<BR>
	 */
	public String accountId;

	/**
	 * (部店ID)<BR>
	 * 部店ID<BR>
	 */
	public String branchId;

	/**
	 * (注文ID)<BR>
	 * 注文ID<BR>
	 */
	public String orderId;

	/**
	 * (銘柄タイプ)<BR>
	 * 銘柄タイプ<BR>
	 */
	public String productType;

	/**
	 * (注文状態)<BR>
	 * 注文状態<BR>
	 */
	public String orderStatus;

	/**
	 * (注文有効状態)<BR>
	 * 注文有効状態<BR>
	 */
	public String orderOpenStatus;

	/**
	 * (識別コード)<BR>
	 * 識別コード<BR>
	 */
	public String requestNumber;

    /**
     * (約定状態)<BR>
     * 約定状態<BR>
     */
	public String execStatus;

    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public String orderBizDate;

	/**
	 * @@roseuid 44BE20C000BB
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultResponse()
	{

	}

	/**
	 * コンストラクタ。<BR>
	 * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
	 * 
	 * @@param l_request - リクエストオブジェクト
	 */
	public WEB3AdminDirSecAioOrderUnitTableSearchResultResponse(WEB3GenRequest l_request)
	{
		super(l_request);
	}
}
@
