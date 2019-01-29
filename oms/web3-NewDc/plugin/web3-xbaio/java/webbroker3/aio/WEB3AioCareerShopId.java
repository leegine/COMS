head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCareerShopId.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キャリア別加盟店IDクラス(WEB3AioCareerShopId.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/13 周捷 (中訊)  新規作成
                   2006/04/26 WeiXin (中訊) 仕様変更・モデル542
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.CareerShopIdDao;
import webbroker3.aio.data.CareerShopIdRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;


/**
 * (キャリア別加盟店ID)<BR>
 * キャリア別加盟店IDクラス。
 * 
 * @@author 周捷 
 * @@version 1.0
 */
public class WEB3AioCareerShopId 
{
    /**
     * ログ出力ユーティリティ。
     */ 
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioCareerShopId.class);      
    
    /**
     * (キャリア別加盟店IDRow)<BR>
     * キャリア別加盟店ID行オブジェクト。
     */
    private CareerShopIdRow  careerShopIdRow;
    
    /**
     * @@roseuid 443DDF5E035B
     */
    public WEB3AioCareerShopId() 
    {
     
    }
    
    /**
     * (キャリア別加盟店ID)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数をキーとして、キャリア別加盟店ＩＤテーブルのレコードを<BR>
     * 取得しプロパティにセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strCareerDiv - (キャリア区分)
     * @@return webbroker3.aio.WEB3AioCareerShopId
     * @@throws NotFoundException 
     * @@throws WEB3BaseException 
     * @@roseuid 443511BE015C
     */
    public WEB3AioCareerShopId(String l_strInstitutionCode, String l_strBranchCode, String l_strCareerDiv) 
        throws NotFoundException, WEB3BaseException 
    {
        final String STR_METHOD_NAME = " WEB3AioCareerShopId(String, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            careerShopIdRow = CareerShopIdDao.findRowByPk(
                l_strInstitutionCode,
                l_strBranchCode, 
                l_strCareerDiv);
                    
        }
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("検索結果に一致する行が存在しない");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get加盟店ID)<BR>
     * 加盟店IDを取得する。
     * @@return String
     * @@roseuid 443528140215
     */
    public String getShopId() 
    {
        return careerShopIdRow.getShopId();
    }
    
    /**
     * (get決済URL)<BR>
     * 決済URLを取得する。
     * @@return String
     * @@roseuid 4435282F0215
     */
    public String getPfURL() 
    {
        return careerShopIdRow.getPfUrl();
    }
    
    /**
     * (getリターンURL )<BR>
     * リターンURLを取得する。
     * @@return String
     * @@roseuid 4435282F0215
     */
    public String getReturnURL() 
    {
        return careerShopIdRow.getReturnUrl();
    }

}
@
