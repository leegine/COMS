head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingContentsConfirmService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݒ���e�m�F�T�[�r�X(WEB3ToSuccSettingContentsConfirmService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/1�V ���r(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�ݒ���e�m�F�T�[�r�X)<BR>
 * �ݒ���e�m�F�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author ���r <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToSuccSettingContentsConfirmService extends WEB3BusinessService 
{
    
    /**
     * �ݒ���e�m�F�T�[�r�X�������s���B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D18AA026C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
