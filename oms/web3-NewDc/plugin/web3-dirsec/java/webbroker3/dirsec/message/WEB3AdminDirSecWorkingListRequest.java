head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecWorkingListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・稼動状況一覧リクエスト(WEB3AdminDirSecWorkingListRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/28 柴双紅(中訊) 新規作成 モデルNo.117、No.124、No.126
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
 * (管理者・稼動状況一覧リクエスト)<BR>
 * 管理者・稼動状況一覧リクエスト<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminDirSecWorkingListRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecWorkingListRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dir_sec_working_list";

    /**
     * serialVersionUID
     */
    public static final long serialVersionUID = 200804281135L;

    /**
     * (部店コード)<BR>
     * ログイン管理者が権限を持つ部店コードの配列<BR>
     */
    public String[] branchCode;

    /**
     * @@roseuid 481155FD0315
     */
    public WEB3AdminDirSecWorkingListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * ○部店一覧のチェック<BR>
     * <BR>
     * １）　@this.部店コード==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01429<BR>
     * <BR>
     * ２）　@this.部店コードの要素数==0の場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_02175<BR>
     * <BR>
     * ３）　@this.部店コードの要素数分、以下を繰り返す。<BR>
     * 　@３－１）this.コード.部店コード ==null の場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@３－２）半角数字以外の文字が入力されているまたは、桁数!=3桁場合、例外をスローする。<BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * 　@　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_00834<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D08BD2002B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.部店コード==nullの場合、例外をスローする
        if (this.branchCode == null)
        {
            log.debug("部店コード一覧が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード一覧が未指定です。");
        }

        //this.部店コードの要素数==0の場合、例外をスローする
        if (this.branchCode.length == 0)
        {
            log.debug("部店コードの要素数が0です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が0です。");
        }

        //this.部店コードの要素数分、以下を繰り返す
        for (int i = 0; i < this.branchCode.length; i++)
        {
            //this.コード.部店コード ==null の場合、例外をスローする
            if (this.branchCode[i] == null)
            {
                log.debug("部店コードが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが未指定です。");
            }

            //半角数字以外の文字が入力されているまたは、桁数!=3桁場合、例外をスローする
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("部店コードが数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }

            if (this.branchCode[i].length() != 3)
            {
                log.debug("部店コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }
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
        return new WEB3AdminDirSecWorkingListResponse(this);
    }
}
@
