head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegiService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X�C���^�[�t�F�C�X(WEB3AdminSrvRegiServiceRegiService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X�C���^�[�t�F�C�X)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�T�[�r�X�C���^�[�t�F�C�X�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public interface WEB3AdminSrvRegiServiceRegiService extends WEB3BusinessService 
{
    
    /**
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�o�^�������s���B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5112402A8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
