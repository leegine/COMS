head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報通知サービスImpl(WEB3AdminEquityAttentionInfoNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 劉剣 (中訊) 新規作成 モデルNo.219 No.226 No.227 ＤＢ更新仕様No.022,No.023
Revision History : 2008/01/14 張少傑 (中訊) 仕様変更 ＤＢ更新仕様No.024
Revision History : 2008/01/14 劉剣 (中訊) 仕様変更 モデルNo.229
Revision History : 2009/01/21 孟亞南(中訊) モデルNo.230 No.232
Revision History : 2009/01/23 劉剣(中訊) モデル No.233
Revision History : 2009/02/11 劉剣(中訊) モデル No.236 ＤＢ更新仕様No.025,026,027
Revision History : 2009/02/17 劉剣(中訊) モデル No.237 ＤＢ更新仕様No.028
Revision History : 2009/02/18 劉剣(中訊) モデル No.238 No.239
Revision History : 2009/02/20 孟亞南(中訊) モデル No.240
Revision History : 2009/02/27 劉剣(中訊) モデル No.241 ＤＢ更新仕様No.029
Revision History : 2009/03/02 劉剣(中訊) モデル No.242
Revision History : 2009/05/05 李玉玲(中訊) モデル No.243 ＤＢ更新仕様No.032
Revision History : 2009/05/14 SRA長谷川 ＤＢ更新仕様No.033
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountCodeDef;
import webbroker3.common.define.WEB3AdminEquityStatusDef;
import webbroker3.common.define.WEB3AttentionInfoDivCodeDef;
import webbroker3.common.define.WEB3AttentionInfoTypeDef;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3PriceRangeIdDef;
import webbroker3.common.define.WEB3PriceRangeTypeDef;
import webbroker3.common.define.WEB3ProcessResultDivDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryDao;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryParams;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityDiscernmentIdDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityHashMapKeyDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityProductUpdateFlagDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyUnitService;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMailInfo;
import webbroker3.gentrade.data.ExtMailProcParams;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (注意情報通知サービスImpl)<BR>
 * 注意情報通知サービス実装クラス<BR>
 * <BR>
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyServiceImpl implements WEB3AdminEquityAttentionInfoNotifyService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyServiceImpl.class);

    /**
     * @@roseuid 49588AEE0270
     */
    public WEB3AdminEquityAttentionInfoNotifyServiceImpl()
    {

    }

    /**
     * 注意情報通知サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータオブジェクト<BR>
     * @@return WEB3BackResponse
     * @@roseuid 49377CBE0128
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (!(l_request instanceof WEB3AdminEquityAttentionInfoNotifyRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        WEB3AdminEquityAttentionInfoNotifyRequest l_attentionInfoNotifyRequest =
            (WEB3AdminEquityAttentionInfoNotifyRequest)l_request;
        WEB3BackResponse l_response = null;

        try
        {
            //getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //注意情報通知TransactionCallback()
            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_transactionCallback =
                new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();

            //doTransaction(トランザクション属性 : int, TransactionCallback : TransactionCallback)
            //引数の設定仕様は以下の通り
            //トランザクション属性 : TX_CREATE_NEW
            //TransactionCallback : 生成した注意情報通知TransactionCallbackオブジェクト
            l_queryProcessor.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallback);

            //createResponse()
            l_response = l_attentionInfoNotifyRequest.createResponse();
        }
        catch (DataCallbackException l_ex)
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
        return l_response;
    }

    /**
     * (注意情報通知TransactionCallback)<BR>
     * 注意情報通知TransactionCallback <BR>
     * <BR>
     * トランザクション処理を実施する内部クラス<BR>
     */
    public class WEB3AdminEquityAttentionInfoNotifyTransactionCallback implements TransactionCallback
    {

        /**
         * (注意情報通知TransactionCallback)<BR>
         * コンストラクタ<BR>
         * @@roseuid 49377F7B0107
         */
        public WEB3AdminEquityAttentionInfoNotifyTransactionCallback()
        {

        }

        /**
         * 注意情報通知処理を実施する。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（注意情報サービス）process」参照<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 493784990359
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //（注意情報通知キューテーブルを取得する）
            String l_strWhere = " request_code in ( ? , ? , ? ) and status = ? ";
            Object[] l_values = {
                WEB3HostRequestCodeDef.SELL_STOP_INFO,
                WEB3HostRequestCodeDef.LIMIT_RANGE_INFO,
                WEB3HostRequestCodeDef.FREE_FORMAT,
                WEB3AdminEquityStatusDef.NOT_DEAL};
            String l_strOrderBy = " info_generation_timestamp ";

            List l_lisHostAttentionInfoNotifyRows = null;
            l_lisHostAttentionInfoNotifyRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    HostAttentionInfoNotifyRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_values);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            WEB3EquityTradingModule l_tradingModule =
                (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

            WEB3EquityProductManager l_equityProductManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            //（取得したキューテーブルのレコード数分LOOP）
            int l_intListSize = l_lisHostAttentionInfoNotifyRows.size();
            for (int i = 0; i < l_intListSize; i++)
            {
                HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                    (HostAttentionInfoNotifyParams)l_lisHostAttentionInfoNotifyRows.get(i);

                try
                {
                    //銘柄テーブル
                    ProductRow l_productRow = null;
                    //株式取引銘柄マスタテーブル
                    EqtypeTradedProductRow l_eqtypeTradedProductRow = null;
                    //株式取引銘柄マスタupdqテーブル
                    EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow = null;
                    //株式銘柄テーブル
                    EqtypeProductRow l_eqtypeProductRow = null;
                    //市場
                    Market l_market = null;
                    //株式銘柄オブジェクト
                    WEB3EquityProduct l_equityProduct = null;
                    //注意情報通知キューテーブル.銘柄コード
                    String l_strProductCode = l_hostAttentionInfoNotifyParams.getProductCode();
                    //注意情報通知キューテーブル.データコード
                    String l_strRequestCode = l_hostAttentionInfoNotifyParams.getRequestCode();
                    //notify制限値幅情報()の戻り値
                    String l_strReturn = null;
                    //notify制限値幅情報()をコールフラグ
                    boolean l_blnFlag = false;

                    try
                    {
                        //証券会社(証券会社コード : String)
                        //引数の設定仕様は以下の通り
                        //証券会社コード : 注文情報通知キューテーブル.証券会社コード
                        String l_strInstitutionCode = l_hostAttentionInfoNotifyParams.getInstitutionCode();
                        Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);

                        //get市場BySONAR(証券会社コード : String, 市場コード(SONAR) : String)
                        //引数の設定仕様は以下の通り
                        //証券会社コード : 注文情報通知キューテーブル.証券会社コード
                        //市場コード（SONAR） : 注意情報通知キューテーブル.市場コード（SONAR）
                        String l_strSonarMarketCode = l_hostAttentionInfoNotifyParams.getSonarMarketCode();
                        l_market = l_finObjectManager.getMarketBySONAR(l_strInstitutionCode, l_strSonarMarketCode);

                        //（分岐フロー：注意情報通知キューテーブル.銘柄コード≠nullの場合）
                        if (l_strProductCode != null)
                        {
                            //株式銘柄オブジェクトを取得する。
                            //引数の設定仕様は以下の通り
                            //　@証券会社 : 証券会社オブジェクト
                            //　@銘柄コード :
                            //　@　@注意情報通知キューテーブル.銘柄コードの値が4桁の場合、注意情報通知キューテーブル.銘柄コード＋"0"
                            //　@　@上記以外の場合、注意情報通知キューテーブル.銘柄コード
                            if (l_strProductCode.length() == 4)
                            {
                                l_strProductCode = l_strProductCode + "0";
                            }

                            l_equityProduct =
                                (WEB3EquityProduct)l_equityProductManager.getProduct(l_institution, l_strProductCode);

                            l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();

                            //分岐フロー：(*1)
                            Timestamp l_tsInfoGenerationTimestamp =
                                l_hostAttentionInfoNotifyParams.getInfoGenerationTimestamp();
                            Date l_datBizDate = l_finApp.getTradingSystem().getBizDate();
                            if (WEB3DateUtility.compareToDay(l_tsInfoGenerationTimestamp, l_datBizDate) == 0)
                            {
                                //（分岐フロー：注意情報通知キューテーブル.データコード＝"注意情報（制限値幅情報）通知"の場合）
                                List l_lisProductRows = null;
                                List l_lisEqtypeTradedProductRows = null;
                                List l_lisEqtypeTradedProductUpdqRows = null;
                                if (WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(l_strRequestCode))
                                {
                                    //銘柄テーブルを取得する
                                    String l_strQuery0 = " product_id = ?";
                                    Object[] l_dataContainers0 = {new Long(l_equityProduct.getProductId())};

                                    l_lisProductRows = Processors.getDefaultProcessor().doFindAllQuery(
                                        ProductRow.TYPE,
                                        l_strQuery0,
                                        l_dataContainers0);

                                    if (l_lisProductRows.isEmpty())
                                    {
                                        log.debug(
                                            "銘柄ＩＤ = " + l_equityProduct.getProductId() + "に該当する銘柄テーブルを取得できません");
                                        log.exiting(STR_METHOD_NAME);
                                        throw new NotFoundException(
                                            "銘柄ＩＤ = " + l_equityProduct.getProductId() + "に該当する銘柄テーブルを取得できません");
                                    }

                                    l_productRow = (ProductRow)l_lisProductRows.get(0);

                                    //株式取引銘柄マスタテーブルを取得する
                                    String l_strBizDate = WEB3DateUtility.formatDate(
                                        l_datBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                                    String l_strQuery1 =
                                        " product_id = ? and market_id = ? and valid_until_biz_date = ?";
                                    Object[] l_dataContainers1 = {
                                        new Long(l_equityProduct.getProductId()),
                                        new Long(l_market.getMarketId()),
                                        l_strBizDate};

                                    l_lisEqtypeTradedProductRows =
                                        Processors.getDefaultProcessor().doFindAllQuery(
                                            EqtypeTradedProductRow.TYPE,
                                            l_strQuery1,
                                            l_dataContainers1);

                                    if (l_lisEqtypeTradedProductRows.isEmpty())
                                    {
                                        log.debug(
                                            "銘柄ＩＤ = " + l_equityProduct.getProductId()
                                            + "市場ＩＤ = " + l_market.getMarketId()
                                            + "有効日 = " + l_strBizDate + "に該当する株式取引銘柄マスタテーブルを取得できません");
                                        log.exiting(STR_METHOD_NAME);
                                        throw new NotFoundException(
                                            "銘柄ＩＤ = " + l_equityProduct.getProductId()
                                            + "市場ＩＤ = " + l_market.getMarketId()
                                            + "有効日 = " + l_strBizDate + "に該当する株式取引銘柄マスタテーブルを取得できません");
                                    }

                                    l_eqtypeTradedProductRow =
                                        (EqtypeTradedProductRow)l_lisEqtypeTradedProductRows.get(0);

                                    //株式取引銘柄マスタupdqテーブルを取得する
                                    WEB3GentradeBizDate l_bizDate =
                                        new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
                                    String l_strBizDateNext = WEB3DateUtility.formatDate(
                                        l_bizDate.roll(1), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

                                    String l_strQuery2 =
                                        " product_id = ? and market_id = ? and valid_until_biz_date = ?";
                                    Object[] l_dataContainers2 = {
                                        new Long(l_equityProduct.getProductId()),
                                        new Long(l_market.getMarketId()),
                                        l_strBizDateNext};

                                    l_lisEqtypeTradedProductUpdqRows =
                                        Processors.getDefaultProcessor().doFindAllQuery(
                                            EqtypeTradedProductUpdqRow.TYPE,
                                            l_strQuery2,
                                            l_dataContainers2);

                                    if (!l_lisEqtypeTradedProductUpdqRows.isEmpty())
                                    {
                                        l_eqtypeTradedProductUpdqRow =
                                            (EqtypeTradedProductUpdqRow)l_lisEqtypeTradedProductUpdqRows.get(0);
                                    }

                                    //notify制限値幅情報(注意情報通知キューParams, 株式取引銘柄Row, 株式取引銘柄updqRow, 銘柄Row)
                                    //引数の設定仕様は以下の通り
                                    //注意情報通知キューテーブル : 注意情報通知キューテーブルオブジェクト
                                    //株式取引銘柄 : 株式取引銘柄オブジェクト
                                    //株式取引銘柄updq : 株式取引銘柄updqオブジェクト
                                    //銘柄 : 銘柄オブジェクト
                                    WEB3AdminEquityAttentionInfoNotifyUnitService l_service =
                                        (WEB3AdminEquityAttentionInfoNotifyUnitService)Services.getService(
                                            WEB3AdminEquityAttentionInfoNotifyUnitService.class);

                                    l_strReturn = l_service.notifyLimitRangeInfo(
                                        l_hostAttentionInfoNotifyParams,
                                        l_eqtypeTradedProductRow,
                                        l_eqtypeTradedProductUpdqRow,
                                        l_productRow);

                                    l_blnFlag = true;
                                }
                            }
                        }

                        if (l_strProductCode == null
                            && WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(l_strRequestCode))
                        {
                            //処理区分
                            l_hostAttentionInfoNotifyParams.setStatus(WEB3AdminEquityStatusDef.ERROR);
                            //更新日付
                            l_hostAttentionInfoNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        }
                        else
                        {
                            //処理区分
                            l_hostAttentionInfoNotifyParams.setStatus(WEB3AdminEquityStatusDef.DEALT);
                            //更新日付
                            l_hostAttentionInfoNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        }
                    }
                    catch (Exception l_ex)
                    {
                        //処理区分
                        l_hostAttentionInfoNotifyParams.setStatus(WEB3AdminEquityStatusDef.ERROR);
                        //更新日付
                        l_hostAttentionInfoNotifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    }

                    //キューテーブルの処理区分、更新日付を更新する
                    Processors.getDefaultProcessor().doUpdateQuery(l_hostAttentionInfoNotifyParams);

                    //insert注意情報履歴
                    //(注意情報通知キューParams, 市場, 銘柄Row, 株式取引銘柄Row, 株式取引銘柄updqRow, String)
                    //引数の設定仕様は以下の通り
                    //注意情報通知キューテーブル : 注意情報通知キューParams
                    //市場 : 市場オブジェクト
                    //更新前の銘柄 :
                    //  注意情報通知キューテーブル.銘柄コード≠nullの場合、銘柄オブジェクト（※）
                    //  以外、null
                    //更新前の株式取引銘柄 :
                    //  注意情報通知キューテーブル.銘柄コード≠nullの場合、株式取引銘柄オブジェクト（※）
                    //  以外、null
                    //更新前の株式取引銘柄updq :
                    //  注意情報通知キューテーブル.銘柄コード≠nullの場合、株式取引銘柄updqオブジェクト（※）
                    //  以外、null
                    //株式銘柄 :
                    //  注意情報通知キューテーブル.銘柄コード≠nullの場合、株式銘柄オブジェクト
                    //  以外、null
                    if (l_strProductCode == null)
                    {
                        l_productRow = null;
                        l_eqtypeTradedProductRow = null;
                        l_eqtypeTradedProductUpdqRow = null;
                        l_eqtypeProductRow = null;
                    }

                    //銘柄更新フラグ :
                    //  notify制限値幅情報()をコールしている場合は、notify制限値幅情報()の戻り値
                    //  以外、"2：銘柄未更新"
                    String l_strProductUpdateFlag = null;
                    if (l_blnFlag)
                    {
                        l_strProductUpdateFlag = l_strReturn;
                    }
                    else
                    {
                        l_strProductUpdateFlag = WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE;
                    }

                    AttentionInfoHistoryParams l_attentionInfoHistoryParams =
                        this.insertAttentionInfoHistory(
                            l_hostAttentionInfoNotifyParams,
                            l_market,
                            l_productRow,
                            l_eqtypeTradedProductRow,
                            l_eqtypeTradedProductUpdqRow,
                            l_eqtypeProductRow,
                            l_strProductUpdateFlag);

                    //insertメール送信テーブル(株式銘柄, 市場, 注意情報履歴)
                    //引数の設定仕様は以下の通り
                    //株式銘柄 : 注意情報通知キューテーブル.銘柄コード≠nullの場合、株式銘柄オブジェクト
                    //以外、null
                    //市場 : 市場オブジェクト
                    //注意情報履歴 : 注意情報履歴オブジェクト
                    this.insertSendMail(
                        l_equityProduct,
                        l_market,
                        l_attentionInfoHistoryParams);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new DataCallbackException(
                        l_ex.getMessage(),
                        l_ex);
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (insert注意情報履歴)<BR>
         * 注意情報履歴テーブルに1件Insertを行い、<BR>
         * Insertされた注意情報履歴オブジェクトを返却する。<BR>
         * <BR>
         * 更新内容は『注意情報通知_注意情報履歴テーブル.xls』を参照。<BR>
         * @@param l_hostAttentionInfoNotifyParams - (注意情報通知キューテーブル)<BR>
         * 注意情報通知キューテーブル<BR>
         * @@param l_market - (市場)<BR>
         * 市場オブジェクト<BR>
         * @@param l_productRow - (更新前の銘柄)<BR>
         * 更新前の銘柄オブジェクト<BR>
         * @@param l_eqtypeTradedProductRow - (更新前の株式取引銘柄)<BR>
         * 更新前の株式取引銘柄オブジェクト<BR>
         * @@param l_eqtypeTradedProductUpdqRow - (更新前の株式取引銘柄updq)<BR>
         * 更新前の株式取引銘柄updqオブジェクト<BR>
         * @@param l_eqtypeProductRow - (株式銘柄)<BR>
         * 株式銘柄オブジェクト<BR>
         * @@param l_strProductUpdateFlag - (銘柄更新フラグ)<BR>
         * 銘柄更新フラグ<BR>
         * <BR>
         * ・1：銘柄更新済み<BR>
         * ・2：銘柄未更新<BR>
         * @@return AttentionInfoHistoryParams
         * @@throws WEB3BaseException
         * @@roseuid 493CADF80307
         */
        private AttentionInfoHistoryParams insertAttentionInfoHistory(
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams,
            Market l_market,
            ProductRow l_productRow,
            EqtypeTradedProductRow l_eqtypeTradedProductRow,
            EqtypeTradedProductUpdqRow l_eqtypeTradedProductUpdqRow,
            EqtypeProductRow l_eqtypeProductRow,
            String l_strProductUpdateFlag) throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "insertAttentionInfoHistory(HostAttentionInfoNotifyParams, Market, ProductRow,"
                + " EqtypeTradedProductRow, EqtypeTradedProductUpdqRow, EqtypeProductRow, String)";
            log.entering(STR_METHOD_NAME);

            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();

            try
            {
                //注意情報履歴ID
                l_attentionInfoHistoryParams.setAttentionInfoHistoryId(AttentionInfoHistoryDao.newPkValue());
                //注意情報種別
                if (WEB3HostRequestCodeDef.SELL_STOP_INFO.equals(l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //注意情報通知キュー.データコード＝"注意情報（売停情報）通知"の場合
                    //"1：売停情報"
                    l_attentionInfoHistoryParams.setAttentionInfoType(WEB3AttentionInfoTypeDef.SELL_STOP_INFO);
                }
                else if (WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //注意情報通知キュー.データコード＝"注意情報（制限値幅情報）通知"の場合
                    //"2：制限値幅情報"
                    l_attentionInfoHistoryParams.setAttentionInfoType(WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO);
                }
                else if (WEB3HostRequestCodeDef.FREE_FORMAT.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //注意情報通知キュー.データコード＝"注意情報（フリーフォーマット）通知"の場合
                    //"3：フリーフォーマット"
                    l_attentionInfoHistoryParams.setAttentionInfoType(WEB3AttentionInfoTypeDef.FREE_FORMAT);
                }
                //証券会社コード
                l_attentionInfoHistoryParams.setInstitutionCode(l_hostAttentionInfoNotifyParams.getInstitutionCode());

                //銘柄ＩＤ
                //株式銘柄.銘柄ＩＤ（※３）
                //銘柄名
                //株式銘柄.銘柄名（※３）
                if (l_eqtypeProductRow != null)
                {
                    l_attentionInfoHistoryParams.setProductId(l_eqtypeProductRow.getProductId());
                    l_attentionInfoHistoryParams.setStandardName(l_eqtypeProductRow.getStandardName());
                }

                //市場ＩＤ
                //市場.市場ＩＤ（※３）
                if (l_market != null)
                {
                    l_attentionInfoHistoryParams.setMarketId(l_market.getMarketId());
                }

                //有効日
                l_attentionInfoHistoryParams.setValidUntilBizDate(
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),
                        WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //注意情報区分コード
                l_attentionInfoHistoryParams.setAttentionInfoDivCode(
                    l_hostAttentionInfoNotifyParams.getAttentionInfoDivCode());

                if (WEB3HostRequestCodeDef.LIMIT_RANGE_INFO.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //評価単価（変更前）
                    //更新前の銘柄.評価単価（※１）（※３）
                    if (l_productRow != null)
                    {
                        l_attentionInfoHistoryParams.setOldEstimationPrice(l_productRow.getEstimationPrice());
                    }

                    if (l_eqtypeTradedProductRow != null)
                    {
                        //基準値（終値）（変更前）
                        //更新前の株式取引銘柄.基準値（終値）（※１）（※３）
                        l_attentionInfoHistoryParams.setOldLastClosingPrice(
                            l_eqtypeTradedProductRow.getLastClosingPrice());

                        //基準値（変更前）
                        //更新前の株式取引銘柄.基準値（※１）（※３）
                        l_attentionInfoHistoryParams.setOldBasePrice(
                            l_eqtypeTradedProductRow.getBasePrice());

                        //値幅チェック区分（変更前）
                        //更新前の株式取引銘柄.値幅チェック区分（※１）（※３）
                        l_attentionInfoHistoryParams.setOldPriceRangeType(
                            l_eqtypeTradedProductRow.getPriceRangeType());

                        //値幅区分（変更前）
                        //更新前の株式取引銘柄.値幅区分（※１）（※３）
                        l_attentionInfoHistoryParams.setOldPriceRangeUnitType(
                            l_eqtypeTradedProductRow.getPriceRangeUnitType());

                        if (!l_eqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull())
                        {
                            //強制値幅（上限値）（変更前）
                            //更新前の株式取引銘柄.強制値幅（上限値）（※１）（※３）
                            l_attentionInfoHistoryParams.setOldHighCompPriceRange(
                                l_eqtypeTradedProductRow.getHighCompulsivePriceRange());
                        }

                        if (!l_eqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull())
                        {
                            //強制値幅（下限値）（変更前）
                            //更新前の株式取引銘柄.強制値幅（下限値）（※１）（※３）
                            l_attentionInfoHistoryParams.setOldLowCompPriceRange(
                                l_eqtypeTradedProductRow.getLowCompulsivePriceRange());
                        }
                    }

                    if (l_eqtypeTradedProductUpdqRow != null)
                    {
                        //基準値（終値）（翌日）（変更前）
                        //更新前の株式取引銘柄updq.基準値（終値）（※１）（※３）
                        l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(
                            l_eqtypeTradedProductUpdqRow.getLastClosingPrice());

                        //基準値（翌日）（変更前）
                        //更新前の株式取引銘柄updq.基準値（※１）（※３）
                        l_attentionInfoHistoryParams.setOldBasePriceUpdq(
                            l_eqtypeTradedProductUpdqRow.getBasePrice());
                    }

                    if (!l_hostAttentionInfoNotifyParams.getBasePriceIsNull())
                    {
                        //評価単価（変更後）
                        //注意情報通知キュー.基準値（※１）
                        l_attentionInfoHistoryParams.setNewEstimationPrice(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //基準値（終値）（変更後）
                        //注意情報通知キュー.基準値（※１）
                        l_attentionInfoHistoryParams.setNewLastClosingPrice(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //基準値（変更後）
                        //注意情報通知キュー.基準値（※１）
                        l_attentionInfoHistoryParams.setNewBasePrice(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //基準値（終値）（翌日）（変更後）
                        //注意情報通知キュー.基準値（※１）
                        l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(
                            l_hostAttentionInfoNotifyParams.getBasePrice());

                        //基準値（翌日）（変更後）
                        //注意情報通知キュー.基準値（※１）
                        l_attentionInfoHistoryParams.setNewBasePriceUpdq(
                            l_hostAttentionInfoNotifyParams.getBasePrice());
                    }

                    //制限値幅上限（変更後）
                    //注意情報通知キュー.制限値幅上限（※１）
                    if (!l_hostAttentionInfoNotifyParams.getHighPriceRangeIsNull())
                    {
                        l_attentionInfoHistoryParams.setNewHighPriceRange(
                            l_hostAttentionInfoNotifyParams.getHighPriceRange());
                    }

                    //制限値幅下限（変更後）
                    //注意情報通知キュー.制限値幅下限（※１）
                    if (!l_hostAttentionInfoNotifyParams.getLowPriceRangeIsNull())
                    {
                        l_attentionInfoHistoryParams.setNewLowPriceRange(
                            l_hostAttentionInfoNotifyParams.getLowPriceRange());
                    }

                    //値幅チェック区分(変更後)
                    //"1:値幅チェックあり"（※１）
                    l_attentionInfoHistoryParams.setNewPriceRangeType(WEB3PriceRangeTypeDef.CHECK);

                    //値幅区分（変更後）
                    //"1：円"（※１）
                    l_attentionInfoHistoryParams.setNewPriceRangeUnitType(WEB3PriceRangeIdDef.YEN);
                }

                if (WEB3HostRequestCodeDef.FREE_FORMAT.equals(
                    l_hostAttentionInfoNotifyParams.getRequestCode()))
                {
                    //表題
                    l_attentionInfoHistoryParams.setFreeFormatTitle(
                        l_hostAttentionInfoNotifyParams.getFreeFormatTitle());
                    //本文
                    l_attentionInfoHistoryParams.setFreeFormatText(
                        l_hostAttentionInfoNotifyParams.getFreeFormatText());
                }
                //情報発生日時
                l_attentionInfoHistoryParams.setInfoGenerationTimestamp(
                    l_hostAttentionInfoNotifyParams.getInfoGenerationTimestamp());

                //注文受付再開日時
                l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(
                    l_hostAttentionInfoNotifyParams.getOrdReceiptRestartTimestamp());

                //売買停止日時／売買再開日時
                l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(
                    l_hostAttentionInfoNotifyParams.getTradeStopRestartTimestamp());

                //処理結果区分
                if (WEB3AdminEquityStatusDef.DEALT.equals(l_hostAttentionInfoNotifyParams.getStatus()))
                {
                    //注意情報通知キュー.処理区分＝"1：処理済"でかつ銘柄更新フラグ＝"1：銘柄更新済み"の場合
                    //1：正常
                    if (WEB3AdminEquityProductUpdateFlagDef.PRODUCT_UPDATE.equals(l_strProductUpdateFlag))
                    {
                        l_attentionInfoHistoryParams.setProcessResultDiv(WEB3ProcessResultDivDef.NORMAL);
                    }
                    //銘柄更新フラグ＝"2：銘柄未更新"の場合
                    //2：正常（更新無）
                    else if (WEB3AdminEquityProductUpdateFlagDef.PRODUCT_NOT_UPDATE.equals(l_strProductUpdateFlag))
                    {
                        l_attentionInfoHistoryParams.setProcessResultDiv(WEB3ProcessResultDivDef.NORMAL_NOT_UPDATE);
                    }
                }
                else if (WEB3AdminEquityStatusDef.ERROR.equals(
                    l_hostAttentionInfoNotifyParams.getStatus()))
                {
                    //注意情報通知キュー.処理区分＝"9：エラー"の場合
                    //9：エラー
                    l_attentionInfoHistoryParams.setProcessResultDiv(WEB3ProcessResultDivDef.ERROR);
                }

                //作成日付
                l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //更新日付
                l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doInsertQuery(l_attentionInfoHistoryParams);
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

            log.exiting(STR_METHOD_NAME);
            return l_attentionInfoHistoryParams;
        }

        /**
         * (insertメール送信テーブル)<BR>
         * メール送信テーブル、メール送信拡張テーブルにInsertを行う。<BR>
         * <BR>
         * １）　@メールオブジェクトを生成する。<BR>
         * 　@　@[引数]<BR>
         * 　@　@　@証券会社コード : 注意情報履歴テーブル.証券会社コード<BR>
         * 　@　@　@送信メール区分 : <BR>
         * 　@　@　@　@注意情報履歴テーブル.処理結果区分＝"エラー"の場合<BR>
         * 　@　@　@　@　@"31：注意情報通知メール"　@＋　@"04：注意情報（エラー）"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報種別＝"売停情報"の場合<BR>
         * 　@　@　@　@　@"31：注意情報通知メール"　@＋　@"01：注意情報（売停情報）"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報種別＝"制限値幅情報"の場合<BR>
         * 　@　@　@　@　@"31：注意情報通知メール"　@＋　@"02：注意情報（制限値幅情報）"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合<BR>
         * 　@　@　@　@　@"31：注意情報通知メール"　@＋　@"03：注意情報（フリーフォーマット）"<BR>
         * <BR>
         * 　@　@　@識別ID : <BR>
         * 　@　@　@　@注意情報履歴テーブル.処理結果区分＝"エラー"の場合、"1"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）"の場合、"1"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）"の場合、"2"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の取消"の場合、"3"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の取消"の場合、"4"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の解除"の場合、"5"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の解除"の場合、"6"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の解除の取消"の場合、"7"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の解除の取消"の場合、"8"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買中断"の場合、"9"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買中断の取消"の場合、"10"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買中断の解除"の場合、"11"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"売買中断の解除の取消"の場合、"12"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合<BR>
         * 　@　@　@　@　@・注意情報履歴テーブル.処理結果区分＝"正常"の場合、"1"<BR>
         * 　@　@　@　@　@・注意情報履歴テーブル.処理結果区分＝"正常（更新無）"の場合、"2"<BR>
         * 　@　@　@　@注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合、"1"<BR>
         * <BR>
         * ２）　@メール送信テーブルに1件Insertを行う。<BR>
         * 　@更新内容は『注意情報通知_メール送信テーブル.xls』を参照<BR>
         * <BR>
         * ３）　@メール送信拡張テーブルにInsertを行なう。<BR>
         * 　@更新内容は『注意情報通知_メール送信拡張テーブル.xls』を参照<BR>
         * <BR>
         * @@param l_equityProduct - (株式銘柄)<BR>
         * 株式銘柄オブジェクト<BR>
         * @@param l_market - (市場)<BR>
         * 市場オブジェクト<BR>
         * @@param l_attentionInfoHistoryParams - (注意情報履歴)<BR>
         * 注意情報履歴オブジェクト<BR>
         * @@throws WEB3BaseException
         * @@roseuid 493CB36902BC
         */
        private void insertSendMail(
            WEB3EquityProduct l_equityProduct,
            Market l_market,
            AttentionInfoHistoryParams l_attentionInfoHistoryParams) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "insertSendMail(WEB3EquityProduct, Market, AttentionInfoHistoryParams)";
            log.entering(STR_METHOD_NAME);

            //メールオブジェクトを生成する。
            //証券会社コード : 注意情報履歴テーブル.証券会社コード
            String l_strInstitutionCode = l_attentionInfoHistoryParams.getInstitutionCode();
            //送信メール区分 :
            String l_strSendmailDiv = null;
            String l_strProcessResultDiv = l_attentionInfoHistoryParams.getProcessResultDiv();
            String l_strAttentionInfoType = l_attentionInfoHistoryParams.getAttentionInfoType();
            //注意情報履歴テーブル.処理結果区分＝"エラー"の場合
            //"31：注意情報通知メール"　@＋　@"04：注意情報（エラー）"
            if (WEB3ProcessResultDivDef.ERROR.equals(l_strProcessResultDiv))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.TTENTION_INFO_ERROR;
            }
            //注意情報履歴テーブル.注意情報種別＝"売停情報"の場合
            //”31：注意情報通知メール"　@＋　@"01：注意情報（売停情報）"
            else if (WEB3AttentionInfoTypeDef.SELL_STOP_INFO.equals(l_strAttentionInfoType))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.ATTENTION_INFO_SELL_STOP_INFO;
            }
            //注意情報履歴テーブル.注意情報種別＝"制限値幅情報"の場合
            //”31：注意情報通知メール"　@＋　@"02：注意情報（制限値幅情報）"
            else if (WEB3AttentionInfoTypeDef.LIMIT_RANGE_INFO.equals(l_strAttentionInfoType))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.TTENTION_INFO_LIMIT_RANGE_INFO;
            }
            //注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
            //”31：注意情報通知メール"　@＋　@"03：注意情報（フリーフォーマット）"
            else if (WEB3AttentionInfoTypeDef.FREE_FORMAT.equals(l_strAttentionInfoType))
            {
                l_strSendmailDiv = WEB3SendmailDivDef.TTENTION_INFO_FREE_FORMAT;
            }

            //識別ID :
            String l_strDiscernmentId = null;
            String l_strAttentionInfoDivCode = l_attentionInfoHistoryParams.getAttentionInfoDivCode();

            //注意情報履歴テーブル.処理結果区分＝"エラー"の場合、"1"
            if (WEB3ProcessResultDivDef.ERROR.equals(l_strProcessResultDiv))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）"の場合、"1"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）"の場合、"2"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TWO;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の取消"の場合、"3"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.THREE;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の取消"の場合、"4"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_CANCEL_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.FOUR;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の解除"の場合、"5"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.FIVE;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の解除"の場合、"6"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.SIX;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の解除の取消"の場合、"7"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_ENABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.SEVEN;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の解除の取消"の場合、"8"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_STOP_RELEASE_CANCEL_ORDER_ACCEPT_DISABLE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.EIGHT;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買中断"の場合、"9"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.NINE;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買中断の取消"の場合、"10"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_CANCEL.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TEN;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買中断の解除"の場合、"11"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ELEVEN;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"売買中断の解除の取消"の場合、"12"
            else if (WEB3AttentionInfoDivCodeDef.TRADE_INTERRUPT_RELEASE_CANCEL.equals(
                l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TWELVE;
            }
            //注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合
            else if (WEB3AttentionInfoDivCodeDef.OPEN_LISTING_PRODUCT_GIVEN_FIRST_VALUE.equals(
                l_strAttentionInfoDivCode))
            {
                //・注意情報履歴テーブル.処理結果区分＝"正常"の場合、"1"
                if (WEB3ProcessResultDivDef.NORMAL.equals(l_strProcessResultDiv))
                {
                    l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
                }
                //・注意情報履歴テーブル.処理結果区分＝"正常（更新無）"の場合、"2"
                else if (WEB3ProcessResultDivDef.NORMAL_NOT_UPDATE.equals(l_strProcessResultDiv))
                {
                    l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.TWO;
                }
            }
            //注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合、"1"
            else if (WEB3AttentionInfoDivCodeDef.FREE_FORMAT.equals(l_strAttentionInfoDivCode))
            {
                l_strDiscernmentId = WEB3AdminEquityDiscernmentIdDef.ONE;
            }

            WEB3GentradeMailInfo l_mailInfo =
                new WEB3GentradeMailInfo(
                    l_strInstitutionCode,
                    l_strSendmailDiv,
                    l_strDiscernmentId);

            //メール送信テーブルに1件Insertを行う。
            //更新内容は『注意情報通知_メール送信テーブル.xls』を参照
            MailProcParams l_mailProcParams = new MailProcParams();

            l_mailProcParams.setInstitutionCode(l_strInstitutionCode);
            l_mailProcParams.setBranchCode(WEB3BranchCodeDef.DEFAULT);
            l_mailProcParams.setSendmailDiv(l_mailInfo.getSendmailDiv());
            l_mailProcParams.setDiscernmentId(l_mailInfo.getDiscernmentId());
            l_mailProcParams.setAccountCode(WEB3AccountCodeDef.ACCOUNT_CODE_0000000);
            l_mailProcParams.setMailId(l_attentionInfoHistoryParams.getAttentionInfoHistoryId());
            if (l_attentionInfoHistoryParams.getStandardName() != null)
            {
                l_mailProcParams.setName1(l_attentionInfoHistoryParams.getStandardName());
            }
            else
            {
                l_mailProcParams.setName1(" ");
            }
            if (l_market != null)
            {
                l_mailProcParams.setName2(l_market.getMarketName());
            }
            else
            {
                l_mailProcParams.setName2(" ");
            }
            l_mailProcParams.setStatus(WEB3AdminEquityStatusDef.NOT_DEAL);
            l_mailProcParams.setEmailAddress(l_mailInfo.getSendAddress());
            l_mailProcParams.setSendEmailAddress(l_mailInfo.getMailSender());
            l_mailProcParams.setSubject(l_mailInfo.getSubject());
            if (l_attentionInfoHistoryParams.getFreeFormatText() != null)
            {
                l_mailProcParams.setMailText(l_attentionInfoHistoryParams.getFreeFormatText());
            }
            else
            {
                l_mailProcParams.setMailText(" ");
            }
            l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            try
            {
                Processors.getDefaultProcessor().doInsertQuery(l_mailProcParams);
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

            //メール送信拡張テーブルにInsertを行なう。
            //更新内容は『注意情報通知_メール送信拡張テーブル.xls』を参照
            String l_strSendMailDiv = l_mailProcParams.getSendmailDiv();

            ExtMailProcParams l_extMailProcParams = new ExtMailProcParams();

            l_extMailProcParams.setInstitutionCode(l_mailProcParams.getInstitutionCode());
            l_extMailProcParams.setBranchCode(l_mailProcParams.getBranchCode());
            l_extMailProcParams.setSendmailDiv(l_strSendMailDiv);
            l_extMailProcParams.setDiscernmentId(l_mailProcParams.getDiscernmentId());
            l_extMailProcParams.setAccountCode(l_mailProcParams.getAccountCode());
            l_extMailProcParams.setMailId(l_mailProcParams.getMailId());
            l_extMailProcParams.setDeleteFlag(BooleanEnum.FALSE);

            //登録内容:項目名 項目内容
            Map l_mapItems = new HashMap();
            //銘柄コード
            //株式銘柄≠nullの場合、株式銘柄.銘柄コード以外、null
            if (l_equityProduct != null)
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE, l_equityProduct.getProductCode());
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE, null);
            }

            //市場コード
            //市場≠nullの場合、市場.市場コード以外、null
            if (l_market != null)
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.MARKET_CODE, l_market.getMarketCode());
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.MARKET_CODE, null);
            }

            //情報発生日時
            //注意情報履歴テーブル.情報発生日時（yyyy/mm/dd hh:mm:ss形式）
            Timestamp l_tsInfoGenerationTimestamp =
                l_attentionInfoHistoryParams.getInfoGenerationTimestamp();
            String l_strInfoGenerationTimestamp = WEB3DateUtility.formatDate(
                l_tsInfoGenerationTimestamp, WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                l_strInfoGenerationTimestamp);

            //売買停止日時／売買再開日時
            //注意情報履歴テーブル.売買停止日時／売買再開日時（yyyy/mm/dd hh:mm:ss形式）
            Timestamp l_tsTradeStopRestartTimestamp =
                l_attentionInfoHistoryParams.getTradeStopRestartTimestamp();
            if (l_tsTradeStopRestartTimestamp != null)
            {
                String l_strTradeStopRestartTimestamp = WEB3DateUtility.formatDate(
                    l_tsTradeStopRestartTimestamp, WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP,
                    l_strTradeStopRestartTimestamp);
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP, null);
            }
            //注文受付再開日時
            //注意情報履歴テーブル.注文受付再開日時（yyyy/mm/dd hh:mm:ss形式）
            Timestamp l_tsOrdReceiptRestartTimestamp =
                l_attentionInfoHistoryParams.getOrdReceiptRestartTimestamp();
            if (l_tsOrdReceiptRestartTimestamp != null)
            {
                String l_strOrdReceiptRestartTimestamp = WEB3DateUtility.formatDate(
                    l_tsOrdReceiptRestartTimestamp, WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP,
                    l_strOrdReceiptRestartTimestamp);
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP, null);
            }

            //評価単価（変更前）
            //注意情報履歴テーブル.評価単価（変更前）
            if (!l_attentionInfoHistoryParams.getOldEstimationPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE,
                    l_attentionInfoHistoryParams.getOldEstimationPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE, null);
            }

            //評価単価（変更後）
            //注意情報履歴テーブル.評価単価（変更後）
            if (!l_attentionInfoHistoryParams.getNewEstimationPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE,
                    l_attentionInfoHistoryParams.getNewEstimationPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE, null);
            }

            //基準値（終値）（変更前）
            //注意情報履歴テーブル.基準値（終値）（変更前）
            if (!l_attentionInfoHistoryParams.getOldLastClosingPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE,
                    l_attentionInfoHistoryParams.getOldLastClosingPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE, null);
            }

            //基準値（終値）（変更後）
            //注意情報履歴テーブル.基準値（終値）（変更後）
            if (!l_attentionInfoHistoryParams.getNewLastClosingPriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE,
                    l_attentionInfoHistoryParams.getNewLastClosingPrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE, null);
            }

            //基準値（変更前）
            //注意情報履歴テーブル.基準値（変更前）
            if (!l_attentionInfoHistoryParams.getOldBasePriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE,
                    l_attentionInfoHistoryParams.getOldBasePrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE, null);
            }

            //基準値（変更後）
            //注意情報履歴テーブル.基準値（変更後）
            if (!l_attentionInfoHistoryParams.getNewBasePriceIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE,
                    l_attentionInfoHistoryParams.getNewBasePrice() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE, null);
            }

            //値幅チェック区分（変更前）
            //注意情報履歴テーブル.値幅チェック区分（変更前）
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_TYPE,
                l_attentionInfoHistoryParams.getOldPriceRangeType());

            //値幅チェック区分（変更後）
            //注意情報履歴テーブル.値幅チェック区分（変更後）
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_TYPE,
                l_attentionInfoHistoryParams.getNewPriceRangeType());

            //値幅区分（変更前）
            //注意情報履歴テーブル.値幅区分（変更前）
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_UNIT_TYPE,
                l_attentionInfoHistoryParams.getOldPriceRangeUnitType());

            //値幅区分（変更後）
            //注意情報履歴テーブル.値幅区分（変更後）
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_UNIT_TYPE,
                l_attentionInfoHistoryParams.getNewPriceRangeUnitType());

            //強制値幅（上限値）（変更前）
            //注意情報履歴テーブル.強制値幅（上限値）（変更前）≠nullかつ
            //注意情報履歴テーブル.基準値（変更前）≠nulの場合
            //注意情報履歴テーブル.基準値（変更前）+注意情報履歴テーブル.強制値幅（上限値）（変更前）
            //（ただし、計算結果が0以下の場合はnull）
            //上記以外の場合、null
            if (!l_attentionInfoHistoryParams.getOldHighCompPriceRangeIsNull()
                && !l_attentionInfoHistoryParams.getOldBasePriceIsNull())
            {
                BigDecimal l_bdOldHighCompPriceRange =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldHighCompPriceRange() + "");
                BigDecimal l_bdOldBasePrice =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldBasePrice() + "");
                BigDecimal l_bdOldHighPriceRange = l_bdOldBasePrice.add(l_bdOldHighCompPriceRange);

                if (l_bdOldHighPriceRange.doubleValue() > 0)
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE,
                        l_bdOldHighPriceRange.doubleValue() + "");
                }
                else
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE, null);
                }
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE, null);
            }

            //制限値幅上限（変更後）
            //注意情報履歴テーブル.制限値幅上限（変更後）
            if (!l_attentionInfoHistoryParams.getNewHighPriceRangeIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE,
                    l_attentionInfoHistoryParams.getNewHighPriceRange() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE, null);
            }

            //強制値幅（下限値）（変更前）
            //注意情報履歴テーブル.強制値幅（下限値）（変更前）≠nullかつ
            //注意情報履歴テーブル.基準値（変更前）≠nullの場合
            //注意情報履歴テーブル.基準値（変更前）-注意情報履歴テーブル.強制値幅（下限値）（変更前）
            //（ただし、計算結果が0以下の場合はnull）
            //上記以外の場合、null
            if (!l_attentionInfoHistoryParams.getOldLowCompPriceRangeIsNull()
                && !l_attentionInfoHistoryParams.getOldBasePriceIsNull())
            {
                BigDecimal l_bdOldLowCompPriceRange =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldLowCompPriceRange() + "");
                BigDecimal l_bdOldBasePrice =
                    new BigDecimal(l_attentionInfoHistoryParams.getOldBasePrice() + "");
                BigDecimal l_bdOldLowPriceRange = l_bdOldBasePrice.subtract(l_bdOldLowCompPriceRange);

                if (l_bdOldLowPriceRange.doubleValue() > 0)
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE,
                        l_bdOldLowPriceRange.doubleValue() + "");
                }
                else
                {
                    l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE, null);
                }
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE, null);
            }

            //強制値幅（下限値）（変更前）
            //注意情報履歴テーブル.強制値幅（下限値）（変更前）
            if (!l_attentionInfoHistoryParams.getNewLowPriceRangeIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE,
                    l_attentionInfoHistoryParams.getNewLowPriceRange() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE, null);
            }

            //基準値（終値）（翌日）（変更前）
            //注意情報履歴テーブル.基準値（終値）（翌日）（変更前）
            if (!l_attentionInfoHistoryParams.getOldLastClosingPriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getOldLastClosingPriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ, null);
            }

            //基準値（終値）（翌日）（変更後）
            //注意情報履歴テーブル.基準値（終値）（翌日）（変更後）
            if (!l_attentionInfoHistoryParams.getNewLastClosingPriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getNewLastClosingPriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ, null);
            }

            //基準値（翌日）（変更前）
            //注意情報履歴テーブル.基準値（翌日）（変更前）
            if (!l_attentionInfoHistoryParams.getOldBasePriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getOldBasePriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ, null);
            }

            //基準値（翌日）（変更後）
            //注意情報履歴テーブル.基準値（翌日）（変更後）
            if (!l_attentionInfoHistoryParams.getNewBasePriceUpdqIsNull())
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ,
                    l_attentionInfoHistoryParams.getNewBasePriceUpdq() + "");
            }
            else
            {
                l_mapItems.put(WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ, null);
            }

            //表題
            //注意情報履歴テーブル.表題
            l_mapItems.put(WEB3AdminEquityHashMapKeyDef.TITLE,
                l_attentionInfoHistoryParams.getFreeFormatTitle());

            Map l_map = new HashMap();

            //メール送信テーブル.送信メール区分 = 3101：注意情報（売停情報）
            //登録項目名:銘柄コード、市場コード、情報発生日時、売買停止日時／売買再開日時、注文受付再開日時
            l_map.put(WEB3SendmailDivDef.ATTENTION_INFO_SELL_STOP_INFO,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE,
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP});

            //メール送信テーブル.送信メール区分 = 3102：注意情報（制限値幅情報）
            //登録項目名:銘柄コード、市場コード、情報発生日時、評価単価（変更前）...
            l_map.put(WEB3SendmailDivDef.TTENTION_INFO_LIMIT_RANGE_INFO,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE,
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ});

            //メール送信テーブル.送信メール区分 = 3103：注意情報（フリーフォーマット）
            //登録項目名:市場コード、情報発生日時、表題
            l_map.put(WEB3SendmailDivDef.TTENTION_INFO_FREE_FORMAT,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.TITLE});

            //メール送信テーブル.送信メール区分 = 3104：注意情報（エラー）
            //登録項目名:　@全て
            l_map.put(WEB3SendmailDivDef.TTENTION_INFO_ERROR,
                new String[]{
                    WEB3AdminEquityHashMapKeyDef.PRODUCT_CODE,
                    WEB3AdminEquityHashMapKeyDef.MARKET_CODE,
                    WEB3AdminEquityHashMapKeyDef.INFO_GENERATION_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.TRADE_STOP_RESTART_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.ORD_RECEIPT_RESTART_TIMESTAMP,
                    WEB3AdminEquityHashMapKeyDef.OLD_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_ESTIMATION_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.NEW_PRICE_RANGE_UNIT_TYPE,
                    WEB3AdminEquityHashMapKeyDef.OLD_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_HIGH_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.NEW_LOW_PRICE_RANGE,
                    WEB3AdminEquityHashMapKeyDef.OLD_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_LAST_CLOSING_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.OLD_BASE_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.NEW_BASE_PRICE_UPDQ,
                    WEB3AdminEquityHashMapKeyDef.TITLE});

            if (l_map.containsKey(l_strSendMailDiv))
            {
                int l_intLength = 0;
                String[] l_strIndexs = (String[])l_map.get(l_strSendMailDiv);
                l_intLength = l_strIndexs.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    if (l_mapItems.containsKey(l_strIndexs[i]) && l_mapItems.get(l_strIndexs[i]) != null)
                    {
                        l_extMailProcParams.setItemName(l_strIndexs[i]);
                        l_extMailProcParams.setItemContents((String)l_mapItems.get(l_strIndexs[i]));
                        l_extMailProcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_extMailProcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        try
                        {
                            Processors.getDefaultProcessor().doInsertQuery(l_extMailProcParams);
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
                    }
                }
            }
            log.exiting(STR_METHOD_NAME);
        }
    }
}@
