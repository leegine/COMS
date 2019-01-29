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
filename	WEB3MutualProductCategory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信銘柄カテゴリー(WEB3MutualProductCategory)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
Revesion History : 2008/04/29 王志葵 (中訊) 仕様変更モデルNo.598
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信銘柄カテゴリー)<BR>
 * 投信銘柄カテゴリー　@エンティティクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualProductCategory 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualProductCategory.class);
    
    /**
     * (投信銘柄カテゴリー行)<BR>
     * 投信銘柄カテゴリー行
     * @@roseuid 415402CD02A3
     */
    private MutualFundProductCategoryParams mutualFundProductCategoryRow;
    
    /**
     * (投信銘柄カテゴリー)<BR>
     *  コンストラクタ <BR>
     */
    public WEB3MutualProductCategory()
    {
        
    }
    
    /**
     * (投信銘柄カテゴリー)<BR>
     * コンストラクタ <BR>
     * <BR>
     * 当オブジェクトを生成し、 引数.投信銘柄カテゴリーRowを<BR>
     * this.投信銘柄カテゴリー行にセットする。
     * @@param l_mutualFundProductCategoryRow - (投信銘柄カテゴリー行)<BR>
     * 投信銘柄カテゴリーRowオブジェクト
     * @@roseuid 415402CD02A3
     */
    public WEB3MutualProductCategory(MutualFundProductCategoryRow l_mutualFundProductCategoryRow)
    {
        this.mutualFundProductCategoryRow = 
            new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);
    }
    
    /**
     * （getDataSourceObjectの実装）<BR> 
     * <BR>
     * this.投信銘柄カテゴリー行を返却する。
     * @@return Object
     * @@roseuid 4153DC980279
     */
    public Object getDataSourceObject()
    {
        return this.mutualFundProductCategoryRow;
    }
    
    /**
     * (createForUpdateParams)
     * 更新行用Paramsのクローン行を生成して返却する。 <BR>
     * <BR>
     * 　@this.投信銘柄カテゴリー行をコピーして、<BR>
     * 同じ内容の別インスタンスを作成する（clone）。 <BR>
     * 作成したコピーを自身のthis.投信銘柄カテゴリー行にセットする。
     * @@roseuid 4153DCBB01FC
     */
    public void createForUpdateParams()
    {
        //更新行用Paramsのクローン行を生成して返却する。
        MutualFundProductCategoryParams l_params = 
            new MutualFundProductCategoryParams();
        l_params.setCategoryCode(this.mutualFundProductCategoryRow.getCategoryCode()) ;
        l_params.setCategoryName(this.mutualFundProductCategoryRow.getCategoryName());
        l_params.setCreatedTimestamp(this.mutualFundProductCategoryRow.getCreatedTimestamp());       
        l_params.setInstitutionCode(this.mutualFundProductCategoryRow.getInstitutionCode());
        l_params.setLastUpdatedTimestamp(this.mutualFundProductCategoryRow.getLastUpdatedTimestamp());
        l_params.setLastUpdater(this.mutualFundProductCategoryRow.getLastUpdater());
        l_params.setParentCategoryCode(this.mutualFundProductCategoryRow.getParentCategoryCode());
        
        //this.投信銘柄カテゴリー行をコピーして、同じ内容の別インスタンスを作成する（clone）。
        //作成したコピーを自身のthis.投信銘柄カテゴリー行にセットする。
        this.mutualFundProductCategoryRow = l_params;
    }
    
    /**
     * (createNew投信銘柄カテゴリー)<BR>
     * 投信銘柄カテゴリーオブジェクトを生成して返却する。<BR>
     * <BR>
     * １）投信銘柄カテゴリーParamsオブジェクトを生成し、<BR>
     * 　@this.投信銘柄カテゴリー行にセットする。<BR>
     * <BR>
     * ２）this.投信銘柄カテゴリー行.set証券会社コード( )をコールする。<BR>
     * [引数]<BR>
     * 　@証券会社コード=引数.証券会社コード<BR>
     * <BR>
     * ３）this.setカテゴリーコード( )をコールする。<BR>
     * [引数]<BR>
     * 　@カテゴリーコード=引数.カテゴリーコード<BR>
     * <BR>
     * ４）this.setカテゴリー名称( )をコールする。<BR>
     * [引数]<BR>
     * 　@カテゴリー名称=引数.カテゴリー名称
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strCategoryCode - (カテゴリーコード)
     * @@param l_strCategoryName - (カテゴリー名称)
     * @@roseuid 4153E78F0056
     */
    public void createNewMutualProductCategory(
        String l_strInstitutionCode,
        String l_strCategoryCode,
        String l_strCategoryName)
    {
        final String STR_METHOD_NAME =
            "createNewMutualProductCategory(String l_strInstitutionCode,"  +
            " String l_strCategoryCode, String l_strCategoryName)";
        log.entering(STR_METHOD_NAME);

        //１）投信銘柄カテゴリーParamsオブジェクトを生成し、 
        //  this.投信銘柄カテゴリー行にセットする。
        MutualFundProductCategoryParams 
            l_mutualFundProductCategoryParams = 
                new MutualFundProductCategoryParams();
        this.mutualFundProductCategoryRow = l_mutualFundProductCategoryParams;
        
        //２）this.投信銘柄カテゴリー行.set証券会社コード( )をコールする。 
        //  [引数] 
        //  証券会社コード=引数.証券会社コード 
        this.mutualFundProductCategoryRow.setInstitutionCode(l_strInstitutionCode);
        
        //３）this.setカテゴリーコード( )をコールする。 
        //  [引数] 
        //  カテゴリーコード=引数.カテゴリーコード 
        this.mutualFundProductCategoryRow.setCategoryCode(l_strCategoryCode);
                
        //４）this.setカテゴリー名称( )をコールする。 
        //  [引数] 
        //  カテゴリー名称=引数.カテゴリー名称
        l_mutualFundProductCategoryParams.setCategoryName(l_strCategoryName);   
        log.exiting(STR_METHOD_NAME) ;
    }
    
    /**
     * (saveNew投信銘柄カテゴリー)<BR>
     * this.投信銘柄カテゴリー行の内容で、データベースを更新する。<BR>
     * （Insert)<BR>
     * <BR>
     * １）this.投信銘柄カテゴリー行オブジェクトに、以下の値をセットする。 <BR>
     * 　@最終更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( ) <BR>
     * 　@作成日付=<BR>
     * GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値 <BR>
     * <BR>
     * 　@更新日付=<BR>
     * GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値 <BR>
     * <BR>
     * ２）this.投信銘柄カテゴリー行オブジェクトの内容で、 <BR>
     * 　@投信銘柄カテゴリーテーブルを更新（Insert）する。
     * @@throws WEB3BaseException
     * @@roseuid 4153E90802F6
     */
    public void saveNewMutualProductCategory() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "saveNewMutualProductCategory";
        log.entering(STR_METHOD_NAME);
        
        //１）this.投信銘柄カテゴリー行オブジェクトに、以下の値をセットする。 
        //    最終更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード( )
        //最終更新者コード
        String l_strAdministratorCode = null;
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_strAdministratorCode = l_admin.getAdministratorCode(); 

        //最終更新者コードをセットする。 
        this.mutualFundProductCategoryRow.setLastUpdater(l_strAdministratorCode);
        
        //作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.mutualFundProductCategoryRow.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        
        //更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値     
        this.mutualFundProductCategoryRow.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        
        //２）this.投信銘柄カテゴリー行オブジェクトの内容で、 
        //　@  投信銘柄カテゴリーテーブルを更新（Insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(this.mutualFundProductCategoryRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error("投信銘柄カテゴリーテーブルを更新（Insert）する....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("投信銘柄カテゴリーテーブルを更新（Insert）する....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save投信銘柄カテゴリー)<BR>
     * this.投信銘柄カテゴリー行の内容で、データベースを更新する。<BR>
     * （Update)<BR>
     * <BR>
     * １）this.投信銘柄カテゴリー行オブジェクトに、以下の値をセットする。<BR>
     * 　@最終更新者コード=<BR>
     * 管理者.getInstanceFromログイン情報( ).get管理者コード( ) <BR>
     * 　@更新日付 =<BR>
     * GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値 <BR>
     * <BR>
     * ２）this.投信銘柄カテゴリー行オブジェクトの内容で、<BR>
     * 　@投信銘柄カテゴリーテーブルを更新(Update)する。
     * @@throws WEB3BaseException
     * @@roseuid 4153E865023A
     */
    public void saveMutualProductCategory() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "saveMutualProductCategory()";
        log.entering(STR_METHOD_NAME);
        
        //１）this.投信銘柄カテゴリー行オブジェクトに、以下の値をセットする。 
        //    最終更新者コード=管理者.getInstanceFromログイン情報( ).get管理者コード()
        String l_strAdministratorCode = null;
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_strAdministratorCode = l_admin.getAdministratorCode();  
            
        //最終更新者コードをセットする。 
        this.mutualFundProductCategoryRow.setLastUpdater(l_strAdministratorCode);
        
        //更新日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値 
        this.mutualFundProductCategoryRow.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        
        //２）this.投信銘柄カテゴリー行オブジェクトの内容で、 
        //    投信銘柄カテゴリーテーブルを更新(Update)する。
      
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(this.mutualFundProductCategoryRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error("投信銘柄カテゴリーテーブルを更新(Update)する.....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("投信銘柄カテゴリーテーブルを更新(Update)する.....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。 <BR>
     * <BR>
     * this.投信銘柄カテゴリー行.get証券会社コード( )の戻り値を返す。
     * @@return String
     * @@roseuid 4153DD6B0315
     */
    public String getInstitutionCode()
    {
        return this.mutualFundProductCategoryRow.getInstitutionCode();
    }
   
    /**
     * (setカテゴリーコード)<BR>
     * カテゴリーコードの設定を行う。 <BR>
     * <BR>
     * 1) this.投信銘柄カテゴリー行.setカテゴリーコード( )をコールする。 <BR>
     * [引数] <BR>
     * 　@カテゴリーコード=引数.カテゴリーコード
     * @@param l_strCategoryCode - (カテゴリーコード)
     * @@roseuid 4153E5CE0298
     */
    public void setCategoryCode(String l_strCategoryCode)
    {
        this.mutualFundProductCategoryRow.setCategoryCode(l_strCategoryCode);
    }
    
    /**
     * (getカテゴリーコード)<BR>
     * カテゴリーコードを返す。 <BR>
     * <BR>
     * this.投信銘柄カテゴリー行.getカテゴリーコード( )の戻り値を返す。
     * @@return String
     * @@roseuid 4153E59A02D6
     */
    public String getCategoryCode()
    {
        return this.mutualFundProductCategoryRow.getCategoryCode();
    }
    
    /**
     * (setカテゴリー名称)<BR>
     * カテゴリー名称の設定を行う。 <BR>
     * <BR>
     * 1) this.投信銘柄カテゴリー行.setカテゴリー名称( )をコールする。 <BR>
     * [引数] <BR>
     * 　@カテゴリー名称=引数.カテゴリー名称
     * @@param l_strCategoryName - (カテゴリー名称)
     * @@roseuid 4153E61803E0
     */
    public void setCategoryName(String l_strCategoryName)
    {
        this.mutualFundProductCategoryRow.setCategoryName(l_strCategoryName);
    }
    
    /**
     * (getカテゴリー名称)<BR>
     * カテゴリー名称を返す。 <BR>
     * <BR>
     * this.投信銘柄カテゴリー行.getカテゴリー名称( )の戻り値を返す。
     * @@return String
     * @@roseuid 4153E6190027
     */
    public String getCategoryName()
    {
        return this.mutualFundProductCategoryRow.getCategoryName();
    }
    
    /**
     * (set親カテゴリーコード)<BR>
     * 親カテゴリーコードの設定を行う。 <BR>
     * <BR>
     * 1) this.投信銘柄カテゴリー行.set親カテゴリーコード( )をコールする。 <BR>
     * [引数] <BR>
     * 　@親カテゴリーコード=引数.親カテゴリーコード
     * @@param l_strParentCategoryCode - (親カテゴリーコード)
     * @@roseuid 4153E6420259
     */
    public void setParentCategoryCode(String l_strParentCategoryCode)
    {
        this.mutualFundProductCategoryRow.setParentCategoryCode(l_strParentCategoryCode);
    }
    
    /**
     * (get親カテゴリーコード)<BR>
     * 親カテゴリーコードを返す。 <BR>
     * <BR>
     * this.投信銘柄カテゴリー行.get親カテゴリーコード( )の戻り値を返す。
     * @@return String
     * @@roseuid 4153E6420269
     */
    public String getParentCategoryCode()
    {
        return this.mutualFundProductCategoryRow.getParentCategoryCode();
    }

    /**
     * (delete投信銘柄カテゴリー)<BR>
     * 以下を条件に、当該レコードを投信銘柄カテゴリーテーブルより削除する。<BR>
     * <BR>
     * [削除条件]<BR>
     * 証券会社コード = 引数．証券会社コード and<BR>
     * カテゴリーコード = 引数．カテゴリーコード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strCategoryCode - (カテゴリーコード)<BR>
     * カテゴリーコード<BR>
     * @@throws WEB3BaseException
     */
    public void deleteMutualProductCategory(
        String l_strInstitutionCode,
        String l_strCategoryCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteMutualProductCategory(String, String)";
        log.entering(STR_METHOD_NAME);

        //以下を条件に、当該レコードを投信銘柄カテゴリーテーブルより削除する。
        //[削除条件]
        //証券会社コード = 引数．証券会社コード and
        //カテゴリーコード = 引数．カテゴリーコード
        String l_strQueryString = " institution_code = ? and category_code = ? ";

        Object[] l_objQueryDataContainers = {l_strInstitutionCode, l_strCategoryCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(
                MutualFundProductCategoryRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainers);
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

        log.exiting(STR_METHOD_NAME);
    }
}
@
