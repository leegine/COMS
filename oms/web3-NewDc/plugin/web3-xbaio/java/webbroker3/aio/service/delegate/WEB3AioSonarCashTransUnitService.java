head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���o��UnitService(WEB3AioSonarCashTransUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/26 ���z (���u) ���r���[
*/


package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.common.WEB3BaseException;

/**
 * (SONAR���o��UnitService)<BR>
 * SONAR���o��UnitService�C���^�[�t�F�C�X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public interface WEB3AioSonarCashTransUnitService extends Service 
{
    
    /**
     * (submit����)<BR>
     * SONAR����̒����̓o�^���s���A�V�K�����̒���ID��ԋp����B<BR>
     * @@param l_hostCashTransferParams - (���o��Params)<BR>
     * ���o��Params�I�u�W�F�N�g<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return long
     * @@roseuid 41009B090109
     */
    public long submitOrder(HostCashTransferParams l_hostCashTransferParams)
        throws WEB3BaseException;
}
@
