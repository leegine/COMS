head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeClosingContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����ԍϓ��̓T�[�r�X(WEB3OptionChangeClosingContractInputService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (OP�����ԍϓ��̓T�[�r�X)<BR>
 * �����w���I�v�V���������ԍϓ��̓T�[�r�X�C���^�t�F�[�X<BR>
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3OptionChangeClosingContractInputService extends WEB3BusinessService 
{
    
    /**
     * �����w���I�v�V���������ԍϓ��̓T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A479F036A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
