head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済ソートキー(WEB3AdminForcedSettleSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成 モデルNo.128
Revision History : 2007/09/11 柴双紅 (中訊) モデルNo.164
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (強制決済ソートキー)<BR>
 * 強制決済ソートキークラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleSortKeyUnit extends Message
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleSortKeyUnit.class);

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * ※使用するキー項目はvalidate()メソッド内にて定義。<BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * 昇順／降順<BR>
     * <BR>
     * A：　@昇順<BR>
     * D：　@降順<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 462CA4290227
     */
    public WEB3AdminForcedSettleSortKeyUnit()
    {

    }

    /**
     * １）this.キー項目 == nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２） this.キー項目に下記の項目以外が<BR>
     * 　@設定されていたら、<BR>
     * 　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@・"部店コード"<BR>
     * 　@　@・"顧客コード"<BR>
     * 　@　@・"強制決済理由"<BR>
     * 　@　@・"市場コード"<BR>
     * 　@　@・"銘柄コード"<BR>
     * 　@　@・"口座区分"<BR>
     * 　@　@・"建区分"<BR>
     * 　@　@・"弁済区分"<BR>
     * 　@　@・"弁済期限値"<BR>
     * 　@　@・"建日"<BR>
     * 　@　@・"決済期日"<BR>
     * 　@　@・"作成日時"<BR>
     * 　@　@・"（非）承認日時"<BR>
     * 　@　@・"建株数"<BR>
     * 　@　@・"建単価"<BR>
     * 　@　@・"建代金"<BR>
     * 　@　@・"注文株数"<BR>
     * 　@　@・"保証金預託率"<BR>
     * 　@　@・"承認状態"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ３）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00087<BR>
     * <BR>
     * ４）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順” <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws  WEB3BaseException
     * @@roseuid 45FF628E03A9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.キー項目 == nullの場合、<BR>
        //ソートキー.キー項目がnull」の例外をスローする。
        if (this.keyItem == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }

        //* ２） this.キー項目に下記の項目以外が<BR>
        //* 　@設定されていたら、<BR>
        //* 　@「ソートキー.キー項目が未定義の値」の例外をスローする。
        if (this.keyItem != null)
        {
            if (!WEB3AdminEquitySortKeyItemNameDef.BRANCH_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.ACCOUNT_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.FORCED_SETTLE_REASON.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.TAX_TYPE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_TYPE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.REPAYMENT_DIV.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.REPAYMENTTIME_LIMIT.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.OPEN_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CLOSE_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CREATE_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.APPROVE_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_QUANTITY.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_PRICE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.CONTRACT_EXEC_PRICE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.ORDER_QUANTITY.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.MARGIN_COLLATERAL_RATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.APPROVE_STATE.equals(this.keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }

        //this.昇順／降順＝nullの場合、
        //「ソートキー.昇順／降順がnull」の例外をスローする。
        if (this.ascDesc == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }

        //４）this.昇順／降順が下記の項目以外の場合、<BR>
        //「ソートキー.昇順／降順が未定義の値」の例外をスローする。
        if (this.ascDesc != null)
        {
            if (!WEB3AscDescDef.ASC.equals(this.ascDesc)
                && !WEB3AscDescDef.DESC.equals(this.ascDesc))
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
}
@
