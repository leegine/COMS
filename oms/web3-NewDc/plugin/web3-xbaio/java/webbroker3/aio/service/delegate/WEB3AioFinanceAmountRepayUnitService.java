head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFinanceAmountRepayUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Z���z�ԍ�UnitService (WEB3AioFinanceAmountRepayUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ������ (���u) �V�K�쐬                                     
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.PayRequiredAmountParams;
import webbroker3.common.WEB3BaseException;

/**
 * (�Z���z�ԍ�UnitService)<BR>
 * �Z���z�ԍ�UnitService�C���^�[�t�F�C�X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3AioFinanceAmountRepayUnitService extends Service
{
    /**
     * �ԍϕK�v�z�f�[�^�X�V�������s���B<BR>
     * @@param l_payRequiredAmountParams - (�ԍϕK�v�z�f�[�^Params)<BR>
     * �ԍϕK�v�z�f�[�^�̍s�I�u�W�F�N�g <BR>
     * <BR>
     * ��DDL��莩������<BR>
     * @@throws WEB3BaseException
     */
    public void execute(PayRequiredAmountParams l_payRequiredAmountParams) throws WEB3BaseException;
}
@
