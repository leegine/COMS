head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.59.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointCommissionInfoReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����萔���������Ɖ�T�[�r�X(WEB3PointCommissionInfoReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�����萔���������Ɖ�T�[�r�X)<BR>
 * �����萔���������Ɖ�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author ���w��
 * @@version 1.0
 */
public interface WEB3PointCommissionInfoReferenceService extends WEB3BusinessService
{
    
    /**
     * �����萔���������Ɖ�T�[�r�X���s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4207166B0126
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
