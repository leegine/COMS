head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.59.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageByCustomerService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�ʃ|�C���g�Ǘ��T�[�r�X(WEB3AdminPointManageByCustomerService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�ڋq�ʃ|�C���g�Ǘ��T�[�r�X)<BR>
 * �ڋq�ʃ|�C���g�Ǘ��T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3AdminPointManageByCustomerService extends WEB3BusinessService 
{
    
    /**
     * �ڋq�ʃ|�C���g�Ǘ��T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41944E9D0119
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
