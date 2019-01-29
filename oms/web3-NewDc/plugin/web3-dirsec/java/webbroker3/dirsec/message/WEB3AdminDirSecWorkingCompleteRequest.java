head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.11.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecWorkingCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・稼動状況変更完了リクエスト(WEB3AdminDirSecWorkingCompleteRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/25 柴双紅(中訊) 新規作成 モデルNo.117、No.124
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・稼動状況変更完了リクエスト)<BR>
 * 管理者・稼動状況変更完了リクエスト<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminDirSecWorkingCompleteRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecWorkingCompleteRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dir_sec_working_complete";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200804251735L;

    /**
     * (電子鳩システム会社部店別プリファ@レンステーブルレコード詳細)<BR>
     * 電子鳩システム会社部店別プリファ@レンステーブルレコード詳細<BR>
     */
    public WEB3AdminDirSecBatoPreferenceRecordDetail[] batoPreferenceRecord;

    /**
     * (暗証番号)<BR>
     * 暗証番号。<BR>
     */
    public String password;

    /**
     * @@roseuid 481169A30140
     */
    public WEB3AdminDirSecWorkingCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@リクエストデータチェック<BR>
     * <BR>
     * １−１）　@this.リクエストデータ==nullの場合、 例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03076<BR>
     * <BR>
     * １−２）　@this.リクエストデータの要素数==0 の場合、 例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03077<BR>
     * <BR>
     * １−３）　@this.リクエストデータの要素数分、以下を繰り返す。<BR>
     * 　@１−３−１）　@this.リクエストデータ.部店コード ==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１−３−２）　@this.リクエストデータ.部店コードが半角数字以外の文字が入力されている<BR>
     * 　@　@または、桁数!=3桁場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00834<BR>
     * <BR>
     * 　@１−３−３）　@this.リクエストデータ.システム障害フラグ ==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03080<BR>
     * <BR>
     * 　@１−３−４）　@this.リクエストデータ.システム障害フラグが半角数字以外の文字<BR>
     * 　@　@が入力されているまたは、桁数!=1桁場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03078<BR>
     * <BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_03079<BR>
     * <BR>
     * ２）　@暗証番号チェック<BR>
     * 　@２−１）this.暗証番号 == nullの場合、<BR>
     * 　@　@　@　@　@「暗証番号が未入力です。」の例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_02510<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C4ABF00270
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.リクエストデータ==nullの場合、 例外をスローする
        if (this.batoPreferenceRecord == null)
        {
            log.debug("リクエストデータが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03076,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータが未指定です。");
        }

        //this.リクエストデータの要素数==0 の場合、 例外をスローする
        if (this.batoPreferenceRecord.length == 0)
        {
            log.debug("リクエストデータの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03077,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータの要素数が０です。");
        }

        //this.リクエストデータの要素数分、以下を繰り返す
        for (int i = 0; i < this.batoPreferenceRecord.length; i++)
        {
            //this.リクエストデータ.部店コード ==null の場合、例外をスローする
            if (this.batoPreferenceRecord[i].branchCode == null)
            {
                log.debug("部店コードが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが未指定です。");
            }

            //リクエストデータ.部店コードが半角数字以外の文字が
            // 入力されているまたは、桁数!=3桁場合、例外をスローする
            if (!WEB3StringTypeUtility.isDigit(this.batoPreferenceRecord[i].branchCode))
            {
                log.debug("部店コードが数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }

            if (this.batoPreferenceRecord[i].branchCode.length() != 3)
            {
                log.debug("部店コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }

            //this.リクエストデータ.システム障害フラグ ==null の場合、例外をスローする。
            if (this.batoPreferenceRecord[i].systemTroubleDiv == null)
            {
                log.debug("システム障害フラグが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03080,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "システム障害フラグが未指定です。");
            }

            //this.リクエストデータ.システム障害フラグが半角数字以外の文字が
            // 入力されているまたは、桁数!=1桁場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.batoPreferenceRecord[i].systemTroubleDiv))
            {
                log.debug("システム障害フラグが半角数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03078,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "システム障害フラグが半角数字以外の値です。");
            }

            if (this.batoPreferenceRecord[i].systemTroubleDiv.length() != 1)
            {
                log.debug("システム障害フラグのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03079,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "システム障害フラグのサイズが不正です。");
            }
        }

        //暗証番号チェック
        //this.暗証番号 == nullの場合、「暗証番号が未入力です。」の例外をスローする。
        if (this.password == null)
        {
            log.debug("暗証番号が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02510,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "暗証番号が未入力です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecWorkingCompleteResponse(this);
    }
}
@
