head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferForceUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֋���UnitService(WEB3AioSecurityTransferForceUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (�،��U�֋���UnitService)<BR>
 * �،��U�֋���UnitService�C���^�[�t�F�C�X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferForceUnitService extends Service 
{
    
    /**
     * (submit����)<BR>
     * �،��U�ւ̒�����o�^���A�z����擾����B
     * @@param l_params - ��p�U�֋����L���[�e�[�u���̍s�I�u�W�F�N�g
     * @@return AioOrderUnit[]
     * @@roseuid 4157961901F5
     */
    public AioOrderUnit[] submitOrder(HostMrgsecTransNotifyParams l_params) 
        throws WEB3BaseException;
}
@
