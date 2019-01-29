head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : CCオペレータ登録情報作成サービスImpl(WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistUnitCreateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * (CCオペレータ登録情報作成サービスImpl)<BR>
 * CCオペレータ登録情報作成サービス実装クラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl implements WEB3AdminMCCCOperatorRegistUnitCreateService 
{
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl.class);
    
    /**
     * (createCCオペレータ登録情報)<BR>
     * 扱者オブジェクトより、CCオペレータ登録情報メッセージデータオブジェクトを作成する。<BR>
     * <BR>
     * CCオペレータ登録情報を生成する。<BR>
     * <BR>
     * 扱者オブジェクト.getDataSourceObject()にて取得した扱者行オブジェクトより<BR>
     * 生成したオブジェクトに、以下の通りプロパティセットを行い返却する。<BR>
     * <BR>
     * 　@部店コード = 扱者者行.部店コード<BR>
     * 　@オペレータコード = 扱者行.扱者コード<BR>
     * 　@オペレータ名 = 扱者行.扱者苗字 ※扱者名（漢字）として使用<BR>
     * 　@代行注文可能区分 = 扱者行.代行注文可否フラグ<BR>
     * 　@所属コード = 扱者行.所属コード<BR>
     * 　@エラー回数 = ログイン行※.get(ログイン属性)<BR>
     * 　@更新日時 = 扱者行.更新日時<BR>
     * 　@更新者コード = 扱者行.更新者コード<BR>
     * <BR>
     * 　@※ ログイン行<BR>
     * 　@以下の条件でログインテーブルを検索し、取得した行オブジェクト。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@ログインテーブル.ログインID = 扱者.getLoginId()<BR>
     * <BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit
     * @@roseuid 417F713B00CF
     */
    public WEB3AdminMCCCOperatorRegistUnit createCCOperatorRegistUnit(WEB3GentradeTrader l_trader) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createCCOperatorRegistUnit(WEB3GentradeTrader l_trader)";
        log.entering(l_strMethodName);
        
        //CCオペレータ登録情報を生成する
        WEB3AdminMCCCOperatorRegistUnit l_web3AdminMCCCOperatorRegistUnit =
            new WEB3AdminMCCCOperatorRegistUnit();
            
        //扱者オブジェクト.getDataSourceObject()にて取得した扱者行オブジェクトより
        //生成したオブジェクトに、以下の通りプロパティセットを行い返却する。
        TraderRow l_traderRow = (TraderRow)l_trader.getDataSourceObject();
        
        //a> 部店コード = 扱者者行.部店コード
        l_web3AdminMCCCOperatorRegistUnit.branchCode = l_traderRow.getBranchCode();
        
        //b> オペレータコード = 扱者行.扱者コード
        l_web3AdminMCCCOperatorRegistUnit.operatorCode = l_traderRow.getTraderCode();
        
        //c> オペレータ名 = 扱者行.扱者苗字 ※扱者名（漢字）として使用
        l_web3AdminMCCCOperatorRegistUnit.operatorName = l_traderRow.getFamilyName();
        
        //d> 代行注文可能区分 = 扱者行.代行注文可否フラグ
        l_web3AdminMCCCOperatorRegistUnit.accountOrderDiv = l_traderRow.getAccountOrderFlag();
        
        //e> 所属コード = 扱者行.所属コード
        l_web3AdminMCCCOperatorRegistUnit.departmentCode = l_traderRow.getDepartmentCode();
        
        //f> エラー回数 = ログイン行※.get(ログイン属性)
        //(1)LoginId
        long l_lngLoginId = l_traderRow.getLoginId();
        
        LoginRow l_loginRow;
        try
        {
            //(2)LoginRow
            l_loginRow = LoginDao.findRowByPk(l_lngLoginId);         
        }  
        catch (DataFindException l_ex)
        {
            log.debug("__DataFindException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);                   
        }
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }   
        //(3)
        int l_intFailureCount = l_loginRow.getFailureCount();
        String l_strFailureCount;
        if (l_intFailureCount == 0)
        {
            l_strFailureCount = null;
        }
        else
        {
            l_strFailureCount = String.valueOf(l_intFailureCount);
        }
        
        l_web3AdminMCCCOperatorRegistUnit.errorCount = l_strFailureCount;         
        
        //g> 更新日時 = 扱者行.更新日時
        l_web3AdminMCCCOperatorRegistUnit.updateTimeStamp = l_traderRow.getLastUpdatedTimestamp();
        
        //h> 更新者コード = 扱者行.更新者コード
        l_web3AdminMCCCOperatorRegistUnit.updaterCode = l_traderRow.getLastUpdater();
        
        log.exiting(l_strMethodName);
        return l_web3AdminMCCCOperatorRegistUnit;
    }
    
    /**
     * (createCCオペレータ登録情報)<BR>
     * 扱者オブジェクトの配列より、CCオペレータ登録情報メッセージデータオブジェクトの配列を作成する。<BR>
     * <BR>
     * 引数の扱者[]の各要素毎に、this.createCCオペレータ登録情報(扱者)をコールしてCCオペレータ登録情報を作成する。<BR>
     * 戻り値を配列にて返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクトの配列<BR>
     * 
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit[]
     * @@roseuid 417F713B00EE
     */
    public WEB3AdminMCCCOperatorRegistUnit[] createCCOperatorRegistUnit(WEB3GentradeTrader[] l_trader) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createCCOperatorRegistUnit(WEB3GentradeTrader[] l_trader)";
        log.entering(l_strMethodName);
        
        //引数の扱者[]の各要素毎に、this.createCCオペレータ登録情報(扱者)をコールしてCCオペレータ登録情報を作成する。
        //戻り値を配列にて返却する。
        
        //WEB3AdminMCCCOperatorRegistUnit[]を生成する
        WEB3AdminMCCCOperatorRegistUnit[] l_web3AdminMCCCOperatorRegistUnits = 
            new WEB3AdminMCCCOperatorRegistUnit[l_trader.length];
            
        for (int i = 0; i < l_trader.length; i++)
        {
            l_web3AdminMCCCOperatorRegistUnits[i] = this.createCCOperatorRegistUnit(l_trader[i]);    
        }
        
        log.exiting(l_strMethodName);   
        return l_web3AdminMCCCOperatorRegistUnits;
    }
}
@
