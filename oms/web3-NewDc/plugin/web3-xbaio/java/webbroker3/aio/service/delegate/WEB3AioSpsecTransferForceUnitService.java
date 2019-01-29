head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSpsecTransferForceUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������U�֋���UnitService(WEB3AioSpsecTransferForceUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/06 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (��������U�֋���UnitService)<BR>
 * ��������U�֋���UnitService�C���^�[�t�F�C�X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public interface WEB3AioSpsecTransferForceUnitService extends Service 
{
    
    /**
     * (submit����)<BR>
     * ��������U�ւ̒�����o�^���A���̒����P�ʂ��擾����B
     * @@param l_params - ������������U�փL���[�e�[�u���̍s�I�u�W�F�N�g
     * @@return AioOrderUnit[]
     * @@roseuid 4157961901F5
     */
    public AioOrderUnit[] submitOrder(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException;
        
    /**
     * (submit���)<BR>
     * ��������U�ւ̒����̎�����s���B
     * @@param l_params - ������������U�փL���[�e�[�u���̍s�I�u�W�F�N�g
     * @@return AioOrderUnit[]
     * @@roseuid 4157961901F5
     */
    public void submitCancel(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException;
        
}
@
