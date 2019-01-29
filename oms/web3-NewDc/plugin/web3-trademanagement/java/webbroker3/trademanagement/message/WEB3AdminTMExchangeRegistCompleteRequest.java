head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�ב֓o�^�������N�G�X�g(WEB3AdminTMExchangeRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�ב֓o�^�������N�G�X�g)<BR>
 * �Ǘ��ҁE�ב֓o�^�������N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_tm_exchange_regist_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200610101425L;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMExchangeRegistCompleteRequest.class);

    /**
     * (�ב֏��ꗗ)<BR>
     * �ב֏��̔z��<BR>
     * <BR>
     * �����͂��������ʉ݁E���[�g�ɂ��Ă̏��݂̂��Z�b�g�����B<BR>
     */
    public WEB3AdminTMExchangeInfoUnit[] exchangeInfoUnit;

    /**
     * �Ïؔԍ�
     */
    public String password;

    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTMExchangeRegistCompleteRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ב֏��ꗗ[] == null or �ב֏��ꗗ[].length == 0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_02195<BR>
     * <BR>
     * �Q�j�@@�ב֏��ꗗ[]�̊e�v�f���ɁA�ב֏��ꗗ.validate()���R�[������B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�ב֏��ꗗ[] == null or �ב֏��ꗗ[].length == 0�̏ꍇ�A��O���X���[����B
        if (this.exchangeInfoUnit == null || this.exchangeInfoUnit.length == 0)
        {
            log.debug("�ב֏��ꗗ[] == null or �ב֏��ꗗ[].length == 0�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02195,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        // �Q�j�@@�ב֏��ꗗ[]�̊e�v�f���ɁA�ב֏��ꗗ.validate()���R�[������B
        int l_intLength = this.exchangeInfoUnit.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("l_intLength = " + l_intLength);
            this.exchangeInfoUnit[i].validate();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMExchangeRegistCompleteResponse(this);
    }
}
@
