head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������勤�ʃ��N�G�X�g(WEB3BondDomesticApplyCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 ���f��No.227
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���������勤�ʃ��N�G�X�g)<BR>
 * ���������勤�ʃ��N�G�X�g<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyCommonRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String productId;

    /**
     * (�\�����z)<BR>
     * �\�����z<BR>
     */
    public String applyAmount;

    /**
     * (�Љ�敪)<BR>
     * �Љ�敪<BR>
     * <BR>
     * 1�F���ڎ��<BR>
     * 2�F�P���Љ�<BR>
     * 3�F���i�Љ�<BR>
     * 4�F������<BR>
     * <BR>
     */
    public String introduceStoreDiv;

    /**
     * (�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h<BR>
     */
    public String introduceStoreCode;

    /**
     * @@roseuid 46A473FD0280
     */
    public WEB3BondDomesticApplyCommonRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }

    /**
     * �����N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * 1�j�@@����ID�`�F�b�N<BR>
     * �@@�@@�@@����ID == null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02229<BR>
     * <BR>
     * �Q�j�@@�\�����z�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�\�����z == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02880<BR>
     * �@@�Q�|�Q�j�@@�\�����z�������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02881<BR>
     * �@@�Q�|�R�j�@@�\�����z �� 0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02882<BR>
     * �@@�Q�|�S�j�@@�\�����z �� 11���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02883<BR>
     * @@throws WEB3BaseException
     * @@roseuid 469F47BE01A6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1�j�@@����ID�`�F�b�N
        //����ID == null�̏ꍇ�A��O���X���[����B
        if (this.productId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }

        //�Q�j�@@�\�����z�`�F�b�N
        //�Q�|�P�j�@@�\�����z == null�̏ꍇ�A��O���X���[����B
        if (this.applyAmount == null)
        {
            log.debug("�\�����z�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02880,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�����z�����w��ł��B");
        }

        //�Q�|�Q�j�@@�\�����z�������łȂ��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.applyAmount))
        {
            log.debug("�\�����z�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02881,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�����z�������ȊO�̒l�ł��B");
        }

        //�Q�|�R�j�@@�\�����z �� 0�̏ꍇ�A��O���X���[����B
        if (Double.parseDouble(this.applyAmount) <= 0)
        {
            log.debug("�\�����z��0�ȉ��̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02882,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�����z��0�ȉ��̒l�ł��B");
        }

        //�Q�|�S�j�@@�\�����z �� 11���̏ꍇ�A��O���X���[����B
        if (this.applyAmount.length() > 11)
        {
            log.debug("�\�����z�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02883,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�����z�̃T�C�Y���s���ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
