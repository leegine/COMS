head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益計算リクエスト(WEB3TrialCalcSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * （計算サービスソートキー）<BR>
 * <BR>
 * 計算サービスソートキー。一覧表示時のソート順制御クラス。<BR>
 * <BR>
 * WEB3TrialCalcSortKeyUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcSortKeyUnit extends Message
{
    /**
     * log WEB3LogUtility
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TrialCalcSortKeyUnit.class);

    /**
     * （キー項目）<BR>
     * <BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名をキー項目とす
     * る。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * keyItem<BR>
     * The symbol name of the response class inside to the request using this class is
     * assumed to be a key item.  <BR>
     * Refer to the description defining the request using this class for the object
     * items. <BR>
     */
    public String keyItem;

    /**
     * （昇順／降順）<BR>
     * <BR>
     * 昇順（asc）／降順（desc）指定。<BR>
     * （A：昇順　@D：降順）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Ascending order (asc)/descending order (desc) specification . <BR>
     * (A: Ascending order D: Descending order)<BR>
     */
    public String ascDesc;

    /**
     * theWEB3TrialCalcPortfolioDisplayRequest WEB3TrialCalcPortfolioDisplayRequest
     */
    public WEB3TrialCalcPortfolioDisplayRequest theWEB3TrialCalcPortfolioDisplayRequest;

    /**
     * @@roseuid 41C81B0702EE
     */
    public WEB3TrialCalcSortKeyUnit()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * <BR>
     * ２）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をthrowする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * <BR>
     * ３）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をthrowする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked (However, it is assumed only when
     * the simple check concluded in this class). <BR>
     * <BR>
     * 1) If "this.keyItem = null", throw the following exception.<BR>
     *   [WEB3TrialCalcSortKeyUnit.keyItem is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * <BR>
     * 2) If "this.ascDesc = null", throw the following exception<BR>
     *   [WEB3TrialCalcSortKeyUnit.ascDesc is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * <BR>
     * 3) If "this.ascDesc" is not 'A'(Ascend) or 'D'(Descend) throw the following
     * exception<BR>
     *   [WEB3TrialCalcSortKeyUnit.ascDesc is an undefined value]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
     * @@roseuid 4192F8E90050
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
