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
filename	WEB3AdminDirSecHostTableStatusUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X(WEB3AdminDirSecHostTableStatusUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 ����(���u) �V�K�쐬
*/

package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X)<BR>
 * �Ǘ��҃L���[�e�[�u���X�e�[�^�X�X�V�T�[�r�X�C���^�t�F�C�X�B<BR>
 * 
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminDirSecHostTableStatusUpdateService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ��ҁE�L���[�e�[�u���X�e�[�^�X�X�V�������J�n����B<BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4417DB710018
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
