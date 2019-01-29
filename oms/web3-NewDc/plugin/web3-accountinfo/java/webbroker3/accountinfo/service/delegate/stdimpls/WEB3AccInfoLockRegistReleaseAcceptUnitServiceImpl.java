head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ロック登録解除受付１件サービス実装クラス(WEB3AccInfoLockRegistRelaxationAcceptUnitImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
                   2006/01/16 呉艶飛(北京中訊) 仕様変更・モデル082,ＤＢ更新仕様020
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.List;


import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.HostLockRegiAcceptParams;
import webbroker3.accountinfo.data.HostLockRegistParams;
import webbroker3.accountinfo.data.HostLockRegistRow;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptUnitService;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptDivDef;
import webbroker3.common.define.WEB3AccountLockErrorCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (ロック登録解除受付１件サービス実装クラス)<BR>
 * ロック登録解除受付１件サービス実装クラス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl implements WEB3AccInfoLockRegistReleaseAcceptUnitService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl.class);
    
    /**
     * ロック登録解除受付１件処理を実施し、処理結果（処理済／エラー）を返却する。<BR> 
     * <BR>
     * １）　@ロック客Y客登録解除キューデータ取得 <BR>
     * 　@ロック客Y客登録解除受付キュー.データコードに対応するロック客Y客登録解除キューデータを以下の条件で検索する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@ロック客Y客登録解除キューテーブル.データコード = 受付キュー.データコード(*1) And <BR>
     * 　@ロック客Y客登録解除キューテーブル.証券会社コード = 受付キュー.証券会社コード And <BR>
     * 　@ロック客Y客登録解除キューテーブル.部店コード = 受付キュー.部店コード And <BR>
     * 　@ロック客Y客登録解除キューテーブル.顧客コード = 受付キュー.顧客コード And <BR>
     *   ロック客Y客登録解除キューテーブル.識別コード = 受付キュー.識別コード <BR>
     * <BR>
     * 　@取得できなかった場合は、処理区分.9：エラー を返却する。 <BR>
     * 
     *   (*1)受付キュー.データコード == GI84G：(WEB3HostRequestCodeDef)の場合、”GI847：(WEB3HostRequestCodeDef)”をセットする。<BR>
     * 　@　@　@ 受付キュー.データコード == GI84F：(WEB3HostRequestCodeDef)の場合、”GI846：(WEB3HostRequestCodeDef)”をセットする。<BR>
     * <BR>
     * ２）　@エラーの場合（※）、以下の処理を実施する。<BR>  
     * 　@（※）　@エラーの場合 <BR>
     * 　@ロック客Y客登録解除受付キューテーブル.受付通知区分 == 2：エラー 且つ <BR>
     * 　@ロック客Y客登録解除受付キューテーブル.エラーコードが下記のエラーコードに当てはまらない場合。 <BR>
     * 　@[エラーコード]  <BR>
     * 　@　@1900：二重登録エラー <BR>
     * 　@　@FF51：抹消済エラー <BR>
     * 　@　@FF72：ロックエラー <BR>
     * 　@　@FF70：解除エラー <BR>
     * 　@　@FF73：認可エラー <BR> 
     *     1E00：取消区分エラー <BR>
     *     2700：該当無し <BR>
     * 
     *２－１） 取得したロック客Y客登録解除キューデータ更新（DB-update）。<BR>  
     *　@　@　@　@　@ 取得したロック客Y客登録解除キューデータを、DB更新仕様の通り更新（update)する。<BR>  
     *<BR>
     *　@　@　@　@　@更新内容は、DB更新仕様 <BR>
     *　@　@　@　@　@「ロック登録解除受付_ロック客Y客登録解除キューテーブル.xls」 参照。<BR>  
     *<BR>
     *１）～２）の処理で、エラーが発生した場合、9：処理区分.エラー を返却する。 <BR> 
     *以外、処理区分.1：処理済　@を返却する。  <BR>
     * @@param l_params
     * @@return String
     */
    public String notifyLockRegistReleaseAccep(HostLockRegiAcceptParams l_params) 
    {
        final String STR_METHOD_NAME = " notifyLockRegistReleaseAccep(HostLockRegiAcceptParams l_params) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_params == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_params] = " + l_params
                );
        }
        /*
         * １）　@ロック客Y客登録解除キューデータ取得 
         * 　@ロック客Y客登録解除受付キュー.データコードに対応するロック客Y客登録解除キューデータを以下の条件で検索する。 
         * 
         * 　@[検索条件] 
         * 　@ロック客Y客登録解除キューテーブル.データコード = 受付キュー.データコード(*1) And 
         * 　@ロック客Y客登録解除キューテーブル.証券会社コード = 受付キュー.証券会社コード And 
         * 　@ロック客Y客登録解除キューテーブル.部店コード = 受付キュー.部店コード And 
         * 　@ロック客Y客登録解除キューテーブル.顧客コード = 受付キュー.顧客コード And 
         *   ロック客Y客登録解除キューテーブル.識別コード = 受付キュー.識別コード 
         */
        String l_strStatus = null;
        List l_lisRecords = null;
        
        String l_strStitutionCode = l_params.getInstitutionCode();
        String l_strBranchCode = l_params.getBranchCode();
        String l_strAccountCode = l_params.getAccountCode();

        try
        {
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("request_code = ?");
            l_sbQueryString.append(" and institution_code = ?");
            l_sbQueryString.append(" and branch_code = ?");
            l_sbQueryString.append(" and account_code = ?");
            l_sbQueryString.append(" and order_request_number = ?");
            
            String l_strRequestCode = null;
            
            if (WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL_ACCEPT.equals(l_params.getRequestCode()))
            {
                l_strRequestCode = WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL;
            }
            else if (WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL_ACCEPT.equals(l_params.getRequestCode()))
            {
                l_strRequestCode = WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL;
            }

            
            Object[] l_queryDataContainer = new Object[] {
                    l_strRequestCode,
                    l_strStitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_params.getOrderRequestNumber()};
            

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostLockRegistRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainer);
            
            //取得できなかった場合は、処理区分.9：エラー を返却する。
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                l_strStatus = WEB3StatusDef.DATA_ERROR;
                return l_strStatus;
            }
            
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            return l_strStatus;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            return l_strStatus;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            return l_strStatus;
        }
        
        HostLockRegistRow l_row = (HostLockRegistRow)l_lisRecords.get(0);
        
        HostLockRegistParams l_lockRegistParams = new HostLockRegistParams(l_row);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); 

            String l_errorCode = l_params.getErrorCode();
            //２）　@エラーの場合（※）、以下の処理を実施する。

            // ロック客Y客登録解除受付キューテーブル.受付通知区分 == 2：エラー 且つ 
            // ロック客Y客登録解除受付キューテーブル.エラーコードが下記のエラーコードに当てはまらない場合。
            // 　@[エラーコード]  
            //  　@　@1900：二重登録エラー 
            //  　@　@FF51：抹消済エラー 
            //  　@　@FF72：ロックエラー 
            //  　@　@FF70：解除エラー 
            //  　@　@FF73：認可エラー 
            //  　@　@1E00：取消区分エラー
            //      2700：該当無し
            if (WEB3AcceptDivDef.ERROR.equals(l_params.getAcceptStatus()) 
                && !WEB3AccountLockErrorCodeDef.DOUBLE_REGI_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.ERASED_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.LOCK_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.LOCK_OFF_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.PERMISSION_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.CANCEL_DIV_ERROR.equals(l_errorCode)
                && !WEB3AccountLockErrorCodeDef.NO_DATA_ERROR.endsWith(l_errorCode))
            {                                
                
                 // ２－１） 取得したロック客Y客登録解除キューデータ更新（DB-update）。 
                 //　@　@　@　@　@ 取得したロック客Y客登録解除キューデータを、DB更新仕様の通り更新（update)する。 
                 // 
                 // 　@　@　@　@　@更新内容は、DB更新仕様
                 // 　@　@　@　@　@「ロック登録解除受付_ロック客Y客登録解除キューテーブル.xls」 参照。 

                l_lockRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_lockRegistParams.setStatus(WEB3StatusDef.DATA_ERROR); 
                
                l_queryProcessor.doUpdateQuery(l_lockRegistParams);                                 
            }
            
            //１）～２）の処理で、エラーが発生した場合、9：処理区分.エラー を返却する。 以外、処理区分.1：処理済　@を返却する。  
            l_strStatus = WEB3StatusDef.DEALT;           
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            l_lockRegistParams.setStatus(WEB3StatusDef.DATA_ERROR);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            l_strStatus = WEB3StatusDef.DATA_ERROR;
            l_lockRegistParams.setStatus(WEB3StatusDef.DATA_ERROR);
        }         
        log.exiting(STR_METHOD_NAME);
        return l_strStatus;
    }
}
@
