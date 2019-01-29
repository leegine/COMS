head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminAccInfoMailAddressFileDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccountOpenMailFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressFileDownloadRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressFileDownloadRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082114L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;
    
    /**
     * (顧客コード（自）)<BR>
     * 顧客コード（自）
     */
    public String accountCodeFrom;
    
    /**
     * (顧客コード（至）)<BR>
     * 顧客コード（至）
     */
    public String accountCodeTo;
    
    /**
     * (送信フラグ)<BR>
     * 送信フラグ<BR>
     * ・要<BR>
     * ・不要<BR>
     * ・指定なし<BR>
     */
    public String sendFlag;

    /**
     * @@roseuid 418F38570203
     */
    public WEB3AdminAccInfoMailAddressFileDownloadRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressFileDownloadResponse(this);
    }

    /**
     * (validate)<BR>
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     *２）顧客コード（自）チェック<BR>
     *　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02007<BR>
     *３）顧客コード（至）チェック<BR>
     *　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02008<BR>
     *<BR>
     *４）送信フラグチェック<BR>
     *　@４－１）　@以下の値に該当しない場合、例外をスローする。<BR>
     *　@　@　@　@　@　@・要<BR>
     *　@　@　@　@　@　@・不要<BR>
     *　@　@　@　@　@　@・指定なし<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02009<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41749AAB0182
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.error("部店コード未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, getClass().getName() + STR_METHOD_NAME, "部店コード未入力");
        }
        if (this.accountCodeFrom == null)
        {
            log.error("顧客コード（自）未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02007, getClass().getName() + STR_METHOD_NAME, "顧客コード（自）未入力");
        }
        if (this.accountCodeTo == null)
        {
            log.error("顧客コード（至）未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02008, getClass().getName() + STR_METHOD_NAME, "顧客コード（至）未入力");
        }
        if (!(this.sendFlag == null) && !(WEB3AccountOpenMailFlagDef.sendFlag.equals(this.sendFlag))
        		 && !(WEB3AccountOpenMailFlagDef.unSendFlag.equals(this.sendFlag)))
        {
            log.error("送信フラグ以下の値に該当しない場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02009, getClass().getName() + STR_METHOD_NAME, "送信フラグ以下の値に該当しない");

        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
