head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄新規登録入力レスポンス(WEB3AdminOffFloorRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄新規登録入力レスポンス)<BR>
 * <BR>
 * 管理者立会外分売銘柄新規登録サービス（入力画面表示）のレスポンスデータ。<BR>
 * <BR>
 * -----<English>------------<BR>
 * <BR>
 * WEB3AdminOffFloorRegistInputResponse<BR>
 * <BR>
 * response data of WEB3AdminOffFloorRegistService(input screen)<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_regist_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (市場コード一覧)<BR>
     * <BR>
     * 市場コード一覧。<BR>
     * <BR>
     * marketList<BR>
     * <BR>
     */
    public String[] marketList;

    /**
     * (受付開始日時)<BR>
     * <BR>
     * 受付開始日時。<BR>
     * （画面に表示する、入力デフォルト値）<BR>
     * <BR>
     * -----<English>----------------<BR>
     * <BR>
     * orderStartDatetime<BR>
     * <BR>
     * （input default value displayed on the screen）<BR>
     * <BR>
     */
    public Date orderStartDatetime;

    /**
     * (受付終了時刻)<BR>
     * <BR>
     * 受付終了時刻。<BR>
     * （画面に表示する、HHMMSSの文字列）<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * orderEndTime<BR>
     * （string of HHMMSS displayed on the screen）<BR>
     * <BR>
     */
    public String orderEndTime;

    /**
     * @@roseuid 421AE4880235
     */
    public WEB3AdminOffFloorRegistInputResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminOffFloorRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
