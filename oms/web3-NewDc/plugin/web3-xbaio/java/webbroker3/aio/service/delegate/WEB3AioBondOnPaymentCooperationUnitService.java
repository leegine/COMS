head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioBondOnPaymentCooperationUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���A�gUnitService(WEB3AioBondOnPaymentCooperationUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.WEB3AioBondOnPaymentInfo;
import webbroker3.common.WEB3BaseException;

/**
 * (���o���A�gUnitService)<BR>
 * ���o���A�gUnitService�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public interface WEB3AioBondOnPaymentCooperationUnitService extends Service
{
    /**
     * (submit����)<BR>
     * ������̒����̓o�^���s���B<BR>
     * @@param l_bondCashOutInfo - (���o�����)<BR>
     * ���o�����I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public void submitOrder(WEB3AioBondOnPaymentInfo l_bondCashOutInfo)
        throws WEB3BaseException;
}
@
