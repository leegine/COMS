head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���ݒ�ꗗ�T�[�r�X(WEB3ToSuccSettingListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�A���ݒ�ꗗ�T�[�r�X)<BR>
 * �A���ݒ�ꗗ�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3ToSuccSettingListService extends WEB3BusinessService 
{
    
    /**
     * �A���ݒ�ꗗ�T�[�r�X�������s���B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431CFEFC01A9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
