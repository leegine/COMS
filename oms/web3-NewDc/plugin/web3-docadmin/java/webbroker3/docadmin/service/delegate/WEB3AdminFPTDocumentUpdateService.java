head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTDocumentUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X(WEB3AdminFPTDocumentUpdateService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.039
*/
package webbroker3.docadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X)<BR>
 * �Ǘ��ҋ����@@��t���ʍX�V�T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public interface WEB3AdminFPTDocumentUpdateService extends WEB3BusinessService
{

    /**
     * (execute)<BR>
     * �����@@��t���ʍX�V���������{����B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C25F010373
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
