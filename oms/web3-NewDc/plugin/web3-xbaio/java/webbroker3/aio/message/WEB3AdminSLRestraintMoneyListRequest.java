head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLRestraintMoneyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン出金拘束金一覧リクエスト(WEB3AdminSLRestraintMoneyListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 孫洪江 (中訊) 新規作成 仕様変更モデル764
Revision History : 2007/09/18 金傑（中訊）モデルNo.768
Revision History : 2007/09/20 金傑（中訊）モデルNo.780
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証券担保ローン出金拘束金一覧リクエスト)<BR>
 * 証券担保ローン出金拘束金一覧リクエストクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminSLRestraintMoneyListRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLRestraintMoneyListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_restraint_money_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709131520L;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode;

    /**
     * (出金停止区分)<BR>
     * 0:通常 1:停止中<BR>
     */
    public String cashOutStopDiv;

    /**
     * (要求ページ番号)<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     */
    public String pageSize;

    /**
     * (ソートキー)<BR>
     */
    public WEB3SLSortKey[] sortKeys;

    public WEB3AdminSLRestraintMoneyListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * 1) 部店コードのチェック<BR>
     * 　@1-1) this.部店コード !=null であり、且つ桁数!=3桁の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00834<BR>
     * 　@1-2) 半角数字以外の文字が入力されている場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * 2) 顧客コードのチェック<BR>
     * 　@2-1) this.顧客コード !=null であり、且つ桁数!=6桁の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00836<BR>
     * 　@2-2) 半角数字以外の文字が入力されている場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01043<BR>
     * <BR>
     * 3) 出金停止区分のチェック<BR>
     * 　@3-1) this.出金停止区分 !=null であり、且つ桁数が2桁以上の場合、<BR>
     * 　@例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR__02918<BR>
     * 　@3-2) 半角数字以外の文字が入力されている場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR__02919<BR>
     * <BR>
     * 4) 要求ページ番号チェック<BR>
     * 　@4-1) this.要求ページ番号==nullの値であれば例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00089<BR>
     * 　@4-2) this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00090<BR>
     * 　@4-3) this.要求ページ番号<= 0であれば例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * 5) ページ内表示行数チェック<BR>
     * 　@5-1) this.ページ内表示行数==nullの値であれば例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00091<BR>
     * 　@5-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00092<BR>
     * 　@5-3) this.ページ内表示行数<= 0であれば例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * 6) 担保ローンソートキーのチェック<BR>
     * 　@6-1) this.担保ローンソートキー==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00231<BR>
     * 　@6-2) this.担保ローンソートキーの要素数==0の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00232<BR>
     * 　@6-3) this.担保ローンソートキーの要素数分、以下を繰り返す。<BR>
     * 　@　@6-3-1) this.担保ローンソートキー.キー項目==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00085<BR>
     * 　@　@6-3-2) this.担保ローンソートキー.昇順／降順==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00087<BR>
     * 　@　@6-3-3) this.担保ローンソートキー.昇順／降順が以下の値以外だった場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@　@"A:昇順" <BR>
     * 　@　@　@　@　@"D:降順" <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1) 部店コードのチェック
        //  1-1) this.部店コード !=nullの場合
        if (this.branchCode != null)
        {
            // this.部店コード !=3桁の場合、例外をスローする。
            if (this.branchCode.length() != 3)
            {
                log.debug("部店コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "部店コードのサイズが不正です。");
            }
            //  1-2) 半角数字以外の文字が入力されている場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.branchCode))
            {
                log.debug("部店コードが数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "部店コードが数値以外の値です。");
            }
        }

        // 2) 顧客コードのチェック
        //  2-1) this.顧客コード !=nullの場合
        if (this.accountCode != null)
        {
            // this.accountCode.length() != 6の場合、例外をスローする
            if (this.accountCode.length() != 6)
            {
                log.debug("顧客コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "顧客コードのサイズが不正です。");
            }
            //  2-2) 半角数字以外の文字が入力されている場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("顧客コードの値が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "顧客コードの値が数字以外の値です。");
            }
        }

        // 3) 出金停止区分のチェック
        //  3-1) this.出金停止区分 !=nullの場合
        if (this.cashOutStopDiv != null)
        {
            // this.cashOutStopDiv.length() >= 2の場合、例外をスローする
            if (this.cashOutStopDiv.length() >= 2)
            {
                log.debug("出金停止区分のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02918,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "出金停止区分のサイズが不正です。");
            }
            //  3-2) 半角数字以外の文字が入力されている場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.cashOutStopDiv))
            {
                log.debug("出金停止区分が半角数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02919,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                   "出金停止区分が半角数字以外の値です。");
            }
        }

        // 4) 要求ページ番号チェック
        //  4-1) this.要求ページ番号==nullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        //  4-2) this.要求ページ番号が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        //  4-3) this.要求ページ番号<= 0であれば例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        // 5) ページ内表示行数チェック
        //  5-1) this.ページ内表示行数==nullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        //  5-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        //  5-3) this.ページ内表示行数<= 0であれば例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        // 6) 担保ローンソートキーのチェック
        //  6-1) this.担保ローンソートキー==nullの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        //  6-2) this.担保ローンソートキーの要素数==0の場合、例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        //  6-3) this.担保ローンソートキーの要素数分、以下を繰り返す。
        int l_intCntForSortKeys = this.sortKeys.length;
        for (int i = 0; i < l_intCntForSortKeys; i++)
        {
          // 6-3-1) this.担保ローンソートキー.キー項目==nullの場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目が未指定です。");
            }

          // 6-3-2) this.担保ローンソートキー.昇順／降順==nullの場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].ascDesc))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が未指定です。");
            }

            // 6-3-3) this.担保ローンソートキー.昇順／降順が以下の値以外だった場合、例外をスローする。
            //　@　@"A:昇順"
            //　@　@"D:降順"
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLRestraintMoneyListResponse(this);
    }
}
@
