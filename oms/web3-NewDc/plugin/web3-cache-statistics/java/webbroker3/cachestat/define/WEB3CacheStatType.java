head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatType�N���X(WEB3CacheStatType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.define;


/**
 * ���v���̎�ނ�\���萔�N���X
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public interface WEB3CacheStatType
{
    
    /**
     * �S���
     */
    public static final int ALL = 0;
    
    /**
     * �T�C�Y���
     */
    public static final int SIZE = 1;
    
    /**
     * �q�b�g��
     */
    public static final int HIT_RATE = 2;
    
    /**
     * �C���o���f�[�V�������
     */
    public static final int INVALIDATION = 3;
    
    /**
     * �^�C�~���O���
     */
    public static final int TIMING = 4;

}
@
