head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatHelper�N���X(WEB3CacheStatHelper.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.handler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * �L���b�V�����v�v���O�C���̃w���p�[�N���X
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3CacheStatHelper
{
    
    /**
     * �X���b�h�Z�[�t��<code>NumberFormat</code>�̃��|�W�g��
     */
    private final ThreadLocal threadLocalNumberFormats = new ThreadLocal()
    {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };
    
    /**
     * <code>NumberFormat</code>���g���Đ��l�𕶎���Ƀt�H�[�}�b�g����B
     * 
     * @@param l_lngValue �t�H�[�}�b�g���鐔�l
     * @@param l_strPattern �p�^�[��
     * @@return �w�肵�����l���t�H�[�}�b�g����������
     */
    String format(long l_lngValue, String l_strPattern)
    {
        return getNumberFormat(l_strPattern).format(l_lngValue);
    }

    /**
     * <code>NumberFormat</code>���g���Đ��l�𕶎���Ƀt�H�[�}�b�g����B
     * 
     * @@param l_lngValue �t�H�[�}�b�g���鐔�l
     * @@param l_strPattern �p�^�[��
     * @@return �w�肵�����l���t�H�[�}�b�g����������
     */
    String format(double l_dblValue, String l_strPattern)
    {
        return getNumberFormat(l_strPattern).format(l_dblValue);
    }
    
    /**
     * �X���b�h�Z�[�t��<code>NumberFormat</code>���擾����B
     * 
     * @@param l_strPattern �p�^�[��
     * @@return �X���b�h�Z�[�t��<code>NumberFormat</code> 
     */
    NumberFormat getNumberFormat(String l_strPattern)
    {
        Map l_numberFormats = (Map) threadLocalNumberFormats.get();
        NumberFormat l_nf = (NumberFormat) l_numberFormats.get(l_strPattern);
        if (l_nf == null)
        {
            l_nf = new DecimalFormat(l_strPattern);
            l_numberFormats.put(l_strPattern, l_nf);
        }
        return l_nf;
    }

}
@
