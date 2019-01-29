head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptResultUploadServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付結果ｱｯﾌﾟﾛｰﾄﾞサービスImpl(WEB3AdminFeqOrderAcceptResultUploadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 鄭海良(中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
                   2006/09/18 徐大方(中訊) 仕様変更・モデル245 
Revesion History : 2009/08/03 車進(中訊) 仕様変更・モデル515,522
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderAcceptResultUploadCSV;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptResultUploadService;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.slebase.data.SleRcvdQDao;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式注文受付結果ｱｯﾌﾟﾛｰﾄﾞサービスImpl)<BR>
 * 管理者外国株式注文受付結果ｱｯﾌﾟﾛｰﾄﾞサービス実装クラス<BR>
 * <BR>
 * 管理者外国株式アップロードサービスインタセプタをPluginする。<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptResultUploadServiceImpl implements WEB3AdminFeqOrderAcceptResultUploadService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptResultUploadServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F1031C
     */
    public WEB3AdminFeqOrderAcceptResultUploadServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式注文受付結果アップロード処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －getアップロード画面()<BR>
     * －validateアップロードファ@イル()<BR>
     * －submitアップロードファ@イル()<BR>
     * －undoアップロードファ@イル()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD3140158
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
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
        if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadInputRequest)
        {
            //getアップロード画面()
            l_response = 
                this.getUploadScreen((WEB3AdminFeqOrderAcceptResultUploadInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadConfirmRequest)
        {
            //validateアップロード()
            l_response = 
                this.validateUpload((WEB3AdminFeqOrderAcceptResultUploadConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadCompleteRequest)
        {
            //submitアップロード()
            l_response = 
                this.submitUpload((WEB3AdminFeqOrderAcceptResultUploadCompleteRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptResultUploadStopRequest)
        {
            //undoアップロード()
            l_response = 
                this.undoUpload((WEB3AdminFeqOrderAcceptResultUploadStopRequest)l_request);
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
     * (getアップロード画面)<BR>
     * アップロード画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)受付一括入力）getアップロード画面」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式注文受付結果アップロード入力リクエストリクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD3140167
     */
    protected WEB3AdminFeqOrderAcceptResultUploadInputResponse getUploadScreen(
        WEB3AdminFeqOrderAcceptResultUploadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getUploadScreen(WEB3AdminFeqOrderAcceptResultUploadInputRequest )";
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

        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadInputResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateアップロード)<BR>
     * アップロードファ@イル確認処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)受付一括入力）validateアップロード」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式注文受付結果アップロード確認リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD3140169
     */
    protected WEB3AdminFeqOrderAcceptResultUploadConfirmResponse validateUpload(
        WEB3AdminFeqOrderAcceptResultUploadConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpload(WEB3AdminFeqOrderAcceptResultUploadConfirmRequest )";
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

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException

        //証券会社コードを取得する。
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //外国株式運用コード採番区分を取得する。
        //[getPREFIX()に指定する引数]
        //証券会社コード：get証券会社コード( )の戻り値
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService) Services.getService(
            WEB3FeqOrderEmpCodeGettingService.class);

        String l_strFeqOrderEmpCodeManageDiv =
            l_feqOrderEmpCodeGettingService.getPREFIX(l_strInstitutionCode);

        //1.4 外国株式注文結果一括入力CSV()
        WEB3FeqOrderAcceptResultUploadCSV l_feqOrderAcceptResultUploadCsv = 
            new WEB3FeqOrderAcceptResultUploadCSV();
        
        //1.5 validate同時アップロード(long)
        l_feqOrderAcceptResultUploadCsv.validateSameTimeUpload(null);//WEB3BaseException
        
        //1.6 get管理者コード( )
        String l_strAdministratorCode = l_admin.getAdministratorCode();

        //1.7 saveアップロード開始(long, String, String, String, String)
        l_feqOrderAcceptResultUploadCsv.saveUpLoadStart(
            0,
            null,
            null,
            null,
            l_strAdministratorCode);//WEB3SystemLayerException

        //1.8 リクエストデータ.アップロードファ@イル[]の各要素毎のLOOP処理
        if (l_request.uploadFileList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードファ@イルが未指定(null)です。");
        }
        int  l_intFileCount = l_request.uploadFileList.length;
        for (int i = 0; i < l_intFileCount; i++)
        {
            int l_intIndex = 0;
            try
            {
                //1.8.1 add明細行(String)
                l_intIndex = l_feqOrderAcceptResultUploadCsv.addRow(
                    l_request.uploadFileList[i]);//WEB3SystemLayerException
                    
                //1.8.2 空行の場合（add明細行()の戻り値 < 0）、当該要素の処理を中断（continue;）
                if (l_intIndex < 0)
                {
                    continue;
                }
            }
            //1.8.3 add明細行()が例外をスローした場合
            catch(WEB3SystemLayerException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                
                //1.8.3.1 saveアップロードエラー(ErrorInfo)
                l_feqOrderAcceptResultUploadCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.3.2 （例外）
                throw l_ex;
            }

            try
            {
                // 受付未済の新規注文以外の場合
                WEB3FeqOrderUnit l_orderUnit =
                    (WEB3FeqOrderUnit)l_feqOrderAcceptResultUploadCsv.getOrderUnit(l_intIndex);
                FeqOrderUnitRow l_orderUnitRow =
                    (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                if (!((l_orderUnit.getOrderStatus().equals(OrderStatusEnum.ORDERING) ||
                        l_orderUnit.getOrderStatus().equals(OrderStatusEnum.MODIFIED))
                        && l_orderUnitRow.getConfirmedQuantity() == 0D))
                {
                    // delete明細行()
                    l_feqOrderAcceptResultUploadCsv.deleteRow(l_intIndex);
                    continue;
                }

                //validate明細行(int,String)
            	//追加した明細行のチェックを行う。
            	//[validate明細行()に指定する引数]
                //行番号：　@（add明細行()の戻り値）
            	//外国株式運用コード採番区分：　@getPREFIX（）の戻り値
                l_feqOrderAcceptResultUploadCsv.validateDetailLine(
                    l_intIndex,
                    l_strFeqOrderEmpCodeManageDiv);
            }
            //1.8.5 validate明細行()が例外をスローした場合
            catch (WEB3BaseException l_ex)
            {
                String l_strMessage = l_ex.getErrorMessage()
                    + l_feqOrderAcceptResultUploadCsv.getEmpCode(i)
                    + "."
                    + l_feqOrderAcceptResultUploadCsv.getProductCode(i);
                log.error(l_strMessage, l_ex);
                
                //1.8.5.1 saveアップロードエラー(ErrorInfo)
                l_feqOrderAcceptResultUploadCsv.saveUploadError(l_ex.getErrorInfo());//WEB3SystemLayerException
                
                //1.8.5.2 （例外）
                if (l_ex instanceof WEB3BusinessLayerException)
                {
                    throw new WEB3BusinessLayerException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
                if (l_ex instanceof WEB3SystemLayerException)
                {
                    throw new WEB3SystemLayerException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
                else
                {
                    throw new WEB3BaseException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME, 
                        l_strMessage,
                        l_ex);
                }
            }
        }

        //1.9 get明細行数( )
        int l_intRowCount = l_feqOrderAcceptResultUploadCsv.getRowCount();

        //1.10 getアップロードＩＤ( )
        long l_lngUploadId = l_feqOrderAcceptResultUploadCsv.getAdministratorUploadId();

        //1.11 saveアップロードTemp( )
        l_feqOrderAcceptResultUploadCsv.saveUploadTemp();//WEB3SystemLayerException

        //1.12 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadConfirmResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadConfirmResponse)l_request.createResponse();
            
        //1.13 プロパティセット
        //アップロード件数
        l_response.uploadNumber = WEB3StringTypeUtility.formatNumber(l_intRowCount);
        //アップロードID
        l_response.uploadId = WEB3StringTypeUtility.formatNumber(l_lngUploadId);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitアップロード)<BR>
     * アップロード完了処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)受付一括入力）submitアップロード」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式注文受付結果アップロード完了リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD314016B
     */
    protected WEB3AdminFeqOrderAcceptResultUploadCompleteResponse submitUpload(
        WEB3AdminFeqOrderAcceptResultUploadCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitUpload(WEB3AdminFeqOrderAcceptResultUploadCompleteRequest )";
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

        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        //1.5 外国株式注文結果一括入力CSV()
        WEB3FeqOrderAcceptResultUploadCSV l_feqOrderAcceptResultUploadCsv = 
            new WEB3FeqOrderAcceptResultUploadCSV();
        
        //1.6 validate同時アップロード(long)
        if (l_request.uploadId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDが未指定(null)です。");
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDタイプ不正。");
        }
        long l_lngUploadId = Long.parseLong(l_request.uploadId);
        l_feqOrderAcceptResultUploadCsv.validateSameTimeUpload(
            new Long(l_lngUploadId));//WEB3BaseException
            
        //1.7 setDataFromアップロードTemp(long)
        l_feqOrderAcceptResultUploadCsv.setDataFromUploadTemp(l_lngUploadId);//WEB3SystemLayerException
            
        //1.8 get明細行数( )
        int l_intRowCount = l_feqOrderAcceptResultUploadCsv.getRowCount();

        //1.9 明細行の数分LOOP処理
        WEB3FeqAcceptUpdateService l_feqAcceptUpdateService = 
            (WEB3FeqAcceptUpdateService )Services.getService(
                WEB3FeqAcceptUpdateService.class);
        if (l_feqAcceptUpdateService == null)
        {
            String l_strMessage = "外国株式受付更新サービスが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);                
        }            
        for (int i = 0; i < l_intRowCount; i++)
        {
            //1.9.1 get注文単位(int)
            WEB3FeqOrderUnit l_orderUnit = l_feqOrderAcceptResultUploadCsv.getOrderUnit(i);//WEB3BaseException
            if (l_orderUnit == null)
            {
                String l_strMessage = "外国株式注文単位が存在しない。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage);
            }
            
            //1.9.2 (注文受付出来通知キューデータの登録を行なう)
            SleRcvdQParams l_rcvdQParams = new SleRcvdQParams();
            try
            {
                //キューID: 自動採番した値
                l_rcvdQParams.setQueueId(SleRcvdQDao.newPkValue());
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
            
            //銘柄タイプ: 4:外国株
            l_rcvdQParams.setXblocksProductType(ProductTypeEnum.FOREIGN_EQUITY);
            
            //注文ID: 外株注文単位.注文ID
            l_rcvdQParams.setInternalRef(l_orderUnit.getOrderId() + "");
            
            //2008/07/07 深セン市場直結対応　@障害対応
            //指値価格:  外株注文単位.指値
            l_rcvdQParams.setPrice(l_orderUnit.getLimitPrice());
            
            //2008/07/07 深セン市場直結対応　@障害対応
            //注文数量: 外株注文単位.注文数量
            l_rcvdQParams.setQuantity(l_orderUnit.getQuantity());
            
            //運用コード: 外株注文単位.運用コード
            l_rcvdQParams.setOrderEmpCode(l_orderUnit.getOrderEmpCode());
            
            //経路区分: 5:注文受付結果一括入力
            l_rcvdQParams.setRouteDiv(WEB3FeqOrderExecRouteDivDef.ORDER_ACCEPT_RESULT_UPLOAD);
            
            //アカウントID: 外株注文単位.口座ID
            l_rcvdQParams.setAccountId(l_orderUnit.getAccountId());
            
            //オペレータタイプ: 
            String l_strOrderStatus = l_orderUnit.getOrderStatus().intValue() + "";
            if (WEB3OrderStatusDef.MODIFYING.equals(l_strOrderStatus))
            {
                //外株注文単位.注文状態==2:発注中(新規注文)の場合は、0:新規
                l_rcvdQParams.setOpType(SleSendqOpTypeEnum.NEW_ORDER);
            }
            else if (WEB3OrderStatusDef.MODIFYED_CHANGEORDER.equals(l_strOrderStatus))
            {
                //外株注文単位.注文状態==10:発注済(変更注文)の場合は、１:訂正
                l_rcvdQParams.setOpType(SleSendqOpTypeEnum.CHANGE_ORDER);
            }
            
            //受付区分: 外国株式注文結果一括入力CSV.get受付区分()の戻り値
            l_rcvdQParams.setAcceptDiv(l_feqOrderAcceptResultUploadCsv.getAcceptDiv(i));
            
            //証券会社コード: 外株注文単位.証券会社コード
            l_rcvdQParams.setInstitutionCode(l_orderUnit.getInstitutionCode());
            
            //部店コード: 外株注文単位.部店IDに該当する部店.部店コード
            l_rcvdQParams.setBranchCode(l_orderUnit.getBranchCode());
            
            //識別コード: 外株注文単位.識別コード
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
            l_rcvdQParams.setOrderRequestNumber(l_feqOrderUnitRow.getOrderRequestNumber());
            
            //更新者コード: 管理者.get管理者コード()の戻り値
            l_rcvdQParams.setLastUpdater(l_admin.getAdministratorCode());
            
            //処理区分: 0:未処理
            l_rcvdQParams.setStatus(SleRcvdqProcStatusEnum.TODO); 
            
            //作成日付: 登録時でsysdate が設定される
            l_rcvdQParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //更新日付: 登録時でsysdate が設定される
            l_rcvdQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_rcvdQParams);
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
        }

        //1.10 saveアップロード終了( )
        l_feqOrderAcceptResultUploadCsv.saveUploadEnd();//WEB3SystemLayerException
        
        //1.11 deleteアップロードTemp( )
        l_feqOrderAcceptResultUploadCsv.deleteUploadTemp();//WEB3SystemLayerException
        
        //1.12 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadCompleteResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoアップロード)<BR>
     * アップロード中止処理を行う。<BR>
     * <BR>
     * シーケンス図「（(管)受付一括入力）undoアップロード」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式注文受付結果アップロード中止リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD314016D
     */
    protected WEB3AdminFeqOrderAcceptResultUploadStopResponse undoUpload(
        WEB3AdminFeqOrderAcceptResultUploadStopRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " undoUpload(WEB3AdminFeqOrderAcceptResultUploadStopRequest )";
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

        if (l_request.uploadId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDが未指定(null)です。");
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.uploadId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストのアップロードIDタイプ不正。");
        }
        long l_lngUploadId = Long.parseLong(l_request.uploadId);

        //1.1 外国株式注文結果一括入力CSV()
        WEB3FeqOrderAcceptResultUploadCSV l_feqOrderAcceptResultUploadCsv = 
            new WEB3FeqOrderAcceptResultUploadCSV(l_lngUploadId);

        //1.2 deleteアップロードTemp( )
        l_feqOrderAcceptResultUploadCsv.deleteUploadTemp();//WEB3SystemLayerException 
        
        //1.3 saveアップロード中止( )
        l_feqOrderAcceptResultUploadCsv.saveUploadStop(); //WEB3SystemLayerException

        //1.4 createResponse( )
        WEB3AdminFeqOrderAcceptResultUploadStopResponse l_response = 
            (WEB3AdminFeqOrderAcceptResultUploadStopResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
