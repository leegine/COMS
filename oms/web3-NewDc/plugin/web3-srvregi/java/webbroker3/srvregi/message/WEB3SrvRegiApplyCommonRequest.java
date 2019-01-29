head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�\�����ʃ��N�G�X�g(WEB3SrvRegiApplyCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 ���o�� �V�K�쐬
Revesion History : 2007/06/05 �Ј��� �d�l�ύX���f��No.248
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiApplyKindDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�\�����ʃ��N�G�X�g)<BR>
 * �T�[�r�X���p�\�����ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiApplyCommonRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiApplyCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_applyCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151437L;
    
    /**
     * (ID)<BR>
     * �T�[�r�X�敪<BR>
     */
    public String serviceDiv;
    
    /**
     * (���p����ID)
     */
    public String chargeId;
    
    /**
     * (���D�z)
     */
    public String bidAmt;
    
    /**
     * (�\����ʋ敪)<BR>
     * 0:�ʏ�\���@@1:�p���\���@@2:���p�\�� 3:�����\��<BR>
     */
    public String applyKindDiv;
    
    /**
     * (����\���敪)<BR>
     * 1:�K�p�I��������<BR>
     */
    public String specialDiv;
    
    /**
     * (���������\���敪)<BR>
     * null or '0' �F�ʏ�\���@@'1'�F���������\��<BR>
     */
    public String freeAttributeApplyDiv;
    
    /**
     * (�T�[�r�X���p�\�����ʃ��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F254DB0251
     */
    public WEB3SrvRegiApplyCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) ID�̃`�F�b�N<BR>
     *  1-1) this.ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *  1-2) this.ID�̌������A2���܂���4���ȊO�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 2) ���D�z�̃`�F�b�N<BR>
     *  2-1) this.���D�z!=null�ł���A�����l�ȊO���Z�b�g����Ă���ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00977<BR>
     *  2-2) this.���D�z!=null�ł���A����this.���D�z�̌�����9���̏ꍇ�A<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00978<BR>
     * ��O���X���[����B<BR>
     * <BR>
     * 3) �\����ʋ敪�̃`�F�b�N<BR>
     *  3-1) this.�\����ʋ敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00979<BR>
     *  3-2) this.�\����ʋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�ʏ�\��"<BR>
     * �@@�@@�@@"�p���\��"<BR>
     * �@@�@@�@@"���p�\��"<BR>
     *       "�����\��"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00980<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4DC010102
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //1-1) this.ID==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //1-2) this.ID�̌������A2���܂���4���ȊO�̏ꍇ�A��O���X���[����B
        //U00871
        if (this.serviceDiv.length() != 2)
        {
            log.debug("*****************************");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        // 2-1) this.���D�z!=null�ł���A�����l�ȊO���Z�b�g����Ă���ꍇ�A
        if ((this.bidAmt != null && bidAmt.trim().length() != 0) &&
            !WEB3StringTypeUtility.isNumber(this.bidAmt))
        {
            log.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00977,
                getClass().getName() + STR_METHOD_NAME);    
        }
        
        //2-2) this.���D�z!=null�ł���A����this.���D�z�̌�����9���̏ꍇ
        if ((this.bidAmt != null && bidAmt.trim().length() != 0) &&
            this.bidAmt.length() > 9)
        {
            log.debug("$$$$$$$$$$$$$$$$$$$$$$$$");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00978,
                getClass().getName() + STR_METHOD_NAME);    
        }
        
        //3-1) this.�\����ʋ敪==null�̏ꍇ�A��O���X���[����B
        if (this.applyKindDiv == null || "".equals(applyKindDiv.trim())) 
        {
            log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00979,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        //3-2) this.�\����ʋ敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(this.applyKindDiv) &&
            !WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(this.applyKindDiv) &&
            !WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(this.applyKindDiv) &&
            !WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(this.applyKindDiv))
        {
            log.debug("#############################");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00980,
                getClass().getName() + STR_METHOD_NAME); 
        }
               
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F4441005D
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
