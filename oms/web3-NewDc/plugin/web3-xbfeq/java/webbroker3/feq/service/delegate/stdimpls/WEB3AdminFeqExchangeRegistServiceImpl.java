head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExchangeRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式為替登録サービスImpl(WEB3AdminFeqExchangeRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 戴義波(中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー  
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
Revesion History : 2009/06/24 柴双紅(中訊) モデルNo.499,500,ＤＢ更新仕様No.102
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.feq.define.WEB3LastUpdaterDef;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExchangeRegistInputResponse;
import webbroker3.feq.message.WEB3FeqExchangeUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqExchangeRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.tradingpower.define.WEB3TPProcessManagementIdDef;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式為替登録サービスImpl)<BR>
 * 外国株式為替登録サービス実装クラス<BR>
 * 
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExchangeRegistServiceImpl implements WEB3AdminFeqExchangeRegistService 
{
	  /**
     * <p>
     * （ログ出力ユーティリティ）。
     * </p>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExchangeRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39EF037A
     */
    public WEB3AdminFeqExchangeRegistServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式為替登録サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get入力画面()<BR>
     *   validate為替登録()<BR>
     *   submit為替登録()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4210847703CD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response;

        //リクエストデータの型により、以下のメソッドをコールする。
        //－get入力画面() 
        if (l_request instanceof WEB3AdminFeqExchangeRegistInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminFeqExchangeRegistInputRequest) l_request);
        }
        //－validate為替登録() 
        else if (l_request instanceof WEB3AdminFeqExchangeRegistConfirmRequest)
        {
            l_response = 
                validateRateRegist((WEB3AdminFeqExchangeRegistConfirmRequest) l_request);
        }
        //－submit為替登録() 
        else if (l_request instanceof WEB3AdminFeqExchangeRegistCompleteRequest)
        {
            l_response = 
                submitRateRegist((WEB3AdminFeqExchangeRegistCompleteRequest) l_request);
        } 
        else
        {
            log.debug("error in get necessory request");
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
     * シーケンス図「（為替登録）get入力画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExchangeRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 421084B6012D
     */
    protected WEB3AdminFeqExchangeRegistInputResponse getInputScreen(
        WEB3AdminFeqExchangeRegistInputRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqExchangeRegistInputRequest) ";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productMgr = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();

        //1.1 管理者インスタンスを取得する
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "管理者ロのグイン情報が存在しない。");
        }
        
        //1.2 権限のチェックを行う。

        //[引数]
        //機@能カテゴリコード： ”基準為替登録”
        //is更新： true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_BASE_EXCHANGE_REGIST, true);

        //1.3 証券会社コードを取得する
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 通貨の配列を取得する。

        //[get通貨()に指定する引数]
        //証券会社コード：　@get証券会社コード()
        WEB3GentradeCurrency[] l_genCurrencys = l_productMgr.getCurrency(l_strInstitutionCode);
        
        int l_intCnt = 0;
        if (l_genCurrencys != null && l_genCurrencys.length > 0)
        {
            l_intCnt = l_genCurrencys.length;
        }
        
        List l_lisExchangeUnits = new ArrayList();
        //1.5 get通貨()戻り値の要素数分LOOP処理
        for (int i = 0; i < l_intCnt; i++) 
        {
            //1.5.1 外国株式為替情報() インスタンスを生成する
            WEB3FeqExchangeUnit l_baseExchangeUnit =
                new WEB3FeqExchangeUnit();
            
            WEB3GentradeCurrency l_genCurrency = l_genCurrencys[i];
            GenCurrencyRow l_currencyRow = (GenCurrencyRow)l_genCurrency.getDataSourceObject();
            
            //1.5.2 外国株式為替情報プロパティに値をセットする
            //通貨コード：　@通貨.通貨コード
            l_baseExchangeUnit.currencyCode = l_currencyRow.getCurrencyCode();
            //レート区分：　@”基準為替”
            l_baseExchangeUnit.rateDiv = WEB3FeqRateDivDef.BASE_EXCHANGE;
            //売付為替レート：　@通貨.get売付基準為替レート()
            l_baseExchangeUnit.sellExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getSellBaseRate());
                if(l_baseExchangeUnit.sellExchangeRate.equals("0"))
                {
                  l_baseExchangeUnit.sellExchangeRate = null;                
                }

            //買付為替レート：　@通貨.get買付基準為替レート()
            l_baseExchangeUnit.buyExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getBuyBaseRate());
                if(l_baseExchangeUnit.buyExchangeRate.equals("0"))
                {
                  l_baseExchangeUnit.buyExchangeRate = null;                
                }
 
            //登録日時：　@通貨.基準為替更新日付
            l_baseExchangeUnit.registDatetime = l_currencyRow.getRateUpdateTimestamp();

            l_lisExchangeUnits.add(l_baseExchangeUnit);

            //1.5.3 外国株式為替情報() インスタンスを生成する
            WEB3FeqExchangeUnit l_executedExchangeUnit =
                new WEB3FeqExchangeUnit();
            
            //1.5.4 外国株式為替情報プロパティに値をセットする
            //通貨コード：　@通貨.通貨コード
            l_executedExchangeUnit.currencyCode =l_currencyRow.getCurrencyCode();
            //レート区分：　@”約定為替”
            l_executedExchangeUnit.rateDiv = WEB3FeqRateDivDef.EXECUTED_EXCHANGE;

            //売付為替レート：　@通貨.get売付約定為替レート()            
            l_executedExchangeUnit.sellExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getSellExecRate());            
                if(l_executedExchangeUnit.sellExchangeRate.equals("0"))
                {
                  l_executedExchangeUnit.sellExchangeRate = null;                
                }

            //買付為替レート：　@通貨.get買付約定為替レート()
            l_executedExchangeUnit.buyExchangeRate =
                WEB3StringTypeUtility.formatNumber(l_genCurrency.getBuyExecRate());
                if(l_executedExchangeUnit.buyExchangeRate.equals("0"))
                {
                  l_executedExchangeUnit.buyExchangeRate = null;            
                }

            //登録日時：　@通貨.約定為替更新
            l_executedExchangeUnit.registDatetime = l_currencyRow.getExecRateUpdateTimestamp();

            l_lisExchangeUnits.add(l_executedExchangeUnit);
        }
        //1.6 レスポンスデータを生成する
        WEB3AdminFeqExchangeRegistInputResponse l_response = 
            (WEB3AdminFeqExchangeRegistInputResponse)l_request.createResponse();

        //1.7 レスポンスデータプロパティに値をセットする
        //(*)　@以下のとおりに、プロパティをセットする。
        //レスポンス.為替情報一覧 =  生成した外国株式為替情報[]
        if (l_intCnt == 0)
        {
            l_response.exchangeList = null;
        }
        else
        {
            WEB3FeqExchangeUnit[] l_feqExchangeUnits = 
                new WEB3FeqExchangeUnit[l_lisExchangeUnits.size()];
            l_lisExchangeUnits.toArray(l_feqExchangeUnits);
            l_response.exchangeList = l_feqExchangeUnits;             
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate為替登録)<BR>
     * 為替登録確認を行う。<BR>
     * <BR>
     * シーケンス図「（為替登録）validate為替登録」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExchangeRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 421084B6014C
     */
    protected WEB3AdminFeqExchangeRegistConfirmResponse validateRateRegist(
        WEB3AdminFeqExchangeRegistConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateRateRegist(WEB3AdminFeqExchangeRegistConfirmRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1 リクエストデータのチェックを行う
        l_request.validate();
        
        //1.2 管理者インスタンスを取得する
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "管理者ロのグイン情報が存在しない。");
        }
        
        //1.3 権限のチェックを行う。

        //[引数]
        //機@能カテゴリコード： ”基準為替登録”
        //is更新： true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_BASE_EXCHANGE_REGIST, true);

        //1.4 レスポンスデータを生成する
        WEB3AdminFeqExchangeRegistConfirmResponse l_response =
            new WEB3AdminFeqExchangeRegistConfirmResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit為替登録)<BR>
     * 為替登録を行う。<BR>
     * <BR>
     * シーケンス図「（為替登録）submit為替登録」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExchangeRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 421084B6016B
     */
    protected WEB3AdminFeqExchangeRegistCompleteResponse submitRateRegist(
        WEB3AdminFeqExchangeRegistCompleteRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitRateRegist(WEB3AdminFeqExchangeRegistCompleteRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1 リクエストデータのチェックを行う
        l_request.validate();
        
        //1.2 管理者インスタンスを取得する
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "管理者ロのグイン情報が存在しない。");
        }

        //1.3 権限のチェックを行う。
        //[引数]
        //機@能カテゴリコード： ”基準為替登録”
        //is更新： true
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_BASE_EXCHANGE_REGIST, true);

        int l_intCnt = 0;
        
        if (l_request.exchangeList != null && l_request.exchangeList.length > 0)
        {
            l_intCnt = l_request.exchangeList.length;
        }

        //get証券会社( )
        Institution l_institution = l_admin.getInstitution();

        //1.4 取引パスワードのチェックを行う。
        //[validate取引パスワード()に指定する引数]
        //パスワード： リクエストデータ.暗証番号
        l_admin.validateTradingPassword(l_request.password);

        
        //1.5 リクエストデータ.為替情報一覧[]の各要素毎にLOOP処理
        for (int i = 0; i < l_intCnt; i++)
        {
            //1.5.1 通貨テーブルに為替情報を更新する。

            //[persist通貨()に指定する引数]
            //為替情報：　@リクエストデータ.為替情報一覧[index]
            persistCurrency(l_request.exchangeList[i]);
            
        }

        //saveプロセス管理(証券会社)
        this.saveProcessManagement(l_institution);

        //1.6 レスポンスデータを生成する
        WEB3AdminFeqExchangeRegistCompleteResponse l_response =
            new WEB3AdminFeqExchangeRegistCompleteResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (persist通貨)<BR>
     * 通貨テーブルに為替情報を更新する。<BR>
     * <BR>
     * ○　@為替レートに入力がない場合（為替情報.売付為替レート == null && 為替<BR>
     * 情報.買付為替レート == null）<BR>
     * 　@処理を終了する。（return;）<BR>
     * <BR>
     * ○　@為替レートに入力がある場合<BR>
     * 　@為替レートを通貨テーブルに更新する。<BR>
     * 　@　@更新内容は以下の通り。<BR>
     * <BR>
     * 　@　@基準為替（為替情報.レート区分 == ”基準為替”）の場合、<BR>
     * 　@　@　@－【xTrade】補足資料.DB更新\\21.（管）為替登録<BR>
     * 　@　@　@　@「為替登録_通貨テーブル仕様.xls#為替登録_通貨テーブル DB更新<BR>
     * 　@　@　@　@（基準為替）」参照。<BR>
     * <BR>
     * 　@　@基準為替（為替情報.レート区分 == ”約定為替”）の場合、<BR>
     * 　@　@　@－【xTrade】補足資料.DB更新\\21.（管）為替登録<BR>
     * 　@　@　@　@「為替登録_通貨テーブル仕様.xls#為替登録_通貨テーブル DB更新<BR>
     * 　@　@　@　@（約定為替）」参照。<BR>
     * <BR>
     * 　@　@以外の場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02159<BR>
     * @@param l_exchangeInfo - (為替情報)<BR>
     * 為替情報メッセージデータオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42BA603901F3
     */
    private void persistCurrency(WEB3FeqExchangeUnit l_exchangeInfo) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " persistCurrency(WEB3FeqExchangeUnit) ";
        log.entering(STR_METHOD_NAME);
        
        log.debug("為替情報.売付為替レート = " + l_exchangeInfo.sellExchangeRate);
        log.debug("為替情報.買付為替レート = " + l_exchangeInfo.buyExchangeRate);
        
        //通貨テーブルに為替情報を更新する。
        //
        //○　@為替レートに入力がない場合
        //（為替情報.売付為替レート == null && 為替情報.買付為替レート == null）
        //　@処理を終了する。（return;）
        if (WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate)
            && WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
        {
            log.debug("為替レートに入力がない場合");
            return;
        }

        //○　@為替レートに入力がある場合
        //　@為替レートを通貨テーブルに更新する。
        //　@　@更新内容は以下の通り。
        //
        //　@　@基準為替（為替情報.レート区分 == ”基準為替”）の場合、
        //　@　@　@－【xTrade】補足資料.DB更新\\21.（管）為替登録
        //　@　@　@　@「為替登録_通貨テーブル仕様.xls#為替登録_通貨テーブル DB更新（基準為替）」参照。
        //
        //　@　@基準為替（為替情報.レート区分 == ”約定為替”）の場合、
        //　@　@　@－【xTrade】補足資料.DB更新\\21.（管）為替登録
        //　@　@　@　@「為替登録_通貨テーブル仕様.xls#為替登録_通貨テーブル DB更新（約定為替）」参照。
        //
        //　@　@以外の場合、例外をスローする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        String l_strCurrencyCode = l_exchangeInfo.currencyCode;

        log.debug("l_strInstitutionCode = " + l_strInstitutionCode);
        log.debug("l_strCurrencyCode = " + l_strCurrencyCode);
        
        GenCurrencyParams l_currencyParams  = null;
        try
        {
            GenCurrencyRow l_row = 
                GenCurrencyDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
            l_currencyParams = new GenCurrencyParams(l_row);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
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
        
        log.debug("為替情報.レート区分 = " + l_exchangeInfo.rateDiv);

        if (WEB3FeqRateDivDef.BASE_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            //○　@為替情報.レート区分 == ”基準為替”の場合。                
            log.debug("○　@為替情報.レート区分 == ”基準為替”の場合");
            
            //今回売付為替レート   
            //（為替情報.売付為替レート == null）の場合、（既存値）。
            //（為替情報.売付為替レート != null）の場合、
            //　@－入力値（為替情報.売付為替レート）をセット。
            //前回売付為替レート   
            //（為替情報.売付為替レート == null）の場合、（既存値）。
            //（為替情報.売付為替レート != null）の場合、
            //　@－今回売付為替レート（※更新前の値）をセット。                
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                l_currencyParams.setPrevSellRate(l_currencyParams.current_sell_rate);
                l_currencyParams.setCurrentSellRate(l_dblSellExchangeRate);                    
            }
        
            //今回買付為替レート
            //（為替情報.買付為替レート == null）の場合、（既存値）。
            //（為替情報.買付為替レート != null）の場合、
            //　@－入力値（為替情報.買付為替レート）をセット。
            //前回買付為替レート   
            //（為替情報.買付為替レート == null）の場合、（既存値）。
            //（為替情報.買付為替レート != null）の場合、
            //　@－今回買付為替レート（※更新前の値）をセット。
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {
                l_currencyParams.setPrevBuyRate(l_currencyParams.current_buy_rate);
                
                l_currencyParams.setCurrentBuyRate(l_dblBuyExchangeRate);
            }    

            //基準為替更新者コード : 管理者コード
            l_currencyParams.setRateLastUpdater(l_admin.getAdministratorCode());
            
            //基準為替更新日付:現在日時
            l_currencyParams.setRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
                
            //更新者コード:管理者コード
            l_currencyParams.setLastUpdater(l_admin.getAdministratorCode());
            
            //更新日付:現在日時
            l_currencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());

        }
        else if (WEB3FeqRateDivDef.EXECUTED_EXCHANGE.equals(l_exchangeInfo.rateDiv))
        {
            log.debug("○　@為替情報.レート区分 == ”約定為替”の場合");
            //○　@為替情報.レート区分 == ”約定為替”
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.sellExchangeRate))
            {
                //前回売付約定為替レート   
                //（為替情報.売付為替レート == null）の場合、（既存値）。
                //（為替情報.売付為替レート != null）の場合、
                //　@－今回売付約定為替レート（※更新前の値）をセット。
                l_currencyParams.setPrevSellExecRate(l_currencyParams.current_sell_exec_rate);
                
                //今回売付約定為替レート   
                //（為替情報.売付為替レート == null）の場合、（既存値）。
                //（為替情報.売付為替レート != null）の場合、
                //　@－入力値（為替情報.売付為替レート）をセット。
                l_currencyParams.setCurrentSellExecRate(l_dblSellExchangeRate);
            }
            
            if (!WEB3StringTypeUtility.isEmpty(l_exchangeInfo.buyExchangeRate))
            {    
                //前回買付約定為替レート   
                //（為替情報.買付為替レート == null）の場合、（既存値）。
                //（為替情報.買付為替レート != null）の場合、
                //　@－今回買付約定為替レート（※更新前の値）をセット。
                l_currencyParams.setPrevBuyExecRate(l_currencyParams.current_buy_exec_rate);
                
                //今回買付約定為替レート
                //（為替情報.買付為替レート == null）の場合、（既存値）。
                //（為替情報.買付為替レート != null）の場合、
                //　@－入力値（為替情報.買付為替レート）をセット。                    
                l_currencyParams.setCurrentBuyExecRate(l_dblBuyExchangeRate);
            }              

            //約定為替更新者コード : 管理者コード
            l_currencyParams.setExecRateLastUpdater(l_admin.getAdministratorCode());

            //約定為替更新日付:現在日時
            l_currencyParams.setExecRateUpdateTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
                
            //更新者コード:管理者コード
            l_currencyParams.setLastUpdater(l_admin.getAdministratorCode());
            
            //更新日付:現在日時
            l_currencyParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());           
        }
        else
        {
            //以外の場合、例外をスローする。
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02159, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try 
        {
            WEB3DataAccessUtility.updateRow(l_currencyParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
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

    /**
     * (saveプロセス管理)<BR>
     * プロセス管理テーブルを更新する。<BR>
     * <BR>
     * １） 以下の条件で「プロセス管理テーブル」から検索する。<BR>
     * 　@[検索条件]<BR>
     * 　@　@プロセスＩＤ＝'0006:余力再計算基準時間'<BR>
     * 　@　@証券会社コード＝証券会社オブジェクト.証券会社コード<BR>
     * <BR>
     * ２） 検索結果が取得できる場合、<BR>
     * 　@取得した検索結果数分LOOP処理<BR>
     * 　@　@ＤＢ更新を行う。<BR>
     * 　@　@　@「為替登録_プロセス管理テーブル仕様.xls<BR>
     * 　@　@　@　@　@#為替登録_プロセス管理 DB更新(Update)」参照。<BR>
     * <BR>
     * ３） 検索結果が取得できない場合、<BR>
     * 　@３-１）部店コード一覧を取得<BR>
     * 　@　@証券会社オブジェクト.getBranches()<BR>
     * <BR>
     * 　@３-２）３-１）で取得した検索結果数分LOOP処理<BR>
     * 　@　@ＤＢ更新を行う。<BR>
     * 　@　@　@「為替登録_プロセス管理テーブル仕様.xls<BR>
     * 　@　@　@　@　@#為替登録_プロセス管理 DB更新(Insert)」参照。<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@throws WEB3BaseException
     */
    private void saveProcessManagement(Institution l_institution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveProcessManagement(Institution)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_queryProcessor = null;
        //以下の条件で「プロセス管理テーブル」から検索する
        //[検索条件]
        // プロセスＩＤ＝'0006:余力再計算基準時間'
        // 証券会社コード＝証券会社オブジェクト.証券会社コード
        StringBuffer l_sbSql = new StringBuffer();
        l_sbSql.append(" process_id = ? ");
        l_sbSql.append(" and institution_code = ? ");

        Object[] l_sqlValues = new Object[]{
            WEB3TPProcessManagementIdDef.TP_DATUM_TIME,
            l_institution.getInstitutionCode()};

        List l_lisResults = null;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                ProcessManagementRow.TYPE,
                l_sbSql.toString(),
                l_sqlValues);
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

        if (!l_lisResults.isEmpty())
        {
            //検索結果が取得できる場合
            //取得した検索結果数分LOOP処理
            //ＤＢ更新を行う
            // 「為替登録_プロセス管理テーブル仕様.xls#為替登録_プロセス管理 DB更新(Update)」参照
            Map l_mapChanges = new HashMap();
            l_mapChanges.put("last_updater", WEB3LastUpdaterDef.RETIMED_BY_AP);
            l_mapChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            try
            {
                l_queryProcessor.doUpdateAllQuery(
                    ProcessManagementRow.TYPE,
                    l_sbSql.toString(),
                    l_sqlValues,
                    l_mapChanges);
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
        else
        {
            //検索結果が取得できない場合
            //部店コード一覧を取得
            //証券会社オブジェクト.getBranches()
            Branch[] l_branchs = l_institution.getBranches();

            //取得した検索結果数分LOOP処理
            //ＤＢ更新を行う
            //「為替登録_プロセス管理テーブル仕様.xls#為替登録_プロセス管理 DB更新(Insert)」参照
            ProcessManagementParams l_processManagementParams =
                new ProcessManagementParams();

            //プロセスＩＤ:'0006'
            l_processManagementParams.setProcessId(WEB3TPProcessManagementIdDef.TP_DATUM_TIME);

            //証券会社コード:証券会社.証券会社コード
            l_processManagementParams.setInstitutionCode(
                l_institution.getInstitutionCode());

            //処理区分:'1'
            l_processManagementParams.setStatus(WEB3StatusDef.DEALT);

            //最終更新者:retimed_by_ap
            l_processManagementParams.setLastUpdater(WEB3LastUpdaterDef.RETIMED_BY_AP);

            for (int i = 0; i < l_branchs.length; i++)
            {
                //部店コード:証券会社.部店コード[i]
                l_processManagementParams.setBranchCode(l_branchs[i].getBranchCode());

                //最終更新時刻:"現在日時(GtlUtils.getSystemTimestamp())"
                l_processManagementParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                try
                {
                    l_queryProcessor.doInsertQuery(l_processManagementParams);
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
}
@
