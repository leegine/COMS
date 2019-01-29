head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.57.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g(WEB3AdminAccOpenApplyDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import java.util.Date;

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
 * (�Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDownloadRequest extends WEB3GenRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_applyDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081613L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�l�A�J�E���g�@@1�F�@@�l�A�J�E���g<BR>
     */
    public String accountType;

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
     * (���ʃR�[�h�i���j)<BR>
     * ���ʃR�[�h�i���j<BR>
     */
    public String requestNumberFrom;

    /**
     * (���ʃR�[�h�i���j)<BR>
     * ���ʃR�[�h�i���j<BR>
     */
    public String requestNumberTo;

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
     * (�_�E�����[�h����)<BR>
     * �_�E�����[�h����<BR>
     */
    public String downloadNumber;
    
    /**
     * (�����J�݋敪)<BR>
     * �����J�݋敪<BR>
     * <BR>
     * 0�F�������J�݁@@1�F�����J�ݍς�<BR>
     * ��null��<BR>
     */    
    public String accountOpenDiv;

    /**
     * (�����I�v�V�����敪)<BR>
     * �����I�v�V�����敪<BR>
     * <BR>
     * 0�F�Ȃ��@@1�F����<BR>
     * ��null��<BR>
     */    
    public String searchOptionDiv;
    
    /**
     * @@roseuid 41B45E7B0222
     */
    public WEB3AdminAccOpenApplyDownloadRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyDownloadResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h[]�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�����敪�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00604<BR>
     * �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00605<BR>
     * <BR>
     * �R�j�@@�����������i���j�C�����������i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�R�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01326<BR>
     * <BR>
     * �S�j�@@�����J�ݓ��i���j�C�����J�ݓ��i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�S�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01328<BR>
     * <BR>
     * �T�j�@@�ڋq�R�[�h�i���j�C�ڋq�R�[�h�i���j�̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     * ���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00795<BR>
     * �@@�T�|�Q�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01330<BR>
     * �@@�T�|�R�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01331<BR>
     * <BR>
     * �U�j�@@���ʃR�[�h�i���j�C���ʃR�[�h�i���j�̃`�F�b�N<BR>
     * �@@���i���j�C�i���j�̗����ɓ��͂�����ꍇ�̂�<BR>
     * �@@�U�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01333<BR>
     * �@@�U�|�Q�j�@@���ʃR�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01334<BR>
     * �@@�U�|�R�j�@@���ʃR�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01335<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A161340293
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@���X�R�[�h[]�̃`�F�b�N
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            String l_strMessage = "���X�R�[�h������!";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // �Q�j�@@�����敪�̃`�F�b�N
        // �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accountType == null || "".equals(this.accountType.trim()))
        {
            String l_strMessage = "�����敪������!";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00604,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
        if (!WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(this.accountType) && 
            !WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(this.accountType))
        {
            String l_strMessage = "�����敪�̒l�A0�F�l�A�J�E���g�A1�F�@@�l�A�J�E���g�ȊO�̏ꍇ. accountType = " + this.accountType;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // �R�j�@@�����������i���j�C�����������i���j�̃`�F�b�N
        // �@@�R�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
        if (this.infoClaimDateFrom != null && this.infoClaimDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.infoClaimDateFrom, this.infoClaimDateTo) > 0)
            {
                String l_strMessage = "�����������i���j> ���������� �i���j." 
                    + " �����������i���j= " + this.infoClaimDateFrom
                    + "; ���������� �i���j= " + this.infoClaimDateTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01326,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        // �S�j�@@�����J�ݓ��i���j�C�����J�ݓ��i���j�̃`�F�b�N
        // �@@�S�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
        if (this.accountOpenDateFrom != null && this.accountOpenDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.accountOpenDateFrom, this.accountOpenDateTo) > 0)
            {
                String l_strMessage = "�����J�ݓ��i���j> �����J�ݓ� �i���j." 
                    + " �����J�ݓ��i���j= " + this.accountOpenDateFrom
                    + "; �����J�ݓ� �i���j= " + this.accountOpenDateTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01328,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // �T�j�@@�ڋq�R�[�h�i���j�C�ڋq�R�[�h�i���j�̃`�F�b�N
        // �@@�T�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
        if (this.accountCodeFrom != null && this.accountCodeTo != null)
        {
            if (this.accountCodeFrom.compareTo(this.accountCodeTo) > 0 )
            {
                String l_strMessage = "�ڋq�R�[�h�i���j> �ڋq�R�[�h �i���j." 
                    + " �ڋq�R�[�h�i���j= " + this.accountCodeFrom
                    + "; �ڋq�R�[�h �i���j= " + this.accountCodeTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00795,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // �@@�T�|�Q�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A
        if (this.accountCodeFrom != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.accountCodeFrom)
                || !WEB3StringTypeUtility.isDigit(this.accountCodeFrom))
            {
                String l_strMessage = "�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ." 
                    + " �ڋq�R�[�h�i���j= " + this.accountCodeFrom;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01330,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // �@@�T�|�R�j�@@�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A
        if (this.accountCodeTo != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.accountCodeTo)
                || !WEB3StringTypeUtility.isDigit(this.accountCodeTo))
            {
                String l_strMessage = "�ڋq�R�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ." 
                    + " �ڋq�R�[�h�i���j= " + this.accountCodeTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01331,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // �U�j�@@���ʃR�[�h�i���j�C���ʃR�[�h�i���j�̃`�F�b�N
        // �@@�U�|�P�j�@@�i���j > �i���j�ł���΁A��O���X���[����B
        if (this.requestNumberFrom != null && this.requestNumberTo != null)
        {
            if (this.requestNumberFrom.compareTo(this.requestNumberTo) > 0 )
            {
                String l_strMessage = "���ʃR�[�h�i���j> ���ʃR�[�h �i���j." 
                    + " ���ʃR�[�h�i���j= " + this.requestNumberFrom
                    + "; ���ʃR�[�h �i���j= " + this.requestNumberTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01333,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        // �@@�U�|�Q�j�@@���ʃR�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A
        if (this.requestNumberFrom != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.requestNumberFrom)
                || !WEB3StringTypeUtility.isDigit(this.requestNumberFrom))
            {
                String l_strMessage = "���ʃR�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ." 
                    + " ���ʃR�[�h�i���j= " + this.requestNumberFrom;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01334,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        // �@@�U�|�R�j�@@���ʃR�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ�A
        if (this.requestNumberTo != null)
        {
            if (WEB3StringTypeUtility.isWbyteString(this.requestNumberTo)
                || !WEB3StringTypeUtility.isDigit(this.requestNumberTo))
            {
                String l_strMessage = "���ʃR�[�h�i���j�ɔ��p�����ȊO�̕������܂܂��ꍇ." 
                    + " ���ʃR�[�h�i���j= " + this.requestNumberTo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01335,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
