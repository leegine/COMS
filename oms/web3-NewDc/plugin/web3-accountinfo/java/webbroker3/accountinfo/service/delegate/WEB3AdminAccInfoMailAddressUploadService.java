head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�X(WEB3AdminAccInfoMailAddressUploadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�X)<BR>
 *  �Ǘ��҂��q�l��񃁁[���A�h���X�A�b�v���[�h�T�[�r�X<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public interface WEB3AdminAccInfoMailAddressUploadService extends WEB3BusinessService 
{
    /**
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException; 
}
@
