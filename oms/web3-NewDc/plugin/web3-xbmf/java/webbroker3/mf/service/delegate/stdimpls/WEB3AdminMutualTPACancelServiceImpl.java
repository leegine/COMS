head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整取消サービスImpl(WEB3AdminMutualTPACancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 黄建  (中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListUnit;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

/**
 * 投信管理者余力調整取消サービスImpl
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminMutualTPACancelServiceImpl 
	implements WEB3AdminMutualTPACancelService
{
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPACancelServiceImpl.class);
    
    /**
     * 投資信託余力調整取消処理を実施する。 <BR>
     * リクエストデータの型により、以下のいずれかのメソッドをコールする。 <BR>
     * ○投信管理者余力調整取消一覧リクエストの場合  <BR>
     * 	this.search余力調整取消( )  <BR>
     * ○投信管理者余力調整取消完了リクエストの場合  <BR>
     * 　@this.submit余力調整取消( ) <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
    	throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualTPACancelListRequest)
        {
            //this.search余力調整取消( )
            l_response = 
                this.searchTPACancel(
                    (WEB3AdminMutualTPACancelListRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualTPACancelCompleteRequest)
        {
            //this.submit余力調整取消( )
            l_response = 
                this.submitTPACancel(
                    (WEB3AdminMutualTPACancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(" パラメータ値が不正する！");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (search余力調整取消)<BR>
     * <BR>
     * 投資信託余力調整取消一覧処理を行う。<BR>
     * シーケンス図「（投信）余力調整取消一覧」参照<BR>
     *<BR>
     *==========================================================<BR>
     * シーケンス図「（投信）余力調整取消一覧」: <BR>
     * 1.4((get顧客(証券会社コード : String, 部店コード : String, 口座コード :String)<BR>
     * 顧客オブジェクトが取得できなかった場合、例外をスロー。<BR>
     *『該当する顧客が存在しません。』<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01035<BR>
     *==========================================================<BR>
     * @@param l_request - (投信管理者余力調整取消一覧リクエスト)
     * @@return WEB3AdminMutualTPACancelListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    protected WEB3AdminMutualTPACancelListResponse searchTPACancel(
        WEB3AdminMutualTPACancelListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "searchTPACancel(WEB3AdminMutualTPACancelListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST, false);
        
        //1.4 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
		//[引数] 
		//証券会社コード： 管理者オブジェクト.get証券会社コード() 
		//部店コード： 管理者オブジェクト.get部店コード() 
		//口座コード： リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {        
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(
                    l_admin.getInstitutionCode(), 
                    l_admin.getBranchCode(), 
                    l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //顧客オブジェクトが取得できなかった場合、例外をスロー。
            //『該当する顧客が存在しません。』
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.5  is信用口座開設(弁済区分 : String)
		boolean l_blnisMarginAccountEstablished =
			l_mainAccount.isMarginAccountEstablished(
			    WEB3GentradeRepaymentDivDef.DEFAULT);
		
        //1.6 getSubAccount(arg0 : SubAccountTypeEnum)
		//[引数]   
		//　@補助口座タイプ：    
		//is信用口座開設=true の場合、SubAccountTypeEnum.株式信用取引口座（保証金）    
		//is信用口座開設=false の場合、SubAccountTypeEnum.株式取引口座（預り金）
		SubAccount l_subAccount = null;
		try
		{
			if (l_blnisMarginAccountEstablished)
			{
	            l_subAccount = 
	                l_mainAccount.getSubAccount(
	                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
			}
			else
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
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
		
        //1.7 以下の条件より投信注文単位の一覧を取得する
		//[検索条件]
		//口座ID： 取得した補助口座オブジェクト.getAccountId( )
		//補助口座ID： 取得した補助口座オブジェク.getSubAccountId( )
		//銘柄タイプ： ”3：投資信託”
		//注文有効状態： ”1:オープン”
		//注文経路区分： ”9：HOST”
		//識別コード： NULL
		//[ソート条件]
		//第1ソートキー： 約定日(昇順)
		//第2ソートキー： 銘柄ID(昇順)
        List l_lisRowsMfOrderUnit = new ArrayList();
        String l_strWhere = 
            "account_id = ? and sub_account_id = ? and product_type = ? " +
            "and order_open_status = ?  and order_root_div = ? and order_request_number is null";
        
        Object[] l_bindVars = {
            new Long(l_subAccount.getAccountId()), 
            new Long(l_subAccount.getSubAccountId()),
            ProductTypeEnum.MUTUAL_FUND,
            OrderOpenStatusEnum.OPEN,
            WEB3OrderRootDivDef.HOST}; 
        String l_strSortKey = " exec_date, product_id ";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRowsMfOrderUnit = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_strSortKey,
                null, 
                l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        int l_intSize = 0;
        if (l_lisRowsMfOrderUnit != null && !l_lisRowsMfOrderUnit.isEmpty())
        {
            l_intSize = l_lisRowsMfOrderUnit.size();
        }

        //1.8 投信注文単位リストの件数分繰り返し、投信管理者余力調整取消一覧行の配列を作成する
        List l_lisMfTPACancelListUnit = new ArrayList();
        for (int i = 0; i < l_intSize; i++)
        {
            MutualFundOrderUnitParams l_mfOrderUnitParams = 
                (MutualFundOrderUnitParams) l_lisRowsMfOrderUnit.get(i);
            
            //1.8.1 投信管理者余力調整取消一覧行( )
            WEB3AdminMutualTPACancelListUnit l_tpaCancelListUnit = 
                new WEB3AdminMutualTPACancelListUnit();
            
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mutualFundProduct =  null;
            try
            {
               l_mutualFundProduct = 
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getProduct(
                        l_mfOrderUnitParams.getProductId());
            }
    		catch (NotFoundException l_ex)
    		{
    			log.error("__NotFoundException__", l_ex);
    			log.exiting(STR_METHOD_NAME);
    			throw new WEB3BusinessLayerException(
    				WEB3ErrorCatalog.BUSINESS_ERROR_00377,
    				this.getClass().getName() + STR_METHOD_NAME,
    				l_ex.getMessage(),
    				l_ex);
    		}    
            
            //1.8.2 ＜プロパティ・セット＞
    		//[投信管理者余力調整取消一覧行に設定する値] 
    		//注文ID 　@　@= 投信注文単位.get注文ID() 
            l_tpaCancelListUnit.orderId = l_mfOrderUnitParams.getOrderId() + "";
    		//銘柄コード = 投信注文単位.getProduct().get銘柄コード() 
            l_tpaCancelListUnit.mutualProductCode = 
                l_mutualFundProduct.getProductCode();
    		//銘柄名　@　@ = 投信注文単位.getProduct().get銘柄名() 
            l_tpaCancelListUnit.mutualProductName = 
                l_mutualFundProduct.getMutualProductName();
    		//精算金額  = 投信注文単位.get注文数量() 
            l_tpaCancelListUnit.settlePrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_mfOrderUnitParams.getQuantity()); 
    		//発注日     = 投信注文単位.get発注日() 
            l_tpaCancelListUnit.orderBizDate = 
                WEB3DateUtility.getDate(
                    l_mfOrderUnitParams.getBizDate(), "yyyyMMdd");
    		//約定日     = 投信注文単位.get約定日() 
            l_tpaCancelListUnit.executionTimestamp = 
                l_mfOrderUnitParams.getExecDate();
            //受渡日     = 投信注文単位.get受渡日() 
            l_tpaCancelListUnit.deliveryDate = 
                l_mfOrderUnitParams.getDeliveryDate();
            l_lisMfTPACancelListUnit.add(l_tpaCancelListUnit);
        }

        //1.9 createResponse( )
        WEB3AdminMutualTPACancelListResponse l_response = 
            (WEB3AdminMutualTPACancelListResponse) l_request.createResponse();
        
        //1.10 ＜プロパティ・セット＞
		//[投信管理者余力調整取消一覧レスポンスに設定する値] 
		//余力調整取消一覧： 作成した投信管理者余力調整取消一覧行の配列
        WEB3AdminMutualTPACancelListUnit[] l_cancelUnits = 
            new WEB3AdminMutualTPACancelListUnit[l_lisMfTPACancelListUnit.size()];
        l_lisMfTPACancelListUnit.toArray(l_cancelUnits);
        l_response.cancelList = l_cancelUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit余力調整取消)<BR>
     * <BR>
     * 投資信託余力調整取消完了処理を行う。<BR>
     * シーケンス図「（投信）余力調整取消完了」参照<BR>
     * @@param l_request - (投信管理者余力調整取消完了リクエスト)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    protected WEB3AdminMutualTPACancelCompleteResponse submitTPACancel(
        WEB3AdminMutualTPACancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitTPACancel(WEB3AdminMutualTPACancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        //ログイン情報より、管理者オブジェクトを取得する。 
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST, true);
        
        //1.4 validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //［引数］  
		//証券会社コード： 管理者オブジェクト.get証券会社コード()の戻り値  
		//部店コード： 管理者オブジェクト.get部店コード()の戻り値  
		//口座コード： リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {        
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(
                    l_admin.getInstitutionCode(), 
                    l_admin.getBranchCode(), 
                    l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //顧客オブジェクトが取得できなかった場合、例外をスロー。
            //『該当する顧客が存在しません。』
            log.error("該当する顧客が存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.6  lock口座(証券会社コード : String, 部店コード : String, 口座コード : String)
        l_accMgr.lockAccount(
            l_admin.getInstitutionCode(), 
            l_admin.getBranchCode(), 
            l_mainAccount.getAccountCode());
        
        //1.7 is信用口座開設(弁済区分 : String)
		boolean l_blnisMarginAccountEstablished =
			l_mainAccount.isMarginAccountEstablished(
			    WEB3GentradeRepaymentDivDef.DEFAULT);
		
        //1.8 getSubAccount(arg0 : SubAccountTypeEnum)
		//補助口座オブジェクトを取得する。  
		//[引数]   
		//補助口座タイプ：    
		//is信用口座開設=true の場合、SubAccountTypeEnum.株式信用取引口座（保証金）    
		//is信用口座開設=false の場合、SubAccountTypeEnum.株式取引口座（預り金） 
		SubAccount l_subAccount = null;
		try
		{
			if (l_blnisMarginAccountEstablished)
			{
	            l_subAccount = 
	                l_mainAccount.getSubAccount(
	                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
			}
			else
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
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
        
        //1.9 (*)検索条件文字列の作成
        String l_strWhere = "order_id = ?";
        //1.10 (*)検索条件データコンテナの作成
        Object[] l_objWhereValues = { l_request.orderId };       
        try
        {
            //1.11 getDefaultProcessor()
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //1.12 投信注文履歴テーブルのレコードを削除する。
            l_processor.doDeleteAllQuery(
                MutualFundOrderActionRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            //1.13 投信注文単位テーブルのレコードを削除する。
            l_processor.doDeleteAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            //1.14 投信注文テーブルのレコードを削除する。
            l_processor.doDeleteAllQuery(
                MutualFundOrderRow.TYPE,
                l_strWhere,
                l_objWhereValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        
        //1.15 余力再計算(補助口座 : 補助口座)
		WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount) l_subAccount;
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_genSubAccount);
        
        //1.16 createResponse( )
        WEB3AdminMutualTPACancelCompleteResponse l_response = 
            (WEB3AdminMutualTPACancelCompleteResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }  
}
@
