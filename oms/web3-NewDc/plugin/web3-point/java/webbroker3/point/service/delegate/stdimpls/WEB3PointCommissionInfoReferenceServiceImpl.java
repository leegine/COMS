head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手数料無料情報照会サービスImpl(WEB3PointCommissionInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 張学剛(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import java.util.Date;

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
import webbroker3.point.message.WEB3PointCommissionInfoReferenceRequest;
import webbroker3.point.message.WEB3PointCommissionInfoReferenceResponse;
import webbroker3.point.service.delegate.WEB3PointCommissionInfoReferenceService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式手数料無料情報照会サービスImpl)<BR>
 * 株式手数料無料情報照会サービス実装クラス<BR>
 * 
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3PointCommissionInfoReferenceServiceImpl extends WEB3PointClientRequestService implements WEB3PointCommissionInfoReferenceService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointCommissionInfoReferenceServiceImpl.class);

    /**
     * @@roseuid 421D7B39006D
     */
    public WEB3PointCommissionInfoReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 株式手数料無料情報照会サービスを行う。<BR>
     * <BR>
     * シーケンス図「表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@roseuid 420716B900F7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME); 
        
        if (!(l_request instanceof WEB3PointCommissionInfoReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1 get証券会社コード()
        String l_strInstitutionCode = this.getInstitutionCode();
        
        //1.2 get部店コード()
        String l_strBranchCode = this.getBranchCode();
        
        //1.3 get顧客コード()
        String l_strAccountCode = this.getAccountCode();
        
        //1.4 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        if (l_accountManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode);//WEB3SystemLayerException
            
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //(*1)トレードボーナスプランDAOにて、
        //     以下の条件のレコードを取得する。
        //[取得条件]
        //証券会社コード = get証券会社コード()の戻り値
        //部店コード = get部店コード()の戻り値
        //口座コード = get顧客コード()の戻り値の上6桁
        OrixTradeBonusPlanRow l_row = null;
        
        try
        {
            l_row = OrixTradeBonusPlanDao.findRowByInstitutionCodeBranchCodeAccountCode(
                l_strInstitutionCode, l_strBranchCode, l_mainAccount.getAccountCode());//DataFindException,DataQueryException,DataNetworkException
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
            
        //1.5 createResponse()
        WEB3PointCommissionInfoReferenceRequest l_referenceRequest = 
            (WEB3PointCommissionInfoReferenceRequest)l_request;
            
        //(*2)プロパティセット
        //(*2)以下のとおりに、プロパティをセットする。
        //レスポンス.手数料無料期間表示フラグ = （以下のとおり）
        //     トレードボーナスプラン.手数料無料期間FROM != null の場合、true
        //     トレードボーナスプラン.手数料無料期間FROM == null の場合、false
        //レスポンス.手数料無料期間（自） = トレードボーナスプラン.手数料無料期間FROM
        //レスポンス.手数料無料期間（至） = トレードボーナスプラン.手数料無料期間TO
        
        //※トレードボーナスプランテーブルのレコードが取得できなかった場合は、以下のとおり。
        //レスポンス.手数料無料期間表示フラグ = false
        //レスポンス.手数料無料期間（自） = null
        //レスポンス.手数料無料期間（至） = null 
        WEB3PointCommissionInfoReferenceResponse l_response = 
            (WEB3PointCommissionInfoReferenceResponse)l_referenceRequest.createResponse();
            
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (l_row != null)
        {
            if (l_row.getFreeTermFrom() != null)
            {
                l_response.freeTermDisplayFlag = true;
            }
            else
            {
                l_response.freeTermDisplayFlag = false;
            }
            
            Date l_freeTermFromdate = WEB3DateUtility.getDate(l_row.getFreeTermFrom(), "yyyyMMdd");
            Date l_freeTermTodate = WEB3DateUtility.getDate(l_row.getFreeTermTo(), "yyyyMMdd");
            
            //レスポンス.手数料無料期間（自） = トレードボーナスプラン.手数料無料期間FROM
            l_response.freeTermFrom = l_freeTermFromdate;
            
            //レスポンス.手数料無料期間（至） = トレードボーナスプラン.手数料無料期間TO
            l_response.freeTermTo = l_freeTermTodate;            
        }
        else
        {
            //レスポンス.手数料無料期間表示フラグ = false
            l_response.freeTermDisplayFlag = false;
            
            //レスポンス.手数料無料期間（自） = null
            l_response.freeTermFrom = null;
            
            //レスポンス.手数料無料期間（至） = null
            l_response.freeTermTo = null;            
        }
   
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
