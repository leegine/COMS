head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.59.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X(WEB3AdminPointTradeBonusPlanReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 �s�p(���u) �V�K�쐬
*/


package webbroker3.point.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X)<BR>
 * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�C���^�[�t�F�C�X<BR>
 */
public interface WEB3AdminPointTradeBonusPlanReferenceService extends WEB3BusinessService
{
    
    /**
     * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X���������{����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A4FED8004C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
