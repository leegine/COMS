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
filename	WEB3AdminFPTUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X(WEB3AdminFPTUploadService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g(���u) �V�K�쐬 ���f�� No.012
*/

package webbroker3.docadmin.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X)<BR>
 * �Ǘ��ҋ����@@��t�{���A�b�v���[�h�T�[�r�X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public interface WEB3AdminFPTUploadService extends WEB3BusinessService
{

    /**
     * �����@@��t�{���A�b�v���[�h�������s���B<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4737F204017F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
