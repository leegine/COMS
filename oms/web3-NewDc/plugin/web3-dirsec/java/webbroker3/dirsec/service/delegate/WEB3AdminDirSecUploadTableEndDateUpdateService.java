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
filename	WEB3AdminDirSecUploadTableEndDateUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�X(WEB3AdminDirSecUploadTableEndDateUpdateService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/11 � �� (���u) �V�K�쐬                   
*/

package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�X)<BR>
 * �Ǘ��҃A�b�v���[�h�e�[�u���I�������X�V�T�[�r�X�C���^�[�t�F�C�X�B
 * 
 * @@author � ��(���u)
 * @@version 1.0
 */

public interface WEB3AdminDirSecUploadTableEndDateUpdateService extends WEB3BusinessService 
{
    /**
     * �Ǘ��ҁE�A�b�v���[�h�e�[�u���I�������X�V�������J�n����B<BR>  
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
