head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointPremium.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント景品(WEB3PointPremium.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.data.PointPremiumMasterParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (ポイント景品)<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointPremium implements BusinessObject 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointPremium.class);
    
    /**
     * (景品行)<BR>
     * ポイント景品行オブジェクト<BR>
     */
    private PointPremiumMasterParams pointPremiumMasterParams;
    
    /**
     * (ポイント景品)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数を、this.景品行にセットする。<BR>
     * @@param l_pointPremiumParams - (景品行)<BR>
     * ポイント景品行オブジェクト<BR>
     * 
     * @@roseuid 419331A20202
     */
    public WEB3PointPremium(PointPremiumMasterParams l_pointPremiumParams) 
    {
        final String STR_METHOD_NAME = " WEB3PointPremium(PointPremiumMasterParams)";
        log.entering(STR_METHOD_NAME);
        
        this.pointPremiumMasterParams = l_pointPremiumParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (ポイント景品)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）空のポイント景品Paramsオブジェクトを生成し、this.景品行にセットする。<BR>
     * <BR>
     * ２）引数を、this.景品行の各項目にセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_lngCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@param l_strPremiumName - (景品名)<BR>
     * 景品名<BR>
     * 
     * @@param l_lngRequiredPoint - (必要ポイント)<BR>
     * 必要ポイント<BR>
     * 
     * @@param l_datStartDateTime - (提供開始日時)<BR>
     * 提供開始日時<BR>
     * 
     * @@param l_datEndDateTime - (提供終了日時)<BR>
     * 提供終了日時<BR>
     * 
     * @@roseuid 419331A201E3
     */
    public WEB3PointPremium(
        String l_strInstitutionCode, 
        long l_lngCategoryNo, 
        String l_strPremiumNo, 
        String l_strPremiumName, 
        long l_lngRequiredPoint,
        Date l_datStartDateTime, 
        Date l_datEndDateTime) 
    {
        final String STR_METHOD_NAME = " WEB3PointPremium(String , long , String , String , long , Date , Date )";
        log.entering(STR_METHOD_NAME);
        
        //１）空のポイント景品Paramsオブジェクトを生成し、this.景品行にセットする。
        PointPremiumMasterParams l_pointPremiumParams = new PointPremiumMasterParams();
        this.pointPremiumMasterParams = l_pointPremiumParams;
        
        //引数を、this.景品行の各項目にセットする
        this.pointPremiumMasterParams.setInstitutionCode(l_strInstitutionCode);
        this.pointPremiumMasterParams.setCategoryNo((int)l_lngCategoryNo);
        this.pointPremiumMasterParams.setPremiumNo(l_strPremiumNo);
        this.pointPremiumMasterParams.setPremiumName(l_strPremiumName);
        this.pointPremiumMasterParams.setRequiredPoint((int)l_lngRequiredPoint);
        this.pointPremiumMasterParams.setStartDateTime(l_datStartDateTime);
        this.pointPremiumMasterParams.setEndDateTime(l_datEndDateTime);        
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get景品番号)<BR>
     * 景品番号を取得する。<BR>
     * <BR>
     * this.景品行.景品番号を返却する。<BR>
     * @@return String
     * @@roseuid 4192E597027F
     */
    public String getPremiumNo() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }

        return this.pointPremiumMasterParams.getPremiumNo();
    }
    
    /**
     * (get景品名)<BR>
     * 景品名を取得する。<BR>
     * <BR>
     * this.景品行.景品名を返却する。<BR>
     * @@return String
     * @@roseuid 4192E69300D9
     */
    public String getPremiumName() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }
        return this.pointPremiumMasterParams.getPremiumName();
    }
    
    /**
     * (get必要ポイント)<BR>
     * 必要ポイントを取得する。<BR>
     * <BR>
     * this.景品行.必要ポイントを返却する。<BR>
     * @@return long
     * @@roseuid 4192E6F3029E
     */
    public long getRequiredPoint() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }
        return this.pointPremiumMasterParams.getRequiredPoint();
    }
    
    /**
     * (get提供開始日時)<BR>
     * 提供開始日時を取得する。<BR>
     * <BR>
     * this.景品行.提供開始日時を返却する。<BR>
     * @@return Date
     * @@roseuid 4192E71C033A
     */
    public Date getStartDateTime() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }
        return this.pointPremiumMasterParams.getStartDateTime();
    }
    
    /**
     * (get提供終了日時)<BR>
     * 提供終了日時を取得する。<BR>
     * <BR>
     * this.景品行.提供終了日時を返却する。<BR>
     * @@return Date
     * @@roseuid 4192E74902BD
     */
    public Date getEndDateTime() 
    {
        if (this.pointPremiumMasterParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }
        return this.pointPremiumMasterParams.getEndDateTime();
    }
    
    /**
     * (set景品名)<BR>
     * 景品名をセットする。<BR>
     * <BR>
     * 引数.景品名を、this.景品行.景品名にセットする。<BR>
     * @@param l_strPremiumName - (景品名)<BR>
     * 景品名<BR>
     * @@roseuid 4193425A02AE
     */
    public void setPremiumName(String l_strPremiumName) 
    {
        final String STR_METHOD_NAME = " setPremiumName(String)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }
        this.pointPremiumMasterParams.setPremiumName(l_strPremiumName);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set必要ポイント)<BR>
     * 必要ポイントをセットする。<BR>
     * <BR>
     * 引数.必要ポイントを、this.景品行.必要ポイントにセットする。<BR>
     * @@param l_lngRequiredPoint - (必要ポイント)<BR>
     * 必要ポイント<BR>
     * @@roseuid 4193429E0185
     */
    public void setRequiredPoint(long l_lngRequiredPoint) 
    {
        final String STR_METHOD_NAME = " setRequiredPoint(long)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }

        this.pointPremiumMasterParams.setRequiredPoint((int)l_lngRequiredPoint);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set提供開始日時)<BR>
     * 提供開始日時をセットする。<BR>
     * <BR>
     * 引数.提供開始日時を、this.景品行.提供開始日時にセットする。<BR>
     * @@param l_datStartDateTime - (提供開始日時)<BR>
     * 提供開始日時<BR>
     * @@roseuid 419342D0006C
     */
    public void setStartDateTime(Date l_datStartDateTime) 
    {
        final String STR_METHOD_NAME = " setStartDateTime(Date)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }
        this.pointPremiumMasterParams.setStartDateTime(l_datStartDateTime);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set提供終了日時)<BR>
     * 提供終了日時をセットする。<BR>
     * <BR>
     * 引数.提供終了日時を、this.景品行.提供終了日時にセットする。<BR>
     * @@param l_datEndDateTime - (提供終了日時)<BR>
     * 提供終了日時<BR>
     * @@roseuid 419343010240
     */
    public void setEndDateTime(Date l_datEndDateTime) 
    {
        final String STR_METHOD_NAME = " setEndDateTime(Date)";
        log.entering(STR_METHOD_NAME);
        
        if (this.pointPremiumMasterParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "ポイント景品行オブジェクト=null.");
        }
        this.pointPremiumMasterParams.setEndDateTime(l_datEndDateTime);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * this.景品行を返却する。<BR>
     * @@return Object
     * @@roseuid 4191BBAE02A9
     */
    public Object getDataSourceObject() 
    {
        return this.pointPremiumMasterParams;
    }
    
    /**
     * this.景品行をコピーして、同じ内容の別インスタンスを作成する（clone）。<BR>
     * 作成したコピーを自身のthis.景品行にセットする。<BR>
     * @@roseuid 4191BBAE02A8
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams()";
        log.entering(STR_METHOD_NAME);
        
        PointPremiumMasterParams l_pointPremiumMasterParams = new PointPremiumMasterParams(this.pointPremiumMasterParams);
        this.pointPremiumMasterParams = l_pointPremiumMasterParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
