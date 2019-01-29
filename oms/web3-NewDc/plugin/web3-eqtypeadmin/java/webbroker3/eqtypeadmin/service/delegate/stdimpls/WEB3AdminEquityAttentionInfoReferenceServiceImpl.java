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
filename	WEB3AdminEquityAttentionInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注意情報照会サービスImpl(WEB3AdminEquityAttentionInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30　@李キョウ(中訊) 新規作成 仕様変更モデルNo.216,No.220,No.225,No.227,No.228
Revision History : 2009/01/20　@孟亞南(中訊) 仕様変更モデルNo.231
Revision History : 2009/02/11　@李玉玲(中訊) 仕様変更モデルNo.234
Revision History : 2009/02/17　@李玉玲(中訊) ＤＢ更新仕様No.028
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryRow;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySortKeyItemNameDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefDetail;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefSortKey;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者注意情報照会サービスImpl)<BR>
 * 管理者注意情報照会サービス実装クラス<BR>
 * <BR>
 * @@author 李キョウ
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoReferenceServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AdminEquityAttentionInfoReferenceService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoReferenceServiceImpl.class);

    /**
     * @@roseuid 49588AED03C8
     */
    public WEB3AdminEquityAttentionInfoReferenceServiceImpl()
    {

    }

    /**
     * 注意情報照会サービスを行う。<BR>
     * <BR>
     * リクエストデータの型により以下のメソッドを<BR>
     * 呼び分ける。<BR>
     * <BR>
     * リクエストデータが、<BR>
     * 　@[管理者・注意情報照会入力リクエストの場合]<BR>
     * 　@　@this.get入力画面()をコールする。<BR>
     * <BR>
     * 　@[管理者・注意情報照会リクエストの場合]<BR>
     * 　@　@this.get照会画面()をコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 494226FD0333
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
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

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminEqAttentionInfoRefInpRequest)
        {
            l_response =
                this.getInputScreen(
                    (WEB3AdminEqAttentionInfoRefInpRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminEqAttentionInfoRefRefRequest)
        {
           l_response =
                this.getReferenceScreen(
                    (WEB3AdminEqAttentionInfoRefRefRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 注意情報照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注意情報照会）get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 注意情報照会入力リクエストオブジェクト<BR>
     * @@return WEB3AdminEqAttentionInfoRefInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 4945BC980227
     */
    protected WEB3AdminEqAttentionInfoRefInpResponse getInputScreen(
        WEB3AdminEqAttentionInfoRefInpRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminEqAttentionInfoRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;

        //getInstanceFromログイン情報
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_ATTENTION_INFO_REFERENCE,
            false);

        //createResponse
        WEB3AdminEqAttentionInfoRefInpResponse l_response =
            (WEB3AdminEqAttentionInfoRefInpResponse)l_request.createResponse();

        //get情報発生日時From
        l_response.infoOccuredDateFrom = this.getInfoOccuredDateFrom();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * 注意情報照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注意情報照会）get照会画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 注意情報照会リクエストオブジェクト<BR>
     * @@return WEB3AdminEqAttentionInfoRefRefResponse
     * @@throws WEB3BaseException
     * @@roseuid 4945BDCC0372
     */
    protected WEB3AdminEqAttentionInfoRefRefResponse getReferenceScreen(
        WEB3AdminEqAttentionInfoRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminEqAttentionInfoRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3Administrator l_administrator = null;
        String l_strInstituionCode = null;
        String l_strQueryString = null;
        String[] l_queryDataContainers = null;
        String l_strSortCond = null;
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRows = null;
        WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetails = null;

        //validate
        l_request.validate();

        //getInstanceFromログイン情報
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.DOMESTIC_EQUITY_ATTENTION_INFO_REFERENCE,
            false);

        //get証券会社コード
        l_strInstituionCode = l_administrator.getInstitutionCode();

        //create検索条件文字列
        l_strQueryString = this.createQueryString(l_request);

        //create検索条件データコンテナ
        l_queryDataContainers = this.createQueryDataContainer(l_request, l_strInstituionCode);

        //createソート条件
        l_strSortCond = this.createSortCond(l_request.sortKeys);

        //get注意情報履歴一覧
        l_attentionInfoHistoryRows = this.getAttentionInfoHistoryList(
            l_strQueryString, l_queryDataContainers, l_strSortCond);

        //createResponse
        WEB3AdminEqAttentionInfoRefRefResponse l_response =
            (WEB3AdminEqAttentionInfoRefRefResponse)l_request.createResponse();

        //get注意情報履歴一覧()の戻り値 == nullの場合
        if (l_attentionInfoHistoryRows == null || l_attentionInfoHistoryRows.length == 0)
        {
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.attentionInfoRefList = null;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_attentionInfoHistoryRows,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        // create注意情報一覧
        l_eqAttentionInfoRefDetails =
            this.createAttentionInfoList((AttentionInfoHistoryRow[])l_pageIndexInfo.getArrayReturned(
                AttentionInfoHistoryRow.class));

        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        l_response.attentionInfoRefList = l_eqAttentionInfoRefDetails;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get情報発生日時From)<BR>
     * 情報発生日時Fromを作成する。<BR>
     * <BR>
     * １）現在日時の取得<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)<BR>
     * にて現在日時を取得<BR>
     * <BR>
     * ２）取得日時を「YYYYMMDD」でフォーマットし、「000000」を連結<BR>
     * <BR>
     * ３）作成した日時を返却<BR>
     * @@return String
     * @@roseuid 4945BFB9003B
     */
    protected String getInfoOccuredDateFrom()
    {
        final String STR_METHOD_NAME = "getInfoOccuredDateFrom()";
        log.entering(STR_METHOD_NAME);
        //現在日時の取得
        Date l_datCurrentDate = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //取得日時を「YYYYMMDD」でフォーマットし、「000000」を連結
        String l_strCurrentDate = null;
        l_strCurrentDate = WEB3DateUtility.formatDate(WEB3DateUtility.toDay(l_datCurrentDate),
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        log.exiting(STR_METHOD_NAME);
        //作成した日時を返却
        return l_strCurrentDate;
    }

    /**
     * (create検索条件文字列)<BR>
     * <BR>
     * 検索条件文字列を作成する。 <BR>
     * <BR>
     * １）　@検索条件文字列インスタンスを生成する。 <BR>
     * <BR>
     * ２）　@証券会社コードを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 =　@"institution_code = ? " <BR>
     * <BR>
     * ３）　@パラメータ.リクエストデータ.注意情報種別!=nullの場合、 <BR>
     * 　@　@ 注意情報種別を検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and attention_info_type = ? " <BR>
     * <BR>
     * ４）　@パラメータ.リクエストデータ.注意情報区分コード!=nullの場合、 <BR>
     * 　@　@ 注意情報区分コードを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and attention_info_div_code = ? " <BR>
     * <BR>
     * ５）　@パラメータ.リクエストデータ.市場コード != nullの場合、 <BR>
     * 　@　@ 市場ＩＤを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and market_id = ? " <BR>
     * <BR>
     * ６）　@パラメータ.リクエストデータ.銘柄コード!= nullの場合、 <BR>
     * 　@銘柄IDを検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and product_id = ? " <BR>
     * <BR>
     * ７）　@パラメータ.リクエストデータ.有効日!= nullの場合、 <BR>
     * 　@有効日を検索条件文字列に追加する。 <BR>
     * <BR>
     * 検索条件文字列 += "and valid_until_biz_date = ? " <BR>
     * <BR>
     * ８）　@パラメータ.リクエストデータ.情報発生日時From!= nullの場合、 <BR>
     * 　@情報発生日時Fromを検索条件文字列に追加する。 <BR>
     * <BR>
     * 　@検索条件文字列 += "and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') " <BR>
     * <BR>
     * ９）　@パラメータ.リクエストデータ.情報発生日時To!= nullの場合、 <BR>
     * 　@情報発生日時Toを検索条件文字列に追加する。 <BR>
     * <BR>
     * 　@検索条件文字列 += "and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') " <BR>
     * <BR>
     * １０）　@作成した検索条件文字列インスタンスを返却する。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return String
     * @@roseuid 4945E58B0128
     */
    protected String createQueryString(WEB3AdminEqAttentionInfoRefRefRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3AdminEqAttentionInfoRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        //検索条件文字列インスタンスを生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        //証券会社コードを検索条件文字列に追加する。
        l_sbQueryString.append("institution_code = ? ");

        //注意情報種別!=nullの場合、注意情報種別を検索条件文字列に追加する。
        if (l_request.attentionInfoType != null)
        {
            l_sbQueryString.append("and attention_info_type = ? ");
        }

        //注意情報区分コード!=nullの場合、注意情報区分コードを検索条件文字列に追加する。
        if (l_request.attentionInfoDivCode != null)
        {
            l_sbQueryString.append("and attention_info_div_code = ? ");
        }

        //市場コード != nullの場合、市場ＩＤを検索条件文字列に追加する。
        if (l_request.marketCode != null)
        {
            l_sbQueryString.append("and market_id = ? ");
        }

        //銘柄コード!= nullの場合、銘柄IDを検索条件文字列に追加する。
        if (l_request.productCode != null)
        {
            l_sbQueryString.append("and product_id = ? ");
        }

        //有効日!= nullの場合、有効日を検索条件文字列に追加する。
        if (l_request.validDate != null)
        {
            l_sbQueryString.append("and valid_until_biz_date = ? ");
        }

        //情報発生日時From!= nullの場合、情報発生日時Fromを検索条件文字列に追加する。
        if (l_request.infoOccuredDateFrom != null)
        {
            l_sbQueryString.append("and info_generation_timestamp >= to_date(?,'YYYYMMDDHH24MISS') ");
        }

        //情報発生日時To!= nullの場合、情報発生日時Toを検索条件文字列に追加する。
        if (l_request.infoOccuredDateTo != null)
        {
            l_sbQueryString.append("and info_generation_timestamp <= to_date(?,'YYYYMMDDHH24MISS') ");
        }

        //作成した検索条件文字列インスタンスを返却する
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを作成する。 <BR>
     * <BR>
     * １）　@検索値を格納するListオブジェクトを生成する。 <BR>
     * <BR>
     * ２）　@パラメータ.証券会社コードをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(証券会社コード); <BR>
     * <BR>
     * ３）　@パラメータ.リクエストデータ.注意情報種別!=nullの場合、 <BR>
     * 　@　@ 注意情報種別をListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(注意情報種別); <BR>
     * <BR>
     * ４）　@パラメータ.リクエストデータ.注意情報区分コード!=nullの場合、 <BR>
     * 　@　@ 注意情報区分コードをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(注意情報区分コード); <BR>
     * <BR>
     * ５）　@パラメータ.リクエストデータ.市場コード != nullの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@５－１）get市場.市場IDをListオブジェクトに追加する。<BR>
     * 　@※拡張金融オブジェクトマネージャ.get市場()にて取得。 <BR>
     * <BR>
     * [引数] <BR>
     * 　@　@証券会社コード　@：　@パラメータ.証券会社コード<BR>
     * 　@　@市場コード：　@パラメータ.リクエストデータ.市場コード<BR>
     * <BR>
     * 　@５－２）<BR>
     * 　@Listオブジェクト.add(市場ID); <BR>
     * <BR>
     * ６）　@パラメータ.リクエストデータ.銘柄コード!= nullの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@６－１）拡張アカウントマネージャ.getInstitution()にて証券会社を取得 <BR>
     * <BR>
     * [引数] <BR>
     * 　@　@証券会社コード　@：　@パラメータ.証券会社コード<BR>
     * <BR>
     * 　@６－２）get銘柄.銘柄IDをListオブジェクトに追加する。<BR>
     * 　@※拡張株式プロダクトマネージャ.getProduct()にて取得。 <BR>
     * <BR>
     * 　@※getProduct()で例外発生時は、PR層にエラーを返す。<BR>
     * 　@　@・指定銘柄なしエラー<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag:　@　@BUSINESS_ERROR_00301<BR>
     * <BR>
     * [引数] <BR>
     * 　@　@証券会社　@： getInstitution()の戻り値<BR>
     * 　@　@銘柄コード： パラメータ.リクエストデータ.銘柄コード<BR>
     * <BR>
     * 　@６－３）<BR>
     * 　@Listオブジェクト.add(銘柄ID); <BR>
     * <BR>
     * ７） パラメータ.リクエストデータ.有効日!= nullの場合、 <BR>
     * 有効日をListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(有効日); <BR>
     * <BR>
     * <BR>
     * ８） パラメータ.リクエストデータ.情報発生日時From != nullの場合、 <BR>
     * 　@ 　@情報発生日時FromをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(情報発生日時From); <BR>
     * <BR>
     * ９） パラメータ.リクエストデータ.情報発生日時To != nullの場合、 <BR>
     * 　@ 情報発生日時ToをListオブジェクトに追加する。 <BR>
     * <BR>
     * 　@Listオブジェクト.add(情報発生日時To); <BR>
     * <BR>
     * １０） 格納したリストを配列オブジェクトに変換する。 <BR>
     * <BR>
     * １１）　@作成した配列オブジェクトを返却する。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4945E76D0286
     */
    protected String[] createQueryDataContainer(
        WEB3AdminEqAttentionInfoRefRefRequest l_request, String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryDataContainer(WEB3AdminEqAttentionInfoRefRefRequest, String)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //検索値を格納するListオブジェクトを生成する
        List l_lisQueryDataContainers = new ArrayList();

        //証券会社コードをListオブジェクトに追加する
        l_lisQueryDataContainers.add(l_strInstitutionCode);

        //注意情報種別!=nullの場合、注意情報種別をListオブジェクトに追加する
        if (l_request.attentionInfoType != null)
        {
            l_lisQueryDataContainers.add(l_request.attentionInfoType);
        }

        //注意情報区分コード!=nullの場合、注意情報区分コードをListオブジェクトに追加する
        if (l_request.attentionInfoDivCode != null)
        {
            l_lisQueryDataContainers.add(l_request.attentionInfoDivCode);
        }

        //市場コード != nullの場合、以下の処理を行う
        if (l_request.marketCode != null)
        {
            Market l_market = null;
            try
            {
            //get市場.市場IDをListオブジェクトに追加する
            //拡張金融オブジェクトマネージャ.get市場()にて取得
            //Listオブジェクト.add(市場ID)
            WEB3GentradeFinObjectManager l_gentradeFinObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market = l_gentradeFinObjManager.getMarket(
                l_strInstitutionCode,
                l_request.marketCode);
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
            l_lisQueryDataContainers.add(l_market.getMarketId() + "");
        }

        //銘柄コード!= nullの場合、以下の処理を行う
        if (l_request.productCode != null)
        {
            Institution l_institution = null;
            try
            {
                //拡張アカウントマネージャ.getInstitution()にて証券会社を取得
                //get銘柄.銘柄IDをListオブジェクトに追加する
                //拡張株式プロダクトマネージャ.getProduct()にて取得
                WEB3GentradeAccountManager l_gentradeAccountManaer =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                l_institution = l_gentradeAccountManaer.getInstitution(
                    l_strInstitutionCode);
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

            //拡張株式プロダクトマネージャ.getProduct()にて取得
            //Listオブジェクト.add(銘柄ID)
            try
            {
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityProductManager l_equityProductManeger =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                EqTypeProduct l_product = l_equityProductManeger.getProduct(l_institution, l_request.productCode);
                l_lisQueryDataContainers.add(l_product.getProductId() + "");
            }
            catch (NotFoundException l_ex)
            {
                log.error("指定した銘柄コードに合致している銘柄が存在しません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定した銘柄コードに合致している銘柄が存在しません。");
            }
        }

        //有効日!= nullの場合、有効日をListオブジェクトに追加する
        if (l_request.validDate != null)
        {
            l_lisQueryDataContainers.add(l_request.validDate);
        }

        //情報発生日時From != nullの場合、情報発生日時FromをListオブジェクトに追加する
        if (l_request.infoOccuredDateFrom != null)
        {
            l_lisQueryDataContainers.add(l_request.infoOccuredDateFrom);
        }

        //情報発生日時To != nullの場合、情報発生日時ToをListオブジェクトに追加する
        if (l_request.infoOccuredDateTo != null)
        {
            l_lisQueryDataContainers.add(l_request.infoOccuredDateTo);
        }

        //格納したリストを配列オブジェクトに変換する
        String[] l_strQueryDatas = new String[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_strQueryDatas);

        //作成した配列オブジェクトを返却する
        log.exiting(STR_METHOD_NAME);
        return l_strQueryDatas;
    }

    /**
     * (createソート条件)<BR>
     * ソート条件を作成する。 <BR>
     * <BR>
     * １）ソート条件文字列(：String)を作成する。 <BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * 　@２-１）ソートキー.キー項目を対応する列物理名に変換し、 <BR>
     * 　@　@作成したソート条件文字列に追加する。 <BR>
     * <BR>
     * 　@　@・「注意情報種別」　@→　@注意情報履歴テーブル.注意情報種別 <BR>
     * 　@　@・「注意情報区分コード」　@→　@注意情報履歴テーブル.注意情報区分コード<BR>
     * 　@　@・「情報発生日時」　@→　@注意情報履歴テーブル.情報発生日時<BR>
     * 　@　@・「銘柄コード」　@→　@注意情報履歴テーブル.銘柄ＩＤ<BR>
     * 　@　@・「市場コード」　@→　@注意情報履歴テーブル.市場ＩＤ<BR>
     * <BR>
     * 　@２-２）ソートキー.昇順／降順に対応する取得順序 <BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。 <BR>
     * <BR>
     * ３）ソート条件末尾に、注意情報履歴テーブル.情報発生日時を降順指定で付加 <BR>
     * 　@※ソート条件に「情報発生日時」が含まれていない場合<BR>
     * <BR>
     * ４）作成したソート条件文字列を返却する。 <BR>
     * <BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * 注意情報照会ソートキー<BR>
     * @@return String
     * @@roseuid 4945E7DA0065
     */
    protected String createSortCond(WEB3AdminEqAttentionInfoRefSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCond(WEB3AdminEqAttentionInfoRefSortKey[])";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortCondReturn = new StringBuffer();
        int l_intSortKeyLength = l_sortKeys.length;
        boolean l_blnInfoGenerationTimestamp = false;

        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //ソートキー.キー項目を対応する列物理名に変換し、作成したソート条件文字列に追加する
            if (WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("attention_info_type");
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_DIV_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("attention_info_div_code");
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.INFO_OCCURED_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("info_generation_timestamp");
                l_blnInfoGenerationTimestamp = true;
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("product_id");
            }
            else if (WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbSortCondReturn.append("market_id");
            }
            else
            {
                continue;
            }

            //ソートキー.昇順／降順に対応する取得順序(asc or desc)をソート条件文字列に追加する
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCondReturn.append(" ASC");
            }
            else
            {
                l_sbSortCondReturn.append(" DESC");
            }
            if (i < l_intSortKeyLength - 1)
            {
                l_sbSortCondReturn.append(", ");
            }
        }

        //ソート条件末尾に、注意情報履歴テーブル.情報発生日時を降順指定で付加
        if (!l_blnInfoGenerationTimestamp)
        {
            if (l_sbSortCondReturn.length() != 0)
            {
                l_sbSortCondReturn.append(", info_generation_timestamp DESC");
            }
            else
            {
                l_sbSortCondReturn.append("info_generation_timestamp DESC");
            }
        }

        log.exiting(STR_METHOD_NAME);

        //作成したソート条件文字列を返却する
        return l_sbSortCondReturn.toString();
    }

    /**
     * (get注意情報履歴一覧)<BR>
     * 引数の条件に該当する注意情報履歴の一覧を返却する。 <BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@arg0：　@注意情報履歴テーブルRow.TYPE <BR>
     * 　@　@arg1：　@パラメータ.検索条件文字列 <BR>
     * 　@　@arg2：　@パラメータ.ソート条件 <BR>
     * 　@　@arg3：　@null <BR>
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ <BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。 <BR>
     * <BR>
     * <BR>
     * ２）検索結果を返却する。 <BR>
     * <BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_queryDataContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return AttentionInfoHistoryRow[]
     * @@throws WEB3BaseException
     * @@roseuid 4945E8A70123
     */
    protected AttentionInfoHistoryRow[] getAttentionInfoHistoryList(
        String l_strQueryString, String[] l_queryDataContainers, String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAttentionInfoHistoryList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //QueryProcessor.doFindAllQuery()メソッドをコールする。
        //arg0：　@注意情報履歴テーブルRow.TYPE
        //arg1：　@パラメータ.検索条件文字列
        //arg2：　@パラメータ.ソート条件
        //arg3：　@null
        //arg4：　@パラメータ.検索条件データコンテナ
        List l_lisResults = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResults = l_processor.doFindAllQuery(
                AttentionInfoHistoryRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryDataContainers);
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

        //検索結果が取得できなかった場合、nullを返却する
        if (l_lisResults.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //検索結果を返却する
        int l_intAttentionInfoHistoryCnt = l_lisResults.size();
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRows =
            new AttentionInfoHistoryRow[l_intAttentionInfoHistoryCnt];
        l_lisResults.toArray(l_attentionInfoHistoryRows);

        log.exiting(STR_METHOD_NAME);
        return l_attentionInfoHistoryRows;
    }

    /**
     * (create注意情報一覧)<BR>
     * 引数の注意情報履歴一覧より注意情報照会明細を作成する。 <BR>
     * <BR>
     * １）　@戻り値を格納するArrayListを生成する。 <BR>
     * <BR>
     * ２）　@パラメータ.注意情報履歴一覧の要素数分、以下の処理を繰り返す。 <BR>
     * <BR>
     * 　@３-１）　@注意情報照会明細を生成する。 <BR>
     * <BR>
     * 　@３-２）　@生成したインスタンスに以下のプロパティをセットする。 <BR>
     * <BR>
     * 　@　@注意情報種別：　@処理対象の要素.注意情報種別 <BR>
     * 　@　@注意情報区分コード：　@処理対象の要素.注意情報区分コード <BR>
     * 　@　@情報発生日時：　@処理対象の要素.情報発生日時 <BR>
     * 　@　@市場コード：　@(*1) <BR>
     * 　@　@銘柄コード：　@(*2) <BR>
     * 　@　@銘柄名：　@処理対象の要素.銘柄名 <BR>
     * 　@　@処理結果区分：　@処理対象の要素.処理結果区分 <BR>
     * 　@　@有効日：　@処理対象の要素.有効日 <BR>
     * 　@　@注文受付再開日時（予定）：　@処理対象の要素.注文受付再開日時 <BR>
     * 　@　@売買停止・再開日時：　@処理対象の要素.売買停止日時／売買再開日時 <BR>
     * 　@　@基準値（変更前）：　@処理対象の要素.基準値（変更前）<BR>
     * 　@　@基準値（変更後）：　@処理対象の要素.基準値（変更後）<BR>
     * <BR>
     * 　@　@制限値幅上限（変更前）：　@(*3) <BR>
     * 　@　@制限値幅上限（変更後）：　@処理対象の要素.制限値幅上限（変更後）<BR>
     * <BR>
     * 　@　@制限値幅下限（変更前）：　@(*4) <BR>
     * 　@　@制限値幅下限（変更後）：　@処理対象の要素.制限値幅下限（変更後）<BR>
     * <BR>
     * 　@　@評価単価（変更前）：処理対象の要素.評価単価（変更前）<BR>
     * 　@　@評価単価（変更後）：処理対象の要素.評価単価（変更後）<BR>
     * 　@　@基準値（終値）（変更前）：処理対象の要素.基準値（終値）（変更前）<BR>
     * 　@　@基準値（終値）（変更後）：処理対象の要素.基準値（終値）（変更後）<BR>
     * 　@　@値幅チェック区分（変更前）：処理対象の要素.値幅チェック区分（変更前）<BR>
     * 　@　@値幅チェック区分（変更後）：処理対象の要素.値幅チェック区分（変更後）<BR>
     * 　@　@値幅区分（変更前）：処理対象の要素.値幅区分（変更前）<BR>
     * 　@　@値幅区分（変更後）：処理対象の要素.値幅区分（変更後）<BR>
     * 　@　@基準値（updq）（終値）（変更前）：処理対象の要素.基準値（終値）（翌日）（変更前）<BR>
     * 　@　@基準値（updq）（終値）（変更後）：処理対象の要素.基準値（終値）（翌日）（変更後）<BR>
     * 　@　@基準値（updq）（変更前）：処理対象の要素.基準値（翌日）（変更前）<BR>
     * 　@　@基準値（updq）（変更後）：処理対象の要素.基準値（翌日）（変更後）<BR>
     * <BR>
     * 　@　@表題：　@処理対象の要素.表題 <BR>
     * 　@　@本文：　@処理対象の要素.本文 <BR>
     * <BR>
     * 　@　@(*1) 処理対象の要素.市場ID≠nullの場合のみ、<BR>
　@　@　@　@ 市場IDに該当する市場.市場コードをセット。<BR>
     * <BR>
     * 　@　@(*2)処理対象の要素.銘柄ID≠nullの場合のみ、<BR>
         銘柄IDに該当する株式銘柄.銘柄コードをセット。<BR>
     * <BR>
     * 　@　@(*3)「処理対象の要素.基準値（変更前）」 = null あるいは、 <BR>
     * 「処理対象の要素.強制値幅（上限値）（変更前）」 = nullの場合、nullをセット <BR>
     * 以外の場合、処理対象の要素.基準値（変更前）+処理対象の要素.強制値幅（上限値）（変更前）<BR>
     * <BR>
     * 　@　@(*4)「処理対象の要素.基準値（変更前）」 = null あるいは、 <BR>
     * 「処理対象の要素.強制値幅（下限値）（変更前）」 = nullの場合、nullをセット <BR>
     * 以外の場合、処理対象の要素.基準値（変更前）-処理対象の要素.強制値幅（下限値）（変更前） <BR>
     * ※ただし、計算結果が0以下の場合はnullをセット <BR>
     * <BR>
     * 　@３-３）　@プロパティセットしたインスタンスをArrayListに追加する。 <BR>
     * <BR>
     * ４）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_attentionInfoHistoryRows - (注意情報履歴一覧)<BR>
     * 注意情報履歴一覧<BR>
     * @@return WEB3AdminEqAttentionInfoRefDetail[]
     * @@throws WEB3BaseException
     * @@roseuid 4945E8D50181
     */
    protected  WEB3AdminEqAttentionInfoRefDetail[] createAttentionInfoList(
        AttentionInfoHistoryRow[] l_attentionInfoHistoryRows) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAttentionInfoList(AttentionInfoHistoryRow[])";
        log.entering(STR_METHOD_NAME);

        //戻り値を格納するArrayListを生成する
        List l_lisAttentionInfoList = new ArrayList();

        //注意情報履歴一覧の要素数分、以下の処理を繰り返す
        int l_intAttentionInfoHistoryCnt = l_attentionInfoHistoryRows.length;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_gentradeFinObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_equityProductManeger =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        for (int i = 0; i < l_intAttentionInfoHistoryCnt; i++)
        {
            //注意情報照会明細を生成する
            WEB3AdminEqAttentionInfoRefDetail l_attentionInfoReferenceUnitList =
                new WEB3AdminEqAttentionInfoRefDetail();

            //注意情報種別：　@処理対象の要素.注意情報種別
            l_attentionInfoReferenceUnitList.attentionInfoType = l_attentionInfoHistoryRows[i].getAttentionInfoType();

            //注意情報区分コード：　@処理対象の要素.注意情報区分コード
            l_attentionInfoReferenceUnitList.attentionInfoDivCode =
                l_attentionInfoHistoryRows[i].getAttentionInfoDivCode();

            //情報発生日時：　@処理対象の要素.情報発生日時
            l_attentionInfoReferenceUnitList.infoOccuredDate =
                l_attentionInfoHistoryRows[i].getInfoGenerationTimestamp();

            try
            {
                //処理対象の要素.市場ID≠nullの場合のみ、市場IDに該当する市場.市場コードをセット。
                if (!l_attentionInfoHistoryRows[i].getMarketIdIsNull())
                {
                    Market l_market = l_gentradeFinObjManager.getMarket(
                        l_attentionInfoHistoryRows[i].getMarketId());
                    l_attentionInfoReferenceUnitList.marketCode = l_market.getMarketCode();
                }

                //処理対象の要素.銘柄ID≠nullの場合のみ、銘柄IDに該当する株式銘柄.銘柄コードをセット
                if (!l_attentionInfoHistoryRows[i].getProductIdIsNull())
                {
                    WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_equityProductManeger.getProduct(
                        l_attentionInfoHistoryRows[i].getProductId());
                    l_attentionInfoReferenceUnitList.productCode = l_equityProduct.getProductCode();
                }
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

            //銘柄名：　@処理対象の要素.銘柄名
            l_attentionInfoReferenceUnitList.productName = l_attentionInfoHistoryRows[i].getStandardName();

            //処理結果区分：　@処理対象の要素.処理結果区分
            l_attentionInfoReferenceUnitList.attentionInfoProcResDiv =
                l_attentionInfoHistoryRows[i].getProcessResultDiv();

            //有効日：　@処理対象の要素.有効日
            l_attentionInfoReferenceUnitList.validDate =
                WEB3DateUtility.getDate(l_attentionInfoHistoryRows[i].getValidUntilBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            //注文受付再開日時（予定）：　@処理対象の要素.注文受付再開日時
            l_attentionInfoReferenceUnitList.orderAcceptResumeScheduledDate =
                l_attentionInfoHistoryRows[i].getOrdReceiptRestartTimestamp();

            //売買停止・再開日時：　@処理対象の要素.売買停止日時／売買再開日時
            l_attentionInfoReferenceUnitList.buySellSuspendResumeDate =
                l_attentionInfoHistoryRows[i].getTradeStopRestartTimestamp();

            //基準値（変更前）：　@処理対象の要素.基準値（変更前）
            if (!l_attentionInfoHistoryRows[i].getOldBasePriceIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgBasePrice =
                    WEB3StringTypeUtility.formatNumber(l_attentionInfoHistoryRows[i].getOldBasePrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgBasePrice = null;
            }

            //基準値（変更後）：　@処理対象の要素.基準値（変更後）
            if (!l_attentionInfoHistoryRows[i].getNewBasePriceIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgBasePrice =
                    WEB3StringTypeUtility.formatNumber(l_attentionInfoHistoryRows[i].getNewBasePrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgBasePrice = null;
            }

            //処理対象の要素.基準値（変更前）」 = null あるいは、
            //処理対象の要素.強制値幅（上限値）（変更前）」 = nullの場合、nullをセット
            //以外の場合、処理対象の要素.基準値（変更前）+処理対象の要素.強制値幅（上限値）（変更前）
            BigDecimal l_bdOldBasePrice =
                new BigDecimal(String.valueOf(l_attentionInfoHistoryRows[i].getOldBasePrice()));
            BigDecimal l_bdOldHighCompPriceRange = new BigDecimal(
                String.valueOf(l_attentionInfoHistoryRows[i].getOldHighCompPriceRange()));
            if (l_attentionInfoHistoryRows[i].getOldBasePriceIsNull()
                || l_attentionInfoHistoryRows[i].getOldHighCompPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgUpperPriceRange = null;
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgUpperPriceRange =
                    WEB3StringTypeUtility.formatNumber(l_bdOldBasePrice.add(l_bdOldHighCompPriceRange).doubleValue());
            }

            //制限値幅上限（変更後）：　@処理対象の要素.制限値幅上限（変更後）
            if (!l_attentionInfoHistoryRows[i].getNewHighPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgUpperPriceRange =
                    WEB3StringTypeUtility.formatNumber(l_attentionInfoHistoryRows[i].getNewHighPriceRange());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgUpperPriceRange = null;
            }

            //「処理対象の要素.基準値（変更前）」 = null あるいは、
            //処理対象の要素.強制値幅（下限値）（変更前）」 = nullの場合、nullをセット
            //以外の場合、処理対象の要素.基準値（変更前）-処理対象の要素.強制値幅（下限値）（変更前）
            //※ただし、計算結果が0以下の場合はnullをセット
            BigDecimal l_bdOldLowCompPriceRange = new BigDecimal(
                String.valueOf(l_attentionInfoHistoryRows[i].getOldLowCompPriceRange()));
            if (l_attentionInfoHistoryRows[i].getOldBasePriceIsNull()
                || l_attentionInfoHistoryRows[i].getOldLowCompPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgLowerPriceRange = null;
            }
            else
            {
                BigDecimal l_bdbefChgLowerPriceRange = l_bdOldBasePrice.subtract(l_bdOldLowCompPriceRange);
                if ((l_bdbefChgLowerPriceRange.doubleValue() < 0)
                    || (GtlUtils.Double.isZero(l_bdbefChgLowerPriceRange.doubleValue())))
                {
                    l_attentionInfoReferenceUnitList.befChgLowerPriceRange = null;
                }
                else
                {
                    l_attentionInfoReferenceUnitList.befChgLowerPriceRange =
                        WEB3StringTypeUtility.formatNumber(
                        l_bdbefChgLowerPriceRange.doubleValue());
                }
            }

            //制限値幅下限（変更後）：　@処理対象の要素.制限値幅下限（変更後））
            if (!l_attentionInfoHistoryRows[i].getNewLowPriceRangeIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgLowerPriceRange =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewLowPriceRange());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgLowerPriceRange = null;
            }

            //評価単価（変更前）：処理対象の要素.評価単価（変更前）
            if (!l_attentionInfoHistoryRows[i].getOldEstimationPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgEvaluationPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldEstimationPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgEvaluationPrice = null;
            }

            //評価単価（変更後）：処理対象の要素.評価単価（変更後）
            if (!l_attentionInfoHistoryRows[i].getNewEstimationPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgEvaluationPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewEstimationPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgEvaluationPrice = null;
            }

            //基準値（終値）（変更前）：処理対象の要素.基準値（終値）（変更前）
            if (!l_attentionInfoHistoryRows[i].getOldLastClosingPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldLastClosingPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPrice = null;
            }

            //基準値（終値）（変更後）：処理対象の要素.基準値（終値）（変更後）
            if (!l_attentionInfoHistoryRows[i].getNewLastClosingPriceIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPrice =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewLastClosingPrice());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPrice = null;
            }

            //値幅チェック区分（変更前）：処理対象の要素.値幅チェック区分（変更前）
            l_attentionInfoReferenceUnitList.befChgPriceRangeCheckDiv =
                l_attentionInfoHistoryRows[i].getOldPriceRangeType();

            //値幅チェック区分（変更後）：処理対象の要素.値幅チェック区分（変更後）
            l_attentionInfoReferenceUnitList.aftChgPriceRangeCheckDiv =
                l_attentionInfoHistoryRows[i].getNewPriceRangeType();

            //値幅区分（変更前）：処理対象の要素.値幅区分（変更前）
            l_attentionInfoReferenceUnitList.befChgPriceRangeDiv =
                l_attentionInfoHistoryRows[i].getOldPriceRangeUnitType();

            //値幅区分（変更後）：処理対象の要素.値幅区分（変更後）
            l_attentionInfoReferenceUnitList.aftChgPriceRangeDiv =
                l_attentionInfoHistoryRows[i].getNewPriceRangeUnitType();

            //基準値（updq）（終値）（変更前）：処理対象の要素.基準値（終値）（翌日）（変更前）
            if (!l_attentionInfoHistoryRows[i].getOldLastClosingPriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldLastClosingPriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgLastClosingPriceUpdq = null;
            }

            //基準値（updq）（終値）（変更後）：処理対象の要素.基準値（終値）（翌日）（変更後）
            if (!l_attentionInfoHistoryRows[i].getNewLastClosingPriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewLastClosingPriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgLastClosingPriceUpdq = null;
            }

            //基準値（updq）（変更前）：処理対象の要素.基準値（翌日）（変更前）
            if (!l_attentionInfoHistoryRows[i].getOldBasePriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.befChgBasePriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getOldBasePriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.befChgBasePriceUpdq = null;
            }

            //基準値（updq）（変更後）：処理対象の要素.基準値（翌日）（変更後）
            if (!l_attentionInfoHistoryRows[i].getNewBasePriceUpdqIsNull())
            {
                l_attentionInfoReferenceUnitList.aftChgBasePriceUpdq =
                    WEB3StringTypeUtility.formatNumber(
                        l_attentionInfoHistoryRows[i].getNewBasePriceUpdq());
            }
            else
            {
                l_attentionInfoReferenceUnitList.aftChgBasePriceUpdq = null;
            }

            //表題：　@処理対象の要素.表題
            l_attentionInfoReferenceUnitList.title =
                l_attentionInfoHistoryRows[i].getFreeFormatTitle();

            //本文：　@処理対象の要素.本文
            l_attentionInfoReferenceUnitList.text =
                l_attentionInfoHistoryRows[i].getFreeFormatText();

            //プロパティセットしたインスタンスをArrayListに追加する
            l_lisAttentionInfoList.add(l_attentionInfoReferenceUnitList);
        }

        //生成したArrayList.toArray()の戻り値を返却する
        int l_intAttentionInfoListCnt = l_lisAttentionInfoList.size();
        WEB3AdminEqAttentionInfoRefDetail[] l_eqAttentionInfoRefDetail =
            new WEB3AdminEqAttentionInfoRefDetail[l_intAttentionInfoListCnt];
        l_lisAttentionInfoList.toArray(l_eqAttentionInfoRefDetail);

        log.exiting(STR_METHOD_NAME);
        return l_eqAttentionInfoRefDetail;
    }
}
@
