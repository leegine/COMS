head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ב֏��(WEB3AdminTMExchangeInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMRateDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�ב֏��)<BR>
 * �ב֏��N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeInfoUnit extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMExchangeInfoUnit.class);

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;

    /**
     * (���[�g�敪)<BR>
     * ���[�g�敪<BR>
     * <BR>
     * 0�F��ב�<BR>
     * 1�F���ב�<BR>
     */
    public String rateDiv;

    /**
     * (���t�בփ��[�g)<BR>
     * ���t�בփ��[�g<BR>
     * <BR>
     * ���o�^���̓��X�|���X�ŃZ�b�g�����l�́A�ύX�O�̒l�B<BR>
     * ����ȊO�ŃZ�b�g�����l�́A�ύX��̒l�B <BR>
     * �i���͂���ĂȂ��ꍇ�́Anull�j<BR>
     */
    public String sellExchangeRate;

    /**
     * (���t�בփ��[�g)<BR>
     * ���t�בփ��[�g<BR>
     * <BR>
     * ���o�^���̓��X�|���X�ŃZ�b�g�����l�́A�ύX�O�̒l�B<BR>
     * ����ȊO�ŃZ�b�g�����l�́A�ύX��̒l�B <BR>
     * �i���͂���ĂȂ��ꍇ�́Anull�j<BR>
     */
    public String buyExchangeRate;

    /**
     * (�o�^����)<BR>
     * �o�^����<BR>
     */
    public Date registrationTime;

    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTMExchangeInfoUnit()
    {

    }

    /**
     * �ב֏��f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���t�בփ��[�g�̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{<BR>
     * <BR>
     * �@@�P�|�P�j�@@���l�łȂ��ꍇ�A��O�����X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02013<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O�ł���΁A<BR>
     * �@@�@@�@@�@@�@@�@@��O�����X���[����B<BR>
     * �@@�P�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����B <BR>
     * �@@�@@�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂��� <BR>
     * �@@�@@�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02014<BR>�@@
     * <BR>�@@
     * �@@�@@�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02015<BR>
     * <BR>
     * �Q�j�@@���t�בփ��[�g�̃`�F�b�N<BR>
     * �@@���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{<BR>
     * <BR>
     * �@@�Q�|�P�j�@@���l�łȂ��ꍇ�A��O�����X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02016<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O�ł���΁A<BR>
     * �@@�@@�@@�@@�@@�@@��O�����X���[����B<BR>
     * �@@�Q�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����B<BR>
     * �@@�@@�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂��� <BR>
     * �@@�@@�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02017<BR>�@@
     * <BR>�@@
     * �@@�@@�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02018<BR>�@@
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���t�בփ��[�g�̃`�F�b�N
        // �@@���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{
        if (this.sellExchangeRate != null)
        {
            // �@@�P�|�P�j�@@���l�łȂ��ꍇ�A��O�����X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.sellExchangeRate))
            {
                //�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂���
                log.debug("���t�בփ��[�g���l�łȂ��ꍇ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02013,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            // �@@�P�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O�ł���΁A
            // �@@�@@�@@�@@�@@�@@��O�����X���[����B
            int l_intIntegerCnt = WEB3StringTypeUtility.getIntegerDigits(this.sellExchangeRate);
            int l_intFractionCnt = WEB3StringTypeUtility.getFractionDigits(this.sellExchangeRate);

            if (l_intIntegerCnt > 3 || l_intFractionCnt > 4)
            {
                //�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂���
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h��בցh�j�̏ꍇ");
                    log.debug("���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h���בցh�j�̏ꍇ");
                    log.debug("���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            // �@@�P�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����B
            double l_dblSellExecRate = Double.parseDouble(this.sellExchangeRate);
            if (l_dblSellExecRate <= 0)
            {
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h��בցh�j�̏ꍇ");
                    log.debug("���t�בփ��[�g <= 0 �̏ꍇ");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02014,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h���בցh�j�̏ꍇ");
                    log.debug("���t�בփ��[�g <= 0 �̏ꍇ");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02015,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        // �Q�j�@@���t�בփ��[�g�̃`�F�b�N
        // �@@���͂�����ꍇ�i���t�בփ��[�g != null�j�̂݁A�ȉ��̏������{
        // �@@�Q�|�P�j�@@���l�łȂ��ꍇ�A��O�����X���[����B
        if (this.buyExchangeRate != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.buyExchangeRate))
            {
                log.debug("���t�בփ��[�g���l�łȂ��ꍇ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02016,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            // �@@�Q�|�Q�j�@@���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O�ł���΁A
            // �@@�@@�@@�@@�@@�@@��O�����X���[����B
            int l_intIntegerCnt = WEB3StringTypeUtility.getIntegerDigits(this.buyExchangeRate);
            int l_intFractionCnt = WEB3StringTypeUtility.getFractionDigits(this.buyExchangeRate);

            if (l_intIntegerCnt > 3 || l_intFractionCnt > 4)
            {
                //�����[�g�敪�ɂ���ė�O���b�Z�[�W�𕪂���
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h��בցh�j�̏ꍇ");
                    log.debug("���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h���בցh�j�̏ꍇ");
                    log.debug("���l�ɕϊ��������̗L���������A�������R���C�������S���͈̔͊O");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            // �@@�Q�|�R�j�@@���t�בփ��[�g <= 0 �̏ꍇ�A��O�����X���[����
            double l_dblSellExecRate = Double.parseDouble(this.buyExchangeRate);
            if (l_dblSellExecRate <= 0)
            {
                //�i���[�g�敪 == �h��בցh�j�̏ꍇ�A�u���t��בփG���[�v
                if (WEB3AdminTMRateDivDef.BASE_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h��בցh�j�̏ꍇ");
                    log.debug("���t�בփ��[�g <= 0 �̏ꍇ");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //�i���[�g�敪 == �h���בցh�j�̏ꍇ�A�u���t���בփG���[�v
                else if (WEB3AdminTMRateDivDef.EXECUTED_EXCHANGE.equals(this.rateDiv))
                {
                    log.debug("�i���[�g�敪 == �h���בցh�j�̏ꍇ");
                    log.debug("���t�בփ��[�g <= 0 �̏ꍇ");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02018,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
