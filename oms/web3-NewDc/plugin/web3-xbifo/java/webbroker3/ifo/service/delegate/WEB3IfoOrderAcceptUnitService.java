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
filename	WEB3IfoOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付UnitServiceインターフェイス(WEB3IfoOrderAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/8 艾興 (中訊) 新規作成  
Revesion History : 2007/01/25 周捷 (中訊) 仕様変更 モデル605
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.ifo.data.HostFotypeOrderAcceptParams;

/**
 * (先物OP注文受付UnitService)<BR>
 * 先物OP注文受付UnitServiceインターフェイス<BR>
 */
public interface WEB3IfoOrderAcceptUnitService extends Service 
{
   
   /**
    * (notify注文受付)<BR>
    * 注文受付処理を行う。<BR>
    * 
    * @@param l_hostFotypeOrderAcceptParams - 注文受付キューParamsオブジェクト
    * @@roseuid 4190C3F400C3
    */
   public void notifyOrderAccept(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
    throws WEB3BaseException;

   /**
    * (notify受付時間外)<BR>
    * 受付時間外処理を行う。<BR>
    *
    * @@param l_hostFotypeOrderAcceptParams - (注文受付キューParams)<BR>
    * 注文受付キューParamsオブジェクト
    */
   public void notifyOrderAcceptOvertime(HostFotypeOrderAcceptParams l_hostFotypeOrderAcceptParams)
   throws WEB3BaseException;
}
@
