head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeRequestNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐����ʒmUnitService(WEB3AccTransChangeRequestNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/26 ���E(���u) ���r���[
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (�U�֐����ʒmUnitService)<BR>
 * �U�֐����ʒmUnitService�C���^�[�t�F�C�X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3AccTransChangeRequestNotifyUnitService extends Service
{

    /**
     * (submit����)<BR>
     * SONAR����̐U�֒����̓o�^���s���A�V�K�����̒���ID��ԋp����B<BR>
     * @@param l_hostTransferReceiptParams - (�U�֓��͒ʒm�L���[Params�I�u�W�F�N�g)<BR>
     * @@param l_orderType - (�������)
     * @@param l_changeType - (�U�փ^�C�v)
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 413C2CC401E5
     */
    public long submitOrder(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
            throws WEB3BaseException;
}
@
