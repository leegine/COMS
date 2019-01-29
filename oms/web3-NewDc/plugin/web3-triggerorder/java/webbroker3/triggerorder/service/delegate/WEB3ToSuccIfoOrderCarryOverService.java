head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.03.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderCarryOverService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�A�������J�z�T�[�r�X�iWEB3ToSuccIfoOrderCarryOverService.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���� (���u) �V�K�쐬 ���f��No.276
*/
package webbroker3.triggerorder.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (�敨OP�A�������J�z�T�[�r�X)<BR>
 * �敨OP�A�������J�z�T�[�r�X�C���^�[�t�F�C�X<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j
 * @@author ����
 * @@version 1.0
 */
public interface WEB3ToSuccIfoOrderCarryOverService extends Service
{

    /**
     * (exec�A�������J�z)<BR>
     * �A�������̌J�z�������s���B<BR>
     * @@param l_carryOverOriginalParentOrderUnit - (�J�z���̐e�����̒����P��)<BR>
     * �J�z���̐e�����̒����P��<BR>
     * @@param l_carryOverAfterParentOrderUnit - (�J�z��̐e�����̒����P��)<BR>
     * �J�z��̐e�����̒����P��<BR>
     * @@param l_lisRsvIfoOrderUnits - (�\�񒍕��P�ʈꗗ)<BR>
     * �\�񒍕��P�ʈꗗ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47D8760A011C
     */
    public void execToSuccOrderCarryOver(
        IfoOrderUnit l_carryOverOriginalParentOrderUnit,
        IfoOrderUnit l_carryOverAfterParentOrderUnit,
        List l_lisRsvIfoOrderUnits) throws WEB3BaseException;
}
@
