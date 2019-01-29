head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p��������Ɖ�N�G�X�g(WEB3MarginContractReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquitySettlementStateDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p��������Ɖ�N�G�X�g�j�B<br>
 * <br>
 * �M�p��������Ɖ�N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginContractReferenceRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginContractReferenceRequest.class);
    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_contractReference";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101801L;     
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * (�s��R�[�h)
     */
    public String marketCode;
    
    /**
     * (���Ϗ�ԋ敪)<BR>
     * <BR>
     * 0�F���ύρ@@1�F�����ρ@@2�F���ϒ�<BR>
     */
    public String settlementState;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �M�p����\�[�g�L�[�̈ꗗ<BR>
     * <BR>
     * �Ώۍ��ځF�����R�[�h�A�s��R�[�h�A�����敪�A���敪�A�ٍϋ敪�A<BR>
     * �ٍϊ����l�A�����A�]�����v<BR>
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w��@@<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �P�y�[�W���ɕ\�����������s�����w��
     */
    public String pageSize;
    
    /**
     * @@roseuid 41403F6F01F4
     */
    public WEB3MarginContractReferenceRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h�����L�ȊO�̒l�̏ꍇ�A<BR>
     * �@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�Enull(�w��Ȃ�)<BR>
     * �@@�@@�E�h1�F�����h<BR>
     *     �E�h2�F���h <BR>
     *     �E�h3�F���É��h <BR>
     *     �E�h6�F�����h <BR>
     *     �E�h8�F�D�y�h <BR>
     *     �E�h9�FNNM�h <BR>
     *     �E�h10�FJASDAQ�h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * �Q�j�@@���Ϗ�ԋ敪�`�F�b�N <BR>
     * �@@this.���Ϗ�ԋ敪�����L�ȊO�̒l�̏ꍇ�A<BR>
     * �@@�u���Ϗ�ԋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�Enull(�w��Ȃ�) <BR>
     * �@@�@@�E�h0�F���ύρh <BR>
     * �@@�@@�E�h1�F�����ρh <BR>
     * �@@�@@�E�h2�F���ϒ��h <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00233<BR>
     * <BR>
     * �R�j�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�R�|�P�jthis.�\�[�g�L�[��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00231<BR>
     * �@@�R�|�Q�jthis.�\�[�g�L�[�̗v�f�����O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00232<BR>
     * �@@�R�|�R�jthis.�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * �@@�@@�R�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�h�����R�[�h�h<BR>
     * �@@�@@�@@�E�h�s��R�[�h�h<BR>
     * �@@�@@�@@�E�h�����敪�h<BR>
     * �@@�@@�@@�E�h���敪�h<BR>
     * �@@�@@�@@�E�h�ٍϋ敪�h<BR>
     * �@@�@@�@@�E�h�ٍϊ����l�h <BR>
     * �@@�@@�@@�E�h�����h <BR>
     * �@@�@@�@@�E�h�����h<BR>
     * �@@�@@�@@�E�h�]�����v�h <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@this.�v���y�[�W�ԍ����ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�v���y�[�W�ԍ���null�v�̗�O���X���[)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00089<BR>
     * �@@�@@�@@�E�����ȊO�@@(�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00090<BR>
     * �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@this.�y�[�W���\���s�����ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�y�[�W���\���s����null�v�̗�O���X���[)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00091<BR>
     * �@@�@@�@@�E�����ȊO�@@(�u�y�[�W���\���s���������ȊO�v�̗�O���X���[)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00092<BR>
     * �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866B1102CA
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "������ WEB3MarginContractReferenceRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�M�p��������Ɖ�N�G�X�g�̃`�F�b�N: BEGIN");
        log.debug("�s��R�[�h�̃`�F�b�N: BEGIN");
        // �P�j�@@�s��R�[�h�`�F�b�N
        if (this.marketCode != null
                && !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "validate");
        }
        log.debug("�s��R�[�h�̃`�F�b�N: END");
        
        log.debug("���Ϗ�ԋ敪�̃`�F�b�N: BEGIN");
        // �Q�j�@@���Ϗ�ԋ敪�`�F�b�N
        if (this.settlementState != null
                && !WEB3EquitySettlementStateDef.SETTLEMENT_END.equals(this.settlementState)
                && !WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED.equals(this.settlementState)
                && !WEB3EquitySettlementStateDef.SETTLING.equals(this.settlementState))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00233,
            this.getClass().getName() + "validate");
        }
        log.debug("���Ϗ�ԋ敪�̃`�F�b�N: END");

        log.debug("�\�[�g�L�[�̃`�F�b�N: BEGIN");
        // �R�j�@@�\�[�g�L�[�`�F�b�N
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,
            this.getClass().getName() + "validate");
        }
        
        int l_intSortKeysLength = this.sortKeys.length;
        if (l_intSortKeysLength == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,
            this.getClass().getName() + "validate");
        }
        
        // �@@�R�|�R�jthis.�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            sortKeys[i].validate();

            if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.TRADEMARKET.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.OPEN_DATE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.CLOSEDATE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.INCOME.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "validate");
            }
        }
        log.debug("�\�[�g�L�[�̃`�F�b�N: END");

        log.debug("�v���y�[�W�ԍ��̃`�F�b�N: BEGIN");
        // �S�j�@@�v���y�[�W�ԍ��`�F�b�N
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00089,
            this.getClass().getName() + "validate");
        }
        
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
            this.getClass().getName() + "validate");
        }
        
        if (Long.parseLong(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
            this.getClass().getName() + "validate");
        }
        log.debug("�v���y�[�W�ԍ��̃`�F�b�N: END");

        log.debug("�y�[�W���\���s���̃`�F�b�N: BEGIN");
        // �T�j�@@�y�[�W���\���s���`�F�b�N
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
            this.getClass().getName() + "validate");
        }
        
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
            this.getClass().getName() + "validate");
        }
        
        if (Long.parseLong(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
            this.getClass().getName() + "validate");
        }
        log.debug("�y�[�W���\���s���̃`�F�b�N: END");
        log.debug("�M�p��������Ɖ�N�G�X�g�̃`�F�b�N: END");
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 41403F6F0212
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginContractReferenceResponse(this);
    }
}
@
