head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptResultUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X(WEB3AdminFeqOrderAcceptResultUploadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �A�C��(���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X)<BR>
 * �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X�C���^�t�F�C�X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3AdminFeqOrderAcceptResultUploadService extends WEB3BusinessService 
{
    
    /**
     * �O������������t���ʃA�b�v���[�h���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get�A�b�v���[�h���()<BR>
     * �|validate�A�b�v���[�h�t�@@�C��()<BR>
     * �|submit�A�b�v���[�h�t�@@�C��()<BR>
     * �|undo�A�b�v���[�h�t�@@�C��()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FD3B80187
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
