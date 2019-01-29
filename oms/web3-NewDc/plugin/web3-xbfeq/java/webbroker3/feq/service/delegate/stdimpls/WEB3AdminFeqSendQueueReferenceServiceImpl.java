head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式SENDキュー照会サービスImpl (WEB3AdminFeqSendQueueReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 齊珂 (中訊) 新規作成    
Revesion History : 2006/11/20 徐大方 (中訊) モデル No.303
Revesion History : 2006/12/11 齊珂   (中訊) モデル No.310
Revesion History : 2007/01/15 徐大方 (中訊) モデル No.331
Revesion History : 2007/02/02 丁昭奎 (中訊) モデル No.340
Revesion History : 2007/02/07 丁昭奎 (中訊) モデル No.343
Revesion History : 2007/02/25 齊珂   (中訊) モデル No.346
Revesion History : 2008/02/02 武波   (中訊) モデル No.391,No.392,No.394
Revesion History : 2009/08/03 武波   (中訊) モデル No.503
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceResponse;
import webbroker3.feq.message.WEB3FeqSendQueueInfo;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式SENDキュー照会サービスImpl )<BR>
 * 管理者外国株式SENDキュー照会サービス実装クラス<BR>
 *   
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminFeqSendQueueReferenceServiceImpl implements WEB3AdminFeqSendQueueReferenceService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueReferenceServiceImpl.class);  
    
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 管理者外国株式SENDキュー照会処理を実施する。<BR>
     * <BR>
     * リクエストデータの型に対応するメソッドをコールする。<BR>
     * <BR>
     * －get入力画面()<BR>
     * －get一覧画面()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 429FEB91032D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response;
        
        //get入力画面
        if (l_request instanceof WEB3AdminFeqSendQueueReferenceInputRequest)
        {
            l_response = 
                this.getInputScreen(
                    (WEB3AdminFeqSendQueueReferenceInputRequest)l_request);   
        }
        
        //get一覧画面
        else if (l_request instanceof WEB3AdminFeqSendQueueReferenceRequest)
        {
            l_response =
                this.getListScreen(
                    (WEB3AdminFeqSendQueueReferenceRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 管理者外国株式SENDキュー照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式SENDキュー照会サービス)get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqSendQueueReferenceInputResponse getInputScreen(
    	WEB3AdminFeqSendQueueReferenceInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqSendQueueReferenceInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        //ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException

        //get市場連動外株市場(証券会社コード : String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        Market[] l_markets = l_finObjectMgr.getLinkFeqMarkets(l_admin.getInstitutionCode());
        //1.3 createResponse( )
        WEB3AdminFeqSendQueueReferenceInputResponse l_response = 
            (WEB3AdminFeqSendQueueReferenceInputResponse)l_request.createResponse();
        
        //1.4 プロパティセット
        List l_lisTransactionDiv = new ArrayList();
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.TODO.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.PROCESSED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.BAT_PROCED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.PREPARE_PROCESSED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.NOT_PROCESSED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.SKIP_PROCESSING_LOCAL.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR.intValue() + "");
        
        l_response.transactionDivList = new String[l_lisTransactionDiv.size()];
        
        l_lisTransactionDiv.toArray(l_response.transactionDivList);
        
        List l_lisBizDateList = new ArrayList();
        //業務日付
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //前日営業日
        Date l_datpreBizDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(-1);
        //前々日営業日
        Date l_datpreBizDate2 = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(-2);
        //前々々日営業日
        Date l_datpreBizDate3 = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(-3);
        //翌営業日
        Date l_datNextBizDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);

        l_lisBizDateList.add(l_datpreBizDate3);
        l_lisBizDateList.add(l_datpreBizDate2);
        l_lisBizDateList.add(l_datpreBizDate);
        l_lisBizDateList.add(l_datBizDate);
        l_lisBizDateList.add(l_datNextBizDate);

        l_response.orderDateList = new Date[l_lisBizDateList.size()];
        
        l_lisBizDateList.toArray(l_response.orderDateList);

        //市場コード一覧:get市場連動外株市場の戻り値.getMarketCode()
        String[] l_strMarkerCodes = new String[l_markets.length];
        for (int i = 0; i < l_strMarkerCodes.length; i++)
        {
            l_strMarkerCodes[i] = l_markets[i].getMarketCode();
        }
        l_response.marketList = l_strMarkerCodes;
        //return レスポンスデータ
	    return l_response;
    }
    
    /**
     * (get一覧画面)<BR>
     * 管理者外国株式SENDキュー照会一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式SENDキュー照会サービス)get一覧画面」参照 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqSendQueueReferenceResponse getListScreen(
    	WEB3AdminFeqSendQueueReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqSendQueueReferenceResponse)";
        log.entering(STR_METHOD_NAME);

        List l_lisSendQueueRefList = null;
        String l_lngProductCode = "";
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException
        
        //1.4 get証券会社コード
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
		//処理区分： リクエスト.処理区分 
		//運用コード： リクエスト.運用コード 
		//部店コード： リクエスト.部店コード 
		//顧客コード： リクエスト.顧客コード 
        //メール送信日時フラグ： リクエスト.メール送信日時フラグ
        //市場コード：リクエスト.市場コード
        String l_strQueryString = this.createQueryString(
        	l_request.transactionDiv,
        	l_request.managementCode,
        	l_request.branchCode,
        	l_request.accountCode,
        	l_request.sendMailDateFlag,
            l_request.marketCode);
        
        //1.6 create検索条件データコンテナ
		// 証券会社コード： 取得した証券会社コード 
		// 処理区分： リクエスト.処理区分 
		// 運用コード： get運用コード（）の戻り値 
        // 発注日: リクエスト.発注日
		// 部店コード： リクエスト.部店コード 
		// 顧客コード： リクエスト.顧客コード
        //市場コード：リクエスト.市場コード
        Object[] l_objDataContainer = this.createQueryDataContainer(
        	l_strInstitutionCode,
        	l_request.transactionDiv,
            l_strEmpCode,
            l_request.orderDate,
        	l_request.branchCode,
        	l_request.accountCode,
            l_request.marketCode);
        
        //1.7 createソート条件
        //ソートキー： リクエスト.ソートキー
        String l_strSortKey = this.createSortCond(l_request.sortKeys);
        
        //1.8 doFindAllQuery	
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
	        l_lisSendQueueRefList = l_queryProcessor.doFindAllQuery(
	        	SleSendQRow.TYPE, 
	        	l_strQueryString,
	            l_strSortKey,
	            null,
	            l_objDataContainer);
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
        
        // 1.9 createResponse
        WEB3AdminFeqSendQueueReferenceResponse l_response = 
            (WEB3AdminFeqSendQueueReferenceResponse)l_request.createResponse();

        // 1.10レコードが取得できた場合
        if (l_lisSendQueueRefList != null && !l_lisSendQueueRefList.isEmpty())
        {
        	//1.10.1 WEB3PageIndexInfo
            WEB3PageIndexInfo l_pageInfo = new WEB3PageIndexInfo(
            	l_lisSendQueueRefList, 
                Integer.parseInt(l_request.pageIndex), 
                Integer.parseInt(l_request.pageSize));
            
            //1.10.2 getArrayReturned( )            
            SleSendQParams[] l_sleSendQParams = 
                (SleSendQParams[])l_pageInfo.getArrayReturned(SleSendQParams.class);
            
            List l_lisSendQueueInfo = new ArrayList();
            
            //1.10.3 取得した表示対象の(外国株取引)SEND_QテーブルParamsの配列の件数分、処理を繰り返す
            int l_intCnt = 0;
            if (l_sleSendQParams != null && l_sleSendQParams.length > 0)
            {
                l_intCnt = l_sleSendQParams.length;
            }    
        	for (int i = 0; i < l_intCnt; i++)
            {
                //1.10.3.1 外国株式SENDキュー情報
        		WEB3FeqSendQueueInfo l_sendQueueInfo = new WEB3FeqSendQueueInfo();
                
                //1.10.3.2 
                //顧客コード
                if (l_sleSendQParams[i].getAccountCodeIsSet())
                {
                    l_sendQueueInfo.accountCode = l_sleSendQParams[i].getAccountCode().substring(0, 6);
                }
                
                //訂正前指値
                if (!l_sleSendQParams[i].getChangeLimitPriceIsNull())
                {
                    l_sendQueueInfo.beforeOrderPrice = 
                    	WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getChangeLimitPrice());
                }
                
                //訂正前数量
                if (!l_sleSendQParams[i].getChangeQuantityIsNull())
                {
                    l_sendQueueInfo.beforeOrderQuantity = 
                    	WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getChangeQuantity());
                }
                
                //部店コード
                if (l_sleSendQParams[i].getBranchCodeIsSet())
                {
                    l_sendQueueInfo.branchCode = l_sleSendQParams[i].getBranchCode();
                }
                
                //作成日付
                l_sendQueueInfo.createTimeStamp = l_sleSendQParams[i].getCreatedTimestamp();
                
                //運用コード
                if (l_sleSendQParams[i].getOrderEmpCodeIsSet())
                {
                	l_sendQueueInfo.managementCode = l_sleSendQParams[i].getOrderEmpCode();
                }
                
                //オペレータタイプ
                if (l_sleSendQParams[i].getOpTypeIsSet())
                {

                    l_sendQueueInfo.operatorType = l_sleSendQParams[i].getOpType().intValue() + "";
                }
                
                //発注日
                if (l_sleSendQParams[i].getBizDateIsSet())
                {
                	l_sendQueueInfo.orderBizDate = l_sleSendQParams[i].getBizDate();
                }
                
                //指値
                if (!l_sleSendQParams[i].getLimitPriceIsNull())
                {
                	l_sendQueueInfo.orderPrice = 
                		WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getLimitPrice());
                }
                
                //注文数量
                if (!l_sleSendQParams[i].getQuantityIsNull())
                {
                	l_sendQueueInfo.orderQuantity = 
                		WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getQuantity());
                }
                
                //注文種別
                if (l_sleSendQParams[i].getOrderTypeIsSet())
                {
                	l_sendQueueInfo.orderType = l_sleSendQParams[i].getOrderType().intValue() + "";
                }

                //注文ID
                if (!l_sleSendQParams[i].getOrderIdIsNull())
                {
                	l_sendQueueInfo.orderId = l_sleSendQParams[i].getOrderId() + "";
                }

                //銘柄コード
                long l_lngOrderUnitId = l_sleSendQParams[i].getOrderUnitId(); 

                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

                WEB3FeqOrderManager l_feqOrderManager =
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

                try
                {
                    WEB3FeqOrderUnit l_orderUnit =
                        (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_lngOrderUnitId);
                    WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnit.getProduct();
                    l_lngProductCode = l_product.getProductCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                l_sendQueueInfo.productCode = l_lngProductCode;
                
                //キューID
                if (l_sleSendQParams[i].getQueueIdIsSet())
                {
                	l_sendQueueInfo.queueId = l_sleSendQParams[i].getQueueId() + "";
                }
                
                //メール送信日時
                l_sendQueueInfo.sendMailDate = l_sleSendQParams[i].getSendProcessDateTime();

                //処理区分
                if (l_sleSendQParams[i].getStatusIsSet())
                {
                	l_sendQueueInfo.transactionDiv = l_sleSendQParams[i].getStatus().intValue() + "";
                }
                               
                //更新日付
                if (l_sleSendQParams[i].getLastUpdatedTimestampIsSet())
                {
                	l_sendQueueInfo.updateTimeStamp = 
                		l_sleSendQParams[i].getLastUpdatedTimestamp();
                }

                //識別コード
                l_sendQueueInfo.requestNumber = l_sleSendQParams[i].getOrderRequestNumber();

                //市場コード
                l_sendQueueInfo.marketCode = l_sleSendQParams[i].getMarketCode();

                l_lisSendQueueInfo.add(l_sendQueueInfo);
            }
            WEB3FeqSendQueueInfo[] l_sendQueueInfos = new WEB3FeqSendQueueInfo[l_lisSendQueueInfo.size()];
            l_lisSendQueueInfo.toArray(l_sendQueueInfos);
            
            //1.10.4:getTotalPages( )
            int l_intTotalPages = l_pageInfo.getTotalPages();
            
            //1.10.5:getTotalRecords( )
            int l_intTotalRecords = l_pageInfo.getTotalRecords();
            
            //1.10.6:getPageIndex( )
            int l_intPageIndex = l_pageInfo.getPageIndex();
        	
            l_response.feqSendQueueInfoList = l_sendQueueInfos;
            l_response.totalPages = l_intTotalPages + "";
            l_response.totalRecords = l_intTotalRecords + "";
            l_response.pageIndex = l_intPageIndex + "";
        }
        //1.11レコードが取得できなかった場合
        else
        {
        	l_response.feqSendQueueInfoList = null;
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
     * 　@　@作成したソート条件文字列に追加する。 <BR>
     * <BR>
     * 　@・「運用コード」　@→　@(外国株取引)SEND_Qテーブル.運用コード <BR>
     * 　@・「部店コード」　@→　@(外国株取引)SEND_Qテーブル.部店コード<BR>
     * 　@・「顧客コード」　@→　@substr((外国株取引)SEND_Qテーブル.アカウントID, 9, 6) <BR>
     * 　@・「処理区分」　@→　@(外国株取引)SEND_Qテーブル.処理区分<BR>
     *　@ ・「作成日付」　@→　@(外国株取引)SEND_Qテーブル.作成日付<BR>
     * 　@・「更新日付」　@→　@(外国株取引)SEND_Qテーブル.更新日付<BR>
     * <BR>
     * 　@２－２）ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ３）ソート条件末尾に、(外国株取引)SEND_Qテーブル.更新日付を昇順指定で付加<BR>
     * 　@※キー項目に「更新日付」が指定されている場合は付加しない。<BR>
     * <BR>
     * ４）作成したソート条件文字列を返却する。　@　@
     * @@param l_sortKeys - (ソートキー)
     * ソートキー
     * 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCond(" +
        "WEB3ForeignSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_strSortKeys = new StringBuffer();
        boolean l_blnDateStatus = true;
        
        for (int i = 0; i < l_sortKeys.length; i++)
        {
        	//運用コード
            if (WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" order_emp_code ASC ");
                }
                else
                {
                    l_strSortKeys.append(" order_emp_code DESC ");
                }
            }
            //部店コード
            else if (WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" branch_code ASC ");
                }
                else
                {
                    l_strSortKeys.append(" branch_code DESC ");
                }
            }
            //顧客コード
            else if (WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" substr(account_id, 9, 6) ASC ");
                }
                else
                {
                    l_strSortKeys.append(" substr(account_id, 9, 6) DESC ");
                }
            }
            //処理区分
            else if (WEB3FeqSortKeyItemNameDef.TRANSACTION_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" status ASC ");
                }
                else
                {
                    l_strSortKeys.append(" status DESC ");
                }
            }
            //作成日付
            else if (WEB3FeqSortKeyItemNameDef.CREATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" created_timestamp ASC ");
                }
                else
                {
                    l_strSortKeys.append(" created_timestamp DESC ");
                }
            }
            //更新日付
            else if (WEB3FeqSortKeyItemNameDef.LAST_UPDATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                l_blnDateStatus = false;
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" last_updated_timestamp ASC ");
                }
                else
                {
                    l_strSortKeys.append(" last_updated_timestamp DESC ");
                }
            }
            if (i < l_sortKeys.length - 1)
            {
                l_strSortKeys.append(" , ");
            }
        }
        if (l_blnDateStatus)
        {
        	l_strSortKeys.append(" , last_updated_timestamp ASC ");
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortKeys.toString();
    }
    
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。 <BR>
     * <BR>
     * １）検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）パラメータ.市場コード != null の場合、<BR>
     * 市場コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 検索条件文字列 += "market_code = ?  and "<BR>
     * <BR>
     * ３）証券会社コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "institution_code = ? "<BR>
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
     * ６）発注日を検索条件文字列に追加する。<BR>
     * 　@検索条件文字列 += "and biz_date = ? "<BR> 
     * <BR>
     * ７）パラメータ.部店コード != nullの場合、<BR>
     * 　@部店コードを検索条件文字列に追加する。<BR> 
     * <BR>
     * ８）パラメータ.顧客コード != nullの場合、<BR>
     * 　@顧客コード条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and substr(account_code, 0, 6) = ? "<BR>
     * <BR>
     * ９）パラメータ.メール送信日時フラグ == trueの場合、 <BR>
     * 　@メール送信日時を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and send_process_date_time is null "<BR>
     * <BR>
     * １０）作成した検索条件文字列を返却する。<BR>
     * @@param l_strStatus - (処理区分)
     * 処理区分
     * @@param l_strOrderEmpCode - (運用コード)
     * 運用コード
     * @@param l_strBranchCode - (部店コード)
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)
     * 顧客コード
     * @@param l_blnMailSendProcessDateFlag - (メール送信日時フラグ)
     * ”電子メール送信日時 is null”を検索条件に追加する場合にはtrueを、 <BR>
     * そうでない場合はfalseを設定する。<BR>
     * @@param l_strMarketCode - (市場コード)
     * 市場コード
     * @@return String
     * @@roseuid 42A80C63000C
     */
    protected String createQueryString(
    	String l_strStatus,
    	String l_strOrderEmpCode,
    	String l_strBranchCode,
    	String l_strAccountCode,
    	boolean l_blnMailSendProcessDateFlag,
        String l_strMarketCode)
    {
        final String STR_METHOD_NAME = 
            "createQueryString(String, String, String, String, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）検索条件文字列インスタンス(：String)を生成する。 
        String l_strQueryString = "";

        //２）パラメータ.市場コード != null の場合、
        //市場コードを検索条件文字列に追加する。
        //検索条件文字列 += "market_code = ?  and "
        if (l_strMarketCode != null)
        {
            l_strQueryString = l_strQueryString + " market_code = ?  and ";
        }

        //３）証券会社コードを検索条件文字列に追加する
        l_strQueryString = l_strQueryString + " institution_code = ? ";
        
        //４）パラメータ.処理区分 != nullの場合、 
        //処理区分を検索条件文字列に追加する。
        if (l_strStatus != null)
        {
        	l_strQueryString = l_strQueryString + " and status = ? ";
        }
        
		//５）パラメータ.運用コード != nullの場合、 
		//運用コードを検索条件文字列に追加する。 
        if (l_strOrderEmpCode != null)
        {
        	l_strQueryString = l_strQueryString + " and order_emp_code = ? ";
        }
        
        //６）発注日を検索条件文字列に追加する。
        //検索条件文字列 += "and biz_date = ? "
        l_strQueryString = l_strQueryString + " and biz_date = ? ";
        
        //７）パラメータ.部店コード != nullの場合、 
        //部店コードを検索条件文字列に追加する。 
        if (l_strBranchCode != null)
        {
        	l_strQueryString = l_strQueryString + " and branch_code = ? ";
        }
        
        //８）パラメータ.顧客コード != nullの場合、 
        //顧客コード条件を検索条件文字列に追加する。 
        if (l_strAccountCode != null)
        {
        	l_strQueryString = l_strQueryString + " and substr(account_code, 0, 6) = ? ";
        }
        
        //９）パラメータ.メール送信日時フラグ == trueの場合、 
        //メール送信日時を検索条件文字列に追加する。 
        if (l_blnMailSendProcessDateFlag)
        {
        	l_strQueryString = l_strQueryString + " and send_process_date_time is null ";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを作成する。 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）パラメタ.市場コード != null の場合、<BR>
     * パラメータ.市場コードを生成したArrayListに追加する。<BR>
     * ３）パラメタ.証券会社コードを生成したArrayListに追加する。 <BR>
     * <BR>
     * ４）パラメータ.処理区分 != nullの場合、 <BR>
     * 　@パラメータ.処理区分を生成したArrayListに追加する。 <BR>
     * <BR>
     * ５）パラメータ.運用コード != nullの場合、<BR> 
     * 　@パラメータ.運用コードを生成したArrayListに追加する。 <BR>
     * <BR>
     * ６－１）パラメータ.発注日 != nullの場合、 <BR>
     * 　@パラメータ.発注日を生成したArrayListに追加する。 <BR>
     * ６－２）上記以外 <BR>
     * 　@業務日付を生成したArrayListに追加する。 <BR>
     * ７）パラメータ.部店コード != nullの場合、 <BR>
     * 　@パラメータ.部店コードを生成したArrayListに追加する。 <BR>
     * <BR>
     * ８）パラメータ.顧客コード != nullの場合、 <BR>
     * 　@パラメータ.顧客コードを生成したArrayListに追加する。 <BR>
     * <BR>
     * ９）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * 証券会社コード
     * @@param l_strStatus - (処理区分)
     * 処理区分
     * @@param l_strOrderEmpCode - (運用コード)
     * 運用コード
     * @@param l_datBizDate - (発注日)
     * 発注日
     * @@param l_strBranchCode - (部店コード)
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)
     * 顧客コード
     * @@param l_strMarketCode - (市場コード)
     * 市場コード
     * @@return Object[]
     * @@roseuid 42A80C63000C
     */
    protected Object[] createQueryDataContainer(
        String l_strInstitutionCode, 
        String l_strStatus, 
        String l_strOrderEmpCode, 
        Date l_datBizDate,
        String l_strBranchCode, 
        String l_strAccountCode,
        String l_strMarketCode) throws WEB3BaseException
	{
        final String STR_METHOD_NAME = 
            "createQueryDataContainer(String, String, String, Date, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）ArrayListを生成する。 
        ArrayList l_arrayList = new ArrayList();

        //２）パラメタ.市場コード != null の場合、
        //パラメータ.市場コードを生成したArrayListに追加する。
        if (l_strMarketCode != null)
        {
            l_arrayList.add(l_strMarketCode);
        }

        //３）パラメタ.証券会社コードを生成したArrayListに追加する。 
        l_arrayList.add(l_strInstitutionCode);
        
        //４）パラメータ.処理区分 != nullの場合、 
        //パラメータ.処理区分を生成したArrayListに追加する。 
        if (l_strStatus != null)
        {
            l_arrayList.add(l_strStatus);
        }
        
        //５）パラメータ.運用コード != nullの場合、 
        //パラメータ.運用コードを生成したArrayListに追加する
        if (l_strOrderEmpCode != null)
        {
            l_arrayList.add(l_strOrderEmpCode);
        }
        
        //６－１）パラメータ.発注日 != nullの場合、
        //パラメータ.発注日を生成したArrayListに追加する。
        //６－２）上記以外
        //業務日付を生成したArrayListに追加する。
        if (l_datBizDate != null)
        {
            l_arrayList.add(WEB3DateUtility.formatDate(l_datBizDate, YYYYMMDD));
        }
        else
        {
            l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            l_arrayList.add(WEB3DateUtility.formatDate(l_datBizDate, YYYYMMDD));
        }
        
        //7）パラメータ.部店コード != nullの場合、 
        //パラメータ.部店コードを生成したArrayListに追加する。 
        if (l_strBranchCode != null)
        {
            l_arrayList.add(l_strBranchCode);
        }
        
        //８）パラメータ.顧客コード != nullの場合、 
        //パラメータ.顧客コードを生成したArrayListに追加する。 
        if (l_strAccountCode != null)
        {
            l_arrayList.add(l_strAccountCode);
        }
        
        //９）生成したArrayList.toArray()の戻り値を返却する。
        Object[] l_strQueryContainers = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
	}
    
    
    
}
@
