head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�������̓��N�G�X�g(WEB3MarginSwapMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.define.WEB3EquityKeyItemDef;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p����������n�������̓��N�G�X�g�j�B<br>
 * <br>
 * �M�p����������n�������̓��N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginInputRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSwapMarginInputRequest.class);

    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_swapMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (ID)<BR>
     * ����ID
     */
    public String[] id;
    
    /**
     * (�M�p����\�[�g�L�[)<BR>
     * �Ώۍ��ځF�����A���v
     */
    public WEB3MarginSortKey[] sortKeys;
    
    /**
     * (��������)<BR>
     * �ꊇ�ԍϒP�ʂ̍��v��������
     */
    public String orderQuantity;
    
    /**
     * @@roseuid 41404255022D
     */
    public WEB3MarginSwapMarginInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�h�c��null�ł������ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
     * �@@�P�|�Q�jthis.�h�c.�v�f�����O�ł������ꍇ�A<BR>
     *          �uID�̗v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00282<BR>
     * <BR>
     * �Q�j�@@�M�p����\�[�g�L�[�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�M�p����\�[�g�L�[��null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�M�p����\�[�g�L�[��null�@@���A<BR>
     * �@@�@@�@@�@@this.�M�p����\�[�g�L�[.�v�f�����O�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�M�p����\�[�g�L�[�̑S�v�f�ɂ��Ĉȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j�M�p����\�[�g�L�[.�L�[���ڂ��ȉ��̍��ڈȊO��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�h�����h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�E�h���P���h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�j�@@���������`�F�b�N<BR>
     * �@@�@@this.����������null�A���� this.�����������O�ł������ꍇ�A<BR>
     * �@@�@@�u����������0�ȉ��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40866B810338
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginSwapMarginInputRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�h�c�`�F�b�N:");
        // �P�j�@@�h�c�`�F�b�N
        if (this.id == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,
            this.getClass().getName() + "validate");
        }
        
        int l_intIDLength = this.id.length;
        if (l_intIDLength == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00282,
            this.getClass().getName() + "validate");
        }
        
        log.debug("�M�p����\�[�g�L�[�`�F�b�N:");
        // �Q�j�@@�M�p����\�[�g�L�[�`�F�b�N
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
            
            if (!WEB3EquityKeyItemDef.OPEN_DATE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.CONTRACT_PRICE.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "validate");
            }
        }

        log.debug("���������`�F�b�N:");
        // �R�j�@@���������`�F�b�N
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) <= 0)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41404255024B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginSwapMarginInputResponse(this);
    }
}
@
