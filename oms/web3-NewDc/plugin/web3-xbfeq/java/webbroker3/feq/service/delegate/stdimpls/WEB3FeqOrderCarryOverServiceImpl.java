head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文繰越サービスImpl(WEB3FeqOrderCarryOverServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー       
Revesion History : 2007/07/09 柴双紅　@モデルNo.352
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3FeqOrderTransferRequest;
import webbroker3.feq.message.WEB3FeqOrderTransferResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverService;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.OrderexecutionEndDao;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (外国株式注文繰越サービスImpl)<BR>
 * 外国株式注文繰越サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqOrderCarryOverServiceImpl implements WEB3FeqOrderCarryOverService 
{
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverServiceImpl.class); 
     
    /**
     * @@roseuid 42CE39F60196
     */
    public WEB3FeqOrderCarryOverServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式注文繰越サービスを行う。<BR>
     * <BR>
     * this.submit注文繰越()をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B8A99403A4
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3BackResponse l_response;
        
        //this.submit注文繰越()をコールする。

        if (l_request instanceof WEB3FeqOrderTransferRequest)
        {
            l_response = 
                submitOrderCarryOver((WEB3FeqOrderTransferRequest)l_request);   
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submit注文繰越)<BR>
     * 外国株式注文繰越処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文繰越）submit注文繰越」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文繰越リクエストオブジェクト
     * @@return WEB3FeqOrderTransferResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B8A7E60385
     */
    protected WEB3FeqOrderTransferResponse submitOrderCarryOver(
        WEB3FeqOrderTransferRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrderCarryOver(" + 
            "WEB3FeqOrderTransferRequest l_request) ";
        log.entering(STR_METHOD_NAME);        
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstitution() 証券会社を取得する。 
        //[引数] 
        //証券会社コード：　@リクエストデータ.証券会社コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_genAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeInstitution l_institution = null;
        try
        {
            l_institution = (WEB3GentradeInstitution)
                l_genAccountManager.getInstitution(l_request.institutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" テーブルに該当するデータがありません: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.3 get注文繰越処理区分(ProductTypeEnum, String)(証券会社::get注文繰越処理区分)
        //注文繰越処理区分を取得する。 
        //[引数] 
        //銘柄タイプ：　@ProductTypeEnum.外国株式 
        //先物／オプション区分：　@"0：DEFAULT"
        String l_strCarryoverEndType = 
            l_institution.getCarryoverEndType(
                ProductTypeEnum.FOREIGN_EQUITY, 
                WEB3FuturesOptionDivDef.DEFAULT);
        
        //(*)注文繰越可能チェック
        //1.4 (*)get注文繰越処理区分の戻り値が以下の条件の
        //    いずれかに該当する場合、処理を終了する。
        //    ※処理終了の理由は、log.info()にてログ出力すること。
        //    (1)出来終了処理が未済の場合(戻り値 == nullの場合)
        //    (2)既に注文繰越サービスが起動済の場合
        //　@   (戻り値 == "2：注文繰越AP呼出中"の場合)
        WEB3FeqOrderTransferResponse l_response = 
            (WEB3FeqOrderTransferResponse) l_request.createResponse();
        
        if (l_strCarryoverEndType == null || 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndType))
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.5 update注文繰越処理区分(String, String)
        //出来終了テーブルの注文繰越処理区分を"注文繰越AP呼出中"にupdateする。 
        //[引数] 
        //証券会社コード：　@リクエストデータ.証券会社コード 
        //注文繰越処理区分：　@"注文繰越AP呼出中"
        this.updateOrderCarryOverStatus(
            l_request.institutionCode, 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
        
        //1.6 get処理対象顧客一覧(String)
        //繰越処理対象となる顧客の一覧を取得する。 
        //[引数] 
        //証券会社コード：　@リクエストデータ.証券会社コード
        WEB3GentradeMainAccount[] l_mainAccounts = 
            this.getMainAccounts(l_request.institutionCode);
        
        boolean l_blnException = false;
        
        //1.7 (*)get処理対象顧客一覧()の戻り値の要素数分Loop処理
        
        WEB3FeqOrderCarryOverUnitService l_orderCarryOverUnitService =
            (WEB3FeqOrderCarryOverUnitService) Services.getService(
                WEB3FeqOrderCarryOverUnitService.class);  
        
        int l_intLength = 0;
        if (l_mainAccounts != null)
        {
            l_intLength = l_mainAccounts.length;
        }
        for (int i = 0; i < l_intLength; i++)
        {
            //1.7.1 exec注文繰越(顧客)
            //顧客単位で注文繰越処理を行う。 
            //[引数] 
            //顧客：　@処理対象の顧客            
            try
            {
                //顧客単位でcommitを行う。
                //※システムエラーが発生した場合は、その顧客のみrollbackを行い、
                //次の顧客へ処理を移行する。(continue)
                l_orderCarryOverUnitService.execOrderCarryOver(l_mainAccounts[i]);            
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("__an WEB3BaseException ", l_ex);
                l_blnException = true;
            }
        }
        
        //1.8 update注文繰越処理区分(String, String)
        //出来終了テーブルの注文繰越処理区分をupdateする。 
        //[引数] 
        //証券会社コード：　@リクエストデータ.証券会社コード 
        //注文繰越処理区分： 
        //　@[exec注文繰越()にて一件も例外がスローされなかった場合] 
        //　@　@"1：処理済"をセット 
        // [上記以外] 
        //　@　@"9：エラー"をセット
        
        String l_strOrderCarryOverStatus = null;
        
        if (!l_blnException)
        {
            l_strOrderCarryOverStatus = WEB3StatusDef.DEALT;
        }
        else
        {
            l_strOrderCarryOverStatus = WEB3StatusDef.DATA_ERROR;
        }
        this.updateOrderCarryOverStatus(
            l_request.institutionCode,
            l_strOrderCarryOverStatus);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get処理対象顧客一覧)<BR>
     * 出来終了処理対象となる注文を保持する<BR>
     * 顧客の一覧を返却する。<BR>
     * <BR>
     * １）　@注文単位検索<BR>
     * 　@外国株式注文マネージャ.get繰越対象注文単位()をコールする。<BR>
     * <BR>
     * 　@[get繰越対象注文単位()に指定する引数]<BR>
     * 　@　@口座ID：　@null<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * <BR>
     * 　@nullが返却された場合、nullを返却する。<BR>
     * <BR>
     * ２）　@顧客オブジェクト作成<BR>
     * 　@２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。<BR>
     * 　@２－２）２－１）にて作成した口座IDの一覧分、顧客オブジェクトを作成し、<BR>
     * 　@　@配列として返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     * @@roseuid 42B8B30A00A6
     */
    protected WEB3GentradeMainAccount[] getMainAccounts(
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccounts(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文単位検索 
        //外国株式注文マネージャ.get繰越対象注文単位()をコールする。 
        //[get繰越対象注文単位()に指定する引数] 
        //　@口座ID：　@null 
        //　@証券会社コード：　@パラメータ.証券会社コード 

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            l_feqOrderManager.getCarryOverOrderUnit(null, l_strInstitutionCode);
        
        //nullが返却された場合、nullを返却する。 
        if (l_feqOrderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //２）　@顧客オブジェクト作成 
        //２－１）１）の検索結果について、ユニークな口座IDの一覧を作成する。
        int l_intLength = l_feqOrderUnits.length;
        String[] l_strAccountIds = new String[l_intLength]; 
        
        for (int i = 0; i < l_intLength; i++)
        {
            l_strAccountIds[i] = l_feqOrderUnits[i].getAccountId() + "";
        }
        Object[] l_objAccountIds = WEB3Toolkit.toUnique(l_strAccountIds);
        
        WEB3GentradeMainAccount[] l_mainAccounts = null;
        
        if (l_objAccountIds != null)
        {
            WEB3GentradeAccountManager l_genAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            l_mainAccounts = 
                new WEB3GentradeMainAccount[l_objAccountIds.length];        
            
            for (int i = 0; i < l_objAccountIds.length; i++)
            {
                try
                {
                    l_mainAccounts[i] = (WEB3GentradeMainAccount)
                        l_genAccountManager.getMainAccount(
                            Long.parseLong((String)l_objAccountIds[i]));
                }
                catch (NotFoundException l_ex)
                {
                    log.error(" テーブルに該当するデータがありません: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),l_ex);
                }
            }   
        }
        
        //２－２）２－１）にて作成した口座IDの一覧分、顧客オブジェクトを作成し、 
        //　@配列として返却する。         
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }
    
    /**
     * (update注文繰越処理区分)<BR>
     * 出来終了テーブル.注文繰越処理区分の<BR>
     * updateを行う。<BR>
     * <BR>
     * 以下の条件に該当する出来終了テーブルの<BR>
     * レコードを更新する。<BR>
     * <BR>
     * [条件]<BR>
     * 　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@銘柄タイプ = ProductTypeEnum.外国株式 And<BR>
     * 　@先物／オプション区分 = "DEFAULT" And<BR>
     * 　@出来終了区分 = "DEFAULT"<BR>
     * <BR>
     * [更新値]<BR>
     * 　@注文繰越処理区分 = パラメータ.注文繰越処理区分<BR>
     * 　@更新日付 = 現在時刻<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strOrderCarryOverStatus - (注文繰越処理区分)<BR>
     * 注文繰越処理区分<BR>
     * <BR>
     * 0：　@未処理<BR>
     * 1：　@処理済<BR>
     * 2：　@注文繰越AP呼出中<BR>
     * 9：　@エラー<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B8B43201CF
     */
    protected void updateOrderCarryOverStatus(
        String l_strInstitutionCode, 
        String l_strOrderCarryOverStatus) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderCarryOverStatus(" +
            "String l_strInstitutionCode, String l_strOrderCarryOverStatus)";
        log.entering(STR_METHOD_NAME);
        
        //以下の条件に該当する出来終了テーブルの 
        //レコードを更新する。 

        //[条件] 
        //証券会社コード = パラメータ.証券会社コード And 
        //銘柄タイプ = ProductTypeEnum.外国株式 And 
        //先物／オプション区分 = "DEFAULT" 
        //出来終了区分 = "DEFAULT"
        OrderexecutionEndRow l_orderExecEndRow = null;
        try
        {
            l_orderExecEndRow =                 
                OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    l_strInstitutionCode, 
                    ProductTypeEnum.FOREIGN_EQUITY, 
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3OrderexecutionEndTypeDef.DEFAULT);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }   
        
        if (l_orderExecEndRow == null)
        {
            log.debug("Error in 出来終了テーブル record not found");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        OrderexecutionEndParams l_orderexecEndParams = 
            new OrderexecutionEndParams(l_orderExecEndRow); 
        
        //[更新値] 
        //注文繰越処理区分 = パラメータ.注文繰越処理区分 
        //更新日付 = 現在時刻
        l_orderexecEndParams.setCarryoverEndType(l_strOrderCarryOverStatus);
        l_orderexecEndParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderexecEndParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
