head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�փT�[�r�X(WEB3AioSecurityTransferService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (�،��U�փT�[�r�X)<BR>
 * �،��U�փT�[�r�X�C���^�[�t�F�C�X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferService extends WEB3BusinessService 
{
    
    /**
     * �،��U�փT�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 4157798800F9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
