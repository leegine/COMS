head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�N�G�X�g(WEB3AdminSrvRegiCustomerReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
Revesion History : 2007/06/19 �����Q(���u) �d�l�ύX���f��No.249
Revesion History : 2007/06/21 �����Q(���u) �d�l�ύX���f��No.267
Revesion History : 2007/06/26 �����Q(���u) �d�l�ύX���f��No.273
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiRigistDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�N�G�X�g�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiCustomerReferenceRequest extends WEB3GenRequest 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerReferenceRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (�T�[�r�X���p�\�[�g�L�[)<BR>
     * �ᒊ�I���̏ꍇ��<BR>
     * �@@���X�^�ڋq�^�\�������敪�^�\�����I�敪�^�K�p�J�n���^<BR>
     * �@@�K�p�I�����^�o�^�敪�^���p�����^�ŏI�X�V���^�ŏI�X�V��"<BR>
     * <BR>
     * �ᒊ�I�L�̏ꍇ��<BR>
     * �@@���X�^�ڋq�^�\�����I�敪�^�\�����^<BR>
     * �@@�K�p�J�n���^�K�p�I�����^�o�^�敪�^���p�����^<BR>
     * �@@�ŏI�X�V���^�ŏI�X�V��<BR>
     */
    public WEB3SrvRegiSortKey[] sortKeys;
    
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
     * 0:���p�@@1:�\���@@2:���I�^�{�\���@@3:���I�@@4:����@@6:�S�ā@@7:�����Ώہ@@8:�\���s�@@9:�S�āi�����p�j<BR>
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
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w�� <BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * 1�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;
    
    /**
     * (�T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE5A95014E
     */
    public WEB3AdminSrvRegiCustomerReferenceRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ꗗ�ύX�Ɖ�X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE5AD501FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiCustomerReferenceResponse();
    }
    
    /**
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
     *  2-1) this.���X�R�[�h!=null�ł���A��������3���̏ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *  2-2) this.�ڋq�R�[�h!=null�ł���A����this.���X�R�[�h==null�̏ꍇ�A<BR> 
     * �@@�@@�܂���this.���X�R�[�h�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * 3) �ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@this.�ڋq�R�[�h!=null�ł���A��������6���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * <BR>
     * 4) �o�^�󋵋敪�̃`�F�b�N<BR>
     * �@@this.�o�^�󋵋敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"�L��"<BR>
     * �@@�@@�@@"����"<BR>
     * �@@�@@�@@"�S��"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01138<BR>
     * <BR>
     * 5) �\�����I�敪�̃`�F�b�N<BR>
     * �@@this.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"���p"<BR>
     * �@@�@@�@@"�\��"<BR>
     * �@@�@@�@@"���I�i�{�\���j"<BR>
     * �@@�@@�@@"���I"<BR>
     * �@@�@@�@@"���"<BR>
     * �@@�@@�@@"�S��"<BR>
     * �@@�@@�@@"�����Ώ�"<BR>
     * �@@�@@�@@"�\���s��"<BR>
     * �@@�@@�@@"�S�āi�����p�j"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00849<BR>
     * <BR>
     * 6) �T�[�r�X���p�\�[�g�L�[�̃`�F�b�N<BR>
     *  6-1) this.�T�[�r�X���p�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     *  6-2) this.�T�[�r�X���p�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     *  6-3) this.�T�[�r�X���p�\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@6-3-1) this.�T�[�r�X���p�\�[�g�L�[.�L�[����==null�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00085<BR>
     * �@@6-3-2) this.�T�[�r�X���p�\�[�g�L�[.�����^�~��==null�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00087<BR>
     * �@@6-3-3) this.�T�[�r�X���p�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �@@�@@�@@"A:����"<BR>
     * �@@�@@�@@"D:�~��"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * 7) �v���y�[�W�ԍ��`�F�b�N <BR>
     *  7-1) this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     *  7-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * 8) �y�[�W���\���s���`�F�b�N <BR>
     *  8-1) this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     *  8-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EE5AD50219
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1) �T�[�r�X�敪�̃`�F�b�N
        // 1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
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

        // 1-2) this.�T�[�r�X�敪�̌������A2���ȊO�̏ꍇ�A��O���X���[����B
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
        //2-1) this.���X�R�[�h!=null�ł���A��������3���̏ꍇ�A��O���X���[����B 
        if(this.branchCode != null)
        {
            int l_intBranchCount = this.branchCode.length;
            for (int i = 0; i < l_intBranchCount; i++)
            {
                if (this.branchCode[i] != null && this.branchCode[i].length() != 3)//U00871
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
        }
        //2-2) this.�ڋq�R�[�h!=null�ł���A����this.���X�R�[�h==null�̏ꍇ�A�܂���this.���X�R�[�h�̗v�f��==0�̏ꍇ�A��O���X���[����B 
        if (this.accountCode != null 
            && !"".equals(this.accountCode.trim())
            && (this.branchCode == null || this.branchCode.length == 0))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("���X�R�[�h�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //3) �ڋq�R�[�h�̃`�F�b�N
        if(this.accountCode != null && !"".equals(this.accountCode.trim()) && this.accountCode.length() != 6)//U00871
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
        if(this.registDiv == null
            || "".equals(this.registDiv.trim())
            || !(WEB3SrvRegiRigistDivDef.CHARGE.equals(this.registDiv)
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
        if(this.applyLotteryDiv == null 
            || "".equals(this.applyLotteryDiv.trim())
            || !(WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.CANCEL.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.EVERYTHING.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.FREE_OBJECT.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.CANNOT_APPLI.equals(this.applyLotteryDiv)
                || WEB3SrvRegiAppliLotDivDef.EVERYTHING_ATTRIBUTE.equals(this.applyLotteryDiv)))
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
        // 6-1) this.�T�[�r�X���p�\�[�g�L�[==null�̏ꍇ�A��O���X���[����B
        if(this.sortKeys == null)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X���p�\�[�g�L�[�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 6-2) this.�T�[�r�X���p�\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B
        if(this.sortKeys.length == 0)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X���p�\�[�g�L�[�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 6-3) this.�T�[�r�X���p�\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B
        WEB3SrvRegiSortKey l_sortKey = null;
        int l_intSortKeyCount = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeyCount; i++)
        {
            l_sortKey = sortKeys[i]; 

            //�@@6-3-1) this.�T�[�r�X���p�\�[�g�L�[.�L�[����==null�̏ꍇ�A
            if(l_sortKey.keyItem == null || "".equals(l_sortKey.keyItem.trim()))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�T�[�r�X���p�\�[�g�L�[�G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            //�@@6-3-2) this.�T�[�r�X���p�\�[�g�L�[.�����^�~��==null�̏ꍇ�A
            if(l_sortKey.ascDesc == null || "".equals(l_sortKey.ascDesc.trim()))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�T�[�r�X���p�\�[�g�L�[�G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }

            //�@@6-3-3) this.�T�[�r�X���p�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A
            if(!WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc)
                && !WEB3AscDescDef.DESC.equals(l_sortKey.ascDesc))
            {
                WEB3BaseException l_e = 
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                        this.getClass().getName() + STR_METHOD_NAME);
                log.debug("�T�[�r�X���p�\�[�g�L�[�G���[.", l_e);
                log.exiting(STR_METHOD_NAME);
                throw l_e;
            }
        }

        //7) �v���y�[�W�ԍ��`�F�b�N 
        // 7-1) this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B 
        if(this.pageIndex == null || "".equals(this.pageIndex.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�v���y�[�W�ԍ��G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 7-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B 
        if(!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�v���y�[�W�ԍ��G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //8) �y�[�W���\���s���`�F�b�N 
        // 8-1) this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B 
        if(this.pageSize == null || "".equals(this.pageSize.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�y�[�W���\���s���G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        // 8-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B 
        if(!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("�y�[�W���\���s���G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
