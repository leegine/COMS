head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�����T�[�r�X�C���^�t�F�C�X(WEB3MutualBuyService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���E (���u) �V�K�쐬
                   2004/08/23 ���� (���u) ���r���[    
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 *�����M�����t�����T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public interface WEB3MutualBuyService extends WEB3BusinessService
{
    /**
     * �����M�����t�����T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40554EC7004D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
