head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����o���ʒm�ꌏ�T�[�r�X�C���^�t�F�[�X(WEB3MarginOrderExecNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ������ (���u) �V�K�쐬
                   2004/12/03 SRA���� call�����ʒm����()�폜
                   2005/01/05 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.equity.data.HostEquityOrderExecNotifyParams;

import webbroker3.common.WEB3BaseException;

/**
 * �i�M�p����o���ʒm�ꌏ�T�[�r�X�C���^�t�F�[�X�j�B<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3MarginOrderExecNotifyUnitService extends Service 
{
    
    /**
     * (notify���)<BR>
     * ��菈�������s����B
     * @@param l_ordeUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B
     * @@param l_equityExecNotifyQueueParams - �����o���ʒm�L���[Params�I�u�W�F�N�g�B
     * @@roseuid 40CFB831029F
     */
    public void notifyExecute(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException;
    
    /**
     * (notify�����)<BR>
     * ��������������s����B
     * @@param l_ordeUnit - (�����P��)<BR>
     * �o���ʒm�L���[.���ʃR�[�h�ɊY������A�����P�ʃI�u�W�F�N�g�B
     * @@param l_equityExecNotifyQueueParams - �����o���ʒm�L���[Params�I�u�W�F�N�g�B
     * @@roseuid 40CFB83102A2
     */
    public void notifyExecuteCancel(EqTypeOrderUnit l_ordeUnit, HostEquityOrderExecNotifyParams l_equityExecNotifyQueueParams)
        throws WEB3BaseException;
    
}
@
