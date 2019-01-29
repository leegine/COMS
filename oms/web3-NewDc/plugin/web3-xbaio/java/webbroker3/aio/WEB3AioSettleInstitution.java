head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSettleInstitution.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 提携決済機@関クラス(WEB3AioSettleInstitution)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 屈陽 (中訊) 新規作成
                   2004/10/22 韋念瓊 (中訊) レビュー 
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.aio.data.CooperateBankMasterDao;
import webbroker3.aio.data.CooperateBankMasterParams;
import webbroker3.aio.data.CooperateBankMasterRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;


/**
 * (提携決済機@関)<BR>
 * 提携決済機@関クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSettleInstitution implements BusinessObject 
{    
    /**
    * ログユーティリティ
    */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioSettleInstitution.class);
    
    /**
     * (提携決済機@関Row)<BR>
     * 提携決済機@関行オブジェクト
     */
    private CooperateBankMasterParams cooperateBankMasterParams;
    
    /**
     * (提携決済機@関)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の決済機@関IDから、提携決済機@関テーブルのレコードを<BR>
     * 取得しプロパティにセットする。<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@return WEB3AioSettleInstitution
     * @@throws WEB3BaseException
     * @@roseuid 40E28D2202B7
     */
    public WEB3AioSettleInstitution(String l_strPaySchemeId)
        throws WEB3BaseException 
    {
        String l_strMethodName = "WEB3AioSettleInstitution(String l_strPaySchemeId)";
        log.entering(l_strMethodName);
        
        try
        {
            //引数の決済機@関IDから、提携決済機@関テーブルのレコードを取得しプロパティにセットする
            CooperateBankMasterRow l_cooperateBankMasterRow = 
                CooperateBankMasterDao.findRowByPaySchemeId(l_strPaySchemeId);
            if (l_cooperateBankMasterRow == null)
            {
                log.debug("提携決済機@関テーブルのレコードを取得しない");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName);
  
            }    
            cooperateBankMasterParams = new CooperateBankMasterParams(l_cooperateBankMasterRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * (get名称)<BR>
     * 名称を取得する。
     * @@return String
     * @@roseuid 40F24D8500BD
     */
    public String getName() 
    {
        return cooperateBankMasterParams.getName();   
    }
    
    /**
     * @@return Object
     * @@roseuid 415A72A50177
     */
    public Object getDataSourceObject() 
    {
     return cooperateBankMasterParams;
    }
}
@
