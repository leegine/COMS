head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashOutOrderTriggerIssueUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�������g���K�[���sUnitService(WEB3AioCashOutOrderTriggerIssueUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/09 �����q (���u) �V�K�쐬 �d�l�ύX���f��No.720
Revision History : 2007/04/10 �����q (���u) �d�l�ύX���f��No.721
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;

/**
 * (�o�������g���K�[���sUnitService)<BR>
 * �o�������g���K�[���sUnitService�C���^�[�t�F�C�X<BR>
 * <BR>
 * @@author �����q(���u)
 * @@version 1.0
 */
public interface WEB3AioCashOutOrderTriggerIssueUnitService extends Service
{
    /**
     * �o�������g���K�[���s�������s���B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@throws WEB3BaseException
     */
    public void execute(String l_strInstitutionCode) throws WEB3BaseException;
}
@
