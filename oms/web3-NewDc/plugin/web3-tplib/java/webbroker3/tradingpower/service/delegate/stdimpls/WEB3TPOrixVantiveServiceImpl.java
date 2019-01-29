head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixVantiveServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : Vantive連携サービスImplクラス(WEB3TPOrixVantiveServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) 新規作成
 Revision History : 2008/10/22 孟亞南 (中訊) QA:WEB3-TPLIB-A-CD-0087
 */
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.text.*;
import java.util.*;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPMarginSecurityInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.data.OrixTpCalcResultEquityParams;
import webbroker3.tradingpower.data.OrixTpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.define.WEB3TPTradingStopDivDef;
import webbroker3.tradingpower.message.*;
import webbroker3.tradingpower.service.delegate.WEB3TPOrixVantiveService;
import webbroker3.util.WEB3LogUtility;

/**
 * (Vantive連携サービスImpl)<BR>
 * Vantive連携サービス実装クラス。<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public class WEB3TPOrixVantiveServiceImpl implements WEB3TPOrixVantiveService 
{

    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TPOrixVantiveServiceImpl.class);

    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();
   
   /**
    * (コンストラクタ)
    */
   public WEB3TPOrixVantiveServiceImpl()
   {
   }
   
   /**
    * (execute)<BR>
    * 余力計算結果サービス処理を実施する。<BR>
    * 
    * @@param l_request
    * @@return webbroker3.common.message.WEB3BackResponse
    * @@throws webbroker3.common.WEB3BaseException
    */
   public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
   {
       final String METHOD_NAME = "execute(WEB3BackRequest)";
       log.entering(METHOD_NAME);
       
       //余力計算結果
       if( l_request instanceof WEB3TPOrixTpCalcResultRequest )
       {
           return createOrixTpCalcResultResponse( (WEB3TPOrixTpCalcResultRequest)l_request );
       }
       else 
       {
           log.error("不正なリクエストです。");
           throw new WEB3BaseRuntimeException( WEB3ErrorCatalog.SYSTEM_ERROR_80018, "execute()" );
       }    
   }


   /**
    * (create余力計算結果)<BR>
    * 余力計算結果処理を実施する。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「（余力計算結果サービス）create余力計算結果」参照。<BR>
    * <BR>
    * @@param l_request
    * @@return webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultResponse
    */
    protected WEB3TPOrixTpCalcResultResponse createOrixTpCalcResultResponse(WEB3TPOrixTpCalcResultRequest l_request) throws WEB3BaseException 
    {

        final String INST_CODE               = "46";    /* 会社コード */
        final String BR_CODE                 = "307";   /* 部店コード */
        final String MORNING_PROC            = "1";     /* 処理区分 1:早朝起動 */
        final String EVENING_PROC            = "2";     /* 処理区分 2:夕方起動 */
        final String STR_METHOD_NAME         = "createOrixTpCalcResultResponse(WEB3TPOrixTpCalcResultRequest)"; /* メソッド名 */
        final int DEFAULT_COMMIT_POINT       = 1000;    /* デフォルトコミットポイント */

        log.entering(STR_METHOD_NAME);

        //処理区分が未指定の場合
        if ((l_request.procDiv.equals(MORNING_PROC) == false) && 
            (l_request.procDiv.equals(EVENING_PROC) == false))
        {
            //エラーをスロー
            log.error("処理区分が未指定です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //From-Toの指定が逆の場合
        if (l_request.fromAccountID > l_request.toAccountID)
        {
            //エラーをスロー
            log.error("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //レスポンス取得
        WEB3TPOrixTpCalcResultResponse l_response = (WEB3TPOrixTpCalcResultResponse)l_request.createResponse();

        // リクエスト取得
        long  l_lngFromAccountId = l_request.fromAccountID; /* From口座ID */
        long  l_lngToAccountId   = l_request.toAccountID;   /* To口座ID */
        String l_strProcDiv      = l_request.procDiv;       /* 処理区分 */

        //レスポンスへFrom口座IDとTo口座IDをセット
        l_response.fromAccountID = l_request.fromAccountID;
        l_response.toAccountID   = l_request.toAccountID;

        //コミットポイントを取得
        int l_intCommitPoint;
        if (GtlUtils.getTradingSystem().getPreference("system.tpvantive.commit.count") != null)
        {
        	//SYSTEM_PREFERENCESから取得
        	l_intCommitPoint = Integer.parseInt(GtlUtils.getTradingSystem().getPreference("system.tpvantive.commit.count"));
        }
        else
        {
        	//デフォルトのコミットポイントをセット
        	l_intCommitPoint = DEFAULT_COMMIT_POINT;
        }
        
        int l_intFromIndex;                                 /* 余力計算Paramsオブジェクトの開始Index */
		int l_intToIndex;                                   /* 余力計算Paramsオブジェクトの終了Index */
        StringBuffer l_strWhereBuf   = null;                /* SQL WHERE句格納文字列バッファ@ */
        String l_strWhere            = null;                /* SQL WHERE句格納文字列 */ 
        StringBuffer l_strOrderByBuf = null;                /* SQL ORDER BY句格納文字列バッファ@ */
        String l_strOrderBy          = null;                /* SQL ORDER BY句格納文字列 */

        //Vantive連携TransactionCallback()
        WEB3TPOrixVantiveTransactionCallback l_transactionCallBack =
            new WEB3TPOrixVantiveTransactionCallback();
        
    	//現物顧客の取得(早朝起動の時のみ起動)
        if (l_strProcDiv.equals(MORNING_PROC))
        {
	        List l_lisTpCalcResultEquityParams       = null;
	        List l_lisTpCalcResultEquityDetailParams = null;
	        try 
	        {
	            Object[] l_bindVars =
                {
                new Long(l_lngFromAccountId),
                new Long(l_lngToAccountId)
                };
	            QueryProcessor l_qp = Processors.getDefaultProcessor();
	            //顧客毎の直近の余力計算結果<現物顧客>Paramの取得
	            //where句
	            l_strWhereBuf = new StringBuffer(" ");
	            l_strWhereBuf.append("(account_id,created_timestamp) IN ");
	            l_strWhereBuf.append("( ");
	            l_strWhereBuf.append("  SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_equity ");
	            l_strWhereBuf.append("  WHERE account_id BETWEEN ? AND ? ");
	            l_strWhereBuf.append("  GROUP BY account_id ");
	            l_strWhereBuf.append(") ");
	            l_strWhere = l_strWhereBuf.toString();
	            //order by句
	            l_strOrderByBuf = new StringBuffer(" ");
	            l_strOrderByBuf.append("account_id");
	            l_strOrderBy = l_strOrderByBuf.toString();
	            l_lisTpCalcResultEquityParams = l_qp.doFindAllQuery(TpCalcResultEquityParams.TYPE,
	                                            l_strWhere,
	                                            l_strOrderBy,
	                                            null,
	                                            l_bindVars,
												new RowType[] {TpCalcResultEquityParams.TYPE});
	            //顧客毎の直近の余力計算結果詳細<現物顧客>Paramの取得
	            //where句
	            l_strWhereBuf = new StringBuffer(" ");
	            l_strWhereBuf.append("calc_result_equity_id IN ");
	            l_strWhereBuf.append("( ");
	            l_strWhereBuf.append("  SELECT calc_result_equity_id FROM tp_calc_result_equity ");
	            l_strWhereBuf.append("  WHERE (account_id,created_timestamp) IN ");
	            l_strWhereBuf.append("  ( ");
	            l_strWhereBuf.append("    SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_equity ");
	            l_strWhereBuf.append("    WHERE account_id BETWEEN ? AND ? ");
	            l_strWhereBuf.append("    GROUP BY account_id ");
	            l_strWhereBuf.append("  ) ");
	            l_strWhereBuf.append(") ");
	            l_strWhere = l_strWhereBuf.toString();
	            //order by句
	            l_strOrderByBuf = new StringBuffer(" ");
	            l_strOrderByBuf.append("account_id");
	            l_strOrderBy = l_strOrderByBuf.toString();
	            l_lisTpCalcResultEquityDetailParams = l_qp.doFindAllQuery(TpCalcResultEquityDetailParams.TYPE,
	                                            l_strWhere,
	                                            l_strOrderBy,
	                                            null,
	                                            l_bindVars,
												new RowType[] {TpCalcResultEquityDetailParams.TYPE});
	        } 
	        catch (DataNetworkException e)
	        {
	            log.error( e.getMessage(), e);
	            throw new WEB3BaseRuntimeException(
	                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                           "余力計算結果<現物顧客>のクエリ取得に失敗しました。");
	        } 
	        catch (DataQueryException e) 
	        {
	            log.error( e.getMessage(), e);
	            throw new WEB3BaseRuntimeException(
	                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                       "余力計算結果<現物顧客>のクエリ取得に失敗しました。");
	        }
			log.info("現物顧客取得件数 : "+Long.toString(l_lisTpCalcResultEquityParams.size()));
	        
			l_intFromIndex = 0;
			l_intToIndex = 0;
	    	while (l_intToIndex < l_lisTpCalcResultEquityParams.size())
	    	{
	            try
	            {
	            	l_intFromIndex = l_intToIndex;
	                if( l_lisTpCalcResultEquityParams.size() < (l_intFromIndex + l_intCommitPoint))
	                {
	                	l_intToIndex = l_lisTpCalcResultEquityParams.size();
	                }
	                else
	                {
	                	l_intToIndex = l_intFromIndex + l_intCommitPoint;
	                }
	            	l_transactionCallBack.setTpCalcResultParams(l_lisTpCalcResultEquityParams.subList(l_intFromIndex, l_intToIndex));
	            	l_transactionCallBack.setTpCalcResultDetailParams(l_lisTpCalcResultEquityDetailParams.subList(l_intFromIndex, l_intToIndex));
	            	l_transactionCallBack.setBlnMargin(false);
	                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
	                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);
	                l_response.equityRows = l_response.equityRows + l_transactionCallBack.getAffectedRows();
					log.info("現物顧客:" + Long.toString(l_transactionCallBack.getAffectedRows()) + "件コミットしました。");
	            }
	            catch (DataNetworkException dne)
	            {
	                log.error(dne.getMessage(), dne);
	                throw new WEB3BaseRuntimeException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                    STR_METHOD_NAME,
	                    dne.getMessage(),
	                    dne);
	            }
	            catch (DataCallbackException dce)
	            {
	                log.error(dce.getMessage(), dce);
	                throw new WEB3BaseRuntimeException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                    STR_METHOD_NAME,
	                    dce.getMessage(),
	                    dce);
	            }
	            catch (DataException de)
	            {
	                log.error(de.getMessage(), de);
	                throw new WEB3BaseRuntimeException(
	                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
	                    STR_METHOD_NAME,
	                    de.getMessage(),
	                    de);
	            }
	            finally
				{
	                l_response.equityRows = l_transactionCallBack.getAffectedRows();
				}
	    	}
        }

        //トランザクションコールバックオブジェクトの挿入件数の0クリア
        l_transactionCallBack.setAffectedRows(0);

        //信用顧客の取得
        List l_lisTpCalcResultMarginParams = null;
        List l_lisTpCalcResultMarginDetailParams = null;
        try 
        {
            Object[] l_bindVars =
            {
            new Long(l_lngFromAccountId),
            new Long(l_lngToAccountId)
            };
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            //顧客毎の直近の余力計算結果<信用顧客>Paramの取得
            //where句
            l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("(account_id,created_timestamp) IN ");
            l_strWhereBuf.append("( ");
            l_strWhereBuf.append("  SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_margin ");
            l_strWhereBuf.append("  WHERE account_id BETWEEN ? AND ? ");
            l_strWhereBuf.append("  GROUP BY account_id ");
            l_strWhereBuf.append(") ");
            l_strWhere = l_strWhereBuf.toString();
            //order by句
            l_strOrderByBuf = new StringBuffer(" ");
            l_strOrderByBuf.append("account_id");
            l_strOrderBy = l_strOrderByBuf.toString();
            l_lisTpCalcResultMarginParams = l_qp.doFindAllQuery(TpCalcResultMarginParams.TYPE,
                                            l_strWhere,
                                            l_strOrderBy,
                                            null,
                                            l_bindVars,
											new RowType[] {TpCalcResultMarginParams.TYPE});
            //顧客毎の直近の余力計算結果詳細<信用顧客>Paramの取得
            //where句
            l_strWhereBuf = new StringBuffer(" ");
            l_strWhereBuf.append("calc_result_margin_id IN ");
            l_strWhereBuf.append("( ");
            l_strWhereBuf.append("  SELECT calc_result_margin_id FROM tp_calc_result_margin ");
            l_strWhereBuf.append("  WHERE (account_id,created_timestamp) IN ");
            l_strWhereBuf.append("  ( ");
            l_strWhereBuf.append("    SELECT account_id,MAX(created_timestamp) FROM tp_calc_result_margin ");
            l_strWhereBuf.append("    WHERE account_id BETWEEN ? AND ? ");
            l_strWhereBuf.append("    GROUP BY account_id ");
            l_strWhereBuf.append("  ) ");
            l_strWhereBuf.append(") ");
            l_strWhere = l_strWhereBuf.toString();
            //order by句
            l_strOrderByBuf = new StringBuffer(" ");
            l_strOrderByBuf.append("account_id");
            l_strOrderBy = l_strOrderByBuf.toString();
            l_lisTpCalcResultMarginDetailParams = l_qp.doFindAllQuery(TpCalcResultMarginDetailParams.TYPE,
                                            l_strWhere,
                                            l_strOrderBy,
                                            null,
                                            l_bindVars,
											new RowType[] {TpCalcResultMarginDetailParams.TYPE});
        } 
        catch (DataNetworkException e)
        {
            log.error( e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                       "余力計算結果<信用顧客>のクエリ取得に失敗しました。");
        } 
        catch (DataQueryException e) 
        {
            log.error( e.getMessage(), e);
            throw new WEB3BaseRuntimeException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                       "余力計算結果<信用顧客>のクエリ取得に失敗しました。");
        }
		log.info("信用顧客取得件数 : "+Long.toString(l_lisTpCalcResultMarginParams.size()));
        
		l_intFromIndex = 0;
		l_intToIndex = 0;
    	while (l_intToIndex < l_lisTpCalcResultMarginParams.size())
    	{
            try
            {
            	l_intFromIndex = l_intToIndex;
                if( l_lisTpCalcResultMarginParams.size() < (l_intFromIndex + l_intCommitPoint))
                {
                	l_intToIndex = l_lisTpCalcResultMarginParams.size();
                }
                else
                {
                	l_intToIndex = l_intFromIndex + l_intCommitPoint;
                }
            	l_transactionCallBack.setTpCalcResultParams(l_lisTpCalcResultMarginParams.subList(l_intFromIndex, l_intToIndex));
            	l_transactionCallBack.setTpCalcResultDetailParams(l_lisTpCalcResultMarginDetailParams.subList(l_intFromIndex, l_intToIndex));
            	l_transactionCallBack.setBlnMargin(true);
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doTransaction(QueryProcessor.TX_CREATE_NEW, l_transactionCallBack);
				log.info("信用顧客:" + Long.toString(l_transactionCallBack.getAffectedRows()) + "件コミットしました。");
            }
            catch (DataNetworkException dne)
            {
                log.error(dne.getMessage(), dne);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dne.getMessage(),
                    dne);
            }
            catch (DataCallbackException dce)
            {
                log.error(dce.getMessage(), dce);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    dce.getMessage(),
                    dce);
            }
            catch (DataException de)
            {
                log.error(de.getMessage(), de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            finally
			{
                l_response.marginRows = l_transactionCallBack.getAffectedRows();
			}
    	}    	
    	log.exiting(STR_METHOD_NAME);
        //レスポンス返却
        return l_response;
    }
    /**
     * (Vantive連携トランザクションコールバック)
     */
    public class WEB3TPOrixVantiveTransactionCallback implements TransactionCallback
    {
		private List tpCalcResultParams;
		private List tpCalcResultDetailParams;
    	private boolean blnMargin;
    	private int affectedRows;

    	/**
         * (コンストラクタ)
         */
        public WEB3TPOrixVantiveTransactionCallback()
        {

        }

        /**
         * (process)
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "WEB3TPOrixVantiveTransactionCallback.process()";
            final String INST_CODE               = "46";    /* 会社コード */
            final String BR_CODE                 = "307";   /* 部店コード */
            final String NOT_DEPOSIT_CUSTOMER    = "0";     /* 預り証券顧客でない */
            final String DEPOSIT_CUSTOMER        = "1";     /* 預り証券顧客 */
            final String DEPOSIT_MARGIN          = "2";     /* 信用顧客属性 */
            final String TRADING_STOP_ORIX       = "2";     /* 余力自動停止フラグ (取引停止 or 余力不可)ORIX連携 */
            final String MARGIN_SEC_RATE_DEFAULT = "00000"; /* 二階建銘柄コード 初期値 */
            final double MARGIN_SEC_RATE_MAX    = 999.99;  /* 二階建銘柄占有率 最大値 */
            log.entering(STR_METHOD_NAME);

            Iterator l_iteTpCalcResultParams = tpCalcResultParams.iterator();
            Iterator l_iteTpCalcResultDetailParams = tpCalcResultDetailParams.iterator();

            //取引余力サービス取得
            WEB3TPTradingPowerService l_tpService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            // システム日付の取得
            SimpleDateFormat l_sdfYMD = new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            Date l_datSysDate = new Date();

            //現物顧客の場合
            if (blnMargin == false)
            {
                while(l_iteTpCalcResultParams.hasNext())
                {
                    //余力計算結果<現物顧客>Paramsを取得
                	TpCalcResultEquityParams l_tpCalcResultEquityParams = (TpCalcResultEquityParams)l_iteTpCalcResultParams.next();

                	//余力計算結果詳細<現物顧客>Paramsを取得
                	TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams = (TpCalcResultEquityDetailParams)l_iteTpCalcResultDetailParams.next();

                    //補助口座オブジェクトを取得
                    WEB3GentradeSubAccount l_subAccount = null;
                    try
                    {
                        l_subAccount = 
                            new WEB3GentradeSubAccount(
                                (SubAccountRow) new WEB3GentradeMainAccount(l_tpCalcResultEquityParams.getAccountId()).getSubAccount(
                                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getDataSourceObject());
                    }
                    catch (NotFoundException nfe)
                    {
                        log.error(nfe.getMessage(), nfe);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00670,
                            STR_METHOD_NAME,
                            nfe);
                    }

                    //資産余力情報<現物顧客>の取得
                    WEB3TPTradingPowerCalcEquity l_tpCalcEquity;
					try {
                        l_tpCalcEquity = l_tpService.getTradingPowerCalcEquity(l_subAccount,
                                                                               l_tpCalcResultEquityParams,
                                                                               l_tpCalcResultEquityDetailParams);
					} catch (WEB3SystemLayerException se) {
                        log.error("資産余力情報<現物顧客>の取得に失敗しました。CalcResultEquityId : " + 
                      		      Long.toString(l_tpCalcResultEquityParams.getCalcResultEquityId()));
                        throw new WEB3BaseRuntimeException(
                        		WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                STR_METHOD_NAME,
                                "資産余力情報<現物顧客>の取得に失敗しました。CalcResultEquityId : " + 
								Long.toString(l_tpCalcResultEquityParams.getCalcResultEquityId()),
    							se);
					}
					log.debug("Equity:" + Long.toString(l_tpCalcResultEquityParams.getAccountId()));
					//オリックス余力計算結果<現物顧客>Paramsを生成する
                    OrixTpCalcResultEquityParams l_calcResultEquityParams = new OrixTpCalcResultEquityParams();
                    //識別番号
                    l_calcResultEquityParams.setCalcResultEquityId(l_tpCalcEquity.getCalcResultEquity().calc_result_equity_id);
                    //口座ID
                    l_calcResultEquityParams.setAccountId(l_tpCalcResultEquityParams.getAccountId());
                    //会社コード
                    l_calcResultEquityParams.setInstitutionCode(INST_CODE);
                    //作業年月日
                    l_calcResultEquityParams.setWorkDate(l_sdfYMD.format(l_datSysDate));
                    //部店コード
                    l_calcResultEquityParams.setBranchCode(BR_CODE);
                    //口座コード
                    l_calcResultEquityParams.setAccountCode(Long.toString(l_tpCalcResultEquityParams.getAccountId()).substring(8, 14));
                    //顧客属性
                    //預り証券評価制顧客の場合
                    if (l_tpCalcEquity.getCalcCondition().isAssetEvalDiv())
                    {
                        l_calcResultEquityParams.setAssetEvaluationDiv(DEPOSIT_CUSTOMER);
                    }
                    //預り証券評価制顧客でない場合
                    else
                    {
                        l_calcResultEquityParams.setAssetEvaluationDiv(NOT_DEPOSIT_CUSTOMER);
                    }
                    //預り金(T+0～T+4) = get預り金残高(T+0～T+4) - get当日約定済代金(T+0～T+4)
                    l_calcResultEquityParams.setAccountBalance0((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_0) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultEquityParams.setAccountBalance1((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_1) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setAccountBalance2((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_2) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setAccountBalance3((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_3) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setAccountBalance4((long)l_tpCalcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_4) -
                                                                (long)l_tpCalcEquity.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //発注充当金(T+1～T+4) = get当日未約定代金(T+1～T+4)
                    l_calcResultEquityParams.setTodayUnexecutedAmount1((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setTodayUnexecutedAmount2((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setTodayUnexecutedAmount3((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setTodayUnexecutedAmount4((long)l_tpCalcEquity.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //日計り調整額(T+0～T+4) = get日計り拘束金(T+0～T+4)
                    l_calcResultEquityParams.setDayTradeRestraint0((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultEquityParams.setDayTradeRestraint1((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setDayTradeRestraint2((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setDayTradeRestraint3((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setDayTradeRestraint4((long)l_tpCalcEquity.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //その他拘束金(T+0～T+4) = getその他拘束金(T+0～T+4)
                    l_calcResultEquityParams.setOtherRestraint0((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultEquityParams.setOtherRestraint1((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setOtherRestraint2((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setOtherRestraint3((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setOtherRestraint4((long)l_tpCalcEquity.getOtherRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //預り証券評価制(T+3～T+4) = get預り証券評価制(T+3～T+4)
                    l_calcResultEquityParams.setTrustSecurityAsset3((long)l_tpCalcEquity.getTrustSecurityAsset(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setTrustSecurityAsset4((long)l_tpCalcEquity.getTrustSecurityAsset(WEB3TPSpecifiedPointDef.T_4));
                    //現物買付余力(T+3～T+4) = calc株式買付可能額(T+3～T+4)
                    l_calcResultEquityParams.setEquityTradingPower3((long)l_tpCalcEquity.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setEquityTradingPower4((long)l_tpCalcEquity.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //現物買付余力(T+4') = calc株式買付可能額<日計り拘束金考慮>(T+4)
                    l_calcResultEquityParams.setEquityTradingPower4Dash((long)l_tpCalcEquity.calcEquityTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //現物買付現金余力(T+3～T+4) = calc使用可能現金(T+3～T+4)
                    l_calcResultEquityParams.setActualAccountBalance3((long)l_tpCalcEquity.calcActualAccountBalance(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setActualAccountBalance4((long)l_tpCalcEquity.calcActualAccountBalance(WEB3TPSpecifiedPointDef.T_4));
                    //現物買付現金余力(T+4') = calc引出可能現金(T+4')
                    l_calcResultEquityParams.setActualAccountBalance4Dash((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_4));
                    //現金余力(T+1～T+4) = calc引出可能現金(T+1～T+4)
                    l_calcResultEquityParams.setActualPaymentBalance1((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultEquityParams.setActualPaymentBalance2((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultEquityParams.setActualPaymentBalance3((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultEquityParams.setActualPaymentBalance4((long)l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_4));
                    //出金実績・予定(T+0～T+2) = get出金額(T+0～T+2)
                    l_calcResultEquityParams.setPaymentAmountDesignate0((long)l_tpCalcEquity.getCalcResultDetailEquity().getPaymentAmountDesignate0());
                    l_calcResultEquityParams.setPaymentAmountDesignate1((long)l_tpCalcEquity.getCalcResultDetailEquity().getPaymentAmountDesignate1());
                    l_calcResultEquityParams.setPaymentAmountDesignate2((long)l_tpCalcEquity.getCalcResultDetailEquity().getPaymentAmountDesignate2());
                    //株式評価額(保護、代用対象。100%評価) = get株式評価額(約残)
                    l_calcResultEquityParams.setEquityAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getEquityAssetExecuted());
                    //累積投資(MMF等)評価額 = get累積投資評価額(約残)
                    l_calcResultEquityParams.setRuitoAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getRuitoAssetExecuted());
                    //投資信託評価額 = get投資信託評価額(約残)
                    l_calcResultEquityParams.setMutualFundAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getMutualFundAssetExecuted());
                    //債券評価額 = get債券評価額(約残)
                    l_calcResultEquityParams.setBondAssetExecuted((long)l_tpCalcEquity.getCalcResultDetailEquity().getBondAssetExecuted());
                    //余力自動停止フラグ
                    //取引停止の場合
                    if (l_tpCalcEquity.getCalcCondition().isTradingStop()) 
                    {
                        l_calcResultEquityParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //取引可能の場合
                    else
                    {
                        //calc引出可能現金が0未満の場合
                        if (l_tpCalcEquity.calcActualPaymentBalance(WEB3TPSpecifiedPointDef.T_0) < 0) 
                        {
                            l_calcResultEquityParams.setTradingStop(TRADING_STOP_ORIX);
                        }
                        //calc引出可能現金が0以上の場合
                        else
                        {
                            l_calcResultEquityParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                        }                  
                    }
                    //出金余力停止フラグ
                    //余力可の場合
                    if (l_tpCalcEquity.getCalcCondition().isPaymentStop()) 
                    {
                        l_calcResultEquityParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //余力不可の場合
                    else
                    {
                        l_calcResultEquityParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //その他商品買付余力停止フラグ
                    //余力可の場合
                    if (l_tpCalcEquity.getCalcCondition().isOtherTradingStop()) 
                    {
                        l_calcResultEquityParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //余力不可の場合
                    else
                    {
                        l_calcResultEquityParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //作成日付
                    l_calcResultEquityParams.setCreatedTimestamp(l_datSysDate);
                    //タイムスタンプ
                    l_calcResultEquityParams.setLastUpdatedTimestamp(l_datSysDate);
                    /*
                     * 生成した、オリックス余力計算結果(現物顧客)Paramsを、オリックス余力計算結果(現物顧客)テーブルにInsertする。
                     */
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();
                        l_processor.doInsertQuery(l_calcResultEquityParams);
                        affectedRows++;
                    }
                    catch (DataException de)
                    {
                        log.error(de.getMessage(), de);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                            STR_METHOD_NAME,
                            de.getMessage() +
                            "calc_result_equity_Id : " + l_calcResultEquityParams.getCalcResultEquityId() + "," +
							"account_Id : " + l_calcResultEquityParams.getAccountId(),
                            de);
                    }
                }
            }
            //信用顧客の場合
            else
            {
                while(l_iteTpCalcResultParams.hasNext())
                {
                    //余力計算結果<信用顧客>Paramsを取得
                	TpCalcResultMarginParams l_tpCalcResultMarginParams = (TpCalcResultMarginParams)l_iteTpCalcResultParams.next();

                	//余力計算結果詳細<信用顧客>Paramsを取得
                	TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = (TpCalcResultMarginDetailParams)l_iteTpCalcResultDetailParams.next();

                	//補助口座オブジェクトを取得
                    WEB3GentradeSubAccount l_subAccount = null;
                    //補助口座を取得
                    try
                    {
                        l_subAccount = 
                            new WEB3GentradeSubAccount(
                                (SubAccountRow) new WEB3GentradeMainAccount(l_tpCalcResultMarginParams.getAccountId()).getSubAccount(
                                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getDataSourceObject());
                    }
                    catch (NotFoundException nfe)
                    {
                        log.error(nfe.getMessage(), nfe);
                        throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00670,
                                STR_METHOD_NAME,
                                nfe);
                    } 
                    //資産余力情報<信用顧客>の取得
                    WEB3TPTradingPowerCalcMargin l_tpCalcMargin = null;
					try {
                        l_tpCalcMargin = l_tpService.getTradingPowerCalcMargin(l_subAccount,
                                                                               l_tpCalcResultMarginParams,
                                                                               l_tpCalcResultMarginDetailParams);
					} catch (WEB3SystemLayerException se) {
                        log.error("資産余力情報<信用顧客>の取得に失敗しました。CalcResultEquityId : " + 
                        		  Long.toString(l_tpCalcResultMarginParams.getCalcResultMarginId()));
                        throw new WEB3BaseRuntimeException(
                        		WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                                STR_METHOD_NAME,
                                "資産余力情報<信用顧客>の取得に失敗しました。CalcResultEquityId : " + 
								Long.toString(l_tpCalcResultMarginParams.getCalcResultMarginId()),
    							se);
					}
					log.debug("Margin:" + Long.toString(l_tpCalcResultMarginParams.getAccountId()));
					//オリックス余力計算結果<信用顧客>Paramsを生成する
                    OrixTpCalcResultMarginParams l_calcResultMarginParams = new OrixTpCalcResultMarginParams();
                    //識別番号
                    l_calcResultMarginParams.setCalcResultMarginId(l_tpCalcMargin.getCalcResultMargin().calc_result_margin_id);
                    //口座ID
                    l_calcResultMarginParams.setAccountId(l_tpCalcResultMarginParams.getAccountId());
                    //会社コード
                    l_calcResultMarginParams.setInstitutionCode(INST_CODE);
                    //作業年月日
                    l_calcResultMarginParams.setWorkDate(l_sdfYMD.format(l_datSysDate));
                    //部店コード
                    l_calcResultMarginParams.setBranchCode(BR_CODE);
                    //口座コード
                    l_calcResultMarginParams.setAccountCode(Long.toString(l_tpCalcResultMarginParams.getAccountId()).substring(8, 14));
                    //顧客属性
                    l_calcResultMarginParams.setAssetEvaluationDiv(DEPOSIT_MARGIN);
                    //預り金(T+0～T+4) = get預り金残高(T+0～T+4) - get当日約定済代金(T+0～T+4)
                    l_calcResultMarginParams.setAccountBalance0((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_0) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setAccountBalance1((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_1) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setAccountBalance2((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_2) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setAccountBalance3((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_3) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setAccountBalance4((long)l_tpCalcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_4) -
                                                                (long)l_tpCalcMargin.getTodayExecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //発注充当金(T+1～T+4) = get当日未約定代金(T+1～T+4)
                    l_calcResultMarginParams.setTodayUnexecutedAmount1((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setTodayUnexecutedAmount2((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setTodayUnexecutedAmount3((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setTodayUnexecutedAmount4((long)l_tpCalcMargin.getTodayUnexecutedAmount(WEB3TPSpecifiedPointDef.T_4));
                    //日計り調整額(T+0～T+4) = get日計り拘束金(T+0～T+4)
                    l_calcResultMarginParams.setDayTradeRestraint0((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setDayTradeRestraint1((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setDayTradeRestraint2((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setDayTradeRestraint3((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setDayTradeRestraint4((long)l_tpCalcMargin.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //その他拘束金(T+0～T+4) = getその他拘束金(T+0～T+4)
                    l_calcResultMarginParams.setOtherRestraint0((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setOtherRestraint1((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setOtherRestraint2((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setOtherRestraint3((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setOtherRestraint4((long)l_tpCalcMargin.getOtherRestraint(WEB3TPSpecifiedPointDef.T_4));
                    //現金保証金(T+0～T+4) = get現金保証金(T+0～T+4)
                    l_calcResultMarginParams.setMarginAccountBalance0((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setMarginAccountBalance1((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginAccountBalance2((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginAccountBalance3((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginAccountBalance4((long)l_tpCalcMargin.calcMarginAccountBalance(WEB3TPSpecifiedPointDef.T_4));
                    //代用証券評価額(T+0～T+4) = get代用証券評価額(T+0～T+4)
                    l_calcResultMarginParams.setSubstituteSecurityAsset0((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setSubstituteSecurityAsset1((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setSubstituteSecurityAsset2((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setSubstituteSecurityAsset3((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setSubstituteSecurityAsset4((long)l_tpCalcMargin.getSubstituteSecurityAsset(WEB3TPSpecifiedPointDef.T_4));
                    //建玉評価損益 = get未決済建玉評価損益(T+0)
                    l_calcResultMarginParams.setContractAssetProfitLoss((long)l_tpCalcMargin.getContractAssetProfitLoss(WEB3TPSpecifiedPointDef.T_0));
                    //諸経費 = get建玉諸経費
                    l_calcResultMarginParams.setContractTotalCost((long)l_tpCalcMargin.getContractTotalCost(WEB3TPSpecifiedPointDef.T_0));
                    //未受渡建玉決済損益累計(T+0～T+3) = -get未受渡建玉決済損(T+0～T+3)
                    l_calcResultMarginParams.setUndeliContractLoss0(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setUndeliContractLoss1(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setUndeliContractLoss2(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setUndeliContractLoss3(-(long)l_tpCalcMargin.getUndeliContractLoss(WEB3TPSpecifiedPointDef.T_3));
                    //決済建玉前日価格評価額損益 = get決済建玉前日価格評価<当日>
                    l_calcResultMarginParams.setTodayRepayContractPreAsset((long)l_tpCalcMargin.getCalcResultDetailMargin().getTodayRepayContractPreAsset());
                    //建玉金額(T+0～T+4) = get未決済建玉代金(T+0～T+4)
                    l_calcResultMarginParams.setContractAmountDayRepay0((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setContractAmountDayRepay1((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setContractAmountDayRepay2((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setContractAmountDayRepay3((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setContractAmountDayRepay4((long)l_tpCalcMargin.getContractAmountDayRepay(WEB3TPSpecifiedPointDef.T_4));
                    //保証金余力(T+0～T+4) = calc保証金余力(T+0～T+4)
                    l_calcResultMarginParams.setMarginPower0((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setMarginPower1((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginPower2((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginPower3((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginPower4((long)l_tpCalcMargin.calcMarginPower(WEB3TPSpecifiedPointDef.T_4));
                    //新規建余力(T+1～T+4) = calc信用新規建可能額(T+1～T+4)
                    l_calcResultMarginParams.setMarginTradingPower1((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginTradingPower2((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginTradingPower3((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginTradingPower4((long)l_tpCalcMargin.calcMarginTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //保証金預託率(T+0～T+4) = calc保証金預託率(T+0～T+4)
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_0) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate0(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_0));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate0(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_1) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate1(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_1));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate1(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_2) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate2(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_2));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate2(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_3) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate3(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_3));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate3(null);
                    }
                    if (l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_4) != Double.POSITIVE_INFINITY)
                    {
                        l_calcResultMarginParams.setMarginDepositRate4(l_tpCalcMargin.calcMarginDepositRate(WEB3TPSpecifiedPointDef.T_4));
                    }
                    else
                    {
                        l_calcResultMarginParams.setMarginDepositRate4(null);
                    }
                    //現引余力(T+3～T+4) = calc信用現引可能額(T+3～T+4)
                    l_calcResultMarginParams.setActRecTradingPower3((long)l_tpCalcMargin.calcActualReceiptTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setActRecTradingPower4((long)l_tpCalcMargin.calcActualReceiptTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //現引余力(T+4') = calc信用現引可能額<日計り拘束金考慮>(T+4)
                    l_calcResultMarginParams.setActRecTradingPower4Dash((long)l_tpCalcMargin.calcActualReceiptTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //現物株買付余力(T+3～T+4) = calc株式買付可能額(T+3～T+4)
                    l_calcResultMarginParams.setEquityTradingPower3((long)l_tpCalcMargin.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setEquityTradingPower4((long)l_tpCalcMargin.calcEquityTradingPower(WEB3TPSpecifiedPointDef.T_4));
                    //現物株買付余力(T+4') = calc株式買付可能額<日計り拘束金考慮>(T+4)
                    l_calcResultMarginParams.setEquityTradingPower4Dash((long)l_tpCalcMargin.calcEquityTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //未受渡建玉金額累計(T+0～T+3) = get未受渡建玉代金(T+0～T+3)
                    l_calcResultMarginParams.setUndeliContractAmount0((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setUndeliContractAmount1((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setUndeliContractAmount2((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setUndeliContractAmount3((long)l_tpCalcMargin.getUndeliContractAmount(WEB3TPSpecifiedPointDef.T_3));
                    //保証金引出余力(T+0～T+4) = calc保証金引出余力(T+0～T+4)
                    l_calcResultMarginParams.setMarginDrawPower0((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setMarginDrawPower1((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setMarginDrawPower2((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setMarginDrawPower3((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setMarginDrawPower4((long)l_tpCalcMargin.calcMarginDrawPower(WEB3TPSpecifiedPointDef.T_4));
                    //現金引出余力(T+1～T+4) = calcその他商品買付可能額<日計り拘束金考慮>(T+1～T+4)
                    l_calcResultMarginParams.setOtherTradingPower1((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setOtherTradingPower2((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setOtherTradingPower3((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setOtherTradingPower4((long)l_tpCalcMargin.calcOtherTradingPowerIncDayTrade(WEB3TPSpecifiedPointDef.T_4));
                    //入金請求(T+0～T+4) = calc入金請求額(T+0～T+4)
                    l_calcResultMarginParams.setDemandamount0((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_0));
                    l_calcResultMarginParams.setDemandamount1((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_1));
                    l_calcResultMarginParams.setDemandamount2((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_2));
                    l_calcResultMarginParams.setDemandamount3((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_3));
                    l_calcResultMarginParams.setDemandamount4((long)l_tpCalcMargin.calcDemandamount(WEB3TPSpecifiedPointDef.T_4));
                    //出金実績・予定(T+0～T+2) = get出金額(T+0～T+2)
                    l_calcResultMarginParams.setPaymentAmountDesignate0((long)l_tpCalcMargin.getCalcResultDetailMargin().getPaymentAmountDesignate0());
                    l_calcResultMarginParams.setPaymentAmountDesignate1((long)l_tpCalcMargin.getCalcResultDetailMargin().getPaymentAmountDesignate1());
                    l_calcResultMarginParams.setPaymentAmountDesignate2((long)l_tpCalcMargin.getCalcResultDetailMargin().getPaymentAmountDesignate2());
                    //二階建チェックエラー銘柄情報の取得
                    WEB3TPMarginSecurityInfo[] l_marginSecInfo = null;
					try {
						l_marginSecInfo = l_tpService.getMarginSecurityInfo(l_subAccount);
					} catch (WEB3SystemLayerException se) {
                        log.error(se.getMessage(), se);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            STR_METHOD_NAME,
                            se.getMessage(),
							se);
					}
/*
                    WEB3TPMarginSecurityInfo[] l_marginSecInfo = new WEB3TPMarginSecurityInfo[2];
                    WEB3TPMarginSecurityInfo l_marginSecList1 = new WEB3TPMarginSecurityInfo();
                    WEB3TPMarginSecurityInfo l_marginSecList2 = new WEB3TPMarginSecurityInfo();
                    l_marginSecList1.marginSecProductId = 1301860100000L;
                    l_marginSecList1.marginSecRate = 999.99;
                    l_marginSecList2.marginSecProductId = 1301860200000L;
                    l_marginSecList2.marginSecRate = 1000.0;               
                    l_marginSecInfo[0] = l_marginSecList1;
                    l_marginSecInfo[1] = l_marginSecList2;
*/
                    //二階建チェックエラー銘柄がある場合
                    if (l_marginSecInfo != null)
                    {
                        String l_strMarginSecProductCode = ""; /* 二階建対象銘柄 */
                        double l_dblMarginSecRate        = 0;  /* 二階建占有率 */

                        for (int i=0;i<l_marginSecInfo.length;i++)
                        {
                            //最大占有率の銘柄ID、占有率を取得する。
                            if (l_dblMarginSecRate < l_marginSecInfo[i].marginSecRate)
                            {
                                //二階建対象銘柄 = 二階建対象銘柄ID
                                l_strMarginSecProductCode = Long.toString(l_marginSecInfo[i].marginSecProductId).substring(4, 9);
                                //二階建占有率 = 二階建占有率
                                l_dblMarginSecRate = l_marginSecInfo[i].marginSecRate;
                            }
                        }
                        //999.99 を超える場合は999.99に設定
                        if (l_dblMarginSecRate > MARGIN_SEC_RATE_MAX) 
                        {
                            l_calcResultMarginParams.setMarginSecRate(MARGIN_SEC_RATE_MAX);
                        }
                        else
                        {
                            l_calcResultMarginParams.setMarginSecRate(l_dblMarginSecRate);
                        }
                        l_calcResultMarginParams.setMarginSecProductCode(l_strMarginSecProductCode);
                    }
                    //二階建チェックエラー銘柄がない場合
                    else
                    {
                        l_calcResultMarginParams.setMarginSecProductCode(MARGIN_SEC_RATE_DEFAULT);
                        l_calcResultMarginParams.setMarginSecRate(null);
                    }
                    //株式評価額(保護、代用対象。100%評価) = get株式評価額(約残)
                    l_calcResultMarginParams.setEquityAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getEquityAssetExecuted());
                    //累積投資(MMF等)評価額 = get累積投資評価額(約残)
                    l_calcResultMarginParams.setRuitoAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getRuitoAssetExecuted());
                    //投資信託評価額 = get投資信託評価額(約残)
                    l_calcResultMarginParams.setMutualFundAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getMutualFundAssetExecuted());
                    //債券評価額 = get債券評価額(約残)
                    l_calcResultMarginParams.setBondAssetExecuted((long)l_tpCalcMargin.getCalcResultDetailMargin().getBondAssetExecuted());
                    //余力自動停止フラグ
                    //取引停止の場合
                    if (l_tpCalcMargin.getCalcCondition().isTradingStop()) 
                    {
                        l_calcResultMarginParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //取引可能の場合
                    else
                    {
                    	//calc預り金請求余力が0未満 または calc追証余力が0未満の場合
                        if (l_tpCalcMargin.calcAccountBalanceDemandPower(WEB3TPSpecifiedPointDef.T_0) < 0 ||
                        	l_tpCalcMargin.calcMarginCallPower(WEB3TPSpecifiedPointDef.T_0)	< 0) 
                        {
                            l_calcResultMarginParams.setTradingStop(TRADING_STOP_ORIX);
                        }
                        //calc引出可能現金が0以上の場合
                        else
                        {
                            l_calcResultMarginParams.setTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                        }                  
                    }
                    //信用新規建余力停止フラグ
                    //取引停止の場合
                    if (l_tpCalcMargin.getCalcCondition().isMarginOpenPositionStop()) 
                    {
                        l_calcResultMarginParams.setMarginOpenPositionStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //取引可能の場合
                    else
                    {
                        l_calcResultMarginParams.setMarginOpenPositionStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //出金余力停止フラグ
                    //余力可の場合
                    if (l_tpCalcMargin.getCalcCondition().isPaymentStop()) 
                    {
                        l_calcResultMarginParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //余力不可の場合
                    else
                    {
                        l_calcResultMarginParams.setPaymentStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //その他商品買付余力停止フラグ
                    //余力可の場合
                    if (l_tpCalcMargin.getCalcCondition().isOtherTradingStop()) 
                    {
                        l_calcResultMarginParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_STOP);
                    }
                    //余力不可の場合
                    else
                    {
                        l_calcResultMarginParams.setOtherTradingStop(WEB3TPTradingStopDivDef.TRADING_OK);
                    }
                    //作成日付
                    l_calcResultMarginParams.setCreatedTimestamp(l_datSysDate);
                    //タイムスタンプ
                    l_calcResultMarginParams.setLastUpdatedTimestamp(l_datSysDate);
                    /*
                     * 生成した、オリックス余力計算結果(信用顧客)Paramsを、オリックス余力計算結果(現物顧客)テーブルにInsertする。
                     */
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();
                        l_processor.doInsertQuery(l_calcResultMarginParams);
                        affectedRows++;
                    }
                    catch (DataException de)
                    {
                        log.error(de.getMessage(), de);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                            STR_METHOD_NAME,
                            de.getMessage() +
                            "calc_result_margin_Id : " + l_calcResultMarginParams.getCalcResultMarginId() + "," +
							"account_Id : " + l_calcResultMarginParams.getAccountId(),
                            de);
                    }
                }
            }        
            log.exiting(STR_METHOD_NAME);
            return null;
        }

		/**
		 * @@return affectedRows を戻します。
		 */
		public int getAffectedRows() {
			return affectedRows;
		}
		/**
		 * @@param tpCalcResultParams tpCalcResultParams を設定。
		 */
		public void setTpCalcResultParams(List tpCalcResultParams) {
			this.tpCalcResultParams = tpCalcResultParams;
		}
		/**
		 * @@param tpCalcResultDetailParams tpCalcResultDetailParams を設定。
		 */
		public void setTpCalcResultDetailParams(List tpCalcResultDetailParams) {
			this.tpCalcResultDetailParams = tpCalcResultDetailParams;
		}
		/**
		 * @@param blnMargin blnMargin を設定。
		 */
		public void setBlnMargin(boolean blnMargin) {
			this.blnMargin = blnMargin;
		}
		/**
		 * @@param affectedRows affectedRows を設定。
		 */
		public void setAffectedRows(int affectedRows) {
			this.affectedRows = affectedRows;
		}

    }
}
@
