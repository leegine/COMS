head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountListInquiryService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽(WEB3AdminAccInfoCommissionRegistAccountListInquiryService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽�C���^�t�F�C�X<BR>
 * @@author �d��
 * @@version 1.0
 */
public interface WEB3AdminAccInfoCommissionRegistAccountListInquiryService extends WEB3BusinessService 
{
    
    /**
     * �萔���ύX�\���ڋq�ꗗ�⍇���������s���B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41512F8D02DB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@