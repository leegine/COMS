head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTelegramProcessServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX�d�������T�[�r�XImpl(WEB3FXTelegramProcessServiceImpl)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
                  : 2006/08/01 ���(SCS)�@@���f��No.609�Ή�
 Revesion History : 2009/08/25 �И���(���u)�@@���f��No.1193�Ή�
 Revesion History : 2009/10/20 �����F(���u)�@@���f��No.1242�Ή�
 */

package webbroker3.aio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.data.GftMessageParams;
import webbroker3.aio.define.WEB3AioHashAlgorithmDef;
import webbroker3.aio.define.WEB3GftTelegramFormatDef;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GftMessageDivDef;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�d�������T�[�r�XImpl) <BR>
 * FX�d�������T�[�r�X�����N���X <BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXTelegramProcessServiceImpl implements
    WEB3FXTelegramProcessService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FXTelegramProcessServiceImpl.class);
    
    /**
     * (createGFT�d���n�b�V���l) <BR>
     * GFT�˗��d���̓��e����n�b�V���l�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j �n�b�V�����ڂ̘A��������̍쐬 <BR>
     * <BR>
     * �u�L�[��=�l�v���Z�b�g�ɂ���������̔z����ȉ��̏��ō쐬���� <BR>
     * <BR>
     * DIR_sendTime=����.GFT�˗��d������.DIR��GFT���M���� <BR>
     * Operation=����.GFT�˗��d������.�����敪 <BR>
     * Account=����.GFT�˗��d������.�ב֕ۏ؋������ԍ� <BR>
     * email==����.GFT�˗��d������.���[���A�h���X <BR>
     * GFT_link_1=����.GFT�˗��d������.�������O�C��ID <BR>
     * GFT_link_2=����.GFT�˗��d������.�����p�X���[�h <BR>
     * Group_name=����.GFT�˗��d������.�S���敪 <BR>
     * Amount=����.GFT�˗��d������.���o���z <BR>
     * wolfSessionKey=����.GFT�˗��d������.WOLF�Z�b�V�����L�[ <BR>
     * aid=����.GFT�˗��d������.�A�v���P�[�V����ID <BR>
     * aa_icd=����.GFT�˗��d������.��ЃR�[�h <BR>
     * aa_bcd=����.GFT�˗��d������.���X�R�[�h <BR>
     * aa_accd=����.GFT�˗��d������.�ڋq�R�[�h <BR>
     * linked_1=����.GFT�˗��d������.���ʃR�[�h <BR>
     * <BR>
     * �Q�j �n�b�V���l�̐��� <BR>
     * <BR>
     * WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B <BR>
     * [�����̐ݒ�] <BR>
     * �v�Z�����F "MD5" <BR>
     * �v�Z�ΏہF �P�j�ɂč쐬����������z�� <BR>
     * <BR>
     * �R�j ���������n�b�V���l��ԋp����B <BR>
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41BE7FC202DE
     */
    public String createGFTTelegramHashValue(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createGFTTelegramHashValue(" +
            "WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftAskingTelegramUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@�n�b�V�����ڂ̘A��������̍쐬 
        //  �u�L�[��=�l�v���Z�b�g�ɂ���������̔z����ȉ��̏��ō쐬���� 
        List l_lisWhere = new Vector();
        
        //   DIR_sendTime=����.GFT�˗��d������.DIR��GFT���M���� 
        l_lisWhere.add(WEB3GftTelegramFormatDef.DIR_sendTime + "=" + 
            ((l_fxGftAskingTelegramUnit.dirSendTime == null) ? "" : 
                l_fxGftAskingTelegramUnit.dirSendTime));
        
        //�@@ Operation=����.GFT�˗��d������.�����敪 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Operation + "=" + 
            ((l_fxGftAskingTelegramUnit.gftOperationDiv == null) ? "" : 
                l_fxGftAskingTelegramUnit.gftOperationDiv));
        
        //�@@ Account=����.GFT�˗��d������.�ב֕ۏ؋������ԍ� 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Account + "=" + 
            ((l_fxGftAskingTelegramUnit.fxAccountCode == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxAccountCode));
        
        //�@@ email==����.GFT�˗��d������.���[���A�h���X 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.email + "=" + 
            ((l_fxGftAskingTelegramUnit.fxMailAddress == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxMailAddress));
        
        //�@@ GFT_link_1=����.GFT�˗��d������.�������O�C��ID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_1 +  "=" + 
            ((l_fxGftAskingTelegramUnit.fxFirstLoginId == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxFirstLoginId));
        
        //�@@ GFT_link_2=����.GFT�˗��d������.�����p�X���[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_2 + "=" + 
            ((l_fxGftAskingTelegramUnit.fxFirstPassword == null) ? "" : 
                l_fxGftAskingTelegramUnit.fxFirstPassword));
        
        //�@@ Group_name=����.GFT�˗��d������.�S���敪 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Group_name + "=" + 
            ((l_fxGftAskingTelegramUnit.groupName == null) ? "" : 
                l_fxGftAskingTelegramUnit.groupName));
        
        //�@@ Amount=����.GFT�˗��d������.���o���z 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Amount + "=" + 
            ((l_fxGftAskingTelegramUnit.cashinoutAmt == null) ? "" : 
                l_fxGftAskingTelegramUnit.cashinoutAmt));
        
        //�@@ wolfSessionKey=����.GFT�˗��d������.WOLF�Z�b�V�����L�[ 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.wolfSessionKey + "=" + 
            ((l_fxGftAskingTelegramUnit.wolfSession == null) ? "" : 
                l_fxGftAskingTelegramUnit.wolfSession));
        
        //�@@ aid=����.GFT�˗��d������.�A�v���P�[�V����ID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.aa_aid + "=" + 
            ((l_fxGftAskingTelegramUnit.wolfAid == null) ? "" : 
                l_fxGftAskingTelegramUnit.wolfAid));
        
        //�@@ aa_icd=����.GFT�˗��d������.��ЃR�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.cpy + "=" + 
            ((l_fxGftAskingTelegramUnit.institutionCode == null) ? "" : 
                l_fxGftAskingTelegramUnit.institutionCode));
        
        //�@@ aa_bcd=����.GFT�˗��d������.���X�R�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.brn + "=" + 
            ((l_fxGftAskingTelegramUnit.branchCode == null) ? "" :
                l_fxGftAskingTelegramUnit.branchCode));
        
        //�@@ aa_accd=����.GFT�˗��d������.�ڋq�R�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.acc + "=" + 
            ((l_fxGftAskingTelegramUnit.accountCode == null) ? "" : 
                l_fxGftAskingTelegramUnit.accountCode));
        
        //�@@ linked_1=����.GFT�˗��d������.���ʃR�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.req + "=" + 
            ((l_fxGftAskingTelegramUnit.requestNumber == null) ? "" : 
                l_fxGftAskingTelegramUnit.requestNumber));
        
        // test log
        for (int i = 0; i < l_lisWhere.size(); i ++)
        {
            log.debug("l_lisWhere(" + i + ") = " + l_lisWhere.get(i));
            
        }
        //�Q�j�@@�n�b�V���l�̐��� 
        //   WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B 
        //�@@ [�����̐ݒ�] 
        //�@@ �v�Z�����F�@@"MD5" 
        //�@@ �v�Z�ΏہF�@@�P�j�ɂč쐬����������z�� 
        String[] l_strAlgorithmObj = new String[l_lisWhere.size()];
        l_lisWhere.toArray(l_strAlgorithmObj);
        String l_strHashValue = 
            WEB3StringTypeUtility.createHashValue(
                WEB3AioHashAlgorithmDef.MD5, l_strAlgorithmObj); 
        
        //�R�j�@@���������n�b�V���l��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_strHashValue;
    }

    /**
     * (createGFT�d���n�b�V���l) <BR>
     * GFT���ʒʒm�d���̓��e����n�b�V���l�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j �n�b�V�����ڂ̘A��������̍쐬 <BR>
     * <BR>
     * �u�L�[��=�l�v���Z�b�g�ɂ���������̔z����ȉ��̏��ō쐬���� <BR>
     * <BR>
     * DIR_sendTime=����.GFT���ʒʒm�d������.DIR��GFT���M���� <BR>
     * Operation=����.GFT���ʒʒm�d������.�����敪 <BR>
     * Account=����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ� <BR>
     * email==����.GFT���ʒʒm�d������.���[���A�h���X <BR>
     * GFT_link_1=����.GFT���ʒʒm�d������.�������O�C��ID <BR>
     * GFT_link_2=����.GFT���ʒʒm�d������.�����p�X���[�h <BR>
     * Group_name=����.GFT���ʒʒm�d������.�S���敪 <BR>
     * Amount=����.GFT���ʒʒm�d������.���o���z <BR>
     * wolfSessionKey=����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[ <BR>
     * aid=����.GFT���ʒʒm�d������.�A�v���P�[�V����ID <BR>
     * aa_icd=����.GFT���ʒʒm�d������.��ЃR�[�h <BR>
     * aa_bcd=����.GFT���ʒʒm�d������.���X�R�[�h <BR>
     * aa_accd=����.GFT���ʒʒm�d������.�ڋq�R�[�h <BR>
     * linked_1=����.GFT���ʒʒm�d������.���ʃR�[�h <BR>
     * resultCode=����.GFT���ʒʒm�d������.��t���� <BR>
     * GFT_sendTime=����.GFT���ʒʒm�d������.GFT��DIR���M���� <BR>
     * GFT_act1=����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i1���ʉ݁j <BR>
     * GFT_act2=����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i10���ʉ݁j <BR>
     * <BR>
     * �Q�j �n�b�V���l�̐��� <BR>
     * <BR>
     * WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B <BR>
     * [�����̐ݒ�] <BR>
     * �v�Z�����F "MD5" <BR>
     * �v�Z�ΏہF �P�j�ɂč쐬����������z�� <BR>
     * <BR>
     * �R�j ���������n�b�V���l��ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41BE94CC0167
     */
    public String createGFTTelegramHashValue(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createGFTTelegramHashValue(" +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�n�b�V�����ڂ̘A��������̍쐬 
        //�u�L�[��=�l�v���Z�b�g�ɂ���������̔z����ȉ��̏��ō쐬���� 
        List l_lisWhere = new Vector();

        //DIR_sendTime=����.GFT���ʒʒm�d������.DIR��GFT���M���� 
        l_lisWhere.add(WEB3GftTelegramFormatDef.DIR_sendTime + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.dirSendTime == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.dirSendTime));
        
        //Operation=����.GFT���ʒʒm�d������.�����敪 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Operation + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftOperationDiv == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.gftOperationDiv));
        
        //Account=����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ� 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Account+ "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxAccountCode == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.fxAccountCode));
        
        //email==����.GFT���ʒʒm�d������.���[���A�h���X 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.email + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxMailAddress == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.fxMailAddress));
        
        //GFT_link_1=����.GFT���ʒʒm�d������.�������O�C��ID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_1 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxFirstLoginId == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.fxFirstLoginId));
        
        //GFT_link_2=����.GFT���ʒʒm�d������.�����p�X���[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_link_2 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.fxFirstPassword == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.fxFirstPassword));
        
        //Group_name=����.GFT���ʒʒm�d������.�S���敪 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Group_name + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.groupName == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.groupName));
        
        //Amount=����.GFT���ʒʒm�d������.���o���z 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.Amount + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.cashinoutAmt == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.cashinoutAmt));
        
        //wolfSessionKey=����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[ 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.wolfSessionKey + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.wolfSession == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.wolfSession));
        
        //aid=����.GFT���ʒʒm�d������.�A�v���P�[�V����ID 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.aa_aid + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.wolfAid == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.wolfAid));
        
        //aa_icd=����.GFT���ʒʒm�d������.��ЃR�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.cpy + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.institutionCode == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.institutionCode));
        
        //aa_bcd=����.GFT���ʒʒm�d������.���X�R�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.brn + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.branchCode == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.branchCode));
        
        //aa_accd=����.GFT���ʒʒm�d������.�ڋq�R�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.acc + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.accountCode == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.accountCode));
        
        //linked_1=����.GFT���ʒʒm�d������.���ʃR�[�h 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.req + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.requestNumber == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.requestNumber));

        //resultCode=����.GFT���ʒʒm�d������.��t���� 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.resultCode + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.resultCode == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.resultCode));
        
        //GFT_sendTime=����.GFT���ʒʒm�d������.GFT��DIR���M���� 
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_sendTime + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftSendTime == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.gftSendTime));
        
        //GFT_act1=����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i1���ʉ݁j
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_ac1 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftAcc1 == null) ? "" : 
                l_fxGftResultNoticeTelegramUnit.gftAcc1)); 
        
        //GFT_act2=����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i10���ʉ݁j
        l_lisWhere.add("&" + WEB3GftTelegramFormatDef.GFT_ac2 + "=" + 
            ((l_fxGftResultNoticeTelegramUnit.gftAcc2 == null) ? "" :
                l_fxGftResultNoticeTelegramUnit.gftAcc2)); 
       
        // test log
        for (int i = 0; i < l_lisWhere.size(); i ++)
        {
            log.debug("l_lisWhere(" + i + ") = " + l_lisWhere.get(i));
            
        }

        //�Q�j�n�b�V���l�̐��� 
        //WEB3StringTypeUtility.createHashValue()��p���ăn�b�V���l�𐶐�����B 
        //[�����̐ݒ�] 
        //�v�Z�����F�@@"MD5" 
        //�v�Z�ΏہF�@@�P�j�ɂč쐬����������z��
        String[] l_strAlgorithmObj = new String[l_lisWhere.size()];
        l_lisWhere.toArray(l_strAlgorithmObj);
        String l_strHashValue = 
            WEB3StringTypeUtility.createHashValue(
                WEB3AioHashAlgorithmDef.MD5, l_strAlgorithmObj); 
        
        //�R�j�@@���������n�b�V���l��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return l_strHashValue;
    }

    /**
     * (isGFT�d�����ڐݒ�) <BR>
     * GFT���ʒʒm�d�����ׂ̕K�{���ڂɒl���ݒ肳��Ă��邩�𔻒肷��B <BR>
     * �ݒ肳��Ă���ꍇ�Atrue���A�ݒ肳��Ă��Ȃ��ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �P�j ���ʕK�{���ڃ`�F�b�N <BR>
     * <BR>
     * �ȉ��̒l�̂����ꂩ��null�ł������ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �E����.GFT���ʒʒm�d������.DIR��GFT���M���� <BR>
     * �E����.GFT���ʒʒm�d������.�����敪 <BR>
     * �E����.GFT���ʒʒm�d������.�������O�C��ID <BR>
     * �E����.GFT���ʒʒm�d������.�S���敪 <BR>
     * �E����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[ <BR>
     * �E����.GFT���ʒʒm�d������.�A�v���P�[�V����ID <BR>
     * �E����.GFT���ʒʒm�d������.�Đ����T�[�r�XID <BR>
     * �E����.GFT���ʒʒm�d������.SSID <BR>
     * �E����.GFT���ʒʒm�d������.��ЃR�[�h <BR>
     * �E����.GFT���ʒʒm�d������.���X�R�[�h <BR>
     * �E����.GFT���ʒʒm�d������.�ڋq�R�[�h <BR>
     * �E����.GFT���ʒʒm�d������.���ʃR�[�h <BR>
     * �E����.GFT���ʒʒm�d������.��t���� <BR>
     * �E����.GFT���ʒʒm�d������.GFT��DIR���M���� <BR>
     * �E����.GFT���ʒʒm�d������.�n�b�V���l <BR>
     * <BR>
     * �Q�j �����敪�ʕK�{���ڃ`�F�b�N <BR>
     * <BR>
     * ������.GFT���ʒʒm�d������.�����敪��01�i�����J�݁j�̏ꍇ <BR>
     * <BR>
     * �ȉ��̒l�̂����ꂩ��null�ł������ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �E����.GFT���ʒʒm�d������.���[���A�h���X <BR>
     * �E����.GFT���ʒʒm�d������.�����p�X���[�h <BR>
     * �E����.GFT���ʒʒm�d������.���O�i���j <BR>
     * 
     * �@@�ȉ��̃`�F�b�N�́A����.GFT���ʒʒm�d������.��t���ʂ�"00000000"�̏ꍇ�̂ݍs���B
     * �@@����ȊO�̏ꍇ�́A�`�F�b�N���X�L�b�v����B
     * �E����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i1���ʉ݁j <BR>
     * �E����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i10���ʉ݁j <BR>
     * <BR>
     * ������.GFT���ʒʒm�d������.�����敪��02�i�����j�A�܂��́A03�i�o���j�̏ꍇ <BR>
     * <BR>
     * �ȉ��̒l�̂����ꂩ��null�ł������ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �E����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ� <BR>
     * �E����.GFT���ʒʒm�d������.���o���z <BR>
     * <BR>
     * �R�j true��ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0017
     */
    public boolean isGFTTelegramSet(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isGFTTelegramSet(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit) ";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@���ʕK�{���ڃ`�F�b�N 
        //  �ȉ��̒l�̂����ꂩ��null�ł������ꍇ�Afalse��ԋp����B 
        //�E����.GFT���ʒʒm�d������.DIR��GFT���M���� 
        //�E����.GFT���ʒʒm�d������.�����敪 
        //�E����.GFT���ʒʒm�d������.�������O�C��ID 
        //�E����.GFT���ʒʒm�d������.�S���敪 
        //�E����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[ 
        //�E����.GFT���ʒʒm�d������.�A�v���P�[�V����ID 
        //�E����.GFT���ʒʒm�d������.�Đ����T�[�r�XID 
        //�E����.GFT���ʒʒm�d������.SSID 
        //�E����.GFT���ʒʒm�d������.��ЃR�[�h 
        //�E����.GFT���ʒʒm�d������.���X�R�[�h 
        //�E����.GFT���ʒʒm�d������.�ڋq�R�[�h 
        //�E����.GFT���ʒʒm�d������.���ʃR�[�h 
        //�E����.GFT���ʒʒm�d������.��t���� 
        //�E����.GFT���ʒʒm�d������.GFT��DIR���M���� 
        //�E����.GFT���ʒʒm�d������.�n�b�V���l 
        if (WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.dirSendTime)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftOperationDiv)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxFirstLoginId)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.groupName)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.wolfSession)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.wolfAid)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.regetServiceId)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.wolfSsid)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.institutionCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.branchCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.accountCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.requestNumber)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.resultCode)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftSendTime)
            || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.hashValue))    
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        } 
                   
        //�Q�j�@@�����敪�ʕK�{���ڃ`�F�b�N 
        //������.GFT���ʒʒm�d������.�����敪��01�i�����J�݁j�̏ꍇ 
        //�ȉ��̒l�̂����ꂩ��null�ł������ꍇ�Afalse��ԋp����B
        if (WEB3GftMessageOperationDef.ACCOUNT_OPEN.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv))
        {
            //�E����.GFT���ʒʒm�d������.���[���A�h���X 
            //�E����.GFT���ʒʒm�d������.�����p�X���[�h 
            //�E����.GFT���ʒʒm�d������.���O�i���j 
            //�E����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i1���ʉ݁j 
            //�E����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ��i10���ʉ݁j 
                       
			if (WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxMailAddress)
				|| WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxFirstPassword)
				|| WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxLastName))

			{
				log.exiting(STR_METHOD_NAME);
				return false;
			}


			if(l_fxGftResultNoticeTelegramUnit.resultCode.equals(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000))
			{
				if(WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftAcc1)
				   || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.gftAcc2))
				{
					log.exiting(STR_METHOD_NAME);
					return false;
				}				   
			}
        }
        
        //������.GFT���ʒʒm�d������.�����敪��02�i�����j�A�܂��́A03�i�o���j�̏ꍇ 
        //�ȉ��̒l�̂����ꂩ��null�ł������ꍇ�Afalse��ԋp����B 
        if (WEB3GftMessageOperationDef.CASH_OUT.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv)
            || WEB3GftMessageOperationDef.CASH_IN.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv))
        {
            //�E����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ� 
            //�E����.GFT���ʒʒm�d������.���o���z 
            if (WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.fxAccountCode)
                || WEB3StringTypeUtility.isEmpty(l_fxGftResultNoticeTelegramUnit.cashinoutAmt))
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }   

        //�R�j�@@true��ԋp����B   
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (isGFT�d������������v) <BR>
     * GFT���ʒʒm�d�����ׂ̓��L���ځiGFT�ݒ荀�ځj�ɂ��āA<BR>
     * �����Ƒ������t�H�[�}�b�g�� ��v���Ă��邩���肷��B <BR>
     * ��v���Ă���ꍇ�Atrue���A��v���Ă��Ȃ��ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �P�j ��t���� <BR>
     * ����.GFT���ʒʒm�d������.��t���ʂ��ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �E�����ȊO <BR>
     * �E8���ȊO <BR>
     * <BR>
     * �Q�j GFT��DIR���M���� <BR>
     * ����.GFT���ʒʒm�d������.GFT��DIR���M�������ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�A<BR>
     *      false��ԋp����B <BR>
     * <BR>
     * �E�����ȊO <BR>
     * �E14���ȊO <BR>
     * <BR>
     * �R�j �ב֕ۏ؋������ԍ�(1���ʉ�)�A�ב֕ۏ؋������ԍ�(10���ʉ�) <BR>
     * <BR>
     * ������.GFT���ʒʒm�d������.�����敪��01(�����J��)�̏ꍇ���A<BR>
     *   ����.GFT���ʒʒm�d������.��t���ʂ�"00000000"�̏ꍇ�̂ݍs���B <BR>
     * <BR>
     * ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(1���ʉ�)�A����� <BR>
     * ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(10���ʉ�)�A<BR>
     * ���ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �E�����ȊO <BR>
     * �E10�� ���傫�� <BR>
     * <BR>
     * �S�j true��ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0027
     */
    public boolean isGFTTelegramLengthPropSame(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isGFTTelegramLengthPropSame(" +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);    
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@��t���� 
        //����.GFT���ʒʒm�d������.��t���ʂ��ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B 
        //�E�����ȊO 
        //�E8���ȊO
        if (!WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.resultCode)
            || l_fxGftResultNoticeTelegramUnit.resultCode.getBytes().length != 8)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@GFT��DIR���M���� 
        // ����.GFT���ʒʒm�d������.GFT��DIR���M�������ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B 
        //�E�����ȊO 
        //�E14���ȊO 
        if (!WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.gftSendTime)
            || l_fxGftResultNoticeTelegramUnit.gftSendTime.getBytes().length != 14)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�R�j�@@�ב֕ۏ؋������ԍ�(1���ʉ�)�A�ב֕ۏ؋������ԍ�(10���ʉ�) 
        // ������.GFT���ʒʒm�d������.�����敪��01(�����J��)�̏ꍇ���A
		//   ����.GFT���ʒʒm�d������.��t���ʂ�"00000000"�̏ꍇ�̂ݍs���B
        //����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(1���ʉ�)�A����� 
        //����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�(10���ʉ�)�A���ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B 
        //�E�����ȊO 
        //�E10�� ���傫�� 
        if (WEB3GftMessageOperationDef.ACCOUNT_OPEN.equals(l_fxGftResultNoticeTelegramUnit.gftOperationDiv)
            && l_fxGftResultNoticeTelegramUnit.resultCode.equals(WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000))
        {
            if (!WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.gftAcc1)
                || l_fxGftResultNoticeTelegramUnit.gftAcc1.getBytes().length > 10
                || !WEB3StringTypeUtility.isDigit(l_fxGftResultNoticeTelegramUnit.gftAcc2)
                || l_fxGftResultNoticeTelegramUnit.gftAcc2.getBytes().length > 10)
            {   
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //�S�j�@@true��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (isGFT�d������M���ڈ�v) <BR>
     * GFT���ʒʒm�d�����ׂ̍��ڒl���˗��d���̍��ڒl�ƈ�v���Ă��邩���肷��B <BR>
     * ��v���Ă���ꍇ�Atrue���A��v���Ă��Ȃ��ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �P�j �˗��d�����e�̎擾 <BR>
     * <BR>
     * FX�f�[�^����T�[�r�XImpl.getGFT�d���ۑ�()���R�[�����AGFT�d���ۑ�Params���擾����B<BR>
     * ���߂�l��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3SystemLayerException <BR>
     * tag: SYSTEM_ERROR_80005 <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * �d����ʋ敪�F 1(���M) <BR>
     * �����敪�F ����.GFT���ʒʒm�d������.�����敪 <BR>
     * �،���ЃR�[�h�F ����.GFT���ʒʒm�d������.��ЃR�[�h <BR>
     * ���X�R�[�h�F ����.GFT���ʒʒm�d������.���X�R�[�h <BR>
     * ���ʃR�[�h�F ����.GFT���ʒʒm�d������.���ʃR�[�h <BR>
     * <BR>
     * �Q�j ����M���ڈ�v�`�F�b�N <BR>
     * <BR>
     * �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �EGFT�d������Params.DIR��GFT���M���� != ����.GFT���ʒʒm�d������.DIR��GFT���M���� <BR>
     * �EGFT�d������Params.�����敪 != ����.GFT���ʒʒm�d������.�����敪 <BR>
     * �EGFT�d������Params.�ב֕ۏ؋������ԍ� != ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ� <BR>
     * �EGFT�d������Params.���[���A�h���X != ����.GFT���ʒʒm�d������.���[���A�h���X <BR>
     * �EGFT�d������Params.�������O�C��ID != ����.GFT���ʒʒm�d������.�������O�C��ID <BR>
     * �EGFT�d������Params.�����p�X���[�h != <BR>
     *     ����.GFT���ʒʒm�d������.�����p�X���[�h���}�X�N������������(*) <BR>
     * �EGFT�d������Params.�S���敪 != ����.GFT���ʒʒm�d������.�S���敪 <BR>
     * �EGFT�d������Params.���o���z != ����.GFT���ʒʒm�d������.���o���z (*2)<BR>
     * �EGFT�d������Params.WOLF�Z�b�V�����L�[ != ����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[ <BR>
     * �EGFT�d������Params.�A�v���P�[�V����ID != ����.GFT���ʒʒm�d������.�A�v���P�[�V����ID <BR>
     * �EGFT�d������Params.�Đ����T�[�r�XID != ����.GFT���ʒʒm�d������.�Đ����T�[�r�XID <BR>
     * �EGFT�d������Params.SSID != ����.GFT���ʒʒm�d������.SSID <BR>
     * �EGFT�d������Params.��ЃR�[�h != ����.GFT���ʒʒm�d������.��ЃR�[�h <BR>
     * �EGFT�d������Params.���X�R�[�h != ����.GFT���ʒʒm�d������.���X�R�[�h <BR>
     * �EGFT�d������Params.�ڋq�R�[�h != ����.GFT���ʒʒm�d������.�ڋq�R�[�h <BR>
     * �EGFT�d������Params.���ʃR�[�h != ����.GFT���ʒʒm�d������.���ʃR�[�h <BR>
     * <BR>
     *  (*)this.mask�p�X���[�h()���g�p<BR>
     *  (*2)GFT���ʒʒm�d������.�����敪��05�F�����i��OP�j�܂���06�F�o���i��OP�j�̏ꍇ�A<BR>
     *      ���o���z+���o���z2�ő��M���̓��o���z�Ɣ�r����B<BR>
     * <BR>
     * �R�j �n�b�V���l��v�`�F�b�N <BR>
     * <BR>
     * �R�|�P�j�n�b�V���l���� <BR>
     * this.createGFT�d���n�b�V���l()���R�[�����A�n�b�V���l�𐶐�����B <BR>
     * <BR>
     * [�����̐ݒ�] <BR>
     * GFT���ʒʒm�d�����ׁF ����.GFT���ʒʒm�d������ <BR>
     * <BR>
     * �R�|�Q�j�ȉ��ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �R�|�P�j�ɂĐ��������n�b�V���l != ����.GFT���ʒʒm�d������.�n�b�V���l <BR>
     * <BR>
     * �S�j true��ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0036
     */
    public boolean isGFTTelegramSendAndReceiveValueSame(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "isGFTTelegramSendAndReceiveValueSame(" +
            "WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_fxGftResultNoticeTelegramUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@�˗��d�����e�̎擾 
        //  FX�f�[�^����T�[�r�XImpl.getGFT�d���ۑ�()���R�[�����AGFT�d���ۑ�Params���擾����B 
        //�@@���߂�l��null�̏ꍇ�A��O��throw����B 
        //�@@[�����̐ݒ�] 
        //�@@�d����ʋ敪�F�@@1(���M) 
        //�@@�����敪�F�@@����.GFT���ʒʒm�d������.�����敪 
        //�@@�،���ЃR�[�h�F�@@����.GFT���ʒʒm�d������.��ЃR�[�h 
        //�@@���X�R�[�h�F�@@����.GFT���ʒʒm�d������.���X�R�[�h 
        //�@@���ʃR�[�h�F�@@����.GFT���ʒʒm�d������.���ʃR�[�h
        WEB3FXDataControlService l_fXDataControlService =
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        GftMessageParams l_gftMessageParams =
            l_fXDataControlService.getGFTMessage(
                WEB3GftMessageDivDef.SEND,
                l_fxGftResultNoticeTelegramUnit.gftOperationDiv,
                l_fxGftResultNoticeTelegramUnit.institutionCode,
                l_fxGftResultNoticeTelegramUnit.branchCode,
                l_fxGftResultNoticeTelegramUnit.requestNumber);
        if (l_gftMessageParams == null)
        {
            log.debug("GFT�d���ۑ��擾�G���[�B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "GFT�d���ۑ��擾�G���[�B");
        }
                
        //�Q�j�@@����M���ڈ�v�`�F�b�N 
        //  �ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B 
        //�EGFT�d������Params.DIR��GFT���M�����@@ != ����.GFT���ʒʒm�d������.DIR��GFT���M���� 
        //�EGFT�d������Params.�����敪 �@@!= ����.GFT���ʒʒm�d������.�����敪 
        //�EGFT�d������Params.�ב֕ۏ؋������ԍ� != ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ� 
        //�EGFT�d������Params.���[���A�h���X �@@!= ����.GFT���ʒʒm�d������.���[���A�h���X 
        //�EGFT�d������Params.�������O�C��ID �@@!= ����.GFT���ʒʒm�d������.�������O�C��ID 
        //�EGFT�d������Params.�����p�X���[�h �@@!= ����.GFT���ʒʒm�d������.�����p�X���[�h 
        //�EGFT�d������Params.�S���敪 �@@ != ����.GFT���ʒʒm�d������.�S���敪 
        //�EGFT�d������Params.���o���z �@@ != ����.GFT���ʒʒm�d������.���o���z 
        //�EGFT�d������Params.WOLF�Z�b�V�����L�[�@@!= ����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[ 
        //�EGFT�d������Params.�A�v���P�[�V����ID  != ����.GFT���ʒʒm�d������.�A�v���P�[�V����ID 
        //�EGFT�d������Params.�Đ����T�[�r�XID �@@!= ����.GFT���ʒʒm�d������.�Đ����T�[�r�XID 
        //�EGFT�d������Params.SSID �@@�@@�@@!= ����.GFT���ʒʒm�d������.SSID 
        //�EGFT�d������Params.��ЃR�[�h != ����.GFT���ʒʒm�d������.��ЃR�[�h 
        //�EGFT�d������Params.���X�R�[�h != ����.GFT���ʒʒm�d������.���X�R�[�h 
        //�EGFT�d������Params.�ڋq�R�[�h != ����.GFT���ʒʒm�d������.�ڋq�R�[�h 
        //�EGFT�d������Params.���ʃR�[�h != ����.GFT���ʒʒm�d������.���ʃR�[�h 

        // GFT�d������Params.���[���A�h���X == ����.GFT���ʒʒm�d������.���[���A�h���X --> true
        boolean l_blnIsMailAddressSame = false;
        if (l_fxGftResultNoticeTelegramUnit.fxMailAddress == null)
        {
            if (l_gftMessageParams.getEmail() == null)
            {
                l_blnIsMailAddressSame = true;
            }
        }
        else
        {
            l_blnIsMailAddressSame = 
                l_fxGftResultNoticeTelegramUnit.fxMailAddress.equals(l_gftMessageParams.getEmail());
        }

        //GFT�d������Params.�����p�X���[�h == ����.GFT���ʒʒm�d������.�����p�X���[�h --> true
        boolean l_blnIsFirstPasswordSame =  false;
        if (l_fxGftResultNoticeTelegramUnit.fxFirstPassword == null)
        {
            if (l_gftMessageParams.getGftLink2() == null)
            {
                l_blnIsFirstPasswordSame = true;
            }
        }
        else
        {
            String l_fxFirstPasswordMasked = 
                this.maskPassword(l_fxGftResultNoticeTelegramUnit.fxFirstPassword);
            l_blnIsFirstPasswordSame = 
                l_fxFirstPasswordMasked.equals(l_gftMessageParams.getGftLink2());
        }
        
        //GFT�d������Params.�ב֕ۏ؋������ԍ� != ����.GFT���ʒʒm�d������.�ב֕ۏ؋������ԍ�-->true
        boolean l_blnIsAccountCodeSame = false;
        if (l_fxGftResultNoticeTelegramUnit.fxAccountCode == null)
        {
            if (l_gftMessageParams.getAccount() == null)
            {
                l_blnIsAccountCodeSame = true;
            }
        }
        else
        {
            l_blnIsAccountCodeSame = 
                l_fxGftResultNoticeTelegramUnit.fxAccountCode.equals(l_gftMessageParams.getAccount());
        }
        
        //GFT�d������Params.���o���z �@@ != ����.GFT���ʒʒm�d������.���o���z-->true
        boolean l_blnIscashinoutAmtSame = false;

        //GFT���ʒʒm�d������.�����敪��05�F�����i��OP�j�܂���06�F�o���i��OP�j�̏ꍇ�A���o���z+���o���z2�ő��M���̓��o���z�Ɣ�r����B
        if (WEB3GftMessageOperationDef.CASH_IN_FUOP.equals(l_gftMessageParams.getOperation())
                || WEB3GftMessageOperationDef.CASH_OUT_FUOP.equals(l_gftMessageParams.getOperation())) {

            if (l_gftMessageParams.getAmount() == null && l_fxGftResultNoticeTelegramUnit.cashinoutAmt == null
                    && l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 == null) {
                l_blnIscashinoutAmtSame = true;
            } else {

                BigDecimal l_bdAmount1 = null;
                BigDecimal l_bdAmount2 = null;

                //���z�P�̃`�F�b�N
                if (WEB3StringTypeUtility.isEmptyOrBlank(l_fxGftResultNoticeTelegramUnit.cashinoutAmt)) {
                    l_bdAmount1 = new BigDecimal("0");
                } else {
                    l_bdAmount1 = new BigDecimal(l_fxGftResultNoticeTelegramUnit.cashinoutAmt);
                }

               //���z�Q�̃`�F�b�N
                if (WEB3StringTypeUtility.isEmptyOrBlank(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2)) {
                    l_bdAmount2 = new BigDecimal("0");
                } else {
                    l_bdAmount2 = new BigDecimal(l_fxGftResultNoticeTelegramUnit.cashinoutAmt2);
                }

                //���z�P�{���z�Q
                BigDecimal l_bdAmount12 = l_bdAmount1.add(l_bdAmount2);

                l_blnIscashinoutAmtSame = l_bdAmount12.toString().equals(l_gftMessageParams.getAmount());
            }

        }

        else if (l_fxGftResultNoticeTelegramUnit.cashinoutAmt == null)
        {
            if (l_gftMessageParams.getAmount() == null)
            {
                l_blnIscashinoutAmtSame = true;
            }
        }
        else
        {
            l_blnIscashinoutAmtSame = 
                l_fxGftResultNoticeTelegramUnit.cashinoutAmt.equals(l_gftMessageParams.getAmount());   
        }

        Date l_datDirSendTime = 
            WEB3DateUtility.getDate(l_gftMessageParams.getDirSendTime(), "yyyyMMddHHmmss");
        Date l_datGFTDirSendTime =
            WEB3DateUtility.getDate(l_fxGftResultNoticeTelegramUnit.dirSendTime, "yyyyMMddHHmmss");
        
        //GFT�d������Params.DIR��GFT���M�����@@ != ����.GFT���ʒʒm�d������.DIR��GFT���M���� 
        boolean l_blnIsDirSendTimeDifferent = 
            WEB3DateUtility.compareToSecond(l_datDirSendTime, l_datGFTDirSendTime) != 0;  
        //GFT�d������Params.�����敪 �@@!= ����.GFT���ʒʒm�d������.�����敪    
        boolean l_blnIsOperationDivSame = 
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv.equals(l_gftMessageParams.getOperation());
        //GFT�d������Params.�������O�C��ID �@@!= ����.GFT���ʒʒm�d������.�������O�C��ID     
        boolean l_blnIsFirstLoginIdSame = 
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId.equals(l_gftMessageParams.getGftLink1());
        //GFT�d������Params.�S���敪 �@@ != ����.GFT���ʒʒm�d������.�S���敪    
        boolean l_blnIsGroupNameSame = 
            l_fxGftResultNoticeTelegramUnit.groupName.equals(l_gftMessageParams.getGroupName());
        //GFT�d������Params.WOLF�Z�b�V�����L�[�@@!= ����.GFT���ʒʒm�d������.WOLF�Z�b�V�����L�[ 
        boolean l_blnIsWolfSessionKeySame = 
            l_fxGftResultNoticeTelegramUnit.wolfSession.equals(l_gftMessageParams.getWolfSessionKey());
        //GFT�d������Params.�A�v���P�[�V����ID  != ����.GFT���ʒʒm�d������.�A�v���P�[�V����ID     
        boolean l_blnIsAidSame = 
            l_fxGftResultNoticeTelegramUnit.wolfAid.equals(l_gftMessageParams.getAaAid());
        //GFT�d������Params.�Đ����T�[�r�XID �@@!= ����.GFT���ʒʒm�d������.�Đ����T�[�r�XID     
        boolean l_blnIsAaRsidSame = 
            l_fxGftResultNoticeTelegramUnit.regetServiceId.equals(l_gftMessageParams.getAaRsid());
        //GFT�d������Params.SSID �@@�@@�@@!= ����.GFT���ʒʒm�d������.SSID    
        boolean l_blnIsSsidSame = 
            l_fxGftResultNoticeTelegramUnit.wolfSsid.equals(l_gftMessageParams.getSsid());
        //GFT�d������Params.��ЃR�[�h != ����.GFT���ʒʒm�d������.��ЃR�[�h     
        boolean l_blnIsCpySame = 
            l_fxGftResultNoticeTelegramUnit.institutionCode.equals(l_gftMessageParams.getCpy());
        //GFT�d������Params.���X�R�[�h != ����.GFT���ʒʒm�d������.���X�R�[�h     
        boolean l_blnIsBrnSame = 
            l_fxGftResultNoticeTelegramUnit.branchCode.equals(l_gftMessageParams.getBrn());
        //GFT�d������Params.�ڋq�R�[�h != ����.GFT���ʒʒm�d������.�ڋq�R�[�h    
        boolean l_blnIsAccSame = 
            l_fxGftResultNoticeTelegramUnit.accountCode.equals(l_gftMessageParams.getAcc());
        //GFT�d������Params.���ʃR�[�h != ����.GFT���ʒʒm�d������.���ʃR�[�h    
        boolean l_blnIsReqSame = 
            l_fxGftResultNoticeTelegramUnit.requestNumber.equals(l_gftMessageParams.getReq());
        if (l_blnIsDirSendTimeDifferent
            || !l_blnIsOperationDivSame
            || !l_blnIsAccountCodeSame
            || !l_blnIsMailAddressSame
            || !l_blnIsFirstLoginIdSame
            || !l_blnIsFirstPasswordSame
            || !l_blnIsGroupNameSame
            || !l_blnIscashinoutAmtSame
            || !l_blnIsWolfSessionKeySame
            || !l_blnIsAidSame
            || !l_blnIsAaRsidSame
            || !l_blnIsSsidSame
            || !l_blnIsCpySame
            || !l_blnIsBrnSame
            || !l_blnIsAccSame
            || !l_blnIsReqSame)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�R�j�@@�n�b�V���l��v�`�F�b�N 
        // �R�|�P�j�n�b�V���l���� 
        //  this.createGFT�d���n�b�V���l()���R�[�����A�n�b�V���l�𐶐�����B 
        //  [�����̐ݒ�] 
        //  GFT���ʒʒm�d�����ׁF�@@����.GFT���ʒʒm�d������ 
        String l_strGFTTelegramHashValue = 
            this.createGFTTelegramHashValue(l_fxGftResultNoticeTelegramUnit);
        
        // �R�|�Q�j�ȉ��ɓ��Ă͂܂�ꍇ�Afalse��ԋp����B 
        //   �R�|�P�j�ɂĐ��������n�b�V���l != ����.GFT���ʒʒm�d������.�n�b�V���l 
        if (!l_fxGftResultNoticeTelegramUnit.hashValue.equals(l_strGFTTelegramHashValue))
        {   
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�S�j�@@true��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (mask�p�X���[�h) <BR>
     * �p�X���[�h�Ƀ}�X�N��������B <BR>
     * <BR>
     * �P�j��̕�����i��A�Ƃ���B�j�𐶐�����B<BR>
     * �Q�j�ȉ��̂Ƃ���ɁA����.�p�X���[�h�̔z���u�������āA<BR>
     *      �P�j�̕�����i��A�j�ɃZ�b�g����B<BR>
     * <BR>
     *   A[0] = ����.�p�X���[�h[1] 
     *   A[1] = ����.�p�X���[�h[8] 
     *   A[2] = ����.�p�X���[�h[4]
     *   A[3] = ����.�p�X���[�h[9] 
     *   A[4] = ����.�p�X���[�h[7] 
     *   A[5] = ����.�p�X���[�h[10] 
     *   A[6] = ����.�p�X���[�h[6] 
     *   A[7] = ����.�p�X���[�h[11] 
     *   A[8] = ����.�p�X���[�h[3] 
     *   A[9] = ����.�p�X���[�h[0] 
     *   A[10] = ����.�p�X���[�h[2] 
     *   A[11] = ����.�p�X���[�h[5] 
     * <BR>
     * �Q�jA�̕������ԋp����B<BR>
     * 
     * @@param l_strMaskPassword  String
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C6C39D0027
     */
    public String maskPassword(String l_strMaskPassword)
       throws WEB3BaseException
    {
       final String STR_METHOD_NAME = "maskPassword(String[] l_strMaskPassword) ";
       log.entering(STR_METHOD_NAME);
        
       if (l_strMaskPassword == null)
       {
           log.exiting(STR_METHOD_NAME);
           return null;
       }

       String l_strPasswordModified = l_strMaskPassword;
       if (l_strMaskPassword.length() < 12)
       {
           for (int i = 12; i > l_strMaskPassword.length(); i --)
           {
               l_strPasswordModified = l_strPasswordModified + " ";
           }
       }
       
       //�P�j��̕�����i��A�Ƃ���B�j�𐶐�����B
       StringBuffer A = new StringBuffer();
        
       //�Q�j�ȉ��̂Ƃ���ɁA����.�p�X���[�h�̔z���u�������āA
       //�P�j�̕�����i��A�j�ɃZ�b�g����B
       //A[0] = ����.�p�X���[�h[1] 
       //A[1] = ����.�p�X���[�h[8] 
       //A[2] = ����.�p�X���[�h[4]
       //A[3] = ����.�p�X���[�h[9] 
       //A[4] = ����.�p�X���[�h[7] 
       //A[5] = ����.�p�X���[�h[10] 
       //A[6] = ����.�p�X���[�h[6] 
       //A[7] = ����.�p�X���[�h[11] 
       //A[8] = ����.�p�X���[�h[3] 
       //A[9] = ����.�p�X���[�h[0] 
       //A[10] = ����.�p�X���[�h[2] 
       //A[11] = ����.�p�X���[�h[5] 
       A.append(l_strPasswordModified.charAt(1));
       A.append(l_strPasswordModified.charAt(8));
       A.append(l_strPasswordModified.charAt(4));
       A.append(l_strPasswordModified.charAt(9));
       A.append(l_strPasswordModified.charAt(7));
       A.append(l_strPasswordModified.charAt(10));
       A.append(l_strPasswordModified.charAt(6));
       A.append(l_strPasswordModified.charAt(11));
       A.append(l_strPasswordModified.charAt(3));
       A.append(l_strPasswordModified.charAt(0));
       A.append(l_strPasswordModified.charAt(2));
       A.append(l_strPasswordModified.charAt(5));
       
       if (l_strMaskPassword.length() > 12)
       {
           A.append(l_strMaskPassword.substring(12, l_strMaskPassword.length()));
       }
       log.exiting(STR_METHOD_NAME);
       return A.toString();
    }
     
    
}@
