head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinSelectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �I�����C�������I���T�[�r�X�C���^�[�t�F�C�X(WEB3AioCashinSelectService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�I�����C�������I���T�[�r�X)<BR>
 * �I�����C�������I���T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3AioCashinSelectService extends WEB3BusinessService 
{
    
    /**
     * �I�����C�������I���T�[�r�X���������{����B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40FE64A103D0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
