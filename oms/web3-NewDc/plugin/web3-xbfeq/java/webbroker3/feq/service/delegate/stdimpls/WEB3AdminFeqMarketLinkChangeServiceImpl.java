head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqMarketLinkChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式市場連動区分変更サービスImpl(WEB3AdminFeqMarketLinkChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/12 何文敏 (中訊) 新規作成   
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeInputRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeInputResponse;
import webbroker3.feq.message.WEB3FeqMarketLinkStateUnit;
import webbroker3.feq.service.delegate.WEB3AdminFeqMarketLinkChangeService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式市場連動区分変更サービスImpl)<BR>
 * 管理者外国株式市場連動区分変更サービス実装クラス<BR>
 *   
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AdminFeqMarketLinkChangeServiceImpl  implements WEB3AdminFeqMarketLinkChangeService
{

    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqMarketLinkChangeServiceImpl.class);
       
    /**
     * 管理者外国株式市場連動区分変更処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －get入力画面()<BR>
     * －validate変更()<BR>
     * －submit変更()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91032D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターが未指定(null)です。");
        }
        
        //　@管理者外国株式市場連動区分変更処理を実施する。
        //　@リクエストデータの型に対応するメソッドをコールする。
        WEB3GenResponse l_response = null;
        
        // －get入力画面()
        if (l_request instanceof WEB3AdminFeqMarketLinkChangeInputRequest)
        {
            l_response = this.getInputScreen(
                (WEB3AdminFeqMarketLinkChangeInputRequest) l_request);
        }
        
        // －validate変更()
        else if (l_request instanceof WEB3AdminFeqMarketLinkChangeConfirmRequest)
        {
            l_response = this.validateChange(
                (WEB3AdminFeqMarketLinkChangeConfirmRequest) l_request);
        }
        
        // －submit変更()
        else if (l_request instanceof WEB3AdminFeqMarketLinkChangeCompleteRequest)
        {
            l_response = this.submitChange(
                (WEB3AdminFeqMarketLinkChangeCompleteRequest) l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示処理を行なう。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式市場連動区分変更)get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqMarketLinkChangeInputResponse getInputScreen(
    	WEB3AdminFeqMarketLinkChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(" +
            "WEB3AdminFeqMarketLinkChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
                 
        // 1.1 getInstanceFromログイン情報
        WEB3Administrator l_admin =
            WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            String l_strMessage = "予期しないシステムエラーが発生しました。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // 1.2 validate権限
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
        
        // 1.3 getMarkets(Institution)       
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        Institution l_institution = l_admin.getInstitution();        
        Market[] l_markets =
            l_finObjectManager.getMarkets(l_institution);
        
        // 1.4 ArrayList( )
        ArrayList l_arrayList = new ArrayList();
        
        // 1.5 （取得したの市場オブジェクトの数分、LOOP）
        int l_intCount = 0;
        if (l_markets != null && l_markets.length != 0)
        {
            l_intCount = l_markets.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            // 1.5.1 findRowByPk(long, String, int)
            long l_lngMarketId = l_markets[i].getMarketId();                   
            MarketRow l_marketRow = 
                (MarketRow)l_markets[i].getDataSourceObject();
            try
            {
                MarketPreferencesDao.findRowByPk(
                    l_lngMarketId, WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER, 1);
            } 
            catch(DataFindException l_ex)
            {
                continue;
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
              
            // 1.5.2 外国株式市場連動状況( )
            WEB3FeqMarketLinkStateUnit l_feqMarketLinkStateUnit = 
                new WEB3FeqMarketLinkStateUnit();
            
            // 1.5.3  プロパティセット
            // 市場コード: 市場.市場コード
            l_feqMarketLinkStateUnit.marketCode = l_marketRow.getMarketCode();
            
            // 市場名: 市場.市場名
            l_feqMarketLinkStateUnit.marketName = l_marketRow.getMarketName();
            
            // 外国株式市場連動区分: 市場.外国株式市場連動区分
            l_feqMarketLinkStateUnit.feqMarketLinkDiv = l_marketRow.getFeqOrderRequestDiv();
            
            // 変更後外国株式市場連動区分: 市場.外国株式市場連動区分
            l_feqMarketLinkStateUnit.afterFeqMarketLinkDiv = l_marketRow.getFeqOrderRequestDiv();
            
            //1.5.4 add(arg0 : Object)
            l_arrayList.add(l_feqMarketLinkStateUnit);
        } 
        
                 
        // 1.6（分岐フロー：　@外国株式市場連動状況のリスト.size() > 0 の場合、
        //   外国株式市場連動状況一覧を取得する）
        WEB3FeqMarketLinkStateUnit[] l_feqMarketLinkStateLists = null;     
        if (l_arrayList.size() > 0)
        {     
            l_feqMarketLinkStateLists = 
                new WEB3FeqMarketLinkStateUnit[l_arrayList.size()];
            
            //1.6.1 toArray(Object[])
            l_arrayList.toArray(l_feqMarketLinkStateLists);
        }
        
        // 1.7 createResponse( )
        WEB3AdminFeqMarketLinkChangeInputResponse l_response = 
            (WEB3AdminFeqMarketLinkChangeInputResponse) l_request.createResponse();
        
        // 1.8 プロパティセット
        // 現在日時: Gtlutils.getSystemTimeStam()
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        l_response.feqMarketLinkStateList = l_feqMarketLinkStateLists;
        
        // return レスポンスデータ
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * 管理者外国株式市場連動区分変更確認処理を行なう。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式市場連動区分変更)validate変更」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqMarketLinkChangeConfirmResponse validateChange(
    	WEB3AdminFeqMarketLinkChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChange(" +
            "WEB3AdminFeqMarketLinkChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        // 1.1 validate()
        l_request.validate();
        
        // 1.2 ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.3 validate権限(String, boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        if (l_admin == null)
        {
            String l_strMessage = "予期しないシステムエラーが発生しました。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // 1.4 メッセージ createResponse()
        WEB3AdminFeqMarketLinkChangeConfirmResponse l_response = 
            (WEB3AdminFeqMarketLinkChangeConfirmResponse) l_request.createResponse();
        
        // 1.5 プロパティセット
        //  現在日時: Gtlutils.getSystemTimeStam()
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        
        //  外国株式市場連動状況一覧: リクエスト.外国株式市場連動状況一覧       
        l_response.feqMarketLinkStateList = l_request.feqMarketLinkStateList;
                
        // return レスポンスデータ
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * (submit変更)<BR>
     * 管理者外国株式市場連動区分変更完了処理を行なう。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式市場連動区分変更)submit変更」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqMarketLinkChangeCompleteResponse submitChange(
    	WEB3AdminFeqMarketLinkChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChange(" +
            "WEB3AdminFeqMarketLinkChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
                
        // 1.1 validate( )
        l_request.validate();
        
        // 1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin =
            WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            String l_strMessage = "予期しないシステムエラーが発生しました。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // 1.3 validate権限
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        // 1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        
        // 1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        // 1.6 （リクエスト.外国株式市場連動状況一覧の件数分、LOOP）
        WEB3FeqMarketLinkStateUnit[] l_feqMarketLinkStateLists = 
            l_request.feqMarketLinkStateList;
        int l_intCount = l_feqMarketLinkStateLists.length;
        for (int i = 0; i < l_intCount; i++)
        {
            // 1.6.1 メッセージ （分岐フロー：　@リクエスト.外国株式市場連動状況一覧[i].
            // 外国株式市場連動区分 != リクエスト.外国株式市場連動状況一覧[i].変更後外国株式市場連動区分の場合）
            String l_strFeqMarketLinkDiv = l_feqMarketLinkStateLists[i].feqMarketLinkDiv;
            String l_strAterFeqMarketLinkDiv = l_feqMarketLinkStateLists[i].afterFeqMarketLinkDiv;
            if (!l_strFeqMarketLinkDiv.equals(l_strAterFeqMarketLinkDiv))
            {            
                // 1.6.2 条件に該当する市場マスタテーブルのレコードを更新する
                try
                {
                    String l_strmarketCode = l_feqMarketLinkStateLists[i].marketCode;
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    String l_strWhere = " market_code = ? and institution_code = ? "; 
                    Object[] l_objWhereValue = new Object[2];
                    l_objWhereValue[0] = l_strmarketCode;
                    l_objWhereValue[1] = l_strInstitutionCode;
                    List l_lisMarket = l_processor.doFindAllQuery(MarketRow.TYPE,
                        l_strWhere, l_objWhereValue);
                    
                    if ((l_lisMarket != null) && (!l_lisMarket.isEmpty()))
                    {
                        MarketParams l_marketParams = 
                            new MarketParams((MarketRow)l_lisMarket.get(0));
                        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
                        String l_strAdministratorCode = l_admin.getAdministratorCode();
                        
                        l_marketParams.setFeqOrderRequestDiv(l_strAterFeqMarketLinkDiv);
                        l_marketParams.setLastUpdater(l_strAdministratorCode);
                        l_marketParams.setLastUpdatedTimestamp(l_tsSystemTime);                     
                        l_processor.doUpdateQuery(l_marketParams);
                    }
                } 
                catch (DataNetworkException l_ex)
                {   
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(), 
                        l_ex);
                } 
                catch (DataQueryException l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(), 
                        l_ex);
                }
            }
        }
             
        // 1.7 createResponse( )
        WEB3AdminFeqMarketLinkChangeCompleteResponse l_response = 
            (WEB3AdminFeqMarketLinkChangeCompleteResponse) l_request.createResponse();
        
        // 1.8 プロパティセット
        // 現在日時: GtlUtils.getSystemTimeStam()
        l_response.currentDate =
            GtlUtils.getSystemTimestamp();
        
        //外国株式市場連動状況一覧: リクエスト.外国株式市場連動状況一覧       
        l_response.feqMarketLinkStateList = 
            l_request.feqMarketLinkStateList;
        
        // return レスポンスデータ
        log.exiting(STR_METHOD_NAME);
        return l_response; 
       
    }
}
@
