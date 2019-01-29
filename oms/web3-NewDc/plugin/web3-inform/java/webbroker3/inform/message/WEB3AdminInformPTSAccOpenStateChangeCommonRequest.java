head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS口座開設状況変更共通リクエスト(WEB3AdminInformPTSAccOpenStateChangeCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 柴双紅(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * 管理者・PTS口座開設状況変更共通リクエスト<BR>
 * 管理者・PTS口座開設状況変更共通リクエストクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeCommonRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformPTSAccOpenStateChangeCommonRequest.class);

    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;

    /**
     * (変更後申込区分)<BR>
     * 変更後申込区分<BR>
     * <BR>
     * 0：未開設<BR>
     * 1：開設<BR>
     */
    public String afterPtsAccOpenDiv;

    /**
     * @@roseuid 47C522D401A2
     */
    public WEB3AdminInformPTSAccOpenStateChangeCommonRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
     public WEB3GenResponse createResponse()
     {
         return null;
     }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）　@桁数が3でない場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     * 　@１－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00835<BR>
     * <BR>
     * 　@２－２）　@桁数が6でない場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00836<BR>
     * <BR>
     * 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01043<BR>
     * <BR>
     * ３）　@変更後申込区分のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_03045<BR>
     * <BR>
     * @@param WEB3BaseException
     * @@roseuid 47B4F3110293
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //部店コードのチェック
        //未入力の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("部店コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }

        //桁数が3でない場合、例外をスローする。
        if (this.branchCode.length() != 3)
        {
            log.debug("部店コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "部店コードのサイズが不正です。");
        }

        //数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("部店コードが数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが数値以外の値です。");
        }

        //顧客コードのチェック
        //未入力の場合、例外をスローする。
        if (this.accountCode == null)
        {
            log.debug("顧客コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");
        }

        //桁数が6でない場合、例外をスローする。
        if (this.accountCode.length() != 6)
        {
            log.debug("顧客コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードのサイズが不正です。");
        }

        //数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }

        //変更後申込区分のチェック
        //未入力の場合、例外をスローする。
        if (this.afterPtsAccOpenDiv == null)
        {
            log.debug("変更後申込区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03045,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更後申込区分が未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
