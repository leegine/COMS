head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeOpenContractService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����V�K���T�[�r�X(WEB3OptionChangeOpenContractService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (OP�����V�K���T�[�r�X) <BR>
 * <BR>
 * �����w���I�v�V���������V�K���T�[�r�X�C���^�t�F�[�X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public interface WEB3OptionChangeOpenContractService extends WEB3BusinessService 
{
    
    /**
     * �����w���I�v�V���������V�K���T�[�r�X���������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BA4F026E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
