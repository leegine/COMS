head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StringTypeUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������̕�����𔻒f����@@�\���������郆�[�e�B���e�B(WEB3StringTypeUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ����� (���u) �V�K�쐬
Revesion History : 2004/08/12 �� ��(���u) formatNumber(long, int)��ǉ�
Revesion History : 2004/08/13 �� ��(���u) formatNumber(double)��ǉ�
Revesion History : 2004/08/13 �� ��(���u) formatNumber(double)���C��
Revesion History : 2004/08/25 �� ��(���u) isInteger(String)��ǉ�
Revesion History : 2004/08/25 �� ��(���u) getNubmerLength(String)��ǉ�
Revesion History : 2004/08/25 �� ��(���u) getIntegerDigits(String)��ǉ�
Revesion History : 2004/08/25 �� ��(���u) getFractionDigits(String)��ǉ�
Revesion History : 2004/08/31 �� ��(���u) formatNumber(double)���C��
Revesion History : 2004/09/07 �� ��(���u) formatNumber(double)�̒�`��synchronized��ǉ�
Revesion History : 2004/09/23 � ��(���u) isMailAddress(String)��ǉ�
Revesion History : 2004/10/09 � ��(���u) isMailAddress(String)���C��
Revesion History : 2005/07/15 �� ��(���u) isMinus(String)��ǉ�
Revesion History : 2006/07/21 ������(���u) �d�l�ύX�E���f��No.201��Ή�
Revesion History : 2007/01/23 �� �i(���u) toJapDate(Date)��ǉ�
Revesion History : 2007/07/18 ����(SCS)   �d�l�ύX�E�����̖��No.017��Ή�
Revesion History : 2008/08/15 ��іQ(���u) �d�l�ύX�E���f��No.331��Ή�
Revesion History : 2008/09/23 ��іQ(���u) �d�l�ύX�E���f��No.334��Ή�
Revesion History : 2009/02/04 ��іQ(���u) �d�l�ύX�E���f��No.336�A337��Ή�
*/

package webbroker3.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;


/**
 * ������̕�����𔻒f����@@�\���������郆�[�e�B���e�B�E�N���X�B<BR>
 * <BR>
 * @@author �e�n(SRA)
 * @@version 1.0
 */
public class WEB3StringTypeUtility 
{

    //private static final char asciiStart           = '\u0020'; //unicode��ASCII�����̊J�n�l
    //private static final char asciiEnd             = '\u007E'; //unicode��ASCII�����̏I���l
    private static final char numberStart          = '\u0030'; //unicode�̔��p�����̊J�n�l(0)
    private static final char numberEnd            = '\u0039'; //unicode�̔��p�����̏I���l(9)
    private static final char zenNumberStart          = '\uff10'; //unicode�̑S�p�����̊J�n�l(�O)
    private static final char zenNumberEnd            = '\uff19'; //unicode�̑S�p�����̏I���l(�X)
    
    private static final char lowerEnglishStart    = '\u0061'; //unicode�̔��p�p���������̊J�n�l(a)
    private static final char lowerEnglishEnd      = '\u007A'; //unicode�̔��p�p���������̏I���l(z)
    private static final char upperEnglishStart    = '\u0041'; //unicode�̔��p�p���啶���̊J�n�l(A)
    private static final char upperEnglishEnd      = '\u005A'; //unicode�̔��p�p���啶���̏I���l(Z)
    private static final char zenLowerEnglishStart    = '\uff41'; //unicode�̑S�p�p���������̊J�n�l(��)
    private static final char zenLowerEnglishEnd    = '\uff5A'; //unicode�̑S�p�p���������̏I���l(��)
    private static final char zenUpperEnglishStart    = '\uff21'; //unicode�̑S�p�p���啶���̊J�n�l(�`)
    private static final char zenUpperEnglishEnd    = '\uff3A'; //unicode�̑S�p�p���啶���̏I���l(�y)
    
    private static final char zenkakukatakanaStart = '\u30A1'; //unicode�̑S�p�J�^�J�i�̊J�n�l
    private static final char zenkakukatakanaEnd   = '\u30FA'; //unicode�̑S�p�J�^�J�i�̏I���l()
    private static final char hankakuKatakanaStart = '\uFF66'; //unicode�̔��p�J�^�J�i�̊J�n�l()
    private static final char hankakuKatakanaEnd   = '\uFF9F'; //unicode�̔��p�J�^�J�i�̏I���l()

    

    private static final String DEFAULT_DOUBLE_FORMAT_PATTERN = "#";
    
    private static DecimalFormat decimalFormat = new DecimalFormat();
    

    private static char[][] single = {
        
        //�J�i   ��`�
        {0xff71} ,// �
        {0xff72} ,// �
        {0xff73} ,// �
        {0xff74} ,// �
        {0xff75} ,// �
        {0xff76} ,// �
        {0xff77} ,// �
        {0xff78} ,// �
        {0xff79} ,// �
        {0xff7a} ,// �
        {0xff7b} ,// �
        {0xff7c} ,// �
        {0xff7d} ,// �
        {0xff7e} ,// �
        {0xff7f} ,// �
        {0xff80} ,// �
        {0xff81} ,// �
        {0xff82} ,// �
        {0xff83} ,// �
        {0xff84} ,// �
        {0xff85} ,// �
        {0xff86} ,// �
        {0xff87} ,// �
        {0xff88} ,// �
        {0xff89} ,// �
        {0xff8a} ,// �
        {0xff8b} ,// �
        {0xff8c} ,// �
        {0xff8d} ,// �
        {0xff8e} ,// �
        {0xff8f} ,// �
        {0xff90} ,// �
        {0xff91} ,// �
        {0xff92} ,// �
        {0xff93} ,// �
        {0xff94} ,// �
        {0xff95} ,// �
        {0xff96} ,// �
        {0xff97} ,// �
        {0xff98} ,// �
        {0xff99} ,// �
        {0xff9a} ,// �
        {0xff9b} ,// �
        {0xff9c} ,// �
        {0xff66} ,// �
        {0xff9d} ,// �
        
        //�J�i   �ށ`��
        {0xff76,0xff9e} ,// ��
        {0xff77,0xff9e} ,// ��
        {0xff78,0xff9e} ,// ��
        {0xff79,0xff9e} ,// ��
        {0xff7a,0xff9e} ,// ��
        
        //�J�i   �ށ`��
        {0xff7b,0xff9e} ,// ��
        {0xff7c,0xff9e} ,// ��
        {0xff7d,0xff9e} ,// ��
        {0xff7e,0xff9e} ,// ��
        {0xff7f,0xff9e} ,// ��
        
        //�J�i   �ށ`��
        {0xff80,0xff9e} ,// ��
        {0xff81,0xff9e} ,// ��
        {0xff82,0xff9e} ,// ��
        {0xff83,0xff9e} ,// ��
        {0xff84,0xff9e} ,// ��
        
        //�J�i   �ށ`��
        {0xff8a,0xff9e} ,// ��
        {0xff8b,0xff9e} ,// ��
        {0xff8c,0xff9e} ,// ��
        {0xff8d,0xff9e} ,// ��
        {0xff8e,0xff9e} ,// ��
        
        //�J�i   ��
        {0xff73,0xff9e} ,// ��
        
        //�J�i   �߁`��
        {0xff8a,0xff9f} ,// ��
        {0xff8b,0xff9f} ,// ��
        {0xff8c,0xff9f} ,// ��
        {0xff8d,0xff9f} ,// ��
        {0xff8e,0xff9f} ,// ��
        
//        //�J�i   ��`�
//        {0xff67} ,// �
//        {0xff68} ,// �
//        {0xff69} ,// �
//        {0xff6a} ,// �
//        {0xff6b} ,// �

//        {0xff6c} ,// �
//        {0xff6d} ,// �
//        {0xff6e} ,// �
//        {0xff6f} ,// �
        
        //�J�i   ��`�
        {0xff71} ,// �
        {0xff72} ,// �
        {0xff73} ,// �
        {0xff74} ,// �
        {0xff75} ,// �
        
        //�J�i   ��A��A�
        {0xff76} ,// �  
        {0xff79} ,// �  
        {0xff82} ,// �  
        
        //�J�i   ԁAՁA�
        {0xff94} ,// �
        {0xff95} ,// �
        {0xff96} ,// �
        
        //�J�i   ܁A-
        {0xff9c} ,// �
        {0x002d} ,// -
        
        //�p��  A�`Z
        {0x0041} ,// A
        {0x0042} ,// B
        {0x0043} ,// C
        {0x0044} ,// D
        {0x0045} ,// E
        {0x0046} ,// F
        {0x0047} ,// G
        {0x0048} ,// H
        {0x0049} ,// I
        {0x004a} ,// J
        {0x004b} ,// K
        {0x004c} ,// L
        {0x004d} ,// M
        {0x004e} ,// N
        {0x004f} ,// O
        {0x0050} ,// P
        {0x0051} ,// Q
        {0x0052} ,// R
        {0x0053} ,// S
        {0x0054} ,// T
        {0x0055} ,// U
        {0x0056} ,// V
        {0x0057} ,// W
        {0x0058} ,// X
        {0x0059} ,// Y
        {0x005a} ,// Z
        
        //�p��  a�`z
        {0x0061} ,// a
        {0x0062} ,// b
        {0x0063} ,// c
        {0x0064} ,// d
        {0x0065} ,// e
        {0x0066} ,// f
        {0x0067} ,// g
        {0x0068} ,// h
        {0x0069} ,// i
        {0x006a} ,// j
        {0x006b} ,// k
        {0x006c} ,// l
        {0x006d} ,// m
        {0x006e} ,// n
        {0x006f} ,// o
        {0x0070} ,// p
        {0x0071} ,// q
        {0x0072} ,// r
        {0x0073} ,// s
        {0x0074} ,// t 
        {0x0075} ,// u
        {0x0076} ,// v
        {0x0077} ,// w
        {0x0078} ,// x
        {0x0079} ,// y
        {0x007a} ,// z
        
        //����  0�`9
        {0x0030} ,// 0
        {0x0031} ,// 1
        {0x0032} ,// 2
        {0x0033} ,// 3
        {0x0034} ,// 4
        {0x0035} ,// 5
        {0x0036} ,// 6
        {0x0037} ,// 7
        {0x0038} ,// 8
        {0x0039} ,// 9
        
        //�L��  -�A(�A)  
        {0x002d} ,// -  
        {0x0028} ,// (
        {0x0029} ,// )
        {0x002e} ,// .
        
        //�X�y�[�X " "
        {0x0020} //" "    

        };

    private static char[] multi={   
        
        //�J�i   �A�`��
        0x30a2 ,// �A
        0x30a4 ,// �C
        0x30a6 ,// �E
        0x30a8 ,// �G
        0x30aa ,// �I
        0x30ab ,// �J
        0x30ad ,// �L
        0x30af ,// �N
        0x30b1 ,// �P
        0x30b3 ,// �R
        0x30b5 ,// �T
        0x30b7 ,// �V
        0x30b9 ,// �X
        0x30bb ,// �Z
        0x30bd ,// �\
        0x30bf ,// �^
        0x30c1 ,// �`
        0x30c4 ,// �c
        0x30c6 ,// �e
        0x30c8 ,// �g
        0x30ca ,// �i
        0x30cb ,// �j
        0x30cc ,// �k
        0x30cd ,// �l
        0x30ce ,// �m
        0x30cf ,// �n
        0x30d2 ,// �q
        0x30d5 ,// �t
        0x30d8 ,// �w
        0x30db ,// �z
        0x30de ,// �}
        0x30df ,// �~
        0x30e0 ,// ��
        0x30e1 ,// ��
        0x30e2 ,// ��
        0x30e4 ,// ��
        0x30e6 ,// ��
        0x30e8 ,// ��
        0x30e9 ,// ��
        0x30ea ,// ��
        0x30eb ,// ��
        0x30ec ,// ��
        0x30ed ,// ��
        0x30ef ,// ��
        0x30f2 ,// ��
        0x30f3 ,// ��
        
        //�J�i   �K�`�S
        0x30ac ,// �K
        0x30ae ,// �M
        0x30b0 ,// �O
        0x30b2 ,// �Q
        0x30b4 ,// �S
        
        //�J�i   �U�`�]
        0x30b6 ,// �U
        0x30b8 ,// �W
        0x30ba ,// �Y
        0x30bc ,// �[
        0x30be ,// �]
        
        //�J�i   �_�`�h
        0x30c0 ,// �_
        0x30c2 ,// �a
        0x30c5 ,// �d
        0x30c7 ,// �f
        0x30c9 ,// �h
        
        //�J�i   �o�`�{
        0x30d0 ,// �o
        0x30d3 ,// �r
        0x30d6 ,// �u
        0x30d9 ,// �x
        0x30dc ,// �{
        
        //�J�i   ��
        0x30f4 ,//��
        
        //�J�i   �p�`�|
        0x30d1 ,// �p
        0x30d4 ,// �s
        0x30d7 ,// �v
        0x30da ,// �y
        0x30dd,  // �|
        
        //�J�i   �@@�`�H
        0x30a1 ,// �@@
        0x30a3 ,// �B
        0x30a5 ,// �D
        0x30a7 ,// �F
        0x30a9 ,// �H
        
        //�J�i   ���A���A�b
        0x30f5 ,// ��
        0x30f6 ,// ��
        0x30c3 ,// �b
        
        //�J�i   ���A���A��
        0x30e3 ,// ��
        0x30e5 ,// ��
        0x30e7 ,// ��
        
        //�J�i   ���A�[
        0x30ee ,// ��
        0x30fc ,// �[
        
        //�p��  �`�`�y
        0xff21 ,// �`
        0xff22 ,// �a
        0xff23 ,// �b
        0xff24 ,// �c
        0xff25 ,// �d
        0xff26 ,// �e
        0xff27 ,// �f
        0xff28 ,// �g
        0xff29 ,// �h
        0xff2a ,// �i
        0xff2b ,// �j
        0xff2c ,// �k
        0xff2d ,// �l
        0xff2e ,// �m
        0xff2f ,// �n
        0xff30 ,// �o
        0xff31 ,// �p
        0xff32 ,// �q
        0xff33 ,// �r
        0xff34 ,// �s
        0xff35 ,// �t
        0xff36 ,// �u
        0xff37 ,// �v
        0xff38 ,// �w
        0xff39 ,// �x
        0xff3a ,// �y
        
        //�p��  ���`��
        0xff41 ,// ��
        0xff42 ,// ��
        0xff43 ,// ��
        0xff44 ,// ��
        0xff45 ,// ��
        0xff46 ,// ��
        0xff47 ,// ��
        0xff48 ,// ��
        0xff49 ,// ��
        0xff4a ,// ��
        0xff4b ,// ��
        0xff4c ,// ��
        0xff4d ,// ��
        0xff4e ,// ��
        0xff4f ,// ��
        0xff50 ,// ��
        0xff51 ,// ��
        0xff52 ,// ��
        0xff53 ,// ��
        0xff54 ,// ��
        0xff55 ,// ��
        0xff56 ,// ��
        0xff57 ,// ��
        0xff58 ,// ��
        0xff59 ,// ��
        0xff5a ,// ��
        
        //����  �O�`�X
        0xff10 ,// �O
        0xff11 ,// �P
        0xff12 ,// �Q
        0xff13 ,// �R
        0xff14 ,// �S
        0xff15 ,// �T
        0xff16 ,// �U
        0xff17 ,// �V
        0xff18 ,// �W
        0xff19 ,// �X
        
        //�L��  �|�A�i�A�j     
        0xff0d ,//�|
        0xff08 ,// �i
        0xff09 ,// �j
        0xff0e ,// �D
        
        //�X�y�[�X "�@@"
        0x3000 //"�@@"  
    };
    
    private static char[] upperFullKana = { 
        0x30a2, // �A
        0x30a4, // �C
        0x30a6, // �E
        0x30a8, // �G
        0x30aa, // �I
        0x30e4, // ��
        0x30e6, // ��
        0x30e8, // ��
        0x30c4 // �c
    };

    private static char[] lowerFullKana = { 
        0x30a1, // �@@
        0x30a3, // �B
        0x30a5, // �D
        0x30a7, // �F
        0x30a9, // �H
        0x30e3, // ��
        0x30e5, // ��
        0x30e7, // ��
        0x30c3 // �b
    };
    
    private static char[] upperHalfKana = { 
        0xff71 ,// �
        0xff72 ,// �
        0xff73 ,// �
        0xff74 ,// �
        0xff75 ,// �
        0xff94 ,// �
        0xff95 ,// �
        0xff96 ,// �
        0xff82  // �
    };
    private static char[] lowerHalfKana = { 
        0xff67 ,// �
        0xff68 ,// �
        0xff69 ,// �
        0xff6a ,// �
        0xff6b ,// �
        0xff6c ,// �
        0xff6d ,// �
        0xff6e ,// �
        0xff6f  // �
    };
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3StringTypeUtility.class);

    
    /**
     * �����񂪐��l�ł��邩���`�F�b�N����B<BR>
     * ���l�̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return ���l�̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B
     * @@roseuid 4088B65B0000
     */
    public static boolean isNumber(String l_str) 
    {   
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        try
        {
            Double.parseDouble(l_str);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
 
        return true;
    }

    /**
     * �����񂪐����ł��邩���`�F�b�N����B<BR>
     * �����̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return ����s�̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B
     * @@roseuid 4088B65B0000
     */
    public static boolean isInteger(String l_str) 
    {   
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        try
        {
            double l_dbl = Double.parseDouble(l_str);
            long l_lng = (long) (l_dbl);
            
            if (l_dbl == l_lng)
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        } 
        
    }
    
    /**
     * ���l�̒������擾���܂��B
     *
     * @@param str ��̕�����
     * @@return ���͂��ꂽstr�̐��l������Ԃ��B
     */
    public static int getNubmerLength(String l_str) 
    {
        if (!isNumber(l_str)) 
        {
            return -1;
        }
        
        l_str = formatNumber(Double.parseDouble(l_str));
            
        return l_str.length();
    }
    
    /**
     * �����̒������擾���܂��B
     *
     * @@param str ��̕�����
     * @@return ���͂��ꂽstr�̐���������Ԃ��B
     */
    public static int getIntegerDigits(String l_str) 
    {
        if (!isNumber(l_str)) 
        {
            return -1;
        }
        
        double l_dbl = Double.parseDouble(l_str);
        long l_lng = (long) (Math.abs(l_dbl));
        
        l_str = formatNumber(l_lng);
            
        return l_str.length();
    }
    
    /**
     * �������̒������擾���܂��B
     *
     * @@param str ��̕�����
     * @@return ���͂��ꂽstr�̏�����������Ԃ��B
     */
    public static int getFractionDigits(String l_str) 
    {
        if (!isNumber(l_str)) 
        {
            return -1;
        }
        
        double l_dbl = Double.parseDouble(l_str);        
        l_str = formatNumber(l_dbl);
        
        int l_intIndex = l_str.lastIndexOf(".");
        
        if (l_intIndex == -1) 
        {
            return 0;
        }
        
        String l_strFraction = l_str.substring(l_intIndex + 1);
            
        return l_strFraction.length();
    }
        
    /**
     * �����񂪐����݂̂ō\������Ă��邩���`�F�b�N����B<BR>
     * �����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return �����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B
     * @@roseuid 4088B65B0000
     */
    public static  boolean isDigit(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleNum(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * �����񂪉p���݂̂ō\������Ă��邩���`�F�b�N����B<BR>
     * �p���݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return �p���݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B
     * @@roseuid 4088B6610252
     */
    public static  boolean isLetter(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleEng(l_ch)) 
            {
                return false;
            }
        }
        return true;
    
    }
    
    /**
     * �����񂪉p���܂��͐����ō\������Ă��邩���`�F�b�N����B<BR>
     * �p���܂��͐����̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * �������̂݁A�p���̂݁A���݂�true<BR>
     * @@param l_str ��̕�����
     * @@return boolean
     * @@roseuid 4088B65F01C5
     */
    public static  boolean isLetterOrDigit(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleEng(l_ch) && !isSingleNum(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * �����񂪉p���Ɛ����ō\������Ă��邩���`�F�b�N����B<BR>
     * �p���Ɛ����̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * ���p�������݂̂�true�A����݂̂�false<BR>
     * @@param l_str ��̕�����
     * @@return boolean
     * @@roseuid 4088B676031D
     */
    public static  boolean isLetterAndDigit(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        boolean l_blnHaveLetter = false;
        boolean l_blnHaveDigit = false;
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (isSingleEng(l_ch)) 
            {
                l_blnHaveLetter = true;
            } 
            else if (isSingleNum(l_ch)) 
            {
                l_blnHaveDigit = true;
            }
            else
            {
                return false;
            }
        }
        
        return (l_blnHaveLetter && l_blnHaveDigit);
    }
    
    /**
     * ������̃o�C�g�������擾���܂��B
     *
     * @@param str ��̕�����
     * @@return ���͂��ꂽstr�̃o�C�g������Ԃ��B
     */
    public static int getByteLength(String str) 
    {
        try 
        {
            byte[] bytes = str.getBytes();
            return bytes.length;
        } 
        catch (Exception e) 
        {
            return -1;
        }
    }
    
    /**
     * �����񂪓��t�^����Ă��邩���`�F�b�N����B
     *
     * @@param l_str ������
     * @@param l_strPattern �t�H�[�}�b�g
     * @@return boolean
     */
    public static boolean isDateStr(String l_str, String l_strPattern) {

        if (l_str == null || l_strPattern == null) 
        {
            return false;
        }

        SimpleDateFormat l_dateFormat = new SimpleDateFormat(l_strPattern);
        l_dateFormat.setLenient(false);

        try 
        {
            l_dateFormat.parse(l_str);
            return true;
        } 
        catch (ParseException ex) 
        {
            return false;
        }
    }
    
    /**
     * ���l���t�H�[�}�b�g���܂��B
     *
     * @@param l_lngNum    ���l
     * @@param l_intLength ����
     * @@return �t�H�[�}�b�g�����������Ԃ��B
     */
    public static String formatNumber(long l_lngNum, int l_intLength) 
    {
 
        StringBuffer l_sb = new StringBuffer("" + l_lngNum);
 
        if (l_sb.length() >= l_intLength) 
        {
            return l_sb.toString();
        }
 
        int l_intSubLength = l_intLength - l_sb.length();
 
        for (int i = 0; i < l_intSubLength; i++) 
        {
            l_sb.insert(0, "0");
        }
 
        return l_sb.toString();
    }    

    /**
     * ���l���t�H�[�}�b�g���܂��B
     *
     * @@param l_dbl    ���l
     * @@return �t�H�[�}�b�g�����������Ԃ��B
     */
    public static synchronized String formatNumber(double l_dbl)
    {
        if (Double.isNaN(l_dbl))
        {
            return null;
        }
        
        // -0.0 -> 0
        if (l_dbl == 0)
        {
            l_dbl = Math.abs(l_dbl);
        }

        decimalFormat.applyPattern(DEFAULT_DOUBLE_FORMAT_PATTERN);
        decimalFormat.setMaximumFractionDigits(300);
        decimalFormat.setMinimumIntegerDigits(1);
        
        return decimalFormat.format(l_dbl);       
    }

    /**
     * (isMailAddress)
     * <BR>
     * �����񂪁A���[���A�h���X�ɓK�؂ȕ���(*1)�ō\������Ă��邩���`�F�b�N����B<BR>
     * �K�؂ł���ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     *  <BR>
     * (*1) ���[���A�h���X�ɓK�؂ȕ����ȉ��̔��p������<BR>
     *   �g�p�\�ō\������A�f@@�f���P�܂܂�Ă��邱�ƁB<BR>
     *  <BR>
     * 0123456789abcdefghijklmnopqrstuvwxyz<BR>
     * ABCDEFGHIJKLMNOPQRSTUVWXYZ!#$&|^_.()-=~[ ]+/?* <BR>
     *  <BR>
     * �܂��A�f@@�f�͐擪�����C���������ł͂Ȃ����ƁB<BR>
     *  <BR>
     * @@param l_str ��̕�����
     * @@return boolean
     */   
    public static boolean isMailAddress(String l_str) 
    {
        final String MAIL_ADDRESS_CHAR = 
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!#$&|^_.()-=~[ ]+/?*@@";
        
        final char CHAR_A = '@@';
            
        if((l_str == null) || (l_str.length() < 1))
        {
            return false;
        }
        
        //�f@@�f�͐擪�����C���������ł͂Ȃ�����
        if((l_str.charAt(0) == CHAR_A) || (l_str.charAt(l_str.length() - 1) == CHAR_A))
        {
            return false;
        }
        
        //���[���A�h���X�ɓK�؂ȕ����ȉ��̔��p������
        //�g�p�\�ō\������A�f@@�f���P�܂܂�Ă��邱��
        int l_intSize = l_str.length();
        for(int i = 0 ; i < l_intSize ; i ++)
        {
            if(MAIL_ADDRESS_CHAR.indexOf(l_str.charAt(i)) == -1)
            {
                return false;
            }
        }
        
        int l_intFirstIndex = l_str.indexOf(CHAR_A);
        int l_intLastIndex = l_str.lastIndexOf(CHAR_A);
        if((l_intFirstIndex == -1) || (l_intLastIndex == -1) || (l_intFirstIndex != l_intLastIndex))
        {
            return false;
        }
        
        return true;
    }
    

    /**
     * �S�p�������ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͑S�p�����Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isMulti(char l_ch)
    {
        switch (l_ch) {
            case 0xff3c:return true;
            case 0xff5e:return true;
            case 0x2225:return true;
            case 0xff0d:return true;
            case 0xffe0:return true;
            case 0xffe1:return true;
            case 0xffe2:return true;
        }

        char l_chConv = WEB3StringTypeUtility.convert(l_ch);

        String l_str = new Character(l_chConv).toString();
        byte[] l_bytes = l_str.getBytes();
        if (l_bytes.length > 1)
        {
            return true;
        }
        return false;
    }
    
    /**
     * �����񂪑S�p�����݂̂ō\������Ă��邩���`�F�b�N����B<BR>
     * �S�p�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return �S�p�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B 
     */
    public static boolean isMulti(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isMulti(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * (isWbyteString)
     * �����񂪑S�p�����݂̂ō\������Ă��邩���`�F�b�N����B<BR>
     * �S�p�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return �S�p�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B 
     */
    public static boolean isWbyteString(String l_str) 
    {
        return isMulti(l_str);
    }
    
    /**
     * �S�p�J�i�i�Љ����j�������ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͑S�p�J�i�i�Љ����j�����Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isWbyteKanaChar(char l_ch) 
    {
        return (l_ch >= zenkakukatakanaStart && l_ch <= zenkakukatakanaEnd);
    }
    
    /**
     * (isWbyteKanaString)
     * �����񂪑S�p�J�i�i�Љ����j�����݂̂ō\������Ă��邩���`�F�b�N����B<BR>
     * �S�p�J�i�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return boolean
     */
    public static boolean isWbyteKanaString(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isWbyteKanaChar(l_ch)) 
            {
                return false;
            }
        }
        return true;
        
    }  
    
    /**
     * ���p�J�i���ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͔��p�J�i�Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean is1byteKanaChar(char l_ch) 
    {
        return (l_ch >= hankakuKatakanaStart && l_ch <= hankakuKatakanaEnd);
    }
    
    /**
     * (is1byteKanaString)
     * �����񂪔��p�J�i�����݂̂ō\������Ă��邩���`�F�b�N����B<BR>
     * ���p�J�i�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return boolean
     */
    public static boolean is1byteKanaString(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!is1byteKanaChar(l_ch)) 
            {
                return false;
            }
        }
        return true;
        
    } 
    
    /**
     * (has1byteKana)
     * ������ɔ��p�J�i���������݂��邩�ǂ������`�F�b�N����B<BR>
     * ���p�J�i���������݂��Ȃ��ꍇ��false���A<BR>
     * �����łȂ��ꍇ��true��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return boolean
     */
    public static boolean has1byteKana(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (is1byteKanaChar(l_ch)) 
            {
                return true;
            }
        }
        
        return false;
        
    }
       
    
    /**
     * ���p�������ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͔��p�����Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isSingle(char l_ch)
    {
        String l_str = new Character(l_ch).toString();
        byte[] l_bytes = l_str.getBytes();
        if (l_bytes.length == 1)
        {
            return true;
        }
            
        return false;
    }
    
    /**
     * �����񂪔��p�����݂̂ō\������Ă��邩���`�F�b�N����B<BR>
     * ���p�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * @@param l_str ��̕�����
     * @@return ���p�����݂̂̏ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B 
     */
    public static boolean isSingle(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingle(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }

    /**
     * ���p�������ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͔��p�����Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isSingleNum(char l_ch) 
    {
        return (l_ch >= numberStart && l_ch <= numberEnd);
    }
    
    /**
     * �S�p�������ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͑S�p�����Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isWbyteNum(char l_ch) 
    {
        return (l_ch >= zenNumberStart && l_ch <= zenNumberEnd);
    }
    
    /**
     * ���p�p�����ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͔��p�p���Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isSingleEng(char l_ch) 
    {
        if (l_ch >= upperEnglishStart && l_ch <= upperEnglishEnd) 
        {
            return true;
        } 
        else if (l_ch >= lowerEnglishStart && l_ch <= lowerEnglishEnd) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * �S�p�p�����ǂ������f���܂��B
     *
     * @@param l_ch ����
     * @@return ���͂��ꂽc�͑S�p�p���Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isWbyteEng(char l_ch) 
    {
        if (l_ch >= zenUpperEnglishStart && l_ch <= zenUpperEnglishEnd) 
        {
            return true;
        } 
        else if (l_ch >= zenLowerEnglishStart && l_ch <= zenLowerEnglishEnd) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * ������null�܂��͋󕶎����𔻒f���܂��B
     *
     * @@param l_str ��̕�����
     * @@return ���͂��ꂽstr��Null�Ȃ�Atrue��Ԃ��B�����łȂ���false��Ԃ��B
     */
    public static boolean isEmpty(String l_str) 
    {
        return (l_str == null || "".equals(l_str));
    }
    
    /**
     * ������null�A�󕶎��܂��͋󔒂��𔻒f���܂��B
     *
     * @@param l_str ��̕�����
     * @@return ���͂��ꂽstr��null�A�󕶎��܂��͋󔒂Ȃ�A<BR>
     *    true��Ԃ��B�����łȂ���false��Ԃ��B<BR>
     */
    public static boolean isEmptyOrBlank(String l_str) 
    {
        return (l_str == null || "".equals(l_str.trim()));
    }

    /**
     * ������null�łȂ����󕶎��łȂ����𔻒f���܂��B
     *
     * @@param str ��̕�����
     * @@return ���͂��ꂽstr��Null�Ȃ�Afalse��Ԃ��B�����łȂ���true��Ԃ��B
     */
    public static boolean isNotEmpty(String l_str)
    {
        return (l_str != null && !"".equals(l_str));
    }
    
    /**
     * (isPhoneNumber)<BR>
     *�����񂪓d�b�ԍ��i�܂��́A�g�єԍ��j�Ƃ��Đ������l���𔻒肷��B<BR>
     *�ȉ��̏����ɓ��Ă͂܂�ꍇtrue�A�ǂꂩ���<BR>
     *�ł����Ă͂܂�Ȃ���false��ԋp����B<BR>
     * <BR>
     * [true�̏���]<BR>
     * �� '-'�Ɛ����ȊO�̕������܂܂�Ȃ����ƁB<BR>
     * �� '-'���Q�܂܂�邱�ƁB<BR>
     * �� �擪�����C����������'-'�łȂ����ƁB<BR>
     * @@param l_str ��̕�����<BR>
     * @@return boolean <BR>
     */
    public static boolean isPhoneNumber(String l_str)
    {
        if (isEmpty(l_str))
        {
            return false;
        }
        
        //�� '-'�Ɛ����ȊO�̕������܂܂�Ȃ����ƁB
        int l_intCnt = 0;
        int l_intLength = l_str.length();
        for (int i = 0; i < l_intLength; i++)
        {
            char l_ch = l_str.charAt(i);
            if ((isSingleNum(l_ch) == false) && ('-' != l_ch))
            {
                return false;
            }
            
            if ('-' == l_ch)
            {
                l_intCnt ++;
            }
            
        }   
        
        //�� '-'���Q�܂܂�邱��
        if (l_intCnt != 2)
        {
            return false;
        }
        
        //�� �擪�����C����������'-'�łȂ�����
        char l_chBegin = l_str.charAt(0);
        char l_chEnd = l_str.charAt(l_intLength - 1);
        if(l_chBegin == '-' || l_chEnd == '-')
        {
            return false;
        }
        
        return true;
        
    }

    /**
     * (isZipCode)
     *�����񂪗X�֔ԍ��Ƃ��Đ������l���𔻒肷��B<BR>
     *�ȉ��̏����ɓ��Ă͂܂�ꍇtrue�A�ǂꂩ��ł����Ă�<BR>
     *�܂�Ȃ���false��ԋp����B<BR>
     *<BR>
     *[true�̏���]<BR>
     *�� ���p�����ȊO�̕������܂܂�Ȃ����ƁB<BR>
     *�� ��������7byte�ł��邱�ƁB<BR>
     * @@param l_str ��̕�����<BR>
     * @@return boolean <BR>
     */
    public static boolean isZipCode(String l_str)
    {
        if (isEmpty(l_str))
        {
            return false;
        }
        
        //�� ��������7byte�ł��邱�ƁB
        int l_intBytesLength = l_str.getBytes().length;
        if(l_intBytesLength != 7)
        {
            return false;
        }
        
        //�� ���p�����ȊO�̕������܂܂�Ȃ�����
        int l_intLength = l_str.length();
        for (int i = 0; i < l_intLength; i++)
        {
            char l_ch = l_str.charAt(i);
            if (isSingleNum(l_ch) == false)
            {
                return false;
            }
        }
        
        return true;
        
    }

    /**
     * (to1byteKana)<BR>
     * �S�p�J�i�C�S�p�p����������𔼊p�J�i�C���p�p����������ɕϊ�����B<BR>
     * �i�y�Ώە����ϊ��\�z�ɊY�����镶����ΏۂƂ���j<BR>
     *  <BR>
     * �y�Ώە����ϊ��\�z<BR>
     *  <BR>
     * ���  �i�S�p�j    �i���p�j<BR>
     * -----------------------------<BR>
     * <BR>
     * �J�i   �A�`��       ��`� <BR>
     *         �K�`�S       �ށ`�� <BR>
     *         �U�`�]       �ށ`�� <BR>
     *         �_�`�h       �ށ`��   <BR>
     *         �o�`�{      �ށ`��<BR>
     *         ��            ��   <BR>
     *         �p�`�|      �߁`�� <BR>
     *         �@@�`�H       ��`� <BR>
     *         ���A��        ��A� <BR>
     *         �b             �    <BR>
     *         ���A���A��    ԁAՁA� <BR>
     *         ��             � <BR>
     *         �[            -  <BR>
     * �p��  �`�`�y       A�`Z <BR>
     *         ���`��        a�`z <BR>
     * ����  �O�`�X       0�`9 <BR>
     * �L��  �|           -  <BR>
     *          �i            ( <BR>
     *          �j            ) <BR>
     * �X�y�[�X"�@@"      " " <BR>
     * <BR>
     * @@param l_strWbyteKana - �S�p�J�i����<BR>
     * @@return String <BR>
     */
    public static String to1byteKana(String l_strWbyteKana) 
    {
        if (isEmpty(l_strWbyteKana))
        {
            return l_strWbyteKana;
        }
        
        String l_strReturn = "";
        int l_intLength = l_strWbyteKana.length();
        for(int i = 0; i < l_intLength; i++)
        {
            l_strReturn = l_strReturn + to1byteKana(l_strWbyteKana.charAt(i));
        }
   
        return l_strReturn;
    }
    
    /**
     * �S�p�J�i�C�S�p�p����������𔼊p�J�i�C���p�p����������ɕϊ�����B<BR>
     * �i�y�Ώە����ϊ��\�z�ɊY�����镶����ΏۂƂ���j<BR>
     *  <BR>
     * �y�Ώە����ϊ��\�z<BR>
     *  <BR>
     * ���  �i�S�p�j    �i���p�j<BR>
     * -----------------------------<BR>
     * <BR>
     * �J�i   �A�`��       ��`� <BR>
     *         �K�`�S       �ށ`�� <BR>
     *         �U�`�]       �ށ`�� <BR>
     *         �_�`�h       �ށ`��   <BR>
     *         �o�`�{      �ށ`�� <BR>
     *         ��            ��   <BR>
     *         �p�`�|      �߁`�� <BR>
     *         �@@�`�H       ��`� <BR>
     *         ���A��        ��A� <BR>
     *         �b             �    <BR>
     *         ���A���A��    ԁAՁA� <BR>
     *         ��             � <BR>
     *         �[            -  <BR>
     * �p��  �`�`�y       A�`Z <BR>
     *         ���`��        a�`z <BR>
     * ����  �O�`�X       0�`9 <BR>
     * �L��  �|           -  <BR>
     *          �i            ( <BR>
     *          �j            ) <BR>
     * �X�y�[�X"�@@"      " " <BR>
     * <BR>
     * @@param l_chWbyteKana - �S�p�J�i����<BR>
     * @@return String <BR>
     */
    private static String to1byteKana(char l_chWbyteKana)
    {
        char l_chWbyteKanaConv = WEB3StringTypeUtility.convert(l_chWbyteKana);

        int l_intLength = multi.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
            if(multi[i] == l_chWbyteKanaConv)
            {
                l_intToken = i;
                break;
            }
        }
        String l_strReturn;
        if(l_intToken == l_intLength)
        {
            l_strReturn = String.valueOf(l_chWbyteKanaConv);
        }
        else
        {
            char[] l_ch  = single[l_intToken];
            if(l_ch.length == 1)
            {
                l_strReturn = String.valueOf(l_ch[0]);
            }
            else
            {
                l_strReturn = String.valueOf(l_ch[0]) + String.valueOf(l_ch[1]);
            }
        }
        
        return l_strReturn;
    }
    
    /**
     * (toUpperWbyteKana)<BR>
     *  <BR>
     * �S�p�J�i�i�������ˑ啶���j�J�i�ϊ����s���B<BR>
     *  <BR>
     * �P�j�@@�������J�i�i�@@�C�B�C�D�C���j��������Ɋ܂܂��΁A<BR>
     * �啶���J�i�ɕϊ�����B�i�������S�p�J�i�łȂ������͕ϊ����Ȃ��j<BR>
     *  <BR>
     * ��j�@@<BR>
     * �@@�@@���@@�A<BR>
     * ���@@���@@��<BR>
     * �D�@@���@@�E<BR>
     * <BR>
     * @@param l_strWbyteKana - �S�p�J�i����<BR>
     * @@return String <BR>
     */
    public static String toUpperWbyteKana(String l_strWbyteKana) 
    {
        if (isEmpty(l_strWbyteKana))
        {
            return l_strWbyteKana;
        }
        
        String l_strReturn = "";
        int l_intLength = l_strWbyteKana.length();
        for(int i = 0; i < l_intLength; i++)
        {
            l_strReturn = l_strReturn + toUpperFullKana(l_strWbyteKana.charAt(i));
        }
   
        return l_strReturn;
        
    }
    
    /**
     * �S�p�J�i�i�������ˑ啶���j�J�i�ϊ����s���B<BR>
     *  <BR>
     * �P�j�@@�������J�i�i�@@�C�B�C�D�C���j��������Ɋ܂܂��΁A<BR>
     * �啶���J�i�ɕϊ�����B�i�������S�p�J�i�łȂ������͕ϊ����Ȃ��j<BR>
     *  <BR>
     * ��j�@@<BR>
     * �@@�@@���@@�A<BR>
     * ���@@���@@��<BR>
     * �D�@@���@@�E<BR>
     * <BR>
     * @@param l_chLowerFullKana - �S�p�J�i����<BR>
     * @@return char <BR>
     */
    private static char toUpperFullKana(char l_chLowerFullKana) 
    {
        int l_intLength = lowerFullKana.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
            if(lowerFullKana[i] == l_chLowerFullKana)
            {
                l_intToken = i;
                break;
            }
        }
        
        char l_chReturn;
        if(l_intToken == l_intLength)
        {
            l_chReturn = l_chLowerFullKana;
        }
        else
        {
            l_chReturn = upperFullKana[l_intToken];
        }
        
        return l_chReturn;
    }
    
    /**
     * ���p�J�i�i�������ˑ啶���j�J�i�ϊ����s���B<BR>
     *  <BR>
     * �P�j�@@�������J�i�i��C��C��C���j��������Ɋ܂܂��΁A<BR>
     * �啶���J�i�ɕϊ�����B�i���������p�J�i�łȂ������͕ϊ����Ȃ��j<BR>
     *  <BR>
     * ��j�@@<BR>
     * ��@@���@@�<BR>
     * ��@@���@@�<BR>
     * ��@@���@@�<BR>
     * <BR>
     * @@param l_chLowerHalfKana - ���p�J�i����<BR>
     * @@return char <BR>
     */
    private static char toUpperHalfKana(char l_chLowerHalfKana) 
    {
        int l_intLength = lowerHalfKana.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
            if(lowerHalfKana[i] == l_chLowerHalfKana)
            {
                l_intToken = i;
                break;
            }
        }
        
        char l_chReturn;
        if(l_intToken == l_intLength)
        {
            l_chReturn = l_chLowerHalfKana;
        }
        else
        {
            l_chReturn = upperHalfKana[l_intToken];
        }
        
        return l_chReturn;
    }
    
    /**
     * (toUpper1byteKana)
     * <BR>
     * �J�i�ϊ����s���B<BR>
     * <BR>
     * �P�j�@@�S�p�J�i������𔼊p�J�i������ɕϊ�����B<BR>
     * �i�S�p�J�i�łȂ������͕ϊ����Ȃ��j<BR>
     * @@see WEB3StringTypeUtility.to1byteKana()<BR>
     * <BR>
     * �Q�j�@@�������J�i�i��C��C��C���j��������Ɋ܂܂��΁A<BR>
     * �啶���J�i�ɕϊ�����B�i�������J�i�łȂ������͕ϊ����Ȃ��j<BR>
     * <BR>
     * ��j�@@<BR>
     * ��@@���@@�<BR>
     * ��@@���@@�<BR>
     * ��@@���@@�<BR>
     * <BR>
     * @@param l_strWbyteKana - �S�p�J�i����<BR>
     * @@return String <BR>
     */
    public static String toUpper1byteKana(String l_strWbyteKana) 
    {
        if (isEmpty(l_strWbyteKana))
        {
            return l_strWbyteKana;
        }
        
        //�P�j�@@�S�p�J�i������𔼊p�J�i������ɕϊ�����B
        String l_strChange = to1byteKana(l_strWbyteKana);
        
        // �Q�j�@@�������J�i�i��C��C��C���j��������Ɋ܂܂��΁A
        //�啶���J�i�ɕϊ�����B
        String l_strReturn = "";
        int l_intLength = l_strChange.length();
        for(int i = 0; i < l_intLength; i++)
        {
            l_strReturn = l_strReturn + toUpperHalfKana(l_strChange.charAt(i));
        }
        
        return l_strReturn;
        
    }
    
    /**
     * (createHashValue)
     * <BR>
     * �n���ꂽ�����iString�̔z��j�̑S�Ă�p���ăn�b�V���l���擾����B<BR>
     * <BR>
     * �P�j�@@����.�v�Z�Ώۂ̒��g�����ɘA�����A�����byte�̔z��ɕϊ�����B<BR>
     * <BR>
     * �Q�j�@@MessageDigest�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *   [MessageDigest.getInstance�ɓn������]<BR>
     *   algorithm������.�v�Z����<BR>
     * <BR>
     * �R�j�@@�P�j�ō쐬�����z��������ɁA<BR>
     * MessageDigest�I�u�W�F�N�g.update()���R�[������B<BR>
     * <BR>
     * �S�j�@@MessageDigest�I�u�W�F�N�g.digest()���R�[������B<BR>
     * �T�j�@@digest()�̖߂�l���P�v�f�������ɂ��āA<BR>
     *  hexDigit()���\�b�h���R�[������B<BR>
     * �U�j�@@�S�Ă�toHexDigit()���\�b�h�̖߂�l��A�����ĕԋp����B<BR>
     * <BR>
     * @@param l_strAlgorithm - (�v�Z����)<BR>
     * @@param l_algorithmObj - (�v�Z�Ώ�)<BR>
     * @@return String <BR>
     */
    public static String createHashValue(String l_strAlgorithm, String[] l_algorithmObj) 
    {
        //�P�j�@@����.�v�Z�Ώۂ̒��g�����ɘA�����A�����byte�̔z��ɕϊ�����
        int l_intLength = l_algorithmObj.length;
        String l_strAlgorithmObj = "";
        for(int i = 0; i < l_intLength; i++)
        {
            if (isNotEmpty(l_algorithmObj[i]))
            {
                l_strAlgorithmObj = l_strAlgorithmObj + l_algorithmObj[i];
            }
        }
        byte[] l_byteAlgorithmObj = l_strAlgorithmObj.getBytes();
        
        //�Q�j�@@MessageDigest�I�u�W�F�N�g���擾����B
        MessageDigest l_messageDigest = null;
        try
        {
            l_messageDigest = MessageDigest.getInstance(l_strAlgorithm);
        }
        catch(NoSuchAlgorithmException nsae)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                "WEB3StringTypeUtility.createHashValue(String, String[])",
                nsae.getMessage(),
                nsae);
        }
        
        //�R�j�@@�P�j�ō쐬�����z��������ɁAMessageDigest�I�u�W�F�N�g.update()���R�[������B
        l_messageDigest.update(l_byteAlgorithmObj);
        
        //�S�j�@@MessageDigest�I�u�W�F�N�g.digest()���R�[������
        StringBuffer l_sbResult = new StringBuffer();
        byte[] l_byteResult = l_messageDigest.digest();

        //�T�j�@@digest()�̖߂�l���P�v�f�������ɂ��āAhexDigit()���\�b�h���R�[������B
        //�U�j�@@�S�Ă�toHexDigit()���\�b�h�̖߂�l��A�����ĕԋp����B
        for (int i = 0; i < l_byteResult.length; i++)
        {
            l_sbResult.append(toHexDigit(l_byteResult[i]));
        }

        return l_sbResult.toString();
    }
    
    private static String toHexDigit(byte x)
    {
        StringBuffer sb = new StringBuffer();
        char c;
        c = (char) ((x >> 4) & 0xf);
        if (c > 9)
        {
            c = (char) ((c - 10) + 'a');
        }
        else
        {
            c = (char) (c + '0');
        }
        sb.append(c);
        c = (char) (x & 0xf);
        if (c > 9)
        {
            c = (char) ((c - 10) + 'a');
        }
        else
        {
            c = (char) (c + '0');
        }
        sb.append(c);
        return sb.toString();
    }
    
    public static boolean isWbyteCrString(String l_str)
    {
        if (isEmpty(l_str))
        {

            return false;
        }

        for (int i = 0; i < l_str.length(); i++)
        {
            char l_ch = l_str.charAt(i);
            if (!isMulti(l_ch))
            {
                if ((l_ch != '\n') && (l_ch != '\r'))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * JIS -> Unicose String �ϊ�����<br />
     * <br />
     * ~  0x007E       0x007E[TILDE]<br />
     * \  0x005C       0x005C[REVERSE SOLIDUS]<br />
     * �_ 0x815F       0xFF3C[FULLWIDTH REVERSE SOLIDUS]<br />
     * �` 0x8160       0x301C[WAVE DASH]<br />
     * �a 0x8161       0x2016[DOUBLE VERTICAL LINE]<br />
     * �| 0x817C       0x2212[MINUS SIGN]<br />
     * �� 0x8191       0x00A2[CENT SIGN]<br />
     * �� 0x8192       0x00A3[POUND SIGN]<br />
     * �� 0x81CA       0x00AC[NOT SIGN]<br />
     * <br />
     * @@param c �ϊ��O����
     * @@return �ϊ��㕶��
     */
    public static char convert(char c)
    {
        char res = c;
        switch (c) {
        case 0x005c:    // REVERSE SOLIDUS ->
        res = 0xff3c; // FULLWIDTH REVERSE SOLIDUS
        break;
        case 0x301c:    // WAVE DASH ->
        res = 0xff5e; // FULLWIDTH TILDE
        break;
        case 0x2016:    // DOUBLE VERTICAL LINE ->
        res = 0x2225; // PARALLEL TO
        break;
        case 0x2212:    // MINUS SIGN ->
        res = 0xff0d; // FULLWIDTH HYPHEN-MINUS
        break;
        case 0x00a2:    // CENT SIGN ->
        res = 0xffe0; // FULLWIDTH CENT SIGN
        break;
        case 0x00a3:    // POUND SIGN ->
        res = 0xffe1; // FULLWIDTH POUND SIGN
        break;
        case 0x00ac:    // NOT SIGN ->
        res = 0xffe2; // FULLWIDTH NOT SIGN
        }
        return res;
    }

    /**
     * JIS -> Unicose String �ϊ�����<br />
     * <br />
     * ~  0x007E       0x007E[TILDE]<br />
     * \  0x005C       0x005C[REVERSE SOLIDUS]<br />
     * �_ 0x815F       0xFF3C[FULLWIDTH REVERSE SOLIDUS]<br />
     * �` 0x8160       0x301C[WAVE DASH]<br />
     * �a 0x8161       0x2016[DOUBLE VERTICAL LINE]<br />
     * �| 0x817C       0x2212[MINUS SIGN]<br />
     * �� 0x8191       0x00A2[CENT SIGN]<br />
     * �� 0x8192       0x00A3[POUND SIGN]<br />
     * �� 0x81CA       0x00AC[NOT SIGN]<br />
     * <br />
     * @@param s �ϊ��O������
     * @@return �ϊ��㕶����
     */
    public static String convert(String s)
    {
        if (s == null)
        {
            return null;
        }
        
        StringBuffer sb = new StringBuffer();
        char c;
        for (int i = 0; i < s.length(); i++) {
            char res  = WEB3StringTypeUtility.convert(s.charAt(i));
            sb.append(res);
        }
        return new String(sb);
    }

    /**
     * String�^�C�v�̐��l���v���X���}�C�i�X���𔻒f<BR>
     * <BR>
     * @@param String ���l
     * @@return boolean true �}�C�i�X
     */
    public static boolean isMinus(String l_strDoubleValue)
    {
        double l_dblValue = 0.0;
        boolean l_isMinus = false;
        
        Double l_value = new Double(l_strDoubleValue);
        l_dblValue = l_value.doubleValue();
        
        if(l_dblValue < 0)
        {
            l_isMinus = true;
        }
        
        return l_isMinus;  
    }

    /**
     * (toWbyteKana)<BR>
     * ���p�J�i�C���p�p�����������S�p�J�i�C�S�p�p����������ɕϊ�����B<BR> 
     * �i�y�Ώە����ϊ��\�z�ɊY�����镶����ΏۂƂ���j<BR>
     * <BR>
     * �y�Ώە����ϊ��\�z<BR>
     * <BR>
     * ��� �i�S�p�j �i���p�j<BR>
     * -----------------------------<BR>
     * �J�i �A�`�� ��`� <BR>
     * �K�`�S �ށ`�� <BR>
     * �U�`�] �ށ`�� <BR>
     * �_�`�h �ށ`�� <BR>
     * �o�`�{ �ށ`�� <BR>
     * �� �� <BR>
     * �p�`�| �߁`�� <BR>
     * �@@�`�H ��`� <BR>
     * ���A�� ��A� <BR>
     * �b � <BR>
     * ���A���A�� ԁAՁA� <BR>
     * �� � <BR>
     * �[ - <BR>
     * �p�� A�`Z A�`Z <BR>
     * ���`�� a�`z <BR>
     * ���� �O�`�X 0�`9 <BR>
     * �L�� �| - <BR>
     * �i ( <BR>
     * �j ) <BR>
     * �X�y�[�X "�@@"   " " <BR>
     * <BR>
     * @@param l_strSingleByte - ���p�J�i����<BR>
     * @@return String <BR>
     */
    public static String toWbyteKana(String l_strSingleByte) 
    {
        if (isEmpty(l_strSingleByte))
        {
            return l_strSingleByte;
        }

        //�߂�l
        String l_strMultiReturn = "";
        int l_intLength = l_strSingleByte.length();
        for(int i = 0; i < l_intLength; i++)
        {
        	boolean l_blnFlag = true;
        	boolean l_blnMaruFlag = true;
        	int l_intIndex = i + 1;

        	if(l_intIndex < l_intLength)
        	{
            	//�ށ`�ށA�ށ`�ށA�ށ`�ށA�ށ`�ށA�ނ̏ꍇ
        		if(l_strSingleByte.charAt(l_intIndex) == '�')
        		{
            		l_blnFlag = false;
        		}

        		//�߁`�߂̏ꍇ
        		if(l_strSingleByte.charAt(l_intIndex) == '�')
        		{
        			l_blnMaruFlag = false;
        		}
        	}

        	//�߂�l = �߂�l + ���p��S�p�ɕϊ���̕�����
        	l_strMultiReturn = l_strMultiReturn + toWbyteKana(l_strSingleByte.charAt(i), l_blnFlag, l_blnMaruFlag);

        	//'�'�܂���'�'���܂ݏꍇ�A�X�M�b�v�ł��B
        	if(!l_blnFlag || !l_blnMaruFlag)
        	{
        		i++;
        	}
        }

        return l_strMultiReturn;
    }

    /**
     * ���p�J�i�C���p�p�����������S�p�J�i�C�S�p�p����������ɕϊ�����B<BR> 
     * �i�y�Ώە����ϊ��\�z�ɊY�����镶����ΏۂƂ���j<BR>
     * <BR>
     * �y�Ώە����ϊ��\�z<BR>
     * <BR>
     * ��� �i�S�p�j �i���p�j<BR>
     * -----------------------------<BR>
     * �J�i �A�`�� ��`� <BR>
     * �K�`�S �ށ`�� <BR>
     * �U�`�] �ށ`�� <BR>
     * �_�`�h �ށ`�� <BR>
     * �o�`�{ �ށ`�� <BR>
     * �� �� <BR>
     * �p�`�| �߁`�� <BR>
     * �@@�`�H ��`� <BR>
     * ���A�� ��A� <BR>
     * �b � <BR>
     * ���A���A�� ԁAՁA� <BR>
     * �� � <BR>
     * �[ - <BR>
     * �p�� A�`Z A�`Z <BR>
     * ���`�� a�`z <BR>
     * ���� �O�`�X 0�`9 <BR>
     * �L�� �| - <BR>
     * �i ( <BR>
     * �j ) <BR>
     * �X�y�[�X "�@@"   " " <BR>
     * <BR>
     * @@param l_chSingleByte - ���p�J�i����<BR>
     * @@param l_blnFlag - ރt���O<BR>
     * @@param l_blnMaruFlag - ߃t���O<BR>
     * @@return String <BR>
     */
    private static String toWbyteKana(char l_chSingleByte, boolean l_blnFlag, boolean l_blnMaruFlag)
    {
        char l_chSingleByteConv = WEB3StringTypeUtility.convert(l_chSingleByte);

        int l_intLength = single.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
        	//���p�̏ꍇ�F
            if(l_blnFlag && l_blnMaruFlag && single[i][0] == l_chSingleByteConv)
            {
                l_intToken = i;
                break;
            }

            //���p + ���p�̏ꍇ
            //�ށ`�ށA�ށ`�ށA�ށ`�ށA�ށ`�ށA�ނ̏ꍇ
            if (!l_blnFlag && single[i].length == 2
            	&& single[i][0] == l_chSingleByteConv
            	&& single[i][1] == '�')
            {
                l_intToken = i;
                break;
            }

            //�߁`�߂̏ꍇ
            if (!l_blnMaruFlag && single[i].length == 2
            	&& single[i][0] == l_chSingleByteConv
            	&& single[i][1] == '�')
            {
                l_intToken = i;
                break;
            }
        }

        //�߂�l
        String l_strMultiReturn;
        //�S�p�̏ꍇ�A�S�p��߂�B
        if(l_intToken == l_intLength)
        {
        	l_strMultiReturn = String.valueOf(l_chSingleByteConv);
        }
        //���p�̏ꍇ�A�S�p�ɕϊ�����B
        else
        {
        	l_strMultiReturn = String.valueOf(multi[l_intToken]);
        }

        return l_strMultiReturn;
    }

    /**
     * ������̃o�C�g�����擾����B<BR>
     * isMulti���\�b�h���g�p����1�������S�p�����p���𔻒f���A<BR>
     * �Z�o�����o�C�g����ԋp����B<BR>
     * <BR>
     * @@param l_str - (������)
     * @@return int
     */
    public static int getFixedByteLength(String l_str)
    {
        if (isEmpty(l_str)) 
        {
            return 0;
        }

        int l_intLenght = 0; 
        for (int i = 0; i < l_str.length(); i++)
        {
            //isMulti���\�b�h���g�p����1�������S�p�����p���𔻒f��
            char l_ch = l_str.charAt(i);
            if (isMulti(l_ch)) 
            {
                l_intLenght = l_intLenght + 2;
            }
            else
            {
                l_intLenght = l_intLenght + 1;
            }
        }

        //�Z�o�����o�C�g����ԋp����B
        return l_intLenght;
    }

    /**
     * �����񂪔��p�����Ɣ��p�s���I�h"."�ō\������Ă��邩���`�F�b�N����B<BR>
     * �Y������ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B<BR>
     * <BR>
     * @@param l_str - (������)
     * @@return boolean
     */
    public static boolean isIpAddress(String l_str)
    {
        if (isEmpty(l_str))
        {
            return false;
        }

        //�����񂪔��p�����Ɣ��p�s���I�h"."�ō\������Ă��邩���`�F�b�N����B
        int l_intLength = l_str.length();
        for (int i = 0; i < l_intLength; i++)
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleNum(l_ch) && '.' != l_ch)
            {
                //�����łȂ��ꍇ��false��Ԃ��B
                return false;
            }
        }

        //�Y������ꍇ��true��Ԃ��B
        return true;
    }
}@
