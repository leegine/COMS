head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.20.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccountBaseInfoInquiryService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X(WEB3AdminAccInfoAccountBaseInfoInquiryService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X)<BR>
 * �Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3AdminAccInfoAccountBaseInfoInquiryService extends WEB3BusinessService 
{
    
    /**
     * �ڋq��{���⍇���������s���B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B2B200BE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@