head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeCompleteUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替完了UnitService(WEB3AccTransChangeCompleteUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/22 王蘭芬(中訊) レビュー                                       
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (振替完了UnitService)<BR>
 * 振替完了UnitServiceインターフェイス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public interface WEB3AccTransChangeCompleteUnitService extends Service
{

    /**
     * (complete振替)
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@throws WEB3BaseException
     * @@roseuid 413C22410253
     */
    public void completeChange(AioOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@
