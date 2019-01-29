head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g(WEB3SuccFuturesCloseInputRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g)<BR>
 * �i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseInputRequest extends WEB3FuturesCloseMarginInputRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    public static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseInputRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121826L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_input";

    /**
     * (�A���������ʏ��)<BR>
     * �A���������ʏ��B<BR>
     */
    public WEB3SuccCommonInfo succCommonInfo;

    /**
     * @@roseuid 47D659380232
     */
    public WEB3SuccFuturesCloseInputRequest()
    {

    }
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesCloseInputResponse(this);
    }
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�A���������ʏ��`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02251 <BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B<BR>
     * <BR>
     * �@@�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"�敨�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�@@"�敨�ԍρi�����c�j"<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02252 <BR>
     * <BR>
     * �@@�@@�@@�@@�@@���R�[�h�l�́A�敨�n�o�\�񒍕��P�ʃe�[�u�����Q�ƁB<BR>
     * <BR>
     * �Q�j�@@this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@super.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A69A5201AC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //  �P�j�@@�A���������ʏ��`�F�b�N
        //  �P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
        // �u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
        if (this.succCommonInfo == null)
        {
            log.debug("�A���������ʏ�񂪖��w��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02251,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A���������ʏ�񂪖��w��ł��B");
        }

        //  �P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
        this.succCommonInfo.validate();

        //  �P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
        //  �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
        if (!WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(
                this.succCommonInfo.succTradingType)
            && !WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(
                this.succCommonInfo.succTradingType))
        {
            log.debug("�A����������敪�̒l�������ΏۊO");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02252,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�A����������敪�̒l�������ΏۊO�ł��B");
        }

        //  �Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
        //  super.validate()���R�[������B
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(
            this.succCommonInfo.succTradingType))
        {
            super.validate();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
