head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDetailsService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[�����ڍ׃T�[�r�X(WEB3AdminMailInfoDetailsService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  鰊](���u) �V�K�쐬
*/
package webbroker3.mailinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (���[�����ڍ׃T�[�r�X)<BR>
 * ���[�����ڍ׃T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author 鰊]
 * @@version 1.0
 */
public interface WEB3AdminMailInfoDetailsService extends WEB3BusinessService 
{    
    /**
     * ���[�����ڍ׏Ɖ�����s���B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C4EF100C0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
