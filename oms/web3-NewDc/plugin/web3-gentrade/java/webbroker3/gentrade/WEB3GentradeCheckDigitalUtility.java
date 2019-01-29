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
filename	WEB3GentradeCheckDigitalUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �`�F�b�N�f�W�b�g���[�e�B���e�B(WEB3GentradeCheckDigitalUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/22 �h�C (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�`�F�b�N�f�W�b�g���[�e�B���e�B)<BR>
 * �`�F�b�N�f�W�b�g���[�e�B���e�B<BR>
 * @@author �h�C (���u)
 * @@version 1.0
 */
public class WEB3GentradeCheckDigitalUtility
{
    /** 
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCheckDigitalUtility.class);
    
    private final static int LENGTH  = 6; //�����R�[�h6��
    private final static int START  = 7;
    private final static int DIV  = 11;
    private final static int REST  = 10;
    
    /**
     * (get�`�F�b�N�f�W�b�g )<BR>
     * <BR>
     * �`�F�b�N�f�W�b�g��t������B <BR>
     * <BR>
     * �P�j ����.�����R�[�h6�����ȉ��̂悤�ɒ�`����B <BR>
     * <BR>
     * �@@�`�a�b�c�d�e <BR>
     * <BR>
     * �Q�j �ȉ��̉��Z�������s���A���ʇ@@�����߂�B <BR>
     * �@@ <BR>
     * �@@�i�` * 7�j + �iB * 6�j + �iC * 5�j + �iD * 4�j + �iE * 3�j + �iF * 2�j = �@@ <BR>
     * <BR>
     * �R�j �Q�j�̏������ʇ@@�ɑ΂��āA�ȉ��̏������s���A���ʇA�����߂�B <BR>
     * <BR>
     * �@@�@@ �� 11 �Ŋ������]�� = �A <BR>
     * <BR>
     * �S�j �R�j�̏������ʇA�̒l�ɂ��A�ȉ��̃`�F�b�N�f�W�b�g��ݒ肷��B <BR>
     * �@@ <BR>
     * �@@�A�̒l�@@�@@���@@�@@�`�F�b�N�f�W�b�g <BR>
     * �@@�@@  <BR>
     *          0        ���@@�@@�@@�@@�@@�@@�@@1 <BR>
     *     �@@ 1        ���@@�@@�@@�@@�@@�@@�@@0 <BR>
     *     �@@ 2        ���@@�@@�@@�@@�@@�@@�@@9 <BR>
     *    �@@  3        ���@@�@@�@@�@@�@@�@@�@@8 <BR>
     *   �@@   4        ���@@�@@�@@�@@�@@�@@�@@7 <BR>
     *   �@@   5        ���@@�@@�@@�@@�@@�@@�@@6 <BR>
     *   �@@   6        ���@@�@@�@@�@@�@@�@@�@@5 <BR>
     *   �@@   7        ���@@�@@�@@�@@�@@�@@�@@4 <BR>
     *   �@@   8        ���@@�@@�@@�@@�@@�@@�@@3 <BR>
     *   �@@   9        ���@@�@@�@@�@@�@@�@@�@@2 <BR>
     *   �@@   10       ���@@�@@�@@�@@�@@�@@1 <BR>
     *       <BR>
     * �T�j �S�j�Őݒ肵���l���A�`�F�b�N�f�W�b�g�Ƃ��Ė߂�l�ŕԂ��B<BR>
     * <BR>
     * @@param l_strAccountCode  �����R�[�h<BR>
     * @@throws WEB3BaseException<BR>
     * @@return String 
     */
    public static String getCheckDigital(String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCheckDigital(String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j ����.�����R�[�h6�����ȉ��̂悤�ɒ�`����B
        //�@@�`�a�b�c�d�e
        if(l_strAccountCode.length() != LENGTH)
        {
            log.error("�i�����R�[�h�̃T�C�Y���s���ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeCheckDigitalUtility.class.getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�̃T�C�Y���s���ł��B");
        }
        if(!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            log.error("�i�����R�[�h�����l�ȊO�̒l�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeCheckDigitalUtility.class.getName() + "." + STR_METHOD_NAME,
                "�����R�[�h�����l�ȊO�̒l�ł��B");
        }
        
        //�Q�j �ȉ��̉��Z�������s���A���ʇ@@�����߂�B
        //�@@�i�` * 7�j + �iB * 6�j + �iC * 5�j + �iD * 4�j + �iE * 3�j + �iF * 2�j = �@@
        int l_intResult = 0;
        for(int i = 0, j = START; i < LENGTH; i++, j--)
        {
            l_intResult += 
                (Integer.valueOf(l_strAccountCode.substring(i, i + 1))).intValue() * j;
        }
        
        //�R�j �Q�j�̏������ʇ@@�ɑ΂��āA�ȉ��̏������s���A���ʇA�����߂�B
        //�@@�@@ �� 11 �Ŋ������]�� = �A
        l_intResult %= DIV;
        
        //�S�j �R�j�̏������ʇA�̒l�ɂ��A�ȉ��̃`�F�b�N�f�W�b�g��ݒ肷��B
        //�@@�A�̒l�@@�@@���@@�@@�`�F�b�N�f�W�b�g
        l_intResult = (DIV - l_intResult) % REST;
        
        //�T�j �S�j�Őݒ肵���l���A�`�F�b�N�f�W�b�g�Ƃ��Ė߂�l�ŕԂ��B
        String l_strResult = String.valueOf(l_intResult);
        log.exiting(STR_METHOD_NAME);
        return l_strResult;
    }
}
@
