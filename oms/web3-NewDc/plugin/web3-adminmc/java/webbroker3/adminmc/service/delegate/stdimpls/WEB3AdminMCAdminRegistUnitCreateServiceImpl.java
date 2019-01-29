head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnitCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者登録情報作成サービスImpl(WEB3AdminMCAdminRegistUnitCreateServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;

import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistUnitCreateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者登録情報作成サービスImpl)<BR>
 * 管理者登録情報作成サービス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistUnitCreateServiceImpl implements WEB3AdminMCAdminRegistUnitCreateService 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMCAdminRegistUnitCreateServiceImpl.class);
    
    /**
     * (create管理者登録情報)<BR>
     * 管理者オブジェクトより、管理者登録情報メッセージデータオブジェクトを作成する。<BR>
     * <BR>
     * 管理者登録情報を生成する。<BR>
     * <BR>
     * 管理者オブジェクト.getDataSourceObject()にて取得した管理者行オブジェクトより<BR>
     * 生成したオブジェクトに、以下の通りプロパティセットを行い返却する。<BR>
     * <BR>
     * 　@部店コード = 管理者行.部店コード<BR>
     * 　@管理者コード = 管理者行.管理者コード<BR>
     * 　@管理者名 = 管理者行.管理者名<BR>
     * 　@メールアドレス = 管理者行.メールアドレス<BR>
     * 　@権限レベルコード = 管理者行.権限レベル<BR>
     * 　@エラー回数 = ログイン行※.get(ログイン属性)<BR>
     * 　@更新日時 = 管理者行.更新日時<BR>
     * 　@更新者コード = 管理者行.更新者コード<BR>
     * <BR>
     * 　@※ ログイン行<BR>
     * 　@以下の条件でログインテーブルを検索し、取得した行オブジェクト。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@ログインテーブル.ログインID = 管理者.getログインID()<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit
     * @@roseuid 417DCBD803E4
     */
    public WEB3AdminMCAdminRegistUnit createAdminRegistUnit(WEB3Administrator l_administrator) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createAdminRegistUnit(WEB3Administrator l_administrator)";
        log.entering(l_strMethodName);
        
        if (l_administrator == null)
        {
            log.error("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //管理者登録情報を生成する。 
        WEB3AdminMCAdminRegistUnit l_web3AdminMCAdminRegistUnit = 
            new WEB3AdminMCAdminRegistUnit();
        
        //管理者オブジェクト.getDataSourceObject()にて取得した管理者行オブジェクトより
        //生成したオブジェクトに、以下の通りプロパティセットを行い返却する。
        
        //a> 管理者行オブジェクトを生成
        AdministratorRow l_administratorRow = 
            (AdministratorRow)l_administrator.getDataSourceObject();
        
        //b> 部店コード = 管理者行.部店コード
        l_web3AdminMCAdminRegistUnit.branchCode =
            l_administratorRow.getBranchCode();
        
        //c> 管理者コード = 管理者行.管理者コード
        l_web3AdminMCAdminRegistUnit.administratorCode =
            l_administratorRow.getAdministratorCode();
        
        //d> 管理者名 = 管理者行.管理者名
        l_web3AdminMCAdminRegistUnit.administratorName =
            l_administratorRow.getName();
        
        //e> メールアドレス = 管理者行.メールアドレス
        l_web3AdminMCAdminRegistUnit.mailAddress =
            l_administratorRow.getEmailAddress();
            
        //f> 権限レベルコード = 管理者行.権限レベル
        l_web3AdminMCAdminRegistUnit.permissionLevel =
            l_administratorRow.getPermissionLevel();
            
        //g> エラー回数 = ログイン行※.get(ログイン属性)
        //※ ログイン行
        //以下の条件でログインテーブルを検索し、取得した行オブジェクト。             
        //[条件]
        //ログインテーブル.ログインID = 管理者.getログインID()
        long l_lngLoginId = l_administrator.getLoginId();
        
        LoginRow l_loginRow;
        try
        {
            l_loginRow = LoginDao.findRowByPk(l_lngLoginId);
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
        if (l_loginRow.getFailureCountIsNull())
        {
            l_web3AdminMCAdminRegistUnit.errorCount = null;                                   
        }
        else
        {
            l_web3AdminMCAdminRegistUnit.errorCount =
                String.valueOf(l_loginRow.getFailureCount());            
        }
        //h> 更新日時 = 管理者行.更新日時
        l_web3AdminMCAdminRegistUnit.updateTimeStamp =
            l_administratorRow.getLastUpdatedTimestamp();
            
        //i> 更新者コード = 管理者行.更新者コード
        l_web3AdminMCAdminRegistUnit.updaterCode =
            l_administratorRow.getLastUpdater();          
            
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCAdminRegistUnit;
    }
    
    /**
     * (create管理者登録情報)<BR>
     * 管理者オブジェクトの配列より、管理者登録情報メッセージデータオブジェクトの配列を作成する。<BR>
     * <BR>
     * 引数の管理者[]の各要素毎に、this.create管理者登録情報(管理者)をコールして管理者登録情報を作成する。<BR>
     * 戻り値を配列にて返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクトの配列<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit[]
     * @@roseuid 417DCBD803E6
     */
    public WEB3AdminMCAdminRegistUnit[] createAdminRegistUnit(WEB3Administrator[] l_administrator) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createAdminRegistUnit(WEB3Administrator[] l_administrator)";
        log.entering(l_strMethodName);
        
        if (l_administrator == null)
        {
            log.error("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //引数の管理者[]の各要素毎に、this.create管理者登録情報(管理者)をコールして管理者登録情報を作成する。
        //戻り値を配列にて返却する。
        WEB3AdminMCAdminRegistUnit[] l_web3AdminMCAdminRegistUnit =
            new WEB3AdminMCAdminRegistUnit[l_administrator.length];
            
        for (int i = 0; i < l_administrator.length; i++)
        {
            l_web3AdminMCAdminRegistUnit[i] = this.createAdminRegistUnit(l_administrator[i]);           
        }
        
        log.exiting(l_strMethodName); 
               
        return l_web3AdminMCAdminRegistUnit;
    }
}
@
