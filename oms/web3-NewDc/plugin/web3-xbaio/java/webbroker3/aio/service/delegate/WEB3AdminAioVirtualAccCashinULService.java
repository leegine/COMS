head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioVirtualAccCashinULService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL�T�[�r�X(WEB3AdminAioVirtualAccCashinULService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 �юu�� (���u) �V�K�쐬                   
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�o�[�`������������UL�T�[�r�X)<BR>
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */

public interface WEB3AdminAioVirtualAccCashinULService extends WEB3BusinessService 
{
    
    /**
     * �o�[�`�������������A�b�v���[�h�������s���B<BR>  
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̒ʂ胁�\�b�h���R�[������B<BR>  
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ޓ���ظ��Ă̏ꍇ<BR>  
     * �@@�|get�A�b�v���[�h���()���R�[������B  <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ފm�Fظ��Ă̏ꍇ<BR>  
     * �@@�|validate�A�b�v���[�h�t�@@�C��()���R�[������B  <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ފ���ظ��Ă̏ꍇ<BR>  
     * �@@�|submit�A�b�v���[�h�t�@@�C��()���R�[������B  <BR>
     * <BR>
     * �� �����̃��N�G�X�g�f�[�^���A�o�[�`�����������౯��۰�ޒ��~ظ��Ă̏ꍇ<BR>  
     * �@@�|undo�A�b�v���[�h()���R�[������B  <BR>
     * <BR>
     * ���T�[�r�X���\�b�h�ɂė�O�����������ꍇ�́A<BR>  
     * �@@��O�I�u�W�F�N�g�̒ǉ�������iWEB3BaseException.errorMessage�j<BR>  
     * �����X�|���X�f�[�^.errorMessage�ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
