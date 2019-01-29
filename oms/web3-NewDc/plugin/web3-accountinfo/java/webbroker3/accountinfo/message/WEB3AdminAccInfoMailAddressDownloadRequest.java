head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminAccInfoMailAddressDownloadRequest.java)
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
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressDownloadRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressDownloadRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082115L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;
    
    /**
     * (顧客コード（自）)<BR>
     * 顧客コード（自）<BR>
     */
    public String accountCodeFrom;
    
    /**
     * (顧客コード（至）)<BR>
     * 顧客コード（至）<BR>
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
     * @@roseuid 418F3857000F
     */
    public WEB3AdminAccInfoMailAddressDownloadRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressDownloadResponse(this);
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
     * ２）　@要求ページ番号チェック <BR>
     * 　@２－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * 　@２－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）　@ページ内表示行数チェック <BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * 　@３－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * ４）顧客コード（自）チェック<BR>
     *　@４－１）　@未入力の場合、例外をスローする。<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02007<BR>
     *<BR>
     *５）顧客コード（至）チェック<BR>
     *　@５－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02008<BR>
     *<BR>
     *６）送信フラグチェック<BR>
     *　@６－１）　@以下の値に該当しない場合、例外をスローする。<BR>
     *　@　@　@　@　@　@・要<BR>
     *　@　@　@　@　@　@・不要<BR>
     *　@　@　@　@　@　@・指定なし<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02009<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4147E5C80308
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

        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.error("要求ページ番号数字以外の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090, getClass().getName() + STR_METHOD_NAME, "要求ページ番号数字以外の文字" + this.pageIndex);
        }
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            log.error("要求ページ番号マイナス値の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616, getClass().getName() + STR_METHOD_NAME, "要求ページ番号マイナス値の文字" + this.pageIndex);
        }

        if (this.pageSize == null || "".equals(this.pageSize))
        {
            log.error("ページ内表示行数未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数未入力" + this.pageSize);
        }
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.error("ページ内表示行数数字以外の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数数字以外の文字" + this.pageSize);
        }
        if (Double.parseDouble(this.pageSize) <= 0)
        {
            log.error("ページ内表示行数マイナス値の文字が含まれる場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数マイナス値の文字" + this.pageSize);
        }
        if (this.accountCodeFrom == null)
        {
            log.error("顧客コード（自）未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02007, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数マイナス値の文字" + this.pageSize);
        }
        if (this.accountCodeTo == null)
        {
            log.error("顧客コード（至）未入力の場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02008, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数マイナス値の文字" + this.pageSize);
        }
        if (!(this.sendFlag == null) && !(WEB3AccountOpenMailFlagDef.sendFlag.equals(this.sendFlag))
        		 && !(WEB3AccountOpenMailFlagDef.unSendFlag.equals(this.sendFlag)))
        {
            log.error("送信フラグ以下の値に該当しない場合、例外をスロー");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02009, getClass().getName() + STR_METHOD_NAME, "ページ内表示行数マイナス値の文字" + this.pageSize);

        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
