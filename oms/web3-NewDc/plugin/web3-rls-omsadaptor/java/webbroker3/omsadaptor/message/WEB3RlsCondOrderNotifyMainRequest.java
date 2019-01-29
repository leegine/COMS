head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderNotifyMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ルールエンジン通知メインリクエスト(WEB3RlsCondOrderNotifyMainRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 劉 (FLJ)新規作成
 */

package webbroker3.omsadaptor.message;

import webbroker3.common.*;
import webbroker3.common.message.*;
import webbroker3.util.*;

/**
 * （ルールエンジン通知メインリクエスト）。<br>
 * <br>
 * ルールエンジン通知メインリクエスト
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3RlsCondOrderNotifyMainRequest
    extends WEB3BackRequest
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsCondOrderNotifyMainRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "rls_cond_notifyMain";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511010000L;

    /**
     * (From口座ID)
     */
    public Long fromAccountId;

    /**
     * (To口座ID)
     */
    public Long toAccountId;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;


    /**
     * 処理タイプ<BR>
     */
    public int rlsNotifyOrderType[];

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3RlsCondOrderNotifyMainRequest()
    {
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3RlsCondOrderNotifyMainResponse();
    }

    /**
     * （validate）<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@this.スレッドＮｏ＝nullまたは、Ｆｒｏｍ口座ＩＤ=nullまたは、To口座ＩＤ=nullの場合、<BR>
     * 例外をthrowする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.fromAccountId == null ||
            this.toAccountId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.threadNo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
