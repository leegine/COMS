head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenBranch.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 部店(WEB3AccOpenBranch.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/15 徐宏偉 (中訊) 新規作成
 */
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (部店)<BR>
 * 部店<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenBranch 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenBranch.class);
    
    /**
     * (部店行)<BR>
     * 部店行<BR>
     * <BR>
     * ※ 部店ParamsクラスはDDLより自動生成する。<BR>
     */
    private BranchParams branchParams;
    
    /**
     * (部店)<BR>
     * 部店オブジェクトを取得する。 <BR> 
     * <BR>
     * 以下の条件で部店テーブルを検索する。<BR> 
     * <BR>
     * 　@[条件] <BR>
     * 　@部店.証券会社コード = 引数.証券会社コード <BR>
     * 　@部店.部店コード = 引数.部店コード <BR>
     * <BR>
     * 検索結果の部店行オブジェクトを、this.部店行にセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenBranch(
        String l_strInstitutionCode, 
        String l_strBranchCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenBranch(String, String)";
        log.entering(STR_METHOD_NAME);    
        
        try
        {
            // 　@部店.証券会社コード = 引数.証券会社コード 
            // 　@部店.部店コード = 引数.部店コード   
            BranchRow l_row = (BranchRow)BranchDao.findRowByInstitutionCodeBranchCode(
                l_strInstitutionCode, 
                l_strBranchCode);
            if (l_row == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            // 検索結果の部店行オブジェクトを、this.部店行にセットする。
            this.branchParams = new BranchParams(l_row);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
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
     * (get顧客ログインタイプID)<BR>
     * 顧客ログインタイプIDを取得する。<BR> 
     * <BR>
     * this.部店行.顧客ログインタイプIDを返却する。<BR>
     * @@return String
     */
    public String getAccountTypeId()
    {
        final String STR_METHOD_NAME = "getAccountTypeId()";
        log.entering(STR_METHOD_NAME);        

        //顧客ログインタイプIDを取得する
        //this.部店行.顧客ログインタイプIDを返却する。
        if (this.branchParams.getAccountTypeIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.formatNumber(
                this.branchParams.getAccountTypeId());
        }    
    }
    
    /**
     * (get顧客ログイングループID)<BR>
     * get顧客ログイングループIDを取得する。<BR> 
     * <BR>
     * this.部店行.get顧客ログイングループIDを返却する。<BR>
     * @@return String
     */
    public String getAccountGroupId()
    {
        final String STR_METHOD_NAME = "getAccountGroupId()";
        log.entering(STR_METHOD_NAME);        

        //get顧客ログイングループIDを取得する。
        //this.部店行.get顧客ログイングループIDを返却する。
        if (this.branchParams.getAccountGroupIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.formatNumber(
                this.branchParams.getAccountGroupId());
        }    
    }

    /**
     * (get部店ID)<BR>
     * 部店ID<BR> 
     * <BR>
     * @@return String
     */
    public String getBranchId()
    {
        final String STR_METHOD_NAME = "getBranchId()";
        log.entering(STR_METHOD_NAME);
        
        //部店IDを取得する。
        String l_strBranchId = WEB3StringTypeUtility.formatNumber(
            this.branchParams.getBranchId());
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchId;
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.branchParams;   
    }
}
@
