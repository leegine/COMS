head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��T�[�r�X�C���^�[�t�F�C�X(WEB3AdminInformReferenceService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
*/

package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�A����񌟍��T�[�r�X)<BR>
 * �A����񌟍��T�[�r�X�C���^�[�t�F�C�X<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3AdminInformReferenceService extends WEB3BusinessService 
{
    /**
     * �A����񌟍��T�[�r�X�������s���B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41BD7F5C014F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
