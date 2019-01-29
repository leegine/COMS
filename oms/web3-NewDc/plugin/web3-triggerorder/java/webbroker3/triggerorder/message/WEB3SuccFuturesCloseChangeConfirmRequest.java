head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesCloseChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�����ԍϊm�F���N�G�X�g(WEB3SuccFuturesCloseChangeConfirmRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,287,303
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�����w���敨�����ԍϊm�F���N�G�X�g)<BR>
 * �i�A���j�����w���敨�����ԍϊm�F���N�G�X�g�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseChangeConfirmRequest extends WEB3FuturesCloseMarginChangeConfirmRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    public static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseChangeConfirmRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121757L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_close_change_confirm";

    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * @@roseuid 47D6593901C5
     */
    public WEB3SuccFuturesCloseChangeConfirmRequest()
    {

    }
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesCloseChangeConfirmResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�P�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02254<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A6B1E1006A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //  �P�j �A�������P�������l���`�F�b�N
        //  �P�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
        //   �A�������P�������l���.validate()���R�[������B
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();

            //  �P�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �����P���敪��"���s"�̏ꍇ
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("�P�������l�ƒ����P���敪�̎w�肪�s����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateAT�����c���)<BR>
     * ���Ύ���ȊO�̏ꍇ�A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�A�������p�̃��\�b�h�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�@@�A�������E���������`�F�b�N<BR>
     * �@@�X�[�p�[�N���X��validate�A���������\�b�h���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A6B211036C
     */
    public void validateATExistingRemainderTrading() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateATExistingRemainderTrading()";
        log.entering(STR_METHOD_NAME);

        //  �P�j �X�[�p�[�N���X��validate���\�b�h���R�[������B
        super.validate();

        //  �Q�j �A�������E���������`�F�b�N
        super.validateSuccOrder();

        log.exiting(STR_METHOD_NAME);
    }
}
@
