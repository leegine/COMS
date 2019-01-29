head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsOpenChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V���������V�K���������N�G�X�g(WEB3SuccOptionsOpenChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,304
Revision History : 2008/04/16 �k�v�u (���u) �d�l�ύX ���f��282
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�i�A���j�����w���I�v�V���������V�K���������N�G�X�g)<BR>
 * �i�A���j�����w���I�v�V���������V�K���������N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsOpenChangeCompleteRequest extends WEB3OptionsOpenMarginChangeCompleteRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsOpenChangeCompleteRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141534L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_open_change_complete";

    /**
     * (�m�F���T�Z��n���)<BR>
     * �m�F���T�Z��n���<BR>
     * <BR>
     * �m�F���X�|���X�ő��M�����l�B<BR>
     */
    public String estimatedPrice;

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
     * @@roseuid 47D9F2CA0105
     */
    public WEB3SuccOptionsOpenChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsOpenChangeCompleteResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�m�F���T�Z��n����`�F�b�N <BR>
     * �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A <BR>
     * �@@��O��throw����B  <BR>
     * <BR>
     * �@@�@@�Enull  <BR>
     * �@@�@@�E�����ȊO  <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@ tag: BUSINESS_ERROR_02292<BR>
     * <BR>
     * �R�j�@@�A�������P�������l���`�F�b�N<BR>
     * �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��<BR>
     * �@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@ tag: BUSINESS_ERROR_02254<BR>
     * <BR>
     * �S�j�@@�A�������E���������`�F�b�N<BR>
     * �@@super.validate�A������()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B0F09E0075
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@super.validate()���R�[������B
        super.validate();

        //�Q�j�@@�m�F���T�Z��n����`�F�b�N
        // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
        // �@@��O��throw����B
        // �@@�@@�Enull
        // �@@�@@�E�����ȊO
        if (this.estimatedPrice == null || !WEB3StringTypeUtility.isInteger(this.estimatedPrice))
        {
            log.debug("�m�F���T�Z��n����̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02292,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F���T�Z��n����̒l���s���ł��B");
        }

        //�R�j�@@�A�������P�������l���`�F�b�N
        // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();

            //�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //        �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            //        ��O��throw����B
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P�������l�ƒ����P���敪�̎w�肪�s�����ł��B");
            }
        }

        //�S�j�@@�A�������E���������`�F�b�N
        // �@@super.validate�A������()���R�[������B
        super.validateSuccOrder();
        log.exiting(STR_METHOD_NAME);
    }
}
@
