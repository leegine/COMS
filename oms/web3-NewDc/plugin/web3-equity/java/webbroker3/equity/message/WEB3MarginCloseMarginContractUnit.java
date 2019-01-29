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
filename	WEB3MarginCloseMarginContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ό�������(WEB3MarginCloseMarginContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�M�p������ό������ׁj�B<br>
 * <br>
 * �M�p������ό������׃N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractUnit extends Message 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginCloseMarginContractUnit.class);
 
    /**
     * (ID)<BR>
     * �����h�c
     */
    public String id;
    
    /**
     * (��������)
     */
    public String orderQuantity;
    
    /**
     * (���Ϗ���)
     */
    public String settlePriority;
    
    /**
     * @@roseuid 414032D00375
     */
    public WEB3MarginCloseMarginContractUnit() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�h�c��null�̏ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �Q�j�@@���Ϗ��ʃ`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���Ϗ��ʁ�null���Athis.���Ϗ��ʁ������̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���Ϗ��ʂ������ȊO�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00329<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.���Ϗ��ʁ�null���Athis.���Ϗ��ʁ��O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���Ϗ��ʂ�0�����v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00246<BR>
     * <BR>
     * �R�j�@@�����`�F�b�N<BR>
     * �@@�R�|�P�jthis.����������null���Athis.���������������̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���������������ȊO�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �@@�R�|�Q�jthis.����������null���Athis.�����������O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u����������0�����v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00126<BR>
     * <BR>
     * �S�j�@@���Ϗ��ʁE�����`�F�b�N <BR>
     * �@@�S�|�P�jthis.���Ϗ��ʁ�null���Athis.������null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���Ϗ��ʎw�莞�͊����w��K�{�v�̗�O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00180<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4084B4D502DB
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�M�p������ό������ׂ̃`�F�b�N�FBEGIN");
        log.debug("�h�c�`�F�b�N:");
        // �P�j�@@�h�c�`�F�b�N<BR>
        // �@@�P�|�P�jthis.�h�c��null�̏ꍇ�A�uID��null�v�̗�O���X���[����B<BR>
        if (this.id == null)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080,
            this.getClass().getName() + "validate");
        }

        log.debug("���Ϗ��ʃ`�F�b�N:");
        // �Q�j�@@���Ϗ��ʃ`�F�b�N<BR>
        // �@@�Q�|�P�jthis.���Ϗ��ʁ�null���Athis.���Ϗ��ʁ������̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u���Ϗ��ʂ������ȊO�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00329<BR>
        if (this.settlePriority != null && !WEB3StringTypeUtility.isNumber(this.settlePriority))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00329,
            this.getClass().getName() + "validate");
        }

        // �@@�Q�|�Q�jthis.���Ϗ��ʁ�null���Athis.���Ϗ��ʁ��O�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u���Ϗ��ʂ�0�����v�̗�O���X���[����B<BR>
        if (this.settlePriority != null && Long.parseLong(this.settlePriority) < 0)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00246,
            this.getClass().getName() + "validate");
        }

        log.debug("�����`�F�b�N:");
        // �R�j�@@�����`�F�b�N<BR>
        // �@@�R�|�P�jthis.����������null���Athis.���������������̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u���������������ȊO�v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00901<BR>
        if (this.orderQuantity != null && !WEB3StringTypeUtility.isNumber(this.orderQuantity))
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00901,
            this.getClass().getName() + "validate");
        }

        // �@@�R�|�Q�jthis.����������null���Athis.�����������O�̏ꍇ�A<BR>
        // �@@�@@�@@�@@�@@�u����������0�����v�̗�O���X���[����B<BR>
        //   class: WEB3BusinessLayerException<BR>
        //   tag:   BUSINESS_ERROR_00902<BR>
        if (this.orderQuantity != null && Long.parseLong(this.orderQuantity) < 0)
        {
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00902,
            this.getClass().getName() + "validate");
        }
        
        // �S�j�@@���Ϗ��ʁE�����`�F�b�N
        // �@@�S�|�P�jthis.���Ϗ��ʁ�null���Athis.������null�̏ꍇ�A
        // �@@�@@�@@�@@�@@�u���Ϗ��ʎw�莞�͊����w��K�{�v�̗�O���X���[����B
        if (this.settlePriority != null && this.orderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.debug("�M�p������ό������ׂ̃`�F�b�N�FEND");
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
