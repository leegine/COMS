head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ظ���(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ظ���)<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest extends WEB3GenRequest
{

    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest.class);
      
      
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_hyperBoxCommissionChangeFileDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082133L;

    /**
     * (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     */
    public Date trialStartDate;

    /**
     * (�K�p�I����)<BR>
     * �K�p�I����<BR>
     */
    public Date trialEndDate;

    /**
     * (�萔���m���D)<BR>
     * �萔���m���D<BR>
     */
    public String commissionNo;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String collectRate;

    /**
     * @@roseuid 418F38540196
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse(this);
    }

    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�K�p�J�n���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * �Q�j�@@�K�p�I�����̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00838<BR>
     * �@@�Q�|�Q�j�@@�i�K�p�J�n�� > �K�p�I�����j�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * <BR>
     * �R�j�@@�萔���m���D�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01141<BR>
     * �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01142<BR>
     * �@@�R�|�R�j�@@�T�C�Y��2byte�łȂ��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01143<BR>
     * <BR>
     * �S�j�@@�������̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01144<BR>
     * �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01145<BR>
     * �@@�R�|�R�j�@@�L���l��1�`100�̊ԂłȂ��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01146<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4146AC400012
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //* �P�j�@@�K�p�J�n���̃`�F�b�N<BR>
        //* �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00837<BR>
        //* <BR>
        if (this.trialStartDate == null)
        {
            //��O
            log.debug("[�K�p�J�n��] = " + trialStartDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                this.getClass().getName() + STR_METHOD_NAME, "�K�p�J�n���̖����͂̏ꍇ");
        }
        
        //* �Q�j�@@�K�p�I�����̃`�F�b�N<BR>
        //* �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00838<BR>
        if (this.trialEndDate == null)
        {
            //��O
            log.debug("[�K�p�I����] = " + trialEndDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00838,
                this.getClass().getName() + STR_METHOD_NAME, "�K�p�I�����̖����͂̏ꍇ");
        }
        
        //* �@@�Q�|�Q�j�@@�i�K�p�J�n�� > �K�p�I�����j�̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00839<BR>
        //* <BR>
        if (this.trialStartDate.compareTo(this.trialEndDate) > 0)
        {
            //��O
            log.debug("[�K�p�J�n��] = " + trialStartDate);
            log.debug("[�K�p�I����] = " + trialEndDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                this.getClass().getName() + STR_METHOD_NAME, "�i�K�p�J�n�� > �K�p�I�����j�̏ꍇ");
        }
        
        //* �R�j�@@�萔���m���D�̃`�F�b�N<BR>
        //* �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01141<BR>
        if (this.commissionNo == null || "".equals(this.commissionNo))
        {
            //��O
            log.debug("[�萔���m���D] = " + commissionNo);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01141,
                this.getClass().getName() + STR_METHOD_NAME,"�萔���m���D�̖����͂̏ꍇ");
        }
        //* �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01142<BR>
        if (!WEB3StringTypeUtility.isDigit(this.commissionNo))
        {
            //��O
            log.debug("[�萔���m���D] = " + commissionNo);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01142,
                this.getClass().getName() + STR_METHOD_NAME, "�萔���m���D�����ȊO�̕������܂܂��ꍇ");
        }
        
        //* �@@�R�|�R�j�@@�T�C�Y��2byte�łȂ��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01143<BR>
        //* <BR>
        if (WEB3StringTypeUtility.getByteLength(this.commissionNo) != 2)
        {
            //��O
            log.debug("[�萔���m���D] = " + commissionNo);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01143,
                this.getClass().getName() + STR_METHOD_NAME, "�萔���m���D�T�C�Y��2byte�łȂ��ꍇ");
        }
        
        //* �S�j�@@�������̃`�F�b�N<BR>
        //* �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01144<BR>
        if (this.collectRate == null || "".equals(this.collectRate))
        {
            //��O
            log.debug("[������] = " + collectRate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01144,
                this.getClass().getName() + STR_METHOD_NAME, "�������̖����͂̏ꍇ");
        }
        //* �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01145<BR>
        if (!WEB3StringTypeUtility.isDigit(this.collectRate))
        {
            //��O
            log.debug("[������] = " + collectRate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01145,
                this.getClass().getName() + STR_METHOD_NAME, "�������̐����ȊO�̕������܂܂��ꍇ");
        }
        
        //* �@@�R�|�R�j�@@�L���l��1�`100�̊ԂłȂ��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01146<BR>
        if (Integer.parseInt(this.collectRate) < 0 ||
            Integer.parseInt(this.collectRate) > 100)
        {
            //��O
            log.debug("[������] = " + collectRate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01146,
                this.getClass().getName() + STR_METHOD_NAME,"�������̗L���l��1�`100�̊ԂłȂ��ꍇ");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
