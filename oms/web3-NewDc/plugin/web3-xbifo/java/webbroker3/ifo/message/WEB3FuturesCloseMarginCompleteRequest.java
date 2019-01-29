head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϒ����������N�G�X�g(futures_closeMarginComplete.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
Revesion History : 2008/03/12 �����F�@@�d�l�ύX���f��825
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨�ԍϒ����������N�G�X�g)<BR>
 * �����w���敨�ԍϊ������N�G�X�g�N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginCompleteRequest extends WEB3FuturesCommonRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCloseMarginCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191547L;

    /**
     * (�ԍό���)<BR>
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits;

    /**
     * (���Ϗ���)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * �ꊇ�ԍώ��̏ꍇ�ݒ�
     */
    public String closingOrder;

    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;

    /**
     * (�m�F���P��)<BR>
     * �m�F���X�|���X�ő��M�����l�B
     */
    public String checkPrice;

    /**
     * (�m�F��������)<BR>
     * �m�F���X�|���X�ő��M�����l�B
     */
    public Date checkDate;

    /**
     * (����ID)<BR>
     * �m�F���X�|���X�ő������l�B<BR>
     */
    public String orderId;

    /**
     * @@roseuid 40F7AE18032C
     */
    public WEB3FuturesCloseMarginCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��<BR>
     * <BR>
     * �Q�j�@@�ԍό��ʃ`�F�b�N<BR>
     * �@@this.�ԍό��ʂ�null�̒l�܂��́A�v�f�����O��<BR>
     * �@@����Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00178<BR>
     * <BR>
     * �R�j�@@���Ϗ����`�F�b�N<BR>
     * �@@this.���Ϗ�����null�ȊO�̒l�ł��A<BR>
     * �@@this.���Ϗ������ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�����_���h<BR>
     * �@@�@@�@@�@@�E�h1�F�P���v���h<BR>
     * �@@�@@�@@�@@�E�h2�F�P�������h<BR>
     * �@@�@@�@@�@@�E�h3�F�������h<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00179<BR>
     * <BR>
     * �S�j�@@���σp�^�[���E.���Ϗ����`�F�b�N<BR>
     * �@@�ꊇ�ԍ�(this.�ԍό��ʂ̗v�f��>1)�@@and<BR>
     * �@@this.���Ϗ���==null�̏ꍇ�A��O�u�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B�v���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02304<BR>
     * <BR>
     * �T�j�@@�������ʃ`�F�b�N<BR>
     * �@@�T�|�P�jthis.���Ϗ�����null�̒l�܂��́A<BR>
     * �@@�@@�@@�@@�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʂ�null�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00245<BR>
     * �@@�T�|�Q�jthis.�������ʂ�null�ȊO�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00075<BR>
     * �@@�T�|�R�jthis.�������ʂ�null�ȊO�̒l�ł��A<BR>
     * �@@�@@�@@�@@this.�������ʁ��O�ł���Η�O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00076<BR>
     * <BR>
     * �U�j�@@���Ϗ����`�F�b�N<BR>
     *   �U�|�P�jthis.���Ϗ���=�h0�F�����_���h �̏ꍇ�A<BR>
     *           �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     *           �ԍό��ʂ�validate()���\�b�h���Ăяo���B<BR>
     *   �U�|�Q�jthis.���Ϗ���=�h0�F�����_���h �̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�U�|�Q�|�P�j�ԍό��ʂ̑S�v�f�ɂ��āA<BR>
     *                �ԍό���.���Ϗ���=null or 0 �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00180<BR>
     * �@@�@@�U�|�Q�|�Q�j�ԍό��ʂ̑S�v�f�ɂ��āA<BR>
     *                �ԍό���.���Ϗ���>0 and �ԍό���.����>0 �ƂȂ�<BR>
     *                �g�ݍ��킹�����݂��Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00180<BR>
     * �@@�U�|�R�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j�̏ꍇ�A<BR>
     *           �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * �@@�@@�U�|�R�|�P�j�ԍό���.���Ϗ��ʁ�null �̏ꍇ�A<BR>
     *                  ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00183<BR>
     * �@@�@@�U�|�R�|�Q�j�ԍό��ʂ�validate()���\�b�h���Ăяo���B<BR>
     * <BR>
     * �V�j�@@�m�F���P���`�F�b�N(���N�G�X�g�f�[�^.����ID==null�̏ꍇ�A�`�F�b�N���s��Ȃ��j<BR>
     * �@@this.�m�F���P���������l�i�����́j�̒l�ł���Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00206<BR>
     * <BR>
     * �W�j�@@�m�F���������`�F�b�N(���N�G�X�g�f�[�^.����ID==null�̏ꍇ�A�`�F�b�N���s��Ȃ��j<BR>
     * �@@this.�m�F���������������l�i�����́j�̒l�ł���Η�O���X���[����B<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2D75F00EB
     */
    public void validate() throws WEB3BaseException
    {
        //�P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();
        
        //�Q�j�ԍό��ʃ`�F�b�N
        if (closeMarginContractUnits == null || closeMarginContractUnits.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178, 
                this.getClass().getName() + "validate",
                "�ԍό��ʂ�null�̒l�܂��́A�v�f�����O�ł���ꍇ�̃G���[");
        }

        //�R)���Ϗ����`�F�b�N
        if (WEB3StringTypeUtility.isNotEmpty(this.closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) 
            && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) 
            && !WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder) 
            && !WEB3ClosingOrderDef.RANDOM.equals(closingOrder))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179, 
                this.getClass().getName() + "validate",
                "���Ϗ������h0�F�����_���h�A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�����ꂩ�ɊY�����Ȃ��ꍇ�װ�B");
        }

        // �S�j�@@���σp�^�[���E.���Ϗ����`�F�b�N
        // �@@�ꊇ�ԍ�(this.�ԍό��ʂ̗v�f��>1)�@@and
        // �@@this.���Ϗ���==null�̏ꍇ�A��O�u�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B�v���X���[����B
        //   class: WEB3BusinessLayerException
        //   tag:   BUSINESS_ERROR_02304
        if (this.closeMarginContractUnits.length > 1
            && this.closingOrder == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02304,
                this.getClass().getName() + "validate",
                "�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B");
        }

        //�T�j�@@�������ʃ`�F�b�N
        //�T�|�P�jthis.���Ϗ�����null�̒l�܂��́A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�ł��Athis.�������ʂ�null�̒l�ł���Η�O���X���[����B
        if ((WEB3StringTypeUtility.isEmpty(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(closingOrder) 
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(closingOrder) 
            || WEB3ClosingOrderDef.OPEN_DATE.equals(closingOrder)) 
            && super.futOrderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245, 
                this.getClass().getName() + "validate",
                "���Ϗ�����null�̒l�܂��́A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�ł��A�������ʂ�null�̒l�ł���");
        }
        
        //�T�|�Q�jthis.�������ʂ�null�ȊO�̒l�ł��Athis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isNotEmpty(super.futOrderQuantity)
            && !WEB3StringTypeUtility.isNumber(super.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                this.getClass().getName() + "validate",
                "�������ʂ�null�ȊO�̒l�ł��A�������ʂ������ȊO�̒l�ł���");
        }
        
        //�T�|�R�jthis.�������ʂ�null�ȊO�̒l�ł��Athis.�������ʁ��O�̒l�ł���Η�O���X���[����B 
        if (WEB3StringTypeUtility.isNotEmpty(super.futOrderQuantity) 
            && Long.parseLong(super.futOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                this.getClass().getName() + "validate",
                "�������ʂ�null�ȊO�̒l�ł��A�������ʁ��O�ł���");
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
                if (WEB3StringTypeUtility.isEmpty(closeMarginContractUnits[i].settlePriority)
                    || Double.parseDouble(closeMarginContractUnits[i].settlePriority) == 0)
                {
                    l_intPriorityCnt += 1;
                }
            
                // �ԍό���.���Ϗ���>0 and �ԍό���.����>0�A
                //     �ƂȂ�g�ݍ��킹�����݂��Ȃ��ꍇ�B
                if (!WEB3StringTypeUtility.isEmpty(closeMarginContractUnits[i].settlePriority) 
                    && (Double.parseDouble(closeMarginContractUnits[i].settlePriority) > 0
                    && Double.parseDouble(closeMarginContractUnits[i].contractOrderQuantity) > 0))
                {
                    l_intPriorityQuantityCnt += 1;
                }
            }
            
            //   �U�|�Q�|�P�jthis.���Ϗ���=�h0�F�����_���h and
            //     �v�f���̂��ׂĂ̌��Ϗ���=null or 0 �̏ꍇ�A��O���X���[����B
            if (l_intPriorityCnt == l_intContractUnitsLength)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                    this.getClass().getName() + "validate",
                    "���Ϗ������g0�F�����_���h�̏ꍇ�A�ԍό���.���Ϗ��ʂ�0�ȊO�̐��l����͂��Ă��������B");
            }

            //   �U�|�Q�|�Q�jthis.���Ϗ���=�h0�F�����_���h and
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
    
        //�@@ �U�|�R�jthis.���Ϗ���=(null or"1�F�P���v��"or"2�F�P������"or"3�F������")�̏ꍇ
        //�@@�@@     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
        if (WEB3StringTypeUtility.isEmpty(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
            || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
            || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            for (int i = 0; i < l_intContractUnitsLength; i++)
            {
                // �U�|�R�|�P�j�ԍό���.���Ϗ��ʁ�null �̏ꍇ�A��O���X���[����B
                if (WEB3StringTypeUtility.isNotEmpty(closeMarginContractUnits[i].settlePriority))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00183,
                        this.getClass().getName() + "validate",
                        "���Ϗ����������_���w��ȊO�̏ꍇ�A�ԍό��ʂ̐��ʂƌ��Ϗ��ʂ͓��͏o���܂���B");
                }
  
                // �U�|�R�|�Q�j�ԍό��ʂ�validate()���\�b�h���Ăяo���B
                closeMarginContractUnits[i].validate();
            }
        }
        
        //�V�j�m�F���P���`�F�b�N
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && WEB3StringTypeUtility.isEmpty(this.checkPrice))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206, 
                this.getClass().getName() + "validate",
                "�m�F���P����null�̒l�ł���");
        }

        //�W�j�m�F���������`�F�b�N
        if (!WEB3StringTypeUtility.isEmpty(this.orderId) 
            && this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078, 
                this.getClass().getName() + "validate",
                "�m�F����������null�̒l�ł���B");
        }
    }

    /**
     * (validateAT���Ύ��)<BR>
     * ���Ύ���w�莞�́A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * �i�A�������p�̃��\�b�h�j <BR>
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo�� <BR>
     * <BR>
     * �Q�j�@@����ID�`�F�b�N <BR>
     * �@@�Q�|�P�jthis.����ID��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00600<BR>
     * <BR>
     * �R�j�@@�ԍό��ʃ`�F�b�N <BR>
     * �@@�R�|�P�jthis.�ԍό���=null �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00178<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00178<BR>
     * <BR>
     * �S�j�@@���Ϗ����`�F�b�N <BR>
     * �@@this.���Ϗ�����null and <BR>
     * �@@this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A�u���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B�v<BR>
     * �@@�@@�@@�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00179<BR>
     * �@@�@@�@@�@@�E�h0�F�����_���h <BR>
     * �@@�@@�@@�@@�E�h1�F�P���v���h <BR>
     * �@@�@@�@@�@@�E�h2�F�P�������h <BR>
     * �@@�@@�@@�@@�E�h3�F�������h <BR>
     * <BR>
     * �T�j�@@�������ʃ`�F�b�N <BR>
     * �@@�T�|�P�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or <BR>
     * �@@�@@�@@�@@�@@�h3�F�������h�j and <BR>
     * �@@�@@�@@�@@�@@this.��������=null �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00245<BR>
     * �@@�T�|�Q�jthis.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�������ʂ������ȊO�̒l�ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00075<BR>
     * �@@�T�|�R�jthis.�������ʁ�null and this.�������ʁ�0 �̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�������ʂ�0�ȉ��̒l�ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00076<BR>
     * <BR>
     * �U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N <BR>
     * �@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A <BR>
     * �@@�@@�@@�@@�ԍό��ʂ̗v�f���� <BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B <BR>
     * �@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�������ʂ��g�p����̂ŁA <BR>
     * �@@�@@�@@�@@���`�F�b�N�s�v�B <BR>
     * �@@�@@�@@�@@�E�ԍό���.���� ���ȉ��̂����ꂩ�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@�u�ԍό��ʂ̐��ʎw�肪�s���v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_03060<BR>
     * �@@�@@�@@�@@�@@�@@�Enull  <BR>
     * �@@�@@�@@�@@�@@�@@�E�����ȊO  <BR>
     * �@@�@@�@@�@@�@@�@@�E�O�ȉ��̐���  <BR>
     * �@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔�� <BR>
     * <BR>
     * �V�j�@@�m�F���P���`�F�b�N <BR>
     * �@@this.�m�F���P����null�ł������ꍇ�A�u�m�F���P����null�v�� <BR>
     * �@@��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00206<BR>
     * <BR>
     * �W�j�@@�m�F���������`�F�b�N <BR>
     * �@@this.�m�F����������null�ł������ꍇ�A�u�m�F����������null�v�� <BR>
     * �@@��O���X���[����B <BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo��
        super.validate();

        //�Q�j�@@����ID�`�F�b�N
        //�@@�Q�|�P�jthis.����ID��null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B
        if (this.orderId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }

        //�R�j�@@�ԍό��ʃ`�F�b�N
        //  �R�|�P�jthis.�ԍό���=null �̏ꍇ�A
        //          �u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B
        if (this.closeMarginContractUnits == null)
        {
            log.debug("�ԍό��ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍό��ʂ����w��ł��B");
        }

        //  �R�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A
        //          �u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B
        if (this.closeMarginContractUnits.length == 0)
        {
            log.debug("�ԍό��ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00178,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍό��ʂ����w��ł��B");
        }

        //�S�j�@@���Ϗ����`�F�b�N
        //�@@this.���Ϗ�����null and
        //�@@this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A�u���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B�v�̗�O���X���[����B
        //�@@�@@�@@�@@�E�h0�F�����_���h
        //�@@�@@�@@�@@�E�h1�F�P���v���h
        //�@@�@@�@@�@@�E�h2�F�P�������h
        //�@@�@@�@@�@@�E�h3�F�������h
        if (this.closingOrder != null
            && !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
            && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
            && !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            log.debug("���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00179,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�T�j�@@�������ʃ`�F�b�N
        //�@@�T�|�P�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j and
        //          this.��������=null �̏ꍇ�A
        //          �u���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B�v�̗�O���X���[����B
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
            && this.futOrderQuantity == null)
        {
            log.debug("���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00245,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B");
        }
        //�@@�T�|�Q�jthis.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A
        //          �u�������ʂ������ȊO�̒l�ł��B�v�̗�O���X���[����B
        if (this.futOrderQuantity != null && !WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            log.debug("�������ʂ������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ������ȊO�̒l�ł��B"); 
        }
        //�@@�T�|�R�jthis.�������ʁ�null and this.�������ʁ�0 �̏ꍇ�A
        //          �u�������ʂ�0�ȉ��̒l�ł��B�v�̗�O���X���[����B
        if (this.futOrderQuantity != null && Long.parseLong(this.futOrderQuantity) <= 0)
        {
            log.debug("�������ʂ�0�ȉ��̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������ʂ�0�ȉ��̒l�ł��B"); 
        }

        //�U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
        //�@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
        //�@@�@@�@@�@@�ԍό��ʂ̗v�f����
        //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B
        //�@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B
        //�@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�������ʂ��g�p����̂ŁA
        //�@@�@@�@@�@@���`�F�b�N�s�v�B
        //�@@�@@�@@�@@�E�ԍό���.���� ���ȉ��̂����ꂩ�̏ꍇ�́A
        //�@@�@@�@@�@@�@@�u�ԍό��ʂ̐��ʎw�肪�s���v�̗�O���X���[����B
        //�@@�@@�@@�@@�@@�@@�Enull
        //�@@�@@�@@�@@�@@�@@�E�����ȊO
        //�@@�@@�@@�@@�@@�@@�E�O�ȉ��̐���
        //�@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔��
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            int l_intLength = this.closeMarginContractUnits.length;
            for (int i = 0; i < l_intLength; i++)
            {
                WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContract =
                    this.closeMarginContractUnits[i];
                String l_strOrderQuantity = l_closeMarginContract.contractOrderQuantity;
                if (l_strOrderQuantity == null
                    || !WEB3StringTypeUtility.isNumber(l_strOrderQuantity)
                    || Long.parseLong(l_strOrderQuantity) <= 0
                    || l_strOrderQuantity.length() > 8)
                {
                    log.debug("�ԍό��ʂ̒������ʎw�肪�s���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03060,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ԍό��ʂ̒������ʎw�肪�s���B"); 
                }
            }
        }

        //�V�j�@@�m�F���P���`�F�b�N
        //�@@this.�m�F���P����null�ł������ꍇ�A�u�m�F���P����null�v��
        //�@@��O���X���[����B
        if (this.checkPrice == null)
        {
            log.debug("�m�F���P�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F���P�������w��ł��B"); 
        }

        //�W�j�@@�m�F���������`�F�b�N
        //�@@this.�m�F����������null�ł������ꍇ�A�u�m�F����������null�v��
        //�@@��O���X���[����B
        if (this.checkDate == null)
        {
            log.debug("�m�F����������null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F�������������͂���Ă��܂���B"); 
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
        return new WEB3FuturesCloseMarginCompleteResponse(this);
    }
}
@
