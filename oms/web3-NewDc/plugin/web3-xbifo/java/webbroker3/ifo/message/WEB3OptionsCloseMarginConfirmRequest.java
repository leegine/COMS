head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϊm�F���N�G�X�g�N���X(WEB3OptionsCloseMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
              001: 2004/07/22 ���Ō� (���u) WEB3ClosingOrderDef��WEB3IfoMessageTypeDef�������ւ���
Revesion History : 2008/03/11 ����(���u) ���f�� 826
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���I�v�V�����ԍϊm�F���N�G�X�g)<BR>
 * �����w���I�v�V�����ԍϊm�F���N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginConfirmRequest extends WEB3OptionsCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginConfirm";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111542L;

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsCloseMarginConfirmRequest.class);

    /**
     * �ԍό���
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits;

    /**
    *  ���Ϗ���<BR>
    * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
    * �ꊇ�ԍώ��̏ꍇ�ݒ�<BR>
    */
    public String closingOrder;

    /**
    * @@roseuid 40C0A8F001C5
    */
    public WEB3OptionsCloseMarginConfirmRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�ԍό��ʃ`�F�b�N<BR>
     *   �Q�|�P�jthis.�ԍό���=null �̏ꍇ�A<BR>
     *         �@@��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00178<BR>
     *   �Q�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A<BR>
     *         �@@��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * �R�j�@@���Ϗ����`�F�b�N<BR>
     * �@@this.���Ϗ�����null�ȊO�̒l�ł��A<BR>
     * �@@this.���Ϗ������ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�����_���h<BR>
     * �@@�@@�@@�@@�E�h1�F�P���v���h<BR>
     * �@@�@@�@@�@@�E�h2�F�P�������h<BR>
     * �@@�@@�@@�@@�E�h3�F�������h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00179<BR>
     * <BR>
     * �S�j�@@���σp�^�[���E.���Ϗ����`�F�b�N <BR>
     * �@@�ꊇ�ԍ�(this.�ԍό��ʂ̗v�f��>1)�@@and�@@ <BR>
     * �@@this.���Ϗ���==null�̏ꍇ�A��O�u�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B�v���X���[����B<BR> 
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02304<BR>
     * �T�j�@@�������ʃ`�F�b�N<BR>
     * �@@�T�|�P�jthis.���Ϗ�����null�̒l�܂��́A<BR>
     * �@@�@@�@@�@@�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00074<BR>
     * �@@�T�|�Q�jthis.�������ʂ�null�ȊO�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00075<BR>
     * �@@�T�|�R�jthis.�������ʂ�null�ȊO�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʁ��O�̒l�ł���Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * �U�j�@@���Ϗ����`�F�b�N<BR>
     * �@@�U�|�P�jthis.���Ϗ���=�h0�F�����_���h �̏ꍇ�A<BR>
     *           �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     *           �E�ԍό��ʂ�validate()���\�b�h���Ăяo���B<BR>
     * �@@�U�|�Q�jthis.���Ϗ���=�h0�F�����_���h and<BR>
     * �@@�@@�@@�@@  �v�f���̂��ׂĂ̌��Ϗ���=null or 0 �̏ꍇ�A<BR>
     *           ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00180<BR>
     * �@@�U�|�R�jthis.���Ϗ���=�h0�F�����_���h and<BR>
     * �@@�@@�@@�@@  �i�ԍό���.���Ϗ���>0 and �ԍό���.����>0 <BR>
     *           �ƂȂ�g�ݍ��킹�����݂��Ȃ��j �ꍇ�A<BR>
     *           ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00180<BR>
     * �@@�U�|�S�jthis.���Ϗ���=�inull or �h1�F�P���v���h or<BR>
     *            �h2�F�P�������h or �h3�F�������h�j �̏ꍇ<BR>
     *   �@@�@@�@@�@@�ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     *     �U�|�S�|�P�j�ԍό���.���Ϗ��ʁ�null �̏ꍇ�A<BR>
     *                  ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00183<BR>
     *     �U�|�S�|�Q�j�ԍό��ʂ�validate()���\�b�h���Ăяo���B<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4069797702C8
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();

        // �Q�j�@@�ԍό��ʃ`�F�b�N
        //this.�ԍό��ʂ�null�̒l�܂��́A�v�f�����O�ł���Η�O���X���[����B
        if (closeMarginContractUnits == null
            || closeMarginContractUnits.length == 0)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "validate",
                "�ԍό��ʂ�null�̒l�܂��́A�v�f�����O�ł���ꍇ�̃G���[");
        }

        // �R�j�@@���Ϗ����`�F�b�N
        //this.���Ϗ�����null�ȊO�̒l�ł��Athis.���Ϗ������ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        if (WEB3StringTypeUtility.isNotEmpty(this.closingOrder)
            && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)
            && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179,
                this.getClass().getName() + "validate",
                "���Ϗ������h0�F�����_���h�A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�����ꂩ�ɊY�����Ȃ��ꍇ�װ�B");
        }

        //�S�j�@@���σp�^�[���E.���Ϗ����`�F�b�N 
        //�ꊇ�ԍ�(this.�ԍό��ʂ̗v�f��>1)�@@and�@@ <BR>
        // �@@this.���Ϗ���==null�̏ꍇ�A��O�u�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B�v���X���[����B
        if (this.closeMarginContractUnits.length > 1 && this.closingOrder == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02304,
                this.getClass().getName() + "validate",
                "�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B");
        }

        // �T�j�@@�������ʃ`�F�b�N
        // �T�|�P�jthis.���Ϗ�����null�̒l�܂��́A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�ł��Athis.�������ʂ�null�̒l�ł���Η�O���X���[����B
        if ((WEB3StringTypeUtility.isEmpty(closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder)
            || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder))
            && (super.opOrderQuantity == null))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245,
                this.getClass().getName() + "validate",
                "���Ϗ�����null�̒l�܂��́A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�ł��A�������ʂ�null�̒l�ł���B");
        }

        // �T�|�Q�jthis.�������ʂ�null�ȊO�̒l�ł��Athis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isNotEmpty(super.opOrderQuantity))
        {
            if(!WEB3StringTypeUtility.isNumber(super.opOrderQuantity))
            {
                //��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                    this.getClass().getName() + "validate",
                    "�������ʂ������ȊO�̒l�ł���B");
            }
        }

        // �T�|�R�jthis.�������ʂ�null�ȊO�̒l�ł��Athis.�������ʁ��O�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isNotEmpty(super.opOrderQuantity)
            && Long.parseLong(super.opOrderQuantity) <= 0)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "validate",
                "�������ʂ�0�ȉ��̒l�ł���B");
        }

        // �U�j�@@���Ϗ����`�F�b�N
        //   �U�|�P�jthis.���Ϗ���=�h0�F�����_���h �̏ꍇ�A
        //      �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
        //         �E�ԍό��ʂ�validate()���\�b�h���Ăяo���B
        int l_intContractUnitsLength = closeMarginContractUnits.length;
        int l_intPriorityCnt = 0;
        int l_intPriorityQuantityCnt = 0;
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                // �ԍό��ʂ�validate()���\�b�h���Ăяo���B
                closeMarginContractUnits[i].validate();

                // �v�f���̂��ׂĂ̌��Ϗ���=null or 0 �̏ꍇ�B
                if (closeMarginContractUnits[i].settlePriority == null
                    || Double.parseDouble(closeMarginContractUnits[i].settlePriority) == 0)
                {
                    l_intPriorityCnt += 1;
                }

                // �ԍό���.���Ϗ���>0 and �ԍό���.����>0�A
                //     �ƂȂ�g�ݍ��킹�����݂��Ȃ��ꍇ�B
                if (closeMarginContractUnits[i].settlePriority != null
                    && (Double.parseDouble(closeMarginContractUnits[i].settlePriority) > 0
                    && Double.parseDouble(closeMarginContractUnits[i].contractOrderQuantity) > 0))
                {
                    l_intPriorityQuantityCnt += 1;
                }
            }
            //   �U�|�Q�jthis.���Ϗ���=�h0�F�����_���h and
            //     �v�f���̂��ׂĂ̌��Ϗ���=null or 0 �̏ꍇ�A��O���X���[����B
            if (l_intPriorityCnt == l_intContractUnitsLength)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                    this.getClass().getName() + "validate",
                    "���Ϗ������g0�F�����_���h�̏ꍇ�A�ԍό���.���Ϗ��ʂ�0�ȊO�̐��l����͂��Ă��������B");
            }

            //   �U�|�R�jthis.���Ϗ���=�h0�F�����_���h and
            //    �i�ԍό���.���Ϗ���>0 and �ԍό���.����>0 �ƂȂ�g�ݍ��킹�����݂��Ȃ��j �ꍇ�A
            //     ��O���X���[����B
            if (l_intPriorityQuantityCnt == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                    this.getClass().getName() + "validate",
                    "���Ϗ������g0�F�����_���h�̏ꍇ�́A�i�ԍό���.���Ϗ���>0 and �ԍό���.����>0) �ƂȂ�g�ݍ��킹���� �݂��Ȃ��ꍇ�̓G���[�B");
            }
        }

        //�@@ �U�|�S�jthis.���Ϗ���=(null or"1�F�P���v��"or"2�F�P������"or"3�F������")�̏ꍇ
        //�@@�@@     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
        if (WEB3StringTypeUtility.isEmpty(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
            || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                // �U�|�S�|�P�j�ԍό���.���Ϗ��ʁ�null �̏ꍇ�A��O���X���[����B
                if (WEB3StringTypeUtility.isNotEmpty(closeMarginContractUnits[i].settlePriority))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00183,
                        this.getClass().getName() + "validate",
                        "���Ϗ����������_���w��ȊO�̏ꍇ�A�ԍό��ʂ̐��ʂƌ��Ϗ��ʂ͓��͏o���܂���B");
                }

                // �U�|�S�|�Q�j�ԍό��ʂ�validate()���\�b�h���Ăяo���B
                closeMarginContractUnits[i].validate();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT���Ύ��) <BR>
     * ���Ύ���w�莞�́A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�A�������p�̃��\�b�h�j <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�ԍό��ʃ`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�ԍό���=null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00178<BR>
     * �@@�Q�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * �R�j�@@���Ϗ����`�F�b�N<BR>
     * �@@this.���Ϗ�����null and <BR>
     * �@@this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00179<BR>
     * �@@�@@�@@�@@�E�h0�F�����_���h<BR>
     * �@@�@@�@@�@@�E�h1�F�P���v���h <BR>
     * �@@�@@�@@�@@�E�h2�F�P�������h <BR>
     * �@@�@@�@@�@@�E�h3�F�������h <BR>
     * <BR>
     * �S�j�@@�������ʃ`�F�b�N<BR>
     * �@@�S�|�P�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�h3�F�������h�j and <BR>
     * �@@�@@�@@�@@this.��������=null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00245<BR>
     * �@@�S�|�Q�jthis.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00075<BR>
     * �@@�S�|�R�jthis.�������ʁ�null and this.�������ʁ�0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * <BR>
     * �T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N<BR>
     * �@@�T�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�ԍό��ʂ̗v�f����<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * �@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B<BR>
     * �@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�������ʂ��g�p����̂ŁA<BR>
     * �@@�@@�@@�@@���`�F�b�N�s�v�B<BR>
     * �@@�@@�@@�@@�E�ԍό���.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�ԍό��ʂ̒������ʎw�肪�s���v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Enull<BR>
     * �@@�@@�@@�@@�@@�E�����ȊO<BR>
     * �@@�@@�@@�@@�@@�E�O�ȉ��̐���<BR>
     * �@@�@@�@@�@@�@@�E�W���𒴂��鐔��<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03060<BR>
     *
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validateATReserveOrder()throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateATReserveOrder()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();
        
        // �Q�j�@@�ԍό��ʃ`�F�b�N
        // �Q�|�P�jthis.�ԍό���=null �̏ꍇ
        if (this.closeMarginContractUnits == null)
        {
            // ��O���X���[����B
            log.debug("�ԍό��ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍό��ʂ����w��ł��B");
        }
        // �Q�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ
        if (this.closeMarginContractUnits.length == 0)
        {
            // ��O���X���[����B
            log.debug("�ԍό��ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍό��ʂ����w��ł��B");
        }
        
        // �R�j�@@���Ϗ����`�F�b�N
        // this.���Ϗ�����null and  this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A��O���X���[����B
            //�E�h0�F�����_���h 
            //�E�h1�F�P���v���h 
            //�E�h2�F�P�������h 
            //�E�h3�F�������h 
        if (WEB3StringTypeUtility.isNotEmpty(this.closingOrder) &&
            !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder) &&
            !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder) &&
            !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder) &&
            !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            // ��O���X���[����B
            log.debug("���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
        // �S�j�@@�������ʃ`�F�b�N
        // �S�|�P�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j and 
        //                        this.��������=null �̏ꍇ�A
        if ((this.closingOrder == null ||
            WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder) ||
            WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder) ||
            WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder)) &&
            this.opOrderQuantity == null)
        {
            // ��O���X���[����B
            log.debug("���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B");
        }
        // �S�|�Q�jthis.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A��O���X���[����B
        if (this.opOrderQuantity != null &&
            !WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            // ��O���X���[����B
            log.debug("�������ʂ������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ������ȊO�̒l�ł��B");
        }
        // �S�|�R�jthis.�������ʁ�null and this.�������ʁ�0 �̏ꍇ�A��O���X���[����B
        if (this.opOrderQuantity != null)
        {
            double l_dblOrderQuantity = Double.parseDouble(this.opOrderQuantity);
            if (l_dblOrderQuantity <= 0)
            {
                // ��O���X���[����B
                log.debug("�������ʂ�0�ȉ��̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������ʂ�0�ȉ��̒l�ł��B"); 
            }
        }
        // �T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
        // �T�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            // �ԍό��ʂ̗v�f����
            // ���L�̃`�F�b�N���J��Ԃ��čs���B
            int l_intCloseMarginContractCnt = this.closeMarginContractUnits.length;
            for (int i = 0; i < l_intCloseMarginContractCnt; i++)
            {
                // �ԍό���.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A�u�ԍό��ʂ̒������ʎw�肪�s���v�̗�O���X���[����B
                // �Enull
                // �E�����ȊO
                // �E�O�ȉ��̐���
                // �E�W���𒴂��鐔��
                String l_strContractOrderQuantity = this.closeMarginContractUnits[i].contractOrderQuantity;
                if(l_strContractOrderQuantity==null ||
                    !WEB3StringTypeUtility.isNumber(l_strContractOrderQuantity) ||
                    Long.parseLong(l_strContractOrderQuantity) <= 0 ||
                    l_strContractOrderQuantity.length() > 8)
                {
                    // ��O���X���[����B
                    log.debug("�ԍό��ʂ̒������ʎw�肪�s���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03060,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ԍό��ʂ̒������ʎw�肪�s���B"); 
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
    * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
    *<BR>
    * @@return ���X�|���X�I�u�W�F�N�g
    */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginConfirmResponse(this);
    }
}
@
