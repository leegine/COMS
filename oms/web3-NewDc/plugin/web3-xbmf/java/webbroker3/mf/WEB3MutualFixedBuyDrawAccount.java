head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyDrawAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定時定額買付引落口座(WEB3MutualFixedBuyDrawAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 栄イ (中訊) 新規作成 
*/
package webbroker3.mf;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.data.MfFixedBuyingDrawAccountParams;
import webbroker3.mf.data.MfFixedBuyingDrawAccountRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (定時定額買付引落口座)<BR>
 *  定時定額買付引落口座
 * @@author 栄イ(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyDrawAccount implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyDrawAccount.class);
    
    /**
     * (定時定額買付引落口座行)<BR>
     * 定時定額買付引落口座行オブジェクト <BR>
     */
    private MfFixedBuyingDrawAccountParams mfFixedBuyingDrawAccountParams;
    
    /**
     * (get金融機@関区分)<BR>
     * 金融機@関区分を返す。<BR>
     * this.getDataSourceObject().get金融機@関区分()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getFinInstitutionDiv()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getFinInstitutionDiv();
    }
    
    /**
     * (get銀行コード)<BR>
     * 銀行コードを返す。<BR>
     * this.getDataSourceObject().get銀行コード()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getFinInstitutionCode()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getFinInstitutionCode();
    }
    
    /**
     * (get支店コード)<BR>
     * 支店コードを返す。<BR>
     * this.getDataSourceObject().get支店コード()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getFinBranchCode()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getFinBranchCode();
    }
    
    /**
     * (get預金区分 )<BR>
     * 預金区分を返す。<BR>
     * this.getDataSourceObject().get預金区分()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getDepositDiv()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getDepositDiv();
    }
    
    /**
     * (get引落口座番号)<BR>
     * 引落口座番号を返す。<BR>
     * this.getDataSourceObject().get引落口座番号()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getDrawAccountNo()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getDrawAccountNo();
    }
    
    /**
     * (get引落口座名義人（カナ）)<BR>
     * 引落口座名義人（カナ）を返す。<BR>
     * this.getDataSourceObject().get引落口座名義人（カナ）()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getDrawAccountNameKana()
    {
        return ((MfFixedBuyingDrawAccountParams)this.getDataSourceObject()).getDrawAccountNameKana();
    }
    
    /**
     * (定時定額買付引落口座)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）以下の条件で、定時定額買付引落口座テーブルを検索する。  <BR>
     * <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード <BR>
     * 　@　@　@部店コード　@= 引数.部店コード <BR>
     * 　@　@　@口座コード　@= 引数.口座コード <BR>
     * <BR>
     * ２）取得されたレコードをthis.定時定額買付引落口座行にセットする。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyDrawAccount(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3MutualFixedBuyDrawAccount(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）以下の条件で、定時定額買付引落口座テーブルを検索する。
        //［検索条件］ 
        //　@　@証券会社コード = 引数.証券会社コード 
        //　@　@部店コード　@= 引数.部店コード
        //　@　@口座コード　@= 引数.口座コード 
        List l_lisRow = new ArrayList();
        String l_strCondition =  
            " institution_code = ? and branch_code = ? and account_code = ? ";
        Object[] l_objConditionValue = new Object[] {
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode};
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingDrawAccountRow.TYPE, 
                l_strCondition, 
                l_objConditionValue);            
        }
        catch(DataNetworkException l_dnex)
           {
            log.error("DBへのアクセスに失敗しました。", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }        
        catch(DataQueryException l_dqex)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        if(l_lisRow == null || l_lisRow.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません!"); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        
        //２）取得されたレコードをthis.定時定額買付引落口座行にセットする。
        MfFixedBuyingDrawAccountRow l_row = (MfFixedBuyingDrawAccountRow)l_lisRow.get(0);
        this.mfFixedBuyingDrawAccountParams = new MfFixedBuyingDrawAccountParams(l_row);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * BusinessObject.getDataSourceObject()<BR>
     * this.mfFixedBuyingDrawAccountParamsを返す。<BR>
     * @@return Object <BR>
     */
    public Object getDataSourceObject() 
    {
        return this.mfFixedBuyingDrawAccountParams;
    }
}
@
