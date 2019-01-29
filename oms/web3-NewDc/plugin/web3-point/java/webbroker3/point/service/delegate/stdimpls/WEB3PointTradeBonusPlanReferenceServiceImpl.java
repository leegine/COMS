head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.51.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トレードボーナスプラン照会サービスImpl(WEB3PointTradeBonusPlanReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 鄭海良(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.point.WEB3PointClientRequestService;
import webbroker3.point.data.OrixTradeBonusPlanDao;
import webbroker3.point.data.OrixTradeBonusPlanRow;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceRequest;
import webbroker3.point.message.WEB3PointTradeBonusPlanReferenceResponse;
import webbroker3.point.service.delegate.WEB3PointTradeBonusPlanReferenceService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トレードボーナスプラン照会サービスImpl)<BR>
 * トレードボーナスプラン照会サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointTradeBonusPlanReferenceServiceImpl extends WEB3PointClientRequestService implements WEB3PointTradeBonusPlanReferenceService 
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PointTradeBonusPlanReferenceServiceImpl.class);        
    
    /**
     * @@roseuid 421D7B390000
     */
    public WEB3PointTradeBonusPlanReferenceServiceImpl() 
    {
     
    }
    
    /**
     * トレードボーナスプラン照会サービスを行う。<BR>
     * <BR>
     * シーケンス図「表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 42071E930368
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (!(l_request instanceof WEB3PointTradeBonusPlanReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1get証券会社コード( )
        String l_strInstitutionCode = this.getInstitutionCode();        
        
        //1.2get部店コード()
        String l_strBranchCode = this.getBranchCode();
        
         
        //1.3get顧客コード( )
        String l_strAccountCode = this.getAccountCode();        
        
        //1.4validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        OrixTradeBonusPlanRow l_row = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);//WEB3SystemLayerException

            l_row = OrixTradeBonusPlanDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_mainAccount.getAccountCode());//DataFindException, DataQueryException, DataNetworkException            
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        //1.5createResponse( )
        WEB3PointTradeBonusPlanReferenceRequest l_tradeBonusPlanReferenceRequest = 
            (WEB3PointTradeBonusPlanReferenceRequest)l_request;        
        WEB3PointTradeBonusPlanReferenceResponse  l_response = 
            (WEB3PointTradeBonusPlanReferenceResponse)l_tradeBonusPlanReferenceRequest.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.6(*2)プロパティセット
        // (ポイント適用年月)
        if (l_row != null)
        {
            l_response.applyMonthCurr = l_row.getApplyMonthCurr();

            // (トレードポイント)
            if (!l_row.getTrdPointCurrIsNull())
            {
                l_response.trdPointCurr = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointCurr());
            }

            // キャンペーンポイント
            if (!l_row.getCmpPointCurrIsNull())
            {
                l_response.cmpPointCurr = 
                    WEB3StringTypeUtility.formatNumber(l_row.getCmpPointCurr());
            }
        
            // (合計ポイント)
            if (!l_row.getTrdPointCurrIsNull() || !l_row.getCmpPointCurrIsNull())
            {
                l_response.totalPointCurr = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointCurr() + l_row.getCmpPointCurr());
            }
            
            // (割引率)
            l_response.cutRateCurr = l_row.getCutRateCurr();

            // (ポイント適用年月（翌月）)
            l_response.applyMonthNext = l_row.getApplyMonthNext();

            // (トレードポイント（翌月）)
            if (!l_row.getTrdPointNextIsNull())
            {
                l_response.trdPointNext = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointNext());
            }

            // (キャンペーンポイント（翌月）)
            if (!l_row.getCmpPointNextIsNull())
            {
                l_response.cmpPointNext = 
                    WEB3StringTypeUtility.formatNumber(l_row.getCmpPointNext());
            }

            // (合計ポイント（翌月）)
            if (!l_row.getTrdPointNextIsNull() || !l_row.getCmpPointNextIsNull())
            {
                l_response.totalPointNext = 
                    WEB3StringTypeUtility.formatNumber(l_row.getTrdPointNext() + l_row.getCmpPointNext());
            }

            // (割引率（翌月）)
            l_response.cutRateNext = l_row.getCutRateNext();
        }
        else
        {
            l_response.applyMonthCurr = null;

            // (トレードポイント)
            l_response.trdPointCurr = null;

            // キャンペーンポイント
            l_response.cmpPointCurr = null;
        
            // (合計ポイント)
            l_response.totalPointCurr = null;

            // (割引率)
            l_response.cutRateCurr = null;

            // (ポイント適用年月（翌月）)
            l_response.applyMonthNext = null;

            // (トレードポイント（翌月）)
            l_response.trdPointNext = null;

            // (キャンペーンポイント（翌月）)
            l_response.cmpPointNext = null;

            // (合計ポイント（翌月）)
            l_response.totalPointNext = null;

            // (割引率（翌月）)
            l_response.cutRateNext = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;           
    }
}
@
