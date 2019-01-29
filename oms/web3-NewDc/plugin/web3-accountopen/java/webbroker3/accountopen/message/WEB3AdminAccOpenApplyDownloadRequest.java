head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.57.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込ダウンロードリクエスト(WEB3AdminAccOpenApplyDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者口座開設申込ダウンロードリクエスト)<BR>
 * 管理者口座開設申込ダウンロードリクエスト<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDownloadRequest extends WEB3GenRequest
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_applyDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081613L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0：個人アカウント　@1：法@人アカウント<BR>
     */
    public String accountType;

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
     * (識別コード（自）)<BR>
     * 識別コード（自）<BR>
     */
    public String requestNumberFrom;

    /**
     * (識別コード（至）)<BR>
     * 識別コード（至）<BR>
     */
    public String requestNumberTo;

    /**
     * (資料請求日（自）)<BR>
     * 資料請求日（自）<BR>
     */
    public Date infoClaimDateFrom;

    /**
     * (資料請求日（至）)<BR>
     * 資料請求日（至）<BR>
     */
    public Date infoClaimDateTo;

    /**
     * (口座開設日（自）)<BR>
     * 口座開設日（自）<BR>
     */
    public Date accountOpenDateFrom;

    /**
     * (口座開設日（至）)<BR>
     * 口座開設日（至）<BR>
     */
    public Date accountOpenDateTo;
    
    /**
     * (ダウンロード件数)<BR>
     * ダウンロード件数<BR>
     */
    public String downloadNumber;
    
    /**
     * (口座開設区分)<BR>
     * 口座開設区分<BR>
     * <BR>
     * 0：口座未開設　@1：口座開設済み<BR>
     * ※null可<BR>
     */    
    public String accountOpenDiv;

    /**
     * (検索オプション区分)<BR>
     * 検索オプション区分<BR>
     * <BR>
     * 0：なし　@1：あり<BR>
     * ※null可<BR>
     */    
    public String searchOptionDiv;
    
    /**
     * @@roseuid 41B45E7B0222
     */
    public WEB3AdminAccOpenApplyDownloadRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyDownloadResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コード[]のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@口座区分のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00604<BR>
     * 　@２－２）　@不正なコード値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00605<BR>
     * <BR>
     * ３）　@資料請求日（自），資料請求日（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@３－１）　@（自） > （至）であれば、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01326<BR>
     * <BR>
     * ４）　@口座開設日（自），口座開設日（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@４－１）　@（自） > （至）であれば、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01328<BR>
     * <BR>
     * ５）　@顧客コード（自），顧客コード（至）のチェック<BR>
     * 　@５－１）　@（自） > （至）であれば、例外をスローする。<BR>
     * ※（自），（至）の両方に入力がある場合のみ<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00795<BR>
     * 　@５－２）　@顧客コード（自）に半角数字以外の文字が含まれる場合、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01330<BR>
     * 　@５－３）　@顧客コード（至）に半角数字以外の文字が含まれる場合、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01331<BR>
     * <BR>
     * ６）　@識別コード（自），識別コード（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@６－１）　@（自） > （至）であれば、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01333<BR>
     * 　@６－２）　@識別コード（自）に半角数字以外の文字が含まれる場合、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01334<BR>
     * 　@６－３）　@識別コード（至）に半角数字以外の文字が含まれる場合、<BR>
     * 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01335<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A161340293
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@部店コード[]のチェック
        // 　@１－１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            String l_strMessage = "部店コード未入力!";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // ２）　@口座区分のチェック
        // 　@２－１）　@未入力の場合、例外をスローする。
        if (this.accountType == null || "".equals(this.accountType.trim()))
        {
            String l_strMessage = "口座区分未入力!";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00604,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // 　@２－２）　@不正なコード値の場合、例外をスローする。
        if (!WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(this.accountType) && 
            !WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(this.accountType))
        {
            String l_strMessage = "口座区分の値、0：個人アカウント、1：法@人アカウント以外の場合. accountType = " + this.accountType;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // ３）　@資料請求日（自），資料請求日（至）のチェック
        // 　@３－１）　@（自） > （至）であれば、例外をスローする。
        if (this.infoClaimDateFrom != null && this.infoClaimDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.infoClaimDateFrom, this.infoClaimDateTo) > 0)
            {
                String l_strMessage = "資料請求日（自）> 資料請求日 （至）." 
                    + " 資料請求日（自）= " + this.infoClaimDateFrom
                    + "; 資料請求日 （至）= " + this.infoClaimDateTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01326,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        // ４）　@口座開設日（自），口座開設日（至）のチェック
        // 　@４－１）　@（自） > （至）であれば、例外をスローする。
        if (this.accountOpenDateFrom != null && this.accountOpenDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.accountOpenDateFrom, this.accountOpenDateTo) > 0)
            {
                String l_strMessage = "口座開設日（自）> 口座開設日 （至）." 
                    + " 口座開設日（自）= " + this.accountOpenDateFrom
                    + "; 口座開設日 （至）= " + this.accountOpenDateTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01328,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // ５）　@顧客コード（自），顧客コード（至）のチェック
        // 　@５－１）　@（自） > （至）であれば、例外をスローする。
        if (this.accountCodeFrom != null && this.accountCodeTo != null)
        {
            if (this.accountCodeFrom.compareTo(this.accountCodeTo) > 0 )
            {
                String l_strMessage = "顧客コード（自）> 顧客コード （至）." 
                    + " 顧客コード（自）= " + this.accountCodeFrom
                    + "; 顧客コード （至）= " + this.accountCodeTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00795,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // 　@５－２）　@顧客コード（自）に半角数字以外の文字が含まれる場合、
        if (this.accountCodeFrom != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.accountCodeFrom)
                || !WEB3StringTypeUtility.isDigit(this.accountCodeFrom))
            {
                String l_strMessage = "顧客コード（自）に半角数字以外の文字が含まれる場合." 
                    + " 顧客コード（自）= " + this.accountCodeFrom;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01330,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // 　@５－３）　@顧客コード（至）に半角数字以外の文字が含まれる場合、
        if (this.accountCodeTo != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.accountCodeTo)
                || !WEB3StringTypeUtility.isDigit(this.accountCodeTo))
            {
                String l_strMessage = "顧客コード（至）に半角数字以外の文字が含まれる場合." 
                    + " 顧客コード（至）= " + this.accountCodeTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01331,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // ６）　@識別コード（自），識別コード（至）のチェック
        // 　@６－１）　@（自） > （至）であれば、例外をスローする。
        if (this.requestNumberFrom != null && this.requestNumberTo != null)
        {
            if (this.requestNumberFrom.compareTo(this.requestNumberTo) > 0 )
            {
                String l_strMessage = "識別コード（自）> 識別コード （至）." 
                    + " 識別コード（自）= " + this.requestNumberFrom
                    + "; 識別コード （至）= " + this.requestNumberTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01333,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        // 　@６－２）　@識別コード（自）に半角数字以外の文字が含まれる場合、
        if (this.requestNumberFrom != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.requestNumberFrom)
                || !WEB3StringTypeUtility.isDigit(this.requestNumberFrom))
            {
                String l_strMessage = "識別コード（自）に半角数字以外の文字が含まれる場合." 
                    + " 識別コード（自）= " + this.requestNumberFrom;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01334,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        // 　@６－３）　@識別コード（至）に半角数字以外の文字が含まれる場合、
        if (this.requestNumberTo != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.requestNumberTo)
                || !WEB3StringTypeUtility.isDigit(this.requestNumberTo))
            {
                String l_strMessage = "識別コード（至）に半角数字以外の文字が含まれる場合." 
                    + " 識別コード（至）= " + this.requestNumberTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01335,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
