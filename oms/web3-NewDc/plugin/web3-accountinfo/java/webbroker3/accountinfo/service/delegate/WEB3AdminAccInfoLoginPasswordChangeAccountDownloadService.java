head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽)<BR>
 * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰�޻��޽�C���^�t�F�C�X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService extends WEB3BusinessService 
{
    
    /**
     * �p�X���[�h�ύX�ڋq�_�E�����[�h���������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146D6630283
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
