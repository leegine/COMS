head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAndExecutionUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���^���X�V�T�[�r�X(WEB3FeqOrderAndExecutionUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 ������(���u) �V�K�쐬
                 : 2005/07/26 ���U(���u) ���r���[
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;


/**
 * (�O�������o���^���X�V�T�[�r�X) <BR>
 * �O�������o���^���X�V�T�[�r�X�C���^�t�F�C�X <BR>
 * TransactionalInterceptor.TX_JOIN_EXISTING <BR>
 * 
 * @@ author ������ 
 * @@ version 1.0
 */
public interface WEB3FeqOrderAndExecutionUpdateService extends Service
{
    
    /**
     * (update�����) <BR>
     * �o���^���X�V�������s�� <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�����X�V�jupdate�����v�Q�ƁB <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[�s) <BR>
     * �O���o���ʒm�L���[�s�I�u�W�F�N�g <BR>
     *  <BR>
     * ���O���o���ʒm�L���[Params��DDL��莩����������B <BR>
     * @@throws WEB3BaseException
     * @@roseuid 428C039102C5
     */
    public void updateExecuteUnit(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) 
        throws WEB3BaseException;
    
    /**
     * (update�����) <BR>
     * �o���^������X�V�������s�� <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�����X�V�jupdate������v�Q�ƁB <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (�O���o���ʒm�L���[�s) <BR>
     * �O���o���ʒm�L���[�s�I�u�W�F�N�g <BR>
     *  <BR>
     * ���O���o���ʒm�L���[Params��DDL��莩����������B <BR>
     * 
     * @@param l_lngExecuteId - (���h�c)
     * @@throws WEB3BaseException
     * @@roseuid 428C039102D5
     */
    public void updateExecuteCancel(
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams, 
        long l_lngExecuteId)throws WEB3BaseException;
}
@
