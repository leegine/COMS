head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenLoginAccountRelation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン・アカウント・リレーション(WEB3AccOpenLoginAccountRelation.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 徐宏偉 (中訊) 新規作成
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountRelationDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountRelationParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginAccountRelationRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ログイン・アカウント・リレーション)<BR>
 * ログイン・アカウント・リレーション<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenLoginAccountRelation 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenLoginAccountRelation.class);
    
    /**
     * (ログイン・アカウント・リレーション行)<BR>
     * ログイン・アカウント・リレーション行<BR>
     * <BR>
     * ※ ログイン・アカウント・リレーションParamsクラスはDDLより自動生成する。<BR>
     */
    private LoginAccountRelationParams loginAccountRelationParams;
    
    /**
     * (ログイン・アカウント・リレーション)<BR>
     * ログイン・アカウント・リレーションオブジェクトを取得する。 <BR> 
     * <BR>
     * 以下の条件で <BR>
     * ログイン・アカウント・リレーションテーブルを検索する。<BR> 
     * <BR>
     * 　@[条件] <BR>
     * 　@ログイン・アカウント・リレーション.ログインタイプＩＤ = 引数.ログインタイプＩＤ <BR>
     * <BR>
     * 検索結果のログイン・アカウント・リレーション行オブジェクトを、<BR> 
     * this.ログイン・アカウント・リレーション行にセットする。<BR>
     * @@param l_strLoginTypeId - (ログインタイプＩＤ)<BR>
     * ログインタイプＩＤ<BR>
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenLoginAccountRelation(String l_strLoginTypeId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3AccOpenLoginAccountRelation(String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // 　@[条件]
            // 　@ログイン・アカウント・リレーション.ログインタイプＩＤ = 引数.ログインタイプＩＤ 
            if (WEB3StringTypeUtility.isEmpty(l_strLoginTypeId))
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
            }
            
            LoginAccountRelationRow l_row = 
                (LoginAccountRelationRow)LoginAccountRelationDao.findRowByTypeId(
                    Long.parseLong(l_strLoginTypeId));
            
            if (l_row == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
            
            // 検索結果のログイン・アカウント・リレーション行オブジェクトを、
            // this.ログイン・アカウント・リレーション行にセットする。
            this.loginAccountRelationParams = new LoginAccountRelationParams(l_row);
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
     * (getリレーションID)<BR>
     * リレーションIDを取得する。<BR> 
     * <BR>
     * this.ログイン・アカウント・リレーション行.リレーションＩＤを返却する。<BR>
     * @@return String
     */
    public String getRelationId()
    {
        final String STR_METHOD_NAME = "getRelationId()";
        log.exiting(STR_METHOD_NAME);
        
        //リレーションIDを取得する。
        log.exiting(STR_METHOD_NAME);
        return WEB3StringTypeUtility.formatNumber(
            this.loginAccountRelationParams.getRelationId());
    }
    
    /**
     * @@return Object
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject()";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        return this.loginAccountRelationParams;   
    }
}
@
