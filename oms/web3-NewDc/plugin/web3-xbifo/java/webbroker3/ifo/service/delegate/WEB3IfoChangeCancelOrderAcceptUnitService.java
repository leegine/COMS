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
filename	WEB3IfoChangeCancelOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP訂正取消受付UnitServiceインターフェイス(WEB3IfoChangeCancelOrderAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 艾興 (中訊) 新規作成
Revesion History : 2007/01/29 齊珂 (中訊) モデルNo.609
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;

/**
 * (先物OP訂正取消受付UnitService)<BR>
 * 先物OP訂正取消受付UnitServiceインターフェイス<BR>
 */
public interface WEB3IfoChangeCancelOrderAcceptUnitService extends Service 
{
   
   /**
    * (notify訂正取消受付)<BR>
    * 訂正取消受付処理を行う。<BR>
    * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
    * @@roseuid 4190CA620314
    */
   public void notifyChangeCancelOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams) 
    throws WEB3BaseException;
   
   /**
    * (notify訂正取消受付時間外)<BR>
    * 訂正取消受付時間外処理を行う。<BR>
    * @@param l_hostFotypeOrderAcceptParams - (注文受付キューParams)<BR>
    * 注文受付キューParamsオブジェクト
    */
   public void notifyChangeCancelOrderAcceptOvertime(
       HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
       throws WEB3BaseException;
}
@
