head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �`�[�쐬���(WEB3AccOpenVoucherInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
                 : 2006/07/10 ���� (���u) �d�l�ύX ���f��075
                 : 2006/08/14 �ęԍg (���u) �d�l�ύX ���f��087
Revesion History : 2009/08/10 �����F (���u) �d�l�ύX ���f��163
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.util.WEB3LogUtility;


/**
 * �`�[�쐬���
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenVoucherInfo extends Message 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenVoucherInfo.class);   
    
    /**
     * (G11�ڋq�o�^)<BR>
     * �`�[�쐬���@@G11�ڋq�o�^<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] accRegVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1151�_�񏑒���)<BR>
     * �`�[�쐬���@@G1151�_�񏑒���<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] contMrgVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G26�U�֐\��)<BR>
     * �`�[�쐬���@@G26�U�֐\��<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] transVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (GA300�ېU����)<BR>
     * �`�[�쐬���@@GA300�ېU����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] agreeTransVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G5401�L�����)<BR>
     * �`�[�쐬���@@G5401�L�����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] chargedInfoVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (GI601MRF����)<BR>
     * �`�[�쐬���@@GI601MRF����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] mrfAccVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G5511�Ïؔԍ�)<BR>
     * �`�[�쐬���@@G5511�Ïؔԍ�<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] passwordVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1159�d�v����)<BR>
     * �`�[�쐬���@@G1159�d�v����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] impConfirmVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1175�{�l�m�F)<BR>
     * �`�[�쐬���@@G1175�{�l�m�F<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] confirmVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (GI311��c�E�d�q��t�E�������)<BR>
     * �`�[�쐬���@@GI311��c�E�d�q��t�E�������<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] tradeConditionVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G9801������)<BR>
     * �`�[�쐬���@@G9801������<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] insiderVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1220GP����)<BR>
     * �`�[�쐬���@@G1220GP����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] gpVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G8610���O���E����)<BR>
     * �`�[�쐬���@@G8610���O���E����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] listedHolderVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G1190�ڋq��������)<BR>
     * �`�[�쐬���@@G1190�ڋq��������<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] accRealNameVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};
    
    /**
     * (G43�O�ݗa�������o�^)<BR>
     * �`�[�쐬���@@G43�O�ݗa�������o�^<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j<BR>
     * 1�F�@@�쐬��<BR>
     * 2�F�@@���M�ۗ���<BR>
     * 3�F�@@���M��<BR>
     * 4�F�@@��M��<BR>
     * 5�F�@@���M�G���[<BR>
     * 6�F�@@��M�G���[<BR>
     */
    public String[] foreignSaveInfoVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};

    /**
     * (GS103�@@�\�ʒm)<BR>
     * �`�[�쐬���@@GS103�@@�\�ʒm <BR>
     * <BR>
     * 0�F�@@DEFAULT�i�`�[���쐬�j <BR>
     * 1�F�@@�쐬�� <BR>
     * 2�F�@@���M�ۗ��� <BR>
     * 3�F�@@���M�� <BR>
     * 4�F�@@��M�� <BR>
     * 5�F�@@���M�G���[ <BR>
     * 6�F�@@��M�G���[ <BR>
     */
    public String[] agencyVoucherDiv = {WEB3VoucherStatusDef.DEFAULT};

    /**
     * @@roseuid 41B45E7B00FA
     */
    public WEB3AccOpenVoucherInfo() 
    {
     
    }
    
    /**
     * �����̃I�u�W�F�N�g���{�I�u�W�F�N�g�Ɠ��ꂩ�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�����̓`�[�쐬����`�[�쐬���^��cast����B<BR>
     * �Q�j�@@�`�[�쐬���̊e�v���p�e�B�Ɩ{�I�u�W�F�N�g�̊e�v���p�e�B���r���A<BR>
     * �@@���ׂē����l���Z�b�g����Ă����true�A<BR>
     * �@@�l�̈Ⴄ�v���p�e�B���P�ł����݂���ꍇ��false��ԋp����B<BR>
     * @@param l_accOpenVoucherInfo - �`�[�쐬���
     * @@return Boolean
     * @@roseuid 4191A4CA0071
     */
    public boolean equals(Object l_accOpenVoucherInfo) 
    {
        final String STR_METHOD_NAME = " equals(Object)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����̓`�[�쐬����`�[�쐬���^��cast����B
        WEB3AccOpenVoucherInfo l_openVoucherInfo = new WEB3AccOpenVoucherInfo();
        
        if (l_accOpenVoucherInfo instanceof WEB3AccOpenVoucherInfo)
        {
            l_openVoucherInfo = (WEB3AccOpenVoucherInfo)l_accOpenVoucherInfo;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        //�Q�j�`�[�쐬���̊e�v���p�e�B�Ɩ{�I�u�W�F�N�g�̊e�v���p�e�B���r���A
        //���ׂē����l���Z�b�g����Ă����true�A
        //�l�̈Ⴄ�v���p�e�B���P�ł����݂���ꍇ��false��ԋp����B
        
        //�`�[�쐬���@@G11�ڋq�o�^        
        boolean l_blnIsVoucherDiv = arrayEquals(l_openVoucherInfo.accRegVoucherDiv, this.accRegVoucherDiv);
        
        //�`�[�쐬���@@G1151�_�񏑒���
        boolean l_blnIsContMrgVoucherDiv = arrayEquals(l_openVoucherInfo.contMrgVoucherDiv, this.contMrgVoucherDiv);
        
        //�`�[�쐬���@@G26�U�֐\��
        boolean l_blnIsTransVoucherDiv = arrayEquals(l_openVoucherInfo.transVoucherDiv, this.transVoucherDiv);
        
        //�`�[�쐬���@@GA300�ېU����
        boolean l_blnIsAgreeTransVoucherDiv = arrayEquals(l_openVoucherInfo.agreeTransVoucherDiv, this.agreeTransVoucherDiv);
        
        //�`�[�쐬���@@G5401�L�����
        boolean l_blnIsChargedInfoVoucherDiv = arrayEquals(l_openVoucherInfo.chargedInfoVoucherDiv, this.chargedInfoVoucherDiv);
        
        //�`�[�쐬���@@GI601MRF����
        boolean l_blnIsMrfAccVoucherDiv = arrayEquals(l_openVoucherInfo.mrfAccVoucherDiv, this.mrfAccVoucherDiv);
        
        //�`�[�쐬���@@G5511�Ïؔԍ�
        boolean l_blnIsPasswordVoucherDiv = arrayEquals(l_openVoucherInfo.passwordVoucherDiv, this.passwordVoucherDiv);
        
        //�`�[�쐬���@@G1159�d�v����
        boolean l_blnIsImpConfirmVoucherDiv = arrayEquals(l_openVoucherInfo.impConfirmVoucherDiv, this.impConfirmVoucherDiv);
        
        //�`�[�쐬���@@G1175�{�l�m�F
        boolean l_blnIsConfirmVoucherDiv = arrayEquals(l_openVoucherInfo.confirmVoucherDiv, this.confirmVoucherDiv);
        
        //�`�[�쐬���@@GI311��c�E�d�q��t�E�������
        boolean l_blnIsTradeConditionVoucherDiv = arrayEquals(l_openVoucherInfo.tradeConditionVoucherDiv, this.tradeConditionVoucherDiv);
        
        //�`�[�쐬���@@G9801������
        boolean l_blnIsInsiderVoucherDiv = arrayEquals(l_openVoucherInfo.insiderVoucherDiv, this.insiderVoucherDiv);
        
        //�`�[�쐬���@@G1220GP����
        boolean l_blnIsGpVoucherDiv = arrayEquals(l_openVoucherInfo.gpVoucherDiv, this.gpVoucherDiv);
        
        //�`�[�쐬���@@G8610���O���E����
        boolean l_blnIsListedHolderVoucherDiv = arrayEquals(l_openVoucherInfo.listedHolderVoucherDiv, this.listedHolderVoucherDiv);
        
        //�`�[�쐬���@@G1190�ڋq��������
        boolean l_blnIsAccRealNameVoucherDiv = arrayEquals(l_openVoucherInfo.accRealNameVoucherDiv, this.accRealNameVoucherDiv);
        
        //�`�[�쐬���@@G43�O�ݗa�������o�^
        boolean l_blnIsForeignSaveInfoVoucherDiv = arrayEquals(l_openVoucherInfo.foreignSaveInfoVoucherDiv, this.foreignSaveInfoVoucherDiv);

        // �`�[�쐬���@@GS103�@@�\�ʒm
        boolean l_blnIsAgencyVoucherDiv = arrayEquals(l_openVoucherInfo.agencyVoucherDiv, this.agencyVoucherDiv);
 
        if (l_blnIsVoucherDiv && l_blnIsContMrgVoucherDiv && 
            l_blnIsTransVoucherDiv && l_blnIsAgreeTransVoucherDiv && 
            l_blnIsChargedInfoVoucherDiv && l_blnIsMrfAccVoucherDiv && 
            l_blnIsPasswordVoucherDiv && l_blnIsImpConfirmVoucherDiv && 
            l_blnIsConfirmVoucherDiv && l_blnIsTradeConditionVoucherDiv&&
            l_blnIsInsiderVoucherDiv && l_blnIsGpVoucherDiv &&
            l_blnIsListedHolderVoucherDiv && l_blnIsAccRealNameVoucherDiv &&
            l_blnIsForeignSaveInfoVoucherDiv && l_blnIsAgencyVoucherDiv)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * ��̔z����r���āA�������ꍇ�ɁAtrue�ɕԋp���܂��B�������Ȃ��ꍇ�ɁAfalse�ɕԋp���܂��B
     * ��̔z��͑S��null�ɂȂ�ꍇ�ɁAtrue�ɕԋp���܂��B
     * ��̔z���null�ŁA���̑��̔z���null�łȂ��ꍇ�ɁAfalse�ɕԋp���܂�
     * @@param l_strArgsOne �z��I�u�W�F�N�g
     * @@param l_strArgsTwo �z��I�u�W�F�N�g
     * @@return Boolean
     */
    private boolean arrayEquals(String[] l_strArgsOne, String[] l_strArgsTwo)
    {
        if (l_strArgsOne == null && l_strArgsTwo == null)
        {
            return true;
        }
        else if (l_strArgsOne == null || l_strArgsTwo == null)
        {
            return false;
        }
        else if(l_strArgsOne.length == 0 && l_strArgsTwo.length == 0)
        {
            return true;
        }
        
        if (l_strArgsOne.length != l_strArgsTwo.length)
        {
            return false;
        }
        
        int l_intArgLength = l_strArgsOne.length;
        
        int l_intKeyOne = this.nullSum(l_strArgsOne);
        int l_intKeyTwo = this.nullSum(l_strArgsTwo);
        
        if (l_intKeyOne != l_intKeyTwo)
        {
            return false;
        }
        
        this.sequence(l_strArgsOne, l_intKeyOne);
        this.sequence(l_strArgsTwo, l_intKeyTwo);
        
        if (l_intKeyOne == 0)
        {
        
            for (int i = 0; i < l_intArgLength; i++)
            {
    
                if (!l_strArgsOne[i].equals(l_strArgsTwo[i]))
                {
                    return false;
                }
    
            }
        }
        else
        {
            for (int i = l_intKeyOne; i < l_intArgLength; i++)
            {
    
                if (!l_strArgsOne[i].equals(l_strArgsTwo[i]))
                {
                    return false;
                }
    
            }
        }
        return true;
    }
    
    /**
     * �z����������ւ̏����ŕ��ёւ��܂��B
     * @@param l_strArgs �z��I�u�W�F�N�g
     * @@return
     */
    private void sequence(String[] l_strArgs, int l_intSum)
    {
        int l_intArgLength = l_strArgs.length;
        String l_strKey = null;
        String[] l_strNews = new String[l_intArgLength];
        String[] l_strNewTwos = null;
        
        l_strNewTwos = new String[l_intArgLength - l_intSum];
        
        int l_intLength = l_strNewTwos.length;
        for (int j = 0; j < l_intLength;)
        {
            for (int i = 0; i < l_intArgLength; i++)
            {
                if (l_strArgs[i] != null)
                {
                    l_strNewTwos[j] = l_strArgs[i];
                    if (j < l_strNewTwos.length)
                    {
                        j = j + 1;
                    }
                }
            }
        }        
        
        int l_intKeySave = 0;
        l_intKeySave = l_intSum;
        
        for (int j = 0; j < l_intLength; j++)
        {
            l_strNews[l_intSum] = l_strNewTwos[j];
            l_intSum = l_intSum + 1;               
        }      
        
        if (l_intSum == 0)
        {
            for (int i = 0; i < l_intArgLength; i++)
            {
                for (int j = i + 1; j < l_intArgLength; j++)
                {                   
                    if (l_strArgs[i].compareTo(l_strArgs[j]) > 0)
                    {
                        l_strKey = l_strArgs[i];
                        l_strArgs[i] = l_strArgs[j];
                        l_strArgs[j] = l_strKey;   
                    }

                }
            }
        }
        else
        {
            for (int i = l_intKeySave; i < l_intArgLength; i++)
            {
                for (int j = i + 1; j < l_intArgLength; j++)
                {                
                    if (l_strNews[i].compareTo(l_strNews[j]) > 0)
                    {
                        l_strKey = l_strNews[i];
                        l_strNews[i] = l_strNews[j];
                        l_strNews[j] = l_strKey;
                    }
                }
            }
            
            for (int i= 0; i < l_strNews.length; i++)
            {
                l_strArgs[i] = l_strNews[i];
            }
        }     
    }
    
    /**
     * �z�񒆂�null�̐����v�Z���܂��B
     * @@param l_strArgs �z��I�u�W�F�N�g
     * @@return
     */
    private int nullSum(String[] l_strArgs)
    {
        int l_intArgLength = l_strArgs.length;
        int l_intKey = 0;
        for (int i = 0; i < l_intArgLength; i++)
        {
            if (l_strArgs[i] == null)
            {
                l_intKey = l_intKey + 1;  
            }
        }
        
        return l_intKey;
    }
}@
