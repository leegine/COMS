head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondConfigUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 銘柄条件設定情報(WEB3AdminPMProductCondConfigUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;

/**
 * (銘柄条件設定情報)<BR>
 * <BR>
 * 銘柄条件設定情報クラス<BR>
 * <BR>
 * WEB3AdminPMProductCondConfigUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfigUnit extends WEB3AdminPMProductCondConfigCommon
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondConfigUnit.class);

    /**
     * （市場コード）<BR>
     * <BR>
     * 市場コード<BR>
     * <BR>
     * 0：　@その他(市場共通)<BR>
     * 1：　@東京<BR>
     * 2：　@大阪<BR>
     * 3：　@名古屋<BR>
     * 6：　@福岡<BR>
     * 8：　@札幌<BR>
     * 9：　@NNM<BR>
     * 10：　@JASDAQ<BR>
     * <BR>
     * --------<English>-------------------<BR>
     * <BR>
     * MarketCode<BR>
     * <BR>
     * 0: Def.DEFAULT(common to markets)<BR>
     * 1: Def.TOKYO<BR>
     * 2: Def.OSAKA<BR>
     * 3: Def.NAGOYA<BR>
     * 6: Def.FUKUOKA<BR>
     * 8: Def.SAPPORO<BR>
     * 9: Def.NNM<BR>
     * 10: Def.JASDAQ<BR>
     * <BR>
     */
    public String marketCode = null;

    /**
     * （登録値(当日)）<BR>
     * <BR>
     * 登録値(当日)<BR>
     * <BR>
     * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * 特定の小、大項目区分については、<BR>
     * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * bizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ※Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String bizDateRegistData = null;

    /**
     * （登録値(翌日)）<BR>
     * <BR>
     * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * 特定の小、大項目区分については、<BR>
     * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * nextBizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ※Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String nextBizDateRegistData = null;

    /**
     * （登録値(予定)）<BR>
     * <BR>
     * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * 特定の小、大項目区分については、<BR>
     * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * scheduleRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ※Set the latest DB data in AP layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String scheduleRegistData = null;

    /**
     * （適用期間From）<BR>
     * <BR>
     * 予定の適用期間From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※Set the latest DB data in AP layer<BR>
     * <BR>
     */

    public String applyStartDate = null;

    /**
     * （適用期間To）
     * <BR>
     * 予定の適用期間To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※Set the latest DB data in AP layer<BR>
     * <BR>
     */
    public String applyEndDate = null;

    /**
     * （変更後登録値(当日)）<BR>
     * <BR>
     * 変更後登録値(当日)<BR>
     * <BR>
     * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * 特定の小、大項目区分については、<BR>
     * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftBizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ※Set input values in PR layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String aftBizDateRegistData = null;

    /**
     * （変更後登録値(翌日)）<BR>
     * <BR>
     * 変更後登録値(翌日)<BR>
     * <BR>
     * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * 特定の小、大項目区分については、<BR>
     * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftNextBizDateRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ※Set input values in PR layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String aftNextBizDateRegistData = null;

    /**
     * （変更後登録値(予定)）<BR>
     * <BR>
     * 変更後登録値(予定)<BR>
     * <BR>
     * 特定の小、大項目区分以外は、文字列がセットされる。<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * 特定の小、大項目区分については、<BR>
     * 「【補足資料】株式銘柄条件設定登録値コード定義」<BR>
     * 参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftScheduleRegistData<BR>
     * <BR>
     * String is set  excluding specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     * ※Set input values in PR layer<BR>
     * <BR>
     * Refer to "[Supplement] equity product condition regist data code def list" about
     * specific smallItemDiv and largeItemDiv<BR>
     * <BR>
     */
    public String aftScheduleRegistData = null;

    /**
     * （変更後適用期間From）<BR>
     * <BR>
     * 変更後適用期間From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftApplyStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※Set input values in PR layer<BR>
     * <BR>
     */
    public String aftApplyStartDate = null;

    /**
     * （変更後適用期間To）<BR>
     * <BR>
     * 変更後適用期間To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftApplyEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※Set input values in PR layer<BR>
     * <BR>
     */
    public String aftApplyEndDate = null;

	/**
	 * （更新者コード）<BR>
	 * <BR>
	 * 更新者コード<BR>
	 */
	public String lastUpdater = null;


    /**
     * (コンストラクタ)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
     * @@roseuid 4185DE65011B
     */
    public WEB3AdminPMProductCondConfigUnit()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）入力内容チェック<BR>
     * 　@１－１）this.大項目、小項目区分が以下に該当する場合、チェックを行う。<BR>
     * 　@　@[this.大項目区分 == "委託保証金率"の場合　@または<BR>
     * 　@　@　@this.小項目区分 == ("売買単位" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"強制限度単位" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"代用掛目" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"代用評価単価" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"預り証券評価掛目" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"基準値(終値)" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"基準値" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"強制値幅(下限)" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"強制値幅(上限)")　@の場合]<BR>
     * 　@　@　@this.変更後登録値(当日)、this.変更後登録値(翌日)、this.変更後登録値(予定)の内<BR>
     * 　@　@　@いづれかが以下の条件に該当する場合、<BR>
     * 　@　@　@「入力数値エラー」の例外をスローする。<BR>
     * 　@　@　@　@・変更後登録値 != null　@かつ　@変更後登録値 != 数値<BR>
     * 　@　@　@　@・変更後登録値 != null　@かつ　@変更後登録値 < 0<BR>
     * 　@　@　@　@・変更後登録値 != null　@かつ　@変更後登録値.length > 12<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01441<BR>
     * <BR>
     * 　@１－２）this.小項目区分 == "強制限度単位"の場合で<BR>
     * 　@　@　@変更後登録値 != null　@かつ　@変更後登録値.length > 7の場合、<BR>
     * 　@　@　@「入力数値エラー」とする。<BR>
     * <BR>
     * 　@１－３）this.小項目区分 == "銘柄名"の場合で<BR>
     * 　@　@　@変更後登録値 != null　@かつ　@変更後登録値.getbyte().length > 50の場合、<BR>
     * 　@　@　@「銘柄名入力エラー(桁数超過)」とする。<BR>
     * <BR>
     * ２）適用期間Fromチェック<BR>
     * 　@this.変更後適用期間From != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@２－１）this.変更後適用期間Fromを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー(適用期間From)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01442<BR>
     * <BR>
     * ３）適用期間Toチェック<BR>
     * 　@this.変更後適用期間To != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@３－１）this.変更後適用期間Toを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー(適用期間To)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01443<BR>
     * <BR>
     * 　@３－２）this.変更後適用期間From  == nullの場合、<BR>
     * 　@　@　@　@　@「日付未入力エラー(適用期間From)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01444<BR>
     * <BR>
     * 　@３－３）this.変更後適用期間To < 業務日付(*1)の場合、<BR>
     * 　@　@　@　@　@「入力日付エラー(適用期間Toが当日日付未満)」の<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01445<BR>
     * <BR>
     * ４）適用期間整合性チェック<BR>
     * 　@this.変更後適用期間From != null　@かつ　@this.変更後適用期間To != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@４－１）this.変更後適用期間From > this.変更後適用期間Toの場合、<BR>
     * 　@　@　@　@　@「適用期間From/To整合性エラー」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01446<BR>
     * <BR>
     * ５）予定データ整合性チェック<BR>
     * 　@５－１）this.変更後登録値(予定) == nullの場合、以下のチェックを行う。<BR>
     * 　@　@(this.変更後適用期間From != null　@または　@this.変更後適用期間To != null)の場合、<BR>
     * 　@　@「変更後の登録値(予定)が未入力」の例外をスローする。<BR>
     * <BR>
     * 　@５－２）５－１）以外の場合、以下のチェックを行う<BR>
     * 　@　@(this.変更後適用期間From == null　@かつ　@this.変更後適用期間To == null)の場合、<BR>
     * 　@　@「変更後の適用期間From/Toが未入力」の例外をスローする。<BR>
     * <BR>
     * (*1)業務日付<BR>
     * 　@TradingSystem.getBizDate()にて取得した業務日付<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 418737C50277
     */
    protected void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);
        TradingSystem l_tradingSystem = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        final String DATE_FORMAT = "yyyyMMdd";

        /*1-1 If either of
         * bizDateRegistData、nextBizDateRegistData、scheduleRegistData
         * meets with the following conditions throws exception
         */
        if ((WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE.equals(largeItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.LOT_SIZE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.BASE_PRICE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE.equals(smallItemDiv))
            || (WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE).equals(smallItemDiv))
        {
            if ((this.aftBizDateRegistData != null
                && (!WEB3StringTypeUtility.isNumber(this.aftBizDateRegistData)
                    || (Double.parseDouble(this.aftBizDateRegistData) < 0)
                    || this.aftBizDateRegistData.length() > 12))
                || (this.aftNextBizDateRegistData != null
                    && (!WEB3StringTypeUtility.isNumber(this.aftNextBizDateRegistData)
                    || Double.parseDouble(this.aftNextBizDateRegistData) < 0
                    || this.aftNextBizDateRegistData.length() > 12))
                || (this.aftScheduleRegistData != null
                    && (!WEB3StringTypeUtility.isNumber(this.aftScheduleRegistData)
                    || Double.parseDouble(this.aftScheduleRegistData) < 0
                    || this.aftScheduleRegistData.length() > 12)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01441,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // 強制限度単位の場合
        if (WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(this.smallItemDiv))
        {
            if (this.aftBizDateRegistData != null && this.aftBizDateRegistData.length() > 7
                || this.aftNextBizDateRegistData != null && this.aftNextBizDateRegistData.length() > 7
                || this.aftScheduleRegistData != null && this.aftScheduleRegistData.length() > 7)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01441,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // 銘柄名の場合
        if (WEB3AdminEquitySmallItemDivDef.STANDARD_NAME.equals(this.smallItemDiv))
        {
            if (this.aftBizDateRegistData != null && this.aftBizDateRegistData.getBytes().length > 50
                || this.aftScheduleRegistData != null && this.aftScheduleRegistData.getBytes().length > 50)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01990,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        /*  2-1 If applyStartDate is unable to change to
         * Date type throw exception
         */
        if (this.aftApplyStartDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.aftApplyStartDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01442,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 If applyEndDate is unable to change this.applyStartDate to Date type throw exception
        if (aftApplyEndDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.aftApplyEndDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01443,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 3-2 if applyStartDate is  null throw exception
            if (aftApplyStartDate == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //3-3  If this.applyEndDate < bizDate (*1)
            Date l_dtbizDate = null;
            l_tradingSystem = l_finApp.getTradingSystem();
            l_dtbizDate = l_tradingSystem.getBizDate();
            Date l_applyEndDate = WEB3DateUtility.getDate(aftApplyEndDate, "yyyyMMdd");
            int l_resultcompare = WEB3DateUtility.compare(l_dtbizDate, l_applyEndDate);
            if (l_resultcompare == 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01445,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if applyStartDate > applyEndDate throw exception
        if ((aftApplyStartDate != null) && (aftApplyEndDate != null))
        {
            Date l_applyStartDate = WEB3DateUtility.getDate(aftApplyStartDate, DATE_FORMAT);
            Date l_applyEndDate = WEB3DateUtility.getDate(aftApplyEndDate, DATE_FORMAT);

            int l_resultcompare = WEB3DateUtility.compare(l_applyStartDate, l_applyEndDate);
            if (l_resultcompare == 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // ５）予定データの整合性チェック
        if (this.aftScheduleRegistData == null)
        {
            if (this.aftApplyStartDate != null || this.aftApplyEndDate != null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01978,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        else
        {
            if (this.aftApplyStartDate == null && this.aftApplyEndDate == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01979,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
