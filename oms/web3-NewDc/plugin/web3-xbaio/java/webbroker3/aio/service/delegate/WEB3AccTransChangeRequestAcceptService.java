head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeRequestAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@
/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �U�֐�����t�T�[�r�X(WEB3AccTransChangeRequestAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ������ (���u) �V�K�쐬
                   2004/10/22 �����(���u) ���r���[                                       
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�U�֐�����t�T�[�r�X)<BR>
 * �U�֐�����t�T�[�r�X �C���^�[�t�F�C�X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3AccTransChangeRequestAcceptService
    extends WEB3BackBusinessService
{

    /**
     * �U�֐�����t�T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1B15035C
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
