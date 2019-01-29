head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z�ʒm���N�G�X�g(WEB3TPReCalcNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͌v�Z�ʒm���N�G�X�g)
 */
public class WEB3TPReCalcNotifyRequest extends WEB3BackRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPReCalcNotifyRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503241100L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tp_recalc_notify";

    /**
     * �X���b�hNo<BR>
     */
    public Long threadNo;

    /**
     * (From����ID)
     */
    public Long fromAccountId;

    /**
     * (To����ID)
     */
    public Long toAccountId;

    /**
     * @@roseuid 423541380308
     */
    public WEB3TPReCalcNotifyRequest()
    {

    }

    /**
     * (validate)
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@this.�X���b�h�m����null�܂��́A�e�����������h�c=null�܂��́ATo�����h�c=null�̏ꍇ�A<BR>
     * ��O��throw����B<BR>
     * <BR>
     * @@roseuid 41F5D1E00017
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (fromAccountId == null || toAccountId == null || threadNo == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 423541380346
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3TPReCalcNotifyResponse(this);
    }
}
@
