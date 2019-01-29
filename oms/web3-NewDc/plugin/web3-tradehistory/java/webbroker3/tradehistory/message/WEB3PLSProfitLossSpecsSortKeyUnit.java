head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細ソートキー(WEB3PLSProfitLossSpecsSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/15 何文敏(中訊) 新規作成　@モデル068
*/
package webbroker3.tradehistory.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.tradehistory.define.WEB3PLSProfitLossSpecsSortKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (損益明細ソートキー)<BR>
 * 損益明細ソートキークラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsSortKeyUnit extends Message
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsSortKeyUnit.class);

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * calcDate：　@計算年月日<BR>
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
     * (損益明細ソートキー)<BR>
     * コンストラクタ<BR>
     */
    public WEB3PLSProfitLossSpecsSortKeyUnit()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_00087<BR>
     * <BR>
     * ３）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_00088<BR>
     * <BR>
     * ４）this.キー項目が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”calcDate：計算年月日”<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag           :  BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）this.キー項目のチェック
        //     this.キー項目＝nullの場合、
        //     「ソートキー.キー項目がnull」の例外をスローする。
        if (this.keyItem == null)
        {
            log.error(" 「ソートキー.キー項目がnull」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }

        // ２）this.昇順／降順のチェック
        //     this.昇順／降順＝nullの場合、
        // 　@  「ソートキー.昇順／降順がnull」の例外をスローする。
        if (this.ascDesc == null)
        {
            log.error("「ソートキー.昇順／降順がnull」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }

        // ３）this.昇順／降順のチェック
        //     this.昇順／降順が下記の項目以外の場合、
        // 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。
        // 　@　@　@・”A：昇順”
        // 　@　@　@・”D：降順”
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) &&
            !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.error("「ソートキー.昇順／降順が未定義の値」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
        }

        // ４）this.キー項目のチェック
        //     this.キー項目が下記の項目以外の場合、
        // 　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。
        // 　@　@　@・”calcDate：計算年月日”
        if (!WEB3PLSProfitLossSpecsSortKeyItemDef.CALC_DATE.equals(this.keyItem))
        {
            log.error("「ソートキー.キー項目が未定義の値」の例外をスローする");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーのキー項目の値が存在しないコード値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
