head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t���ʃ��N�G�X�g(WEB3FeqBuyCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���������t���ʃ��N�G�X�g)<BR>
 * �O���������t���ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqBuyCommonRequest extends WEB3FeqCommonRequest 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0�F�~��<BR>
     * 1�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F���<BR>
     * 1�F����<BR>
     */
    public String taxType;
    
    /**
     * @@roseuid 42CE3A050148
     */
    public WEB3FeqBuyCommonRequest() 
    {
     
    }
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyCommonRequest.class);
        
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���Ăяo���B<BR>
     * <BR>
     * �Q�j�����R�[�h�`�F�b�N<BR>
     *    this.�����R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A�u�����R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * �R�j���ϋ敪�`�F�b�N<BR>
     * <BR>
     * �R�|�P�j<BR>
     *    this.���ϋ敪 == null<BR>
     * <BR>
     *    �̏ꍇ�A�u���ϋ敪��null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02111<BR>
     * <BR>
     * �R�|�Q�j<BR>
     *    this.���ϋ敪 != �i�h�~�݁h or �h�O�݁h�j<BR>
     * <BR>
     *    �̏ꍇ�A�u���ϋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02112<BR>
     * <BR>
     * �S�j��������敪�`�F�b�N<BR>
     * <BR>
     * �S�|�P�j<BR>
     *    this.��������敪 == null<BR>
     * <BR>
     *    �̏ꍇ�A�u��������敪��null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02113<BR>
     * <BR>
     * �S�|�Q�j<BR>
     *    this.��������敪 != �i�h��ʁh or �h����h�j<BR>
     * <BR>
     *    �̏ꍇ�A�u��������敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02114<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 428C426800A2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate()���\�b�h���Ăяo���B
        super.validate();
        
        //�Q�j�����R�[�h�`�F�b�N 
        //this.�����R�[�h == null
        //�̏ꍇ�A�u�����R�[�h��null�v�̗�O���X���[����B
        if (this.productCode == null)
        {
            log.debug("�����R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����R�[�h�����w��ł��B" + this.productCode);
        }
        
        //�R�j���ϋ敪�`�F�b�N
        //�R�|�P�jthis.���ϋ敪 == null
        //�̏ꍇ�A�u���ϋ敪��null�v�̗�O���X���[����B
        if (this.settleDiv == null)
        {
            log.debug("���ϋ敪��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02111,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ϋ敪��null�ł��B" + this.settleDiv);
        }
        
        //�R�|�Q�jthis.���ϋ敪 != �i�h�~�݁h or �h�O�݁h�j
        //�̏ꍇ�A�u���ϋ敪������`�̒l�v�̗�O���X���[����B
        if (!WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(this.settleDiv)
            && !WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(this.settleDiv))
        {
            log.debug("���ϋ敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02112,
                this.getClass().getName() + STR_METHOD_NAME,
                "���ϋ敪������`�̒l�ł��B" + this.settleDiv);
        }
        
        //�S�j��������敪�`�F�b�N
        //�S�|�P�jthis.��������敪 == null
        //�̏ꍇ�A�u��������敪��null�v�̗�O���X���[����B
        if (this.taxType == null)
        {
            log.debug("��������敪��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02113,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������敪��null�ł��B" + this.taxType);
        }
        
        //�S�|�Q�j
        //this.��������敪 != �i�h��ʁh or �h����h�j
        //�̏ꍇ�A�u��������敪������`�̒l�v�̗�O���X���[����B
        if (!WEB3TaxTypeSpecialDef.NORMAL.equals(this.taxType) &&
            !WEB3TaxTypeSpecialDef.SPECIAL.equals(this.taxType))
        {
            log.debug("��������敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02114,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������敪������`�̒l�ł��B" + this.taxType);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
