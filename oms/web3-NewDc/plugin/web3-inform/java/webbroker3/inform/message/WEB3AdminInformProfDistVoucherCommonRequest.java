head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����`�[�쐬���ʃ��N�G�X�g(WEB3AdminInformProfDistVoucherCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.054
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����`�[�쐬���ʃ��N�G�X�g)<BR>
 * �����E���z���`�[�쐬���ʃ��N�G�X�g�N���X
 */
public class WEB3AdminInformProfDistVoucherCommonRequest extends WEB3GenRequest
{

    /**
     * (�A�����)<BR>
     * �A�����
     */
    public WEB3InformDetailInfoUnit informInfoUnit;

    /**
     * @@roseuid 4663A9D70129
     */
    public WEB3AdminInformProfDistVoucherCommonRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
