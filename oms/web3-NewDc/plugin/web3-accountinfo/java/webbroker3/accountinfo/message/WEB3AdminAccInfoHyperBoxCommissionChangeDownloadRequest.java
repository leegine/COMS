head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest extends WEB3GenRequest
{

    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest.class);
           
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_hyperBoxCommissionChangeDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082134L;

    /**
     * (適用開始日)<BR>
     * 適用開始日<BR>
     */
    public Date trialStartDate;

    /**
     * (適用終了日)<BR>
     * 適用終了日<BR>
     */
    public Date trialEndDate;

    /**
     * (手数料Ｎｏ．)<BR>
     * 手数料Ｎｏ．<BR>
     */
    public String commissionNo;

    /**
     * (徴収率)<BR>
     * 徴収率<BR>
     */
    public String collectRate;

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
     * @@roseuid 418F3853033C
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse(this);
    }

    /**
     * (validate)<BR>
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@適用開始日のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * ２）　@適用終了日のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00838<BR>
     * 　@２－２）　@（適用開始日 > 適用終了日）の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * <BR>
     * ３）　@手数料Ｎｏ．のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01141<BR>
     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01142<BR>
     * 　@３－３）　@サイズが2byteでない場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01143<BR>
     * <BR>
     * ４）　@徴収率のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01144<BR>
     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01145<BR>
     * 　@３－３）　@有効値が0～100の間でない場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01146<BR>
     * <BR>
     * ５）　@要求ページ番号チェック <BR>
     * 　@５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ６）　@ページ内表示行数チェック <BR>
     * 　@６－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * 　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * 　@６－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4146A8A70254
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //     * １）　@適用開始日のチェック<BR>
        //* 　@１－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00837<BR>
        if (this.trialStartDate == null)
        {
            //例外
            log.debug("[適用開始日] = " + trialStartDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                this.getClass().getName() + STR_METHOD_NAME, "適用開始日の未入力の場合");
        }
        
        //     * ２）　@適用終了日のチェック<BR>
        //* 　@２－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00838<BR>
        if (this.trialEndDate == null)
        {
            //例外
            log.debug("[適用終了日] = " + trialEndDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00838,
                this.getClass().getName() + STR_METHOD_NAME, "適用終了日の未入力の場合");
        }
        
        //     * 　@２－２）　@（適用開始日 > 適用終了日）の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00839<BR>
        if (this.trialStartDate.compareTo(this.trialEndDate) > 0)
        {
            //例外
            log.debug("[適用開始日] = " + trialEndDate);
            log.debug("[適用終了日] = " + trialEndDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                this.getClass().getName() + STR_METHOD_NAME, "（適用開始日 > 適用終了日）の場合");
        }
        
        //     * ３）　@手数料Ｎｏ．のチェック<BR>
        //* 　@３－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01141<BR>
        if (this.commissionNo == null || "".equals(this.commissionNo))
        {
            //例外
            log.debug("[手数料Ｎｏ．] = " + commissionNo);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01141,
                this.getClass().getName() + STR_METHOD_NAME, "手数料Ｎｏ．の未入力の場合");
        }
        
        //     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01142<BR>
        if (!WEB3StringTypeUtility.isDigit(this.commissionNo))
        {
            //例外
            log.debug("[手数料Ｎｏ．] = " + commissionNo);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01142,
                this.getClass().getName() + STR_METHOD_NAME, "手数料Ｎｏ．の数字以外の文字が含まれる場合");
        }
        
        //* 　@３－３）　@サイズが2byteでない場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01143<BR>
        if (WEB3StringTypeUtility.getByteLength(this.commissionNo) != 2)
        {
            //例外
            log.debug("[手数料Ｎｏ．] = " + commissionNo);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01143,
                this.getClass().getName() + STR_METHOD_NAME, "手数料Ｎｏ．のサイズが2byteでない場合");
        }
        
        //     * ４）　@徴収率のチェック<BR>
        //* 　@３－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01144<BR>
        if (this.collectRate == null || "".equals(this.collectRate))
        {
            //例外
            log.debug("[徴収率] = " + collectRate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01144,
                this.getClass().getName() + STR_METHOD_NAME,
                "徴収率のチェック未入力の場合");
        }
        
        //     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01145<BR>
        if (!WEB3StringTypeUtility.isDigit(this.collectRate))
        {
            //例外
            log.debug("[徴収率] = " + collectRate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01145,
                this.getClass().getName() + STR_METHOD_NAME, " 徴収率の数字以外の文字が含まれる場合 ");
        }
        
        //     * 　@３－３）　@有効値が0～100の間でない場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01146<BR>
        if (Integer.parseInt(this.collectRate) < 0 ||
            Integer.parseInt(this.collectRate) > 100)
        {
            //例外
            log.debug("[徴収率] = " + collectRate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01146,
                this.getClass().getName() + STR_METHOD_NAME, "徴収率の有効値が0～100の間でない場合");
        }
        
        //     * ５）　@要求ページ番号チェック <BR>
       // * 　@５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.debug("[要求ページ番号] = " + pageIndex);
            this.pageIndex = "1"; 
        }
       
       //* 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
       // *         class: WEB3BusinessLayerException<BR>
       // *         tag:   BUSINESS_ERROR_00090<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
           //例外
           log.debug("[要求ページ番号] = " + pageIndex);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00090,
               this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号数字以外の文字が含まれる場合");
        }
       
       //     * 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
       //*         class: WEB3BusinessLayerException<BR>
       //*         tag:   BUSINESS_ERROR_00616<BR>
        double l_dblPageIndex = Double.parseDouble(this.pageIndex);
        if (l_dblPageIndex <= 0)
        {
              //例外
            log.debug("[要求ページ番号] = " + pageIndex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号マイナス値の場合");        
        }
       
       //     * ６）　@ページ内表示行数チェック <BR>
       //* 　@６－１）　@未入力の場合、例外をスローする。 <BR>
       //*         class: WEB3BusinessLayerException<BR>
       //*         tag:   BUSINESS_ERROR_00091<BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
           //例外
           log.debug("[ページ内表示行数] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091,
               this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数未入力の場合");
        }
       
       //* 　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
       //*         class: WEB3BusinessLayerException<BR>
       //*         tag:   BUSINESS_ERROR_00092<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
           //例外
           log.debug("[ページ内表示行数] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + STR_METHOD_NAME,"ページ内表示行数数字以外の文字が含まれる場合");
        }
       
       //* 　@６－３）　@マイナス値の場合、例外をスローする。<BR>
       //*         class: WEB3BusinessLayerException<BR>
       //*         tag:   BUSINESS_ERROR_00617<BR>
        double l_dblPageSize = Double.parseDouble(this.pageSize);
        if (l_dblPageSize <= 0)
        {
           //例外
           log.debug("[ページ内表示行数] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00617,
               this.getClass().getName() + STR_METHOD_NAME,"ページ内表示行数マイナス値の場合");        
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
