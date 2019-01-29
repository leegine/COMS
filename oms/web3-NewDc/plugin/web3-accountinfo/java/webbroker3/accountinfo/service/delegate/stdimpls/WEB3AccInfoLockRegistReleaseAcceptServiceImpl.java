head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ロック登録解除受付サービス実装クラス(WEB3AccInfoLockRegistReleaseAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.HostLockRegiAcceptParams;
import webbroker3.accountinfo.data.HostLockRegiAcceptRow;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (ロック登録解除受付サービス実装クラス)<BR>
 * ロック登録解除受付サービス実装クラス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptServiceImpl implements WEB3AccInfoLockRegistReleaseAcceptService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockRegistReleaseAcceptServiceImpl.class);
    
    /**
     * ロック登録解除受付処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「ロック登録解除受付execute」参照。<BR>
     *@@param l_request
     *@@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //ロック登録解除受付TransactionCallbackを生成する。 
            WEB3AccInfoLockRegistReleaseAcceptTransactionCallback l_callback = 
                new WEB3AccInfoLockRegistReleaseAcceptTransactionCallback();
            
            /*
             * DBトランザクション処理を実施する。 
             * [doTransaction()に指定する引数] 
             * トランザクション属性：　@TX_JOIN_EXISTING 
             * トランザクションコールバック：　@ロック登録解除受付TransactionCallbackインスタンス 
             */
            l_queryProcessor.doTransaction(TransactionalInterceptor.TX_JOIN_EXISTING, l_callback);
            
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //レスポンスデータを生成する。
        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
    
    public class WEB3AccInfoLockRegistReleaseAcceptTransactionCallback implements TransactionCallback
    {
        /**
         * (コンストラクタ)
         *コンストラクタ
         */
        public WEB3AccInfoLockRegistReleaseAcceptTransactionCallback()
        {
            
        }
        
        /**
         * トランザクション処理を実施する。<BR>  
         * <BR>
         * シーケンス図 <BR>
         * 「ロック登録解除受付process」参照。<BR>
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process()";
            log.entering(STR_METHOD_NAME);
            
            //未処理のロック客Y客登録解除受付キューデータを取得する。
            try
            {
                HostLockRegiAcceptParams[] l_params = this.getAcceptQueue(); 
            
                
                WEB3AccInfoLockRegistReleaseAcceptUnitService l_service =
                    (WEB3AccInfoLockRegistReleaseAcceptUnitService)Services.getService(WEB3AccInfoLockRegistReleaseAcceptUnitService.class);
                if (l_params != null)
                {
                    for (int i = 0; i < l_params.length; i++)
                    {
                        HostLockRegiAcceptParams l_acceptParams = l_params[i];
        
                        //ロック登録解除受付１件処理を実施し、処理結果を返却する。
                        String l_strStatus = l_service.notifyLockRegistReleaseAccep(l_acceptParams);
                        
                        /*
                         * ロック登録解除受付キューを更新する。 
                         * 
                         * [update受付キュー（）に指定する引数］
                         * ロック登録解除受付キュー： get受付キュー（）の戻り値の各要素
                         * 処理区分： notiｆｙロック登録解除受付（）の戻り値
                         */
                        this.updatQueue(l_acceptParams, l_strStatus);        
                    }
                }
            }
            catch (WEB3BaseException l_ex)
            {
                ErrorInfo l_errorInfo = l_ex.getErrorInfo();
                l_errorInfo.setErrorClass(l_ex.getClass().getName());
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_errorInfo);
            }
            return null;
        }
        
        /**
         *(get受付キュー)
         * get受付キュー
         * ロック客Y客登録解除受付キューデータを取得する。 <BR>
         * <BR>
         * １）　@ロック客Y客登録解除受付キューテーブル検索 <BR>
         * 　@以下の[検索条件]で、ロック客Y客登録解除受付キューテーブルを行ロックオプション（select for update）にて検索する。<BR> 
         * <BR>
         * 　@[検索条件] <BR>
         * 　@ロック客Y客登録解除受付キュー.処理区分 = 0:未処理 And <BR>
         * 　@ロック客Y客登録解除受付キュー.データコード = GI84G：Y客登録受付 or GI84F：ロック客登録解除受付<BR>
         * 　@※　@未処理のロック客Y客登録解除受付データ <BR>
         * ２）　@検索結果を配列にして返却する。 <BR>
         * @@throws WEB3BaseException
         */
        public HostLockRegiAcceptParams[] getAcceptQueue() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " getAcceptQueue()";
            log.entering(STR_METHOD_NAME);
            
            //１）ArrayListを生成する。 
            List l_lisRecords = null;

            try
            {

                /*
                 * ロック客Y客登録解除受付キューデータを取得する。 
                 * 
                 * １）　@ロック客Y客登録解除受付キューテーブル検索 
                 * 　@以下の[検索条件]で、ロック客Y客登録解除受付キューテーブルを行ロックオプション（select for update）にて検索する。
                 * 
                 * 　@[検索条件] 
                 * 　@ロック客Y客登録解除受付キュー.処理区分 = 0:未処理 And <BR>
                 * 　@ロック客Y客登録解除受付キュー.データコード = GI84G：Y客登録受付 or GI84F：ロック客登録解除受付
                 * 
                 * 　@※　@未処理のロック客Y客登録解除受付データ 
                 * 
                 * ２）　@検索結果を配列にして返却する。
                 */
                StringBuffer l_sbQueryString = new StringBuffer();
                l_sbQueryString.append("status = ?");
                l_sbQueryString.append(" and (request_code = ? or request_code = ?)");
                
                Object[] l_queryDataContainer = new Object[] {
                        WEB3StatusDef.NOT_DEAL,
                        WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL_ACCEPT,
                        WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL_ACCEPT};
                //２）顧客行オブジェクトのListを取得する。

                /*
                  * １）　@ロック客Y客登録解除受付キューテーブル検索 
                 * 　@以下の[検索条件]で、ロック客Y客登録解除受付キューテーブルを行ロックオプション（select for update）にて検索する。 
                 * 
                 * 　@[検索条件] 
                 * 　@ロック客Y客登録解除受付キュー.処理区分 = 0:未処理 And 
                 * 　@ロック客Y客登録解除受付キュー.データコード = GI84G：Y客登録受付 or GI84F：ロック客登録解除受付
                 */
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    HostLockRegiAcceptRow.TYPE,
                    l_sbQueryString.toString(),
                    " for update",
                    l_queryDataContainer);
            }
            catch (DataFindException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            log.exiting(STR_METHOD_NAME);
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                return null;
            }
            //２）　@検索結果を配列にして返却する。
            HostLockRegiAcceptParams[] l_params = new HostLockRegiAcceptParams[l_lisRecords.size()];
            l_lisRecords.toArray(l_params);
            return l_params;
        }
        
        /**
         * (ｕｐｄａｔｅ受付キュー)<BR>
         * ｕｐｄａｔｅ受付キュー<BR>
         * ロック登録解除受付キューを更新(update)する。 <BR>
         * －引数のロック登録解除受付キューに以下の通り値をセットして更新（update)する。 <BR>
         * <BR>
         * 　@　@ロック登録解除受付キュー.処理区分 = 処理区分 <BR>
         * <BR>
         *     ※DB更新仕様<BR>
         *    　@　@　@「ロック登録解除受付_ロック客Y客登録解除受付キューテーブル.xls」参照 <BR>
         * @@param l_params
         * @@param l_strStatus
         * @@throws WEB3BaseException
         */
        public void updatQueue(HostLockRegiAcceptParams l_params, String l_strStatus) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " updatQueue(HostLockRegiAcceptParams l_params, String l_strStatus)";
            log.entering(STR_METHOD_NAME);
            
            /*
             *ロック登録解除受付キューを更新(update)する。 
             *   －引数のロック登録解除受付キューに以下の通り値をセットして更新（update)する。 
             *   
             *   ロック登録解除受付キュー.処理区分 = 処理区分 
             *   
             *   ※DB更新仕様
             *   「ロック登録解除受付_ロック客Y客登録解除受付キューテーブル.xls」参照 
             */
                        
            l_params.setStatus(l_strStatus);
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_params);
            }
            catch (DataFindException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
    }
}
@
