head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替取消サービスImplクラス(WEB3FEqConTransferCancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/23 周勇 (中訊) 新規作成       
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;
import java.util.Vector;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FEqConTransferDataControlService;
import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.define.WEB3AioFeqConOperationDivDef;
import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectResponse;
import webbroker3.aio.message.WEB3FEqConTransferUnit;
import webbroker3.aio.service.delegate.WEB3FEqConTransferCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株口座への振替取消サービスImpl)<BR>
 * 外株口座への振替取消サービス実装クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferCancelServiceImpl extends WEB3ClientRequestService 
    implements WEB3FEqConTransferCancelService 
{
    
    /**
     * @@roseuid 42355E710000
     */
    public WEB3FEqConTransferCancelServiceImpl() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCancelServiceImpl.class);

    
    /**
     * 外株口座への振替取消処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *   get選択画面()<BR>
     *   validate取消()<BR>
     *   submit取消()
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3955B02C9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if(l_request instanceof WEB3FEqConTransferCancelSelectRequest)
        {
            l_response = 
                this.getSelectScreen((WEB3FEqConTransferCancelSelectRequest) l_request);

        }
        else if(l_request instanceof WEB3FEqConTransferCancelConfirmRequest)
        {
            l_response = 
                this.validateCancel((WEB3FEqConTransferCancelConfirmRequest) l_request);
            
        }
        else if(l_request instanceof WEB3FEqConTransferCancelCompleteRequest)
        {
            l_response = 
                this.submitCancel((WEB3FEqConTransferCancelCompleteRequest) l_request);
            
        }
        else
        {
            log.debug(
                    "リクエストデータが"
                    + " WEB3FEqConTransferCancelSelectRequest "
                    + " と WEB3FEqConTransferCancelConfirmRequest"
                    + " と WEB3FEqConTransferCancelCompleteRequest以外である, but is "
                    + l_request.getClass().getName());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get選択画面)<BR>
     * 選択画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座への振替取消）get選択画面」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferCancelSelectResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E395970087
     */
    protected WEB3FEqConTransferCancelSelectResponse getSelectScreen(
        WEB3FEqConTransferCancelSelectRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "getSelectScreen(WEB3FEqConTransferCancelSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) get補助口座(SubAccountTypeEnum)
        //アイテムの定義
        //補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金） 
        SubAccount l_subAcccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        log.debug("AccountId = " + l_subAcccount.getAccountId());
        log.debug("SubAccountId = " + l_subAcccount.getSubAccountId());
        
        //1.2) validate注文(SubAccount)
        //アイテムの定義
        //以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等）
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.validateOrder(l_subAcccount);
        
        //1.3) validate外株振替可能(SubAccount)
        //アイテムの定義
        //外株の振替取引が可能であるかをチェックする。
        //[引数] 
        //補助口座： get補助口座()の戻り値
        l_aioOrderManager.validateFeqConTransferPossible(l_subAcccount);
        
        WEB3FEqConTransferCancelSelectResponse l_response = null;
        
        try
        {
            //1.4) getDefaultProcessor( )
            //1.5) doFindAllQuery(RowType, String, String, String, Object[], int, int)
            //アイテムの定義
            //注文単位テーブルから、取消可能な注文単位を取得する。
            //[条件] 
            //注文種別 = 1013（外国株式振替注文（預り金から外国株式口座へ）） 
            //受渡日 > 現在日付 
            //[引数] 
            //Rowタイプ： 注文単位Row.TYPE 
            //Where： "account_id=? and sub_account_id=? and order_type=? and delivery_date>?" 
            //orderBy： "received_date_time desc" 
            //condition： null 
            //リスト： 以下の要素の配列 
            // 補助口座.getAccountId()の戻り値 
            //補助口座.getSubAccountId()の戻り値 
            //1013（外国株式振替注文（預り金から外国株式口座へ）） 
            //現在日付
            Object[] l_objValue = 
                {
                    l_subAcccount.getAccountId() + "", 
                    l_subAcccount.getSubAccountId() + "", 
                    OrderTypeEnum.TRANSFER_TO_FEQ, 
                    GtlUtils.getSystemTimestamp()
                };
            
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    "account_id = ? and sub_account_id = ? and order_type = ? and delivery_date > ? ",
                    "received_date_time desc",
                    null,
                    l_objValue);
            log.debug("l_lisRows.size() = " + l_lisRows.size());
            
            //1.6)  ArrayList( )
            List l_lisFEqConTransferUnits = new Vector();
            
            //1.7) 取得した注文単位毎にLoop処理
            for(int i = 0; i < l_lisRows.size(); i++)
            {
                AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams) l_lisRows.get(i);
                
                // test log
                log.debug("取得した注文単位: l_aioOrderUnitParams[" + i + "] = " + l_aioOrderUnitParams);
                
                
                //1.7.1) get処理区分(注文単位Params)
                //アイテムの定義
                //処理区分を取得する。
                //[引数] 
                //注文単位Params： 注文単位Paramsオブジェクト 
                String l_strOperationDiv = this.getOperationDiv(l_aioOrderUnitParams);
                log.debug("l_strOperationDiv = " + l_strOperationDiv);
                
                //1.7.2) get処理区分()の戻り値 == ”振替中” or
                //get処理区分()の戻り値 == ”UWG決済中” の場合、実施
                if(WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv)
                    || WEB3AioFeqConOperationDivDef.UWG_TRANSFERING.equals(l_strOperationDiv))
                {
                    //1.7.2.1) 外株振替明細( )
                    WEB3FEqConTransferUnit l_fEqConTransferUnit = new WEB3FEqConTransferUnit();
                    
                    //1.7.2.2) プロパティセット
                    //(*2)以下のとおりに、プロパティをセットする。
                    //外株振替明細.注文ID = 注文単位.注文ID
                    l_fEqConTransferUnit.orderId = l_aioOrderUnitParams.getOrderId() + "";
                        
                    //外株振替明細.受付日時 = 注文単位.受注日時
                    l_fEqConTransferUnit.receptionDate = l_aioOrderUnitParams.getReceivedDateTime();
                    
                    //外株振替明細.振替金額 = 注文単位.注文数量
                    l_fEqConTransferUnit.changeAmt = WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getQuantity());
                    
                    //外株振替明細.受渡予定日 = 注文単位.受渡日
                    l_fEqConTransferUnit.deliveryScheduledDate = l_aioOrderUnitParams.getDeliveryDate();
                    
                    //外株振替明細.処理状況 = get処理区分()の戻り値
                    l_fEqConTransferUnit.transactionStateType = l_strOperationDiv;
                    
                    //外株振替明細.取消可能フラグ = （以下のとおり）
                    //   処理状況 == ”振替中” の場合、true
                    //   処理状況 != ”振替中” の、false
                    if(WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv))
                    {
                        l_fEqConTransferUnit.cancelFlag = true;
                    }
                    else
                    {
                        l_fEqConTransferUnit.cancelFlag = false;
                    }
                    
                    //1.7.2.3) add(arg0 : Object)
                    //[引数] 
                    //arg0： 外国株式振替明細オブジェクト 
                    l_lisFEqConTransferUnits.add(l_fEqConTransferUnit);

                }
            }
            
            //1.8) toArray( )
            //アイテムの定義
            //配列を取得する。
            WEB3FEqConTransferUnit[] l_fEqConTransferUnit = 
                new WEB3FEqConTransferUnit[l_lisFEqConTransferUnits.size()];
            
            l_lisFEqConTransferUnits.toArray(l_fEqConTransferUnit);
            
            //1.9) createResponse( )
            //アイテムの定義
            //レスポンスデータを生成する。
            l_response = (WEB3FEqConTransferCancelSelectResponse)l_request.createResponse();
            
            //1.10)プロパティセット
            //(*3)以下のとおりに、プロパティをセットする。
            //レスポンス.外国株式振替一覧 = 外国株式振
            l_response.fstkTransferList = l_fEqConTransferUnit;
        }
        catch (DataException l_ex)
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
        return l_response;
    }
    
    /**
     * (validate取消)<BR>
     * 振替の取消確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座への振替取消）validate取消」 参照
     * ------------------------------------------------
     * 1.6 get処理区分(注文単位Params)
     *  get処理区分の戻り値 != ”振替中”の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01955<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3959700A6
     */
    protected WEB3FEqConTransferCancelConfirmResponse validateCancel(
        WEB3FEqConTransferCancelConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateCancel(WEB3FEqConTransferCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) get補助口座(SubAccountTypeEnum)
        //アイテムの定義
        //補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金）
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3) validate注文(SubAccount)
        //アイテムの定義
        //以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等）
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4) validate外株振替可能(SubAccount)
        //アイテムの定義
        //外株の振替取引が可能であるかをチェックする。
        //[引数] 
        //補助口座： get補助口座()の戻り値
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);

        //1.5) getOrderUnits(SubAccount, FilterQueryParamsSpec, PaginationQueryParamsSpec, SortKeySpec)
        //アイテムの定義
        //取消対象となる注文単位を取得する。
        //※配列の先頭の要素を取得する。 
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        
        if (l_orderUnits.length <= 0)
        {
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        OrderUnit l_orderUnit = l_orderUnits[0];
        
        //1.6 get処理区分(注文単位Params)
        //アイテムの定義
        //処理区分を取得する。
        //[引数] 
        //注文単位Params： 注文単位.getDataSourceObject()の戻り値 
        AioOrderUnitParams l_aioOrderUnitParams = 
            (AioOrderUnitParams)l_orderUnit.getDataSourceObject();
        
        String l_strOperationDiv = this.getOperationDiv(l_aioOrderUnitParams);
        
        // get処理区分の戻り値 != ”振替中”
        //の場合、例外をスローする。
        if(!WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01955,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分[" + l_strOperationDiv + "] != 振替中");
        }
        
        //初回振替注文の
        //取消チェック
        //1.7)  validate外株振替取消(補助口座, String)
        //アイテムの定義
        //該当注文が取消可能かをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //識別コード： 注文単位.識別コード 
        l_aioOrderManager.validateFeqConTransferCancel(
            l_subAccount,
            l_aioOrderUnitParams.getOrderRequestNumber());

        //1.8) get外国株式顧客(String, String, String)
        //アイテムの定義
        //外国株式顧客オブジェクトを取得する。
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード： 補助口座.get取引店.getBranchCode() 
        //顧客コード： 補助口座.getMainAccount().getAccountCode() 
         
        WEB3FEqConTransferDataControlService l_fEqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);

        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = l_fEqConTransferDataControlService.getFeqAccountByAccountCode(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,      
                l_ex.getMessage(),
                l_ex);
        }
        //1.9) createResponse( )
        WEB3FEqConTransferCancelConfirmResponse l_response = 
            (WEB3FEqConTransferCancelConfirmResponse)l_request.createResponse();
        
        //1.10) プロパティセット
        //(*)以下のとおりに、プロパティをセットする。
        //レスポンス.取引口座番号 = 外国株式顧客.外国株式口座番号
        l_response.tradeAccountCode = l_feqAccountParams.getFeqAccountCode();
        
        //レスポンス.受付日時 = 注文単位.受注日時
        l_response.receptionDate = l_aioOrderUnitParams.getReceivedDateTime();
        
        //レスポンス.振替金額 = 注文単位.注文数量
        l_response.changeAmt = WEB3StringTypeUtility.formatNumber(l_aioOrderUnitParams.getQuantity());
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit取消)<BR>
     * 振替の取消完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（外株口座への振替取消）submit取消」 参照
     * ------------------------------------------------
     * 1.6 get処理区分(注文単位Params)
     *  get処理区分の戻り値 != ”振替中”の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01955<BR>
     * <BR>
     * ------------------------------------------------
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E3959700C6
     */
    protected WEB3FEqConTransferCancelCompleteResponse submitCancel(
        WEB3FEqConTransferCancelCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "submitCancel(WEB3FEqConTransferCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1)  validate( )
        //アイテムの定義
        //リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2) get補助口座(SubAccountTypeEnum)
        //アイテムの定義
        //補助口座オブジェクトを取得する。
        //[引数] 
        //補助口座タイプ： 1（株式取引口座（預り金）
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3) validate注文(SubAccount)
        //アイテムの定義
        //以下のチェックを行う。 
        //　@－受付時間チェック 
        //　@－システム停止中チェック 
        //　@－顧客のチェック（Ｙ客、管理ロック等）
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        
        l_aioOrderManager.validateOrder(l_subAccount);
        
        //1.4) validate外株振替可能(SubAccount)
        //アイテムの定義
        //外株の振替取引が可能であるかをチェックする。
        //[引数] 
        //補助口座： get補助口座()の戻り値
        l_aioOrderManager.validateFeqConTransferPossible(l_subAccount);

        //1.5) getOrderUnits(long)
        //アイテムの定義
        //取消される注文単位を取得する。
        //※配列の先頭の要素を取得する。
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        
        if (l_orderUnits.length <= 0)
        {
          throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80005,
              this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        OrderUnit l_orderUnit = l_orderUnits[0];
        
        //1.6 get処理区分(注文単位Params)
        //アイテムの定義
        //処理区分を取得する。
        //[引数] 
        //注文単位Params： 注文単位.getDataSourceObject()の戻り値 
        AioOrderUnitParams l_aioOrderUnitParams = (AioOrderUnitParams)l_orderUnit.getDataSourceObject();
        
        log.debug("submitCancel :: l_aioOrderUnitParams = " + l_aioOrderUnitParams);
        
        String l_strOperationDiv = this.getOperationDiv(l_aioOrderUnitParams);
        
        // get処理区分の戻り値 != ”振替中”
        //の場合、例外をスローする。
        if(!WEB3AioFeqConOperationDivDef.TRANSFERING.equals(l_strOperationDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01955,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分[" + l_strOperationDiv + "] != 振替中");
        }
        
        //初回振替注文の
        //取消チェック
        //1.7)  validate外株振替取消(補助口座, String)
        //アイテムの定義
        //該当注文が取消可能かをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //識別コード： 注文単位.識別コード 
        l_aioOrderManager.validateFeqConTransferCancel(
            l_subAccount,
            l_aioOrderUnitParams.getOrderRequestNumber());

        //1.8)  get代理入力者( )
        //アイテムの定義
        //代理入力者を取得する。
        Trader l_trader = this.getTrader();

        //1.9) validate取引パスワード(Trader, SubAccount, String)
        //アイテムの定義
        //暗証番号のチェックを行う。
        //[引数] 
        //代理入力者： get代理入力者()の戻り値 
        //補助口座： get補助口座()の戻り値 
        //パスワード： リクエストデータ.暗証番号 
        WEB3GentradeOrderValidator l_gentradeOrderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_gentradeOrderValidator.validateTradingPassword(
                l_trader, 
                l_subAccount, 
                l_request.password);
        
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("チェックエラーの場合はを例外をスローする" + 
                l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.10) getUWG振替状況(String, String, String)
        //アイテムの定義
        //取消された注文に関連するUWG振替状況のレコードを取得する。
        //[引数] 
        //証券会社コード： 補助口座.証券会社コード 
        //部店コード： 補助口座.get取引店.getBranchCode() 
        //識別コード： 注文単位.識別コード 
        WEB3FEqConTransferDataControlService l_fEqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        UwgTransferStatusParams l_uwgTransferStatusParams = 
            l_fEqConTransferDataControlService.getUwgTransferStatus(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_aioOrderUnitParams.getOrderRequestNumber());
        log.debug("l_uwgTransferStatusParams.getInstitutionCode() = " + l_uwgTransferStatusParams.getInstitutionCode());
        log.debug("l_uwgTransferStatusParams.getBranchCode() = " + l_uwgTransferStatusParams.getBranchCode());
        log.debug("l_uwgTransferStatusParams.getAccountCode() = " + l_uwgTransferStatusParams.getAccountCode());
        log.debug("l_uwgTransferStatusParams.getOrderRequestNumber() = " + l_uwgTransferStatusParams.getOrderRequestNumber());
        log.debug("l_uwgTransferStatusParams.getMrgTrnOrderRequestNumber() = " + l_uwgTransferStatusParams.getMrgTrnOrderRequestNumber());
        
        //1.11) 振替注文取消(String, String, String, String, String)
        //アイテムの定義
        //振替注文の取消を行う。
        //[引数] 
        //証券会社コード： UWG振替状況Params.証券会社コード 
        //部店コード： UWG振替状況Params.部店コード 
        //顧客コード： UWG振替状況Params.顧客コード 
        //識別コード： UWG振替状況Params.識別コード 
        //信用振替用識別コード： UWG振替状況Params.信用振替用識別コード 
        l_aioOrderManager.transferOrderCancel(
            l_uwgTransferStatusParams.getInstitutionCode(),
            l_uwgTransferStatusParams.getBranchCode(),
            l_uwgTransferStatusParams.getAccountCode(),
            l_uwgTransferStatusParams.getOrderRequestNumber(),
            l_uwgTransferStatusParams.getMrgTrnOrderRequestNumber());
        
        //1.12) updateUWG振替状況(UWG振替状況Params, String)
        //アイテムの定義
        //UWG振替状況を更新する。
        //[引数] 
        //UWG振替状況Params： getUWG振替状況()の戻り値 
        //更新後振替状況区分： 3（取消）
        log.debug("submitCancel :: l_uwgTransferStatusParams = " + l_uwgTransferStatusParams);
        l_fEqConTransferDataControlService.updateUwgTransferStatus(
            l_uwgTransferStatusParams,
            WEB3TransferStatusDivDef.CANCEL);
       
        //1.13) 余力再計算(補助口座 : 補助口座)
        //アイテムの定義
        //余力の再計算を行う。
        //[引数] 
        //補助口座： 補助口座オブジェクト 
        WEB3TPTradingPowerService l_service =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        
        WEB3GentradeSubAccount l_gentradeSubAccount = 
            (WEB3GentradeSubAccount)l_subAccount;
        
        l_service.reCalcTradingPower(l_gentradeSubAccount);
        
        //1.14) getOrderUnits(SubAccount, FilterQueryParamsSpec, PaginationQueryParamsSpec, SortKeySpec)
        //アイテムの定義
        //取消された注文単位を取得する。
        //※配列の先頭の要素を取得する。
        //[引数] 
        //注文ID： リクエストデータ.注文ID 
        OrderUnit[] l_updatedOrderUnits = l_aioOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
        OrderUnit l_updatedOrderUnit = l_updatedOrderUnits[0];
        
        AioOrderUnitParams l_updateAioOrderUnitParams = (AioOrderUnitParams)l_updatedOrderUnit.getDataSourceObject();
        log.debug("submitCancel :: l_updateAioOrderUnitParams = " + l_updateAioOrderUnitParams);
        
        //1.15) createResponse( )
        WEB3FEqConTransferCancelCompleteResponse l_response =
            (WEB3FEqConTransferCancelCompleteResponse)l_request.createResponse();
        
        //1.16) プロパティセット
        //(*)以下のとおりに、プロパティをセットする。
        //レスポンス.取消日時 = 注文単位.更新日付
        l_response.cancelDate = l_updateAioOrderUnitParams.getLastUpdatedTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get処理区分)<BR>
     * 振替明細にセットする処理区分を取得する。<BR>
     * <BR>
     * １）注文単位に対応するUWG振替状況を取得する。<BR>
     * <BR>
     *    外株振替連携データ制御サービスImpl.getUWG振替状況()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    証券会社コード： 引数.注文単位Params.部店IDから取得した証券会社コード<BR>
     *    部店コード： 引数.注文単位Params.部店IDから取得した部店コード<BR>
     *    識別コード： 引数.注文単位Params.識別コード<BR>
     * <BR>
     * ２）処理区分を判定する。<BR>
     * <BR>
     * ２－１）UWG振替状況.振替状況区分 == 0（決済中） and<BR>
     *          UWG振替状況.送受信区分 == 0（未送信） の場合<BR>
     * <BR>
     *    0（振替中） を返却する。<BR>
     * <BR>
     * ２－２）UWG振替状況.振替状況区分 == 0（決済中） and<BR>
     *          UWG振替状況.送受信区分 == 1（送信済） の場合<BR>
     * <BR>
     *    1（UWG決済中） を返却する。<BR>
     * <BR>
     * ２－３）UWG振替状況.振替状況区分 == 1（決済完了） の場合<BR>
     * <BR>
     *    2（UWG決済完了） を返却する。<BR>
     * <BR>
     * ２－４）UWG振替状況.振替状況区分 == 2（決済エラー） の場合<BR>
     * <BR>
     *    3（UWG決済エラー） を返却する。<BR>
     * <BR>
     * ２－５）UWG振替状況.振替状況区分 == 3（取消） の場合<BR>
     * <BR>
     *    4（取消済） を返却する。
     * @@param l_orderUnitParams - 注文単位Paramsオブジェクト
     * 
     * @@return String
     * @@roseuid 41ECE2D900AB
     */
    protected String getOperationDiv(AioOrderUnitParams l_orderUnitParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getOperationDiv(AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        //１）注文単位に対応するUWG振替状況を取得する。
        //外株振替連携データ制御サービスImpl.getUWG振替状況()をコールする。
        //[引数] 
        //証券会社コード： 引数.注文単位Params.部店IDから取得した証券会社コード 
        //部店コード： 引数.注文単位Params.部店IDから取得した部店コード 
        //識別コード： 引数.注文単位Params.識別コード 
        WEB3FEqConTransferDataControlService l_fEqConTransferDataControlService = 
            (WEB3FEqConTransferDataControlService) Services.getService(
                WEB3FEqConTransferDataControlService.class);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        Branch l_branch = null;
        UwgTransferStatusParams l_uwgTransferStatusParams = null;
        try
        {
            l_branch = l_finApp.getAccountManager().getBranch(l_orderUnitParams.getBranchId());
            
            log.debug("getOperationDiv :: l_orderUnitParams = " + l_orderUnitParams);
            l_uwgTransferStatusParams = 
                l_fEqConTransferDataControlService.getUwgTransferStatus(
                    l_branch.getInstitution().getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_orderUnitParams.getOrderRequestNumber());
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,      
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("l_uwgTransferStatusParams = " + l_uwgTransferStatusParams);
        
        //２）処理区分を判定する。 
        //２－１）UWG振替状況.振替状況区分 == 0（決済中） and 
        //UWG振替状況.送受信区分 == 0（未送信） の場合
        //0（振替中） を返却する。 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_uwgTransferStatusParams.getTransferStatusDiv())
            && WEB3SendRcvDivDef.NOT_SEND.equals(l_uwgTransferStatusParams.getSendRcvDiv()))
        {
            log.debug("************entry 2-1)");
            return WEB3AioFeqConOperationDivDef.TRANSFERING;
        }
        
        //２－２）UWG振替状況.振替状況区分 == 0（決済中） and 
        //UWG振替状況.送受信区分 == 1（送信済） の場合
        //1（UWG決済中） を返却する。 
        if(WEB3TransferStatusDivDef.PROCESSING.equals(l_uwgTransferStatusParams.getTransferStatusDiv())
            && WEB3SendRcvDivDef.SEND_COMPLETE.equals(l_uwgTransferStatusParams.getSendRcvDiv()))
        {
            log.debug("************entry 2-2)");
            return WEB3AioFeqConOperationDivDef.UWG_TRANSFERING;
        }
        
        //２－３）UWG振替状況.振替状況区分 == 1（決済完了） の場合
        //2（UWG決済完了） を返却する。 
        if(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(l_uwgTransferStatusParams.getTransferStatusDiv()))
        {
            return WEB3AioFeqConOperationDivDef.UWG_TRANSFER_COMPLETE;
        }

        //２－４）UWG振替状況.振替状況区分 == 2（決済エラー） の場合
        //3（UWG決済エラー） を返却する。 
        if(WEB3TransferStatusDivDef.PROCESS_ERROR.equals(l_uwgTransferStatusParams.getTransferStatusDiv()))
        {
            return WEB3AioFeqConOperationDivDef.UWG_TRANSFER_ERROE;
        }

        
        //２－５）UWG振替状況.振替状況区分 == 3（取消） の場合 
        //4（取消済） を返却する。 
        if(WEB3TransferStatusDivDef.CANCEL.equals(l_uwgTransferStatusParams.getTransferStatusDiv()))
        {
            return WEB3AioFeqConOperationDivDef.CANCELED;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
