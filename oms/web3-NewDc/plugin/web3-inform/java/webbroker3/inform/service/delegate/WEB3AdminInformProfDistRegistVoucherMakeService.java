head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistRegistVoucherMakeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���o�^�`�[�쐬�T�[�r�X(WEB3AdminInformProfDistRegistVoucherMakeService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.056�ANo.069
*/

package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (�����E���z���o�^�`�[�쐬�T�[�r�X)<BR>
 * �����E���z���o�^�`�[�쐬�T�[�r�X�C���^�t�F�C�X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public interface WEB3AdminInformProfDistRegistVoucherMakeService extends WEB3BusinessService
{
    /**
     * �����E���z���o�^�`�[�쐬�T�[�r�X�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 4642664800ED
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
