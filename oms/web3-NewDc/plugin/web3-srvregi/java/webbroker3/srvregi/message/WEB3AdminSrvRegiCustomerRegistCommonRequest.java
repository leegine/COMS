head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�o�^���ʃ��N�G�X�g(WEB3AdminSrvRegiCustomerRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�o�^���ʃ��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�ύX���N�G�X�g���ʏ��N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerRegistCommonRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerRegistCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerRegistCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151453L;
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (���X�R�[�h)
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)
     */
    public String accountCode;
    
    /**
     * (�\����)
     */
    public Date applyDate;
    
    /**
     * (�K�p�J�n��)
     */
    public Date trialStartDate;
    
    /**
     * (�K�p�I����)
     */
    public Date trialEndDate;
    
    /**
     * (�o�^�敪)<BR>
     * 0:�L���@@1:����<BR>
     */
    public String registDiv;
    
    /**
     * (���p����)
     */
    public String chargeAmt;
    
    /**
     * (�o����)
     */
    public Date paymentDate;
    
    /**
     * (�T�[�r�X���p�Ǘ��Ҍڋq�o�^�ύX���N�G�X�g���ʏ��)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F2318600DA
     */
    public WEB3AdminSrvRegiCustomerRegistCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.�T�[�r�X�敪�̌�����2���ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) ���X�R�[�h�̃`�F�b�N<BR>
     *  2-1) this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-2) this.���X�R�[�h�̌�����3���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * 3) �ڋq�R�[�h�̃`�F�b�N<BR>
     *  3-1) this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     *  3-2) this.�ڋq�R�[�h�̌�����6���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * <BR>
     * 4) �K�p�J�n���̃`�F�b�N<BR>
     * �@@this.�K�p�J�n��==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * 5) �K�p�I�����̃`�F�b�N<BR>
     *  5-1) this.�K�p�I����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00838<BR>
     *  5-2) this.�K�p�I�������Athis.�K�p�J�n�����ȑO�̓��t�������ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * <BR>
     * 6) ���p�����̃`�F�b�N<BR>
     *  6-1) this.���p����!=null�ł���A�Z�b�g����Ă���l�����l�ȊO�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00842<BR>
     *  6-2) this.���p�����̌�����9���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00843<BR>
     * <BR>
     * 7) �\�����̃`�F�b�N<BR>
     *  7-1) this.�\����!=null�ł���A����this.�\������this.�K�p�J�n���̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00840<BR>
     * 8) �o�����̃`�F�b�N<BR>
     *  8-1) this.�o�^�敪="�L��"�ł���A����this.�o����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00879<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4DCE201AE
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X�敪�̃`�F�b�N
        //1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("1-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //1-2) this.�T�[�r�X�敪�̌�����2���ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //2) ���X�R�[�h�̃`�F�b�N
        //2-1) this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || "".equals(branchCode.trim()))
        {
            log.debug("2-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME); 
        }

        // 2-2) this.���X�R�[�h�̌�����3���̏ꍇ�A��O���X���[����B
        //U00871 start
        if (this.branchCode.length() != 3)
        {
            log.debug("2-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                getClass().getName() + STR_METHOD_NAME); 
        }
        //U00871 end

        //3) �ڋq�R�[�h�̃`�F�b�N
        //3-1) this.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B
        if (this.accountCode == null || "".equals(accountCode.trim()))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3-2) this.�ڋq�R�[�h�̌�����6���̏ꍇ�A��O���X���[����B
        //U00871 start
        if (this.accountCode.length() != 6)
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + STR_METHOD_NAME); 
        }
        //U00871 end

        // 4) �K�p�J�n���̃`�F�b�N
        //this.�K�p�J�n��==null�̏ꍇ�A��O���X���[����B
        if (this.trialStartDate == null)
        {
            log.debug("4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5) �K�p�I�����̃`�F�b�N
        //5-1) this.�K�p�I����==null�̏ꍇ�A��O���X���[����B
        if (this.trialEndDate == null)
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00838,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 5-2) this.�K�p�I�������Athis.�K�p�J�n�����ȑO�̓��t�������ꍇ�A]
        //U00892
        if (WEB3DateUtility.compareToSecond(this.trialEndDate, this.trialStartDate) < 0)
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6) ���p�����̃`�F�b�N
        //6-1) this.���p����!=null�ł���A�Z�b�g����Ă���l�����l�ȊO�̏ꍇ�A
        if (this.chargeAmt != null && !"".equals(chargeAmt.trim()) &&
            !WEB3StringTypeUtility.isNumber(this.chargeAmt))
        {
            log.debug("6-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00842,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6-2) this.���p�����̌�����9���̏ꍇ�A��O���X���[����B
        if (chargeAmt != null && this.chargeAmt.length() > 9)
        {
            log.debug("6-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00843,
                getClass().getName() + STR_METHOD_NAME);
        }

        //7) �\�����̃`�F�b�N       
        // 7-1)  this.�\����!=null�ł���A����this.�\������this.�K�p�J�n���̏ꍇ�A��O���X���[����B
        if (this.applyDate != null &&
            WEB3DateUtility.compareToSecond(this.applyDate, this.trialStartDate) > 0)
        {
            log.debug("7-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00840,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 8) �o�����̃`�F�b�N<BR>
        //8-1) this.�o�^�敪="�L���I"�ł���A����this.�o����==null�̏ꍇ�A��O���X���[����B
        if (WEB3PaymentDivDef.CHARGE.equals(this.registDiv) && this.paymentDate == null)
        {
            log.debug("8-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00879,
                getClass().getName() + STR_METHOD_NAME);
        }
                
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F49840196
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
