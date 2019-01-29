head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyNormalTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知正常処理一件TransactionCallback(WEB3AioOutputNotifyNormalTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/19 韋念瓊 (中訊) 新規作成
*/


package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

import webbroker3.aio.data.HostSecDeliveryTransferRow;
import webbroker3.aio.data.HostTransferPaymentRow;
import webbroker3.aio.define.WEB3HostTransferPaymentStatusDef;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;




/**
 * （証券振替通知正常処理一件TransactionCallback）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 韋念瓊
 * @@version 1.0
 */
public class WEB3AioOutputNotifyNormalTransactionCallback implements TransactionCallback
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyNormalTransactionCallback.class);

    /**
      * 振替請求伝票キューRowオブジェクト。<BR>
      */
    private HostTransferPaymentRow hostTransferPaymentRow;
    
    /**
     * 証券出庫請求伝票キューRowオブジェクト。<BR>
     */
    private HostSecDeliveryTransferRow hostSecDeliveryTransferRow;
   
    /**
     * is振替請求伝票キューRow。<BR>
     */
    private boolean isTransferPayment;

    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostTransferPaymentRow - (振替請求伝票キューRow)
     * @@params l_blnIsTransferPayment - (is振替請求伝票キュー)
     */
    public WEB3AioOutputNotifyNormalTransactionCallback(
        HostTransferPaymentRow l_hostTransferPaymentRow,
        boolean l_blnIsTransferPaymentRow)
    {
        hostTransferPaymentRow = l_hostTransferPaymentRow;
        isTransferPayment = l_blnIsTransferPaymentRow;
    }
    
    /**
     * コンストラクタ。<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * @@params l_hostTransferPaymentRow - (証券出庫請求伝票キューRow)
     * @@params l_blnIsTransferPayment - (is振替請求伝票キュー)
     */
    public WEB3AioOutputNotifyNormalTransactionCallback(
        HostSecDeliveryTransferRow l_hostSecDeliveryTransferRow,
        boolean l_blnIsTransferPayment)
    {
        hostSecDeliveryTransferRow = l_hostSecDeliveryTransferRow;
        isTransferPayment = l_blnIsTransferPayment;
    }
    
    

   /**
     * トランザクション処理を実施する。<BR>
     * @@return Object
     * @@throws DataQueryException, DataNetworkException, DataCallbackException
     */
    public Object process()
        throws DataQueryException, DataNetworkException, DataCallbackException
    {
        final String STR_METHOD_NAME = "process()";
        log.entering(STR_METHOD_NAME);

        if (isTransferPayment)
        {
            HostTransferPaymentRow l_hostTransferPaymentRow = hostTransferPaymentRow;       
    
            //証券会社コード： 振替請求伝票キューテーブル.証券会社コード 
            String l_strInstatutionCode =
                l_hostTransferPaymentRow.getInstitutionCode();
            
            //部店コード： 振替請求伝票キューテーブル.部店コード 
            String l_strBranchCode =
                l_hostTransferPaymentRow.getBranchCode();
            
            //顧客コード： 振替請求伝票キューテーブル.顧客コード 
            String l_strAccountCode =
                l_hostTransferPaymentRow.getAccountCode();
            
            //銘柄コード： 振替請求伝票キューテーブル.銘柄コード 
            String l_strProductCode =
                l_hostTransferPaymentRow.getProductCode();
            
            //保管区分： 振替請求伝票キューテーブル.保管区分 
            String l_strCustodyDiv =
                l_hostTransferPaymentRow.getCustodyDiv();
            
            //特定口座区分： 振替請求伝票キューテーブル.特定口座区分 
            String l_strSpecificDiv = l_hostTransferPaymentRow.getSpecificDiv();
            
            //数量： 振替請求伝票キューテーブル.数量
            Long l_lngQuantity = 
                new Long(l_hostTransferPaymentRow.getQuantity());
                
            //商品区分： 振替請求伝票キューテーブル.商品区分
            String l_strCommodityDiv =
			    l_hostTransferPaymentRow.getCommodityDiv();
			
			//取消区分： null
			String l_strCancelDiv = null;
            
            //1.2.1） notify出庫通知
            String l_strNotifyOutputNotify = null;

            try
            {
                //出庫通知UnitServiceを取得する。
                WEB3AioOutputNotifyUnitService
                    l_aioOutputNotifyUnitService =
                        (WEB3AioOutputNotifyUnitService) Services.getService(
                            WEB3AioOutputNotifyUnitService.class);
                
                l_strNotifyOutputNotify = l_aioOutputNotifyUnitService.notifyOutputNotify(
                    l_strInstatutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strProductCode,
                    l_strCustodyDiv,
                    l_strSpecificDiv,
                    l_lngQuantity,
				    l_strCommodityDiv,
				    l_strCancelDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }        
             
            HashMap l_map = new HashMap();
            
            //1.2.2)（※２）キューテーブルのレコードの処理区分の更新
            if (WEB3HostTransferPaymentStatusDef.DEALT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEALT);               
            }
            else if(WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT);  
            }
            else if (WEB3HostTransferPaymentStatusDef.NO_ASSET.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.NO_ASSET);   
            } 
                               
			// 処理対象の振替請求伝票キューテーブル.処理区分を設定
			String l_strUpdateWhereA = "rowid = ? ";

			String[] l_objUpdateWhereValuesA = {l_hostTransferPaymentRow.getRowid()};      
    
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    
            //  1.2.2 キューテーブルのレコードの処理区分の更新
            l_queryProcessor.doUpdateAllQuery(
                HostTransferPaymentRow.TYPE,
                l_strUpdateWhereA.toString(),
                l_objUpdateWhereValuesA,
                l_map);
        }
        else
        {
            //証券会社コード： 証券出庫請求伝票キューテーブル.証券会社コード 
            String l_strInstatutionCode =
                hostSecDeliveryTransferRow.getInstitutionCode();
            
            //部店コード： 証券出庫請求伝票キューテーブル.部店コード 
            String l_strBranchCode =
                hostSecDeliveryTransferRow.getBranchCode();
            
            //顧客コード： 証券出庫請求伝票キューテーブル.顧客コード 
            String l_strAccountCode =
                hostSecDeliveryTransferRow.getAccountCode();
            
            //銘柄コード： 証券出庫請求伝票キューテーブル.銘柄コード 
            String l_strProductCode =
                hostSecDeliveryTransferRow.getProductCode();
            
            //保管区分： 証券出庫請求伝票キューテーブル.保管区分 
            String l_strCustodyDiv =
                hostSecDeliveryTransferRow.getCustodyDiv();
            
            //特定口座区分： 証券出庫請求伝票キューテーブル.特定口座区分 
            String l_strSpecificDiv = hostSecDeliveryTransferRow.getSpecificDiv();
            
            //数量： 証券出庫請求伝票キューテーブル.数量
            Long l_lngQuantity = 
                new Long(hostSecDeliveryTransferRow.getQuantity());
                
			//商品区分： 証券出庫請求伝票キューテーブル.商品区分
			String l_strCommodityDiv =
			    hostSecDeliveryTransferRow.getCommodityDiv();
			
			//取消区分： 証券出庫請求伝票キューテーブル.取消区分
			String l_strCancelDiv = 
			    hostSecDeliveryTransferRow.getCancelDiv();
            
            //1.4.1） notify出庫通知
            String l_strNotifyOutputNotify = null;

            try
            {
                //出庫通知UnitServiceを取得する。
                WEB3AioOutputNotifyUnitService
                    l_aioOutputNotifyUnitService =
                        (WEB3AioOutputNotifyUnitService) Services.getService(
                            WEB3AioOutputNotifyUnitService.class);
                
                l_strNotifyOutputNotify = l_aioOutputNotifyUnitService.notifyOutputNotify(
                    l_strInstatutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strProductCode,
                    l_strCustodyDiv,
                    l_strSpecificDiv,
                    l_lngQuantity,
				    l_strCommodityDiv,
				    l_strCancelDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("予期しないシステムエラーが発生しました。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }        
                
            HashMap l_map = new HashMap();
            
            //1.4.2)（※２）キューテーブルのレコードの処理区分の更新   
            if (WEB3HostTransferPaymentStatusDef.DEALT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEALT);               
            }
            else if(WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT);  
            }
            else if (WEB3HostTransferPaymentStatusDef.NO_ASSET.equals(l_strNotifyOutputNotify))
            {
                l_map.put("status", WEB3HostTransferPaymentStatusDef.NO_ASSET);   
            }
            
			// 処理対象の証券出庫請求伝票キューテーブル.処理区分を設定用
			String l_strUpdateWhereB = "rowid = ? ";

			String[] l_objUpdateWhereValuesB = {hostSecDeliveryTransferRow.getRowid()};
           
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //  1.4.2 キューテーブルのレコードの処理区分の更新
            l_queryProcessor.doUpdateAllQuery(
                HostSecDeliveryTransferRow.TYPE,
                l_strUpdateWhereB.toString(),
                l_objUpdateWhereValuesB,
                l_map);            
        }
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
}




@
