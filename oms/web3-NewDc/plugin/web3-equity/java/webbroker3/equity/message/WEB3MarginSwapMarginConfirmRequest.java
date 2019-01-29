head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����m�F���N�G�X�g(WEB3MarginSwapMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3TaxTypeDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����������n�����m�F���N�G�X�g�j�B<br>
 * <br>
 * �M�p����������n�����m�F���N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginConfirmRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSwapMarginConfirmRequest.class);
    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginConfirm";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (���Ϗ����敪)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     * <BR>
     * �ꊇ�������n�̏ꍇ�ݒ�<BR>
     */
    public String closingOrder;
    
    /**
     * (���ό����ꗗ)<BR>
     * �M�p������ό������ׂ̈ꗗ
     */
    public WEB3MarginCloseMarginContractUnit[] closeMarginContractUnits;
    
    /**
     * (��������)
     */
    public String orderQuantity;
    
    /**
     * (�����挻�n�������敪)<BR>
     * 0�F��ʁ@@1�F����<BR>
     * <BR>
     * �����̎��͌���������敪<BR>
     * ���n�̎��͌��n�������敪<BR>
     */
    public String swapTaxType;
    
    /**
     * @@roseuid 4140425500C4
     */
    public WEB3MarginSwapMarginConfirmRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���ό����ꗗ�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00610<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���ό����ꗗ�̗v�f�����O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00611<BR>
     * <BR>
     * �Q�j�@@���Ϗ����敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���Ϗ����敪��null ���A<BR>
     * �@@�@@�@@�@@�@@this.���Ϗ����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���Ϗ����敪������`�̒l�v�̗�O���X���[����B<BR>
     *�@@�@@�@@ �@@�@@�@@�@@�E�h0�F�����_���h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�h1�F�P���v���h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�h2�F�P�������h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�h3�F�������h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00618<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���Ϗ����敪��null���A<BR>
     * �@@�@@�@@�@@�@@this.���ό����ꗗ�̗v�f�����P�̏ꍇ�A<BR>
     *�@@�@@�@@�@@�@@�u�ꊇ�ԍώ��A���Ϗ��������w��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR> 
     *   tag:   BUSINESS_ERROR_02304<BR>
     * <BR>
     * �R�j�@@���������`�F�b�N<BR>
     * �@@�R�|�P�jthis.���Ϗ����敪���inull�A�h1�F�P���v���h�A�h2�F�P�������h�A<BR>
     * �h3�F�������h�̂����ꂩ�̒l�j�A<BR>
     * �@@�@@�@@�@@���@@this.����������null�ł����<BR>
     * �@@�@@�@@�@@�u����������null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �@@�R�|�Q�jthis.����������null ���A<BR>
     * �@@�@@�@@�@@this.���������������ȊO�̒l�ł����<BR>
     * �@@�@@�@@�@@�u���������������ȊO�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �@@�R�|�R�jthis.����������null ���A<BR>
     * �@@�@@�@@�@@this.�����������O�ł���΁u����������0�ȉ��v��<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �S�j�@@���Ϗ��ʃ`�F�b�N<BR>
     * �@@�S�|�P�jthis.���Ϗ����敪���h0�F�����_���h�̒l�̏ꍇ<BR>
     * �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * �@@�@@�@@�@@�E���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>
     * <BR>
     * �@@�S�|�Q�jthis.���Ϗ����敪���h0�F�����_���h�̒l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�S�|�Q�|�P�j���ό����ꗗ�̗v�f�����̑S�Ă̌��Ϗ��ʁ�null�܂���0<BR>
     * �@@�@@�@@�@@�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v�f�����S�Ă̌��Ϗ��ʂ�null�܂���0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00619<BR>
     * <BR>
     * �@@�@@�S�|�Q�|�Q�j���ό����ꗗ�̗v�f�����S�Ă̒��������A���Ϗ��ʂɂ��āA<BR>
     * �@@�@@�@@�@@�������� > 0�@@���@@���Ϗ��� > 0�ƂȂ�g�ݍ��킹�����݂��Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�u���ϑΏۂȂ��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00620<BR>
     * <BR>
     * �@@�S�|�R�jthis.���Ϗ����敪��null �܂��́A<BR>
     * �@@�@@�@@�@@�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�̏ꍇ<BR>
     * �@@�@@�@@�@@���ό����ꗗ�̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * <BR>
     * �@@�@@�S�|�R�|�P�j���ό�������.����������null<BR>
     *  ���� ���ό�������.���Ϗ��ʁ�null<BR>
     * �@@�@@�@@�@@�ł���΁u�����_�����ψȊO�͒��������A���Ϗ��ʂ̎w��s�v<BR>
     * �̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00621<BR>
     * <BR>
     * �@@�@@�S�|�R�|�Q�j���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>
     * <BR>
     * �T�j�@@�������n�������敪�`�F�b�N<BR>
     * �@@�T�|�P�jthis.�����挻�n�������敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����挻�n�������敪��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00622<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�����挻�n�������敪��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����挻�n�������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F��ʁh<BR>
     * �@@�@@�@@�@@�E�h1�F����h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00623<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4085DA5F0175
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("���ό����ꗗ�`�F�b�N!");
        // �P�j�@@���ό����ꗗ�`�F�b�N<BR>
        // �@@�P�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00610<BR>
        if (this.closeMarginContractUnits == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00610,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �@@�P�|�Q�jthis.���ό����ꗗ�̗v�f�����O�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00611<BR>
        int l_intCloseMarginContractUnitsLength = this.closeMarginContractUnits.length;
        if (l_intCloseMarginContractUnitsLength == 0)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00611,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug("���Ϗ����敪�`�F�b�N!");
        // �Q�j�@@���Ϗ����敪�`�F�b�N<BR>
        // �@@�Q�|�P�jthis.���Ϗ����敪��null ���A<BR>
        // �@@�@@�@@�@@�@@this.���Ϗ����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u���Ϗ����敪������`�̒l�v�̗�O���X���[����B<BR>
        // �@@�@@�@@�@@�@@�@@�@@�E�h0�F�����_���h<BR>
        // �@@�@@�@@�@@�@@�@@�@@�E�h1�F�P���v���h<BR>
        // �@@�@@�@@�@@�@@�@@�@@�E�h2�F�P�������h<BR>
        // �@@�@@�@@�@@�@@�@@�@@�E�h3�F�������h<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00618<BR>
        if (this.closingOrder != null
                && !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00618,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �@@�Q�|�Q�jthis.���Ϗ����敪��null���A<BR>
        // �@@�@@�@@�@@�@@this.���ό����ꗗ�̗v�f�����P�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u�ꊇ�ԍώ��A���Ϗ��������w��v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_02304<BR>
        if (this.closingOrder == null
                &&l_intCloseMarginContractUnitsLength > 1)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02304,
            this.getClass().getName() + "validate");
        }

        log.debug("���������`�F�b�N!");
        // �R�j�@@���������`�F�b�N<BR>
        // �@@�R�|�P�jthis.���Ϗ����敪���inull�A�h1�F�P���v���h�A�h2�F�P�������h�A<BR>
        // �h3�F�������h�̂����ꂩ�̒l�j�A<BR>
        // �@@�@@�@@�@@���@@this.����������null�ł����<BR>
        // �@@�@@�@@�@@�u����������null�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00126<BR>
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
                && this.orderQuantity == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00126,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �@@�R�|�Q�jthis.����������null ���A<BR>
        // �@@�@@�@@�@@this.���������������ȊO�̒l�ł����<BR>
        // �@@�@@�@@�@@�u���������������ȊO�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00126<BR>
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �@@�R�|�R�jthis.����������null ���A<BR>
        // �@@�@@�@@�@@this.�����������O�ł���΁u����������0�ȉ��v��<BR>
        // �@@�@@�@@�@@��O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00126<BR>
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) <= 0)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug("���Ϗ��ʃ`�F�b�N!");
        // �S�j�@@���Ϗ��ʃ`�F�b�N<BR>
        if (WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder))
        {
            boolean existPrioritySetContract = false;
            boolean existValidQuantityContract = false;
            
            for (int i = 0; i < l_intCloseMarginContractUnitsLength; i++)
            {
                // �@@�S�|�P�jthis.���Ϗ����敪���h0�F�����_���h�̒l�̏ꍇ<BR>
                // �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
                // �@@�@@�@@�@@�E���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>
                closeMarginContractUnits[i].validate();

                //���Ϗ��ʂ�1�ȏ�̒l���ݒ肳��Ă��邩�`�F�b�N
                if (closeMarginContractUnits[i].settlePriority == null)
                {
                    continue;
                }
                else if (Long.parseLong(closeMarginContractUnits[i].settlePriority) <= 0)
                {
                    continue;
                }
                existPrioritySetContract = true;
                
                if (Long.parseLong(closeMarginContractUnits[i].orderQuantity) <= 0)
                {
                    continue;
                }
                existValidQuantityContract = true;
            }

            // �@@�S�|�Q�jthis.���Ϗ����敪���h0�F�����_���h�̒l�̏ꍇ�A<BR>
            // �@@�@@�@@�@@�@@�ȉ��̃`�F�b�N���s���B<BR>
            // �@@�@@�S�|�Q�|�P�j���ό����ꗗ�̗v�f�����̑S�Ă̌��Ϗ��ʁ�null�܂���0<BR>
            // �@@�@@�@@�@@�ł������ꍇ�A<BR>
            // �@@�@@�@@�@@�u�v�f�����S�Ă̌��Ϗ��ʂ�null�܂���0�v�̗�O���X���[����B<BR>
            //   class: WEB3BusinessLayerException<BR>
            //   tag:   BUSINESS_ERROR_00619<BR>
            if (existPrioritySetContract == false)
            {
                //��O
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00619,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // �@@�@@�S�|�Q�|�Q�j���ό����ꗗ�̗v�f�����S�Ă̒��������A���Ϗ��ʂɂ��āA<BR>
            // �@@�@@�@@�@@�������� > 0�@@���@@���Ϗ��� > 0�ƂȂ�g�ݍ��킹�����݂��Ȃ��ꍇ�A<BR>
            // �@@�@@�@@�@@�u���ϑΏۂȂ��v�̗�O���X���[����B<BR>
            //   class: WEB3BusinessLayerException<BR>
            //   tag:   BUSINESS_ERROR_00620<BR>
            if (existValidQuantityContract == false)
            {
                //��O
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00620,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // �@@�S�|�R�jthis.���Ϗ����敪��null �܂��́A<BR>
        // �@@�@@�@@�@@�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̒l�̏ꍇ<BR>
        // �@@�@@�@@�@@���ό����ꗗ�̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B<BR>
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder)))
        {
            for (int i = 0; i < l_intCloseMarginContractUnitsLength; i++)
            {
                // �@@�@@�S�|�R�|�P�j���ό�������.����������null<BR>
                //  �܂��� ���ό�������.���Ϗ��ʁ�null<BR>
                // �@@�@@�@@�@@�ł���΁u�����_�����ψȊO�͒��������A���Ϗ��ʂ̎w��s�v<BR>
                // �̗�O���X���[����B<BR>
                //   class: WEB3BusinessLayerException<BR>
                //   tag:   BUSINESS_ERROR_00621<BR>
                if (closeMarginContractUnits[i].orderQuantity != null
                        || closeMarginContractUnits[i].settlePriority != null)
                {
                    //��O
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00621,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
                }
    
                // �@@�@@�S�|�R�|�Q�j���ό������ׂ�validate()���\�b�h���Ăяo���B<BR>
                closeMarginContractUnits[i].validate();
            }
        }

        log.debug("�������n�������敪�`�F�b�N!");
        // �T�j�@@�������n�������敪�`�F�b�N<BR>
        // �@@�T�|�P�jthis.�����挻�n�������敪��null�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u�����挻�n�������敪��null�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00622<BR>
        if (this.swapTaxType == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00622,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // �@@�T�|�Q�jthis.�����挻�n�������敪��null�A<BR>
        // �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u�����挻�n�������敪������`�̒l�v�̗�O���X���[����B<BR>
        // �@@�@@�@@�@@�E�h0�F��ʁh<BR>
        // �@@�@@�@@�@@�E�h1�F����h<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00623<BR>
        if (!WEB3TaxTypeDef.NORMAL.equals(this.swapTaxType)
                && !WEB3TaxTypeDef.SPECIAL.equals(this.swapTaxType))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00623,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT���Ύ��)<BR>
     * ���Ύ���w�莞�́A�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * �i�A�������p�̃��\�b�h�j<BR>
     * <BR>
     * �P�j�@@���ό����ꗗ�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���ό����ꗗ��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00610<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���ό����ꗗ�̗v�f�����O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ό����ꗗ.�v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00611<BR>
     * <BR>
     * �Q�j�@@���Ϗ����敪�`�F�b�N<BR>
     * �@@this.���Ϗ����敪��null ���A<BR>
     * �@@this.���Ϗ����敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * �@@�u���Ϗ����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F�����_���h<BR>
     * �@@�@@�@@�@@�E�h1�F�P���v���h<BR>
     * �@@�@@�@@�@@�E�h2�F�P�������h<BR>
     * �@@�@@�@@�@@�E�h3�F�������h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00618<BR>
     * <BR>
     * �R�j�@@���������`�F�b�N<BR>
     * �@@�R�|�P�jthis.���Ϗ����敪���inull�A�h1�F�P���v���h�A�h2�F�P�������h�A�h3�F�������h�̂����ꂩ�̒l�j�A<BR>
     * �@@�@@�@@�@@���@@this.����������null�ł����<BR>
     * �@@�@@�@@�@@�u����������null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �@@�R�|�Q�jthis.����������null ���A<BR>
     * �@@�@@�@@�@@this.���������������ȊO�̒l�ł����<BR>
     * �@@�@@�@@�@@�u���������������ȊO�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00901<BR>
     * <BR>
     * �@@�R�|�R�jthis.����������null ���A<BR>
     * �@@�@@�@@�@@this.�����������O�ł���΁u����������0�ȉ��v��<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00902<BR>
     * <BR>
     * �S�j�@@���ׂ̒��������`�F�b�N<BR>
     * �@@�S�|�P�j���Ϗ����敪���h0�F�����_���h�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@���ό����ꗗ�̗v�f(���ό�������)����<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B<BR>
     * �@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B<BR>
     * �@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�����������g�p����̂ŁA<BR>
     * �@@�@@�@@�@@���`�F�b�N�s�v�B<BR>
     * �@@�@@�@@�@@�E���ό�������.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���ϖ��ׂ̒��������w�肪�s���v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�Enull<BR>
     * �@@�@@�@@�@@�@@�@@�E�����ȊO<BR>
     * �@@�@@�@@�@@�@@�@@�E�O�ȉ��̐���<BR>
     * �@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔��<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02285<BR>
     * <BR>
     * �T�j�@@�������n�������敪�`�F�b�N<BR>
     * �@@�T�|�P�jthis.�����挻�n�������敪��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����挻�n�������敪��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00622<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�����挻�n�������敪��null�A<BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����挻�n�������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h0�F��ʁh<BR>
     * �@@�@@�@@�@@�E�h1�F����h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00623<BR>
     * @@throws WEB3BaseException
     */
    public void validateAtReverseOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        if (this.closeMarginContractUnits == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00610,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        int l_intCloseMarginContractUnitsLength = this.closeMarginContractUnits.length;
        if (l_intCloseMarginContractUnitsLength == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00611,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.closingOrder != null
                && !WEB3ClosingOrderDef.RANDOM.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                && !WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00618,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if ((this.closingOrder == null
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.closingOrder)
                || WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.closingOrder)
                || WEB3ClosingOrderDef.OPEN_DATE.equals(this.closingOrder))
                && this.orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00902,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (WEB3ClosingOrderDef.RANDOM.equals(closingOrder))
        {
            for (int i = 0;i < closeMarginContractUnits.length;i++)
            {
                String l_strOrderQuantity = closeMarginContractUnits[i].orderQuantity;
                if (l_strOrderQuantity == null)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                if (!WEB3StringTypeUtility.isNumber(l_strOrderQuantity))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                long l_lngOrderQuantity =
                    Long.parseLong(closeMarginContractUnits[i].orderQuantity);
                if (l_lngOrderQuantity <= 0L ||
                    l_lngOrderQuantity > 99999999L)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02285,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        
        if (this.swapTaxType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00622,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (!WEB3TaxTypeDef.NORMAL.equals(this.swapTaxType)
                && !WEB3TaxTypeDef.SPECIAL.equals(this.swapTaxType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00623,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140425500D8
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginSwapMarginConfirmResponse(this);
    }
}
@
