head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminSwitchOrderRouteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注先切替サービス実装クラス(WEB3AdminSwitchOrderRouteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  孟亜南 (中訊) 仕様変更モデルNo.020
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
Revesion History : 2007/02/17  李木子 (中訊) 実装の問題No.002-004
Revesion History : 2007/02/27  謝旋 (中訊) モデルNo.024,026-028,031-039
Revesion History : 2007/06/21  謝旋 (中訊) モデルNo.102
Revesion History : 2007/06/22  徐宏偉 (中訊) モデルNo.104,107
Revesion History : 2007/06/22  劉立峰 (中訊) モデルNo.103,106
Revesion History : 2007/06/22  周墨洋 (中訊) モデルNo.105,108
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.WEB3AdminSwitchOrderRouteService;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者発注経路切替サービス)<BR>
 * <BR>
 * 管理者発注先切替サービス実装クラス<BR>
 * <BR>
 * WEB3AdminSwitchOrderRouteServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminSwitchOrderRouteServiceImpl implements WEB3AdminSwitchOrderRouteService
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteServiceImpl.class);

    /**
     * @@roseuid 430C4986035B
     */
    public WEB3AdminSwitchOrderRouteServiceImpl()
    {

    }

    /**
     * 管理者発注先切替サービスを行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * リクエストデータが、<BR>
     * 　@[管理者・発注先切替選択リクエストの場合]<BR>
     * 　@　@this.get選択画面()をコールする。<BR>
     * <BR>
     * 　@[管理者・発注先切替確認リクエストの場合]<BR>
     * 　@　@this.validate発注先切替()をコールする。<BR>
     * <BR>
     * 　@[管理者・発注先切替完了リクエストの場合]<BR>
     * 　@　@this.submit発注先切替()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 42D23A3E016B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        try
        {
            //get選択画面
            if (l_request instanceof WEB3AdminSwitchOrderRouteSelectRequest)
            {
                l_response =
                    this.getSelectScreen((WEB3AdminSwitchOrderRouteSelectRequest) l_request);

            //validate発注先切替()
            }
            else if (l_request instanceof WEB3AdminSwitchOrderRouteConfirmRequest)
            {
                l_response =
                    this.validateOrderRouteChange(
                        (WEB3AdminSwitchOrderRouteConfirmRequest) l_request);

            //submit発注先切替()
            }
            else if (l_request instanceof WEB3AdminSwitchOrderRouteCompleteRequest)
            {
                l_response =
                    this.submitOrderRouteChange(
                        (WEB3AdminSwitchOrderRouteCompleteRequest) l_request);                
            }
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "INPUT リクエスト NOT 管理者発注先切替サービスリクエスト");
            }
        }
 
        catch (DataQueryException l_dqex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dqex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.toString(),
                l_dqex);

        } 
        catch (DataNetworkException l_dnex)
        {
            String l_strMsg = "Error while aquiring the Data ";
            log.error(l_strMsg, l_dnex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.toString(),
                l_dnex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * 管理者発注先切替選択画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注先切替）get選択画面」参照。<BR>
     * @@param リクエストデータ - 管理者・発注先切替選択リクエストオブジェクト<BR>
     * @@return 管理者・発注先切替選択レスポンス<BR>
     * @@roseuid 42D23A9C014C
     */
    protected WEB3AdminSwitchOrderRouteSelectResponse getSelectScreen(WEB3AdminSwitchOrderRouteSelectRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "getSelectScreen()";
        log.entering(STR_METHOD_NAME);
        
        // フロント発注管理共通サービスインスタンス生成
        WEB3AdminDirSecFrontOrderCommonService l_adminFrontOrderCommonService =
            (WEB3AdminDirSecFrontOrderCommonService) Services.getService(
            WEB3AdminDirSecFrontOrderCommonService.class);

        //発注先切替テーブルRow型配列
        OrderSwitchingRow[] orderSwitchingRow = null;
        
        // 発注先情報型配列
        WEB3AdminOrderRouteSwitchingInfo[] switchInfos = null;
        
        // ログイン情報インスタンス
        WEB3Administrator l_administrator = null;

        // 1.1.リクエストパラメータのnullチェック
        l_request.validate();
        
        // 1.2.ログイン情報インスタンス取得
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.3.validate権限チェック()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            false);

        //1.4. isDIR管理者( )チェック DIR管理者でない場合、例外をスローする。
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        // 1.2.発注先切替テーブルからレコードを取得
        orderSwitchingRow = l_adminFrontOrderCommonService.getOrderRouteSwitchingRows(l_request.institutionCode);

        // 1.3.発注先一覧情報を生成する
        switchInfos = l_adminFrontOrderCommonService.createSwitchRouteInfoList(orderSwitchingRow);

        // 1.4.レスポンスデータ作成    	
        WEB3AdminSwitchOrderRouteSelectResponse l_response =
            (WEB3AdminSwitchOrderRouteSelectResponse) l_request.createResponse();

        // 1.5.プロパティセット
        l_response.infoList = switchInfos;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * 管理者発注先切替確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注先切替）validate発注先切替」参照。<BR>
     * @@param リクエストデータ - 管理者・発注先切替確認リクエストオブジェクト<BR>
     * @@return 管理者・発注先切替確認レスポンス<BR>
     * @@roseuid 42D23A9C016B
     */
    protected WEB3AdminSwitchOrderRouteConfirmResponse validateOrderRouteChange(WEB3AdminSwitchOrderRouteConfirmRequest l_request)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validateOrderRouteChange()";
        log.entering(STR_METHOD_NAME);

        // 発注先情報オブジェクト取得
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = l_request.infoUnit;

        // 1.1.リクエストデータvalidateチェック
        l_request.validate();

        // 発注経路区分を取得
        String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
        
        // 変更後有効フラグを取得
        String l_changedValidFlg = l_switchInfoObj.changedValidFlag;

        //フロント発注副系を有効にする場合、ＳＯＮＡＲ全障害チェックを行う
        if (l_sbumitRouteDiv.equals(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION) && 
                                                            l_changedValidFlg.equals(WEB3ValidFlag.ON))
        {
            // フロント発注管理共通インスタンス生成
        	WEB3AdminDirSecFrontOrderCommonService l_adminFrontOrderCommonService =
                (WEB3AdminDirSecFrontOrderCommonService) Services.getService(
                WEB3AdminDirSecFrontOrderCommonService.class);

            //フロント発注取引所区分コードを取得する
            String frontOrderExchangeCode = l_adminFrontOrderCommonService.getFrontOrderExchangeCode(l_switchInfoObj.convertMarketCode);

            //ＳＯＮＡＲ全障害チェックを行う
            //ＳＯＮＡＲ全障害レコードが取得できた場合は、例外をスローする
            l_adminFrontOrderCommonService.validateSonarCheck(
                                l_request.institutionCode,
                                frontOrderExchangeCode,
                                l_switchInfoObj.frontOrderSystemCode,
                                l_switchInfoObj.productType);

        }

        //レスポンスデータ作成    	
        WEB3AdminSwitchOrderRouteConfirmResponse l_response =
            (WEB3AdminSwitchOrderRouteConfirmResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * 管理者発注先切替完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注先切替）submit発注先切替」参照。<BR>
     * @@param リクエストデータ - 管理者・発注先切替完了リクエストオブジェクト<BR>
     * @@return 管理者・発注先切替完了レスポンス<BR>
     * @@roseuid 42D23A9C018B
     */
    protected WEB3AdminSwitchOrderRouteCompleteResponse submitOrderRouteChange(WEB3AdminSwitchOrderRouteCompleteRequest l_request)
        throws WEB3BaseException, DataNetworkException, DataQueryException
    {

        final String STR_METHOD_NAME = "submitOrderRouteChange()";
        log.entering(STR_METHOD_NAME);

        // ログイン情報インスタンス
        WEB3Administrator l_administrator = null;
        // パスワード
        String l_strPassword = null;
        // 発注先情報インスタンス
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = l_request.infoUnit;
        // フロント発注管理共通インスタンス生成
        WEB3AdminDirSecFrontOrderCommonService l_adminFrontOrderCommonService =
            (WEB3AdminDirSecFrontOrderCommonService) Services.getService(
            WEB3AdminDirSecFrontOrderCommonService.class);
       
        // 1.1.ログイン情報取得
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 1.2.リクエストデータvalidateチェック
        l_request.validate();
        
        // 1.3.validate権限チェック()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            true);

        //1.4. isDIR管理者( )チェック DIR管理者でない場合、例外をスローする。
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        //パスワードチェック
        l_strPassword = l_request.password;
        l_administrator.validateTradingPassword(l_strPassword);

        //証券会社コード取得
        String institutionCode = l_administrator.getInstitutionCode();

        // 発注経路区分を取得
        String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
    
        // 変更後有効フラグを取得
        String l_changedValidFlg = l_switchInfoObj.changedValidFlag;

        // フロント発注取引所区分コード
        String l_frontExCode = l_adminFrontOrderCommonService.getFrontOrderExchangeCode(l_switchInfoObj.convertMarketCode);

        //フロント発注副系を有効にする場合、ＳＯＮＡＲ全障害チェックを行う
        if (l_sbumitRouteDiv.equals(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION) && 
                                                            l_changedValidFlg.equals(WEB3ValidFlag.ON))
        {
            //ＳＯＮＡＲ全障害チェックを行う
            //ＳＯＮＡＲ全障害レコードが取得できた場合は、例外をスローする
            l_adminFrontOrderCommonService.validateSonarCheck(
                                l_request.institutionCode,
                                l_frontExCode,
                                l_switchInfoObj.frontOrderSystemCode,
                                l_switchInfoObj.productType);
        }

        //get有効発注経路
        WEB3GentradeOrderSwitching l_orderSwitching =
            this.getExpirationOrderRoute(l_request.institutionCode, l_switchInfoObj);
        //発注先切替テーブル更新処理
        this.updateOrderSwitching(institutionCode, l_switchInfoObj);
        
        // 顧客側のキューテーブル更新処理と同期を図る為、1秒間Sleepする。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            log.error(" sleep処理の失敗");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        //リクエストデータ.発注先情報.銘柄タイプが1：株式の場合
        if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(l_switchInfoObj.productType))
        {
            this.updateEqtypeOrder(l_request.institutionCode, l_frontExCode, l_switchInfoObj, l_orderSwitching);
        }

        //リクエストデータ.発注先情報.銘柄タイプが6：先物オプションの場合
        if (Integer.toString(ProductTypeEnum.IFO.intValue()).equals(l_switchInfoObj.productType))
        {
            this.updateIfoOrder(l_request.institutionCode, l_frontExCode, l_switchInfoObj, l_orderSwitching);
        }

        //レスポンスデータ作成    	
        WEB3AdminSwitchOrderRouteCompleteResponse l_response =
            (WEB3AdminSwitchOrderRouteCompleteResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }

    /**
     * 発注先切替テーブルのレコードを有効／無効に更新する。<BR>
     * <BR>
     * １）パラメータ.発注先情報.変更後有効フラグ == 1（有効）の場合、<BR>
     * 　@条件に該当する既存レコードを全て無効に更新する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@　@市場コード = パラメータ.発注先情報.市場コード And<BR>
     * 　@　@フロント発注システム区分 = パラメータ.発注先情報.フロント発注システム区分 <BR>
     * And<BR>
     * 　@　@銘柄タイプ = パラメータ.発注先情報.銘柄タイプ <BR>
     * <BR>
     * 　@[更新値]<BR>
     * 　@　@DB更新仕様<BR>
     * 　@　@　@「発注先切替_発注先切替テーブル.xls<BR>
     * 　@　@　@＃発注先切替[有効レコード以外]_発注先切替テーブル_DB更新シート」参照。<BR>
     * <BR>
     * ２）条件に該当する発注先切替テーブルのレコード.有効フラグを<BR>
     * 　@更新する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@　@市場コード = パラメータ.発注先情報.市場コード And<BR>
     * 　@　@フロント発注システム区分 = パラメータ.発注先情報.フロント発注システム区分 <BR>
     * And<BR>
     * 　@　@銘柄タイプ = パラメータ.発注先情報.銘柄タイプ And <BR>
     * 　@　@発注経路区分 = パラメータ.発注先情報.発注経路<BR>
     * <BR>
     * 　@[更新値]<BR>
     * 　@　@DB更新仕様<BR>
     * 　@　@　@「発注先切替_発注先切替テーブル.xls<BR>
     * 　@　@　@＃発注先切替[有効or無効]_発注先切替テーブル_DB更新シート」参照。<BR>
     * ３）commitを行う。 <BR>
     *  * QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[]) <BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@param 発注先情報 - 発注先情報オブジェクト<BR>
     * @@roseuid 42D38BAC037E
     */
    protected void updateOrderSwitching(
        String institutioncode,
        WEB3AdminOrderRouteSwitchingInfo infolist)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderSwitching()";
        log.entering(STR_METHOD_NAME);
        
        // 更新条件文字列の生成
        StringBuffer l_sbInValidWhere = new StringBuffer();
        l_sbInValidWhere.append(" institution_code = ? ");
        l_sbInValidWhere.append(" and market_code = ? ");
        l_sbInValidWhere.append(" and front_order_system_code = ? ");
        l_sbInValidWhere.append(" and product_type = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objInValidWhere =
            {
                institutioncode,
                infolist.marketCode,
                infolist.frontOrderSystemCode,
                infolist.productType
            };
            
        // 更新条件文字列の生成
        StringBuffer l_sbValidWhere = new StringBuffer();
        l_sbValidWhere.append(" institution_code = ? ");
        l_sbValidWhere.append(" and market_code = ? ");
        l_sbValidWhere.append(" and front_order_system_code = ? ");
        l_sbValidWhere.append(" and product_type = ? ");
        l_sbValidWhere.append(" and submit_order_route_div = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objValidWhere =
            {
                institutioncode,
                infolist.marketCode,
                infolist.frontOrderSystemCode,
                infolist.productType,
                infolist.submitOrderRouteDiv
            };            
        
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();

            // パラメータ.発注先情報.変更後有効フラグ == 1（有効）の場合、
            if (infolist.changedValidFlag.equals(WEB3ValidFlag.ON))
            {
                //BatchedQuery[]の作成用
                ArrayList l_listQuery = new ArrayList();
                
                // 条件に該当する既存レコードを全て無効に更新する。
                BatchedQuery l_updQueryOff = BatchedQuery.createUpdateAllQuery(OrderSwitchingRow.TYPE,
                                                                                  l_sbInValidWhere.toString(),
                                                                                  l_objInValidWhere,
                                                                                  this.setColumValue("valid_flag", WEB3ValidFlag.OFF));
                                                                                  
                // Listにadd
                l_listQuery.add(l_updQueryOff);                                                                                  

                // 選択された発注経路を有効に更新する。
                BatchedQuery l_updQueryOn = BatchedQuery.createUpdateAllQuery(OrderSwitchingRow.TYPE,
                                                                              l_sbValidWhere.toString(),
                                                                              l_objValidWhere,
                                                                              this.setColumValue("valid_flag", WEB3ValidFlag.ON));

                // Listにadd
                l_listQuery.add(l_updQueryOn);

                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // 配列型に変換
                l_listQuery.toArray(l_queries);   
                
                // 更新処理実行/別トランザクションにより、単独コミット
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );

            }
            // パラメータ.発注先情報.変更後有効フラグ == 0（無効）の場合、
            // 条件に該当する既存レコードを無効に更新する。
            else if (infolist.changedValidFlag.equals(WEB3ValidFlag.OFF))
            {
                // 条件に該当する既存レコードを無効に更新する。
                BatchedQuery l_updQueryOff = BatchedQuery.createUpdateAllQuery(OrderSwitchingRow.TYPE,
                                                                                l_sbValidWhere.toString(),
                                                                                l_objValidWhere,
                                                                                this.setColumValue("valid_flag", WEB3ValidFlag.OFF));
                                                                                
                BatchedQuery[] l_queries = {l_updQueryOff};
                
                // 更新処理実行/別トランザクションにより、単独コミット
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *株式注文取引キューテーブルのレコードを条件により抽出する。 <BR>
     *<BR>
     *１）条件に該当するレコードを抽出する。 <BR>
     *　@※口座IDの昇順でソートする。 <BR>
     *<BR>
     * 株式注文キューテーブルから、statusが"0":未処理のレコードを選択する。<BR>
     * <BR>
     * [条件]<BR>
     * 証券会社コード = パラメータ.証券会社コード And<BR>
     *　@フロント発注取引所区分コード = パラメータ.フロント発注取引所区分コード and <BR>
     *　@フロント発注システム区分 = パラメータ.発注先情報.フロント発注システム区分 and <BR>
     *　@フロント発注取引区分コード = 1：株券売買 and <BR>
     *　@処理区分 = 0：未処理 and <BR>
     *　@全訂正処理区分 = 0：全訂正以外 and <BR>
     *　@(取引コード（SONAR） in (11：通常取引（上場銘柄）, 51：信用建, 52：信用埋) 
     *　@　@or 取引コード（SONAR） is NULL) <BR>
     *<BR>
     *２）　@検索結果を返却する。 <BR>
     *<BR>
     * @@param institutioncode -証券会社コード
     * @@param frotExCode      -フロント発注取引所区分コード
     * @@param infoUnit        -発注先情報
     * @@return キューテーブルList
     * @@throws WEB3BaseException
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     */
    protected List selectEqtypeOrderAll(String institutioncode, String frotExCode, 
                                        WEB3AdminOrderRouteSwitchingInfo infoUnit)throws WEB3BaseException, 
                                                                                          DataNetworkException, 
                                                                                          DataQueryException,
                                                                                          DataCallbackException
    {
        final String STR_METHOD_NAME = "selectEqtypeOrderAll()";
        log.entering(STR_METHOD_NAME);
       
        List EqOrderList = new ArrayList();       
        
        // 抽出条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and status = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        l_sbWhere.append(" and (sonar_traded_code in (?, ?, ?) ");
        l_sbWhere.append(" or sonar_traded_code is NULL) ");

        // 口座ID昇順指定
        String l_strSort = "account_id asc";
                
        // 抽出条件コンテナの生成
        Object[] l_objWhere =
            {
                institutioncode,
                frotExCode,
                infoUnit.frontOrderSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                WEB3StatusDef.NOT_DEAL, 
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE, 
                WEB3TransactionTypeSONARDef.MARKET_TRADING, 
                WEB3TransactionTypeSONARDef.OPEN_CONTRACT, 
                WEB3TransactionTypeSONARDef.SETTLE_CONTRACT, 
            };
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();

            // キューテーブルを検索する。 
            EqOrderList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return EqOrderList;
    }

    /**
     * 株式注文取引キューテーブルのレコードの発注経路区分を更新する。<BR>
     * <BR>
     * １）条件に該当するレコードを更新する。<BR>
     * <BR>
     * 　@[条件]<BR>
     *　@口座ID = パラメータ.株式注文取引キューRow.口座ID and <BR>
     *　@識別コード = パラメータ.株式注文取引キューRow.識別コード and <BR>
     *　@処理区分 = パラメータ.株式注文取引キューRow.処理区分 <BR>
     * <BR>
     *２）　@変更後有効フラグによって、レコードの発注経路区分を更新する。 <BR>
     *<BR>
     *　@２－１）　@パラメータ.発注先情報.変更後有効フラグ == 1（有効）の場合 <BR>
     *　@　@選択した発注経路に更新する。 <BR>
     *<BR>
     *　@２－２）　@パラメータ.発注先情報.変更後有効フラグ == 0（無効）の場合 <BR>
     *　@　@発注経路を"発注停止"に更新する。 <BR>
     *<BR>
     *　@【ｘTrade】補足資料.DB更新  <BR>
     *　@「発注先切替_株式注文取引キューテーブル.xls」を参照。 <BR>
     *<BR>
     *３）commitを行う。 <BR>
     *　@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[])<BR>
     * @@param 株式注文取引キューRow - 株式注文取引キューRow<BR>
     * @@param 発注先情報 - 発注先情報オブジェクト<BR>
     * @@roseuid 42D38F1701F8
     */
    protected void updateEqtypeOrderAll(
        HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow,
        WEB3AdminOrderRouteSwitchingInfo infoUnit)
        throws WEB3BaseException, DataNetworkException, DataQueryException, DataCallbackException
    {

        final String STR_METHOD_NAME = "updateEqtypeOrderAll(HostEqtypeOrderAllRow, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // 更新条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and order_request_number = ? ");
        l_sbWhere.append(" and status = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                Long.toString(l_hostEqtypeOrderAllRow.getAccountId()),
                l_hostEqtypeOrderAllRow.getOrderRequestNumber(),
                l_hostEqtypeOrderAllRow.getStatus()
            };

        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();

            // パラメータ.発注先情報.変更後有効フラグ == 1（有効）の場合、
            if (infoUnit.changedValidFlag.equals(WEB3ValidFlag.ON))
            {                
                // BatchedQuery[]の作成用
                ArrayList l_listQuery = new ArrayList();
                
                // キューテーブルの発注経路を更新する。 
                BatchedQuery l_updQueryDiv = BatchedQuery.createUpdateAllQuery(HostEqtypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",infoUnit.submitOrderRouteDiv));
                
                // Listにadd
                l_listQuery.add(l_updQueryDiv);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // 配列型に変換
                l_listQuery.toArray(l_queries);   
                
                // 更新処理実行/別トランザクションにより、単独コミット
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );                                    
            }
            // パラメータ.発注先情報.変更後有効フラグ == 0（無効）の場合、
            else if (infoUnit.changedValidFlag.equals(WEB3ValidFlag.OFF))
            {
                // BatchedQuery[]の作成用
                ArrayList l_listQuery = new ArrayList();
                
                BatchedQuery l_updQueryStop = BatchedQuery.createUpdateAllQuery(HostEqtypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",WEB3SubmitOrderRouteDivDef.ORDER_STOP));
                
                // Listにadd
                l_listQuery.add(l_updQueryStop);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // 配列型に変換
                l_listQuery.toArray(l_queries);     
                
                // 更新処理実行/別トランザクションにより、単独コミット
                qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * テーブルカラムと値をMapオブジェクトにセットする<BR>
     * <BR>
     * １）パラメータをマップオブジェクトにセットして返却する<BR>
     * <BR>
     * @@param テーブルカラム - テーブルカラム<BR>
     * @@param カラムに対する値 - <BR>
     * @@return Map<BR>
     * @@roseuid 
     */
    private Map setColumValue(String l_priColum, String l_priValue)
    {    
        Map objMap = new HashMap();
        
        objMap.put(l_priColum, l_priValue);
        objMap.put("last_updated_timestamp",GtlUtils.getSystemTimestamp());

        return objMap;
    }
    
    /**
     * 株式注文単位テーブルから、切替対象発注経路のレコードを選択する。<BR>
     * <BR>
     *１）拡張アカウントマネージャ.get証券会社（）で、証券会社オブジェクトを取得する。 <BR>
     *<BR>
     *　@[引数] <BR>
     *　@証券会社コード： パラメータ.証券会社コード <BR>
     *<BR>
     *２）拡張金融オブジェクトマネージャ.get市場（）で、市場オブジェクトを取得する。 <BR>
     *<BR>
     *　@[引数] <BR>
     *　@証券会社： １）の証券会社 <BR>
     *　@市場コード： パラメータ.発注先情報.市場コード <BR>
     *<BR>
     *３）条件に該当するレコードを検索する。 <BR>
     *　@※口座IDの昇順でソートする。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@部店IDの先頭2桁 = １）の証券会社.get証券会社ID（）の戻り値 and <BR>
     *　@銘柄タイプ = パラメータ.発注先情報.銘柄タイプ and <BR>
     *　@市場ID = ２）の市場.get市場ID（）の戻り値 and <BR>
     *　@注文有効状態 = 1：オープン and <BR>
     *　@取引コード（SONAR） in (11：普通株式, 51：信用建, 52：信用返済) <BR>
     *<BR>
     *４）検索結果を返却する。<BR>
     * <BR> 
     * @@param institutioncode -証券会社コード
     * @@param infoUnit        -発注先情報
     * @@return 注文単位テーブルList
     * @@throws WEB3BaseException
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     */
    protected List selectEqtypeOrderUnit(String l_institutionCode, 
                                        WEB3AdminOrderRouteSwitchingInfo infoUnit)throws WEB3BaseException, 
                                                                                          DataNetworkException, 
                                                                                          DataQueryException,
                                                                                          DataCallbackException
    {
        final String STR_METHOD_NAME = "selectEqtypeOrderUnit(String, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);
       
        List EqOrderUnitList = new ArrayList();       
        
        // 抽出条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" substr(branch_id,1,2) = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_id = ? ");
        l_sbWhere.append(" and order_open_status = ? ");
        l_sbWhere.append(" and sonar_traded_code in (?,?,?) ");

        try
        {
            //市場ID取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager
                = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            // １）拡張アカウントマネージャ.get証券会社（）で、証券会社オブジェクトを取得する
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_institutionCode);

            // ２）拡張金融オブジェクトマネージャ.get市場（）で、市場オブジェクトを取得する
            WEB3GentradeMarket l_market = null;        //市場
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_institution, infoUnit.marketCode);
  
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
            {
                Long.toString(l_institution.getInstitutionId()),
                infoUnit.productType,
                Long.toString(l_market.getMarketId()),
                OrderOpenStatusEnum.OPEN,
                WEB3TransactionTypeSONARDef.MARKET_TRADING,
                WEB3TransactionTypeSONARDef.OPEN_CONTRACT,
                WEB3TransactionTypeSONARDef.SETTLE_CONTRACT,                
            };
            
            // 口座ID昇順指定
            String l_strSort = "account_id asc";

            QueryProcessor qp = Processors.getDefaultProcessor();

            // 株式注文単位テーブルを検索する。 
            EqOrderUnitList = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_nfe)
        {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return EqOrderUnitList;
    }
    
    /**
     * 株式注文単位テーブルを更新するか否かを判定する。<BR>
     *<BR>
     *更新する場合、trueを返却する。 <BR>
     *更新しない場合、falseを返却する。 <BR>
     *<BR>
     *１）発注先情報.市場コード == "JASDAQ"の場合、オークション or マーケットメイクをチェックする。 <BR>
     * <BR>
     *  １－１）株式取引銘柄を取得する。<BR>
     *<BR>
     *１－２）株式取引銘柄.店頭公開区分 == "マーケットメイク"の場合<BR>
     *　@１－２－１）発注先情報.フロント発注システム区分 != "マーケットメイク"なら、falseを返却する。<BR>
     *<BR>
     *１－３）株式取引銘柄.店頭公開区分 != "マーケットメイク"の場合<BR>
     *　@１－３－１）発注先情報.フロント発注システム区分 == "マーケットメイク"なら、falseを返却する。<BR>
     *<BR>
     *２）trueを返却する。<BR>
     * @@param 株式注文単位Row - 株式注文単位Row <BR>
     * @@param 発注先情報オブジェクト - 発注先情報オブジェクト <BR>
     * @@throws WEB3BaseException 
     */
    private boolean isUpdateEqtypeOrderUnit(EqtypeOrderUnitRow l_oldOrderUnitRow, 
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".isUpdateEqtypeOrderUnit(EqtypeOrderUnitRow," +
                "WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);
        
        //市場 == "JASDAQ"の場合、オークション or マーケットメイクをチェックする。                       
        if (WEB3MarketCodeDef.JASDAQ.equals(l_switchInfoObj.marketCode))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);       
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.
                getProductManager();

            //getTradedProduct(銘柄ID, 市場ID)
            WEB3EquityTradedProduct l_tradedProduct;
            try 
            {
                l_tradedProduct = (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    l_oldOrderUnitRow.getProductId(), l_oldOrderUnitRow.getMarketId());
            } 
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME,
                    l_nfe.getMessage());
            }

            EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();

            //(取引銘柄.店頭公開区分 == "マーケットメイク"の場合
            if(WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
            {
                //フロント発注システム区分 != "マーケットメイク"なら注文単位テーブルを更新しない。
                if(!WEB3FrontOrderSystemCodeDef.JASDAQ_MM.equals(l_switchInfoObj.frontOrderSystemCode))
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }   
            //(取引銘柄.店頭公開区分 != "マーケットメイク"の場合
            else                       
            {
                //フロント発注システム区分 == "マーケットメイク"なら注文単位テーブルを更新しない。
                if(WEB3FrontOrderSystemCodeDef.JASDAQ_MM.equals(l_switchInfoObj.frontOrderSystemCode))
                {
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return true;
    }
    
   /**
    *株式注文単位テーブルの発注経路区分を更新する。 <BR>
    * <BR>
     *１）株式注文単位Rowの発注経路区分を切替対象の発注経路区分、 <BR>
     *　@更新日付を現在日時にそれぞれ更新する。 <BR>
     *<BR>
    * [条件]<BR>
    *  注文単位ID = パラメータ.株式注文単位Row.注文単位ID <BR>
    * <BR>
    * [更新値]<BR>
     *　@発注経路区分 = パラメータ.発注先情報.発注経路区分 <BR>
    *  更新日付 = 現在日時<BR>
     *<BR>
     *　@【ｘTrade】補足資料.DB更新 <BR>
     *　@「発注先切替_株式注文単位テーブル仕様.xls」を参照。 <BR>
    *  <BR>
    * ２）commitを行う。<BR>
    * 　@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, <BR>
    * BatchedQuery[])<BR>
    * <BR>
    * @@param 株式注文単位Row - 株式注文単位Row <BR>
    * @@param 発注先情報オブジェクト - 発注先情報オブジェクト <BR>
    * @@throws WEB3BaseException 
    */
    private void updateEqtypeOrderUnit(EqtypeOrderUnitRow l_oldOrderUnitRow, 
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".updateEqtypeOrderUnit(EqtypeOrderUnitRow," +
        "WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // 発注経路区分を取得
        String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
        
        // 更新条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" order_unit_id = ? ");

        // 更新条件コンテナの生成
        Object[] l_objWhere =
            {
                Long.toString(l_oldOrderUnitRow.getOrderUnitId())
            };

        //注文単位レコードの発注経路区分を切替対象の発注経路、更新日付を現在日時にそれぞれ更新する。
        QueryProcessor qp;
        try
        {
            qp = Processors.getDefaultProcessor();
            //BatchedQuery[]の作成用
            ArrayList l_listQuery = new ArrayList();
            
            BatchedQuery l_updateQuery = BatchedQuery.createUpdateAllQuery(EqtypeOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere,
                this.setColumValue("submit_order_route_div",l_sbumitRouteDiv));
            
            l_listQuery.add(l_updateQuery);
            
            BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
            
            // 配列型に変換
            l_listQuery.toArray(l_queries);  
            
            // 更新処理実行/別トランザクションにより、単独コミット
            qp.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 株式注文の更新処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「 （発注先切替）update株式注文」参照。<BR>
     * @@param l_strInstitutioncode - 証券会社コード<BR>
     * @@param l_strFrotExCode - フロント発注取引所区分コード<BR>
     * @@param infoUnit - 発注先情報<BR>
     * @@param l_orderSwitching - 切替前発注先切替<BR>
     * @@throws WEB3BaseException<BR>
     * @@throws DataNetworkException<BR>
     * @@throws DataQueryException<BR>
     * @@throws DataCallbackException<BR>
     */
    private void updateEqtypeOrder(String l_strInstitutioncode, 
        String l_strFrotExCode, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit,
        WEB3GentradeOrderSwitching l_orderSwitching)
        throws WEB3BaseException, 
                    DataNetworkException, 
                    DataQueryException,
                    DataCallbackException
    {
        String STR_METHOD_NAME =
            ".updateEqtypeOrder(String, String, WEB3AdminOrderRouteSwitchingInfo, WEB3GentradeOrderSwitching)";
        log.entering(STR_METHOD_NAME);

        List l_lstEqtypeOrder = new ArrayList();
        //株式注文取引キューテーブルのレコードを条件により抽出する。
        l_lstEqtypeOrder = this.selectEqtypeOrderAll(l_strInstitutioncode, l_strFrotExCode, l_infoUnit);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);           
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        HostEqtypeOrderAllRow l_hostEqtypeOrderAllRowLast = null;

        for (int i = 0; i < l_lstEqtypeOrder.size(); i++)
        {
            HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_lstEqtypeOrder.get(i);
            

            //ループ1回目、または、株式注文取引キューRow.口座IDが前レコードと異なる場合
            if (i == 0)
            {
                l_hostEqtypeOrderAllRowLast = l_hostEqtypeOrderAllRow;
                //顧客をロックする。 
                l_accountManager.lockAccount(l_strInstitutioncode, 
                    l_hostEqtypeOrderAllRow.getBranchCode(), 
                    l_hostEqtypeOrderAllRow.getAccountCode());
            } 
            else 
            {
                if (l_hostEqtypeOrderAllRow.getAccountId() != l_hostEqtypeOrderAllRowLast.getAccountId()) 
                {
                    //顧客をロックする。 
                    l_accountManager.lockAccount(l_strInstitutioncode, 
                        l_hostEqtypeOrderAllRow.getBranchCode(), 
                        l_hostEqtypeOrderAllRow.getAccountCode()); 
                }

                l_hostEqtypeOrderAllRowLast = l_hostEqtypeOrderAllRow;
            }

            //株式注文取引キューテーブルの発注経路区分を更新する。 
            this.updateEqtypeOrderAll(l_hostEqtypeOrderAllRow, l_infoUnit);
        }

        //切替前発注先切替が、銘柄タイプに対応する注文単位テーブルの更新対象となる
        //発注経路か否かを判定する。
        //[引数]
        //切替前発注先切替： 引数.切替前発注先切替
        boolean l_blnUpdate = this.isOrderUnitUpdateObjSwitchOrderRoute(l_orderSwitching);

        if (!l_blnUpdate)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        if (!WEB3ValidFlag.ON.equals(l_infoUnit.changedValidFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        try
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRowlast = null;
            //株式注文単位テーブルから、切替対象発注経路のレコードを検索する。
            List l_lstEqtypeOrderUnit = this.selectEqtypeOrderUnit(l_strInstitutioncode, l_infoUnit);
            for (int i = 0; i < l_lstEqtypeOrderUnit.size(); i++)
            {
                EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_lstEqtypeOrderUnit.get(i);
                
                //株式注文単位テーブルを更新するか否かを判定する。 
                boolean l_blnUpdateOrderUnit = this.isUpdateEqtypeOrderUnit(l_eqtypeOrderUnitRow, l_infoUnit);
    
                //株式注文単位テーブルを更新しない場合
                if (!l_blnUpdateOrderUnit)
                {
                    continue;
                }
    
                    if (i == 0)
                    {
                        l_eqtypeOrderUnitRowlast = l_eqtypeOrderUnitRow;
                        MainAccount l_mainAccount = l_accountManager.getMainAccount(l_eqtypeOrderUnitRow.getAccountId());
                        //顧客をロックする。 
                        l_accountManager.lockAccount(l_strInstitutioncode, 
                            l_mainAccount.getBranch().getBranchCode(), 
                            l_mainAccount.getAccountCode());
                    }
                    else
                    {
                        if (l_eqtypeOrderUnitRow.getAccountId() != l_eqtypeOrderUnitRowlast.getAccountId())
                        {
                            MainAccount l_mainAccount = l_accountManager.getMainAccount(l_eqtypeOrderUnitRow.getAccountId());
                            //顧客をロックする。 
                            l_accountManager.lockAccount(l_strInstitutioncode, 
                                l_mainAccount.getBranch().getBranchCode(), 
                                l_mainAccount.getAccountCode());
                        }

                        l_eqtypeOrderUnitRowlast = l_eqtypeOrderUnitRow;
                    }
    
                //株式注文単位テーブルの発注経路区分を更新する。 
                this.updateEqtypeOrderUnit(l_eqtypeOrderUnitRow, l_infoUnit);
            }
        }
        catch (NotFoundException l_notFoundException)
        {
            log.error(STR_METHOD_NAME, l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_notFoundException.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 先物OP注文の更新処理を行う。 <BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注先切替）update先物OP注文」参照。<BR>
     * @@param l_strInstitutioncode - 証券会社コード<BR>
     * @@param l_strFrotExCode - フロント発注取引所区分コード<BR>
     * @@param l_infoUnit - 発注先情報<BR>
     * @@param l_orderSwitching - 切替前発注先切替<BR>
     * @@throws WEB3BaseException
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     */
    private void updateIfoOrder(String l_strInstitutioncode,
        String l_strFrotExCode,
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit,
        WEB3GentradeOrderSwitching l_orderSwitching)
        throws WEB3BaseException,
                    DataNetworkException,
                    DataQueryException,
                    DataCallbackException
    {
        String STR_METHOD_NAME =
            ".updateIfoOrder(String, String, WEB3AdminOrderRouteSwitchingInfo, WEB3GentradeOrderSwitching)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstIfoOrderAll = new ArrayList();
        //先物OP注文取引キューテーブルのレコードを条件により抽出する。 
        l_lstIfoOrderAll = this.selectIfoOrderAll(l_strInstitutioncode, l_strFrotExCode, l_infoUnit);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);           
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        HostFotypeOrderAllRow l_hostFotypeOrderAllRowlast = null;
        for (int i = 0; i < l_lstIfoOrderAll.size(); i++)
        {
            HostFotypeOrderAllRow l_hostFotypeOrderAllRow = (HostFotypeOrderAllRow)l_lstIfoOrderAll.get(i);
            
            if (i == 0)
            {
                l_hostFotypeOrderAllRowlast = l_hostFotypeOrderAllRow;
                //顧客をロックする。
                l_accountManager.lockAccount(l_strInstitutioncode, 
                    l_hostFotypeOrderAllRow.getBranchCode(), 
                    l_hostFotypeOrderAllRow.getAccountCode());
            }
            else
            {
                if (l_hostFotypeOrderAllRow.getAccountId() != l_hostFotypeOrderAllRowlast.getAccountId())
                {
                    //顧客をロックする。
                    l_accountManager.lockAccount(l_strInstitutioncode, 
                        l_hostFotypeOrderAllRow.getBranchCode(), 
                        l_hostFotypeOrderAllRow.getAccountCode());
                }
                
                l_hostFotypeOrderAllRowlast = l_hostFotypeOrderAllRow;
            }
            
            //先物OP注文取引キューテーブルの発注経路区分を更新する。
            this.updateIfoOrderAll(l_hostFotypeOrderAllRow, l_infoUnit);
        }
        
        // 切替前発注先切替が、銘柄タイプに対応する注文単位テーブルの更新対象となる
        // 発注経路か否かを判定する。
        // [引数]
        // 切替前発注先切替： 引数.切替前発注先切替
        boolean l_blnOrderUnitUpdateObjSwitchOrderRoute =
            this.isOrderUnitUpdateObjSwitchOrderRoute(l_orderSwitching);
        
        if (!l_blnOrderUnitUpdateObjSwitchOrderRoute)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        if (!WEB3ValidFlag.ON.equals(l_infoUnit.changedValidFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        List l_lstIfoOrderUnit = new ArrayList();
        //先物OP注文単位テーブルから、切替対象発注経路のレコードを検索する。 
        l_lstIfoOrderUnit = this.selectIfoOrderUnit(l_strInstitutioncode, l_infoUnit);

        try
        {
            IfoOrderUnitRow l_ifoOrderUnitRowlast = null;
            for (int i = 0; i < l_lstIfoOrderUnit.size(); i++)
            {
                IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_lstIfoOrderUnit.get(i);
                
                if (i == 0)
                {
                    l_ifoOrderUnitRowlast = l_ifoOrderUnitRow;
                    MainAccount l_mainAccount = l_accountManager.getMainAccount(l_ifoOrderUnitRow.getAccountId());
                    //顧客をロックする。 
                    l_accountManager.lockAccount(l_strInstitutioncode, 
                        l_mainAccount.getBranch().getBranchCode(), 
                        l_mainAccount.getAccountCode());
                }
                else
                {
                    if (l_ifoOrderUnitRow.getAccountId() != l_ifoOrderUnitRowlast.getAccountId())
                    {
                        MainAccount l_mainAccount = l_accountManager.getMainAccount(l_ifoOrderUnitRow.getAccountId());
                        //顧客をロックする。 
                        l_accountManager.lockAccount(l_strInstitutioncode, 
                            l_mainAccount.getBranch().getBranchCode(), 
                            l_mainAccount.getAccountCode());
                    }
                    
                    l_ifoOrderUnitRowlast = l_ifoOrderUnitRow;
                }
                
                //先物OP注文単位テーブルの発注経路区分を更新する。 
                this.updateIfoOrderUnit(l_ifoOrderUnitRow, l_infoUnit);
            }
        }
        catch (NotFoundException l_notFoundException)
        {
            log.error(STR_METHOD_NAME, l_notFoundException);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_notFoundException.getMessage());
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *先物OP注文取引キューテーブルのレコードを条件により抽出する。 <BR>
     *<BR>
     *１）条件に該当するレコードを抽出する。 <BR>
     *　@※口座IDの昇順でソートする。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@証券会社コード = パラメータ.証券会社コード and <BR>
     *　@フロント発注取引所区分コード = パラメータ.フロント発注取引所区分コード and <BR>
     *　@フロント発注システム区分 = パラメータ.発注先情報.フロント発注システム区分 and <BR>
     *　@フロント発注取引区分コード = 1：株券売買 and <BR>
     *　@処理区分 = 0：未処理 and <BR>
     *　@全訂正処理区分 = 0：全訂正以外 <BR>
     *<BR>
     *２）　@検索結果を返却する。<BR>
     * <BR> 
     * @@param institutioncode -証券会社コード
     * @@param frotExCode -フロント発注取引所区分コード
     * @@param infoUnit -発注先情報
     * @@return List
     */
    private List selectIfoOrderAll(String l_strInstitutioncode, 
        String l_strFrotExCode, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = ".selectIfoOrderAll(String, String, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // 抽出条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and status = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        
        // 口座ID昇順指定
        String l_strSort = " account_id asc ";

        // 抽出条件コンテナの生成
        Object[] l_objWhere = 
        {
            l_strInstitutioncode,
            l_strFrotExCode,
            l_infoUnit.frontOrderSystemCode,
            WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
            WEB3StatusDef.NOT_DEAL,
            WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
        };

        List l_lstIfoOrder = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    
            // 先物OP注文取引キューテーブルを検索する。 
            l_lstIfoOrder = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        return l_lstIfoOrder;
    }
    
    /**
     *先物OP注文単位テーブルから、切替対象発注経路のレコードを検索する。 <BR>
     *<BR>
     *１）拡張アカウントマネージャ.get証券会社（）で、証券会社オブジェクトを取得する。 <BR>
     *<BR>
     *　@[引数] <BR>
     *　@証券会社コード： パラメータ.証券会社コード <BR>
     *<BR>
     *２）拡張金融オブジェクトマネージャ.get市場（）で、市場オブジェクトを取得する。 <BR>
     *<BR>
     *　@[引数] <BR>
     *　@証券会社： １）の証券会社 <BR>
     *　@市場コード： パラメータ.発注先情報.市場コード <BR>
     *<BR>
     *３）条件に該当するレコードを検索する。 <BR>
     *　@※口座IDの昇順でソートする。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@部店IDの先頭2桁 = １）の証券会社.get証券会社ID（）の戻り値 and <BR>
     *　@銘柄タイプ = パラメータ.発注先情報.銘柄タイプ and <BR>
     *　@市場ID = ２）の市場.get市場ID（）の戻り値 and <BR>
     *　@注文有効状態 = 1：オープン and <BR>
     *<BR>
     *４）　@検索結果を返却する。<BR>
     * <BR> 
     * @@param l_strInstitutionCode -証券会社コード
     * @@param l_infoUnit        -発注先情報
     * @@return 注文単位テーブルList
     * @@throws WEB3BaseException
     */
    private List selectIfoOrderUnit(String l_strInstitutionCode, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = ".selectIfoOrderUnit(String, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);
       
        List IfoOrderUnitList = new ArrayList();       
        
        // 抽出条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" substr(branch_id,1,2) = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_id = ? ");
        l_sbWhere.append(" and order_open_status = ? ");

        try
        {
            //市場ID取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager
                = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            // １）拡張アカウントマネージャ.get証券会社（）で、証券会社オブジェクトを取得する
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);

            // ２）拡張金融オブジェクトマネージャ.get市場（）で、市場オブジェクトを取得する
            WEB3GentradeMarket l_market = null;        //市場
            l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                l_institution, l_infoUnit.marketCode);
  
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
            {
                Long.toString(l_institution.getInstitutionId()),
                l_infoUnit.productType,
                Long.toString(l_market.getMarketId()),
                OrderOpenStatusEnum.OPEN 
            };
            
            // 口座ID昇順指定
            String l_strSort = "account_id asc";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // 先物OP注文単位テーブルを検索する。 
            IfoOrderUnitList = l_queryProcessor.doFindAllQuery(IfoOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataFindException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_nfe)
        {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00645,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return IfoOrderUnitList;
    }

    /**
     * (is注文単位更新対象発注経路)
     *切替前発注先切替が、銘柄タイプに対応する注文単位テーブルの更新対象となる<BR>
　@   *発注経路か否かを判定する。 <BR>
     *<BR>
     *更新対象となる発注経路の場合、trueを返却する。  <BR>
     *更新対象となる発注経路でない場合、falseを返却する。  <BR>
     *<BR>
     *１）有効な切替前発注先切替が取得できない（引数.切替前発注先切替 == null）、 <BR>
     *　@　@または、発注エンジンがSONAR（引数.切替前発注先切替.isSONAR() == true）の場合 <BR>
     *<BR>
     *　@１－１）trueを返却する。 <BR>
     *<BR>
     *２）falseを返却する。<BR>
     *<BR>
     *@@param l_orderSwitching - 切替前発注先切替
     *@@return boolean
     */
    private boolean isOrderUnitUpdateObjSwitchOrderRoute(
        WEB3GentradeOrderSwitching l_orderSwitching) 
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = " isOrderUnitUpdateObjSwitchOrderRoute(WEB3GentradeOrderSwitching)";
        log.entering(STR_METHOD_NAME);

        //１）有効な切替前発注先切替が取得できない（引数.切替前発注先切替 == null）、
        //　@または、発注エンジンがSONAR（引数.切替前発注先切替.isSONAR() == true）の場合
        //１－１）trueを返却する。
        if (l_orderSwitching == null || l_orderSwitching.isSONAR())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //２）falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     *先物OP注文取引キューテーブルの発注経路区分を更新する。 <BR>
     *<BR>
     *１）条件に該当するレコードを更新する。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@口座ID = パラメータ.先物OP注文取引キューRow.口座ID and <BR>
     *　@識別コード = パラメータ.先物OP注文取引キューRow.識別コード and <BR>
     *　@処理区分 = パラメータ.先物OP注文取引キューRow.処理区分 <BR>
     *<BR>
     *２）　@変更後有効フラグによって、レコードの発注経路区分を更新する。 <BR>
     *<BR>
     *　@２－１）　@パラメータ.発注先情報.変更後有効フラグ == 1（有効）の場合 <BR>
     *　@　@選択した発注経路に更新する。 <BR>
     *<BR>
     *　@２－２）　@パラメータ.発注先情報.変更後有効フラグ == 0（無効）の場合 <BR>
     *　@　@発注経路を"発注停止"に更新する。 <BR>
     *<BR>
     *　@【ｘTrade】補足資料.DB更新  <BR>
     *　@「発注先切替_先物OP注文取引キューテーブル.xls」を参照。 <BR>
     *<BR>
     *３）commitを行う。 <BR>
     *　@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[])<BR>
     *<BR>
     *@@param 先物OP注文取引キューRow - 先物OP注文取引キューRow <BR>
     *@@param 発注先情報 - 発注先情報 <BR>
     *@@throws WEB3BaseException <BR>
     */
    private void updateIfoOrderAll(HostFotypeOrderAllRow l_hostFotypeOrderAllRow, 
        WEB3AdminOrderRouteSwitchingInfo l_infoUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".updateIfoOrderAll(HostFotypeOrderAllRow, WEB3AdminOrderRouteSwitchingInfo)";
        log.entering(STR_METHOD_NAME);

        // 更新条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and order_request_number = ? ");
        l_sbWhere.append(" and status = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                Long.toString(l_hostFotypeOrderAllRow.getAccountId()),
                l_hostFotypeOrderAllRow.getOrderRequestNumber(),
                l_hostFotypeOrderAllRow.getStatus()
            };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // パラメータ.発注先情報.変更後有効フラグ == 1（有効）の場合、
            if (WEB3ValidFlag.ON.equals(l_infoUnit.changedValidFlag))
            {                
                // BatchedQuery[]の作成用
                ArrayList l_listQuery = new ArrayList();
                
                // キューテーブルの発注経路を更新する。 
                BatchedQuery l_updQueryDiv = BatchedQuery.createUpdateAllQuery(HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",l_infoUnit.submitOrderRouteDiv));
                
                // Listにadd
                l_listQuery.add(l_updQueryDiv);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // 配列型に変換
                l_listQuery.toArray(l_queries);   
                
                // 更新処理実行/別トランザクションにより、単独コミット
                l_queryProcessor.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );                                    
            }
            // パラメータ.発注先情報.変更後有効フラグ == 0（無効）の場合、
            else if (WEB3ValidFlag.OFF.equals(l_infoUnit.changedValidFlag))
            {
                // BatchedQuery[]の作成用
                ArrayList l_listQuery = new ArrayList();
                
                BatchedQuery l_updQueryStop = BatchedQuery.createUpdateAllQuery(HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere,
                    this.setColumValue("submit_order_route_div",WEB3SubmitOrderRouteDivDef.ORDER_STOP));
                
                // Listにadd
                l_listQuery.add(l_updQueryStop);
                
                BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
                
                // 配列型に変換
                l_listQuery.toArray(l_queries);     
                
                // 更新処理実行/別トランザクションにより、単独コミット
                l_queryProcessor.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
            }
        }
        catch (DataFindException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(" DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *先物OP注文単位テーブルの発注経路区分を更新する。 <BR>
     *<BR>
     *１）先物OP注文単位Rowの発注経路区分を切替対象の発注経路区分、 <BR>
     *　@更新日付を現在日時にそれぞれ更新する。 <BR>
     *<BR>
     *　@[条件] <BR>
     *　@注文単位ID = パラメータ.先物OP注文単位Row.注文単位ID <BR>
     *<BR>
     *　@[更新値] <BR>
     *　@発注経路区分 = パラメータ.発注先情報.発注経路区分 <BR>
     *　@更新日付 = 現在日時 <BR>
     *<BR>
     *　@【ｘTrade】補足資料.DB更新 <BR>
     *　@「発注先切替_先物OP注文単位テーブル仕様.xls」を参照。 <BR>
     *<BR>
     *２）commitを行う。 <BR>
     *　@* QueryProcessor.doQueries(QueryProcessor.TX_CREATE_NEW, BatchedQuery[])<BR>
     * <BR>
     * @@param 先物OP注文取引キューRow - 先物OP注文取引キューRow <BR>
     * @@param 発注先情報オブジェクト - 発注先情報オブジェクト <BR>
     * @@throws WEB3BaseException 
     */
     private void updateIfoOrderUnit(IfoOrderUnitRow l_ifoOrderUnitRow, 
         WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj) 
         throws WEB3BaseException
     {
         final String STR_METHOD_NAME = ".updateIfoOrderUnit(IfoOrderUnitRow," +
         "WEB3AdminOrderRouteSwitchingInfo)";
         log.entering(STR_METHOD_NAME);

         // 発注経路区分を取得
         String l_sbumitRouteDiv = l_switchInfoObj.submitOrderRouteDiv;
         
         // 更新条件文字列の生成
         StringBuffer l_sbWhere = new StringBuffer();
         l_sbWhere.append(" order_unit_id = ? ");

         // 更新条件コンテナの生成
         Object[] l_objWhere =
             {
                 Long.toString(l_ifoOrderUnitRow.getOrderUnitId())
             };

         //注文単位レコードの発注経路区分を切替対象の発注経路、更新日付を現在日時にそれぞれ更新する。
         QueryProcessor l_queryProcessor;
         try
         {
             l_queryProcessor = Processors.getDefaultProcessor();
             //BatchedQuery[]の作成用
             ArrayList l_listQuery = new ArrayList();
             
             BatchedQuery l_updateQuery = BatchedQuery.createUpdateAllQuery(IfoOrderUnitRow.TYPE,
                 l_sbWhere.toString(),
                 l_objWhere,
                 this.setColumValue("submit_order_route_div",l_sbumitRouteDiv));
             
             l_listQuery.add(l_updateQuery);
             
             BatchedQuery[] l_queries = new BatchedQuery[l_listQuery.size()];
             
             // 配列型に変換
             l_listQuery.toArray(l_queries);  
             
             // 更新処理実行/別トランザクションにより、単独コミット
             l_queryProcessor.doQueries( QueryProcessor.TX_CREATE_NEW, l_queries );
         }
         catch (DataException l_de)
         {
             log.error(STR_METHOD_NAME, l_de);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_de.getMessage(),
                 l_de);
         }
         log.exiting(STR_METHOD_NAME);
     }

    /**
     * (get有効発注経路)<BR>
     * 有効な発注先切替オブジェクトを取得する。 <BR>
     * <BR>
     * １）有効な発注先切替オブジェクトを取得する。 <BR>
     * <BR>
     * 　@* WEB3GentradeOrderSwitching.getOnOrderSwitching() <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@証券会社コード： パラメータ.証券会社コード <BR>
     * 　@銘柄タイプ： パラメータ.発注先情報.銘柄タイプ <BR>
     * 　@市場コード： パラメータ.発注先情報.市場コード <BR>
     * 　@フロント発注システム区分： パラメータ.発注先情報.フロント発注システム区分 <BR>
     * <BR>
     * ２）戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_orderRouteSwitchingInfo - 発注先情報
     * @@return WEB3GentradeOrderSwitching
     * @@throws WEB3BaseException
     */
     private WEB3GentradeOrderSwitching getExpirationOrderRoute(
         String l_strInstitutionCode,
         WEB3AdminOrderRouteSwitchingInfo l_orderRouteSwitchingInfo) throws WEB3BaseException
     {
         final String STR_METHOD_NAME = " getExpirationOrderRoute(String, WEB3AdminOrderRouteSwitchingInfo)";
         log.entering(STR_METHOD_NAME);

         EnumeratedManager l_enumeratedManager = EnumeratedManager.getInstance();

         int l_intProductType = Integer.parseInt(l_orderRouteSwitchingInfo.productType);
         ProductTypeEnum l_productTypeEnum = (ProductTypeEnum)l_enumeratedManager.valueFromInt(
             ProductTypeEnum.class,
             l_intProductType);

         //１）有効な発注先切替オブジェクトを取得する。
         WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
             WEB3GentradeOrderSwitching.getOnOrderSwitching(
                 l_strInstitutionCode,
                 l_productTypeEnum,
                 l_orderRouteSwitchingInfo.marketCode,
                 l_orderRouteSwitchingInfo.frontOrderSystemCode);

         //２）戻り値を返却する。
         log.exiting(STR_METHOD_NAME);
         return l_gentradeOrderSwitching;
     }
}
@
