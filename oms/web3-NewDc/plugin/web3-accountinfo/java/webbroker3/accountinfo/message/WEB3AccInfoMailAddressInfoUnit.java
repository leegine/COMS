head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���A�h���X���iWEB3AccInfoMailAddressInfoUnit.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/21 ���g (���u) �V�K�쐬 ���f��No.257
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���[���A�h���X���)<BR>
 * ���[���A�h���X��񃁃b�Z�[�W<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressInfoUnit extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressInfoUnit.class);

    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;

    /**
     * (���[���A�h���X�ԍ�)<BR>
     * ���[���A�h���X�ԍ�<BR>
     */
    public String mailAddressNo;

    /**
     * (���[���A�h���X�敪)<BR>
     * ���[���A�h���X�敪<BR>
     * <BR>
     * 1:PC���[���A�h���X<BR>
     * 2:�g�у��[���A�h���X<BR>
     */
    public String mailAddressDiv;

    /**
     * (���[���A�h���X���)<BR>
     * �f�B�t�H���g�R���X�g���N�^
     */
    public WEB3AccInfoMailAddressInfoUnit()
    {

    }

    /**
     * (validate���[���A�h���X���)
     * ���[���A�h���X���ɓ��͒l������ꍇ�A�ݒ�l�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���[���A�h���X�`�F�b�N<BR>
     * <BR>
     * �@@�P-�P�jthis.���[���A�h���X�ɓ��͒l������ꍇ�A�ȉ��������s���B<BR>
     * <BR>
     * �@@�@@�@@���[���A�h���X�Ƃ��ēK�؂Ȓl���𔻒肷��B<BR>
     * ���[���A�h���X���.���[���A�h���X�敪== �u2:�g�у��[���A�h���X�v�̏ꍇ�A�ȉ����������{����B<BR>
     * �@@�@@�@@[WEB3StringTypeUtility.isMailAddress() �Ɏw�肷�����]<BR>
     * �@@�@@�@@l_str�F this.���[���A�h���X<BR>
     * <BR>
     * �@@�@@�@@WEB3StringTypeUtility.isMailAddress()�̖߂�l�� false �̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02415<BR>
     * <BR>
     * �Q�j�@@���[���A�h���X�ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���[���A�h���X�ԍ� == null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03190<BR>
     * �@@�Q�|�Q�jthis.���[���A�h���X�ԍ��������ȊO�̒l�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03191<BR>
     * �@@�Q�|�R�jthis.���[���A�h���X�ԍ����O�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03192<BR>
     * <BR>
     * �R�j�@@���[���A�h���X�敪�`�F�b�N<BR>
     * �@@�R�|�P�jthis.���[���A�h���X�敪 == null�̏ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03193<BR>
     * @@throws WEB3BaseException
     */
    public void validateMailAddressInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMailAddressInfo()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���[���A�h���X�`�F�b�N
        //�P-�P�jthis.���[���A�h���X�ɓ��͒l������ꍇ�A�ȉ��������s���B
        if (!WEB3StringTypeUtility.isEmpty(this.mailAddress))
        {
            //���[���A�h���X���.���[���A�h���X�敪== �u2:�g�у��[���A�h���X�v�̏ꍇ�A�ȉ����������{����B
            if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(this.mailAddressDiv))
            {
                if (!WEB3StringTypeUtility.isMailAddress(this.mailAddress))
                {
                    log.debug("���[���A�h���X���s���ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "���[���A�h���X���s���ł��B"); 
                }
            }
        }

        //�Q�j�@@���[���A�h���X�ԍ��`�F�b�N
        //�Q�|�P�jthis.���[���A�h���X�ԍ� == null�̏ꍇ�A��O���X���[����B
        if (this.mailAddressNo == null)
        {
            log.debug("���[���A�h���X�ԍ��������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03190,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�ԍ��������͂ł��B"); 
        }

        //�Q�|�Q�jthis.���[���A�h���X�ԍ��������ȊO�̒l�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.mailAddressNo))
        {
            log.debug("���[���A�h���X�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03191,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�ԍ��������ȊO�̒l�ł��B"); 
        }

        //�Q�|�R�jthis.���[���A�h���X�ԍ����O�̏ꍇ�A��O���X���[����B
        if (Integer.parseInt(this.mailAddressNo) <= 0)
        {
            log.debug("���[���A�h���X�ԍ��̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03192,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�ԍ��̓��͂��s���ł��B"); 
        }

        //�R�j�@@���[���A�h���X�敪�`�F�b�N
        //�R�|�P�jthis.���[���A�h���X�敪 == null�̏ꍇ�A��O���X���[����B
        if (this.mailAddressDiv == null)
        {
            log.debug("���[���A�h���X�敪�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03193,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���[���A�h���X�敪�������͂ł��B"); 
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
