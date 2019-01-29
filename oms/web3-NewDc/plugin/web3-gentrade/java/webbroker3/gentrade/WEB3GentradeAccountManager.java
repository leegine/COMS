head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeAccountManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張アカウントマネージャ(WEB3GentradeAccountManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 本郷　@千草(SRA) 新規作成
Revesion History : 2004/02/09 今井　@高史(SRA) 実装
Revesion History : 2004/08/25 鄒政 (中訊) 変更 RuntimeSystemException --> WEB3BaseRuntimeException
Revesion History : 2004/10/09 孟東 (中訊) getMainAccount(String ,String)を修正 
Revesion History : 2004/11/22 鄒政 (中訊) is金融商品販売法@同意()メソッドを削除
Revesion History : 2006/06/14 凌建平 (中訊) 仕様変更・モデルNo.196を対応
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AccountManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.AccountProductOrderStopRow;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3LogUtility;

/**
 * 顧客に対する操作を表現します。<BR>
 * xTradeのAccountManagerを拡張したクラス。<BR>
 *<BR>
 * @@author 　@(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AccountManagerImpl
 */
public class WEB3GentradeAccountManager extends AccountManagerImpl
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMainAccount.class);

    /**
     * get部店<BR>
     *<BR> 
     * 指定した証券会社コード、部店コードに該当する部店をＤＢより検索し、<BR>
     * 部店オブジェクトを返却する。<BR>
     *<BR> 
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_strBranchCode 部店コード
     * @@throws NotFoundException
     * @@return WEB3GentradeBranch
     * @@roseuid 400CEB880171
     */
    public WEB3GentradeBranch getWeb3GenBranch(
        String l_strInstitutionCode,
        String l_strBranchCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getWeb3GenBranch(String, String)";
        log.entering(STR_METHOD_NAME);

        Institution l_institution = super.getInstitution(l_strInstitutionCode);

        log.exiting(STR_METHOD_NAME);
        return (WEB3GentradeBranch) this.getBranch(
            l_institution,
            l_strBranchCode);
    }

    /**
     * get顧客<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * (public MainAccount getMainAccount(long accountId))<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public MainAccount getMainAccount(long l_lngAccountId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMainAccount(long)";
        
        try
        {
            return new WEB3GentradeMainAccount(l_lngAccountId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No MainAccount could be found with id : " + l_lngAccountId);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting MainAccount for Id:" + l_lngAccountId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }

    /**
     * get顧客<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * getMainAccount(long l_lngInstitutionId, long l_lngBranchId, String l_strAccountCode)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     * 
     */
    public MainAccount getMainAccount(
        long l_lngInstitutionId,
        long l_lngBranchId,
        String l_strAccountCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMainAccount(long, long, String)";
        
        try
        {
            return new WEB3GentradeMainAccount(
                l_lngInstitutionId,
                l_lngBranchId,
                l_strAccountCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No MainAccount could be found with instId,branchId,accountCode: "
                    + l_lngInstitutionId
                    + ","
                    + l_lngBranchId
                    + ","
                    + l_strAccountCode);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting MainAccount for inst id:"
                    + l_lngInstitutionId
                    + ", branchId: "
                    + l_lngBranchId
                    + ", accountCode :"
                    + l_strAccountCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get顧客<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * getMainAccount(long l_lngInstitutionId, String l_strBranchCode, String l_strAccountCode)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public MainAccount getMainAccount(
        long l_lngInstitutionId,
        String l_strBranchCode,
        String l_strAccountCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMainAccount(long, String, String)";
        
        try
        {
            return new WEB3GentradeMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No MainAccount could be found with instId,branchCode,accountCode: "
                    + l_lngInstitutionId
                    + ","
                    + l_strBranchCode
                    + ","
                    + l_strAccountCode);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting MainAccount for inst id:"
                    + l_lngInstitutionId
                    + ", branchCode: "
                    + l_strBranchCode
                    + ", accountCode :"
                    + l_strAccountCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }
    
    /**
     * get顧客<BR>
     * （getMainAccountのオーバーロード）<BR>
     *  <BR>
     * 顧客マスタテーブルより、引数の証券会社コード，部店コード，<BR>
     * 口座コード※に該当する顧客オブジェクトを取得し返却する。<BR>
     * 複数件取得できた場合は、データ不整合の例外をスローする。<BR>
     *  <BR>
     * ※　@口座コード <BR> 
     * 　@（口座コード.length() == 6）の場合は、最初の6byteのみ比較する。 <BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 口座コード
     * @@throws WEB3BaseException
     */
    public WEB3GentradeMainAccount getMainAccount(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords;
        try
        {
            
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            if(l_strAccountCode == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "口座コード = null");
            }            

            if(l_strAccountCode.length() == 6)
            {
                //（口座コード.length() == 6）の場合は、最初の6byteのみ比較する。                
                l_lisRecords =
                    l_QueryProcessor.doFindAllQuery(
                        MainAccountRow.TYPE,
                        "institution_code = ? and branch_code = ? and substr(account_code ,0 ,6) = ?",
                        new Object[] { l_strInstitutionCode, l_strBranchCode, l_strAccountCode });
            }
            else
            {
                l_lisRecords =
                    l_QueryProcessor.doFindAllQuery(
                        MainAccountRow.TYPE,
                        "institution_code = ? and branch_code = ? and account_code = ?",
                        new Object[] { l_strInstitutionCode, l_strBranchCode, l_strAccountCode });
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_lisRecords.size() == 0)
        {
            // （口座コード.length() == 6）の場合は、WEB3BusinessLayerExceptionをスローする。                                  
            if(l_strAccountCode.length() == 6)                          
            {                           
                throw new WEB3BusinessLayerException(                       
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,                  
                    this.getClass().getName() + "." + STR_METHOD_NAME,                  
                    "顧客マスタテーブルで顧客オブジェクトを取得できない場合");                   
            }
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客マスタテーブルで顧客オブジェクトを取得できない場合");
            }
        }
        else if (l_lisRecords.size() != 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客マスタテーブルに複数件取得できた場合");
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return new WEB3GentradeMainAccount(l_mainAccountRow);
    }
    

    /**
     * get補助口座<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * getSubAccount(long l_lngAccountId, long l_lngSubAccountId)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public SubAccount getSubAccount(
        long l_lngAccountId,
        long l_lngSubAccountId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getSubAccount(long, long)";
        
        try
        {
            return new WEB3GentradeSubAccount(
                l_lngAccountId,
                l_lngSubAccountId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "SubAccount not found for accountId= "
                    + l_lngAccountId
                    + ", SubAccount Id : "
                    + l_lngSubAccountId);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting SubAccount for accountId:"
                    + l_lngAccountId
                    + ", subAccountId:"
                    + l_lngSubAccountId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get補助口座<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * getSubAccount(long l_lngAccountId, SubAccountTypeEnum l_subAccountType)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public SubAccount getSubAccount(
        long l_lngAccountId,
        SubAccountTypeEnum l_subAccountType)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getSubAccount(long, SubAccountTypeEnum)";
        
        try
        {
            SubAccountRow row =
                SubAccountDao.findRowByAccountIdSubAccountType(
                    l_lngAccountId,
                    l_subAccountType);
            if (row != null)
            {
                return new WEB3GentradeSubAccount(row);
            }
            else
            {
                throw new NotFoundException(
                    "SubAccount not found for accountId= "
                        + l_lngAccountId
                        + ", SubAccountType : "
                        + l_subAccountType);
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting SubAccount for accountId:"
                    + l_lngAccountId
                    + ", SubAccountType :"
                    + l_subAccountType;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get証券会社<BR>
     *<BR>
     * super Class[AccountManagerImpl]の<BR>
     * getInstitution(long institutionId)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public Institution getInstitution(long l_lngInstitutionId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getInstitution(long)";
        
        try
        {
            return new WEB3GentradeInstitution(l_lngInstitutionId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No institution   object found with the  id : "
                    + l_lngInstitutionId);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting institution  Object for  id : "
                    + l_lngInstitutionId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get証券会社<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * getMainAccount(String l_strInstitutionCode)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public Institution getInstitution(String l_strInstitutionCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getInstitution(String)";
        
        try
        {
            return new WEB3GentradeInstitution(l_strInstitutionCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Institution   object found with the  code : "
                    + l_strInstitutionCode);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting Institution  Object for  code : "
                    + l_strInstitutionCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get部店<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * getBranch(long branchId)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public Branch getBranch(long l_lngBranchId) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getBranch(long)";
        
        try
        {
            return new WEB3GentradeBranch(l_lngBranchId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No branch   object found with the  id : " + l_lngBranchId);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting Branch  Object for  id : " + l_lngBranchId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get部店<BR>
     *<BR> 
     * super Class[AccountManagerImpl]の<BR>
     * getBranch(Institution l_institution, String l_strBranchCode)<BR>
     * メソッドのオーバーライド<BR>
     * @@author 今井　@高史(SRA)
     * @@throws NotFoundException
     *
     */
    public Branch getBranch(Institution l_institution, String l_strBranchCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getBranch(Institution, String)";
        
        try
        {
            return new WEB3GentradeBranch(l_institution, l_strBranchCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No branch   object found with the  code : " + l_strBranchCode);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting Branch  Object for  code : "
                    + l_strBranchCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (lock口座) <BR>
     *<BR> 
     * 下り処理の処理中に、 <BR>
     * 外部プロセスからのデータアクセスを防止するために <BR>
     * 口座をロックする。  <BR>
     * 引数の情報より口座ＩＤを取得し、取得した口座をロックする。 <BR> 
     * シーケンス図 「（口座）lock顧客」参照。 <BR> 
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (口座コード)
     * @@return void
     * @@throws WEB3BaseException
     */
    public void lockAccount(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "lockAccount(String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        Institution l_institution = null;
        long l_lngInstitutionId = 0;
        MainAccount l_mainAccount = null;
        
        try
        {
            //NotFoundExceptionをスロー
            l_institution = this.getInstitution(l_strInstitutionCode);
            
            l_lngInstitutionId = l_institution.getInstitutionId();
            l_mainAccount = getMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode
                );
            
            //ResourceBusyExceptionをスロー
            l_mainAccount.serializeOperationsWithWait();
        }
        catch(NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get顧客銘柄別取引停止) <BR>
     * <BR>
     *引数に該当する顧客銘柄別取引停止Paramsを返却する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 以下の条件に該当するデータを取得する。<BR>
     *  <BR>
     * [条件] <BR>
     *   証券会社コード = パラメータ.証券会社コード　@かつ<BR>
     *   部店ID = パラメータ.部店ID　@かつ<BR>
     *   口座ID = パラメータ.口座ID　@かつ<BR>
     *   銘柄ID = パラメータ.銘柄ID　@かつ<BR>
     *   適用開始年月日 = パラメータ.有効期限From <BR>
     *  <BR>
     * 取得できなかった場合は、nullを返却する。<BR>
     *  <BR>
     * ２）取得した検索結果を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_lngBranchId - (部店ID)
     * @@param l_lngAccountId - (口座ID)
     * @@param l_lngProductId - (銘柄ID)
     * @@param l_dateFrom - (有効期限From)
     * @@return 顧客銘柄別取引停止Params
     * @@throws WEB3SystemLayerException
     */
    public AccountProductOrderStopParams getAccountProductOrderStop(
        String l_strInstitutionCode,
        long l_lngBranchId,
        long l_lngAccountId,
        long l_lngProductId,
        Date l_dateFrom)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getAccountProductOrderStop(String, long, long, long, Date)";
            
        //１）DB検索
        //  証券会社コード = パラメータ.証券会社コード　@かつ
        //  部店ID = パラメータ.部店ID　@かつ
        //  口座ID = パラメータ.口座ID　@かつ
        //  銘柄ID = パラメータ.銘柄ID　@かつ
        //  適用開始年月日 = パラメータ.有効期限From
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_id = ? ");
        l_sbWhere.append(" and account_id = ? ");
        l_sbWhere.append(" and product_id = ? ");
        l_sbWhere.append(" and apply_start_date = ? ");
        Object[] l_obWhere = new Object[]
        {
            l_strInstitutionCode,
            new Long(l_lngBranchId),
            new Long(l_lngAccountId),
            new Long(l_lngProductId),
            l_dateFrom
        };
        List l_lstRecords;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                AccountProductOrderStopRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        int l_intSize = l_lstRecords.size();
        if(l_intSize  == 0)
        {
            return null;
        }
        else if(l_intSize == 1)
        {
            AccountProductOrderStopRow l_accountProductOrderStopRow = 
                (AccountProductOrderStopRow)l_lstRecords.get(0);
            return new AccountProductOrderStopParams(l_accountProductOrderStopRow);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "検索結果 = " + l_intSize);
        }
        
    }
    
    /**
     * (insert顧客銘柄別取引停止) <BR>
     * <BR>
     * 顧客銘柄別取引停止テーブルに一件データを登録する。<BR>
     *  <BR>
     * １）QueryProcessor.doInsertQuery()をコールする。<BR>
     * [doInsertQuery()にセットするパラメータ] <BR>
     *  arg0：　@パラメータ.顧客銘柄別取引停止Params<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - (顧客銘柄別取引停止Params)
     * @@return void
     * @@throws WEB3SystemLayerException
     */
    public void insertAccountProductOrderStop(AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "insertAccountProductOrderStop(AccountProductOrderStopParams)";
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_accountProductOrderStopParams);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }
    
    /**
     * (get顧客銘柄別取引停止一覧) <BR>
     * <BR>
     * 引数の条件に該当する顧客銘柄別取引停止Paramsの<BR>
     * 一覧を返却する。<BR>
     *  <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     *  <BR>
     * [doFindAllQuery()にセットするパラメータ]<BR>
     *  arg0：　@"顧客銘柄別取引停止テーブル"(account_product_order_stop)<BR>
     *  arg1：　@パラメータ.検索条件文字列 <BR>
     *  arg2：　@パラメータ.ソート条件<BR>
     *  arg3：　@null<BR>
     *  arg4：　@パラメータ.検索条件データコンテナ<BR>
     *  <BR>
     * 検索結果が取得できなかった場合、nullを返却する。<BR>
     *  <BR>
     * ３）２）の検索結果を返却する。<BR>
     *  <BR>
     * @@param l_strWhere - (検索条件文字列) <BR>
     * @@param l_bindVars - (検索条件データコンテナ) <BR>
     * @@param l_strOrderBy - (ソート条件) <BR>
     *    ※ 指定しない場合null <BR>
     * @@return List
     * @@throws WEB3SystemLayerException
     */
    public List getAccountProductOrderStopList(
        String l_strWhere,
        Object[] l_bindVars,
        String l_strOrderBy)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getAccountProductOrderStopList(String, Object[], String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRows;    
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRows =
                l_queryProcessor.doFindAllQuery(
                    AccountProductOrderStopRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        int l_intSize = l_lstRows.size();
        if(l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        List l_lstParams = new ArrayList(); 
        for(int i = 0; i < l_intSize; i++)
        {
            AccountProductOrderStopRow l_accountProductOrderStopRow =
                (AccountProductOrderStopRow)l_lstRows.get(i);
            l_lstParams.add(new AccountProductOrderStopParams(l_accountProductOrderStopRow));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lstParams;
    }
    
    /**
     * (update顧客銘柄別取引停止) <BR>
     * <BR>
     * 顧客銘柄別取引停止テーブルを更新する。<BR>
     *  <BR>
     * １）QueryProcessor.doUpdateQuery()をコールする。<BR>
     *  <BR>
     * [doUpdateQuery()にセットするパラメータ]<BR>
     *  arg0：　@パラメータ.顧客銘柄別取引停止Params<BR>
     *  <BR>
     * @@param l_accountProductOrderStopParams - (顧客銘柄別取引停止Params)
     * @@return void
     * @@throws WEB3SystemLayerException
     */
    public void updateAccountProductOrderStop(AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "updateAccountProductOrderStop(AccountProductOrderStopParams)";
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_accountProductOrderStopParams);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }
    
    /**
     * (delete顧客銘柄別取引停止) <BR>
     * <BR>
     * 顧客銘柄別取引停止テーブルのデータを削除する。<BR>
     *  <BR>
     * １）QueryProcessor.doDeleteAllQuery()をコールする。<BR>
     *  <BR>
     * [doDeleteAllQuery()にセットするパラメータ]<BR>
     *  arg0：　@パラメータ.顧客銘柄別取引停止Params<BR>
     *  <BR>
     * @@param l_accountProductOrderStopParams - (顧客銘柄別取引停止Params)
     * @@return void
     * @@throws WEB3SystemLayerException
     */
    public void deleteAccountProductOrderStop(AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "deleteAccountProductOrderStop(AccountProductOrderStopParams)";
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_accountProductOrderStopParams.getPrimaryKey());
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get顧客（全部店分）) <BR>
     * <BR>
     * 顧客マスタ（全部店分）テーブルより、<BR>
     * 引数の証券会社コード，部店コード，顧客コード<BR>
     * ※に該当する顧客（全部店分）オブジェクトを取得し返却する。<BR> 
     * <BR>
     * ※　@顧客コード <BR>
     * 　@（顧客コード.length() > 6）の場合は、最初の6byteのみ比較する。<BR>
     *  <BR>
     * @@param l_strCompCode - (証券会社コード)
     * @@param l_strBrCode - (部店コード)
     * @@param l_strCustCode - (顧客コード)
     * @@return MainAccountAllRow
     * @@throws WEB3BaseException
     */
    public MainAccountAllRow getMainAccountAll(
        String l_strCompCode,
        String l_strBrCode,
        String l_strCustCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccountAllRow(String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        //顧客マスタ（全部店分）テーブルより、 
        //引数の証券会社コード，部店コード，顧客コード※に該当する顧客（全部店分）オブジェクトを取得し返却する。 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" comp_code = ? ");
        l_sbWhere.append(" and br_code = ? ");
        l_sbWhere.append(" and cust_code = ? ");

        //※　@顧客コード（顧客コード.length() > 6）の場合は、最初の6byteのみ比較する。 
        if (l_strCustCode != null && l_strCustCode.length() > 6)
        {
            l_strCustCode = l_strCustCode.substring(0, 6);
        }

        Object[] l_obWhere = new Object[]
        {
            l_strCompCode,
            l_strBrCode,
            l_strCustCode
        };

        List l_lstRecords = null;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException l_dexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dexp.getMessage(),
                l_dexp);
        }

        if (l_lstRecords != null && l_lstRecords.size() != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return (MainAccountAllRow) l_lstRecords.get(0);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
