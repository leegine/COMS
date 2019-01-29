head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoExecMailDistributionChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報約定／未約定メール配信設定変更完了リクエスト(WEB3AccInfoExecMailDistributionChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoExecDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoProductTypeDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報約定／未約定メール配信設定変更完了リクエスト)<BR>
 * お客様情報約定／未約定メール配信設定変更完了リクエスト<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoExecMailDistributionChangeCompleteRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoExecMailDistributionChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_execMailDistributionChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082156L;

    /**
     * (銘柄タイプ区分)<BR>
     * 銘柄タイプ区分<BR>
     * <BR>
     * 1：　@株式（Equity）<BR>
     * 6：　@株価指数先物／オプション（Ifo）<BR>
     */
    public String productTypeDiv;

    /**
     * (約定／未約定区分)<BR>
     * 約定／未約定区分<BR>
     * <BR>
     * 0：　@未約定メール設定<BR>
     * 1：　@約定メール設定<BR>
     */
    public String execDiv;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 418F39F20186
     */
    public WEB3AccInfoExecMailDistributionChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoExecMailDistributionChangeCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@銘柄タイプ区分のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01109<BR>
     * 　@１－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01110<BR>
     * <BR>
     * ２）　@約定／未約定区分のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01111<BR>
     * 　@２－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01112<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413C0916002C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.productTypeDiv == null || "".equals(this.productTypeDiv))
        {
            log.error("銘柄タイプ区分未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01109, getClass().getName() + STR_METHOD_NAME, "銘柄タイプ区分未入力");
        }
        if ( !WEB3AccInfoProductTypeDivDef.EQUITY.equals(this.productTypeDiv)
             && !WEB3AccInfoProductTypeDivDef.IFO.equals(this.productTypeDiv) )
        {
            log.error("銘柄タイプ区分不正なコード値の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01110, getClass().getName() + STR_METHOD_NAME, "銘柄タイプ区分不正なコード値");     
        }
        
        if (this.execDiv == null || "".equals(this.execDiv))
        {
            log.error("約定／未約定区分未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01111, getClass().getName() + STR_METHOD_NAME, "約定／未約定区分未入力");
        }
        if ((!WEB3AccInfoExecDivDef.EXEC_MAIL.equals(this.execDiv))
            && (!WEB3AccInfoExecDivDef.UNEXEC_MAIL.equals(this.execDiv)))
        {
            log.error("約定／未約定区分不正なコード値の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01112, getClass().getName() + STR_METHOD_NAME, "約定／未約定区分不正なコード値");
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
