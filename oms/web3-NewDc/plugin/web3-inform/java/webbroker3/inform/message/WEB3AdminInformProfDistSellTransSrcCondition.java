head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先検索条件クラス(WEB3AdminInformProfDistSellTransSrcCondition.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.045
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (利金・分配金・売却代金振込先検索条件クラス)<BR>
 * 利金・分配金・売却代金振込先検索条件クラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcCondition extends Message
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcCondition.class);

    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;

    /**
     * (扱者コード)<BR>
     * 扱者コード
     */
    public String traderCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;

    /**
     * (指定区分)<BR>
     * 指定区分
     */
    public String specifyDiv;

    /**
     * (商品)<BR>
     * 商品
     */
    public String product;

    /**
     * (振替区分)<BR>
     * 振替区分
     */
    public String transferDiv;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;

    /**
     * (登録日（自）)<BR>
     * 登録日（自）
     */
    public Date registDateFrom;

    /**
     * (登録日（至）)<BR>
     * 登録日（至）
     */
    public Date registDateTo;

    /**
     * @@roseuid 465593750398
     */
    public WEB3AdminInformProfDistSellTransSrcCondition()
    {

    }

    /**
     * 整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     * 　@１－１）以下の場合、例外の「部店コードエラー」をthrowする。<BR>
     * <BR>
     * 　@　@部店コード != null and<BR>
     * 　@　@部店コード != 半角数字<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）扱者コード<BR>
     * <BR>
     * 　@２－１）以下の場合、例外の「扱者コードエラー」をthrowする。<BR>
     * <BR>
     * 　@　@扱者コード != null and<BR>
     * 　@　@扱者コード != 半角数字<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@BUSINESS_ERROR_02782<BR>
     * <BR>
     * ３）顧客コード<BR>
     * <BR>
     * 　@３－１）以下の場合、例外の「顧客コードエラー」をthrowする。<BR>
     * <BR>
     * 　@　@顧客コード != null and<BR>
     * 　@　@顧客コード != 半角数字<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@BUSINESS_ERROR_00780<BR>
     * <BR>
     * ４）銘柄コード<BR>
     * <BR>
     * 　@４－１）以下の場合、例外の「銘柄コードエラー」をthrowする。<BR>
     * <BR>
     * 　@　@銘柄コード != null and<BR>
     * 　@　@銘柄コード != 半角数字<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@BUSINESS_ERROR_01067<BR>
     * <BR>
     * ５）登録日<BR>
     * <BR>
     * 　@５－１）以下の場合、例外の「日付エラー」をthrowする。<BR>
     * <BR>
     * 　@　@登録日（自）　@!=　@null　@and<BR>
     * 　@　@登録日（至）　@!=　@null　@and<BR>
     * 　@　@登録日（自）　@> 登録日（至）<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@BUSINESS_ERROR_02222<BR>
     * @@throws WEB3BaseException
     * @@roseuid 461F213A007B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １－１）以下の場合、例外の「部店コードエラー」をthrowする。
        // 部店コード != null and 部店コード != 半角数字
        if (this.branchCode != null && !WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("部店コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードの入力が不正です。");
        }

        // ２－１）以下の場合、例外の「扱者コードエラー」をthrowする。
        // 扱者コード != null and 扱者コード != 半角数字
        if (this.traderCode != null && !WEB3StringTypeUtility.isDigit(this.traderCode))
        {
            log.debug("扱者コード（文字列）の長さが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02782,
                this.getClass().getName() + STR_METHOD_NAME,
                "扱者コードエラー。");
        }

        // ３－１）以下の場合、例外の「顧客コードエラー」をthrowする。
        // 顧客コード != null and 顧客コード != 半角数字
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("顧客コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コードの入力が不正です。");
        }

        // ４－１）以下の場合、例外の「銘柄コードエラー」をthrowする。
        // 銘柄コード != null and 銘柄コード != 半角数字
        if (this.productCode != null && !WEB3StringTypeUtility.isDigit(this.productCode))
        {
            log.debug("銘柄コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コードの入力が不正です。");
        }

        // ５－１）以下の場合、例外の「日付エラー」をthrowする。
        // 登録日（自） != null and 登録日（至） != null and 登録日（自） > 登録日（至）
        if (registDateFrom != null && registDateTo != null && (WEB3DateUtility.compare(registDateFrom, registDateTo) > 0))
        {
            log.debug("登録日（自）が登録日（至）を超えています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02222,
                this.getClass().getName() + STR_METHOD_NAME,
                "登録日（自）が登録日（至）を超えています。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
