head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransForeignUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o���i�O�݁jUnitService(WEB3AioSonarCashTransForeignUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.common.WEB3BaseException;

/**
 * ( SONAR���o���i�O�݁jUnitService)<BR>
 * SONAR���o���i�O�݁jUnitService�C���^�[�t�F�C�X<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public interface WEB3AioSonarCashTransForeignUnitService extends Service
{
    /**
     * (submit����)<BR>
     * SONAR����̒����̓o�^���s���A�V�K�����̒���ID��ԋp����B<BR>
     * @@param l_hostForeignCashTransferParams - (�O�ݓ��o��Params�I�u�W�F�N�g)<BR>
     * �O�ݓ��o��Params�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@return long
     */
    public long submitOrder(HostForeignCashTransferParams l_hostForeignCashTransferParams)
        throws WEB3BaseException;
}
@
