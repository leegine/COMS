head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEqTypeSwitchUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文株式切替一件サービス(WEB3ToWLimitEqTypeSwitchUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/17 徐宏偉 (中訊) 新規作成 （モデル）No.176
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (W指値注文株式切替一件サービス)<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3ToWLimitEqTypeSwitchUnitService extends Service
{
    /**
     * W指値注文株式切替一件処理を行う。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * @@throws WEB3BaseException
     */
    public void submit(EqTypeOrderUnit l_eqTypeOrderUnit) throws WEB3BaseException;
}
@
