head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽�C���^�t�F�C�X<BR>
 * @@author ���]��
 * @@version 1.0
 */
public interface WEB3AdminAccInfoMailAddressChangeAccountDownloadService extends WEB3BusinessService 
{
    
    /**
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h���������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F2B100D6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
