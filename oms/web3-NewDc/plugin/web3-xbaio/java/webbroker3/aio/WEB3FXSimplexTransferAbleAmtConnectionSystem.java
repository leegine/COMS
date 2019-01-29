head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXSimplexTransferAbleAmtConnectionSystem.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Simplex�U�։\�z�ڑ��V�X�e���iWEB3FXSimplexTransferAbleAmtConnectionSystem.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/17 �����F (���u) �V�K�쐬�E���f��1200 1226
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXSimplexAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (Simplex�U�։\�z�ڑ��V�X�e��)<BR>
 * Simplex�U�։\�z�ڑ��V�X�e���N���X<BR>
 * <BR>
 * @@author �����F(���u)
 * @@version 1.0
 */
public class WEB3FXSimplexTransferAbleAmtConnectionSystem extends WEB3FXSimplexConnectionSystem
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSimplexTransferAbleAmtConnectionSystem.class);
    
    /**
     * Simplex�U�։\�z�p�n�b�V���𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�jSimplex�˗��d������.�^�C���X�^���v �� <BR>
     * �@@����.�I�y���[�V��������A�����AMD5�Í����������̂�啶���ɂ����l���擾����B <BR>
     * <BR>
     * �Q�j����.Simplex�˗��d�����ׂ̃n�b�V���l�Ɏ擾�����n�b�V���l���Z�b�g����B <BR>
     * <BR>
     * �R�j����.Simplex�˗��d�����ׂ�ԋp����B<BR>
     * <BR>
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
        //Simplex�˗��d������.�^�C���X�^���v ��
        //����.�I�y���[�V��������A�����AMD5�Í����������̂�啶���ɂ����l���擾����B
        String[] l_strValue =  new String[]{
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
     * Simplex�U�։\�z�擾�p�ڑ�URL�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�jStringBuffer�̃C���X�^���X���쐬����B <BR>
     * <BR>
     * �Q�jStringBuffer�ɁA����.�G���h�|�C���g����ǉ�����B <BR>
     * <BR>
     * �R�jStringBuffer�ɁA������GetWithdrawLimit.do��ǉ� <BR>
     * <BR>
     * �S�jStringBuffer�ɁAhttp����URL�p '?' �𖖔��ɒǉ�����B <BR>
     * <BR>
     * �T�j����.simplex�˗��d���̍��ڕ� <BR>
     * �⑫�����u�iDIR-Simplex�j�d���t�H�[�}�b�g.xls�v��sheet�uURL�ɂ��Ă̐U�։\�z�擾�v���Q�Ƃ��A<BR>
     * ���L�������s���F <BR>
     * �@@�T�|�P�j���ږ��@@+�@@"="�@@+�@@���ڒl + "&"�@@ <BR>
     * �@@�@@�@@�@@�@@(�Ō�̍��ڂɏ������A"&"��ǉ��s�v�A���ڒl��null�̏ꍇ�A���ڒl��ǉ����Ȃ�) <BR>
     * �@@�T�|�Q�j1�ɂĐ��������������StringBuffer�̖����ɒǉ�����B <BR>
     * <BR>
     * �U�jStringBuffer.toString()��Ԃ��B<BR>
     * <BR>
     * @@param l_strEndpointName - (�G���h�|�C���g��)<BR>
     * �G���h�|�C���g��<BR>
     * @@param l_simplexAskingTelegramUnit - (Simplex�˗��d������)<BR>
     * Simplex�˗��d������<BR>
     * @@return String
     */
    protected String createURL(String l_strEndpointName, WEB3FXSimplexAskingTelegramUnit l_simplexAskingTelegramUnit)
        throws WEB3BaseException
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
        //StringBuffer�ɁA������GetWithdrawLimit.do��ǉ� 
        l_sbValue.append("/GetWithdrawLimit.do");
        //StringBuffer�ɁAhttp����URL�p '?' �𖖔��ɒǉ�����
        l_sbValue.append("?");
        //����.simplex�˗��d���̍��ڕ�
        //�⑫�����u�iDIR-Simplex�j�d���t�H�[�}�b�g.xls�v��sheet�uURL�ɂ��Ă̐U�։\�z�擾�v���Q�Ƃ��A
        //���L�������s���F
        //���ږ��@@+�@@"="�@@+�@@���ڒl + "&"
        //(�Ō�̍��ڂɏ������A"&"��ǉ��s�v�A���ڒl��null�̏ꍇ�A���ڒl��ǉ����Ȃ�)
        //1�ɂĐ��������������StringBuffer�̖����ɒǉ�����B 
        if (l_simplexAskingTelegramUnit.oseAccountId != null)
        {
            l_sbValue.append("oseAccountId" + "=" + l_simplexAskingTelegramUnit.oseAccountId + "&");
        }
        else
        {
            l_sbValue.append("oseAccountId" + "=" + "&");
        }
        l_sbValue.append("tie-upAccountId" + "=" + "&");
        l_sbValue.append("tie-upSystemId" + "=" + "&");
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
        //StringBuffer.toString()��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_sbValue.toString();
    }

    /**
     * (createSimplex�˗��d������)<BR>
     * Simplex�����J�݈˗��d�����ׂ��쐬����B<BR>
     * <BR>
     * ����.GFT�d�����b�Z�[�W����A�u�iDIR-Simplex�j�d���t�H�[�}�b�g.xls�v�̐U�։\�z�擾�������Q�l���A<BR>
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

        //�U�։\�z
        //���FX�����ԍ�
        l_simplexAskingTelegramUnit.oseAccountId = l_gftAskingTelegramUnit.fxFirstLoginId;
        //�^�C���X�^���v
        l_simplexAskingTelegramUnit.timeStamp = l_gftAskingTelegramUnit.dirSendTime;
        //�n�b�V���l
        l_simplexAskingTelegramUnit.hashValue = l_gftAskingTelegramUnit.hashValue;

        log.exiting(STR_METHOD_NAME);
        return l_simplexAskingTelegramUnit;
    }
}
@
