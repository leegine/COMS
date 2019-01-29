head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCategory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイントカテゴリー(WEB3PointCategory.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.point.data.PointCategoryMasterParams;
import webbroker3.point.data.PointCategoryMasterRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (ポイントカテゴリー)<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointCategory implements BusinessObject 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointCategory.class);
    
    /**
     * (カテゴリー行)<BR>
     * ポイントカテゴリー行オブジェクト<BR>
     */
    private PointCategoryMasterParams pointCategoryMasterParams;
    
    /**
     * (ポイントカテゴリー)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数を、this.カテゴリー行にセットする。<BR>
     * @@param l_pointCategoryParams - (カテゴリー行)<BR>
     * ポイントカテゴリー行オブジェクト<BR>
     * 
     * @@roseuid 4191C3DF00A5
     */
    public WEB3PointCategory(PointCategoryMasterParams l_pointCategoryParams) 
    {
        final String STR_METHOD_NAME = " WEB3PointCategory(PointCategoryMasterParams)";
        log.entering(STR_METHOD_NAME);
        
        this.pointCategoryMasterParams = l_pointCategoryParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (ポイントカテゴリー)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）空のポイントカテゴリーParamsオブジェクトを生成し、<BR>this.カテゴリー行にセットする。<BR>
     * <BR>
     * ２）引数を、this.カテゴリー行の各項目にセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryName - (カテゴリー名)<BR>
     * カテゴリー名<BR>
     * 
     * @@param l_strOutline - (概要)<BR>
     * 概要<BR>
     * 
     * @@roseuid 4191A0FB00CC
     */
    public WEB3PointCategory(String l_strInstitutionCode, String l_strCategoryName, String l_strOutline) 
    {
        final String STR_METHOD_NAME = " WEB3PointCategory(String , String , String )";
        log.entering(STR_METHOD_NAME);
        
        //１）空のポイントカテゴリーParamsオブジェクトを生成し、<BR>this.カテゴリー行にセットする。
        PointCategoryMasterParams l_pointCategoryParams = new PointCategoryMasterParams();
        this.pointCategoryMasterParams = l_pointCategoryParams;
        
        //２）引数を、this.カテゴリー行の各項目にセットする。
        this.pointCategoryMasterParams.setInstitutionCode(l_strInstitutionCode);
        this.pointCategoryMasterParams.setCategoryName(l_strCategoryName);
        this.pointCategoryMasterParams.setCategoryOutline(l_strOutline);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setNewカテゴリー番号)<BR>
     * 新規のカテゴリー番号を取得し、this.カテゴリー行.カテゴリー番号にセットする。<BR>
     * <BR>
     * １）以下の条件で、ポイントカテゴリーマスターの検索を行う。<BR>
     * <BR>
     * 検索条件： 証券会社コード = 引数.証券会社コード<BR>
     * order by： カテゴリー番号の降順<BR>
     * <BR>
     * ２）リストの1番目の要素を取得する。<BR>
     *    ※カテゴリー番号が最大のものを取得する。<BR>
     * <BR>
     * ３）取得した要素のカテゴリー番号に+1した値を、<BR>this.カテゴリー行.カテゴリー番号にセットする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@roseuid 4191A02E034D
     */
    public void setNewCategoryNo(String l_strInstitutionCode) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " setNewCategoryNo(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // １）以下の条件で、ポイントカテゴリーマスターの検索を行う。
            String l_strWhere = "institution_code = ?";
            String l_strOrderBy = "category_no desc";
            Object[] l_objBinds = new Object[]{l_strInstitutionCode};         

            List l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(
                PointCategoryMasterRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_objBinds);//DataException
        
            // ２）リストの1番目の要素を取得する。
            int l_intNewCategoryNo = 0;
            if (l_lisRecords != null && l_lisRecords.size() > 0)
            {
                log.debug(" ２）リストの1番目の要素を取得する。");
                PointCategoryMasterParams l_param = (PointCategoryMasterParams)l_lisRecords.get(0);
                if (l_param == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }

                l_intNewCategoryNo = l_param.getCategoryNo() ;
            }
            
            // ３）取得した要素のカテゴリー番号に+1した値を、this.カテゴリー行.カテゴリー番号にセットする。
            this.pointCategoryMasterParams.setCategoryNo(l_intNewCategoryNo + 1);            
        }
        catch (DataException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setカテゴリー名)<BR>
     * 引数のカテゴリー名を、this.カテゴリー行.カテゴリー名にセットする。<BR>
     * @@param l_strCategoryName - (カテゴリー名)<BR>
     * カテゴリー名<BR>
     * @@roseuid 4191B4DD00F3
     */
    public void setCategoryName(String l_strCategoryName) 
    {
        final String STR_METHOD_NAME = " setCategoryName(String)";
        log.entering(STR_METHOD_NAME);

        if (pointCategoryMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイントカテゴリー行オブジェクト=null.");
        }
        this.pointCategoryMasterParams.setCategoryName(l_strCategoryName);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setカテゴリー概要)<BR>
     * 引数のカテゴリー概要を、this.カテゴリー行.カテゴリー概要にセットする。<BR>
     * @@param l_strOutline - (概要)<BR>
     * カテゴリー概要<BR>
     * @@roseuid 4191B5450170
     */
    public void setCategoryOutline(String l_strOutline) 
    {
        final String STR_METHOD_NAME = " setCategoryOutline(String)";
        log.entering(STR_METHOD_NAME);
        
        if (pointCategoryMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイントカテゴリー行オブジェクト=null.");
        }
        this.pointCategoryMasterParams.setCategoryOutline(l_strOutline);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getカテゴリー番号)<BR>
     * カテゴリー番号を取得する。<BR>
     * <BR>
     * this.カテゴリー行.カテゴリー番号を返却する。<BR>
     * @@return long
     * @@roseuid 4192D733028D
     */
    public long getCategoryNo() 
    {
        if (pointCategoryMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイントカテゴリー行オブジェクト=null.");
        }
        return this.pointCategoryMasterParams.getCategoryNo();
    }
    
    /**
     * (getカテゴリー名)<BR>
     * カテゴリー名を取得する。<BR>
     * <BR>
     * this.カテゴリー行.カテゴリー名を返却する。<BR>
     * @@return String
     * @@roseuid 4192D76F03A6
     */
    public String getCategoryName() 
    {
        if (pointCategoryMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイントカテゴリー行オブジェクト=null.");
        }
        return this.pointCategoryMasterParams.getCategoryName();
    }
    
    /**
     * (getカテゴリー概要)<BR>
     * カテゴリー概要を取得する。<BR>
     * <BR>
     * this.カテゴリー行.カテゴリー概要を返却する。<BR>
     * @@return String
     * @@roseuid 4192D7830193
     */
    public String getCategoryOutline() 
    {
        if (pointCategoryMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイントカテゴリー行オブジェクト=null.");
        }
        return this.pointCategoryMasterParams.getCategoryOutline();
    }
    
    /**
     * this.カテゴリー行を返却する。<BR>
     * @@return Object
     * @@roseuid 4191BAA101BE
     */
    public Object getDataSourceObject() 
    {
        return this.pointCategoryMasterParams;
    }
    
    /**
     * this.カテゴリー行をコピーして、同じ内容の別インスタンスを作成する（clone）。<BR>
     * 作成したコピーを自身のthis.カテゴリー行にセットする。<BR>
     * @@roseuid 4191B9400325
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        PointCategoryMasterParams l_pointCategoryParams = 
            new PointCategoryMasterParams(this.pointCategoryMasterParams);
        this.pointCategoryMasterParams = l_pointCategoryParams;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
