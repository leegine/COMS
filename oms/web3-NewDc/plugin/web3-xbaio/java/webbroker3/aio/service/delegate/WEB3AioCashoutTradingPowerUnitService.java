head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutTradingPowerUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�NUnitService(WEB3AioCashoutTradingPowerUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 ���� (���u) �V�K�쐬
                   2004/10/25 ���E (���u) ���r���[
                   2006/02/21 ��O�� (���u) �d�l�ύX�E���f��No.498
                   2006/08/28 �Ԑi (���u) �d�l�ύX�E���f��No.630�A635�A645
                   2006/11/15 ���G�� (���u)�d�l�ύX�E���f��No.684
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.common.WEB3BaseException;


/**
 * (�o���]�̓`�F�b�NUnitService)<BR>
 * �o���]�̓`�F�b�NUnitService�C���^�[�t�F�C�X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public interface WEB3AioCashoutTradingPowerUnitService extends Service
{
    
    /**
     * �����̗]�̓`�F�b�N�������s���B
     * @@param l_hostPaymentOrderParams - (�o�����������L���[�̍s�I�u�W�F�N�g)
     * @@param l_strProcessFlag - (�����t���O)
     * @@param l_bolTriggerIssueFlag - (�g���K���s�t���O)
     * @@param l_strDbCurrentPriceCheckDiv - (DB�����]�̓`�F�b�N�敪)
     * @@throws WEB3BaseException
     */
    public void execute(
        HostPaymentOrderParams l_hostPaymentOrderParams,
        String l_strProcessFlag,
        boolean l_bolTriggerIssueFlag,
        String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException;
}
@
