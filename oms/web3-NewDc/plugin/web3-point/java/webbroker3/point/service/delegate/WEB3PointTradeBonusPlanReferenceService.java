head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.59.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X(WEB3PointTradeBonusPlanReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X))<BR>
 * �g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3PointTradeBonusPlanReferenceService extends WEB3BusinessService
{
    
    /**
     * �g���[�h�{�[�i�X�v�����Ɖ�T�[�r�X���s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 42071E2601F1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@