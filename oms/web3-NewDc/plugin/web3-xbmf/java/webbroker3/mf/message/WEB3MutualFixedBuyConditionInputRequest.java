head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���������o�^���̓��N�G�X�g(WEB3MutualFixedBuyConditionInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 ���z(���u) �V�K�쐬 ���f��605
Revision History : 2008/08/01 ���g(���u) �d�l�ύX ���f��623
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFFirstDisplayDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (���M�莞��z���t���������o�^���̓��N�G�X�g)<BR>
 * ���M�莞��z���t���������o�^���̓��N�G�X�g<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_condition_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200807101430L;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionInputRequest.class);

    /**
     * (�J�e�S���[�R�[�h)<BR>
     * �J�e�S���[�R�[�h<BR>
     */
    public String categoryCode;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String[] productCode;

    /**
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String  typeCode;

    /**
     * (�d�q���`�F�b�N�t���O)<BR>
     * �d�q���`�F�b�N�t���O<BR>
     * <BR>
     * true�F�`�F�b�N�v<BR>
     * false�F�`�F�b�N�s�v<BR>
     */
    public boolean batoCheckFlag;

    /**
     * (����\���t���O)<BR>
     * ����\���t���O<BR>
     * <BR>
     * �ꗗ����o�^���͉�ʂɑJ�ڂ���ہA�V�K�ǉ�������\�����Ȃ��悤���f����B<BR>
     * <BR>
     * 0�F�\������<BR>
     * 1�F�\�����Ȃ�<BR>
     * 2�F�{���`�F�b�N��<BR>
     */
    public String firstDisplayDiv;

    /**
     * (���M�莞��z���t���������o�^���̓��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 4848DB8400E9
     */
    public WEB3MutualFixedBuyConditionInputRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�莞��z���t���������o�^���̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4848DBB40000
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualFixedBuyConditionInputResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j����\���t���O�`�F�b�N���s��<BR>
     * �@@�@@�P�|�P)�@@����\���t���O==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03100<BR>
     * �@@�@@�P�|�Q)�@@����\���t���O���ȉ��̒l�̂����ꂩ�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�h1�F�\�����Ȃ��h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�h0�F�\������h<BR>
     * �@@�@@�@@�@@�@@�@@�@@�h2�F�{���`�F�b�N���h<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03101<BR>
     * @@throws WEB3BaseException
     * @@roseuid 486C885102BA
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j����\���t���O�`�F�b�N���s��
        //�@@�@@�P�|�P)�@@����\���t���O==null�̏ꍇ�A��O���X���[����B
        if (this.firstDisplayDiv == null)
        {
            log.debug("����\���t���O�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03100,
                getClass().getName() + "." + STR_METHOD_NAME,
                "����\���t���O�����w��ł��B");
        }

        //�@@�@@�P�|�Q)�@@����\���t���O���ȉ��̒l�̂����ꂩ�łȂ��ꍇ�A��O���X���[����B
        //�@@�@@�@@�@@�@@�@@�@@�h1�F�\�����Ȃ��h
        //�@@�@@�@@�@@�@@�@@�@@�h0�F�\������h
        //             �h2�F�{���`�F�b�N���h
        if (!WEB3MFFirstDisplayDivDef.NO_DISPLAY.equals(this.firstDisplayDiv)
            && !WEB3MFFirstDisplayDivDef.DISPLAY.equals(this.firstDisplayDiv)
            && !WEB3MFFirstDisplayDivDef.READING_CHECK.equals(this.firstDisplayDiv))
        {
            log.debug("����\���t���O�̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03101,
                getClass().getName() + "." + STR_METHOD_NAME,
                "����\���t���O�̒l���s���ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
