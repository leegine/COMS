head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �g���K�[���s�����T�[�r�X(WEB3AdminDirSecTriggerIssueService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/17 �đo�g(���u) �V�K�쐬 ���f��No.116�ANo.118
*/

package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�g���K�[���s�����T�[�r�X)<BR>
 * �g���K�[���s�����T�[�r�X�N���X�B<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public interface WEB3AdminDirSecTriggerIssueService extends WEB3BusinessService
{

    /**
     * �Ǘ��ҁE�g���K�[���s�������J�n����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47E0D707036C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
