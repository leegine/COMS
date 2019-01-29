head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuotePriceSaveRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : レスポンスクラス(WEB3QuotePriceSaveRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/06 劉 (FLJ)新規作成
 */

package webbroker3.quoteprice.message;

import com.fitechlabs.xtrade.kernel.message.*;
import webbroker3.common.*;
import webbroker3.util.*;

/**
 * （レスポンスクラススクラス）。<br>
 * <br>
 * レスポンスクラス
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3QuotePriceSaveRequest
    extends Message
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePriceSaveRequest.class);

    /**
     * (会社コード)
     */
    public String institutionCd;

    /**
     * (From銘柄コードD)
     */
    public Long fromProductCd;

    /**
     * (To銘柄コードD)
     */
    public Long toProductCd;

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

        if (this.fromProductCd == null ||
            this.toProductCd == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.institutionCd == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * デフォルトコンストラクタ<BR>
     */
    public WEB3QuotePriceSaveRequest()
    {
    }

}
@
