head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecuteUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券自動約定Unitサービス(WEB3BondAutoExecuteUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 徐大方 (中訊) 新規作成
*/

package webbroker3.bd.service.delegate;


import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (債券自動約定Unitサービス)<BR>
 * 債券自動約定Unitサービスインターフェース
 * 
 * @@author 徐大方
 * @@version 1.0
 */

public interface WEB3BondAutoExecuteUnitService extends Service
{
    
    /**
     * (notify自動約定)<BR>
     * 自動約定処理をする <BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D7227703CC
     */
    public void notifyAutoExecute(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException;
}
@
