head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAccountConnectionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客連絡リクエスト(WEB3PvInfoAccountConnectionRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/20 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (顧客連絡リクエスト)<BR>
 * 顧客連絡リクエストクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoAccountConnectionRequest extends WEB3GenRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoAccountConnectionRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_accountConnection";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;

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
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）要求ページ番号チェック <BR>
     * 　@１－１）this.要求ページ番号 == nullであった場合、 <BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@１－２）this.要求ページ番号が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@１－３）this.要求ページ番号 <= 0であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ２）ページ内表示行数チェック <BR>
     * 　@２－１）this.ページ内表示行数 == nullであった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@２－２）this.ページ内表示行数が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>　@
     * 　@２－３）this.ページ内表示行数 <= 0であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * @@roseuid 41452AB302AE
     */
    public void validate() throws WEB3BusinessLayerException
    {

        String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）要求ページ番号チェック
        log.debug("１）　@要求ページ番号チェック: ENTER");

        //１－１）this.要求ページ番号 == nullであった場合、「要求ページ番号がnull」の例外をスローする。
        log.debug("１－１）　@要求ページ番号nullチェック: ENTER");
        if (this.pageIndex == null)
        {
            log.error("要求ページ番号がnull");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("１－１）　@要求ページ番号nullチェック: END");

        //１－２）this.要求ページ番号が数字以外の値であった場合、「要求ページ番号が数字以外」の例外をスローする。
        log.debug("１－２）　@要求ページ番号numberチェック: ENTER");
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error("要求ページ番号が数字以外のエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("１－２）　@要求ページ番号numberチェック: END");

        //１－３）this.要求ページ番号 <= 0であった場合、「要求ページ番号が0以下」の例外をスローする。
        log.debug("１－３）　@要求ページ番号valueチェック: ENTER");
        double l_dblPageIndex = Double.parseDouble(this.pageIndex);
        if (l_dblPageIndex <= 0)
        {
            log.error("要求ページ番号valueが0以下");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("１－３）　@要求ページ番号valueチェック: END");

        log.debug("１）　@要求ページ番号チェック: END");

        //２）ページ内表示行数チェック
        log.debug("２）　@ページ内表示行数チェック: ENTER");

        //２－１）this.ページ内表示行数 == nullであった場合、「ページ内表示行数がnull」の例外をスローする。
        log.debug("２－１）　@ページ内表示行数nullチェック: ENTER");
        if (this.pageSize ==null )
        {
            log.error("ページ内表示行数null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("２－１）　@ページ内表示行数nullチェック: END");

        //２－２）this.ページ内表示行数が数字以外の値であった場合、「ページ内表示行数が数字以外」の例外をスローする。
        log.debug("２－２）　@ページ内表示行数numberチェック: ENTER");
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error("ページ内表示行数を数字以外のエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("２－２）　@ページ内表示行数numberチェック: END");

        //２－３）this.ページ内表示行数 <= 0であった場合、「ページ内表示行数が0以下」の例外をスローする。
        log.debug("２－３）　@ページ内表示行数valueチェック: ENTER");
        double l_dbPageSize = Double.parseDouble(this.pageSize);
        if (l_dbPageSize <= 0)
        {
            log.error("ページ内表示行数valueエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("２－３）　@ページ内表示行数valueチェック: END");

        log.debug("２）　@ページ内表示行数チェック: END");

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 417343990167
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3PvInfoAccountConnectionResponse(this);
    }
}
@
