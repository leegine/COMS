head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenCompleteSoapRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
Author Name         : Daiwa Institute of Research
File Name           : FX�����J�݊������N�G�X�g�iSOAP�ڑ��j(WEB3FXAccOpenCompleteSoapRequest.java)
Revision History    : 2008/04/08 ���u��(���u) �V�K�쐬 ���f��No.837
Revision History    : 2009/10/27 �����F(���u) �d�l�ύX ���f��No.1245
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�����J�݊������N�G�X�g�iSOAP�ڑ��j)<BR>
 * FX�����J�݊������N�G�X�g�iSOAP�ڑ��j�N���X<BR>
 * <BR>
 * @@author ���u��
 * @@version 1.0
 */
public class WEB3FXAccOpenCompleteSoapRequest extends WEB3FXAccOpenAskingRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "fx_acc_open_complete_soap";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804081441L;

    /**
     * (FX�Ïؔԍ��Q)<BR>
     * FX�Ïؔԍ��Q<BR>
     */
    public String fxPassword2;

    /**
     * (FX�����J�݊������N�G�X�g�iSOAP�ڑ��j)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3FXAccOpenCompleteSoapRequest()
    {

    }

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenCompleteSoapRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@FX�Ïؔԍ��Q�`�F�b�N<BR>
     * �@@FX�Ïؔԍ��Q��null����Ȃ��ꍇ�ȉ��`�F�b�N���s���A<BR>
     * �@@�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A��O��throw����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.FX�Ïؔԍ��Q�����p�p����<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01302 <BR>
     * <BR>
     * �@@�P�|�Q�j�@@this.FX�Ïؔԍ��Q��8��<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �@@�P�|�R�j�@@this.FX�Ïؔԍ��Q��12��<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01784 <BR>
     * <BR>
     * �@@�P�|�S�j�@@this.FX�Ïؔԍ��Q��this.FX�Ïؔԍ�<BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_03185 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String l_strMethodName = "validate()";
        log.entering(l_strMethodName);

        if (this.fxPassword2 != null)
        {
            //�@@�P�|�P�j�@@this.FX�Ïؔԍ��Q�����p�p����
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.fxPassword2))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01302,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q�����p�p����" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
            }

            //�@@�P�|�Q�j�@@this.FX�Ïؔԍ��Q��8��
            if (this.fxPassword2.length() < 8)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q��8��" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
            }

            //�@@�P�|�R�j�@@this.FX�Ïؔԍ��Q��12��
            if (this.fxPassword2.length() > 12)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01784,
                    this.getClass().getName() + "." + l_strMethodName,
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q��12��" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
            }

            //�@@�P�|�S�j�@@this.FX�Ïؔԍ��Q��this.FX�Ïؔԍ�
            if (this.fxPassword2.equals(this.fxPassword))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03185,
                    this.getClass().getName() + "." + l_strMethodName,
                    "FX�Ïؔԍ��Q��FX�Ïؔԍ�����v�ł��B" +
                    "���N�G�X�g�f�[�^.FX�Ïؔԍ��Q = " + this.fxPassword2);
            }
        }

        log.exiting(l_strMethodName);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXAccOpenCompleteSoapResponse(this);
    }
}
@
