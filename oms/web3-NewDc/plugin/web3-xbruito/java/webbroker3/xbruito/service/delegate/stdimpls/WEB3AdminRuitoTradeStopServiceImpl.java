head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者累投銘柄別売買停止サービスImpl (WEB3AdminRuitoTradeStopServieImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductUpdqRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeInfo;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse;
import webbroker3.xbruito.service.delegate.WEB3AdminRuitoTradeStopService;

/**
 * (管理者累投銘柄別売買停止サービスImpl)<BR>
 * 管理者累投銘柄別売買停止サービス実装クラス
 */
public class WEB3AdminRuitoTradeStopServiceImpl implements WEB3AdminRuitoTradeStopService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminRuitoTradeStopServiceImpl.class);
    
    /**
     * 累投管理者機@能銘柄別売買停止サービスを実施する。<BR>
     *リクエストデータのクラスによって、以下のいずれかのメソッドをコールする。<BR> 
     *－get入力画面() <BR>
     *－validate銘柄別売買停止()<BR> 
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 406932820270
     */
    public  WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        if (l_request instanceof WEB3AdminRuitoTradeStopInputRequest)
        {
            //リクエストデータの具象データ型が「累投銘柄別売買停止入力画面リクエスト」の場合
            log.exiting(STR_METHOD_NAME);
            return this.getInputScreen((WEB3AdminRuitoTradeStopInputRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminRuitoTradeStopConfirmRequest)
        {
            //リクエストデータの具象データ型が「累投銘柄別売買停止確認画面リクエスト」の場合
            log.exiting(STR_METHOD_NAME);
            return this.validateTradeStop((WEB3AdminRuitoTradeStopConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminRuitoTradeStopCompleteRequest)
        {
            //リクエストデータの具象データ型が「累投銘柄別売買停止完了画面リクエスト」の場合
            log.exiting(STR_METHOD_NAME);
            return this.submitTradeStop((WEB3AdminRuitoTradeStopCompleteRequest) l_request);
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
    }
    
    /**
     * (get入力画面)<BR>
     * 累投銘柄別売買停止入力画面取得処理を実施する。<BR>
     *シーケンス図「（累投管理者）get入力画面」参照<BR>
     * @@param l_request - 累投銘柄別売買停止入力画面リクエスト
     * @@return webbroker3.common.message.WEB3AdminRuitoTradeStopInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 406932820270
     */
    private WEB3AdminRuitoTradeStopInputResponse getInputScreen(
        WEB3AdminRuitoTradeStopInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminRuitoTradeStopInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.2 ログイン情報より管理者オブジェクトを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 該当の管理者がこの機@能が使えるか権限チェックを行う。 
        //validate権限(String, boolean)
        //[引数] 
        //　@機@能カテゴリーコード＝”累投（銘柄管理）” 
        //is更新： false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_RUITO_TRADE_STOP,
            false);
        
        //1.4 管理者オブジェクトより所属する証券会社を取得する。
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.5 累投銘柄を全て検出する。
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        List l_lisRuitoProduct = 
            l_ruitoProductManager.getRuitoProductList(
                    l_institution.getInstitutionCode());
        log.debug("l_lisRuitoProduct.size() = " + l_lisRuitoProduct.size());
        
        //－戻り値の件数＝0件の場合、（データ不整合）として例外をスローする。
        if (l_lisRuitoProduct.size() == 0)
        {
            log.debug("データ不整合エラー");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "戻り値の件数＝0件の場合");
        }
        
        //1.6 現在日付取得
        //GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        Timestamp l_tsSystemTime =  GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //1.7  営業日チェック取得した現在日付が営業日かどうかを判定する。
		Date l_dateSystemDate = WEB3DateUtility.getDate(
			WEB3DateUtility.formatDate(l_tsSystemTime,"yyyyMMdd"),"yyyyMMdd");
		Timestamp l_tsSystemDate = new Timestamp(l_dateSystemDate.getTime()); 
        boolean l_blnBizDate = this.isBizDate (l_tsSystemDate);
        
        Date l_datCurBizDate = null;
        Date l_datNextBizDate = null;
        
        List l_lisAdminRuitoTradeInfo = new ArrayList();
        
        //1.8 get累投銘柄一覧()の戻り値の件数分、繰り返して累投銘柄別売買情報オブジェクトの配列を作成する。
        for (int i = 0; i < l_lisRuitoProduct.size(); i++)
        {
            RuitoProductRow l_ruitoProductRow = 
                (RuitoProductRow)l_lisRuitoProduct.get(i);
            
            //1.8.1 累投銘柄別売買情報( )(
            WEB3AdminRuitoTradeInfo l_adminRuitoTradeInfo = 
                new WEB3AdminRuitoTradeInfo();
            
            //1.8.2 銘柄情報のプロパティ・セット

            //・銘柄コード＝get累投銘柄一覧()の戻り値（n件目）の銘柄コード
            l_adminRuitoTradeInfo.ruitoProductCode = l_ruitoProductRow.getProductCode();
            
            //・銘柄名＝get累投銘柄一覧()の戻り値（n件目）の銘柄名
            l_adminRuitoTradeInfo.ruitoProductName = l_ruitoProductRow.getStandardName();
            
            //・買付開始日＝get累投銘柄一覧()の戻り値（n件目）の買付開始日
            l_adminRuitoTradeInfo.buyStartDate = l_ruitoProductRow.getBuyStartDate();
            
            //・買付終了日＝get累投銘柄一覧()の戻り値（n件目）の買付終了日
            l_adminRuitoTradeInfo.buyEndDate = l_ruitoProductRow.getBuyEndDate();
            
            //・解約開始日＝get累投銘柄一覧()の戻り値（n件目）の解約開始日
            l_adminRuitoTradeInfo.sellStartDate = l_ruitoProductRow.getSellStartDate();
            
            //・解約終了日＝get累投銘柄一覧()の戻り値（n件目）の解約終了日
            l_adminRuitoTradeInfo.sellEndDate = l_ruitoProductRow.getSellEndDate();
            
            //1.8.4 －reset受付時間区分()の実施            
            //・get累投銘柄一覧()の戻り値（n件目）の累投タイプ＝”MMF”の場合、”MMF（設定）”をセット。
            //・get累投銘柄一覧()の戻り値（n件目）の累投タイプ＝”中国F”の場合、”中国F”をセット。
            String l_strTradingTimeType = "";
            if (RuitoTypeEnum.MMF.equals(l_ruitoProductRow.getRuitoType()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET;                
            }
            else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoProductRow.getRuitoType()))
            {
                l_strTradingTimeType = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            }
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
            
            //1.8.5 －setTimestamp()の実施
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //1.8.6 取引銘柄オブジェクトを取得する。
            //  [get累投取引銘柄に渡す引数]
            //  証券会社＝管理者オブジェクトget証券会社()の戻り値
            //  銘柄コード＝get累投銘柄一覧()の戻り値（n件目）の銘柄コード
            RuitoTradedProduct l_ruitoTradedProduct = null;
            try
            {
                log.debug("銘柄コード＝ " + l_ruitoProductRow.getProductCode());
                l_ruitoTradedProduct = 
                    l_ruitoProductManager.getRuitoTradedProduct(
                        l_institution,
                        l_ruitoProductRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("拡張累投取引銘柄がない");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00250,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            RuitoTradedProductRow l_ruitoTradedProductRow = 
                (RuitoTradedProductRow)l_ruitoTradedProduct.getDataSourceObject();          
            
            //1.8.7 is営業日()＝trueだった場合
            if (l_blnBizDate)
            {
                log.debug("is営業日()＝trueだった場合");
                //営業日計算
                
                //基準日＝取引銘柄オブジェクトの有効日
                Date l_datValidForBizDate = WEB3DateUtility.getDate(
                        l_ruitoTradedProductRow.getValidForBizDate(), "yyyyMMdd");
                
                //[rollに渡す引数]
                //加算／減算日数＝１をセット。（翌日分を指定）
                int l_intRoll = 0;
				RuitoTradedProductUpdqRow l_ruitoTradedProductUpdqRow = null;
				try
				{
                    log.debug("（翌日分を指定）");
                   	l_intRoll = 1;
                    
					log.debug("[rollに渡す引数]=" + l_intRoll);
					Date l_datSalsDate = new WEB3GentradeBizDate(
							new Timestamp(l_datValidForBizDate.getTime())).roll(l_intRoll);
                
					String l_strSalsDate = 
						WEB3DateUtility.formatDate(l_datSalsDate, "yyyyMMdd");
                    
					//取引銘柄UPDQオブジェクト取得処理
					//－以下の条件で、「累投取引銘柄一時テーブル」を検索する。
					//[検索条件]
					//取引銘柄ID＝取引銘柄オブジェクトの取引銘柄ID and
					//有効日＝roll()の戻り値
					String l_strWhereClause = 
						"traded_product_id = ? and valid_for_biz_date = ?";
                    
					log.debug("取引銘柄ID＝ " + 
							l_ruitoTradedProduct.getTradedProductId() + "");
					log.debug("有効日＝ " + 
							l_strSalsDate);
                    
					//DataQueryException,DataNetworkException
					List l_lisRows =
						Processors.getDefaultProcessor().doFindAllQuery(
							RuitoTradedProductUpdqRow.TYPE,
							l_strWhereClause,
							new Object[] { 
								new Long(l_ruitoTradedProduct.getTradedProductId()), 
								l_strSalsDate });
    						//－戻り値 !=1件の場合、（データ不整合）として例外をスローする。
							log.debug("size ="  + l_lisRows.size());
					if (l_lisRows.size() != 1)
					{
						log.debug("データ不整合エラー");
						throw new WEB3SystemLayerException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80006,
							getClass().getName() + "." + STR_METHOD_NAME, 
							"戻り値 !=1件の場合");                       
					}
					l_ruitoTradedProductUpdqRow = 
						(RuitoTradedProductUpdqRow)l_lisRows.get(0);
					log.debug("l_ruitoTradedProductUpdqRow = " + l_ruitoTradedProductUpdqRow);

                }
                catch (DataQueryException l_ex)
                {
                    log.error("__DataQueryException__");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("__DataNetworkException__");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                
                //累投銘柄別売買情報オブジェクトに、以下のプロパティをセットする。
                //・買付可能区分（当日）＝”取引銘柄（当日分）”の買付停止
                //・解約可能区分（当日）＝”取引銘柄（当日分）”の解約停止
                //・買付可能区分（翌日）＝”取引銘柄（翌日分）”の買付停止
                //・解約可能区分（翌日）＝”取引銘柄（翌日分）”の解約停止
                log.debug("当営業日分の取引銘柄オブジェクトのプロパティ・セット");
                //1.8.7.4 取引銘柄のプロパティ・セット
                l_adminRuitoTradeInfo.curBuyPosDiv = l_ruitoTradedProductRow.getBuyStop();
                l_adminRuitoTradeInfo.curSellPosDiv = l_ruitoTradedProductRow.getSellStop();
				log.debug("翌営業日分取引銘柄オブジェクトのプロパティ・セット");
                l_adminRuitoTradeInfo.nextBuyPosDiv = l_ruitoTradedProductUpdqRow.getBuyStop();
                l_adminRuitoTradeInfo.nextSellPosDiv = l_ruitoTradedProductUpdqRow.getSellStop();
                   
                l_datCurBizDate = WEB3DateUtility.getDate(
                    l_ruitoTradedProductRow.getValidForBizDate(), "yyyyMMdd");
                l_datNextBizDate = WEB3DateUtility.getDate(
                    l_ruitoTradedProductUpdqRow.getValidForBizDate(), "yyyyMMdd");

            }
            else
            {
                log.debug("is営業日()＝falseだった場合");
                //累投銘柄別売買情報オブジェクトに、以下のプロパティをセットする。
                //－取引銘柄Aを”取引銘柄（翌日分）”とする。
                //・買付可能区分（当日）＝NULL
                //・解約可能区分（当日）＝NULL
                //・買付可能区分（翌日）＝”取引銘柄（翌日分）”の買付停止
                //・解約可能区分（翌日）＝”取引銘柄（翌日分）”の解約停止
                
                //1.8.8
                l_adminRuitoTradeInfo.curBuyPosDiv = null;
                l_adminRuitoTradeInfo.curSellPosDiv = null;
                l_adminRuitoTradeInfo.nextBuyPosDiv = l_ruitoTradedProductRow.getBuyStop();
                l_adminRuitoTradeInfo.nextSellPosDiv = l_ruitoTradedProductRow.getSellStop();

                l_datCurBizDate = null;
                l_datNextBizDate = WEB3DateUtility.getDate(
                    l_ruitoTradedProductRow.getValidForBizDate(), "yyyyMMdd");              
            }         
            l_lisAdminRuitoTradeInfo.add(l_adminRuitoTradeInfo);
        }
        
        //1.9 createレスポンス( )       
        WEB3AdminRuitoTradeStopInputResponse l_adminRuitoTradeStopInputResponse =
            (WEB3AdminRuitoTradeStopInputResponse) 
                l_request.createResponse();   
        //取得したレスポンスデータに以下のプロパティをセットし、返却する。

        WEB3AdminRuitoTradeInfo[] l_adminRuitoTradeInfo =  
            new WEB3AdminRuitoTradeInfo[l_lisAdminRuitoTradeInfo.size()];
        
        l_lisAdminRuitoTradeInfo.toArray(l_adminRuitoTradeInfo);
       
        //・銘柄情報一覧＝＜繰り返し処理＞で作成した累投銘柄別売買情報オブジェクトの配列
        l_adminRuitoTradeStopInputResponse.productInfoList = 
            l_adminRuitoTradeInfo;
        
        //・オペレーション日付＝取得した現在日付
        l_adminRuitoTradeStopInputResponse.operationDate = l_tsSystemTime;
        
        //・現在日からの発注日＝取引銘柄（当日分）の有効日(*)
        l_adminRuitoTradeStopInputResponse.curBizDate = l_datCurBizDate;
        
        //・現在日からの翌営業日＝取引銘柄（翌日分）の有効日
        l_adminRuitoTradeStopInputResponse.nextBizDate = l_datNextBizDate;
        
        log.exiting(STR_METHOD_NAME);
        return l_adminRuitoTradeStopInputResponse;
    }
    
    /**
     * (validate銘柄別売買停止)<BR>
     * 累投銘柄別売買停止確認処理を実施する。<BR>
     * シーケンス図「（累投管理者）銘柄別売買停止審査」参照<BR>
     * @@param l_request - 累投銘柄別売買停止確認リクエスト
     * @@return webbroker3.common.message.WEB3AdminRuitoTradeStopConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 406932820270
     */
    private WEB3AdminRuitoTradeStopConfirmResponse validateTradeStop(
        WEB3AdminRuitoTradeStopConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradeStop(WEB3AdminRuitoTradeStopConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 入力内容チェック 
        l_request.validate();
        
        //1.3 ログイン情報より管理者オブジェクトを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 該当の管理者がこの機@能が使えるか権限チェックを行う。 
        //validate権限(String, boolean)
        //[引数] 
        //　@機@能カテゴリーコード＝”累投（銘柄管理）” 
        //is更新： false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_RUITO_TRADE_STOP,
            true);
                
        //1.5 オペレーション日付チェック
        Timestamp l_tsSystemTime =  GtlUtils.getTradingSystem().getSystemTimestamp();
        if (!(WEB3DateUtility.compareToDay(l_request.operationDate, l_tsSystemTime) == 0))
        {
            log.debug("オペレーション日付が現在日付ではありません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01354,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "リクエストデータ.オペレーション日付!= 現在日付の場合");                       
        }
        
        //1.6 管理者オブジェクトより所属する証券会社を取得する。
        Institution l_institution = l_web3Administrator.getInstitution();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        log.debug("リクエストデータ.銘柄更新情報の戻り値の件数 = " + 
                l_request.productUpdateInfoList.length);
        
        //1.7 リクエストデータ.銘柄更新情報の戻り値の件数分、繰り返し
        for (int i = 0; i < l_request.productUpdateInfoList.length; i++)
        {
            //1.7.1 累投銘柄の取得 
            //[get累投銘柄に渡す引数]
            //証券会社＝管理者オブジェクトget証券会社()の戻り値
            //銘柄コード＝リクエストデータ.銘柄更新情報.銘柄コード
            RuitoProduct l_ruitoProduct = null;
            try
            {
                l_ruitoProduct = 
                    l_ruitoProductManager.getRuitoProduct(
                        l_institution,
                        l_request.productUpdateInfoList[i].ruitoProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00193,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            RuitoProductRow l_ruitoProductRow = (RuitoProductRow) l_ruitoProduct.getDataSourceObject();
            RuitoProductParams l_ruitoProductParams = 
                new RuitoProductParams(l_ruitoProductRow);
            
            //1.7.2 以下のいずれかがNULLの場合
            if (l_request.productUpdateInfoList[i].buyStartDate == null ||
                l_request.productUpdateInfoList[i].buyEndDate == null ||
                l_request.productUpdateInfoList[i].sellStartDate == null ||
                l_request.productUpdateInfoList[i].sellEndDate == null)
            {
                //(*) リクエストデータ.銘柄更新情報の買付開始日＝NULLの場合、
                //取得した累投銘柄.getDataSourceObject()のget買付開始日()の戻り値とする。
                //（買付終了日、解約開始日、解約終了日も同様に取得すること）
                
                Date l_datBuyStartDate = 
                    l_request.productUpdateInfoList[i].buyStartDate;
                Date l_datBuyEndDate = 
                    l_request.productUpdateInfoList[i].buyEndDate;
                Date l_datSellStartDate = 
                    l_request.productUpdateInfoList[i].sellStartDate;
                Date l_datSellEndDate = 
                    l_request.productUpdateInfoList[i].sellEndDate;
                
                if (l_datBuyStartDate == null)
                {
                    l_datBuyStartDate = l_ruitoProductRow.getBuyStartDate();
                }
                if (l_datBuyEndDate == null)
                {
                    l_datBuyEndDate = l_ruitoProductRow.getBuyEndDate();
                }
                if (l_datSellStartDate == null)
                {
                    l_datSellStartDate = l_ruitoProductRow.getSellStartDate();
                }
                if (l_datSellEndDate == null)
                {
                    l_datSellEndDate = l_ruitoProductRow.getSellEndDate();
                }                
                
                //1.7.3 買付開始日(*)≧買付終了日(*)の場合、例外をスローする。
                //解約開始日(*)≧解約終了日(*)の場合、例外をスローする。
                if (WEB3DateUtility.compareToDay(l_datBuyStartDate, l_datBuyEndDate) >= 0 )
                {
                    log.debug("買付開始日(*)≧買付終了日(*)の場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01240,
                        getClass().getName() + "." + STR_METHOD_NAME, 
                        "買付開始日(*)≧買付終了日(*)の場合");
                }
                if (WEB3DateUtility.compareToDay(l_datSellStartDate, l_datSellEndDate) >= 0)
                {
                    log.debug("解約開始日(*)≧解約終了日(*)の場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01241,
                        getClass().getName() + "." + STR_METHOD_NAME, 
                        "解約開始日(*)≧解約終了日(*)の場合");
                }
            }
        }
        //1.8 createレスポンス( )
        WEB3AdminRuitoTradeStopConfirmResponse l_adminRuitoTradeStopConfirmResponse =
            (WEB3AdminRuitoTradeStopConfirmResponse) 
                l_request.createResponse();   
        
        log.exiting(STR_METHOD_NAME);
        return l_adminRuitoTradeStopConfirmResponse;
    }
    
    /**
     * (submit銘柄別売買停止)<BR>
     * 累投銘柄別売買停止完了処理を実施する。<BR>
     * シーケンス図「（累投管理者）銘柄別売買停止更新」参照<BR>
     * @@param l_request - 累投銘柄別売買停止完了リクエスト
     * @@return webbroker3.common.message.WEB3AdminRuitoTradeStopCompleteResponse<BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 406932820270
     */
    private WEB3AdminRuitoTradeStopCompleteResponse submitTradeStop(
            WEB3AdminRuitoTradeStopCompleteRequest l_request)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitTradeStop(WEB3AdminRuitoTradeStopCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 入力内容チェック 
        l_request.validate();
        
        //1.3 ログイン情報より管理者オブジェクトを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.4 該当の管理者がこの機@能が使えるか権限チェックを行う。 
        //validate権限(String, boolean)
        //[引数] 
        //　@機@能カテゴリーコード＝”累投（銘柄管理）” 
        //  is更新： false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_RUITO_TRADE_STOP,
            true);
        
        //1.5 管理者の暗証番号をチェックする。 
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        //1.6 管理者オブジェクトより所属する証券会社を取得する。
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.7 オペレーション日付チェック
        //現在日付取得
        //GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        Timestamp l_tsSystemTime =  GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //リクエストデータ.オペレーション日付 != 現在日付の場合、例外をスローする。
        if (!(WEB3DateUtility.compareToDay(l_request.operationDate, l_tsSystemTime) == 0))
        {
            log.debug("オペレーション日付が現在日付ではありません。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01354,
                getClass().getName() + "." + STR_METHOD_NAME, 
                "リクエストデータ.オペレーション日付 != 現在日付の場合");                       
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoProductManager l_ruitoProductManager =   
            (WEB3RuitoProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getProductManager();
        
        log.debug("リクエストデータ.銘柄更新情報の戻り値の件数 = " + 
                l_request.productUpdateInfoList.length);
        
        //1.8 リクエストデータ.銘柄更新情報の戻り値の件数分、繰り返し
        for (int i = 0; i < l_request.productUpdateInfoList.length; i++)
        {
            //1.8.1 累投銘柄の取得       
            RuitoProduct l_ruitoProduct = null;
            try
            {
                l_ruitoProduct = 
                    l_ruitoProductManager.getRuitoProduct(
                        l_institution,
                        l_request.productUpdateInfoList[i].ruitoProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__");                
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00193,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        
            RuitoProductRow l_ruitoProductRow =  
                (RuitoProductRow)l_ruitoProduct.getDataSourceObject();
            RuitoProductParams l_ruitoProductParams = 
                new RuitoProductParams(l_ruitoProductRow);
            
            //1.8.2 以下のいずれかがNULLの場合
            if (l_request.productUpdateInfoList[i].buyStartDate == null ||
                l_request.productUpdateInfoList[i].buyEndDate == null ||
                l_request.productUpdateInfoList[i].sellStartDate == null ||
                l_request.productUpdateInfoList[i].sellEndDate == null)
            {
                //(*) リクエストデータ.銘柄更新情報の買付開始日＝NULLの場合、
                //取得した累投銘柄.getDataSourceObject()のget買付開始日()の戻り値とする。
                //（買付終了日、解約開始日、解約終了日も同様に取得すること）
                
                Date l_datBuyStartDate = 
                    l_request.productUpdateInfoList[i].buyStartDate;
                Date l_datBuyEndDate = 
                    l_request.productUpdateInfoList[i].buyEndDate;
                Date l_datSellStartDate = 
                    l_request.productUpdateInfoList[i].sellStartDate;
                Date l_datSellEndDate = 
                    l_request.productUpdateInfoList[i].sellEndDate;
                
                if (l_datBuyStartDate == null)
                {
                    l_datBuyStartDate = l_ruitoProductRow.getBuyStartDate();
                }
                if (l_datBuyEndDate == null)
                {
                    l_datBuyEndDate = l_ruitoProductRow.getBuyEndDate();
                }
                if (l_datSellStartDate == null)
                {
                    l_datSellStartDate = l_ruitoProductRow.getSellStartDate();
                }
                if (l_datSellEndDate == null)
                {
                    l_datSellEndDate = l_ruitoProductRow.getSellEndDate();
                }                
                
                //1.7.3 買付開始日(*)≧買付終了日(*)の場合、例外をスローする。
                //解約開始日(*)≧解約終了日(*)の場合、例外をスローする。
                if (WEB3DateUtility.compareToDay(l_datBuyStartDate, l_datBuyEndDate) >= 0 )
                {
                    log.debug("買付開始日(*)≧買付終了日(*)の場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01240,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "買付開始日(*)≧買付終了日(*)の場合");
                }
                if (WEB3DateUtility.compareToDay(l_datSellStartDate, l_datSellEndDate) >= 0)
                {
                    log.debug("解約開始日(*)≧解約終了日(*)の場合");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01241,
                        getClass().getName() + "." + STR_METHOD_NAME, 
                        "解約開始日(*)≧解約終了日(*)の場合");
                }
            }
            
            try
            {
                //1.8.3 銘柄情報の更新
				QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                //(*)
                //・リクエストデータの値＝NULLの場合、その項目への値セットは行わない。
                //・リクエストデータのセットする値が全てNULLだった場合、その列への更新処理は行わない。
                if (l_request.productUpdateInfoList[i].buyStartDate != null)
                {
                    //・買付開始日＝リクエストデータ.銘柄更新情報.買付開始日(*)
                    l_ruitoProductParams.setBuyStartDate(
                            l_request.productUpdateInfoList[i].buyStartDate);
                }
                if (l_request.productUpdateInfoList[i].buyEndDate != null)
                {
                    //・買付終了日＝リクエストデータ.銘柄更新情報.買付終了日(*)
                    l_ruitoProductParams.setBuyEndDate(
                            l_request.productUpdateInfoList[i].buyEndDate);
                }
                if (l_request.productUpdateInfoList[i].sellStartDate != null)
                {
                    //・解約開始日＝リクエストデータ.銘柄更新情報.解約開始日(*)
                    l_ruitoProductParams.setSellStartDate(
                            l_request.productUpdateInfoList[i].sellStartDate);
                }
                if (l_request.productUpdateInfoList[i].sellEndDate != null)
                {
                    //・解約終了日＝リクエストデータ.銘柄更新情報.解約終了日(*)
                    l_ruitoProductParams.setSellEndDate(
                            l_request.productUpdateInfoList[i].sellEndDate);
                }
                
                //・更新者コード＝管理者オブジェクトより取得した管理者コード            
                l_ruitoProductParams.setLastUpdater(
                        l_web3Administrator.getAdministratorCode());
                
                //・更新日付（オンライン）＝取得した現在日付
                l_ruitoProductParams.setOnlineUpdatedTimestamp(l_tsSystemTime);
                
                //・リクエストデータのセットする値が全てNULLだった場合、その列への更新処理は行わない。
                if (l_request.productUpdateInfoList[i].buyStartDate != null ||
					l_request.productUpdateInfoList[i].buyEndDate != null ||
					l_request.productUpdateInfoList[i].sellStartDate != null ||
					l_request.productUpdateInfoList[i].sellEndDate != null)
                {
                    log.debug("l_ruitoProductParams = " + l_ruitoProductParams);
                    l_queryProcessor.doUpdateQuery(l_ruitoProductParams);
                }
                
            	//1.8.5 －reset受付時間区分()の実施
            	//[reset受付時間区分に渡す引数]
            	//・get累投銘柄一覧()の戻り値（n件目）の累投タイプ＝”MMF”の場合、”MMF（設定）”をセット
            	//・get累投銘柄一覧()の戻り値（n件目）の累投タイプ＝”中国F”の場合、”中国F”をセット。
            	String l_strTradingTimeType = "";
            	if (RuitoTypeEnum.MMF.equals(l_ruitoProductRow.getRuitoType()))
            	{
                	log.debug("get累投銘柄一覧()の戻り値（n件目）の累投タイプ＝”MMF”の場合");
                	l_strTradingTimeType = WEB3TradingTimeTypeDef.MMF_SET;                
            	}
            	else if (RuitoTypeEnum.CHUUKOKU_FUND.equals(l_ruitoProductRow.getRuitoType()))
            	{
                	log.debug("get累投銘柄一覧()の戻り値（n件目）の累投タイプ＝”中国F”の場合");
                	l_strTradingTimeType = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
            	}
            	log.debug("reset受付時間区分 = " + l_strTradingTimeType);
            	WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
                
      	   		//1.8.6 setTimestamp( )
        	    WEB3GentradeTradingTimeManagement.setTimestamp();
				
				//1.8.7 取引銘柄オブジェクトの取得取引銘柄オブジェクトととして、
				//拡張累投取引銘柄オブジェクトを取得する。
				RuitoTradedProduct l_ruitoTradedProduct = null;
				try
				{
					l_ruitoTradedProduct = 
						l_ruitoProductManager.getRuitoTradedProduct(
							l_institution,
							l_request.productUpdateInfoList[i].ruitoProductCode);
				}
				catch (NotFoundException l_ex)
				{
					log.error("拡張累投取引銘柄がない");                
					throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00250,
						getClass().getName() + "." + STR_METHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
				
				//1.8.9 発注日を取得する 
				Date l_datBizDate = 
					WEB3GentradeTradingTimeManagement.getOrderBizDate();
				String l_strBizDate = 
					WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
				
				//1.8.10 is営業日（リクエスト.オペレーション日付）
				Date l_dateSystemDate = WEB3DateUtility.getDate(
					WEB3DateUtility.formatDate(l_tsSystemTime,"yyyyMMdd"),"yyyyMMdd");
				Timestamp l_tsSystemDate = new Timestamp(l_dateSystemDate.getTime()); 
				boolean l_blnBizDate = this.isBizDate(l_tsSystemDate);

				// 当日分の取引銘柄（UPDQ）オブジェクトの更新			
				// 取引銘柄オブジェクト取得処理
				//－以下の条件で、「累投取引銘柄テーブル」を検索する。
				//[検索条件]
				//銘柄コード＝取引銘柄オブジェクトの取引銘柄ID and
				//有効日＝get発注日()の戻り値
				RuitoTradedProductRow l_ruitoTradedProductRow = null;
				RuitoTradedProductUpdqRow l_ruitoTradedProductUpdqRow = null;

				try
				{
					String l_strWhereClause = 
							"traded_product_id = ? and valid_for_biz_date = ?";
							
					log.debug("「累投取引銘柄テーブル」を検索");
					log.debug("取引銘柄ID＝ " + 
							l_ruitoTradedProduct.getTradedProductId() + "");
					log.debug("有効日＝ " + l_strBizDate);
                  
					//DataQueryException,DataNetworkException
					List l_listRutioTradedProductRows =
						Processors.getDefaultProcessor().doFindAllQuery(
							RuitoTradedProductRow.TYPE,
							l_strWhereClause,
							new Object[] { 
								new Long(l_ruitoTradedProduct.getTradedProductId()), 
										l_strBizDate });
										
					//－戻り値 = 0件の場合、取引銘柄UPDQオブジェクトを検索する。
					log.debug("size ="  + l_listRutioTradedProductRows.size());
					if (l_listRutioTradedProductRows.size() == 0)
					{
						//取引銘柄UPDQオブジェクト取得処理
						//－以下の条件で、「累投取引銘柄一時テーブル」を検索する。
						//[検索条件]
						//取引銘柄ID＝取引銘柄オブジェクトの取引銘柄ID and
						//有効日＝get発注日()の戻り値
						log.debug("「累投取引銘柄一時テーブル」を検索");
						log.debug("取引銘柄ID＝ " + 
								l_ruitoTradedProduct.getTradedProductId() + "");
						log.debug("有効日＝ " + l_strBizDate);
                  
						//DataQueryException,DataNetworkException
						List l_listRutioTradedProductUpdqRows =
							Processors.getDefaultProcessor().doFindAllQuery(
								RuitoTradedProductUpdqRow.TYPE,
								l_strWhereClause,
								new Object[] { 
									new Long(l_ruitoTradedProduct.getTradedProductId()), 
											l_strBizDate });
						//－戻り値 !=1件の場合、（データ不整合）として例外をスローする。
						log.debug("size ="  + l_listRutioTradedProductUpdqRows.size());
						if (l_listRutioTradedProductUpdqRows.size() != 1)
						{
							log.debug("データ不整合エラー");
							throw new WEB3SystemLayerException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80006,
								getClass().getName() + "." + STR_METHOD_NAME, 
								"戻り値 !=1件の場合");                       
						}
					
						l_ruitoTradedProductUpdqRow = 
							(RuitoTradedProductUpdqRow)l_listRutioTradedProductUpdqRows.get(0);
						log.debug("l_ruitoTradedProductUpdqRow = " + l_ruitoTradedProductUpdqRow);
						
						//取引銘柄Updqオブジェクトの更新
						//累投取引銘柄一時テーブルParamsに以下のプロパティをセットし、
						//その内容で更新処理を行う。
						RuitoTradedProductUpdqParams l_ruitoTradedProductUpdqParams =
							 new RuitoTradedProductUpdqParams(l_ruitoTradedProductUpdqRow);
							
						// is営業日＝trueの場合
						if (l_blnBizDate)
						{
							log.debug("is営業日()＝trueだった場合");               
							if (l_request.productUpdateInfoList[i].curBuyPosDiv != null)
							{
								//・買付停止＝リクエストデータ.銘柄更新情報.買付可能区分（当日）
								l_ruitoTradedProductUpdqParams.setBuyStop(
										l_request.productUpdateInfoList[i].curBuyPosDiv);
							}
							if (l_request.productUpdateInfoList[i].curSellPosDiv != null)
							{		
								//・解約停止＝リクエストデータ.銘柄更新情報.解約可能区分（当日）
								l_ruitoTradedProductUpdqParams.setSellStop(
										l_request.productUpdateInfoList[i].curSellPosDiv);
							}
				
							//・リクエストデータのセットする値が全てNULLだった場合、その列への更新処理は行わない。
							if (l_request.productUpdateInfoList[i].curBuyPosDiv != null ||
									l_request.productUpdateInfoList[i].curSellPosDiv != null)
							{
								//・更新者コード＝管理者オブジェクトより取得した管理者コード
								l_ruitoTradedProductUpdqParams.setLastUpdater(
										l_web3Administrator.getAdministratorCode());
               
								//・更新日付（オンライン）＝取得した現在日付
								l_ruitoTradedProductUpdqParams.setOnlineUpdatedTimestamp(
										l_tsSystemTime);
               
								log.debug("l_ruitoTradedProductUpdqParams = " + 
									 l_ruitoTradedProductUpdqParams);
								l_queryProcessor.doUpdateQuery(l_ruitoTradedProductUpdqParams);
							}
						}
						// is営業日＝falseの場合
						else if (!l_blnBizDate)
						{
							log.debug("is営業日()＝falseだった場合");  
							if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null)
							{
								//・買付停止＝リクエストデータ.銘柄更新情報.買付可能区分（翌日）
								l_ruitoTradedProductUpdqParams.setBuyStop(
										l_request.productUpdateInfoList[i].nextBuyPosDiv);
							}
							if (l_request.productUpdateInfoList[i].nextSellPosDiv != null)
							{
								//・解約停止＝リクエストデータ.銘柄更新情報.解約可能区分（翌日）
								l_ruitoTradedProductUpdqParams.setSellStop(
										l_request.productUpdateInfoList[i].nextSellPosDiv);
							}
				
							//・リクエストデータのセットする値が全てNULLだった場合、その列への更新処理は行わない。
							if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null ||
									l_request.productUpdateInfoList[i].nextSellPosDiv != null)
							{
								//・更新者コード＝管理者オブジェクトより取得した管理者コード
								l_ruitoTradedProductUpdqParams.setLastUpdater(
										l_web3Administrator.getAdministratorCode());
               					//・更新日付（オンライン）＝取得した現在日付
								l_ruitoTradedProductUpdqParams.setOnlineUpdatedTimestamp(
									l_tsSystemTime);
               					log.debug("l_ruitoTradedProductUpdqParams = " + 
											l_ruitoTradedProductUpdqParams);
								l_queryProcessor.doUpdateQuery(l_ruitoTradedProductUpdqParams);
							}
						}
					}
					
					//－戻り値 >1件の場合、（データ不整合）として例外をスローする。
					else if (l_listRutioTradedProductRows.size() > 1)
					{
						log.debug("データ不整合エラー");
						throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80006,
							getClass().getName() + "." + STR_METHOD_NAME, 
							"戻り値 >1件の場合");                       
					}
					else
					{
						l_ruitoTradedProductRow = (RuitoTradedProductRow)l_listRutioTradedProductRows.get(0);
						log.debug("l_ruitoTradedProductRow = " + l_ruitoTradedProductRow);
						//取引銘柄オブジェクトの更新
						//累投取引銘柄テーブルParamsに以下のプロパティをセットし、
						//その内容で更新処理を行う。
						RuitoTradedProductParams l_ruitoTradedProductParams = 
							new RuitoTradedProductParams(l_ruitoTradedProductRow);

						if (l_request.productUpdateInfoList[i].curBuyPosDiv != null)
						{
							//・買付停止＝リクエストデータ.銘柄更新情報.買付可能区分（当日）
							l_ruitoTradedProductParams.setBuyStop(
									l_request.productUpdateInfoList[i].curBuyPosDiv);
						}
						if (l_request.productUpdateInfoList[i].curSellPosDiv != null)
						{
							//・解約停止＝リクエストデータ.銘柄更新情報.解約可能区分（当日）
							l_ruitoTradedProductParams.setSellStop(
									l_request.productUpdateInfoList[i].curSellPosDiv);
						}
						//・リクエストデータのセットする値が全てNULLだった場合、その列への更新処理は行わない。
						if (l_request.productUpdateInfoList[i].curBuyPosDiv != null ||
								l_request.productUpdateInfoList[i].curSellPosDiv != null)
						{
							//・更新者コード＝管理者オブジェクトより取得した管理者コード
							l_ruitoTradedProductParams.setLastUpdater(
									l_web3Administrator.getAdministratorCode());
               
							//・更新日付（オンライン）＝取得した現在日付
							l_ruitoTradedProductParams.setOnlineUpdatedTimestamp(
									l_tsSystemTime);
              
							log.debug("l_ruitoTradedProductParams = " + 
									l_ruitoTradedProductParams);
							l_queryProcessor.doUpdateQuery(l_ruitoTradedProductParams);
						}
					}
				}
				catch (DataQueryException l_ex)
				{
					log.error("__DataQueryException__");
					throw new WEB3BaseRuntimeException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80003,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
				catch (DataNetworkException l_ex)
				{
					log.error("__DataNetworkException__");
					throw new WEB3BaseRuntimeException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80003,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						l_ex.getMessage(),
						l_ex);
				}
            
				// is営業日＝trueの場合、翌営業日分の取引銘柄UPDQオブジェクトの更新
				if (l_blnBizDate)
				{					
					//[rollに渡す引数]
					//加算／減算日数＝１をセット。（翌日分を指定）
					int l_intRoll = 0;
					RuitoTradedProductUpdqRow l_ruitoTradedProductUpdqRowNextDate = null;
					String l_strNextBizDate = null;
					Date l_datNextBizDate = null;
					try
					{
						log.debug("（翌日分を指定）");
						l_intRoll = 1;
                   
						log.debug("[rollに渡す引数]=" + l_intRoll);
						l_datNextBizDate = new WEB3GentradeBizDate(
								new Timestamp(l_datBizDate.getTime())).roll(l_intRoll);
               
						l_strNextBizDate = 
							WEB3DateUtility.formatDate(l_datNextBizDate, "yyyyMMdd");
                   
						// 取引銘柄UPDQオブジェクト取得処理
						//－以下の条件で、「累投取引銘柄一時テーブル」を検索する。
						//[検索条件]
						//取引銘柄ID＝取引銘柄オブジェクトの取引銘柄ID and
						//有効日＝roll()の戻り値
						String l_strWhereClause = 
								"traded_product_id = ? and valid_for_biz_date = ?";
                   
						log.debug("取引銘柄ID＝ " + 
								l_ruitoTradedProduct.getTradedProductId() + "");
						log.debug("有効日＝ " + l_strNextBizDate);
                   
						//DataQueryException,DataNetworkException
						List l_lisRows =
							Processors.getDefaultProcessor().doFindAllQuery(
								RuitoTradedProductUpdqRow.TYPE,
								l_strWhereClause,
								new Object[] { 
									new Long(l_ruitoTradedProduct.getTradedProductId()), 
											l_strNextBizDate });
    					//－戻り値 !=1件の場合、（データ不整合）として例外をスローする。
						log.debug("size ="  + l_lisRows.size());
						if (l_lisRows.size() != 1)
						{
							log.debug("データ不整合エラー");
							throw new WEB3SystemLayerException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80006,
								getClass().getName() + "." + STR_METHOD_NAME, 
								"戻り値 !=1件の場合");                       
						}
						l_ruitoTradedProductUpdqRowNextDate = 
							(RuitoTradedProductUpdqRow)l_lisRows.get(0);
						log.debug("l_ruitoTradedProductUpdqRowNextDate = " 
								+ l_ruitoTradedProductUpdqRowNextDate);
					
						//取引銘柄UPDQオブジェクトの更新
						//累投取引銘柄一時テーブルParamsに以下のプロパティをセットし、
						//その内容で更新処理を行う。
						RuitoTradedProductUpdqParams l_ruitoTradedProductUpdqParamsNextDate = 
							new RuitoTradedProductUpdqParams(l_ruitoTradedProductUpdqRowNextDate);

						if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null)
						{
							//・買付停止＝リクエストデータ.銘柄更新情報.買付可能区分（翌日）
							l_ruitoTradedProductUpdqParamsNextDate.setBuyStop(
									l_request.productUpdateInfoList[i].nextBuyPosDiv);
						}
						if (l_request.productUpdateInfoList[i].nextSellPosDiv != null)
						{
							//・解約停止＝リクエストデータ.銘柄更新情報.解約可能区分（翌日）
							l_ruitoTradedProductUpdqParamsNextDate.setSellStop(
									l_request.productUpdateInfoList[i].nextSellPosDiv);
						}               
              
						//・リクエストデータのセットする値が全てNULLだった場合、その列への更新処理は行わない。
						if (l_request.productUpdateInfoList[i].nextBuyPosDiv != null ||
								l_request.productUpdateInfoList[i].nextSellPosDiv != null)
						{
							//・更新者コード＝管理者オブジェクトより取得した管理者コード
							l_ruitoTradedProductUpdqParamsNextDate.setLastUpdater(
									l_web3Administrator.getAdministratorCode());
             
							//・更新日付（オンライン）＝取得した現在日付
							l_ruitoTradedProductUpdqParamsNextDate.setOnlineUpdatedTimestamp(
									l_tsSystemTime);
							
							log.debug("l_ruitoTradedProductUpdqParamsNextDate = "
							 		 + l_ruitoTradedProductUpdqParamsNextDate);
							l_queryProcessor.doUpdateQuery(l_ruitoTradedProductUpdqParamsNextDate);
						}
					}
					catch (DataQueryException l_ex)
					{
						log.error("__DataQueryException__");
						throw new WEB3BaseRuntimeException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80003,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							l_ex.getMessage(),
							l_ex);
					}
					catch (DataNetworkException l_ex)
					{
						log.error("__DataNetworkException__");
						throw new WEB3BaseRuntimeException(
							WEB3ErrorCatalog.SYSTEM_ERROR_80003,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							l_ex.getMessage(),
							l_ex);
					}
				}
			}            
			catch (DataQueryException l_ex)
			{
				log.error("__DataQueryException__");
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
			catch (DataNetworkException l_ex)
			{
				log.error("__DataNetworkException__");
				throw new WEB3BaseRuntimeException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}
        }
        //1.9 createレスポンス( )       
        WEB3AdminRuitoTradeStopCompleteResponse l_adminRuitoTradeStopCompleteResponse =
            (WEB3AdminRuitoTradeStopCompleteResponse) 
                l_request.createResponse();   
        
        log.exiting(STR_METHOD_NAME);
        return l_adminRuitoTradeStopCompleteResponse;
    }
    
    /**
     * (is営業日)<BR>
     *引数.対象日付が休日かどうかを判定する。 <BR>
     *<BR>
     *１）引数.対象日付が”土曜日”または”日曜日”の場合、falseを返却する。<BR> 
     *<BR>
     *２）以下の条件で「カレンダーテーブル」を検索する。<BR>
     *[検索条件] <BR>
　@   *日付＝引数.対象日付 and <BR>
　@   *営業日区分＝”非営業日” <BR>
     *－検索結果の件数＞0件の場合、falseを返却する。<BR>
     *<BR>
     *３）上記以外の場合、trueを返却する。<BR>
     * @@param l_timestap - 対象日付
     * @@return l_bln
     * @@roseuid 406932820270
     */
    private boolean isBizDate(Timestamp l_tsObjectDate)
    {
        final String STR_METHOD_NAME =
            "submitTradeStop(WEB3AdminRuitoTradeStopCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);   
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_tsObjectDate);
        
        int l_intWeekDiv = l_calendar.get(Calendar.DAY_OF_WEEK) - 1;
        
        //１）引数.対象日付が”土曜日”または”日曜日”の場合、falseを返却する。 
        if (l_intWeekDiv == 6 || l_intWeekDiv == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）以下の条件で「カレンダーテーブル」を検索する。
        try
        {
            String l_strWhereClause = 
                "holiday = ? and biz_date_type = ?";
            //DataQueryException,DataNetworkException
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                    l_strWhereClause,
                    new Object[] { 
                        l_tsObjectDate, 
                        WEB3BizDateTypeDef.NO_BIZ_DATE });

            if (l_lisRows.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;                      
            }           
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
