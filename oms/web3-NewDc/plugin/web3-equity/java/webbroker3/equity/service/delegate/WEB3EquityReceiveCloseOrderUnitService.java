head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveCloseOrderUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�ꌏ�T�[�r�X(WEB3EquityReceiveCloseOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 ���j (���u) �V�K�쐬
                   2004/09/20 羐� (���u) �C��
                   2004/12/10 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;

/**
 * �i���������ʒm�ꌏ�T�[�r�X�C���^�t�F�[�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j
 * @@version 1.0
 */
public interface WEB3EquityReceiveCloseOrderUnitService extends Service
{

    /**
     * (exec����)<BR>
     * ���������ꌏ�������s���B<BR>
     * @@param l_params - (���������ʒm�L���[Params)<BR>
     * �y���������ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4087A39F02E2
     */
    public void execCloseOrder(
        HostEqtypeCloseOrderNotifyParams l_params,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;

	/**
	 * (notify����)<BR>
	 * @@param l_params - (���������ʒm�L���[Params)<BR>
	 * �y���������ʒm�L���[�e�[�u���z�̂P���R�[�h�B<BR>
	 * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g)<BR>
	 * @@throws WEB3BaseException
	 */        
    public void notifyCloseOrder(
		HostEqtypeCloseOrderNotifyParams l_params,
		EqTypeOrderUnit l_orderUnit)
		throws WEB3BaseException;
}
@
