head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqRcvdQueueReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式RCVDキュー照会サービスImpl(WEB3AdminFeqRcvdQueueReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 徐大方 (中訊) 新規作成
                   2006/10/18 徐大方(中訊) モデル No.293対応
                   2006/11/20 徐大方 (中訊) モデル No.302対応
                   2006/11/22 徐大方 (中訊) モデル No.305対応
                   2006/12/25 李　@俊 (中訊) モデル No.325対応
                   2007/01/15 徐大方 (中訊) モデル No.331対応
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.472 （実装）No.026
Revesion History : 2009/08/03 武　@波(中訊) 【外国株式】仕様変更管理台帳（モデル）No.503
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceResponse;
import webbroker3.feq.message.WEB3FeqRcvdQueueInfo;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqRcvdQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式RCVDキュー照会サービスImpl)<BR>
 * 管理者外国株式RCVDキュー照会サービス実装クラス<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminFeqRcvdQueueReferenceServiceImpl implements WEB3AdminFeqRcvdQueueReferenceService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqRcvdQueueReferenceServiceImpl.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3AdminFeqRcvdQueueReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 管理者外国株式RCVDキュー照会処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －get入力画面()<BR>
     * －get一覧画面()<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqRcvdQueueReferenceInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqRcvdQueueReferenceInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqRcvdQueueReferenceRequest)
        {
            //get一覧画面
            l_response = 
                this.getListScreen((WEB3AdminFeqRcvdQueueReferenceRequest)l_request);
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
     * 管理者外国株式RCVDキュー照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式RCVDキュー照会サービス)get入力画面」参照<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFeqRcvdQueueReferenceInputResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    protected WEB3AdminFeqRcvdQueueReferenceInputResponse getInputScreen(
        WEB3AdminFeqRcvdQueueReferenceInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqRcvdQueueReferenceInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 getInstanceFromログイン情報( )
        //ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者権限チェックを行う。 
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外株（注文約定管理） 
        //is更新：　@false(更新なし) 
        //※機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
        
        //1.3 createResponse( )
        WEB3AdminFeqRcvdQueueReferenceInputResponse l_response = 
            (WEB3AdminFeqRcvdQueueReferenceInputResponse)l_request.createResponse();
        
        //1.4 プロパティセット
        //更新者コード: 管理者.get管理者コード()の戻り値
        l_response.updaterCode = l_admin.getAdministratorCode();
        
        //処理区分一覧
        l_response.transactionDivList = new String[] {
            SleRcvdqProcStatusEnum.TODO.intValue() + "",
            SleRcvdqProcStatusEnum.PROCESSED.intValue() + "",
            SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "",
            SleRcvdqProcStatusEnum.SKIP_PROCESSING_IGNORE.intValue() + "",
            SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR.intValue() + ""};
        
        //経路区分一覧
        l_response.orderRootDivList = new String[] {
            WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY,
            WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD,
            WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT,
            WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD};
        
        //受付区分一覧
        l_response.acceptDivList = new String[] {
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE,
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR,
            WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL,
            WEB3FeqAcceptTypeDef.CHANGED,
            WEB3FeqAcceptTypeDef.CHANGE_ERROR,
            WEB3FeqAcceptTypeDef.CANCEL,
            WEB3FeqAcceptTypeDef.CANCEL_ERROR,
            WEB3FeqAcceptTypeDef.NOT_EXECUTED};
        
        log.exiting(STR_METHOD_NAME);
        return l_response;  
    }
    
    /**
     * (get一覧画面)<BR>
     * 管理者外国株式RCVDキュー照会一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式RCVDキュー照会サービス)get一覧画面」参照<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFeqRcvdQueueReferenceResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    protected WEB3AdminFeqRcvdQueueReferenceResponse getListScreen(
        WEB3AdminFeqRcvdQueueReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqRcvdQueueReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate()
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();  

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //管理者権限チェックを行う。 
        //[validate権限()に指定する引数] 
        //機@能カテゴリコード：　@機@能カテゴリコード.外株（注文約定管理） 
        //is更新：　@false(更新なし) 
        //※機@能カテゴリコードは、DBレイアウト「管理者権限テーブル.xls#（補足資料）機@能カテゴリコード一覧」参照。
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get運用コード(証券会社コード : String, 運用コード : String)
        //証券会社コード： get証券会社コード（）の戻り値
        //運用コード：リクエストデータ.運用コード
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.5 create検索条件文字列
        //検索条件文字列を作成する。 
        //[create検索条件文字列()に指定する引数] 
        //証券会社コード： 取得した証券会社コード 
        //更新者コード：リクエスト.更新者コード 
        //処理区分： リクエスト.処理区分 
        //運用コード： リクエスト.運用コード 
        //部店コード： リクエスト.部店コード 
        //顧客コード： リクエスト.顧客コード 
        //経路区分： リクエスト.経路区分 
        //受付区分： リクエスト.受付区分
        String l_strQueryString = this.createQueryString(
            l_request.updaterCode,
            l_request.transactionDiv,
            l_request.managementCode,
            l_request.branchCode,
            l_request.accountCode,
            l_request.orderRootDiv,
            l_request.acceptDiv);
        
        //1.6 create検索条件データコンテナ
        //検索条件データコンテナを作成する。 
        //[create検索条件データコンテナ()に指定する引数] 
        //証券会社コード： 取得した証券会社コード 
        //更新者コード：リクエスト.更新者コード 
        //処理区分： リクエスト.処理区分 
        //運用コード： get運用コード（）の戻り値 
        //部店コード： リクエスト.部店コード 
        //顧客コード： リクエスト.顧客コード 
        //経路区分：リクエスト.経路区分 
        //受付区分：リクエスト.受付区分

        Object[] l_objQueryDataContainer = this.createQueryDataContainer(
            l_strInstitutionCode,
            l_request.updaterCode,
            l_request.transactionDiv,
            l_strEmpCode,
            l_request.branchCode,
            l_request.accountCode,
            l_request.orderRootDiv,
            l_request.acceptDiv);
        
        //1.7 createソート条件
        //ソート条件を作成する。 
        //[createソート条件()に指定する引数] 
        //ソートキー： リクエスト.ソートキー
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.8 doFindAllQuery
        //条件に該当する(外国株取引)RCVD_Qテーブルのリストを取得する。 
        //[doFindAllQuery件()に指定する引数] 
        //行タイプ： (外国株取引)RCVD_QテーブルRow.TYPE 
        //検索条件文字列： 生成した検索条件文字列 
        //ソート条件： 生成したソート条件 
        //コンディション： null 
        //検索条件データコンテナ： 生成した検索条件データコンテナ
        List l_lisRecord = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecord = l_queryProcessor.doFindAllQuery(
                SleRcvdQRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_objQueryDataContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
        
        //1.9 createResponse()
        WEB3AdminFeqRcvdQueueReferenceResponse l_response = 
            (WEB3AdminFeqRcvdQueueReferenceResponse)l_request.createResponse();
        
        //1.10 レコードが取得できた場合(*1)
        if (l_lisRecord != null && !l_lisRecord.isEmpty())
        {
            //1.10.1 WEB3PageIndexInfo(List, int, int)
            //WEB3PageIndexInfoオブジェクトを生成する。 
            //[コンストラクタに指定する引数] 
            //明細リスト： 取得した(外国株取引)RCVD_Qテーブルのリスト 
            //要求ページ番号： リクエスト.要求ページ番号 
            //要求ページ内表示行数： リクエスト.ページ内表示行数
            WEB3PageIndexInfo l_pageIndexInfo = 
                new WEB3PageIndexInfo(
                    l_lisRecord, 
                    Integer.parseInt(l_request.pageIndex), 
                    Integer.parseInt(l_request.pageSize));
                       
            //1.10.2 getArrayReturned
            //表示対象の(外国株取引)RCVD_QテーブルParamsの配列を取得する。  
            //[getArrayReturned()に指定する引数]  
            //class：　@(外国株取引)RCVD_QテーブルParams.class
            SleRcvdQParams[] l_sleRcvdQParams = 
                (SleRcvdQParams[])l_pageIndexInfo.getArrayReturned(SleRcvdQParams.class);
            
            //1.10.3 取得した表示対象の(外国株取引)RCVD_QテーブルParamsの配列の件数分、処理を繰り返す。
            int l_intRcvdQParamsLength = 0;
            if (l_sleRcvdQParams != null && l_sleRcvdQParams.length > 0)
            {
                l_intRcvdQParamsLength = l_sleRcvdQParams.length;
            }
            List l_lisRcvdQueueInfo = new ArrayList();
            for (int i = 0; i < l_intRcvdQParamsLength; i++)
            {
                //1.10.3.1 外国株式RCVDキュー情報()
                WEB3FeqRcvdQueueInfo l_rcvdQueueInfo = new WEB3FeqRcvdQueueInfo();
                
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accMgr = 
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                if (l_accMgr == null)
                {
                    log.debug("拡張アカウントマネージャが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "拡張アカウントマネージャが存在しない。");
                }
                WEB3GentradeMainAccount l_mainAccount = null;
                try
                {
                    l_mainAccount = (WEB3GentradeMainAccount)
                        l_accMgr.getMainAccount(l_sleRcvdQParams[i].getAccountId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 

                //1.10.3.2
                //生成した外國株式RCVDキュー情報オブジェクトに(外國株取引)RCVD_QテーブルParamsの項目値を設定する。
                FeqOrderUnitRow l_feqOrderUnitRow = null;
                FeqProductRow l_feqProductRow = null;
                try
                {
                    List l_lstFeqOrderUnit =
                        FeqOrderUnitDao.findRowsByOrderId(Long.parseLong(l_sleRcvdQParams[i].getInternalRef()));
                    l_feqOrderUnitRow = (FeqOrderUnitRow)l_lstFeqOrderUnit.get(0);
                    l_feqProductRow = FeqProductDao.findRowByPk(l_feqOrderUnitRow.getProductId());
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

                //顧客コード：RCVD_QテーブルParams同項目　@上６桁を設定（下１桁はチェックディジットの為削除）。
                l_rcvdQueueInfo.accountCode = l_mainAccount.getAccountCode().substring(0, 6);

                //RCVD_QテーブルParamsの経路区分が注文受付取消認証又は出来入力又は注文受付結果一括入力又は約定結果一括入力の時
                if (WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_CANCEL_AUTHENTICATE.equals(l_sleRcvdQParams[i].getRouteDiv())
                    || WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_sleRcvdQParams[i].getRouteDiv())
                    || WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD.equals(l_sleRcvdQParams[i].getRouteDiv())
                    || WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_sleRcvdQParams[i].getRouteDiv()))
                {
                    //売買区分：注文単位.注文種別==701の時　@１
                    if (OrderTypeEnum.FEQ_BUY.equals(l_feqOrderUnitRow.getOrderType()))
                    {
                        l_rcvdQueueInfo.dealingType = WEB3BuySellTypeDef.BUY;
                    }
                    //注文単位.注文種別==702の時　@２
                    else if (OrderTypeEnum.FEQ_SELL.equals(l_feqOrderUnitRow.getOrderType()))
                    {
                        l_rcvdQueueInfo.dealingType = WEB3BuySellTypeDef.SELL;
                    }
                }
                //上記以外
                else
                {
                    //売買区分：RCVD_QテーブルParams同項目を設定。
                    if (!l_sleRcvdQParams[i].getSideIsNull())
                    {
                        l_rcvdQueueInfo.dealingType = l_sleRcvdQParams[i].getSide() + "";
                    }
                }
                //銘柄コード：注文単位.銘柄IDに該当する銘柄.銘柄コードを設定。
                l_rcvdQueueInfo.productCode = l_feqProductRow.getProductCode();
                //上記以外はRCVD_QテーブルParamsの同項目
                l_rcvdQueueInfo.acceptDiv = l_sleRcvdQParams[i].getAcceptDiv();
                l_rcvdQueueInfo.branchCode = l_sleRcvdQParams[i].getBranchCode();
                l_rcvdQueueInfo.createTimeStamp = l_sleRcvdQParams[i].getCreatedTimestamp();
                if (!l_sleRcvdQParams[i].getFxRateIsNull())
                {
                    l_rcvdQueueInfo.exchangeRate =
                        WEB3StringTypeUtility.formatNumber(l_sleRcvdQParams[i].getFxRate());
                }
                if (!l_sleRcvdQParams[i].getExecPriceIsNull())
                {
                    l_rcvdQueueInfo.execPrice =
                        WEB3StringTypeUtility.formatNumber(l_sleRcvdQParams[i].getExecPrice());
                }
                if (!l_sleRcvdQParams[i].getExecQtyIsNull())
                {
                    l_rcvdQueueInfo.execQuantity =
                        WEB3StringTypeUtility.formatNumber(l_sleRcvdQParams[i].getExecQty());
                }
                l_rcvdQueueInfo.executeNo = l_sleRcvdQParams[i].getExecSerialNo();
                l_rcvdQueueInfo.executionDate = l_sleRcvdQParams[i].getExecutionDate();
                l_rcvdQueueInfo.executionTimestamp = l_sleRcvdQParams[i].getExecTimestamp();
                l_rcvdQueueInfo.localDeliveryDate = l_sleRcvdQParams[i].getFDeliveryDate();
                l_rcvdQueueInfo.managementCode = l_sleRcvdQParams[i].getOrderEmpCode();
                l_rcvdQueueInfo.orderId = l_sleRcvdQParams[i].getInternalRef();
                l_rcvdQueueInfo.orderRootDiv = l_sleRcvdQParams[i].getRouteDiv();
                l_rcvdQueueInfo.transactionDiv = l_sleRcvdQParams[i].getStatus().intValue() + "";
                l_rcvdQueueInfo.updaterCode = l_sleRcvdQParams[i].getLastUpdater();
                l_rcvdQueueInfo.updateTimeStamp = l_sleRcvdQParams[i].getLastUpdatedTimestamp();

                //識別コード
                l_rcvdQueueInfo.requestNumber = l_sleRcvdQParams[i].getOrderRequestNumber();

                //拒否原因コード
                l_rcvdQueueInfo.rejectCauseCode = l_sleRcvdQParams[i].getRejectCode();

                l_lisRcvdQueueInfo.add(l_rcvdQueueInfo);
            }
     
            WEB3FeqRcvdQueueInfo[] l_rcvdQueueInfos = new WEB3FeqRcvdQueueInfo[l_lisRcvdQueueInfo.size()];
            l_lisRcvdQueueInfo.toArray(l_rcvdQueueInfos);
            //1.10.4 getTotalPages()
            int l_intTotalPages = l_pageIndexInfo.getTotalPages();
            
            //1.10.5 getTotalRecords()
            int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();            
            
            //1.10.6 getPageIndex()
            int l_intPageIndex = l_pageIndexInfo.getPageIndex();
            
            //1.10.7 プロパティセット
            l_response.feqRcvdQueueInfoList = l_rcvdQueueInfos;
            l_response.totalPages = l_intTotalPages + "";
            l_response.totalRecords = l_intTotalRecords + "";
            l_response.pageIndex = l_intPageIndex + "";      
        }
        else
        {
            //1.11 レコードが取得できなかった場合(*2)
            l_response.feqRcvdQueueInfoList = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";  
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * １）ソート条件文字列(：String)を作成する。<BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@２－１）ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「更新者コード」 → (外国株取引)RCVD_Qテーブル.更新者コード<BR>
     * 　@　@・「運用コード」 → (外国株取引)RCVD_Qテーブル.運用コード<BR>
     * 　@　@・「部店コード」 → (外国株取引)RCVD_Qテーブル.部店コード <BR>
     * 　@　@・「顧客コード」 → substr((外国株取引)RCVD_Qテーブル.アカウントID, 9, 6)<BR>
     * 　@　@・「経路区分」　@→　@(外国株取引)RCVD_Qテーブル.経路区分<BR>
     * 　@　@・「受付区分」　@→　@(外国株取引)RCVD_Qテーブル.受付区分<BR>
     * 　@　@・「処理区分」 → (外国株取引)RCVD_Qテーブル.処理区分<BR>
     * 　@　@・「作成日付」 → (外国株取引)RCVD_Qテーブル.作成日付<BR>
     * 　@　@・「更新日付」 → (外国株取引)RCVD_Qテーブル.更新日付<BR>
     * <BR>
     * 　@２－２）ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ３）ソート条件末尾に、(外国株取引)RCVD_Qテーブル.更新日付を昇順指定で付加<BR>
     * 　@※キー項目に「更新日付」が指定されている場合は付加しない。<BR>
     * <BR>
     * ４）作成したソート条件文字列を返却する。<BR>
     * <BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3ForeignSortKey[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //１）ソート条件文字列(：String)を作成する。
        StringBuffer l_sortCond = new StringBuffer();
        
        //２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        int l_intSortKeysLength = l_sortKeys.length;
        boolean l_blnDateStatus = true;
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            //更新者コード
            if (WEB3FeqSortKeyItemNameDef.UPDATER_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" last_updater ASC ");
                }
                else
                {
                    l_sortCond.append(" last_updater DESC ");
                }
            }
            //運用コード
            else if (WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" order_emp_code ASC ");
                }
                else
                {
                    l_sortCond.append(" order_emp_code DESC ");
                }
            }
            //部店コード
            else if (WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" branch_code ASC ");
                }
                else
                {
                    l_sortCond.append(" branch_code DESC ");
                }
            }
            //顧客コード
            else if (WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" substr(account_id, 9, 6) ASC ");
                }
                else
                {
                    l_sortCond.append(" substr(account_id, 9, 6) DESC ");
                }
            }
            //経路区分
            else if (WEB3FeqSortKeyItemNameDef.ORDER_ROOT_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" route_div ASC ");
                }
                else
                {
                    l_sortCond.append(" route_div DESC ");
                }
            }
            //受付区分
            else if (WEB3FeqSortKeyItemNameDef.ACCEPT_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" accept_div ASC ");
                }
                else
                {
                    l_sortCond.append(" accept_div DESC ");
                }
            }
            //処理区分
            else if (WEB3FeqSortKeyItemNameDef.TRANSACTION_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" status ASC ");
                }
                else
                {
                    l_sortCond.append(" status DESC ");
                }
            }
            //作成日付
            else if (WEB3FeqSortKeyItemNameDef.CREATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" created_timestamp ASC ");
                }
                else
                {
                    l_sortCond.append(" created_timestamp DESC ");
                }
            }
            //更新日付
            else if (WEB3FeqSortKeyItemNameDef.LAST_UPDATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                l_blnDateStatus = false;
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_sortCond.append(" last_updated_timestamp ASC ");
                }
                else
                {
                    l_sortCond.append(" last_updated_timestamp DESC ");
                }
            }
            if (i < l_sortKeys.length - 1)
            {
                l_sortCond.append(" , ");
            }
        }
        if (l_blnDateStatus)
        {
            if (l_sortCond.length() == 0)
            {
                l_sortCond.append(" last_updated_timestamp ASC ");
            }
            else
            {           
                l_sortCond.append(" , last_updated_timestamp ASC ");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_sortCond.toString();
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）証券会社コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "institution_code = ? "<BR>
     * <BR>
     * ３）パラメータ.更新者コード != nullの場合、<BR>
     * 　@更新者コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and last_updater = ? "<BR>
     * <BR>
     * ４）パラメータ.処理区分 != nullの場合、<BR>
     * 　@処理区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and status = ? "<BR>
     * <BR>
     * ５）パラメータ.運用コード != nullの場合、<BR>
     * 　@運用コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and order_emp_code = ? "<BR>
     * <BR>
     * ６）パラメータ.部店コード != nullの場合、<BR>
     * 　@部店コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and branch_code = ? "<BR>
     * <BR>
     * ７）パラメータ.顧客コード != nullの場合、<BR>
     * 　@顧客コード条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and substr(account_id, 9, 6) = ? "<BR>
     * <BR>
     * ８）パラメータ.経路区分 != nullの場合、<BR>
     * 　@経路区分を検索条件文字列に追加する。<BR>  
     * <BR>
     * 　@検索条件文字列 += "and route_div = ? "<BR>
     * <BR>
     * ９）パラメータ.受付区分 != nullの場合、<BR>
     * 　@受付区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and accept_div = ? "<BR>
     * <BR>
     * １０）パラメータ.経路区分 == nullの場合、 <BR>
     * 　@経路区分を検索条件文字列に追加する。 <BR>
     * <BR>
     * 　@検索条件文字列 += "and route_div is not null " <BR>
     * <BR>
     * <BR>
     * １１）作成した検索条件文字列を返却する。<BR>
     * <BR> 
     * @@param l_StrUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_StrStatus - (処理区分)<BR>
     * 処理区分<BR>
     * @@param l_StrOrderEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@param l_StrBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_StrCustomerCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_StrOrderRootDiv - (経路区分)<BR>
     * 経路区分<BR>
     * @@param l_StrAcceptDiv - (受付区分)<BR>
     * 受付区分<BR>
     * @@return String
     * @@roseuid 4214980A032E
     */
    protected String createQueryString(
        String l_StrUpdaterCode, 
        String l_StrStatus, 
        String l_StrOrderEmpCode,
        String l_StrBranchCode,
        String l_StrCustomerCode,
        String l_StrOrderRootDiv,
        String l_StrAcceptDiv) 
    {
        final String STR_METHOD_NAME = 
            "createQueryString(String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）検索条件文字列インスタンス(：String)を生成する。 
        String l_strQueryString = "";
        
        //２）証券会社コードを検索条件文字列に追加する。 
        //検索条件文字列 += "institution_code = ? " 
        l_strQueryString = l_strQueryString + " institution_code = ? ";
        
        //３）パラメータ.更新者コード != nullの場合、 
        //更新者コードを検索条件文字列に追加する。 
        //検索条件文字列 += "and last_updater = ? "
        if (l_StrUpdaterCode != null)
        {
            l_strQueryString = l_strQueryString + " and last_updater = ? ";
        }
        
        //４）パラメータ.処理区分 != nullの場合、 
        //処理区分を検索条件文字列に追加する。 
        //検索条件文字列 += "and status = ? "
        if (l_StrStatus != null)
        {
            l_strQueryString = l_strQueryString + " and status = ? ";
        }
        
        //５）パラメータ.運用コード != nullの場合、 
        //運用コードを検索条件文字列に追加する。 
        //検索条件文字列 += "and order_emp_code = ? "
        if (l_StrOrderEmpCode != null)
        {
            l_strQueryString = l_strQueryString + " and order_emp_code = ? ";
        }
        
        //６）パラメータ.部店コード != nullの場合、 
        //部店コードを検索条件文字列に追加する。 
        //検索条件文字列 += "and branch_code = ? " 
        if (l_StrBranchCode != null)
        {
            l_strQueryString = l_strQueryString + " and branch_code = ? ";
        }
        
        //７）パラメータ.顧客コード != nullの場合、 
        //顧客コード条件を検索条件文字列に追加する。 
        //検索条件文字列 += "and substr(account_id, 9, 6) = ? " 
        if (l_StrCustomerCode != null)
        {
            l_strQueryString = l_strQueryString + " and substr(account_id, 9, 6) = ? ";
        }
        
        //８）パラメータ.経路区分 != nullの場合、 
        //経路区分を検索条件文字列に追加する。 
        //検索条件文字列 += "and route_div = ? " 
        if (l_StrOrderRootDiv != null)
        {
            l_strQueryString = l_strQueryString + " and route_div = ? ";
        }
        
        //９）パラメータ.受付区分 != nullの場合、 
        //受付区分を検索条件文字列に追加する。 
        //検索条件文字列 += "and accept_div = ? " 
        if (l_StrAcceptDiv != null)
        {
            l_strQueryString = l_strQueryString + " and accept_div = ? ";
        }
        
        //１０）パラメータ.経路区分 == nullの場合、 
        //経路区分を検索条件文字列に追加する。 
        //検索条件文字列 += "and route_div is not null " 
        if (l_StrOrderRootDiv == null)
        {
            l_strQueryString = l_strQueryString + " and route_div is not null ";
        }

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメタ.証券会社コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ３）パラメータ.更新者コード != nullの場合、<BR>
     * 　@パラメータ.更新者コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ４）パラメータ.処理区分 != nullの場合、<BR>
     * 　@パラメータ.処理区分を生成したArrayListに追加する。<BR>
     * <BR>
     * ５）パラメータ.運用コード != nullの場合、<BR>
     * 　@パラメータ.運用コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ６）パラメータ.部店コード != nullの場合、<BR>
     * 　@パラメータ.部店コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ７）パラメータ.顧客コード != nullの場合、<BR>
     * 　@パラメータ.顧客コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ８）パラメータ.経路区分 != nullの場合、<BR>
     * 　@パラメータ.経路区分を生成したArrayListに追加する。<BR>
     * <BR>
     * ９）パラメータ.受付区分 != nullの場合、<BR>
     * 　@パラメータ.受付区分を生成したArrayListに追加する。<BR>
     * <BR>
     * １０）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_StrInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_StrUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_StrStatus - (処理区分)<BR>
     * 処理区分<BR>
     * @@param l_StrOrderEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@param l_StrBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_StrCustomerCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_StrOrderRootDiv - (経路区分)<BR>
     * 経路区分<BR>
     * @@param l_StrAcceptDiv - (受付区分)<BR>
     * 受付区分<BR>
     * @@return Object[]
     * @@roseuid 4214980A032E
     */
    protected Object[] createQueryDataContainer(
        String l_StrInstitutionCode, 
        String l_StrUpdaterCode, 
        String l_StrStatus, 
        String l_StrOrderEmpCode,
        String l_StrBranchCode,
        String l_StrCustomerCode,
        String l_StrOrderRootDiv,
        String l_StrAcceptDiv)
    {
        final String STR_METHOD_NAME = 
            "createQueryDataContainer(String, String, String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）ArrayListを生成する。 
        ArrayList l_arrayList = new ArrayList();
        
        //２）パラメタ.証券会社コードを生成したArrayListに追加する。 
        l_arrayList.add(l_StrInstitutionCode);
        
        //３）パラメータ.更新者コード != nullの場合、 
        //パラメータ.更新者コードを生成したArrayListに追加する。 
        if (l_StrUpdaterCode != null)
        {
            l_arrayList.add(l_StrUpdaterCode);
        }
        
        //４）パラメータ.処理区分 != nullの場合、 
        //パラメータ.処理区分を生成したArrayListに追加する。 
        if (l_StrStatus != null)
        {
            l_arrayList.add(l_StrStatus);
        }
        
        //５）パラメータ.運用コード != nullの場合、 
        //パラメータ.運用コードを生成したArrayListに追加する。 
        if (l_StrOrderEmpCode != null)
        {
            l_arrayList.add(l_StrOrderEmpCode);
        }
        
        //６）パラメータ.部店コード != nullの場合、 
        //パラメータ.部店コードを生成したArrayListに追加する。 
        if (l_StrBranchCode != null)
        {
            l_arrayList.add(l_StrBranchCode);
        }
        
        //７）パラメータ.顧客コード != nullの場合、 
        //パラメータ.顧客コードを生成したArrayListに追加する。 
        if (l_StrCustomerCode != null)
        {
            l_arrayList.add(l_StrCustomerCode);
        }
        
        //８）パラメータ.経路区分 != nullの場合、 
        //パラメータ.経路区分を生成したArrayListに追加する。 
        if (l_StrOrderRootDiv != null)
        {
            l_arrayList.add(l_StrOrderRootDiv);
        }
        
        //９）パラメータ.受付区分 != nullの場合、 
        //パラメータ.受付区分を生成したArrayListに追加する。 
        if (l_StrAcceptDiv != null)
        {
            l_arrayList.add(l_StrAcceptDiv);
        }
    
        //１０）生成したArrayList.toArray()の戻り値を返却する
        Object[] l_objQueryDataContainer = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_objQueryDataContainer);
        
        log.exiting(STR_METHOD_NAME);
        return l_objQueryDataContainer;
    }
}
@
