head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݊������[�����MUnitService(WEB3AdminAccOpenCompleteMailSendUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;


/**
 * (�����J�݊������[�����MUnitService)<BR>
 * �����J�݊������[�����MUnitService�C���^�t�F�C�X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public interface WEB3AdminAccOpenCompleteMailSendUnitService extends Service 
{
    
    /**
     * (sendMailProcess)<BR>
     * �w��ڋq�Ɍ����J�݊������[���𑗐M����B<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@throws WEB3BaseException
     * @@roseuid 41A56076008D
     */
    public void sendMailProcess(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
}
@
