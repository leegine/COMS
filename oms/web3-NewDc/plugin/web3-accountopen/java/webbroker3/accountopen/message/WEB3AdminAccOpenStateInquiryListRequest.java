head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g(WEB3AdminAccOpenStateInquiryListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 �s�p (���u) �V�K�쐬
Revesion History : 2009/08/13 ���g (���u) �d�l�ύX���f��No.163
Revesion History : 2010/02/10 ���g (���u) �d�l�ύX���f��No.216
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.accountopen.define.WEB3AccOpenDeleteFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenForeignerFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenInsiderDivDef;
import webbroker3.accountopen.define.WEB3AccOpenReceiveFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenTaxTypeDivDef;
import webbroker3.accountopen.define.WEB3AccountOpenKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PrintFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݏ󋵖⍇���ꗗ���N�G�X�g<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
 
public class WEB3AdminAccOpenStateInquiryListRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryListRequest.class);
 
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081605L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * �ڋq�R�[�h�i���j<BR>
     */
    public String accountCodeFrom;

    /**
     * (�ڋq�R�[�h�i���j)<BR>
     * �ڋq�R�[�h�i���j<BR>
     */
    public String accountCodeTo;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�l�A�J�E���g�@@1�F�@@�l�A�J�E���g<BR>
     */
    public String accountType;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountFamilyNameKana;

    /**
     * (�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j<BR>
     */
    public String accountNameKana;

    /**
     * (�����������i���j)<BR>
     * �����������i���j<BR>
     */
    public Date infoClaimDateFrom;

    /**
     * (�����������i���j)<BR>
     * �����������i���j<BR>
     */
    public Date infoClaimDateTo;

    /**
     * (SONAR���M���i���j)<BR>
     * SONAR���M���i���j<BR>
     */
    public Date sonarSendDateFrom;

    /**
     * (SONAR���M���i���j)<BR>
     * SONAR���M���i���j<BR>
     */
    public Date sonarSendDateTo;

    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR>
     */
    public Date accountOpenDateFrom;

    /**
     * (�����J�ݓ��i���j)<BR>
     * �����J�ݓ��i���j<BR>
     */
    public Date accountOpenDateTo;

    /**
     * (�����J�ݏ�)<BR>
     * �����J�ݏ�<BR>
     * <BR>
     * 0�F�@@DEFAULT�i���J�݁j<BR>
     * 1�F�@@�J�ݒ�<BR>
     * 2�F�@@�G���[����<BR>
     * 3�F�@@�J�ݍ�<BR>
     */
    public String accountOpenStateDiv;

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
     * (�폜�t���O)<BR>
     * �폜�t���O<BR>
     * <BR>
     * 1�FTRUE/�����i�폜�j<BR> 
     * 0�FFALSE/�L���iDEFAULT�j<BR>
     */
    public String deleteFlag;

    /**
     * (����t���O)<BR>
     * ����t���O<BR>
     * <BR>
     * 0�F�����<BR>
     * 1�F�����<BR>
     * 3�F�������iDEFAULT�j<BR>
     */
    public String printFlag;

    /**
     * (��̃t���O)<BR>
     * ��̃t���O<BR>
     * <BR>
     * 1�FTRUE/��̍�<BR>
     * 0�FFALSE/����́iDEFAULT�j<BR>
     */
    public String receiveFlag;

    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * <BR>
     * 0�F��ʌ���<BR>
     * 1�F�������<BR>
     */
    public String taxTypeDiv;

    /**
     * (�O���l�t���O)<BR>
     * �O���l�t���O<BR>
     * <BR>
     * 1�FTRUE/���{�ȊO<BR>
     * 0�FFALSE/���{�iDEFAULT�j<BR>
     */
    public String foreignerFlag;

    /**
     * (�����ғo�^�敪)<BR>
     * �����ғo�^�敪<BR>
     * <BR>
     * 0�F�o�^�Ȃ�<BR>
     * 1�F�o�^����<BR>
     */
    public String insiderDiv;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3AccOpenSortKey[] sortKeys;

    /**
     * @@roseuid 41B45E7600DA
     */
    public WEB3AdminAccOpenStateInquiryListRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenStateInquiryListResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����������i���j�C�����������i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�P�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01326<BR>
     * <BR>
     * �Q�j�@@SONAR���M���i���j�CSONAR���M���i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�Q�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01327<BR>
     * <BR>
     * �R�j�@@�����J�ݓ��i���j�C�����J�ݓ��i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�R�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01328<BR>
     * <BR>
     * �S�j�@@�ڋq�R�[�h�i���j�C�ڋq�R�[�h�i���j�̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B���i���j�C<BR>
     * �i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00795<BR>
     * �@@�S�|�Q�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01330<BR>
     * �@@�S�|�R�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01331<BR>
     * <BR>
     * �T�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00090<BR>
     * �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �U�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00091<BR>
     * �@@�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00092<BR>
     * �@@�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N <BR>
     * �@@�V�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * �@@�V�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * �@@�V�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�V�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�V�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A <BR>
     * ��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00086<BR>
     * �@@�@@�@@�@@ �����J�ݏ�.���X�R�[�h <BR>
     * �@@�@@�@@�@@ �����J�ݏ�.�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@ �����J�ݏ�.���ʃR�[�h <BR>
     * �@@�@@�@@�@@ �����J�ݏ�.����������<BR>
     * �@@�@@�@@�@@ �����J�ݏ�.�����J�ݓ�<BR>
     * <BR>
     * �W�j�@@�폜�t���O�̃`�F�b�N<BR>
     * �@@�W�|�P�j�@@�폜�t���O != null ���� �폜�t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03173<BR>
     * �@@�@@�@@1�FTRUE/�����i�폜�j<BR>
     * �@@�@@�@@0�FFALSE/�L���iDEFAULT�j<BR>
     * <BR>
     * �X�j�@@����t���O�̃`�F�b�N<BR>
     * �@@�X�|�P�j�@@����t���O != null ���� ����t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03174<BR>
     * �@@�@@�@@0�F�����<BR>
     * �@@�@@�@@1�F�����<BR>
     * �@@�@@�@@3�F�������iDEFAULT�j<BR>
     * <BR>
     * �P�O�j�@@��̃t���O�̃`�F�b�N<BR>
     * �@@�P�O�|�P�j�@@��̃t���O != null ���� ��̃t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03175<BR>
     * �@@�@@�@@1�FTRUE/��̍�<BR>
     * �@@�@@�@@0�FFALSE/����́iDEFAULT�j<BR>
     * <BR>
     * �P�P�j�@@��������敪�̃`�F�b�N<BR>
     * �@@�P�P�|�P�j�@@��������敪 != null ���� ��������敪�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02114<BR>
     * �@@�@@�@@0�F��ʌ���<BR>
     * �@@�@@�@@1�F�������<BR>
     * <BR>
     * �P�Q�j�@@�O���l�t���O�̃`�F�b�N<BR>
     * �@@�P�Q�|�P�j�@@�O���l�t���O != null ���� �O���l�t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03176<BR>
     * �@@�@@�@@1�FTRUE/���{�ȊO<BR>
     * �@@�@@�@@0�FFALSE/���{�iDEFAULT�j<BR>
     * <BR>
     * �P�R�j�@@�����ғo�^�敪�̃`�F�b�N<BR>
     * �@@�P�R�|�P�j�@@�����ғo�^�敪 != null ���� �����ғo�^�敪�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03186<BR>
     * �@@�@@�@@1�FTRUE/�o�^����<BR>
     * �@@�@@�@@0�FFALSE/�o�^�Ȃ��iDEFAULT�j<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C631D035E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����������i���j�C�����������i���j�̃`�F�b�N 
        //  ���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂� 
        if (this.infoClaimDateFrom != null && this.infoClaimDateTo != null) 
        {
            //�P�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
            int l_intFlag = WEB3DateUtility.compareToSecond(this.infoClaimDateFrom, this.infoClaimDateTo);
            if (l_intFlag > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01326,
                    getClass().getName() + STR_METHOD_NAME,
                    " �����������i���j�͎����������i���j���傫���ł��B" + 
                    this.infoClaimDateFrom.toString() + ", " + 
                    this.infoClaimDateTo.toString()); 
            }
        }

        //�Q�j�@@SONAR���M���i���j�CSONAR���M���i���j�̃`�F�b�N 
        //  ���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂� 
        if (this.sonarSendDateFrom != null && this.sonarSendDateTo != null) 
        {
            //�Q�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
            int l_intFlag = WEB3DateUtility.compareToSecond(this.sonarSendDateFrom, this.sonarSendDateTo);
            if (l_intFlag > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01327,
                    getClass().getName() + STR_METHOD_NAME,
                    " SONAR���M���i���j��SONAR���M���i���j���傫���ł��B" + 
                    this.sonarSendDateFrom.toString() + ", " + 
                    this.sonarSendDateTo.toString()); 
            }
        }
         

        //�R�j�@@�����J�ݓ��i���j�C�����J�ݓ��i���j�̃`�F�b�N 
        //  ���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂� 
        if (this.accountOpenDateFrom != null && this.accountOpenDateTo != null) 
        {
            //�R�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
            int l_intFlag = WEB3DateUtility.compareToSecond(this.accountOpenDateFrom, this.accountOpenDateTo);
            if (l_intFlag > 0)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01328,
                    getClass().getName() + STR_METHOD_NAME,
                    " �����J�ݓ��i���j�͌����J�ݓ��i���j���傫���ł��B" + 
                    this.accountOpenDateFrom.toString() + ", " + 
                    this.accountOpenDateTo); 
            }
        }
         
        //�S�j�@@�ڋq�R�[�h�i���j�C�ڋq�R�[�h�i���j�̃`�F�b�N 
        //�S�|�Q�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (this.accountCodeFrom != null && !WEB3StringTypeUtility.isNumber(this.accountCodeFrom))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01330,
                getClass().getName() + STR_METHOD_NAME,
                " �ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B" + 
                this.accountCodeFrom); 
        }
        
        //�S�|�R�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (this.accountCodeTo != null && !WEB3StringTypeUtility.isNumber(this.accountCodeTo))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01331,
                getClass().getName() + STR_METHOD_NAME,
                " �ڋq�R�[�h�i���j�̒l�������ȊO�̒l�ł��B" + 
                this.accountCodeTo); 
        }
        
        //�S�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂� 
        if (this.accountCodeFrom != null && this.accountCodeTo != null)
        {
            int l_intFrom = (int)Double.parseDouble(this.accountCodeFrom);
            int l_intTo = (int)Double.parseDouble(this.accountCodeTo);
            if (l_intFrom > l_intTo)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00795,
                    getClass().getName() + STR_METHOD_NAME,
                    " �ڋq�R�[�h�i���j�͌ڋq�R�[�h�i���j���傫���ł��B" + 
                    this.accountCodeFrom + ", " + this.accountCodeTo); 
            }
        }

        //�T�j�@@�v���y�[�W�ԍ��`�F�b�N 
        //�T�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B 
        if (this.pageIndex == null || "".equals(this.pageIndex.trim()))
        {
            this.pageIndex  = "1";
        }
        //�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                " �v���y�[�W�ԍ��������ȊO�̒l�ł���B" + 
                this.pageIndex); 
        }
        //�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        if ((int)Double.parseDouble(this.pageIndex) < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME,
                " �v���y�[�W�ԍ��̒l��0�ȉ��ł��B" + 
                this.pageIndex); 
        }

        //�U�j�@@�y�[�W���\���s���`�F�b�N 
        //�U�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.pageSize == null || "".equals(this.pageSize.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�B" + 
                this.pageIndex); 
        }
        //�U�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s���͐����ȊO�̒l�ł��B" + 
                this.pageSize);
        }
        //�U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B         
        if ((int)Double.parseDouble(this.pageSize) < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s���̒l��0�ȉ��ł��B" + 
                this.pageSize);
        }
        
        //�V�j�@@�\�[�g�L�[�̃`�F�b�N 
        //�V�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[��null�̒l�ł���B" + 
                this.sortKeys);
        }
        //�V�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�̗v�f�����O�ł���B" + 
                this.sortKeys.length);
        }
        //�V�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3AccOpenSortKey l_accOpenSortKey = this.sortKeys[i];
            //�V�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
            l_accOpenSortKey.validate();
            //�V�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
            //�����J�ݏ�.���X�R�[�h 
            //�����J�ݏ�.�ڋq�R�[�h 
            //�����J�ݏ�.���ʃR�[�h 
            //�����J�ݏ�.���������� 
            //�����J�ݏ�.�����J�ݓ�
            if (!(WEB3AccountOpenKeyItemDef.BRANCH_CODE.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.ACCOUNT_CODE.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.REQUEST_NUMBER.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.INFO_CLAIM_DATE.equals(l_accOpenSortKey.keyItem) ||
                WEB3AccountOpenKeyItemDef.ACCOUNT_OPEN_DATE.equals(l_accOpenSortKey.keyItem))) 
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    " �L�[���ڂɖ������A�����敪�A�������Ԃ̍��ږ��ȊO�̒l�����݂��Ă��܂��B" + 
                    l_accOpenSortKey.keyItem);         
            }
        }

        //�W�j�@@�폜�t���O�̃`�F�b�N
        //�W�|�P�j�@@�폜�t���O != null ���� �폜�t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        //1�FTRUE/�����i�폜�j
        //0�FFALSE/�L���iDEFAULT�j
        if (this.deleteFlag != null
            && !(WEB3AccOpenDeleteFlagDef.DEFAULT.equals(this.deleteFlag)
            || WEB3AccOpenDeleteFlagDef.DELETE.equals(this.deleteFlag)))
        {
            log.debug("�폜�t���O�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03173,
                getClass().getName() + STR_METHOD_NAME,
                "�폜�t���O�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�X�j�@@����t���O�̃`�F�b�N
        //�X�|�P�j�@@����t���O != null ���� ����t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        //0�F�����
        //1�F�����
        //3�F�������iDEFAULT�j
        if (this.printFlag != null
            && !(WEB3PrintFlagDef.ENABLE_PRINT.equals(this.printFlag)
            || WEB3PrintFlagDef.PRINT_COMPLETE.equals(this.printFlag)
            || WEB3PrintFlagDef.DEFAULT.equals(this.printFlag)))
        {
            log.debug("����t���O�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03174,
                getClass().getName() + STR_METHOD_NAME,
                "����t���O�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�P�O�j�@@��̃t���O�̃`�F�b�N
        //�P�O�|�P�j�@@��̃t���O != null ���� ��̃t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        //1�FTRUE/��̍�
        //0�FFALSE/����́iDEFAULT�j
        if (this.receiveFlag != null
            && !(WEB3AccOpenReceiveFlagDef.RECEIVED.equals(this.receiveFlag)
            || WEB3AccOpenReceiveFlagDef.DEFAULT.equals(this.receiveFlag)))
        {
            log.debug("��̃t���O�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03175,
                getClass().getName() + STR_METHOD_NAME,
                "��̃t���O�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�P�P�j�@@��������敪�̃`�F�b�N
        //�P�P�|�P�j�@@��������敪 != null ���� ��������敪�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        //0�F��ʌ���
        //1�F�������
        if (this.taxTypeDiv != null
            && !(WEB3AccOpenTaxTypeDivDef.NORMAL.equals(this.taxTypeDiv)
            || WEB3AccOpenTaxTypeDivDef.SPECIAL.equals(this.taxTypeDiv)))
        {
            log.debug("��������敪������`�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02114,
                getClass().getName() + STR_METHOD_NAME,
                "��������敪������`�̒l�ł��B");
        }

        //�P�Q�j�@@�O���l�t���O�̃`�F�b�N
        //�P�Q�|�P�j�@@�O���l�t���O != null ���� �O���l�t���O�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        //1�FTRUE/���{�ȊO
        //0�FFALSE/���{�iDEFAULT�j
        if (this.foreignerFlag != null
            && !(WEB3AccOpenForeignerFlagDef.DEFAULT.equals(this.foreignerFlag)
            || WEB3AccOpenForeignerFlagDef.FOREIGNER.equals(this.foreignerFlag)))
        {
            log.debug("�O���l�t���O���݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03176,
                getClass().getName() + STR_METHOD_NAME,
                "�O���l�t���O���݂��Ȃ��R�[�h�l�ł��B");
        }
        log.exiting(STR_METHOD_NAME);

        //�P�R�j�@@�����ғo�^�敪�̃`�F�b�N
        //�����ғo�^�敪 != null ���� �����ғo�^�敪�����L�̍��ڈȊO�̏ꍇ�A ��O���X���[����B
        if (this.insiderDiv != null
            && !(WEB3AccOpenInsiderDivDef.TRUE.equals(this.insiderDiv)
                || WEB3AccOpenInsiderDivDef.DEFAULT.equals(this.insiderDiv)))
        {
            log.debug("�����ғo�^�敪�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03186,
                getClass().getName() + STR_METHOD_NAME,
                "�����ғo�^�敪�����݂��Ȃ��R�[�h�l�ł��B");
        }
    }

}
@
