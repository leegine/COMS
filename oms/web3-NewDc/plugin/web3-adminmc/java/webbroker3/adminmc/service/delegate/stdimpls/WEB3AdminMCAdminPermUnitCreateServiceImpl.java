head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermUnitCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者権限情報作成サービスImpl(WEB3AdminMCAdminPermUnitCreateServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 屈陽 (中訊) 新規作成 
                 : 2006/08/28 肖志偉 (中訊) 仕様変更 モデル022
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AdminPermissionPK;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者権限情報作成サービスImpl)<BR>
 * 管理者権限情報作成サービス実装クラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermUnitCreateServiceImpl implements WEB3AdminMCAdminPermUnitCreateService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMCAdminPermUnitCreateServiceImpl.class);
    
    /**
     * (update処理可能機@能カテゴリ)<BR>
     * 引数の内容で、管理者権限テーブルを更新する。<BR>
     * <BR>
     * １）　@管理者権限テーブル検索 <BR>
     * 　@以下の条件で、管理者権限テーブルを検索する。 <BR>
     * 　@取得した行オブジェクトを指定し、管理者権限List（：ArrayList）を生成する。<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 証券会社コード And<BR>
     * 　@権限レベル = 権限レベル<BR>
     * <BR>
     * ２）　@権限レベル更新（Insert/Update）<BR>
     * 　@処理可能機@能カテゴリ[]の各要素毎に２－１）～２－２）を実施する。<BR>
     * 　@（処理可能機@能カテゴリ == null，または、要素数 == 0の場合は、処理を実施しない）<BR>
     * <BR>
     * 　@２－１）　@既存データ更新（DB Update）<BR>
     * 　@　@処理対象要素.機@能カテゴリコードに該当する行が管理者権限List（：ArrayList）内に存在する場合、<BR>
     * 　@　@以下の処理を実施する。<BR>
     * <BR>
     * 　@　@－管理者権限List（：ArrayList）より、indexOf()， remove()メソッドを使用し、該当要素を削除する。 <BR>
     * 　@　@－既存行を下記の通り更新（DB Update）する。<BR>
     * <BR>
     * 　@　@[管理者権限テーブル更新内容（Update）]<BR>
     * 　@　@更新許可フラグ：　@<BR>
     * 　@　@　@－（処理対象要素.更新許可フラグ == true）の場合、BooleanEnum.TRUE<BR>
     * 　@　@　@－（処理対象要素.更新許可フラグ == false）の場合、BooleanEnum.FALSE<BR>
     * 　@　@更新者コード：　@管理者コード<BR>
     * 　@　@更新日時：　@処理日時（※ TradingSystem.getSystemTimestamp()）<BR>
     * <BR>
     * 　@２－２）　@新規データ挿入（DB Insert）<BR>
     * 　@　@処理対象要素.機@能カテゴリコードに該当する行が管理者権限List（：ArrayList）内に存在しない場合、<BR>
     * 　@　@以下の処理を実施する。<BR>
     * <BR>
     * 　@　@－下記の通り、新規行を挿入（DB Insert）する。<BR>
     * <BR>
     * 　@　@[管理者権限テーブル更新内容（Insert）]<BR>
     * 　@　@証券会社コード：　@証券会社コード<BR>
     * 　@　@権限レベル：　@権限レベル<BR>
     * 　@　@機@能カテゴリコード：　@処理対象要素.機@能カテゴリコード<BR>
     * 　@　@更新許可フラグ：　@<BR>
     * 　@　@　@－（処理対象要素.更新許可フラグ == true）の場合、BooleanEnum.TRUE<BR>
     * 　@　@　@－（処理対象要素.更新許可フラグ == false）の場合、BooleanEnum.FALSE<BR>
     * 　@　@更新者コード：　@管理者コード<BR>
     * 　@　@作成日時：　@処理日時（※ TradingSystem.getSystemTimestamp()）<BR>
     * 　@　@更新日時：　@処理日時（※ TradingSystem.getSystemTimestamp()）<BR>
     * <BR>
     * ３）　@権限なしに変更された権限レベル削除(Delete)<BR>
     * 　@　@管理者権限List（：ArrayList）内に未処理の行が残っている場合（管理者権限List（：ArrayList）.size() != 0）、<BR>
     * 　@　@残りの行は権限なしに変更されたと判断し、DBより削除（DB Delete）する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@param l_operatePossibleTransactionCategory - (処理可能機@能カテゴリ)<BR>
     * 機@能カテゴリコード一覧<BR>
     * <BR>
     * @@param l_strAdministratorCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@roseuid 4175FA16006F
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory, 
        String l_strAdministratorCode)
            throws WEB3BaseException 
    {
        String l_strMethodName = 
            "updateOperatePossibleTransactionCategory(" +
            "String l_strInstitutionCode, " +
            "String l_strPermissionLevel, " +
            "WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory, " +
            "String l_strAdministratorCode)";
        log.entering(l_strMethodName);
        
        //１）　@管理者権限テーブル検索 
        //以下の条件で、管理者権限テーブルを検索する。                 
        //[条件] 
        //証券会社コード = 証券会社コード And
        //権限レベル = 権限レベル
        String l_strWhereSelect = " institution_code = ? and permission_level = ? ";
        Object[] l_bindVarsSelect = {l_strInstitutionCode, l_strPermissionLevel};
        
        //ArrayList
        List l_lisAdminPermission = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //取得した行オブジェクトを指定し、管理者権限List（：ArrayList）を生成する。
            l_lisAdminPermission = 
                l_queryProcessor.doFindAllQuery(
                    AdminPermissionRow.TYPE,
                    l_strWhereSelect,
                    l_bindVarsSelect);
        }
        catch (DataFindException l_ex)
        {
            log.error("__DataFindException in select__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException in select__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException in select__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        
        if (l_lisAdminPermission == null)
        {
            log.error("Fail when 管理者権限テーブル検索");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //２）　@権限レベル更新（Insert/Update）
        //処理可能機@能カテゴリ[]の各要素毎に２－１）～２－２）を実施する。
        //（処理可能機@能カテゴリ == null，または、要素数 == 0の場合は、処理を実施しない）
                
        if (!(l_operatePossibleTransactionCategory == null || l_operatePossibleTransactionCategory.length == 0))
        {
            int l_rcdno = l_operatePossibleTransactionCategory.length;
            for (int i = 0; i < l_rcdno; i++)
            {               
                //get the iterator of the list
                Iterator l_iteratorAdmin = l_lisAdminPermission.iterator();    
                int  l_adminpermissionsize = l_lisAdminPermission.size();
                int  l_flg = 0;
                while (l_iteratorAdmin.hasNext())
                {
                    //２－１）既存データ更新（DB Update）                        
                    //処理対象要素.機@能カテゴリコードに該当する行が管理者権限List（：ArrayList）内に存在する場合、
                    //以下の処理を実施する。                        
                    if (l_operatePossibleTransactionCategory[i].transactionCategory.equals(
                            ((AdminPermissionRow)l_iteratorAdmin.next()).getTransactionCategory()))
                    {                      
                        //[管理者権限テーブル更新内容（Update）] 
                        AdminPermissionPK l_adminPermissionPK = 
                            new AdminPermissionPK(
                                l_strInstitutionCode,
                                l_strPermissionLevel,
                                l_operatePossibleTransactionCategory[i].transactionCategory);
                                
                        //a> 更新許可フラグ：　@
                        //（処理対象要素.更新許可フラグ == true）の場合、BooleanEnum.TRUE
                        //（処理対象要素.更新許可フラグ == false）の場合、BooleanEnum.FALSE
                        HashMap l_hashMap = new HashMap();
                        
                        if (l_operatePossibleTransactionCategory[i].updatePermissionFlag == true)
                        {
                            l_hashMap.put("update_permission_flag", BooleanEnum.TRUE);        
                        }
                        else
                        {
                            l_hashMap.put("update_permission_flag", BooleanEnum.FALSE);        
                        }
                        
                        //b> 更新者コード：管理者コード
                        l_hashMap.put("last_updater", l_strAdministratorCode);       
                        
                        //c> 更新日時：処理日時（※ TradingSystem.getSystemTimestamp()）                        
                        //FinApp
                        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);                       
                        //Trading System 
                        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                                               
                        l_hashMap.put("update_timestamp", l_tradingSystem.getSystemTimestamp());       
                        
                        //update database
                        try
                        {
                            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                            
                            l_queryProcessor.doUpdateQuery(l_adminPermissionPK, l_hashMap);
                        }
                        catch (DataFindException l_ex)
                        {
                            log.error("__DataFindException in update__");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                        }
                        catch (DataQueryException l_ex)
                        {
                            log.error("__DataQueryException in update__");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
                        }
                        catch (DataNetworkException l_ex)
                        {
                            log.error("DataNetworkException in update");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                        }  
                        //管理者権限List（：ArrayList）より、indexOf()， remove()メソッドを使用し、該当要素を削除する。 
                        //既存行を下記の通り更新（DB Update）する。
                        l_iteratorAdmin.remove();      
                        break;                                                                           
                    }
                    else
                    {
                        l_flg++;
                    }
                       
                }  
                if (l_flg == l_adminpermissionsize)
                {
                    //２－２）新規データ挿入（DB Insert）                        
                    //処理対象要素.機@能カテゴリコードに該当する行が管理者権限List（：ArrayList）内に存在しない場合、
                    //以下の処理を実施する。
                    //下記の通り、新規行を挿入（DB Insert）する。
                    AdminPermissionParams l_adminPermissionParams =
                        new AdminPermissionParams();
    
                    //[管理者権限テーブル更新内容（Insert）]
                    //a> 証券会社コード：証券会社コード
                    l_adminPermissionParams.setInstitutionCode(l_strInstitutionCode);
                    
                    //b> 権限レベル：権限レベル
                    l_adminPermissionParams.setPermissionLevel(l_strPermissionLevel);
                    
                    //c> 機@能カテゴリコード：処理対象要素.機@能カテゴリコード
                    l_adminPermissionParams.setTransactionCategory(
                        l_operatePossibleTransactionCategory[i].transactionCategory);
                    
                    //d> 更新許可フラグ：　@
                    //（処理対象要素.更新許可フラグ == true）の場合、BooleanEnum.TRUE
                    //（処理対象要素.更新許可フラグ == false）の場合、BooleanEnum.FALSE
                    if (l_operatePossibleTransactionCategory[i].updatePermissionFlag == true)
                    {
                        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);        
                    }
                    else
                    {
                        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);        
                    }
                    
                    //e> 更新者コード：管理者コード
                    l_adminPermissionParams.setLastUpdater(l_strAdministratorCode);
                    
                    //f> 作成日時：処理日時（※ TradingSystem.getSystemTimestamp()）
                    //FinApp
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);                       
                    //Trading System 
                    TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                    
                    l_adminPermissionParams.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
                    
                    //g> 更新日時：処理日時（※ TradingSystem.getSystemTimestamp()） 
                    l_adminPermissionParams.setUpdateTimestamp(l_tradingSystem.getSystemTimestamp());    
                    
                    //insert database 
                    try
                    {
                        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                        
                        l_queryProcessor.doInsertQuery(l_adminPermissionParams);
                    }
                    catch (DataFindException l_ex)
                    {
                        log.error("__DataFindException in insert__");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error("__DataQueryException in insert__");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
                    }
                    catch (DataNetworkException l_ex)
                    {
                        log.error("DataNetworkException in insert__");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                    }                                   
                }      
            }  
    
            
        }      
 
        //３）　@権限なしに変更された権限レベル削除(Delete)
        //管理者権限List（：ArrayList）内に未処理の行が残っている場合（管理者権限List（：ArrayList）.size() != 0）、
        //残りの行は権限なしに変更されたと判断し、DBより削除（DB Delete）する。
        int l_intDeleteSize = 0;
        if (l_lisAdminPermission != null && l_lisAdminPermission.size() != 0)
        {
            l_intDeleteSize = l_lisAdminPermission.size();         
        }
         
        if (l_intDeleteSize != 0)
        {
            try
            {
                //new QueryProcessor 
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        
                //where
                StringBuffer l_sbWhereDelete = new StringBuffer();
                l_sbWhereDelete.append(" institution_code = ? ");
                l_sbWhereDelete.append(" and permission_level = ? ");
                l_sbWhereDelete.append(" and transaction_category = ? ");
        
                //value array
                Object[] l_bindVarsDelete = new Object[3];
                l_bindVarsDelete[0] = l_strInstitutionCode;
                l_bindVarsDelete[1] = l_strPermissionLevel;               

                for (int k = 0; k < l_intDeleteSize; k++)
                {
                    //get the row
                    AdminPermissionRow l_adminPermissionRow = (AdminPermissionRow)l_lisAdminPermission.get(k);
            
                    //value
                    l_bindVarsDelete[2] = l_adminPermissionRow.getTransactionCategory();
            
                    l_queryProcessor.doDeleteAllQuery(
                        AdminPermissionRow.TYPE,
                        l_sbWhereDelete.toString(),
                        l_bindVarsDelete);            
                }            
            }
            catch (DataFindException l_ex)
            {
                log.error("__DataFindException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DataNetworkException");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
            }                  
        }                                
        
        log.exiting(l_strMethodName);    
    }
    
    /**
     * (create処理可能機@能カテゴリ一覧)<BR>
     * 証券会社，権限レベルに該当する機@能カテゴリ情報を配列を作成する。<BR>
     * <BR>
     * １）　@管理者権限テーブル検索 <BR>
     * 　@以下の条件で、管理者権限テーブルを検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 証券会社コード And<BR>
     * 　@権限レベル = 権限レベル And<BR>
     * 　@機@能カテゴリコード in （機@能カテゴリコード[0]，機@能カテゴリコード[1]，，）<BR>
     * 　@※ 引数の機@能カテゴリコード[]の要素を列挙。但し、（機@能カテゴリコード[] == null）の場合は当該条件を指定しない。<BR>
     * <BR>
     * 　@[取得順]<BR>
     * 　@機@能カテゴリコード，昇順（asc）<BR>
     * <BR>
     * ２）　@機@能カテゴリ情報List（：ArrayList）生成<BR>
     * 　@ArrayListを生成する。 <BR>
     * <BR>
     * ３）　@機@能カテゴリ情報生成<BR>
     * 　@１）で取得した各行オブジェクト（：管理者権限Params）毎に、３－１）～３－２）の処理を行う。 <BR>
     * <BR>
     * 　@３－１）　@機@能カテゴリ情報を生成し、以下の通りプロパティセットを行う。<BR>
     * 　@　@機@能カテゴリコード = 管理者権限行.機@能カテゴリコード<BR>
     * 　@　@更新許可フラグ = (*1)<BR>
     * <BR>
     * 　@　@(*1) 更新許可フラグ<BR>
     * 　@　@（管理者権限行.更新許可フラグ == BooleanEnum.TRUE）の場合、true<BR>
     * 　@　@（管理者権限行.更新許可フラグ == BooleanEnum.FALSE）の場合、false<BR>
     * <BR>
     * 　@３－２）　@機@能カテゴリ情報List（：ArrayList）にオブジェクトを追加する。<BR>
     * 　@　@３－１）で生成したオブジェクトを機@能カテゴリ情報List（：ArrayList）に追加（add）する。<BR>
     * <BR>
     * ４）　@配列返却<BR>
     * 　@機@能カテゴリ情報List（：ArrayList）を配列に変換（toArray()）し、返却する。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@param l_strTransactionCategory - (機@能カテゴリコード)<BR>
     * 機@能カテゴリコードの配列<BR>
     * <BR>
     * ※ 指定しない場合はnull<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit[]
     * @@roseuid 4175F793011B
     */
    public WEB3AdminMCTransactionCategoryUnit[] createOperatePossibleTransactionCategoryUnit(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String[] l_strTransactionCategory) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "createOperatePossibleTransactionCategoryUnit(" +
            "String l_strInstitutionCode," +
            "String l_strPermissionLevel," +
            "String[] l_strTransactionCategory)";
        log.entering(l_strMethodName);
        
        //１）管理者権限テーブル検索 
        //以下の条件で、管理者権限テーブルを検索する。
        
        //管理者権限List生成
        List l_lisAdminPermission = new ArrayList();       
        try
        {
            //QueryProcessor
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //[条件] 
            //証券会社コード = 証券会社コード And
            //権限レベル = 権限レベル And
            //機@能カテゴリコード in （機@能カテゴリコード[0]，機@能カテゴリコード[1]，，）
            //※ 引数の機@能カテゴリコード[]の要素を列挙。但し、（機@能カテゴリコード[] == null）の場合は当該条件を指定しない。                         
            StringBuffer l_strWhereSelect = new StringBuffer();
        
            if (l_strTransactionCategory == null || l_strTransactionCategory.length == 0)
            {               
                //Where
                l_strWhereSelect.append(" institution_code = ? ");
                l_strWhereSelect.append(" and permission_level = ? "); 
            
                //Value
                Object[] l_bindVarsSelect = {l_strInstitutionCode, l_strPermissionLevel};
                
                //[取得順]
                //機@能カテゴリコード，昇順（asc）
                String l_strOrderBySelect = " transaction_category asc ";
            
                //Select
                l_lisAdminPermission = 
                    l_queryProcessor.doFindAllQuery(
                        AdminPermissionRow.TYPE,
                        l_strWhereSelect.toString(),
                        l_strOrderBySelect,
                        null,
                        l_bindVarsSelect);
            }
            else 
            {      
                //
                StringBuffer l_strWhere = new StringBuffer();
                        
                //Where
                l_strWhereSelect.append(" institution_code = ? ");
                l_strWhereSelect.append(" and permission_level = ? ");
                
                //value Array length
                int l_intLength = l_strTransactionCategory.length + 2;
                
                //Value
                Object[] l_bindVars = new Object[l_intLength];
                l_bindVars[0] = l_strInstitutionCode;
                l_bindVars[1] = l_strPermissionLevel;
                
                //[取得順]
                //機@能カテゴリコード，昇順（asc）
                String l_strOrderBy = " transaction_category asc ";
       
                for (int i = 0; i < l_strTransactionCategory.length; i++)
                {
                    //the first one
                    if (l_strWhere.length() == 0)
                    {
                        l_strWhere.append(" and transaction_category in ( ? ");
                    }
                    else 
                    {
                        l_strWhere.append(", ?");
                    }
                    
                    l_bindVars[i + 2] = l_strTransactionCategory[i];                                                              
                }  
                l_strWhere.append(" ) ");  
                
                l_strWhereSelect.append(l_strWhere);               
                
                //Select 
                l_lisAdminPermission = 
                    l_queryProcessor.doFindAllQuery(
                        AdminPermissionRow.TYPE,
                        l_strWhereSelect.toString(),
                        l_strOrderBy,
                        null,
                        l_bindVars);                                                    
            }                       
        }
        catch (DataFindException l_ex)
        {
            log.error("__DataFindException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }                            
        
        //２）機@能カテゴリ情報List（：ArrayList）生成
        //ArrayListを生成する。
        List l_lisCategoryUnit = new ArrayList(); 
        
        //３）機@能カテゴリ情報生成
        //１）で取得した各行オブジェクト（：管理者権限Params）毎に、３－１）～３－２）の処理を行う。 
        int l_intSize = 0;
        if (l_lisAdminPermission != null && l_lisAdminPermission.size() != 0)
        {
            l_intSize = l_lisAdminPermission.size();    
        }
        
        //機@能カテゴリ情報を生成
       // WEB3AdminMCTransactionCategoryUnit l_adminMCTransactionCategoryUnit =
       //     new WEB3AdminMCTransactionCategoryUnit();
            
        for (int j = 0; j < l_intSize; j++)
        {
            //３－１）機@能カテゴリ情報を生成し、以下の通りプロパティセットを行う。
            WEB3AdminMCTransactionCategoryUnit l_adminMCTransactionCategoryUnit =
                        new WEB3AdminMCTransactionCategoryUnit();
            //get the row
            AdminPermissionRow l_adminPermissionRow = 
                (AdminPermissionRow)l_lisAdminPermission.get(j);
            
            //機@能カテゴリコード = 管理者権限行.機@能カテゴリコード
            l_adminMCTransactionCategoryUnit.transactionCategory = 
                l_adminPermissionRow.getTransactionCategory();
                
            //更新許可フラグ = (*1)
            //(*1) 更新許可フラグ
            //（管理者権限行.更新許可フラグ == BooleanEnum.TRUE）の場合、true
            //（管理者権限行.更新許可フラグ == BooleanEnum.FALSE）の場合、false
            if (BooleanEnum.TRUE.equals(l_adminPermissionRow.getUpdatePermissionFlag()))
            {
                l_adminMCTransactionCategoryUnit.updatePermissionFlag = true;        
            }
            else 
            {
                l_adminMCTransactionCategoryUnit.updatePermissionFlag = false;        
            }
        
            //３－２）機@能カテゴリ情報List（：ArrayList）にオブジェクトを追加する。
            //３－１）で生成したオブジェクトを機@能カテゴリ情報List（：ArrayList）に追加（add）する。
            l_lisCategoryUnit.add(l_adminMCTransactionCategoryUnit);                                    
        }
        
        //４）配列返却<BR>
        //機@能カテゴリ情報List（：ArrayList）を配列に変換（toArray()）し、返却する。 
        WEB3AdminMCTransactionCategoryUnit[] l_web3AdminMCTransactionCategoryUnit =
            new WEB3AdminMCTransactionCategoryUnit[l_lisCategoryUnit.size()];
        l_lisCategoryUnit.toArray(l_web3AdminMCTransactionCategoryUnit);
                  
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCTransactionCategoryUnit;
    }
    
    /**
     * (create管理者タイプ情報)<BR>
     * 管理者タイプオブジェクトより、管理者タイプ情報メッセージデータオブジェクトを作成する。<BR>
     * <BR>
     * 管理者タイプ情報を生成，以下の通りプロパティセットを行い返却する。<BR>
     * <BR>
     * 　@権限レベルコード = 管理者タイプ.get権限レベル()<BR>
     * 　@権限レベル名称 = 管理者タイプ.get権限レベル名称()<BR>
     * 　@DIR管理者フラグ = 管理者タイプ.getDIR管理者フラグ() <BR>
     * 　@全部店許可フラグ = 管理者タイプ.is全部店許可()<BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (管理者タイプ)<BR>
     * 管理者タイプ<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit
     * @@roseuid 417722A3030D
     */
    public WEB3AdminMCAdminTypeUnit createAdminTypeUnit(WEB3AdminMCAdminType l_adminType) 
    {
        String l_strMethodName = "createAdminTypeUnit(WEB3AdminMCAdminType l_adminType)";
        log.entering(l_strMethodName);
        
        if (l_adminType == null)
        {
            log.error("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //a> 管理者タイプ情報を生成，以下の通りプロパティセットを行い返却する。
        WEB3AdminMCAdminTypeUnit l_web3AdminMCAdminTypeUnit =
            new WEB3AdminMCAdminTypeUnit();
       
        //b> 権限レベルコード = 管理者タイプ.get権限レベル()
        l_web3AdminMCAdminTypeUnit.permissionLevel = 
            l_adminType.getPermissionLevel();
        
        //c> 権限レベル名称 = 管理者タイプ.get権限レベル名称()
        l_web3AdminMCAdminTypeUnit.permissionLevelName =
            l_adminType.getPermissionLevelName();
        
        //d> DIR管理者フラグ = 管理者タイプ.getDIR管理者フラグ()
        l_web3AdminMCAdminTypeUnit.dirAdminFlag =
            l_adminType.getDIRAdminFlag();
        
        //e>全部店許可フラグ = 管理者タイプ.is全部店許可()
        l_web3AdminMCAdminTypeUnit.allBranchPermissionFlag = 
            l_adminType.isAllBranchPermission();
        
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCAdminTypeUnit;
    }
    
    /**
     * (create管理者タイプ情報)<BR>
     * 管理者タイプオブジェクトの配列より、管理者タイプ情報メッセージデータオブジェクトの配列を作成する。<BR>
     * <BR>
     * 引数の管理者タイプ[]の各要素毎に、this.create管理者タイプ情報(管理者タイプ)をコールして管理者タイプ情報を作成する。<BR>
     * 戻り値を配列にて返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (管理者タイプ)<BR>
     * 管理者タイプオブジェクトの配列<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit[]
     * @@roseuid 4177493800FA
     */
    public WEB3AdminMCAdminTypeUnit[] createAdminTypeUnit(WEB3AdminMCAdminType[] l_adminType) 
    {
        String l_strMethodName = "createAdminTypeUnit(WEB3AdminMCAdminType[] l_adminType)";
        log.entering(l_strMethodName);
        
        if (l_adminType == null)
        {
            log.error("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }

        //引数の管理者タイプ[]の各要素毎に、this.create管理者タイプ情報(管理者タイプ)をコールして管理者タイプ情報を作成する。
        //戻り値を配列にて返却する。
        
        //管理者タイプ情報[]を生成
        WEB3AdminMCAdminTypeUnit[] l_web3AdminMCAdminTypeUnit =
            new WEB3AdminMCAdminTypeUnit[l_adminType.length];
            
        for (int i = 0; i < l_adminType.length; i++)
        {
            l_web3AdminMCAdminTypeUnit[i] =
                this.createAdminTypeUnit(l_adminType[i]);        
        }
        
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCAdminTypeUnit;
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strPermissionLevel
     * @@param l_operatePossibleTransactionCategory
     * @@roseuid 4198640E0222
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit l_operatePossibleTransactionCategory) 
    {
     
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strPermissionLevel
     * @@param l_strTransactionCategory
     * @@return webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit[]
     * @@roseuid 4198640E038A
     */
    public WEB3AdminMCTransactionCategoryUnit[] createOperatePossibleTransactionCategoryList(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String[] l_strTransactionCategory) 
    {
     return null;
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strPermissionLevel
     * @@param l_operatePossibleTransactionCategory
     * @@roseuid 4199C22701E4
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory) 
    {
     
    }
}
@
