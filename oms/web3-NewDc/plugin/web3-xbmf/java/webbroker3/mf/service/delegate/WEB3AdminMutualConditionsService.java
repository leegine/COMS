head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��M���������o�^�T�[�r�X�@@�C���^�[�t�F�C�X(WEB3AdminMutualConditionsService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �����(���u) �V�K�쐬
                   2004/08/25 ��O�� (���u) ���r���[    
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * �Ǘ��ғ��M���������o�^�T�[�r�X�@@�C���^�[�t�F�C�X�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AdminMutualConditionsService extends WEB3BusinessService 
{
    
    /**
     * �Ǘ��ғ����M�� ���������o�^�����{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3C53601ED
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
