head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLProductRegistControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録制御サービスImpl(WEB3AdminAioSLProductRegistControlServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 趙林鵬(中訊) 新規作成 モデルNo.760,ＤＢ更新仕様No.151
Revision History : 2007/09/29 趙林鵬(中訊)  ＤＢ更新仕様No.154
Revision History : 2007/10/19 孟亞南(中訊)  ＤＢ更新仕様No.155
Revision History : 2007/10/23 金傑(中訊)　@仕様変更モデルNo.811
Revision History : 2007/10/26 金傑(中訊)　@仕様変更モデルNo.816,ＤＢ更新仕様No.158
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.data.SecurityProductDao;
import webbroker3.aio.data.SecurityProductPK;
import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (担保銘柄登録制御サービスImpl)<BR>
 * 担保銘柄登録制御サービス実装クラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminAioSLProductRegistControlServiceImpl implements WEB3AdminAioSLProductRegistControlService
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistControlServiceImpl.class);

    /**
     * @@roseuid 46EA39FB018A
     */
    public WEB3AdminAioSLProductRegistControlServiceImpl()
    {

    }

    /**
     * (validate担保銘柄同一期間)<BR>
     * 同一銘柄で、かつ同一期間内の銘柄が存在するかチェックを行う。<BR>
     * 存在する場合、例外をスローする。<BR>
     * <BR>
     * １）　@引数.適用期間to がnullの場合、9999/12/31をセット<BR>
     * <BR>
     * ２）　@引数:担保銘柄情報の要素分、Loop処理<BR>
     * <BR>
     * 　@２－１）　@担保銘柄行の取得<BR>
     * <BR>
     * 　@２－２）　@担保銘柄行.get適用期間to = null の場合、<BR>
     * <BR>
     * 　@　@２－２－１）　@担保銘柄行.get適用期間from <=　@<BR>
     * 　@　@　@　@　@　@　@　@引数.適用期間from <= 9999/12/31<BR>
     * 　@　@　@　@　@　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02927<BR>
     * <BR>
     * 　@２－３）　@担保銘柄行.get適用期間to != null の場合、<BR>
     * <BR>
     * 　@　@２－３－１）　@　@担保銘柄行.get適用期間from <= <BR>
     * 　@　@　@　@　@　@　@　@引数.適用期間from <= 担保銘柄行.get適用期間to<BR>
     * 　@　@　@　@　@　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02927<BR>
     * <BR>
     * <BR>
     * 　@２－４）　@引数.適用期間from <= 担保銘柄行.get適用期間from <= <BR>
     * 　@　@　@　@　@　@ 引数.適用期間to<BR>
     * 　@　@　@　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02927<BR>
     * <BR>
     * 　@＊ 全ての例外において、「同一銘柄、同一期間の担保銘柄登録が存在します。<BR>
     * 　@エラーをスロー<BR>
     * <BR>
     * @@param l_lisSecurityProductInfos - (担保銘柄情報)<BR>
     * 担保銘柄登録情報<BR>
     * @@param l_datTargetPeriodFrom - (適用期間from)<BR>
     * 適用期間from<BR>
     * @@param l_datTargetPeriodTo - (適用期間to)<BR>
     * 適用期間to<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D6819601A5
     */
    public void validateSecurityProductSameTerm(
        List l_lisSecurityProductInfos,
        Date l_datTargetPeriodFrom,
        Date l_datTargetPeriodTo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateSecurityProductSameTerm(List, Date, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisSecurityProductInfos == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //引数.適用期間to がnullの場合、9999/12/31をセット
        Date l_datTargetPeriod =
            WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD);
        if (l_datTargetPeriodTo == null)
        {
            l_datTargetPeriodTo = l_datTargetPeriod;
        }

        int l_intSecurityProductInfosSize = l_lisSecurityProductInfos.size();
        //２）　@引数:担保銘柄情報の要素分、Loop処理
        for (int i = 0; i < l_intSecurityProductInfosSize; i++)
        {
            //２－１）　@担保銘柄行の取得
            SecurityProductRow l_securityProductRow =
                (SecurityProductRow)l_lisSecurityProductInfos.get(i);

            Date l_datApplyTermFrom = l_securityProductRow.getApplyTermFrom();
            Date l_datApplyTermTo = l_securityProductRow.getApplyTermTo();
            //２－２）　@担保銘柄行.get適用期間to = null の場合、
            if (l_datApplyTermTo == null)
            {
                //２－２－１）　@担保銘柄行.get適用期間from <= 引数.適用期間from <= 9999/12/31
                //の場合、例外をスローする。
                if (WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datApplyTermFrom) >= 0
                    && WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datTargetPeriod) <= 0)
                {
                    log.debug("同一銘柄、同一期間の担保銘柄登録が存在します。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02927,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同一銘柄、同一期間の担保銘柄登録が存在します。");
                }
            }
            //２－３）　@担保銘柄行.get適用期間to != null の場合、
            else
            {
                //２－３－１）　@　@担保銘柄行.get適用期間from <= 引数.適用期間from <= 担保銘柄行.get適用期間to
                //の場合、例外をスローする。
                if (WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datApplyTermFrom) >= 0
                    && WEB3DateUtility.compareToDay(l_datTargetPeriodFrom, l_datApplyTermTo) <= 0)
                {
                    log.debug("同一銘柄、同一期間の担保銘柄登録が存在します。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02927,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同一銘柄、同一期間の担保銘柄登録が存在します。");
                }
            }

            //２－４）　@引数.適用期間from <= 担保銘柄行.get適用期間from <= 引数.適用期間to
            //の場合、例外をスローする。
            if (WEB3DateUtility.compareToDay(l_datApplyTermFrom, l_datTargetPeriodFrom) >= 0
                && WEB3DateUtility.compareToDay(l_datApplyTermFrom, l_datTargetPeriodTo) <= 0)
            {
                log.debug("同一銘柄、同一期間の担保銘柄登録が存在します。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02927,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "同一銘柄、同一期間の担保銘柄登録が存在します。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert担保銘柄情報)<BR>
     * 担保銘柄テーブルに担保銘柄情報をinsrtする。<BR>
     * <BR>
     * ＊ 詳細はDB更新仕様「担保銘柄登録_担保銘柄テーブル.xls」<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_stockLoanProductInfo - (銘柄登録情報)<BR>
     * 担保銘柄登録情報オブジェクト<BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D7BD790062
     */
    public void insertSecurityProductInfo(
        String l_strInstitutionCode,
        WEB3SLProductInfoUnit l_stockLoanProductInfo,
        String l_strAdministratorCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertSecurityProductInfo(String, WEB3SLProductInfoUnit, String)";
        log.entering(STR_METHOD_NAME);

        if (l_stockLoanProductInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        SecurityProductParams l_securityProductParams = new SecurityProductParams();

        //銘柄ＩＤ
        //銘柄登録情報.銘柄ID
        l_securityProductParams.setProductId(l_stockLoanProductInfo.productId);

        //証券会社コード
        //引数:証券会社コード
        l_securityProductParams.setInstitutionCode(l_strInstitutionCode);

        //銘柄コード
        //銘柄登録情報.銘柄コード
        l_securityProductParams.setProductCode(l_stockLoanProductInfo.productCode);

        //銘柄タイプ
        //銘柄登録情報.銘柄タイプ
        ProductTypeEnum l_productTypeEnum =
            (ProductTypeEnum)EnumeratedManager.getInstance().valueFromInt(
                ProductTypeEnum.class,
                Integer.parseInt(l_stockLoanProductInfo.productType));

        l_securityProductParams.setProductType(l_productTypeEnum);

        //評価掛目
        //銘柄登録情報.掛目 (nullの場合は0をセット)
        if (l_stockLoanProductInfo.weight != null)
        {
            l_securityProductParams.setEstimationRatio(Double.parseDouble(l_stockLoanProductInfo.weight));
        }
        else
        {
            l_securityProductParams.setEstimationRatio(0);
        }

        //適格区分
        //銘柄登録情報.適格区分
        l_securityProductParams.setFitFlg(l_stockLoanProductInfo.qualifiedDiv);

        //適用期間from
        //銘柄登録情報.適用期間from
        l_securityProductParams.setApplyTermFrom(l_stockLoanProductInfo.targetPeriodFrom);

        //適用期間to
        //銘柄登録情報.適用期間to
        //nullの場合は9999/12/31をセット
        if (l_stockLoanProductInfo.targetPeriodTo == null)
        {
            l_securityProductParams.setApplyTermTo(
                WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD));
        }
        else
        {
            l_securityProductParams.setApplyTermTo(l_stockLoanProductInfo.targetPeriodTo);
        }

        //理由
        //銘柄登録情報.理由
        l_securityProductParams.setReason(l_stockLoanProductInfo.reason);

        //更新者コード
        //引数:管理者コード
        l_securityProductParams.setLastUpdater(l_strAdministratorCode);

        //作成日付
        //TradingSystem.getSystemTimestamp()
        l_securityProductParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        //更新日付
        //TradingSystem.getSystemTimestamp()
        l_securityProductParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_securityProductParams);

        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get担保銘柄行)<BR>
     * 主キーから担保銘柄行を取得する。<BR>
     * <BR>
     * <BR>
     * １）　@担保銘柄テーブルからレコードを取得する。<BR>
     * 　@[検索条件]<BR>
     * 　@銘柄ID = 引数:銘柄ID<BR>
     * 　@適用期間from = 引数:適用期間from<BR>
     * <BR>
     * 2) 取得した担保銘柄行を返却する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_datTargetPeriodFrom - (適用期間from)<BR>
     * 適用期間from<BR>
     * @@throws WEB3BaseException
     * @@return SecurityProductRow
     * @@roseuid 46DCF0420182
     */
    public SecurityProductRow getSecurityProductRow(
        long l_lngProductId,
        Date l_datTargetPeriodFrom)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSecurityProductRow(long, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_datTargetPeriodFrom == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //）　@担保銘柄テーブルからレコードを取得する。
        //[検索条件]
        //銘柄ID = 引数:銘柄ID
        //適用期間from = 引数:適用期間from
        SecurityProductRow l_securityProductRow = null;

        try
        {
            l_securityProductRow =
                SecurityProductDao.findRowByPk(
                    l_lngProductId,
                    new Timestamp(l_datTargetPeriodFrom.getTime()));
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //取得した担保銘柄行を返却する
        log.exiting(STR_METHOD_NAME);
        return l_securityProductRow;
    }

    /**
     * (compare変更情報)<BR>
     * 変更前と変更後の状態を比較する。<BR>
     * <BR>
     * １）以下の項目全て、this.is項目変更（）をコールし、全て差異がない場合、<BR>
     * 　@１を返却。<BR>
     * 　@1つでも差異が存在する場合は0を返却する。<BR>
     * <BR>
     * [比較対照項目]<BR>
     * ・適格区分<BR>
     * ・掛目<BR>
     * ・適用期間from<BR>
     * ・適用期間to<BR>
     * ・理由<BR>
     * @@param l_changeBeforeSecurityProductInfo - (変更前担保銘柄情報)<BR>
     * 変更前担保銘柄情報<BR>
     * @@param l_changeAfterSecurityProductInfo - (変更後担保銘柄情報)<BR>
     * 変更後担保銘柄情報<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 46DD19C802A1
     */
    public int compareChangeInfo(
        SecurityProductRow l_changeBeforeSecurityProductInfo,
        WEB3SLProductInfoUnit l_changeAfterSecurityProductInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "compareChangeInfo(SecurityProductRow, WEB3SLProductInfoUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_changeBeforeSecurityProductInfo == null || l_changeAfterSecurityProductInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //以下の項目全て、this.is項目変更（）をコールし、
        //適格区分
        boolean l_blnIsFitFlg = this.isItemChange(
            l_changeBeforeSecurityProductInfo.getFitFlg(),
            l_changeAfterSecurityProductInfo.qualifiedDiv);

        //掛目
        boolean l_blnIsWeight = this.isItemChange(
            WEB3StringTypeUtility.formatNumber(l_changeBeforeSecurityProductInfo.getEstimationRatio()),
            l_changeAfterSecurityProductInfo.weight);

        //適用期間from
        boolean l_blnIsApplyTermFrom = this.isItemChange(
            WEB3DateUtility.formatDate(l_changeBeforeSecurityProductInfo.getApplyTermFrom(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_changeAfterSecurityProductInfo.targetPeriodFrom,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //適用期間to
        boolean l_blnIsApplyTermTo = this.isItemChange(
            WEB3DateUtility.formatDate(l_changeBeforeSecurityProductInfo.getApplyTermTo(),
                WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_changeAfterSecurityProductInfo.targetPeriodTo,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //理由
        boolean l_blnIsReason = this.isItemChange(
            l_changeBeforeSecurityProductInfo.getReason(),
            l_changeAfterSecurityProductInfo.reason);

        //全て差異がない場合、１を返却。
        if (!l_blnIsFitFlg
            && !l_blnIsWeight
            && !l_blnIsApplyTermFrom
            && !l_blnIsApplyTermTo
            && !l_blnIsReason)
        {
            log.exiting(STR_METHOD_NAME);
            return 1;
        }

        //1つでも差異が存在する場合は0を返却する。
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (update担保銘柄情報)<BR>
     * 1) 検索キー情報に紐付くレコードを削除する。<BR>
     * <BR>
     * 2) 引数の担保銘柄Rowをinsertする。<BR>
     * <BR>
     * ＊詳細はDB更新仕様<BR>
     * 　@「担保銘柄変更_担保銘柄テーブル.xls」を参照<BR>
     * @@param l_searchKeyConditions - (検索キー情報)<BR>
     * 担保銘柄検索キー情報<BR>
     * @@param l_securityProductRow - (担保銘柄Row)<BR>
     * 担保銘柄Row<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DD4BCF03C8
     */
    public void updateSecurityProductInfo(
        WEB3SLProductSearchConditions l_searchKeyConditions,
        SecurityProductRow l_securityProductRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateSecurityProductInfo(WEB3SLProductSearchConditions, SecurityProductRow)";
        log.entering(STR_METHOD_NAME);

        if (l_searchKeyConditions == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            SecurityProductPK l_securityProductPk = new SecurityProductPK(
                l_searchKeyConditions.productId,
                new Timestamp(l_searchKeyConditions.targetPeriodFrom.getTime()));

            // 1) 検索キー情報に紐付くレコードを削除する。
            l_queryProcessor.doDeleteQuery(l_securityProductPk);

            // 2) 引数の担保銘柄Rowをinsertする。
            l_queryProcessor.doInsertQuery(l_securityProductRow);

        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete担保銘柄情報)<BR>
     * 主キーを対象に担保銘柄テーブルのレコードを削除する。<BR>
     * @@param l_deleteObjectKey - (削除対象キー)<BR>
     * 削除レコード対象キー<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DE41620259
     */
    public void deleteSecurityProductInfo(WEB3SLProductSearchConditions l_deleteObjectKey)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "deleteSecurityProductInfo(WEB3SLProductSearchConditions)";
        log.entering(STR_METHOD_NAME);

        if (l_deleteObjectKey == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        try
        {
            SecurityProductPK l_securityProductPK = new SecurityProductPK(
                l_deleteObjectKey.productId,
                new Timestamp(l_deleteObjectKey.targetPeriodFrom.getTime()));

            //主キーを対象に担保銘柄テーブルのレコードを削除する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_securityProductPK);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get担保銘柄情報)<BR>
     * 銘柄IDをキーに担保銘柄テーブルのレコードを取得し、返却する。<BR>
     * <BR>
     * １）以下の条件で、担保銘柄テーブルのレコードを検索<BR>
     * [検索条件]<BR>
     * 銘柄ID： 引数:銘柄ID<BR>
     * <BR>
     * ２）取得した情報を返却する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@throws WEB3BaseException
     * @@return List
     * @@roseuid 46DE4FFE02BE
     */
    public List getSecurityProductInfo(long l_lngProductId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSecurityProductInfo(long)";
        log.entering(STR_METHOD_NAME);

        List l_lisSecurityProducts = new ArrayList();

        String l_strWhere = "product_id = ?";
        Object[] l_whereValues = {new Long(l_lngProductId)};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisSecurityProducts = l_queryProcessor.doFindAllQuery(
                SecurityProductRow.TYPE,
                l_strWhere,
                l_whereValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //取得した情報を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisSecurityProducts;
    }

    /**
     * (is項目変更)<BR>
     * 項目が変更されているかを判別する。<BR>
     * <BR>
     * １）変更前項目と変更後項目を比較し、値が変更されている場合trueを<BR>
     * 　@変更無の場合はfalseを返却する。<BR>
     * @@param l_strChangeBeforeItem - (変更前項目)<BR>
     * 変更前項目(BR)
     * @@param l_strChangeAfterItem - (変更後項目)<BR>
     * 変更後項目<BR>
     * @@return boolean
     * @@roseuid 46DE65DA03E5
     */
    public boolean isItemChange(String l_strChangeBeforeItem, String l_strChangeAfterItem)
    {
        final String STR_METHOD_NAME = "isItemChange(String, String)";
        log.entering(STR_METHOD_NAME);

        if (!WEB3Toolkit.isEquals(l_strChangeBeforeItem, l_strChangeAfterItem))
        {
            //変更前項目と変更後項目を比較し、値が変更されている場合trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //変更無の場合はfalseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
