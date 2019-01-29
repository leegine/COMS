head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文通知UnitService(WEB3FuturesOrderNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

/**
 * (先物注文通知UnitService)<BR>
 * 株価指数先物注文通知UnitServiceインタフェイス<BR>
 * @@author  : 凌建平
 * @@version : 1.0
 */
public interface WEB3FuturesOrderNotifyUnitService extends Service 
{
   
   /**
    * (notify新規建注文)<BR>
    * 新規建注文通知処理を実施する。 <BR>
    * @@param l_hostFotypeOrderReceiptParams - (先物OP注文通知キューParams)<BR>
    * 【先物OP注文通知キューテーブル】の1-Rowを表現するクラス<BR>
    * @@param l_subAccount - 補助口座オブジェクト
    * @@roseuid 4174F78701F0
    */
   public void notifyOpenContractOrder(HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, SubAccount l_subAccount) throws WEB3BaseException;
   
   /**
    * (notify返済注文)<BR>
    * 返済注文通知処理を実施する。 <BR>
    * @@param l_hostFotypeOrderReceiptParams - (先物OP注文通知キューParams)<BR>
    * 【先物OP注文通知キューテーブル】の1-Rowを表現するクラス<BR>
    * @@param l_subAccount - 補助口座オブジェクト<BR>
    * @@roseuid 4174F787020F
    */
   public void notifySettleContractOrder(HostFotypeOrderReceiptParams l_hostFotypeOrderReceiptParams, SubAccount l_subAccount) throws WEB3BaseException;
}
@
