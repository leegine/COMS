head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDataManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ(break)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2005/08/16 沢村(SRA) 未取込No.016(パフォーマンス改善)
Revesion History : 2005/10/07 譚漢江(中訊) 仕様変更No.056
Revesion History : 2005/10/13 沢田(SRA) 障害管理No.U2469(市場開局時間帯取得の不具合)
Revesion History : 2005/10/31 沢田(SRA) 障害管理No.U2480(表示内容Params取得時における、表示内容ID設定の不正)
Revesion History : 2006/01/17 沢田(SRA) 仕様変更No.061
Revesion History : 2006/05/22 肖志偉(中訊) 仕様変更 モデル063 065　@
Revesion History : 2006/09/12 張騰宇(中訊) 仕様変更モデル070
Revesion History : 2007/02/26 金傑(中訊) 仕様変更モデル073
Revesion History : 2007/03/13 金傑(中訊) 仕様変更モデル075
Revesion History : 2007/03/16 金傑(中訊) 仕様変更モデル076
Revesion History : 2007/04/19 金傑(中訊) 仕様変更モデル077
Revision History : 2007/07/13 謝旋(中訊) 仕様変更モデル083
Revision History : 2007/12/07 孟亞南(中訊) 仕様変更モデル095,096
Revision History : 2008/01/08 孟亞南(中訊) 仕様変更モデル097
Revision History : 2008/04/15 武波(中訊) 仕様変更モデル103
Revision History : 2008/05/14 馮海濤(中訊) 仕様変更モデル104
Revision History : 2008/10/06 柴双紅(中訊) 仕様変更モデル106、モデル111
*/
package webbroker3.pvinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductPK;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AdditionalDepositStopDef;
import webbroker3.common.define.WEB3CategCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OnlyMobileOpenDivDef;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3ValidDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductDao;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.pvinfo.data.BrowseHistoryDao;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.BrowseHistoryRow;
import webbroker3.pvinfo.data.DisplayConditionParams;
import webbroker3.pvinfo.data.DisplayConditionRow;
import webbroker3.pvinfo.data.DisplayContentsDao;
import webbroker3.pvinfo.data.DisplayContentsPK;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.data.DisplayContentsRow;
import webbroker3.pvinfo.define.WEB3PvInfoProductDivDef;
import webbroker3.pvinfo.define.WEB3PvInfoTPBizDateCheckNumDef;
import webbroker3.pvinfo.define.WEB3PvInfoTradingPowerInfoDef;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginRow;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬImpl)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝのDB I/Oなどを管理するクラス。(実装クラス)<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoDataManagerImpl implements WEB3PvInfoDataManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoDataManagerImpl.class);

    /**
     * (get注文件数)<BR>
     * 引数の商品区分の注文件数を取得する。<BR>
     * <BR>
     * １）パラメータ.商品区分により、処理対象のテーブル名を決定する。<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("0：現物" or "1：信用")の場合]<BR>
     * 　@　@テーブル名 = 株式注文単位テーブル(eqtype_order_unit)<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合]<BR>
     * 　@　@テーブル名 = 先物OP注文単位テーブル(ifo_order_unit)<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("4：外国株")の場合]<BR>
     * 　@　@テーブル名 = 外株注文単位テーブル(feq_order_unit)<BR>
     * <BR>
     * ２）パラメータ.is当日注文により、当日/翌日注文を取得する条件を<BR>
     * 　@　@パラメータ.検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@[パラメータ.is当日注文 == trueの場合]<BR>
     * 　@　@　@パラメータ.検索条件文字列 += " and biz_date = ?"<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@パラメータ.検索条件文字列 += " and biz_date > ?"<BR>
     * <BR>
     * ３）業務日付(*1)を文字列変換(フォーマット：yyyyMMdd)し、<BR>
     * 　@パラメータ.検索条件データコンテナに追加する。<BR>
     * <BR>
     * ４）商品を判別する条件をパラメータ.検索条件文字列&<BR>
     * 　@　@パラメータ.検索条件データコンテナに追加する。<BR>
     * <BR>
     * 　@パラメータ.商品区分が、<BR>
     * 　@["0：現物"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and order_categ = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and order_type not in (?, ?)"<BR>
     * 　@　@・パラメータ.検索条件データコンテナに以下の値を追加。<BR>
     * 　@　@　@　@・"1：現物注文"<BR>
     * 　@　@　@　@・"101：株式ミニ投資買注文"<BR>
     * 　@　@　@　@・"102：株式ミニ投資売注文"<BR>
     * <BR>
     * 　@["1：信用"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and order_categ != ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and order_type not in (?, ?)"<BR>
     * 　@　@・パラメータ.検索条件データコンテナに以下の値を追加。<BR>
     * 　@　@　@　@・"1：現物注文"<BR>
     * 　@　@　@　@・"101：株式ミニ投資買注文"<BR>
     * 　@　@　@　@・"102：株式ミニ投資売注文"<BR>
     * <BR>
     * 　@["2：先物"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and future_option_div = ? "<BR>
     * 　@　@・パラメータ.検索条件データコンテナに"1：先物"(先物／オプション区分)を<BR>
     * 追加。<BR>
     * <BR>
     * 　@["3：オプション"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and future_option_div = ? "<BR>
     * 　@　@・パラメータ.検索条件データコンテナに"2：オプション"(先物／オプション区分)<BR>
     * を追加。<BR>
     * <BR>
     * 　@["4：外国株"の場合]<BR>
     * 　@　@・追加条件なし<BR>
     * <BR>
     * ５）QueryProcessor.doGetCountQuery()にて、注文件数を取得する。<BR>
     * <BR>
     * 　@[doGetCountQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@１）にて取得したテーブル名<BR>
     * 　@arg1：　@上記条件を追加したパラメータ.検索条件文字列<BR>
     * 　@arg2：　@上記条件を追加したパラメータ.検索条件データコンテナ<BR>
     * <BR>
     * ６）取得した件数を返却する。<BR>
     * <BR>
     * (*1)業務日付<BR>
     * 　@GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付<BR>
     * @@param l_strProductDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 0：　@現物 <BR>
     * 1：　@信用 <BR>
     * 2：　@先物 <BR>
     * 3：　@オプション <BR>
     * 4：　@外国株 <BR>
     * <BR>
     * @@param l_isTodayOrder - (is当日注文)<BR>
     * 当日注文を取得するかどうかのフラグ<BR>
     * <BR>
     * false：　@翌日注文<BR>
     * true：　@当日注文<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@return int
     * @@roseuid 4141331503A3
     */
    public int getOrderCnt(String l_strProductDiv, boolean l_isTodayOrder, String l_strQueryString, String[] l_strQueryDataContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderCnt(String, boolean, String, String[])";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.商品区分をチェックする。
        if(!WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            log.error("商品区分が不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "商品区分が不正");
        }
        
        //パラメータ.検索条件文字列をチェックする。
        if (l_strQueryString == null)
        {
            log.error("パラメータ.検索条件文字列Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.検索条件文字列Null出来ない。");
        }
        
        //パラメータ.検索条件データコンテナをチェックする。
        if(l_strQueryDataContainers == null)
        {
            log.error("パラメータ.検索条件データコンテナNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.検索条件データコンテナ出来ない。");
        }
                
        log.debug("商品区分: " + l_strProductDiv);
        log.debug("is当日注文: " + l_isTodayOrder);
        log.debug("検索条件文字列: " + l_strQueryString);
        StringBuffer l_sbQueryString = new StringBuffer(l_strQueryString);
        
        List l_lisQueryVars = new ArrayList();
        for(int i = 0; i < l_strQueryDataContainers.length; i++)
        {
            l_lisQueryVars.add(l_strQueryDataContainers[i]);
        } 
        log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());
        
        //１）パラメータ.商品区分により、処理対象のテーブル名を決定する。
        RowType l_rowType = null;  
        //[パラメータ.商品区分 == ("0：現物" or "1：信用")の場合 
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) || 
            WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_rowType = EqtypeOrderUnitRow.TYPE;
        }
        //[パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合]
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) || 
            WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_rowType = IfoOrderUnitRow.TYPE;
        }
        //[パラメータ.商品区分 == ("4：外国株")の場合]
        else if (WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            l_rowType = FeqOrderUnitRow.TYPE;
        }
        
        //２）パラメータ.is当日注文により、当日/翌日注文を取得する条件をパラメータ.検索条件文字列に追加する。
        //[パラメータ.is当日注文 == trueの場合]
        if(l_isTodayOrder)
        {
            l_sbQueryString.append(" and biz_date = ? ");
        }
        //[上記以外の場合]
        else
        {
            l_sbQueryString.append(" and biz_date > ? ");
        }
        
        //３）業務日付(*1)を文字列変換(フォーマット：yyyyMMdd)し、パラメータ.検索条件データコンテナに追加する。
        //業務日付取得 GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //フォーマット：yyyyMMdd
        l_datBizDate = WEB3DateUtility.toDay(l_datBizDate);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");

        //パラメータ.検索条件データコンテナに追加
        l_lisQueryVars.add(l_strBizDate);

        //４）商品を判別する条件をパラメータ.検索条件文字列&パラメータ.検索条件データコンテナに追加する。
        //    パラメータ.商品区分が、
        //["0：現物"の場合]
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv))
        {      
            l_sbQueryString.append(" and order_categ = ? and order_type not in (?, ?) ");
            
            //検索条件データコンテナに追加
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        //["1：信用"の場合]
        else if (WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and order_categ != ? and order_type not in (?, ?) ");
            
            //検索条件データコンテナに追加
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }     
        //["2：先物"の場合]
        else if(WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //検索条件データコンテナに"1：先物"(先物／オプション区分)を追加。
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.FUTURES);
            
        }
        //["3：オプション"の場合]
        else if(WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //検索条件データコンテナに"2：オプション"(先物／オプション区分)を追加。
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.OPTION);
        }
        //["4：外国株"の場合]
        else if(WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {

        }
        
        int l_intReturnRecordCnt = 0;
        //５）QueryProcessor.doGetCountQuery()にて、注文件数を取得する。
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);
            
            log.debug("検索条件文字列: " + l_sbQueryString.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());

            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(l_rowType,
                l_sbQueryString.toString(),
                l_objVars);

            log.debug("QueryProcessor.doGetCountQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //６）取得した件数を返却する。
        log.exiting(STR_METHOD_NAME);
        log.debug("注文件数: " + l_intReturnRecordCnt);
        return l_intReturnRecordCnt;
    }

    /**
     * (get約定件数)<BR>
     * 引数の商品区分の当日約定件数を取得する。<BR>
     * <BR>
     * １）パラメータ.商品区分により、処理対象のテーブル名を決定する。<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("0：現物" or "1：信用")の場合]<BR>
     * 　@　@テーブル名 = 株式注文単位テーブル(eqtype_order_unit)<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合]<BR>
     * 　@　@テーブル名 = 先物OP注文単位テーブル(ifo_order_unit)<BR>
     * <BR>
     * 　@[パラメータ.商品区分 == ("4：外国株")の場合]<BR>
     * 　@　@テーブル名 = 外株注文単位テーブル(feq_order_unit)<BR>
     * <BR>
     * ２）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@口座ID = パラメータ.顧客.getAccountId()　@かつ<BR>
     * 　@　@発注日 = 業務日付(*1)　@かつ<BR>
     * 　@　@約定数量 IS NOT null　@かつ<BR>
     * 　@　@約定数量 != 0<BR>
     * <BR>
     * 　@２－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "account_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ " biz_date = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "executed_quantity is not null and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "executed_quantity != to_number(?)) "<BR>
     * <BR>
     * 　@２－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@　@・パラメータ.顧客.getAccountId()<BR>
     * 　@　@　@・業務日付　@※文字列変換(フォーマット：yyyyMMdd)し、セット<BR>
     * 　@　@　@・null<BR>
     * 　@　@　@・"0"<BR>
     * <BR>
     * ３）商品を判別する条件を検索条件文字列&ArrayListに追加する。<BR>
     * <BR>
     * 　@パラメータ.商品区分が、<BR>
     * 　@["0：現物"の場合]<BR>
     * 　@　@・検索条件文字列 += "and order_categ = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and order_type not in (?, ?)"<BR>
     * 　@　@・ArrayListに以下の値を追加。<BR>
     * 　@　@　@　@・"1：現物注文"<BR>
     * 　@　@　@　@・"101：株式ミニ投資買注文"<BR>
     * 　@　@　@　@・"102：株式ミニ投資売注文"<BR>
     * <BR>
     * 　@["1：信用"の場合]<BR>
     * 　@　@・検索条件文字列 += "and order_categ != ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and order_type not in (?, ?)"<BR>
     * 　@　@・ArrayListに以下の値を追加。<BR>
     * 　@　@　@　@・"1：現物注文"<BR>
     * 　@　@　@　@・"101：株式ミニ投資買注文"<BR>
     * 　@　@　@　@・"102：株式ミニ投資売注文"<BR>
     * <BR>
     * 　@["2：先物"の場合]<BR>
     * 　@　@・検索条件文字列 += "and future_option_div = ? "<BR>
     * 　@　@・ArrayListに"1：先物"(先物／オプション区分)を追加。<BR>
     * <BR>
     * 　@["3：オプション"の場合]<BR>
     * 　@　@・検索条件文字列 += "and future_option_div = ? "<BR>
     * 　@　@・ArrayListに"2：オプション"(先物／オプション区分)を追加。<BR>
     * <BR>
     * 　@["4：外国株"の場合]<BR>
     * 　@　@・追加条件なし<BR>
     * <BR>
     * ４）QueryProcessor.doGetCountQuery()にて、当日約定件数を取得する。<BR>
     * <BR>
     * 　@[doGetCountQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@１）にて取得したテーブル名<BR>
     * 　@arg1：　@作成した検索条件文字列<BR>
     * 　@arg2：　@作成したArrayList.toArray()<BR>
     * <BR>
     * ５）取得した件数を返却する。<BR>
     * <BR>
     * (*1)業務日付<BR>
     * 　@GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strProductDiv - (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 0：　@現物 <BR>
     * 1：　@信用 <BR>
     * 2：　@先物 <BR>
     * 3：　@オプション <BR>
     * 4：　@外国株 <BR>
     * 
     * @@return int
     * @@roseuid 414152F00384
     */
    public int getExecuteCnt(WEB3GentradeMainAccount l_mainAccount, String l_strProductDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExecuteCnt(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }

        //パラメータ.商品区分をチェックする。
        if(!WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv) &&
            !WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            log.error("商品区分が不正");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "商品区分が不正");
        }
        
        log.debug("商品区分: " + l_strProductDiv);
        //１）パラメータ.商品区分により、処理対象のテーブル名を決定する。
        RowType l_rowType = null;
        //[パラメータ.商品区分 == ("0：現物" or "1：信用")の場合]
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv) ||
            WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_rowType = EqtypeOrderUnitRow.TYPE;
        }
        //[パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合]
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv) ||
            WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_rowType = IfoOrderUnitRow.TYPE;
        }
        //[パラメータ.商品区分 == ("4：外国株")の場合]
        else if (WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {
            l_rowType = FeqOrderUnitRow.TYPE;
        }

        //２）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        l_sbQueryString.append("account_id = ? and ");
        l_sbQueryString.append(" biz_date = ? and ");
        l_sbQueryString.append("executed_quantity is not null and executed_quantity <> to_number(?)");
        

        l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
        //業務日付取得 GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //フォーマット：yyyyMMdd
        l_datBizDate = WEB3DateUtility.toDay(l_datBizDate);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        l_lisQueryVars.add(l_strBizDate);
        l_lisQueryVars.add("0");
        
        //３）商品を判別する条件を検索条件文字列&ArrayListに追加する。
        //パラメータ.商品区分が、
        //["0：現物"の場合]
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and order_categ = ? and order_type not in (?, ?) ");
            
            //検索条件データコンテナに追加
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        //["1：信用"の場合]
        else if (WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and order_categ != ? and order_type not in (?, ?) ");
            
            //検索条件データコンテナに追加
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }     
        //["2：先物"の場合]
        else if(WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //検索条件データコンテナに"1：先物"(先物／オプション区分)を追加。
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.FUTURES);
            
        }
        //["3：オプション"の場合]
        else if(WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDiv))
        {
            l_sbQueryString.append(" and future_option_div = ? ");
            
            //検索条件データコンテナに"2：オプション"(先物／オプション区分)を追加。
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.OPTION);
        }
        //["4：外国株"の場合]
        else if(WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY.equals(l_strProductDiv))
        {

        }
            
        //４）QueryProcessor.doGetCountQuery()にて、当日約定件数を取得する。
        int l_intReturnRecordCnt = 0;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);

            log.debug("検索条件文字列: " + l_sbQueryString.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());

            l_intReturnRecordCnt = l_queryProcessor.doGetCountQuery(
                l_rowType,
                l_sbQueryString.toString(),
                l_objVars);

            log.debug("QueryProcessor.doGetCountQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //５）取得した件数を返却する。
        log.exiting(STR_METHOD_NAME);
        log.debug("約定件数: " + l_intReturnRecordCnt);
        return l_intReturnRecordCnt;
    }

    /**
     * (get表示内容Params)<BR>
     * 引数の表示内容IDに該当する表示内容Paramsを<BR>
     * 表示内容テーブルから取得する。<BR>
     * <BR>
     * １）以下の条件に該当する表示内容Paramsを取得する。<BR>
     * 　@[条件]<BR>
     * 　@　@表示内容ID = パラメータ.表示内容ID<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）１）の結果を返却する。<BR>
     * @@param l_lngDisplayContentsId - (表示内容ID)<BR>
     * 表示内容ID<BR>
     * @@return webbroker3.pvinfo.data.DisplayContentsParams
     * @@roseuid 4147A21F0069
     */
    public DisplayContentsParams getDisplayContentsParams(long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDisplayContentsParams(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("表示内容ID: " + l_lngDisplayContentsId);
        DisplayContentsRow l_dcRow = null;
        try
        {
            //１）引数の表示内容IDに該当する表示内容Paramsを表示内容テーブルから取得する。
            DisplayContentsPK l_dcPk = new DisplayContentsPK(l_lngDisplayContentsId);
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_dcRow = (DisplayContentsRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_dcPk);
            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()を執行します");
        }
        //null value return
        catch(DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        //２）の結果を返却する。
        if (l_dcRow == null)
        {
            log.debug("表示内容Params is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return (DisplayContentsParams)l_dcRow;
    }

    /**
     * (get表示内容Params一覧)<BR>
     * 条件に該当する表示内容Paramsの一覧を<BR>
     * 表示内容テーブルから取得する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@”表示内容テーブル(display_contents)”<BR>
     * 　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@arg2：　@パラメータ.ソート条件<BR>
     * 　@arg3：　@null<BR>
     * 　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）１）の結果を返却する。<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@roseuid 4145092300D9
     */
    public List getDisplayContentsParamsList(
        String l_strQueryString,
        String[] l_strQueryDataContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDisplayContentsParamsList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.検索条件文字列をチェックする。
        if (l_strQueryString == null)
        {
            log.error("パラメータ.検索条件文字列Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.検索条件文字列Null出来ない。");
        }
        
        //パラメータ.検索条件データコンテナをチェックする。
        if(l_strQueryDataContainers == null)
        {
            log.error("パラメータ.検索条件データコンテナNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.検索条件データコンテナ出来ない。");
        }
        
        log.debug("検索条件文字列: " + l_strQueryString);
        log.debug("ソート条件: " + l_strSortCond);
        List l_lisDisplayContentsParams = null;
        try
        {
            //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDisplayContentsParams = l_queryProcessor.doFindAllQuery(
                DisplayContentsParams.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainers);

            log.debug("QueryProcessor.doFindAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisDisplayContentsParams.isEmpty())
        {
            log.debug("表示内容テーブルに該当データなし");
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisDisplayContentsParams;
    }

    /**
     * (insert表示内容)<BR>
     * 表示内容テーブルに新規データを一行登録する。<BR>
     * <BR>
     * １）表示内容Paramsを生成する。<BR>
     * <BR>
     * ２）生成した表示内容Paramsに対し、以下のプロパティセットを行う。<BR>
     * <BR>
     * 　@表示内容ID	= パラメータ.表示内容情報の同名項目<BR>
     * 　@証券会社コード	= 管理者.get証券会社コード()<BR>
     * 　@部店コード	= 管理者.get部店コード()<BR>
     * 　@表示条件番号	= パラメータ.表示内容情報の同名項目<BR>
     * 　@優先区分	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示期間From	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示期間To	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示タイトル	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示文章	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示色		= パラメータ.表示内容情報の同名項目<BR>
     * 　@点滅表示フラグ	= パラメータ.表示内容情報の同名項目<BR>
     * 　@URL指定		= パラメータ.表示内容情報の同名項目<BR>
     * 　@最終更新日時表示フラグ	= パラメータ.表示内容情報の同名項目<BR>
     * 　@有効/無効フラグ	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示媒体	= パラメータ.表示内容情報の同名項目<BR>
     * 　@最終更新者	= パラメータ.表示内容情報の同名項目<BR>
     * 　@作成日付	= 現在時刻(*1)<BR>
     * 　@更新日付	= 現在時刻<BR>
     * <BR>
     * ３）QueryProcessor.doInsertQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doInsertQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@２）にてプロパティセットした表示内容Params<BR>
     * <BR>
     * (*1)現在時刻<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理<BR>
     * TIMESTAMP_TAG)<BR>
     * 　@にて取得した現在時刻<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@param l_displayContentsInfo - (表示内容情報)<BR>
     * 表示内容情報オブジェクト<BR>
     * @@roseuid 415BF49B0047
     */
    public void insertDisplayContents(
        WEB3Administrator l_administrator,
        WEB3PvInfoDisplayContentsUnit l_displayContentsInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertDisplayContents(WEB3Administrator, WEB3PvInfoDisplayContentsUnit)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.管理者をチェックする。
        if(l_administrator == null)
        {
            log.error("パラメータ.管理者Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.管理者Null出来ない。");
        }
        
        //パラメータ.表示内容情報をチェックする。
        if(l_displayContentsInfo == null)
        {
            log.error("パラメータ.表示内容情報Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.表示内容情報Null出来ない。");
        }

        //１）表示内容Paramsを生成する。
        DisplayContentsParams l_dcParams = new DisplayContentsParams();

        //２）生成した表示内容Paramsに対し、プロパティセットを行う。
        //表示内容ID    = パラメータ.表示内容情報の同名項目
        l_dcParams.setDisplayContentsId(Long.parseLong(l_displayContentsInfo.displayContentsId));
        log.debug("表示内容ID: " + l_dcParams.getDisplayContentsId());
        //証券会社コード   = 管理者.get証券会社コード()
        l_dcParams.setInstitutionCode(l_administrator.getInstitutionCode());
        log.debug("証券会社コード: " + l_dcParams.getInstitutionCode());
        //部店コード   = 管理者.get部店コード()
        l_dcParams.setBranchCode(l_administrator.getBranchCode());
        log.debug("部店コード: " + l_dcParams.getBranchCode());
        //表示条件番号    = パラメータ.表示内容情報の同名項目
        l_dcParams.setConditionNo(l_displayContentsInfo.conditionNumber);
        log.debug("表示条件番号: " + l_dcParams.getConditionNo());
        //優先区分    = パラメータ.表示内容情報の同名項目
        l_dcParams.setPriorityDiv(l_displayContentsInfo.priorityDiv);
        log.debug("優先区分: " + l_dcParams.getPriorityDiv());
        //表示期間From  = パラメータ.表示内容情報の同名項目
        l_dcParams.setTermFrom(WEB3DateUtility.getDate(l_displayContentsInfo.listStartDate, "yyyyMMddHHmm"));
        log.debug("表示期間From: " + l_dcParams.getTermFrom());
        //表示期間To    = パラメータ.表示内容情報の同名項目
        l_dcParams.setTermTo(WEB3DateUtility.getDate(l_displayContentsInfo.listEndDate, "yyyyMMddHHmm"));
        log.debug("表示期間To: " + l_dcParams.getTermTo());
        //表示タイトル    = パラメータ.表示内容情報の同名項目
        l_dcParams.setDisplayTitle(l_displayContentsInfo.displayTitle);
        log.debug("表示タイトル: " + l_dcParams.getDisplayTitle());
        //表示文章  = パラメータ.表示内容情報の同名項目
        l_dcParams.setDisplayMessage(l_displayContentsInfo.displayMessage);
        log.debug("表示文章: " + l_dcParams.getDisplayMessage());
        //表示色       = パラメータ.表示内容情報の同名項目
        l_dcParams.setDisplayColor(l_displayContentsInfo.displayColor);
        log.debug("表示色: " + l_dcParams.getDisplayColor());
        //点滅表示フラグ   = パラメータ.表示内容情報の同名項目
        l_dcParams.setBlinkDisplayFlag(l_displayContentsInfo.blinkDisplayFlag ?
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES : 
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_NO);
        log.debug("点滅表示フラグ: " + l_dcParams.getBlinkDisplayFlag());
        //URL指定     = パラメータ.表示内容情報の同名項目
        l_dcParams.setDisplayUrl(l_displayContentsInfo.displayUrl);
        log.debug("URL指定: " + l_dcParams.getDisplayUrl());
        //最終更新日時表示フラグ   = パラメータ.表示内容情報の同名項目
        l_dcParams.setLastUpdateTimeDisplayFlag(l_displayContentsInfo.lastUpdateTimeDisplayFlag ?
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_NO :
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES);
        log.debug("最終更新日時表示フラグ: " + l_dcParams.getLastUpdateTimeDisplayFlag());
        //有効/無効フラグ  = パラメータ.表示内容情報の同名項目
        l_dcParams.setEffectiveFlag(l_displayContentsInfo.effectiveFlag ?
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_NO :
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);
        log.debug("有効/無効フラグ: " + l_dcParams.getEffectiveFlag());
        //表示媒体  = パラメータ.表示内容情報の同名項目
        l_dcParams.setDisplayDevice(l_displayContentsInfo.displayDevice);
        log.debug("表示媒体: " + l_dcParams.getDisplayDevice());
        //最終更新者 = パラメータ.表示内容情報の同名項目
        l_dcParams.setLastUpdateMember(l_displayContentsInfo.lastUpdateMember);
        log.debug("最終更新者: " + l_dcParams.getLastUpdateMember());
                
        Timestamp l_tsCurrent = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        //作成日付  = 現在時刻
        l_dcParams.setCreatedTimestamp(l_tsCurrent);
        log.debug("作成日付: " + l_dcParams.getCreatedTimestamp());
        //更新日付  = 現在時刻
        l_dcParams.setLastUpdatedTimestamp(l_tsCurrent);
        log.debug("更新日付: " + l_dcParams.getLastUpdatedTimestamp());
        
        //３）QueryProcessor.doInsertQuery()メソッドをコールする。
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_dcParams);
            
            log.debug("QueryProcessor.doInsertQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update表示内容)<BR>
     * 引数の表示内容Paramsで表示内容テーブルを更新する。<BR>
     * <BR>
     * １）QueryProcessor.doUpdateQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"表示内容テーブル(display_contents)"<BR>
     * 　@　@arg1：　@パラメータ.表示内容Params<BR>
     * @@param l_displayContentsParams - (表示内容Params)<BR>
     * 表示内容Paramsオブジェクト<BR>
     * @@roseuid 415BF49B0076
     */
    public void updateDisplayContents(DisplayContentsParams l_displayContentsParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateDisplayContents(DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.表示内容Paramsをチェックする。
        if(l_displayContentsParams == null)
        {
            log.error("パラメータ.表示内容ParamsNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.表示内容ParamsNull出来ない。");
        }
        
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_displayContentsParams);

            log.debug("QueryProcessor.doUpdateQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete表示内容)<BR>
     * 引数の表示内容IDに該当する表示内容テーブルのデータを削除する。<BR>
     * <BR>
     * １）表示内容テーブルPKインスタンスを生成する。<BR>
     *  ※表示内容テーブルPKクラスはDDLより自動生成する。 <BR>
     * <BR>
     * 　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@表示内容ID：　@パラメータ.表示内容ID<BR>
     * <BR>
     * ２）QueryProcessor.doDeleteQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doDeleteQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@１）にて作成した表示内容テーブルPK<BR>
     * @@param l_lngDisplayContentsId - (表示内容ID)<BR>
     * 表示内容ID<BR>
     * @@roseuid 415D3043036B
     */
    public void deleteDisplayContents(long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteDisplayContents(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("表示内容ID: " + l_lngDisplayContentsId);
        //１）表示内容テーブルPKインスタンスを生成する。
        DisplayContentsPK l_dcPK = new DisplayContentsPK(l_lngDisplayContentsId);
        
        //２）QueryProcessor.doDeleteQuery()メソッドをコールする。
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_dcPK);

            log.debug("QueryProcessor.doDeleteQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get新規表示内容ID)<BR>
     * 表示内容IDを新規採番し、返却する。<BR>
     * <BR>
     * １）表示内容テーブルDAO.newPkValue()メソッドをコールする。<BR>
     * 　@※表示内容テーブルDAOクラスはDDLより自動生成する。 <BR>
     * <BR>
     * ２）１）の結果を返却する。<BR>
     * @@return long
     * @@roseuid 415C0F010281
     */
    public long getNewDisplayContentsId() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewDisplayContentsId()";
        log.entering(STR_METHOD_NAME);
        
        //１）表示内容テーブルDAO.newPkValue()メソッドをコールする。
        long l_lngDisplayContentsId;
        try
        {
            l_lngDisplayContentsId = DisplayContentsDao.newPkValue();

            log.debug("DisplayContentsDao.newPkValue()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //２）１）の結果を返却する。
        log.exiting(STR_METHOD_NAME);
        log.debug("新規表示内容ID: " + l_lngDisplayContentsId);
        return l_lngDisplayContentsId;
    }

    /**
     * (get表示条件設定Params一覧)<BR>
     * 引数に該当する表示条件設定Paramsの一覧を取得する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " institution_code = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@　@・パラメータ.証券会社コード<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"表示条件設定テーブル(display_condition)"<BR>
     * 　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@arg2：　@作成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ３）２）の検索結果を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return List
     * @@roseuid 415BE56D02E2
     */
    public List getDisplayConditionParamsList(String l_strInstitutionCode, String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDisplayConditionParamsList(String, String)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.証券会社コードをチェックする。
        if(l_strInstitutionCode == null)
        {
            log.error("パラメータ.証券会社コードNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.証券会社コードNull出来ない。");
        }
    
        //パラメータ.部店コードをチェックする。
        //TODO:this parameter defined but never used.
//        if(l_strBranchCode == null)
//        {
//            log.error("パラメータ.部店コードNull出来ない。");
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80019,
//                this.getClass().getName() + STR_METHOD_NAME,
//                "パラメータ.部店コードNull出来ない。");
//        }
        
        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //[検索条件] 証券会社コード = パラメータ.証券会社コード
        l_sbQuery.append(" institution_code = ? ");
        l_lisQueryVars.add(l_strInstitutionCode);
        
        //２）QueryProcessor.doFindAllQuery()メソッドをコールする。
        List l_lisResultParams = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("検索条件文字列: " + l_sbQuery.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());
            
            l_lisResultParams =l_queryProcessor.doFindAllQuery(
                DisplayConditionRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisResultParams.isEmpty())
        {
            log.debug("該当データなし");
            return null;     
        }

        //３）２）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }

    /**
     * (create表示条件情報一覧)<BR>
     * ダイレクト指定を除く、表示条件情報の一覧を作成する。<BR>
     * <BR>
     * １）this.get表示条件設定Params一覧()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get表示条件設定Params一覧()にセットするパラメータ]<BR>
     * 　@　@証券会社コード：　@パラメータ.管理者.get証券会社コード()<BR>
     * 　@　@部店コード：　@null<BR>
     * <BR>
     * 　@　@get表示条件設定Params一覧()の戻り値 == nullの場合、<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * ２）ArrayListを生成する。<BR>
     * <BR>
     * ３）１）の戻り値の要素(=表示条件設定Params)数分以下の処理を<BR>
     * 　@繰り返す。<BR>
     * <BR>
     * 　@３－１）処理対象の表示条件設定Params.表示条件番号 != <BR>
     * 　@　@　@　@　@"0000：ダイレクト指定"の場合、以下の処理を行う。<BR>
     * 　@　@３－１－１）表示条件情報オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@３－１－２）表示条件情報オブジェクトに以下のプロパティをセットする。<BR>
     * 　@　@　@表示条件情報.表示条件番号 = 表示条件設定Params.表示条件番号<BR>
     * 　@　@　@表示条件情報.表示名 = 表示条件設定Params.表示条件名<BR>
     * <BR>
     * 　@　@３－１－３）生成したArrayListにプロパティセットした表示条件情報<BR>
     *       オブジェクトを追加する。<BR>
     * <BR>
     * ４）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit[]
     * @@roseuid 415BFC23009C
     */
    public WEB3PvInfoDisplayConditionUnit[] createDisplayConditionList(WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createDisplayConditionList(WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.管理者をチェックする。
        if(l_administrator == null)
        {
            log.error("パラメータ.管理者Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.管理者Null出来ない。");
        }

        //１）this.get表示条件設定Params一覧()をコールする。
        List l_lisDisplayContensParams;
        l_lisDisplayContensParams = getDisplayConditionParamsList(l_administrator.getInstitutionCode(), null);
        if (l_lisDisplayContensParams == null)
        {
            log.debug("this.get表示条件設定Params一覧() is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２）ArrayListを生成する。
        List l_lisDisplayConditionUnit = new ArrayList();
        
        //３）１）LOOP
        int l_intParamsCnt = l_lisDisplayContensParams.size();
        for(int i = 0; i < l_intParamsCnt; i ++)
        {
            
            DisplayConditionParams l_params = (DisplayConditionParams)l_lisDisplayContensParams.get(i);
            //３－１）処理対象の表示条件設定Params.表示条件番号 != "0000：ダイレクト指定"の場合、以下の処理を行う。
            if(!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_params.condition_no))
            {
                //３－１－１）表示条件情報オブジェクトを生成する。
                WEB3PvInfoDisplayConditionUnit l_dcUnit = new WEB3PvInfoDisplayConditionUnit();
                
                //３－１－２）表示条件情報オブジェクトに以下のプロパティをセットする。
                l_dcUnit.conditionName = l_params.condition_name;
                l_dcUnit.conditionNumber = l_params.condition_no;
                
                //３－１－３）生成したArrayListにプロパティセットした表示条件情報オブジェクトを追加する。
                l_lisDisplayConditionUnit.add(l_dcUnit);
            }
        }
        
        //４）生成したArrayList.toArray()の戻り値を返却する。
        WEB3PvInfoDisplayConditionUnit[] l_dcUnits = null;
        
        if (l_intParamsCnt > 0)
        {
            l_dcUnits = new WEB3PvInfoDisplayConditionUnit[l_lisDisplayConditionUnit.size()];
            l_lisDisplayConditionUnit.toArray(l_dcUnits); 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dcUnits;
    }

    /**
     * (get閲覧履歴Params)<BR>
     * 閲覧履歴Paramsを取得する。<BR>
     * <BR>
     * １）閲覧履歴テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = パラメータ.顧客.証券会社コード<BR>
     * 　@部店コード = パラメータ.顧客.部店コード<BR>
     * 　@表示内容ID = パラメータ.表示内容ID<BR>
     * 　@顧客コード = パラメータ.顧客.getAccountCode()<BR>
     * 　@※顧客コードは先頭6byteで比較すること。<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ２）１）の検索結果を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_lngDisplayContentsId - (表示内容ID)<BR>
     * 表示内容ID<BR>
     * @@return webbroker3.pvinfo.data.BrowseHistoryParams
     * @@roseuid 414523FA032B
     */
    public BrowseHistoryParams getBrowseHistoryParams(
        WEB3GentradeMainAccount l_mainAccount,
        long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBrowseHistoryParams(WEB3GentradeMainAccount, long)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }
        
        log.debug("表示内容ID: " + l_lngDisplayContentsId);
        
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //※顧客コードは先頭6byteで比較すること。
        String l_strAccountCode = l_mainAccount.getAccountCode().substring(0, 6);
        
        //検索結果が取得できなかった場合はnullを返却する。
        BrowseHistoryRow l_bhRow = null;
        try
        {
            l_bhRow = BrowseHistoryDao.findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode(
                l_strInstitutionCode,
                l_strBranchCode,
                l_lngDisplayContentsId,
                l_strAccountCode
                );

            log.debug("BrowseHistoryDao.findRowByInstitutionCodeBranchCodeDisplayContentsIdAccountCode()を執行します");            
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //２）１）の検索結果を返却する。
        if (l_bhRow == null)
        {
            log.debug("get閲覧履歴Params is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return (BrowseHistoryParams) l_bhRow;
    }

    /**
     * (get閲覧履歴Params一覧)<BR>
     * 条件に該当する閲覧履歴Paramsの一覧を<BR>
     * 表示内容テーブルから取得する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@”閲覧履歴テーブル(browse_history)”<BR>
     * 　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@arg2：　@パラメータ.ソート条件<BR>
     * 　@arg3：　@null<BR>
     * 　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ２）１）の結果を返却する。<BR>
     * @@param l_strSQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return List
     * @@roseuid 415CBDF403C1
     */
    public List getBrowseHistoryParamsList(
        String l_strQueryString,
        String[] l_strQueryDataContainers,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBrowseHistoryParamsList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.検索条件文字列をチェックする。
        if (l_strQueryString == null)
        {
            log.error("パラメータ.検索条件文字列Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.検索条件文字列Null出来ない。");
        }
        
        //パラメータ.検索条件データコンテナをチェックする。
        if(l_strQueryDataContainers == null)
        {
            log.error("パラメータ.検索条件データコンテナNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.検索条件データコンテナ出来ない。");
        }
        
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        List l_lisResultParams = null;
        try
        {        
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("検索条件文字列: " + l_strQueryString);
            log.debug("ソート条件: " + l_strSortCond);
            
            l_lisResultParams = l_queryProcessor.doFindAllQuery(
                BrowseHistoryRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainers);

            log.debug("QueryProcessor.doFindAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisResultParams.isEmpty())
        {
            log.debug("該当データなし");
            return null;
        }

        //２）１）の結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }
    /**
     * (get閲覧履歴Params一覧)<BR>
     * 引数の条件に該当する閲覧履歴Paramsの一覧を取得する。 <BR>
     * １）以下の検索条件文字列を作成する。 <BR>
     * 検索条件文字列 = "institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and display_contents_id = ? " <BR>
     * <BR>
     * ２）パラメータ.部店コード != nullの場合、 <BR>
     * 部店条件を検索条件文字列に追加する。 <BR>
     * パラメータ.部店コードの要素数分"?"を追加する。 <BR>
     * 検索条件文字列 += "and branch_code in (?,?,,,) " <BR>
     * <BR>
     * ３）パラメータ.顧客コード != nullの場合、 <BR>
     * 顧客条件を検索条件文字列に追加する。 <BR>
     * 検索条件文字列 += " and account_code like '?%'" <BR>
     * <BR>
     * ４）検索条件の"?"にセットする検索条件データコンテナ(：String[])を作成する。 <BR>
     * 検索条件データコンテナの要素は、以下の順序でセットすること。 <BR>
     * ①@パラメータ.証券会社コード <BR>
     * ②パラメータ.表示内容ID <BR>
     * ③パラメータ.部店コードの全要素 <BR>
     * ④パラメータ.顧客コード <BR>
     * ※③、④については、対応するパラメータ != nullの場合のみセット。 <BR>
     * ※④については、パラメータ.顧客コード.length() == 7byteの場合、先頭の6byteのみをセット。 <BR>
     * <BR>
     * ５）this.get閲覧履歴Params一覧()メソッドをコールする。 <BR>
     * [get閲覧履歴Params一覧()にセットするパラメータ] <BR>
     * 検索条件文字列：　@作成した検索条件文字列 <BR>
     * 検索条件データコンテナ：　@作成した検索条件データコンテナ <BR>
     * ソート条件：　@null <BR>
     * 検索結果が取得できなかった場合はnullを返却する。 <BR>
     * <BR>
     * ６）５）の検索結果を返却する。 <BR>
     * @@param l_strInstitutionCode<BR>
     * @@param l_strBranchCode<BR>
     * @@param l_strAccountCode<BR>
     * @@param l_lngDisplayContentsId<BR>
     * @@return List
     * @@throws WEB3BaseException<BR>
     */
    public List getBrowseHistoryParamsList(String l_strInstitutionCode,String[] l_strBranchCode,String l_strAccountCode,long l_lngDisplayContentsId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBrowseHistoryParamsList(String, String[], String,long)";
        log.entering(STR_METHOD_NAME);
        if (l_strInstitutionCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //１）空の文字列を生成する。<BR>
        StringBuffer l_sbQueryString = new StringBuffer();
        //１）以下の検索条件文字列を作成する。

        l_sbQueryString.append("institution_code = ? and display_contents_id = ?");
        //２）パラメータ.部店コード != nullの場合、
        if (l_strBranchCode != null)
        {
            if (l_strBranchCode.length == 1)
            {
                l_sbQueryString.append(" and branch_code = ?");
            }
            
            if (l_strBranchCode.length > 1)
            {
                l_sbQueryString.append(" and (branch_code = ?");

                for (int i = 1; i < l_strBranchCode.length; i++)
                {
                    l_sbQueryString.append(" or branch_code = ?");
                }

                l_sbQueryString.append(")");
            }
        }
        
        //３）パラメータ.顧客コード != nullの場合
        if (l_strAccountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%'");
        }
        
        //４）検索条件の"?"にセットする検索条件データコンテナ(：String[])を作成する。
        //検索条件データコンテナの要素は、以下の順序でセットすること。 
        //空のArrayListを生成する。<BR>
        List l_lstQueryContainer = new ArrayList();
        //①@パラメータ.証券会社コード
        l_lstQueryContainer.add(l_strInstitutionCode);
        //パラメータ.表示内容ID 
        l_lstQueryContainer.add(Long.toString(l_lngDisplayContentsId));
        //   引数.部店コードの各要素 を１）のListに追加する。<BR>
        //③パラメータ.部店コードの全要素 

        if (l_strBranchCode != null)
        {
            if (l_strBranchCode.length > 0)
            {
                for (int i = 0; i < l_strBranchCode.length; i++)
                {
                    l_lstQueryContainer.add(l_strBranchCode[i]);
                }
            }
        }

        //④パラメータ.顧客コード 
        if (l_strAccountCode !=null)
        {
            int l_intAcountCode = WEB3StringTypeUtility.getByteLength(l_strAccountCode);
            if (l_intAcountCode == 7)
            {
                l_lstQueryContainer.add(l_strAccountCode.substring(0,6));
            }
            else
            {

                l_lstQueryContainer.add(l_strAccountCode);
            }
        }

        
        //生成されたListから配列を取得し、返却する。
        String[] l_queryContainer = new String[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);
        //５）this.get閲覧履歴Params一覧()メソッドをコールする。
        List l_listParamsList = this.getBrowseHistoryParamsList(l_sbQueryString.toString(),l_queryContainer,null);
        return l_listParamsList;
    }
    /**
     * (insert閲覧履歴)<BR>
     * 閲覧履歴テーブルにデータを一行登録する。<BR>
     * <BR>
     * １）閲覧履歴Paramsを生成する。<BR>
     * <BR>
     * ２）生成したParamsに対し、以下のプロパティセットを行う。<BR>
     * <BR>
     * 　@閲覧履歴ID = 新規採番したID(*1)<BR>
     * 　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@部店コード = パラメータ.部店コード<BR>
     * 　@表示内容ID = パラメータ.表示内容ID<BR>
     * 　@顧客コード = パラメータ.顧客コード<BR>
     * 　@未読既読フラグ = <BR>
     * 　@　@[パラメータ.is既読 == falseの場合]<BR>
     * 　@　@　@"0：未読"をセット。<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@"1：既読"をセット。<BR>
     * 　@削除フラグ = "0：未削除"<BR>
     *  最終閲覧日時 = <BR>
     *      [パラメータ.is既読 == falseの場合]<BR>
     *      nullをセット。<BR>
     *      [上記以外の場合]<BR>
     *      現在時刻(*2)をセット。<BR>
     *  作成日付 = 現在時刻<BR>
     *  更新日付 = 現在時刻 <BR>
     * <BR>
     * ３）QueryProcessor.doInsertQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doInsertQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@２）にてプロパティセットした閲覧履歴Params<BR>
     * <BR>
     * 　@(*1)　@閲覧履歴ＩＤ新規採番 <BR>
     * 　@閲覧履歴テーブルDAO.newPkValue()メソッドにて取得する。 <BR>
     * 　@※閲覧履歴テーブルDAOクラスはDDLより自動生成する。 <BR>
     * <BR>
     * 　@(*2)　@現在時刻<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 部店コード<BR>
     * @@param l_lngDisplayContentsId - (表示内容ID)<BR>
     * 表示内容ID<BR>
     * @@param l_isRead - (is既読)<BR>
     * 未読既読フラグを既読で登録するかどうかのフラグ<BR>
     * <BR>
     * false：　@未読で登録<BR>
     * true：　@既読で登録<BR>
     * @@roseuid 4160D8C4017D
     */
    public void insertBrowseHistory(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        long l_lngDisplayContentsId,
        boolean l_isRead) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " insertBrowseHistory(String, String, String, long, boolean)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.証券会社コードをチェックする。
        if(l_strInstitutionCode == null)
        {
            log.error("パラメータ.証券会社コードNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.証券会社コードNull出来ない。");
        }
    
        //パラメータ.部店コードをチェックする。
        if(l_strBranchCode == null)
        {
            log.error("パラメータ.部店コードNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.部店コードNull出来ない。");
        }
        
        //パラメータ.顧客コードをチェックする。
        if(l_strAccountCode == null)
        {
            log.error("パラメータ.顧客コードNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客コードNull出来ない。");
        }
        
// *** 未取込No.016 START            
        // 顧客を取得する。
        MainAccount l_mainAccount = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        l_mainAccount = l_accManager.getMainAccount(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strAccountCode);
        long l_lngAccountId = l_mainAccount.getAccountId();
// *** 未取込No.016 END            
        
        log.debug("証券会社コード: " + l_strInstitutionCode);
        log.debug("部店コード: " + l_strBranchCode);
        log.debug("顧客コード: " + l_strAccountCode);
        log.debug("表示内容ID: " + l_lngDisplayContentsId);
        log.debug("is既読: " + l_isRead);
// *** 未取込No.016 START            
        log.debug("口座ID: " + l_lngAccountId);
// *** 未取込No.016 END            

        //１）閲覧履歴Paramsを生成する。
        BrowseHistoryParams l_bhParams = new BrowseHistoryParams();
        
        //２）生成したParamsに対し、以下のプロパティセットを行う。
        try
        {
            l_bhParams.setBrowseHistoryId(BrowseHistoryDao.newPkValue());
            l_bhParams.setInstitutionCode(l_strInstitutionCode);
            l_bhParams.setBranchCode(l_strBranchCode);
            l_bhParams.setDisplayContentsId(l_lngDisplayContentsId);
            l_bhParams.setAccountCode(l_strAccountCode);
// *** 未取込No.016 START            
            l_bhParams.setAccountId(l_lngAccountId);
// *** 未取込No.016 END            
            l_bhParams.setReadFlag(l_isRead ? WEB3PvInfoReadFlagDef.READ_YES : WEB3PvInfoReadFlagDef.READ_NO);
            l_bhParams.setDeleteFlag(WEB3PvInfoDeleteFlagDef.DELETE_NO);
            log.debug("削除フラグ: " + l_bhParams.getDeleteFlag());
            //ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻
            Timestamp l_tsCurrent = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            //[パラメータ.is既読 == falseの場合]     
            if (l_isRead == false)
            {
                //最終閲覧日時 = nullをセット。
                l_bhParams.setLastReadTimestamp(null);
            }
            //[上記以外の場合] 現在時刻(*2)をセット。
            else
            {
                l_bhParams.setLastReadTimestamp(l_tsCurrent);
            }
                       
            l_bhParams.setCreatedTimestamp(l_tsCurrent);
            log.debug("作成日付: " + l_bhParams.getCreatedTimestamp());
            l_bhParams.setLastUpdatedTimestamp(l_tsCurrent);
            log.debug("更新日付: " + l_bhParams.getLastUpdatedTimestamp());
            
            //３）QueryProcessor.doInsertQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_bhParams);

            log.debug("QueryProcessor.doInsertQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update閲覧履歴)<BR>
     * 引数の閲覧履歴Paramsで閲覧履歴テーブルを更新する。<BR>
     * <BR>
     * １）QueryProcessor.doUpdateQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doUpdateQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"閲覧履歴テーブル(browse_history)"<BR>
     * 　@　@arg1：　@パラメータ.閲覧履歴Params<BR>
     * @@param l_browseHistoryParams - (閲覧履歴Params)<BR>
     * 閲覧履歴Paramsオブジェクト<BR>
     * @@roseuid 4145236D005C
     */
    public void updateBrowseHistory(BrowseHistoryParams l_browseHistoryParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " updateBrowseHistory(BrowseHistoryParams)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.閲覧履歴Paramsをチェックする。
        if(l_browseHistoryParams == null)
        {
            log.error("パラメータ.閲覧履歴ParamsNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.閲覧履歴ParamsNull出来ない。");
        }
        
        try
        {
            //1 ）QueryProcessor.doInsertQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_browseHistoryParams);

            log.debug("QueryProcessor.doUpdateQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete閲覧履歴)<BR>
     * 引数の表示内容IDに該当する閲覧履歴テーブルのデータを削除する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@表示内容ID = パラメータ.表示内容ID<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " display_contents_id = ?"<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値をセットする。<BR>
     * 　@　@※文字列変換してセットすること。<BR>
     * 　@　@　@・パラメータ.表示内容ID<BR>
     * <BR>
     * ２）QueryProcessor.doDeleteQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doDeleteQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"閲覧履歴テーブル(browse_history)<BR>
     * 　@　@arg1：　@１）にて作成した検索条件文字列<BR>
     * 　@　@arg2：　@１）にて作成したArrayList.toArray()<BR>
     * @@param l_lngDisplayContentsId - (表示内容ID)<BR>
     * 表示内容ID<BR>
     * @@roseuid 4160E9200090
     */
    public void deleteBrowseHistory(long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteBrowseHistory(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("表示内容ID: " + l_lngDisplayContentsId);
        
        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //[検索条件] １－１）検索条件を基に、検索条件文字列を作成する。
        l_sbQuery.append("display_contents_id = ?");
        //１－２）"?"にセットするためのパラメータセットを作成する。
        l_lisQueryVars.add(new Long(l_lngDisplayContentsId));
        
        try
        {
            //２）QueryProcessor.doDeleteQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("検索条件文字列: " + l_sbQuery.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());
            
            l_queryProcessor.doDeleteAllQuery(
                BrowseHistoryRow.TYPE,
                l_sbQuery.toString(),
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doDeleteAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete閲覧履歴)<BR>
     * 引数の顧客情報、表示内容IDに該当する閲覧履歴テーブルのデータを<BR>
     * 削除する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード かつ<BR>
     * 　@　@部店コード = パラメータ.部店コード かつ<BR>
     * 　@　@顧客コード = パラメータ.顧客コード かつ<BR>
     * 　@　@表示内容ID = パラメータ.表示内容ID<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "institution_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and branch_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and account_code = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and display_contents_id = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値をセットする。<BR>
     * 　@　@　@・パラメータ.証券会社コード<BR>
     * 　@　@　@・パラメータ.部店コード<BR>
     * 　@　@　@・パラメータ.顧客コード<BR>
     * 　@　@　@・パラメータ.表示内容ID<BR>
     * <BR>
     * ２）QueryProcessor.doDeleteQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doDeleteQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"閲覧履歴テーブル(browse_history)<BR>
     * 　@　@arg1：　@１）にて作成した検索条件文字列<BR>
     * 　@　@arg2：　@１）にて作成したArrayList.toArray()<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_lngDisplayContentsId - (表示内容ID)<BR>
     * 表示内容ID<BR>
     * @@roseuid 41610CD7028C
     */
    public void deleteBrowseHistory(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        long l_lngDisplayContentsId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteBrowseHistory(String, String, String, long)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.証券会社コードをチェックする。
        if(l_strInstitutionCode == null)
        {
            log.error("パラメータ.証券会社コードNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.証券会社コードNull出来ない。");
        }
    
        //パラメータ.部店コードをチェックする。
        if(l_strBranchCode == null)
        {
            log.error("パラメータ.部店コードNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.部店コードNull出来ない。");
        }
        
        //パラメータ.顧客コードをチェックする。
        if(l_strAccountCode == null)
        {
            log.error("パラメータ.顧客コードNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客コードNull出来ない。");
        }

        log.debug("証券会社コード: " + l_strInstitutionCode);
        log.debug("部店コード: " + l_strBranchCode);
        log.debug("顧客コード: " + l_strAccountCode);
        log.debug("表示内容ID: " + l_lngDisplayContentsId);

        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        
        //[検索条件] １－１）検索条件を基に、検索条件文字列を作成する。
        l_sbQuery.append("institution_code = ? ");
        l_sbQuery.append("and branch_code = ?  ");
        l_sbQuery.append("and account_code = ?  ");
        l_sbQuery.append("and display_contents_id = ? ");

        //１－２）"?"にセットするためのパラメータセットを作成する。
        l_lisQueryVars.add(l_strInstitutionCode);
        l_lisQueryVars.add(l_strBranchCode);
        l_lisQueryVars.add(l_strAccountCode);
        l_lisQueryVars.add(new Long(l_lngDisplayContentsId));
        
        try
        {
            //２）QueryProcessor.doDeleteQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("検索条件文字列: " + l_sbQuery.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());
            
            l_queryProcessor.doDeleteAllQuery(
                BrowseHistoryRow.TYPE,
                l_sbQuery.toString(),
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doDeleteAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getIPO申告Params)<BR>
     * 当選したIPO申告Paramsを取得する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@部店ID = パラメータ.顧客.部店ID　@かつ<BR>
     * 　@　@口座ID = パラメータ.顧客.口座ID　@かつ<BR>
     * 　@　@補助口座ID = 補助口座.getSubAccountId()(*1)　@かつ<BR>
     * 　@　@(抽選結果 = "1：当選"　@または<BR>
     * 　@　@抽選結果(繰上) = "1：当選")<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " branch_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "sub_account_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(lot_result = ? or "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "lot_result_retry = ?) "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@※文字列変換してセットする。<BR>
     * 　@　@　@・パラメータ.顧客.部店ID<BR>
     * 　@　@　@・パラメータ.顧客.口座ID<BR>
     * 　@　@　@・補助口座.getSubAccountId()<BR>
     * 　@　@　@・"1：当選"<BR>
     * 　@　@　@・"1：当選"<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@”IPO申告テーブル(ipo_order)”<BR>
     * 　@arg1：　@作成した検索条件文字列<BR>
     * 　@arg2：　@作成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ３）２）の結果を返却する。<BR>
     * <BR>
     * (*1)補助口座<BR>
     * 　@パラメータ.顧客.getSubAccount()にて取得。<BR>
     * <BR>
     * 　@[getSubAccount()にセットするパラメータ]<BR>
     * 　@　@補助口座タイプ：　@SubAccountTypeEnum.株式取引口座<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return List
     * @@roseuid 41454EEC000E
     */
    public List getIpoOrderParams(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIpoOrderParams(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }

        List l_lisResultParams = null;
        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        try
        {        
            //[検索条件]１－１）検索条件を基に、検索条件文字列を作成する。
            l_sbQuery.append(" branch_id = ? and ");
            l_sbQuery.append("account_id = ? and ");
            l_sbQuery.append("sub_account_id = ? and ");
            l_sbQuery.append("(lot_result = ? or ");
            l_sbQuery.append("lot_result_retry = ?) ");
        
            //１－２）"?"にセットするためのパラメータセットを作成する。
            l_lisQueryVars.add(new Long(l_mainAccount.getBranch().getBranchId()));
            l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
            l_lisQueryVars.add(new Long(l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId()));
            l_lisQueryVars.add(WEB3LotResultDef.ELECTION);
            l_lisQueryVars.add(WEB3LotResultRetryDef.ELECTION);
        
            //２）QueryProcessor.doFindAllQuery()メソッドをコールする。
        
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();

            log.debug("検索条件文字列: " + l_sbQuery.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());

            l_lisResultParams = l_queryProcessor.doFindAllQuery(
                IpoOrderRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
         
        if (l_lisResultParams.isEmpty())
        {
            log.debug("該当データなし");
            return null;
        }
         
        //３）２）の結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }

    /**
     * (getIPO銘柄Params)<BR>
     * 引数のIPO銘柄IDに該当する購入申込締切前の<BR>
     * IPO銘柄Paramsを取得する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@IPO銘柄ID = パラメータ.IPO銘柄ID　@かつ<BR>
     * 　@　@購入申込終了日時(当社設定) > 現在時刻(*1)<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " ipo_product_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@      　@+ "offer_end_datetime > <BR>
     *                               to_date(?, 'YYYYMMDDHH24MISS') "<BR>
     * <BR>
     * 　@２－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@※文字列変換してセットする。<BR>
     * 　@　@　@・パラメータ.IPO銘柄ID<BR>
     * 　@　@　@・現在時刻　@※文字列変換(フォーマット：yyyyMMddHHmmss)し、セット。<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@”IPO銘柄テーブル(ipo_product)”<BR>
     * 　@arg1：　@作成した検索条件文字列<BR>
     * 　@arg2：　@ArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ３）２）の結果を返却する。<BR>
     * <BR>
     * (*1)現在時刻<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻<BR>
     * @@param l_lngIpoProductId - (IPO銘柄ID)<BR>
     * IPO銘柄ID<BR>
     * @@param l_isAdvancedElection - (is繰上げ当選)<BR>
     * 繰上げ当選かどうかのフラグ<BR>
     * false：　@繰上げ当選でない<BR>
     * true：　@繰上げ当選である<BR>
     * @@return webbroker3.ipo.data.IpoProductParams
     * @@roseuid 41455DCC02AE
     */
    public IpoProductParams getIpoProductParams(long l_lngIpoProductId, boolean l_isAdvancedElection) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIpoProductParams(long)";
        log.entering(STR_METHOD_NAME);

        log.debug("IPO銘柄ID: " + l_lngIpoProductId);

        //１）検索条件文字列&検索条件データコンテナ
        //２－２）"?"にセットするためのパラメータセットを作成する。
        Timestamp l_tsCurrent = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        //２）QueryProcessor.doFindAllQuery()メソッドをコールする。
        IpoProductRow l_ipoProductRow = null;
        try
        {
            l_ipoProductRow = IpoProductDao.findRowByPk(l_lngIpoProductId);
            
            log.debug("IpoProductDao.findRowByPk()を執行します");

            if(l_ipoProductRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            // 繰上げ当選の場合
            if (l_isAdvancedElection)
            {
	            Timestamp l_tsOed = l_ipoProductRow.getOfferEndDateProspec();
	            if (WEB3DateUtility.compareToDay(l_tsOed, l_tsCurrent) < 0)
	            {
	                log.exiting(STR_METHOD_NAME);
	                return null;
	            }
	            else
	            {
	                log.exiting(STR_METHOD_NAME);
	                return (IpoProductParams) l_ipoProductRow;
	            }
            }
            else    // 当選の場合
            {
	            Timestamp l_tsOed = l_ipoProductRow.getOfferEndDatetime();
	            if (WEB3DateUtility.compareToSecond(l_tsOed, l_tsCurrent) <= 0)
	            {
	                log.exiting(STR_METHOD_NAME);
	                return null;
	            }
	            else
	            {
	                log.exiting(STR_METHOD_NAME);
	                return (IpoProductParams) l_ipoProductRow;
	            }
            }
            
        }
        catch (DataFindException l_ex)
        {
            log.debug("getIPO銘柄Params is NULL");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
    }

    /**
     * (get決済期限間近建玉一覧)<BR>
     * 引数の顧客の保持する決済期限間近の建玉を取得する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@口座ID = パラメータ.顧客.getAccountId()　@かつ<BR>
     * 　@　@補助口座ID = 補助口座.getSubAccountId()(*1)　@かつ<BR>
     * 　@　@期日 <= to_date(?)　@かつ<BR>
     * 　@　@[一週間前の建玉の場合]<BR>
     * 　@　@　@期日 >= to_date(?)　@かつ　@建株数 != 0<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@期日 > to_date(?)　@かつ　@建株数 != 0<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "account_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "sub_account_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@[パラメータ.is決済一週間前 == trueの場合]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "close_date >= to_date(?) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "close_date > to_date(?) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "quantity != ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@※文字列変換してセットする。<BR>
     * 　@　@　@・パラメータ.顧客.getAccountId()<BR>
     * 　@　@　@・補助口座.getSubAccountId()<BR>
     * <BR>
     * ２）パラメータ.is決済一週間前により、期日の値を<BR>
     * 　@　@ArrayListに追加する。<BR>
     * 　@　@※文字列変換(フォーマット：yyyyMMdd)し、セットする。<BR>
     * <BR>
     * 　@　@[パラメータ.is決済一週間前 == trueの場合]<BR>
     * 　@　@　@・現在時刻(*2) + 7日<BR>
     * 　@　@　@・現在時刻<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@・現在時刻 + 一ヶ月<BR>
     * 　@　@　@・現在時刻 + 7日<BR>
     * <BR>
     * ３）QueryProcessor.doFindAllQuery()にて、決済間近建玉を取得する。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@"建株テーブル(eqtype_contract)"<BR>
     * 　@arg1：　@作成した検索条件文字列<BR>
     * 　@arg2：　@作成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ４）取得したデータを返却する。<BR>
     * <BR>
     * (*1)補助口座<BR>
     * 　@パラメータ.顧客.getSubAccount()にて取得。<BR>
     * <BR>
     * 　@[getSubAccount()にセットするパラメータ]<BR>
     * 　@　@補助口座タイプ：　@SubAccountTypeEnum.信用取引口座<BR>
     * <BR>
     * (*2)現在時刻<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_isSettleBeforeOneWeek - (is決済一週間前)<BR>
     * 決済期限が一週間前かどうかのフラグ<BR>
     * <BR>
     * false：　@決済一ヶ月前<BR>
     * true：　@決済一週間前<BR>
     * @@return List
     * @@roseuid 414567D50221
     */
    public List getSettleContractList(WEB3GentradeMainAccount l_mainAccount, boolean l_isSettleBeforeOneWeek) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettleContractList(WEB3GentradeMainAccount, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }
                
        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        
        List l_lisResultParams = null;
        try
        {
            List l_lisObjs = new ArrayList();
            //１－２）"?"にセットするためのパラメータセットを作成する。
            //１－１）検索条件を基に、検索条件文字列を作成する。
            l_sbQuery.append(" account_id = ? and sub_account_id = ? and ");
            l_lisObjs.add(new Long(l_mainAccount.getAccountId()));
            
            long l_lngSubAccountId = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getSubAccountId();
            l_lisObjs.add(new Long(l_lngSubAccountId));
            
            //２）パラメータ.is決済一週間前により、期日の値をArrayListに追加する。
            //現在時刻
            Date l_dCurrent = (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            Date l_dCurrentBeforeOneWeek = WEB3DateUtility.addDay(l_dCurrent, 7);
            //[パラメータ.is決済一週間前 == trueの場合]
            if(l_isSettleBeforeOneWeek)
            {
                l_sbQuery.append("close_date <= to_date(?) and close_date >= to_date(?) and ");
                String l_strCurrentBeforeOneWeek = WEB3DateUtility.formatDate(l_dCurrentBeforeOneWeek, "yyyy/MM/dd");
                String l_strCurrent = WEB3DateUtility.formatDate(l_dCurrent, "yyyy/MM/dd");
                l_lisObjs.add(l_strCurrentBeforeOneWeek);
                l_lisObjs.add(l_strCurrent);
            }
            //[上記以外の場合]
            else
            {
                l_sbQuery.append("close_date <= add_months(to_date(?, 'yyyy/MM/dd'), 1) and close_date > to_date(?) and ");
                String l_strCurrent = WEB3DateUtility.formatDate(l_dCurrent, "yyyy/MM/dd");
                String l_strCurrentBeforeOneWeek = WEB3DateUtility.formatDate(l_dCurrentBeforeOneWeek, "yyyy/MM/dd");
                l_lisObjs.add(l_strCurrent);
                l_lisObjs.add(l_strCurrentBeforeOneWeek);
            }
            
            l_sbQuery.append("quantity != ? ");
            l_lisObjs.add(new Double(0));
            
            Object[] l_whereObjs = new Object[l_lisObjs.size()];
            
            l_lisObjs.toArray(l_whereObjs);
         
            //３）QueryProcessor.doFindAllQuery()にて、決済間近建玉を取得する。 
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = l_sbQuery.toString();
            
            log.debug("検索条件文字列&検索条件データコンテナ: " + l_strWhere);
            
            l_lisResultParams = l_queryProcessor.doFindAllQuery(EqtypeContractRow.TYPE, l_strWhere, l_whereObjs);

            log.debug("QueryProcessor.doFindAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        log.exiting(STR_METHOD_NAME);
        if (l_lisResultParams != null && l_lisResultParams.size() == 0)
        {
            return null;
        }
        return l_lisResultParams;
    }

    /**
     * (get証拠金不足一覧)<BR>
     * 引数の顧客に該当するデータを証拠金テーブルから取得する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード = パラメータ.顧客.証券会社コード　@かつ<BR>
     * 　@　@部店コード = パラメータ.顧客.部店コード　@かつ<BR>
     * 　@　@顧客コード = パラメータ.顧客.getAccountCode()　@かつ<BR>
     * 　@　@削除フラグ = "0：false"<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " institution_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "branch_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "account_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "delete_flag = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値を上から順にセットする。<BR>
     * 　@　@　@・パラメータ.顧客.証券会社コード<BR>
     * 　@　@　@・パラメータ.顧客.部店コード<BR>
     * 　@　@　@・パラメータ.顧客.getAccountCode()<BR>
     * 　@　@　@・"0：false"<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"証拠金テーブル(ifo_deposit)"<BR>
     * 　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@arg2：　@作成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合は、nullを返却する。<BR>
     * <BR>
     * ３）２）の検索結果を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return List
     * @@roseuid 4145913501B4
     */
    public List getIfoDepositShortageList(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIfoDepositShortageList(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }
        
        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();
        //[検索条件] １－１）検索条件を基に、検索条件文字列を作成する。
        l_sbQuery.append(" institution_code = ? and ");
        l_sbQuery.append("branch_code = ? and ");
        l_sbQuery.append("account_code = ? and ");
        l_sbQuery.append("delete_flag = ? ");
        
        //１－２）"?"にセットするためのパラメータセットを作成する。
        l_lisQueryVars.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisQueryVars.add(l_mainAccount.getBranch().getBranchCode());
        l_lisQueryVars.add(l_mainAccount.getAccountCode());
        l_lisQueryVars.add(WEB3PvInfoDeleteFlagDef.DELETE_NO);
        
        //２）QueryProcessor.doFindAllQuery()メソッドをコールする。
        List l_lisResultParams = null; 
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("検索条件文字列: " + l_sbQuery.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());
            
            l_lisResultParams = l_queryProcessor.doFindAllQuery(
                IfoDepositRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 

        if (l_lisResultParams.isEmpty())
        {
            log.debug("証拠金テーブルに該当データなし");
            return null;
        }
        
        //３）２）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisResultParams;
    }

    /**
     * (get株式銘柄Params)<BR>
     * 引数の銘柄IDに該当する株式銘柄Paramsを<BR>
     * 株式銘柄テーブルより取得する。<BR>
     * <BR>
     * １）株式銘柄テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@銘柄ID = パラメータ.銘柄ID<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ２）１）の検索結果を返却する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams
     * @@roseuid 4147AB310115
     */
    public EqtypeProductParams getEqtypeProductParams(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEqtypeProductParams(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("銘柄ID: " + l_lngProductId);
        
        //１）株式銘柄テーブルを以下の条件で検索する。
        EqtypeProductRow l_epRow = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_epRow = (EqtypeProductRow) l_queryProcessor.doFindByPrimaryKeyQuery(new EqtypeProductPK(l_lngProductId));

            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()を執行します");
        }
        //null value return
        catch(DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            return null;
        }        
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }         
        
        //２）１）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        if (l_epRow == null)
        {
            return null;
        }
        return (EqtypeProductParams)l_epRow;
    }

    /**
     * (get先物OP銘柄Params)<BR>
     * 引数の銘柄IDに該当する先物OP銘柄Paramsを<BR>
     * 先物OP銘柄テーブルより取得する。<BR>
     * <BR>
     * １）先物OP銘柄テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@銘柄ID = パラメータ.銘柄ID<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ２）１）の検索結果を返却する。<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@return IfoProductParams
     * @@roseuid 41593981026F
     */
    public IfoProductParams getIfoProductParams(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getIfoProductParams(long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("銘柄ID: " + l_lngProductId);
        
        //１）先物OP銘柄テーブルを以下の条件で検索する。
        IfoProductRow l_ifoRow = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_ifoRow = (IfoProductRow) l_queryProcessor.doFindByPrimaryKeyQuery(new IfoProductPK(l_lngProductId));

            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()を執行します");
        }
        //null value return
        catch(DataFindException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //２）１）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        if (l_ifoRow == null)
        {
            return null;
        }
        return (IfoProductParams) l_ifoRow;
    }

    /**
     * (clone閲覧履歴Params)<BR>
     * 引数の閲覧履歴Paramsをコピーして、<BR>
     * 同じ内容の別インスタンスを作成し、返却する。<BR>
     * @@param l_browseHistoryParams - (閲覧履歴Params)<BR>
     * 閲覧履歴Paramsオブジェクト<BR>
     * @@return webbroker3.pvinfo.data.BrowseHistoryParams
     * @@roseuid 4147C8E403AD
     */
    public BrowseHistoryParams cloneBrowseHistoryParams(BrowseHistoryParams l_browseHistoryParams)
    {
        final String STR_METHOD_NAME = " cloneBrowseHistoryParams(BrowseHistoryParams)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.閲覧履歴Paramsをチェックする。
        if (l_browseHistoryParams == null)
        {
            log.error("パラメータ.閲覧履歴ParamsNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.閲覧履歴ParamsNull出来ない。");
        }

        //同じ内容の別インスタンスを作成し、返却する。
        BrowseHistoryParams l_bhParams = new BrowseHistoryParams();
        l_bhParams.setBrowseHistoryId(l_browseHistoryParams.getBrowseHistoryId());
        l_bhParams.setInstitutionCode(l_browseHistoryParams.getInstitutionCode());
        l_bhParams.setBranchCode(l_browseHistoryParams.getBranchCode());
        l_bhParams.setDisplayContentsId(l_browseHistoryParams.getDisplayContentsId());
        l_bhParams.setAccountCode(l_browseHistoryParams.getAccountCode());
//      *** 未取込No.016 START            
                 l_bhParams.setAccountId(l_browseHistoryParams.getAccountId());
//      *** 未取込No.016 END            
        l_bhParams.setReadFlag(l_browseHistoryParams.getReadFlag());
        l_bhParams.setDeleteFlag(l_browseHistoryParams.getDeleteFlag());
        l_bhParams.setLastReadTimestamp(l_browseHistoryParams.getLastReadTimestamp());
        l_bhParams.setCreatedTimestamp(l_browseHistoryParams.getCreatedTimestamp());
        l_bhParams.setLastUpdatedTimestamp(l_browseHistoryParams.getLastUpdatedTimestamp());

        log.exiting(STR_METHOD_NAME);
        return l_bhParams;
    }

    /**
     * (clone表示内容Params)<BR>
     * 引数の表示内容Paramsをコピーして、<BR>
     * 同じ内容の別インスタンスを作成し、返却する。<BR>
     * @@param l_displayContentsParams - (表示内容Params)<BR>
     * 表示内容Paramsオブジェクト<BR>
     * @@return webbroker3.pvinfo.data.DisplayContentsParams
     * @@roseuid 4147C96201B9
     */
    public DisplayContentsParams cloneDisplayContentsParams(DisplayContentsParams l_displayContentsParams)
    {
        final String STR_METHOD_NAME = " cloneDisplayContentsParams(DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.表示内容Paramsをチェックする。
        if (l_displayContentsParams == null)
        {
            log.error("パラメータ.表示内容ParamsNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.表示内容ParamsNull出来ない。");
        }

        //同じ内容の別インスタンスを作成し、返却する。
        DisplayContentsParams l_dcParams = new DisplayContentsParams();
        l_dcParams.setDisplayContentsId(l_displayContentsParams.getDisplayContentsId());
        l_dcParams.setInstitutionCode(l_displayContentsParams.getInstitutionCode());
        l_dcParams.setBranchCode(l_displayContentsParams.getBranchCode());
        l_dcParams.setConditionNo(l_displayContentsParams.getConditionNo());
        l_dcParams.setPriorityDiv(l_displayContentsParams.getPriorityDiv());
        l_dcParams.setTermFrom(l_displayContentsParams.getTermFrom());
        l_dcParams.setTermTo(l_displayContentsParams.getTermTo());
        l_dcParams.setDisplayTitle(l_displayContentsParams.getDisplayTitle());
        l_dcParams.setDisplayMessage(l_displayContentsParams.getDisplayMessage());
        l_dcParams.setDisplayColor(l_displayContentsParams.getDisplayColor());
        l_dcParams.setBlinkDisplayFlag(l_displayContentsParams.getBlinkDisplayFlag());
        l_dcParams.setDisplayUrl(l_displayContentsParams.getDisplayUrl());
        l_dcParams.setLastUpdateTimeDisplayFlag(l_displayContentsParams.getLastUpdateTimeDisplayFlag());
        l_dcParams.setEffectiveFlag(l_displayContentsParams.getEffectiveFlag());
        l_dcParams.setDisplayDevice(l_displayContentsParams.getDisplayDevice());
        l_dcParams.setLastUpdateMember(l_displayContentsParams.getLastUpdateMember());
        l_dcParams.setCreatedTimestamp(l_displayContentsParams.getCreatedTimestamp());
        l_dcParams.setLastUpdatedTimestamp(l_displayContentsParams.getLastUpdatedTimestamp());

        log.exiting(STR_METHOD_NAME);
        return l_dcParams;
    }

    /**
     * (create表示内容Params)<BR>
     * 引数の表示内容情報から<BR>
     * 表示内容Paramsを作成し、返却する。<BR>
     * <BR>
     * １）this.get表示内容Params()をコールする。<BR>
     * <BR>
     * 　@[get表示内容Params()にセットするパラメータ]<BR>
     * 　@　@表示内容ID：　@パラメータ.表示内容情報.表示内容ID<BR>
     * <BR>
     * 　@nullが返却された場合は、nullを返却する。<BR>
     * <BR>
     * ２）this.clone表示内容Params()をコールし、表示内容Params(クローン)を<BR>
     * 　@取得する。<BR>
     * <BR>
     * 　@[clone表示内容Params()にセットするパラメータ]<BR>
     * 　@　@表示内容Params：　@１）にて取得した表示内容Params<BR>
     * <BR>
     * ３）２）にて取得した表示内容Params(クローン)に以下のプロパティをセットする。<BR>
     * <BR>
     * 　@表示条件番号	= パラメータ.表示内容情報の同名項目<BR>
     * 　@優先区分	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示期間From	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示期間To	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示タイトル	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示文章	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示色		= パラメータ.表示内容情報の同名項目<BR>
     * 　@点滅表示フラグ	= パラメータ.表示内容情報の同名項目<BR>
     * 　@URL指定		= パラメータ.表示内容情報の同名項目<BR>
     * 　@最終更新日時表示フラグ	= パラメータ.表示内容情報の同名項目<BR>
     * 　@有効/無効フラグ	= パラメータ.表示内容情報の同名項目<BR>
     * 　@表示媒体	= パラメータ.表示内容情報の同名項目<BR>
     * 　@最終更新者	= パラメータ.表示内容情報の同名項目<BR>
     * 　@更新日付	= 現在時刻(*1)<BR>
     * <BR>
     * ４）３）にてプロパティセットした表示内容Params(クローン)を返却する。<BR>
     * <BR>
     * (*1)現在時刻<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻<BR>
     * @@param l_displayContentsUnit - (表示内容情報)<BR>
     * 表示内容情報オブジェクト<BR>
     * @@return webbroker3.pvinfo.data.DisplayContentsParams
     * @@roseuid 415D227A02CF
     */
    public DisplayContentsParams createDisplayContentsParams(WEB3PvInfoDisplayContentsUnit l_displayContentsUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createDisplayContentsParams(WEB3PvInfoDisplayContentsUnit)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.表示内容情報をチェックする。
        if (l_displayContentsUnit == null)
        {
            log.error("パラメータ.表示内容情報Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.表示内容情報Null出来ない。");
        }

        //１）this.get表示内容Params()をコールする。
        DisplayContentsParams l_dcParams = null;
        try
        {
            long l_lngDisplayContentsId = Long.parseLong(l_displayContentsUnit.displayContentsId);
            l_dcParams = getDisplayContentsParams(l_lngDisplayContentsId);

            log.debug("this.get表示内容Params()を執行します");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("getDisplayContentsParams error", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //nullが返却された場合は、nullを返却する。
        if (l_dcParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）this.clone表示内容Params()をコールし、表示内容Params(クローン)を取得する。
        DisplayContentsParams l_dcParamsClone = cloneDisplayContentsParams(l_dcParams);


        //３）２）にて取得した表示内容Params(クローン)に以下のプロパティをセットする。
        //      (1)表示条件番号    = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setConditionNo(l_displayContentsUnit.conditionNumber);
        //      (2)優先区分    = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setPriorityDiv(l_displayContentsUnit.priorityDiv);
        //      (3)表示期間From  = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setTermFrom(WEB3DateUtility.getDate(l_displayContentsUnit.listStartDate, "yyyyMMddHHmm"));
        //      (4)表示期間To  = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setTermTo(WEB3DateUtility.getDate(l_displayContentsUnit.listEndDate, "yyyyMMddHHmm"));
        //      (5)表示タイトル   = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setDisplayTitle(l_displayContentsUnit.displayTitle);
        //      (6)表示文章 = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setDisplayMessage(l_displayContentsUnit.displayMessage);
        //      (7)表示色      = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setDisplayColor(l_displayContentsUnit.displayColor);        
        // 　@   (8)点滅表示フラグ  = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setBlinkDisplayFlag(l_displayContentsUnit.blinkDisplayFlag ?
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES :
            WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_NO);
        //      (9) 　@URL指定        = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setDisplayUrl(l_displayContentsUnit.displayUrl);
        // 　@   (10)最終更新日時表示フラグ  = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setLastUpdateTimeDisplayFlag(l_displayContentsUnit.lastUpdateTimeDisplayFlag ?
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_NO :
            WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES);
        // 　@   (11)有効/無効フラグ = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setEffectiveFlag(l_displayContentsUnit.effectiveFlag ?
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_NO :
            WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);
        //　@    (12)表示媒体 = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setDisplayDevice(l_displayContentsUnit.displayDevice);
        // 　@   (13)最終更新者    = パラメータ.表示内容情報の同名項目
        l_dcParamsClone.setLastUpdateMember(l_displayContentsUnit.lastUpdateMember);
        // 　@   (14)更新日付 = 現在時刻(*1)<BR>
        l_dcParamsClone.setLastUpdatedTimestamp((Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        //４）３）にてプロパティセットした表示内容Params(クローン)を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dcParamsClone;
    }

    /**
     * (is資産保有)<BR>
     * 引数の銘柄タイプに該当する資産を保有しているか判別する。<BR>
     * <BR>
     * 保有している場合true、以外falseを返却する。<BR>
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@口座ID = パラメータ.顧客.口座ID　@かつ<BR>
     * 　@　@銘柄タイプ = パラメータ.銘柄タイプ　@かつ<BR>
     * 　@　@数量 != 0　@かつ<BR>
     * 　@　@ミニ株区分 = (パラメータ.isミニ株の値による)<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " account_id = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and product_type = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and quantity != ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and mini_stock_div = ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値をセットする。<BR>
     * 　@　@※文字列変換してセットする。<BR>
     * 　@　@　@・パラメータ.顧客.getAccountId()<BR>
     * 　@　@　@・パラメータ.銘柄タイプ<BR>
     * 　@　@　@・0<BR>
     * <BR>
     * ２）パラメータ.isミニ株により、以下の条件をArrayListに追加する。<BR>
     * 　@[パラメータ.isミニ株 == trueの場合]<BR>
     * 　@　@　@生成したArrayList.add("1：ミニ株")<BR>
     * <BR>
     * 　@[パラメータ.isミニ株 == falseの場合]<BR>
     * 　@　@　@生成したArrayList.add("0：DEFAULT")<BR>
     * <BR>
     * ３）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@”保有資産テーブル(asset)”<BR>
     * 　@arg1：　@作成した検索条件文字列<BR>
     * 　@arg2：　@作成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できた場合はtrue、以外falseを返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * (ProductTypeEnumにて定義)<BR>
     * @@param l_isMiniStock - (isミニ株)<BR>
     * 取得対象保有資産が、ミニ株かどうかのフラグ<BR>
     * <BR>
     * true：　@ミニ株<BR>
     * false：　@ミニ株でない<BR>
     * @@return boolean
     * @@roseuid 41590F7E0221
     */
    public boolean isAssetHas(
        WEB3GentradeMainAccount l_mainAccount,
        ProductTypeEnum l_productType,
        boolean l_isMiniStock) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isAssetHas(WEB3GentradeMainAccount, ProductTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }

        //パラメータ.銘柄タイプをチェックする。
        if (l_productType == null)
        {
            log.error("パラメータ.銘柄タイプNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.銘柄タイプNull出来ない。");
        }

        log.debug("銘柄タイプ: " + l_productType);
        log.debug("isミニ株: " + l_isMiniStock);

        //１）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();

        //１－１）検索条件を基に、検索条件文字列を作成する。<BR>
        l_sbQuery.append(" account_id = ? ");
        l_sbQuery.append("and product_type = ? ");
        l_sbQuery.append("and quantity != ? ");
        l_sbQuery.append("and mini_stock_div = ? ");
        //１－２）"?"にセットするためのパラメータセットを作成する。
        l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
        l_lisQueryVars.add(new Integer(l_productType.intValue()));
        l_lisQueryVars.add(new Long(0));
        //２）パラメータ.isミニ株により、以下の条件をArrayListに追加する。
        if (l_isMiniStock)
        {
            l_lisQueryVars.add(WEB3MiniStockDivDef.MINI_STOCK);
        }
        else
        {
            l_lisQueryVars.add(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        }

        //３）QueryProcessor.doFindAllQuery()メソッドをコールする。
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            
            log.debug("検索条件文字列: " + l_sbQuery.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());
            
            List l_lisResultRow = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());
            
            log.debug("QueryProcessor.doFindAllQuery()を執行します");

            //検索結果が取得できた場合はtrue、以外falseを返却する。
            if(l_lisResultRow != null && l_lisResultRow.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }              
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (is建玉保有)<BR>
     * 引数の銘柄タイプ、先物／オプション区分に該当する建玉を<BR>
     * 保有しているか判別する。<BR>
     * <BR>
     * 保有している場合true、以外falseを返却する。<BR>
     * <BR>
     * １）パラメータ.銘柄タイプにより検索対象となるテーブルを決定する。<BR>
     * <BR>
     * 　@[パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合]<BR>
     * 　@　@検索対象テーブル = 建株テーブル(eqtype_contract)<BR>
     * <BR>
     * 　@[パラメータ.銘柄タイプ == ProductTypeEnum.先物オプションの場合]<BR>
     * 　@　@検索対象テーブル = 建玉テーブル(ifo_contract)<BR>
     * <BR>
     * ２）検索条件文字列&検索条件データコンテナ<BR>
     * 　@以下の検索条件を表す、検索条件文字列と<BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@口座ID = パラメータ.顧客.口座ID　@かつ<BR>
     * 　@　@建玉数 != 0　@かつ<BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = " account_id = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and quantity != ? "<BR>
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR>
     * 　@　@ArrayListを生成し、以下の値をセットする。<BR>
     * 　@　@　@・パラメータ.顧客.getAccountId()　@※文字列変換してセット。<BR>
     * 　@　@　@・"0"<BR>
     * 　@<BR>
     * ３）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@１）にて決定した検索対象テーブル<BR>
     * 　@arg1：　@作成した検索条件文字列<BR>
     * 　@arg2：　@作成したArrayList.toArray()<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合はfalseを返却する。<BR>
     * <BR>
     * ４）パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合、<BR>
     * 　@検索結果が取得できた場合はtrueを返却する。<BR>
     * <BR>
     * ５）パラメータ.銘柄タイプ == ProductTypeEnum.先物オプションの場合、<BR>
     * 　@３）にて取得した全ての建玉Paramsに対し、以下の処理を繰り返す。<BR>
     * <BR>
     * 　@５－１）this.get先物OP銘柄Params()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@[get先物OP銘柄Params()にセットするパラメータ]<BR>
     * 　@　@　@　@　@　@銘柄ID：　@取得した建玉Params.銘柄ID<BR>
     * <BR>
     * 　@５－２）get先物OP銘柄Params()の戻り値.先物／オプション区分 == <BR>
     * 　@　@　@　@　@　@パラメータ.先物／オプション区分の場合、trueを返却する。<BR>
     * <BR>
     * ６）falseを返却する。　@※指定した建玉を保有していない為。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * (ProductTypeEnumにて定義)<BR>
     * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分<BR>
     * <BR>
     * 0：　@DEFAULT<BR>
     * 1：　@先物<BR>
     * 2：　@オプション<BR>
     * @@return boolean
     * @@roseuid 4159148800B9
     */
    public boolean isContractHas(
        WEB3GentradeMainAccount l_mainAccount,
        ProductTypeEnum l_productType,
        String l_strFutureOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isContractHas(WEB3GentradeMainAccount, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);
        
        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }

        //パラメータ.銘柄タイプをチェックする。
        if (l_productType == null)
        {
            log.error("パラメータ.銘柄タイプNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.銘柄タイプNull出来ない。");
        }
        
        if (l_productType.intValue() != ProductTypeEnum.IntValues.EQUITY && 
            l_productType.intValue() != ProductTypeEnum.IntValues.IFO)
        {
            log.error("パラメータ.銘柄タイプ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.銘柄タイプ値不正。");
        }

        if(l_strFutureOptionDiv != null && 
            !WEB3FuturesOptionDivDef.DEFAULT.equals(l_strFutureOptionDiv) &&
            !WEB3FuturesOptionDivDef.FUTURES.equals(l_strFutureOptionDiv) &&
            !WEB3FuturesOptionDivDef.OPTION.equals(l_strFutureOptionDiv))
        {
            log.error("パラメータ.先物／オプション区分値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.先物／オプション区分値不正。");
        }

        //１）パラメータ.銘柄タイプにより検索対象となるテーブルを決定する。
        RowType l_rowType = (l_productType.intValue() == ProductTypeEnum.IntValues.EQUITY) 
            ? EqtypeContractRow.TYPE //[パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合]
            : IfoContractRow.TYPE;   //[パラメータ.銘柄タイプ == ProductTypeEnum.先物オプションの場合] 

        //２）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisQueryVars = new ArrayList();

        //１－１）検索条件を基に、検索条件文字列を作成する。
        l_sbQuery.append(" account_id = ? ");
        l_sbQuery.append("and quantity != ? ");

        //１－２）"?"にセットするためのパラメータセットを作成する。
        l_lisQueryVars.add(new Long(l_mainAccount.getAccountId()));
        l_lisQueryVars.add("0");
        
        List l_lisResultRow = null;
        try
        {
            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();

            log.debug("検索条件文字列: " + l_sbQuery.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());

            //３）QueryProcessor.doFindAllQuery()メソッドをコールする。
            l_lisResultRow = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_sbQuery.toString(),
                null,
                l_lisQueryVars.toArray());

            log.debug("QueryProcessor.doFindAllQuery()を執行します");
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                
        //検索結果が取得できなかった場合はfalseを返却する。
        if(l_lisResultRow == null || l_lisResultRow != null && l_lisResultRow.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //４）パラメータ.銘柄タイプ == ProductTypeEnum.株式の場合、検索結果が取得できた場合はtrueを返却する。
        if(l_productType.intValue() == ProductTypeEnum.IntValues.EQUITY)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //５）パラメータ.銘柄タイプ == ProductTypeEnum.先物オプションの場合、３）にて取得した全ての建玉Paramsに対し、以下の処理を繰り返す。
        else if (l_productType.intValue() == ProductTypeEnum.IntValues.IFO)
        {
            int l_intTemp = l_lisResultRow.size();
            for(int i = 0; i < l_intTemp; i++)
            {
                //５－１）this.get先物OP銘柄Params()をコールする。
                IfoContractParams l_ifoContractParams = (IfoContractParams)l_lisResultRow.get(i);
                //[get先物OP銘柄Params()にセットするパラメータ]
                IfoProductParams l_ifoProductParams = getIfoProductParams(l_ifoContractParams.product_id);
                //５－２）get先物OP銘柄Params()の戻り値.先物／オプション区分 == パラメータ.先物／オプション区分の場合、trueを返却する。
                if (l_ifoProductParams.future_option_div.equals(l_strFutureOptionDiv))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }

        //６）falseを返却する。　@※指定した建玉を保有していない為。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get資産余力情報)<BR>
     * 引数の補助口座に該当する資産余力情報を返却する。<BR>
     * 戻り値：　@資産余力情報<現物顧客> or<BR>
     *              資産余力情報<信用顧客><BR>
     * ※本メソッドを使用する場合は、使用するサービスのサービスインタセプタ.onReturn()、onThrowable()<BR>
     * 　@内にて、"TRADING_POWER_INFO"の設定キーにて設定されているデータをクリアすること。<BR>
     * １）ThreadLocalSystemAttributesRegistry.getAttribute()メソッドをコールする。<BR>
     * 　@[getAttribute()にセットするパラメータ]<BR>
     * 　@　@arg0：　@"TRADING_POWER_INFO"<BR>
     * <BR>
     * ２）１）の戻り値 != nullの場合、１）の戻り値を返却する。<BR>
     * <BR>
     * ３）２）以外の場合、以下の処理を実施する。<BR>
     * 　@３－１）パラメータ.補助口座.getMainAccount()メソッドにて<BR>
     * 　@　@顧客オブジェクトを取得する。<BR>
     * 　@３－２）顧客オブジェクト.is信用口座開設()メソッドをコールする。<BR>
     * 　@３－３）資産余力情報クラスの取得する<BR>
     * 　@　@３－２）のメソッドの戻り値が、<BR>
     * 　@　@[falseの場合]<BR>
     * 　@　@　@取引余力サービス.get資産余力情報<現物顧客>()メソッドをコールする。<BR>
     * 　@　@[上記以外の場合]<BR>
     * 　@　@　@取引余力サービス.get資産余力情報<信用顧客>()メソッドをコールする。<BR>
     * 　@　@※各get資産余力情報<...顧客>()メソッド引数には、パラメータ.補助口座をセットすること。<BR>
     * 　@３－４）ThreadLocalSystemAttributesRegistry.getAttribute()メソッドをコールし、<BR>
     * 　@　@３－３）の戻り値をセットする。<BR>
     * 　@　@[setAttribute()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@"TRADING_POWER_INFO"<BR>
     * 　@３－５）３－３）の戻り値を返却する。<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    public Object getTradingPowerInfo(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradingPowerInfo(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error("パラメータ.Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.Null出来ない。");
        }
        //１）ThreadLocalSystemAttributesRegistry.getAttribute()メソッドをコールする。
        Object l_objPowerInfo = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3PvInfoTradingPowerInfoDef.TRADING_POWER_INFO);
        // ２）１）の戻り値 != nullの場合、１）の戻り値を返却する。
        if (l_objPowerInfo != null)
        {
            return l_objPowerInfo;
        }
        //３）２）以外の場合、以下の処理を実施する。
        //３－１）パラメータ.補助口座.getMainAccount()メソッドにて顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //３－２）顧客オブジェクト.is信用口座開設()メソッドをコールする。
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        //３－３）資産余力情報クラスの取得する
        //３－２）のメソッドの戻り値が、[falseの場合]
        WEB3TPTradingPowerService l_service = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        if (!l_blnIsMarginAccount)
        {
            //取引余力サービス.get資産余力情報<現物顧客>()メソッドをコールする。
            l_objPowerInfo = l_service.getTradingPowerCalcEquity(l_subAccount);
        }        
        //[上記以外の場合]
        else
        {
            l_objPowerInfo = l_service.getTradingPowerCalcMargin(l_subAccount);
        }
        //３－４）ThreadLocalSystemAttributesRegistry.getAttribute()メソッドをコールし、
        //３－３）の戻り値をセットする。[setAttribute()にセットするパラメータ]
        ThreadLocalSystemAttributesRegistry.setAttribute(WEB3PvInfoTradingPowerInfoDef.TRADING_POWER_INFO,l_objPowerInfo);
        //３－５）３－３）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_objPowerInfo;
    }
    
    /**
     * (get入金請求発生日)<BR>
     * 入金請求発生日を返却する。<BR>
     * １）this.get資産余力情報()メソッドをコールする。<BR>
     * 　@[get資産余力情報()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * <BR>
     * ２）パラメータ.補助口座.getMainAccount()メソッドにて<BR>
     * 　@顧客オブジェクトを取得する。<BR>
     * <BR>
     * ３）顧客オブジェクト.is信用口座開設()メソッドをコールする。<BR>
     * <BR>
     * ４）パラメータ.is信用口座開設 == false(現物顧客)の場合、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@４－１）１）の戻り値を資産余力情報<現物顧客>型にキャストする。<BR>
     * 　@４－２）以下の処理を、インデックスの範囲(0～5)について繰り返す。<BR>
     * 　@　@４－２－１）資産余力情報<現物顧客>.calc入金請求額()をコールする。<BR>
     * 　@　@　@[calc入金請求額()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@現在のインデックス<BR>
     * 　@　@４－２－２）４－２－１）の計算結果が0より大きい場合(0<計算結果)、 <BR>
     * 　@　@　@資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * 　@　@　@[get営業日()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@現在のインデックス　@　@<BR>
     * <BR>
     * ５）４）以外の場合、以下の処理を実施する。<BR>
     * 　@５－１）１）の戻り値を資産余力情報<信用顧客>型にキャストする。<BR>
     * 　@５－２）以下の処理を、インデックスの範囲(0～5)について繰り返す。<BR>
     * 　@　@５－２－１）資産余力情報<信用顧客>.calc入金請求額()をコールする。<BR>
     * 　@　@　@[calc入金請求額()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@現在のインデックス<BR>
     * 　@　@５－２－２）５－２－１）の計算結果が0より大きい場合(0<計算結果)、<BR>
     * 　@　@　@資産余力情報<信用顧客>.余力計算条件.get営業日()メソッドの<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * 　@　@　@[get営業日()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@現在のインデックス　@<BR>
     * <BR>　@
     * ４）nullを返却する。　@※入金請求が発生していない為。<BR>
     * @@param l_subAccount<BR>
     * @@return Date<BR>
     */
    public Date getPayClaimGenDate(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPayClaimGenDate(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error("パラメータ.Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.Null出来ない。");
        }
        
        //１）this.get資産余力情報()メソッドをコールする。
        Object l_objPowerInfo = this.getTradingPowerInfo(l_subAccount);
        //２）パラメータ.補助口座.getMainAccount()メソッドにて顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //３）顧客オブジェクト.is信用口座開設()メソッドをコールする。３）顧客オブジェクト.is信用口座開設()メソッドをコールする。       
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        if (!l_blnIsMarginAccount)
        {
            //　@４－１）１）の戻り値を資産余力情報<現物顧客>型にキャストする。
            WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
            //４－２）以下の処理を、インデックスの範囲(0～5)について繰り返す。
            for (int i = 0; i < 6; i++)
            {
            	/*
                //４－２－１）資産余力情報<現物顧客>.calc引出可能現金()をコールする。
                double l_dblBalance = l_powerCalcEquity.calcActualPaymentBalance(i);
                //４－２－２）４－２－１）の計算結果がマイナス(<0)だった場合、
                if (l_dblBalance < 0)
                {
                    //資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcEquity.getCalcCondition().getBizDate(i);
                }
                */
				//2006/03/13 修正	
				//４－２－１）資産余力情報<現物顧客>.calc入金請求額()をコールする。
				double l_dblBalance = l_powerCalcEquity.calcDemandamount(i);
				//４－２－２）４－２－１）の計算結果が0より大きい場合(0<計算結果)、 
				if (l_dblBalance > 0)
				{
					//資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcEquity.getCalcCondition().getBizDate(i);
				}
                
            }

        }
        //５）４）以外の場合、以下の処理を実施する。
        else
        {
            //５－１）１）の戻り値を資産余力情報<信用顧客>型にキャストする。
            WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
            //５－２）以下の処理を、インデックスの範囲(0～5)について繰り返す。
            for (int i = 0; i < 6; i++)
            {
            	/*
                //５－２－１）資産余力情報<信用顧客>.calc引出可能現金()をコールする。
                double l_dblBalance = l_powerCalcMargin.calcActualPaymentBalance(i);
                //５－２－２）資産余力情報<信用顧客>.calc追証余力()をコールする。
                double l_dblCallPower = l_powerCalcMargin.calcMarginCallPower(i);
                
                //５－２－３）５－２－１）、５－２－２）の計算結果がマイナス(<0)だった場合、
                if (l_dblBalance < 0 || l_dblCallPower < 0)
                {
                    //資産余力情報<信用顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcMargin.getCalcCondition().getBizDate(i);
                }
                */
				//５－２－１）資産余力情報<信用顧客>.calc入金請求額()をコールする。
				double l_dblBalance = l_powerCalcMargin.calcDemandamount(i);
				//５－２－２）資産余力情報<信用顧客>.calc追証余力()をコールする。
				//double l_dblCallPower = l_powerCalcMargin.calcMarginCallPower(i);
                
				//５－２－２）５－２－１）の計算結果が0より大きい場合(0<計算結果)、 
				if (l_dblBalance > 0)
				{
					//資産余力情報<信用顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcMargin.getCalcCondition().getBizDate(i);
				}
                
            }
        }
        //４）nullを返却する。　@※入金請求が発生していない為。
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get立替金発生日)<BR>
     * 立替金発生日を返却する。<BR>
     * １）this.get資産余力情報()メソッドをコールする。<BR>
     * 　@[get資産余力情報()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * <BR>
     * ２）パラメータ.補助口座.getMainAccount()メソッドにて<BR>
     * 　@顧客オブジェクトを取得する。<BR>
     * <BR>
     * ３）顧客オブジェクト.is信用口座開設()メソッドをコールする。<BR>
     * <BR>
     * ４）パラメータ.is信用口座開設 == false(現物顧客)の場合、<BR>
     * 　@以下の処理を実施する。　@※trueの場合、nullを返却して終了する。<BR>
     * 　@４－１）１）の戻り値を資産余力情報<現物顧客>型にキャストする。<BR>
     * 　@４－２）預り証券評価制顧客の場合、<BR>
     * 　@　@(資産余力情報<現物顧客>.余力計算条件.預り証券評価制区分 == true　@かつ<BR>
     * 　@　@資産余力情報<現物顧客>.余力計算条件.預り証券評価制<現物株式評価> == true)<BR>
     * 　@　@以下の処理を実施する。<BR>
     * 　@　@４－２－１）取引時間管理.is市場開局時間帯()メソッドをコールする。<BR>
     * 　@　@４－２－２）４－２－１）の戻り値 == falseの場合、<BR>
     * 　@　@　@　@指定日 = 1　@とする。<BR>
     * 　@　@　@　@以外、指定日 = 0　@とする。<BR>
     * 　@　@４－２－３）資産余力情報<現物顧客>.calc立替金()をコールする。<BR>
     * 　@　@　@　@[calc立替金()にセットするパラメータ]<BR>
     * 　@　@　@　@　@指定日：　@決定した指定日<BR>
     * 　@　@４－２－４）４－２－３）の計算結果が0より大きい場合(0<計算結果)、かつ<BR>
     * 　@　@　@　@資産余力情報<現物顧客>.余力計算条件.取引停止区分 == "取引可能"の場合、<BR>
     * 　@　@　@　@資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの<BR>
     * 　@　@　@　@戻り値を返却する。<BR>
     * 　@　@　@　@[get営業日()にセットするパラメータ]<BR>
     * 　@　@　@　@　@指定日：　@決定した指定日<BR>
     * 　@４－３）４－２）以外の場合、(預り証券評価制顧客でない)<BR>
     * 　@　@以下の処理を、インデックスの範囲(0～5)について繰り返す。<BR>
     * 　@　@４－３－１）資産余力情報<現物顧客>.calc立替金()をコールする。<BR>
     * 　@　@　@[calc立替金()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@現在のインデックス<BR>
     * 　@　@４－３－２）４－３－１）の計算結果が0より大きい場合(0<計算結果)、かつ<BR>
     * 　@　@　@資産余力情報<現物顧客>.余力計算条件.取引停止区分 == "取引可能"の場合、<BR>
     * 　@　@　@資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * 　@　@　@[get営業日()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@現在のインデックス　@　@<BR>
     * <BR>
     *５）４）以外の場合、以下の処理を実施する。<BR>
     *　@５－１）１）の戻り値を資産余力情報<信用顧客>型にキャストする。<BR>
     *　@５－２）以下の処理を、インデックスの範囲(0～5)について繰り返す。<BR>
     *　@　@５－２－１）資産余力情報<信用顧客>.calc立替金()をコールする。<BR>
     *<BR>
     *　@　@　@[calc立替金()にセットするパラメータ]<BR>
     *　@　@　@　@指定日：　@現在のインデックス<BR>
     *<BR>
     *　@　@５－２－２）５－２－１）の計算結果が0より大きい場合(0<計算結果)、かつ<BR>
     *　@　@　@資産余力情報<信用顧客>.余力計算条件.is取引停止区分() == false(取引可能)の場合、<BR>
     *　@　@　@資産余力情報<信用顧客>.余力計算条件.get営業日()メソッドの<BR>
     *　@　@　@戻り値を返却する。<BR>
     *<BR>
     *　@　@　@[get営業日()にセットするパラメータ]<BR>
     *　@　@　@　@指定日：　@現在のインデックス　@　@<BR>
     *<BR>
     *６）nullを返却する。　@※立替金が発生していない為。<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    public Date getAdvanceGenDate(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAdvanceGenDate(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.error("パラメータ.Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.Null出来ない。");
        }
        //１）this.get資産余力情報()メソッドをコールする。
        Object l_objPowerInfo = this.getTradingPowerInfo(l_subAccount);
        //２）パラメータ.補助口座.getMainAccount()メソッドにて顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //３）顧客オブジェクト.is信用口座開設()メソッドをコールする。３）顧客オブジェクト.is信用口座開設()メソッドをコールする。       
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        // ４）パラメータ.is信用口座開設 == false(現物顧客)の場合、
        //以下の処理を実施する。　@
        if (!l_blnIsMarginAccount)
        {
            //　@４－１）１）の戻り値を資産余力情報<現物顧客>型にキャストする。
            WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
            //４－２）預り証券評価制顧客の場合、
            //資産余力情報<現物顧客>.余力計算条件.預り証券評価制区分 == true　@かつ
            //資産余力情報<現物顧客>.余力計算条件.預り証券評価制<現物株式評価> == true)
            if (l_powerCalcEquity.getCalcCondition().isAssetEvalDiv() 
                    && l_powerCalcEquity.getCalcCondition().isEquityEvalDiv())
            {
                //４－２－１）取引時間管理.is市場開局時間帯()メソッドをコールする。
                int l_intDate;
                if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
                {
                    //４－２－２）４－２－１）の戻り値 == falseの場合、指定日 = 1　@とする。
                    l_intDate = 1;
                }
                //以外、指定日 = 0　@とする。
                else
                {
                    l_intDate = 0;
                }
                /*
                //４－２－３）資産余力情報<現物顧客>.calc使用可能現金()をコールする。 
                double l_dblBalance = l_powerCalcEquity.calcActualAccountBalance(l_intDate); 
                //４－２－４）４－２－３）の計算結果がマイナス(<0)だった場合　@
                //かつ資産余力情報<現物顧客>.余力計算条件.取引停止区分 == "取引可能"の場合、
                if (l_dblBalance < 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
                {
                    //資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcEquity.getCalcCondition().getBizDate(l_intDate);
                }
                */
				//４－２－３）資産余力情報<現物顧客>.calc立替金()をコールする。 
				double l_dblBalance = l_powerCalcEquity.calcDebitAmount(l_intDate); 
				//４－２－４）４－２－３）の計算結果が0より大きい場合(0<計算結果)、　@
				//かつ資産余力情報<現物顧客>.余力計算条件.取引停止区分 == "取引可能"の場合、
				if (l_dblBalance > 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
				{
					//資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcEquity.getCalcCondition().getBizDate(l_intDate);
				}
                
            }
            //４－３）４－２）以外の場合、(預り証券評価制顧客でない)    
            else
            {
                //以下の処理を、インデックスの範囲(0～5)について繰り返す。
                for (int i = 0; i <= WEB3PvInfoTPBizDateCheckNumDef.MAX_DAYS; i++)
                {
                	/*
                    //４－３－１）資産余力情報<現物顧客>.calc使用可能現金()をコールする。
                    double l_dblBalance = l_powerCalcEquity.calcActualPaymentBalance(i);
                    //４－３－２）４－３－１）の計算結果がマイナス(<0)だった場合　@
                    //かつ資産余力情報<現物顧客>.余力計算条件.取引停止区分 == "取引可能"の場合、
                    if (l_dblBalance < 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
                    {
                        //資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
                        log.exiting(STR_METHOD_NAME);
                        return l_powerCalcEquity.getCalcCondition().getBizDate(i);
                    }
                    */
					//４－３－１）資産余力情報<現物顧客>.calc立替金()をコールする。
					double l_dblBalance = l_powerCalcEquity.calcDebitAmount(i);
					//４－３－２）４－３－１）の計算結果が0より大きい場合(0<計算結果)、　@
					//かつ資産余力情報<現物顧客>.余力計算条件.取引停止区分 == "取引可能"の場合、
					if (l_dblBalance > 0 && !l_powerCalcEquity.getCalcCondition().isTradingStop())
					{
						//資産余力情報<現物顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
						log.exiting(STR_METHOD_NAME);
						return l_powerCalcEquity.getCalcCondition().getBizDate(i);
					}
                    
                }
                
            }
        }
        //５）４）以外の場合、以下の処理を実施する。
        else
        {
            //５－１）１）の戻り値を資産余力情報<信用顧客>型にキャストする。
            WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
            //５－２）以下の処理を、インデックスの範囲(0～5)について繰り返す。
            for (int i = 0; i <= WEB3PvInfoTPBizDateCheckNumDef.MAX_DAYS; i++)
            {
            	/*
                //５－２－１）資産余力情報<信用顧客>.calc使用可能現金()をコールする。
                double l_dblBalance = l_powerCalcMargin.calcActualAccountBalance(i);
                //５－２－２）５－２－１）の計算結果がマイナス(<0)だった場合　@かつ
                //*資産余力情報<信用顧客>.余力計算条件.is取引停止区分() == false(取引可能)の場合、
                if (l_dblBalance < 0 && !l_powerCalcMargin.getCalcCondition().isTradingStop())
                {
                    //資産余力情報<信用顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
                    log.exiting(STR_METHOD_NAME);
                    return l_powerCalcMargin.getCalcCondition().getBizDate(i);
                }
                */
				//５－２－１）資産余力情報<信用顧客>.calc立替金()をコールする。
				double l_dblBalance = l_powerCalcMargin.calcDebitAmount(i);
				//５－２－２）５－２－１）の計算結果が0より大きい場合(0<計算結果)、　@かつ
				//*資産余力情報<信用顧客>.余力計算条件.is取引停止区分() == false(取引可能)の場合、
				if (l_dblBalance > 0 && !l_powerCalcMargin.getCalcCondition().isTradingStop())
				{
					//資産余力情報<信用顧客>.余力計算条件.get営業日()メソッドの戻り値を返却する。
					log.exiting(STR_METHOD_NAME);
					return l_powerCalcMargin.getCalcCondition().getBizDate(i);
				}
                
            }
        }
        // ６）nullを返却する。　@※立替金が発生していない為。
        return null;
    }
    
    /**
     * (get売買代金)<BR>
     * 引数の商品区分の売買代金合計を取得する。<BR>
     * ※売買区分を問わず単価 * 数量の絶対値の集計を行う。<BR>
     * 　@手数料、消費税は含まれない。<BR>
     * 　@現引・現渡の代金は加算されない。<BR>
     * １）パラメータ.商品区分により、処理対象のテーブル名を決定する。<BR>
     * 　@[パラメータ.商品区分 == ("0：現物" or "1：信用")の場合]<BR>
     * 　@　@テーブル名 = 株式注文単位テーブル(eqtype_order_unit)<BR>
     * 　@[パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合]<BR>
     * 　@　@テーブル名 = 先物OP注文単位テーブル(ifo_order_unit)<BR>
     * <BR>
     * ２）当日注文を取得する条件をパラメータ.検索条件文字列に追加する。<BR>
     * 　@パラメータ.検索条件文字列 += " and biz_date = ?"<BR>
     * <BR>
     * ３）業務日付(*1)を文字列変換(フォーマット：yyyyMMdd)し、<BR>
     * 　@パラメータ.検索条件データコンテナに追加する。<BR>
     * <BR>
     * ４）商品を判別する条件をパラメータ.検索条件文字列&<BR>
     * 　@　@パラメータ.検索条件データコンテナに追加する。<BR>
     * 　@パラメータ.商品区分が、<BR>
     * 　@["0：現物"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and order_categ = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and order_type not in (?, ?)"<BR>
     * 　@　@・パラメータ.検索条件データコンテナに以下の値を追加。<BR>
     * 　@　@　@　@・"1：現物注文"<BR>
     * 　@　@　@　@・"101：株式ミニ投資買注文"<BR>
     * 　@　@　@　@・"102：株式ミニ投資売注文"<BR>
     * 　@["1：信用"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and order_categ not in (?, ?) "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and order_type not in (?, ?)"<BR>
     * 　@　@・パラメータ.検索条件データコンテナに以下の値を追加。<BR>
     * 　@　@　@　@・"1：現物注文"<BR>
     * 　@　@　@　@・"7：現引・現渡注文"<BR>
     * 　@　@　@　@・"101：株式ミニ投資買注文"<BR>
     * 　@　@　@　@・"102：株式ミニ投資売注文"<BR>
     * 　@["2：先物"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and future_option_div = ? "<BR>
     * 　@　@・パラメータ.検索条件データコンテナに"1：先物"(先物／オプション区分)を追加。<BR>
     * 　@["3：オプション"の場合]<BR>
     * 　@　@・パラメータ.検索条件文字列 += "and future_option_div = ? "<BR>
     * 　@　@・パラメータ.検索条件データコンテナに"2：オプション"(先物／オプション区分)を追加。<BR>
     * <BR>
     * ５）QueryProcessor.doFindAllQuery()にて、注文単位を取得する。<BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@１）にて取得したテーブル名<BR>
     * 　@arg1：　@上記条件を追加したパラメータ.検索条件文字列<BR>
     * 　@arg4：　@上記条件を追加したパラメータ.検索条件データコンテナ<BR>
     * 　@検索結果が取得できなかった場合は、0を返却する。<BR>
     * <BR>
     * ６）売買代金合計を算出する。<BR>
     * 　@５）の戻り値の要素(=注文単位Row)数分、以下の処理を繰り返す。<BR>
     * 　@６－１）instanceofにて処理対象の注文単位Rowの型が以下のどちらなのかを判別し、<BR>
     * 　@　@対応する型にキャストする。<BR>
     * 　@　@　@・株式注文単位Row<BR>
     * 　@　@　@・先物OP注文単位Row<BR>
     * 　@　@６－１－１）キャストした注文単位Rowより、以下の項目値を取得する。<BR>
     * 　@　@　@・注文ID<BR>
     * 　@　@　@・注文単位ID<BR>
     * 　@　@　@・注文数量<BR>
     * 　@　@　@・注文単価<BR>
     * 　@　@　@・約定数量 ※nullの場合は、0とする。<BR>
     * 　@　@　@・指数乗数(*2)<BR>
     * 　@６－２）取消済でない取引(*3)のみ、未約定分の売買代金を集計する。<BR>
     * 　@　@　@売買代金 = 注文単価 * (注文数量 - 約定数量)<BR>
     * 　@６－３）約定数量 != 0の場合、約定分の売買代金を集計する。<BR>
     * 　@　@　@注文単位IDに該当するトランザクションを取得する。<BR>
     * 　@　@　@this.getトランザクション一覧()メソッドをコールする。<BR>
     * 　@　@　@[getトランザクション一覧()にセットするパラメータ]<BR>
     * 　@　@　@　@rowType：　@<BR>
     * 　@　@　@　@　@[パラメータ.商品区分 == ("0：現物" or "1：信用")の場合]<BR>
     * 　@　@　@　@　@　@EqtypeFinTransactionRow.TYPE<BR>
     * 　@　@　@　@　@[パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合]<BR>
     * 　@　@　@　@　@　@IfoFinTransactionRow.TYPEをセット。<BR>
     * 　@　@　@　@注文ID：　@注文ID<BR>
     * 　@　@　@　@注文単位ID：　@注文単位ID<BR>
     * 　@　@６－３－１）getトランザクション()の戻り値の要素数分、以下の処理を繰り返す。<BR>
     * 　@　@　@　@instanceofにて処理対象の要素の型が以下のどちらなのかを判別し、<BR>
     * 　@　@　@　@対応する型にキャストする。キャストした後、以下の計算式により売買代金に加算する。<BR>
     * 　@　@　@　@　@・EqtypeFinTransactionRow<BR>
     * 　@　@　@　@　@・IfoFinTransactionRow<BR>
     * 　@　@　@　@売買代金 += キャストしたトランザクションRow.約定単価 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@キャストしたトランザクションRow.約定数<BR>
     *     ６－４）パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合、<BR>
     * 　@　@　@売買代金に指数乗数をかける。<BR>
     * 　@　@　@売買代金 = 売買代金 * 指数乗数<BR>
     * 　@６－５）売買代金合計に加算する。<BR>
     * 　@　@　@売買代金合計 += 売買代金<BR>
     * <BR>
     * ７）売買代金合計を返却する。<BR>
     * (*1)業務日付<BR>
     * 　@GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付<BR>
     * (*2)先物OP注文単位Row型にキャストした場合は、<BR>
     * 　@this.get指数乗数()にて指数乗数を取得する。<BR>
     * 　@[get指数乗数()にセットするパラメータ]<BR>
     * 　@　@部店ID：　@キャストした注文単位Row.部店ID<BR>
     * 　@　@市場ID：　@キャストした注文単位Row.市場ID<BR>
     * 　@　@銘柄ID：　@キャストした注文単位Row.銘柄ID<BR>
     * (*3)取消済でない取引： 
     * 　@注文訂正・取消区分が2（一部取消完了）でない取引
     * @@param l_strProductDivDiv<BR>
     * @@param l_strQueryString<BR>
     * @@param l_strQueryDataContainers<BR>
     * @@return<BR>
     */
    public double getTradePrice(String l_strProductDivDiv,  String l_strQueryString, String[] l_strQueryDataContainers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getTradePrice(String l_strProductDivDef,  String l_strQueryString, String[] l_strQueryDataContainers)";
        log.entering(STR_METHOD_NAME);
        //１）パラメータ.商品区分により、処理対象のテーブル名を決定する。
        //[パラメータ.商品区分 == ("0：現物" or "1：信用")の場合]
        //テーブル名 = 株式注文単位テーブル(eqtype_order_unit)
        if (l_strQueryString == null || l_strQueryDataContainers == null)
        {
            log.error("パラメータ.Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.Null出来ない。");
        }
        RowType l_rowType = null;
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDivDiv)
                || WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDivDiv))
        {
            l_rowType = EqtypeOrderUnitRow.TYPE;
        }
        //　@[パラメータ.商品区分 == ("2：先物 " or "3：オプション")の場合]
        //テーブル名 = 先物OP注文単位テーブル(ifo_order_unit)
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDivDiv)
                || WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDivDiv))
        {
            l_rowType = IfoOrderUnitRow.TYPE;
        }
        //２）検索条件文字列&検索条件データコンテナ
        StringBuffer l_sbQueryString = new StringBuffer();
        //２）当日注文を取得する条件をパラメータ.検索条件文字列に追加する。
        l_sbQueryString.append(l_strQueryString);
        l_sbQueryString.append(" and biz_date = ?");
        //３）業務日付(*1)を文字列変換(フォーマット：yyyyMMdd)し、
        //パラメータ.検索条件データコンテナに追加する。
        //(*1)業務日付GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        
        List l_lisQueryVars = new ArrayList();
        for(int i = 0; i < l_strQueryDataContainers.length; i++)
        {
            l_lisQueryVars.add(l_strQueryDataContainers[i]);
        } 
        //フォーマット：yyyyMMdd
        l_datBizDate = WEB3DateUtility.toDay(l_datBizDate);
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");

        //パラメータ.検索条件データコンテナに追加
        l_lisQueryVars.add(l_strBizDate);
        
        //４）商品を判別する条件をパラメータ.検索条件文字列&パラメータ.検索条件データコンテナに追加する。
        //　@パラメータ.商品区分が、["0：現物"の場合] ・パラメータ.検索条件文字列 += "and order_categ = ? "
        //+ "and order_type not in (?, ?)"
        
        if (WEB3PvInfoProductDivDef.PD_SPOT.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and order_categ = ? and order_type not in (?, ?)");
            
            //　@　@・パラメータ.検索条件データコンテナに以下の値を追加。
           //・"1：現物注文"
          //・"101：株式ミニ投資買注文"
         //・"102：株式ミニ投資売注文"
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        //　@["1：信用"の場合]・パラメータ.検索条件文字列 +=
        // "and order_categ not in (?, ?) "+ "and order_type not in (?, ?)"
        else if (WEB3PvInfoProductDivDef.PD_CREDIT.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and order_categ not in (?, ?) "+ "and order_type not in (?, ?)");
            
            //　@　@・パラメータ.検索条件データコンテナに以下の値を追加。
            //・"1：現物注文"
            //・"7：現引・現渡注文"
            //・"101：株式ミニ投資買注文"
            //・"102：株式ミニ投資売注文"
            l_lisQueryVars.add(OrderCategEnum.ASSET);
            l_lisQueryVars.add(OrderCategEnum.SWAP_MARGIN);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_BUY);
            l_lisQueryVars.add(OrderTypeEnum.MINI_STOCK_SELL);
        }
        
        //　@["2：先物"の場合]
        //・パラメータ.検索条件文字列 += "and future_option_div = ? "
        //・パラメータ.検索条件データコンテナに"1：先物"(先物／オプション区分)を追加。
        else if (WEB3PvInfoProductDivDef.PD_FUTURE.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and future_option_div = ? ");
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.FUTURES);
        }
        
        //　@["3：オプション"の場合]
        //・パラメータ.検索条件文字列 += "and future_option_div = ? "
        //・パラメータ.検索条件データコンテナに"2：オプション"(先物／オプション区分)を追加。
        else if (WEB3PvInfoProductDivDef.PD_OPTION.equals(l_strProductDivDiv))
        {
            l_sbQueryString.append("and future_option_div = ? ");
            l_lisQueryVars.add(WEB3FuturesOptionDivDef.OPTION);
        }
        
        //５）QueryProcessor.doFindAllQuery()にて、注文単位を取得する。
        //　@[doFindAllQuery()にセットするパラメータ]
        //arg0：　@１）にて取得したテーブル名
        //arg1：　@上記条件を追加したパラメータ.検索条件文字列
        //arg4：　@上記条件を追加したパラメータ.検索条件データコンテナ
        List l_lstQuery = new ArrayList();
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            Object[] l_objVars = new Object[l_lisQueryVars.size()];
            l_lisQueryVars.toArray(l_objVars);

            log.debug("検索条件文字列: " + l_sbQueryString.toString());
            log.debug("検索条件データコンテナ: " + l_lisQueryVars.toString());

            l_lstQuery = l_queryProcessor.doFindAllQuery(
                l_rowType,
                l_sbQueryString.toString(),
                l_objVars);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //検索結果が取得できなかった場合は、0を返却する。
        if (l_lstQuery == null || l_lstQuery.size() == 0)
        {
            return 0;
        }
        //６）売買代金合計を算出する。
        //５）の戻り値の要素(=注文単位Row)数分、以下の処理を繰り返す。
        //　@６－１）instanceofにて処理対象の注文単位Rowの型が以下のどちらなのかを判別し、
        //対応する型にキャストする
        //・株式注文単位Row
        //・先物OP注文単位Row
        int l_intSize = l_lstQuery.size();
        //売買代金
        double l_dblTradePrice = 0D;                
        for (int i = 0; i < l_intSize; i++)
        {
            if (l_lstQuery.get(i) instanceof EqtypeOrderUnitRow)
            {
                
                //６－１－１）キャストした注文単位Rowより、以下の項目値を取得する。
                EqtypeOrderUnitRow l_eqtypeRow = (EqtypeOrderUnitRow)l_lstQuery.get(i);
                //注文ID
                long l_lngOrderId = l_eqtypeRow.getOrderId();
                log.debug("注文ID: " + l_lngOrderId);
                //注文単位ID
                long l_lngOrderUnitId = l_eqtypeRow.getOrderUnitId();
                log.debug("注文単位ID: " + l_lngOrderUnitId);                
                //注文数量
                double l_dblQuantity = l_eqtypeRow.getQuantity();
                log.debug("注文数量: " + l_dblQuantity);              
                //注文単価
                double l_dblPrice = l_eqtypeRow.getPrice();
                log.debug("注文単価: " + l_dblPrice);   
                //約定数量 ※nullの場合は、0とする。
                double l_dblExecutedQuantity = 0D;
                if (!l_eqtypeRow.getExecutedQuantityIsNull())
                {
                    l_dblExecutedQuantity = l_eqtypeRow.getExecutedQuantity();
                } 
                log.debug("約定数量: " + l_dblExecutedQuantity);   

                //　@６－２）取消済でない取引のみ、未約定分の売買代金を集計する。売買代金 = 注文単価 * (注文数量 - 約定数量) 
                double l_dblEqtypeTradePrice = 0D;
                if (!WEB3ModifyCancelTypeDef.PART_CANCELED.equals(l_eqtypeRow.getModifyCancelType()))
                {
                    l_dblEqtypeTradePrice = l_dblPrice * (l_dblQuantity - l_dblExecutedQuantity);
                    log.debug("未約定分売買代: " + l_dblEqtypeTradePrice);
                }
                    
                //６－３）約定数量 != 0の場合、約定分の売買代金を集計する。
                double l_dbEqtypelexecutedTradePrice = 0D;
                if (l_dblExecutedQuantity != 0)
                {
                    //注文単位IDに該当するトランザクションを取得する。
                    //this.getトランザクション一覧()メソッドをコールする。
                    List l_lstFinTransactions = this.getFinTransactionList(EqtypeFinTransactionRow.TYPE,l_lngOrderId,l_lngOrderUnitId);
                    //６－３－１）getトランザクション()の戻り値の要素数分、以下の処理を繰り返す。
                    int l_intFinTransactionLenght = 0;
                    if (l_lstFinTransactions != null)
                    {
                        l_intFinTransactionLenght = l_lstFinTransactions.size();
                    }
                    
                    for (int k = 0; k < l_intFinTransactionLenght; k++)
                    {
                        EqtypeFinTransactionRow l_row = (EqtypeFinTransactionRow)l_lstFinTransactions.get(k);
                        //売買代金 += キャストしたトランザクションRow.約定単価 *キャストしたトランザクションRow.約定数量
                        l_dbEqtypelexecutedTradePrice += l_row.getPrice() * l_row.getQuantity();
                        log.debug("約定売買代: " + l_dbEqtypelexecutedTradePrice);
                    }
                }   
                //売買代金合計 += 売買代金
                l_dblTradePrice += (l_dblEqtypeTradePrice + l_dbEqtypelexecutedTradePrice); 
                log.debug("売買代金合計: " + l_dblTradePrice);            
            }
            else if (l_lstQuery.get(i) instanceof IfoOrderUnitRow)
            {
                //６－１－１）キャストした注文単位Rowより、以下の項目値を取得する。
                IfoOrderUnitRow l_ifoRow = (IfoOrderUnitRow)l_lstQuery.get(i);
                //注文ID
                long l_lngOrderId = l_ifoRow.getOrderId();
                log.debug("注文ID: " + l_lngOrderId); 
                //注文単位ID
                long l_lngOrderUnitId = l_ifoRow.getOrderUnitId();
                log.debug("注文単位ID: " + l_lngOrderUnitId);                 
                //注文数量
                double l_dblQuantity = l_ifoRow.getQuantity();
                log.debug("注文数量: " + l_dblQuantity); 
                //注文単価
                double l_dblPrice = l_ifoRow.getPrice();
                log.debug("注文単価: " + l_dblPrice); 
                //約定数量 ※nullの場合は、0とする。
                double l_dblExecutedQuantity = 0D;
                
                if (!l_ifoRow.getExecutedQuantityIsNull())
                {
                    l_dblExecutedQuantity = l_ifoRow.getExecutedQuantity();
                } 
                log.debug("約定数量: " + l_dblExecutedQuantity); 
                //指数乗数(*2)            

                //　@６－２）取消済でない取引のみ、未約定分の売買代金を集計する。売買代金 = 注文単価 * (注文数量 - 約定数量) 
                double l_dblIfoTradePrice = 0D;
                if (!WEB3ModifyCancelTypeDef.PART_CANCELED.equals(l_ifoRow.getModifyCancelType()))
                {
                    l_dblIfoTradePrice = l_dblPrice * (l_dblQuantity - l_dblExecutedQuantity);
                    log.debug("未約定分の売買代金を集計する。: " + l_dblIfoTradePrice); 
                }
                
                //６－３）約定数量 != 0の場合、約定分の売買代金を集計する。
                double l_dblIfoExecutedTradePrice = 0D;
                if (l_dblExecutedQuantity != 0)
                {
                    //注文単位IDに該当するトランザクションを取得する。
                    //this.getトランザクション一覧()メソッドをコールする。
                    List l_lstFinTransactions = this.getFinTransactionList(IfoFinTransactionRow.TYPE,l_lngOrderId,l_lngOrderUnitId);
                    //６－３－１）getトランザクション()の戻り値の要素数分、以下の処理を繰り返す。
                    int l_intFinTransactionLenght = 0;
                    if (l_lstFinTransactions != null)
                    {
                        l_intFinTransactionLenght = l_lstFinTransactions.size();
                    }
                    
                    for (int k = 0; k < l_intFinTransactionLenght; k++)
                    {
                        IfoFinTransactionRow l_row = (IfoFinTransactionRow)l_lstFinTransactions.get(k);
                        //売買代金 += キャストしたトランザクションRow.約定単価 *キャストしたトランザクションRow.約定数量
                        log.debug("キャストしたトランザクションRow.約定単価 : " + l_row.getPrice()); 
                        log.debug("キャストしたトランザクションRow.約定数量 : " + l_row.getQuantity()); 
                        l_dblIfoExecutedTradePrice += l_row.getPrice() * l_row.getQuantity();
                    }
                }
                
                //(*2)先物OP注文単位Row型にキャストした
                //this.get指数乗数()にて指数乗数を取得する。
                double l_dblUnitSize = this.getUnitSize(l_ifoRow.getBranchId(),l_ifoRow.getMarketId(),l_ifoRow.getProductId());
                log.debug("指数乗数: " + l_dblUnitSize);
                //売買代金合計 += 売買代金
                l_dblTradePrice += (l_dblIfoTradePrice + l_dblIfoExecutedTradePrice) * l_dblUnitSize;
                log.debug("売買代金合計: " + l_dblTradePrice);  
            }
        }
        log.exiting(STR_METHOD_NAME);
        //７）売買代金合計を返却する。
        return l_dblTradePrice;
    }
    /**
     * (getトランザクション一覧)<BR>
     * トランザクションの一覧を取得する。<BR>
     * １）以下の条件に該当するデータを<BR>
     * パラメータ.rowTypeに該当するトランザクションテーブルから取得する。<BR>
     * (doFindAllQueryを使用)<BR>
     * <BR>
     * [条件]<BR>
     * 注文ID　@=　@パラメータ.注文ID　@かつ<BR>
     * 注文単位ID　@=　@パラメータ.注文単位ID　@かつ<BR>
     * 削除フラグ　@=　@FALSE<BR>
     * 検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ２）検索結果を返却する。<BR>
     * @@param l_rowType
     * @@param l_lngOrderId
     * @@param l_lngOrderUnitId
     * @@return
     */
    public List getFinTransactionList(RowType l_rowType, long l_lngOrderId, long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinTransactionList(RowType l_rowType, long l_lngOrderId, long l_lngOrderUnitId)";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            String l_strWhere = "order_id = ? and order_unit_id = ? and delete_flag= ? ";
            QueryProcessor l_qp = l_qp = Processors.getDefaultProcessor();
            List l_lisRows = null;
            Object[] l_objWhereValues = { new Long(l_lngOrderId), new Long(l_lngOrderUnitId), BooleanEnum.FALSE};
            l_lisRows = l_qp.doFindAllQuery(l_rowType, l_strWhere, l_objWhereValues);
            
            if (l_lisRows == null || l_lisRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisRows;
        }
        catch (DataNetworkException l_dnwe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_dnwe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dnwe.getMessage(), l_dnwe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
    }
    /**
     * (get営業日一覧)<BR>
     * 営業日の一覧を返却する。<BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）日付のセット<BR>
     * 　@・ArrayListに業務日付(*1)の日付部分(YYYYMMDD)を追加する。<BR>
     * 　@・ArrayListに業務日付の翌営業日の日付部分(YYYYMMDD)を追加する。<BR>
     * <BR>
     * ３）ArrayList.toArray()の戻り値を返却する。<BR>
     * @@return<BR>
     */
    public Date[] getBizDateList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBizDateList()";
        log.entering(STR_METHOD_NAME);
        //１）ArrayListを生成する。
        List l_datBizDateList = new ArrayList();
        
        //２）日付のセット
        //ArrayListに業務日付(*1)の日付部分(YYYYMMDD)を追加する。
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        l_datBizDateList.add(WEB3DateUtility.toDay(l_datBizDate));
        //ArrayListに業務日付の翌営業日の日付部分(YYYYMMDD)を追加する。
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_datBizDateList.add(WEB3DateUtility.toDay(l_bizDate.roll(1)));
        
        // ３）ArrayList.toArray()の戻り値を返却する。
        Date[] l_datBizDates = new Date[l_datBizDateList.size()];
        l_datBizDateList.toArray(l_datBizDates);
        
        log.exiting(STR_METHOD_NAME);
        return l_datBizDates;
    }
    
    /**
     * (get指数乗数)<BR>
     * 指数乗数を取得し返却する。<BR>
     * １）取引カレンダコンテキストを先物OPの設定にする。<BR>
     * 　@以下の値で取引カレンダコンテキストをリセットする。<BR>
     * 　@※リセット前の各値は退避させておくこと。<BR>
     * 　@取引カレンダコンテキスト.市場コード = "0：その他"<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = パラメータ銘柄IDに該当する先物OP銘柄オブジェクト,get原資産銘柄コード()<BR>
     * ２）先物OP取引銘柄の取得<BR>
     * 　@先物OPプロダクトマネージャ.get取引銘柄()にて<BR>
     * 　@先物OP取引銘柄を取得する。<BR>
     * 　@[get取引銘柄()にセットするパラメータ]<BR>
     * 　@　@証券会社：　@パラメータ.部店IDに該当する部店.getInstitution()<BR>
     * 　@　@銘柄コード：　@取得した先物OP銘柄オブジェクト.銘柄コード<BR>
     * 　@　@市場コード：　@パラメータ.市場IDに該当する市場.市場コード<BR>
     * ３）取引カレンダコンテキストをﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝの設定に戻す。<BR>
     * 　@１）にて退避させた値で、取引カレンダコンテキストのリセットを行う。<BR>
     * ４）取得した先物OP取引銘柄.１単位当り乗数を返却する。<BR>
     * @@param l_lngBranchId<BR>
     * @@param l_lngMarketId<BR>
     * @@param l_lngProductId<BR>
     * @@return<BR>
     */
    public double getUnitSize(long l_lngBranchId,long l_lngMarketId, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUnitSize(long l_lngBranchId,long l_lngMarketId, long l_lngProductId)";
        log.entering(STR_METHOD_NAME);
        
        //１）取引カレンダコンテキストを先物OPの設定にする。
        //以下の値で取引カレンダコンテキストをリセットする。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_manager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();     
        WEB3GentradeTradingClendarContext l_context = (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //２）先物OP取引銘柄の取得
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

        WEB3GentradeAccountManager l_accountManger = (WEB3GentradeAccountManager)l_finApp.getAccountManager();    
        
        String l_strOldMaketCode = l_context.getMarketCode();
        String l_strOldTradingTimeType = l_context.getTradingTimeType();
        String l_strOldProductCode = l_context.getProductCode();
        try
        {
            //取引カレンダコンテキスト.市場コード = "0：その他"
            String l_strMaketCode = l_manager.getMarket(l_lngMarketId).getMarketCode();
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP”
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

            
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl)l_productManager.getProduct(l_lngProductId);
            //取引カレンダコンテキスト.銘柄コード = パラメータ銘柄IDに該当する先物OP銘柄オブジェクト,get原資産銘柄コード()
            l_context.setProductCode(l_product.getUnderlyingProductCode());
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
            
            //証券会社：　@パラメータ.部店IDに該当する部店.getInstitution()
            Institution l_institution = l_accountManger.getBranch(l_lngBranchId).getInstitution();
            // 先物OPプロダクトマネージャ.get取引銘柄()にて
            WEB3IfoTradedProductImpl l_tradedProduct = l_productManager.getIfoTradedProduct(l_institution,l_product.getProductCode(),l_strMaketCode);
           
            //３）取引カレンダコンテキストをﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝの設定に戻す
            l_context.setMarketCode(l_strOldMaketCode);
            l_context.setTradingTimeType(l_strOldTradingTimeType);
            l_context.setProductCode(l_strOldProductCode);
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,l_context);
           
            //４）取得した先物OP取引銘柄.１単位当り乗数を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_tradedProduct.getUnitSize();
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (is建玉強制処分)<BR>
     * 強制処分の対象となる建玉を<BR> 
     * 保有しているか判別する。<BR>
     * <BR>
     * 保有している場合true、以外falseを返却する。<BR> 
     * <BR>
     * １）this.get入金請求管理信用Paramsをコールする。<BR> 
     * <BR>
     * 　@[get入金請求管理信用Paramsにセットするパラメータ]<BR>  
     * 　@　@顧客：　@顧客オブジェクト <BR>
     * <BR>
     * ２）１）の取得結果がnullの場合、falseを返却する。<BR> 
     * <BR>
     * ３）１）の取得結果から次の値を取得する<BR> 
     * 　@20%割れ日数 = 入金請求管理信用Params.20%割れ発生経過日数<BR> 
     * 　@30%割れ日数 = 入金請求管理信用Params.30%割れ発生経過日数<BR>
     * <BR>
     * ４）20%割れ日数、30%割れ日数から強制処分の対象か判断する<BR> 
     * <BR>
     * 　@４－１）パラメータ.表示条件番号 = "1041" かつ<BR> 
     * 　@　@　@　@　@20%割れ日数 = 1 かつ <BR>
     * 　@　@　@　@　@30%割れ日数 <= 5 の場合、true を返却する。<BR> 
     * 　@４－２）パラメータ.表示条件番号 = "1042" かつ <BR>
     * 　@　@　@　@　@20%割れ日数 = 1 かつ <BR>
     * 　@　@　@　@　@30%割れ日数 = 6 の場合、true を返却する。<BR> 
     * 　@４－３）パラメータ.表示条件番号 = "1043" かつ <BR>
     * 　@　@　@　@　@20%割れ日数 = 2 かつ <BR>
     * 　@　@　@　@　@30%割れ日数 <= 6 の場合、true を返却する。<BR> 
     * 　@４－４）パラメータ.表示条件番号 = "1044" かつ <BR>
     * 　@　@　@　@　@20%割れ日数 >= 3 の場合、true を返却する。<BR> 
     * 　@４－５）パラメータ.表示条件番号 = "1045" かつ <BR>
     * 　@　@　@　@　@30%割れ日数 >= 2 かつ <BR>
     * 　@　@　@　@　@30%割れ日数 <= 4 の場合、true を返却する。<BR> 
     * 　@４－６）パラメータ.表示条件番号 = "1046" かつ <BR>
     * 　@　@　@　@　@30%割れ日数 = 5 の場合、true を返却する。<BR> 
     * 　@４－７）パラメータ.表示条件番号 = "1047" かつ <BR>
     * 　@　@　@　@　@30%割れ日数 = 6 の場合、true を返却する。<BR> 
     * 　@４－８）パラメータ.表示条件番号 = "1048" かつ<BR> 
     * 　@　@　@　@　@30%割れ日数 >= 7 の場合、true を返却する。<BR> 
     * <BR>
     * 　@上記以外の場合、falseを返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)
     * 顧客オブジェクト<BR>
     * @@param l_strConditionNo - (表示条件番号)
     * 表示条件設定Params.表示条件番号<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isContractEnforcedDisposal(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strConditionNo)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isContractEnforcedDisposal(WEB3GentradeMainAccount l_mainAccount, String l_strConditionNo)";
        log.entering(STR_METHOD_NAME);
        
        //１）this.get入金請求管理信用Paramsをコールする。
        PaymentRequisitionMarginRow l_marginRow = this.getPaymentRequisitionMarginParams(l_mainAccount);
        
        //２）１）の取得結果がnullの場合、falseを返却する。
        if (l_marginRow == null) 
        {
            return false;
        }

        //３）１）の取得結果から次の値を取得する
        //20%割れ日数 = 入金請求管理信用Params.20%割れ発生経過日数
        String l_strBreak20ElapsedDays = l_marginRow.getBreak20elapsedDays();
        
        //30%割れ日数 = 入金請求管理信用Params.30%割れ発生経過日数
        String l_strBreak30ElapsedDays = l_marginRow.getBreak30elapsedDays();
        
        //４）20%割れ日数、30%割れ日数から強制処分の対象か判断する
        //４－１）パラメータ.表示条件番号 = "1041" かつ20%割れ日数 = 1 かつ
        //      30%割れ日数 <= 5 の場合、true を返却する。
        if (l_strBreak20ElapsedDays == null || l_strBreak30ElapsedDays == null)
        {
            return false;
        }
        int l_intBreak20ElapsedDays = Integer.parseInt(l_strBreak20ElapsedDays);
        int l_intBreak30ElapsedDays = Integer.parseInt(l_strBreak30ElapsedDays);
        
        if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays == 1 &&
            l_intBreak30ElapsedDays <= 5)
        {
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        
        //４－２）パラメータ.表示条件番号 = "1042" かつ 20%割れ日数 = 1 かつ
        //30%割れ日数 = 6 の場合、true を返却する。
        else if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays == 1 &&
            l_intBreak30ElapsedDays == 6) 
        {
            log.exiting(STR_METHOD_NAME);
            return true; 
        }
        
        //４－３）パラメータ.表示条件番号 = "1043" かつ 20%割れ日数 = 2 かつ
        //30%割れ日数 <= 6 の場合、true を返却する。
        else if (WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays == 2 &&
            l_intBreak30ElapsedDays <= 6) 
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //４－４）パラメータ.表示条件番号 = "1044" かつ20%割れ日数 >= 3 の場合、true を返却する。
        else if (WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(l_strConditionNo) &&
            l_intBreak20ElapsedDays >= 3) 
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //　@４－５）パラメータ.表示条件番号 = "1045" かつ 30%割れ日数 >= 2 かつ 
        //　@　@　@　@　@30%割れ日数 <= 4 の場合、true を返却する。 
        else if (WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays >= 2 &&
            l_intBreak30ElapsedDays <= 4)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //　@４－６）パラメータ.表示条件番号 = "1046" かつ 
        //　@　@　@　@　@30%割れ日数 = 5 の場合、true を返却する。
        else if (WEB3PvInfoConditionDef.BREAK_5DAY.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays == 5)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //　@４－７）パラメータ.表示条件番号 = "1047" かつ 
        //　@　@　@　@　@30%割れ日数 = 6 の場合、true を返却する。
        else if (WEB3PvInfoConditionDef.BREAK_6DAY.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays == 6)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //　@４－８）パラメータ.表示条件番号 = "1048" かつ 
        //　@　@　@　@　@30%割れ日数 >= 7 の場合、true を返却する。
        else if (WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(l_strConditionNo) &&
            l_intBreak30ElapsedDays >= 7)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //上記以外の場合、falseを返却する。 
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get入金請求管理信用Params)<BR>
     * 入金請求管理信用Paramsを取得する。<BR> 
     * <BR>
     * １）検索条件文字列&検索条件データコンテナ<BR> 
     * 　@以下の検索条件を表す、検索条件文字列と <BR>
     * 　@ArrayList(検索条件データコンテナ)を作成する。<BR> 
     * <BR>
     * 　@[条件]<BR> 
     * 　@　@口座ID = パラメータ.顧客.口座ID<BR> 
     * 　@　@計算日 = 業務日付 <BR>
     * <BR>
     * 　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR> 
     * <BR>
     * 　@　@検索条件文字列 = " account_id = ? "<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ " calc_date = ? "<BR> 
     * <BR>
     * 　@１－２）"?"にセットするためのパラメータセットを作成する。<BR> 
     * 　@　@ArrayListを生成し、以下の値をセットする。 <BR>
     * 　@　@　@・パラメータ.顧客.getAccountId()　@※文字列変換してセット。<BR> 
     * 　@　@　@・GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付<BR> 
     * 　@ <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR> 
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR> 
     * 　@arg0：　@入金請求管理テーブル（信用） <BR>
     * 　@arg1：　@作成した検索条件文字列 <BR>
     * 　@arg2：　@作成したArrayList.toArray()<BR> 
     * <BR>
     * 　@検索結果が取得できなかった場合はnullを返却する。<BR> 
     * <BR>
     * ３）２）の検索結果を返却する。<BR> 
     * <BR>
     * @@param l_mainAccount - (顧客)
     * 顧客オブジェクト<BR>
     * @@return PaymentRequisitionMarginParams 
     * @@throws WEB3BaseException 
     */
    public PaymentRequisitionMarginParams getPaymentRequisitionMarginParams(
        WEB3GentradeMainAccount l_mainAccount)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getPaymentRequisitionMarginParams(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);        
              
        //　@１－１）上記検索条件を基に、検索条件文字列を作成する。 
        //
        //　@　@検索条件文字列 = " account_id = ? " 
        //　@　@　@　@　@　@　@　@　@　@　@　@　@+ " calc_date = ? " 
        String l_strQuery = " account_id = ? and calc_date = ? ";
        
        //　@１－２）"?"にセットするためのパラメータセットを作成する。 
        //　@　@ArrayListを生成し、以下の値をセットする。 
        //　@　@　@・パラメータ.顧客.getAccountId()　@※文字列変換してセット。 
        //　@　@　@・GtlUtils.getTradingSystem().getBizDate()にて取得した業務日付 
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(String.valueOf(l_mainAccount.getAccountId()));
        l_lisWhere.add(GtlUtils.getTradingSystem().getBizDate()); 
        
        //２）QueryProcessor.doFindAllQuery()メソッドをコールする。 
        //
        //　@[doFindAllQuery()にセットするパラメータ] 
        //　@arg0：　@入金請求管理テーブル（信用） 
        //　@arg1：　@作成した検索条件文字列 
        //　@arg2：　@作成したArrayList.toArray()        
        List l_lisRecords = null;
        try 
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                PaymentRequisitionMarginRow.TYPE,
                l_strQuery,
                l_lisWhere.toArray());
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //　@検索結果が取得できなかった場合はnullを返却する。 
        if (l_lisRecords == null || l_lisRecords.size() == 0)  
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //３）２）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return (PaymentRequisitionMarginParams) l_lisRecords.get(0);
    }
    
    /**
     * (is出金停止)<BR>
     *出金停止顧客か判別する。<BR>                 
     * <BR>                                        
     *出金停止顧客の場合true、以外falseを返却する。<BR> 
     *１）this.get担保不足顧客データParamsをコールする。<BR> 
     *<BR>                                         
     *　@[get担保不足顧客データParamsにセットするパラメータ]<BR>  
     *　@　@顧客：　@顧客オブジェクト <BR>            
     *　@　@出金停止区分：　@パラメータ.出金停止区分 <BR>
     *<BR>                                       
     *２）１）検索結果が取得できた場合はtrue、<BR> 
     *　@取得できなかった場合はfalseを返却する。<BR>
     *@@param l_mainAccount-(顧客)<BR>
     *顧客オブジェクト<BR>
     *@@param l_strCashoutStopDiv-出金停止区分 <BR>
     *出金停止区分<BR>
     *<BR>
     *1：全額<BR>
     *2：一部<BR>
     *@@return boolean 
     *@@throws WEB3BaseException
     */
    public boolean isCashoutStop(WEB3GentradeMainAccount l_mainAccount,
        String l_strCashoutStopDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isCashoutStop(WEB3GentradeMainAccount l_mainAccount,String l_strCashoutStopDiv)";
        log.entering(STR_METHOD_NAME); 
        SecurityShortageAccountParams l_params = null;
        //１）this.get担保不足顧客データParamsをコールする。
        l_params =  this.getSecurityShortageAccountParams(l_mainAccount,l_strCashoutStopDiv);
        
        //２）１）検索結果が取得できた場合はtrue、 
       //取得できなかった場合はfalseを返却する。
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get担保不足顧客データParams)<BR>
     *条件に該当する担保不足顧客データParamsを取得する。<BR> 
     *<BR>
     *１）検索条件文字列&検索条件データコンテナ<BR> 
     *　@以下の検索条件を表す、検索条件文字列と <BR>
     *　@ArrayList(検索条件データコンテナ)を作成する。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@　@口座ID = パラメータ.顧客.口座ID <BR>
     *　@　@出金停止区分 = パラメータ.出金停止区分 <BR>
     *<BR>
     *　@１－１）上記検索条件を基に、検索条件文字列を作成する。<BR> 
     *<BR>
     *　@　@検索条件文字列 = " account_id = ? " <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@+ " payment_stop_div = ? " <BR>
     *<BR>
     *　@１－２）"?"にセットするためのパラメータセットを作成する。 <BR>
     *　@　@ArrayListを生成し、以下の値をセットする。 <BR>
     *　@　@　@・パラメータ.顧客.getAccountId()　@※文字列変換してセット。 <BR>
     *　@　@　@・パラメータ.出金停止区分 <BR>
     *<BR>
     *２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR> 
     *<BR>
     *　@[doFindAllQuery()にセットするパラメータ] <BR>
     *　@arg0：　@担保不足顧客データテーブル <BR>
     *　@arg1：　@作成した検索条件文字列 <BR>
     *　@arg2：　@作成したArrayList.toArray() <BR>
     *<BR>
     *　@検索結果が取得できなかった場合はnullを返却する。 <BR>
     *<BR>
     *３）２）の検索結果を返却する。<BR>
      *@@param l_mainAccount-(顧客)<BR>
     *顧客オブジェクト<BR>
     *@@param l_strCashoutStopDiv-出金停止区分 <BR>
     *出金停止区分<BR>
     *<BR>
     *1：全額<BR>
     *2：一部<BR>
     *@@return 担保不足顧客データParams  
     *@@throws WEB3BaseException
     */
    public SecurityShortageAccountParams getSecurityShortageAccountParams(
        WEB3GentradeMainAccount l_mainAccount,
        String l_strCashoutStopDiv)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getSecurityShortageAccountParams(WEB3GentradeMainAccount l_mainAccount,String l_strCashoutStopDiv)";
        log.entering(STR_METHOD_NAME); 
        
        //１－１）上記検索条件を基に、検索条件文字列を作成する。
        //検索条件文字列 = " account_id = ? " 
        //                       + " payment_stop_div = ? " 
        String l_strQuery = " account_id = ? and payment_stop_div = ? ";
        
        //１－２）"?"にセットするためのパラメータセットを作成する。 
        //ArrayListを生成し、以下の値をセットする。 
        //・パラメータ.顧客.getAccountId()　@※文字列変換してセット。 
        //・パラメータ.出金停止区分 
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(String.valueOf(l_mainAccount.getAccountId()));
        l_lisWhere.add(l_strCashoutStopDiv); 
        
        //２）QueryProcessor.doFindAllQuery()メソッドをコールする。 
        //[doFindAllQuery()にセットするパラメータ] 
        //arg0：　@担保不足顧客データテーブル 
        //arg1：　@作成した検索条件文字列 
        //arg2：　@作成したArrayList.toArray() 
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                SecurityShortageAccountRow.TYPE,
                l_strQuery,
                l_lisWhere.toArray());

            log.debug("QueryProcessor.doFindByPrimaryKeyQuery()を執行します");
        }
//        catch(DataFindException l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage());
//            log.exiting(STR_METHOD_NAME);
//            return null;
//        }        
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }         
        //検索結果が取得できなかった場合はnullを返却する。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //３）２）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return (SecurityShortageAccountParams) l_lisRecords.get(0);
    }
    
    /**
     * (is手数料割引キャンペーン)<BR>
	 * 手数料割引キャンペーン対象顧客か判別する。<BR>
	 * <BR>
	 * 手数料割引キャンペーン対象顧客の場合true、以外falseを返却する。<BR>
     * １）this.get割引キャンペーン顧客Paramsをコールする。<BR>
     * <BR>
     * [get割引キャンペーンParamsにセットするパラメータ]<BR>
     * 顧客：　@顧客オブジェクト<BR>
     * <BR>
     * ２）１）検索結果が取得できた場合はtrue、取得できなかった場合はfalseを返却する。<BR>
     * @@param l_mainAccount-(顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
	public boolean isAccInfoCampaign(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isAccInfoCampaign(WEB3GentradeMainAccount l_mainAccount)";
		log.entering(STR_METHOD_NAME);
		
		// パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }
        
		// １）this.get割引キャンペーン顧客Paramsをコールする。
		CommCampaignAccHistoryParams [] l_commCampaignAccHistoryParams = 
			this.getCommCampaignAccHistoryParams(l_mainAccount);

		// ２）１）検索結果が取得できた場合はtrue、取得できなかった場合はfalseを返却する。
		if (l_commCampaignAccHistoryParams == null)
		{
			log.exiting(STR_METHOD_NAME);
			return false;
		}
		log.exiting(STR_METHOD_NAME);
		return true;
	}
	
    /**    
     * (get割引キャンペーン顧客Params)<BR>
     * 手数料割引キャンペーン顧客履歴Paramsを取得する。<BR>
     * <BR>
     * 条件に該当する手数料割引キャンペーン顧客履歴Paramsを取得する。<BR>
     * １）検索条件文字列&検索条件データコンテナ以下の検索条件を表す、検索条件文字列と<BR>
     * ArrayList(検索条件データコンテナ)を作成する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 顧客ID = 顧客.getAccountId（） and <BR>
     * 対象期間From <= 翌々営業日 and <BR>
     * 対象期間To >= 当日営業日 and <BR>
     * 有効区分 = １：有効<BR>
     * <BR>
     * [ソート条件]<BR>
     * 対象期間From.asc<BR>
     * <BR>
     * ２）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * ２－１）検索結果が取得できなかった場合はnullを返却する。<BR>
     * ２－２）取得したList中に【登録タイプ="２：強制個別顧客指定"】の<BR>
     *        データが含まれる場合はnullを返却する。<BR>
     * <BR>
     * 検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ３）２）の検索結果を返却する。<BR>
     * @@param l_mainAccount-(顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return CommCampaignAccHistoryParams[]
     * @@throws WEB3BaseException
     */
	public CommCampaignAccHistoryParams[] getCommCampaignAccHistoryParams(
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
	{
        final String STR_METHOD_NAME = 
            "getCommCampaignAccHistoryParams(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);
        
		// パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }
        
        //１）検索条件文字列&検索条件データコンテナ 以下の検索条件を表す、検索条件文字列と
        //　@  ArrayList(検索条件データコンテナ)を作成する。
        String l_strQueryCondition = "account_id = ? and " +
        							 "appli_start_date <= ? and " +
                                     "appli_end_date >= ? and "+
                                     "valid_div = ?";

        
        String l_strSortCondition = " appli_start_date asc " ;
        
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(String.valueOf(l_mainAccount.getAccountId()));
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        l_lisWhere.add(l_bizDate.roll(2)); 
        l_lisWhere.add(l_datBizDate);
        l_lisWhere.add(WEB3ValidDivDef.EFFECTIVE);

        // ２）QueryProcessor.doFindAllQuery()メソッドをコールする。
        List l_lisRecords = null;
        try 
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
            	CommCampaignAccHistoryRow.TYPE,
            	l_strQueryCondition,
            	l_strSortCondition,
            	null,
            	l_lisWhere.toArray());
        }
        catch (DataFindException l_ex)
        {
            log.debug("検索結果が取得できなかった場合はnullを返却する。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //　@２－１）検索結果が取得できなかった場合はnullを返却する。 
        if (l_lisRecords == null || l_lisRecords.isEmpty())  
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        // ２－２）取得したList中に【登録タイプ="２：強制個別顧客指定"】のデータが含まれる場合はnullを返却する。
        for (int i = 0; i < l_lisRecords.size(); i++)
		{
        	if(WEB3AccInfoRegistTypeDef.FORCE_INDIVIDUAL.equals(
        			((CommCampaignAccHistoryParams)l_lisRecords.get(i)).getRegistType()))
        	{
        		return null;
        	}
        }
        
        // ３）２）の検索結果を返却する。
        log.exiting(STR_METHOD_NAME);
        CommCampaignAccHistoryParams[] l_CommCampaignAccHistoryParams= new CommCampaignAccHistoryParams[l_lisRecords.size()];
        l_lisRecords.toArray(l_CommCampaignAccHistoryParams);        
        return l_CommCampaignAccHistoryParams;
	}
	
	/**
	 * (get商品コード)<BR>
	 * 手数料割引キャンペーン商品マスタから商品コードを取得する。<BR>
	 * <BR>
	 * １）手数料割引キャンペーン商品マスタからレコードを取得<BR>
	 * <BR>
	 * [検索条件]<BR>
	 *   キャンペーン手数料条件ID = 引数.キャンペーン手数料条件ID<BR>
	 *   <BR>
	 * ２）取得したListから、Loop処理にて商品コードのListを生成<BR>
	 * <BR>
	 * ３）２）で生成した商品コードListをString型の配列に変換し、返却<BR>
	 * @@param l_lngAccInfoConditionId-(キャンペーン手数料条件ID)<BR>
	 * @@return String[]
	 * @@throws WEB3BaseException
	 */
	public String[] getCommProductCodes(long l_lngAccInfoConditionId) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getCommProductCodes(long l_lngAccInfoConditionId)";
		log.entering(STR_METHOD_NAME);
		// １）手数料割引キャンペーン商品マスタからレコードを取得
		String l_strQueryCondition = "campaign_id = ?";
		Object[] l_objValue = { String.valueOf(l_lngAccInfoConditionId) };

		List l_lisRecords = null;
		List l_lisProductCodes = new ArrayList();
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_queryProcessor.doFindAllQuery(
				CommCampaignProductMstRow.TYPE,
				l_strQueryCondition,
				l_objValue);
		}
		catch (DataFindException l_ex)
		{
			log.error("DBへのアクセスに失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			return null;
		}
		catch (DataNetworkException l_ex)
		{
			log.error("DBへのアクセスに失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
					this.getClass().getName()
					+ STR_METHOD_NAME, 
					l_ex.getMessage(), 
					l_ex);
		}
		catch (DataQueryException l_ex)
		{
			log.error("DBへのアクセスに失敗しました。", l_ex);
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
					this.getClass().getName()
					+ STR_METHOD_NAME, 
					l_ex.getMessage(), 
					l_ex);
		}
 
        if (l_lisRecords == null || l_lisRecords.isEmpty())  
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
					this.getClass().getName()
					+ STR_METHOD_NAME);
        }

		// ２）取得したListから、Loop処理にて商品コードのListを生成
		for (int i = 0; i < l_lisRecords.size(); i++)
		{
			l_lisProductCodes.add(((CommCampaignProductMstParams)l_lisRecords.get(i)).getCommProductCode());
		}
		// ３）２）で生成した商品コードListをString型の配列に変換し、返却
		log.exiting(STR_METHOD_NAME);
		return (String[])l_lisProductCodes.toArray(new String[l_lisProductCodes.size()]);
	}

    /**
     * (isモバイル専用口座開設)<BR>
     * モバイル専用口座の開設／未開設を判別する。  <BR>
     * <BR>
     * モバイル専用口座開設の場合true、以外(モバイル専用口座未開設)の場合falseを返却する。 <BR>
     * <BR>
     * １）　@顧客.getDateSouceObject()にて顧客行を取得。 <BR>
     * <BR>
     * ２）　@顧客行.モバイル専用口座開設区分 == "1：開設"の場合、true <BR>
     * 　@　@上記以外（nullを含む）の場合、falseを返却する。 <BR>
     * <BR>
     * @@param l_mainAccount-(顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isOnlyMobileOpen(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isOnlyMobileOpen(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        // パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.debug("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }

        //顧客.getDateSouceObject()にて顧客行を取得。
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //顧客行.モバイル専用口座開設区分 == "1：開設"の場合、true
        if (WEB3OnlyMobileOpenDivDef.OPEN.equals(l_mainAccountRow.getOnlyMobileOpenDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //上記以外（nullを含む）の場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is書面交付日より11ヶ月経過)<BR>
     * 書面交付日より11ヶ月経過の顧客か判別する。<BR>
     * <BR>
     * 書面交付日より11ヶ月経過の顧客の場合true、以外falseを返却する。<BR>
     * <BR>
     * １）　@this.get書面交付管理Paramsをコールする。<BR>
     * <BR>
     * 　@[get書面交付管理Paramsにセットするパラメータ]<BR>
     * 　@顧客：　@顧客オブジェクト<BR>
     * <BR>
     * ２）　@get書面交付管理Params()の戻り値がnullの場合、falseを返却する。<BR>
     * <BR>
     * ３）　@get書面交付管理Params()の戻り値の要素数分LOOP処理を行い、<BR>
     * 　@　@　@書面交付日より11ヶ月経過か判別する。<BR>
     * <BR>
     * 　@３－１）　@get書面交付管理Params()の戻り値.get(index).get書面交付日()を取得する。<BR>
     * <BR>
     * 　@３－２）　@取得した書面交付日の１１ヶ月後の日付を算出する。<BR>
     * <BR>
     * 　@３－３）　@３－２）にて算出した日付 <= 現在日付 の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@みなし交付日より11ヶ月経過か判別する。<BR>
     * <BR>
     * 　@　@３－３－１）　@get書面交付管理Params()の戻り値.get(index).getみなし交付日()を取得する。<BR>
     * <BR>
     * 　@　@３－３－２）　@getみなし交付日()の戻り値がnullの場合、trueを返却する。<BR>
     * <BR>
     * 　@　@３－３－３）　@取得したみなし交付日の１１ヶ月後の日付を算出する。<BR>
     * <BR>
     * 　@　@３－３－４）　@３－３－３）にて算出した日付 <= 現在日付 の場合、trueを返却する。<BR>
     * <BR>
     * ４）上記以外の場合、falseを返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDeliveryDate(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDeliveryDate(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //this.get書面交付管理Paramsをコールする。
        List l_lisDocManagementParams = this.getDocDeliveryManagementParams(l_mainAccount);

        //get書面交付管理Params()の戻り値がnullの場合、falseを返却する
        if (l_lisDocManagementParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        DocDeliveryManagementRow l_docManagementRow = null;
        //get書面交付管理Params()の戻り値の要素数分LOOP処理を行い,書面交付日より11ヶ月経過か判別する。
        for (int i = 0; i < l_lisDocManagementParams.size(); i++)
        {
            //get書面交付管理Params()の戻り値.get(index).get書面交付日()を取得する。
            l_docManagementRow = (DocDeliveryManagementRow)l_lisDocManagementParams.get(i);

            //３－２）取得した書面交付日の１１ヶ月後の日付を算出する。
            Date l_datDeliveryDate =
                WEB3DateUtility.addMonth(l_docManagementRow.getDeliveryDate(), 11);

            //現在日付
            Date l_datSystemTime =
                WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());

            //　@３－３）　@３－２）にて算出した日付 <= 現在日付 の場合、みなし交付日より11ヶ月経過か判別する。
            if (WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datSystemTime) <= 0)
            {
                //３－３－１）　@get書面交付管理Params()の戻り値.get(index).getみなし交付日()を取得する。
                Date l_datDeemedDeliveryDate =
                    l_docManagementRow.getDeemedDeliveryDate();
                //３－３－２）　@getみなし交付日()の戻り値がnullの場合、trueを返却する。
                if (l_datDeemedDeliveryDate == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
                //３－３－３）取得したみなし交付日の１１ヶ月後の日付を算出する。
                l_datDeemedDeliveryDate =
                    WEB3DateUtility.addMonth(l_datDeemedDeliveryDate, 11);
                //３－３－４）　@３－３－３）にて算出した日付 <= 現在日付 の場合、trueを返却する。
                if (WEB3DateUtility.compareToDay(l_datDeemedDeliveryDate, l_datSystemTime) <= 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }
        //上記以外の場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get書面交付管理Params)<BR>
     * 検索条件に該当する書面交付管理レコードをList型で返却する。<BR>
     * <BR>
     * １）　@電子鳩銘柄コード管理テーブルから電子鳩銘柄コードを取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@証券会社コード = 引数.顧客.証券会社コード and<BR>
     * 　@部店コード = 引数.顧客.部店コード and <BR>
     * 　@書面区分 = '010' and <BR>
     * 　@有効区分 = '0'(valid) <BR>
     * <BR>
     * 　@※検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ２）書面交付管理レコードを取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@口座ＩＤ = 顧客.getAccountId() and<BR>
     * 　@書面区分 = '010' and<BR>
     * 　@＜１）の戻り値が1件の場合＞<BR>
     * 　@  銘柄コード = １）の戻り値.get電子鳩銘柄コード()<BR>
     * 　@＜１）の戻り値が複数件の場合＞<BR>
     *   　@銘柄コード in ( １）の戻り値.get電子鳩銘柄コード() )<BR>
     * <BR>
     * 　@※検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ３）　@２）の検索結果を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getDocDeliveryManagementParams(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocDeliveryManagementParams(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //１）　@電子鳩銘柄コード管理テーブルから電子鳩銘柄コードを取得する。
        //[検索条件]
        //証券会社コード = 引数.顧客.証券会社コード and
        //部店コード = 引数.顧客.部店コード and
        //書面区分 = '010' and
        //有効区分 = '0'(valid)
        String l_strQueryString =
            " institution_code = ? and branch_code = ? and document_div = ? and valid_flag = ? ";

        Object[] l_queryContainers = new Object[4];
        l_queryContainers[0] = l_mainAccount.getInstitution().getInstitutionCode();
        l_queryContainers[1] = l_mainAccount.getBranch().getBranchCode();
        l_queryContainers[2] = WEB3CategCodeDef.DOCUMENT_DELIVERY;
        l_queryContainers[3] = WEB3ValidFlagDef.VALID;

        List l_lisBatoProductManagements = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBatoProductManagements = l_queryProcessor.doFindAllQuery(
                BatoProductManagementRow.TYPE,
                l_strQueryString,
                l_queryContainers);
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
        //検索結果が取得できなかった場合はnullを返却する
        if (l_lisBatoProductManagements == null || l_lisBatoProductManagements.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）書面交付管理レコードを取得する。
        //[検索条件]
        //口座ＩＤ = 顧客.getAccountId() and
        //書面区分 = '010' and
        //＜１）の戻り値が1件の場合＞
        //銘柄コード = １）の戻り値.get電子鳩銘柄コード()
        //＜１）の戻り値が複数件の場合＞
        //銘柄コード in ( １）の戻り値.get電子鳩銘柄コード() )
        //※検索結果が取得できなかった場合はnullを返却する。
        StringBuffer l_sbDocQueryString = new StringBuffer();
        Object[] l_docQueryContainers = null;
        BatoProductManagementRow l_batoProductManagementRow = null;

        //＜１）の戻り値が1件の場合＞
        if (l_lisBatoProductManagements.size() == 1)
        {
            l_sbDocQueryString.append(" account_id = ? and document_div = ? and product_code = ? ");
            l_batoProductManagementRow = (BatoProductManagementRow)l_lisBatoProductManagements.get(0);
            l_docQueryContainers = new Object[3];
            //検索条件
            l_docQueryContainers[0] = String.valueOf(l_mainAccount.getAccountId());
            l_docQueryContainers[1] = WEB3CategCodeDef.DOCUMENT_DELIVERY;
            l_docQueryContainers[2] = l_batoProductManagementRow.getBatoProductCode();
        }
        //＜１）の戻り値が複数件の場合＞
        else
        {
            l_sbDocQueryString.append(" account_id = ? and document_div = ? and product_code in ( ?");
            for (int i = 1; i < l_lisBatoProductManagements.size(); i++)
            {
                l_sbDocQueryString.append(", ?");
            }
            l_sbDocQueryString.append(" )");

            List l_lisQueryContainers = new ArrayList();
            l_lisQueryContainers.add(String.valueOf(l_mainAccount.getAccountId()));
            l_lisQueryContainers.add(WEB3CategCodeDef.DOCUMENT_DELIVERY);
            //検索条件
            for (int i = 0;i < l_lisBatoProductManagements.size(); i++)
            {
                l_batoProductManagementRow = (BatoProductManagementRow)l_lisBatoProductManagements.get(i);
                l_lisQueryContainers.add(l_batoProductManagementRow.getBatoProductCode());
            }
            l_docQueryContainers = l_lisQueryContainers.toArray();
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                DocDeliveryManagementRow.TYPE,
                l_sbDocQueryString.toString(),
                l_docQueryContainers);
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

        //検索結果が取得できなかった場合はnullを返却する
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisRecords;
    }

    /**
     * (get不足金発生状況)<BR>
     * 該当顧客の不足金発生状況を返す。<BR>
     * <BR>
     * １）　@入金請求管理サービスImpl.get不足金発生状況をコールする。<BR>
     * <BR>
     * ２）　@１）で取得した値を返却する。<BR>
     * <BR>
     * ※ 返却される値の一覧<BR>
     * "0"：　@不足金未発生<BR>
     * "1"：　@不足金発生（信用口座未開設）<BR>
     * "2"：　@不足金発生（信用口座開設）<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getShortfallGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getShortfallGenerationStatus(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //入金請求管理サービスImpl.get不足金発生状況をコールする。
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        String l_strShortfallGenerationStatus =
            l_manageService.getLackCashOccurSituation(l_mainAccount);

        //１）で取得した値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strShortfallGenerationStatus;
    }

    /**
     * (get追証発生状況)<BR>
     * 該当顧客の追証発生状況を返す。<BR>
     * <BR>
     * １）　@入金請求管理サービスImpl.get追証発生状況をコールする。<BR>
     * <BR>
     * ２）　@１）で取得した値を返却する。<BR>
     * <BR>
     * ※ 返却される値の一覧<BR>
     * "0"：　@追証未発生<BR>
     * "1"：　@第一水準追証発生<BR>
     * "2"：　@第二水準追証発生<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdddepositGenerationStatus(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdddepositGenerationStatus(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //入金請求管理サービスImpl.get追証発生状況をコールする
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        String l_strAdddepositGenerationStatus =
            l_manageService.getAdditionalMarginSituation(l_mainAccount);

        //１）で取得した値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strAdddepositGenerationStatus;
    }

    /**
     * (get不足金発生情報)<BR>
     * 不足金発生情報を返す。<BR>
     * <BR>
     * １）　@入金請求管理サービスImpl.get不足金発生情報をコールする。<BR>
     * <BR>
     * ２）　@１）で取得した値を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3TPShortfallOccurInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getShortfallGenerationInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //入金請求管理サービスImpl.get不足金発生情報をコールする
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            l_manageService.getShortfallGenerationInfo(l_mainAccount);

        //１）で取得した値を返却する
        log.exiting(STR_METHOD_NAME);
        return l_shortfallGenerationInfo;
    }

    /**
     * (get追証発生情報)<BR>
     * 追証発生情報を返す。<BR>
     * <BR>
     * １）　@入金請求管理サービスImpl.get追証発生情報をコールする。<BR>
     * <BR>
     * ２）　@１）で取得した値を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdddepositGenerationInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        //入金請求管理サービスImpl.get追証発生情報をコールする
        WEB3TPPaymentRequisitionManageService l_manageService =
            (WEB3TPPaymentRequisitionManageService)Services.getService(
                WEB3TPPaymentRequisitionManageService.class);
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
            l_manageService.getAdddepositGenerationInfo(l_mainAccount);

        //１）で取得した値を返却する
        log.exiting(STR_METHOD_NAME);
        return l_adddepositGenerationInfo;
    }

    /**
     * (get余力取引停止区分)<BR>
     * 顧客余力条件テーブルの取引停止区分を取得する。<BR>
     * <BR>
     * １）　@this.get顧客余力条件Paramsをコールする。<BR>
     * 　@[get顧客余力条件Paramsにセットするパラメータ]<BR>
     * 　@口座ID：　@顧客オブジェクト.口座ID<BR>
     * <BR>
     * ２）　@this.get顧客余力条件Paramsの戻り値がnull出ない場合、<BR>
     * this.get顧客余力条件Paramsの戻り値.get取引停止区分で取得した値を返却する。<BR>
     * <BR>
     * ３）　@this.get顧客余力条件Paramsの戻り値がnullの場合、"0"を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getTPTradingStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTPTradingStop(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //this.get顧客余力条件Paramsをコールする
        //[get顧客余力条件Paramsにセットするパラメータ]
        // 口座ID：　@顧客オブジェクト.口座ID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(
                new Long(l_mainAccount.getAccountId()));

        if (l_tradingpowerCalcConditionParams != null)
        {
            //this.get顧客余力条件Paramsの戻り値がnull出ない場合
            //this.get顧客余力条件Paramsの戻り値.get取引停止区分で取得した値を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tradingpowerCalcConditionParams.getTradingStop();
        }
        else
        {
            //this.get顧客余力条件Paramsの戻り値がnullの場合、"0"を返却する
            log.exiting(STR_METHOD_NAME);
            return WEB3AdminTPTradingPowerCalcConditionTradingStopDivDef.ENABLE;
        }
    }

    /**
     * (get顧客余力条件Params)<BR>
     * 検索条件に該当する顧客余力条件レコードを返却する。<BR>
     * <BR>
     * １）　@顧客余力条件テーブルから顧客余力条件レコードを取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@口座ID = 引数.口座ID<BR>
     * <BR>
     * 　@※検索結果が取得できなかった場合はnullを返却する。<BR>
     * <BR>
     * ２）　@１）で取得した結果を返却する。<BR>
     * <BR>
     * @@param l_accountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@return TradingpowerCalcConditionParams
     * @@throws WEB3BaseException
     */
    public TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(Long l_accountId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradingpowerCalcConditionParams(Long)";
        log.entering(STR_METHOD_NAME);

        //顧客余力条件テーブルから顧客余力条件レコードを取得する
        //[検索条件]
        // 口座ID = 引数.口座ID
        String l_strSql = " account_id = ? ";
        Object[] l_sqlValues = new Object[]{l_accountId};
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingpowerCalcConditionRow.TYPE,
                l_strSql,
                l_sqlValues);
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

        //検索結果が取得できなかった場合はnullを返却する
        if (l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow =
            (TradingpowerCalcConditionRow)l_lisRecords.get(0);
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            new TradingpowerCalcConditionParams(l_tradingpowerCalcConditionRow);

        //１）で取得した結果を返却する
        log.exiting(STR_METHOD_NAME);
        return l_tradingpowerCalcConditionParams;
    }

    /**
     * (get追証未入金区分)<BR>
     * 顧客余力条件テーブルの追証未入金区分を取得する。<BR>
     * <BR>
     * １）　@this.get顧客余力条件Paramsをコールする。<BR>
     * 　@[get顧客余力条件Paramsにセットするパラメータ]<BR>
     * 　@口座ID：　@顧客オブジェクト.口座ID<BR>
     * <BR>
     * ２）　@this.get顧客余力条件Paramsの戻り値がnull出ない場合、<BR>
     * this.get顧客余力条件Paramsの戻り値.get追証未入金区分で取得した値を返却する。<BR>
     * <BR>
     * ３）　@this.get顧客余力条件Paramsの戻り値がnullの場合、"0"を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAdditionalDepositStop(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionalDepositStop(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //this.get顧客余力条件Paramsをコールする
        //[get顧客余力条件Paramsにセットするパラメータ]
        // 口座ID：　@顧客オブジェクト.口座ID
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            this.getTradingpowerCalcConditionParams(
                new Long(l_mainAccount.getAccountId()));

        if (l_tradingpowerCalcConditionParams != null)
        {
            //this.get顧客余力条件Paramsの戻り値がnull出ない場合
            //this.get顧客余力条件Paramsの戻り値.get追証未入金区分で取得した値を返却する
            log.exiting(STR_METHOD_NAME);
            return l_tradingpowerCalcConditionParams.getAdditionalDepositStop();
        }
        else
        {
            //this.get顧客余力条件Paramsの戻り値がnullの場合、"0"を返却する
            log.exiting(STR_METHOD_NAME);
            return WEB3AdditionalDepositStopDef.ADDITIONAL_DEPOSIT_NOT_STOP;
        }
    }
}@
