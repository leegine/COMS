head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm�ꌏ�T�[�r�X(WEB3TPReCalcNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ResourceBusyException;

import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;

/**
 * (�]�͌v�Z�ʒm�ꌏ�T�[�r�X)
 */
public interface WEB3TPReCalcNotifyUnitService extends Service
{

    /**
     * (notify�]�͍Čv�Z)
     * @@param l_subAccount - (�⏕����)
     * @@param �]�͍Čv�Z�L���[Params - (�]�͍Čv�Z�L���[Params)
     * @@roseuid 41F456590372
     */
    public void notifyReCalc(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultExecNotifyParams l_params)
        throws ResourceBusyException, DataQueryException, DataNetworkException;
}
@
