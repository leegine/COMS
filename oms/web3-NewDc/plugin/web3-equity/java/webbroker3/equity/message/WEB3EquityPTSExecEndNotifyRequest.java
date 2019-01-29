head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)株式出来終了通知リクエスト(WEB3EquityPTSExecEndNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 趙林鵬(中訊) 新規作成 モデルNo.1286
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * ((PTS)株式出来終了通知リクエスト)<BR>
 * (PTS)株式出来終了通知リクエストクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyRequest extends WEB3BackRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_pts_exec_end_notify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20080123101000L;

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (From口座ID)<BR>
     * From口座ID<BR>
     */
    public long rangeFrom;

    /**
     * (To口座ID)<BR>
     * To口座ID<BR>
     */
    public long rangeTo;

    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;

    /**
     * @@roseuid 462CA4250276
     */
    public WEB3EquityPTSExecEndNotifyRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@証券会社コードチェック<BR>
     * 　@this.証券会社コード＝nullの場合、<BR>
     * 　@「証券会社コードがnull」の例外をthrowする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag  :　@BUSINESS_ERROR_00827<BR>
     * <BR>
     * ２）　@市場コードチェック<BR>
     * 　@this.市場コード＝nullの場合、<BR>
     * 　@　@「市場コードがnull」の例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag  :　@BUSINESS_ERROR_00443<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46075A5D03A3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.証券会社コード＝nullの場合
        //「証券会社コードがnull」の例外をthrowする。
        if (this.institutionCode == null)
        {
            log.debug("証券会社コードがnull");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }

        //this.市場コード＝nullの場合
        //「市場コードがnull」の例外をスローする。
        if (this.marketCode == null)
        {
            log.debug("市場コードがnull");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "市場コードが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityPTSExecEndNotifyResponse(this);
    }
}
@
