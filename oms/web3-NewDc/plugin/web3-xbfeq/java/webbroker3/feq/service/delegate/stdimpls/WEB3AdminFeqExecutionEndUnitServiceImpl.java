head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式出来終了UnitServiceImpl(WEB3AdminFeqExecutionEndUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/18 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
Revesion History : 2010/01/12 張騰宇(中訊) モデルNo.533 No.538
Revesion History : 2010/09/10 劉レイ(中訊) モデルNo.547
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqExecEndUpdateInterceptor;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式出来終了UnitServiceImpl)<BR>
 * 外国株式出来終了UnitService実装クラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionEndUnitServiceImpl implements WEB3AdminFeqExecutionEndUnitService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndUnitServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F1000F
     */
    public WEB3AdminFeqExecutionEndUnitServiceImpl() 
    {
     
    }
    
    /**
     * (exec出来終了)<BR>
     * 顧客単位の出来終了処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）出来終了）exec出来終了」<BR>
     * @@param l_account - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF87C0325
     */
    public void execExecEnd(WEB3GentradeMainAccount l_account, String l_strMarketCode, Date l_datBizDate) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execExecEnd(WEB3GentradeMainAccount, String, Date) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_account == null)
        {
            log.debug("顧客オブジェクトが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "顧客オブジェクトが存在しない。");
        }
        
        //1.1:lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.debug("FinAppが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "FinAppが存在しない。");
        }

        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug("拡張アカウントマネージャが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "拡張アカウントマネージャが存在しない。");
        }
        Institution l_institution = l_account.getInstitution();
        if (l_institution == null)
        {
            log.debug("証券会社が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "証券会社が存在しない。");
        }
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        
        Branch l_branch = l_account.getBranch();
        if (l_branch == null)
        {
            log.debug("部店が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "部店が存在しない。");
        }
             
        l_accountManager.lockAccount(
            l_strInstitutionCode,
            l_branch.getBranchCode(),
            l_account.getAccountCode());

        //1.2:get出来終了対象注文単位(long, String, String, Date)
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        if (l_tradingModule == null)
        {
            log.debug("TradingModuleが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "TradingModuleが存在しない。");
        }
        WEB3FeqOrderManager l_orderMgr = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderMgr == null)
        {
            log.debug("外国株式注文マネージャが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式注文マネージャが存在しない。");
        }
        
        WEB3FeqOrderUnit[] l_orderUnits = l_orderMgr.getOrderExecEndObjectOrderUnit(
            new Long(l_account.getAccountId()),
            l_strInstitutionCode,
            l_strMarketCode,
            l_datBizDate);

        //1.3: updateトランザクション(注文単位ＩＤ一覧 : long[], 基準日 : Date, isネッティング処理 : boolean)
        WEB3FeqPositionManager l_positionMgr = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        if (l_positionMgr == null)
        {
            log.debug("外国株式ポジションマネージャが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式ポジションマネージャが存在しない。");
        }
            
        //注文単位ID一覧：　@get出来終了対象注文単位()の戻り値より抽出した注文単位IDの一覧         
        long[] l_lngOrderUnitIdList = null;
        int l_intCnt = 0;
        
        if (l_orderUnits != null && l_orderUnits.length > 0)
        {
            l_intCnt = l_orderUnits.length;            
            int l_intCnt2 = 0;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
                
                if (l_orderUnit != null)
                {
                    l_intCnt2++;
                }
            }            
            
            if (l_intCnt2 > 0)
            {
                l_lngOrderUnitIdList = new long[l_intCnt2];
                int l_intNo = 0;
            
                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
                
                    if (l_orderUnit != null)
                    {
                        l_lngOrderUnitIdList[l_intNo] = l_orderUnit.getOrderUnitId();                        
                        l_intNo++;
                    }
                }
            }
        }        

        //isネッティング処理
        boolean l_blnIsNetting = false;

        //updateトランザクション(注文単位ＩＤ一覧 : long[], 基準日 : Date, isネッティング処理 : boolean)
        //[引数]
        //注文単位ID一覧：　@get出来終了対象注文単位()の戻り値より抽出した注文単位IDの一覧
        //基準日：　@パラメータ.発注日
        //isネッティング処理：　@false
        l_positionMgr.updateTransaction(l_lngOrderUnitIdList, l_datBizDate, l_blnIsNetting);
        
        //1.4:(*)get出来終了対象注文単位()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
            
            if (l_orderUnit != null)
            {
                WEB3GentradeMarket l_market = null;
                boolean l_blnIsDayTradeAdoption = false;
                boolean l_blnIsDayTradeMarket = false;
                try
                {
                    FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                    l_market = (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_feqOrderUnitRow.getMarketId()));

                    l_blnIsDayTradeAdoption = ((WEB3GentradeInstitution)l_institution).isDayTradeAdoption();
                    l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //（is日計り取引採用() == false　@or　@is日計り市場() == false）　@且つ 買付注文（is買付() == true)
                if ((!l_blnIsDayTradeAdoption || !l_blnIsDayTradeMarket) && l_orderUnit.isBuy())
                {
                    //update保有資産(注文単位 : 外国株式注文単位)
                    l_positionMgr.updateAsset(l_orderUnit);
                }

                //1.4.2:(*)出来終了対象注文チェック
                //以下の条件を満たす注文単位は、出来終了処理を行う。
                //  ①@期日が到来している注文
                //    (注文単位.注文失効日付 <= パラメータ.発注日)
                //  ②オープンである注文単位
                //    (注文単位.注文有効状態 == "オープン") 
                int l_intFlag = WEB3DateUtility.compareToDay(
                    l_orderUnit.getExpirationTimestamp(), 
                    l_datBizDate);
                
                if (l_intFlag <= 0 && OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
                {                    
                    //1.4.2.1:外国株式出来終了更新イベントインタセプタ( )
                    WEB3FeqExecEndUpdateInterceptor l_execEndUpdatenIterceptor = 
                        new WEB3FeqExecEndUpdateInterceptor();
                    
                    //1.4.2.2:setThreadLocalPersistenceEventInterceptor
                    //(arg0 : FeqOrderManagerPersistenceEventInterceptor)
                    l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_execEndUpdatenIterceptor);
                    
                    //1.4.2.3:expireOrder(arg0 : long)
                    ProcessingResult l_result = l_orderMgr.expireOrder(l_orderUnit.getOrderId());
        
                    if (l_result != null && l_result.isFailedResult())
                    {
                        log.debug("expireOrderエラー");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            l_result.getErrorInfo(),
                            this.getClass().getName() + STR_METHOD_NAME,
                            "expireOrderエラー");
                    }
                }
                //1.4.3:(*)上記以外の場合
                else
                {
                    //1.4.3.1:update出来終了注文(外国株式注文単位)
                    this.updateExecEndOrder(l_orderUnit);
                }
            }                        
        }
        
        //1.5:is信用口座開設(String)
        boolean l_blnIsMarginAccountEstablished = 
            l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //1.6:getSubAccount(arg0 : SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = null;
        //[is信用口座開設()の戻り値 == trueの場合] 
        try
        {
            if (l_blnIsMarginAccountEstablished)
            {                
                //SubAccountTypeEnum.株式信用取引口座をセット。 
                l_subAccount = (WEB3GentradeSubAccount)l_account.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);//NotFoundException
            }                
            //[上記以外の場合] 
            else
            {                
                //SubAccountTypeEnum.株式取引口座をセット。
                l_subAccount = (WEB3GentradeSubAccount)l_account.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException
            }
        }
        catch (NotFoundException l_ex)
        {
            log.debug("補助口座がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "補助口座がありません。",
                l_ex); 
        }
        
        //1.7:余力再計算(補助口座 : 補助口座)
        WEB3TPTradingPowerReCalcService l_tPTradingPowerReCalcServiceImpl = 
            (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
        
        if (l_tPTradingPowerReCalcServiceImpl == null)
        {
            log.debug("余力再計算サービスImplが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "余力再計算サービスImplが存在しない。");
        }
        
        l_tPTradingPowerReCalcServiceImpl.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (update出来終了注文)<BR>
     * 引数の注文に出来終了日時などを設定する。<BR>
     * <BR>
     * １）　@更新者コードの取得<BR>
     * 　@セッションよりログインＩＤを取得し、<BR>
     * 　@ログインＩＤに該当する管理者を取得する。<BR>
     * 　@取得した管理者.管理者コードを更新者コードとする。<BR>
     * <BR>
     * ２）　@出来終了注文の出来終了日時をupdateする。<BR>
     * <BR>
     * 　@２－１）　@以下の条件に該当する出来終了注文の注文単位レコードをupdateする。<BR>
     * 　@　@<条件><BR>
     * 　@　@　@注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@　@注文単位レコード.出来終了処理日時 = 現在日時<BR>
     * 　@　@　@注文単位レコード.更新者コード = 取得した更新者コード<BR>
     * 　@　@　@注文単位レコード.更新日付 = 現在日時<BR>
     * <BR>
     * 　@２－２）　@以下の条件に該当する出来終了注文の注文履歴の、<BR>
     * 　@　@　@　@　@最終履歴レコードの更新者コード、更新日付をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@履歴テーブル.注文単位ID = パラメータ.注文単位.注文単位ID　@かつ<BR>
     * 　@　@履歴テーブル.注文履歴番号 = パラメータ.注文単位.注文履歴最終通番<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@履歴レコード.更新者コード = 取得した更新者コード<BR>
     * 　@　@履歴レコード.更新日付 = 現在日時<BR>
     * <BR>
     * 　@２－３）　@以下の条件に該当する、出来終了注文の注文（ヘッダ）の<BR>
     * 　@　@更新者コード、更新日時をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@注文（ヘッダ）テーブル.注文ID = パラメータ.注文単位.注文ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@注文（ヘッダ）レコード.更新者コード = 取得した更新者コード<BR>
     * 　@　@注文（ヘッダ）レコード.更新日付 = 現在日時<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42C0B613024F
     */
    public void updateExecEndOrder(WEB3FeqOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " updateExecEndOrder(WEB3FeqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("注文単位が存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文単位が存在しない。");
        }
                
        try
        {
            //１）　@更新者コードの取得
            //セッションよりログインＩＤを取得し、ログインＩＤに該当する管理者を取得する。
            //取得した管理者.管理者コードを更新者コードとする。
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();
            if (l_administrator == null)
            {
                log.debug("管理者が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者が存在しない。");
            }

            String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
            //２）　@出来終了注文の出来終了日時をupdateする。            
            //２－１）　@以下の条件に該当する出来終了注文の注文単位レコードをupdateする。 
            //      <条件> 
            //　@　@　@     注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID 
            //
            //　@　@     <更新内容> 
            //　@　@　@     注文単位レコード.出来終了日時 = 現在日時 
            //　@　@　@     注文単位レコード.更新者コード = 取得した更新者コード 
            //　@　@　@     注文単位レコード.更新日付 = 現在日時 
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (l_orderUnitRow == null)
            {
                log.debug("注文単位行が存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "注文単位行が存在しない。");
            }
            
            long l_lngOrderUnitId = l_orderUnitRow.getOrderUnitId();
            
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            
            String l_strWhere1 = " order_unit_id = ? ";
            Object[] l_obj1 = {new Long(l_lngOrderUnitId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
            List l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE, 
                l_strWhere1,
                " FOR UPDATE ",
                l_obj1);//DataNetworkException, DataQueryException
            
            if (l_lisOrderUnitRows != null && !l_lisOrderUnitRows.isEmpty())
            {
                int l_intCnt = l_lisOrderUnitRows.size();
                
                if (l_intCnt == 1)
                {
                    FeqOrderUnitRow l_orderUnitRowForUpdate = 
                        (FeqOrderUnitRow)l_lisOrderUnitRows.get(0);
            
                    FeqOrderUnitParams l_orderUnitParamsForUpdate = 
                        new FeqOrderUnitParams(l_orderUnitRowForUpdate);
                
                    l_orderUnitParamsForUpdate.setExecEndTimestamp(l_tsSystemTime);
                    l_orderUnitParamsForUpdate.setLastUpdater(l_strAdministratorCode);
                    l_orderUnitParamsForUpdate.setLastUpdatedTimestamp(l_tsSystemTime);

                    l_queryProcessor.doUpdateQuery(l_orderUnitParamsForUpdate);
                    //DataNetworkException,DataQueryException
                }
                else
                {
                    log.debug("注文単位テーブルがデータ不整合エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "注文単位テーブルがデータ不整合エラー。");
                }
            }            
            
            //２－２）　@以下の条件に該当する出来終了注文の注文履歴の、
            //最終履歴レコードの更新者コード、更新日付をupdateする。
            //     <条件> 
            //　@　@      履歴テーブル.注文単位ID = パラメータ.注文単位.注文単位ID　@かつ 
            //　@　@      履歴テーブル.注文履歴番号 = パラメータ.注文単位.注文履歴最終通番 
            //　@　@ <更新内容> 
            //　@　@      履歴レコード.更新者コード = 取得した更新者コード 
            //　@　@      履歴レコード.更新日付 = 現在日時 
            int l_intLastOrderActionSerialNo = l_orderUnitRow.getLastOrderActionSerialNo();
            
            String l_strWhere2 = " order_unit_id = ? and order_action_serial_no = ? ";
            Object[] l_obj2 = {new Long(l_lngOrderUnitId),
                new Integer(l_intLastOrderActionSerialNo)};
                
            List l_lisOrderActionRows = l_queryProcessor.doFindAllQuery(
                FeqOrderActionRow.TYPE, 
                l_strWhere2,
                " FOR UPDATE ",
                l_obj2);//DataNetworkException, DataQueryException
            
            if (l_lisOrderActionRows != null && !l_lisOrderActionRows.isEmpty())
            {                
                int l_intCnt = l_lisOrderActionRows.size();
                
                if (l_intCnt == 1)
                {
                    FeqOrderActionParams l_orderActionParams = new FeqOrderActionParams(
                        (FeqOrderActionRow)l_lisOrderActionRows.get(0));
                    
                    l_orderActionParams.setLastUpdater(l_strAdministratorCode);
                    l_orderActionParams.setLastUpdatedTimestamp(l_tsSystemTime);
                    
                    l_queryProcessor.doUpdateQuery(l_orderActionParams);//DataNetworkException,DataQueryException
                }
                else
                {
                    log.debug("注文履歴テーブルがデータ不整合エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "注文履歴テーブルがデータ不整合エラー。");
                }
            }
            
            //２－３）　@以下の条件に該当する、出来終了注文の注文（ヘッダ）の 
            //　@　@      更新者コード、更新日時をupdateする。 
            //　@　@          <条件> 
            //　@　@          注文（ヘッダ）テーブル.注文ID = パラメータ.注文単位.注文ID 
            //　@　@          <更新内容> 
            //　@　@          注文（ヘッダ）レコード.更新者コード = 取得した更新者コード 
            //　@　@          注文（ヘッダ）レコード.更新日付 = 現在日時             
            String l_strWhere3 = " order_id = ? ";
            Object[] l_obj3 = {new Long(l_orderUnit.getOrderId())};
            
            List l_lisOrderRows = l_queryProcessor.doFindAllQuery(
                FeqOrderRow.TYPE, 
                l_strWhere3,
                " FOR UPDATE ",
                l_obj3);//DataNetworkException, DataQueryException
            
            if (l_lisOrderRows != null && !l_lisOrderRows.isEmpty())
            {
                int l_intCnt = l_lisOrderRows.size();
                
                if (l_intCnt == 1)
                {
                    FeqOrderRow l_ordertRows = 
                        (FeqOrderRow)l_lisOrderRows.get(0);
            
                    FeqOrderParams l_orderParams = 
                        new FeqOrderParams(l_ordertRows);
                
                     l_orderParams.setLastUpdater(l_strAdministratorCode);
                     l_orderParams.setLastUpdatedTimestamp(l_tsSystemTime);
                    
                     l_queryProcessor.doUpdateQuery(l_orderParams);//DataNetworkException,DataQueryException
                }
                else
                {
                    log.debug("注文（ヘッダ）テーブルがデータ不整合エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "注文（ヘッダ）テーブルがデータ不整合エラー。");
                }
            }  
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
}
@
