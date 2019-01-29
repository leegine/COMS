head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報通知一件サービスImpl(WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 張少傑(中訊) 新規作成 モデルNo.219 ＤＢ更新仕様No.022,No.023,No.024
Revision History : 2009/01/21 孟亞南(中訊) 仕様変更モデルNo.232
Revision History : 2009/02/12 孟亞南(中訊) 仕様変更モデルNo.236
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AttentionInfoCompTakingDivDef;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityProductUpdateFlagDef;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.data.InstitutionPreferencesDao;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (注意情報通知一件サービスImpl)<BR>
 * 注意情報通知一件サービス実装クラス<BR>
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl
    implements WEB3AdminEquityAttentionInfoNotifyUnitService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 49588AEE03C8
     */
    public WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify制限値幅情報)<BR>
     * 注意情報（制限値幅情報）処理を行なう。<BR>
     * 銘柄の更新処理を行なっている場合は「1：銘柄更新済み」を <BR>
     * 銘柄の更新処理を行なっていない場合は「2：銘柄未更新」を返却する。 <BR>
     * <BR>
     * １）　@キューに必要な情報が格納されていない場合、「2：銘柄未更新」をreturnする。 <BR>
     * 　@　@　@[条件] <BR>
     * 　@　@　@　@注意情報通知キューテーブル.基準値＝null <BR>
     * <BR>
     * ２）　@証券会社プリファ@レンステーブルを取得する。 <BR>
     * 　@　@　@[検索条件] <BR>
     * 　@　@　@　@証券会社ＩＤ：　@証券会社.証券会社ＩＤ <BR>
     * 　@　@　@　@プリファ@レンス名：　@"attention.info.comp.taking.div" <BR>
     * 　@　@　@　@プリファ@レンス名の連番：　@"1"（固定） <BR>
     * <BR>
     * ３）　@２）で証券会社プリファ@レンステーブルが取得出来なかった場合又は、 <BR>
     * 　@　@　@証券会社プリファ@レンステーブル.プリファ@レンスの値が"取込まない"の場合、 <BR>
     * 　@　@　@「2：銘柄未更新」をreturnする。 <BR>
     * <BR>
     * ４）　@銘柄、株式取引銘柄、株式取引銘柄UPDQオブジェクトのクローンを作成する。 <BR>
     * 　@　@　@以降は、複製したオブジェクトに対し更新処理を行なう。 <BR>
     * <BR>
     * ５）　@銘柄.優先市場IDがnullの場合　@又は、 <BR>
     * 　@　@　@銘柄.優先市場IDと注意情報通知キューテーブル.市場コード（SONAR）に <BR>
     * 　@　@　@該当する市場IDが等しい場合、銘柄を更新する。 <BR>
     * 　@　@　@※更新内容は、DB更新仕様書『注意情報通知_銘柄テーブル.xls』参照。 <BR>
     * <BR>
     * ６）　@株式取引銘柄を更新する。 <BR>
     * 　@　@　@※更新内容は、DB更新仕様書『注意情報通知_株式取引銘柄マスタテーブル.xls』参照。 <BR>
     * <BR>
     * ７）　@株式取引銘柄UPDQを取得している場合、株式取引銘柄UPDQを更新する。 <BR>
     * 　@　@　@※更新内容は、DB更新仕様書『注意情報通知_株式取引銘柄マスタupdqテーブル.xls』参照。 <BR>
     * <BR>
     * ８）　@「1：銘柄更新済み」をreturnする。<BR>
     * @@param l_hostAttentionInfoNotifyParams - (注意情報通知キューテーブル)<BR>
     * 注意情報通知キューテーブル<BR>
     * @@param l_eqtypeTradedProductRow - (株式取引銘柄)<BR>
     * 株式取引銘柄オブジェクト<BR>
     * @@param l_eqtypeTradedProductUpdqRow - (株式取引銘柄updq)<BR>
     * 株式取引銘柄updq<BR>
     * @@param l_productRow - (銘柄)<BR>
     * 銘柄<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4938744503A0
     */
    public String notifyLimitRangeInfo(
        HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams,
        EqtypeTradedProductRow l_eqtypeTradedProductRow,
        EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow,
        ProductRow l_productRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyLimitRangeInfo(HostAttentionInfoNotifyParams, EqtypeTradedProductRow,"
            + " EqtypeTradedProductUpdqRow, ProductRow)";
        log.entering(STR_METHOD_NAME);

        //１）　@キューに必要な情報が格納されていない場合、「2：銘柄未更新」をreturnする。
        //[条件]
        //     注意情報通知キューテーブル.基準値＝null
        if (l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Institution l_institution = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_hostAttentionInfoNotifyParams.getInstitutionCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@証券会社プリファ@レンステーブルを取得する。
        //　@　@　@[検索条件]
        //　@　@　@　@証券会社ＩＤ：　@証券会社.証券会社ＩＤ
        //　@　@　@　@プリファ@レンス名：　@"attention.info.comp.taking.div"
        //　@　@　@　@プリファ@レンス名の連番：　@"1"（固定）
        InstitutionPreferencesRow l_institutionPreferencesRow = null;
        try
        {
            l_institutionPreferencesRow =
                InstitutionPreferencesDao.findRowByInstitutionIdNameNameSerialNo(
                    l_institution.getInstitutionId(),
                    WEB3InstitutionPreferencesNameDef.ATTENTION_INFO_COMP_TAKING_DIV,
                    1);
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

        //３）　@２）で証券会社プリファ@レンステーブルが取得出来なかった場合又は、
        //   　@　@　@証券会社プリファ@レンステーブル.プリファ@レンスの値が"取込まない"の場合、
        //   　@　@　@「2：銘柄未更新」をreturnする。
        if (l_institutionPreferencesRow == null
            || WEB3AttentionInfoCompTakingDivDef.DEFAULT.equals(l_institutionPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE;
        }

        //４）　@銘柄、株式取引銘柄、株式取引銘柄UPDQオブジェクトのクローンを作成する。
        //以降は、複製したオブジェクトに対し更新処理を行なう。
        ProductParams l_productParams = new ProductParams(l_productRow);
        EqtypeTradedProductParams l_eqtypeTradedProductParams =
            new EqtypeTradedProductParams(l_eqtypeTradedProductRow);

        try
        {
            //５）　@銘柄.優先市場IDがnullの場合　@又は、
            //銘柄.優先市場IDと注意情報通知キューテーブル.市場コード（SONAR）に
            //該当する市場IDが等しい場合、銘柄を更新する。
            //※更新内容は、DB更新仕様書『注意情報通知_銘柄テーブル.xls』参照。
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            if (l_productRow.getPrimaryMarketIdIsNull())
            {
                //評価単価 = 注意情報通知キュー.基準値
                l_productParams.setEstimationPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                //更新者コード = 注意情報通知キュー.データコード
                l_productParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
                //更新日付 = 現在日時（GtlUtils.getSystemTimestamp()）
                l_productParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                l_processor.doUpdateQuery(l_productParams);
            }
            else
            {
                WEB3GentradeFinObjectManager l_finObjectManager =
                    (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                String l_strSonarMarketCode = l_hostAttentionInfoNotifyParams.getSonarMarketCode();

                Market l_market =
                    l_finObjectManager.getMarketBySONAR(l_institution.getInstitutionCode(), l_strSonarMarketCode);

                if (l_productRow.getPrimaryMarketId() == l_market.getMarketId())
                {
                    //評価単価 = 注意情報通知キュー.基準値
                    l_productParams.setEstimationPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                    //更新者コード = 注意情報通知キュー.データコード
                    l_productParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
                    //更新日付 = 現在日時（GtlUtils.getSystemTimestamp()）
                    l_productParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_processor.doUpdateQuery(l_productParams);
                }
            }

            //６）　@株式取引銘柄を更新する。
            //　@　@　@※更新内容は、DB更新仕様書『注意情報通知_株式取引銘柄マスタテーブル.xls』参照。
            //基準値（終値）  = 注意情報通知キュー.基準値
            l_eqtypeTradedProductParams.setLastClosingPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
            //値幅チェック区分 = "1:値幅チェックあり"
            l_eqtypeTradedProductParams.setPriceRangeType(WEB3PriceRangeTypeDef.CHECK);
            //強制値幅（上限値） =
            //注意情報通知キュー.制限値幅上限≠nullかつ
            //注意情報通知キュー.基準値≠nullの場合
            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
            //　@（ただし、計算結果が0以下の場合はnullをセット）
            //上記以外の場合、null
            if (!l_hostAttentionInfoNotifyParams.getHighPriceRangeIsNull()
                && !l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
            {
                //注意情報通知キュー.制限値幅上限
                double l_dblHighPriceRange = l_hostAttentionInfoNotifyParams.getHighPriceRange();
                BigDecimal l_bdHighPriceRange = new BigDecimal("" + l_dblHighPriceRange);

                //注意情報通知キュー.基準値
                double l_dblBasePrice = l_hostAttentionInfoNotifyParams.getBasePrice();
                BigDecimal l_bdBasePrice = new BigDecimal("" + l_dblBasePrice);

                double l_dblHighCompulsivePriceRange = l_bdHighPriceRange.subtract(l_bdBasePrice).doubleValue();
                if (GtlUtils.Double.isZero(l_dblHighCompulsivePriceRange) || l_dblHighCompulsivePriceRange < 0)
                {
                    l_eqtypeTradedProductParams.setHighCompulsivePriceRange(null);
                }
                else
                {
                    l_eqtypeTradedProductParams.setHighCompulsivePriceRange(l_dblHighCompulsivePriceRange);
                }
            }
            else
            {
                l_eqtypeTradedProductParams.setHighCompulsivePriceRange(null);
            }

            //強制値幅（下限値）
            //注意情報通知キュー.制限値幅下限≠nullかつ
            //注意情報通知キュー.基準値≠nullの場合
            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
            //（ただし、計算結果が0以下の場合はnullをセット）
            //上記以外の場合、null
            if (!l_hostAttentionInfoNotifyParams.getLowPriceRangeIsNull()
                && !l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
            {
                //注意情報通知キュー.制限値幅下限
                double l_dblLowPriceRange = l_hostAttentionInfoNotifyParams.getLowPriceRange();
                BigDecimal l_bdLowPriceRange = new BigDecimal("" + l_dblLowPriceRange);

                //注意情報通知キュー.基準値
                double l_dblBasePrice = l_hostAttentionInfoNotifyParams.getBasePrice();
                BigDecimal l_bdBasePrice = new BigDecimal("" + l_dblBasePrice);

                double l_dblLowCompulsivePriceRange = l_bdBasePrice.subtract(l_bdLowPriceRange).doubleValue();
                if (GtlUtils.Double.isZero(l_dblLowCompulsivePriceRange) || l_dblLowCompulsivePriceRange < 0)
                {
                    l_eqtypeTradedProductParams.setLowCompulsivePriceRange(null);
                }
                else
                {
                    l_eqtypeTradedProductParams.setLowCompulsivePriceRange(l_dblLowCompulsivePriceRange);
                }
            }
            else
            {
                l_eqtypeTradedProductParams.setLowCompulsivePriceRange(null);
            }

            //値幅区分を更新
            if (!l_eqtypeTradedProductParams.getHighCompulsivePriceRangeIsNull()
                && !l_eqtypeTradedProductParams.getLowCompulsivePriceRangeIsNull())
            {
                l_eqtypeTradedProductParams.setPriceRangeUnitType(WEB3PriceRangeIdDef.YEN);
            }
            //更新者コード = 注意情報通知キュー.データコード
            l_eqtypeTradedProductParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
            //更新日付 = 現在日時（GtlUtils.getSystemTimestamp()）
            l_eqtypeTradedProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            //基準値 = 注意情報通知キュー.基準値
            l_eqtypeTradedProductParams.setBasePrice(l_hostAttentionInfoNotifyParams.getBasePrice());
            l_processor.doUpdateQuery(l_eqtypeTradedProductParams);

            //７）　@株式取引銘柄UPDQを取得している場合、株式取引銘柄UPDQを更新する。
            //　@　@　@※更新内容は、DB更新仕様書『注意情報通知_株式取引銘柄マスタupdqテーブル.xls』参照。
            if (l_eqtypeTradedProductUpdqRow != null)
            {
                EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                    new EqtypeTradedProductUpdqParams(l_eqtypeTradedProductUpdqRow);
                //基準値（終値） = 注意情報通知キュー.基準値
                l_eqtypeTradedProductUpdqParams.setLastClosingPrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                //更新者コード = 注意情報通知キュー.データコード
                l_eqtypeTradedProductUpdqParams.setLastUpdater(l_hostAttentionInfoNotifyParams.getRequestCode());
                //更新日付 = 現在日時（GtlUtils.getSystemTimestamp()）
                l_eqtypeTradedProductUpdqParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //基準値 = 注意情報通知キュー.基準値
                l_eqtypeTradedProductUpdqParams.setBasePrice(l_hostAttentionInfoNotifyParams.getBasePrice());
                l_processor.doUpdateQuery(l_eqtypeTradedProductUpdqParams);
            }
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

        //８）　@「1：銘柄更新済み」をreturnする。
        log.exiting(STR_METHOD_NAME);
        return WEB3AdminEquityProductUpdateFlagDef.PRODUCT_UPDATE;
    }
}
@
