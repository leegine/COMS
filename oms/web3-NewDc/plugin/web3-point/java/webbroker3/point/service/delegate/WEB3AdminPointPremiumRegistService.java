head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�o�^�T�[�r�X(WEB3AdminPointPremiumRegistService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�i�i�o�^�T�[�r�X)<BR>_
 * �i�i�o�^�T�[�r�X�C���^�[�t�F�C�X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public interface WEB3AdminPointPremiumRegistService extends WEB3BusinessService 
{
    
    /**
     * �i�i�o�^�T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4192E9A50175
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException ;
}
@
