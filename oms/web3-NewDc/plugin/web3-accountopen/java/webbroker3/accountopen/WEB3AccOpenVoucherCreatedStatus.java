head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherCreatedStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票作成ステータス(WEB3AccOpenVoucherCreatedStatus.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 李頴淵 新規作成
*/

package webbroker3.accountopen;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設伝票作成ステータス)<BR>
 * 口座開設伝票作成ステータス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0 
 */
public class WEB3AccOpenVoucherCreatedStatus implements BusinessObject
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenVoucherCreatedStatus.class);
    
    /**
     * (口座開設伝票作成ステータス行)<BR>
     * 口座開設伝票作成ステータス行オブジェクト<BR>
     * <BR>
     * ※ 口座開設伝票作成ステータスParamsクラスはDDLより自動生成する。<BR>
     */
    private AccOpenVoucherStatusParams accOpenVoucherStatusParams;

    /**
     * (口座開設伝票作成ステータス)<BR>
     * コンストラクタ。<BR>
     * 指定行オブジェクトをプロパティにセットし、インスタンスを生成する。 <BR>
     * <BR>
     * ※ 口座開設伝票作成ステータスParamsクラスはDDLより自動生成する。<BR>
     * @@param l_accOpenVoucherStatusParams - 口座開設伝票作成ステータス行オブジェクト<BR>
     * <BR>
     * ※ 口座開設伝票作成ステータスParamsクラスはDDLより自動生成する。<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus
     * @@roseuid 4191977E0091
     */
    public WEB3AccOpenVoucherCreatedStatus(AccOpenVoucherStatusParams l_accOpenVoucherStatusParams)
    {
        final String STR_METHOD_NAME = " WEB3AccOpenVoucherCreatedStatus(AccOpenVoucherStatusParams)";
        log.entering(STR_METHOD_NAME);
        this.accOpenVoucherStatusParams = l_accOpenVoucherStatusParams;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.口座開設伝票作成ステータス行を返却する。 <BR>
     * @@return Object
     * @@roseuid 419196EF0311
     */
    public Object getDataSourceObject()
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
         
        log.exiting(STR_METHOD_NAME);
        return this.accOpenVoucherStatusParams;
    }

    /**
     * (get伝票作成ステータス)<BR>
     * this.口座開設伝票作成ステータス行.伝票作成ステータスを返却する。<BR>
     * @@return String
     * @@roseuid 419332890007
     */
    public String getVoucherStatus()
    {
        final String STR_METHOD_NAME = " getVoucherStatus()";
        log.entering(STR_METHOD_NAME);
        
        String l_strVoucherStatus = this.accOpenVoucherStatusParams.getVoucherStatus();
        
        log.exiting(STR_METHOD_NAME);
        return l_strVoucherStatus;
    }

    /**
     * (get口座開設伝票作成ステータス)<BR>
     * 証券会社コード，識別コードに該当する口座開設伝票作成ステータスを<BR>
     * すべて取得する。<BR>
     * <BR>
     * 以下の条件で口座開設伝票作成ステータステーブルを検索する。<BR>
     * （該当行がない場合は、nullを返却する）<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@口座開設伝票作成ステータス.証券会社コード = 証券会社コード<BR>
     * 　@口座開設伝票作成ステータス.識別コード = 識別コード<BR>
     * <BR>
     * 検索結果の各口座開設伝票作成ステータス行オブジェクトにて<BR>
     * 口座開設伝票作成ステータスオブジェクトを生成し、配列にて返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus[]
     * @@roseuid 41919C3D0052
     */
    public static WEB3AccOpenVoucherCreatedStatus[] getAccOpenVoucherCreatedStatus(String l_strInstitutionCode, String l_strRequestNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccOpenVoucherCreatedStatus(String, String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor(); //DataNetworkException, DataQueryException
            String l_strWhere =
                "institution_code = ? and " +        //口座開設伝票作成ステータス.証券会社コード = 証券会社コード 
                "acc_open_request_number = ? ";      //口座開設伝票作成ステータス.識別コード = 識別コード  

            
            Object l_bindVars[] =
                {l_strInstitutionCode,
                 l_strRequestNumber};
                    
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                AccOpenVoucherStatusRow.TYPE,
                l_strWhere,
                l_bindVars);
                
            int l_intSize = 0;
            if (l_lisRows != null)
            {
                l_intSize = l_lisRows.size();
            }
            
            if (l_intSize == 0)
            {
                log.debug("該当行がない場合は、nullを返却する");
                log.exiting(STR_METHOD_NAME);
                return null;    
            }
            else
            {
                List l_lis = new ArrayList();
                for (int i = 0; i < l_intSize; i ++)
                {
                    AccOpenVoucherStatusParams l_accOpenVoucherStatusParams = 
                        new AccOpenVoucherStatusParams((AccOpenVoucherStatusRow)l_lisRows.get(i));
                        
                    WEB3AccOpenVoucherCreatedStatus l_accOpenVoucherCreatedStatus = 
                        new WEB3AccOpenVoucherCreatedStatus(l_accOpenVoucherStatusParams);  
                          
                    l_lis.add(l_accOpenVoucherCreatedStatus);
                    log.debug("l_lis.add(l_accOpenVoucherCreatedStatus);");
                }
                WEB3AccOpenVoucherCreatedStatus[] l_accOpenVoucherCreatedStatuses = 
                    new WEB3AccOpenVoucherCreatedStatus[l_intSize];
                l_lis.toArray(l_accOpenVoucherCreatedStatuses);    
                
                log.exiting(STR_METHOD_NAME);
                return l_accOpenVoucherCreatedStatuses;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenVoucherCreatedStatus.class.getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenVoucherCreatedStatus.class.getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
    }
}
@
