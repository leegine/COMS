head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO��������������۰�޴װ�����T�[�r�X(WEB3AdminFeqUploadErrCancelService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �s�p (���u) �V�K�쐬
                 : 2005/08/01 ��O��(���u) ���r���[   
*/

package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X)<BR>
 * �Ǘ��ҊO��������������۰�޴װ�����T�[�r�X�C���^�t�F�C�X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public interface WEB3AdminFeqUploadErrCancelService extends WEB3BusinessService 
{
    
    /**
     * �O�����������A�b�v���[�h�G���[�������������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get���͉��()<BR>
     * �|validate����()<BR>
     * �|submit����()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB733000E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
