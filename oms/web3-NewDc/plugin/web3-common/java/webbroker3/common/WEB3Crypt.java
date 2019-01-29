head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3Crypt.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������̈Í����ƕ������s���N���X(WEB3Crypt.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.common;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import webbroker3.util.WEB3LogUtility;

/**
 * ������̈Í����ƕ������s���B
 * 
 * �o�b�`�ɂ������N���X������܂��B
 * ���N���X�̏C���́A�o�b�`���̃N���X�ɂ����f���Ă��������B
 * �o�b�`���̓��e�ɂ��ẮA�uw3jCrypt�ڍא݌v��.doc�v��
 * �Q�Ƃ��Ă��������B
 *
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public class WEB3Crypt
{
    /**
     * �ϊ��̖��O�B
     */
    private final static String TRANSFORMATION = "DES";

    /**
     * ��������Í������Đ��������o�C�g��𕶎���ɒu�������邽�߂̃e�[�u���B
     */
    private final static char[] CODES = {'!', '#', '$', '&', '(', ')', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>'};

    /**
     * �閧���f�[�^�B
     */
    private final static byte[] DES_KEY_DATA = {(byte)0x2C, (byte)0xAA, (byte)0xF2, (byte)0x55, (byte)0xEF, (byte)0xAF, (byte)0x86, (byte)0x6E};

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3Crypt.class);

    /**
     * �閧���B
     */
    private SecretKeySpec key;

    /**
     * �Í�������ѕ������̋@@�\��񋟂���I�u�W�F�N�g�B
     */
    private Cipher cipher;

    /**
     * �R���X�g���N�^�B
     */
    public WEB3Crypt()
    {
        key = new SecretKeySpec(DES_KEY_DATA, TRANSFORMATION);

        try
        {
            cipher = Cipher.getInstance(TRANSFORMATION);
        }
        catch (Exception l_exp)
        {
            throw new RuntimeSystemException(l_exp.getMessage(), l_exp);
        }
    }

    /**
     * ������̈Í������s���B
     * �������Í�������������̏ꍇ�́A���̕���������̂܂ܕԂ��B
     *
     * @@param l_strPlane �Í���������������
     * @@return �Í�������������
     */
    public String encrypt(String l_strPlane)
    {
        byte[] l_btArray = null;
        byte[] l_btPlane;
        int l_intPlaneLength;
        int l_intCodesLength;
        int i, j;
        String l_strMethodName = getClass().getName() + ".encrypt(String)";

        log.entering(l_strMethodName);

        if (l_strPlane == null)
        {
            return null;
        }

        l_btPlane = l_strPlane.getBytes();
        l_intPlaneLength = l_btPlane.length;
        if (l_intPlaneLength < 1)
        {
            return null;
        }
        if ((l_intPlaneLength % 2) == 0)
        {
            l_intCodesLength = CODES.length;
            j = 0;
            for (i = 0;i < l_intPlaneLength;i++)
            {
                for (j = 0;j < l_intCodesLength;j++)
                {
                    if (l_btPlane[i] == CODES[j])
                    {
                        break;
                    }
                }
                if (j == l_intCodesLength)
                {
                    break;
                }
            }
            if (i == l_intPlaneLength && j < l_intCodesLength)
            {
                return l_strPlane;
            }
        }

        // �Í������s��
        try
        {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            l_btArray = cipher.doFinal(l_strPlane.getBytes());
        }
        catch (Exception l_exp)
        {
            throw new RuntimeSystemException(l_exp.getMessage(), l_exp);
        }

        log.exiting(l_strMethodName);

        return toCodeString(l_btArray);
    }

    /**
     * �Í�������̕��������s���B
     * �������Í�������������łȂ��ꍇ�́A���̕���������̂܂ܕԂ��B
     *
     * @@param l_strEncrypt �Í�������������
     * @@return ����������������
     */
    public String decrypt(String l_strEncrypt)
    {
        byte[] l_btArray = null;
        byte[] l_btEncrypt = null;
        int l_intLength;
        int i, j, k;
        String l_strMethodName = getClass().getName() + ".decrypt(String)";

        log.entering(l_strMethodName);

        if (l_strEncrypt == null)
        {
            return null;
        }

        l_btArray = l_strEncrypt.getBytes();
        l_intLength = l_btArray.length;
        if (l_intLength < 2 || (l_intLength % 2) != 0)
        {
            // �Í�������������ł͂Ȃ��i�Í�������������̒����͕K�������j
            return l_strEncrypt;
        }
        l_btEncrypt = new byte[l_intLength / 2];
        for (i = 0, j = 0;i < l_intLength;)
        {
            for (k = 0;k < CODES.length;k++)
            {
                if (l_btArray[i] == CODES[k])
                {
                    l_btEncrypt[j] = (byte)(k << 4);
                    break;
                }
            }
            if (k == CODES.length)
            {
                // �Í�������������ł͂Ȃ��i�Í��������R�[�h�ȊO���܂܂�Ă���j
                return l_strEncrypt;
            }
            i++;

            for (k = 0;k < CODES.length;k++)
            {
                if (l_btArray[i] == CODES[k])
                {
                    l_btEncrypt[j] += (byte)k;
                    break;
                }
            }
            if (k == CODES.length)
            {
                // �Í�������������ł͂Ȃ��i�Í��������R�[�h�ȊO���܂܂�Ă���j
                return l_strEncrypt;
            }
            i++;
            j++;
        }

        // ���������s��
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            l_btArray = cipher.doFinal(l_btEncrypt);
        }
        catch (Exception l_exp)
        {
            throw new RuntimeSystemException(l_exp.getMessage(), l_exp);
        }

        log.exiting(l_strMethodName);

        return new String(l_btArray);
    }

    /**
     * �Í��������o�C�g�̒l���R�[�h�ɕϊ����A������o�b�t�@@�ɒǉ�����B
     * 
     * @@param l_btValue �Í��������o�C�g
     * @@param l_sbBuf ������o�b�t�@@
     */
    private void byte2code(byte l_btValue, StringBuffer l_sbBuf)
    {
        int l_intHigh = ((l_btValue & 0xf0) >> 4);
        int l_intLow = (l_btValue & 0x0f);
        l_sbBuf.append(CODES[l_intHigh]);
        l_sbBuf.append(CODES[l_intLow]);
    }

    /**
     * �Í��������o�C�g����R�[�h������ɕϊ����ĕԂ��B
     * 
     * @@param l_btArray �Í��������o�C�g��
     * @@return �R�[�h������
     */
    private String toCodeString(byte[] l_btArray)
    {
        if (l_btArray == null)
        {
            return null;
        }

        StringBuffer l_sbBuf = new StringBuffer();

        for (int i = 0;i < l_btArray.length;i++)
        {
            byte2code(l_btArray[i], l_sbBuf);
        }
        return l_sbBuf.toString();
    }
}
@
