head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力再計算ServiceImplクラス(WEB3AdminTPReCalcServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPOccurredDivDef;
import webbroker3.tradingpower.define.WEB3TPRealCalcFlagDef;
import webbroker3.tradingpower.define.WEB3TPStatusDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPReCalcService;
import webbroker3.util.WEB3LogUtility;

/**
 * 余力再計算ServiceImplクラス
 */
public class WEB3AdminTPReCalcServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPReCalcService 
{
    /**
     * ログ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPReCalcServiceImpl.class);
    
    /**
     * @@roseuid
     */
    public WEB3AdminTPReCalcServiceImpl() 
    {    
    }
   
    /**
    * 余力再計算処理を行う。 
    * 
    * ○資産余力再計算リクエストの場合 
    * 　@this.submit資産余力再計算()メソッドをコールする。
    * @@param l_request - リクエスト
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
   		throws WEB3BaseException
   	{
        final String METHOD_NAME = "execute(WEB3GenRequest)";

        log.entering(METHOD_NAME);
      
        WEB3GenResponse l_response = null;
      
		if(l_request instanceof WEB3AdminTPReCalcRequest)
		{
		    l_response =  this.createReCalc((WEB3AdminTPReCalcRequest)l_request);           
		}
        else if(l_request instanceof WEB3AdminTPReCalcInputRequest)
        {
            l_response =  this.getReCalcInput((WEB3AdminTPReCalcInputRequest)l_request);           
        }
		else
		{
		    //予期せぬエラー
		    throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());
		}
		log.exiting(METHOD_NAME);
		return l_response;
      
   	}
   
   /**
    * 余力再計算実行処理を行う。
    * 
    * １）リクエスト入力項目チェック。
    * 　@・部店コード
    * 　@・顧客コード
    * 
    * ２）権限をチェックする。
    * 　@・管理者権限
    * 　@・管理者部店権限
    * 　@・期間チェック
    * 
    * ３）this.get顧客Params一覧()を呼ぶ.
    * 
    * ４）this.insert余力計算キューParams()を呼ぶ.
    * 
    * ５）余力機@能制御検索結果レスポンスを作成し
    * プロパティに値をセットする。
    * 
    * ６）レスポンスを返却する。
    * @@param l_request - 余力再計算実行リクエスト
    * @@return WEB3AdminTPReCalcResponse
    * @@roseuid
    */
   protected WEB3AdminTPReCalcResponse createReCalc(WEB3AdminTPReCalcRequest l_request) 
   {
        final String METHOD_NAME = "createReCalc(WEB3AdminTPReCalcRequest l_request)";
        int l_intInsertCnt = 0;    //INSERT件数
        int l_intResultCnt = 0;    //実行件数
        int l_intFailCnt = 0;      //失敗件数

        WEB3AdminTPReCalcResponse l_response = new WEB3AdminTPReCalcResponse();

        try
        {

            //リクエスト入力項目チェック。
            l_request.validate();
      
            //管理者権限をチェックする。
            WEB3Administrator l_admin;  //クラス宣言
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);

            //変数の設定
            String l_strBranchCodes     = l_request.branchCodes;        //管理者部店コード
            String l_strAccountProperty = l_request.accountProperty;    //対象顧客区分
            String l_strAccountCodeSt   = l_request.accountCodeSt;      //顧客コード(自)
            String l_strAccountCodeEd   = l_request.accountCodeEd;      //顧客コード(至)

            //管理者部店権限をチェックする。
            l_admin.validateBranchPermission(l_strBranchCodes);

            //証券会社IDを取得する
            long l_lngInstitutionId = l_admin.getInstitution().getInstitutionId();

            //顧客マスタより検索を行う
            List l_rows = this.getAccountParamsList(l_lngInstitutionId, l_strBranchCodes,l_strAccountProperty,
                                                              l_strAccountCodeSt,l_strAccountCodeEd);

            for(int i = 0; i < l_rows.size(); i++)
            {
                MainAccountRow l_row = (MainAccountRow)l_rows.get(i);
                long l_intAccountId = l_row.getAccountId();
                    
                //余力計算キューテーブルにINSERTを行う
                l_intInsertCnt = this.insertExecNotifyParams(l_intAccountId); 

                if (l_intInsertCnt > 0)
                {
                    //正常の場合には、実行件数にカウントを足し込み
                    l_intResultCnt = l_intResultCnt + 1;
                }
                else
                {
                    //エラーの場合には、非実行件数にカウントを足し込み
                    l_intFailCnt = l_intFailCnt + 1;
                }
            }

            //現在時刻を取得
            Timestamp l_timestamp = null;
            l_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            
            //レスポンスデータの設定
            l_response.practiceCnt   = Integer.toString(l_intResultCnt);
            l_response.failCnt       = Integer.toString(l_intFailCnt);
            l_response.receiptDay    = l_timestamp;

        }
        catch(Exception e)
        {
            //カスタマイズ定義済みエラーの場合
            if(e instanceof WEB3BaseException)
            {
                l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();               
            }
            else
            {    
                //予期せぬエラー
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;               
            }
           
            log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);
        }
        
        //処理終了
        return l_response;                      
   }

   /**
    * 余力再計算入力処理を行う。
    * 
    * １）権限をチェックする。
    * 　@・管理者権限
    * 　@・管理者部店権限
    * 　@・期間チェック
    * 
    * ２）レスポンスを返却する。
    * @@param l_request - 余力再計算入力リクエスト
    * @@return WEB3AdminTPReCalcInputResponse
    * @@roseuid
    */
   protected WEB3AdminTPReCalcInputResponse getReCalcInput(WEB3AdminTPReCalcInputRequest l_request) 
   {
        final String METHOD_NAME = "getReCalcInput(WEB3AdminTPReCalcInputRequest l_request)";

        WEB3AdminTPReCalcInputResponse l_response = new WEB3AdminTPReCalcInputResponse();

        try
        {
      
            //管理者権限をチェックする。
            WEB3Administrator l_admin;  //クラス宣言
            l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, true);

        }
        catch(Exception e)
        {
            //カスタマイズ定義済みエラーの場合
            if(e instanceof WEB3BaseException)
            {
                l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();               
            }
            else
            {    
                //予期せぬエラー
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;               
            }
           
            log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);
        }
        
        //処理終了
        return l_response;                      
   }


   /**
    * (get顧客Params一覧)<BR>
    *
    *条件：
    * 顧客テーブルから以下の条件で検索した結果を返却する。<BR>
    * 検索条件：<BR>
    *  顧客テーブル.証券会社ID = 引数.証券会社ID AND<BR> 
    *  顧客テーブル.部店コード = 引数.部店コード AND<BR>
    * 
    * 動的検索条件：<BR>
    *   以下の条件に該当する場合に条件を追加する。
    * 
    *   [対象顧客区分【1:現物】]
    *    ・顧客テーブル.制度信用取引口座開設区分 = DEFAULT(0) AND
    *      顧客テーブル.一般信用取引口座開設区分 = DEFAULT(0)
    * 
    *   [対象顧客区分【2:信用】]
    *    ・顧客テーブル.制度信用取引口座開設区分 = 口座開設(1) OR
    *      顧客テーブル.一般信用取引口座開設区分 = 口座開設(1)
    * 
    *   [対象顧客区分【4:顧客指定】]
    *    ・顧客テーブル.顧客コード => 引数.顧客コード(自) AND
    *      顧客テーブル.顧客コード =< 引数.顧客コード(至)
    *
    * @@param l_lngInstitutionId - 証券会社ID
    * @@param l_branchCodes      - 部店コード
    * @@param l_accountProperty  - 顧客属性
    * @@param l_accountCodeSt    - 顧客コード(自)
    * @@param l_accountCodeEd    - 顧客コード(至)
    * @@return List
    * @@roseuid
    */
   protected List getAccountParamsList(long l_lngInstitutionId, String l_branchCodes,String l_accountProperty,String l_accountCodeSt,String l_accountCodeEd) 
     throws WEB3BaseException
   {
        final String METHOD_NAME = "getAccountParamsList(Long l_lngInstitutionId, String l_branchCodes,String l_accountProperty,String l_accountCodeSt,String l_accountCodeEd)";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //検索条件追加（部店コード）
        l_sbWhere.append("INSTITUTION_ID = ?");        
        l_sbWhere.append(" and BRANCH_CODE = ?");
        l_lisBindVars.add(new Long(l_lngInstitutionId));
        l_lisBindVars.add(l_branchCodes);

        //動的検索条件追加（対象顧客区分）
        if(l_accountProperty.equals("1"))
        {
            //対象顧客区分が現物の場合
            l_sbWhere.append(" and MARGIN_SYS_ACC_OPEN_DIV = ?");
            l_sbWhere.append(" and MARGIN_GEN_ACC_OPEN_DIV = ?");
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
        }
        else if(l_accountProperty.equals("2"))
        {
            //対象顧客区分が信用の場合
            l_sbWhere.append(" and ( MARGIN_SYS_ACC_OPEN_DIV = ?");
            l_sbWhere.append(" or MARGIN_GEN_ACC_OPEN_DIV = ? )");
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_lisBindVars.add(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
        }
        else if(l_accountProperty.equals("4"))
        {
            //対象顧客区分が顧客指定の場合
            l_sbWhere.append(" and ACCOUNT_CODE >= ?");
            l_sbWhere.append(" and ACCOUNT_CODE <= ?");
            l_lisBindVars.add(l_accountCodeSt);
            l_lisBindVars.add(l_accountCodeEd);
        }
       
        final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();
        log.debug("l_strWhere=" + l_strWhere);
        log.debug("l_bindVars=" + l_bindVars);
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            return l_qp.doFindAllQuery(MainAccountRow.TYPE, l_strWhere, l_bindVars);
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());                                         
        }                      
   }

   /**
    * (insert余力計算キューテーブル)
    *
    * １）必要な変数をパラメータに設定する<BR>
    * ２）更新内容を余力計算キューDBへ保存する<BR>
    *
    * @@param l_intAccountId　@- 口座ID<BR>
    * @@roseuid
    */
   public int insertExecNotifyParams(long l_lngAccountId) throws WEB3BaseException
   {
        final String METHOD_NAME = "insertExecNotifyParams(long)";
        int l_intRowCnt = 0;
        
        TpCalcResultExecNotifyParams l_params = new TpCalcResultExecNotifyParams();
        
        try
        {
            //時刻を取得
            Timestamp l_timestamp = 
                (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

            //パラメータの設定
            l_params.setAccountId(l_lngAccountId);            
            l_params.setOccurredDiv(WEB3TPOccurredDivDef.ADMIN);
            l_params.setRealCalcFlag(WEB3TPRealCalcFlagDef.CLOSING_PRICE);
            l_params.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);            
            l_params.setStatus(WEB3TPStatusDef.NOT_DEAL);
            l_params.setCreatedTimestamp(l_timestamp);
            l_params.setLastUpdatedTimestamp(l_timestamp);

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_qp.doInsertQuery(l_params);
            l_intRowCnt = l_intRowCnt + 1;

       }
       catch(DataQueryException dq)
       {
           //このエラーの場合には、失敗としてカウントを０で返すため何も処理しない
           log.error(dq.getMessage(), dq);
       }       
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
       catch(Exception e)
       {
           log.error(e.getMessage(), e);
           //予期しないエラー
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME);
       }

       return l_intRowCnt;
   }


}
@
