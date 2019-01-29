head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccFuturesOpenChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�����V�K���������N�G�X�g(WEB3SuccFuturesOpenChangeCompleteRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/12 �k�v�u(���u) �V�K�쐬���f��272,302
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�����w���敨�����V�K���������N�G�X�g)<BR>
 * �i�A���j�����w���敨�����V�K���������N�G�X�g�N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenChangeCompleteRequest extends WEB3FuturesOpenMarginChangeCompleteRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    public static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenChangeCompleteRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803121831L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_futures_open_change_complete";

    /**
     * (�m�F���T�Z�����)<BR>
     * �m�F���T�Z�����<BR>
     * <BR>
     * �m�F���X�|���X�ő��M�����l�B<BR>
     */
    public String estimatedContractPrice;

    /**
     * (�P�������l���)<BR>
     * �P�������l���B<BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * (������P��)<BR>
     * ������P���B <BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 47D6593800EA
     */
    public WEB3SuccFuturesOpenChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccFuturesOpenChangeCompleteResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�m�F���T�Z������`�F�b�N <BR>
     * �@@this.�m�F���T�Z������̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A  <BR>
     * �@@��O��throw����B <BR>
     * <BR>
     * �@@�@@�Enull<BR>
     * �@@�@@�E�����ȊO<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03061<BR>
     * <BR>
     * �R�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02254 <BR>
     * <BR>
     * �S�j�@@�A�������E���������`�F�b�N<BR>
     * �@@super.validate�A������()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A693740281
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.debug(STR_METHOD_NAME);

        //  �P�j�@@super.validate()���R�[������B
        super.validate();

        //  �Q�j�@@�m�F���T�Z������`�F�b�N
        //  this.�m�F���T�Z������̒l���ȉ��̂����ꂩ�ɊY������ꍇ��
        //  null
        //  �����ȊO
        if (this.estimatedContractPrice == null || !WEB3StringTypeUtility.isInteger(this.estimatedContractPrice))
        {
            log.debug("�m�F���T�Z������̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03061,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F���T�Z������̒l���s���ł��B");
        }

        //  �R�j �A�������P�������l���`�F�b�N
        if (this.priceAdjustmentValueInfo != null)
        {
            //  �R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            this.priceAdjustmentValueInfo.validate();

            //  �R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v
            //  �̗�O��throw����B
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("�P�������l�ƒ����P���敪�̎w�肪�s����");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
            }
        }

        //  �S�j �A�������E���������`�F�b�N
        //  super.validate�A������()���R�[������B
        super.validateSuccOrder();

        log.exiting(STR_METHOD_NAME);
    }
}
@
