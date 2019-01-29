head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒������ʃ��N�G�X�g�N���X(WEB3AdminMutualTPAdjustCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ��O�� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�Ǘ��җ]�͒������ʃ��N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualTPAdjustCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191010L;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPAdjustCommonRequest.class);
    
    /**
     * (�ڋq�R�[�h)
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�����R�[�h)
     * �����R�[�h<BR>
     */
    public String mutualProductCode;
    
    /**
     * (���Z���z)
     * ���Z���z<BR>
     */
    public String settlePrice;
    
    /**
     * (������)
     * ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * (����)
     * ����<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (��n��)
     * ��n��<BR>
     */
    public Date deliveryDate;
     
    /**
     * (validate)
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B  <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�ڋq�R�[�h�`�F�b�N <BR>
     *      this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_00835 <BR>
     * <BR>
     * �Q�j�@@�����R�[�h�`�F�b�N <BR>
     *      this.�����R�[�h==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_01252 <BR>
     * <BR>
     * �R�j�@@���Z���z�`�F�b�N <BR>
     * �@@�R�|�P) this.���Z���z==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_02334 <BR>
     *   �R�|�Q) this.���Z���z�̌�����11���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_02335 <BR>
     * <BR>
     * �S�j�@@�������`�F�b�N <BR>
     *      this.������==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_00406 <BR>
     * <BR>
     * �T�j�@@�����`�F�b�N <BR>
     *      this.����==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_02184 <BR>
     * <BR>
     * �U�j�@@��n���`�F�b�N <BR>
     *      this.��n��==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException <BR>
     *         tag:   BUSINESS_ERROR_01079 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153B64902D6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ڋq�R�[�h�`�F�b�N 
        //this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B 

        if (this.accountCode == null)
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }
        
        //�Q�j�@@�����R�[�h�`�F�b�N 
        //this.�����R�[�h==null�̏ꍇ�A��O���X���[����B 
        if (this.mutualProductCode == null)
        {
            log.debug("�����R�[�h�̎w�肪����܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01252,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�̎w�肪����܂���B");
        }
        
        //�R�j�@@���Z���z�`�F�b�N 
        //�@@�R�|�P) this.���Z���z==null�̏ꍇ�A��O���X���[����B 
        if (this.settlePrice == null)
        {
            log.debug("���Z���z�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02334,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Z���z�����w��ł��B");
        }
        //  �R�|�Q) this.���Z���z�̌�����11���̏ꍇ�A��O���X���[����B   
        if (this.settlePrice.length() > 11)
        {
            log.debug("���Z���z��11���𒴂��܂����B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02335,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Z���z��11���𒴂��܂����B");
        }
        
        //�S�j�@@�������`�F�b�N 
        //this.������==null�̏ꍇ�A��O���X���[����B 
        if (this.orderBizDate == null)
        {
            log.debug("�����������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����������w��ł��B");
        }
        
        //�T�j�@@�����`�F�b�N 
        //this.����==null�̏ꍇ�A��O���X���[����B 
        if (this.executionTimestamp == null)
        {
            log.debug("�������������͂ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02184,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������������͂ł��B");
        }
        
        //�U�j�@@��n���`�F�b�N 
        //this.��n��==null�̏ꍇ�A��O���X���[����B 
        if (this.deliveryDate == null)
        {
            log.debug("��n�������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��n�������w��ł��B");
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF81440100
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
