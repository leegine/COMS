head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressTypeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[����ʏ��iWEB3AccInfoMailAddressTypeUnit.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/21 ���g (���u) �V�K�쐬 ���f��No.257
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.common.define.WEB3MailAssortmentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���[����ʏ��)<BR>
 * ���[����ʏ�񃁃b�Z�[�W<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressTypeUnit extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressTypeUnit.class);

    /**
     * (���[���A�h���X�ԍ�)<BR>
     * ���[���A�h���X�ԍ�<BR>
     */
    public String mailAddressNo;

    /**
     * (���[����ʔԍ�)<BR>
     * ���[����ʔԍ�<BR>
     * <BR>
     * 1:������胁�[��<BR>
     * 2:��������胁�[��<BR>
     * 3:��OP��胁�[��<BR>
     * 4:��OP����胁�[��<BR>
     * 5:�d�v���[��<BR>
     * 6:�ē����[��<BR>
     * 7:�d�q��t���[��<BR>
     */
    public String mailAddressTypeNo;

    /**
     * (���[����ʏ��)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3AccInfoMailAddressTypeUnit()
    {

    }

    /**
     * (validate���[����ʏ��)<BR>
     * ���[����ʏ��ɓ��͒l������ꍇ�A�ݒ�l�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���[���A�h���X�ԍ��`�F�b�N<BR>
     * �@@this.���[���A�h���X�ԍ� != null�̏ꍇ�A�ȉ����������{����B<BR>
     * �@@�P�|�P�jthis.���[���A�h���X�ԍ��������ȊO�̒l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03191<BR>
     * �@@�P�|�Q�jthis.���[���A�h���X�ԍ����O�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03192<BR>
     * �@@�P�|�R�jthis.���[���A�h���X�ԍ��̓��[���A�h���X���[].���[���A�h���X�ԍ��Ɋ܂܂�Ă��Ȃ��ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03194<BR>
     * <BR>
     * �Q�j�@@���[����ʔԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.���[����ʔԍ� == null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03195<BR>
     * �@@�Q�|�Q�jthis.���[����ʔԍ����ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�E�h1:������胁�[���h<BR>
     * �@@�@@�@@�@@�@@�E�h2:��������胁�[���h<BR>
     * �@@�@@�@@�@@�@@�E�h3:��OP��胁�[���h<BR>
     * �@@�@@�@@�@@�@@�E�h4:��OP����胁�[���h<BR>
     * �@@�@@�@@�@@�@@�E�h5:�d�v���[���h<BR>
     * �@@�@@�@@�@@�@@�E�h6:�ē����[���h<BR>
     * �@@�@@�@@�@@�@@�E�h7:�d�q��t���[���h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03196<BR>
     * <BR>
     * �R�j�@@���[����ʔԍ��E���[���A�h���X�`�F�b�N<BR>
     * �@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���̗v�f����Loop�������s���B<BR>
     * <BR>
     * �@@�R�|�P�jthis.���[���A�h���X�ԍ� != null�@@����<BR>
     * �@@this.���[���A�h���X�ԍ�==���[���A�h���X���.���[���A�h���X�ԍ��@@����<BR>
     * �@@���[���A�h���X���[i].���[���A�h���X == null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03197<BR>
     * <BR>
     * �@@�R�|�Q�jthis.���[����ʔԍ� == 7:�d�q��t���[���̏ꍇ�A�ȉ����������{����B<BR>
     * �@@�@@�R�|�Q�|�P�jthis.���[���A�h���X�ԍ� == null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03198<BR>
     * �@@�@@�R�|�Q�|�Q�j�@@this.���[���A�h���X�ԍ�==���[���A�h���X���.���[���A�h���X�ԍ��@@����<BR>
     * �@@�@@���[���A�h���X���[i].���[���A�h���X�敪���u1:PC���[���A�h���X�v�ȊO�̒l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03198<BR>
     * @@param l_mailAddressInfoList - (���[���A�h���X���)<BR>
     * ���[���A�h���X���<BR>
     * @@throws WEB3BaseException
     */
    public void validateMailAddressTypeInfo(
        WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMailAddressInfo(WEB3AccInfoMailAddressInfoUnit[])";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@���[���A�h���X�ԍ��`�F�b�N
        //this.���[���A�h���X�ԍ� != null�̏ꍇ�A�ȉ����������{����B
        if (this.mailAddressNo != null)
        {
            //�P�|�P�jthis.���[���A�h���X�ԍ��������ȊO�̒l�̏ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.mailAddressNo))
            {
                log.debug("���[���A�h���X�ԍ��������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03191,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���[���A�h���X�ԍ��������ȊO�̒l�ł��B"); 
            }

            //�P�|�Q�jthis.���[���A�h���X�ԍ����O�̏ꍇ�A��O���X���[����B
            if (Integer.parseInt(this.mailAddressNo) <= 0)
            {
                log.debug("���[���A�h���X�ԍ��̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03192,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���[���A�h���X�ԍ��̓��͂��s���ł��B"); 
            }

            //�P�|�R�jthis.���[���A�h���X�ԍ��̓��[���A�h���X���[].���[���A�h���X�ԍ�
            //�Ɋ܂܂�Ă��Ȃ��ꍇ�A��O���X���[����B
            int l_intlength = 0;
            if (l_mailAddressInfoList != null)
            {
                l_intlength = l_mailAddressInfoList.length;
            }
            boolean l_blnflag = false;
            for (int i = 0; i < l_intlength; i++)
            {
                if (this.mailAddressNo.equals(l_mailAddressInfoList[i].mailAddressNo))
                {
                    l_blnflag = true;
                }
            }
            if (!l_blnflag)
            {
                log.debug("���[���A�h���X�ԍ������[���A�h���X���ɑ��݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03194,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "���[���A�h���X�ԍ������[���A�h���X���ɑ��݂��܂���B"); 
            }
        }

        //�Q�j�@@���[����ʔԍ��`�F�b�N
        //�Q�|�P�jthis.���[����ʔԍ� == null�̏ꍇ�A��O���X���[����B 
        if (this.mailAddressTypeNo == null)
        {
            log.debug("���[����ʔԍ��������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03195,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���[����ʔԍ��������͂ł��B"); 
        }

        //�@@�Q�|�Q�jthis.���[����ʔԍ����ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B
        //�@@�@@�@@�@@�@@�E�h1:������胁�[���h
        //�@@�@@�@@�@@�@@�E�h2:��������胁�[���h
        //�@@�@@�@@�@@�@@�E�h3:��OP��胁�[���h
        //�@@�@@�@@�@@�@@�E�h4:��OP����胁�[���h
        //�@@�@@�@@�@@�@@�E�h5:�d�v���[���h
        //�@@�@@�@@�@@�@@�E�h6:�ē����[���h
        //�@@�@@�@@�@@�@@�E�h7:�d�q��t���[���h 
        if (!WEB3MailAssortmentDivDef.EQUITY_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.EQUITY_NOT_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.FUTURES_OPTION_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.FUTURES_OPTION_NOT_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.IMPORTANT_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.GUIDE_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.ELECTRONIC_DELIVER_MAIL.equals(this.mailAddressTypeNo))
        {
            log.debug("���[����ʔԍ��̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03196,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���[����ʔԍ��̓��͂��s���ł��B"); 
        }

        //�R�j�@@���[����ʔԍ��E���[���A�h���X�`�F�b�N
        //�@@���N�G�X�g�f�[�^.�����A�h���X���X�g.���[���A�h���X���̗v�f����Loop�������s���B
        int l_intLength = 0;
        if (l_mailAddressInfoList != null);
        {
            l_intLength = l_mailAddressInfoList.length;
        }
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3AccInfoMailAddressInfoUnit l_mailAddressInfo = l_mailAddressInfoList[i];
            //�R�|�P�jthis.���[���A�h���X�ԍ� != null�@@����
            //�@@this.���[���A�h���X�ԍ�==���[���A�h���X���.���[���A�h���X�ԍ��@@����
            //�@@���[���A�h���X���[i].���[���A�h���X == null�̏ꍇ�A��O���X���[����B
            if (this.mailAddressNo != null && this.mailAddressNo.equals(l_mailAddressInfo.mailAddressNo)
                && l_mailAddressInfo.mailAddress == null)
            {
                log.debug("�I���������[���A�h���X�������͂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03197,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�I���������[���A�h���X�������͂ł��B"); 
            }

            //�R�|�Q�jthis.���[����ʔԍ� == 7:�d�q��t���[���̏ꍇ�A�ȉ����������{����B
            if (WEB3MailAssortmentDivDef.ELECTRONIC_DELIVER_MAIL.equals(this.mailAddressTypeNo))
            {
                //�R�|�Q�|�P�jthis.���[���A�h���X�ԍ� == null�̏ꍇ�A��O���X���[����B
                if (this.mailAddressNo == null)
                {
                    log.debug("�d�q��t���[����PC���[���A�h���X�݂̂Ɏw��\�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03198,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "�d�q��t���[����PC���[���A�h���X�݂̂Ɏw��\�ł��B"); 
                }

                //�@@�@@�R�|�Q�|�Q�j�@@this.���[���A�h���X�ԍ�==���[���A�h���X���.���[���A�h���X�ԍ��@@����
                //�@@�@@���[���A�h���X���[i].���[���A�h���X�敪���u1:PC���[���A�h���X�v�ȊO�̒l�̏ꍇ�A��O���X���[����B
                if (this.mailAddressNo.equals(l_mailAddressInfo.mailAddressNo)
                    && !WEB3AddressDivDef.PC_MAIL_ADDRESS.equals(l_mailAddressInfo.mailAddressDiv))
                {
                    log.debug("�d�q��t���[����PC���[���A�h���X�݂̂Ɏw��\�ł��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03198,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "�d�q��t���[����PC���[���A�h���X�݂̂Ɏw��\�ł��B"); 
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
