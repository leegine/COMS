head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݓ`�[�o�^��t�T�[�r�X(WEB3AccOpenVoucherRegAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (�����J�ݓ`�[�o�^��t�T�[�r�X)<BR>
 * �����J�ݓ`�[�o�^��t�T�[�r�X�C���^�t�F�C�X<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public interface WEB3AccOpenVoucherRegAcceptService extends WEB3BackBusinessService 
{
    
    /**
     * �����J�ݓ`�[�o�^��t���������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A44DA8024A
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
