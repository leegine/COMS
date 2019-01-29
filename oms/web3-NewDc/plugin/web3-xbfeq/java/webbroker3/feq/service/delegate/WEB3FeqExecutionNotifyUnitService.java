head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���ʒm�P���T�[�r�X(WEB3AdminFeqExecutionOneNotifyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/12 ꎉ� (���u) �V�K�쐬 
                   2006/10/17 �����(���u) ���f��No.288�Ή�
                   2006/12/15 ꎉ� (���u) ���f��No.311�Ή�
                   2006/12/19 ꎉ� (���u) ���f��No.319�Ή�   
                   2006/12/20 ꎉ� (���u) ���f��No.323�Ή�
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.468               
Revesion History : 2010/03/05 ���g (���u)�y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.541
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.slebase.data.SleRcvdQParams;

/**
 * (�O�������o���ʒm�P���T�[�r�X)<BR>
 * �O�������o���ʒm�P���T�[�r�X�C���^�t�F�C�X <BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j<BR>
 *   
 * @@author ꎉ�
 * @@version 1.0
 */
public interface WEB3FeqExecutionNotifyUnitService extends Service
{

    /**
     * notify���<BR>
     * ��菈�����s���B<BR>
     * @@param l_feqOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[)<BR>
     * �O���o���ʒm�L���[<BR>
     * @@param l_sleRvcdQParams - (�O�������RCVD_Q)<BR>
     * �O�������RCVD_Q<BR>
     * @@param l_todayLoginFlag - (�����ב֓o�^�t���O)<BR>
     * �����ב֓o�^�t���O<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FECAF006D
     */
    public void notifyOrder(
    	WEB3FeqOrderUnit l_feqOrderUnit, 
    	HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams,
    	SleRcvdQParams l_sleRvcdQParams,
        Boolean l_todayLoginFlag)
        throws WEB3BaseException;
}
@
