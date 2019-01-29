head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMobileMailAddressCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���A�h���X�g�у`�F�b�N�N���X(WEB3GentradeMobileMailAddressCheck.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/26 �����F (���u) �V�K�쐬 ���f��347
Revision History : 2010/02/23 ��іQ (���u) �d�l�ύX ���f��350�C352
*/

package webbroker3.gentrade;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���[���A�h���X�g�у`�F�b�N)<BR>
 * �g�у��[���A�h���X�̃`�F�b�N�@@�\���������郆�[�e�B���e�B�E�N���X�B<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3GentradeMobileMailAddressCheck
{
    /**
     * ���O�o��<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMobileMailAddressCheck.class);

    /**
     * (validate�g�уA�h���X)<BR>
     * ���͂������[���A�h���X���g�у��[���A�h���X�ɂł��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j����.���[���A�h���X��"@@"��̕������擾����i���P�j�B<BR>
     * <BR>
     * �Q�j�g�у��[���A�h���X�ɂł��邩�`�F�b�N����B<BR>
     * �Q�|�P�j�V�X�e���v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@�@@[����] <BR>
     * �@@�@@���́i���ϐ����jlike 'mobile.mail.%'<BR>
     * <BR>
     * �@@�@@�������ʂ��擾�ł��Ȃ������ꍇ�A�G���[���X���[����B<BR>
     * <BR>
     * �Q�|�Q�j�擾�������R�[�h�̗v�f����LOOP�����B<BR>
     * �@@�@@�@@�@@�@@�V�X�e���v���t�@@�����X�e�[�u��.�l��null�łȂ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�V�X�e���v���t�@@�����X�e�[�u��.�l�͂P�j�Ŏ擾����<BR>
     * �@@�@@�@@�@@�@@���[���A�h���X"@@"��̕����Ɋ܂܂�Ă���ꍇ�A�G���[���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_03168<BR>
     * <BR>
     * �i���P�j�̗�F�g�у��[���A�h���X�Ftest1��pdx.ne.jp<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�g�у��[���A�h���X"@@"��̕����Fpdx.ne.jp<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�g�у��[���A�h���X�ڔ���Fpdx<BR>
     * @@param l_strMailAddress - ���[���A�h���X
     * @@throws WEB3BaseException
     */
    public static void validateMobileAddress(String l_strMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMobileAddress(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strMailAddress == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����.���[���A�h���X��"@@"��̕������擾����i���P�j�B
        int l_intIndexOf = l_strMailAddress.indexOf("@@");
        String l_strSub = l_strMailAddress.substring(l_intIndexOf + 1);

        //�V�X�e���v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B
        //[����]
        //���́i���ϐ����jlike 'mobile.mail.%'
        String l_strWhere = " name like ? || '%'";
        Object[] l_objConds =  new Object[]{"mobile.mail."};
        QueryProcessor l_queryProcessor = null;

        List l_lisRecordexcs = null;

        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecordexcs = l_queryProcessor.doFindAllQuery(
                SystemPreferencesRow.TYPE,
                l_strWhere,
                l_objConds);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������ʂ��擾�ł��Ȃ������ꍇ�A�G���[���X���[����B
        if (l_lisRecordexcs.isEmpty())
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                 "�e�[�u���ɊY������f�[�^������܂���B");
        }

        //�擾�������R�[�h�̗v�f����LOOP�����B
        int l_intSize = l_lisRecordexcs.size();
        for (int i = 0; i < l_intSize; i++)
        {
            SystemPreferencesRow l_systemPreferencesRow = (SystemPreferencesRow)l_lisRecordexcs.get(i);
            String l_strValue = l_systemPreferencesRow.getValue();

            //�V�X�e���v���t�@@�����X�e�[�u��.�l��null�łȂ��ꍇ�A
            if (l_strValue != null)
            {
                //�V�X�e���v���t�@@�����X�e�[�u��.�l�͂P�j�Ŏ擾����
                //���[���A�h���X"@@"��̕����Ɋ܂܂�Ă���ꍇ�A�G���[���X���[����B
                int l_intFlag = l_strSub.indexOf(l_strValue);
                if (l_intFlag >= 0)
                {
                    log.debug("���͂���郁�[���A�h���X�͌g�у��[���A�h���X�ł���B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                         WEB3ErrorCatalog.BUSINESS_ERROR_03168,
                         WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                         "���͂���郁�[���A�h���X�͌g�у��[���A�h���X�ł���B");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validatePC���[���A�h���X)<BR>
     * ���͂������[���A�h���X��PC���[���A�h���X�ɂł��邩�`�F�b�N����B<BR>
     * <BR>
     * �@@�@@PC���[���A�h���X�`�F�b�N<BR>
     * �@@�@@[this.validate�g�у��[���A�h���X() �Ɏw�肷�����]<BR>
     * �@@�@@�@@l_str�F this.���[���A�h���X<BR>
     * <BR>
     * �@@�@@�P�jPC���[���A�h���X�̏ꍇ�ithis.validate�g�у��[���A�h���X ������I���j<BR>
     * �A�������s�킸�I������B<BR>
     * <BR>
     * �@@�@@�Q�j�g�у��[���A�h���X�̏ꍇ�ithis.validate�g�у��[���A�h���X���G���[�����j<BR>
     * �A�G���[���X���[����B<BR>
     * <BR>
     * @@param l_strMailAddress - (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     * @@throws WEB3BaseException
     */
    public static void validatePCMailAddress(String l_strMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validatePCMailAddress(String)";
        log.entering(STR_METHOD_NAME);

        //PC���[���A�h���X�`�F�b�N
        //[this.validate�g�у��[���A�h���X() �Ɏw�肷�����]
        //l_str�F this.���[���A�h���X
        validateMobileAddress(l_strMailAddress);

        log.exiting(STR_METHOD_NAME);
    }
}
@
