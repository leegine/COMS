head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyCnfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\���m�F���N�G�X�g(WEB3InformPTSAccOpenApplyCnfRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.123�ANo.125
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS�����J�ݐ\���m�F���N�G�X�g)<BR>
 * PTS�����J�ݐ\���m�F���N�G�X�g
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyCnfRequest extends WEB3InformPTSAccOpenApplyCommonRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyCnfRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "inform_pts_acc_open_apply_cnf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181639L;

    /**
     * (�d�q���`�F�b�N�t���O)<BR>
     * �d�q���`�F�b�N�t���O<BR>
     * �d�q���V�X�e���֐ڑ����s������ݒ肷��B<BR>
     * <BR>
     * true�F�ڑ�������B<BR>
     * false�F�ڑ������Ȃ��B<BR>
     */
    public boolean batoCheckFlag;

    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h
     */
    public String typeCode;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String[] productCode;

    /**
     * @@roseuid 47B9271A0186
     */
    public WEB3InformPTSAccOpenApplyCnfRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
     * <BR>
     * �P�j�@@super.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�d�q���`�F�b�N�t���O == true�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@��ʃR�[�h�`�F�b�N<BR>
     * �@@�@@this.��ʃR�[�h == null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_02202<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@this.�����R�[�h�̗v�f�����u0�v�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_03023<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47AA91A600E9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //super.validate()���R�[������B
        super.validate();

        //�d�q���`�F�b�N�t���O == true�̏ꍇ�A�ȉ��̃`�F�b�N���s��
        if (this.batoCheckFlag)
        {
            //��ʃR�[�h�`�F�b�N
            // this.��ʃR�[�h == null�̏ꍇ�A��O��throw����
            if (this.typeCode == null)
            {
                log.debug("��ʃR�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    "��ʃR�[�h�����w��ł��B");
            }

            //�����R�[�h�`�F�b�N
            //this.�����R�[�h�̗v�f�����u0�v�̏ꍇ�A��O��throw����
            if (this.productCode == null || this.productCode.length == 0)
            {
                log.debug("�����R�[�h�̗v�f�����O�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03023,
                    WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                    "�����R�[�h�̗v�f�����O�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3InformPTSAccOpenApplyCnfResponse(this);
    }
}
@
