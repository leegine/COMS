head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・書面未承諾 強制ログアウトサービスImpl(WEB3AdminFPTForceLogoutServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 許(FLJ) 新規作成
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3ServerUrlAccessorDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.data.DocForceLogoutRunStatusRow;
import webbroker3.docadmin.define.WEB3DocForceLogoutRunStatusStatusDef;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutProductCondition;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceResponse;
import webbroker3.docadmin.message.WEB3FPTForceLogoutInfoUnit;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmResponse;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.system.tune.affinity.message.WEB3AffinityDescendRequest;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.comm.client.CommunicationException;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;
import com.fitechlabs.xtrade.kernel.comm.client.ServerException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * 管理者 書面未承諾 強制ログアウトサービスImpl
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutServiceImpl implements WEB3AdminFPTForceLogoutService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutServiceImpl.class);
    
    /**
     * @@roseuid 47DF467601FB
     */
    public WEB3AdminFPTForceLogoutServiceImpl() 
    {
     
    }
    
    /**
     * 書面未承諾強制ログアウト入力画面表示処理を行う。 
     * 
     * シーケンス図 
     * 「get入力画面」参照
     * @@param l_request - 管理者 書面未承諾 
     * 強制ログアウト入力リクエスト
     * @@return WEB3AdminFPTForceLogoutInputResponse
     * TForceLogoutInputResponse
     * @@roseuid 47CFA5C1019E
     */
    protected WEB3AdminFPTForceLogoutInputResponse getInputPage(WEB3AdminFPTForceLogoutInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getInputPage(WEB3AdminFPTForceLogoutInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTForceLogoutInputResponse l_response = null;
       
        // ログイン情報より管理者インスタンスを取得する
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 管理者権限チェック処理を行う
        validateAdminPermission(l_web3Administrator, true);

        // 部店コードを取得する
        String l_strBranchCode = l_web3Administrator.getBranchCode();

        // 証券会社コードを取得する
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        // 書面区分管理を生成する
        WEB3AdminFPTDocDivManagement l_management = 
            new WEB3AdminFPTDocDivManagement(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    null,
                    null);

        // 書面区分管理情報を取得する
        WEB3FPTDocumentDivAdminInfoUnit[] l_infoUnit = l_management.getDocDivManagementLists();

        TreeMap l_map = new TreeMap();
        for (int i=0;i<l_infoUnit.length;i++)
        {
            // 書面チェック区分＝金商法@の場合
            if (WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW.equals(l_infoUnit[i].docuCheckDiv))
            {
                l_map.put(l_infoUnit[i].documentCategory, l_infoUnit[i]);
            }
        }

        // TreeMapが0サイズの場合
        if (l_map.size() == 0)
        {
            log.debug("書面区分管理テーブルにレコードが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02998,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面区分管理テーブルにレコードが存在しません。");
        }
        
        WEB3FPTDocumentDivAdminInfoUnit[] l_infoUnitFin = (WEB3FPTDocumentDivAdminInfoUnit[])l_map.values().toArray(new WEB3FPTDocumentDivAdminInfoUnit[0]);
        
        l_response = (WEB3AdminFPTForceLogoutInputResponse) l_request.createResponse();
        l_response.documentDivList = l_infoUnitFin;

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * 書面未承諾強制ログアウト確認画面表示処理を行う。 
     * 
     * シーケンス図 
     * 「validate強制ログアウト」参照
     * @@param l_request - 管理者 書面未承諾 
     * 強制ログアウト確認リクエスト
     * @@return WEB3AdminFPTForceLogoutconfirmResponse
     * TForceLogoutconfirmResponse
     * @@roseuid 47D0EEB2014D
     */
    protected WEB3AdminFPTForceLogoutConfirmResponse validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutConfirmResponse l_response = null;
        
        // リクエストデータの整合性をチェックする
        l_request.validate();
        
        // ログイン情報より管理者インスタンスを取得する
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // 管理者権限チェック処理を行う
        validateAdminPermission(l_web3Administrator, true);
        
        // 強制ログアウト処理が可能かチェックを行う
        validateForceLogoutPossible(l_request.documentDivList);
        
        l_response = (WEB3AdminFPTForceLogoutConfirmResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * 書面未承諾強制ログアウト完了画面表示処理を行う。 
     * 
     * シーケンス図 
     * 「submit強制ログアウト」参照
     * @@param l_request - 管理者 書面未承諾 
     * 強制ログアウト完了リクエスト
     * @@return WEB3AdminFPTForceLogoutCompleteResponse
     * TForceLogoutCompleteResponse
     * @@roseuid 47D6223603A8
     */
    protected WEB3AdminFPTForceLogoutCompleteResponse submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        // リクエストデータの整合性をチェックする
        l_request.validate();
        
        // ログイン情報より管理者インスタンスを取得する
        WEB3Administrator l_web3Admin = WEB3Administrator.getInstanceFromLoginInfo();

        // 管理者権限チェック処理を行う
        validateAdminPermission(l_web3Admin, true);
        
        // 証券会社コードを取得する
        String l_strInstitutionCode = l_web3Admin.getInstitutionCode();
        
        // 暗証番号のチェックを行う
        l_web3Admin.validateTradingPassword(l_request.password);
        
        // 強制ログアウト処理が可能かチェックを行う
        WEB3AdminFPTForceLogoutProductCondition l_forceLogoutProductCondition = this.validateForceLogoutPossible(l_request.documentDivList);
        
        // 実行結果を削除する
        this.deleteExecuteResult(l_strInstitutionCode);
        
        // デーモントリガー一覧を取得する
        List l_lisDaemonTriggerUnits = this.getDaemonTrigerList();
        
        // ServerAccessorオブジェクトを取得する
        ServerAccessor l_serverAccessor = this.getServerAccessor();
        
        // getデーモントリガー一覧()の戻り値の要素数分、Loop処理
        int l_intSize = l_lisDaemonTriggerUnits.size();
        try
        {
            for (int i = 0; i < l_intSize; i++)
            {
                DaemonTriggerRow l_row = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                
                //updateAP呼出中(long)
                this.updateAPCalling(l_row.getThreadNo());

                //管理者・強制決済仮注文承認／非承認メインリクエスト( )
                WEB3AdminFPTForceLogoutMainRequest l_mainRequest =
                    new WEB3AdminFPTForceLogoutMainRequest();

                //証券会社コード   ＝　@get証券会社コード()の戻り値
                l_mainRequest.institutionCode = l_strInstitutionCode;

                //スレッドNo     ＝　@処理対象の要素.スレッド番号
                l_mainRequest.threadNo = new Long(l_row.getThreadNo());

                //From口座ID   ＝　@処理対象の要素.顧客コード（自）
                l_mainRequest.accountIdFrom = new Long(l_row.getRangeFrom());

                //To口座ID     ＝　@処理対象の要素.顧客コード（至）
                l_mainRequest.accountIdTo = new Long(l_row.getRangeTo());

                //管理者ID     ＝　@管理者ID
                l_mainRequest.adminId = new Long(l_web3Admin.getAdministratorId());

                //強制ログアウト対象銘柄条件  ＝　@validate強制ログアウト可能
                l_mainRequest.forceLogoutProductCondition = l_forceLogoutProductCondition;

                WEB3AffinityDescendRequest l_affinityDescendRequest = new WEB3AffinityDescendRequest();
                l_affinityDescendRequest.account_id_range = l_row.getRangeFrom() + "," + l_row.getRangeTo();
                l_affinityDescendRequest.request = new Request[1]; 
                l_affinityDescendRequest.request[0] = l_mainRequest;
                
                // （非同期）強制ログアウト処理を行う
                l_serverAccessor.doRequest(l_affinityDescendRequest);
            }
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // createResponse( )
        WEB3AdminFPTForceLogoutCompleteResponse l_response =
            (WEB3AdminFPTForceLogoutCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * 書面未承諾強制ログアウト可能チェック処理を行う。 
     * 
     * シーケンス図 
     * 「validate強制ログアウト可能」参照
     * @@param l_docDivMngArr - 書面区分管理情報
     * @@return WEB3AdminFPTForceLogoutProductCondition
     * TForceLogoutProductCondition
     * @@roseuid 47D60DEA0356
     */
    protected WEB3AdminFPTForceLogoutProductCondition validateForceLogoutPossible(WEB3FPTDocumentDivAdminInfoUnit[] l_docDivMngArr) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateForceLogoutPossible()";
        log.entering(STR_METHOD_NAME);
        
        // DB電子鳩銘柄Map
        Map l_batoProductMngMap = new HashMap();
        // 書面区分TreeSet
        TreeSet l_docDivSet = new TreeSet();
        
        
        // ログイン情報より管理者インスタンスを取得する
        WEB3Administrator l_web3Admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // デーモントリガー一覧を取得する
        List l_lisDaemonTriggerUnits = this.getDaemonTrigerList();
        
        // 証券会社コードを取得する
        String l_strInstitutionCode = l_web3Admin.getInstitutionCode();
        
        // 強制ログアウト実行結果一覧を取得する
        List l_runResultList = this.getForceLogoutExecuteResultList(l_strInstitutionCode);
        
        // 二重起動チェック
        if (l_runResultList != null)
        {
            if (l_runResultList.size() == l_lisDaemonTriggerUnits.size())
            {
                DocForceLogoutRunStatusRow l_runStatusRow = null;
                DaemonTriggerRow l_daemonTriggerRow = null;
                for (int i = 0; i < l_runResultList.size(); i++)
                {
                    l_runStatusRow = (DocForceLogoutRunStatusRow)l_runResultList.get(i);
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                    if (WEB3DocForceLogoutRunStatusStatusDef.PROCESSING.equals(l_runStatusRow.getStatus())
                        || !WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus()))
                    {
                        log.debug("指定AP起動中（二重起動エラー）。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "指定AP起動中（二重起動エラー）。");
                    }
                }
            }
            else
            {
                log.debug("指定AP起動中（二重起動エラー）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "指定AP起動中（二重起動エラー）。");
            }
        }
        
        // 部店コードを取得する
        String l_strBranchCode = l_web3Admin.getBranchCode();
        
        try
        {
            //　@書面種類管理テーブルを検索する。
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("institution_code = ? ");
            l_sbWhere.append("and branch_code = ? ");
            l_sbWhere.append("and document_category in (");
            
            List l_objList = new ArrayList();
            l_objList.add(l_strInstitutionCode);
            l_objList.add(l_strBranchCode);
    
            for (int i = 0; i < l_docDivMngArr.length; i++) 
            {
                if(i > 0)
                {
                    l_sbWhere.append(",");
                }
                l_sbWhere.append("?");
                l_objList.add(l_docDivMngArr[i].documentCategory);
            }
            
            l_sbWhere.append(")");
            Object[] l_vars = l_objList.toArray();
       
            //検索を実行
            List l_docCateMngList = Processors.getDefaultProcessor().doFindAllQuery(DocCategoryManagementRow.TYPE,l_sbWhere.toString(),l_vars);
            
            //　@書面種類管理テーブルの検索結果サイズ　@＜　@書面区分管理情報のサイズ　@の場合
            if (l_docCateMngList.size() < l_docDivMngArr.length)
            {
                log.debug("書面種類管理レコードチェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03000,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類管理レコードチェックエラー。");
            }
            
            // 書面区分TreeSetに書面区分を追加する
            for(int i = 0; i < l_docDivMngArr.length; i++)
            {
                l_docDivSet.add(l_docDivMngArr[i].documentDiv);
            }
            
            //　@電子鳩銘柄コード管理テーブルを検索する
            l_objList.clear();
            l_sbWhere = new StringBuffer();
            l_sbWhere.append("institution_code = ? ");
            l_sbWhere.append("and branch_code = ? ");
            l_sbWhere.append("and document_div in (");
            
            l_objList.add(l_strInstitutionCode);
            l_objList.add(l_strBranchCode);
    
            Iterator it = l_docDivSet.iterator();
            while(it.hasNext())
            {
                l_objList.add(it.next());
                
                if(it.hasNext())
                {
                    l_sbWhere.append("?,");
                }
                else
                {
                    l_sbWhere.append("?");
                }
            }
            l_sbWhere.append(") ");
            
            l_sbWhere.append("and substr(bato_product_code,1,3) in (");
            for (int i = 0; i < l_docDivMngArr.length; i++) 
            {
                if(i > 0)
                {
                    l_sbWhere.append(",");
                }
                l_sbWhere.append("?");
                l_objList.add(l_docDivMngArr[i].documentCategory);
            }
            l_sbWhere.append(") ");
            
            l_sbWhere.append("and valid_flag = ?");
            l_objList.add(WEB3ValidFlagDef.VALID);
            
            l_vars = l_objList.toArray();
    
            List l_batoProductMngList = null;
    
            // 検索を実行
            l_batoProductMngList = 
                Processors.getDefaultProcessor().doFindAllQuery(
                        BatoProductManagementRow.TYPE,
                        l_sbWhere.toString(),
                        l_vars);
            
            // 電子鳩銘柄コード管理テーブルの検索結果が0サイズの場合
            if (l_batoProductMngList == null || l_batoProductMngList.size() == 0)
            {
                log.debug("電子鳩銘柄コード管理レコードチェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "電子鳩銘柄コード管理レコードチェックエラー。");
            }

            // DB電子鳩銘柄Mapにキー＝電子鳩銘柄コードの先頭３桁、値＝Rowでセットする
            for (int i = 0; i < l_batoProductMngList.size(); i++)
            {
                BatoProductManagementRow l_batoProductMngRow = (BatoProductManagementRow)l_batoProductMngList.get(i);
                l_batoProductMngMap.put(l_batoProductMngRow.getBatoProductCode().substring(0, 3), l_batoProductMngRow);
            }
            
            for (int i = 0; i < l_docDivMngArr.length; i++) 
            {
                // メッセージ DB電子鳩銘柄Map.containsKey(書面区分管理情報.書面種類コード)＝＝falseの場合
                if (l_batoProductMngMap.containsKey(l_docDivMngArr[i].documentCategory) == false)
                {
                    log.debug("電子鳩銘柄コード管理レコードチェックエラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "電子鳩銘柄コード管理レコードチェックエラー。");
                }
            }
            
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
               
        WEB3AdminFPTForceLogoutProductCondition l_forceLogoutProductCondition = new WEB3AdminFPTForceLogoutProductCondition();
        l_forceLogoutProductCondition.documentCatCodeArr = new String[l_docDivMngArr.length];       
        for (int i = 0; i < l_docDivMngArr.length; i++) 
        {
            l_forceLogoutProductCondition.documentCatCodeArr[i] = l_docDivMngArr[i].documentCategory;
        }
        l_forceLogoutProductCondition.documentDivArr = new String[l_docDivSet.size()];   
        l_docDivSet.toArray(l_forceLogoutProductCondition.documentDivArr);
        int l_index=0;
        l_forceLogoutProductCondition.batoProductCodeArr = new String[l_batoProductMngMap.values().size()]; 
        for (Iterator iter = l_batoProductMngMap.values().iterator(); iter.hasNext();)
        {
            BatoProductManagementRow l_row = (BatoProductManagementRow) iter.next();
            l_forceLogoutProductCondition.batoProductCodeArr[l_index] = l_row.getBatoProductCode();
            l_index++;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_forceLogoutProductCondition;
    }
    
    /**
     * 管理者 書面未承諾 強制ログアウトの管理者権限チェック処理を行う。 
     * 
     * 
     * １）引数.is全部店許可==falseの場合、
     * 　@全部店許可の管理者でない場合エラー
     * 　@（BUSINESS_ERROR_01380）
     * 
     * ２）引数.validate権限
     * 　@[引数]
     *   機@能カテゴリコード："G0105"(書面未承諾強制ログアウト)
     *   is更新：引数.更新フラグ
     * @@param l_admin - 管理者
     * @@param l_updateFlg - 更新フラグ
     * @@roseuid 47D60FDE039E
     */
    protected void validateAdminPermission(WEB3Administrator l_admin, boolean l_updateFlg) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validateAdminPermission()";
        log.entering(STR_METHOD_NAME);
        
        if (l_admin.isAllBranchsPermission() == false)
        {
            log.debug("全部店許可の管理者でない場合は、操作不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01380,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "全部店許可の管理者でない場合は、操作不可。");
        }
        
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.FPT_FORCE_LOGOUT,
            l_updateFlg);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getデーモントリガー一覧
     * 
     * 書面未承諾 強制ログアウト
     * デーモントリガーテーブルのレコードを返却する。 
     * 
     * １）　@DB検索 
     * 　@以下の条件でデーモントリガーテーブルを検索する。 
     * 　@[条件] 
     * 　@　@処理タイプ = "書面未承諾 強制ログアウト" 
     * 
     * 　@[ソート条件] 
     * 　@　@スレッド番号　@昇順 
     * 
     * 　@該当データなしの場合、「該当データなし」の 
     * 　@システムエラーをスローする。 
     * 　@（SYSTEM_ERROR_80005）
     * 
     * ２）　@検索結果を返却する。
     * @@return java.util.List
     * @@roseuid 47D6115803CC
     */
    protected java.util.List getDaemonTrigerList() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDaemonTrigerList()";
        log.entering(STR_METHOD_NAME);

        String l_strTriggerType = WEB3DaemonTriggerTypeDef.FPT_FORCE_LOGOUT;

        Object[] l_objValues = {l_strTriggerType};

        //　@[ソート条件]
        String l_strCondition = "thread_no asc";

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DaemonTriggerRow.TYPE,
                " trigger_type = ?",
                l_strCondition,
                null,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@該当データなしの場合、「該当データなし」の
        //　@システムエラーをスローする。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lisRecords;
    }
    
    /**
     * get強制ログアウト実行結果一覧
     * 
     * 引数の条件に該当する書面未承諾 強制ログアウト実行結果テーブルの 
     * レコードを返却する。 
     * 
     * １）　@DB検索 
     * 　@以下の条件でオンライン実行結果テーブルを検索する。 
     * 　@[条件] 
     * 　@　@証券会社コード = パラメータ.証券会社コード 
     * 
     * 　@該当データなしの場合、nullを返却する。 
     * 
     * ２）　@検索結果を返却する。
     * @@param 証券会社コード - 証券会社コード
     * @@return java.util.List
     * @@roseuid 47D6131E03C1
     */
    protected java.util.List getForceLogoutExecuteResultList(String l_institutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getForceLogoutExecuteResultList(String)";
        log.entering(STR_METHOD_NAME);

        String l_strWhere = " institution_code = ?";

        Object[] l_objValues = {l_institutionCode};

        List l_lisRecords = null;
        try
        {
            l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                DocForceLogoutRunStatusRow.TYPE,
                l_strWhere,
                l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //　@該当データなしの場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lisRecords;
    }
    
    /**
     * delete実行結果
     * 
     * 条件に該当する書面未承諾 強制ログアウト実行結果テーブルの 
     * レコードを物理削除する。 
     * 
     * １）以下の条件に該当するレコードをdeleteする。 
     * 
     * 　@[条件] 
     * 　@　@証券会社コード = 引数.証券会社コード 
     * 
     * 　@※削除対象のレコードがなくても例外としないこと。 
     * 　@※本処理は新規トランザクションで処理を行い、 
     * 　@　@処理完了時に更新が反映されるようにすること。
     * @@param 証券会社コード - 証券会社コード
     * @@roseuid 47D627B70236
     */
    protected void deleteExecuteResult(String l_institutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteExecuteResult(String)";
        log.entering(STR_METHOD_NAME);

        final String l_strWhere = " institution_code = ? ";
        final String l_strCondition = "for update";
        final Object[] l_objValues = {l_institutionCode};

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_queryProcesser.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
                        {
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                DocForceLogoutRunStatusRow.TYPE,
                                l_strWhere,
                                l_strCondition,
                                l_objValues);
                            int l_intSize =  l_lisRows.size();
                            for (int i = 0; i < l_intSize; i++)
                            {
                                DocForceLogoutRunStatusRow l_row = (DocForceLogoutRunStatusRow)l_lisRows.get(i);
                                WEB3DataAccessUtility.deleteRow(l_row);
                            }
                            log.exiting(STR_METHOD_NAME);
                            return null;
                        }
                    }
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * 負荷分散処理を行う為のServerAccessorオブジェクトを 
     * 返却する。 
     * 
     * １）　@クラスタリング先サーバーURLを取得する。 
     * 　@　@QueryProcessor.doFindAllQuery()メソッドを 
     * 　@　@コールする。 
     * 
     * 　@　@[doFindAllQuery()にセットするパラメータ] 
     * 　@　@　@arg0：　@ServerConfigRow.TYPE 
     * 　@　@　@arg1：　@"config_categ = ?" 
     * 　@　@　@arg2：　@"cluster.urls"のみを要素とする配列 
     * 
     * 　@　@※"cluster.urls"は定数定義クラス参照すること。 
     * 
     * 　@　@検索結果の各要素.config_valueを取得し、文字列配列を 
     * 　@　@作成する。 
     * 　@　@※検索結果が取得できなかった場合、「該当データなし」の 
     * 　@　@　@システムエラーをスローする。 
     * 
     * ２）　@ServerAccessorの作成 
     * 　@ServerConnector.createAccessor()メソッドをコールし、 
     * 　@戻り値をthis.accessorにセットした後、戻り値を返却する。 
     * 
     * 　@[craeteAccessor()にセットするパラメータ] 
     * 　@　@arg0（ACCESSOR_KEY）：　@"web3-static-cluster" 
     * 　@　@arg1（URL）：　@１）にて作成した文字列配列 
     * 
     * 　@　@※"web3-static-cluster"は定数定義クラス参照すること。
     * @@return ServerAccessor
     * @@roseuid 47D6296000F0
     */
    protected ServerAccessor getServerAccessor() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getServerAccessor()";
        log.entering(STR_METHOD_NAME);

        String l_strQueryWhere = "config_categ = ?";
        Object[] l_bindValues = {WEB3ServerUrlAccessorDef.CLUSTER_URLS};
        List l_lisRecords = null;

        try
        {
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcesser.doFindAllQuery(
                ServerConfigRow.TYPE,
                l_strQueryWhere,
                l_bindValues);

            if (l_lisRecords == null || l_lisRecords.size() == 0)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@ServerAccessorの作成
        int l_intLength = l_lisRecords.size();
        ArrayList l_lisConfigValues = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            ServerConfigRow l_serverConfigRow = (ServerConfigRow)l_lisRecords.get(i);
            l_lisConfigValues.add(l_serverConfigRow.getConfigValue());
        }

        String[] l_strServerConfigList = new String[l_lisConfigValues.size()];
        l_lisConfigValues.toArray(l_strServerConfigList);

        ServerAccessor l_serverAccessor = null;
        try
        {
            l_serverAccessor = ServerConnector.createAccessor(
                WEB3ServerUrlAccessorDef.WEB3_STATIC_CLUSTER,
                l_strServerConfigList);
        }
        catch (CommunicationException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (ServerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_serverAccessor;
    }
    
    /**
     * 引数のスレッドNoに該当するデーモントリガーの 
     * レコードを、"AP呼出中"でupdateする。 
     * 
     * １）　@以下の条件に該当するデーモントリガーテーブルの 
     * 　@レコードをupdateする。 
     * 
     * 　@[条件] 
     * 　@　@スレッド番号 = パラメータ.スレッドNo 
     * 
     * 　@[更新値] 
     * 　@　@処理状態 = "トリガー（AP呼出中）" 
     * 
     * 　@※本処理は新規トランザクションで処理を行い、 
     * 　@　@処理完了時に更新が反映されるようにすること。
     * @@param スレッドNo - スレッドNo
     * @@roseuid 47D62D940191
     */
    protected void updateAPCalling(long l_threadNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAPCalling(long)";
        log.entering(STR_METHOD_NAME);

        final int UPDATE_SUCCESS = 0; // 更新処理が正常終了した値
        final int UPDATE_FAIL = -1; // 更新対象スレッド情報の更新に失敗した場合の値
        final int NO_ROWS = -2; // 更新対象スレッド情報を取得できなかった場合の値

        try
        {
            final HashMap l_changes = new HashMap();
            l_changes.put("trigger_status", WEB3DaemonTriggerStatusDef.THREAD_API_CALL);

            final String l_strWhere = "thread_no = ?";
            final String l_strCondition = "for update";
            final Object l_bindVars[] = {new Long(l_threadNo)};
            final QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            Integer l_intResult = (Integer)
                l_queryProcesser.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new TransactionCallback()
                    {
                        public Object process() throws DataNetworkException,
                            DataQueryException,
                            DataCallbackException
                        {
                            Integer l_intResult = null;
                            List l_lisRows = l_queryProcesser.doFindAllQuery(
                                DaemonTriggerRow.TYPE,
                                l_strWhere, l_strCondition, l_bindVars);
                            if (l_lisRows != null && l_lisRows.size() > 0)
                            {
                                DaemonTriggerRow l_row =
                                    (DaemonTriggerRow)l_lisRows.get(0);
                                WEB3DataAccessUtility.updateRow(l_row, l_changes);
                                l_intResult = new Integer(UPDATE_SUCCESS);
                            }
                            else
                            {
                                l_intResult = new Integer(NO_ROWS);
                            }
                            return l_intResult;
                        }
                    }
                );

            if (l_intResult.intValue() == UPDATE_FAIL)
            {
                log.error("DBへのアクセスに失敗しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。");
            }
            else if (l_intResult.intValue() == NO_ROWS)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 書面未承諾 強制ログアウト結果照会画面表示処理を行う。 
     * 
     * シーケンス図 
     * 「(管理者 書面未承諾 強制ログアウトサービス)get結果照会」参照。
     * @@param l_request - 管理者 書面未承諾 
     * 強制ログアウト結果照会リクエスト
     * @@return WEB3AdminFPTForceLogoutReferenceResponse
     * TForceLogoutReferenceResponse
     * @@roseuid 47D7B8E101BE
     */
    protected WEB3AdminFPTForceLogoutReferenceResponse getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFPTForceLogoutReferenceResponse l_response = null;
        
               
        // ログイン情報より管理者インスタンスを取得する
        WEB3Administrator l_web3Admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 管理者権限チェック処理を行う
        validateAdminPermission(l_web3Admin, false);
        
        // 部店コードを取得する
        String l_strBranchCode = l_web3Admin.getBranchCode();

        // 証券会社コードを取得する
        String l_strInstitutionCode = l_web3Admin.getInstitutionCode();
        
        // デーモントリガー一覧を取得する
        List l_lisDaemonTriggerUnits = this.getDaemonTrigerList();
        
        // 強制ログアウト実行結果一覧を取得する
        List l_runResultList = this.getForceLogoutExecuteResultList(l_strInstitutionCode);
               
        String l_executeResult = null;
        Date l_executeStartTime = null;
        Date l_executeStopTime = null;
        long l_executeResultCount = 0;
        String l_updaterCode = null;
        TreeSet l_documentDivSet = new TreeSet();
        Map l_documentDivMap = new HashMap();
        String[] l_docDivString = null;
        
        if (l_runResultList == null)
        {
            // 強制ログアウト実行結果が取得できなかった場合
            l_response = (WEB3AdminFPTForceLogoutReferenceResponse)l_request.createResponse();
            return l_response;
        }
        else
        {
            //処理ステータス   ＝　@以下の分岐によりセットする。
            if (l_lisDaemonTriggerUnits.size() != l_runResultList.size())
            {
                l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESSING;
            }
            else 
            {
                int l_intSize = l_lisDaemonTriggerUnits.size();
                DaemonTriggerRow l_daemonTriggerRow = null;
                DocForceLogoutRunStatusRow l_onlineRunStatusRow = null;
                int l_intFlag = 0;
                for(int i = 0; i < l_intSize; i++)
                {
                    l_daemonTriggerRow = (DaemonTriggerRow)l_lisDaemonTriggerUnits.get(i);
                    l_onlineRunStatusRow = (DocForceLogoutRunStatusRow)l_runResultList.get(i);
                    //　@・取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
                    //　@・取得したオンライン実行結果レコード.実行ステータス区分に"処理中"が1件でも存在する場合
                    if (WEB3DaemonTriggerStatusDef.THREAD_PROCESSING.equals(l_daemonTriggerRow.getTriggerStatus()) 
                        || WEB3DocForceLogoutRunStatusStatusDef.PROCESSING.equals(l_onlineRunStatusRow.getStatus()))
                    {
                        l_intFlag = 1;
                        break;
                    }
                    else if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerRow.getTriggerStatus())
                        || !WEB3DocForceLogoutRunStatusStatusDef.PROCESSED.equals(l_onlineRunStatusRow.getStatus()))
                    {
                        l_intFlag = 2;                 
                    }
                }
                //①@"処理中"をセットする条件
                if (l_intFlag == 1)
                {
                    l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESSING;
                }
                //　@②"処理済"をセットする条件
                //　@・取得した全てのデーモントリガーレコード.処理状態 == "未稼動"　@かつ
                //　@取得した全てのオンライン実行結果レコード.実行ステータス区分 == "処理済"
                else if (l_intFlag == 0)
                {
                    l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESSED;
                }
                //"エラー"をセットする条件
                //以外の場合
                else if (l_intFlag == 2)
                {
                    l_executeResult = WEB3DocForceLogoutRunStatusStatusDef.PROCESS_ERROR;
                }
            }
            
            for (int i=0;i<l_runResultList.size();i++)
            {
                DocForceLogoutRunStatusRow l_runStatusRow = (DocForceLogoutRunStatusRow)l_runResultList.get(i);
                
                if (l_executeStartTime == null ||
                    WEB3DateUtility.compare(l_executeStartTime,l_runStatusRow.getStartTimestamp()) > 0)
                {
                    // 開始日時＝対象レコード.get開始日時
                    l_executeStartTime = l_runStatusRow.getStartTimestamp();
                }
                
                if (l_executeStopTime == null ||
                    WEB3DateUtility.compare(l_executeStopTime,l_runStatusRow.getEndTimestamp()) < 0)
                {
                    // 終了日時＝対象レコード.get終了日時
                    l_executeStopTime = l_runStatusRow.getEndTimestamp();
                }
                
                if (l_docDivString == null)
                {
                    l_docDivString = l_runStatusRow.getDocumentCategoryList().split(",");
                    for (int j=0;j<l_docDivString.length;j++)
                    {
                        // 書面種類コード一覧TreeSetに書面種類コードをaddする
                        l_documentDivSet.add(l_docDivString[j]);
                    }
                }
                
                if (l_updaterCode == null)
                {
                    // 更新者コード＝対象レコード.get更新者コード
                    l_updaterCode = l_runStatusRow.getLastUpdater();
                }
                
                // 処理件数 += 対象レコード.get処理件数
                l_executeResultCount += l_runStatusRow.getProcessCount();
                
            }
        }
        
        // 書面種類コード一覧TreeSetのサイズ＞0 の場合
        if (l_documentDivSet.size() > 0)
        {
            // 書面区分管理を生成する
            WEB3AdminFPTDocDivManagement l_management = 
                new WEB3AdminFPTDocDivManagement(
                        l_strInstitutionCode,
                        l_strBranchCode,
                        null,
                        null);
            
            // 書面区分管理情報を取得する
            WEB3FPTDocumentDivAdminInfoUnit[] l_infoUnit = l_management.getDocDivManagementLists();
            
            for (int k=0;k<l_infoUnit.length;k++)
            {
                // 書面チェック区分＝金商法@の場合
                if (WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW.equals(l_infoUnit[k].docuCheckDiv))
                {
                    l_documentDivMap.put(l_infoUnit[k].documentCategory, l_infoUnit[k]);
                }
            }
            
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivUnits = new WEB3FPTDocumentDivAdminInfoUnit[l_documentDivSet.size()];
            Iterator it = l_documentDivSet.iterator();
            int l_documentDivUnitNum = 0;
            while(it.hasNext())
            {
                // 書面区分管理Map.get(書面種類コード)で書面区分管理情報を取得する
                WEB3FPTDocumentDivAdminInfoUnit l_documentDivUnit = (WEB3FPTDocumentDivAdminInfoUnit)l_documentDivMap.get(it.next());
                
                if (l_documentDivUnit == null)
                {
                    log.debug("書面区分管理レコードチェックエラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02998,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "書面区分管理レコードチェックエラー。");
                }
                
                l_documentDivUnits[l_documentDivUnitNum] = l_documentDivUnit;
                l_documentDivUnitNum++;
            }
            
            WEB3FPTForceLogoutInfoUnit[]  l_forceLogoutInfoList = null;
            WEB3FPTForceLogoutInfoUnit    l_forceLogoutInfoUnit = null;
            
            l_forceLogoutInfoUnit = new WEB3FPTForceLogoutInfoUnit();
            l_forceLogoutInfoUnit.transactionResult = l_executeResult;
            l_forceLogoutInfoUnit.transactionStartDate = l_executeStartTime;
            if (WEB3DocForceLogoutRunStatusStatusDef.PROCESSED.equals(l_executeResult) ||
                WEB3DocForceLogoutRunStatusStatusDef.PROCESS_ERROR.equals(l_executeResult))
            {
                l_forceLogoutInfoUnit.transactionEndDate = l_executeStopTime; 
            }
            l_forceLogoutInfoUnit.endCount = Long.toString(l_executeResultCount);
            l_forceLogoutInfoUnit.updaterCode = l_updaterCode;
            l_forceLogoutInfoUnit.documentDivList = l_documentDivUnits;
            
            l_forceLogoutInfoList = new WEB3FPTForceLogoutInfoUnit[1];
            l_forceLogoutInfoList[0] = l_forceLogoutInfoUnit;

            // createResponse() 
            l_response = (WEB3AdminFPTForceLogoutReferenceResponse) l_request.createResponse();
            l_response.forceLogoutInfoList = l_forceLogoutInfoList;
        }
               
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * 書面未承諾 強制ログアウト処理を実施する。
     * 
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 
     * 
     * ○ 引数のリクエストデータが、管理者 書面未承諾 
     * 強制ログアウト入力リクエストの場合 
     * 　@－get入力画面()をコールする。 
     * ○ 引数のリクエストデータが、管理者 書面未承諾 
     * 強制ログアウト確認リクエストの場合 
     * 　@－validate強制ログアウト()をコールする。 
     * ○ 引数のリクエストデータが、管理者 書面未承諾 
     * 強制ログアウト完了リクエストの場合 
     * 　@－submit強制ログアウト()をコールする。 
     * ○ 引数のリクエストデータが、管理者 書面未承諾 
     * 強制ログアウト結果照会リクエストの場合 
     * 　@－get結果照会()をコールする。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@roseuid 47DB254200B7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminFPTForceLogoutInputRequest)
        {
            l_response =
                this.getInputPage(
                    (WEB3AdminFPTForceLogoutInputRequest) l_request);
        } 
        else if (l_request instanceof WEB3AdminFPTForceLogoutConfirmRequest)
        {
            l_response =
                this.validateForceLogout(
                    (WEB3AdminFPTForceLogoutConfirmRequest) l_request);
        } 
        else if (l_request instanceof WEB3AdminFPTForceLogoutCompleteRequest)
        {
            l_response =
                this.submitForceLogout(
                    (WEB3AdminFPTForceLogoutCompleteRequest) l_request);
        } 
        else if (l_request instanceof WEB3AdminFPTForceLogoutReferenceRequest)
        {
            l_response =
                this.getResultRefrence(
                    (WEB3AdminFPTForceLogoutReferenceRequest) l_request);
        } 
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
