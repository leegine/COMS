head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqForeignCostRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式現地手数料登録サービスImpl(WEB3AdminFeqForeignCostRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 戴義波(中訊) 新規作成
                   2005/08/01 韋念瓊(中訊) レビュー
Revesion History : 2007/01/16 齊珂 (中訊) 仕様変更No.335を対応
Revesion History : 2008/11/12 劉仁和 (中訊) 仕様変更No.494、ＤＢ更新仕様No.101
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.data.ForeignCostRow;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.feq.define.WEB3FeqRoundDivDef;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqForeignCostRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (外国株式現地手数料登録サービスImpl)<BR>
 * 外国株式現地手数料登録サービス実装クラス<BR>
 * 
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistServiceImpl implements WEB3AdminFeqForeignCostRegistService 
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostRegistServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F00177
     */
    public WEB3AdminFeqForeignCostRegistServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式現地手数料登録サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    get入力画面()<BR>
     *    validate登録()<BR>
     *    submit登録()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214982000EC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータはNullです");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response;

        //リクエストデータの型により、以下のメソッドをコールする。
        //－get入力画面()
        if (l_request instanceof WEB3AdminFeqForeignCostRegistInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminFeqForeignCostRegistInputRequest) l_request);
        }
        //－validate登録()
        else if (l_request instanceof WEB3AdminFeqForeignCostRegistConfirmRequest)
        {
            l_response = 
                validateRegist((WEB3AdminFeqForeignCostRegistConfirmRequest) l_request);
        }
        //－submit登録()
        else if (l_request instanceof WEB3AdminFeqForeignCostRegistCompleteRequest)
        {
            l_response = 
                submitRegist((WEB3AdminFeqForeignCostRegistCompleteRequest) l_request);
        } 
        else
        {
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
     * シーケンス図<BR>
     * 「（（管）現地手数料登録）get入力画面」 参照<BR>
     * ======================================================== <BR>
     * シーケンス図(「(外国株式サービスモデル) /<BR>
     * （（管）現地手数料登録）get入力画面 <BR>
     *  1.9.1 (*1)で生成した市場コードの配列にリクエスト.市場コードが<BR>
     * 含まれてない場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00645 <BR>
     * ========================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42149847004F
     */
    protected WEB3AdminFeqForeignCostRegistInputResponse getInputScreen(
        WEB3AdminFeqForeignCostRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFeqForeignCostRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();

        //1.2 ログイン情報から管理者インスタンスを取得する
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 権限チェックを行う。
        //[引数]
        //機@能カテゴリーコード： 機@能カテゴリーコード.”外株（現地手数料管理）”
        //is更新： false
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
            false);

        //1.4 証券会社コードを取得する
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //get取扱可能市場
        //証券会社：　@get証券会社コード()の戻り値
        //銘柄タイプ：　@ProductTypeEnum.外国株式
        String[] l_strPossibleMarkets = WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
            l_strInstitutionCode, ProductTypeEnum.FOREIGN_EQUITY);
       
        if (l_strPossibleMarkets.length == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "テーブルに該当するデータがありません。");
        }
        
        //1.6 空のリストを生成する
        List l_lisMarketCode = new ArrayList();

        //get取扱可能市場()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_strPossibleMarkets.length; i++)
        {
            //市場コードをリストに追加する。 
            //[引数]
            //arg0： get取扱可能市場()の戻り値の該当要素
            l_lisMarketCode.add(l_strPossibleMarkets[i]);
        }

        //1.8 市場コードの配列を取得する
        String[] l_strMarketCodes = new String[l_lisMarketCode.size()];
        l_lisMarketCode.toArray(l_strMarketCodes);

        //1.10 レスポンスデータを生成する
        WEB3AdminFeqForeignCostRegistInputResponse l_response =
            new WEB3AdminFeqForeignCostRegistInputResponse();
        l_response.foreignCostRegist = null;
        l_response.marketList = l_strMarketCodes;

        WEB3AdminFeqForeignCostUnit[] l_feqForeignCostUnits = null;
        //1.9 (*2) リクエスト.市場コード != null の場合
        if (!WEB3StringTypeUtility.isEmpty(l_request.marketCode)) 
        {
            List l_lisRows = null;
            try
            {
                //1.9.1 (*1)で生成した市場コードの配列にリクエスト.市場コードが
                //含まれてない場合、例外をスローする。
                // class: WEB3BusinessLayerException
                // tag: BUSINESS_ERROR_00645
                if (!WEB3Toolkit.contain(l_lisMarketCode, l_request.marketCode))
                {
                    log.debug("市場コードの配列にリクエスト.市場コードが含まれてない");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00645, 
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //1.9.2 クエリプロセッサを取得する
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                //1.9.3 海外諸経費マスタテーブルから、該当データを取得する。
                //[引数]
                //Rowタイプ： 海外諸経費マスタRow.TYPE
                //Where： "institution_code = ? and market_code = ? and cost_div = ? and side_div = ? "
                String l_strWhere = " institution_code = ? and market_code = ? and cost_div = ? and side_div = ? ";
                //orderBy： "amount_from, appli_start_date"
                String l_strOrderBy = " amount_from, appli_start_date ";
                //Condition： null
                //リスト： 以下の要素の配列
                Object[] l_objBinds = new Object[4];
                //   get証券会社コード()の戻り値
                l_objBinds[0] = l_strInstitutionCode;
                //   リクエスト.市場コード
                l_objBinds[1] = l_request.marketCode;
                //   リクエスト.コスト区分
                l_objBinds[2] = l_request.costDiv;
                //   リクエスト.売買区分
                l_objBinds[3] = l_request.dealingType;
                l_lisRows = l_processor.doFindAllQuery(
                    ForeignCostRow.TYPE, 
                    l_strWhere,
                    l_strOrderBy, 
                    null, 
                    l_objBinds);
            }
            catch (DataFindException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + STR_METHOD_NAME, 
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

            //1.9.4 空のリストを生成する
            List l_lisForeignCost = new ArrayList();

            if (l_lisRows != null)
            {
                //1.9.5 (*3) 取得したレコード毎にLoop処理
                for (int j = 0; j < l_lisRows.size(); j++) 
                {
                    ForeignCostRow l_foreignCostRow =
                        (ForeignCostRow) l_lisRows.get(j);
                    //1.9.5.1 現地手数料情報インスタンスを生成する
                    WEB3AdminFeqForeignCostUnit l_unit =
                        new WEB3AdminFeqForeignCostUnit();
                    //1.9.5.2 (*4) プロパティセット
                    //(*4) 以下のとおりに、プロパティをセットする。
                    //適用期間（自）： 海外諸経費テーブル.摘要開始年月日
                    l_unit.applyStartDate = l_foreignCostRow.getAppliStartDate();
                    //適用期間（至）： 海外諸経費テーブル.摘要終了年月日
                    l_unit.applyEndDate = l_foreignCostRow.getAppliEndDate();
                    //取引金額（自）： 海外諸経費テーブル.売買代金（FROM）
                    l_unit.tradingAmtFrom = 
                        WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getAmountFrom());
                    //取引金額（至）： 海外諸経費テーブル.売買代金（TO）
                    if (l_foreignCostRow.getAmountToIsNull())
                    {
                        l_unit.tradingAmtTo = null;
                    }
                    else
                    {
                        l_unit.tradingAmtTo =
                            WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getAmountTo());
                    }
                    //徴収率： 海外諸経費テーブル.徴収率
                    l_unit.collectRate = 
                        WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getCommisionRate());
                    //付加金額： 海外諸経費テーブル.付加金額
                    l_unit.additionalAmt = 
                        WEB3StringTypeUtility.formatNumber(l_foreignCostRow.getAddAmount());
    
                    //1.9.5.3 リストに追加する。
                    //[引数]
                    //arg0： 現地手数料情報オブジェクト
                    l_lisForeignCost.add(l_unit);
                }
            }
            
            //1.11 (*5) プロパティセット
            //(*5) 以下のとおりに、プロパティをセットする。
            //現地手数料情報一覧： 現地手数料情報の配列(*)
            //市場コード一覧： 市場コードの配列
            //(*)リクエスト.市場コード == null の場合は、null
            //1.9.6 現地手数料情報の配列を取得する
            l_feqForeignCostUnits = 
                new WEB3AdminFeqForeignCostUnit[l_lisForeignCost.size()];
            l_lisForeignCost.toArray(l_feqForeignCostUnits);
            l_response.foreignCostRegist = l_feqForeignCostUnits;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate登録)<BR>
     * 登録内容の確認を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）現地手数料登録）validate登録」 参照<BR>
     * ======================================================== <BR>
     * シーケンス図(「(外国株式サービスモデル) /<BR>
     * （（管）現地手数料登録）validate登録 <BR> 
     *  1.4 市場が取得できなかった場合、市場コード不正として<BR>
     *　@例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00003 <BR>
     * ========================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214984F01C6
     */
    protected WEB3AdminFeqForeignCostRegistConfirmResponse validateRegist(
        WEB3AdminFeqForeignCostRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateRegist(WEB3AdminFeqForeignCostRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();

        //1.2 ログイン情報から管理者インスタンスを取得する
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 権限チェックを行う。
        //[引数]
        //機@能カテゴリーコード： 機@能カテゴリーコード.”外株（現地手数料管理）”
        //is更新： true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
            true);

        //1.4 市場オブジェクトを取得する。

        //[引数]
        //証券会社コード： 管理者.get証券会社コード()の戻り値
        //市場コード： リクエスト.市場コード
        //市場が取得できなかった場合、市場コード不正として
        //例外をスローする。
        try
        {
            l_finObjectManager.getMarket(
                l_web3Administrator.getInstitutionCode(),
                l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("市場が取得できなかった場合、市場コード不正", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.5 レスポンスデータを生成する
        WEB3AdminFeqForeignCostRegistConfirmResponse l_response =
            new WEB3AdminFeqForeignCostRegistConfirmResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit登録)<BR>
     * 登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）現地手数料登録）submit登録」 参照<BR>
     * ======================================================== <BR>
     * シーケンス図(「(外国株式サービスモデル) /<BR>
     * （（管）現地手数料登録）submit登録 <BR> 
     *  1.5 市場が取得できなかった場合、市場コード不正として<BR>
     *　@例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00003 <BR>
     * ========================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42149854035D
     */
    protected WEB3AdminFeqForeignCostRegistCompleteResponse submitRegist(
        WEB3AdminFeqForeignCostRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitRegist(WEB3AdminFeqForeignCostRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager 
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        //1.1 validate()
        l_request.validate();

        //1.2 ログイン情報から管理者インスタンスを取得する
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 権限チェックを行う。
        //[引数]
        //機@能カテゴリーコード： 機@能カテゴリーコード.”外株（現地手数料管理）”
        //is更新： true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_LOCATION_COMMISSION_MANAGE, 
            true);

        //1.4 パスワードのチェックを行う。

        //[引数]
        //パスワード： リクエスト.暗証番号
        l_web3Administrator.validateTradingPassword(l_request.password);

        //1.5 市場オブジェクトを取得する。

        //[引数]
        //証券会社コード： 管理者.get証券会社コード()の戻り値
        //市場コード： リクエスト.市場コード
        //市場が取得できなかった場合、市場コード不正として
        //例外をスローする。
        try 
        {
            l_finObjectManager.getMarket(
                l_web3Administrator.getInstitutionCode(),
                l_request.marketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("市場が取得できなかった場合、市場コード不正", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.6 クエリプロセッサを取得する。
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //1.7 海外諸経費マスタテーブルのレコードを削除する。

            //[引数]
            //Rowタイプ： 海外諸経費マスタRow.TYPE
            //Where： "institution_code = ? and market_code = ? and cost_div = ? and side_div = ? "
            String l_strWhere = " institution_code = ? and market_code = ? and cost_div = ? and side_div = ? ";
            //リスト： 以下の要素の配列
            Object[] l_objBinds = new Object[4];
            //   管理者.証券会社コード
            l_objBinds[0] = l_web3Administrator.getInstitutionCode();
            //   リクエスト.市場コード
            l_objBinds[1] = l_request.marketCode;
            //   リクエスト.コスト区分
            l_objBinds[2] = l_request.costDiv;
            //   リクエスト.売買区分
            l_objBinds[3] = l_request.dealingType;
            l_processor.doDeleteAllQuery(
                ForeignCostRow.TYPE, 
                l_strWhere, 
                l_objBinds);
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

        //1.8 (*1) リクエスト.現地手数料情報一覧の各要素について、Loop処理
        for (int i = 0; i < l_request.feqLocalFeeUnit.length; i++) 
        {
            //1.8.1 海外諸経費マスタテーブルに登録する。

            //[引数]
            //管理者： 管理者オブジェクト
            //現地手数料情報： 現地手数料情報オブジェクト
            //市場コード： リクエスト.市場コード
            //諸経費区分： リクエスト.コスト区分
            //売買区分： リクエスト.売買区分
            createForeignCostMaster(
                l_web3Administrator,
                l_request.feqLocalFeeUnit[i],
                l_request.marketCode,
                l_request.costDiv,
                l_request.dealingType);
        }

        //1.9 レスポンスデータを生成する
        WEB3AdminFeqForeignCostRegistCompleteResponse l_response =
            new WEB3AdminFeqForeignCostRegistCompleteResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create海外諸経費マスタ)<BR>
     * 海外諸経費マスタテーブルのレコードを登録する。<BR>
     * <BR>
     * １）海外諸経費マスタ行インスタンスを生成する。<BR>
     * <BR>
     * ２）海外諸経費マスタ行インスタンスにプロパティをセットする。<BR>
     * <BR>
     *    ※DB更新仕様参照<BR>
     * <BR>
     * ３）DBに登録する。<BR><BR>
     * <BR>
     *    WEB3DataAccessUtility.insertRow()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    l_row： 海外諸経費マスタ行インスタンス<BR>
     * 
     *    ※insertに失敗した場合は、「登録データ不整合（重複登録不可）」の業務エラーとする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_02183<BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * 
     * @@param l_offshoreCommissionInfo - (現地手数料情報)<BR>
     * 現地手数料情報オブジェクト<BR>
     * 
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * 
     * @@param l_strCostDiv - (諸経費区分)<BR>
     * 諸経費区分<BR>
     * 
     * @@param l_strSideDiv - (売買区分)<BR>
     * 売買区分<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 42B236880144
     */
    protected void createForeignCostMaster(
        WEB3Administrator l_admin, 
        WEB3AdminFeqForeignCostUnit l_offshoreCommissionInfo, 
        String l_strMarketCode, 
        String l_strCostDiv,
        String l_strSideDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createForeignCostMaster(WEB3Administrator,"
            + "WEB3AdminFeqForeignCostUnit, String, String, String )";
        log.entering(STR_METHOD_NAME);
        //海外諸経費マスタテーブルのレコードを登録する。
        //１）海外諸経費マスタ行インスタンスを生成する。
        ForeignCostParams l_foreignCostParams = new ForeignCostParams();
        //２）海外諸経費マスタ行インスタンスにプロパティをセットする。
        //   ※DB更新仕様参照
        //証券会社コード: 管理者.証券会社コード
        l_foreignCostParams.setInstitutionCode(l_admin.getInstitutionCode());
        //市場コード:引数.市場コード
        l_foreignCostParams.setMarketCode(l_strMarketCode);
        //諸経費区分:引数.諸経費区分
        l_foreignCostParams.setCostDiv(l_strCostDiv);
        //摘要開始年月日:現地手数料情報.適用期間（自）
        l_foreignCostParams.setAppliStartDate(l_offshoreCommissionInfo.applyStartDate);
        //摘要終了年月日:現地手数料情報.適用期間（至）
        //現地手数料情報.適用期間（至）==nullの場合は、"9999/12/31"をセット
        Date l_datApplyEndDate = null;
        if (null == l_offshoreCommissionInfo.applyEndDate) 
        {
           l_datApplyEndDate = WEB3DateUtility.getDate("9999/12/31", "yyyy/MM/dd");
        } 
        else 
        {
            l_datApplyEndDate = l_offshoreCommissionInfo.applyEndDate;
        }
        l_foreignCostParams.setAppliEndDate(l_datApplyEndDate);
        //売買代金（FROM）: 現地手数料情報.取引金額（自）
        l_foreignCostParams.setAmountFrom(
            Double.parseDouble(l_offshoreCommissionInfo.tradingAmtFrom));
        //売買代金（TO）:現地手数料情報.取引金額（至）
        //現地手数料情報.取引金額（至）==nullの場合は、99999999999.99をセット
        double l_dblTradingAmtTo = 99999999999.99D;
        if (l_offshoreCommissionInfo.tradingAmtTo != null) 
        {
            l_dblTradingAmtTo = 
                Double.parseDouble(l_offshoreCommissionInfo.tradingAmtTo);
        }

        l_foreignCostParams.setAmountTo(l_dblTradingAmtTo);
        //徴収率:現地手数料情報.徴収率
        l_foreignCostParams.setCommisionRate(
            Double.parseDouble(l_offshoreCommissionInfo.collectRate));
        //付加金額:現地手数料情報.付加金額
        l_foreignCostParams.setAddAmount(
            Double.parseDouble(l_offshoreCommissionInfo.additionalAmt));
        
        //市場＝香港の場合で諸経費区分が03 or 04の場合
        //（小数点以下切上げ　@以外は小数点第３位を四捨五入
        if ((WEB3MarketCodeDef.HONGKONG.equals(l_strMarketCode))
             && (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(l_strCostDiv)
             || WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(l_strCostDiv)))
        {
            //小数部桁数:0
            l_foreignCostParams.setScale(0);
            //計算結果丸め方式: 2：”切上”
            l_foreignCostParams.setRoundDiv(WEB3FeqRoundDivDef.CUT_UP);
        }
        else
        {
            //小数部桁数:2
            l_foreignCostParams.setScale(2);
            //計算結果丸め方式: 0：”四捨五入”
            l_foreignCostParams.setRoundDiv(WEB3FeqRoundDivDef.ROUND);
        }
        //更新者コード: 管理者.管理者コード
        l_foreignCostParams.setLastUpdater(l_admin.getAdministratorCode());
        //作成日付: システムタイムスタンプ
        l_foreignCostParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        //更新日付: システムタイムスタンプ
        l_foreignCostParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        //売買区分: 引数.売買区分
        l_foreignCostParams.setSideDiv(l_strSideDiv);

        //３）DBに登録する。
        //   WEB3DataAccessUtility.insertRow()をコールする。
        //
        //   [引数]
        //   l_row： 海外諸経費マスタ行インスタンス
        //   ※insertに失敗した場合は、「登録データ不整合（重複登録不可）」の業務エラーとする。
        try
        {
            WEB3DataAccessUtility.insertRow(l_foreignCostParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02183, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02183, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
