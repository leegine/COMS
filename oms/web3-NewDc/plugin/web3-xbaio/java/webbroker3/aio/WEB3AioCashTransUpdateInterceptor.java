head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金更新インタセプタ(WEB3AioCashTransUpdateInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 周勇 (中訊) 新規作成     
                   2004/10/23 于美麗 (中訊) レビュー 
                   2004/12/06 周勇 (中訊) 残対応         
                   2006/09/04 徐宏偉(中訊) モデル 634、642      
Revesion History : 2007/08/08 張騰宇(中訊) モデル 751
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.BatchedQuery;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (入出金更新インタセプタ)<BR>
 * 入出金更新インタセプタクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransUpdateInterceptor implements AioOrderManagerPersistenceEventInterceptor 
{
    
    /**
     * (入出金更新インタセプタ)
     * @@return webbroker3.aio.WEB3AioCashTransUpdateInterceptor
     * @@roseuid 40E2A309034C
     */
    public WEB3AioCashTransUpdateInterceptor() 
    {
     
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransUpdateInterceptor.class);   
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １）　@注文単位オブジェクト取得 <BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@拡張項目セット <BR>
     * <BR>
     * ２－１） 注文取消区分、注文エラー理由コード <BR>
     * 注文単位オブジェクトの同名項目から値をセットする。 <BR>
     * <BR>
     * ２－２） 更新者 <BR>
     * セッションからログインIDが取得できる場合、ログインIDをセットする。 <BR>
     * 取得できない場合、”SYSTEM”をセットする。  <BR>
     * <BR>
     * ２－３）　@通貨コード、入出金金額（円換算額）<BR>
     * 注文単位オブジェクトの同名項目から値をセットする。<BR>
     * ただし、同名項目がnullの場合はnullをセットする。<BR>
     * <BR>
     * @@param l_updateType - ((更新タイプ))<BR>
     * INSERTまたは、UPDATE
     * <BR>
     * （OrderManagerPersistenceTypeにて定数定義。）
     * @@param l_process - (処理)<BR>
     * （OrderManagerPersistenceContextにて定数定義）
     * @@param l_orderActionParams - (注文履歴Params)
     * <BR>
     * 注文履歴のパラメータクラス
     * @@return AioOrderActionParams
     * @@roseuid 40E2A370035C
     */
    public AioOrderActionParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, AioOrderActionParams l_orderActionParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType l_updateType," +
                         "OrderManagerPersistenceContext l_process, " +
                         "AioOrderActionParams l_orderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderActionParams == null)
        {
            log.debug("注文履歴ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //１）　@注文単位オブジェクト取得 
            //引数の注文履歴Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。
            long l_lngOrdrUnitId = l_orderActionParams.getOrderUnitId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3AioOrderManager l_aioOrderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(ProductTypeEnum.AIO).getOrderManager();
            OrderUnit l_orderUnit = l_aioOrderManager.getOrderUnit(l_lngOrdrUnitId);
            log.debug("(AioOrderUnitRow)l_orderUnit.getDataSourceObject() = " + 
                (AioOrderUnitRow)l_orderUnit.getDataSourceObject());
            
            //===========remain zhou-yong 2004/12/7 NO.1 begin===========
            
            //２）　@拡張項目セット 
            //２－１） 注文取消区分、注文エラー理由コード 
            //注文単位オブジェクトの同名項目から値をセットする。 
            
            // 2-1)注文取消区分
            l_orderActionParams.setCancelType(
                ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getCancelType());
            
            // 2-2)注文エラー理由コード
            l_orderActionParams.setErrorReasonCode(
                ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getErrorReasonCode());
            
            //２－２） 更新者 
            //セッションからログインIDが取得できる場合、ログインIDをセットする。 
            //取得できない場合、”SYSTEM”をセットする。
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService)
            Services.getService(OpLoginSecurityService.class);

            long l_lngLoginId = 0;
            
            boolean l_blnIsLogin = true;
            try
            {
                l_lngLoginId = l_opLoginSec.getLoginId();
            }
            catch (IllegalSessionStateException l_ex)
            {
                l_blnIsLogin = false;
            }
            log.debug("セッションからログインID = " + l_lngLoginId);
            
            if(l_blnIsLogin && l_lngLoginId != 0)
            {
                l_orderActionParams.setLastUpdatedUser(Long.toString(l_lngLoginId));
            }
            else
            {
                l_orderActionParams.setLastUpdatedUser("SYSTEM");
            }
            
            //===========remain zhou-yong 2004/12/7 NO.1 end===========
            
            //２－３）　@通貨コード、入出金金額（円換算額） 
            //注文単位オブジェクトの同名項目から値をセットする。
            //ただし、同名項目がnullの場合はnullをセットする。
            l_orderActionParams.setCurrencyCode(
                ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getCurrencyCode());
            
            if (((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getConvertAmountIsNull())
            {
                l_orderActionParams.setConvertAmount(null);
            }
            else
            {
                l_orderActionParams.setConvertAmount(
                    ((AioOrderUnitRow)l_orderUnit.getDataSourceObject()).getConvertAmount());
            }

        }
        catch(NotFoundException l_ex)
        {
            log.error("該当する注文単位オブジェクトがありません", l_ex); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderActionParams;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return BatchedQuery
     * @@roseuid 415A762000E2
     */
    public BatchedQuery getQueryToExecute(OrderManagerPersistenceType arg0, Class arg1) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return AioOrderUnitParams
     * @@roseuid 415A7814023F
     */
    public AioOrderUnitParams mutate(OrderManagerPersistenceType arg0, OrderManagerPersistenceContext arg1, AioOrderUnitParams arg2) 
    {
     return arg2;
    }
}
@
