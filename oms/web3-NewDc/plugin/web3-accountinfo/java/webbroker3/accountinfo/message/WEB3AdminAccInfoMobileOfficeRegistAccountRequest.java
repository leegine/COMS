head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqظ���(WEB3AdminAccInfoMobileOfficeRegistAccountRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/12/14 ���� (���u) ���f��No.153
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3RegistStateDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqظ���)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋqظ���<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistAccount";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082112L;
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistAccountRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�J�n��)<BR>
     * �J�n���i���j<BR>
     */
    public Date startDate;

    /**
     * (�I����)<BR>
     * �I�����i���j<BR>
     */
    public Date endDate;

    /**
     * (�\���󋵋敪)<BR>
     * �\���󋵋敪<BR>
     * <BR>
     * ���@@�w��Ȃ��̏ꍇ��null<BR>
     * <BR>
     * 0�F�@@����҂�<BR>
     * 1�F�@@����҂��i�m�F���j<BR>
     * 2�F�@@����ς�<BR>
     */
    public String searchApplyStateDiv;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�l����<BR>
     * 1�F�@@�l����<BR>
     */
    public String accountType;

    /**
     * (�\�[�g�L�[)<BR>
     * ���q�l���\�[�g�L�[[] <BR>
     * �Ώۍ��ځF�\����(applyDate)�A���X�R�[�h�A�ڋq�R�[�h�A�������(judgementDate)<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;
    /**
     * @@roseuid 418F385A0138
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMobileOfficeRegistAccountResponse(this);
    }

    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B<BR>
     * �@@�Q�|�P�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�����������ڂ̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�ڋq�R�[�h�C�J�n���C�I�����̂��ׂĂ������͂̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01159<BR>
     * �@@�R�|�Q�j�@@�\���󋵋敪�ɓ��͂�����A<BR>
     * �s���ȃR�[�h�l�̏ꍇ�͗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01158<BR>
     * �@@�R�|�R�j�@@�J�n���C�I�����̂ǂ��炩����̂ݓ��͂���Ă����ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01160<BR>
     * �@@�R�|�S�j�@@�J�n���C�I�����ɗ������͂�����A<BR>
     * �i�J�n�� > �I�����j�ł���΁A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01151<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * �U�j�@@�\�[�g�L�[�̃`�F�b�N  <BR>
     * �@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BBUSINESS_ERROR_00231<BR>
     * �@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * �@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR> 
     * �@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B<BR>
     * �@@�@@  �E�\������<BR>
     * �@@�@@  �E���X�R�[�h<BR>
     * �@@�@@  �E�ڋq�R�[�h<BR>
     * �@@�@@  �E�������<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �V�j�@@�����敪������ <BR>
     * �@@�����敪 != null �̏ꍇ�A�ȉ����������s���B <BR>
     * �@@�V�|�P�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01303<BR>
     * @@throws WEB3BaseException
     * @@roseuid 414974A201A2
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || "".equals(this.branchCode))
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName(),
                " ���X�R�[�h==null�ł���");
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
        //�����͂�����ꍇ�݈̂ȉ��̃`�F�b�N���s���B<BR>
        //�Q�|�P�j�@@������6�łȂ��ꍇ�A��O���X���[����B
        if (this.accountCode != null && !"".equals(this.accountCode))
        {
            
            if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
            {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName(),
                    " ������6�łȂ��ꍇ");
            }
            //�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
            else if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName(),
                    " �ڋq�R�[�h�ɐ����ȊO�̕���������̏ꍇ�G���[�ł���");
            }
        }
        
        //�R�j�@@�����������ڂ̃`�F�b�N<BR>
        //�R�|�P�j�@@�ڋq�R�[�h�C�J�n���C�I�����̂��ׂĂ������͂̏ꍇ�A��O���X���[����B
        if ((this.accountCode == null || "".equals(this.accountCode))
            && this.startDate == null 
            && this.endDate == null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01159,
                this.getClass().getName(),
                " �ڋq�R�[�h�C�J�n���C�I�����̂��ׂĂ������͂ł�");
        }
        
        //�R�|�Q�j�@@�\���󋵋敪�ɓ��͂�����A�s���ȃR�[�h�l�̏ꍇ�͗�O���X���[����B
        if(this.searchApplyStateDiv != null && !"".equals(this.searchApplyStateDiv))
        {
            
            if(!WEB3RegistStateDivDef.WAIT_DECISION.equals(this.searchApplyStateDiv)
                && !WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING.equals(this.searchApplyStateDiv)
                && !WEB3RegistStateDivDef.DECISION_COMPLETE.equals(this.searchApplyStateDiv))
            {
             
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01158,
                    this.getClass().getName(),
                    " ���茋�ʋ敪�̃R�[�h�l���s���ł�");
            }
        }

        //�R�|�R�j�@@�J�n���C�I�����̂ǂ��炩����̂ݓ��͂���Ă����ꍇ�A��O���X���[����B
        if (this.startDate == null && this.endDate != null
            || this.endDate == null && this.startDate != null)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01160,
                this.getClass().getName(),
                " �J�n���C�I�����̂ǂ��炩����̂ݓ��͂ł�");
        }

        //�R�|�S�j�@@�J�n���C�I�����ɗ������͂����� �i�J�n�� > �I�����j�ł���΁A��O���X���[����B
        if (this.startDate != null 
            && this.endDate != null)
        {
            if (WEB3DateUtility.compareToDay(this.startDate, this.endDate) > 0)
            {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01151,
                    this.getClass().getName(),
                    " �J�n���C�I�����ɗ������͂�����A�i�J�n�� > �I�����j�ł�");
            }
        }

        //�S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
        //�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }

        //�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName(),
                " �v���y�[�W�ԍ��������ȊO�̒l�ł���");
        }

        //�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(this.pageIndex) <= 0)
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName(),
                " �v���y�[�W�ԍ��̒l��0�ȉ��ł�");
        }

        //�T�j�@@�y�[�W���\���s���`�F�b�N <BR>
        //�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName(),
                " �y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ");
        }

        //�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName(),
                " �y�[�W���\���s���������ȊO�̒l�ł�");
        }

        //�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(this.pageSize) <= 0)
        {

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName(),
                " �y�[�W���\���s���̒l��0�ȉ��ł�");
        }
        
        //�U�j�@@�\�[�g�L�[�̃`�F�b�N  
		//�@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231 ,
                    this.getClass().getName(),
                    " �\�[�g�L�[��������");
        }
		//�@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName(),
                    " �\�[�g�L�[�̗v�f�� == 0");
        }
		//�@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        int l_len = this.sortKeys.length;
        for (int i = 0; i < l_len; i++)
        {
    		//�@@�@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
        	this.sortKeys[i].validate();
    		//�@@�@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
    		//�@@�@@�@@�@@�@@�@@�@@�E�\������
    		//�@@�@@�@@�@@�@@�@@�@@�E���X�R�[�h
    		//�@@�@@�@@�@@�@@�@@�@@�E�ڋq�R�[�h
    		//�@@�@@�@@�@@�@@�@@�@@�E�������
        	String l_strKeyItem = this.sortKeys[i].keyItem;
        	if (!WEB3AccInfoKeyItemDef.APPLY_DATE.equals(l_strKeyItem)
        			&& !WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_strKeyItem)
					&& !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_strKeyItem)
					&& !WEB3AccInfoKeyItemDef.JUDGEMENT_DATE.equals(l_strKeyItem))
        	{
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName(),
                        " �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ");
        	}
        	
        }

        //�V�j�@@�����敪������
        //�����敪 != null �̏ꍇ�A�ȉ����������s���B
        //�V�|�P�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
        if (this.accountType != null)
        {
            if (!WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(this.accountType)
               && !WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(this.accountType))
            {
                log.debug("[�����敪] = " + accountType);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01303,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "�����敪�̕s���ȃR�[�h�l�̏ꍇ");
            }
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
