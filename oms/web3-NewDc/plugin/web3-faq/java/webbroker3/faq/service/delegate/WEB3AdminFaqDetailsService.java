head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqDetailsService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ₢���킹�Ǘ����⍇���ڍ׃T�[�r�X(WEB3AdminFaqDetailsService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 ����� (���u) �V�K�쐬
*/

package webbroker3.faq.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��Җ₢���킹�Ǘ����⍇���ڍ׃T�[�r�X)<BR>
 * �Ǘ��Җ₢���킹�Ǘ����⍇���ڍ׃T�[�r�X�C���^�t�F�C�X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3AdminFaqDetailsService extends WEB3BusinessService 
{
    
    /**
     * �⍇���ڍו\�����������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41AC2E7F0352
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
