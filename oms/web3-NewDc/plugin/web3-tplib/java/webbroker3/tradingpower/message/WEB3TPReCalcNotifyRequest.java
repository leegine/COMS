head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知リクエスト(WEB3TPReCalcNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (余力計算通知リクエスト)
 */
public class WEB3TPReCalcNotifyRequest extends WEB3BackRequest
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503241100L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tp_recalc_notify";

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * (From口座ID)
     */
    public Long fromAccountId;

    /**
     * (To口座ID)
     */
    public Long toAccountId;

    /**
     * @@roseuid 423541380308
     */
    public WEB3TPReCalcNotifyRequest()
    {

    }

    /**
     * (validate)
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@this.スレッドＮｏ＝nullまたは、Ｆｒｏｍ口座ＩＤ=nullまたは、To口座ＩＤ=nullの場合、<BR>
     * 例外をthrowする。<BR>
     * <BR>
     * @@roseuid 41F5D1E00017
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (fromAccountId == null || toAccountId == null || threadNo == null)
        {
            //エラーをスロー
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 423541380346
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3TPReCalcNotifyResponse(this);
    }
}
@
