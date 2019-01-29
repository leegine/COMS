head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文有効期限取得レスポンス(WEB3ExpirationDateListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 于瀟(中訊) 新規作成モデル319
*/

package webbroker3.gentrade.message;


import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (注文有効期限取得レスポンス)<BR>
 * 注文有効期限取得レスポンスクラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ExpirationDateListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "expiration_date_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802141630L;

    /**
     * (注文期限区分一覧)<BR>
     * 注文期限区分一覧<BR>
     */
    public String[] expirationDateTypeList;

    /**
     * (有効期限開始日)<BR>
     * 有効期限開始日<BR>
     */
    public Date expirationStartDate;

    /**
     * (有効期限最終日)<BR>
     * 有効期限最終日<BR>
     */
    public Date expirationEndDate;

    /**
     * (有効期限内祝日一覧)<BR>
     * 有効期限内祝日一覧<BR>
     */
    public Date[] holidayList;

    /**
     * (立会区分)<BR>
     * 夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 47B3E1310232
     */
    public WEB3ExpirationDateListResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3ExpirationDateListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
