head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者為替登録サービスImpl(WEB3AdminTMExchangeRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.trademanagement.define.WEB3AdminTMRateDivDef;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeInfoUnit;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMExchangeRegistService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者為替登録サービスImpl)<BR>
 * 管理者為替登録サービス実装クラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistServiceImpl
    implements WEB3AdminTMExchangeRegistService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMExchangeRegistServiceImpl.class);

    /**
     * コンストラクタ
     */
    public WEB3AdminTMExchangeRegistServiceImpl()
    {

    }

    /**
     * 管理者為替登録サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     * get入力画面()<BR>
     * validate為替登録()<BR>
     * submit為替登録()<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        //get入力画面()
        if (l_request instanceof WEB3AdminTMExchangeRegistInputRequest)
        {
            log.debug("get入力画面()");
            l_response = getInputScreen(
                (WEB3AdminTMExchangeRegistInputRequest)l_request);
        }
        //validate為替登録()
        else if (l_request instanceof WEB3AdminTMExchangeRegistConfirmRequest)
        {
            log.debug("validate為替登録())");
            l_response = validateExchangeRegist(
                (WEB3AdminTMExchangeRegistConfirmRequest)l_request);
        }
        //submit為替登録()
        else if (l_request instanceof WEB3AdminTMExchangeRegistCompleteRequest)
        {
            log.debug("submit為替登録()");
            l_response = submitExchangeRegist(
                (WEB3AdminTMExchangeRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図「（管理者為替登録）get入力画面」 参照<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminTMExchangeRegistInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminTMExchangeRegistInputResponse getInputScreen(
        WEB3AdminTMExchangeRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminTMExchangeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //WEB3SystemLayerException

        //1.2.validate権限(String, boolean)
        //権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： ”基準為替登録”
        //is更新： true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.BASE_FX_RATE_REG, true);

        //1.3.get証券会社コード( )
        //証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.4.get（共通）通貨(String)
        //（共通）通貨の配列を取得する。
        //[引数]
        //証券会社コード：　@get証券会社コード()の戻り値
        WEB3GentradeCurrency[] l_gentradeCurrencys =
            WEB3GentradeCurrency.getGentradeCurrency(l_strInstitutionCode);

        //1.5.(*)　@get（共通）通貨()戻り値の要素数分LOOP処理
        // ArrayListオブジェクトの生成
        int l_intCnt = 0;
        if (l_gentradeCurrencys != null && l_gentradeCurrencys.length > 0)
        {
            l_intCnt = l_gentradeCurrencys.length;
        }

        List l_lisArrays = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.5.1.為替情報( )
            //インスタンスを生成する。
            WEB3AdminTMExchangeInfoUnit l_baseExChangeInfo =
                new WEB3AdminTMExchangeInfoUnit();

            //1.5.2.(*)　@プロパティセット
            //為替情報プロパティに値をセットする。
            //通貨コード：　@（共通）通貨.通貨コード
            //レート区分：　@”基準為替”
            //売付為替レート：　@（共通）通貨.get売付基準為替レート()
            //買付為替レート：　@（共通）通貨.get買付基準為替レート()
            //登録日時：　@（共通）通貨.基準為替更新日付
            GenCurrencyRow l_genCurrencyRow =
                (GenCurrencyRow)l_gentradeCurrencys[i].getDataSourceObject();

            l_baseExChangeInfo.currencyCode = l_gentradeCurrencys[i].getCurrencyCode();
            l_baseExChangeInfo.rateDiv = WEB3AdminTMRateDivDef.BASE_EXCHANGE;
            l_baseExChangeInfo.sellExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getSellBaseRate());
            l_baseExChangeInfo.buyExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getBuyBaseRate());
            l_baseExChangeInfo.registrationTime = l_genCurrencyRow.getRateUpdateTimestamp();
            l_lisArrays.add(l_baseExChangeInfo);

            //1.5.3.為替情報( )
            //インスタンスを生成する。
            WEB3AdminTMExchangeInfoUnit l_executedExChangeInfo =
                new WEB3AdminTMExchangeInfoUnit();

            //1.5.4.(*)　@プロパティセット
            //為替情報プロパティに値をセットする。
            //通貨コード：　@（共通）通貨.通貨コード
            //レート区分：　@”約定為替”
            //売付為替レート：　@（共通）通貨.get売付約定為替レート()
            //買付為替レート：　@（共通）通貨.get買付約定為替レート()
            //登録日時：　@（共通）通貨.約定為替更新日付
            l_executedExChangeInfo.currencyCode = l_gentradeCurrencys[i].getCurrencyCode();
            l_executedExChangeInfo.rateDiv = WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE;
            l_executedExChangeInfo.sellExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getSellExecRate());
            l_executedExChangeInfo.buyExchangeRate = WEB3StringTypeUtility.formatNumber(
                l_gentradeCurrencys[i].getBuyExecRate());
            l_executedExChangeInfo.registrationTime = l_genCurrencyRow.getExecRateUpdateTimestamp();
            l_lisArrays.add(l_executedExChangeInfo);
        }
        //1.6.createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTMExchangeRegistInputResponse l_response =
            (WEB3AdminTMExchangeRegistInputResponse)l_request.createResponse();

        //1.7.(*) プロパティセット
        //レスポンスデータプロパティに値をセットする。
        //(*) 以下のとおりに、プロパティにセットする。

        if (l_intCnt == 0)
        {
            l_response.exchangeInfoUnit = null;
        }
        else
        {
            //返却するWEB3AdminTMExchangeInfoUnit[]型配列
            WEB3AdminTMExchangeInfoUnit[] l_exchangeInfos =
                new WEB3AdminTMExchangeInfoUnit[l_lisArrays.size()];

            // Listから配列に変換
            l_lisArrays.toArray(l_exchangeInfos);

            //レスポンス.為替情報一覧 = 生成した為替情報の配列
            l_response.exchangeInfoUnit = l_exchangeInfos;
        }
        //1.8.return レスポンス
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate為替登録)<BR>
     * 為替登録確認を行う。<BR>
     * <BR>
     * シーケンス図「（管理者為替登録）validate為替登録」 参照<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminTMExchangeRegistConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminTMExchangeRegistConfirmResponse validateExchangeRegist(
        WEB3AdminTMExchangeRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateExchangeRegist(WEB3AdminTMExchangeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        //リクエストデータのチェックを行う。
        //1.1.1.validate( )
        //為替情報のチェックを行う。
        l_request.validate();

        //1.2.getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        //WEB3SystemLayerException

        //1.3.validate権限(String, boolean)権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： ”基準為替登録”
        //is更新： true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.BASE_FX_RATE_REG, true);

        //1.4.createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTMExchangeRegistConfirmResponse l_response =
            (WEB3AdminTMExchangeRegistConfirmResponse)l_request.createResponse();

        //1.5.return レスポンス
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit為替登録)<BR>
     * 為替登録を行う。<BR>
     * <BR>
     * シーケンス図「（管理者為替登録）submit為替登録」 参照<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminTMExchangeRegistCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminTMExchangeRegistCompleteResponse submitExchangeRegist(
        WEB3AdminTMExchangeRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitExchangeRegist(WEB3AdminTMExchangeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate( )
        //リクエストデータのチェックを行う。
        //1.1.1.validate( )
        //為替情報のチェックを行う。
        l_request.validate();

        //1.2.getInstanceFromログイン情報( )
        //管理者インスタンスを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3.validate権限(String, boolean)権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： ”基準為替登録”
        //is更新： true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.BASE_FX_RATE_REG, true);

        //1.4.validate取引パスワード(String)
        //パスワードのチェックを行う。
        //[引数]
        //パスワード：　@リクエスト.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        //1.5.(*) リクエストデータ.為替情報一覧[]の各要素毎にLOOP処理
        //リクエストデータ.為替情報一覧[]の各要素毎にLOOP処理
        WEB3AdminTMExchangeInfoUnit[] l_exchangeInfoUnits = l_request.exchangeInfoUnit;

        int l_intLength = l_exchangeInfoUnits.length;

        for (int i = 0; i < l_intLength; i++)
        {
            //1.5.1.persist通貨(為替情報)
            //（共通）通貨テーブルに為替情報を更新する。
            //[引数]
            //為替情報：　@リクエストデータ.為替情報一覧[index]
            persistCurrency(l_exchangeInfoUnits[i]);
        }

        //1.6.createResponse( )
        //レスポンスデータを生成する。
        WEB3AdminTMExchangeRegistCompleteResponse l_response =
            (WEB3AdminTMExchangeRegistCompleteResponse)l_request.createResponse();

        //1.7.return レスポンス
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (persist通貨)<BR>
     * （共通）通貨テーブルに為替情報を更新する。<BR>
     * <BR>
     * ○　@為替レートに入力がない場合（為替情報.売付為替レート == null && <BR>
     * 　@　@為替情報.買付為替レート == null）<BR>
     * 　@処理を終了する。（return）<BR>
     * <BR>
     * ○　@為替レートに入力がある場合<BR>
     * 　@為替レートを（共通）通貨テーブルに更新する。<BR>
     * 　@　@更新内容は以下の通り。<BR>
     * <BR>
     * 　@　@基準為替（為替情報.レート区分 == ”基準為替”）の場合、<BR>
     * 　@　@　@－【ｘTrade】補足資料.DB更新\\70.株管理者\\11.為替登録<BR>
     * 　@　@　@　@「為替登録_（共通）通貨テーブル仕様.xls#<BR>
     * 　@　@　@　@　@為替登録_（共通）通貨テーブル DB更新（基準為替）」参照。<BR>
     * <BR>
     * 　@　@基準為替（為替情報.レート区分 == ”約定為替”）の場合、<BR>
     * 　@　@　@－【ｘTrade】補足資料.DB更新\\70.株管理者\\11.為替登録<BR>
     * 　@　@　@　@「為替登録_（共通）通貨テーブル仕様.xls#<BR>
     * 　@　@　@　@　@為替登録_（共通）通貨テーブル DB更新（約定為替）」参照。<BR>
     * <BR>
     * 　@　@以外の場合、例外をスローする。<BR>
     * <BR>
     * @@param l_exchangeInfo - 為替情報
     * @@throws WEB3BaseException
     */
    private void persistCurrency(WEB3AdminTMExchangeInfoUnit l_exchangeInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "persistCurrency(WEB3AdminTMExchangeInfoUnit l_exchangeInfo)";
        log.entering(STR_METHOD_NAME);

        //   　@為替レートに入力がない場合（為替情報.売付為替レート == null
        // 　@　@&& 為替情報.買付為替レート == null）
        // 　@処理を終了する。（return）
        if (WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate)
            && WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
        {
            log.debug("為替レートに入力がない場合");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        //WEB3SystemLayerException
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        String l_strCurrencyCode = l_exchangeInfo.currencyCode;

        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        log.debug("l_strCurrencyCode = " + l_strCurrencyCode);

        GenCurrencyParams l_genCurrencyParams = null;
        try
        {
            GenCurrencyRow l_row =
                GenCurrencyDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strCurrencyCode);
            l_genCurrencyParams = new GenCurrencyParams(l_row);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //為替情報.売付為替レート
        double l_dblSellExchangeRate = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
        {
            l_dblSellExchangeRate = Double.parseDouble(l_exchangeInfo.sellExchangeRate);
        }

        //為替情報.買付為替レート
        double l_dblBuyExchangeRate = 0.0D;
        if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
        {
            l_dblBuyExchangeRate = Double.parseDouble(l_exchangeInfo.buyExchangeRate);
        }
        //   　@為替レートに入力がある場合
        // 　@為替レートを（共通）通貨テーブルに更新する
        // 　@　@更新内容は以下の通り。
        // 　@　@基準為替（為替情報.レート区分 == ”基準為替”）の場合
        // 　@　@　@－【ｘTrade】補足資料.DB更新\\70.株管理者\\11.為替登録
        // 　@　@　@　@「為替登録_（共通）通貨テーブル仕様.xls#為替登録_（共通）通貨テーブル DB更新（基準為替）」参照。
        if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            log.debug("基準為替（為替情報.レート区分 == ”基準為替”）の場合");
            //今回売付為替レート
            //為替情報.売付為替レート
            //"（為替情報.売付為替レート == null）の場合、（既存値）。
            //（為替情報.売付為替レート != null）の場合、
            //　@－入力値（為替情報.売付為替レート）をセット。"
            //前回売付為替レート
            //"（為替情報.売付為替レート == null）の場合、（既存値）。
            //（為替情報.売付為替レート != null）の場合、
            //　@－今回売付為替レート（※更新前の値）をセット。"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                l_genCurrencyParams.setPrevSellRate(
                    l_genCurrencyParams.getCurrentSellRate());
                l_genCurrencyParams.setCurrentSellRate(l_dblSellExchangeRate);
            }

            //今回買付為替レート
            //"（為替情報.買付為替レート == null）の場合、（既存値）。
            //（為替情報.買付為替レート != null）の場合、
            //　@－入力値（為替情報.買付為替レート）をセット。"
            //前回買付為替レート
            //"（為替情報.買付為替レート == null）の場合、（既存値）。
            //（為替情報.買付為替レート != null）の場合、
            //　@－今回買付為替レート（※更新前の値）をセット。"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {
                l_genCurrencyParams.setPrevBuyRate(
                    l_genCurrencyParams.getCurrentBuyRate());
                l_genCurrencyParams.setCurrentBuyRate(l_dblBuyExchangeRate);
            }

            //基準為替更新者コード : 管理者コード
            l_genCurrencyParams.setRateLastUpdater(l_administrator.getAdministratorCode());

            //基準為替更新日付:現在日時
            l_genCurrencyParams.setRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

            //更新者コード:管理者コード
            l_genCurrencyParams.setLastUpdater(l_administrator.getAdministratorCode());

            //更新日付:現在日時
            l_genCurrencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

        }
        // 　@　@基準為替（為替情報.レート区分 == ”約定為替”）の場合、
        // 　@　@　@－【ｘTrade】補足資料.DB更新\\70.株管理者\\11.為替登録
        // 　@　@　@　@「為替登録_（共通）通貨テーブル仕様.xls#為替登録_（共通）通貨テーブル DB更新（約定為替）」参照
        else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            log.debug("基準為替（為替情報.レート区分 == ”約定為替”）の場合");

            //今回売付約定為替レート
            //"（為替情報.売付為替レート == null）の場合、（既存値）。
            //（為替情報.売付為替レート != null）の場合、
            //　@－入力値（為替情報.売付為替レート）をセット。"
            //前回売付約定為替レート
            //"（為替情報.売付為替レート == null）の場合、（既存値）。
            //（為替情報.売付為替レート != null）の場合、
            //　@－今回売付約定為替レート（※更新前の値）をセット。"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                l_genCurrencyParams.setPrevSellExecRate(
                    l_genCurrencyParams.getCurrentSellExecRate());
                l_genCurrencyParams.setCurrentSellExecRate(l_dblSellExchangeRate);
            }

            //今回買付約定為替レート
            //"（為替情報.買付為替レート == null）の場合、（既存値）。
            //（為替情報.買付為替レート != null）の場合、
            //　@－入力値（為替情報.買付為替レート）をセット。"
            //前回買付約定為替レート
            //"（為替情報.買付為替レート == null）の場合、（既存値）。
            //（為替情報.買付為替レート != null）の場合、
            //　@－今回買付約定為替レート（※更新前の値）をセット。"
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {
                l_genCurrencyParams.setPrevBuyExecRate(
                    l_genCurrencyParams.getCurrentBuyExecRate());
                l_genCurrencyParams.setCurrentBuyExecRate(l_dblBuyExchangeRate);
            }

            //約定為替更新者コード : 管理者コード
            l_genCurrencyParams.setExecRateLastUpdater(l_administrator.getAdministratorCode());

            //約定為替更新日付:現在日時
            l_genCurrencyParams.setExecRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

            //更新者コード:管理者コード
            l_genCurrencyParams.setLastUpdater(l_administrator.getAdministratorCode());

            //更新日付:現在日時
            l_genCurrencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        }
        // 　@　@以外の場合、例外をスローする。
        else
        {
            log.debug("為替情報のレート区分が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02159,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            WEB3DataAccessUtility.updateRow(l_genCurrencyParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }
}
@
