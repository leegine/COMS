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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 文字列の暗号化と復号を行うクラス(WEB3Crypt.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import webbroker3.util.WEB3LogUtility;

/**
 * 文字列の暗号化と復号を行う。
 * 
 * バッチにも同じクラスがあります。
 * 当クラスの修正は、バッチ側のクラスにも反映してください。
 * バッチ側の内容については、「w3jCrypt詳細設計書.doc」を
 * 参照してください。
 *
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public class WEB3Crypt
{
    /**
     * 変換の名前。
     */
    private final static String TRANSFORMATION = "DES";

    /**
     * 文字列を暗号化して生成したバイト列を文字列に置き換えるためのテーブル。
     */
    private final static char[] CODES = {'!', '#', '$', '&', '(', ')', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>'};

    /**
     * 秘密鍵データ。
     */
    private final static byte[] DES_KEY_DATA = {(byte)0x2C, (byte)0xAA, (byte)0xF2, (byte)0x55, (byte)0xEF, (byte)0xAF, (byte)0x86, (byte)0x6E};

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3Crypt.class);

    /**
     * 秘密鍵。
     */
    private SecretKeySpec key;

    /**
     * 暗号化および復号化の機@能を提供するオブジェクト。
     */
    private Cipher cipher;

    /**
     * コンストラクタ。
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
     * 文字列の暗号化を行う。
     * 引数が暗号化した文字列の場合は、その文字列をそのまま返す。
     *
     * @@param l_strPlane 暗号化したい文字列
     * @@return 暗号化した文字列
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

        // 暗号化を行う
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
     * 暗号文字列の復号化を行う。
     * 引数が暗号化した文字列でない場合は、その文字列をそのまま返す。
     *
     * @@param l_strEncrypt 暗号化した文字列
     * @@return 復号化した文字列
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
            // 暗号化した文字列ではない（暗号化した文字列の長さは必ず偶数）
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
                // 暗号化した文字列ではない（暗号化文字コード以外が含まれている）
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
                // 暗号化した文字列ではない（暗号化文字コード以外が含まれている）
                return l_strEncrypt;
            }
            i++;
            j++;
        }

        // 復号化を行う
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
     * 暗号化したバイトの値をコードに変換し、文字列バッファ@に追加する。
     * 
     * @@param l_btValue 暗号化したバイト
     * @@param l_sbBuf 文字列バッファ@
     */
    private void byte2code(byte l_btValue, StringBuffer l_sbBuf)
    {
        int l_intHigh = ((l_btValue & 0xf0) >> 4);
        int l_intLow = (l_btValue & 0x0f);
        l_sbBuf.append(CODES[l_intHigh]);
        l_sbBuf.append(CODES[l_intLow]);
    }

    /**
     * 暗号化したバイト列をコード文字列に変換して返す。
     * 
     * @@param l_btArray 暗号化したバイト列
     * @@return コード文字列
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
