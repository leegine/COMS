head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h���N�G�X�g(WEB3AdminSrvRegiDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
Revesion History : 2005/04/04 ���c ��(SRA) ���X�R�[�h3���`�F�b�N�Ή�
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiRigistDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�_�E�����[�h���N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃_�E�����[�h���N�G�X�g�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiDownloadRequest extends WEB3GenRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiDownloadRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_download";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (���X�R�[�h)
     */
    public String[] branchCode;
    
    /**
     * (�ڋq�R�[�h)
     */
    public String accountCode;
    
    /**
     * (�\�����I�敪)<BR>
     * 0:���p�@@1:�\���@@2:���I�i�{�\���j�@@3:���I�@@4:����@@6:�S��<BR>
     */
    public String applyLotteryDiv;
    
    /**
     * (�o�^�敪)<BR>
     * 0:�L���@@1:�����@@2:�S��<BR>
     */
    public String registDiv;
    
    /**
     * (�K�p�J�n���i���j)
     */
    public Date trialStartFrom;
    
    /**
     * (�K�p�J�n���i���j)
     */
    public Date trialStartTo;
    
    /**
     * (�\�����i���j)
     */
    public Date applyDateFrom;
    
    /**
     * (�\�����i���j)
     */
    public Date applyDateTo;
    
    /**
     * (�T�[�r�X���p�\�[�g�L�[)<BR>
     * �Ώۍ��ځᒊ�I���̏ꍇ��"�ڋq","�K�p�J�n��"<BR>
     * �@@�@@�@@�@@�@@�@@�ᒊ�I�L�̏ꍇ��"�ڋq","�\����"<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃_�E�����[�h���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4100634E03A4
     */
    public WEB3AdminSrvRegiDownloadRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��҃_�E�����[�h���X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 410063BE00F5
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiDownloadResponse();
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.�T�[�r�X�敪�̌������A2���ȊO�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) ���X�R�[�h�̃`�F�b�N<BR>
     *  2-1) this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B  
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-2) this.���X�R�[�h�̌���!=3���̏ꍇ�A��O���X���[����B 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * 3) �ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h!=null�ł���A��������6���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * <BR>
     * 4) �o�^�󋵋敪�̃`�F�b�N<BR>
     * 4-1) this.�o�^�󋵋敪==null�̏ꍇ�A��O���X���[����B
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01137<BR>
     * 4-2)�@@this.�o�^�󋵋敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�L��"<BR>
     * �@@�@@�@@"����"<BR>
     * �@@�@@�@@"�S��"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01138<BR>
     * <BR>
     * 5) �\�����I�敪�̃`�F�b�N<BR>
     * 5-1) this.�\�����I�敪==null�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01905<BR>
     * 5-2)�@@this.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"���p"<BR>
     * �@@�@@�@@"�\��"<BR>
     * �@@�@@�@@"���I�i�{�\���j"<BR>
     * �@@�@@�@@"���I"<BR>
     * �@@�@@�@@"���"<BR>
     * �@@�@@�@@"�S��"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00849<BR>
     * 6) �T�[�r�X���p�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@this.�T�[�r�X���p�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * @@throws WEB3BaseException
     * @@roseuid 410063BE0104
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1)�T�[�r�X�敪�̃`�F�b�N
        //1-1) this.�T�[�r�X�敪==null
        if(this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        //1-2) this.�T�[�r�X�敪
        if(this.serviceDiv.length() != 2)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //2) ���X�R�[�h�̃`�F�b�N
        //2-1) this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B 
        if(this.branchCode == null || this.branchCode.length == 0)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("���X�R�[�h==null�ł���.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //2-2)this.���X�R�[�h�̌���!=3���̏ꍇ�A��O���X���[����B 
        int l_intBranchCount = this.branchCode.length;
        for (int i = 0; i < l_intBranchCount; i++)
        {
            if(this.branchCode[i] == null || "".equals(this.branchCode[i].trim()))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("���X�R�[�h==null�ł���.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
            
            if (this.branchCode[i].length() != 3)
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("���X�R�[�h�G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }
        
        //3) �ڋq�R�[�h�̃`�F�b�N
        if(this.accountCode != null && !"".equals(this.accountCode.trim()) && this.accountCode.length() > 6)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�ڋq�R�[�h�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //4) �o�^�󋵋敪�̃`�F�b�N
        //4-1)this.�o�^�󋵋敪==null�̏ꍇ�A��O���X���[����
        if(this.registDiv == null || "".equals(this.registDiv.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01137,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�o�^�󋵋敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        //4-2)this.�o�^�󋵋敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����
        if(!(WEB3SrvRegiRigistDivDef.CHARGE.equals(this.registDiv)
            || WEB3SrvRegiRigistDivDef.FREE.equals(this.registDiv)
            || WEB3SrvRegiRigistDivDef.EVERYTHING.equals(this.registDiv)))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01138,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�o�^�󋵋敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //5) �\�����I�敪�̃`�F�b�N
        //5-1) this.�\�����I�敪==null�̏ꍇ�A��O���X���[����B 
        if(this.applyLotteryDiv == null || "".equals(this.applyLotteryDiv.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01905,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�\�����I�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        //5-2)�@@this.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        if(!(WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.APPLI.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.CANCEL.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION.equals(this.applyLotteryDiv)
            || WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(this.applyLotteryDiv)))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00849,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�\�����I�敪�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //6) �T�[�r�X���p�\�[�g�L�[�̃`�F�b�N 
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X���p�\�[�g�L�[�����w��ł�", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        log.exiting(STR_METHOD_NAME);        
    }
}
@
