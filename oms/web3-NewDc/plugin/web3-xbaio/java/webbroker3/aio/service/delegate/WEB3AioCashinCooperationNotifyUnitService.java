head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinCooperationNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�ʒm�ꌏ�T�[�r�X(WEB3AioCashinCooperationNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 ����(���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate;

import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * �����A�g�ʒm�ꌏ�T�[�r�X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3AioCashinCooperationNotifyUnitService extends Service 
{
    /**
     * (notify�����A�g)<BR>
     * �����ʒm���P����������B<BR>
     * �����ʒm�̒����o�^����B<BR>
     * �V�[�P���X�} <BR>
     * �u(�����A�g�ʒm�ꌏ�T�[�r�XImpl).notify�����A�g�v �Q�� <BR>
     * <BR>
     * @@param l_bankDepositNotifyParams -  �����ʒmParams <BR>
     * @@param l_mainAccount -  �ڋq <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40BEF93003E7
     */
    public void notifyCashinCooperation(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
}
@
