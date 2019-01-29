head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止登録サービスImpl(WEB3AdminToTradeStopRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04  鄭徳懇(中訊) 新規作成
                 : 2006/04/12  鄭徳懇(中訊) 仕様変更・モデル052
*/
package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopDao;
import webbroker3.triggerorder.base.data.TriggerOrderStopParams;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止登録サービスImpl)<BR>
 * トリガー注文管理者・取扱停止登録サービス実装クラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminToTradeStopRegistServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopRegistService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopRegistServiceImpl.class);

    /**
     * @@roseuid 4430DC770138
     */
    public WEB3AdminToTradeStopRegistServiceImpl() 
    {
     
    }
    
    /**
     * 取扱停止登録処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○トリガー注文管理者・取扱停止登録確認リクエストの場合<BR>
     * 　@this.validate登録()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・取扱停止登録完了リクエストの場合<BR>
     * 　@this.submit登録()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D4D70155
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
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
        //○トリガー注文管理者・取扱停止登録確認リクエストの場合
        // 　@this.validate登録()をコールする。
        if (l_request instanceof WEB3AdminToTradeStopRegConfirmRequest)
        {
            l_response = this.validateRegist((WEB3AdminToTradeStopRegConfirmRequest) l_request);
        }
        // ○トリガー注文管理者・取扱停止登録完了リクエストの場合
        // 　@this.submit登録()をコールする。
        else if (l_request instanceof WEB3AdminToTradeStopRegCompleteRequest)
        {
            l_response = this.submitRegist((WEB3AdminToTradeStopRegCompleteRequest) l_request);
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
     * (validate登録)<BR>
     * 取扱停止登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止登録サービス）validate登録」参照。<BR>
     * ============================================================== <BR>
     *    シーケンス図：「（トリガー注文管理者・取扱停止登録サービス）validate登録」<BR>
     *    具体位置：<BR>
     *    1.5 getProduct(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)<BR>
     *    取得できなかった場合、<BR>
     *    「条件に該当するデータが存在しない。」の<BR>
     *    例外をスローする。<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止登録確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopRegConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D704009A
     */
    protected WEB3AdminToTradeStopRegConfirmResponse validateRegist(WEB3AdminToTradeStopRegConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminToTradeStopRegConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1.4 get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 getProduct(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();
        EqTypeProduct l_eqtypeProduct = null;
        try
        {
            l_eqtypeProduct = l_productMgr.getProduct(l_institution, l_request.tradeStopInfoUnit.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("条件に該当するデータが存在しない。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        //1.6 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.7 validate重複データ(String, 取扱停止情報) 
        this.validateDuplicateData(l_strInstitutionCode, l_request.tradeStopInfoUnit);
        
        //1.8 createResponse( )
        WEB3AdminToTradeStopRegConfirmResponse l_response = 
            (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
        
        //1.9 (*)プロパティセット
        //銘柄名   ＝　@getProduct()の戻り値.getDataSourceObject().銘柄名
        l_response.productName = ((EqtypeProductRow) l_eqtypeProduct.getDataSourceObject()).getStandardName();
        
        //1.10 
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit登録)<BR>
     * 取扱停止登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止登録サービス）submit登録」参照。<BR>
     * ============================================================== <BR>
     *    シーケンス図：「（トリガー注文管理者・取扱停止登録サービス）submit登録」<BR>
     *    具体位置：<BR>
     *    1.6 getProduct(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)   <BR>
     *    取得できなかった場合、<BR>
     *    「条件に該当するデータが存在しない。」の<BR>
     *    例外をスローする。<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止登録完了リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopRegCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D70400B9
     */
    protected WEB3AdminToTradeStopRegCompleteResponse submitRegist(WEB3AdminToTradeStopRegCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminToTradeStopRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);
        
        //1.4 validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.6 getProduct(arg0 : Institution, arg1 : 論理ビュー::java::lang::String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr = (WEB3EquityProductManager) l_tradingModule.getProductManager();

        try
        {
            l_productMgr.getProduct(l_institution, l_request.tradeStopInfoUnit.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("条件に該当するデータが存在しない。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        //1.7 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.8 validate重複データ(String, 取扱停止情報)
        this.validateDuplicateData(l_strInstitutionCode, l_request.tradeStopInfoUnit);
        
        //1.9 submit新規取扱停止(管理者, 取扱停止情報)
        this.submitNewTradeStop(l_admin, l_request.tradeStopInfoUnit);
        
        //1.10 createResponse( )
        WEB3AdminToTradeStopRegCompleteResponse l_response =
            (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        //1.11
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate重複データ)<BR>
     * 入力された取扱停止情報が既に登録済みかどうか<BR>
     * チェックする。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件で、特殊執行条件取扱停止テーブルを<BR>
     * 　@検索する。<BR>
     * 　@　@【検索条件】<BR>
     * 　@　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@　@And 部店コード = "000"<BR>
     * 　@　@　@And 設定対象種別 = "銘柄"<BR>
     * 　@　@　@And キー情報 = パラメータ.取扱停止情報.銘柄コード<BR>
     * 　@　@　@And 有効期限From = パラメータ.取扱停止情報.有効期限From<BR>
     * 　@　@　@And 削除フラグ = "DEFAULT"<BR>
     * <BR>
     * ２）　@検索結果が取得できた場合、<BR>
     * 　@「入力した取扱停止情報は既に登録済」の例外をスローする。<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_02432<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_tradeStopInfoUnit - (取扱停止情報)<BR>
     * 取扱停止情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44113CBE005B
     */
    protected void validateDuplicateData(String l_strInstitutionCode, WEB3AdminToTradeStopInfoUnit l_tradeStopInfoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateData(String, WEB3AdminToTradeStopInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@DB検索
        // 　@以下の条件で、特殊執行条件取扱停止テーブルを検索する。
        // 　@　@【検索条件】
        // 　@　@　@証券会社コード = パラメータ.証券会社コード
        // 　@　@　@And 部店コード = "000"
        // 　@　@　@And 設定対象種別 = "銘柄"
        // 　@　@　@And キー情報 = パラメータ.取扱停止情報.銘柄コード
        // 　@　@　@And 有効期限From = パラメータ.取扱停止情報.有効期限From
        // 　@　@　@And 削除フラグ = "DEFAULT"
        String l_strWhere = " institution_code = ? and branch_code = ? and target_type = ? and key = ? ";
        l_strWhere += "and valid_term_from = ? and delete_flag = ? ";
        
        Object[] l_objValues = new Object[] {
            l_strInstitutionCode,
            WEB3BranchCodeDef.DEFAULT,
            WEB3TargetTypeDef.PRODUCT,
            l_tradeStopInfoUnit.productCode,
            l_tradeStopInfoUnit.expirationStartDate,
            BooleanEnum.FALSE};
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TriggerOrderStopRow.TYPE,
                l_strWhere,
                null,
                l_objValues);
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
        
        // ２）　@検索結果が取得できた場合、
        //「入力した取扱停止情報は既に登録済」の例外をスローする。
        if (l_lisRecords != null && l_lisRecords.size() > 0)
        {
            log.debug("入力した取扱停止情報は既に登録済です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02432,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "入力した取扱停止情報は既に登録済です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit新規取扱停止)<BR>
     * 特殊執行条件取扱停止テーブルに1レコードinsertする。<BR>
     * <BR>
     * １）　@特殊執行条件取扱停止Paramsを生成する。<BR>
     * <BR>
     * ２）　@生成したParamsに以下のプロパティをセットする。<BR>
     * 　@　@特殊執行条件取扱停止ID =<BR>
     * 　@　@　@特殊執行条件取扱停止Dao.newPkValue()<BR>
     * 　@　@証券会社コード = 管理者.証券会社コード<BR>
     * 　@　@部店コード = パラメータ.取扱停止情報の同名項目<BR>
     * 　@　@設定対象種別 = "銘柄"<BR>
     * 　@　@キー情報 = パラメータ.取扱停止情報.銘柄コード<BR>
     * 　@　@停止理由 = パラメータ.取扱停止情報の同名項目<BR>
     * 　@　@有効期限From = パラメータ.取扱停止情報の同名項目<BR>
     * 　@　@有効期限To = パラメータ.取扱停止情報の同名項目<BR>
     * 　@　@削除フラグ = "DEFAULT"<BR>
     * 　@　@更新者コード = 管理者.管理者コード<BR>
     * 　@　@作成日付 = 現在時刻<BR>
     * 　@　@更新日付 = 現在時刻<BR>
     * <BR>
     * ３）　@パラメータ.取扱停止情報.注文停止状況一覧の要素数分、<BR>
     * 　@以下の処理をLoopする。<BR>
     * 　@３－１）　@処理対象の要素.条件注文種別 == "連続注文"の場合<BR>
     * 　@　@プロパティセットしたParams.連続注文停止フラグ =<BR>
     * 　@　@　@処理対象の要素.停止フラグをセット。<BR>
     * <BR>
     * 　@３－２）　@処理対象の要素.条件注文種別 == "OCO注文"の場合<BR>
     * 　@　@プロパティセットしたParams.OCO注文停止フラグ =<BR>
     * 　@　@　@処理対象の要素.停止フラグをセット。<BR>
     * <BR>
     * 　@３－３）　@処理対象の要素.条件注文種別 == "IFD注文"の場合<BR>
     * 　@　@プロパティセットしたParams.IFD注文停止フラグ =<BR>
     * 　@　@　@処理対象の要素.停止フラグをセット。<BR>
     * <BR>
     * 　@３－４）　@処理対象の要素.条件注文種別 == "逆指値注文"の場合<BR>
     * 　@　@プロパティセットしたParams.逆指値注文停止フラグ =<BR>
     * 　@　@　@処理対象の要素.停止フラグをセット。<BR>
     * <BR>
     * 　@３－５）　@処理対象の要素.条件注文種別 == "W指値注文"の場合<BR>
     * 　@　@プロパティセットしたParams.W指値注文停止フラグ =<BR>
     * 　@　@　@処理対象の要素.停止フラグをセット。<BR>
     * <BR>
     * ４）　@QueryProcessor.doInsertQuery()をコールする。<BR>
     * <BR>
     * 　@[doInsertQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@プロパティセットしたParams<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@param l_tradeStopInfoUnit - (取扱停止情報)<BR>
     * 取扱停止情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44113F64029E
     */
    protected void submitNewTradeStop(
        WEB3Administrator l_administrator,
        WEB3AdminToTradeStopInfoUnit l_tradeStopInfoUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitNewTradeStop(WEB3Administrator, WEB3AdminToTradeStopInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // １）　@特殊執行条件取扱停止Paramsを生成する。
        TriggerOrderStopParams l_params = new TriggerOrderStopParams();
        
        // ２）　@生成したParamsに以下のプロパティをセットする。
        // 　@　@証券会社コード = 管理者.証券会社コード
        l_params.setInstitutionCode(l_administrator.getInstitutionCode());
        // 　@　@部店コード = パラメータ.取扱停止情報の同名項目
        l_params.setBranchCode(l_tradeStopInfoUnit.branchCode);
        // 　@　@設定対象種別 = "銘柄"
        l_params.setTargetType(WEB3TargetTypeDef.PRODUCT);
        // 　@　@キー情報 = パラメータ.取扱停止情報.銘柄コード
        l_params.setKey(l_tradeStopInfoUnit.productCode);
        // 　@　@停止理由 = パラメータ.取扱停止情報の同名項目
        l_params.setStopReason(l_tradeStopInfoUnit.stopReason);
        // 　@　@有効期限From = パラメータ.取扱停止情報の同名項目
        l_params.setValidTermFrom(WEB3DateUtility.getDate(l_tradeStopInfoUnit.expirationStartDate, "yyyyMMdd"));
        // 　@　@有効期限To = パラメータ.取扱停止情報の同名項目
        l_params.setValidTermTo(WEB3DateUtility.getDate(l_tradeStopInfoUnit.expirationEndDate, "yyyyMMdd"));
        // 　@　@削除フラグ = "DEFAULT"
        l_params.setDeleteFlag(BooleanEnum.FALSE.intValue());
        // 　@　@更新者コード = 管理者.管理者コード
        l_params.setLastUpdater(l_administrator.getAdministratorCode());
        // 　@　@作成日付 = 現在時刻
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        // 　@　@更新日付 = 現在時刻
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        // ３）　@パラメータ.取扱停止情報.注文停止状況一覧の要素数分、
        // 　@以下の処理をLoopする。
        for (int i = 0, l_intValue = 0, l_intLen = l_tradeStopInfoUnit.orderStopStateList.length; i < l_intLen; i++)
        {
            if (l_tradeStopInfoUnit.orderStopStateList[i].stopFlag)
            {
                l_intValue = BooleanEnum.TRUE.intValue();
            }
            else
            {
                l_intValue = BooleanEnum.FALSE.intValue();
            }
            
            // 　@３－１）　@処理対象の要素.条件注文種別 == "連続注文"の場合
            // 　@　@プロパティセットしたParams.連続注文停止フラグ =
            // 　@　@　@処理対象の要素.停止フラグをセット。
            if (WEB3TriggerOrderTypeDef.SUCC.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setSuccOrderStopFlag(l_intValue);
            }
            // 　@３－２）　@処理対象の要素.条件注文種別 == "OCO注文"の場合
            // 　@　@プロパティセットしたParams.OCO注文停止フラグ =
            // 　@　@　@処理対象の要素.停止フラグをセット。
            else if (WEB3TriggerOrderTypeDef.OCO.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setOcoOrderStopFlag(l_intValue);
            }
            // 　@３－３）　@処理対象の要素.条件注文種別 == "IFD注文"の場合
            // 　@　@プロパティセットしたParams.IFD注文停止フラグ =
            // 　@　@　@処理対象の要素.停止フラグをセット。
            else if (WEB3TriggerOrderTypeDef.IFD.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setIfdOrderStopFlag(l_intValue);
            }
            // 　@３－４）　@処理対象の要素.条件注文種別 == "逆指値注文"の場合
            // 　@　@プロパティセットしたParams.逆指値注文停止フラグ =
            // 　@　@　@処理対象の要素.停止フラグをセット。
            else if (WEB3TriggerOrderTypeDef.STOP.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setStopOrderStopFlag(l_intValue);
            }
            // 　@３－５）　@処理対象の要素.条件注文種別 == "W指値注文"の場合
            // 　@　@プロパティセットしたParams.W指値注文停止フラグ =
            // 　@　@　@処理対象の要素.停止フラグをセット。
            else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_tradeStopInfoUnit.orderStopStateList[i].triggerOrderType))
            {
                l_params.setWlimitOrderStopFlag(l_intValue);
            }
        }
        
        // ４）　@QueryProcessor.doInsertQuery()をコールする。
        // 　@[doInsertQuery()にセットするパラメータ]
        // 　@　@arg0：　@プロパティセットしたParams
        try
        {
            // 　@　@特殊執行条件取扱停止ID =
            // 　@　@　@特殊執行条件取扱停止Dao.newPkValue()
            l_params.setTriggerOrderStopId(TriggerOrderStopDao.newPkValue());
            Processors.getDefaultProcessor().doInsertQuery(l_params);
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
    }
}
@
