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
filename	WEB3MarginCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ψꗗ���N�G�X�g(WEB3MarginCloseMarginListRequest.java)
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

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p������ψꗗ���N�G�X�g�j�B<br>
 * <br>
 * �M�p������ψꗗ���N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginListRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginCloseMarginListRequest.class);
    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101759L;     
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * (�s��R�[�h)
     */
    public String marketCode;
    
    /**
     * (�\�[�g�L�[)<BR>
     * �M�p����\�[�g�L�[�̈ꗗ<BR>
     * <BR>
     * �Ώۍ��ځF�����R�[�h�A�s��R�[�h�A�����敪�A���敪�A�ٍϋ敪�A<BR>
     * �ٍϊ����l�A�]�����v<BR>
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;

    /**
     * (�A�������t���O)<BR>
     * <BR>
     * �A�������t���O�B <BR>
     * �ifalse�F �ʏ풍���Atrue�F �A�������j<BR>
     */
    public boolean succFlag;
    
    /**
     * @@roseuid 414032D1016D
     */
    public WEB3MarginCloseMarginListRequest() 
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
     * �Q�j�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�Q�|�P�jthis.�\�[�g�L�[��null�̏ꍇ�A�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00231<BR>
     * �@@�Q�|�Q�jthis.�\�[�g�L�[�̗v�f�����O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00232<BR>
     * �@@�Q�|�R�jthis.�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * �@@�@@�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�h�����R�[�h�h<BR>
     * �@@�@@�@@�E�h�s��R�[�h�h<BR>
     * �@@�@@�@@�E�h�����敪�h<BR>
     * �@@�@@�@@�E�h���敪�h<BR>
     * �@@�@@�@@�E�h�ٍϋ敪�h<BR>
     * �@@�@@�@@�E�h�ٍϊ����l�h<BR>
     * �@@�@@�@@�E�h�]�����v�h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
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
     * �S�j�@@�y�[�W���\���s���`�F�b�N<BR>
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
     * @@roseuid 4084943C007A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "������ WEB3MarginCloseMarginListRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�M�p������ψꗗ���N�G�X�g�̃`�F�b�N: BEGIN");
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

        log.debug("�\�[�g�L�[�̃`�F�b�N: BEGIN");
        // �Q�j�@@�\�[�g�L�[�`�F�b�N
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

        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            sortKeys[i].validate();
            if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.TRADEMARKET.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.CONTRACT_DIVISION.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.INCOME.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "validate");
            }
        }
        log.debug("�\�[�g�L�[�̃`�F�b�N: END");

        log.debug("�v���y�[�W�ԍ��̃`�F�b�N: BEGIN");
        // �R�j�@@�v���y�[�W�ԍ��`�F�b�N
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
        // �S�j�@@�y�[�W���\���s���`�F�b�N
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
        log.debug("�M�p������ψꗗ���N�G�X�g�̃`�F�b�N: END");

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 414032D1018B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginCloseMarginListResponse(this);
    }
}
@
