head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������o���ʒm�ꌏ�T�[�r�X�C���^�[�t�F�C�X(WEB3EquityOrderExecNotifyPartService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                   2004/12/02 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;

/**
 * �i���������o���ʒm�ꌏ�T�[�r�X�C���^�[�t�F�C�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j
 * @@version 1.0
 */
public interface WEB3EquityOrderExecNotifyUnitService extends Service
{
   /**
    * (notify���)<BR>
    * <BR>
    * ��菈�����s���B<BR>
    * <BR>
    * @@param l_orderUnit - �����P��
    * @@param l_hostEquityOrderExecNotifyParams - �����o���ʒm�L���[Params
    */
   public void notifyExecute(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
       throws WEB3BaseException;

    /**
     * (notify�����)<BR>
     * <BR>
     * ������������s���B<BR>
     * <BR>
     * @@param l_orderUnit - �����P��
     * @@param l_hostEquityOrderExecNotifyParams - �����o���ʒm�L���[Params
     */
    public void notifyExecuteCancel(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException;
}
@