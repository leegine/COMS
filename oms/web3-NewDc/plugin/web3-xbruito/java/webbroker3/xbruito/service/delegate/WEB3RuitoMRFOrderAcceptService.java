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
filename	WEB3RuitoMRFOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ���MRF������t�T�[�r�X�C���^�[�t�F�C�X(WEB3RuitoMRFOrderAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate;

import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;

/**
 * �ݐϓ���MRF������t�T�[�r�X�C���^�[�t�F�C�X<BR>
 */
public interface WEB3RuitoMRFOrderAcceptService extends WEB3BackBusinessService
{

    /**
     * �ݐϓ���MRF������t�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405814510021
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
