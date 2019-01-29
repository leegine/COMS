head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定時定額買付条件(WEB3MutualFixedBuyCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 栄イ (中訊) 新規作成 
*/
package webbroker3.mf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.data.MfFixedBuyingCondParams;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (定時定額買付条件)<BR>
 *  定時定額買付条件
 * @@author 栄イ(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyCondition implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCondition.class);
    
    /**
     * (定時定額買付条件行)<BR>
     * 定時定額買付条件行オブジェクト <BR>
     */
    private MfFixedBuyingCondParams mfFixedBuyingCondParams;
    
    /**
     * (定時定額買付条件) <BR> 
     * コンストラクタ<BR>
     * <BR>
     * １）以下の条件で、定時定額買付条件テーブルを検索する。  <BR>
     * <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード <BR>
     * 　@　@　@部店コード　@= 引数.部店コード <BR>
     * 　@　@　@口座コード　@= 引数.口座コード <BR>
     * 　@　@　@銘柄コード　@= 引数.銘柄コード <BR>
     * <BR>
     * ２）取得されたレコードをthis.定時定額買付条件行にセットする。  <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyCondition(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "WEB3MutualFixedBuyCondition(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）以下の条件で、定時定額買付条件テーブルを検索する。
        //［検索条件］ 
        //　@　@証券会社コード = 引数.証券会社コード 
        //　@　@部店コード　@= 引数.部店コード 
        //　@　@口座コード　@= 引数.口座コード 
        //　@　@銘柄コード　@= 引数.銘柄コード 
        List l_list = new ArrayList();
        String l_strCondition =  
            " institution_code = ? and branch_code = ? and account_code = ? and product_code = ? ";
        Object[] l_objConditionValue = new Object[] {
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode, 
            l_strProductCode};
        
        try
        {
            //定時定額買付条件テーブルを検索する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_list = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingCondRow.TYPE, 
                l_strCondition, 
                l_objConditionValue);            
        }
        catch(DataNetworkException l_dnex)
           {
            log.error(STR_METHOD_NAME, l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }
        catch(DataFindException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }
        catch(DataQueryException l_dqex)
        {
            log.error(STR_METHOD_NAME, l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        if(l_list == null || l_list.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません!"); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        
        //２）取得されたレコードをthis.定時定額買付条件行にセットする。
        MfFixedBuyingCondRow l_row = (MfFixedBuyingCondRow)l_list.get(0);
        this.mfFixedBuyingCondParams = new MfFixedBuyingCondParams(l_row);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get買付金額（月々）)<BR>
     * 買付金額（月々）を返す。<BR>
     * this.getDataSourceObject().get買付金額（月々）()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getMonthlyBuyAmount()
    {
        double l_dblMonthlyBuyAmount = 
            ((MfFixedBuyingCondParams)this.getDataSourceObject()).getMonthlyBuyAmount();
        return WEB3StringTypeUtility.formatNumber(l_dblMonthlyBuyAmount);
    }
    
    /**
     * (get買付金額（積み増し）)<BR>
     * 買付金額（積み増し）を返す。 <BR>
     * this.getDataSourceObject().get買付金額（積み増し）()の戻り値を返す。<BR>
     * @@return String<BR>
     */
    public String getIncreaseBuyAmount()
    {
        double l_dblIncreaseBuyAmount = 
            ((MfFixedBuyingCondParams)this.getDataSourceObject()).getIncreaseBuyAmount();
        return WEB3StringTypeUtility.formatNumber(l_dblIncreaseBuyAmount);
    }
    
    /**
     * (get適用開始年月)<BR>
     * get適用開始年月  <BR>
     * this.getDataSourceObject().get適用開始年月()の戻り値を返す。<BR>
     * @@return Date <BR>
     */
    public Date getValidStartDate()
    {
        return ((MfFixedBuyingCondParams)this.getDataSourceObject()).getValidStartDate();
    }
    
    /**
     * (get口座引落年月 )<BR>
     * get口座引落年月  <BR>
     * this.getDataSourceObject().get口座引落年月()の戻り値を返す。<BR>
     * @@return Date <BR>
     */
    public Date getDrawDate()
    {
        return ((MfFixedBuyingCondParams)this.getDataSourceObject()).getDrawDate();
    }

    /**
     * BusinessObject.getDataSourceObject()<BR>
     * this.mfFixedBuyingCondParamsを返す。<BR>
     * @@return Object <BR>
     */
    public Object getDataSourceObject() 
    {
        return this.mfFixedBuyingCondParams;
    }
}
@
