head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondFinTransactionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券トランザクションマネージャ(WEB3BondFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/14 徐大方(中訊) 新規作成         
*/

package webbroker3.bd;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondFinTransactionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.data.FixedFinTransactionParams;
import webbroker3.tradingpower.data.FixedFinTransactionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券トランザクションマネージャ)<BR>
 * 債券トランザクションマネージャクラス
 * 
 * @@author 徐大方
 * @@version 1.0
 */

public class WEB3BondFinTransactionManager extends BondFinTransactionManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondFinTransactionManager.class);
    
    /**
     * (undo確定トランザクションBy注文単位ID)<BR>
     * 引数.注文単位IDに関連する確定トランザクションをundoする <BR>
     * <BR>
     * １）確定トランザクション（株式顧客勘定明細）テーブルを検索する。 <BR>
     * 　@［検索条件］  <BR>
     * 　@　@注文単位ID＝引数.注文単位ID  <BR>
     * 　@　@銘柄タイプ　@＝債券  <BR>
     *  <BR>
     * ２）１）で取得したレコード数分ループ  <BR>
     * 　@２－１）ループ中の確定トランザクションParamsのクローンを作成する。 <BR>
     * 　@２－２）クローンの削除フラグをTRUEにする。  <BR>
     * 　@２－３）DB更新処理をする。  <BR>
     *  <BR>
     * @@param l_bondOrderUnit - (注文単位ID)<BR>
     * 注文単位ID
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    public void  undoTransactionByOrderUnitId(long l_lngOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "undoTransactionByOrderUnitId(long)";
        log.entering(STR_METHOD_NAME);

        List l_lisFixedFinTransaction = new ArrayList();

        //１）確定トランザクション（株式顧客勘定明細）テーブルを検索する。 
        //［検索条件］ 
        //注文単位ID＝引数.注文単位ID 
        //銘柄タイプ　@＝債券 
        String l_strWhere = "order_unit_id = ? and product_type = ?";
        Object[] l_bindVars = {new Long(l_lngOrderUnitId), ProductTypeEnum.BOND};
        QueryProcessor l_queryProcessos = null;  
        try
        {
            l_queryProcessos = Processors.getDefaultProcessor();  
            l_lisFixedFinTransaction = 
                l_queryProcessos.doFindAllQuery(
                    FixedFinTransactionRow.TYPE,
                    l_strWhere,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            throw new WEB3SystemLayerException(
              WEB3ErrorCatalog.SYSTEM_ERROR_80003,
              this.getClass().getName() + "." + STR_METHOD_NAME,
              l_ex.getMessage(),
              l_ex);    
        }
            
        int l_intSize = 0;
        if (l_lisFixedFinTransaction != null && !l_lisFixedFinTransaction.isEmpty())
        {
            l_intSize = l_lisFixedFinTransaction.size();   
        }
        
        //２）１）で取得したレコード数分ループ 
        FixedFinTransactionParams l_fixedFinTransactionParams = null;
        for (int i = 0; i < l_intSize; i++)
        {
            //２－１）ループ中の確定トランザクションParamsのクローンを作成する。 
            FixedFinTransactionRow l_fixedFinTransactionRow = 
                (FixedFinTransactionRow)l_lisFixedFinTransaction.get(i);
            l_fixedFinTransactionParams = 
                new FixedFinTransactionParams(l_fixedFinTransactionRow);
            //２－２）クローンの削除フラグをTRUEにする。
            l_fixedFinTransactionParams.setDeleteFlag(BooleanEnum.TRUE);
            try
            {
                //２－３）DB更新処理をする。
                l_queryProcessos.doUpdateQuery(l_fixedFinTransactionParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);    
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました");
                throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);    
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
