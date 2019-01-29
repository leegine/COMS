head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXSimplexAccOpenConnectionSystem.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Simplex�����J�ݐڑ��V�X�e���iWEB3FXSimplexAccOpenConnectionSystem.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 �����F (���u) �V�K�쐬�E���f��1200 1226
Revision History : 2009/10/27 �����F(���u) �d�l�ύX ���f��No.1247
Revision History : 2009/12/10 �����F(���u) �����̖�� No.22
*/
package webbroker3.aio;

import java.net.URLEncoder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (Simplex�����J�ݐڑ��V�X�e��)<BR>
 * Simplex�����J�ݐڑ��V�X�e���N���X<BR>
 * <BR>
 * @@author �����F(���u)
 * @@version 1.0
 */
public class WEB3FXSimplexAccOpenConnectionSystem extends WEB3FXSimplexConnectionSystem
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexAccOpenConnectionSystem.class);

    /**
     * Simplex�����J�ݗp�n�b�V���𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�jSimplex�˗��d������.���FX���O�C��ID ��<BR>
     * �@@�@@Simplex�˗��d������.��ꃁ�[���A�h���X ��<BR>
     * �@@�@@Simplex�˗��d������.�^�C���X�^���v ��<BR>
     * �@@�@@����.�I�y���[�V��������A�����AMD5�Í����������̂�啶���ɂ����l���擾����B<BR>
     * <BR>
     * �Q�j����.Simplex�˗��d�����ׂ̃n�b�V���l�Ɏ擾�����n�b�V���l���Z�b�g����B<BR>
     * <BR>
     * �R�j����.Simplex�˗��d�����ׂ�ԋp����B<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex�˗��d������)<BR>
     * Simplex�˗��d������<BR>
     * @@param l_strOperationName - (�I�y���[�V������)<BR>
     * �I�y���[�V������<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     */
    protected WEB3FXSimplexAskingTelegramUnit createHash(
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit, String l_strOperationName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createHash(WEB3FXSimplexAskingTelegramUnit, String)";
        log.entering (STR_METHOD_NAME);
        if (l_simplexAskingTelegramUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //Simplex�˗��d������.���FX���O�C��ID ��
        //Simplex�˗��d������.��ꃁ�[���A�h���X ��
        //Simplex�˗��d������.�^�C���X�^���v ��
        //����.�I�y���[�V��������A�����AMD5�Í����������̂�啶���ɂ����l���擾����B
        String[] l_strValue =  new String[]{
            l_simplexAskingTelegramUnit.oseFxLoginId,
            l_simplexAskingTelegramUnit.mail1,
            l_simplexAskingTelegramUnit.timeStamp,
            l_strOperationName};
        String l_strHashValue =
            WEB3StringTypeUtility.createHashValue(WEB3AioHashAlgorithmDef.MD5, l_strValue);
        //����.Simplex�˗��d�����ׂ̃n�b�V���l�Ɏ擾�����n�b�V���l���Z�b�g����B 
        l_simplexAskingTelegramUnit.hashValue = l_strHashValue.toUpperCase();
        //����.Simplex�˗��d�����ׂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_simplexAskingTelegramUnit;
    }

    /**
     * Simplex�����J�ݗp�ڑ�URL�𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�jStringBuffer�̃C���X�^���X���쐬����B<BR>
     * <BR>
     * �Q�jStringBuffer�ɁA����.�G���h�|�C���g����ǉ�����B<BR>
     * <BR>
     * �R�jStringBuffer�ɁA������RegisterOseAccount.do��ǉ�<BR>
     * <BR>
     * �S�jStringBuffer�ɁAhttp����URL�p '?' �𖖔��ɒǉ�����B<BR>
     * <BR>
     * �T�j����.simplex�˗��d���̍��ڕ�<BR>
     * �⑫�����u�iDIR-Simplex�j�d���t�H�[�}�b�g.xls�v<BR>
     * �@@��sheet�uURL�ɂ��Ă̌����J�ݕ����v���Q�Ƃ��A���L�������s���F<BR>
     * �@@�T�|�P�j���ږ��@@+�@@"="�@@+�@@���ڒl + "&"�@@<BR>
     * �@@�@@�@@�@@�@@(�Ō�̍��ڂɏ������A"&"��ǉ��s�v�A���ڒl��null�̏ꍇ�A���ڒl��ǉ����Ȃ�)<BR>
     * �@@�T�|�Q�j1�ɂĐ��������������StringBuffer�̖����ɒǉ�����B<BR>
     * <BR>
     * �U�jStringBuffer.toString()��Ԃ��B<BR>
     * <BR>
     * @@param l_strEndpointName - (�G���h�|�C���g��)<BR>
     * �G���h�|�C���g��<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex�˗��d������)<BR>
     * Simplex�˗��d������<BR>
     * @@return String
     */
    protected String createURL(
        String l_strEndpointName, WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createURL(String, WEB3FXSimplexAskingTelegramUnit)";
        log.entering (STR_METHOD_NAME);
        if (l_simplexAskingTelegramUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //StringBuffer�̃C���X�^���X���쐬����
        StringBuffer l_sbValue = new StringBuffer();
        //StringBuffer�ɁA����.�G���h�|�C���g����ǉ�����
        l_sbValue.append(l_strEndpointName);
        //StringBuffer�ɁA������RegisterOseAccount.do��ǉ�
        l_sbValue.append("/RegisterOseAccount.do");
        //StringBuffer�ɁAhttp����URL�p '?' �𖖔��ɒǉ�����
        l_sbValue.append("?");
        //����.simplex�˗��d���̍��ڕ�
        //�⑫�����u�iDIR-Simplex�j�d���t�H�[�}�b�g.xls�v��sheet�uURL�ɂ��Ă̌����J�ݕ����v���Q�Ƃ��A
        //���L�������s���F
        //���ږ��@@+�@@"="�@@+�@@���ڒl + "&"
        //(�Ō�̍��ڂɏ������A"&"��ǉ��s�v�A���ڒl��null�̏ꍇ�A���ڒl��ǉ����Ȃ�)
        //1�ɂĐ��������������StringBuffer�̖����ɒǉ�����B 
        try
        {
            if (l_simplexAskingTelegramUnit.oseFxLoginId != null)
            {
                l_sbValue.append("oseFxLoginId" + "=" + l_simplexAskingTelegramUnit.oseFxLoginId + "&");
            }
            else
            {
                l_sbValue.append("oseFxLoginId" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.fullNameKana != null)
            {
                l_sbValue.append("fullNameKana" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.fullNameKana,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("fullNameKana" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.fullName != null)
            {
                l_sbValue.append("fullName" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.fullName,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("fullName" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.genderType != null)
            {
                l_sbValue.append("genderType" + "=" + l_simplexAskingTelegramUnit.genderType + "&");
            }
            else
            {
                l_sbValue.append("genderType" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.zipCode != null)
            {
                l_sbValue.append("zipCode" + "=" + l_simplexAskingTelegramUnit.zipCode + "&");
            }
            else
            {
                l_sbValue.append("zipCode" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.address1 != null)
            {
                l_sbValue.append("address1" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.address1,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("address1" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.address2 != null)
            {
                l_sbValue.append("address2" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.address2,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("address2" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.address3 != null)
            {
                l_sbValue.append("address3" + "=" + URLEncoder.encode(l_simplexAskingTelegramUnit.address3,"Windows-31J") + "&");
            }
            else
            {
                l_sbValue.append("address3" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.mail1 != null)
            {
                l_sbValue.append("mail1" + "=" + l_simplexAskingTelegramUnit.mail1 + "&");
            }
            else
            {
                l_sbValue.append("mail1" + "=" + "&");
            }
            l_sbValue.append("mail2" + "=" + "&");
            if (l_simplexAskingTelegramUnit.initialLoginPassword != null)
            {
                l_sbValue.append("initialLoginPassword" + "=" + l_simplexAskingTelegramUnit.initialLoginPassword + "&");
            }
            else
            {
                l_sbValue.append("initialLoginPassword" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.initialTradePassword != null)
            {
                l_sbValue.append("initialTradePassword" + "=" + l_simplexAskingTelegramUnit.initialTradePassword + "&");
            }
            else
            {
                l_sbValue.append("initialTradePassword" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.initialOsePassword != null)
            {
                l_sbValue.append("initialOsePassword" + "=" + l_simplexAskingTelegramUnit.initialOsePassword + "&");
            }
            else
            {
                l_sbValue.append("initialOsePassword" + "=" + "&");
            }
            l_sbValue.append("cashOutBankCd" + "=" + "&");
            l_sbValue.append("cashOutBranchCd" + "=" + "&");
            l_sbValue.append("cashOutAccountType" + "=" + "&");
            l_sbValue.append("cashOutAccountNo" + "=" + "&");
            l_sbValue.append("cashOutAccountName" + "=" + "&");
            l_sbValue.append("virtualBankCd" + "=" + "&");
            l_sbValue.append("virtualBranchCd" + "=" + "&");
            l_sbValue.append("virtualAccountType" + "=" + "&");
            l_sbValue.append("virtualAccountNo" + "=" + "&");
            l_sbValue.append("virtualAccountName" + "=" + "&");
            if (l_simplexAskingTelegramUnit.timeStamp != null)
            {
                l_sbValue.append("timeStamp" + "=" + l_simplexAskingTelegramUnit.timeStamp + "&");
            }
            else
            {
                l_sbValue.append("timeStamp" + "=" + "&");
            }
            if (l_simplexAskingTelegramUnit.hashValue != null)
            {
                l_sbValue.append("hashValue" + "=" + l_simplexAskingTelegramUnit.hashValue);
            }
            else
            {
                l_sbValue.append("hashValue" + "=");
            }
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�����Ȃ��V�X�e���G���[���������܂����B");
        }
        //StringBuffer.toString()��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_sbValue.toString();
    }

    /**
     * (createSimplex�˗��d������)<BR>
     * Simplex�����J�݈˗��d�����ׂ��쐬����B<BR>
     * <BR>
     * ����.GFT�d�����b�Z�[�W����A�u�iDIR-Simplex�j�d���t�H�[�}�b�g.xls�v�̌����J�ݕ������Q�l���A<BR>
     * Simplex�˗��d�����ׂ��쐬����B<BR>
     * <BR>
     * �i*�jSimplex�˗��d������.�����敪 = GFT�d�����b�Z�[�W.�����敪<BR>
     * @@param l_message - (GFT�d�����b�Z�[�W)<BR>
     * GFT�d�����b�Z�[�W<BR>
     * @@return WEB3FXSimplexAskingTelegramUnit
     * @@throws WEB3BaseException
     */
    protected WEB3FXSimplexAskingTelegramUnit createSimplexAskingTelegramUnit(
        Message l_message)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSimplexAskingTelegramUnit(Message)";
        log.entering(STR_METHOD_NAME);
        if (l_message == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        if (!(l_message instanceof WEB3FXGftAskingTelegramUnit))
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }
        WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit = new WEB3FXSimplexAskingTelegramUnit();
        WEB3FXGftAskingTelegramUnit l_gftAskingTelegramUnit = (WEB3FXGftAskingTelegramUnit)l_message;
        String l_strOperationDiv = l_gftAskingTelegramUnit.gftOperationDiv;
        l_simplexAskingTelegramUnit.simplexOperationDiv = l_strOperationDiv;
        //�����J��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_gftAskingTelegramUnit.institutionCode,
                l_gftAskingTelegramUnit.branchCode,
                l_gftAskingTelegramUnit.accountCode);
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //���FX���O�C��ID
        l_simplexAskingTelegramUnit.oseFxLoginId = l_gftAskingTelegramUnit.fxFirstLoginId;
        //�ڋq���J�i
        //�Ή��Ƃ��ẮA"�i"�A"�j"�A"."�Ɋւ��Ă���𔼊p�X�y�[�X�ɕϊ�����Ƃ����Ή�������
        String l_strfullNameKana = WEB3StringTypeUtility.to1byteKana(l_mainAccountRow.getFamilyNameAlt1());
        l_simplexAskingTelegramUnit.fullNameKana = l_strfullNameKana.replaceAll("\\(|\\)|\\."," ");
        //�ڋq��
        l_simplexAskingTelegramUnit.fullName = l_mainAccountRow.getFamilyName();
        //����
        l_simplexAskingTelegramUnit.genderType = l_mainAccountRow.getSex();
        //�X�֔ԍ�
        l_simplexAskingTelegramUnit.zipCode = l_mainAccountRow.getZipCode();
        //�Z���P
        l_simplexAskingTelegramUnit.address1 = l_gftAskingTelegramUnit.address1;
        //�Z��2
        if(l_gftAskingTelegramUnit.address2 == null)
        {
        	l_simplexAskingTelegramUnit.address2 = " ";	
        }
        else
        {
            l_simplexAskingTelegramUnit.address2 = l_gftAskingTelegramUnit.address2;
        }
        //�Z��3
        l_simplexAskingTelegramUnit.address3 = l_gftAskingTelegramUnit.address3;
        //��ꃁ�[���A�h���X
        l_simplexAskingTelegramUnit.mail1 = l_gftAskingTelegramUnit.fxMailAddress;
        //�������O�C���p�X���[�h
        l_simplexAskingTelegramUnit.initialLoginPassword = l_gftAskingTelegramUnit.fxFirstPassword;
        //��������p�X���[�h
        l_simplexAskingTelegramUnit.initialTradePassword = l_gftAskingTelegramUnit.fxFirstPassword;
        //������؃p�X���[�h
        l_simplexAskingTelegramUnit.initialOsePassword = l_gftAskingTelegramUnit.fxPassword2;
        //�^�C���X�^���v
        l_simplexAskingTelegramUnit.timeStamp = l_gftAskingTelegramUnit.dirSendTime;
        //�n�b�V���l
        l_simplexAskingTelegramUnit.hashValue = l_gftAskingTelegramUnit.hashValue;

        log.exiting(STR_METHOD_NAME);
        return l_simplexAskingTelegramUnit;
    }
}
@
