head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.22.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DigestKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3DigestKey�N���X(WEB3DigestKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/6/07 ��(FLJ) �V�K�쐬
*/
package webbroker3.login.service.delegate;

/**
 *  WEB3�ɕK�v�ȃZ�L�����e�B�[�L�[��ۑ�����B
 * 
 *  @@author      ��(FLJ)
 *  @@version     1.0
 */
public class WEB3DigestKey implements java.io.Serializable
{
    
    public final static String TIME_FORMAT = "yyyyMMddHHmmss";
    
    /**�L�[1(����������b):  �t�H�[�}�b�g�FYYYYMMDDHHNNSS�@@*/
    private String key1 = null;
    
    /**�L�[2(�Œ蕶����):  HimawariWEBBROKER3�@@*/
    private final static String key2 = "HimawariWEBBROKER3";
    
    /**�L�[3(GUID):  �B��̕W���̕�����@@�@@�@@32���啶���̉p�����@@*/
    private String key3 = null;
    
    /**�L�[4(SHA1�R�[�h):  40���̕����E������@@*/
    private String key4 = null;
    

    /**
     * @@return �L�[1(����������b)��߂��܂��B
     */
    public String getKey1()
    {
        return key1;
    }
    /**
     * @@param key1 �L�[1(����������b)��ݒ肵�܂��B
     */
    public void setKey1(String l_key1)
    {
        this.key1 = l_key1;
    }
    /**
     * @@return key2 �L�[2(�Œ蕶����)��߂��܂��B
     */
    public String getKey2()
    {
        return key2;
    }

    /**
     * @@return �L�[3(GUID) ��߂��܂��B
     */
    public String getKey3()
    {
        return key3;
    }
    /**
     * @@param key3 �L�[3(GUID) ��ݒ�B
     */
    public void setKey3(String l_key3)
    {
        this.key3 = l_key3;
    }
    /**
     * @@return �L�[4(SHA1�R�[�h)��߂��܂��B
     */
    public String getKey4()
    {
        return key4;
    }
    /**
     * @@param key4 �L�[4(SHA1�R�[�h)��ݒ�B
     */
    public void setKey4(String l_key4)
    {
        this.key4 = l_key4;
    }

    
}
@
